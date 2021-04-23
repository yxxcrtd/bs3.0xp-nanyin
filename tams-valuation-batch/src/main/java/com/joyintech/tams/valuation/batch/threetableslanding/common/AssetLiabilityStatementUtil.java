/*
 * 文件名：AssetLiabilityStatementUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.common;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.AssetLiabilityStatementDto;

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
//    public static final String ASSET_SUM = "资产总计";
//
//    public static final String LIABILITY_SUM = "负债总计";
//
//    public static final String INTEREST_SUM = "所有者权益总计";
//
//    public static final String LIABILITY_INTEREST_SUM = "负债和所有者权益总计";

    /** add by yanya 20190929 增加邮储模板常量 **/
    public static final String FIN_TYPE_01 = "01";// 01标准

    public static final String FIN_TYPE_02 = "02";// 02邮储模板

    /**
     * 获得资产表样
     * 
     * @return
     */
    public static List<AssetLiabilityStatementDto> getAssetList() {
        List<AssetLiabilityStatementDto> list = new ArrayList<>();

        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(), AssetLiabilityStatementEnum.资产类_银行存款_银行存款,
                AssetLiabilityStatementEnum.资产类_银行存款_资金同业款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(), AssetLiabilityStatementEnum.资产类_结算备付金_结算备付金
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(), AssetLiabilityStatementEnum.资产类_存出保证金_存出保证金
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇买卖,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_外汇结售汇,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_金融掉期,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_远期投资,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_股票期权,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_买入个股期权,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_买入指数期权,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_买入期货期权,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_其他衍生工具,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_期货投资,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_套期工具,
                 AssetLiabilityStatementEnum.资产类_衍生金融资产_被套期项目
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(), AssetLiabilityStatementEnum.资产类_买入返售金融资产_买入返售金融资产
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益的其他债权投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_净值型_资产投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_货币型_资产投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_境内计划类_利率型_资产投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_其中_权益投资_公允价值损益类_股票投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益类债券投资,
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益的其他债权投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_基金投资,
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_净值型_资产投资,
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_货币型_资产投资,
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_境内计划类_利率型_资产投资

        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无

        ));

        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值且其变动计入其他综合收益类债券投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值计量且其变动计入其他综合收益的其他债权投资,
                AssetLiabilityStatementEnum.资产类_以公允价值计量且其变动计入其他综合收益的金融资产_综合收益类_计划类资产投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_其中_权益投资_暂无
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值且其变动计入其他综合收益类债券投资,
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入其他综合收益的其他债权投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_综合收益类_计划类资产投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_1
        ));


        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产产_摊余成本类债券投资,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产产_以摊余成本计量的其他债权投资,
                AssetLiabilityStatementEnum.资产类_以摊余成本计量的金融资产产_摊余成本类_计划类资产投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_其中_债权投资_摊余成本类债券投资,
                AssetLiabilityStatementEnum.资产类_其中_债权投资_以摊余成本计量的其他债权投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_摊余成本类_计划类资产投资
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_2
        ));

        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_应收红利_应收红利
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_应收其他投资收入_暂无
        ));

        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_应收证券清算款_证券清算款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_应收申购款_应收申购款,
                AssetLiabilityStatementEnum.资产类_应收申购款_产品申购款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_借方.getValue(), B_AMT,
                DictCodeList.科目类型_资产类.getValue(),
                AssetLiabilityStatementEnum.资产类_其他资产_其他应收款,
                AssetLiabilityStatementEnum.资产类_其他资产_应收逾期款,
                AssetLiabilityStatementEnum.资产类_其他资产_待摊费用,
                AssetLiabilityStatementEnum.资产类_其他资产_坏账准备,
                AssetLiabilityStatementEnum.资产类_其他资产_应付账款
        ));
        return list;
    }

    /**
     * 获得负债和所有者权益表样
     *
     * @return
     */
    public static List<AssetLiabilityStatementDto> getLiabilityInterestList() {
        List<AssetLiabilityStatementDto> list = new ArrayList<>();

        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(), AssetLiabilityStatementEnum.负债类_同业借款_拆入款项));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_卖出回购金融资产款_卖出回购金融资产款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_以公允价值计量且其变动计入当期损益的金融负债_以公允价值计量且其变动计入当期损益类金融负债
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇结售汇,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_金融掉期,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_期货投资,
                AssetLiabilityStatementEnum.资产类_衍生金融负债_股票期权,
                AssetLiabilityStatementEnum.资产类_衍生金融负债_卖出个股期权,
                AssetLiabilityStatementEnum.资产类_衍生金融负债_卖出指数期权,
                AssetLiabilityStatementEnum.资产类_衍生金融负债_卖出期货期权,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_套期工具,
                AssetLiabilityStatementEnum.负债类_衍生金融负债_被套期项目
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付证券清算款_证券清算款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付赎回款_应付赎回款
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付投资顾问费_应付投顾费
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付管理人报酬_应付管理人报酬
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付托管费_应付托管费
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付销售服务费_应付销售服务费
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付交易费用_应付交易费用
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应交税金_应交税费,
                AssetLiabilityStatementEnum.负债类_应交税金_合作方应交税费
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_应付利润_应付利润
        ));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_负债类.getValue(),
                AssetLiabilityStatementEnum.负债类_其他负债_应付赎回费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付代销费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付外包服务费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付推介费,
                AssetLiabilityStatementEnum.负债类_其他负债_应付账款,
                AssetLiabilityStatementEnum.负债类_其他负债_待清算资金,
                AssetLiabilityStatementEnum.负债类_其他负债_其他应付款,
                AssetLiabilityStatementEnum.负债类_其他负债_预提费用
        ));


        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_所有者权益类.getValue(),
                AssetLiabilityStatementEnum.权益类_实收理财资金_实收资本));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_所有者权益类.getValue(),
                AssetLiabilityStatementEnum.权益类_其他综合收益_其他综合收益));
        list.add(new AssetLiabilityStatementDto(DictCodeList.余额方向_贷方.getValue(), B_AMT,
                DictCodeList.科目类型_所有者权益类.getValue(),
                AssetLiabilityStatementEnum.权益类_未分配利润_利息收入,
                AssetLiabilityStatementEnum.权益类_未分配利润_公允价值变动损益,
                AssetLiabilityStatementEnum.权益类_未分配利润_投资收益,
                AssetLiabilityStatementEnum.权益类_未分配利润_汇兑损益,
                AssetLiabilityStatementEnum.权益类_未分配利润_其他收入,
                AssetLiabilityStatementEnum.权益类_未分配利润_管理人报酬,
                AssetLiabilityStatementEnum.权益类_未分配利润_托管费,
                AssetLiabilityStatementEnum.权益类_未分配利润_销售服务费,
                AssetLiabilityStatementEnum.权益类_未分配利润_代销费,
                AssetLiabilityStatementEnum.权益类_未分配利润_外包服务费,
                AssetLiabilityStatementEnum.权益类_未分配利润_交易费用,
                AssetLiabilityStatementEnum.权益类_未分配利润_应交税费支出,
                AssetLiabilityStatementEnum.权益类_未分配利润_利息支出,
                AssetLiabilityStatementEnum.权益类_未分配利润_资产减值损失,
                AssetLiabilityStatementEnum.权益类_未分配利润_推介费支出,
                AssetLiabilityStatementEnum.权益类_未分配利润_合作方应交税费支出,
                AssetLiabilityStatementEnum.权益类_未分配利润_利息支出_利率互换,
                AssetLiabilityStatementEnum.权益类_未分配利润_其他费用,
                AssetLiabilityStatementEnum.权益类_未分配利润_其他支出,
                AssetLiabilityStatementEnum.权益类_未分配利润_以前年度损益调整,
                AssetLiabilityStatementEnum.权益类_未分配利润_损益平准金,
                AssetLiabilityStatementEnum.权益类_未分配利润_本期利润,
                AssetLiabilityStatementEnum.权益类_未分配利润_利润分配
        ));
        return list;
    }

}
