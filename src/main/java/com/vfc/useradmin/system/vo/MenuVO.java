package com.vfc.useradmin.system.vo;

import com.alibaba.fastjson.JSON;

public class MenuVO   extends BaseVO{

	private Long  menuId;  
	private String  menuCode;  
	private String  menuName;  
	private Integer  isValid;    
	private String  memo;  
	private String  isMemu;  
	private String  linkUrl; 
	private String  picUrl;   
	private Integer  parentId;   
	private Integer  orderNo;  
	private Integer  layer;
	
	
	
	


	public Long getMenuId() {
		return menuId;
	}






	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}






	public String getMenuCode() {
		return menuCode;
	}






	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}






	public String getMenuName() {
		return menuName;
	}






	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}






	public Integer getIsValid() {
		return isValid;
	}






	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}






	public String getMemo() {
		return memo;
	}






	public void setMemo(String memo) {
		this.memo = memo;
	}






	public String getIsMemu() {
		return isMemu;
	}






	public void setIsMemu(String isMemu) {
		this.isMemu = isMemu;
	}






	public String getLinkUrl() {
		return linkUrl;
	}






	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}






	public String getPicUrl() {
		return picUrl;
	}






	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}






	public Integer getParentId() {
		return parentId;
	}






	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}






	public Integer getOrderNo() {
		return orderNo;
	}






	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}






	public Integer getLayer() {
		return layer;
	}






	public void setLayer(Integer layer) {
		this.layer = layer;
	}






	@Override
	public String toString() {
		return JSON.toJSONString(this);
	} 
	
	
}
