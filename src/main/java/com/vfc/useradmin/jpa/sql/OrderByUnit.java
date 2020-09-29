package com.vfc.useradmin.jpa.sql;

public class OrderByUnit {

	private String  colName;
	
	private String asc;

	public OrderByUnit(){}
	
	public OrderByUnit(String colName,String asc){
		this.colName=colName;
		this.asc=asc;
	}
	
	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}
	
	public StringBuffer toSqlString(){
		StringBuffer sb = new StringBuffer();
		sb.append(colName).append(" ").append(asc);
		return sb;
	}
}
