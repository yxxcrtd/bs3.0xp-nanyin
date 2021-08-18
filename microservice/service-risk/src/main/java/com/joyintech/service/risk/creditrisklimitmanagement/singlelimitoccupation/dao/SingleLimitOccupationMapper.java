/*
 * 文件名：CeAssetGroupManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;

/**
 * 
 * 单笔资产额度校验规则
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupationMapper
 * @since 1.0
 */
@Repository
public interface SingleLimitOccupationMapper {


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SingleLimitOccupationDto> selectForPage(Map<String, Object> param);

    /**
     * 
     * @param map
     * @return
     */
   	List<SelectOptionDto> getLimitLabel(Map<String, Object> map);
 
	/**
	 * 资产代码联想框
	 * @param finprodMarketId
	 * @return
	 */
   	List<SelectOptionDto> getFinprodMarketIds(Map<String, Object> param);
	
	/**
	 * 获取单资产规则信息
	 * @param creditRuleId
	 * @return
	 */
   	SingleLimitOccupationDto getBussinessEntityByIndId(Map<String, Object> map);
    
}
