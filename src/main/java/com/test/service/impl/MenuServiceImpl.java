package com.test.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.bean.StaffInfo;
import com.test.mapper.MenuMapper;
import com.test.service.IMenuService;

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
	public List<StaffInfo> findStaff() {
		return mapper.findStaff();
	}

	@Transactional
	@Override
	public void saveMenu(MenuInfo mi) {
		mapper.saveMenu(mi);
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
