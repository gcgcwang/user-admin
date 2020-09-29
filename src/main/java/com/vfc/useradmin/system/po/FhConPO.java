package com.vfc.useradmin.system.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.vfc.useradmin.jpa.GeneralSqlGenerator;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.FhConVO;
import com.vfc.useradmin.system.vo.Pager;

@Component
public class FhConPO extends BasePO{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public JdbcOperations getJdbcOperator(){
		return namedJdbcTemplate.getJdbcOperations();
	}
	
	
	public FhConVO findByID(String cid){
		
		String sql=" select * from fh.fh_contract  where cid = ? ";
		
		FhConVO vo = getJdbcOperator().queryForObject(sql,
				new BeanPropertyRowMapper<FhConVO>(FhConVO.class),cid);
		return vo;
	};
	
	
	public List<FhConVO> findList(FhConVO fhConVO){
		
		String sql=" select * from fh.fh_contract ";
		
		List<Object> paraArr=new ArrayList<Object>();
		
		
		List<FhConVO> list=getJdbcOperator().query(sql,paraArr.toArray(),
				new BeanPropertyRowMapper<FhConVO>(FhConVO.class));
			
		return list;
	}
	
	
}
