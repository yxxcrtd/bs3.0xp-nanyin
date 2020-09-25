package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TacticConfigOptDetail extends TacticConfigOptDetailKey {
    /**
     * 理论配置比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoConfigRate;

    /**
     * 样本平均收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal samleAverRate;

    /**
     * 波动率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vix;

    /**
     * 修正比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRate;

    /**
     * 修正场景
     */
    private String corScene;

    /**
     * 修正后占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRatio;

    /**
     * 修正观点
     */
    private String corView;

    /**
     * 取得 理论配置比例
     * @return 理论配置比例
     */
    public BigDecimal getTheoConfigRate() {
        return theoConfigRate;
    }

    /**
     * 设置 理论配置比例
     * @param theoConfigRate 理论配置比例
     */
    public void setTheoConfigRate(BigDecimal theoConfigRate) {
        this.theoConfigRate = theoConfigRate;
    }

    /**
     * 取得 样本平均收益率
     * @return 样本平均收益率
     */
    public BigDecimal getSamleAverRate() {
        return samleAverRate;
    }

    /**
     * 设置 样本平均收益率
     * @param samleAverRate 样本平均收益率
     */
    public void setSamleAverRate(BigDecimal samleAverRate) {
        this.samleAverRate = samleAverRate;
    }

    /**
     * 取得 波动率
     * @return 波动率
     */
    public BigDecimal getVix() {
        return vix;
    }

    /**
     * 设置 波动率
     * @param vix 波动率
     */
    public void setVix(BigDecimal vix) {
        this.vix = vix;
    }

    /**
     * 取得 修正比例
     * @return 修正比例
     */
    public BigDecimal getCorRate() {
        return corRate;
    }

    /**
     * 设置 修正比例
     * @param corRate 修正比例
     */
    public void setCorRate(BigDecimal corRate) {
        this.corRate = corRate;
    }

    /**
     * 取得 修正场景
     * @return 修正场景
     */
    public String getCorScene() {
        return corScene;
    }

    /**
     * 设置 修正场景
     * @param corScene 修正场景
     */
    public void setCorScene(String corScene) {
        this.corScene = corScene == null ? null : corScene.trim();
    }

    /**
     * 取得 修正后占比
     * @return 修正后占比
     */
    public BigDecimal getCorRatio() {
        return corRatio;
    }

    /**
     * 设置 修正后占比
     * @param corRatio 修正后占比
     */
    public void setCorRatio(BigDecimal corRatio) {
        this.corRatio = corRatio;
    }

    /**
     * 取得 修正观点
     * @return 修正观点
     */
    public String getCorView() {
        return corView;
    }

    /**
     * 设置 修正观点
     * @param corView 修正观点
     */
    public void setCorView(String corView) {
        this.corView = corView == null ? null : corView.trim();
    }
}