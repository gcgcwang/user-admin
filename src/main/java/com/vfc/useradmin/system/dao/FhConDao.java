package com.vfc.useradmin.system.dao;

import java.util.List;

import com.vfc.useradmin.system.common.MyBatisDao;
import com.vfc.useradmin.system.vo.FhConVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FhConDao {
	
	public FhConVO findByID(String cid);
	
	
	public List<FhConVO> findList(FhConVO fhConVO);
}
