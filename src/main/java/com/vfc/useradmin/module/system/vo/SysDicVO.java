package com.vfc.useradmin.module.system.vo;

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.annotation.Transient;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import java.io.Serializable;
import java.util.Date;

@TableEntry(tableName="sys_dic")
public class SysDicVO implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		
		@PKField(pkGenerator=PkGeneratorEnum.AUTO_INCREASING)	
		@ColumnField(columnName="id")
		private Long id;  //
		
		
		@ColumnField(columnName="name")
		private String name;  //
		
		
		@ColumnField(columnName="code")
		private String code;  //
		
		
		@ColumnField(columnName="type")
		private String type;  //
		
		
		@ColumnField(columnName="parent_id")
		private String parentId;  //
		
		
		@ColumnField(columnName="order_no")
		private Integer orderNo;  //
		
		
		@ColumnField(columnName="memo")
		private String memo;  //
		
		
		@ColumnField(columnName="is_valid")
		private Integer isValid;  //
		
		
		@ColumnField(columnName="creater")
		private Long creater;  //
		
		
		@ColumnField(columnName="create_time")
		private Date createTime;  //
		
		
		@ColumnField(columnName="updater")
		private Long updater;  //
		
		
		@ColumnField(columnName="update_time")
		private Date updateTime;  //
		
		
		
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		
		
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		
		
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
		}
		
		
		public String getType(){
			return type;
		}
		public void setType(String type){
			this.type=type;
		}
		
		
		public String getParentId(){
			return parentId;
		}
		public void setParentId(String parentId){
			this.parentId=parentId;
		}
		
		
		public Integer getOrderNo(){
			return orderNo;
		}
		public void setOrderNo(Integer orderNo){
			this.orderNo=orderNo;
		}
		
		
		public String getMemo(){
			return memo;
		}
		public void setMemo(String memo){
			this.memo=memo;
		}
		
		
		public Integer getIsValid(){
			return isValid;
		}
		public void setIsValid(Integer isValid){
			this.isValid=isValid;
		}
		
		
		public Long getCreater(){
			return creater;
		}
		public void setCreater(Long creater){
			this.creater=creater;
		}
		
		
		public Date getCreateTime(){
			return createTime;
		}
		public void setCreateTime(Date createTime){
			this.createTime=createTime;
		}
		
		
		public Long getUpdater(){
			return updater;
		}
		public void setUpdater(Long updater){
			this.updater=updater;
		}
		
		
		public Date getUpdateTime(){
			return updateTime;
		}
		public void setUpdateTime(Date updateTime){
			this.updateTime=updateTime;
		}
		
		

		
}
