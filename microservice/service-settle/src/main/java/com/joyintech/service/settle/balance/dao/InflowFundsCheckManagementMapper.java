/*
 * 文件名：BussSettleOrderCommMapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.balance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;

@Repository
public interface InflowFundsCheckManagementMapper {

    List<BaseOutflowFundsSystemFlowDto> findSysPage(Map<String, Object> params);

    List<PortfolioUnmatchedInfo> findPortfolioUnmatched(Map<String, Object> params);
}
