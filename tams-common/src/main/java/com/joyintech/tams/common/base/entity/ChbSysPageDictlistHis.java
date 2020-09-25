package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPageDictlistHis extends BaseEntity {
    /**
     * ����
     */
    private String pageId;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * ������
     */
    private String dictId;

    /**
     * �������
     */
    private String dictItemVal;

    /**
     * 取得 ����
     * @return ����
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 ����
     * @param pageId ����
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }

    /**
     * 取得 ������
     * @return ������
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * 设置 ������
     * @param dictId ������
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    /**
     * 取得 �������
     * @return �������
     */
    public String getDictItemVal() {
        return dictItemVal;
    }

    /**
     * 设置 �������
     * @param dictItemVal �������
     */
    public void setDictItemVal(String dictItemVal) {
        this.dictItemVal = dictItemVal == null ? null : dictItemVal.trim();
    }
}