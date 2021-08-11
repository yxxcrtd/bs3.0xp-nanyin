/*
 * 文件名：ValBondValuationInfo003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @Description:减值PD计算批处理-process计算变量dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param:
 * @return:
 */
public class VariablePdCalculateDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    public static final String YEAR_TYPE = "1"; //年份
    public static final String YEARMON_TYPE = "2";//年月

    public static final String TRUE_STR = "true";

    public static final String USEPROCESS_PD="04";

    /**
     * [PD计算过程DTO]列表
     */
    private List<ModEclResultProcess> modEclResultProcessResultList;

    /**
     * 交易数据列表
     */
    List<ModEclResultProcess> assetImpairmentPdCalculateList;

    /**
     * 季度列表List
     */
    private List<String> quarterList;


    /**
     * 评级类型
     */
    private String oType;
    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 主体是否持有金融牌照
     */
    private String isFinLicence;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 资产到期日所在年月
     */
    private String mdateYearMonStr;

    /**
     * 当前年月
     */
    private String nowYearMonStr;

    /**
     * 12个月后所在年月
     */
    private String endYearMonStr;

    /**
     * 资产是否有交易处于二三阶段 two three
     */
    private Boolean ttStage;

    /**
     * 资产剩余期限是否不足一年
     */
    private Boolean  maturityYear;

    /**
     * 第12个月累积加权PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal twelveMonWeightedPd;

    /**
     * 到期日累积加权PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mdateWeightedPd;


    /**
     * 前瞻前第12个月累积加权PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preTwelveMonWeightedPd;

    /**
     * 前瞻前到期日累积加权PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preMdateWeightedPd;

    /**
     * 年份列表List
     */
    private List<String> yearList;

    /**
     * [PD计算过程DTO]列表
     */
    List<ModGradePdConfigDto> modGradePdConfigList;

    /**
     * 配置使用情况DTO列表
     */
    List<ModGradePdConfigDto> modGradePdConfigUsedList;

    /**
     * 年月列表List
     */
    private List<String> yearMonList;


    /**
     * 前瞻前累计PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preCdfPd;

    /**
     * 乐观累计PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal optimisticCdfPd;

    /**
     * 基础累计PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal basicCdfPd;

    /**
     * 悲观累计PD
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pessimismCdfPd;


    public BigDecimal getPreCdfPd() {
        return preCdfPd;
    }

    public void setPreCdfPd(BigDecimal preCdfPd) {
        this.preCdfPd = preCdfPd;
    }

    public BigDecimal getOptimisticCdfPd() {
        return optimisticCdfPd;
    }

    public void setOptimisticCdfPd(BigDecimal optimisticCdfPd) {
        this.optimisticCdfPd = optimisticCdfPd;
    }

    public BigDecimal getBasicCdfPd() {
        return basicCdfPd;
    }

    public void setBasicCdfPd(BigDecimal basicCdfPd) {
        this.basicCdfPd = basicCdfPd;
    }

    public BigDecimal getPessimismCdfPd() {
        return pessimismCdfPd;
    }

    public void setPessimismCdfPd(BigDecimal pessimismCdfPd) {
        this.pessimismCdfPd = pessimismCdfPd;
    }

    public List<ModEclResultProcess> getModEclResultProcessResultList() {
        return modEclResultProcessResultList;
    }

    public void setModEclResultProcessResultList(List<ModEclResultProcess> modEclResultProcessResultList) {
        this.modEclResultProcessResultList = modEclResultProcessResultList;
    }

    public List<ModEclResultProcess> getAssetImpairmentPdCalculateList() {
        return assetImpairmentPdCalculateList;
    }

    public void setAssetImpairmentPdCalculateList(List<ModEclResultProcess> assetImpairmentPdCalculateList) {
        this.assetImpairmentPdCalculateList = assetImpairmentPdCalculateList;
    }

    public List<String> getQuarterList() {
        return quarterList;
    }

    public void setQuarterList(List<String> quarterList) {
        this.quarterList = quarterList;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public String getIsFinLicence() {
        return isFinLicence;
    }

    public void setIsFinLicence(String isFinLicence) {
        this.isFinLicence = isFinLicence;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getMdateYearMonStr() {
        return mdateYearMonStr;
    }

    public void setMdateYearMonStr(String mdateYearMonStr) {
        this.mdateYearMonStr = mdateYearMonStr;
    }

    public String getNowYearMonStr() {
        return nowYearMonStr;
    }

    public void setNowYearMonStr(String nowYearMonStr) {
        this.nowYearMonStr = nowYearMonStr;
    }

    public String getEndYearMonStr() {
        return endYearMonStr;
    }

    public void setEndYearMonStr(String endYearMonStr) {
        this.endYearMonStr = endYearMonStr;
    }


    public Boolean getTtStage() {
        return ttStage;
    }

    public void setTtStage(Boolean ttStage) {
        this.ttStage = ttStage;
    }

    public Boolean getMaturityYear() {
        return maturityYear;
    }

    public void setMaturityYear(Boolean maturityYear) {
        this.maturityYear = maturityYear;
    }

    public BigDecimal getTwelveMonWeightedPd() {
        return twelveMonWeightedPd;
    }

    public void setTwelveMonWeightedPd(BigDecimal twelveMonWeightedPd) {
        this.twelveMonWeightedPd = twelveMonWeightedPd;
    }

    public BigDecimal getMdateWeightedPd() {
        return mdateWeightedPd;
    }

    public void setMdateWeightedPd(BigDecimal mdateWeightedPd) {
        this.mdateWeightedPd = mdateWeightedPd;
    }

    public BigDecimal getPreTwelveMonWeightedPd() {
        return preTwelveMonWeightedPd;
    }

    public void setPreTwelveMonWeightedPd(BigDecimal preTwelveMonWeightedPd) {
        this.preTwelveMonWeightedPd = preTwelveMonWeightedPd;
    }

    public BigDecimal getPreMdateWeightedPd() {
        return preMdateWeightedPd;
    }

    public void setPreMdateWeightedPd(BigDecimal preMdateWeightedPd) {
        this.preMdateWeightedPd = preMdateWeightedPd;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<ModGradePdConfigDto> getModGradePdConfigList() {
        return modGradePdConfigList;
    }

    public void setModGradePdConfigList(List<ModGradePdConfigDto> modGradePdConfigList) {
        this.modGradePdConfigList = modGradePdConfigList;
    }

    public List<ModGradePdConfigDto> getModGradePdConfigUsedList() {
        return modGradePdConfigUsedList;
    }

    public void setModGradePdConfigUsedList(List<ModGradePdConfigDto> modGradePdConfigUsedList) {
        this.modGradePdConfigUsedList = modGradePdConfigUsedList;
    }

    public List<String> getYearMonList() {
        return yearMonList;
    }

    public void setYearMonList(List<String> yearMonList) {
        this.yearMonList = yearMonList;
    }
}
