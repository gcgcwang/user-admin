package com.vfc.useradmin.jpa;

import java.util.List;
import java.util.Map;


import com.vfc.useradmin.jpa.sql.OrderBy;
import com.vfc.useradmin.jpa.sql.SqlSet;
import com.vfc.useradmin.jpa.sql.Where;
import com.vfc.useradmin.system.vo.Pager;

public interface JpaService {

	<T> T findById(Class<T> clazz, Long id);
	
	<T> T findById(Class<T> clazz, Where pks_where);
	
	<T> List<T> findList(T inputDto);
	
	<T> List<T> findList(Class<T> clazz, Where where, OrderBy orderby);

	<T> List<T> findList(Class<T> clazz, Where where);

	List<Map<String,Object>> findList(String tableName, Where where, OrderBy orderby);
	
	
	List<Map<String,Object>> findCascededAll(Class<?> clazz);

	<T> List<T> findCascededAllList(Class<T> clazz);
	 
	 
	List<Map<String,Object>> findList(String tableName, Where where);
	

	<T> List<T> findAll(Class<T> clazz);

	<T> List<T> findAll(Class<T> clazz, OrderBy orderby);
	

	List<Map<String,Object>> findAll(String tableName);

	List<Map<String,Object>> findAll(String tableName, OrderBy orderby);
	
	
	
	

	<T> Pager<T> findPager(Class<T> clazz, Pager<T> pager, Where where);

	<T> Pager<T> findPager(Class<T> clazz, Pager<T> pager);

	<T> Pager<T> findPager(Class<T> clazz, Pager<T> pager, Where where,
                           OrderBy orderby);

	
	Long selectTotal(String tableName, Where where);

	Long selectTotal(Class<?> clazz, Where where);
	

	<T> List<T> selectPageList(Class<T> clazz, Pager<T> pager, Where where,
                               OrderBy orderby);

	int insert(Object entry);
	
	int insert(Class<?> clazz, SqlSet sql_set);
	
	int update(Object entry);
	
	int update(Class<?> clazz, SqlSet sql_set);
	
	long insertUseKey(Object t);
	
	long insertUseKey(Class<?> clazz, SqlSet sql_set);

	int delete(Class<?> clazz, Long id);
	
	int delete(Class<?> clazz, Where pk_where);
	
	Long getSeq(Class<?> clazz);
}
