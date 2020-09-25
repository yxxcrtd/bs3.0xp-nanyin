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


import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.common.base.entity.RepTrustInvestMode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.report.asset.trust.validator.BussRepOutInvestAgrInfoValidator;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 委外投资—协议方式中债报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfo
 * @since 1.0
 */
@ValidLogic(targetClass = BussRepOutInvestAgrInfoValidator.class,groups = {ValidateGroupEdit.class})
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
     * 是否需要验证
     */
    private boolean needValid = true;

    /**
     * 获取 needValid
     *
     * @return needValid
     */
    public boolean isNeedValid() {
        return needValid;
    }

    /**
     * 设置 needValid
     *
     * @param needValid needValid
     */
    public void setNeedValid(boolean needValid) {
        this.needValid = needValid;
    }

    /**
     * 二级资产类别
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
    // 是否投向金融资产投资公司发行的私募资产管理产品，中债资产其它要素entity by 赵东东2019.10.10 start
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
     * 设置  repAssetOther
     *
     * @param  repAssetOther  repAssetOther
     */
	public void setRepAssetOther(RepAssetOther repAssetOther) {
		this.repAssetOther = repAssetOther;
	}
	// 是否投向金融资产投资公司发行的私募资产管理产品，中债资产其它要素entity by 赵东东2019.10.10 end
	/**
     * 获取 repAssetType
     *
     * @return repAssetType
     */
    public String getRepAssetType() {
        if (needValid) {
            return repAssetType;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
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
     * 取得 资金实际投向
     * @return 资金实际投向
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repCapiInvest",groups={ValidateGroupEdit.class})
    public String getRepCapiInvest() {
        if (needValid) {
            return super.getRepCapiInvest();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 取得 资金运用方式
     * @return 资金运用方式
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repApplicationMode",groups={ValidateGroupEdit.class})
    public String getRepApplicationMode() {
        if (needValid) {
            return super.getRepApplicationMode();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 取得 资金运用行业
     * @return 资金运用行业
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repCapUseInduLevel1",groups={ValidateGroupEdit.class})
    public String getRepCapUseIndu() {
        if (needValid) {
            return super.getRepCapUseIndu();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 取得 资金运用行业二级分类
     * @return 资金运用行业二级分类
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repCapUseInduLevel2",groups={ValidateGroupEdit.class})
    public String getRepCapUseInduLevel2() {
        if (needValid) {
            return super.getRepCapUseInduLevel2();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 取得 委外投资属性_中债
     * @return 委外投资属性_中债
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repTrustInvestAttribute",groups={ValidateGroupEdit.class})
    public String getRepTrustInvestAttribute() {
        if (needValid) {
            return super.getRepTrustInvestAttribute();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repContainExpectedYield",groups={ValidateGroupEdit.class})
    public String getRepContainExpectedYield() {
        if (needValid) {
            return super.getRepContainExpectedYield();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    public boolean isRepContainExpected(){return StringUtil.equals(DictCodeList.是否_是.getValue(),super.getRepContainExpectedYield());}

    /**
     * 取得 预期最低收益率%
     * @return 预期最低收益率%
     */
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMinExpectedYield", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepMinExpectedYield() {
        if (needValid) {
            return super.getRepMinExpectedYield();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 预期最高收益率%
     * @return 预期最高收益率%
     */
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMaxExpectedYield", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepMaxExpectedYield() {
        if (needValid) {
            return super.getRepMaxExpectedYield();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 管理费率
     * @return 管理费率
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repManagementRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepManagementRate() {
        if (needValid) {
            return super.getRepManagementRate();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 托管费率%
     * @return 托管费率%
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repTrustRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepTrustRate() {
        if (needValid) {
            return super.getRepTrustRate();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 交易相关合计费率%
     * @return 交易相关合计费率%
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repTradeSumRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepTradeSumRate() {
        if (needValid) {
            return super.getRepTradeSumRate();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 中介服务机构合计费率%
     * @return 中介服务机构合计费率%
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repIntermediarySumRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepIntermediarySumRate() {
        if (needValid) {
            return super.getRepIntermediarySumRate();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 其他合计费率%
     * @return 其他合计费率%
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repOtherSumRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepOtherSumRate() {
        if (needValid) {
            return super.getRepOtherSumRate();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 取得 委托投资金额
     * @return 委托投资金额
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repEntrustInvestMoney",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepOutInvestAgrInfo.repEntrustInvestMoney", maxIntBit = 13, maxDecBit = 2,groups={ValidateGroupEdit.class})
    public BigDecimal getRepEntrustInvestMoney() {
        if (needValid) {
            return super.getRepEntrustInvestMoney();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 获取 repTradeMarket
     *
     * @return repTradeMarket
     */
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repTradeMarket",groups={ValidateGroupEdit.class})
    public String getRepTradeMarket() {
        if (needValid) {
            return repTradeMarket;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }
    
    @NotEmpty(fieldName = "BussRepOutInvestAgrInfo.repTrustee",groups={ValidateGroupEdit.class})
    public String getRepTrustee() {
        if (needValid) {
            return super.getRepTrustee();
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
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
