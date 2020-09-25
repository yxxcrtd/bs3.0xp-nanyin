/*
 * 文件名：BussRepOutInvestAgrInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.plan.planNet.dto;


import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepTrustInvestMode;

/**
 * 委外投资—协议方式中债报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfo
 * @since 1.0
 */
public class BussRepOutInvestAgrInfo extends RepTrustInvestMode{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 交易流通场所
     */
    private String repTradeMarket;
    /**
     * 二级资产类型
     */
    private String repAssetType;
    /**
     * 资产负债报送信息entity
     */
    private RepAssetDept repAssetDept;
    /**
     * 委外投资—协议方式报送信息entity
     */
    private RepTrustInvestMode repTrustInvestMode;
    
    // 增加是否投向金融资产投资公司发行的私募资产管理产品by 赵东东 2019.10.14 start
    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;
    /**
     * 获取 repPrivateprdFlag
     *
     * @return repPrivateprdFlag
     */
    public String getRepPrivateprdFlag() {
		return repPrivateprdFlag;
	}
    /**
     * 设置 repPrivateprdFlag
     *
     * @param repPrivateprdFlag repPrivateprdFlag
     */
	public void setRepPrivateprdFlag(String repPrivateprdFlag) {
		this.repPrivateprdFlag = repPrivateprdFlag;
	}
	// 增加是否投向金融资产投资公司发行的私募资产管理产品by 赵东东 2019.10.14 end
	/**
     * 获取 repAssetType
     *
     * @return repAssetType
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    /**
     * 设置 repAssetType
     *
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    /**
     * 获取 repAssetDept
     *
     * @return repAssetDept
     */
    public RepAssetDept getRepAssetDept() {
        return repAssetDept;
    }

    /**
     * 设置 repAssetDept
     *
     * @param repAssetDept repAssetDept
     */
    public void setRepAssetDept(RepAssetDept repAssetDept) {
        this.repAssetDept = repAssetDept;
    }

    /**
     * 获取 repTrustInvestMode
     *
     * @return repTrustInvestMode
     */
    public RepTrustInvestMode getRepTrustInvestMode() {
        return repTrustInvestMode;
    }

    /**
     * 设置 repTrustInvestMode
     *
     * @param repTrustInvestMode repTrustInvestMode
     */
    public void setRepTrustInvestMode(RepTrustInvestMode repTrustInvestMode) {
        this.repTrustInvestMode = repTrustInvestMode;
    }

    /**
     * 获取 repTradeMarket
     *
     * @return repTradeMarket
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    /**
     * 设置 repTradeMarket
     *
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }
}
