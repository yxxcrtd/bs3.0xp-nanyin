/**  
 * @Project     service-customer-report  
 * @File        OverallInfoMapper.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao  
 * @Version     V1.0  
 * @Date        2019年8月7日 下午3:29:51  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.OverallInfoDto;

/**  
 * @Description 表内外投资业务总体情况数据库访问层  
 * @ClassName   OverallInfoMapper  
 * @Date        2019年8月7日 下午3:29:51  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Repository
public interface OverallInfoMapper {

	/**  
	 * @Description 根据季末时间查询表内外投资业务总体情况基础数据
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:32:38  
	 * @param @param map
	 * @param @return 参数  
	 * @return List<OverallInfoDto> 返回类型   
	 * @throws  
	 */  
	List<OverallInfoDto> queryOverallInfoList(Map<String,Object> map);
	
	
	/**  
	 * @Description 查询融资融券收益权信息  
	 * @Author      高健  
	 * @Date        2019年8月8日 上午11:08:50  
	 * @param @param map
	 * @param @return 参数  
	 * @return OverallInfoDto 返回类型   
	 * @throws  
	 */  
	OverallInfoDto queryMarginTrading(Map<String,Object> map);
	
	/**  
	 * @Description 根据时间查询非金融债券信息    
	 * @Author      高健  
	 * @Date        2019年8月9日 下午4:35:03  
	 * @param @param startDate
	 * @param @param lastStartDate
	 * @param @return 参数  
	 * @return List<OverallInfoDto> 返回类型   
	 * @throws  
	 */  
	List<OverallInfoDto> queryNotBankDebentureInfoList(@Param("startDate")Date startDate, @Param("lastStartDate")Date lastStartDate);
}
