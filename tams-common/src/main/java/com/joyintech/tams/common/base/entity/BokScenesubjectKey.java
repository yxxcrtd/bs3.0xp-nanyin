package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokScenesubjectKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 场景分支节点
     */
    private String sceneNodeId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 余额方向
     */
    private String cdFlag;

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
     * 取得 场景分支节点
     * @return 场景分支节点
     */
    public String getSceneNodeId() {
        return sceneNodeId;
    }

    /**
     * 设置 场景分支节点
     * @param sceneNodeId 场景分支节点
     */
    public void setSceneNodeId(String sceneNodeId) {
        this.sceneNodeId = sceneNodeId == null ? null : sceneNodeId.trim();
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 余额方向
     * @return 余额方向
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 余额方向
     * @param cdFlag 余额方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag == null ? null : cdFlag.trim();
    }
}