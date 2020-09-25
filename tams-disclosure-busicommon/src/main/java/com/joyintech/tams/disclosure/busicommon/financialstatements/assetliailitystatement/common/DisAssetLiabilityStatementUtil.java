/*
 * 文件名：AssetLiabilityStatementUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001Dto;


/**
 * 资产负债表工具类
 * 
 * @author zhangwei
 * @version 1.0 2018年9月20日
 * @see AssetLiabilityStatementUtil
 * @since 1.0
 */
public class DisAssetLiabilityStatementUtil {

    public static final String ENDING_BALANCE = "期末余额";

    public static final String INITIAL_BALANCE = "年初余额";

    public static final String O_AMT = "原币余额";

    public static final String ASSET_SUM = "资产总计";

    public static final String LIABILITY_SUM = "负债总计";

    public static final String INTEREST_SUM = "所有者权益总计";

    public static final String LIABILITY_INTEREST_SUM = "负债和所有者权益总计";

    /**
     * 获得资产表样
     * 
     * @return
     */
    public static List<DisAssetLiabilityStatementQuery001Dto> getAssetList() {
        List<DisAssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_银行存款));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_结算备付金));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_存出保证金));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_存放同业款项));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_拆出资金));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_交易性金融资产));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
                DisAssetLiabilityStatementEnum.资产类_买入返售金融资产_返售证券, DisAssetLiabilityStatementEnum.资产类_买入返售金融资产_返售票据,
                DisAssetLiabilityStatementEnum.资产类_买入返售金融资产_返售其他资产));
        
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_衍生金融资产_外汇买卖, DisAssetLiabilityStatementEnum.资产类_衍生金融资产_远期投资,
            DisAssetLiabilityStatementEnum.资产类_衍生金融资产_其他衍生工具));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_可供出售金融资产,
            DisAssetLiabilityStatementEnum.资产类_可供出售国家债券投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售中央银行债券投资减值准备,
            DisAssetLiabilityStatementEnum.资产类_可供出售政策性银行债券投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售其他金融债券投资减值准备,
            DisAssetLiabilityStatementEnum.资产类_可供出售其他债券减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售股权投资减值准备,
            DisAssetLiabilityStatementEnum.资产类_可供出售非标准化债权投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售其他金融资产减值准备));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_应收款项类投资));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_持有至到期投资));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_应收利息));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
                DisAssetLiabilityStatementEnum.资产类_应收证券清算款));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
                DisAssetLiabilityStatementEnum.资产类_应收股利));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
                DisAssetLiabilityStatementEnum.资产类_应收申购款));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
                DisAssetLiabilityStatementEnum.资产类_递延所得税资产));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_借方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.资产类_其他资产_其他应收款, DisAssetLiabilityStatementEnum.资产类_其他资产_应收款项金融资产减值准备,
            DisAssetLiabilityStatementEnum.资产类_其他资产_持有至到期投资减值准备,
             DisAssetLiabilityStatementEnum.资产类_其他资产_待摊费用,
             DisAssetLiabilityStatementEnum.资产类_可供出售国家债券投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售中央银行债券投资减值准备,
             DisAssetLiabilityStatementEnum.资产类_可供出售政策性银行债券投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售其他金融债券投资减值准备,
             DisAssetLiabilityStatementEnum.资产类_可供出售其他债券减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售股权投资减值准备,
             DisAssetLiabilityStatementEnum.资产类_可供出售非标准化债权投资减值准备,DisAssetLiabilityStatementEnum.资产类_可供出售其他金融资产减值准备
             ));

        return list;
    }

    /**
     * 获得资产总计表样
     * 
     * @param rowList
     * @return
     */
    public static List<DisAssetLiabilityStatementQuery001Dto> getAssetListSum(List<DisAssetLiabilityStatementQuery001Dto> rowList) {
        List<DisAssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        for (DisAssetLiabilityStatementQuery001Dto item : rowList) {
            endingBalance = endingBalance.add(item.getEndingBalance());
            initialBalance = initialBalance.add(item.getInitialBalance());
        }
        list.add(new DisAssetLiabilityStatementQuery001Dto(ASSET_SUM, endingBalance, initialBalance));
        return list;
    }

    /**
     * 获得负债和所有者权益表样
     * 
     * @return
     */
    public static List<DisAssetLiabilityStatementQuery001Dto> getLiabilityInterestList() {
        List<DisAssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_拆入资金));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_交易性金融负债));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖, 
             DisAssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资,
            DisAssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具
            ));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_卖出回购金融资产款_证券款,
            DisAssetLiabilityStatementEnum.负债类_卖出回购金融资产款_票据款,
            DisAssetLiabilityStatementEnum.负债类_卖出回购金融资产款_其他资产
        		));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付管理人报酬));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付交易费用));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付托管费));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付销售服务费));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应交税费));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付利息));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付赎回款));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付证券清算款));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_应付利润));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.负债类_其他负债_应付赎回费,DisAssetLiabilityStatementEnum.负债类_其他负债_其他应付款,
            DisAssetLiabilityStatementEnum.负债类_其他负债_预提费用));
        list.add(new DisAssetLiabilityStatementQuery001Dto(LIABILITY_SUM));

        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.所有者权益类_实收资本));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.所有者权益类_资本公积));
        list.add(new DisAssetLiabilityStatementQuery001Dto(DictCodeList.余额方向_贷方.getValue(), O_AMT,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_利息收入, DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_公允价值变动损益,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_投资收益, DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_汇兑损益,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_其他收入,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_管理人报酬,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_托管费, DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_销售服务费,      
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_交易费用, 
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_利息支出, 
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_税金及附加, 
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_其他费用, DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_资产损失,
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_以前年度损益调整, 
            DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_本期利润, DisAssetLiabilityStatementEnum.所有者权益类_未分配利润_利润分配));
        list.add(new DisAssetLiabilityStatementQuery001Dto(INTEREST_SUM));

        return list;
    }

    /**
     * 获得负债和所有者权益总计表样
     * 
     * @param rowList
     * @return
     */
    public static List<DisAssetLiabilityStatementQuery001Dto> getLiabilityInterestListSum(List<DisAssetLiabilityStatementQuery001Dto> rowList) {
        List<DisAssetLiabilityStatementQuery001Dto> list = new ArrayList<>();
        BigDecimal endingBalance = BigDecimal.ZERO;
        BigDecimal initialBalance = BigDecimal.ZERO;
        for (DisAssetLiabilityStatementQuery001Dto item : rowList) {
            if (!LIABILITY_SUM.equals(item.getItemName()) && !INTEREST_SUM.equals(item.getItemName())) { // 负债总计和所有者权益总计不重复计算
                endingBalance = endingBalance.add(item.getEndingBalance());
                initialBalance = initialBalance.add(item.getInitialBalance());
            }
        }
        list.add(new DisAssetLiabilityStatementQuery001Dto(LIABILITY_INTEREST_SUM, endingBalance, initialBalance));
        return list;
    }

}
