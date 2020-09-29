package com.vfc.useradmin.jpa.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.enums.JoinTypeEnum;

public class TableAsso {

	private Logger log =LoggerFactory.getLogger(TableAsso.class);
	
	private List<TableRef> tableRefs;
   // private String  tableName;
	private String table_alia_str="0abcdefghijklmnopqrstuywxyz";
	private String table_alia_numstr="0123456789";

	private Integer table_alia_str_index=0;
	
	public TableAsso(){
		tableRefs = new ArrayList<TableRef>();
	}
	
	public boolean add(JoinTypeEnum joinType, String tableName, String[] pkColName, String refColName,TableRef pTableRef
			,Where where, OrderBy orderby){
		
		String table_alia = genTableAlia();
		return  tableRefs.add(new TableRef(joinType, tableName, table_alia, pkColName, refColName,pTableRef
				, where, orderby ));
	}
	
	public boolean add(TableRef tr){
		
		return  tableRefs.add(tr);
	}
	
	public boolean remove(TableRef ref){
		return tableRefs.remove(ref);
	}
	
	public TableRef remove(int index){
		return tableRefs.remove(index);
	}
	
	public TableRef get(int index){
		return tableRefs.get(index);
	}
	
	
	public int indexOf(TableRef ref){
		return tableRefs.indexOf(ref);
	}
	
	public Iterator<TableRef> iterator(){
		return tableRefs.iterator();
	}
	
	public int size(){
		return tableRefs.size();
	}
	
	public TableRef set(int index, TableRef ref){
		return  tableRefs.set(index, ref);
	}
	
	public String toQuerySqlString(){
		StringBuffer sql = new StringBuffer(" FROM ");
		StringBuffer sel_sql = new StringBuffer(" SELECT ");
		Assert.state( tableRefs.size()>0 , "tableRefs的大小应大于0");
		
		
		TableRef  fir_ref = tableRefs.get(0);
			sql.append(fir_ref.getTableName()+" "+fir_ref.getTableAlia()).append(" ");
			sel_sql.append( fir_ref.getTableAlia()+".*");
		// FROM ROOT_TABLE
		
		 cascadeAppendTableRef(sql, sel_sql, null);
		 sel_sql.append(sql);
		 String[] pk_cols = fir_ref.getPkColName();
		 sel_sql.append(" ORDER BY ");	
		 for(String pk_col : pk_cols){
			 sel_sql.append(fir_ref.getTableAlia()).append(".").append(pk_col).append(" asc").append(",");
		 }
		 sel_sql.deleteCharAt(sel_sql.lastIndexOf(","));
		
		return sel_sql.toString();
		
	}

	private void cascadeAppendTableRef(StringBuffer sql, StringBuffer sel_sql,TableRef root_ref) {
		// TODO 
		for(TableRef  ref : tableRefs){
			if( ref.getpTableRef() == root_ref  ){
				// jOIN	.... ON  ..=..
				if(root_ref != null){
					sql.append(" ").append(ref.getJoinType().getCode()).append(" ").append(ref.getTableName());
					sql.append(" ").append(ref.getTableAlia());
					sql.append(" ON ").append(ref.getTableAlia()+"."+ref.getRefName()).append("=");
					
					sql.append(root_ref.getTableAlia()+".").append(root_ref.getRefName()).append(" ");
					
					sel_sql.append( ", "+ref.getTableAlia()+".*");
				}else{
					cascadeAppendTableRef(sql, sel_sql, ref);
				}
			}
			
				
		}
		
	}

	public List<TableRef> getTableRefs() {
		return tableRefs;
	}

	public void setTableRefs(List<TableRef> tableRefs) {
		if(tableRefs!=null && tableRefs.size()>0){
			for(TableRef ref : tableRefs){
				ref.setTableAlia(genTableAlia());
			}
		}
		this.tableRefs = tableRefs;
	}
	
	public String genTableAlia(){
		table_alia_str_index++;
		String table_alia ="";
			int num_index = table_alia_str_index / 26;
			int zm_index = table_alia_str_index % 26;
			 table_alia = String.valueOf(table_alia_str.charAt(zm_index))
					+ String.valueOf(table_alia_numstr.charAt(num_index));
			 return table_alia;
	}


	public TableRef getTableRefByTableName(String tab_name) {
		// TODO 
		for(TableRef ref : tableRefs ){
			if(ref.getTableName().equals( tab_name) ){
				return ref;
			}
		}
		return null;
	}

	
	
}
