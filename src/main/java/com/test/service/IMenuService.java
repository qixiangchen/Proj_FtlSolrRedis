package com.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;

public interface IMenuService {
	//根据菜单名称查询菜单结果
	public List<MenuInfo> findMenu(String name);
	//返回全部材料列表
	public List<StaffInfo> findStaff();
	//保存菜单对象
	public void saveMenu(MenuInfo mi);
	//更新菜单对象
	public void updateMenu(MenuInfo mi);
	//根据菜单ID删除记录
	public void deleteMenu(Integer mid);
	//保存中间表
	public void saveM2S(M2SInfo m2s);
	//根据菜单ID删除中间表
	public void deleteM2S(Integer mid);
	//返回多表关联查询数据
	public List<MenuInfo> findMenu2(String name);
	//根据菜单ID返回菜单对象，需要多表关联查询
	public MenuInfo getMenuById(Integer mid);
}
