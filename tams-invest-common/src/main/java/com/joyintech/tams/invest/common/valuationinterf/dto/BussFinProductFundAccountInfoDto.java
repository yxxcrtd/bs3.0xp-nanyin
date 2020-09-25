/*
 * 文件名：FundAccountInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.valuationinterf.dto;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;


/**
 * 资金账户实体类
 * @author fanshuanghu
 * @version 1.0 2018年9月11日
 * @see BussFinProductFundAccountInfoDto
 * @since 1.0
 */
public class BussFinProductFundAccountInfoDto extends StlCapacctInfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6028360717726083021L;
    /**
     * 产品Id
     */
    private String finProdId;
    /**
     * 账户类型
     */
    @NotEmpty(fieldName = "FundAccountInfo.acctType",groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String acctType;
    /**
     * 账号
     */
    @NotEmpty(fieldName="FundAccountInfo.acctNo",groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String acctNo;
    
    /**
     * 费用代码
     */
    private String feeType;
    
    private String remark;
   

    /**
     * 获取 acctType
     * @return acctType.
     */
    public String getAcctType() {
        return acctType;
    }
    
    /**
     * 设置 acctType
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
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

	public String getFinProdId() {
		return finProdId;
	}

	public void setFinProdId(String finProdId) {
		this.finProdId = finProdId;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
    
}
