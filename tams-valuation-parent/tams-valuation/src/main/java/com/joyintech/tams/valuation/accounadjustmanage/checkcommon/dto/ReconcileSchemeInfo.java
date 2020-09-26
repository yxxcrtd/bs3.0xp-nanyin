package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

public class ReconcileSchemeInfo extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 估值表类型
     */
    private String schemeType;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 是否默认方案 Y：是 N：否
     */
    private String isdefaule;

    /**
     * 对账误差设置
     */
    private BigDecimal errorNumber;

    /**
     * 本方数据起始行
     */
    private Long myStartRowNo;

    /**
     * 对方数据起始行
     */
    private Long othersideStartRowNo;

    /**
     * 本方数据结束行
     */
    private Long myEndRowNo;

    /**
     * 对方数据结束行
     */
    private Long othersideEndRowNo;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 方案名称
     * 
     * @return 方案名称
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 设置 方案名称
     * 
     * @param schemeName 方案名称
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    /**
     * 取得 方案类型
     * 
     * @return 方案类型
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 对账类型 GZB：估值表 YEB：余额表
     * 
     * @param schemeType 对账类型 GZB：估值表 YEB：余额表
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 是否默认方案 Y：是 N：否
     * 
     * @return 是否默认方案 Y：是 N：否
     */
    public String getIsdefaule() {
        return isdefaule;
    }

    /**
     * 设置 是否默认方案 Y：是 N：否
     * 
     * @param isdefaule 是否默认方案 Y：是 N：否
     */
    public void setIsdefaule(String isdefaule) {
        this.isdefaule = isdefaule == null ? null : isdefaule.trim();
    }

    /**
     * 取得 对账误差设置
     * 
     * @return 对账误差设置
     */
    public BigDecimal getErrorNumber() {
        return errorNumber;
    }

    /**
     * 设置 对账误差设置
     * 
     * @param errorNumber 对账误差设置
     */
    public void setErrorNumber(BigDecimal errorNumber) {
        this.errorNumber = errorNumber;
    }

    /**
     * 取得 本方数据起始行
     * 
     * @return 本方数据起始行
     */
    public Long getMyStartRowNo() {
        return myStartRowNo;
    }

    /**
     * 设置 本方数据起始行
     * 
     * @param myStartRowNo 本方数据起始行
     */
    public void setMyStartRowNo(Long myStartRowNo) {
        this.myStartRowNo = myStartRowNo;
    }

    /**
     * 取得 对方数据起始行
     * 
     * @return 对方数据起始行
     */
    public Long getOthersideStartRowNo() {
        return othersideStartRowNo;
    }

    /**
     * 设置 对方数据起始行
     * 
     * @param othersideStartRowNo 对方数据起始行
     */
    public void setOthersideStartRowNo(Long othersideStartRowNo) {
        this.othersideStartRowNo = othersideStartRowNo;
    }

    /**
     * 取得 本方数据结束行
     * 
     * @return 本方数据结束行
     */
    public Long getMyEndRowNo() {
        return myEndRowNo;
    }

    /**
     * 设置 本方数据结束行
     * 
     * @param myEndRowNo 本方数据结束行
     */
    public void setMyEndRowNo(Long myEndRowNo) {
        this.myEndRowNo = myEndRowNo;
    }

    /**
     * 取得 对方数据结束行
     * 
     * @return 对方数据结束行
     */
    public Long getOthersideEndRowNo() {
        return othersideEndRowNo;
    }

    /**
     * 设置 对方数据结束行
     * 
     * @param othersideEndRowNo 对方数据结束行
     */
    public void setOthersideEndRowNo(Long othersideEndRowNo) {
        this.othersideEndRowNo = othersideEndRowNo;
    }
}