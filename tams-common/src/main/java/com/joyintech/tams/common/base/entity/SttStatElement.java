package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class SttStatElement extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 对象代码
     */
    private String objectId;

    /**
     * 元素代码
     */
    private String elementId;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券管理账户/通道代码
     */
    private String secAcctId;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 对象代码
     * @return 对象代码
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象代码
     * @param objectId 对象代码
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 元素代码
     * @return 元素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 元素代码
     * @param elementId 元素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
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

    /**
     * 取得 证券管理账户/通道代码
     * @return 证券管理账户/通道代码
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券管理账户/通道代码
     * @param secAcctId 证券管理账户/通道代码
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 参数类型
     * @return 参数类型
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 设置 参数类型
     * @param paramType 参数类型
     */
    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 字典代码
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }
}