/*
 * 文件名：SettlementInstructionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.settlementinstruction.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.valuation.batch.settlementinstruction.dto.SettlementInstructionDTO;
import com.joyintech.tams.valuation.batch.settlementinstruction.dto.TrdProductDealDTO;

/**
 * 查询处理对象信息Mapper
 * @author ruanhongming
 * @version 1.0 2018年6月8日
 * @see SettlementInstructionMapper
 * @since 1.0
 */
@Repository
public interface SettlementInstructionMapper {
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     */
    public List<SettlementInstructionDTO> selectDealInfo(Map<String, Object> param);
    
    //ADD BY WUJIE ON 20191108
    public  TrdProductDealDTO selectTrdProductDealDTO(Map<String,Object> param);
    
    //查询前置交易的前台指令信息
    public  StlPayOrders selectStlPayOrders(Map<String,Object> param);
    
}
