package com.vfc.useradmin.temple;

import java.util.HashMap;
import java.util.Map;

public class JavaTypeMap {

	private static Map<String, String> mysql = new HashMap<String, String>();
	private static Map<String, String> oracle = new HashMap<String, String>();
	
	static {
		// MySQL类型映射
		mysql.put("varchar", "String");
		mysql.put("tinyint", "Integer");
		mysql.put("int", "Integer");
		mysql.put("bigint", "Long");
		mysql.put("smallint", "Integer");
		mysql.put("bigint", "Long");
		mysql.put("mediumtext", "String");
		mysql.put("text", "String");
		mysql.put("decimal", "BigDecimal");
		mysql.put("timestamp", "Date");
		mysql.put("datetime","Date");
		mysql.put("date","Date");
		mysql.put("float","Float");
		
		// Oracle类型映射
		oracle.put("nvarchar2", "String");
		oracle.put("varchar2", "String");
		oracle.put("integer","Integer");
		oracle.put("long","Long");
		oracle.put("clob", "String");
		oracle.put("blob", "String");
		oracle.put("number", "BigDecimal");
		oracle.put("timestamp","Date");
		oracle.put("date", "Date");
		oracle.put("char", "String");
	}
	
	public static String getJavaType(String databaseName, String columnType){
		if(databaseName.equalsIgnoreCase("mysql") ){
			return mysql.get(columnType.toLowerCase());
		} else if (databaseName.equalsIgnoreCase("oracle")){
			return oracle.get(columnType.toLowerCase());
		}
		return "";
	}
}
