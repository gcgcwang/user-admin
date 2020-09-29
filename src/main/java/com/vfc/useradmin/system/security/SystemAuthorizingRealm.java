package com.vfc.useradmin.system.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.vfc.useradmin.system.bo.SystemBO;
import com.vfc.useradmin.system.vo.SysResource;
import com.vfc.useradmin.system.vo.SysUser;

public class SystemAuthorizingRealm extends  AuthorizingRealm   {

	private static final Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

	private SystemBO systemBO;


	/**
	 * 获取用户权限控制(用户验证通过后)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO 
		Principal principal = (Principal) super.getAvailablePrincipal(principals);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
			logger.info("获取用户访问资源控制权限");
			
			// 获取用户所有权限
			List<SysResource> userResourcesList = systemBO.findUserResources(principal.getUserId());
			if(userResourcesList != null && userResourcesList.size() > 0){
				for(SysResource sr : userResourcesList){
					info.addStringPermission(sr.getCode());
				}
			}
		return info;
	}


	/**
	 * 用户登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) 
			throws AuthenticationException {
		// TODO 
		
		UsernamePasswordToken upToken=(UsernamePasswordToken) token; 
		SysUser user=systemBO.findUserByUniName(upToken.getUsername());

		if(user!=null){
			return new SimpleAuthenticationInfo(new Principal(user), user.getPassword(), getName());
		}else{
			throw new UnknownAccountException();
		}
		
	}


	public SystemBO getSystemBO() {
		return systemBO;
	}


	public void setSystemBO(SystemBO systemBO) {
		this.systemBO = systemBO;
	}

	
}
