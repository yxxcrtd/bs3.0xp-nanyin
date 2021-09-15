package com.joyintech.tams.invest.risk.outsvaltableimporthis.dto;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;

public class RskOutValtblImportHis001Dto extends RskOutValtblImportHis implements ProcessEntity {
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 估值日期
     */
    private Date valdate;

    /**开始时间*/
    private String startTimeStr ;

    /**结束时间*/
    private String endTimeStr;

    /**
     * 接入状态
     */
    private String importTypeStr;

    /**
     * 接入状态
     */
    private String errorCodeStr;

    private String runFlag;

    private String resultInfo;

    private String resultInfoStr;

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getResultInfoStr() {
        if (StringUtil.isNotEmpty(resultInfo)){
            resultInfoStr = "详细信息";
        }else{
            resultInfoStr = "";
        }
        return resultInfoStr;
    }

    public void setResultInfoStr(String resultInfoStr) {
        this.resultInfoStr = resultInfoStr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public Date getValdate() {
        return valdate;
    }

    public String getValdateStr() {
        return DateUtil.format(valdate);
    }

    public void setValdate(Date valdate) {
        this.valdate = valdate;
    }

    public String getErrorCodeStr() {
        if (StringUtil.equals(this.getErrorCode(), DictCodeListExt1.估值表自动解析错误码_超过可配置的估值日期.getValue())){
            String days = StringUtil.defaultString(CacheUtil.getSysParam("MAX_SUB_VALDATE"),"16");
            return  SysDataUtil.getNameFromDataDict(DictCode.估值表自动解析错误码, this.getErrorCode()) +":"
                    + DateUtil.format(DateUtil.addDay(this.getImpdate(),-1*Integer.valueOf(days)))+ "-" + DateUtil.format(this.getImpdate());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.估值表自动解析错误码, this.getErrorCode());
    }

    public void setErrorCodeStr(String errorCodeStr) {
        this.errorCodeStr = errorCodeStr;
    }

    public String getImportTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值表自动解析处理状态, this.getImportType());
    }

    public void setImportTypeStr(String importTypeStr) {
        this.importTypeStr = importTypeStr;
    }

    public String getStartTimeStr() {
        return DateUtil.format(this.getStartTime(),DateUtil.DEFAULT_DATETIME_FORMAT);
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return DateUtil.format(this.getEndTime(),DateUtil.DEFAULT_DATETIME_FORMAT);
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }


    public String getRunFlag() {
        return runFlag;
    }

    public void setRunFlag(String runFlag) {
        this.runFlag = runFlag;
    }

    private String assetSubMapId;

    public String getAssetSubMapId() {
        return assetSubMapId;
    }

    public void setAssetSubMapId(String assetSubMapId) {
        this.assetSubMapId = assetSubMapId;
    }
}
