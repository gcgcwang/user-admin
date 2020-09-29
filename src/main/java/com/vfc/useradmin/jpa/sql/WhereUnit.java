package com.vfc.useradmin.jpa.sql;

public class WhereUnit {
	
	private String colName;
	
	private String comparetor;
	
	private String linkSign;
	
	private Object colVal;

	public WhereUnit(){}
	
	public WhereUnit(String linkSign, String colName , String comparetor,Object colVal){
		this.linkSign=linkSign;
		this.colName=colName;
		this.comparetor=comparetor;
		this.colVal=colVal;
	}
	
	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}


	public Object getColVal() {
		return colVal;
	}

	public void setColVal(Object colVal) {
		this.colVal = colVal;
	}

	public String getComparetor() {
		return comparetor;
	}

	public void setComparetor(String comparetor) {
		this.comparetor = comparetor;
	}

	public String getLinkSign() {
		return linkSign;
	}

	public void setLinkSign(String linkSign) {
		this.linkSign = linkSign;
	}
	
	public StringBuffer toSqlString(boolean isNamed){
		 StringBuffer unit_Buffer = new StringBuffer(); 
		 unit_Buffer.append(" ").append(linkSign).append(" ").append(colName).append(" ").append(comparetor);
		 if("IN".equalsIgnoreCase(comparetor)){
			 unit_Buffer.append(" (").append(colVal).append(") ");;
		 }else{
			 if(isNamed){
				 unit_Buffer.append(" :"+colName+" ");
			 }else{
				 unit_Buffer.append(" ? ");
			 }
		 }
		 	
		 return unit_Buffer;
	}
	
}
