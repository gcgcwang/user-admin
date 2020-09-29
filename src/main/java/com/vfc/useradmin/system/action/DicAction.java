package com.vfc.useradmin.system.action;


import java.util.List;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vfc.useradmin.jpa.EntryMapper;
import com.vfc.useradmin.jpa.JpaService;
import com.vfc.useradmin.system.vo.DicVO;
import com.vfc.useradmin.system.vo.Pager;
import com.vfc.useradmin.system.vo.SysUser;
import com.vfc.useradmin.system.vo.UserResource;

@Controller
@RequestMapping("/dic")
public class DicAction extends BaseAction{

	
	@Autowired
	private JpaService jpaService;
	@Autowired
	private EntryMapper entryMapper;
	
	@RequestMapping("/findDicPager.do")
	@ResponseBody
	public Pager<DicVO> findPagedic(Pager<DicVO>  pager,HttpServletRequest req){
		
		jpaService.findCascededAll( UserResource.class);
		return jpaService.findPager(DicVO.class, pager);
		
	}
	
}
