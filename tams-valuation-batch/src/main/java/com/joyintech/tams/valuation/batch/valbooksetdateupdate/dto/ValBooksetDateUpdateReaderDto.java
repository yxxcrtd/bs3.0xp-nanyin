/*
 * 文件名：ValBooksetDateUpdateReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbooksetdateupdate.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年7月11日
 * @see ValBooksetDateUpdateReaderDto
 * @since 1.0
 */
public class ValBooksetDateUpdateReaderDto extends NeedOperateEntity {
    /**
     * 账套代码
     */
    private List<String> booksetIds;

    public ValBooksetDateUpdateReaderDto(List<String> booksetIds){
        this.booksetIds = booksetIds;
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
