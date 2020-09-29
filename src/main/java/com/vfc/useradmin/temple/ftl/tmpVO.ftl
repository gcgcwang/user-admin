package ${packageName};

import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.annotation.Transient;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import java.io.Serializable;

@TableEntry(tableName="${tableName}")
public class ${className}VO implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		<#list columnModelList as columnModel>
		
		<#if columnModel.isPK == 1>
		@PKField(pkGenerator=PkGeneratorEnum.${pkGenType!"SEQUENCE"}<#if seqName??>,sequenceName="${seqName}"</#if>)	
		</#if>
		@ColumnField(columnName="${columnModel.columnName}")
		private ${columnModel.javaType} ${columnModel.javaName};  //${columnModel.columnMemo!""}
		
		</#list>
		
		<#list columnModelList as columnModel>
		
		public ${columnModel.javaType} get${columnModel.javaName?cap_first}(){
			return ${columnModel.javaName};
		}
		public void set${columnModel.javaName?cap_first}(${columnModel.javaType} ${columnModel.javaName}){
			this.${columnModel.javaName}=${columnModel.javaName};
		}
		
		</#list>
		

		
}
