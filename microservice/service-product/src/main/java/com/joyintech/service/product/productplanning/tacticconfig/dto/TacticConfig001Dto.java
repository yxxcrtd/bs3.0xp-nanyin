package com.joyintech.service.product.productplanning.tacticconfig.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@PageId("TacticConfig001")
public class TacticConfig001Dto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 战术配置编号
     */
    private String tacticConfigCode;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 结束日
     */
    private Date edate;

    /**
     * 投研支持观点
     */
    private String opinionRinv;

    /**
     * 备注
     */
    private String remark;
    
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
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 战术配置编号
     * @return 战术配置编号
     */
    public String getTacticConfigCode() {
        return tacticConfigCode;
    }

    /**
     * 设置 战术配置编号
     * @param tacticConfigCode 战术配置编号
     */
    public void setTacticConfigCode(String tacticConfigCode) {
        this.tacticConfigCode = tacticConfigCode == null ? null : tacticConfigCode.trim();
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * 设置 结束日
     * @param edate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 取得 投研支持观点
     * @return 投研支持观点
     */
    public String getOpinionRinv() {
        return opinionRinv;
    }

    /**
     * 设置 投研支持观点
     * @param opinionRinv 投研支持观点
     */
    public void setOpinionRinv(String opinionRinv) {
        this.opinionRinv = opinionRinv == null ? null : opinionRinv.trim();
    }
    
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
    
    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
    
    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
