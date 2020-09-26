/*
 * 文件名：ValBokBookDetailResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto;

import java.util.ArrayList;
import java.util.List;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 账务明细信息取得结果DTO 〈功能详细描述〉
 * 
 * @author ruanhongming
 * @version 1.0 2018年6月1日
 * @see ValBokBookDetailDelResult
 * @since 1.0
 */
public class ValBokBookDetailDelResult extends NeedToWriteEntity {

    /**
     * 账套代码list
     */
    private List<String> booksetIds;

    /**
     * @param booksetIds
     */
    public ValBokBookDetailDelResult(List<String> booksetIds) {
        super();
        this.booksetIds = booksetIds;
    }

    /**
     * 获取 booksetIds
     * 
     * @return booksetIds.
     */
    public List<String> getBooksetIds() {
        return booksetIds;
    }

    /**
     * 设置 booksetIds
     * 
     * @param booksetIds booksetIds
     */
    public void setBooksetIds(List<String> booksetIds) {
        this.booksetIds = booksetIds;
    }

}
