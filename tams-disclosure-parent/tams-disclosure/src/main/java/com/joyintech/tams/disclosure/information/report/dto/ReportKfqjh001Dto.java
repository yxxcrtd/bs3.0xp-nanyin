
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品开放期公告/报告-开放计划
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportKfqjh001Dto
 * @since 1.0
 */
public class ReportKfqjh001Dto extends BaseEntity {
    private String openVDate;
    private String openEDate;
    private String collincoffPeriod;
    private String confirmDate;

    public String getOpenVDate() {
        return openVDate;
    }

    public void setOpenVDate(String openVDate) {
        this.openVDate = openVDate;
    }

    public String getOpenEDate() {
        return openEDate;
    }

    public void setOpenEDate(String openEDate) {
        this.openEDate = openEDate;
    }

    public String getCollincoffPeriod() {
        return collincoffPeriod;
    }

    public void setCollincoffPeriod(String collincoffPeriod) {
        this.collincoffPeriod = collincoffPeriod;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }
}
