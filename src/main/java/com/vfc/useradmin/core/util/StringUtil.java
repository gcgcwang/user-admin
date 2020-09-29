package com.vfc.useradmin.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vfc.useradmin.system.vo.SysUser;

public class StringUtil {

	public static  String toUpperCase(String str){
		return str==null?null:str.toUpperCase();
	}
	
	public static  String toLowerCase(String str){
		return str==null?null:str.toLowerCase();
	}
	
	public static  String formatter(String str){
		return str==null?"":str;
	}
	
	public static  String trim(String str){
		return str==null?"":str.trim();
	}
	
	public static String toUpperFristChar(String string) {
		char[] charArray = string.toCharArray();
		String fir_str= String.valueOf(charArray[0]).toUpperCase();
		charArray[0]=fir_str.charAt(0);
		return String.valueOf(charArray);
	}
	public static String toLowerFristChar(String str) {
		if(StringUtil.isEmpty(str)) return null;
		String sec_str = str.substring(1);
		String fir_str = String.valueOf(str.charAt(0));
		return fir_str.toLowerCase()+sec_str;
	}
	public static boolean isEmpty(String str) {
		return str==null || "".equals(str);
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static void main(String[] args) {
		 String table_alia_str="0abcdefghijklmnopqrstuywxyz";
		 String table_alia_numstr="0123456789";

		 Integer table_alia_str_index=1;
		 String table_alia ="";
			int num_index = table_alia_str_index / 26;
			int zm_index = table_alia_str_index % 26;
			 table_alia = String.valueOf(table_alia_str.charAt(zm_index))
					+ String.valueOf(table_alia_numstr.charAt(num_index));
			 
		System.out.println(table_alia);
	}
}
