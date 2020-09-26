/*
 * 文件名：ValProductInfoFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.finncproductbuild.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2020年2月12日
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
     * 资金账户
     */
    private String acctNo;
    
    /**
     * 币种
     */
    private String ccy;

    /**
     * 账户类型
     */
    private String acctType;
    
    /**
     * 户名
     */
    private String acctName;
    
    /**
     * 中文名
     */
    private String acctName1;
    
    /**
     * 账号
     */
    private String busiAcctNo;
    
    /**
     * 开户行
     */
    private String bankDep;
    
    /**
     * 行内行外
     */
    private String inOutBank;
    
    /**
     * 开户行类型
     */
    private String bankDepType;

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
     * 备注
     */
    private String remark;
    
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
     * 获取 金融产品类型数据字典
     * @return 金融产品类型数据字典
     */
    public String getFinprodTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType):this.finprodType;
    }
    
    /**
     * 获取 金融产品类型
     * @return 金融产品类型.
     */
    public String getFinprodType() {
        return finprodType;
    }
    
    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
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
     * 获取 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 币种数据字典
     * 
     * @return 币种数据字典
     */
    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy))?
    			SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy):this.ccy;
    }
    
    /**
     * 获取 资金账户类型数据字典
     * @return 资金账户类型数据字典
     */
    public String getAcctTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.资金账户类型, this.acctType))?
    			SysDataUtil.getNameFromDataDict(DictCode.资金账户类型, this.acctType):this.acctType;
    }
    
    /**
     * 获取 资金账户类型
     * @return 资金账户类型.
     */
    public String getAcctType() {
        return acctType;
    }
    
    /**
     * 设置 资金账户类型
     * @param acctType 资金账户类型
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
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
     * 获取 acctName1
     * @return acctName1
     */
    public String getAcctName1() {
        return acctName1;
    }

    /**
     * 设置 acctName1
     * @param acctName1 acctName1
     */
    public void setAcctName1(String acctName1) {
        this.acctName1 = acctName1;
    }
    
    /**
     * 获取 账号
     * @return 账号
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 账号
     * @param busiAcctNo 账号
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }
    
    /**
     * 获取 开户行
     * @return 开户行
     */
    public String getBankDep() {
        return bankDep;
    }

    /**
     * 设置 开户行
     * @param bankDep 开户行
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }
    
    /**
     * 获取 行内行外
     * @return 行内行外
     */
    public String getInOutBank() {
        return inOutBank;
    }

    /**
     * 设置 行内行外
     * @param inOutBank 行内行外
     */
    public void setInOutBank(String inOutBank) {
        this.inOutBank = inOutBank;
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
     * 获取 blnArea
     * @return blnArea
     */
    public String getBlnArea() {
        return blnArea;
    }

    
    /**
     * 设置 blnArea
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }
    
    
    /**
     * 获取 blnArea
     * @return blnArea.
     */
    public String getBlnAreaFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea))?
    			SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea):this.blnArea;
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
    
    /**
     * 获取 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
