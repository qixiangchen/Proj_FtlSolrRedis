package com.test.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.bean.M2TInfo;
import com.test.bean.MenuInfo;
import com.test.bean.TypeInfo;
import com.test.mapper.M2TMapper;
import com.test.mapper.MenuMapper;
import com.test.mapper.TypeMapper;
import com.test.service.IM2TService;
import com.test.service.IMenuService;
import com.test.service.ITypeService;

@Service
public class M2TServiceImpl extends ServiceImpl<M2TMapper,M2TInfo>
	implements IM2TService
{

}
