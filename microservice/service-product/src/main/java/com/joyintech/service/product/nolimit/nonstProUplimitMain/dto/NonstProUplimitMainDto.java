package com.joyintech.service.product.nolimit.nonstProUplimitMain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.BaseDto;
import com.joyintech.framework.json.BigDecimalSerializer;

public class NonstProUplimitMainDto extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4971363416209830787L;

	/**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;

    /**
     * 非标资产比例上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal nonStandardLimit;

    /**
     * 取得 产品规划系列名称
     * @return 产品规划系列名称
     */
    public String getPrdPlanSerisName() {
        return prdPlanSerisName;
    }

    /**
     * 设置 产品规划系列名称
     * @param prdPlanSerisName 产品规划系列名称
     */
    public void setPrdPlanSerisName(String prdPlanSerisName) {
        this.prdPlanSerisName = prdPlanSerisName == null ? null : prdPlanSerisName.trim();
    }

    /**
     * 取得 非标资产比例上限
     * @return 非标资产比例上限
     */
    public BigDecimal getNonStandardLimit() {
        return nonStandardLimit;
    }

    /**
     * 设置 非标资产比例上限
     * @param nonStandardLimit 非标资产比例上限
     */
    public void setNonStandardLimit(BigDecimal nonStandardLimit) {
        this.nonStandardLimit = nonStandardLimit;
    }
    /**
     * 产品规划系列编号
     */
    private String prdPlanSerisId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 取得 产品规划系列编号
     * @return 产品规划系列编号
     */
    public String getPrdPlanSerisId() {
        return prdPlanSerisId;
    }

    /**
     * 设置 产品规划系列编号
     * @param prdPlanSerisId 产品规划系列编号
     */
    public void setPrdPlanSerisId(String prdPlanSerisId) {
        this.prdPlanSerisId = prdPlanSerisId == null ? null : prdPlanSerisId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }
    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    /**
     * 非标比例明细表
     */
    private List<NonstProUplimitMainDto> tableList;

	public List<NonstProUplimitMainDto> getTableList() {
		return tableList;
	}

	public void setTableList(List<NonstProUplimitMainDto> tableList) {
		this.tableList = tableList;
	}
}
