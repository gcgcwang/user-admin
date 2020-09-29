package com.vfc.useradmin.system.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.util.DigestUtils;


public class SystemCredentilsMatcher extends SimpleCredentialsMatcher{

	
	public boolean doCredentialsMatch(AuthenticationToken authctoken, AuthenticationInfo info)
   {

		UsernamePasswordToken token = (UsernamePasswordToken) authctoken;
		
		Object accountCredentials = getCredentials(info);
		
		String passwordType = String.valueOf(token.getPassword());
		
		// 获取密码长度，判断是用户输入还是jcis传递过来的
		if(passwordType.length() == 32){	
			// 若用户
			if(equals(String.valueOf(token.getPassword()), accountCredentials)){
				return true;
			}else{
				return equals(DigestUtils.md5DigestAsHex(String.valueOf(token.getPassword()).getBytes()), accountCredentials);
			}
		} else {
			return equals(DigestUtils.md5DigestAsHex(String.valueOf(token.getPassword()).getBytes()), accountCredentials);
		}
	}
	
}
