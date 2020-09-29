package com.vfc.useradmin.core;

import java.util.LinkedHashMap;
import java.util.Map;

public class Params {
	
	/*登录用户名称*/
	public static final String SYSTEM_USER = "CURR_USER" ;			// 后台系统用户
	//上传图片对应索引类型
	
	
	public  static  final int isValid_history = -1 ;//历史（无效）
	public  static  final int isValid_notValid = 0 ;//无效
	public  static  final int isValid_valid = 1 ;//有效



	public static Map<Integer,String> validMap=new LinkedHashMap<Integer,String>();
	static{
		validMap.put(isValid_history, "历史");
		validMap.put(isValid_notValid, "无效");
		validMap.put(isValid_valid, "有效");
	}
	
	public  static  final int status_avail = 100 ;//有效
	public  static  final int status_unavail = -1 ;//失效的
	


	public static Map<Integer,String> statusMap=new LinkedHashMap<Integer,String>();
	static{
		statusMap.put(status_avail, "有效");
		statusMap.put(status_unavail, "失效");
	}
	
}
