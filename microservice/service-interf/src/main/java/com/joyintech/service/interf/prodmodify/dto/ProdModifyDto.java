/*
 * 文件名：ProdModifyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodmodify.dto;

import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品新建到期日调整接口参数类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月8日
 * @see ProdModifyDto
 * @since 1.0
 */
public class ProdModifyDto extends InterfFinProdDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4834873545776296940L;

    /**
     * @param finProdId
     */
    public ProdModifyDto(String finProdId) {
        super(finProdId);
    }

    /**
     * 画面ID
     */
    private String pageId;

    /**
     * 共同业务类型
     */
    private DictCodeList busiType;

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

    /**
     * 获取 busiType
     * 
     * @return busiType.
     */
    public DictCodeList getBusiType() {
        return busiType;
    }

    /**
     * 设置 busiType
     * 
     * @param busiType busiType
     */
    public void setBusiType(DictCodeList busiType) {
        this.busiType = busiType;
    }
}
