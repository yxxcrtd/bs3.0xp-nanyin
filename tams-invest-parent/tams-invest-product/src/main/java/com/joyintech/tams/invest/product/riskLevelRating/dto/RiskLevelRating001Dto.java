package com.joyintech.tams.invest.product.riskLevelRating.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.riskLevelRating.validator.RiskLevelRatingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: RiskLevelRating001Dto
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02 16:44
 **/
@PageId("RiskLevelRating001")
@ValidLogic(targetClass = RiskLevelRatingValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class, ValidateGroupNew.class})
public class RiskLevelRating001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 流水号
     */
    @NotEmpty(fieldName = "RiskLevelRating001.seqNo")
    private String seqNo;
    /**
     * 资产风险等级编号
     */
    private String riskLevelNo;
    /**
     * 生成时间
     */
    private Date generateTime;
    /**
     * 金融产品代码
     */
    @NotEmpty(fieldName = "RiskLevelRating001.finProductCode", groups = {ValidateGroupEdit.class,
            ValidateGroupDelete.class, ValidateGroupNew.class})
    private String finProductCode;
    /**
     * 产品简称
     */
    private String finprodAbbr;
    /**
     * 分支序号
     */
    private BigDecimal branch;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 调整后风险等级
     */
    private String ajustRiskLevel;
    /**
     * 是否存续
     */
    private String isLast;
    /**
     * 操作
     */
    private String OperateType;
    /**
     * 资产
     */
    private List<RiskLevelRating002Dto> selectIem;

    @PrimaryKey(value = Field.PK_1)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public String getFinProductCode() {
        return finProductCode;
    }

    public void setFinProductCode(String finProductCode) {
        this.finProductCode = finProductCode;
    }

    public BigDecimal getBranch() {
        return branch;
    }

    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public String getRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产风险等级2, this.getRiskLevel());
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAjustRiskLevel() {
        return ajustRiskLevel;
    }

    public String getAjustRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产风险等级2, this.getAjustRiskLevel());
    }

    public void setAjustRiskLevel(String ajustRiskLevel) {
        this.ajustRiskLevel = ajustRiskLevel;
    }

    public String getIsLast() {
        return isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getOperateType() {
        return OperateType;
    }

    public void setOperateType(String operateType) {
        OperateType = operateType;
    }

    public String getRiskLevelNo() {
        return riskLevelNo;
    }

    public void setRiskLevelNo(String riskLevelNo) {
        this.riskLevelNo = riskLevelNo;
    }

    public List<RiskLevelRating002Dto> getSelectIem() {
        return selectIem;
    }

    public void setSelectIem(List<RiskLevelRating002Dto> selectIem) {
        this.selectIem = selectIem;
    }
}
