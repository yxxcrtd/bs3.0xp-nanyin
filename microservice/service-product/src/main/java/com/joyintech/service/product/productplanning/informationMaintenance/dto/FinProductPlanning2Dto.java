package com.joyintech.service.product.productplanning.informationMaintenance.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 *产品规划信息维护结果dto
 *
 */
public class FinProductPlanning2Dto extends BaseEntity  {
    /**
     * 按钮Id
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 画面Id
     */
    private String pageId;
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
     * 规划编号
     */
    private String planningNo;
    /**
     * 列表集合
     * @return
     */
    private List<FinProductPlanning2DetailDto> detailDtoList;
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
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBaseDateStr() {
        return DateUtil.format(baseDate);
    }

    public String getPlanningNo() {
        return planningNo;
    }

    public void setPlanningNo(String planningNo) {
        this.planningNo = planningNo;
    }

    public List<FinProductPlanning2DetailDto> getDetailDtoList() {
        return detailDtoList;
    }

    public void setDetailDtoList(List<FinProductPlanning2DetailDto> detailDtoList) {
        this.detailDtoList = detailDtoList;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
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

}
