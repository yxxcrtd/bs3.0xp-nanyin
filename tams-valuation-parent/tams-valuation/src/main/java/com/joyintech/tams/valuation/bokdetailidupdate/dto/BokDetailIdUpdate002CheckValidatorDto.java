package com.joyintech.tams.valuation.bokdetailidupdate.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.bokdetailidupdate.validator.BokDetailIdUpdate002CheckValidator;
/**
 * 调整参数校验Dto
 * @author 吴洁
 *
 */
@ValidLogic(targetClass = BokDetailIdUpdate002CheckValidator.class)
public class BokDetailIdUpdate002CheckValidatorDto extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	//业务明细代码
	@NotEmpty(fieldName = "BokDetailIdUpdate.busiId")
	private String busiId;
	
	//业务明细类型
	@NotEmpty(fieldName = "BokDetailIdUpdate.busiDetailType")
	private String busiDetailType;
	
	//理财子产品起息日
	private Date vdate;
	
	//收益类型
	private String profitType;
	
	//投资目的
	private String invAim;
	
	//币种
	private String ccy;

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getBusiDetailType() {
		return busiDetailType;
	}

	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
	
	
}
