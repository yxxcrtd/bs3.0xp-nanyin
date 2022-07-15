
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品开放期公告/报告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportKfqgg001Dto
 * @since 1.0
 */
public class ReportKfqgg001Dto extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 公告年度
     */
    private String announceYear;

    /**
     * 公告月
     */
    private String announceMonth;

    /**
     * 公告日
     */
    private String announceDay;

    /**
     *
     * @return
     */
    private String pProdId;

    public String getpProdId() {
        return pProdId;
    }

    public void setpProdId(String pProdId) {
        this.pProdId = pProdId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAnnounceYear() {
        return announceYear;
    }

    public void setAnnounceYear(String announceYear) {
        this.announceYear = announceYear;
    }

    public String getAnnounceMonth() {
        return announceMonth;
    }

    public void setAnnounceMonth(String announceMonth) {
        this.announceMonth = announceMonth;
    }

    public String getAnnounceDay() {
        return announceDay;
    }

    public void setAnnounceDay(String announceDay) {
        this.announceDay = announceDay;
    }
}
