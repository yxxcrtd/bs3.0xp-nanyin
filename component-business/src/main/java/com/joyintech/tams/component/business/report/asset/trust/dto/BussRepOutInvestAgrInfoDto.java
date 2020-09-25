/*
 * 文件名：BussRepOutInvestAgrInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.trust.dto;


import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.common.base.entity.RepTrustInvestMode;

/**
 * 委外投资—协议方式中债报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfoDto
 * @since 1.0
 */
public class BussRepOutInvestAgrInfoDto {
    /**
     * 交易流通场所
     */
    private String repTradeMarket;
    /**
     * 二级资产类型
     */
    private String type6;
    /**
     * 资产负债报送信息entity
     */
    private RepAssetDept repAssetDept;
    /**
     * 委外投资—协议方式报送信息entity
     */
    private RepTrustInvestMode repTrustInvestMode;
    // 中债资产其它要素entity by 赵东东 2019.10.10 start
    /**
     * 中债资产其它要素entity
     */
    private RepAssetOther repAssetOther;
    /**
     * 获取 repAssetOther
     *
     * @return repAssetOther
     */
    public RepAssetOther getRepAssetOther() {
		return repAssetOther;
	}
    /**
     * 设置 repAssetOther
     *
     * @param repAssetOther repAssetOther
     */
	public void setRepAssetOther(RepAssetOther repAssetOther) {
		this.repAssetOther = repAssetOther;
	}
	// 中债资产其它要素entity by 赵东东 2019.10.10 end
	/**
     * 获取 type6
     *
     * @return type6
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     *
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
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
}
