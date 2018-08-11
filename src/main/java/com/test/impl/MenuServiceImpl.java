package com.test.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.bean.MenuInfo;
import com.test.mapper.MenuMapper;
import com.test.service.IMenuService;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,MenuInfo>
	implements IMenuService
{

}
