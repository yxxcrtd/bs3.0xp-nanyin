/*
 * 文件名：BussReduceShareInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.project.validator.BussReduceShareInfoValidator;

/**
 * 减持信息DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussReduceShareInfoValidator.class,groups = {ValidateGroupEdit.class})
public class BussReduceShareInfoDto extends FinReduceShareInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private List<FinReduceShareInfo> finReduceShareInfoList;

    
    /**
     * 获取 finReduceShareInfoList
     * @return finReduceShareInfoList.
     */
    public List<FinReduceShareInfo> getFinReduceShareInfoList() {
        return finReduceShareInfoList;
    }

    
    /**
     * 设置 finReduceShareInfoList
     * @param finReduceShareInfoList finReduceShareInfoList
     */
    public void setFinReduceShareInfoList(List<FinReduceShareInfo> finReduceShareInfoList) {
        this.finReduceShareInfoList = finReduceShareInfoList;
    }
    
}
