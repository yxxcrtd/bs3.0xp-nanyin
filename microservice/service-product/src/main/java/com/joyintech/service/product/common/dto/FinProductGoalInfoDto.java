/*
 * 文件名：BussFinProductGoalInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PtlGoal;

/**
 * 产品投向业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see FinProductGoalInfoDto
 * @since 1.0
 */
public class FinProductGoalInfoDto extends PtlGoal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1185366540776249582L;
    
    /**
     * 投向列表    
     */
    private List<PtlGoal> finProductPtlGoalDtoList;


    
    /**
     * 获取 finProductPtlGoalDtoList
     * @return finProductPtlGoalDtoList.
     */
    public List<PtlGoal> getFinProductPtlGoalDtoList() {
        return finProductPtlGoalDtoList;
    }
    
    /**
     * 设置 finProductPtlGoalDtoList
     * @param finProductPtlGoalDtoList finProductPtlGoalDtoList
     */
    public void setFinProductPtlGoalDtoList(List<PtlGoal> finProductPtlGoalDtoList) {
        this.finProductPtlGoalDtoList = finProductPtlGoalDtoList;
    }
}
