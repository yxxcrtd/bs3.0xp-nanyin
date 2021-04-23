/*
 * 文件名：MstProjectCatInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.773
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.projectcatinfo.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinProductValuation;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.773
 * @see MstProjectCatInfo001Service
 * @since 1.0
 */
public class MstProjectCatInfoDto extends FinProductValuation {



    /**
     * 资产代码
     */
    private String finprodId;
    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    /**
     * 资产名称
     */
    private String finProdName;

    /**
     * 取得 资产名称
     *
     * @return 资产名称
     */
    public String getFinProdName() {
        return this.finProdName;
    }

    /**
     * 设置 资产名称
     *
     * @param finProdName 资产名称
     */
    public void setFinProdName(String finProdName) {
        this.finProdName = finProdName;
    }

    /**
     * 单位净值
     */
    private BigDecimal valuation;

    /**
     * 取得 单位净值
     *
     * @return 单位净值
     */
    public BigDecimal getValuation() {
        return this.valuation;
    }

    /**
     * 设置 单位净值
     *
     * @param valuation 单位净值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 管理人
     */
    private String fundManager;

    /**
     * 取得 管理人
     *
     * @return 管理人
     */
    public String getFundManager() {
        return this.fundManager;
    }

    /**
     * 设置 管理人
     *
     * @param fundManager 管理人
     */
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 备注
     *
     * @return 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 收益类型
     */
    private String profitType;

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 金融市场代码
     */
    private String finProdMarketId;

    public String getFinProdMarketId() {
        return finProdMarketId;
    }

    public void setFinProdMarketId(String finProdMarketId) {
        this.finProdMarketId = finProdMarketId;
    }

    private Date importDate;

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}