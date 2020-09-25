/*
 * 文件名：BaseFinProductOpenPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductOpenPlanDto
 * @since 1.0
 */
public class BaseFinProductOpenPlanDto extends FinOpenPlan{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private List<FinOpenPlan> finOpenPlanList;

    
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 开放状态，不开放、开放
     */
    private String openStatus;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 募集结束日
     */
    private Date openDateEnd;

    
    /**
     * 获取 finOpenPlanList
     * @return finOpenPlanList.
     */
    public List<FinOpenPlan> getFinOpenPlanList() {
        return finOpenPlanList;
    }

    
    /**
     * 设置 finOpenPlanList
     * @param finOpenPlanList finOpenPlanList
     */
    public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
        this.finOpenPlanList = finOpenPlanList;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    
    /**
     * 获取 openStatus
     * @return openStatus.
     */
    public String getOpenStatus() {
        return openStatus;
    }

    
    /**
     * 设置 openStatus
     * @param openStatus openStatus
     */
    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    /**
     * 获取 openDateEnd
     * @return openDateEnd.
     */
    public Date getOpenDateEnd() {
        return openDateEnd;
    }

    
    /**
     * 设置 openDateEnd
     * @param openDateEnd openDateEnd
     */
    public void setOpenDateEnd(Date openDateEnd) {
        this.openDateEnd = openDateEnd;
    }
    
    
    
}
