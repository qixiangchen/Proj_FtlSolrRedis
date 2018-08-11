package com.test.bean;

import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

//菜单与材料关联表，实现多对多关系
//MybatisPlus使用注解定义表名
@TableName("t_m2t")
public class M2TInfo implements Serializable{
	private Integer mid = null;
	private Integer tid = null;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}	
}
