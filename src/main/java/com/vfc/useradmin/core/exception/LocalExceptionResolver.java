package com.vfc.useradmin.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class LocalExceptionResolver implements HandlerExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(LocalExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception ex) {
		
		LocalException localException = null;
		if(ex instanceof LocalException){
			localException = (LocalException)ex;
		}else{
			localException = new LocalException("系统出错了，请联系系统管理员！");
			ex.printStackTrace();
		}
		log.error("-----系统出错了，请联系系统管理员！-----");
		//错误信息
		String message = localException.getMessage();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		
		//指向错误页面
		modelAndView.setViewName("error");

		
		return modelAndView;
	}
	
}
