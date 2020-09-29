package com.vfc.useradmin.jpa;

import java.util.List;
import java.util.Map;

import com.vfc.useradmin.jpa.sql.OrderBy;
import com.vfc.useradmin.jpa.sql.SqlSet;
import com.vfc.useradmin.jpa.sql.Where;
import com.vfc.useradmin.system.vo.Pager;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;


public  class DefaultJpaService extends AbstractJpaService{
	
	@Override
	public  <T> T findById(Class<T> clazz,Long id){
		
		Assert.notNull(id, "id不能为空");

		
		Where pk_where =new Where();
		pk_where.and("SINGLE_ID", "=", id);
		String sql=sqlGenerator.genSQL_findByID(clazz,pk_where);
		
		List<T> list =(List<T>) getJdbcOperator().query(sql, 
					 new BeanPropertyRowMapper<T>(clazz),id);
		
		 Assert.notNull(list, "List不为空");
		 Assert.state( list.size() <= 1, "期望的结果是一个，但是返回多个");

		return list.size()==0?null:list.iterator().next();
	}
	
	@Override
	public  <T> T findById(Class<T> clazz,Where pks_where){
		
		Assert.notNull(pks_where, "pks_where不能为空");

		String sql=sqlGenerator.genSQL_findByID(clazz,pks_where);
		Object[] val_arr = (pks_where==null?null:pks_where.getParamterValArr());
		
		List<T> list =(List<T>) getJdbcOperator().query(sql,val_arr,
					new BeanPropertyRowMapper<T>(clazz));
		
		 Assert.notNull(list, "List不为空");
		 Assert.state( list.size() <= 1, "期望的结果是一个，但是返回多个");

		return list.size()==0?null:list.iterator().next();
	}
	
	@Override
	public    <T> List<T> findList(Class<T> clazz,Where where,OrderBy orderby){
		
		Assert.notNull(clazz, "clazz不能为空");
		

		String sql=sqlGenerator.genSQL_findList(clazz, where, orderby);

		Object[] val_arr = (where==null?null:where.getParamterValArr());

		List<T> list =(List<T>) getJdbcOperator().query(sql,val_arr,
				new BeanPropertyRowMapper<T>(clazz));
		
		 Assert.notNull(list, "List不为空");

		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> findList(String tableName, Where where,
			OrderBy orderby) {
		// TODO 
		Assert.notNull(tableName, "tableName不能为空");

		String sql=sqlGenerator.genSQL_findList(tableName, where, orderby);

		Object[] val_arr = (where==null?null:where.getParamterValArr());

		List<Map<String, Object>> list = getJdbcOperator().query(sql,val_arr,
				new ColumnMapRowMapper());
		
		 Assert.notNull(list, "List不为空");

		return list;
	}
	
	@Override
	public List<Map<String, Object>> findCascededAll( Class<?> clazz) {
		// TODO 
		
		Assert.notNull(clazz, "clazz不能为空");

		String sql= sqlGenerator.genSQL_findAll_cascade(clazz);

	

	 List<Map<String, Object>> list = getJdbcOperator().query(sql, new Object[0],
			new ColumnMapRowMapper());
	
	//	 Assert.notNull(list, "List不为空");

		return list;
	}
	
	
	
	@Override
	public    <T> List<T> findList(Class<T> clazz,Where where){
		return findList(clazz,where,null);
	}
	
	@Override
	public    <T> List<T> findAll(Class<T> clazz){
		return findList(clazz,null,null);
	}
	
	@Override
	public <T> List<T> findAll(Class<T> clazz, OrderBy orderby) {
		// TODO 
		return findList(clazz,null,orderby);
	}
	
	
	
	@Override
	public    <T> Pager<T> findPager(Class<T> clazz,Pager<T> pager,Where where){
		return findPager(clazz,pager,where,null);
	}

	@Override
	public    <T> Pager<T> findPager(Class<T> clazz,Pager<T> pager){
		return findPager(clazz,pager,null,null);
	}
	
	@Override
	public    <T> Pager<T> findPager(Class<T> clazz,Pager<T> pager,Where where,OrderBy orderby){
		pager.setRows(selectPageList(clazz, pager, where, orderby));
		pager.setTotal(selectTotal(clazz,where));
		return pager;
	}

	
	@Override
	public  <T> List<T> selectPageList(Class<T> clazz,Pager<T> pager,Where where,OrderBy orderby) {
		// TODO 
		Assert.notNull(clazz, "clazz不能为空");
		
		
		String  sql = sqlGenerator.genSQL_selectPageList(clazz, pager, where, orderby,getDbType());
		Map<String,Object> para_map = (where==null?null:where.getParamterMap());
		
		List<T> list =(List<T>) namedJdbcTemplate.query(sql,para_map,
				new BeanPropertyRowMapper<T>(clazz));
		
		return list;
	}


	@Override
	public Long selectTotal(Class<?> clazz,Where where) {
		// TODO
		
		Assert.notNull(clazz, "clazz不能为空");
		
		String  sql = sqlGenerator.genSQL_selectTotal(clazz, where);
		
		Object[] val_arr = (where==null?null:where.getParamterValArr());
		
		Long tol =(Long) getJdbcOperator().queryForObject(sql, val_arr, Long.class);
		
		return tol;
	}







	@Override
	public Long selectTotal(String tableName, Where where) {
		// TODO 
		Assert.notNull(tableName, "tableName不能为空");
		
		String  sql = sqlGenerator.genSQL_selectTotal(tableName, where);
		
		Object[] val_arr = (where==null?null:where.getParamterValArr());
		
		Long tol =(Long) getJdbcOperator().queryForObject(sql, val_arr, Long.class);
		
		return tol;
	}





	@Override
	public List<Map<String, Object>> findList(String tableName, Where where) {
		// TODO 
		return findList(tableName, where, null);
	}

	@Override
	public List<Map<String, Object>> findAll(String tableName) {
		// TODO 
		return findAll(tableName, null);
	}

	
	@Override
	public List<Map<String, Object>> findAll(String tableName, OrderBy orderby) {
		// TODO 
		return findList(tableName, null, orderby);
	}
	
	
	
	@Override
	public int insert(Class<?> clazz,SqlSet sql_set) {
		// TODO 
		Assert.notNull(sql_set, "sql_set不能为空");
		Assert.notNull(clazz, "clazz不能为空");

		String sql=sqlGenerator.genSQL_insert(clazz,sql_set);
		
		Assert.notNull(sql_set,"sql_set不为空");

		Object[] val_arr = sql_set.getValArr().toArray();
		
		int res = getJdbcOperator().update(sql,val_arr);
		
		 Assert.state( res == 1, "期望插入一条，但是插入"+res+"条");

		return res;
	}
	
	
	@Override
	public  int  insert(Object entry){
		
		Assert.notNull(entry, "entry不能为空");

		SqlSet sql_set =new SqlSet(entry);
		
		Class<?> clazz = entry.getClass();
		
		return insert(clazz,sql_set);
	}

	@Override
	public Long getSeq(Class<?> clazz) {
		// TODO 
		Assert.state("oracle".equalsIgnoreCase(getDbType()), "当前连接的数据库"+getDbType());
		String sql = sqlGenerator.genSQL_getSeq(clazz);
		Long seq = getJdbcOperator().queryForObject(sql, Long.class);
		return seq;
	}


	@Override
	public long insertUseKey(Class<?> clazz, SqlSet sql_set) {
		// TODO 
		Assert.notNull(clazz, "clazz不能为空");
		Assert.notNull(sql_set, "sql_set不能为空");
		
		String sql=sqlGenerator.genSQL_insert(clazz,sql_set);
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
	//	SqlParameterSource  para_source= new BeanPropertySqlParameterSource(entry);
		Map<String,Object> entry_map = sql_set.getEntryMap();
		SqlParameterSource  para_source= new MapSqlParameterSource(entry_map);
        namedJdbcTemplate.update(sql, para_source, keyHolder,getEntryMapper().getPKFieldName(clazz));
        return keyHolder.getKey().longValue();
		
	}
	
	@Override
	public long insertUseKey(Object entry) {
		// TODO 
		Assert.notNull(entry, "entry不能为空");

		SqlSet sql_set =new SqlSet(entry,true);
		Class<?> clazz = entry.getClass();
		return insertUseKey(clazz,sql_set);
	}


	@Override
	public <T> List<T> findList(T inputDto) {
		// TODO 
		Assert.notNull(inputDto, "inputDto不能为空");
		
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) inputDto.getClass();
		
		Where dto_where = new Where(inputDto);
		
		String sql=sqlGenerator.genSQL_findList(clazz, dto_where, null);

		Object[] val_arr = dto_where.getParamterValArr();
		
		List<T> list = getJdbcOperator().query(sql , val_arr,new BeanPropertyRowMapper<T>(clazz));
		return list;
	}

	

