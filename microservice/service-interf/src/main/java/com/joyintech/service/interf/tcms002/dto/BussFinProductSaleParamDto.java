/*
 * 文件名：BussFinProductSaleParamDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import com.joyintech.tams.common.base.entity.PrdSaleParam;

/**
 * 销售参数业务组件Dto
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see BussFinProductSaleParamDto
 * @since 1.0
 */
public class BussFinProductSaleParamDto extends PrdSaleParam {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2867160252565349304L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}
