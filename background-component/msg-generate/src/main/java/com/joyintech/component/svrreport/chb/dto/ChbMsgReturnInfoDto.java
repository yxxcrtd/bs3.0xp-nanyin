/*
 * 文件名：ChbMsgReturnInfoDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年09月27日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王波
 * @date 2020年09月27日
 * @see ChbMsgReturnInfoDto
 * @since 1.0
 */
public class ChbMsgReturnInfoDto {

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 报送类型
     */
    private String dealType;

    /**
     * 中债返回码
     */
    private String retCode;

    /**
     * 报送成功条数
     */
    private int successNum;

    /**
     * 报送失败条数
     */
    private int errorNum;

    /**
     * 报送信息状态
     */
    private ChbInfoReportDto chbInfoReportDto;

    /**
     * 中债报送状态
     */
    private List<ChbSubmitStatusDto> chbSubmitStatusDtos;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ChbSubmitStatusDto> getChbSubmitStatusDtos() {
        return chbSubmitStatusDtos;
    }

    public void setChbSubmitStatusDtos(List<ChbSubmitStatusDto> chbSubmitStatusDtos) {
        this.chbSubmitStatusDtos = chbSubmitStatusDtos;
    }

    /**
     * 日志记录
     */
    private List<InterfLog> logList;

    public List<InterfLog> getLogList() {
        return logList;
    }

    public void setLogList(List<InterfLog> logList) {
        this.logList = logList;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public ChbInfoReportDto getChbInfoReportDto() {
        return chbInfoReportDto;
    }

    public void setChbInfoReportDto(ChbInfoReportDto chbInfoReportDto) {
        this.chbInfoReportDto = chbInfoReportDto;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

}
