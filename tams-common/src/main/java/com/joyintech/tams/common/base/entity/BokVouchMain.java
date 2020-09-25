package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokVouchMain extends BaseEntity {
    /**
     * 凭证编号
     */
    private String vouchNum;

    /**
     * 凭证编号
     */
    private String busVouchnum;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 日期
     */
    private Date bookDate;

    /**
     * 合并场景代码
     */
    private String comTableId;

    /**
     * 凭证说明
     */
    private String vouchRemark;

    /**
     * 冲正标识
     */
    private String offsetFlag;

    /**
     * 年份
     */
    private Long vouchYear;

    /**
     * 月份
     */
    private Long vouchMonth;

    /**
     * 编号位数
     */
    private Long num;

    /**
     * 凭证类型
     */
    private String bookType;

    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 凭证编号
     * @param vouchNum 凭证编号
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum == null ? null : vouchNum.trim();
    }

    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getBusVouchnum() {
        return busVouchnum;
    }

    /**
     * 设置 凭证编号
     * @param busVouchnum 凭证编号
     */
    public void setBusVouchnum(String busVouchnum) {
        this.busVouchnum = busVouchnum == null ? null : busVouchnum.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 日期
     * @param bookDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 合并场景代码
     * @return 合并场景代码
     */
    public String getComTableId() {
        return comTableId;
    }

    /**
     * 设置 合并场景代码
     * @param comTableId 合并场景代码
     */
    public void setComTableId(String comTableId) {
        this.comTableId = comTableId == null ? null : comTableId.trim();
    }

    /**
     * 取得 凭证说明
     * @return 凭证说明
     */
    public String getVouchRemark() {
        return vouchRemark;
    }

    /**
     * 设置 凭证说明
     * @param vouchRemark 凭证说明
     */
    public void setVouchRemark(String vouchRemark) {
        this.vouchRemark = vouchRemark == null ? null : vouchRemark.trim();
    }

    /**
     * 取得 冲正标识
     * @return 冲正标识
     */
    public String getOffsetFlag() {
        return offsetFlag;
    }

    /**
     * 设置 冲正标识
     * @param offsetFlag 冲正标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag == null ? null : offsetFlag.trim();
    }

    /**
     * 取得 年份
     * @return 年份
     */
    public Long getVouchYear() {
        return vouchYear;
    }

    /**
     * 设置 年份
     * @param vouchYear 年份
     */
    public void setVouchYear(Long vouchYear) {
        this.vouchYear = vouchYear;
    }

    /**
     * 取得 月份
     * @return 月份
     */
    public Long getVouchMonth() {
        return vouchMonth;
    }

    /**
     * 设置 月份
     * @param vouchMonth 月份
     */
    public void setVouchMonth(Long vouchMonth) {
        this.vouchMonth = vouchMonth;
    }

    /**
     * 取得 编号位数
     * @return 编号位数
     */
    public Long getNum() {
        return num;
    }

    /**
     * 设置 编号位数
     * @param num 编号位数
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * 取得 凭证类型
     * @return 凭证类型
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置 凭证类型
     * @param bookType 凭证类型
     */
    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }
}