package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepExtConusKey extends BaseEntity {
    /**
     * 日期
     */
    private Date dataDate;

    /**
     * 数据类型：01 中债-新综合全价(总值)指数、02 沪深300、03 中债-新综合财富(1-3年)指数、04 中证500、05 银行一年定期存款利率、06 7日通知存款利率
     */
    private String dataType;

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getDataDate() {
        return dataDate;
    }

    /**
     * 设置 日期
     * @param dataDate 日期
     */
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    /**
     * 取得 数据类型：01 中债-新综合全价(总值)指数、02 沪深300、03 中债-新综合财富(1-3年)指数、04 中证500、05 银行一年定期存款利率、06 7日通知存款利率
     * @return 数据类型：01 中债-新综合全价(总值)指数、02 沪深300、03 中债-新综合财富(1-3年)指数、04 中证500、05 银行一年定期存款利率、06 7日通知存款利率
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 数据类型：01 中债-新综合全价(总值)指数、02 沪深300、03 中债-新综合财富(1-3年)指数、04 中证500、05 银行一年定期存款利率、06 7日通知存款利率
     * @param dataType 数据类型：01 中债-新综合全价(总值)指数、02 沪深300、03 中债-新综合财富(1-3年)指数、04 中证500、05 银行一年定期存款利率、06 7日通知存款利率
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}