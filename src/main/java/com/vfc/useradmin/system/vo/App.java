package com.vfc.useradmin.system.vo;


import com.alibaba.fastjson.JSON;
import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

@TableEntry(tableName="platform.SM_APP_T3")
public class App extends BaseVO{
	
	@PKField(pkGenerator=PkGeneratorEnum.SEQUENCE,sequenceName="")
	@ColumnField(columnName="appID1")
	private Long appID;
	
	@ColumnField(columnName="appName1")
	private String appName;
	
	@ColumnField(columnName="appCode1")
	private String appCode;
	
	@ColumnField(columnName="icon1")
	private String icon;
	
	@ColumnField(columnName="valid1")
	private Integer valid;
	
	public Long getAppID() {
		return appID;
	}
	public void setAppID(Long appID) {
		this.appID = appID;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	} 
	
	
}
