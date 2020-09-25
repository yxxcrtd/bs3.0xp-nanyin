/*
 * 文件名：BussSettleOrderCommMapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.fundback.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackPrintDataBaseDto;

@Repository
public interface FundBackgroundPrintMapper {

    FundBackPrintDataBaseDto findDepoPrintData(Map<String, Object> params);

    List<Map<String, String>> findTraders(Map<String, Object> params);

    FundBackPrintDataBaseDto findLendOutPrintData(Map<String, Object> params);

    FundBackPrintDataBaseDto findLendInExpPrintData(Map<String, Object> params);

    FundBackPrintDataBaseDto findSpLendInPrintData(Map<String, Object> params);

    FundBackPrintDataBaseDto findPurOfProjectAsset(Map<String, Object> params);

    FundBackPrintDataBaseDto findAppForPurchase(Map<String, Object> params);

    String findTraderProcInsId(Map<String, Object> params);

    String findNameByUserName(String assignee_);

    FundBackPrintDataBaseDto findAppForProduct(Map<String, Object> params);

    List<String> findTextList(String value);

    FundBackPrintDataBaseDto findDataForCashAndFee(Map<String, Object> params);
}
