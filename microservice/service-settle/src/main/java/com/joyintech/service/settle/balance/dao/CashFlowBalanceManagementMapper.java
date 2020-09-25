/*
 * 文件名：BussSettleOrderCommMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.balance.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseCashFlowBalanceManagementDto;

@Repository
public interface CashFlowBalanceManagementMapper {
    List<SelectOptionDto> findCounterAcctNo(Map<String, Object> params);

    List<BaseCashFlowBalanceManagementDto> findPage(Map<String, Object> params);

    List<SuggestDto> findCounterAcctNoForSugg(@Param("search") String search);
    
    List<SuggestDto> findAcctNoByTg(@Param("search") String search); 
}
