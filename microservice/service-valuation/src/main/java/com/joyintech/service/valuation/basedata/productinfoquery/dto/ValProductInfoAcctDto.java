/*
 * 文件名：ValProductInfoFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：Aug 17, 2018
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.basedata.productinfoquery.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户
 * 〈功能详细描述〉
 * @author 王耕耘
 * @version 1.0 Aug 17, 2018
 * @see ValProductInfoFeeDto
 * @since 1.0
 */
public class ValProductInfoAcctDto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodType;
    
    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodTypeCode;
    /**
     * 资金账户
     */
    private String acctNo;
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 币种
     */
    private String ccyCode;
    /**
     * 账户类型
     */
    private String acctType;
    /**
     * 账户类型
     */
    private String acctTypeCode;
    /**
     * 户名
     */
    private String acctName;
    /**
     * 开户行
     */
    private String bankDep;
    /**
     * 开户行类型
     */
    private String bankDepType;
    /**
     * 开户行类型
     */
    private String bankDepTypeCode;
    /**
     * 联合行号
     */
    private String corrNo;
    /**
     * 大额支付系统号
     */
    private String largePayno;
    /**
     * 境内外
     */
    private String blnArea;
    /**
     * 境内外
     */
    private String blnAreaCode;
    
    /**
     * 中文名（客户名称）
     */
    private String customerName;
    /**
     * 银行国际代码
     */
    private String swiftCode;
    /**
     * 证券编号（内部ID）
     */
    private String acctId;
    /**
     * 证券账户名称
     */
    private String acctCode;
    /**
     * 证券所属券商
     */
    private String brokerName;
    /**
     * 证券账户描述
     */
    private String acctDesc;
    /**
     * 证券账户描述
     */
    private String bankDescnumb;
    /**
     * 证券账户描述
     */
    private String bankDesc;    
    
    
    
    
    /**
     * 获取 finprodId
     * @return finprodId.
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
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return this.finprodType;
    }
    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
    
    /**
     * 获取 finprodTypeCode
     * @return finprodTypeCode.
     */
    public String getFinprodTypeCode() {
        return finprodTypeCode;
    }
    
    /**
     * 设置 finprodTypeCode
     * @param finprodTypeCode finprodTypeCode
     */
    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode;
    }
    
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }
    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return this.ccy;
    }
    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    /**
     * 获取 ccyCode
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }
    
    /**
     * 设置 ccyCode
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }
    
    /**
     * 获取 acctType
     * @return acctType.
     */
    public String getAcctType() {
        return this.acctType;
    }
    
    /**
     * 设置 acctType
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
    
    /**
     * 获取 acctTypeCode
     * @return acctTypeCode.
     */
    public String getAcctTypeCode() {
        return acctTypeCode;
    }
    
    /**
     * 设置 acctTypeCode
     * @param acctTypeCode acctTypeCode
     */
    public void setAcctTypeCode(String acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    /**
     * 获取 acctName
     * @return acctName.
     */
    public String getAcctName() {
        return acctName;
    }

    
    /**
     * 设置 acctName
     * @param acctName acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    
    /**
     * 获取 bankDep
     * @return bankDep.
     */
    public String getBankDep() {
        return bankDep;
    }

    
    /**
     * 设置 bankDep
     * @param bankDep bankDep
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    
    /**
     * 获取 corrNo
     * @return corrNo.
     */
    public String getCorrNo() {
        return corrNo;
    }

    
    /**
     * 设置 corrNo
     * @param corrNo corrNo
     */
    public void setCorrNo(String corrNo) {
        this.corrNo = corrNo;
    }

    
    /**
     * 获取 largePayno
     * @return largePayno.
     */
    public String getLargePayno() {
        return largePayno;
    }

    
    /**
     * 设置 largePayno
     * @param largePayno largePayno
     */
    public void setLargePayno(String largePayno) {
        this.largePayno = largePayno;
    }

    
    /**
     * 获取 blnArea
     * @return blnArea.
     */
    public String getBlnArea() {
        return this.blnArea;
    }

    
    /**
     * 设置 blnArea
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }

    
    /**
     * 获取 bankDepType
     * @return bankDepType.
     */
    public String getBankDepType() {
        return bankDepType;
    }

    
    /**
     * 设置 bankDepType
     * @param bankDepType bankDepType
     */
    public void setBankDepType(String bankDepType) {
        this.bankDepType = bankDepType;
    }

    
    /**
     * 获取 bankDepTypeCode
     * @return bankDepTypeCode.
     */
    public String getBankDepTypeCode() {
        return bankDepTypeCode;
    }

    
    /**
     * 设置 bankDepTypeCode
     * @param bankDepTypeCode bankDepTypeCode
     */
    public void setBankDepTypeCode(String bankDepTypeCode) {
        this.bankDepTypeCode = bankDepTypeCode;
    }

    
    /**
     * 获取 blnAreaCode
     * @return blnAreaCode.
     */
    public String getBlnAreaCode() {
        return blnAreaCode;
    }

    
    /**
     * 设置 blnAreaCode
     * @param blnAreaCode blnAreaCode
     */
    public void setBlnAreaCode(String blnAreaCode) {
        this.blnAreaCode = blnAreaCode;
    }

    
    /**
     * 获取 中文名（客户名）
     * @return 中文名（客户名）
     */
    public String getCustomerName() {
        return customerName;
    }

    
    /**
     * 设置 中文名（客户名）
     * @param customerName 中文名（客户名）
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    /**
     * 获取 swiftCode
     * @return swiftCode.
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    
    /**
     * 设置 swiftCode
     * @param swiftCode swiftCode
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getAcctCode() {
		return acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getAcctDesc() {
		return acctDesc;
	}

	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}

	public String getBankDescnumb() {
		return bankDescnumb;
	}

	public void setBankDescnumb(String bankDescnumb) {
		this.bankDescnumb = bankDescnumb;
	}

	public String getBankDesc() {
		return bankDesc;
	}

	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}
   
    

}
