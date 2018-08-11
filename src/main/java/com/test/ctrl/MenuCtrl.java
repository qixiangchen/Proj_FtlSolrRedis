package com.test.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.bean.M2TInfo;
import com.test.bean.MenuInfo;
import com.test.bean.TypeInfo;
import com.test.service.IM2TService;
import com.test.service.IMenuService;
import com.test.service.ITypeService;
import com.test.util.FreeMarkerUtil;
import com.test.util.PageUtil;
import com.test.util.RedisTool;
import com.test.util.ResultInfo;
import com.test.util.SolrTool;

@Controller
@CacheConfig(cacheNames="menu")
public class MenuCtrl {
	@Autowired
	private IMenuService menuServ;
	@Autowired
	private ITypeService typeServ;
	@Autowired
	private IM2TService m2tServ;
	@Autowired
	private SolrTool solr;
	@Autowired
	private RedisTool redis;

	/**
	 * 通过数据库查询结果集数据，并且提供分页支持
	 * @param page 当前页码
	 * @param rows 每页显示记录数
	 * @param name 查询的关键字
	 * @return
	 */
	private ModelAndView initListDataDb(Integer page,Integer rows,String name)
	{
		//如果没有定义分页参数，初始化
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		if(name == null)
			name = "";
		ModelAndView mv = new ModelAndView();
		//定义MybatisPlus查询封装对象
		Wrapper<MenuInfo> wrapper = new EntityWrapper<MenuInfo>();
		//定义查询条件
		wrapper.like("name", "%"+name+"%");
		//定义分页对象
		Page<MenuInfo> p = new Page<MenuInfo>(page,rows);
		//查询分页对象
		Page pageObj = menuServ.selectPage(p,wrapper);
		List<MenuInfo> menuList = pageObj.getRecords();
		for(MenuInfo mi:menuList)
		{
			//定义MyBatisPlus查询封装对象
			Wrapper<M2TInfo> wrapper2 = new EntityWrapper<M2TInfo>();
			//定义查询条件
			wrapper2.eq("mid", mi.getId());
			//查询结果集
			List<M2TInfo> m2tLst = m2tServ.selectList(wrapper2);
			String tname = "";
			for(M2TInfo m2t:m2tLst)
			{
				TypeInfo ti = typeServ.selectById(m2t.getTid());
				tname = tname + ti.getName() + ",";
			}
			mi.setTname(tname);
		}
		//将查询结果集存放到ModelAndView对象中，以便前台页面读取
		mv.addObject("menuList", menuList);
		//定义返回的页面名称
		mv.setViewName("menu");
		//分页对应的URL
		String url = "/init?name="+name;
		//查询菜单记录总数
		Integer total = menuServ.selectCount(wrapper);
		PageUtil pu = new PageUtil(url,page,rows,total);
		//保存分页Html代码
		mv.addObject("pagediv", pu.toHtml());
		
		return mv;
	}

	/**
	 * 从Solr中查询符合条件的数据
	 * @param page 当前页码
	 * @param rows 每页显示记录数
	 * @param name 查询的关键字
	 * @return
	 */
	private ModelAndView initListDataSolr(Integer page,Integer rows,String name)
	{
		//如果没有定义分页参数，初始化
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		if(name == null)
			name = "";
		String query = "*:*";//"name:"+name;
		if(name != null && !"".equals(name))
			query = "name:"+name;
		ModelAndView mv = new ModelAndView();
		//转化当前页码为开始记录数
		Integer starts = (page-1)*rows;
		//从Solr中查询符合条件的数据
		ResultInfo<MenuInfo> result = solr.queryList(MenuInfo.class, query, starts, rows, "name");		
		mv.addObject("menuList", result.getList());
		mv.setViewName("menu");
		
		String url = "/init?name="+name;
		Integer total = result.getTotal().intValue();
		PageUtil pu = new PageUtil(url,page,rows,total);
		mv.addObject("pagediv", pu.toHtml());
		
		return mv;
	}

