package com.vfc.useradmin.system.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.vfc.useradmin.system.bo.FhConBO;
import com.vfc.useradmin.system.bo.SystemBO;
import com.vfc.useradmin.system.security.Principal;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.MenuVO;
import com.vfc.useradmin.system.vo.SysUser;

@Controller
@RequestMapping("/sys")
public class SystemAction extends BaseAction{

	private static final Logger log = LoggerFactory.getLogger(SystemAction.class);
	@Autowired
	private SystemBO systemBO;
	
	@RequestMapping("/toLogin.do")
	public String ToLogin(){
		return "login";
	}
	@RequestMapping("/toIndex.do")
	public String ToIndex(){
		return "index";
	}
	
	@RequestMapping("/logout.do")
	public String logOut(){
		
		SecurityUtils.getSubject().logout();
		
		return "redirect:toLogin.do";
	}
	
	@RequestMapping("/main.do")
	public String ToMain(HttpServletRequest req){
		//SysUser user = (SysUser) req.getSession().getAttribute("CURR_USER");
		Principal pri = (Principal) SecurityUtils.getSubject().getPrincipal();
		SysUser user=new SysUser(pri);
		setMenusListByUser(user,null,req);
		
		return "main";
	}
	
	/*@RequestMapping("/ValidLogin.do")
	public String ValidLogin(String loginName,String password,HttpServletRequest req){
		if(loginName==null || "".equals(loginName)){
			req.setAttribute("msg", "登陆名不能为空") ;
			return "login";
		}
		
		SysUser user=systemBO.findUserByUniName(loginName);
		if(user==null){
			req.setAttribute("msg", "没有此用户") ;
			return "login";
		}
		if(password==null || "".equals(password)){
			req.setAttribute("msg", "密码不能为空") ;
			return "login";
		}
		if(  DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			req.setAttribute("msg",  "登陆成功") ;
			req.getSession().setAttribute("CURR_USER", user);
		
			return "redirect:main.do";
		}else{
			req.setAttribute("msg", "密码错误") ; 
			return "login";
		}
	}
*/
	//shiro登陆验证
	@RequestMapping("/ValidLogin.do")
	public String ValidLogin(@RequestParam("loginName")String loginName,
			@RequestParam("password")String password,HttpServletRequest req){
		if(loginName==null || "".equals(loginName)){
			req.setAttribute("msg", "登陆名不能为空") ;
			return "login";
		}
		
		Subject currSubject=SecurityUtils.getSubject();
		if(!currSubject.isAuthenticated()){
			UsernamePasswordToken token =new UsernamePasswordToken(loginName, password);
			try {
				
				currSubject.login(token);
				log.info("===="+currSubject.getPrincipal());
				
			} catch (UnknownAccountException uae) {
				req.setAttribute("msg", "账户不存在") ; 
				log.info(token.getPrincipal() + "账户不存在");
				return "login";
			} catch (IncorrectCredentialsException ice) {
				log.info(token.getPrincipal() + "密码不正确");
				req.setAttribute("msg", "密码不正确") ; 
				return "login";
			} catch (LockedAccountException lae) {
				log.info(token.getPrincipal() + "用户被锁定了 ");
				req.setAttribute("msg", "用户被锁定了") ; 
				return "login";
			} catch (AuthenticationException ae) {
				log.info(ae.toString());
				ae.printStackTrace();
				req.setAttribute("msg", "验证异常") ; 
				return "login";
			}
			
		}
		
			req.setAttribute("msg",  "登陆成功") ;
		
			return "redirect:main.do";
	}

	
	private List<App> setAppListByUser(SysUser user,HttpServletRequest req) {
		// TODO 
		if(user==null || user.getUserId()==0){
			return null;
		}
	
		List<App> appList= systemBO.findAppByUser(user.getUserId());
		req.setAttribute("appList", appList);
		return appList;
	}
	
	private void setMenusListByUser(SysUser user,Long appID,HttpServletRequest req) {
		// TODO 
		if(user==null || user.getUserId()==0){
			return ;
		}
		/*if(appID==0){
			return ;
		}*/
		List<MenuVO> menuList= systemBO.findMenusByUser(user.getUserId(),appID);
		if(menuList!=null && menuList.size()>0){
			List<MenuVO> rootMenus=new ArrayList<MenuVO>();
			List<MenuVO> conMenus=new ArrayList<MenuVO>();
			for(MenuVO menu:menuList){
				if(menu.getParentId()==0){
					rootMenus.add(menu);
				}else{
					conMenus.add(menu);
				}
			}
			req.setAttribute("rootMenus", rootMenus);
			req.setAttribute("conMenus", conMenus);
		}
	}
	
	@ResponseBody
	@RequestMapping("/getMenusListByUser.do")
	public Map<String,Object> getMenusListByUser(Long appID,HttpServletRequest req) {
		// TODO 
	/*	if(appID==0){
			return null;
		}*/
		Principal pri=(Principal) SecurityUtils.getSubject().getPrincipal();
		SysUser user=new SysUser(pri);
	//	SysUser user =(SysUser)req.getSession().getAttribute("CURR_USER");
		List<MenuVO> menuList= systemBO.findMenusByUser(user.getUserId(),appID);
		Map<String, Object>	rtnMap = new HashMap<String, Object>();
		if(menuList!=null && menuList.size()>0){
			List<MenuVO> rootMenus=new ArrayList<MenuVO>();
			List<MenuVO> conMenus=new ArrayList<MenuVO>();
			for(MenuVO menu:menuList){
				if(menu.getParentId()==0){
					rootMenus.add(menu);
				}else{
					conMenus.add(menu);
				}
			}
		rtnMap.put("rootMenus", rootMenus);
		rtnMap.put("conMenus", conMenus);
		}
		return  rtnMap;
	}
	
	
}
