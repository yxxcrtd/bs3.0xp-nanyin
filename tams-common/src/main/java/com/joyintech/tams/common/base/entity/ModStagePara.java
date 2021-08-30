package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ModStagePara extends BaseEntity {
    /**
     * 序列号
     */
    private String seqno;

    /**
     * 资产所属阶段(数据字典 减值阶段)
     */
    private String stageOfAsset;

    /**
     * 要素绝对性(数据字典 要素绝对性)
     */
    private String absoluteOfElements;

    /**
     * 要素(数据字典 减值要素)
     */
    private String elements;

    /**
     * 要素值
     */
    private String elementsValue;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序列号
     * @param seqno 序列号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 资产所属阶段(数据字典 减值阶段)
     * @return 资产所属阶段(数据字典 减值阶段)
     */
    public String getStageOfAsset() {
        return stageOfAsset;
    }

    /**
     * 设置 资产所属阶段(数据字典 减值阶段)
     * @param stageOfAsset 资产所属阶段(数据字典 减值阶段)
     */
    public void setStageOfAsset(String stageOfAsset) {
        this.stageOfAsset = stageOfAsset == null ? null : stageOfAsset.trim();
    }

    /**
     * 取得 要素绝对性(数据字典 要素绝对性)
     * @return 要素绝对性(数据字典 要素绝对性)
     */
    public String getAbsoluteOfElements() {
        return absoluteOfElements;
    }

    /**
     * 设置 要素绝对性(数据字典 要素绝对性)
     * @param absoluteOfElements 要素绝对性(数据字典 要素绝对性)
     */
    public void setAbsoluteOfElements(String absoluteOfElements) {
        this.absoluteOfElements = absoluteOfElements == null ? null : absoluteOfElements.trim();
    }

    /**
     * 取得 要素(数据字典 减值要素)
     * @return 要素(数据字典 减值要素)
     */
    public String getElements() {
        return elements;
    }

    /**
     * 设置 要素(数据字典 减值要素)
     * @param elements 要素(数据字典 减值要素)
     */
    public void setElements(String elements) {
        this.elements = elements == null ? null : elements.trim();
    }

    /**
     * 取得 要素值
     * @return 要素值
     */
    public String getElementsValue() {
        return elementsValue;
    }

    /**
     * 设置 要素值
     * @param elementsValue 要素值
     */
    public void setElementsValue(String elementsValue) {
        this.elementsValue = elementsValue == null ? null : elementsValue.trim();
    }
}