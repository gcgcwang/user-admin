package com.vfc.useradmin.system.bo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vfc.useradmin.system.po.AppPO;
import com.vfc.useradmin.system.vo.App;
import com.vfc.useradmin.system.vo.Pager;

@Service
public class AppBO extends BaseBO{
	
	@Autowired
	//private  AppDao appDao;
	private  AppPO appPO;
	
	public Pager findPageApp(Pager<App> pager) {
		// TODO Auto-generated method stub
		try {
			pager.setTotal(appPO.selectTotal());
			pager.setRows(appPO.findList(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	}
}
