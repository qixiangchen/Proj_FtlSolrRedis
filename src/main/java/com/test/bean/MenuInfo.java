package com.test.bean;

import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

//菜单表实体类
@TableName("t_menu")
public class MenuInfo implements Serializable{
	//定义主键策略
	@TableId(type=IdType.AUTO)
	private Integer id = null;
	private String name = null;
	private Date dt = null;
	private Float price = null;
	//定义非数据库字段，默认exist=true，代表是数据库字段
	@TableField(exist=false)
	private String tid = null;
	@TableField(exist=false)
	private String tname = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}

}
