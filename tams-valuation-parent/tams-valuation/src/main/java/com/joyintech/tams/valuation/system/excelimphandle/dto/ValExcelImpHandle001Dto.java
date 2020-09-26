/*
 * 文件名：ValExcelImpHandle001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 导入管理Dto
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelImpHandle001Dto
 * @since 1.0
 */
public class ValExcelImpHandle001Dto extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /**
     * 导入文件名
     */
    private String impFileName;

    /**
     * 导入文件sheet名
     */
    private String impFileSheetName;

    /**
     * 导入文件时间戳
     */
    private String impFileTime;

    /**
     * excel导入文件中所有sheet信息
     */
    private List<ValExcelImpFileSheetInfoDto> sheetInfoList;

    /**
     * 导入文件数据列表
     */
    private List<List<String>> fileDataList;

    /**
     * excel导入文件信息列表
     */
    private List<ValExcelImpFileInfoDto> impFileInfoList;

    /**
     * 获取 impFileName
     *
     * @return impFileName.
     */
    public String getImpFileName() {
        return impFileName;
    }

    /**
     * 设置 impFileName
     *
     * @param impFileName impFileName
     */
    public void setImpFileName(String impFileName) {
        this.impFileName = impFileName;
    }

    /**
     * 获取 impFileTime
     *
     * @return impFileTime.
     */
    public String getImpFileTime() {
        return impFileTime;
    }

    /**
     * 设置 impFileTime
     *
     * @param impFileTime impFileTime
     */
    public void setImpFileTime(String impFileTime) {
        this.impFileTime = impFileTime;
    }

    /**
     * 获取 fileDataList
     *
     * @return fileDataList.
     */
    public List<List<String>> getFileDataList() {
        return fileDataList;
    }

    /**
     * 设置 fileDataList
     *
     * @param fileDataList fileDataList
     */
    public void setFileDataList(List<List<String>> fileDataList) {
        this.fileDataList = fileDataList;
    }

    /**
     * 获取 impFileInfoList
     *
     * @return impFileInfoList.
     */
    public List<ValExcelImpFileInfoDto> getImpFileInfoList() {
        return impFileInfoList;
    }

    /**
     * 设置 impFileInfoList
     *
     * @param impFileInfoList impFileInfoList
     */
    public void setImpFileInfoList(List<ValExcelImpFileInfoDto> impFileInfoList) {
        this.impFileInfoList = impFileInfoList;
    }

    /**
     * 获取 impFileSheetName
     *
     * @return impFileSheetName.
     */
    public String getImpFileSheetName() {
        return impFileSheetName;
    }

    /**
     * 设置 impFileSheetName
     *
     * @param impFileSheetName impFileSheetName
     */
    public void setImpFileSheetName(String impFileSheetName) {
        this.impFileSheetName = impFileSheetName;
    }

    /**
     * 获取 sheetInfoList
     *
     * @return sheetInfoList.
     */
    public List<ValExcelImpFileSheetInfoDto> getSheetInfoList() {
        return sheetInfoList;
    }

    /**
     * 设置 sheetInfoList
     *
     * @param sheetInfoList sheetInfoList
     */
    public void setSheetInfoList(List<ValExcelImpFileSheetInfoDto> sheetInfoList) {
        this.sheetInfoList = sheetInfoList;
    }

}
