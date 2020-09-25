
package com.joyintech.service.product.messageadjust.finproductquotation.dto;

/**
 * 微服务端产品报价管理dto
 * @author wangkang
 * 
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProduct;

public class FinProductQuotation001Dto extends FinProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364633720300166637L;
	/**
	 * 销售部门
	 */
	private String saleDepartment;
	/**
	 * 生效日
	 */
	private Date effDate;
	/**
	 * 产品款型
	 */
	private String prodType;
	/**
	 * 报价日
	 */
	private Date releaseDate;
	/**
	 * 收益率/单位净值/万份收益
	 */
	private BigDecimal yield;
	/**
	 * 七日年化收益率
	 */
	private BigDecimal day7YearYield;
	/**
	 * 年化收益率
	 */
	private BigDecimal yearYield;
	/**
	 * 累计净值
	 */
	private BigDecimal totalValue;
	/**
	 * 报价来源
	 */
	private String inputType;
	/**
	 * 现金流代码
	 */
	private String cashId;
	
	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 对象list
	 */
	private List<FinProductQuotation001Dto> selectedItem;
	/**
	 * 元素Id
	 */
	private String elementId;
	/**
     * 是否分层
     */
    private String isLay;
    /**
     * 分层类型
     */
    private String layType;
    /**
     * 客户等级
     */
    private String customLevel;
    /**
     * 金额下限
     */
    private BigDecimal amtMin;
    /**
     * 金额上限
     */
    private BigDecimal amtMax;
    /**
     * 期限上限
     */
    private Long termMax;
    /**
     * 期限下限
     */
    private Long termMin;
    /**
     * 单位净值/万份收益
     */
    private BigDecimal valuation;
    /**
     * 收益率2
     */
    private BigDecimal yield2;
    /**
     * 是否是初始利率
     */
    private String isInitial;
    
    /**
     * 发送状态
     */
    private String sendStatus;
    
    
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSaleDepartment() {
		return saleDepartment;
	}
	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	public BigDecimal getDay7YearYield() {
		return day7YearYield;
	}
	public void setDay7YearYield(BigDecimal day7YearYield) {
		this.day7YearYield = day7YearYield;
	}
	public BigDecimal getYearYield() {
		return yearYield;
	}
	public void setYearYield(BigDecimal yearYield) {
		this.yearYield = yearYield;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public List<FinProductQuotation001Dto> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(List<FinProductQuotation001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
    
    public String getIsLay() {
        return isLay;
    }
    
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }
    
    public String getCustomLevel() {
        return customLevel;
    }
    
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }
    
    public BigDecimal getAmtMin() {
        return amtMin;
    }
    
    public void setAmtMin(BigDecimal amtMin) {
        this.amtMin = amtMin;
    }
    
    public BigDecimal getAmtMax() {
        return amtMax;
    }
    
    public void setAmtMax(BigDecimal amtMax) {
        this.amtMax = amtMax;
    }
    
    public Long getTermMax() {
        return termMax;
    }
    
    public void setTermMax(Long termMax) {
        this.termMax = termMax;
    }
    
    public Long getTermMin() {
        return termMin;
    }
    
    public void setTermMin(Long termMin) {
        this.termMin = termMin;
    }
    
    public BigDecimal getValuation() {
        return valuation;
    }
    
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }
    
    public BigDecimal getYield2() {
        return yield2;
    }
    
    public void setYield2(BigDecimal yield2) {
        this.yield2 = yield2;
    }
    
    /**
     * 获取 layType
     * @return layType.
     */
    public String getLayType() {
        return layType;
    }
    
    /**
     * 设置 layType
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }
    
    /**
     * 获取 isInitial
     * @return isInitial.
     */
    public String getIsInitial() {
        return isInitial;
    }
    
    /**
     * 设置 isInitial
     * @param isInitial isInitial
     */
    public void setIsInitial(String isInitial) {
        this.isInitial = isInitial;
    }
    
    /**
     * 获取 prodType
     * @return prodType.
     */
    public String getProdType() {
        return prodType;
    }
    
    /**
     * 设置 prodType
     * @param prodType prodType
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
	
	
}
