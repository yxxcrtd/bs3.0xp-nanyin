package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

public class TopInvestorInformation extends BaseEntity {
    /**
     * 业务主键
     */
    private String id;

    /**
     * 产品主代码
     */
    private String booksetId;

    /**
     * 年度
     */
    private String indexYear;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 排名
     */
    private String tzRank;

    /**
     * 类别
     */
    private String tzType;

    /**
     * 持有份额(份)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdShare;

    /**
     * 占总份额比例（%）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal proportionShare;

    /**
     * 取得 业务主键
     * @return 业务主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 业务主键
     * @param id 业务主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 产品主代码
     * @return 产品主代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 产品主代码
     * @param booksetId 产品主代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 年度
     * @return 年度
     */
    public String getIndexYear() {
        return indexYear;
    }

    /**
     * 设置 年度
     * @param indexYear 年度
     */
    public void setIndexYear(String indexYear) {
        this.indexYear = indexYear == null ? null : indexYear.trim();
    }

    /**
     * 取得 指标类型
     * @return 指标类型
     */
    public String getIndexType() {
        return indexType;
    }

    /**
     * 设置 指标类型
     * @param indexType 指标类型
     */
    public void setIndexType(String indexType) {
        this.indexType = indexType == null ? null : indexType.trim();
    }

    /**
     * 取得 排名
     * @return 排名
     */
    public String getTzRank() {
        return tzRank;
    }

    /**
     * 设置 排名
     * @param tzRank 排名
     */
    public void setTzRank(String tzRank) {
        this.tzRank = tzRank == null ? null : tzRank.trim();
    }

    /**
     * 取得 类别
     * @return 类别
     */
    public String getTzType() {
        return tzType;
    }

    /**
     * 设置 类别
     * @param tzType 类别
     */
    public void setTzType(String tzType) {
        this.tzType = tzType == null ? null : tzType.trim();
    }

    /**
     * 取得 持有份额(份)
     * @return 持有份额(份)
     */
    public BigDecimal getHoldShare() {
        return holdShare;
    }

    /**
     * 设置 持有份额(份)
     * @param holdShare 持有份额(份)
     */
    public void setHoldShare(BigDecimal holdShare) {
        this.holdShare = holdShare;
    }

    /**
     * 取得 占总份额比例（%）
     * @return 占总份额比例（%）
     */
    public BigDecimal getProportionShare() {
        return proportionShare;
    }

    /**
     * 设置 占总份额比例（%）
     * @param proportionShare 占总份额比例（%）
     */
    public void setProportionShare(BigDecimal proportionShare) {
        this.proportionShare = proportionShare;
    }

    public String getTzTypeStr(){
        if(StringUtil.isNotEmpty(this.getTzType())){
            return SysDataUtil.getNameFromDataDict(DictCode.前十大投资者信息类别,this.getTzType());
        }else{
            return null;
        }
    }
}