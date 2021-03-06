package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepAssetOther extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

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
     * 是否标准化票据
     */
    private String isBzhpj;
    /**
     * 是否政府出资产业投资基金
     */
    private String isUnlisted;
    /**
     * 是否商业银行可转债
     */
    private String bankSecKzzq;
    /**
     * 是否专项债券
     */
    private String bankSecZxzq;

    /**
     * 人行机构规模
     */
    private String repInstitSize;

    /**
     * 受让形式
     */
    private String repAssignee;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     * @return 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    public String getRepPrivatefundFlag() {
        return repPrivatefundFlag;
    }

    /**
     * 设置 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     * @param repPrivatefundFlag 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    public void setRepPrivatefundFlag(String repPrivatefundFlag) {
        this.repPrivatefundFlag = repPrivatefundFlag == null ? null : repPrivatefundFlag.trim();
    }

    /**
     * 取得 公共PPP
     * @return 公共PPP
     */
    public String getRepPppflag() {
        return repPppflag;
    }

    /**
     * 设置 公共PPP
     * @param repPppflag 公共PPP
     */
    public void setRepPppflag(String repPppflag) {
        this.repPppflag = repPppflag == null ? null : repPppflag.trim();
    }

    /**
     * 取得 是否小微企业信贷资产流转和收益权转让
     * @return 是否小微企业信贷资产流转和收益权转让
     */
    public String getRepSmallcredit() {
        return repSmallcredit;
    }

    /**
     * 设置 是否小微企业信贷资产流转和收益权转让
     * @param repSmallcredit 是否小微企业信贷资产流转和收益权转让
     */
    public void setRepSmallcredit(String repSmallcredit) {
        this.repSmallcredit = repSmallcredit == null ? null : repSmallcredit.trim();
    }

    /**
     * 取得 是否投向投向市场化债转股相关产品的部分
     * @return 是否投向投向市场化债转股相关产品的部分
     */
    public String getRepBondtostockFlag() {
        return repBondtostockFlag;
    }

    /**
     * 设置 是否投向投向市场化债转股相关产品的部分
     * @param repBondtostockFlag 是否投向投向市场化债转股相关产品的部分
     */
    public void setRepBondtostockFlag(String repBondtostockFlag) {
        this.repBondtostockFlag = repBondtostockFlag == null ? null : repBondtostockFlag.trim();
    }

    /**
     * 取得 是否投向金融资产投资公司发行的私募资产管理产品
     * @return 是否投向金融资产投资公司发行的私募资产管理产品
     */
    public String getRepPrivateprdFlag() {
        return repPrivateprdFlag;
    }

    /**
     * 设置 是否投向金融资产投资公司发行的私募资产管理产品
     * @param repPrivateprdFlag 是否投向金融资产投资公司发行的私募资产管理产品
     */
    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag == null ? null : repPrivateprdFlag.trim();
    }

    /**
     * 取得 人行机构规模
     * @return 人行机构规模
     */
    public String getRepInstitSize() {
        return repInstitSize;
    }

    /**
     * 设置 人行机构规模
     * @param repInstitSize 人行机构规模
     */
    public void setRepInstitSize(String repInstitSize) {
        this.repInstitSize = repInstitSize == null ? null : repInstitSize.trim();
    }

    /**
     * 取得 受让形式
     * @return 受让形式
     */
    public String getRepAssignee() {
        return repAssignee;
    }

    /**
     * 设置 受让形式
     * @param repAssignee 受让形式
     */
    public void setRepAssignee(String repAssignee) {
        this.repAssignee = repAssignee == null ? null : repAssignee.trim();
    }

	public String getIsBzhpj() {
		return isBzhpj;
	}

	public void setIsBzhpj(String isBzhpj) {
		this.isBzhpj = isBzhpj;
	}

	public String getIsUnlisted() {
		return isUnlisted;
	}

	public void setIsUnlisted(String isUnlisted) {
		this.isUnlisted = isUnlisted;
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