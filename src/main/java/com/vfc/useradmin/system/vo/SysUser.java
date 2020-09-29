package com.vfc.useradmin.system.vo;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.vfc.useradmin.jpa.annotation.JoinColumn;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.enums.JoinTypeEnum;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import com.vfc.useradmin.system.security.Principal;

@TableEntry(tableName="SYS_USER")
public class SysUser extends BaseVO{
	
	@PKField(pkGenerator=PkGeneratorEnum.AUTO_INCREASING)
	@JoinColumn(refName="user_id",  name = "")
	private Long userId;
	private String loginName;
	private String password;
	private String realName;
	private String displayName;
	private String orgName;
	private String address;
	
	@JoinColumn(refName="user_id", name = "user_id",joinType=JoinTypeEnum.LEFT_JOIN,refTableName="SYS_R_USER_RESOURCE")
	private List<UserResource> userResourceList;
	
	public SysUser(){}
	
	public SysUser(Principal user){
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



	public void setUserId(Long userID) {
		this.userId = userID;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public String getDisplayName() {
		return displayName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	
}
