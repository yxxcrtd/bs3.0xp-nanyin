/*
 * 文件名：BussRepAssetMngProdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.plan.plancur.dto;


import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.RepAssetOther;

/**
 * 资产管理计划报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfoDto
 * @since 1.0
 */
//@ValidLogic(targetClass = BussRepDepositInfoValidator.class , groups = {ValidateGroupEdit.class})
public class BussRepAssetMngProdInfoDto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /*add by zhuyongkang 20191008 start*/
    private RepAssetOther repAssetOther;

    public RepAssetOther getRepAssetOther() {
        return repAssetOther;
    }

    public void setRepAssetOther(RepAssetOther repAssetOther) {
        this.repAssetOther = repAssetOther;
    }
    /*add by zhuyongkang 20191008 start*/
    /**
     * 资产负债报送信息entity
     */
    private RepAssetDept repAssetDept;
    /**
     * 资产管理计划报送信息entity
     */
    private RepAssetMngPlan repAssetMngPlan;
    /**
     * 获取 repAssetDept
     *
     * @return repAssetDept
     */
    public RepAssetDept getRepAssetDept() {
        return repAssetDept;
    }

    /**
     * 设置 repAssetDept
     *
     * @param repAssetDept repAssetDept
     */
    public void setRepAssetDept(RepAssetDept repAssetDept) {
        this.repAssetDept = repAssetDept;
    }

    /**
     * 获取 repAssetMngPlan
     *
     * @return repAssetMngPlan
     */
    public RepAssetMngPlan getRepAssetMngPlan() {
        return repAssetMngPlan;
    }

    /**
     * 设置 repAssetMngPlan
     *
     * @param repAssetMngPlan repAssetMngPlan
     */
    public void setRepAssetMngPlan(RepAssetMngPlan repAssetMngPlan) {
        this.repAssetMngPlan = repAssetMngPlan;
    }
}
