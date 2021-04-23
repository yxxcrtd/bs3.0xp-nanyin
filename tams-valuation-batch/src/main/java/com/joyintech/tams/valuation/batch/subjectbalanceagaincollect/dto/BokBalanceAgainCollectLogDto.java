/*
 * 文件名：BooksetTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lby
 * 修改时间：2020年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 科目余额重汇信息记录DTO
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see BokBalanceAgainCollectLogDto
 * @since 1.0
 */
public class BokBalanceAgainCollectLogDto extends BaseEntity {

    private static final long serialVersionUID = -5203381388587310415L;
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 重汇结束日
     */
    private Date cdate;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}
