/*
 * 文件名：ValExcelImpResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * excel导入结果dto
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelImpResultDto
 * @since 1.0
 */
public class ValExcelImpResultDto extends BaseEntity{

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
     * 导入数据表
     */
    private String impTargetTableName;

    /**
     * 导入结果（导入成功 导入失败）
     */
    private String importFlag;

    /**
     * 导入结果处理详情
     */
    private String impResultMsg;

    /**
     * 登录数据件数
     */
    private int insertDataCount;

    /**
     * 更新数据件数
     */
    private int updateDataCount;

    /**
     * 错误信息列表
     */
    private List<String> errorMsgList = new ArrayList<String>();

    /**
     * 导入结果列表
     */
    private List<ValExcelImpResultDto> resultList = new ArrayList<ValExcelImpResultDto>();

    /**
     * 将单个sheet的处理结果MSG列表添加到总列表中
     *
     * @param sheetName sheet名称
     * @param msgList 单个sheet的处理结果MSG列表
     * @see
     * @since 1.0
     */
    public void addeErorMsgList(String sheetName, List<String> msgList) {
        for (String msg : msgList) {
            this.errorMsgList.add("[" + sheetName +  "]sheet： " + msg);
        }
    }

    /**
     * 获取 importFlag
     *
     * @return importFlag.
     */
    public String getImportFlag() {
        return importFlag;
    }

    /**
     * 设置 importFlag
     *
     * @param importFlag importFlag
     */
    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    /**
     * 获取 errorMsgList
     *
     * @return errorMsgList.
     */
    public List<String> getErrorMsgList() {
        return errorMsgList;
    }

    /**
     * 设置 errorMsgList
     *
     * @param errorMsgList errorMsgList
     */
    public void setErrorMsgList(List<String> errorMsgList) {
        this.errorMsgList = errorMsgList;
    }

    /**
     * 获取 insertDataCount
     *
     * @return insertDataCount.
     */
    public int getInsertDataCount() {
        return insertDataCount;
    }

    /**
     * 设置 insertDataCount
     *
     * @param insertDataCount insertDataCount
     */
    public void setInsertDataCount(int insertDataCount) {
        this.insertDataCount = insertDataCount;
    }

    /**
     * 获取 updateDataCount
     *
     * @return updateDataCount.
     */
    public int getUpdateDataCount() {
        return updateDataCount;
    }

    /**
     * 设置 updateDataCount
     *
     * @param updateDataCount updateDataCount
     */
    public void setUpdateDataCount(int updateDataCount) {
        this.updateDataCount = updateDataCount;
    }

    /**
     * 获取 resultList
     *
     * @return resultList.
     */
    public List<ValExcelImpResultDto> getResultList() {
        return resultList;
    }

    /**
     * 设置 resultList
     *
     * @param resultList resultList
     */
    public void setResultList(List<ValExcelImpResultDto> resultList) {
        this.resultList = resultList;
    }

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
     * 获取 impTargetTableName
     *
     * @return impTargetTableName.
     */
    public String getImpTargetTableName() {
        return impTargetTableName;
    }

    /**
     * 设置 impTargetTableName
     *
     * @param impTargetTableName impTargetTableName
     */
    public void setImpTargetTableName(String impTargetTableName) {
        this.impTargetTableName = impTargetTableName;
    }

    /**
     * 获取 impResultMsg
     *
     * @return impResultMsg.
     */
    public String getImpResultMsg() {
        return impResultMsg;
    }

    /**
     * 设置 impResultMsg
     *
     * @param impResultMsg impResultMsg
     */
    public void setImpResultMsg(String impResultMsg) {
        this.impResultMsg = impResultMsg;
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
}
