package com.shopping.model;

public class Admin {

	private String adminId;
	private String password;
	private String name;
	
	//생성자
	public Admin(String adminId, String password, String name) {
		//super();
		this.adminId = adminId;
		this.password = password;
		this.name = name;
	}

	//getter
	public String getAdminId() {
		return adminId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("관리자 ID: %s, 관리자 이름: %s", adminId, name);
	}
	
}
