package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;

@Mapper
public interface MenuMapper {
	public List<MenuInfo> findMenu(@Param("name") String name);
	public List<StaffInfo> findStaff();
	public void saveMenu(MenuInfo mi);
	public void updateMenu(MenuInfo mi);
	public void deleteMenu(@Param("id") Integer mid);
	public void saveM2S(M2SInfo m2s);
	public void deleteM2S(@Param("id") Integer mid);
	public List<MenuInfo> findMenu2(@Param("name") String name);
	public MenuInfo getMenuById(@Param("id") Integer mid);
}
