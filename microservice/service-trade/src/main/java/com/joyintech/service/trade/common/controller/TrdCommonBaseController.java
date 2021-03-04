/*
 * 文件名：PtlActivityPeriod001Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：fanshuanghu
 * 修改时间：2018年9月17日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.common.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTradeInfoDto;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.core.*;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepBankBackAssetInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDlRepoInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepBankBackAssetInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepTradeInfoDto;
import com.joyintech.tams.component.base.report.trade.BaseRepTradeInfoService;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.dto.*;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;

/**
 * 交易基础组件调用
 *
 * @author 强成西
 * @version 1.0 2018年9月17日
 * @see TrdCommonBaseController
 * @since 1.0
 */
@RestController("trdcommonbaseCtr_dtx")
@RequestMapping("/common/trdcommonbase")
public class TrdCommonBaseController {

    /**
     * 交易基础组件调用Service
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * 金融产品交易基础组件组件
     */
    @Autowired
    private BaseTradeProductDealService baseTradeProductDealServiceImpl;

    /**
     * 金融产品现金流信息组件Serivice
     */
    @Autowired
    private BaseFinProductCashInfoService baseFinProductCashInfoService;

    /**
     * 现金流计算规则组件Serivice
     */
    @Autowired
    private BaseFinProductCashCalcRuleService baseFinProductCashCalcRuleService;

    /**
     * 金融产品现金流支付规则组件
     */
    @Autowired
    private BaseFinProductPayRuleService baseFinProductPayRuleService;

    /**
     * 现金流利率规则信息组件
     */
    @Autowired
    private BaseFinProductCashRateRuleService baseFinProductCashRateRuleService;

    /**
     * 金融产品现金流支付计划组件Service
     */
    @Autowired
    private BaseFinProductCashPayPlanService baseFinProductCashPayPlanService;

    /**
     * 交易报送信息组件
     */
    @Autowired
    private BaseRepTradeInfoService baseRepTradeInfoService;

    /**
     * 资产负债信息报送组件
     */
    @Autowired
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;

    /**
     * BaseRepBankBackAssetInfoService
     */
    @Autowired
    private BaseRepBankBackAssetInfoService baseRepBankBackAssetInfoService;

    /**
     * BasePtlSecPositionInfoService
     */
    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * BasePtlCashPositionInfoService
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * BaseEtrInvestInfo
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoServiceImpl;

    /**
     * BaseRepBankBackAssetInfoService
     */
    @Autowired
    private BaseRepDlRepoInfoService baseRepDlRepoInfoService;

    /**
     * BaseFinProductCashResetPlanService
     */
    @Autowired
    private BaseFinProductCashResetPlanService baseFinProductCashResetPlanService;
    /**
     *
     */
    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;
    
    /**
     * 根据委托代码查询生效的委托
     *
     * @return BaseEtrInvestInfoDto
     */
    @PostMapping("findEtrInfo")
    public BaseEtrInvestInfoDto findEtrInfo(@RequestBody String entrustId) {
        return baseEtrInvestInfoServiceImpl.queryActiveMandate(entrustId);
    }

    /**
     * 【金融产品交易组件.新建金融产品交易信息】,写入金融产品交易信息
     *
     * @return 成功/失败
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertTradeInfo")
    public boolean insertPosition(@RequestBody List<BaseTradeProductDealDto> baseTradeProductDealDtos) {
        return baseTradeProductDealServiceImpl.insert(baseTradeProductDealDtos);
    }

    /**
     * 基础组件.【金融产品交易组件.撤销交易金融产品交易信息】，撤销金融产品交易信息
     *
     * @return 成功/失败
     */
    @Transactional
    @DTXTransaction
    @PostMapping("revokeTradeInfo")
    public boolean revokeTradeInfo(@RequestBody List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeDtos) {
        return baseTradeProductDealServiceImpl.revoke(baseTradeProductDealRevokeDtos);
    }

