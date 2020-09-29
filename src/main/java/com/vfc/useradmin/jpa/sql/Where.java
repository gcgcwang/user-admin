package com.vfc.useradmin.jpa.sql;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.util.Assert;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.Transient;
import com.vfc.useradmin.system.vo.SysResource;

public class Where {
	
	
	private List<WhereUnit> whereUnitList ;
	private boolean isNamed = false ;
	
	public Where(){
		whereUnitList = new ArrayList<WhereUnit>() ;
	}
	
	public Where(boolean isNamed){
		this();
		this.isNamed=isNamed;
	}
	
	public Where(Object inputDto,boolean isNamed){
		this(isNamed);
		initEntry(inputDto);
	}
	
	public Where(Object inputDto){
		this();
		initEntry(inputDto);
	}
	
	public Where and(String colName ,String comparator ,Object colVal)
	{
		
		add(new WhereUnit("AND",colName,comparator,colVal));
		
		return this;
	}
	
	public Where or(String colName ,String comparator ,Object colVal)
	{
		add(new WhereUnit("OR",colName,comparator,colVal));
		return this;
	}
	
	public boolean add(WhereUnit unit)
	{
		return whereUnitList.add(unit);
	}
	
	
	
	public WhereUnit get(int index)
	{
		return whereUnitList.get(index);
	}
	
	public WhereUnit set(int index,WhereUnit unit)
	{
		if(index<0 || index >= size() ){
			throw new IndexOutOfBoundsException("setUnit的index超出List的长度");
		}
		return whereUnitList.set(index, unit);
	}
	
	public Iterator<WhereUnit> iterator(){
		return whereUnitList.iterator();
	}
	
	public WhereUnit remove(int index)
	{
		return whereUnitList.remove(index);
	}
	
	public boolean remove(WhereUnit unit)
	{
		return whereUnitList.remove(unit);
	}
	
	public int size()
	{
		return whereUnitList.size();
	}
	
	public int indexOf(WhereUnit unit)
	{
		return whereUnitList.indexOf(unit);
	}
	
	
	public StringBuffer toSqlString(){
		
		StringBuffer where_Buffer=new StringBuffer(" WHERE 1=1 ");
		Iterator<WhereUnit> iter=whereUnitList.iterator();
		
		while(iter.hasNext()){
			WhereUnit unit = iter.next();
			Assert.notNull(unit,"WhereUnit不未空");
			
			where_Buffer.append(unit.toSqlString(this.isNamed));
			
		}
		
		return where_Buffer;
	}
	
	public Object[] getParamterValArr(){
		List<Object> paras= new ArrayList<Object>(size());
		Iterator<WhereUnit> iter = whereUnitList.iterator();
		
		while(iter.hasNext()){
			WhereUnit unit =iter.next();
			if("IN".equalsIgnoreCase(unit.getComparetor())){
				continue;
			}
			paras.add(unit.getColVal());
		}
		return paras.toArray();
		
	}
	
	public Map<String,Object> getParamterMap(){
		Map<String,Object> para_map= new HashMap<String,Object>(size());
		Iterator<WhereUnit> iter = whereUnitList.iterator();
		
		while(iter.hasNext()){
			WhereUnit unit =iter.next();
			if("IN".equalsIgnoreCase(unit.getComparetor())){
				continue;
			}
			para_map.put( unit.getColName(),unit.getColVal());
		}
		return para_map;
		
	}
	
	private void initEntry(Object entry) {
		// TODO
		Assert.notNull(entry, "entry不为空");
		Assert.notNull(whereUnitList, "whereUnitList不为空");
		
		Class<?> entry_class = entry.getClass();
		try{
		     Field[] fields = entry_class.getDeclaredFields();
		     for(Field field : fields){
		    	 String field_name =  field.getName();
				if(field_name.equalsIgnoreCase("serialVersionUID"))continue;
		    	
				String read_method_name = "get"+StringUtil.toUpperFristChar(field_name);
				Method  read_method= entry_class.getDeclaredMethod(read_method_name);
	    		
				Object	field_val = read_method.invoke(entry);
	    		 if ( field_val != null ){
	 				ColumnField columnFieldAnno = field.getAnnotation(ColumnField.class);
	    			 if(columnFieldAnno == null){
	    				 and(field_name,"=",field_val);
	    			 }else{
	    				 and(columnFieldAnno.columnName(),"=",field_val);
	    			 }
        			
        		}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<WhereUnit> getWhereUnitList() {
		return whereUnitList;
	}

	public void setWhereUnitList(List<WhereUnit> whereUnitList) {
		this.whereUnitList = whereUnitList;
	}
	
	
}
