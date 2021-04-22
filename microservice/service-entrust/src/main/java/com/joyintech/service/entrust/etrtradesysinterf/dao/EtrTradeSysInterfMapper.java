/*
 * 文件名：EtrProjectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrtradesysinterf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrTradePledgeDto;
import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrTradeSysInterfDto;

/**
 * 指令接口查询mapper
 * 〈功能详细描述〉
 * @author chaowei
 * @version 1.0 2020年3月16日
 * @see EtrTradeSysInterfMapper
 * @since 1.0
 */
@Repository
public interface EtrTradeSysInterfMapper {

    /**
     * 指令接口查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrTradeSysInterfDto> selectList(Map<String, Object> param);
    
    
    List<EtrTradePledgeDto> queryEtrPledgeListByEtrId(@Param("entrustId") String entrustId);
    
    /**
     * 更新指令状态
     * @param entrustId
     * @param entrustStatus
     * @return
     */
    Integer updateEtrStatu(@Param("entrustId") String entrustId,@Param("entrustStatus") String entrustStatus);
   
}
