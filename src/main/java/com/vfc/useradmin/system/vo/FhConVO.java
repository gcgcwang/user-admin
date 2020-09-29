package com.vfc.useradmin.system.vo;

import com.alibaba.fastjson.JSON;
import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;

@TableEntry(tableName="fh.FH_CONTRACT")
public class FhConVO {
	@PKField
	@ColumnField(columnName="cid")
	private String cid;
	@PKField
	private String cno;
	private String cba;
	private String clocation;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCba() {
		return cba;
	}
	public void setCba(String cba) {
		this.cba = cba;
	}
	public String getClocation() {
		return clocation;
	}
	public void setClocation(String clocation) {
		this.clocation = clocation;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	
}
