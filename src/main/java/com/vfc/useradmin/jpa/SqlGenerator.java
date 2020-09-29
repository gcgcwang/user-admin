package com.vfc.useradmin.jpa;


import com.vfc.useradmin.jpa.sql.OrderBy;
import com.vfc.useradmin.jpa.sql.SqlSet;
import com.vfc.useradmin.jpa.sql.TableAsso;
import com.vfc.useradmin.jpa.sql.Where;
import com.vfc.useradmin.system.vo.Pager;


public interface SqlGenerator {

	
	String genSQL_findByID(Class<?> clazz, Where pk_where);
	
	
	String genSQL_findList(Class<?> clazz, Where where, OrderBy orderby);
	
	String genSQL_findList(Class<?> clazz, Where where);
	
	String genSQL_findList(String tableName, Where where, OrderBy orderby);
	
	String genSQL_findList(String tableName, Where where);

	
	String genSQL_findAll(Class<?> clazz);
	
	String genSQL_findAll(Class<?> clazz, OrderBy orderby);
	
	String genSQL_findAll(String tableName);
	
	String genSQL_findAll(String tableName, OrderBy orderby);
	
	String genSQL_findAll_cascade(Class<?> clazz);

	
	String genSQL_selectTotal(String tableName, Where where);
	
	String genSQL_selectTotal(Class<?> clazz, Where where);


	String genSQL_selectPageList(Class<?> clazz, Pager<?> pager, Where where, OrderBy orderby, String dbType);


	String genSQL_insert(Class<?> clazz, SqlSet sql_set);

	String genSQL_getSeq(Class<?> clazz);

	EntryMapper getEntryMapper();
	
	String genSQL_update(Class<?> clazz, SqlSet sql_set);
	
	String genSQL_delete(Class<?> clazz, Where pk_where);


	
	
}
