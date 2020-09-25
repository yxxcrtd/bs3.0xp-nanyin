/*
 * 文件名：SurplusDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

import java.util.Map;

/**
 * 一轮对比后，剩余的数据，用于后续判断
 * 
 * @author yangjian
 * @version 1.0 2019年1月8日
 * @see SurplusDataDto
 * @since 1.0
 */
public class SurplusDataDto {

    /**
     * 科目id
     */
    private String subjectId;

    /**
     * 顺序
     */
    private Long rowNo;

    /**
     * 3级科目id
     */
    private String threeSubject;

    /**
     * 我方资产id
     */
    private String myAssetId;

    /**
     * 映射资产id
     */
    private String mappingAssetId;

    /**
     * 科目性质
     */
    private String nature;

    /**
     * excel数据
     */
    private Map<String, String> excelData;

    /**
     * 获取 subjectId
     * 
     * @return subjectId.
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 subjectId
     * 
     * @param subjectId subjectId
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取 rowNo
     * 
     * @return rowNo.
     */
    public Long getRowNo() {
        return rowNo;
    }

    /**
     * 设置 rowNo
     * 
     * @param rowNo rowNo
     */
    public void setRowNo(Long rowNo) {
        this.rowNo = rowNo;
    }

    /**
     * 获取 excelData
     * 
     * @return excelData.
     */
    public Map<String, String> getExcelData() {
        return excelData;
    }

    /**
     * 设置 excelData
     * 
     * @param excelData excelData
     */
    public void setExcelData(Map<String, String> excelData) {
        this.excelData = excelData;
    }

    /**
     * 获取 threeSubject
     * 
     * @return threeSubject.
     */
    public String getThreeSubject() {
        return threeSubject;
    }

    /**
     * 设置 threeSubject
     * 
     * @param threeSubject threeSubject
     */
    public void setThreeSubject(String threeSubject) {
        this.threeSubject = threeSubject;
    }

    /**
     * 获取 myAssetId
     * 
     * @return myAssetId.
     */
    public String getMyAssetId() {
        return myAssetId;
    }

    /**
     * 设置 myAssetId
     * 
     * @param myAssetId myAssetId
     */
    public void setMyAssetId(String myAssetId) {
        this.myAssetId = myAssetId;
    }

    /**
     * 获取 mappingAssetId
     * 
     * @return mappingAssetId.
     */
    public String getMappingAssetId() {
        return mappingAssetId;
    }

    /**
     * 设置 mappingAssetId
     * 
     * @param mappingAssetId mappingAssetId
     */
    public void setMappingAssetId(String mappingAssetId) {
        this.mappingAssetId = mappingAssetId;
    }

    /**
     * 获取 nature
     * 
     * @return nature.
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置 nature
     * 
     * @param nature nature
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

}