    /**
     * 调用基础组件【组合可用持仓和实际持仓查询更新组件】.删除持仓流水
     *
     * @return 成功/失败
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteSecPosition")
    public int deleteSecPosition(@RequestBody PtlSecPositionFlowDto positionQueryDto) {
        return basePtlSecPositionInfoService.deleteSecPosition(positionQueryDto);
    }

    /**
     * 调用基础组件【组合可用持仓和实际持仓查询更新组件】.删除持仓流水
     *
     * @return 成功/失败
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteCashPosition")
    public boolean deleteCashPosition(@RequestBody PtlCashPositionFlowDto positionQueryDto) {
        return basePtlCashPositionInfoService.deleteCashPosition(positionQueryDto);
    }

    /**
     * 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
     *
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("selectTradeInfo")
    public List<BaseTradeProductDealDto> selectTradeInfo(String tradeId) {
        List<String> list = new ArrayList<String>();
        list.add(tradeId);
        return baseTradeProductDealServiceImpl.select(list);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doRevokeInsertTotle")
    public String doRevokeInsertTotle(@RequestBody Map<String, Object> params) {
        params.put("time", new Date());
        investPeriodCommonService.insertWflTable(params);
        return Boolean.TRUE.toString();
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doRevokeComplete")
    public String doRevokeComplete(@RequestBody Map<String, Object> params) {
        String workflowId = (String) params.get("workflowId");
        if(StringUtil.isNotEmpty(workflowId)) {
            investPeriodCommonService.deleteByWorkflowId(workflowId);
        }
        TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService.revoke汇总和交易相关表((String) params.get("bizCode"),
                (String) params.get("updateUser"), (String) params.get("revokeReason"), null, null);
        return Boolean.TRUE.toString();
    }

    /**
     * 根据工作流代码查询金融产品交易附属业务流程
     *
     * @param workflowId 工作流代码
     * @return 金融产品交易附属业务流程信息
     * @see
     * @since 1.0
     */
    @GetMapping("selectWflTrdProductDealAdd/{workflowId}")
    public WflTrdProductDealAdd selectWflTrdProductDealAdd(@PathVariable("workflowId") String workflowId) {
        return trdCommonBaseService.selectWflTrdProductDealAdd(workflowId);
    }

    /**
     * 根据工作流代码查询指令编号列表
     *
     * @param workflowId 工作流代码
     * @return 指令编号列表
     * @see
     * @since 1.0
     */
    @GetMapping("selectOrderIdList/{workflowId}")
    public List<WflStlPayOrders> selectOrderIdList(@PathVariable("workflowId") String workflowId) {
        return trdCommonBaseService.selectOrderIdList(workflowId);
    }

    /**
     * 检索已质押标准券额度
     *
     * @param portfolioId  组合代码
     * @param secAcctId    证券账户
     * @param finProdId    金融产品代码（债券代码）
     * @param searchDate   检索日期（yyyy-MM-dd）
     * @param isWithInvAim 检索项目是否包含检索投资目的
     * @return 已质押标准券信息Dto列表
     * @see
     * @since 1.0
     */
    @GetMapping("selectPledgeStandardPrinAmt/{portfolioId}/{secAcctId}/{finProdId}/{searchDate}/{isWithInvAim}")
    public List<TrdStaSecInfoDto> selectPledgeStandardPrinAmt(@PathVariable("portfolioId") String portfolioId,
                                                              @PathVariable("secAcctId") String secAcctId,
                                                              @PathVariable("finProdId") String finProdId,
                                                              @PathVariable("searchDate") String searchDate,
                                                              @PathVariable("isWithInvAim") Boolean isWithInvAim) {
        return trdCommonBaseService.selectPledgeStandardPrinAmt(portfolioId, secAcctId, finProdId, searchDate, isWithInvAim);
    }

