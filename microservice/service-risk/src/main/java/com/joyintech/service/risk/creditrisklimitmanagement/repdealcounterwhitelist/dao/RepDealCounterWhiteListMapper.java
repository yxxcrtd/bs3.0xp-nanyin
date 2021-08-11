package com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto.RepDealCounterWhiteListDto;


@Repository
public interface RepDealCounterWhiteListMapper {
		
	List<RepDealCounterWhiteListDto> queryForPage(Map<String, Object> pageParam);
	
	/*对历史导入的白名单设置失效处理*/
	void ivalidHisDate(Map<String, Object> pageParam);
	
	/*根据信用代码查询机构代码*/
	List<String> queryCustomerIdByCode(@Param("insitituteCode")String insitituteCode);
	
}
