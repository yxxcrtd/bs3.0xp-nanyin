package com.joyintech.tams.batch.report.equitysharecheckservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 投资者份额检查批处理mapper
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月9日
 * @see EquityShareCheckMapper
 * @since 1.0
 */
@Repository
public interface EquityShareCheckMapper {
	int insertChbEqudetCheckResult(@Param("date") String date);
	
	int deleteChbEqudetCheckResult(@Param("date") String date);
}
