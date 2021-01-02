/**  
 * @Project     service-customer-report  
 * @File        ChbBankMonthReportMapper.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dao  
 * @Version     V1.0  
 * @Date        2019年8月5日 上午8:53:50  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto.ChbMonthReportViewModelDto;
import com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto.RemainingMaturityDto;

/**  
 * @Description 一级交易商月报表数据库连接层  
 * @ClassName   ChbBankMonthReportMapper  
 * @Date        2019年8月5日 上午8:53:50  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */

@Repository
public interface ChbBankMonthReportMapper {
	
	/**  
	 * @Description 按条件查询属性以及科目余额
	 * @Author      高健  
	 * @Date        2019年8月5日 下午2:42:05  
	 * @param @param map
	 * @param @return 参数  
	 * @return List<ChbMonthReportViewModelDto> 返回类型   
	 * @throws  
	 */  
	List<ChbMonthReportViewModelDto> queryEndingBalance(Map<String,Object> map);
	
	/**  
	 * @Description 根据报表生成日期查询资产剩余期限信息  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:54:10  
	 * @param @param endDate
	 * @param @return 参数  
	 * @return List<PalOpenMarketAssetInfoDto> 返回类型   
	 * @throws  
	 */  
	List<RemainingMaturityDto> queryAssetRemainingMaturity(@Param("endDate") Date endDate);
	
	/**  
	 * @Description 根据报表生成日期查询负债剩余期限信息  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:54:10  
	 * @param @param endDate
	 * @param @return 参数  
	 * @return List<PalOpenMarketAssetInfoDto> 返回类型   
	 * @throws  
	 */  
	List<RemainingMaturityDto> queryBusinessRemainingMaturity(@Param("endDate") Date endDate);
}
