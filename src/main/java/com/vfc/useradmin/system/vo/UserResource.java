package com.vfc.useradmin.system.vo;

import java.io.Serializable;
import java.util.List;

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.JoinColumn;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.enums.JoinTypeEnum;

@TableEntry(tableName="SYS_R_USER_RESOURCE")
public class UserResource implements Serializable{
	@PKField
	@ColumnField(columnName="user_id")
	private Long userId;
	@PKField
	@ColumnField(columnName="resource_id")
	private Long resourceId;
	
	@JoinColumn(refName="user_id", name = "user_id",joinType=JoinTypeEnum.LEFT_JOIN, refTableName="SYS_USER")
	private SysUser user;
	
	@JoinColumn(refName="id", name = "resource_id",joinType=JoinTypeEnum.LEFT_JOIN, refTableName="SYS_RESOURCE")
	private SysResource sysResource;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	
}
