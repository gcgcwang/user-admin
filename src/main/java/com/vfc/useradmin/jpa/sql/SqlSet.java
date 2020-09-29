package com.vfc.useradmin.jpa.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.Transient;

public class SqlSet {
	

	private Map<String,Object> entryMap;
	private Map<String,Object> removedMap;

	private List<Object> valArr;
	
	private boolean isNamed = false;
	
	public SqlSet(){
		entryMap = new LinkedHashMap<String, Object>();
		removedMap = new LinkedHashMap<String, Object>();
		valArr = new  ArrayList<Object>();
	}
	

	public SqlSet(Object entry){
		this();
		initEntry(entry);
		this.isNamed=false;
	}
	
	public SqlSet(Object entry,boolean isNamed){
		this();
		initEntry(entry);
		this.isNamed=isNamed;
	}
	
	public Object add(String key,Object value){
		valArr.add(value);
		return entryMap.put(key, value);
	}
	
	public void putMap(Map<String,Object> map){
		Assert.notEmpty(map,"map不为空");
		Set<String> key_set =  map.keySet();
		Iterator<String> key_iter = key_set.iterator();
		while(key_iter.hasNext()){
			String key = key_iter.next();
			Assert.notNull(key, "key不为空");
			Object val = map.get(key);
			Assert.notNull(val, "val不为空");
			add(key,val);
		}
	}
	
	public Object remove(String key){
		Object val = entryMap.get(key);
		valArr.remove(val);
		removedMap.put(key, val);
		return entryMap.remove(key);
	}
	
	public Object get(String key){
		return entryMap.get(key);
	}
	
	public int size(){
		return entryMap.size();
	}
	
	
	
	public String toSqlString_update() {
		// TODO 
		Set<String> keySet = entryMap.keySet();
		 Assert.notEmpty(keySet,"keySet不为空");
		 Assert.notEmpty(valArr,"valArr不为空");
		 Assert.state(keySet.size()==valArr.size(),"keySet和valArr大小不相等");

		StringBuffer sql=new StringBuffer(" SET ");
		 
		Iterator<String> key_iter = keySet.iterator();
		while(key_iter.hasNext()){
			String key = key_iter.next();
			if(this.isNamed){
				sql.append(key).append("=").append(":").append(key).append(" , ");
			}else{
				sql.append(key).append("=").append("?").append(" , ");
			}
		}

		sql.deleteCharAt(sql.lastIndexOf(","));

		return sql.toString();
	}
	
	public String toSqlString_insert() {
		// TODO 
		StringBuffer insert_sql=new StringBuffer();
		
		Set<String> keySet = entryMap.keySet();
		
		 Assert.notEmpty(keySet,"keySet不为空");
		 Assert.notEmpty(valArr,"valArr不为空");
		 Assert.state(keySet.size()==valArr.size(),"keySet和valArr大小不相等");

		Iterator<String> key_iter = keySet.iterator();
		StringBuffer valStr=new StringBuffer(" VALUES (");

		insert_sql.append(" (");
		while(key_iter.hasNext()){
			String key = key_iter.next();
			insert_sql.append(key).append(",");
			if(this.isNamed){
				valStr.append(":").append(key).append(",");
			}else{
				valStr.append("?").append(",");
			}
		}
		insert_sql.deleteCharAt(insert_sql.lastIndexOf(","));
		valStr.deleteCharAt(valStr.lastIndexOf(","));
		valStr.append(") "); 
		insert_sql.append(") ").append(valStr);

		return insert_sql.toString();
	}

	private void initEntry(Object entry) {
		// TODO
		Assert.notNull(entry, "entry不为空");
		Assert.notNull(entryMap, "entryMap不为空");
		
		Class<?> entry_class = entry.getClass();
		Field[] fields = entry_class.getDeclaredFields();
		for(Field field : fields){
			
			Transient transientAnno=field.getAnnotation(Transient.class);
			if(transientAnno==null){
				ColumnField columnFieldAnno=field.getAnnotation(ColumnField.class);
			
				String field_name = field.getName();
				Assert.notNull(field_name, "field_name不为空");
				if(field_name.equalsIgnoreCase("serialVersionUID"))continue;
				String getMethodName="get"+StringUtil.toUpperFristChar(field_name);
					
				Method method=null;
				Object  val=null;
				try {
					method = entry_class.getDeclaredMethod(getMethodName);
					val= method.invoke(entry);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
				if(val!=null){
					if(columnFieldAnno!=null){
						String col_name = columnFieldAnno.columnName();
						Assert.notNull(col_name, "columnField.columnName未设置");
						 add(col_name, val);
					}else{
						  add(field.getName(), val);
					}
				}
			}//transientAnno==null
		}//for
	}

	public Map<String, Object> getEntryMap() {
		return entryMap;
	}

	public void setEntryMap(Map<String, Object> entryMap) {
		this.entryMap = entryMap;
	}


	public List<Object> getValArr() {
		return valArr;
	}

	public void setValArr(List<Object> valArr) {
		this.valArr = valArr;
	}
	
	public Map<String, Object> getRemovedMap() {
		return removedMap;
	}

	public void setRemovedMap(Map<String, Object> removedMap) {
		this.removedMap = removedMap;
	}

	public boolean isNamed() {
		return isNamed;
	}

	public void setNamed(boolean isNamed) {
		this.isNamed = isNamed;
	}
	
}
