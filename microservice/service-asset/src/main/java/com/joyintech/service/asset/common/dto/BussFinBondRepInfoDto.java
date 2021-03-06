/*
 * 文件名：BussFinBondRepInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import com.joyintech.tams.common.base.entity.RepAssetBond;

/**
 *  债券类中债报送DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoDto
 * @since 1.0
 */
public class BussFinBondRepInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 资产/负债类别
     */
    private String repAssetType;
    
    /**
     * 交易流通场所
     */
    private String repTradeMarket;

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

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;

    /**
     * 人行机构规模
     */
    private String repInstitSize;

    /**
     * 受让形式
     */
    private String repAssignee;
    
    
    /**
     * 是否由金融资产投资公司发行
     */
    private String repFinancialComissue;
    
    /**
     * 商业银行二级资本债
     */
    private String repBankSecCapital;
    
    /**
     * 商业银行永续债
     */
    private String repBankSecPerpetual;
    
    /**
     * 是否商业银行可转债
     */
    private String bankSecKzzq;
    /**
     * 是否专项债券
     */
    private String bankSecZxzq;

    public String getRepFinancialComissue() {
		return repFinancialComissue;
	}

    /**
     * 是否由金融资产投资公司发行
     */
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue == null?null:repFinancialComissue.trim();
	}

	public String getRepBankSecCapital() {
		return repBankSecCapital;
	}

	/**
     * 商业银行二级资本债
     */
	public void setRepBankSecCapital(String repBankSecCapital) {
		this.repBankSecCapital = repBankSecCapital == null?null:repBankSecCapital.trim();
	}

	public String getRepBankSecPerpetual() {
		return repBankSecPerpetual;
	}

	 /**
     * 商业银行永续债
     */
	public void setRepBankSecPerpetual(String repBankSecPerpetual) {
		this.repBankSecPerpetual = repBankSecPerpetual == null?null:repBankSecPerpetual.trim();
	}
    
    
    /**
     * 获取 repAssetType
     * @return repAssetType.
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    
    /**
     * 设置 repAssetType
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }


    
    /**
     * 获取 repTradeMarket
     * @return repTradeMarket.
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }


    
    /**
     * 设置 repTradeMarket
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
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

    public String getRepPrivateprdFlag() {
        return repPrivateprdFlag;
    }

    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag;
    }

    public String getRepInstitSize() {
        return repInstitSize;
    }

    public void setRepInstitSize(String repInstitSize) {
        this.repInstitSize = repInstitSize;
    }

    public String getRepAssignee() {
        return repAssignee;
    }

    public void setRepAssignee(String repAssignee) {
        this.repAssignee = repAssignee;
    }

	public String getBankSecKzzq() {
		return bankSecKzzq;
	}

	public void setBankSecKzzq(String bankSecKzzq) {
		this.bankSecKzzq = bankSecKzzq;
	}

	public String getBankSecZxzq() {
		return bankSecZxzq;
	}

	public void setBankSecZxzq(String bankSecZxzq) {
		this.bankSecZxzq = bankSecZxzq;
	}
}
