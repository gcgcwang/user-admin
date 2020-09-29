package com.vfc.useradmin.jpa;


import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;


public abstract  class AbstractJpaService implements JpaService{
	
	
	protected  NamedParameterJdbcTemplate namedJdbcTemplate;
	protected  SqlGenerator sqlGenerator;
	
	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
	public SqlGenerator getSqlGenerator() {
		return sqlGenerator;
	}
	public void setSqlGenerator(SqlGenerator sqlGenerator) {
		this.sqlGenerator = sqlGenerator;
	}
	
	public String getDbType(){
		Assert.notNull(namedJdbcTemplate, "jdbcTemplate不为空");
		JdbcTemplate    jdbcTemplate = (JdbcTemplate)namedJdbcTemplate.getJdbcOperations();
		DruidDataSource dataSource = (DruidDataSource) jdbcTemplate.getDataSource();
		String dbType =dataSource.getDbType();
		
		Assert.notNull(dbType,"dbType不为空");
		return  dbType;
	}
	
	public JdbcOperations getJdbcOperator(){
		Assert.notNull(namedJdbcTemplate, "jdbcTemplate不为空");
		JdbcOperations    JdbcOperator = (JdbcTemplate)namedJdbcTemplate.getJdbcOperations();
		Assert.notNull(JdbcOperator,"JdbcOperator不为空");
		return  JdbcOperator;
	}
	
	public EntryMapper getEntryMapper(){
		Assert.notNull(sqlGenerator, "sqlGenerator不为空");
		return  sqlGenerator.getEntryMapper();
	}
	
	//@方式二
	/*  private static String jdbcTemplate_className;
	private static String sqlGenerator_className;
	static{
		Properties prop = new Properties();
		try {
			prop.load(JpaService.class.getResourceAsStream("beanClassName.properties"));
		} catch (IOException e) {
			throw new RuntimeException("读取jpaService.properties文件异常",e);
		}
		jdbcTemplate_className=prop.getProperty("jdbcTemplate_className");
		sqlGenerator_className=prop.getProperty("sqlGenerator_className");
	}
	protected void initService() {
		SpringContextHolder holder = new SpringContextHolder();
		 try {
			jdbcTemplate=(JdbcTemplate) holder.getBean(Class.forName(jdbcTemplate_className));
	
			sqlGenerator= (BranchSqlGenerator)holder.getBean(Class.forName(sqlGenerator_className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 DruidDataSource dataSource = (DruidDataSource) jdbcTemplate.getDataSource();
		 dbType = dataSource.getDbType();
	}*/
	
	
	
}
