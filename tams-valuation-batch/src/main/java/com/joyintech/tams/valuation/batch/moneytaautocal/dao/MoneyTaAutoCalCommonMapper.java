/*
 * 文件名：MoneyTaAutoCalReaderMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.moneytaautocal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.moneytaautocal.dto.BokAcctLayInfoDto;

@Repository
public interface MoneyTaAutoCalCommonMapper {

    public List<BokAcctLayInfoDto> selectLayInfo(Map<String, Object> param);

    public int countTrade(Map<String, Object> map);

    public List<String> selectTradeId(Map<String, Object> map);
    
    public int delTrdProductDeal(Map<String, Object> map);
    
    public int delProductDealExt(Map<String, Object> map);
    
    public int delBokDealAccounting(Map<String, Object> map);
    
    public int delBokDealAccountingDetail(Map<String, Object> map);
}
