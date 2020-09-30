package com.vfc.useradmin.system.dao;

import java.util.List;

import com.vfc.useradmin.system.common.MyBatisDao;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.Pager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppDao {
	
	public long selectTotal();
	
	public List<App> findList(Pager<App> pager);
}
