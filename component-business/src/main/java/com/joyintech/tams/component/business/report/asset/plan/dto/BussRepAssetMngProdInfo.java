/*
 * 文件名：BussRepAssetMngProdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.asset.plan.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.report.asset.depo.validator.BussRepDepositInfoValidator;
import com.joyintech.tams.component.business.report.asset.plan.validator.BussRepAssetMngProdInfoValidator;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产管理计划报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfo
 * @since 1.0
 */
@ValidLogic(targetClass = BussRepAssetMngProdInfoValidator.class,groups = {ValidateGroupEdit.class})
public class BussRepAssetMngProdInfo extends BaseEntity {

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
     * 交易流通场所
     */
    private String repTradeMarket       ;
    /**
     * 二级资产类别
     */
    private String repAssetType         ;
    /**
     * 资管计划发起人机构编码
     */
    private String repPartyCode         ;
    /**
     * 资管计划登记编码
     */
    private String repRegistCode        ;
    /**
     * 资金实际投向
     */
    private String repCapiInvest        ;
    /**
     * 资金运用方式
     */
    private String repCapUseWay         ;
    /**
     * 资金运用行业
     */
    private String repCapUseIndu        ;
    /**
     * 资金运用行业二级分类
     */
    private String repCapUseInduLevel2  ;
    /**
     * 资管计划属性
     */
    private String repPlanProp          ;
    /**
     * 是否有预期收益率
     */
    private String repYieldFlag ;

    /**
     * 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String repPrivatefundFlag;

    /**
     * 公共PPP
     */
    private String repPppflag;

    /**
     * 是否小微企业信贷资产流转和收益权转让
     */
    private String repSmallcredit;

    /**
     * 是否投向投向市场化债转股相关产品的部分
     */
    private String repBondtostockFlag;


    public boolean isRepYield(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepYieldFlag());}
