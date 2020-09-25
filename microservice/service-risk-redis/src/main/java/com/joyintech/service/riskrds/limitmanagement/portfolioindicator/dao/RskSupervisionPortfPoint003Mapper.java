/*
 * 文件名：RskSupervisionPortfPoint003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackageDtlImp;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPoolConfigDto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPtlPortfolioDto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint003Mapper
 * @since 1.0
 */
@Repository
public interface RskSupervisionPortfPoint003Mapper {

    List<RskPortfolioPoolConfigDto> selectForPage(Map<String, Object> param);
    
    int selectReleteIndByBagId(@Param("bagId")String bagId);
    
    List<RskPortfolioPackageDtlImp> selectPortfBagDtlByBagId(@Param("bagId")String bagId);
    
    List<PtlParamPoolConfig> selectPortfPoolByBagId(@Param("bagId")String bagId);
    
    int deleteBagDtlByBagId(@Param("bagId")String bagId);
    
    int deletePoolByBagId(@Param("bagId")String bagId);

    int searchRepeatPortfNameByPortName(@Param("portName") String portName);
    
    List<RskPtlPortfolioDto> selectPortElementForPage(Map<String, Object> param);
}
