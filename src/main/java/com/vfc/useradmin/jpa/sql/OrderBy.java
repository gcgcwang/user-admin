package com.vfc.useradmin.jpa.sql;

import java.util.Iterator;
import java.util.Vector;

import org.springframework.util.Assert;

public class OrderBy {

private Vector<OrderByUnit> orderByUnitList ;
	
	public OrderBy(){
		orderByUnitList = new Vector<OrderByUnit>() ;
	}
	
	public OrderBy add(String colName,String asc)
	{
		return add(new OrderByUnit(colName,asc));
	}
	
	public OrderBy add(OrderByUnit unit)
	{ 
		orderByUnitList.add(unit);
		return this;
	}
	
	public OrderByUnit get(int index)
	{
		return orderByUnitList.get(index);
	}
	
	public OrderByUnit set(int index,OrderByUnit unit)
	{
		if(index<0 || index >= size() ){
			throw new IndexOutOfBoundsException("set的index超出List的长度");
		}
		return orderByUnitList.set(index, unit);
	}
	
	public OrderByUnit remove(int index)
	{
		return orderByUnitList.remove(index);
	}
	
	public Iterator<OrderByUnit> iterator(){
		return orderByUnitList.iterator();
	}
	
	public boolean remove(OrderByUnit unit)
	{
		return orderByUnitList.remove(unit);
	}
	
	public int size()
	{
		return orderByUnitList.size();
	}
	
	public int indexOf(OrderByUnit unit)
	{
		return orderByUnitList.indexOf(unit);
	}
	
	
	public StringBuffer toSqlString(){
		
		StringBuffer OrderBy_Buffer=new StringBuffer(" ORDER BY  ");
		Iterator<OrderByUnit> iter=orderByUnitList.iterator();
		
		while(iter.hasNext()){
			OrderByUnit unit = iter.next();
			Assert.notNull(unit,"OrderByUnit不未空");
			
			OrderBy_Buffer.append(unit.toSqlString()).append(",");
			
		}
		OrderBy_Buffer.deleteCharAt(OrderBy_Buffer.lastIndexOf(","));
		return OrderBy_Buffer;
	}

	public Vector<OrderByUnit> getOrderByUnitList() {
		return orderByUnitList;
	}

	public void setOrderByUnitList(Vector<OrderByUnit> orderByUnitList) {
		this.orderByUnitList = orderByUnitList;
	}
	
	
}