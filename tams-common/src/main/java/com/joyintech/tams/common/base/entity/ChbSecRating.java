package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_SECRATING表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-02-18
 */
public class ChbSecRating extends BaseEntity {
    /**
     * 流水号
     */
    private String secratingid;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String registBankCode;

    /**
     * 评级类型：C主体评级 I债项评级
     */
    private String ratingtype;

    /**
     * 长期短期：S:短期 L:长期
     */
    private String ratingsubtype;

    /**
     * 评级来源
     */
    private String ratingsrc;

    /**
     * 评级机构
     */
    private String ratingorg;

    /**
     * 评级结果
     */
    private String rating;

    /**
     * 发布日期
     */
    private Date pubdate;

    /**
     * 数据状态  D	删除 A	待处理 E	有效
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 流水号
     * @return SECRATINGID 流水号
     */
    public String getSecratingid() {
        return secratingid;
    }

    /**
     * 流水号
     * @param secratingid 流水号
     */
    public void setSecratingid(String secratingid) {
        this.secratingid = secratingid == null ? null : secratingid.trim();
    }

    /**
     * 行内资产/负债编码
     * @return ASSET_CODE 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 发行机构代码
     * @return REGIST_BANK_CODE 发行机构代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 发行机构代码
     * @param registBankCode 发行机构代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 评级类型：C主体评级 I债项评级
     * @return RATINGTYPE 评级类型：C主体评级 I债项评级
     */
    public String getRatingtype() {
        return ratingtype;
    }

    /**
     * 评级类型：C主体评级 I债项评级
     * @param ratingtype 评级类型：C主体评级 I债项评级
     */
    public void setRatingtype(String ratingtype) {
        this.ratingtype = ratingtype == null ? null : ratingtype.trim();
    }

    /**
     * 长期短期：S:短期 L:长期
     * @return RATINGSUBTYPE 长期短期：S:短期 L:长期
     */
    public String getRatingsubtype() {
        return ratingsubtype;
    }

    /**
     * 长期短期：S:短期 L:长期
     * @param ratingsubtype 长期短期：S:短期 L:长期
     */
    public void setRatingsubtype(String ratingsubtype) {
        this.ratingsubtype = ratingsubtype == null ? null : ratingsubtype.trim();
    }

    /**
     * 评级来源
     * @return RATINGSRC 评级来源
     */
    public String getRatingsrc() {
        return ratingsrc;
    }

    /**
     * 评级来源
     * @param ratingsrc 评级来源
     */
    public void setRatingsrc(String ratingsrc) {
        this.ratingsrc = ratingsrc == null ? null : ratingsrc.trim();
    }

    /**
     * 评级机构
     * @return RATINGORG 评级机构
     */
    public String getRatingorg() {
        return ratingorg;
    }

    /**
     * 评级机构
     * @param ratingorg 评级机构
     */
    public void setRatingorg(String ratingorg) {
        this.ratingorg = ratingorg == null ? null : ratingorg.trim();
    }

    /**
     * 评级结果
     * @return RATING 评级结果
     */
    public String getRating() {
        return rating;
    }

    /**
     * 评级结果
     * @param rating 评级结果
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    /**
     * 发布日期
     * @return PUBDATE 发布日期
     */
    public Date getPubdate() {
        return pubdate;
    }

    /**
     * 发布日期
     * @param pubdate 发布日期
     */
    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * 数据状态  D	删除 A	待处理 E	有效
     * @return STATUS 数据状态  D	删除 A	待处理 E	有效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态  D	删除 A	待处理 E	有效
     * @param status 数据状态  D	删除 A	待处理 E	有效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}