/*新加字段 add by zhuyongkang 2019/10/08 start*/
    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag ;

    //public boolean isRepPrivateprdFlag(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepPrivateprdFlag());}

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
    /*新加字段 add by zhuyongkang 2019/10/08 END*/
    /**
     * 预期最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxRate		;
    /**
     * 预期最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinRate 		;
    /**
     * 购买结构
     */

    private String repBuyStruct 		;
    /**
     * 管理方式
     */
    private String repManageType 		;
    /**
     * 是否掌控计划底层资产详情
     */

    private String repIsCtrlBottom  	;

    public boolean isRepIsCtrlBottomFlag(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepIsCtrlBottom());}

    /**
     * 管理费率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal repMgrFeeRate 	;
    /**
     * 托管费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTruFeeRate  	;
    /**
     * 交易相关合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeFeeRate  ;
    /**
     * 中介服务机构合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAgencyFeeRate ;
    /**
     * 其他合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherFeeRate  ;
    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt ;
    /**
     * 备注
     */
    private String repRemark ;
    
    /**
     * 是否由金融资产投资公司发行
     */
    private String repFinancialComissue;
    
    public String getRepFinancialComissue() {
		return repFinancialComissue;
	}
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue;
	}

    /**
     * 获取 repTradeMarket
     *
     * @return repTradeMarket
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repTradeMarket",groups={ValidateGroupEdit.class})
    public String getRepTradeMarket() {
        if (needValid) {
            return repTradeMarket;
        } else {
            return "xxx";
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

    /**
     * 获取 repAssetType
     *
     * @return repAssetType
     */
    public String getRepAssetType() {
        if (needValid) {
            return repAssetType;
        } else {
            return "xxx";
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
     * 获取 repPartyCode
     *
     * @return repPartyCode
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repPartyCode",groups={ValidateGroupEdit.class})
    public String getRepPartyCode() {
        if (needValid) {
            return repPartyCode;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repPartyCode
     *
     * @param repPartyCode repPartyCode
     */
    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode;
    }

    /**
     * 获取 repRegistCode
     *
     * @return repRegistCode
     */
    public String getRepRegistCode() {
        return repRegistCode;
    }

    /**
     * 设置 repRegistCode
     *
     * @param repRegistCode repRegistCode
     */
    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode;
    }

    /**
     * 获取 repCapiInvest
     *
     * @return repCapiInvest
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repCapiInvest",groups={ValidateGroupEdit.class})
    public String getRepCapiInvest() {
        if (needValid) {
            return repCapiInvest;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repCapiInvest
     *
     * @param repCapiInvest repCapiInvest
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest;
    }

    /**
     * 获取 repCapUseWay
     *
     * @return repCapUseWay
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repCapUseWay",groups={ValidateGroupEdit.class})
    public String getRepCapUseWay() {
        if (needValid) {
            return repCapUseWay;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repCapUseWay
     *
     * @param repCapUseWay repCapUseWay
     */
    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay;
    }

    /**
     * 获取 repCapUseIndu
     *
     * @return repCapUseIndu
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repCapUseInduLevel2",groups={ValidateGroupEdit.class})
    public String getRepCapUseIndu() {
        if (needValid) {
            return repCapUseIndu;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repCapUseIndu
     *
     * @param repCapUseIndu repCapUseIndu
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu;
    }

    /**
     * 获取 repCapUseInduLevel2
     *
     * @return repCapUseInduLevel2
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repCapUseIndu",groups={ValidateGroupEdit.class})
    public String getRepCapUseInduLevel2() {
        if (needValid) {
            return repCapUseInduLevel2;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repCapUseInduLevel2
     *
     * @param repCapUseInduLevel2 repCapUseInduLevel2
     */
    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2;
    }

    /**
     * 获取 repPlanProp
     *
     * @return repPlanProp
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repPlanProp",groups={ValidateGroupEdit.class})
    public String getRepPlanProp() {
        if (needValid) {
            return repPlanProp;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repPlanProp
     *
     * @param repPlanProp repPlanProp
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp;
    }

    /**
     * 获取 repYieldFlag
     *
     * @return repYieldFlag
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repYieldFlag",groups={ValidateGroupEdit.class})
    public String getRepYieldFlag() {
        if (needValid) {
            return repYieldFlag;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repYieldFlag
     *
     * @param repYieldFlag repYieldFlag
     */
    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag;
    }

    /**
     * 获取 repMaxRate
     *
     * @return repMaxRate
     */
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMaxRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }

    /**
     * 设置 repMaxRate
     *
     * @param repMaxRate repMaxRate
     */
    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }

    /**
     * 获取 repMinRate
     *
     * @return repMinRate
     */
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMinRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepMinRate() {
        return repMinRate;
    }

    /**
     * 设置 repMinRate
     *
     * @param repMinRate repMinRate
     */
    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }

    /**
     * 获取 repBuyStruct
     *
     * @return repBuyStruct
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repBuyStruct",groups={ValidateGroupEdit.class})
    public String getRepBuyStruct() {
        if (needValid) {
            return repBuyStruct;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repBuyStruct
     *
     * @param repBuyStruct repBuyStruct
     */
    public void setRepBuyStruct(String repBuyStruct) {
        this.repBuyStruct = repBuyStruct;
    }

    /**
     * 获取 repManageType
     *
     * @return repManageType
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repManageType",groups={ValidateGroupEdit.class})
    public String getRepManageType() {
        if (needValid) {
            return repManageType;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repManageType
     *
     * @param repManageType repManageType
     */
    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType;
    }

    /**
     * 获取 repIsCtrlBottom
     *
     * @return repIsCtrlBottom
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repIsCtrlBottom",groups={ValidateGroupEdit.class})
    public String getRepIsCtrlBottom() {
        if (needValid) {
            return repIsCtrlBottom;
        } else {
            return BussinessConstant.REPORT_INFORMATION;
        }
    }

    /**
     * 设置 repIsCtrlBottom
     *
     * @param repIsCtrlBottom repIsCtrlBottom
     */
    public void setRepIsCtrlBottom(String repIsCtrlBottom) {
        this.repIsCtrlBottom = repIsCtrlBottom;
    }

    /**
     * 获取 repMgrFeeRate
     *
     * @return repMgrFeeRate
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repMgrFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepMgrFeeRate() {
        if (needValid) {
            return repMgrFeeRate;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置 repMgrFeeRate
     *
     * @param repMgrFeeRate repMgrFeeRate
     */
    public void setRepMgrFeeRate(BigDecimal repMgrFeeRate) {
        this.repMgrFeeRate = repMgrFeeRate;
    }

    /**
     * 获取 repTruFeeRate
     *
     * @return repTruFeeRate
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repTruFeeRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repTruFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepTruFeeRate() {
        if (needValid) {
            return repTruFeeRate;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置 repTruFeeRate
     *
     * @param repTruFeeRate repTruFeeRate
     */
    public void setRepTruFeeRate(BigDecimal repTruFeeRate) {
        this.repTruFeeRate = repTruFeeRate;
    }

    /**
     * 获取 repTradeFeeRate
     *
     * @return repTradeFeeRate
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repTradeFeeRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repTradeFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepTradeFeeRate() {
        if (needValid) {
            return repTradeFeeRate;
        } else {
            return BigDecimal.ZERO;
        }

    }

    /**
     * 设置 repTradeFeeRate
     *
     * @param repTradeFeeRate repTradeFeeRate
     */
    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }

    /**
     * 获取 repAgencyFeeRate
     *
     * @return repAgencyFeeRate
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repAgencyFeeRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repAgencyFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepAgencyFeeRate() {
        if (needValid) {
            return repAgencyFeeRate;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置 repAgencyFeeRate
     *
     * @param repAgencyFeeRate repAgencyFeeRate
     */
    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }

    /**
     * 获取 repOtherFeeRate
     *
     * @return repOtherFeeRate
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repOtherFeeRate",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repOtherFeeRate", maxIntBit = 3, maxDecBit = 8,groups={ValidateGroupEdit.class})
    public BigDecimal getRepOtherFeeRate() {
        if (needValid) {
            return repOtherFeeRate;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置 repOtherFeeRate
     *
     * @param repOtherFeeRate repOtherFeeRate
     */
    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    /**
     * 获取 repAmt
     *
     * @return repAmt
     */
    @NotEmpty(fieldName = "BussRepAssetMngProdInfo.repAmt",groups={ValidateGroupEdit.class})
    @Amount(fieldName = "BussRepAssetMngProdInfo.repAmt", maxIntBit = 13, maxDecBit = 2,groups={ValidateGroupEdit.class})
    public BigDecimal getRepAmt() {
        if (needValid) {
            return repAmt;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置 repAmt
     *
     * @param repAmt repAmt
     */
    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 remark
     *
     * @param repRemark repRemark
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark;
    }

    public String getRepPrivatefundFlag() {
        return repPrivatefundFlag;
    }

    public void setRepPrivatefundFlag(String repPrivatefundFlag) {
        this.repPrivatefundFlag = repPrivatefundFlag;
    }

    public String getRepPppflag() {
        return repPppflag;
    }

    public void setRepPppflag(String repPppflag) {
        this.repPppflag = repPppflag;
    }

    public String getRepSmallcredit() {
        return repSmallcredit;
    }

    public void setRepSmallcredit(String repSmallcredit) {
        this.repSmallcredit = repSmallcredit;
    }

    public String getRepBondtostockFlag() {
        return repBondtostockFlag;
    }

    public void setRepBondtostockFlag(String repBondtostockFlag) {
        this.repBondtostockFlag = repBondtostockFlag;
    }
}
