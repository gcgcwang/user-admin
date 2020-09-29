package com.vfc.useradmin.jpa;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.Assert;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.sql.OrderBy;
import com.vfc.useradmin.jpa.sql.TableAsso;
import com.vfc.useradmin.jpa.sql.Where;
import com.vfc.useradmin.jpa.sql.WhereUnit;
import com.vfc.useradmin.system.vo.Pager;


public abstract class GeneralSqlGenerator extends AbstractSqlGenerator{
	


	@Override
	public String genSQL_findByID(Class<?> clazz, Where pk_where) {
		// TODO 
		StringBuffer sql =new StringBuffer();

		sql.append(" SELECT ").append(" * ")
		.append(" FROM ").append(entryMapper.getEntryTableName(clazz));
		
		
		String[] pk_arrs=entryMapper.getPKFieldName(clazz);
		List<String> pk_list = Arrays.asList(pk_arrs);
		
		
		Assert.notNull(pk_where, "pk_where不为空");
		Assert.state(pk_list.size()==pk_where.size(),"pk_where条件需与pk_list大小不相符，请检查where条件和实体配置");
		
		Iterator<WhereUnit> iter = pk_where.iterator();
	
		while(iter.hasNext()){
			WhereUnit unit =iter.next();
			if(unit!=null){
				if("SINGLE_ID".equalsIgnoreCase(unit.getColName())){
					unit.setColName(pk_arrs[0]);
				}else{
					if(pk_list.indexOf(StringUtil.toLowerCase(unit.getColName())) == -1 ){
					//	iter.remove();
						Assert.state(false, "传入where的colName参数与实体配置不符合");
					}
					Assert.state("=".equals(StringUtil.trim(unit.getComparetor())), "whereUnit比较符号必须都为=号");
				}
			}
		}	
		
		sql.append(pk_where.toSqlString());
		
		return sql.toString();
	}

	
	
	@Override
	public String genSQL_findList(Class<?> clazz, Where where, OrderBy orderby) {
		// TODO Auto-generated method stub
		
		Assert.notNull(clazz, "clazz不能为空");

		return genSQL_findList(entryMapper.getEntryTableName(clazz),where,orderby);
	}

	@Override
	public String genSQL_findList(Class<?> clazz, Where where) {
		// TODO Auto-generated method stub
		return genSQL_findList(clazz,where,null);
	}

	@Override
	public String genSQL_findList(String tableName,Where where, OrderBy orderby) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer();
		
		sql.append(" SELECT ").append(" * ")
		.append(" FROM ").append(tableName);
		
		if(where!=null){
			sql.append(where.toSqlString());
		}
		if(orderby!=null){
			sql.append(orderby.toSqlString());
		}

		return sql.toString();
	}

	
	
	@Override
	public String genSQL_findList(String tableName,
			Where where) {
		// TODO Auto-generated method stub
		return genSQL_findList(tableName,where,null);
	}

	@Override
	public String genSQL_findAll(Class<?> clazz) {
		// TODO Auto-generated method stub
		return genSQL_findAll(entryMapper.getEntryTableName(clazz),null);
	}

	@Override
	public String genSQL_findAll(Class<?> clazz, OrderBy orderby) {
		// TODO Auto-generated method stub
		return genSQL_findAll(entryMapper.getEntryTableName(clazz),orderby);
	}

	@Override
	public String genSQL_findAll(String tableName) {
		// TODO Auto-generated method stub
		return genSQL_findAll(tableName,null);
	}

	
	@Override
	public String genSQL_findAll_cascade(Class<?> clazz) {
		// TODO Auto-generated method stub
		TableAsso table_asso = entryMapper.getTableAsso( clazz);
		return table_asso.toQuerySqlString();
	}
	
	
	@Override
	public String genSQL_findAll(String tableName, OrderBy orderby) {
		// TODO Auto-generated method stub
	StringBuffer sql =new StringBuffer();
		
		sql.append(" SELECT ").append(" * ")
		.append(" FROM ").append(tableName);
		
		if(orderby!=null){
			sql.append(orderby.toSqlString());
		}

		return sql.toString();
	}

	
	
	@Override
	public String genSQL_selectTotal(String tableName, Where where) {
		// TODO 
		StringBuffer sql =new StringBuffer();
		
		sql.append(" SELECT ").append(" count(*) ")
		.append(" FROM ").append(tableName);
		
		if(where!=null){
			sql.append(where.toSqlString());
		}

		
		return sql.toString();
	}

	@Override
	public String genSQL_selectTotal(Class<?> clazz, Where where) {
		// TODO 
		return genSQL_selectTotal(entryMapper.getEntryTableName(clazz),where);
	}


	@Override
	public String genSQL_selectPageList(Class<?> clazz, Pager<?> pager,
			Where where, OrderBy orderby,String dbType) {
		// TODO Auto-generated method stub
		return genSQL_selectPageList(entryMapper.getEntryTableName(clazz),pager,where,orderby,dbType);
	}
	
	public abstract String genSQL_selectPageList(String tableName, Pager<?> pager,
			Where where, OrderBy orderby,String dbType);




	
	
}
