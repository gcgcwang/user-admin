package com.vfc.useradmin.system.po;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.Pager;

@Component
public class AppPO extends BasePO{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public JdbcOperations getJdbcOperator(){
		return namedJdbcTemplate.getJdbcOperations();
	}
	
	public long selectTotal()throws Exception{
		String sql=" SELECT count(*) FROM platform.SM_APP_T3 ";
		Long  tol = getJdbcOperator().queryForObject(sql, Long.class);
		return tol;
	}
	
	public List<App> findList(Pager<App> pager)throws Exception{
		
		StringBuffer sb=new StringBuffer();
		sb.append("  select b.* from ")
		.append(" (SELECT a.*,rownum as rn FROM platform.SM_APP_T3 a where rownum <= ? )b ")
		.append(" where b.rn >=  ? "); 
		
		List<Object> paraArr=new ArrayList<Object>();
		paraArr.add(pager.getEndNum());
		paraArr.add(pager.getBeginNum());
		
		List<App> list=getJdbcOperator().query(sb.toString(),paraArr.toArray(),
				new BeanPropertyRowMapper<App>(App.class));
			
		return list;
		
		
	}
}
