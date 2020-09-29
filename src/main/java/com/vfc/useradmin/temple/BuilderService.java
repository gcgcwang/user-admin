package com.vfc.useradmin.temple;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vfc.useradmin.core.util.StringUtil;

public class BuilderService {

	public BuilderSetting dataMappingToTemple(BuilderSetting setting){
		
		List<ColumnModel> model_list = getBuilderColumnModels( setting.getTableName());
		setting.setColumnModelList( model_list);
		
		return setting;
	}
	
	
	
	
	
	
	public List<ColumnModel> getBuilderColumnModels(String tableName){
		BuilderDao dao =new BuilderDao();
		if(FreemarkerUtil.jdbc_driver.indexOf("mysql") >= 0 ){
			return dao.getTableColumnInfo_mysql(tableName);
		}
		if(FreemarkerUtil.jdbc_driver.indexOf("oracle") >= 0 ){
			return dao.getTableColumnInfo_oracle(tableName);
		}
		return  null;
	}
	
	
	

	
	
	
}
