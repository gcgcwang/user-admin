package com.vfc.useradmin.system.vo;

import java.util.Date;

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

@TableEntry(tableName="SYS_DIC")
public class DicVO {
	
	@PKField(pkGenerator=PkGeneratorEnum.AUTO_INCREASING)
	private Long id; 
	private String  name;
	private String  code; 
	private String  type; 
	
	private Long  parentId; 
	private Integer  orderNo; 
	private String  memo;  
	private Integer  isValid; 
	
	private Long  creater; 
	private Date  createTime; 
	private Long  updater;
	private Date  updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Long getCreater() {
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
