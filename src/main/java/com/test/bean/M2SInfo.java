package com.test.bean;

import java.io.Serializable;

//菜单关联材料的实体类
public class M2SInfo  implements Serializable{
	//菜单表ID
	private Integer mid = null;
	//材料表ID
	private Integer sid = null;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}
