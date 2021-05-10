/*
 * 文件名：AssetLiabilityStatementUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001Dto;

/**
 * 资产负债表工具类
 *
 * @author zhangwei
 * @version 1.0 2018年9月20日
 * @see AssetLiabilityStatementNewUtil
 * @since 1.0
 */
public class AssetLiabilityStatementNewUtil {

    public static final String ENDING_BALANCE = "期末余额";

    public static final String INITIAL_BALANCE = "年初余额";

    //public static final String O_AMT = "原币余额";

    public static final String B_AMT = "本币余额";

    public static final String ASSET_SUM = "资产总计";

    public static final String LIABILITY_SUM = "负债合计";

    public static final String INTEREST_SUM = "净资产合计";

    public static final String LIABILITY_INTEREST_SUM = "负债和净资产总计";

    /** add by yanya 20190929 增加邮储模板常量 **/
    public static final String FIN_TYPE_01 = "01";//01标准
    public static final String FIN_TYPE_02 = "02";//02邮储模板
    /** add by libingyan 20210113 增加前台界面【资产总计】不统计的资产 **/
    //以公允价值计量且其变动计入当期损益的金融资产
    public static final String ITEMCODE_ZC_06 = "ZC_06";
    //以公允价值计量且其变动计入其他综合收益的金融资产
    public static final String ITEMCODE_ZC_11 = "ZC_11";
    //以摊余成本计量的金融投资
    public static final String ITEMCODE_ZC_16 = "ZC_16";

