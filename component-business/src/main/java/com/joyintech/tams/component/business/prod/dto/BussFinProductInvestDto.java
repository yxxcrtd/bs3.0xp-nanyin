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

import com.joyintech.tams.common.base.entity.PtlGoal;

/**
 * 产品投向信息
 * @author yanya
 * @version 1.0 2019年8月22日
 * @see BussFinProductInvestDto
 * @since 1.0
 */
public class BussFinProductInvestDto extends PtlGoal {

    /**
     */
    private static final long serialVersionUID = -6988283539747988051L;
    
    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
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

}
