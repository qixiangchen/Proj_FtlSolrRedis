package com.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.URLTemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {

	/**
	 * 此方法根据模板和数据在指定的目录生成静态页面，数据对象Map必须存在键值id，它对应的值是生成静态页面的文件名
	 * @param servletContext  Servlet上下文对象，可以通过request获得
	 * @param staticHtmlPath  生成静态网页的目录，它位于webapp目录下，通过浏览器地址可以访问到
	 * @param template  freemarker模板名称，它必须位于webapp/ftl目录下
	 * @param map  数据模型
	 */
	public static void generateHtml(ServletContext servletContext,
			String staticHtmlPath,String template,Map map)
	{
		try
		{
			//声明配置对象
			Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
			conf.setEncoding(Locale.getDefault(), "UTF-8");
			//声明模板加载器
			WebappTemplateLoader wtl = new WebappTemplateLoader(servletContext, "/ftl");
			//绑定到配置对象
			conf.setTemplateLoader(wtl);
			Template tmplt = conf.getTemplate(template);
			//根据模板与数据模型生成静态网页
			String path = servletContext.getRealPath("/"+staticHtmlPath);
			String file = path+"/"+map.get("id")+".html";
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			tmplt.process(map, bw);

			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
