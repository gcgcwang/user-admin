package com.vfc.useradmin.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.vfc.useradmin.core.DataWrapper;


@ControllerAdvice
public class BaseAction {

	  // 初始化绑定
    @InitBinder
    public void initBinder(WebDataBinder binder) {
         //处理表单数据转换对象异常（Date）
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	
	protected  DataWrapper success(Object data,String msg){
		
		return new DataWrapper(1,data,msg);
	}
	
	protected DataWrapper success(Object data){
		
		return success(data,"操作成功");
	}
	
	protected DataWrapper failure(Object data,String msg){
		
		return new DataWrapper(-1,data,msg);
	}
	
	protected DataWrapper failure(Object data){
		
		return failure(data,"操作失败");
	}
	
	protected DataWrapper expection(Object data,String msg){
		
		return new DataWrapper(-2,data,msg);
	}
	
	protected DataWrapper expection(Object data){
		
		return expection(data,"操作异常");
	}
	
	
}
