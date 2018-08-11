package com.test.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;
import com.test.service.IMenuService;
import com.test.util.PageUtil;

@Controller
public class MenuCtrl {
	@Reference(check=false)
	private IMenuService serv;
	@Autowired
	private SendMsg sendmsg;
	
	@RequestMapping("/test")
	public String test(HttpServletRequest req,Integer page,Integer rows)
	{
		return "add";
	}

	//跳转菜单列表的方法
	//接收前台参数page,rows
	@RequestMapping("/list")
	public String list(HttpServletRequest req,Integer page,Integer rows)
	{
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		String name = req.getParameter("name");
		return toListPage(req,page,rows,name);
	}
	
	@ResponseBody
	@RequestMapping("/list2")
	public List<MenuInfo> list2(HttpServletRequest req,Integer page,Integer rows)
	{
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		PageInfo pi = serv.getPageMenu("", page, rows);
		List<MenuInfo> menuList2 = pi.getList();//当前页显示记录集合
		return menuList2;
	}
	
	//删除方法，通过id删除菜单和中间表
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req,Integer page,Integer rows)
	{
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		String[] ids = req.getParameterValues("id");
		if(ids != null)
		{
			for(String id:ids)
			{
				Integer mid = new Integer(id);
				serv.deleteMenu(mid);
				serv.deleteM2S(mid);
			}
		}
		
		return toListPage(req,1,4,null);
	}
	
	//跳转到新增页面
	@RequestMapping("/add")
	public String add(HttpServletRequest req)
	{
		List<StaffInfo> staffList = serv.findStaff();
		req.setAttribute("staffList", staffList);
		return "add";
	}
	
	//跳转到修改页面
	@RequestMapping("/modify")
	public String modify(HttpServletRequest req)
	{
		String id = req.getParameter("id");
		Integer mid = new Integer(id);
		MenuInfo menu = serv.getMenuById(mid);
		req.setAttribute("menu", menu);
		String sid = menu.getSid();//1,2,3
		System.out.println("sid="+sid);
		List<StaffInfo> staffList = serv.findStaff();
		for(StaffInfo si:staffList)
		{
			String sid2 = si.getId().toString();
			if(sid.indexOf(sid2)>=0)
				si.setChecked("checked");
		}
		req.setAttribute("staffList", staffList);
		return "add";
	}
	
	//跳转到查看页面
	@RequestMapping("/view")
	public String view(HttpServletRequest req)
	{
		String id = req.getParameter("id");
		Integer mid = new Integer(id);
		MenuInfo menu = serv.getMenuById(mid);
		req.setAttribute("menu", menu);
		String sid = menu.getSid();//1,2,3
		System.out.println("sid="+sid);
		List<StaffInfo> staffList = serv.findStaff();
		for(StaffInfo si:staffList)
		{
			String sid2 = si.getId().toString();
			if(sid.indexOf(sid2)>=0)
				si.setChecked("checked");
		}
		req.setAttribute("staffList", staffList);
		return "view";
	}
	
	//保存菜单数据到Rabbitmq队列，返回到列表页面
	@RequestMapping("/save")
	public String save(HttpServletRequest req,MenuInfo mi)
	{
		sendmsg.send(mi);
		
		return toListPage(req,1,4,null);
	}
	
	//公共方法，统一返回到列表页面
	private String toListPage(HttpServletRequest req,Integer page,Integer rows,String name)
	{
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		if(name == null)
			name = "";
		
		System.out.println("page===="+page+",rows==="+rows+",name="+name);
		PageInfo pi = serv.getPageMenu(name, page, rows);
		List<MenuInfo> menuList2 = pi.getList();//当前页显示记录集合
		Long total = pi.getTotal();//数据库表全部记录数
		req.setAttribute("menuList", menuList2);
		System.out.println("menuList2===="+menuList2.size());
		String url = "/list";
		PageUtil pu = new PageUtil(url,page,rows,total);
		req.setAttribute("pageDiv", pu.toHtml());
		req.setAttribute("name", name);
		
		
		return "menu";
	}
}
