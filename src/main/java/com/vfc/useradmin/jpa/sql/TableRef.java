package com.vfc.useradmin.jpa.sql;

import com.vfc.useradmin.jpa.enums.JoinTypeEnum;

public class TableRef {

	private JoinTypeEnum joinType;
	private String tableName;
	private String tableAlia;
	private String[] pkColName; 
	
	private String refName;
	
	private TableRef pTableRef;  //父节点
	
	private Where where;
	private OrderBy orderBy;
	
	public TableRef(){};
	
	public TableRef(JoinTypeEnum joinType, String tableName,String tableAlia, String[] pkColName
			, String refName, TableRef pTableRef, Where where, OrderBy orderby){
		this.joinType=joinType;
		this.tableName=tableName;
		this.tableAlia=tableAlia;
		this.pkColName=pkColName;
		this.refName=refName;
		this.pTableRef=pTableRef;
		this.where=where;
		this.orderBy=orderby;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public JoinTypeEnum getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinTypeEnum joinType) {
		this.joinType = joinType;
	}

	public String getTableName(){
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String[] getPkColName() {
		return pkColName;
	}
	public void setPkColName(String[] pkColName) {
		this.pkColName = pkColName;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getTableAlia() {
		return tableAlia;
	}

	public void setTableAlia(String tableAlia) {
		this.tableAlia = tableAlia;
	}

	public TableRef getpTableRef() {
		return pTableRef;
	}

	public void setpTableRef(TableRef pTableRef) {
		this.pTableRef = pTableRef;
	}

	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderby) {
		this.orderBy = orderby;
	}

	
	
	
	
}
