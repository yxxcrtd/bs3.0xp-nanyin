package com.joyintech.invest.interf.sample.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 示例 DTO
 */
public class SampleDTO extends BaseEntity {
    /**
     * 示例主键
     */
    private  String sampleBusiId;


    /**
     * 示例内容
     */
    private String sampleText;

    public String getSampleBusiId() {
        return sampleBusiId;
    }

    public void setSampleBusiId(String sampleBusiId) {
        this.sampleBusiId = sampleBusiId;
    }

    public String getSampleText() {
        return sampleText;
    }

    public void setSampleText(String sampleText) {
        this.sampleText = sampleText;
    }
}
