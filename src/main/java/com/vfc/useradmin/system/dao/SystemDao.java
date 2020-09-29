package com.vfc.useradmin.system.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vfc.useradmin.system.common.MyBatisDao;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.MenuVO;
import com.vfc.useradmin.system.vo.SysResource;
import com.vfc.useradmin.system.vo.SysUser;

@MyBatisDao
public interface SystemDao {
	
	public SysUser findUserByUniName(String loginName);
	
	public List<App> findAppByUser(long userID);

	public List<MenuVO> findMenusByUser(@Param("userID") long userID, @Param("appID") long appID);

	public List<SysResource> findUserResources(long userID);

}
