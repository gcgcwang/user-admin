package com.vfc.useradmin.jpa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.annotation.ColumnField;
import com.vfc.useradmin.jpa.annotation.JoinColumn;
import com.vfc.useradmin.jpa.annotation.PKField;
import com.vfc.useradmin.jpa.annotation.TableEntry;
import com.vfc.useradmin.jpa.enums.JoinTypeEnum;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import com.vfc.useradmin.jpa.sql.TableAsso;
import com.vfc.useradmin.jpa.sql.TableRef;

public class JpaEntryMapper implements EntryMapper{

	public  String getEntryTableName(Class<?> clazz){
		Assert.notNull(clazz, "clazz不能为空");
		
		TableEntry  tableAnno=(TableEntry) clazz.getAnnotation(TableEntry.class); 
		
		Assert.notNull(tableAnno, "TableEntry注解未设置");
		Assert.state(StringUtil.isNotEmpty(tableAnno.tableName()), "tableAnno.tableName未设置");

		return StringUtil.toUpperCase(tableAnno.tableName());
	}
	
	

	
	public  String[] getPKFieldName(Class<?> clazz){
		
		Assert.notNull(clazz, "clazz不能为空");
		Field[]  fields= clazz.getDeclaredFields();
		int pkArrLen=(fields.length>10?10:fields.length);
	//	String[] pkArr=new String[pkArrLen];
		
		List<String> pk_list = new ArrayList<String>(pkArrLen);
		
		for(int i=0; i < fields.length ; i++){
			if("serialVersionUID".equalsIgnoreCase(fields[i].getName()))continue;

			PKField pkField = fields[i].getAnnotation(PKField.class);
			
			if(pkField != null){
				ColumnField columnField = fields[i].getAnnotation(ColumnField.class);
				
				if(columnField==null){
					//未设置columnField注解默认属性值
					pk_list.add(StringUtil.toLowerCase(fields[i].getName()));
				}else{
					pk_list.add(StringUtil.toLowerCase(columnField.columnName()));
				}
			}
		}
		Assert.notEmpty(pk_list, "PKField未设置或ColumnField未设置");
		return pk_list.toArray(new String[0]);
	}
	
	public  String getSequenceName(Class<?> clazz){
		Assert.notNull(clazz, "clazz不能为空");

		Field[]  fields= clazz.getDeclaredFields();
		for(Field field : fields){
			if("serialVersionUID".equalsIgnoreCase(field.getName()))continue;
			PKField pkField = field.getAnnotation(PKField.class);
			
			if(pkField != null){
				if(StringUtil.isNotEmpty(pkField.sequenceName())){
					return StringUtil.toUpperCase(pkField.sequenceName());
				}
				
			}
		}
		Assert.state(false, "pkField.sequenceName未设置");
		return null;
	}
	
	public  PkGeneratorEnum getPkGenerator(Class<?> clazz){
		Assert.notNull(clazz, "clazz不能为空");

		Field[]  fields= clazz.getDeclaredFields();
		for(Field field : fields){
			if("serialVersionUID".equalsIgnoreCase(field.getName()))continue;
			PKField pkField = field.getAnnotation(PKField.class);
			
			if(pkField != null){
			//	Assert.notNull(pkField.pkGenerator(), "pkField.pkGenerator未设置");
				if(pkField.pkGenerator()!=null){
					return pkField.pkGenerator();
				}
			}
		}
		Assert.state(false, "PKField未设置");
		return null;
	}
	
	
	
	//获取主表的表明，若有从表则加上从表
	public TableAsso  getTableAsso(Class<?> clazz ){
		TableAsso table_asso = new TableAsso();
		
		
		layerTableRef(clazz, table_asso);
		return  table_asso;
	}
	
	public  void  layerTableRef(Class<?> clazz, TableAsso  table_asso){
		
		
		
			String  tab_name = this.getEntryTableName(clazz);
			String[]  pk_name = this.getPKFieldName(clazz);
			
			 
			 Field[] fields  = clazz.getDeclaredFields();
				for(Field field : fields){
					JoinColumn joinColumnAnno =	field.getAnnotation(JoinColumn.class);
					if(joinColumnAnno != null ){
						
						Assert.state(StringUtil.isNotEmpty(joinColumnAnno.refName()), "joinColumnAnno.refName未设置");
						
						 String refColName = joinColumnAnno.refName();
						 String fk_name = joinColumnAnno.name();
						 JoinTypeEnum joinType = joinColumnAnno.joinType();
						 String refTableName = joinColumnAnno.refTableName();
						 
						TableRef ref = table_asso.getTableRefByTableName(tab_name);
						String alias = ((ref == null)?table_asso.genTableAlia():ref.getTableAlia());
							
						TableRef fk_tr = new TableRef(null, tab_name, alias,
									 pk_name, fk_name, null, null, null);	
						 
						 
						 TableRef	 tr = new TableRef(joinType, refTableName, table_asso.genTableAlia(),
								 pk_name, refColName, fk_tr, null, null);
						 table_asso.add(fk_tr);
						 table_asso.add(tr);

					
					}
				}
		
		
		
	}




	
	
	

}
