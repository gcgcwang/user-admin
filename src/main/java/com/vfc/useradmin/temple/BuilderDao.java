package com.vfc.useradmin.temple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderDao {

	private static final Logger logger = LoggerFactory.getLogger(BuilderDao.class);

	public List<ColumnModel> getTableColumnInfo_oracle(String tableName)
	{
		Connection conn =null;
		PreparedStatement ps=null;
	try {
		if(tableName==null||"".equals(tableName))throw new Exception("table不为空");
		if(tableName.indexOf(".")>=0)tableName=tableName.substring(tableName.lastIndexOf(".")+1);
	
			Class.forName(FreemarkerUtil.jdbc_driver);
			 conn = DriverManager.getConnection(FreemarkerUtil.jdbc_url, FreemarkerUtil.jdbc_username,
					FreemarkerUtil.jdbc_password);
			StringBuffer sql=new StringBuffer();
			sql.append(" SELECT ")
				.append("	utc.column_name,utc.data_type,utc.data_length,utc.data_precision,  ")
				.append("	utc.data_Scale,decode(utc.nullable,'N',0,'Y',1) as nullable,utc.data_default,ucc.comments  ")
				.append(" FROM USER_TAB_COLUMNS utc,USER_COL_COMMENTS ucc  ")
				.append("	WHERE  ")
				.append("	utc.table_name = ucc.table_name  ")
				.append("	and utc.column_name = ucc.column_name  ")
				.append("	and utc.table_name = ?  ")
				.append("	ORDER BY column_id ");
			
			 ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tableName.toUpperCase());
			logger.info("=====sql===="+sql.toString());
			logger.info("=======tableName====="+tableName.toUpperCase());
			ResultSet rs = ps.executeQuery();
			
			List<ColumnModel> model_list =new ArrayList<ColumnModel>();
				while(rs.next()){
					ColumnModel model = new ColumnModel();
					model.setColumnName(rs.getString("column_name"));
					model.setColumnMemo(rs.getString("comments"));
					model.setColumnType(rs.getString("data_type"));
					model.setColumnTypeLen(rs.getInt("data_length"));
					model.setIsPK(rs.getInt("nullable")==0?1:0);
					model.setJavaName(FreemarkerUtil.humpedColName(rs.getString("column_name")));
					model.setJavaType(JavaTypeMap.getJavaType("oracle", rs.getString("data_type")));
					model_list.add(model);
				}
			
			return model_list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public List<ColumnModel> getTableColumnInfo_mysql(String tableName)
	{
		Connection conn =null;
		PreparedStatement ps=null;
	try {
		if(tableName==null||"".equals(tableName))throw new Exception("table不为空");
		if(tableName.indexOf(".")>=0)tableName=tableName.substring(tableName.lastIndexOf(".")+1);
	
			Class.forName(FreemarkerUtil.jdbc_driver);
			 conn = DriverManager.getConnection(FreemarkerUtil.jdbc_url, FreemarkerUtil.jdbc_username,
					FreemarkerUtil.jdbc_password);
			StringBuffer sql=new StringBuffer();
			sql.append(" select TABLE_NAME,COLUMN_NAME,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE ")
	           .append(" ,COLUMN_COMMENT, COLUMN_TYPE from information_schema.COLUMNS  ")
				.append("	WHERE  ")
				.append("	TABLE_NAME = ? order by ORDINAL_POSITION ");
			
			 ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tableName.toUpperCase());
			logger.info("=====sql===="+sql.toString());
			logger.info("=======tableName====="+tableName.toUpperCase());
			ResultSet rs = ps.executeQuery();
			
			List<ColumnModel> model_list =new ArrayList<ColumnModel>();
				while(rs.next()){
					ColumnModel model = new ColumnModel();
					model.setColumnName(rs.getString("COLUMN_NAME"));
					model.setColumnMemo(rs.getString("COLUMN_COMMENT"));
					model.setColumnType(rs.getString("DATA_TYPE"));
					model.setColumnTypeLen( getColumnTypeLen(rs.getString("COLUMN_TYPE")));
					model.setIsPK(rs.getString("IS_NULLABLE").equalsIgnoreCase("NO")?1:0);
					model.setJavaName(FreemarkerUtil.humpedColName(rs.getString("COLUMN_NAME")));
					model.setJavaType(JavaTypeMap.getJavaType("mysql", rs.getString("DATA_TYPE")));
					model_list.add(model);
				}
			
			return model_list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	private int getColumnTypeLen(String str) {
		// TODO 
		if(str.indexOf("(")>0 && str.indexOf(")")>0 ){
			String sub_str = str.substring(str.indexOf("(")+1, str.indexOf(")"));
			return Integer.parseInt(sub_str);
		}
		
		return 0;
	}
	
}
