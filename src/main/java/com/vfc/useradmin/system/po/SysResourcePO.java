package com.vfc.useradmin.system.po;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


@Component
public class SysResourcePO {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public JdbcOperations getJdbcOperator(){
		return namedJdbcTemplate.getJdbcOperations();
	}

	public int insert4() {
		// TODO 
		return  getJdbcOperator().update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO 
				String sql = " insert into shsa.sysresource(id,name,code,orderno) values( ?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setLong(1, 13L);
				ps.setString(2, "name07");
				ps.setString(3, "code07");
				ps.setLong(4, 6L);
				return ps;
			}
			
		}, new GeneratedKeyHolder());
		
	}
	
	public int insert5() {
		// TODO 
		int res= getJdbcOperator().update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO 
				String sql = " insert into shsa.sysresource(id,name,code,orderno) values( ?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setLong(1, 14L);
				ps.setString(2, "name08");
				ps.setString(3, "code08");
				ps.setLong(4, 5L);
				return ps;
			}
			
		}, new GeneratedKeyHolder());
		if(res==1){
			throw new RuntimeException("jdbc exception");
		}
		return res ;
		
	}
	
}
