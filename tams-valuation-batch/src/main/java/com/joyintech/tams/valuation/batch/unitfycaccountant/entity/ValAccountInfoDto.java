package com.joyintech.tams.valuation.batch.unitfycaccountant.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.constant.SystemDictCode;
import java.math.BigDecimal;
import java.util.Date;

public class ValAccountInfoDto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal useingTime;
    /**
     * 任务状态
     * */
    private String taskStatusCode;
    /**
     * 任务状态
     * */
    private String detailName;
    /**
     * 分类名称
     * */
    private String taskGrpId;
    /**
     * 分类ID
     * */
    private String taskGrpName;
    /**
     * 分类类型 task:批处理
     * */
    private String taskGrpType;
    /**
     * 父分类ID
     * */
    private String pTaskGrpId;
    /**
     * 顺序
     * */
    private String orderNo;

    /**
     * executeinfo的seqNo也即是executelog的seqNo
     */
    private String seqNo;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 所属任务组代码
     */
    private String taskGroupId;

    /**
     * 所属任务组
     */
    private String taskGroupName;

    /**
     * 产品组代码
     */
    private String prdGroupId;

    /**
     * 产品组名称
     */
    private String prdGroupName;

    /**
     * 业务开始日期
     */
    private String businessStartDate;

    /**
     * 业务截止日期
     */
    private String businessEndDate;

    /**
     * 批处理执行日
     */
    private String batchDate;

    /**
     * 批处理执行开始时间
     */
    private Date batchStartTime;

    /**
     * 批处理执行终止时间
     */
    private Date batchEndTime;

    /**
     * 批处理执行状态
     */
    private String taskStatus;

    /**
     * 批处理启动模式
     */
    private String startMode;

    /**
     * 任务创建人
     */
    private String createUser;

    /**
     * 日志详情
     */
    private String logInfo;


    /**
     * 获取 taskStatusCode
     * @return taskStatusCode.
     */
    public String getTaskStatusCode() {
        return taskStatusCode;
    }



    /**
     * 设置 taskStatusCode
     * @param taskStatusCode taskStatusCode
     */
    public void setTaskStatusCode(String taskStatusCode) {
        this.taskStatusCode = taskStatusCode;
    }


    /**
     * 获取 useingTime
     * @return useingTime.
     */
    public BigDecimal getUseingTime() {
        return useingTime;
    }


    /**
     * 设置 useingTime
     * @param useingTime useingTime
     */
    public void setUseingTime(BigDecimal useingTime) {
        this.useingTime = useingTime;
    }


    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }




    /**
     * 获取 detailName
     * @return detailName.
     */
    public String getDetailName() {
        return detailName;
    }




    /**
     * 设置 detailName
     * @param detailName detailName
     */
    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }




    /**
     * 获取 taskGrpId
     * @return taskGrpId.
     */
    public String getTaskGrpId() {
        return taskGrpId;
    }




    /**
     * 设置 taskGrpId
     * @param taskGrpId taskGrpId
     */
    public void setTaskGrpId(String taskGrpId) {
        this.taskGrpId = taskGrpId;
    }




    /**
     * 获取 taskGrpName
     * @return taskGrpName.
     */
    public String getTaskGrpName() {
        return taskGrpName;
    }




    /**
     * 设置 taskGrpName
     * @param taskGrpName taskGrpName
     */
    public void setTaskGrpName(String taskGrpName) {
        this.taskGrpName = taskGrpName;
    }




    /**
     * 获取 taskGrpType
     * @return taskGrpType.
     */
    public String getTaskGrpType() {
        return taskGrpType;
    }




    /**
     * 设置 taskGrpType
     * @param taskGrpType taskGrpType
     */
    public void setTaskGrpType(String taskGrpType) {
        this.taskGrpType = taskGrpType;
    }




    /**
     * 获取 pTaskGrpId
     * @return pTaskGrpId.
     */
    public String getpTaskGrpId() {
        return pTaskGrpId;
    }




    /**
     * 设置 pTaskGrpId
     * @param pTaskGrpId pTaskGrpId
     */
    public void setpTaskGrpId(String pTaskGrpId) {
        this.pTaskGrpId = pTaskGrpId;
    }




    /**
     * 获取 orderNo
     * @return orderNo.
     */
    public String getOrderNo() {
        return orderNo;
    }





    /**
     * 获取 seqNo
     * @return seqNo.
     */
    public String getSeqNo() {
        return seqNo;
    }




    /**
     * 设置 seqNo
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }




    /**
     * 获取 taskId
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }




    /**
     * 设置 taskId
     * @param taskId taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }




    /**
     * 获取 taskName
     * @return taskName.
     */
    public String getTaskName() {
        return taskName;
    }




    /**
     * 设置 taskName
     * @param taskName taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }




    /**
     * 获取 taskGroupId
     * @return taskGroupId.
     */
    public String getTaskGroupId() {
        return taskGroupId;
    }




    /**
     * 设置 taskGroupId
     * @param taskGroupId taskGroupId
     */
    public void setTaskGroupId(String taskGroupId) {
        this.taskGroupId = taskGroupId;
    }




    /**
     * 获取 taskGroupName
     * @return taskGroupName.
     */
    public String getTaskGroupName() {
        return taskGroupName;
    }




    /**
     * 设置 taskGroupName
     * @param taskGroupName taskGroupName
     */
    public void setTaskGroupName(String taskGroupName) {
        this.taskGroupName = taskGroupName;
    }




    /**
     * 获取 prdGroupId
     * @return prdGroupId.
     */
    public String getPrdGroupId() {
        return prdGroupId;
    }




    /**
     * 设置 prdGroupId
     * @param prdGroupId prdGroupId
     */
    public void setPrdGroupId(String prdGroupId) {
        this.prdGroupId = prdGroupId;
    }




    /**
     * 获取 prdGroupName
     * @return prdGroupName.
     */
    public String getPrdGroupName() {
        return prdGroupName;
    }




    /**
     * 设置 prdGroupName
     * @param prdGroupName prdGroupName
     */
    public void setPrdGroupName(String prdGroupName) {
        this.prdGroupName = prdGroupName;
    }




    /**
     * 获取 businessStartDate
     * @return businessStartDate.
     */
    public String getBusinessStartDate() {
        return businessStartDate;
    }




    /**
     * 设置 businessStartDate
     * @param businessStartDate businessStartDate
     */
    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }




    /**
     * 获取 businessEndDate
     * @return businessEndDate.
     */
    public String getBusinessEndDate() {
        return businessEndDate;
    }




    /**
     * 设置 businessEndDate
     * @param businessEndDate businessEndDate
     */
    public void setBusinessEndDate(String businessEndDate) {
        this.businessEndDate = businessEndDate;
    }




    /**
     * 获取 batchDate
     * @return batchDate.
     */
    public String getBatchDate() {
        return batchDate;
    }




    /**
     * 设置 batchDate
     * @param batchDate batchDate
     */
    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }




    /**
     * 获取 batchStartTime
     * @return batchStartTime.
     */
    public Date getBatchStartTime() {
        return batchStartTime;
    }




    /**
     * 设置 batchStartTime
     * @param batchStartTime batchStartTime
     */
    public void setBatchStartTime(Date batchStartTime) {
        this.batchStartTime = batchStartTime;
    }




    /**
     * 获取 batchEndTime
     * @return batchEndTime.
     */
    public Date getBatchEndTime() {
        return batchEndTime;
    }




    /**
     * 设置 batchEndTime
     * @param batchEndTime batchEndTime
     */
    public void setBatchEndTime(Date batchEndTime) {
        this.batchEndTime = batchEndTime;
    }




    /**
     * 获取 taskStatus
     * @return taskStatus.
     */
    public String getTaskStatus() {
        return SysDataUtil.getNameFromDataDict(SystemDictCode.执行状态, this.taskStatus);
    }




    /**
     * 设置 taskStatus
     * @param taskStatus taskStatus
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }




    /**
     * 获取 startMode
     * @return startMode.
     */
    public String getStartMode() {
        return startMode;
    }




    /**
     * 设置 startMode
     * @param startMode startMode
     */
    public void setStartMode(String startMode) {
        this.startMode = startMode;
    }




    /**
     * 获取 createUser
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }




    /**
     * 设置 createUser
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }




    /**
     * 获取 logInfo
     * @return logInfo.
     */
    public String getLogInfo() {
        return logInfo;
    }




    /**
     * 设置 logInfo
     * @param logInfo logInfo
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }



    /**
     * 设置 orderNo
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStartTimeFmt(){
        return DateUtil.format(this.batchStartTime, "HH:mm:ss SSS");
    }

    public String getStartDateFmt(){
        return DateUtil.format(this.batchStartTime,"yyyy-MM-dd");
    }
    public String getEndTimeFmt(){
        return DateUtil.format(this.batchEndTime, "HH:mm:ss SSS");
    }

    public String getEndDateFmt(){
        return DateUtil.format(this.batchEndTime,"yyyy-MM-dd");
    }


}
