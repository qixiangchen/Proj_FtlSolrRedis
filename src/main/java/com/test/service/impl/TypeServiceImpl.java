package com.test.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.bean.MenuInfo;
import com.test.bean.TypeInfo;
import com.test.mapper.MenuMapper;
import com.test.mapper.TypeMapper;
import com.test.service.IMenuService;
import com.test.service.ITypeService;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper,TypeInfo>
	implements ITypeService
{

}
