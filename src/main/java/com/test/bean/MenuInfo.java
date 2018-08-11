package com.test.bean;

import java.io.Serializable;

//菜单表实体类
public class MenuInfo implements Serializable{
	private Integer id = null;
	private String name = null;
	private Integer price = null;
	//冗余字段，存放材料名称，以逗号分隔
	private String sname = null;
	//冗余字段，存放材料ID，以逗号分隔
	private String sid = null;
	
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

}
