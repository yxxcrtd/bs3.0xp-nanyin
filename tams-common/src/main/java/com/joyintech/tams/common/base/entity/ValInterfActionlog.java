package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValInterfActionlog extends BaseEntity {
    /**
     * 非业务主键：UUID
     */
    private String seqno;

    /**
     * 业务日期
     */
    private Date busiDate;

    /**
     * 批次号
     */
    private Long orderno;

    /**
     * 任务组英文名称
     */
    private String taskEname;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 执行结果
     */
    private String execResult;

    /**
     * 执行时长，单位为:S
     */
    private Long lastTime;

    /**
     * null
     */
    private String exectaskSeqno;

    /**
     * 执行明细
     */
    private String detailAction;

    /**
     * 取得 非业务主键：UUID
     * @return 非业务主键：UUID
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 非业务主键：UUID
     * @param seqno 非业务主键：UUID
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 业务日期
     * @return 业务日期
     */
    public Date getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     * @param busiDate 业务日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public Long getOrderno() {
        return orderno;
    }

    /**
     * 设置 批次号
     * @param orderno 批次号
     */
    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    /**
     * 取得 任务组英文名称
     * @return 任务组英文名称
     */
    public String getTaskEname() {
        return taskEname;
    }

    /**
     * 设置 任务组英文名称
     * @param taskEname 任务组英文名称
     */
    public void setTaskEname(String taskEname) {
        this.taskEname = taskEname == null ? null : taskEname.trim();
    }

    /**
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 开始时间
     * @param startTime 开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 取得 结束时间
     * @return 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 结束时间
     * @param endTime 结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 执行结果
     * @return 执行结果
     */
    public String getExecResult() {
        return execResult;
    }

    /**
     * 设置 执行结果
     * @param execResult 执行结果
     */
    public void setExecResult(String execResult) {
        this.execResult = execResult == null ? null : execResult.trim();
    }

    /**
     * 取得 执行时长，单位为:S
     * @return 执行时长，单位为:S
     */
    public Long getLastTime() {
        return lastTime;
    }

    /**
     * 设置 执行时长，单位为:S
     * @param lastTime 执行时长，单位为:S
     */
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getExectaskSeqno() {
        return exectaskSeqno;
    }

    /**
     * 设置 null
     * @param exectaskSeqno null
     */
    public void setExectaskSeqno(String exectaskSeqno) {
        this.exectaskSeqno = exectaskSeqno == null ? null : exectaskSeqno.trim();
    }

    /**
     * 取得 执行明细
     * @return 执行明细
     */
    public String getDetailAction() {
        return detailAction;
    }

    /**
     * 设置 执行明细
     * @param detailAction 执行明细
     */
    public void setDetailAction(String detailAction) {
        this.detailAction = detailAction == null ? null : detailAction.trim();
    }
}