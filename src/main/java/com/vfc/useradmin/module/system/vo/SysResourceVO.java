package com.vfc.useradmin.module.system.vo;

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.annotation.Transient;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import java.io.Serializable;
import java.math.BigDecimal;

@TableEntry(tableName="shsa.sysResource")
public class SysResourceVO implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		
		@PKField(pkGenerator=PkGeneratorEnum.SEQUENCE,sequenceName="shsa.seq_mon_id")	
		@ColumnField(columnName="ID")
		private BigDecimal id;  //主键
		
		
		@ColumnField(columnName="NAME")
		private String name;  //
		
		
		@ColumnField(columnName="CODE")
		private String code;  //
		
		
		@ColumnField(columnName="ICON")
		private String icon;  //
		
		
		@ColumnField(columnName="ORDERNO")
		private BigDecimal orderno;  //
		
		
		@ColumnField(columnName="MENUID")
		private BigDecimal menuid;  //
		
		
		
		public BigDecimal getId(){
			return id;
		}
		public void setId(BigDecimal id){
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
		
		
		public String getIcon(){
			return icon;
		}
		public void setIcon(String icon){
			this.icon=icon;
		}
		
		
		public BigDecimal getOrderno(){
			return orderno;
		}
		public void setOrderno(BigDecimal orderno){
			this.orderno=orderno;
		}
		
		
		public BigDecimal getMenuid(){
			return menuid;
		}
		public void setMenuid(BigDecimal menuid){
			this.menuid=menuid;
		}
		
		

		
}
