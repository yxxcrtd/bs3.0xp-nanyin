package com.joyintech.tams.invest.common.component.business.basicdata.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 交易费用输入参数
 */
public class BasicTradeFeeInputDto extends TrdProductDeal{
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	private String secAcctId;
    /**
     * 费用类型List
     */
    private List<String> feeTypeList;
    /**
     * 费用类型
     */
    private String feeType;
    /**
     * 期限品种
     */
    private String termType;
    /**
     * 是否是多个质押券
     */
    private String isGrade;
    
	/**
	 * 投管一级分类 type6
	 * @return
	 */
	private String type6;

	public List<String> getFeeTypeList() {
		return feeTypeList;
	}

	public void setFeeTypeList(List<String> feeTypeList) {
		this.feeTypeList = feeTypeList;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}
    
    /**
     * 获取 isGrade
     * @return isGrade.
     */
    public String getIsGrade() {
        return isGrade;
    }
    
    /**
     * 设置 isGrade
     * @param isGrade isGrade
     */
    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade;
    }


	public String getType6() {
		return type6;
	}

	public void setType6(String type6) {
		this.type6 = type6;
	}
}
