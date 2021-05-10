/*
 * 文件名：ValBondMarketInfo001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto;

import java.util.Date;

import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 每日债券行情查询条件dto
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001SearchDto
 * @since 1.0
 */
public class ComBondMarketInfo001SearchDto {
    
    /**
     * 资产代码
     */
    private String finprodId;
    /**
     * 债券简称
     */
    private String finprodAbbr;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     *  估值日
     */
    private String valueDate;

    /**
     *  估值日开始时间
     */
    private Date valueDateBegin;

    /**
     *  估值日结束时间
     */
    private Date valueDateEnd;

    
    
    private String finprodType;
    
    
    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return DictCodeListExt1.金融产品类型_债券.getValue();
    }



    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }



    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }


    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }


    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }


    /**
     * 获取 valueSource
     * @return valueSource.
     */
    public String getValueSource() {
        return valueSource;
    }

    
    /**
     * 设置 valueSource
     * @param valueSource valueSource
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    
    /**
     * 获取 valueDate
     * @return valueDate.
     */
    public String getValueDate() {
        return valueDate;
    }

    
    /**
     * 设置 valueDate
     * @param valueDate valueDate
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    
    /**
     * 获取 valueDateBegin
     * @return valueDateBegin.
     */
    public Date getValueDateBegin() {
        return valueDateBegin;
    }

    
    /**
     * 设置 valueDateBegin
     * @param valueDateBegin valueDateBegin
     */
    public void setValueDateBegin(Date valueDateBegin) {
        this.valueDateBegin = valueDateBegin;
    }

    
    /**
     * 获取 valueDateEnd
     * @return valueDateEnd.
     */
    public Date getValueDateEnd() {
        return valueDateEnd;
    }

    
    /**
     * 设置 valueDateEnd
     * @param valueDateEnd valueDateEnd
     */
    public void setValueDateEnd(Date valueDateEnd) {
        this.valueDateEnd = valueDateEnd;
    }
    
    


}
