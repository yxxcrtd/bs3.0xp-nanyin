/*
 * 文件名：BussFinProductValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.dto;

import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussFinProductValuationValidator;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品估值信息
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see BussFinProductValuationDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductValuationValidator.class)
public class BussFinProductValuationDto extends PrdValParam {

    /**
     */
    private static final long serialVersionUID = -6988283539747988051L;
    
    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
    /**
     * 收益类型
     */
    private String profitType;

    
    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
    
    public boolean isCountingCurInt() {
        return StringUtil.equals(DictCodeList.是否_是.getValue(), super.getIsCountingCurInt());
    }

}
