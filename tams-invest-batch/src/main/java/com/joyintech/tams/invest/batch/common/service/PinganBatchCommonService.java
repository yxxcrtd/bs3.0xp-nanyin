/*
 * 文件名：PinganBatchCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderResultDto;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.component.business.common.BaseAssetCashPositionCommService;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioPositionsFlowInputDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 批处理共通Service
 *
 * @author etliu
 * @version 1.0 2018年12月12日
 * @see PinganBatchCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class PinganBatchCommonService extends BaseService {

    /**
     * 业务共通组件.资产头寸现金持仓共通组件
     */
    @Autowired
    private BaseAssetCashPositionCommService assetPositionsCashCommServiceImpl;

    /**
     * 业务共通组件.资产头寸持仓共通组件
     */
    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 业务共通组件.资产头寸现金共通组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 持仓现金执行共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * 清算指令生成
     *
     * @param paramDto 参数dto
     * @return 生成结果
     * @see
     * @since 1.0
     */
    public List<WflStlPayOrders> generateSettleOrder(BatchBussSettleOrderParameterDto paramDto) {

        String institutionCode = MultilevelDataAuthorityUtil.getOrgCodeByBussinessIDType(paramDto.getPortfolioId(), com.joyintech.framework.constant.DictCodeList.联社模式查询数据权限_修改和删除.getValue(), DictCodeList.共通业务类型_组合管理.getValue());

        // 特拆账户.账户代码
        String LENDING_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.LENDING_ACCT_NO_KEY,institutionCode);
        // 特拆账户.账户名称
        String LENDING_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.LENDING_ACCT_NAME_KEY,institutionCode);
        // 产品托管户(虚拟).账户代码
        String PROD_TRU_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.PROD_TRU_ACCT_NO_KEY,institutionCode);
        // 特拆账户.账户代码
        String PROD_TRU_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.PROD_TRU_ACCT_NAME_KEY,institutionCode);

        // modify  by wj 20200620
        String SETTLE_ACCT_NO = CacheUtil.getSpecialSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY,institutionCode);
        String SETTLE_ACCT_NAME = CacheUtil.getSpecialSysParam(BussinessConstant.SETTLE_ACCT_NAME_KEY,institutionCode);
        //        // 清算户（虚拟）.账户代码