    /**
     * 获得资产表样
     *
     * @param finType 模板类型
     * @return
     */
    public static List<AssetLiabilityStatementNewQuery001Dto> getAssetList() {
        List<AssetLiabilityStatementNewQuery001Dto> list = new ArrayList<>();
        //借方
        list.add(new AssetLiabilityStatementNewQuery001Dto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                AssetLiabilityStatementNewEnum.资产类_银行存款_银行存款,
                AssetLiabilityStatementNewEnum.资产类_银行存款_资金同业款,
                AssetLiabilityStatementNewEnum.资产类_结算备付金_结算备付金,
                AssetLiabilityStatementNewEnum.资产类_存出保证金_存出保证金,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_外汇买卖,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_外汇结售汇,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_金融掉期,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_远期投资,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_股票期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_买入个股期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_买入指数期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_买入期货期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_其他衍生工具,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_期货投资,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_套期工具,
                AssetLiabilityStatementNewEnum.资产类_衍生金融资产_被套期项目,
                AssetLiabilityStatementNewEnum.资产类_买入返售金融资产_买入返售金融资产,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_净值型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_货币型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_境内计划类_利率型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_其中_利率型_权益投资_公允价值损益类_股票投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益类债券投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_基金投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_净值型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_货币型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_境内计划类_利率型_资产投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值且其变动计入其他综合收益类债券投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值计量且其变动计入其他综合收益的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_综合收益类_计划类资产投资,
                AssetLiabilityStatementNewEnum.资产类_其中_利率型_权益投资_暂无,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值且其变动计入其他综合收益类债券投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入其他综合收益的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_综合收益类_计划类资产投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_1,
                AssetLiabilityStatementNewEnum.资产类_以摊余成本计量的金融资产产_摊余成本类债券投资,
                AssetLiabilityStatementNewEnum.资产类_以摊余成本计量的金融资产产_以摊余成本计量的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_以摊余成本计量的金融资产产_摊余成本类_计划类资产投资,
                AssetLiabilityStatementNewEnum.资产类_其中_利率型_债权投资_摊余成本类债券投资,
                AssetLiabilityStatementNewEnum.资产类_其中_利率型_债权投资_以摊余成本计量的其他债权投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_摊余成本类_计划类资产投资,
                AssetLiabilityStatementNewEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_2,
                AssetLiabilityStatementNewEnum.资产类_应收红利_应收红利,
                AssetLiabilityStatementNewEnum.资产类_应收其他投资收入_暂无,
                AssetLiabilityStatementNewEnum.资产类_应收证券清算款_证券清算款,
                AssetLiabilityStatementNewEnum.资产类_应收申购款_应收申购款,
                AssetLiabilityStatementNewEnum.资产类_应收申购款_产品申购款,
                AssetLiabilityStatementNewEnum.资产类_其他资产_其他应收款,
                AssetLiabilityStatementNewEnum.资产类_其他资产_应收逾期款,
                AssetLiabilityStatementNewEnum.资产类_其他资产_待摊费用,
                AssetLiabilityStatementNewEnum.资产类_其他资产_坏账准备,
                AssetLiabilityStatementNewEnum.资产类_其他资产_应付账款,
                AssetLiabilityStatementNewEnum.资产类_资产总计,
                AssetLiabilityStatementNewEnum.资产类_负债和所有者权益
        ));
        return list;
    }

    /**
     * 获得资产总计表样
     *
     * @param rowList
     * @return
     */
    public static List<AssetLiabilityStatementNewQuery001Dto> getAssetListSum(List<AssetLiabilityStatementNewQuery001Dto> rowList) {
        List<AssetLiabilityStatementNewQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        String booksetId = "";
        for (AssetLiabilityStatementNewQuery001Dto item : rowList) {
            // VAL_TABLE_BAS 【资产负债表数据】中的以下三项是相应 【其中】 资产的总和
            // ZC_06：以公允价值计量且其变动计入当期损益的金融资产
            // ZC_11：以公允价值计量且其变动计入其他综合收益的金融资产
            // ZC_16：以摊余成本计量的金融投资
            // 所以为避免前台展示界面 资产总和 重复统计，这里对以上三项 不在统计。
            if (
                    StringUtil.equals(ITEMCODE_ZC_06,item.getItemCode())||
                            StringUtil.equals(ITEMCODE_ZC_11,item.getItemCode())||
                            StringUtil.equals(ITEMCODE_ZC_16,item.getItemCode())
            ){
                continue;
            }
            endingBalance = endingBalance.add(item.getEndingBalance());
            initialBalance = initialBalance.add(item.getInitialBalance());
            booksetId = item.getBooksetId();
        }
        list.add(new AssetLiabilityStatementNewQuery001Dto(ASSET_SUM,"ZCHJ",booksetId, endingBalance, initialBalance));
        return list;
    }

    /**
     * 获得负债和所有者权益表样
     *
     * @param finType 模板类型
     * @return
     */
    public static List<AssetLiabilityStatementNewQuery001Dto> getLiabilityInterestList() {
        List<AssetLiabilityStatementNewQuery001Dto> list = new ArrayList<>();
        //贷方
        list.add(new AssetLiabilityStatementNewQuery001Dto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                AssetLiabilityStatementNewEnum.负债类_同业借款_拆入款项,
                AssetLiabilityStatementNewEnum.负债类_卖出回购金融资产款_卖出回购金融资产款,
                AssetLiabilityStatementNewEnum.负债类_以公允价值计量且其变动计入当期损益的金融负债_以公允价值计量且其变动计入当期损益类金融负债,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_外汇买卖,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_外汇结售汇,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_金融掉期,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_远期投资,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_期货投资,
                AssetLiabilityStatementNewEnum.资产类_衍生金融负债_股票期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融负债_卖出个股期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融负债_卖出指数期权,
                AssetLiabilityStatementNewEnum.资产类_衍生金融负债_卖出期货期权,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_其他衍生工具,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_套期工具,
                AssetLiabilityStatementNewEnum.负债类_衍生金融负债_被套期项目,
                AssetLiabilityStatementNewEnum.负债类_应付证券清算款_证券清算款,
                AssetLiabilityStatementNewEnum.负债类_应付赎回款_应付赎回款,
                AssetLiabilityStatementNewEnum.负债类_应付投资顾问费_应付投顾费,
                AssetLiabilityStatementNewEnum.负债类_应付管理人报酬_应付管理人报酬,
                AssetLiabilityStatementNewEnum.负债类_应付托管费_应付托管费,
                AssetLiabilityStatementNewEnum.负债类_应付销售服务费_应付销售服务费,
                AssetLiabilityStatementNewEnum.负债类_应付交易费用_应付交易费用,
                AssetLiabilityStatementNewEnum.负债类_应交税金_应交税费,
                AssetLiabilityStatementNewEnum.负债类_应交税金_合作方应交税费,
                AssetLiabilityStatementNewEnum.负债类_应付利润_应付利润,
                AssetLiabilityStatementNewEnum.负债类_其他负债_应付赎回费,
                AssetLiabilityStatementNewEnum.负债类_其他负债_应付代销费,
                AssetLiabilityStatementNewEnum.负债类_其他负债_应付外包服务费,
                AssetLiabilityStatementNewEnum.负债类_其他负债_应付推介费,
                AssetLiabilityStatementNewEnum.负债类_其他负债_应付账款,
                AssetLiabilityStatementNewEnum.负债类_其他负债_待清算资金,
                AssetLiabilityStatementNewEnum.负债类_其他负债_其他应付款,
                AssetLiabilityStatementNewEnum.负债类_其他负债_预提费用,
                AssetLiabilityStatementNewEnum.负债类_负债总计,
                AssetLiabilityStatementNewEnum.权益类_实收理财资金_实收资本,
                AssetLiabilityStatementNewEnum.权益类_其他综合收益_其他综合收益,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_利息收入,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_公允价值变动损益,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_投资收益,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_汇兑损益,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_其他收入,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_管理人报酬,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_托管费,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_销售服务费,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_代销费,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_外包服务费,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_交易费用,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_应交税费支出,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_利息支出,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_资产减值损失,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_推介费支出,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_合作方应交税费支出,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_利息支出_利率互换,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_其他费用,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_其他支出,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_以前年度损益调整,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_损益平准金,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_本期利润,
                AssetLiabilityStatementNewEnum.权益类_未分配利润_利润分配,
                AssetLiabilityStatementNewEnum.权益类_所有者权益总计,
                AssetLiabilityStatementNewEnum.权益类_负债和所有者权益总计
        ));
        return list;
    }

    /**
     * 获得负债和所有者权益总计表样
     *
     * @param rowList
     * @return
     */
    public static List<AssetLiabilityStatementNewQuery001Dto> getLiabilityInterestListSum(List<AssetLiabilityStatementNewQuery001Dto> rowList) {
        List<AssetLiabilityStatementNewQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        String booksetId = "";
        for (AssetLiabilityStatementNewQuery001Dto item : rowList) {
            if (!LIABILITY_SUM.equals(item.getItemName()) && !INTEREST_SUM.equals(item.getItemName())) { // 负债总计和所有者权益总计不重复计算
                endingBalance = endingBalance.add(item.getEndingBalance());
                initialBalance = initialBalance.add(item.getInitialBalance());
                booksetId = item.getBooksetId();
            }
        }
        list.add(new AssetLiabilityStatementNewQuery001Dto(LIABILITY_INTEREST_SUM, "HZSYZHJ", booksetId, endingBalance, initialBalance));
        return list;
    }

}