    /**
     * 投资交易新建时，现金流相关信息处理
     *
     * @param inputDto 金融产品现金流相关信息
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertFinProductCashData")
    public boolean insertFinProductCashData(@RequestBody BaseFinProductCashInputDto inputDto) {
        // List<现金流信息Entity>
        List<FinCashInfo> finCashInfoList = inputDto.getFinCashInfoList();
        if (!StringUtil.isEmptyList(finCashInfoList)) {
            baseFinProductCashInfoService.insertList(finCashInfoList);
        }

        // List<现金流支付规则表Entity>
        List<FinCashPayRule> finCashPayRuleList = inputDto.getFinCashPayRuleList();
        if (!StringUtil.isEmptyList(finCashPayRuleList)) {
            baseFinProductPayRuleService.insert(finCashPayRuleList);
        }

        // List<现金流计算规则表Entity>
        List<FinCashCalcRule> finCashCalcRuleList = inputDto.getFinCashCalcRuleList();
        if (!StringUtil.isEmptyList(finCashCalcRuleList)) {
            baseFinProductCashCalcRuleService.insert(finCashCalcRuleList);
        }

        // List<现金流利率信息表Entity>
        List<FinRateInfo> finRateInfoList = inputDto.getFinRateInfoList();
        if (!StringUtil.isEmptyList(finRateInfoList)) {
            baseFinProductCashRateRuleService.insert(finRateInfoList);
        }

        // List<现金流计划表Entity>
        List<FinCashPlan> finCashPlanList = inputDto.getFinCashPlanList();
        if (!StringUtil.isEmptyList(finCashPlanList)) {
            baseFinProductCashPayPlanService.insertList(finCashPlanList);
        }
        
        // List<利率重置计划表Entity>
        List<FinCashResetPlan> finCashResetPlanList = inputDto.getFinCashResetPlanList();
        if (!StringUtil.isEmptyList(finCashResetPlanList)) {
            baseFinProductCashResetPlanService.insertList(finCashResetPlanList);
        }

        return true;
    }

    /**
     * 查询产品登记编码
     *
     * @param portfolioId 组合代码
     * @return 产品登记编码
     * @see
     * @since 1.0
     */
    @PostMapping("selectProdRegistCode/{portfolioId}")
    public String selectProdRegistCode(@PathVariable("portfolioId") String portfolioId) {
        return trdCommonBaseService.selectProdRegistCode(portfolioId);
    }

