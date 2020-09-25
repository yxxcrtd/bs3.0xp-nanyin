/*
 * 文件名：ValItemValueGenAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.aop;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 估值核算相关字段值获取设值aop
 *
 * @author etliu
 * @version 1.0 2019年1月14日
 * @see ValItemValueGenAop
 * @since 1.0
 */
@Component
@Aspect
public class ValItemValueGenAop {
    
    /**
     * POINT
     */
    private static final String POINT = "execution(* com.joyintech.tams.common.base.dao.*.*(..))";
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ValItemValueGenAop.class);

    /**
     * @param joinPoint JoinPoint
     */
    @Before(POINT)
    private void beforeMethod(JoinPoint joinPoint) {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0 || (joinPoint.getSignature().getName().indexOf("insert") < 0
                && joinPoint.getSignature().getName().indexOf("update") < 0 )) {
            return;
        }

        logger.info("估值核算相关字段值获取设值aop处理开始--------------------------------");
        
        Object paramObject = joinPoint.getArgs()[0];
        
        Field[] fields = paramObject.getClass().getFields();
        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, paramObject);
            if (value != null && value instanceof String) {
                ReflectionUtils.setField(field, paramObject, ((String) value).trim());
            }
        }

        // 金融产品交易表
        if (paramObject instanceof TrdProductDeal) {
            TrdProductDeal targetTalbleObj = (TrdProductDeal) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
            // 处理业务类型
            ValItemValueGenUtil.genBusiType(targetTalbleObj);
        }
        // 交易类金融产品表
        else if (paramObject instanceof FinTradeProduct) {
            FinTradeProduct targetTalbleObj = (FinTradeProduct) paramObject;
            // 处理金融产品类型（估值核算）
            targetTalbleObj.setCapiIncomeFeature(coverNo(targetTalbleObj.getCapiIncomeFeature()));
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 标的类金融产品表不通过aop
        // 现金流支付规则表
        else if (paramObject instanceof FinCashPayRule) {
            FinCashPayRule targetTalbleObj = (FinCashPayRule) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 现金流计算规则表
        else if (paramObject instanceof FinCashCalcRule) {
            FinCashCalcRule targetTalbleObj = (FinCashCalcRule) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 场外金融产品估值
        else if (paramObject instanceof FinProductValuation) {
            FinProductValuation targetTalbleObj = (FinProductValuation) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 金融产品账户表
        else if (paramObject instanceof StlFinProductAcct) {
            StlFinProductAcct targetTalbleObj = (StlFinProductAcct) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 金融产品交易汇总表
        else if (paramObject instanceof TrdFinProductDealTotal) {
            TrdFinProductDealTotal targetTalbleObj = (TrdFinProductDealTotal) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 行权计划表
        else if (paramObject instanceof FinExercisePlan) {
            FinExercisePlan targetTalbleObj = (FinExercisePlan) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 现金流计划表
        else if (paramObject instanceof FinCashPlan) {
            FinCashPlan targetTalbleObj = (FinCashPlan) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 行权规则表
        else if (paramObject instanceof FinExerciseRule) {
            FinExerciseRule targetTalbleObj = (FinExerciseRule) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 交易类金融产品附属表
        else if (paramObject instanceof FinTradeProductAdd) {
            FinTradeProductAdd targetTalbleObj = (FinTradeProductAdd) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 现金流利率信息表
        else if (paramObject instanceof FinRateInfo) {
            FinRateInfo targetTalbleObj = (FinRateInfo) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 金融产品交易汇总业务流程
        else if (paramObject instanceof WflTrdFinProductDealTotal) {
            WflTrdFinProductDealTotal targetTalbleObj = (WflTrdFinProductDealTotal) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
        }
        // 金融产品交易业务流程
        else if (paramObject instanceof WflTrdProductDeal) {
            WflTrdProductDeal targetTalbleObj = (WflTrdProductDeal) paramObject;
            // 处理金融产品类型（估值核算）
            ValItemValueGenUtil.genFinprodType(targetTalbleObj);
            // 处理业务类型
            ValItemValueGenUtil.genBusiType(targetTalbleObj);
        }
        // 标的类金融产品
        else if (paramObject instanceof FinProduct) {
            // 实际到期日为空，则默认设置成到期日
            FinProduct targetTalbleObj = (FinProduct) paramObject;
            if (targetTalbleObj.getActmdate() == null) {
                targetTalbleObj.setActmdate(targetTalbleObj.getMdate());
            }
            
            targetTalbleObj.setCapiIncomeFeature(coverNo(targetTalbleObj.getCapiIncomeFeature()));
        }
        //REP_PROD_ISSUE产品发行报送信息
        else if(paramObject instanceof  RepProdIssue){
            RepProdIssue targetTalbleObj = (RepProdIssue) paramObject;
            //REP_HOLIDAY_ON_FLAG 是否节假日
            targetTalbleObj.setRepHolidayOnFlag(coverNo(targetTalbleObj.getRepHolidayOnFlag()));

        }
        // REP_ASSET_AST_BILL存值不符合接口规范
        else if(paramObject instanceof RepAssetAstBill){
            RepAssetAstBill targetTalbleObj = (RepAssetAstBill) paramObject;
            //REP_IS_RESALE 是否属于买入返售"
            targetTalbleObj.setRepIsResale(coverNo(targetTalbleObj.getRepIsResale()));
            //"REP_IS_RIGHT 是否为收/受益权
            targetTalbleObj.setRepIsRight(coverNo(targetTalbleObj.getRepIsRight()));

        }
        // REP_ASSET_CREDIT_NONSTD存值不符合接口规范
        else if(paramObject instanceof RepAssetCreditNonstd){
            RepAssetCreditNonstd targetTalbleObj = (RepAssetCreditNonstd) paramObject;
            //REP_IS_RESALE 是否属于买入返售"
            targetTalbleObj.setRepIsResale(coverNo(targetTalbleObj.getRepIsResale()));
            //REP_RATE_FLAG 是否有预期收益率
            targetTalbleObj.setRepRateFlag(coverNo(targetTalbleObj.getRepRateFlag()));
            //REP_INDUSTRY_FLAG 是否属于重点监控行业和领域"
            targetTalbleObj.setRepIndustryFlag(coverNo(targetTalbleObj.getRepIndustryFlag()));
            //部分赎回标识
            targetTalbleObj.setRepPartRedemFlag(coverNo(targetTalbleObj.getRepPartRedemFlag()));
        }
        //REP_ASSET_EQUITY权益类资产
        else if(paramObject instanceof RepAssetEquity){
            RepAssetEquity targetTalbleObj = (RepAssetEquity) paramObject;
            //REP_IS_PLEDGE 是否为质押融资
            targetTalbleObj.setRepIsPledge(coverNo(targetTalbleObj.getRepIsPledge()));
            //是否为债转股 modify by wj 20200410 入库时 将N改为02
            targetTalbleObj.setRepDeptEquity(coverNo(targetTalbleObj.getRepDeptEquity()));
            //是否为优先股
            targetTalbleObj.setRepBankSecPrefer(coverNo(targetTalbleObj.getRepBankSecPrefer()));
        } //REP_ASSET_BOND 债券类资产 modify by wj 20200410 入库时 将N改为02
        else if(paramObject instanceof RepAssetBond){
        	RepAssetBond targetTalbleObj = (RepAssetBond) paramObject;
            // 是否为是否由金融资产投资公司发行
            targetTalbleObj.setRepFinancialComissue(coverNo(targetTalbleObj.getRepFinancialComissue()));
            //商业银行二级资本债
            targetTalbleObj.setRepBankSecCapital(coverNo(targetTalbleObj.getRepBankSecCapital()));
            //商业银行永续债
            targetTalbleObj.setRepBankSecPerpetual(coverNo(targetTalbleObj.getRepBankSecPerpetual()));
        }
        //RepFund modify by lwj 20200410 入库时 将N改为02 start
        else if(paramObject instanceof RepFund){
            RepFund targetTalbleObj = (RepFund) paramObject;
            // 是否由金融资产投资公司发行
            targetTalbleObj.setRepFinancialComissue(coverNo(targetTalbleObj.getRepFinancialComissue()));
            //是否属于政府投资基金
            targetTalbleObj.setRepIsGovInvest(coverNo(targetTalbleObj.getRepIsGovInvest()));
        }
        //RepFund modify by lwj 20200410 入库时 将N改为02 end
        // REP_ASSET_MNG_PLAN存值不符合接口规范
        else if(paramObject instanceof RepAssetMngPlan){
            RepAssetMngPlan targetTalbleObj = (RepAssetMngPlan) paramObject;
           // "REP_YIELD_FLAG 是否有预期收益率
            targetTalbleObj.setRepYieldFlag(coverNo(targetTalbleObj.getRepYieldFlag()));
           // REP_IS_CTRL_BOTTOM 是否掌控计划底层资产详情"
            targetTalbleObj.setRepIsCtrlBottom(coverNo(targetTalbleObj.getRepIsCtrlBottom()));
            // 是否由金融资产投资公司发行 modify by wj 20200410 入库时 将N改为02
            targetTalbleObj.setRepFinancialComissue(coverNo(targetTalbleObj.getRepFinancialComissue()));
            
        }
       // REP_TRUST_INVEST_MODE存值不符合接口规范
        else if(paramObject instanceof RepTrustInvestMode){
            RepTrustInvestMode targetTalbleObj = (RepTrustInvestMode) paramObject;
            //REP_CONTAIN_EXPECTED_YIELD 是否有预期收益率
            targetTalbleObj.setRepContainExpectedYield(coverNo(targetTalbleObj.getRepContainExpectedYield()));

        }
        //REP_OTHER_INVEST_TARGET存值不符合接口规范
        else if(paramObject instanceof RepOtherInvestTarget){
            RepOtherInvestTarget targetTalbleObj = (RepOtherInvestTarget) paramObject;
           // "REP_CONTAIN_EXPECTED_YIELD2 是否有预期收益率
            targetTalbleObj.setRepContainExpectedYield2(coverNo(targetTalbleObj.getRepContainExpectedYield2()));
            //REP_INDUSTRY_FLAG 是否属于重点监控行业和领域"
            targetTalbleObj.setRepIndustryFlag(coverNo(targetTalbleObj.getRepIndustryFlag()));
        }
        //REP_ASSET_AST_QDII_SEC存值不符合接口规范
        else if(paramObject instanceof RepAssetAstQdiiSec){
            RepAssetAstQdiiSec targetTalbleObj = (RepAssetAstQdiiSec) paramObject;
            // REP_SPECIAL_CMT 是否含权等特殊条款情况说明
            targetTalbleObj.setRepSpecialCmt(coverNo(targetTalbleObj.getRepSpecialCmt()));
        }
        //指令入库的时候存汉字是否修改
        else if(paramObject instanceof StlPayOrders){
            StlPayOrders targetTableObj = (StlPayOrders) paramObject;
            if (StringUtil.equals(targetTableObj.getIsNeedSend(), "是")){
                targetTableObj.setIsNeedSend(DictCodeList.是否_是.getValue());
            }else if (StringUtil.equals(targetTableObj.getIsNeedSend(), "否")){
                targetTableObj.setIsNeedSend(DictCodeList.是否_否.getValue());
            }
        }
        //REP_ASSET_OTHER存值不符合接口规范
        else if(paramObject instanceof RepAssetOther){
            RepAssetOther targetTalbleObj = (RepAssetOther) paramObject;
            targetTalbleObj.setRepPrivatefundFlag(coverNo(targetTalbleObj.getRepPrivatefundFlag()));
            targetTalbleObj.setRepPppflag(coverNo(targetTalbleObj.getRepPppflag()));
            targetTalbleObj.setRepSmallcredit(coverNo(targetTalbleObj.getRepSmallcredit()));
            targetTalbleObj.setRepBondtostockFlag(coverNo(targetTalbleObj.getRepBondtostockFlag()));
            targetTalbleObj.setRepPrivateprdFlag(coverNo(targetTalbleObj.getRepPrivateprdFlag()));
        }
        logger.info("估值核算相关字段值获取设值aop处理结束--------------------------------");
    }

    /**
     * N 转换成 02
     * @param oriStr 原数据N
     * @return
     */
    private String coverNo(String oriStr){
        if (StringUtil.equals(oriStr, DictCodeList.是否_否.getValue())){
            return DictCodeList.是否_中债_否.getValue();
        }else if(StringUtil.equals(oriStr, DictCodeList.是否_是.getValue())){
            return DictCodeList.是否_中债_是.getValue();
        } else{
            return oriStr;
        }

    }
}
