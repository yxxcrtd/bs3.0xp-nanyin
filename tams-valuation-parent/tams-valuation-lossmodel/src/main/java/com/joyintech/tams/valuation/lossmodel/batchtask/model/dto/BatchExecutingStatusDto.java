package com.joyintech.tams.valuation.lossmodel.batchtask.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-16
 * @see BatchExecutingStatusDto
 * @since 1.0
 */
public class BatchExecutingStatusDto extends SysBatchgrpExecInfo {

    private Map<String, String> userMap = CacheUtil.getAppCache(GlobalConstant.SYS_USER_KEY, Map.class);

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 耗时
     */
    private Long processTime;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal percent;

    private String realName;


    /**
     * 发生异常的executeInfo的seqNo
     */
    private int infoSeqNo;

    /**
     * 任务名称
     */
    private String taskName;

    private String taskId;


    private Date executeDate;

    private String executeDateStr;

    /**
     * 资产总数
     */
    private int totalNum;

    /**
     * 成功
     */
    private int successNum;

    /**
     * 失败
     */
    private int errorNum;

    /**
     * 警告数量
     */
    private int warnNum;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
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

    public int getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(int warnNum) {
        this.warnNum = warnNum;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExecuteDateStr() {
        return DateUtil.format(this.executeDate);
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String getRealName() {
        if(GlobalConstant.SYSTEM_USER.equals(this.getCreateUser())) {
            return "自动批处理";
        }
        return userMap.get(this.getCreateUser());
    }

    public void setRealName(String realname) {
        this.realName = realname;
    }

    public int getInfoSeqNo() {
        return infoSeqNo;
    }

    public void setInfoSeqNo(int infoSeqNo) {
        this.infoSeqNo = infoSeqNo;
    }


    public String getCreateTimeString() {
        if (getCreateTime() != null) {
            return DateUtil.format(getCreateTime(), "yyyy-MM-dd HH:mm:ss.SSS");
        }
        return StringUtil.EMPTY;
    }

    public String getUpdateTimeString() {
        if (getUpdateTime() != null) {
            return DateUtil.format(getUpdateTime(), "yyyy-MM-dd HH:mm:ss.SSS");
        }
        return StringUtil.EMPTY;
    }
}
