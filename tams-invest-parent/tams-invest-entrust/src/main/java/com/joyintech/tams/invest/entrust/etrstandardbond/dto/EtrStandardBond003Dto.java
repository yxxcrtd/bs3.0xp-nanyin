/*
 * 文件名：TrdStandardBond002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrSecPriority;
import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import com.joyintech.tams.invest.entrust.etrstandardbond.validator.EtrStandardBond003DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("EtrStandardBond003")
@ValidLogic(targetClass = EtrStandardBond003DtoValidator.class, groups = ValidateGroupEdit.class)
public class EtrStandardBond003Dto extends EtrSecPriority implements ProcessEntity {

    /**
     * 
     */
    
    private static final long serialVersionUID = 6792778858874406513L;
    
    
    private List<EtrSecPriorityDetail> detailList = new ArrayList<EtrSecPriorityDetail>() ;
    
    public List<EtrSecPriorityDetail> getDetailList() {
        return detailList;
    }
    
    
    public void setDetailList(List<EtrSecPriorityDetail> detailList) {
        this.detailList = detailList;
    }
    
    /**
     * 设置编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getConfigId() {
        return StringUtil.isNotEmpty(super.getConfigId())?super.getConfigId():IDGenerator.generate();
    }
    
}