	//初始化URL
	@RequestMapping("/init")
	public ModelAndView init(Integer page,Integer rows,String name)
	{
		return initListDataSolr(page,rows,name);
	}
	
	//显示菜单页面，传人菜单ID
	@RequestMapping("/addmenu")
	public ModelAndView addmenu(Integer mid)
	{
		MenuInfo mi = new MenuInfo();
		if(mid != null)
		{
			mi = menuServ.selectById(mid);
		}
		ModelAndView mv = new ModelAndView();
		//定义MyBatisPlus查询封装对象
		Wrapper<TypeInfo> wrapper = new EntityWrapper<TypeInfo>();
		//查询分类列表
		List<TypeInfo> typeList = typeServ.selectList(wrapper);
		if(mid != null)
		{
			//定义MyBatisPlus查询封装对象
			Wrapper<M2TInfo> wrapper2 = new EntityWrapper<M2TInfo>();
			//定义查询条件
			wrapper2.eq("mid", mid);
			List<M2TInfo> m2tLst = m2tServ.selectList(wrapper2);
			//循环赋值分类选中状态，对应前台CheckBox选中状态，可以简化前台代码处理
			for(TypeInfo ti:typeList)
			{
				for(M2TInfo m2t:m2tLst)
				{
					if(ti.getId() == m2t.getTid())
						ti.setChecked("checked");
				}
			}
		}
		//将查询结果集存放到ModelAndView对象中，以便前台页面读取
		mv.addObject("menu", mi);
		mv.addObject("typeList", typeList);
		//返回页面名称
		mv.setViewName("addmenu");
		return mv;
	}

	//定义菜单保存方法，从前台接收参数封装为MenuInfo对象
	@RequestMapping("/savemenu")
	public ModelAndView savemenu(HttpServletRequest req,MenuInfo mi)
	{
		//首先从包装对象中取出分类ID，中间以逗号分隔的字符串，如果执行保存操作后，此属性的值清空
		String tid = mi.getTid();
		//调用MyBatisPlus的保存与更新方法
		menuServ.insertOrUpdate(mi);
		//根据菜单ID，删除中间表数据
		Map delMap = new HashMap();
		delMap.put("mid", mi.getId());
		m2tServ.deleteByMap(delMap);
		
		String tname = "";
		if(tid != null)
		{
			//分割菜单分类ID
			String[] dim = tid.split(",");
			for(String tid2:dim)
			{
				//创建中间表对象
				M2TInfo m2t = new M2TInfo();
				m2t.setMid(mi.getId());
				m2t.setTid(Integer.parseInt(tid2));
				m2tServ.insert(m2t);
				//记录分类名称，中间以逗号分隔，最后为菜单对象赋值，前台页面方可显示分类名称
				TypeInfo ti = typeServ.selectById(tid2);
				tname = tname + ti.getName() + ",";
			}
		}
		//定义数据对象，一定需要定义ID键
		Map m = new HashMap();
		m.put("id",mi.getId());
		m.put("name",mi.getName());
		m.put("dt",mi.getDt());
		m.put("price",mi.getPrice());
		m.put("tname",tname);

		//生成静态页面
		FreeMarkerUtil.generateHtml(req.getServletContext(), "html", "menu.html", m);
		//数据添加到Solr
		solr.addMap(m);
		
		return initListDataSolr(1,4,null);
	}

	/**
	 * 根据前台选中的菜单ID，删除菜单数据与中间表数据
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletemenu")
	public boolean deletemenu(Integer[] ids)
	{
		if(ids != null)
		{
			for(Integer id:ids)
			{
				menuServ.deleteById(id);
				Map delMap = new HashMap();
				delMap.put("mid", id);
				m2tServ.deleteByMap(delMap);
				
				solr.delete(id+"");
			}
		}
		return true;
	}
}
