package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class TrdVouchDeal extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 分录类型
     */
    private String recordType;

    /**
     * 记账日期
     */
    private Date bookDate;

    /**
     * 冲销标识
     */
    private String offsetFlag;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 凭证类型
     */
    private String bookType;

    /**
     * 摘要
     */
    private String bookRemark;

    /**
     * 原始交易编号
     */
    private String pTradeId;

    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 记账顺序
     */
    private String accountOrder;

    /**
     * 业务类型--仅供查询使用
     */
    private String finprodType;

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
     * 取得 分录类型
     * @return 分录类型
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置 分录类型
     * @param recordType 分录类型
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    /**
     * 取得 记账日期
     * @return 记账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 记账日期
     * @param bookDate 记账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 冲销标识
     * @return 冲销标识
     */
    public String getOffsetFlag() {
        return offsetFlag;
    }

    /**
     * 设置 冲销标识
     * @param offsetFlag 冲销标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag == null ? null : offsetFlag.trim();
    }

    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 凭证编号
     * @param bookId 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
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

    /**
     * 取得 摘要
     * @return 摘要
     */
    public String getBookRemark() {
        return bookRemark;
    }

    /**
     * 设置 摘要
     * @param bookRemark 摘要
     */
    public void setBookRemark(String bookRemark) {
        this.bookRemark = bookRemark == null ? null : bookRemark.trim();
    }

    /**
     * 取得 原始交易编号
     * @return 原始交易编号
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 原始交易编号
     * @param pTradeId 原始交易编号
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId == null ? null : pTradeId.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }
    public String getAccountOrder() {
		return accountOrder;
	}

	public void setAccountOrder(String accountOrder) {
		this.accountOrder = accountOrder;
	}

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
}