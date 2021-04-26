/*
 * 文件名：ProfitDistributionReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.psbc.productreport.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

import java.util.Date;

/**
 * 理财产品报表
 *
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see ProductReportResult
 * @since 1.0
 */
public class ProductReportResult extends NeedToWriteEntity {

    /**
     * 账套
     */
    private String bookSetId;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 理财产品状态表
     */
    private RepProdStatusDto repProdStatusDto;

    /**
     * 理财信息状态表
     */
    private RepProdMessageDto repProdMessageDto;

    public String getBookSetId() {
        return bookSetId;
    }

    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public RepProdStatusDto getRepProdStatusDto() {
        return repProdStatusDto;
    }

    public void setRepProdStatusDto(RepProdStatusDto repProdStatusDto) {
        this.repProdStatusDto = repProdStatusDto;
    }

    public RepProdMessageDto getRepProdMessageDto() {
        return repProdMessageDto;
    }

    public void setRepProdMessageDto(RepProdMessageDto repProdMessageDto) {
        this.repProdMessageDto = repProdMessageDto;
    }
}