//        String SETTLE_ACCT_NO = CacheUtil.getSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY);
//        // 清算户（虚拟）.账户名称
//        String SETTLE_ACCT_NAME = CacheUtil.getSysParam(BussinessConstant.SETTLE_ACCT_NAME_KEY);

        // add by renping 外汇即远掉期集中净额清算集中账户 20190805 start
        // 外汇即远掉期集中净额清算集中账户.账户代码
        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NO = CacheUtil
                .getSysParam(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NO_KEY
                        + paramDto.getDeal().getCcy());
        // 外汇即远掉期集中净额清算集中账户.账户名称
        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME = CacheUtil
                .getSysParam(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME_KEY
                        + paramDto.getDeal().getCcy());
        // add by renping 外汇即远掉期集中净额清算集中账户 20190805 end
        BatchBussSettleOrderResultDto tempResultDto = restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/generateSettleOrder?isTotalTrade="
                                          + paramDto.getIsTotalTrade() + "&bankLendingType=" + paramDto.getBankLendingType()
                                          + "&portfolioId=" + paramDto.getPortfolioId()
                                          + "&LENDING_ACCT_NO=" + LENDING_ACCT_NO
                                          + "&LENDING_ACCT_NAME=" + LENDING_ACCT_NAME
                                          + "&PROD_TRU_ACCT_NO=" + PROD_TRU_ACCT_NO
                                          + "&PROD_TRU_ACCT_NAME=" + PROD_TRU_ACCT_NAME
                                          + "&SETTLE_ACCT_NO=" + SETTLE_ACCT_NO
                                          + "&SETTLE_ACCT_NAME=" + SETTLE_ACCT_NAME
                                          // add by renping 外汇即远掉期集中净额清算集中账户 20190805 start
                                          + "&CENTRAL_NET_LIQUIDATION_ACCOUNT_NO=" + CENTRAL_NET_LIQUIDATION_ACCOUNT_NO
                                          + "&CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME=" + CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME,
                                          // add by renping 外汇即远掉期集中净额清算集中账户 20190805 end
                                          paramDto.getDeal(), BatchBussSettleOrderResultDto.class);

        if (tempResultDto == null) {
            return null;
        }

        // 组合代码
        tempResultDto.setPortfolioId(paramDto.getPortfolioId());
        // 交易编号
        tempResultDto.setTradeId(paramDto.getDeal().getTradeId());
        // 指令状态
        tempResultDto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());

        String 付款账户 = null;
        if (!StringUtil.isEmptyList(tempResultDto.getMyCapacctInfoList())) {
            付款账户 = tempResultDto.getMyCapacctInfoList().get(0).getAcctNo();
        }
        String 收款账户 = null;
        if (!StringUtil.isEmptyList(tempResultDto.getCounterCapacctInfoList())) {
            收款账户 = tempResultDto.getCounterCapacctInfoList().get(0).getAcctNo();
        }
        if (StringUtil.equals(tempResultDto.getPs(), DictCodeListExt1.收支方向_支出.getValue())) {
            tempResultDto.setMyAcctNo(付款账户);
            tempResultDto.setCounterAcctNo(收款账户);
        }
        else {
            tempResultDto.setMyAcctNo(收款账户);
            tempResultDto.setCounterAcctNo(付款账户);
        }

        WflStlPayOrders resultDto = new WflStlPayOrders();
        BeanUtil.copyPropertiesIgnoreNull(resultDto, tempResultDto);

        // 工作流状态
        resultDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

        return Arrays.asList(resultDto);
    }

    /**
     * 生成业务编号
     *
     * @param parameter 共通业务类型
     * @return 业务编号
     * @throws Exception
     */
    public String buildBussinessCode(BussinessCodeParameter parameter) {
        BussinessCodeParameter req = new BussinessCodeParameter();
        req.setMapInfo(parameter.initMapInfo());
        // 生成的业务编号
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/selectRule", req,
            String.class);
    }

    /**
     * 交易生效持仓信息登录
     * 
     * @param finProductTradeDto FinProductTradeDto
     * @return 处理状态
     * @see
     * @since 1.0
     */
    public CommonResult deal有效(FinProductTradeDto finProductTradeDto) {
        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        // 3.写入类型为[实际]的组合持仓流水与组合现金流水
        insertPosition(finProductTradeDto);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    public Boolean insertPosition(FinProductTradeDto finProductTradeDto) {
        DictCodeList 持仓类型 = null;
        boolean needNegative = false;
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = true;
        }
        else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_可用;
            needNegative = false;
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = false;
        }
        if (持仓类型 == null) {
            return false;
        }

        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            PortfolioPositionsFlowInputDto inputDto = new PortfolioPositionsFlowInputDto();
            inputDto.setPositionType(持仓类型.getValue());
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
//            增加附属表dto
            inputDto.setTrdFinProductBarterList(baseTradeProductDealDto.getTrdFinProductBarterList());
            inputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
            inputDto.setAssetType2(finProductTradeDto.getAssetType2());
            List<PtlSecPositionFlowDto> ptlSecPositionFlowDtoss = assetPositionsCashCommServiceImpl.positionDeal(inputDto);
            ptlSecPositionFlows.addAll(ptlSecPositionFlowDtoss);

            if (baseTradeProductDealDto.getStlPayOrdersList() != null && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
                PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
                cashInputDto.setPositionType(持仓类型.getValue());
                cashInputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                cashInputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                cashInputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
                cashInputDto.setAssetType2(finProductTradeDto.getAssetType2());
                cashInputDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList());
                cashInputDto.setIeFlag(finProductTradeDto.getIeFlag());
                List<PtlCashPositionFlowDto> resultList = assetPositionsCashCommServiceImpl.cashDeal(cashInputDto);
                ptlCashPositionFlows.addAll(resultList);
            }
            //        现金与持仓执行共同
            if(baseEtrCashPositionCommService != null && !finProductTradeDto.isNeedExecuteEtrPosition()){
                //modify by mashukai 20210306 修复债券借贷融入交易后指令可用持仓未改变问题 start
                if(finProductTradeDto.getBussRepoSec() != null){
                    baseTradeProductDealDto.setBussRepoSec(finProductTradeDto.getBussRepoSec());
                }
                //modify by mashukai 20210306 修复债券借贷融入交易后指令可用持仓未改变问题 end
                baseTradeProductDealDto.setAssetType2(finProductTradeDto.getAssetType2());
                baseTradeProductDealDto.setBatch(true);// 自动批处理
                baseEtrCashPositionCommService.executeEtrCashPosition(baseTradeProductDealDto, 持仓类型, needNegative,finProductTradeDto.getIeFlag(),finProductTradeDto.getIsOverdueDeal());
            }
        }
        if (needNegative) {
            if (ptlCashPositionFlows != null && !ptlCashPositionFlows.isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPosition : ptlCashPositionFlows) {
                    if (ptlCashPosition.getAmount() != null) {
                        ptlCashPosition.setAmount(ptlCashPosition.getAmount().negate());
                    }
                    if (ptlCashPosition.getOrderId().lastIndexOf('R') != ptlCashPosition.getOrderId().length() - 1) {
                        ptlCashPosition.setOrderId(ptlCashPosition.getOrderId() + "R");
                    }
                }
            }
            if (ptlSecPositionFlows != null && !ptlSecPositionFlows.isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPosition : ptlSecPositionFlows) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                    if (ptlSecPosition.getTradeId().lastIndexOf('R') != ptlSecPosition.getTradeId().length() - 1) {
                        ptlSecPosition.setTradeId(ptlSecPosition.getTradeId() + "R");
                    }
                }
            }
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        // 更新持仓流水
        if (ptlPositionHoldDto.getPtlSecPositionFlows() != null
            && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
            for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlow);
            }
        }
        // 更新现金流水
        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
            && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }
        return Boolean.TRUE;
    }

}
