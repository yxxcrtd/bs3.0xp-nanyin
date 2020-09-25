/*
 * 文件名：BussSettleOrderCommImplService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.settle.impl;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderResultDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 清算指令共通Service
 */
@Service
public class BussSettleOrderCommImplService extends BaseService implements BussSettleOrderCommService {

    @Override
    public BussSettleOrderResultDto generateSettleOrder(TrdProductDeal deal, String isTotalTrade,
                                                        String bankLendingType,
                                                        String portfolioId,String dealMode,String ieFlag) {

        // modify by wj 20200620  五个特殊参数 修改 为特殊取值方法
        String institutionCode = "";
        if(MultilevelDataAuthorityUtil.isEnabled()){
            institutionCode =  MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName());
        }

        // 特拆账户.账户代码
        String LENDING_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.LENDING_ACCT_NO_KEY,institutionCode);
        // 特拆账户.账户名称
        String LENDING_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.LENDING_ACCT_NAME_KEY,institutionCode);
        // 产品托管户(虚拟).账户代码
        String PROD_TRU_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.PROD_TRU_ACCT_NO_KEY,institutionCode);
        // 产品托管户(虚拟).账户名称
        String PROD_TRU_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.PROD_TRU_ACCT_NAME_KEY,institutionCode);
        // 清算户（虚拟）.账户代码
//        String SETTLE_ACCT_NO = CacheUtil.getSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY);
//        // 清算户（虚拟）.账户名称
//        String SETTLE_ACCT_NAME = CacheUtil.getSysParam(BussinessConstant.SETTLE_ACCT_NAME_KEY);
        String SETTLE_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY,institutionCode);
        String SETTLE_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.SETTLE_ACCT_NAME_KEY,institutionCode);

        // add by renping 利率互换划款指令用行内统一账户 20190826 start
        String IRS_ACCOUNT_NO = CacheUtil.getSysParam(BussinessConstant.IRS_ACCOUNT_NO_KEY);
        String IRS_ACCOUNT_NAME = CacheUtil.getSysParam(BussinessConstant.IRS_ACCOUNT_NAME_KEY);
        // add by renping 利率互换划款指令用行内统一账户 20190826 end
        // modified by wangguoxian 外汇即远掉期集中净额清算集中账户 20190805 start
        // 外汇即远掉期集中净额清算集中账户.账户代码
        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NO = CacheUtil.getSysParam(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NO_KEY+deal.getCcy());
        // 外汇即远掉期集中净额清算集中账户.账户名称
        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME = CacheUtil.getSysParam(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME_KEY+deal.getCcy());
        return restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME
                                          + "/BussSettleOrderComm/generateSettleOrder?isTotalTrade="
                                          + isTotalTrade + "&bankLendingType=" + bankLendingType
                                          + "&dealMode=" + dealMode + "&ieFlag=" + ieFlag
                                          + "&portfolioId=" + portfolioId + "&LENDING_ACCT_NO="
                                          + LENDING_ACCT_NO + "&LENDING_ACCT_NAME="
                                          + LENDING_ACCT_NAME + "&PROD_TRU_ACCT_NO="
                                          + PROD_TRU_ACCT_NO + "&PROD_TRU_ACCT_NAME="
                                          + PROD_TRU_ACCT_NAME + "&SETTLE_ACCT_NO="
                                          + SETTLE_ACCT_NO + "&SETTLE_ACCT_NAME="
                                          + SETTLE_ACCT_NAME
                                          + "&IRS_ACCOUNT_NO=" + IRS_ACCOUNT_NO
                                          + "&IRS_ACCOUNT_NAME=" + IRS_ACCOUNT_NAME
                                          + "&CENTRAL_NET_LIQUIDATION_ACCOUNT_NO="
                                          + CENTRAL_NET_LIQUIDATION_ACCOUNT_NO+"&CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME="
                                          + CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME,
            deal, BussSettleOrderResultDto.class);
        // modified by wangguoxian 外汇即远掉期集中净额清算集中账户 20190805 end
    }

    @Override
    public BussSettleOrderResultDto generateSettleOrder(BussSettleOrderParameterDto dto) {
        return generateSettleOrder(dto.getDeal(), dto.getIsTotalTrade(), dto.getBankLendingType(),
            dto.getPortfolioId(),dto.getDealMode(),dto.getIeFlag());
    }

    @Override
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(String bizCode) {
        return restTemplate.postForList(
            ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/selectOrdersByBizCode",
            bizCode, BussTrdTransferOrderBase.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SelectOptionDto> queryAcctList(Map<String, Object> paramMap) {
        return restTemplate.postForList(
            ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/queryAcctList",
            paramMap, SelectOptionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StlCapacctInfo queryStlCapacctInfoByBusiAcctNo(String ccy, String busiAcctNo) {
        return restTemplate.getForObject(ServiceApplication.SETTLE_SERVICE_NAME
                                         + "/BussSettleOrderComm/queryStlCapacctInfoByBusiAcctNo?ccy="
                                         + ccy + "&busiAcctNo=" + busiAcctNo,
            StlCapacctInfo.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<StlCapacctInfo> selectProdCapacctInfoList(Map<String, Object> paramMap) {
        return restTemplate.postForList(
                ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/selectProdCapacctInfoList",
                paramMap, StlCapacctInfo.class);
    }

    @Override
    public List<StlCapacctInfo> getAcctNoByInfo(Map<String, Object> paramMap) {
        return restTemplate.postForList(
                ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/getAcctNoByInfo",
                paramMap, StlCapacctInfo.class);
    }

    @Override
    public List<StlCapacctInfo> selectCounterCapacctInfoList(Map<String, Object> paramMap) {
        return restTemplate.postForList(
                ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/selectCounterCapacctInfoList",
                paramMap, StlCapacctInfo.class);
    }
}
