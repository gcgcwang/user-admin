package com.vfc.useradmin.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



public class SpringContextHolder implements ApplicationContextAware{
 
	private static ApplicationContext applicationContext;
	/**
	 * 当继承了ApplicationContextAware类之后，那么程序在调用
	 * getBean(String)的时候会自动调用该方法，不用自己操作
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext= applicationContext;
	}
 
	public Object getBean(String beanName){
		return this.applicationContext.getBean(beanName);
	}
	public Object getBean(Class<?> clazz){
		return this.applicationContext.getBean(clazz);
	}
}
