package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfPayhsrcsecratingKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secid;

    /**
     * 评级机构
     */
    private String ratingorg;

    /**
     * 评级类型
     */
    private String ratingtype;

    /**
     * 长期短期 
     */
    private String ratingsubtype;

    /**
     * 发布日期
     */
    private Date pubdate;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 债券ID：xxxx.IB/xxxx.SZ
     * @return 债券ID：xxxx.IB/xxxx.SZ
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券ID：xxxx.IB/xxxx.SZ
     * @param secid 债券ID：xxxx.IB/xxxx.SZ
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 评级机构
     * @return 评级机构
     */
    public String getRatingorg() {
        return ratingorg;
    }

    /**
     * 设置 评级机构
     * @param ratingorg 评级机构
     */
    public void setRatingorg(String ratingorg) {
        this.ratingorg = ratingorg == null ? null : ratingorg.trim();
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getRatingtype() {
        return ratingtype;
    }

    /**
     * 设置 评级类型
     * @param ratingtype 评级类型
     */
    public void setRatingtype(String ratingtype) {
        this.ratingtype = ratingtype == null ? null : ratingtype.trim();
    }

    /**
     * 取得 长期短期 
     * @return 长期短期 
     */
    public String getRatingsubtype() {
        return ratingsubtype;
    }

    /**
     * 设置 长期短期 
     * @param ratingsubtype 长期短期 
     */
    public void setRatingsubtype(String ratingsubtype) {
        this.ratingsubtype = ratingsubtype == null ? null : ratingsubtype.trim();
    }

    /**
     * 取得 发布日期
     * @return 发布日期
     */
    public Date getPubdate() {
        return pubdate;
    }

    /**
     * 设置 发布日期
     * @param pubdate 发布日期
     */
    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}