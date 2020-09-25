/*
 * 文件名：TrdCommandsCollection003Dto.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2020年01月06日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 *指令汇总质押券dto
 * @author 王贵奇
 * @version 1.0 2020年01月06日
 * @see TrdCommandsCollection003Dto
 * @since 1.0
 */
public class TrdCommandsCollection003Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 资产二级分类
     */
    private String type6;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 质押金额
     */
    private BigDecimal pledgeAmt;

    /**
     * 折算比例
     */
    private BigDecimal pledgeRatio;

    /**
     * 获取 finprodId
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodName
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 type6
     * @return type6
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }
    
    /**
     * 获取 type6字典名称
     * @return type6字典名称
     */
    public String getType6Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, getType6());
    }

    /**
     * 获取 registOrg
     * @return registOrg
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 registOrg
     * @param registOrg registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    /**
     * 获取 registOrg字典名称
     * @return registOrg字典名称
     */
    public String getRegistOrgStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, getRegistOrg());
    }
    /**
     * 获取 portfolioId
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     * @return portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 pledgeAmt
     * @return pledgeAmt
     */
    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    /**
     * 设置 pledgeAmt
     * @param pledgeAmt pledgeAmt
     */
    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    /**
     * 获取 pledgeRatio
     * @return pledgeRatio
     */
    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    /**
     * 设置 pledgeRatio
     * @param pledgeRatio pledgeRatio
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

}
