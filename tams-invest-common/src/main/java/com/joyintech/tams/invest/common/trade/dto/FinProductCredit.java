package com.joyintech.tams.invest.common.trade.dto;

import com.joyintech.tams.common.base.entity.FinProduct;

public class FinProductCredit extends FinProduct {

	/**
	 * 平安银行授信个性化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 分类1，估值核算专用
     */
    private String type1;

    /**
     * 分类2（核算类型），估值核算专用
     */
    private String type2;

    /**
     * 分类3，估值核算专用
     */
    private String type3;

    /**
     * 分类4（wind一级类型）
     */
    private String type4;

    /**
     * 分类5（wind二级类型）
     */
    private String type5;

    /**
     * 分类6（行内一级类型）
     */
    private String type6;

    /**
     * 分类7（行内二级类型）
     */
    private String type7;

    /**
     * null
     */
    private String type8;
    
    /**
     * 是否次级债
     */
    private String isSecBond;
    
    private FinProduct finProduct;
    
	public FinProduct getFinProduct() {
		return finProduct;
	}

	public void setFinProduct(FinProduct finProduct) {
		this.finProduct = finProduct;
	}

	public String getIsSecBond() {
		return isSecBond;
	}

	public void setIsSecBond(String isSecBond) {
		this.isSecBond = isSecBond;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getType4() {
		return type4;
	}

	public void setType4(String type4) {
		this.type4 = type4;
	}

	public String getType5() {
		return type5;
	}

	public void setType5(String type5) {
		this.type5 = type5;
	}

	public String getType6() {
		return type6;
	}

	public void setType6(String type6) {
		this.type6 = type6;
	}

	public String getType7() {
		return type7;
	}

	public void setType7(String type7) {
		this.type7 = type7;
	}

	public String getType8() {
		return type8;
	}

	public void setType8(String type8) {
		this.type8 = type8;
	}
    
    
}
