package com.vfc.useradmin.system.po;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vfc.useradmin.jpa.GeneralSqlGenerator;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.MenuVO;
import com.vfc.useradmin.system.vo.SysResource;
import com.vfc.useradmin.system.vo.SysUser;


@Component
public class SystemPO extends BasePO{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public JdbcOperations getJdbcOperator(){
		return namedJdbcTemplate.getJdbcOperations();
	}
	
	public SysUser findUserByUniName(String loginName)throws Exception{
		
		String sql=" select * from SYS_USER  where login_name = ? ";
		SysUser user=getJdbcOperator().queryForObject(sql, 
				new BeanPropertyRowMapper<SysUser>(SysUser.class),loginName);
		return user;
	}
	
	public List<App> findAppByUser(long userID)throws Exception{
		
		String sql="  SELECT app.*  "
		+" FROM platform.SM_APP_T3 app,platform.SM_ROLE_T3 role "
		+"	,platform.SM_R_ROLE_USER_T3 ru "
		+" WHERE app.appid=role.appid "
		+"	 and role.roleid=ru.roleid  and app.valid=1 and app.appID>0 "
		+"	and ru.userid= ? ";
		
		List<App> appList=getJdbcOperator().query(sql,
				new BeanPropertyRowMapper<App>(App.class),userID);
		return appList;
	}

	public List<MenuVO> findMenusByUser(long userID,Long appID)throws Exception{
		
		String sql=" SELECT menu.*  "
		+" FROM SYS_MENU menu,SYS_R_ROLE_MENU rm ,SYS_ROLE role "
		+"	,SYS_R_ROLE_USER ru "
		+" WHERE menu.menu_id=rm.menu_id and rm.role_id = role.role_id "
		+"	 and role.role_id=ru.role_id  and menu.is_valid=1 and role.is_valid=1  "
		+"	and ru.user_id=?  ";
		
		List<MenuVO> menuList=getJdbcOperator().query(sql,
				new BeanPropertyRowMapper<MenuVO>(MenuVO.class),userID);
		return menuList;
		
	}

	public List<SysResource> findUserResources(long userID)throws Exception{
		
		String sql=" SELECT a.* "
		+" FROM SYS_RESOURCE a,SYS_R_USER_RESOURCE b "
		+" WHERE a.id=b.resource_id and b.user_id= ? ";
				
		List<SysResource> resList=getJdbcOperator().query(sql,
				new BeanPropertyRowMapper<SysResource>(SysResource.class) ,userID);
		return resList;
	}
	
}