	@Override
	public int update(Class<?> clazz, SqlSet sql_set) {
		// TODO
		Assert.notNull(clazz,"clazz不为空");
		Assert.notNull(sql_set,"sql_set不为空");

		String sql=sqlGenerator.genSQL_update(clazz, sql_set);

		Map<String,Object>  removed_map= sql_set.getRemovedMap();
		Assert.state(removed_map.size()>=1, "removed_map的大小需要大于等于主键的length");
		
		sql_set.putMap(removed_map);
		
		Object[] val_arr = sql_set.getValArr().toArray();
		
		int res = getJdbcOperator().update(sql , val_arr);
		
		return res;
	}

	@Override
	public int update(Object entry) {
		// TODO 
		SqlSet sql_set = new SqlSet(entry,false);
		
		Class<?> clazz = entry.getClass();
		return update(clazz,sql_set);
	}

	@Override
	public int delete(Class<?> clazz, Long id) {
		// TODO 
		String[] pk_arr = getEntryMapper().getPKFieldName(clazz);
		Assert.state(pk_arr.length==1, "非单主键");
		return delete(clazz,new Where().and(pk_arr[0], "=", id));
	}

	@Override
	public int delete(Class<?> clazz, Where pk_where) {
		// TODO 
		Assert.notNull(clazz,"clazz不为空");
		Assert.notNull(pk_where,"pk_where不为空");
		
		String sql=sqlGenerator.genSQL_delete(clazz, pk_where);

		Object[] val_arr = pk_where.getParamterValArr();
		
		int res = getJdbcOperator().update(sql , val_arr);
		return res;
	}



	@Override
	public <T> List<T> findCascededAllList(Class<T> clazz) {
		// TODO 
		List<Map<String, Object>> map_list = findCascededAll( clazz);
		
		
		return null;
	}

	

	
	
	
	
	
}
