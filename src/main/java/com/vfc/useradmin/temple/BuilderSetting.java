package com.vfc.useradmin.temple;

import java.util.List;

import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

public class BuilderSetting {

	private String tableName;
	private String className;

	private String packageName;//classes下的
	private String seqName;
	private PkGeneratorEnum pkGenType;
	
	private List<ColumnModel> columnModelList;


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public PkGeneratorEnum getPkGenType() {
		return pkGenType;
	}

	public void setPkGenType(PkGeneratorEnum pkGenType) {
		this.pkGenType = pkGenType;
	}

	public List<ColumnModel> getColumnModelList() {
		return columnModelList;
	}

	public void setColumnModelList(List<ColumnModel> columnModelList) {
		this.columnModelList = columnModelList;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
}
