/*
 * 文件名：AssetLiabilityStatementUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.financialstatement.assetliailitystatement.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产负债表工具类
 * 
 * @author zhangwei
 * @version 1.0 2018年9月20日
 * @see AssetLiabilityStatementUtil
 * @since 1.0
 */
public class AssetLiabilityStatementUtil {

    public static final String ENDING_BALANCE = "期末余额";

    public static final String INITIAL_BALANCE = "年初余额";

    //public static final String O_AMT = "原币余额";

    public static final String B_AMT = "本币余额";

    public static final String ASSET_SUM = "资产总计";

    public static final String LIABILITY_SUM = "负债总计";

    public static final String INTEREST_SUM = "所有者权益总计";

    public static final String LIABILITY_INTEREST_SUM = "负债和所有者权益总计";
    
    /** add by yanya 20190929 增加邮储模板常量 **/
    public static final String FIN_TYPE_01 = "01";//01标准
    public static final String FIN_TYPE_02 = "02";//02邮储模板

    /**
     * 获得资产表样
     * 
     * @param finType 模板类型
     * @return
     */
    public static List<AssetLiabilityStatementQuery001Dto> getAssetList(String finType) {
        List<AssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        /** modify by yanya on 20190929 增加财务四表模板：01标准，02邮储 start**/
        if(StringUtil.isNotEmpty(finType) && FIN_TYPE_02.equals(finType)) {
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_银行存款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_结算备付金_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_存出保证金_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_资金同业款_02, AssetLiabilityStatementEnum.资产类_拆放款项减值准备_02,
                AssetLiabilityStatementEnum.资产类_存放款项减值准备_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_买入返售金融资产_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇买卖_02, AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇结售汇_02,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_金融掉期_02, AssetLiabilityStatementEnum.资产类_衍生金融资产_远期投资_02,
                /** add by yanya on 20200219 邮储资产负债表增加期权 start**/
                AssetLiabilityStatementEnum.资产类_衍生金融资产_股票期权_02, AssetLiabilityStatementEnum.资产类_衍生金融资产_买入个股期权_02,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_买入指数期权_02, AssetLiabilityStatementEnum.资产类_衍生金融资产_买入期货期权_02,
                /** add by yanya on 20200219 邮储资产负债表增加期权 end**/
                AssetLiabilityStatementEnum.资产类_衍生金融资产_其他衍生工具_02, AssetLiabilityStatementEnum.资产类_衍生金融资产_套期工具_02,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_被套期项目_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资_02,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资_02,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类资产投资_02,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值且其变动计入其他综合收益类债券投资_02,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_公允价值损益类_计划类货币型资产投资_02,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_综合收益类债券减值准备_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类债券投资_02,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类_计划类资产投资_02,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类债券减值准备_02,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类计划类资产减值准备_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收利息_应收利息_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收证券清算款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收红利_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收申购款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_其他资产_其他应收款_02,
                AssetLiabilityStatementEnum.资产类_其他资产_待摊费用_02,
                /**ADD BY WUJIE ON 20200212 START**/
                AssetLiabilityStatementEnum.资产类_其他资产_应收逾期款_02, 
                /**ADD BY WUJIE ON 20200212 END**/
                AssetLiabilityStatementEnum.资产类_其他资产_应付账款_02));
        }
        else {
           
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_银行存款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_结算备付金));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_存出保证金));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_存放同业款项));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_拆出资金));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_买入返售金融资产));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇买卖, AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇结售汇,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_金融掉期, AssetLiabilityStatementEnum.资产类_衍生金融资产_远期投资,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 start**/
                    AssetLiabilityStatementEnum.资产类_衍生金融资产_股票期权, AssetLiabilityStatementEnum.资产类_衍生金融资产_买入个股期权,
                    AssetLiabilityStatementEnum.资产类_衍生金融资产_买入指数期权, AssetLiabilityStatementEnum.资产类_衍生金融资产_买入期货期权,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 end**/
                AssetLiabilityStatementEnum.资产类_衍生金融资产_其他衍生工具, AssetLiabilityStatementEnum.资产类_衍生金融资产_套期工具,
                AssetLiabilityStatementEnum.资产类_衍生金融资产_被套期项目));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类资产投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值且其变动计入其他综合收益类债券投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_公允价值损益类_计划类货币型资产投资));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类债券投资,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产_摊余成本类_计划类资产投资));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收利息_应收利息, AssetLiabilityStatementEnum.资产类_应收利息_非上市债券应收利息));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收证券清算款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收红利));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_应收申购款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.资产类_其他资产_非上市债券成本, AssetLiabilityStatementEnum.资产类_其他资产_其他应收款,
                AssetLiabilityStatementEnum.资产类_其他资产_资产减值准备, AssetLiabilityStatementEnum.资产类_其他资产_坏账准备,
                AssetLiabilityStatementEnum.资产类_其他资产_应收逾期款, AssetLiabilityStatementEnum.资产类_其他资产_待摊费用,
                AssetLiabilityStatementEnum.资产类_其他资产_应付账款));
        }
        /** modify by yanya on 20190929 增加财务四表模板：01标准，02邮储 end**/
        return list;
    }

    /**
     * 获得资产总计表样
     * 
     * @param rowList
     * @return
     */
    public static List<AssetLiabilityStatementQuery001Dto> getAssetListSum(List<AssetLiabilityStatementQuery001Dto> rowList) {
        List<AssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        for (AssetLiabilityStatementQuery001Dto item : rowList) {
            endingBalance = endingBalance.add(item.getEndingBalance());
            initialBalance = initialBalance.add(item.getInitialBalance());
        }
        list.add(new AssetLiabilityStatementQuery001Dto(ASSET_SUM, endingBalance, initialBalance));
        return list;
    }

    /**
     * 获得负债和所有者权益表样
     * 
     * @param finType 模板类型
     * @return
     */
    public static List<AssetLiabilityStatementQuery001Dto> getLiabilityInterestList(String finType) {
        List<AssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        /** modify by yanya on 20190929 增加财务四表模板：01标准，02邮储 start**/
        if(StringUtil.isNotEmpty(finType) && FIN_TYPE_02.equals(finType)) {
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_拆入资金_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_以公允价值计量且其变动计入当期损益的金融负债_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇结售汇_02,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_金融掉期_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资_02,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 start**/
                    AssetLiabilityStatementEnum.负债类_衍生金融负债_股票期权_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出个股期权_02,
                    AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出指数期权_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出期货期权_02,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 end**/
                AssetLiabilityStatementEnum.负债类_衍生金融负债_期货投资_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具_02,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_套期工具_02, AssetLiabilityStatementEnum.负债类_衍生金融负债_被套期项目_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_卖出回购金融资产款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付管理人报酬_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付交易费用_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付托管费_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付销售服务费_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付利息_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付赎回款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付证券清算款_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付利润_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_其他负债_应付赎回费_02, AssetLiabilityStatementEnum.负债类_其他负债_应付代销费_02,
                AssetLiabilityStatementEnum.负债类_其他负债_应付外包服务费_02, AssetLiabilityStatementEnum.负债类_其他负债_应付投顾费_02,
                AssetLiabilityStatementEnum.负债类_其他负债_应付推介费_02, AssetLiabilityStatementEnum.负债类_其他负债_合作方应交税费_02,
                AssetLiabilityStatementEnum.负债类_其他负债_应交税费_02, AssetLiabilityStatementEnum.负债类_其他负债_应付账款_02,
                AssetLiabilityStatementEnum.负债类_其他负债_待清算资金_02, AssetLiabilityStatementEnum.负债类_其他负债_其他应付款_02,
                AssetLiabilityStatementEnum.负债类_其他负债_预提费用_02));
            list.add(new AssetLiabilityStatementQuery001Dto(LIABILITY_SUM));
    
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_实收资本_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_资本公积_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_其他综合收益_02));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_利息收入_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_公允价值变动损益_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_投资收益_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_汇兑损益_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他收入_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_管理人报酬_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_托管费_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_销售服务费_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_代销费_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_外包服务费_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_交易费用_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_税费支出_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_利息支出_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_资产减值损失_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_推介费支出_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_合作方应交税费支出_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他费用_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他支出_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_以前年度损益调整_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_损益平准金_02,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_本期利润_02, AssetLiabilityStatementEnum.所有者权益类_未分配利润_利润分配_02,
                AssetLiabilityStatementEnum.特殊处理C_所有者权益类_其他综合收益_02));
            list.add(new AssetLiabilityStatementQuery001Dto(INTEREST_SUM));
        }
        else {
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_拆入资金));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_以公允价值计量且其变动计入当期损益的金融负债));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖, AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇结售汇,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_金融掉期, AssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 start**/
                    AssetLiabilityStatementEnum.负债类_衍生金融负债_股票期权, AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出个股期权,
                    AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出指数期权, AssetLiabilityStatementEnum.负债类_衍生金融负债_卖出期货期权,
                    /** add by yanya on 20200219 邮储资产负债表增加期权 end**/
                AssetLiabilityStatementEnum.负债类_衍生金融负债_期货投资, AssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_套期工具, AssetLiabilityStatementEnum.负债类_衍生金融负债_被套期项目));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_卖出回购金融资产款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付管理人报酬));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付交易费用));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付托管费));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付销售服务费));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付利息));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付赎回款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付证券清算款));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_应付利润));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.负债类_其他负债_应付赎回费, AssetLiabilityStatementEnum.负债类_其他负债_应付代销费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付外包服务费, AssetLiabilityStatementEnum.负债类_其他负债_应付投顾费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付推介费, AssetLiabilityStatementEnum.负债类_其他负债_合作方应交税费,
                AssetLiabilityStatementEnum.负债类_其他负债_应交税费, AssetLiabilityStatementEnum.负债类_其他负债_应付账款,
                AssetLiabilityStatementEnum.负债类_其他负债_待清算资金, AssetLiabilityStatementEnum.负债类_其他负债_其他应付款,
                AssetLiabilityStatementEnum.负债类_其他负债_预提费用));
            list.add(new AssetLiabilityStatementQuery001Dto(LIABILITY_SUM));
    
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_实收资本));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_资本公积));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_其他综合收益));
            list.add(new AssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_利息收入, AssetLiabilityStatementEnum.所有者权益类_未分配利润_公允价值变动损益,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_投资收益, AssetLiabilityStatementEnum.所有者权益类_未分配利润_汇兑损益,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他收入, AssetLiabilityStatementEnum.所有者权益类_未分配利润_管理人报酬,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_托管费, AssetLiabilityStatementEnum.所有者权益类_未分配利润_销售服务费,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_代销费, AssetLiabilityStatementEnum.所有者权益类_未分配利润_外包服务费,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_交易费用, AssetLiabilityStatementEnum.所有者权益类_未分配利润_税费支出,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_利息支出, AssetLiabilityStatementEnum.所有者权益类_未分配利润_资产减值损失,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_推介费支出, AssetLiabilityStatementEnum.所有者权益类_未分配利润_合作方应交税费支出,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他费用, AssetLiabilityStatementEnum.所有者权益类_未分配利润_其他支出,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_以前年度损益调整, AssetLiabilityStatementEnum.所有者权益类_未分配利润_损益平准金,
                AssetLiabilityStatementEnum.所有者权益类_未分配利润_本期利润, AssetLiabilityStatementEnum.所有者权益类_未分配利润_利润分配,
                AssetLiabilityStatementEnum.特殊处理C_所有者权益类_其他综合收益));
            list.add(new AssetLiabilityStatementQuery001Dto(INTEREST_SUM));
        }
        return list;
    }

    /**
     * 获得负债和所有者权益总计表样
     * 
     * @param rowList
     * @return
     */
    public static List<AssetLiabilityStatementQuery001Dto> getLiabilityInterestListSum(List<AssetLiabilityStatementQuery001Dto> rowList) {
        List<AssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        for (AssetLiabilityStatementQuery001Dto item : rowList) {
            if (!LIABILITY_SUM.equals(item.getItemName()) && !INTEREST_SUM.equals(item.getItemName())) { // 负债总计和所有者权益总计不重复计算
                endingBalance = endingBalance.add(item.getEndingBalance());
                initialBalance = initialBalance.add(item.getInitialBalance());
            }
        }
        list.add(new AssetLiabilityStatementQuery001Dto(LIABILITY_INTEREST_SUM, endingBalance, initialBalance));
        return list;
    }

}
