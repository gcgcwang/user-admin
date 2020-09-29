package com.vfc.useradmin.system.security;

import java.io.Serializable;

import com.vfc.useradmin.system.vo.SysUser;

public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String loginName;
	private String password;
	private String realName;
	private String displayName;
	private String orgName;
	private String address;
	
	public Principal(SysUser user){
		this.userId=user.getUserId();
		this.loginName=user.getLoginName();
		this.password=user.getPassword();
		this.realName=user.getRealName();
		this.displayName=user.getDisplayName();
		this.orgName=user.getOrgName();
		this.address=user.getAddress();
	}

	public Long getUserId() {
		return userId;
	}


	public String getLoginName() {
		return loginName;
	}


	public String getPassword() {
		return password;
	}


	public String getRealName() {
		return realName;
	}

	public String getDisplayName() {
		return displayName;
	}


	public String getOrgName() {
		return orgName;
	}


	public String getAddress() {
		return address;
	}


	
}
