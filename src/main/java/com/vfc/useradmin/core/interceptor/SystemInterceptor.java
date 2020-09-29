package com.vfc.useradmin.core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.vfc.useradmin.core.Params;
import com.vfc.useradmin.system.vo.SysUser;


public class SystemInterceptor implements HandlerInterceptor {
	
	private static final Logger log =LoggerFactory.getLogger(SystemInterceptor.class); 
	
  

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		HttpSession session=request.getSession();  
		SysUser user = (SysUser) session.getAttribute(Params.SYSTEM_USER);
	     //session中获取用户名信息  
		if (user == null) {
			PrintWriter out = response.getWriter();    
            StringBuilder builder = new StringBuilder();  
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");    
            builder.append("alert(\"登录超时，请重新登录\");");    
            builder.append("window.top.location.href=\""+request.getSession().getServletContext().getContextPath() +"\"");    
            builder.append("</script>");    
            out.print(builder.toString());    
            out.close();  
			return false;
		}
	    return true;
		
	}

}
