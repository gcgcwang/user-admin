package com.vfc.useradmin.jpa;



import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;
import com.vfc.useradmin.jpa.sql.TableAsso;

public interface EntryMapper {

	 abstract String getEntryTableName(Class<?> clazz);
	
	 
	 abstract String[] getPKFieldName(Class<?> clazz);
		
	
	 abstract String getSequenceName(Class<?> clazz);
	  
	 abstract PkGeneratorEnum getPkGenerator(Class<?> clazz);
	 
	 abstract TableAsso getTableAsso(Class<?> clazz);
}
