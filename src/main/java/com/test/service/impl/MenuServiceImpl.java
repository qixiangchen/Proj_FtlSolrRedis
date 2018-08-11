package com.test.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;
import com.test.mapper.MenuMapper;
import com.test.service.IMenuService;

@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IMenuService.class)
@Service
public class MenuServiceImpl implements IMenuService{
	private Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private MenuMapper mapper;
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public List<MenuInfo> findMenu(String name) {
		return mapper.findMenu(name);
	}
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public PageInfo getPageMenu(String name,Integer page,Integer rows) {
		System.out.println("name="+name+",page="+page+",rows="+rows);
		PageHelper.startPage(page, rows);
		List<MenuInfo> menuList = findMenu2(name);
		PageInfo pi = new PageInfo(menuList);
		return pi;
	}

	@Override
	public List<StaffInfo> findStaff() {
		return mapper.findStaff();
	}

	@Transactional
	@Override
	public MenuInfo saveMenu(MenuInfo mi) {
		mapper.saveMenu(mi);
		return mi;
	}

	@Override
	public void updateMenu(MenuInfo mi) {
		mapper.updateMenu(mi);
	}

	@Override
	public void deleteMenu(Integer mid) {
		mapper.deleteMenu(mid);
	}

	@Override
	public void saveM2S(M2SInfo m2s) {
		mapper.saveM2S(m2s);
	}

	@Override
	public void deleteM2S(Integer mid) {
		mapper.deleteM2S(mid);
	}

	@Override
	public List<MenuInfo> findMenu2(String name) {
		return mapper.findMenu2(name);
	}

	@Override
	public MenuInfo getMenuById(Integer mid) {
		return mapper.getMenuById(mid);
	}

}
