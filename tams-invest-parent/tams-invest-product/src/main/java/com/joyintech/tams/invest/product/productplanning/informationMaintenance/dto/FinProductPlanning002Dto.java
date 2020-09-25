package com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.validator.FinProductPlanning002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

/**
 *产品规划信息维护结果dto
 *
 */
@PageId(value = "FinProductPlanningEstablish002")
@ValidLogic(targetClass = FinProductPlanning002DtoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class FinProductPlanning002Dto extends BaseEntity implements ProcessEntity {
    /**
     * 按钮Id
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 画面I
     * d
     */
    private String pageId;
    /**
     * 规划编号
     */
    private String planningNo;
    /**
     * 基准日期  默认为平台日
     */
    @NotEmpty(fieldName = "FinProductPlanningEstablish001.bdate")
    private Date baseDate;

    /**
     * 规划开始日
     */
    @NotEmpty(fieldName = "FinProductPlanningEstablish001.sdate")
    private  Date  planningStartDate;

    /**
     * 规划结束日
     */
    @NotEmpty(fieldName = "FinProductPlanningEstablish001.edate")
    private  Date  planningEndDate;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 備註
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    private List<FinProductPlanning002DetailDto> detailDtoList;

    public String getBaseDateStr() {
        return DateUtil.format(baseDate);
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }
    public Date getBaseDate() {
        return baseDate;
    }

    public String getPlanningStartDateStr() {
        return DateUtil.format(planningStartDate) ;
    }
    public Date getPlanningStartDate() {
        return planningStartDate ;
    }

    public void setPlanningStartDate(Date planningStartDate) {
        this.planningStartDate = planningStartDate;
    }

    public String getPlanningEndDateStr() {
        return DateUtil.format(planningEndDate);
    }

    public Date getPlanningEndDate() {
        return planningEndDate;
    }

    public void setPlanningEndDate(Date planningEndDate) {
        this.planningEndDate = planningEndDate;
    }
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<FinProductPlanning002DetailDto> getDetailDtoList() {
        return detailDtoList;
    }

    public void setDetailDtoList(List<FinProductPlanning002DetailDto> detailDtoList) {
        this.detailDtoList = detailDtoList;
    }
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getPlanningNo() {
        return planningNo;
    }

    public void setPlanningNo(String planningNo) {
        this.planningNo = planningNo;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
