package com.vfc.useradmin.jpa;




import org.springframework.util.Assert;

import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import com.vfc.useradmin.jpa.sql.OrderBy;
import com.vfc.useradmin.jpa.sql.SqlSet;
import com.vfc.useradmin.jpa.sql.Where;
import com.vfc.useradmin.system.vo.Pager;


public class BranchSqlGenerator extends GeneralSqlGenerator{
	


	@Override
	public String genSQL_selectPageList(String tableName,Pager<?> pager, Where where,OrderBy orderby,String dbType) {
		
		Assert.notNull(dbType, "dbType不为空");
		Assert.notNull(tableName, "tableName不为空");
		
		if(dbType.equalsIgnoreCase("oracle")){
			return genOracleSQL_selectPageList(tableName,pager,where,orderby);
		}else if(dbType.equalsIgnoreCase("mysql")){
			return genMysqlSQL_selectPageList(tableName,pager,where,orderby);
		}
		return null;
	}
	
	
	public String genOracleSQL_selectPageList(String tableName,Pager<?> pager, Where where,OrderBy orderby) {
		// TODO 
		
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT b.* FROM (");
		sql.append(" SELECT ").append(" a.*,rownum as RN ")
		.append(" FROM ").append(tableName).append(" a ");
		
		if(where!=null){	
			sql.append(where.toSqlString());
			sql.append(" and rownum <= ").append(pager.getEndNum());
		}else{
			sql.append(" WHERE rownum <= ").append(pager.getEndNum());
		}
		
		sql.append(")b WHERE b.RN >= ").append(pager.getBeginNum());
		
		if(orderby!=null){
			sql.append(orderby.toSqlString());
		}
		
		return sql.toString();
	}


	public String genMysqlSQL_selectPageList(String tableName,Pager<?> pager, Where where,OrderBy orderby) {
		// TODO 
		
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT ").append(" * ")
		.append(" FROM ").append(tableName);
		
		if(where!=null){	
			sql.append(where.toSqlString());
		}
		
		sql.append(" LIMIT ").append(pager.getBeginNum()).append(",").append(pager.getEndNum());
		
		if(orderby!=null){
			sql.append(orderby.toSqlString());
		}
		
		return sql.toString();
	}

	@Override
	public String genSQL_insert(Class<?> clazz,SqlSet sql_set ) {

		Assert.notNull(clazz, "clazz不为空");
		
		Assert.notNull(entryMapper,"entryMapper不为空");

		StringBuffer sql =new StringBuffer();
		sql.append(" INSERT INTO ").append(entryMapper.getEntryTableName(clazz));
		
		PkGeneratorEnum pkGen_enum= entryMapper.getPkGenerator(clazz);
		
		if(PkGeneratorEnum.AUTO_INCREASING == pkGen_enum ){
			String[]  pk_arr= entryMapper.getPKFieldName(clazz);
			for(String pk_key : pk_arr){
				sql_set.remove(pk_key);
			}
		}
		sql.append(sql_set.toSqlString_insert());
		return sql.toString();
	}
	


	@Override
	public String genSQL_getSeq(Class<?> clazz) {
		// TODO 
		Assert.notNull(entryMapper,"entryMapper不为空");
		Assert.notNull(clazz,"clazz不为空");
	
		PkGeneratorEnum  pkGen_enum = entryMapper.getPkGenerator(clazz);
		Assert.state(PkGeneratorEnum.SEQUENCE==pkGen_enum,"entryMapper.getPkGenerator不为seq类型！");
		
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT ").append(entryMapper.getSequenceName(clazz))
		.append(".nextval FROM DUAL ");
		
		return sql.toString();
	}


	@Override
	public String genSQL_update(Class<?> clazz, SqlSet sql_set) {
		// TODO 
		Assert.notNull(sql_set,"sql_set不为空");
		Assert.notNull(clazz,"clazz不为空");
		
		String[] pk_arr = entryMapper.getPKFieldName(clazz);
		Assert.notEmpty(pk_arr,"pk_arr不为空");
		
		
		StringBuffer sql =new StringBuffer();
		sql.append(" UPDATE ").append(entryMapper.getEntryTableName(clazz));
		
		Where pk_where = new Where();
		for(String pk : pk_arr){
			Assert.notNull(pk,"pk不为空");
			Object val = sql_set.get(pk);
			Assert.notNull(val,"val不为空");
			pk_where.and(pk, "=", val);
			sql_set.remove(pk);
		}
		sql.append(sql_set.toSqlString_update());
		sql.append(pk_where.toSqlString());
		
		return sql.toString();
	}


	@Override
	public String genSQL_delete(Class<?> clazz, Where pk_where) {
		// TODO 

		String[] pk_arr = entryMapper.getPKFieldName(clazz);
		Assert.notEmpty(pk_arr,"pk_arr不为空");
		Assert.state(pk_arr.length==pk_where.size(),"pk_arr和pk_where需要大小相等");

		StringBuffer sql =new StringBuffer();
		sql.append(" DELETE FROM ").append(entryMapper.getEntryTableName(clazz));
		
		for(String pk :pk_arr){
			Assert.notNull(pk, "pk不为空");
			Object val = pk_where.getParamterMap().get(pk);
			Assert.notNull(val, "val不为空");
		}
		sql.append(pk_where.toSqlString());
		return sql.toString();
	}
}
