package com.vfc.useradmin.system.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vfc.useradmin.core.DataWrapper;
import com.vfc.useradmin.jpa.JpaService;
import com.vfc.useradmin.system.bo.SysResourceBO;
import com.vfc.useradmin.system.vo.SysResource;

@Controller
@RequestMapping("/sysResource")
public class SysResourceAction extends BaseAction{

	@Autowired 
	private SysResourceBO sysResourceBO;
	@Autowired
	private JpaService jpaService;
	
	@ResponseBody
	@RequestMapping("addBatch.do")
	public DataWrapper addBatch(){
		int res=0;
		try {
			res =sysResourceBO.add();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res==1?success(res):failure(res);
	}
	
	@ResponseBody
	@RequestMapping("findList.do")
	public DataWrapper findList(SysResource inputDto){
		List<SysResource> res=null;
		try {
			res = jpaService.findList(inputDto);

		} catch (Exception e) {
			e.printStackTrace();
			expection(res,e.getMessage());
		}
		return success(res);
	}
	
	@ResponseBody
	@RequestMapping("remove.do")
	public DataWrapper merge(SysResource inputDto){
		int res=0;
		try {
			res = sysResourceBO.remove(inputDto);

		} catch (Exception e) {
			e.printStackTrace();
			return expection(res,e.getMessage());
		}
		return success(res);
	}
	
}
