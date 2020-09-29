package com.vfc.useradmin.core.util;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vfc.useradmin.system.vo.SysUser;

public class BeanMapUtil {

	public static Object convertBean(Class<?> clazz, Map map)throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		Object obj = clazz.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				descriptor.getWriteMethod().invoke(obj, value);
			}
		}
		return obj;
	}

	public static Map convertBean(Object bean,Map resmap)throws Exception{
		  Class type=bean.getClass();
		    resmap = (resmap==null?new HashMap(): resmap);
		    BeanInfo beanInfo = Introspector.getBeanInfo(type);
		     PropertyDescriptor[] propertyDescriptors = beanInfo
		    		 .getPropertyDescriptors();
		     for(PropertyDescriptor desc : propertyDescriptors){
		    	String propertyName =  desc.getName();
		    	if(!propertyName.equals("class")){
		    		Method read_method = desc.getReadMethod();
		    		Object result = read_method.invoke(bean);
	        		if (result!=null){
	        			resmap.put(propertyName,result);
	        		}else{
	        			resmap.put(propertyName,"");
	        		}
	        	}
		    }
		    return resmap;
		}
		   
/*	public static void main(String[] args) {
		SysUser user=new SysUser();
		user.setAddress("address");
		user.setUserID(121L);
		user.setDisplayName("displayName");
		try {
		System.out.println(convertBean(user,new HashMap<String,Object>()).keySet());
		Set sets = convertBean(user,new HashMap<String,Object>()).entrySet();
		Iterator<Object> iter = sets.iterator();
		while(iter.hasNext()){
			Object str =  iter.next();
			System.out.println(str);
		}
		System.out.println();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		SysUser user=new SysUser();
		user.setAddress("address");
		user.setUserId(121L);
		user.setDisplayName("displayName");
		JSONObject json = (JSONObject) JSON.toJSON(user);
		System.out.println(json);
		System.out.println(json.keySet());
		Object[] objArr = json.entrySet().toArray();
		for(Object obj:objArr){
			System.out.println(obj);

		}
		//System.out.println(json.entrySet().toArray() );

	}
	

}
