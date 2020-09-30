package com.vfc.useradmin.system.bo;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vfc.useradmin.system.po.SystemPO;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.MenuVO;
import com.vfc.useradmin.system.vo.SysResource;
import com.vfc.useradmin.system.vo.SysUser;

@Service
public class SystemBO{
	private static final Logger log=LoggerFactory.getLogger(SystemBO.class);

	@Autowired
	private SystemPO systemPO;
	//private  SystemDao systemDao;
	public   SysUser  findUserByUniName(String loginName){
		log.info("==============="+systemPO);
		SysUser user=null;
		try {
			 user = systemPO.findUserByUniName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	 @Cacheable(value="ehcache",key="'userApps_'+#userID")
	public   List<App>  findAppByUser(long userID){
		 List<App>  appList=null;
		 try {
			 appList = systemPO.findAppByUser(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return appList;
	}
	
	 @Cacheable(value="ehcache",key="'userMenus_'+#userID")
	public List<MenuVO> findMenusByUser(long userID,Long appID) {
		// TODO 
		 List<MenuVO>  menuList=null;
		 try {
			 menuList	 = systemPO.findMenusByUser(userID,appID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

	public List<SysResource> findUserResources(long userID) {
		// TODO Auto-generated method stub
		List<SysResource> resourceList=null;
		try {
			resourceList = systemPO.findUserResources(userID);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resourceList;
	}
	
}
