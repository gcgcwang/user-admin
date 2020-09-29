package com.vfc.useradmin.temple;

import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

public class ColumnModel {
	
	private String columnName;
	private int isPK;
	private String columnType;
	private int columnTypeLen;
	private String columnMemo; //注释

	
	private String javaName;
	private String javaType;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getIsPK() {
		return isPK;
	}
	public void setIsPK(int isPK) {
		this.isPK = isPK;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public int getColumnTypeLen() {
		return columnTypeLen;
	}
	public void setColumnTypeLen(int columnTypeLen) {
		this.columnTypeLen = columnTypeLen;
	}
	public String getColumnMemo() {
		return columnMemo;
	}
	public void setColumnMemo(String columnMemo) {
		this.columnMemo = columnMemo;
	}
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	
	
}
