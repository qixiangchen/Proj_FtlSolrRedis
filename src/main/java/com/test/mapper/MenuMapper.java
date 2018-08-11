package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;

//Mybatis接口主键，Springboot自动扫描
@Mapper
public interface MenuMapper {
	//根据菜单名称查询菜单结果，@Param("name")标注参数名可以使用在SQL语句中
	public List<MenuInfo> findMenu(@Param("name") String name);
	//返回全部材料列表
	public List<StaffInfo> findStaff();
	//保存菜单对象
	public void saveMenu(MenuInfo mi);
	//更新菜单对象
	public void updateMenu(MenuInfo mi);
	//根据菜单ID删除记录，@Param("id")标注参数名可以使用在SQL中
	public void deleteMenu(@Param("id") Integer mid);
	//保存中间表
	public void saveM2S(M2SInfo m2s);
	//根据菜单ID删除中间表
	public void deleteM2S(@Param("id") Integer mid);
	//返回多表关联查询数据
	public List<MenuInfo> findMenu2(@Param("name") String name);
	//根据菜单ID返回菜单对象，需要多表关联查询
	public MenuInfo getMenuById(@Param("id") Integer mid);
}

