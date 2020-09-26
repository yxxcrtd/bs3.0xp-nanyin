/*
 * 文件名：ValBokBookDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 待处理账套Dto
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValBokBookDetailDelDto
 * @since 1.0
 */
public class ValBokBookDetailDelDto extends NeedOperateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    
    /**
     * 账套代码list
     */
    private List<String> booksetIds;

    
    /**
     * @param booksetIds
     */
    public ValBokBookDetailDelDto(List<String> booksetIds) {
        super();
        this.booksetIds = booksetIds;
    }

    /**
     * @param booksetIds
     */
    public ValBokBookDetailDelResult toResult() {
        return new ValBokBookDetailDelResult(booksetIds);
    }
    
    /**
     * 获取 booksetIds
     * @return booksetIds.
     */
    public List<String> getBooksetIds() {
        return booksetIds;
    }

    
    /**
     * 设置 booksetIds
     * @param booksetIds booksetIds
     */
    public void setBooksetIds(List<String> booksetIds) {
        this.booksetIds = booksetIds;
    }
    
}
