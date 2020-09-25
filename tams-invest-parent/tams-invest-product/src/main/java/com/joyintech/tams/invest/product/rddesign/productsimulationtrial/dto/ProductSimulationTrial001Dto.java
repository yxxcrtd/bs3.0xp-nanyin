package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.validator.ProductSimulationTrial001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品模拟试算dto
 * 
 * @author jiaobo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrial001Dto
 * @since 1.0
 * PRODUCT_REQUIREMENT	产品模拟试算表
 */
@PageId(value = "ProductSimulationTrial001")
@ValidLogic(targetClass = ProductSimulationTrial001Validator.class,groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ProductSimulationTrial001Dto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 流水号
     */
    private String seqNo;
    
    /**
     * 有效状态
     */
    private String dataStatus;

    /**
     * 测算日
     */
    private Date mockDate;

    /**
     * 生成时间
     */
    @NotEmpty(fieldName="ProductSimulationTrial001.generateTime",groups = {ValidateGroupEdit.class})
    private Date generateTime;

    /**
     * 产品设计系列编码
     */
    private String prdDesignId;

    /**
     * 产品规模
     */
    private BigDecimal prdScale;

    /**
     * 产品期限
     */
    private String prdPeriod;

    /**
     * 收益率上限
     */
    private String rateMax;

    /**
     * 收益率下限
     */
    private String rateMix;

    /**
     * 需求编号
     */
    private String requirementId;

    /**
     * 模拟配置资产列表
     */
    private List<ProductSimulationTrial002Dto> productSimulationTrialList;

    /**
     * 操作类型
     */
    private String operateType;

    private String elementId;

    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<ProductSimulationTrial002Dto> getProductSimulationTrialList() {
        return productSimulationTrialList;
    }

    public void setProductSimulationTrialList(List<ProductSimulationTrial002Dto> productSimulationTrialList) {
        this.productSimulationTrialList = productSimulationTrialList;
    }

    public String getRateMax() {
        return rateMax;
    }

    public void setRateMax(String rateMax) {
        this.rateMax = rateMax;
    }

    public String getRateMix() {
        return rateMix;
    }

    public void setRateMix(String rateMix) {
        this.rateMix = rateMix;
    }

    public String getDataStatus() {
        return dataStatus;
    }
    
    public String getDataStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态,this.dataStatus);
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getMockDate() {
        return mockDate;
    }

    public void setMockDate(Date mockDate) {
        this.mockDate = mockDate;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public String getPrdDesignId() {
        return prdDesignId;
    }

    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId;
    }

    public BigDecimal getPrdScale() {
        return prdScale;
    }

    public void setPrdScale(BigDecimal prdScale) {
        this.prdScale = prdScale;
    }

    public String getPrdPeriod() {
        return prdPeriod;
    }

    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }
}
