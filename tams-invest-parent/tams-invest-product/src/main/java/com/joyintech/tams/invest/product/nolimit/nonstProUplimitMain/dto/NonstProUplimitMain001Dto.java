package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.validator.NonstProUplimitMainValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
@ValidLogic(targetClass = NonstProUplimitMainValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class, ValidateGroupNew.class})
public class NonstProUplimitMain001Dto extends BaseEntity implements ProcessEntity{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;

    /**
     * 非标资产比例上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @Rate(fieldName="NonstProUplimitMain.nonStandardLimit",groups = {ValidateGroupEdit.class})
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
    @PrimaryKey(value= Field.PK_1)
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
    private String operateType;//操作类型

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	/**
	 * 非标比例明细表
     */
    private List<NonstProUplimitMain001Dto> tableList;

	public List<NonstProUplimitMain001Dto> getTableList() {
		return tableList;
	}

	public void setTableList(List<NonstProUplimitMain001Dto> tableList) {
		this.tableList = tableList;
	}
    
	public String getDetailKey() {
		return getEffDate() + getPrdPlanSerisId();
	}
	
	private String pageModel;
	  /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


}