    /**
     * 调用基础组件.【交易报送组件】将交易报送信息Entity插入交易报送信息表
     *
     * @param repTradeList 交易报送信息Entity列表
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepTrade")
    public boolean insertRepTrade(@RequestBody List<RepTrade> repTradeList) {
        return baseRepTradeInfoService.insert(repTradeList);
    }

    /**
     * 调用基础组件.【交易报送组件】删除交易报送信息
     *
     * @param bankTradeCodeList List<行内交易编码>
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteRepTrade")
    public boolean deleteRepTrade(@RequestBody List<String> bankTradeCodeList) {
        BaseRepTradeInfoDto dto = new BaseRepTradeInfoDto();
        dto.setBankTradeCodeList(bankTradeCodeList);
        return baseRepTradeInfoService.delete(dto);
    }

    /**
     * 调用资产负债信息报送组件
     *
     * @param repAssetDeptList 资产负债报送信息Entity列表
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepAssetDebt")
    public boolean insertRepAssetDebt(@RequestBody List<RepAssetDept> repAssetDeptList) {
        return baseRepAssetDeptInfoService.insert(repAssetDeptList);
    }

    /**
     * 调用资产负债信息报送组件 删除
     *
     * @param finprodIdList finprodIdList
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteRepAssetDebt")
    public boolean deleteRepAssetDebt(@RequestBody List<String> finprodIdList) {
        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        baseRepAssetDeptInfoDto.setFinprodIdList(finprodIdList);
        return baseRepAssetDeptInfoService.deleteByPrimaryKey(baseRepAssetDeptInfoDto);
    }

    /**
     * 利用金融产品代码作为条件把资产负债报送信息表中的报送状态更改为是
     *
     * @param finprodId 金融产品代码
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateRepAssetDebt")
    public boolean updateRepAssetDebt(@RequestBody String finprodId) {
        return trdCommonBaseService.updateRepAssetDebt(finprodId);
    }

    /**
     * 调用负债(同业拆入卖出回购)报送信息组件
     *
     * @param repLiabilitiesList 负债(同业拆入卖出回购)报送信息Entity
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepLiabilities")
    public boolean insertRepLiabilities(@RequestBody List<RepLiabilities> repLiabilitiesList) {
        return baseRepBankBackAssetInfoService.insert(repLiabilitiesList);
    }

    /**
     * 调用负债(拆放同业及买入返售)报送信息组件
     *
     * @param repDlRepoInfoList 负债(同业拆入卖出回购)报送信息Entity
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepDlRepoInfo")
    public boolean insertRepDlRepoInfo(@RequestBody List<RepAssetInterLend> repDlRepoInfoList) {
        return baseRepDlRepoInfoService.insert(repDlRepoInfoList);
    }

    /**
     * 调用负债(同业拆入卖出回购)报送信息组件 删除
     *
     * @param repFinprodIdList finprodIdList
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteRepLiabilities")
    public boolean deleteRepLiabilities(@RequestBody List<String> repFinprodIdList) {
        BaseRepBankBackAssetInfoDto dto = new BaseRepBankBackAssetInfoDto();
        dto.setRepFinprodIdList(repFinprodIdList);
        return baseRepBankBackAssetInfoService.deleteByPrimaryKey(dto);
    }
    
    /**
     * 根据工作流代码查询金融产品交易流程
     * 
     * @param workflowId 工作流代码
     * @return 金融产品交易流程信息
     * @see
     * @since 1.0
     */
    @GetMapping("selectWflTrdProductDeal/{workflowId}")
    public AllTradeCommonInputDto selectWflTrdProductDeal(@PathVariable("workflowId") String workflowId) {
        return trdCommonBaseService.selectWflTrdProductDeal(workflowId);
    }
    /**
     * 利率互换付息计划修改（生效）
     *处理逻辑：
     1、	根据利率互换付息计划维护DTO.固息端现金流代码 = 现金流计划表.现金流代码，删除现金流计划表对应数据。

     2、	根据利率互换付息计划维护DTO.浮息端现金流代码 = 现金流计划表.现金流代码，删除现金流计划表对应数据。

     3、	将List<现金流计划Entity>保存进现金流计划表。
     * by xkni 20190816 Add
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("paymentPlanEdit")
    public Boolean paymentPlanEdit(@RequestBody PaymentPlanEditDto paymentPlanEditDto) {
        // List<现金流计划表Entity>--固息端资金计划维护
        if (!StringUtil.isEmptyList(paymentPlanEditDto.getFixedFinCashPlanList())) {

            //新增数据前根据现金流代码删除已有数据
            baseFinProductCashPayPlanService.deleteByCashId(paymentPlanEditDto.getFixedFinCashPlanList().get(0).getCashId());
            baseFinProductCashPayPlanService.insertList(paymentPlanEditDto.getFixedFinCashPlanList());
        }
        // List<现金流计划表Entity>--浮息端资金计划维护
        if (!StringUtil.isEmptyList(paymentPlanEditDto.getFloatFinCashPlanList())) {
            //新增数据前根据现金流代码删除已有数据
            baseFinProductCashPayPlanService.deleteByCashId(paymentPlanEditDto.getFloatFinCashPlanList().get(0).getCashId());
            baseFinProductCashPayPlanService.insertList(paymentPlanEditDto.getFloatFinCashPlanList());
        }
        return true;

    }
    // 2.0自动生成反向交易 bug修改 start
    /**
     * 查询系统参数
     *
     * @param systemCode 系统代码
     * @param paraCode 参数code
     *
     * @return 系统参数
     * @see
     * @since 1.0
     */
    @GetMapping("selectParam/{systemCode}/{paraCode}")
    public String selectParam(@PathVariable("systemCode") String systemCode,
                                                   @PathVariable("paraCode") String paraCode) {
        return trdCommonBaseService.selectParam(systemCode, paraCode);
    }
    // 2.0自动生成反向交易 bug修改 end
    
