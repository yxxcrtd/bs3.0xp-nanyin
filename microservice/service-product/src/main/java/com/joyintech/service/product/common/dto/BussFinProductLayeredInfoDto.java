/*
 * 文件名：BussFinProductLayeredInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dto;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.PrdLayerParam;



/**
 * 产品分层
 * 
 * @author yejiejie
 * @version 1.0 2019年1月24日
 * @see BussFinProductLayeredInfoDto
 * @since 1.0
 */

public class BussFinProductLayeredInfoDto extends PrdLayerParam {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6929498311648247411L;

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
