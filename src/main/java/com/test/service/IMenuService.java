package com.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;

public interface IMenuService {
	public List<MenuInfo> findMenu(String name);
	public List<StaffInfo> findStaff();
	public MenuInfo saveMenu(MenuInfo mi);
	public void updateMenu(MenuInfo mi);
	public void deleteMenu(Integer mid);
	public void saveM2S(M2SInfo m2s);
	public void deleteM2S(Integer mid);
	public List<MenuInfo> findMenu2(String name);
	public MenuInfo getMenuById(Integer mid);
	public PageInfo getPageMenu(String name,Integer page,Integer rows);
}
