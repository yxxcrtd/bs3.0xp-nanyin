/*
 * 文件名：CeAssetGroupManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.common.base.entity.CreditLines;

/**
 * 
 * 主体额度占用调整
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupationMapper
 * @since 1.0
 */
@Repository
public interface ObjectLimitOccupationMapper {


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ObjectLimitOccupationDto> selectForPage(Map<String, Object> param);

	/**
	 * 主体名称联想框
	 * @param 
	 * @return
	 */
   	List<SelectOptionDto> getObject(Map<String, Object> param);
   	
   	List<SelectOptionDto> selectByUserName(Map<String, Object> param);
   	
    /**
     * 调整历史查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ObjectLimitOccupationDto> selectHistory(Map<String, Object> param);
    
    List<CreditLines> selectCreditLine(Map<String, Object> param);
}
