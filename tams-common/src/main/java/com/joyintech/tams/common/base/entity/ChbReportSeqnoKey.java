package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbReportSeqnoKey extends BaseEntity {
    /**
     * 报送日期
     */
    private Date sendDate;

    /**
     * 登记类型代码 参照数据字典
     */
    private String reportTypeCd;

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 设置 报送日期
     * @param sendDate 报送日期
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 取得 登记类型代码 参照数据字典
     * @return 登记类型代码 参照数据字典
     */
    public String getReportTypeCd() {
        return reportTypeCd;
    }

    /**
     * 设置 登记类型代码 参照数据字典
     * @param reportTypeCd 登记类型代码 参照数据字典
     */
    public void setReportTypeCd(String reportTypeCd) {
        this.reportTypeCd = reportTypeCd == null ? null : reportTypeCd.trim();
    }
}