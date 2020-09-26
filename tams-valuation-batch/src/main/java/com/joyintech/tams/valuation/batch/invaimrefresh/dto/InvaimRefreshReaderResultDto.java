/*
 * 文件名：ReaderResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 投资目的刷新reader返回实体类
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see InvaimRefreshReaderResultDto
 * @since 1.0
 */
public class InvaimRefreshReaderResultDto extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 交易关联出来的投资目的信息
     */
    List<DealInvaimInfoDto> dealInvaimIntoDtoList = new ArrayList<DealInvaimInfoDto>();

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public List<DealInvaimInfoDto> getDealInvaimIntoDtoList() {
        return dealInvaimIntoDtoList;
    }

    public void setDealInvaimIntoDtoList(List<DealInvaimInfoDto> dealInvaimIntoDtoList) {
        this.dealInvaimIntoDtoList = dealInvaimIntoDtoList;
    }
}
