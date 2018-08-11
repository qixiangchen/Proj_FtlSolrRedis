package com.test.bean;

import java.io.Serializable;

//材料表实体类
public class StaffInfo  implements Serializable{
	private Integer id = null;
	private String name = null;
	//冗余字段，代表前台页面Checkbox控件是否选中，true为选中
	private String checked = "";
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

}