    /**
     * 指令接收交易录单根据委托代码查询流程中已录入金额
     * @author wangguiqi
     * @return BigDecimal
     */
    @PostMapping("getWflPrinAmt")
    public BigDecimal getWflPrinAmt(@RequestBody Map<String,Object> map) {
        return trdCommonBaseService.queryWflPrinAmt(map);
    }
    /**
     * 指令接收交易录单根据委托代码查询委托金额
     * @author wangguiqi
     * @return BigDecimal
     */
    @PostMapping("getEntrustAmtByEntrustId")
    public BigDecimal getEntrustAmtByEntrustId(@RequestBody String entrustId) {
        return trdCommonBaseService.queryEntrustAmtByEntrustId(entrustId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertAutoClassifyBatch")
    public String insertAutoClassifyBatch(@RequestBody TrdCommonParam trdCommonParam){
//        Object[] obj = new Object[]{};
//        if(trdCommonParam != null){
//            mapperDelegateService.insertAutoClassifyBatch(mapperDelegateService.analysisEntityForObjectArr(trdCommonParam));
//            return "Success";
//        }
//        else{
//            return "Fail";
//        }
        trdCommonBaseService.insertAutoClassifyBatch(trdCommonParam);
        return "Success";
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertAutoClassifyBatchWfl")
    public String insertAutoClassifyBatchWfl(@RequestBody TrdCommonWflParam trdCommonWflParam){
//        Object[] obj = new Object[]{};
//        if(trdCommonWflParam != null){
//            mapperDelegateService.insertAutoClassifyBatch(mapperDelegateService.analysisEntityForObjectArr(trdCommonWflParam));
//            return "Success";
//        }
//        else{
//            return "Fail";
//        }
        trdCommonBaseService.insertAutoClassifyBatchWfl(trdCommonWflParam);
        return "Success";
    }

    /**
     * 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
     *
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("selectTotelTradeInfo")
    public List<BaseTradeProductDealDto> selectTotelTradeInfo(String tradeId) {
        List<String> list = new ArrayList<String>();
        list.add(tradeId);
        return baseTradeProductDealServiceImpl.selectTotalDeal(list);
    }

    /**
     * 模拟试算
     * 金融产品交易主方法查询
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectTradeinfodeal")
    public MockTradeInfoDto selectTradeinfodeal(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectTradeinfodeal(param);
    }

    /**
     * 模拟试算
     * 资产端交易列表
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectTradedealz")
    public List<MockTrdProductDealDto> selectTradedealz(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectTradedealz(param);
    }

    /**
     * 模拟试算
     * 负债端交易列表
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectTradedealf")
    public List<MockTrdProductDealDto> selectTradedealf(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectTradedealf(param);
    }

    /**
     * 模拟试算
     * 资产端T+N交易列表
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectTradedealztn")
    public List<MockTrdProductDealDto> selectTradedealztn(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectTradedealztn(param);
    }

    /**
     * 模拟试算
     * 负债端T+N交易列表
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectTradedealftn")
    public List<MockTrdProductDealDto> selectTradedealftn(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectTradedealftn(param);
    }

    /**
     * 模拟试算
     * 费用交易列表查询
     * @author wl
     * 2020-04-14
     */
    @PostMapping("selectFyTradedeal")
    public List<MockTrdProductDealDto> selectFyTradedeal(@RequestBody Map<String, Object> param) throws Exception {
        return trdCommonBaseService.selectFyTradedeal(param);
    }
}
