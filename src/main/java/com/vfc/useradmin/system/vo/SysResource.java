package com.vfc.useradmin.system.vo;

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.annotation.Transient;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

@TableEntry(tableName="sys_resource")
public class SysResource extends BaseVO {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@PKField(pkGenerator=PkGeneratorEnum.AUTO_INCREASING)
	  	private Long id;
	  	// 资源名称
		@ColumnField(columnName="")
		private String name;
					
		private String code;
					
		private String icon;
		
		
		
		@Transient
		private Integer orderNo;

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

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}


		public Integer getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(Integer orderNo) {
			this.orderNo = orderNo;
		}

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		
}
