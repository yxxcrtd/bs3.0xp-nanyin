package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfSendMurex extends BaseEntity {
    /**
     * GUID
     */
    private String uuid;

    /**
     * 业务类型:拆借、质押式回购、债券
     */
    private String businessType;

    /**
     * 业务流水号
     */
    private String busiSeqNo;

    /**
     * 发送次数
     */
    private Integer sendNum;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 指令接收状态
     */
    private String orderRecStatus;

    /**
     * 处理状态(MUREX导入状态)
     */
    private String orderRetStatus;

    /**
     * MUREX合约号
     */
    private String murexAgreeNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送数据
     */
    private String sendData;

    /**
     * 取得 GUID
     * @return GUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 GUID
     * @param uuid GUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 业务类型:拆借、质押式回购、债券
     * @return 业务类型:拆借、质押式回购、债券
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置 业务类型:拆借、质押式回购、债券
     * @param businessType 业务类型:拆借、质押式回购、债券
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 取得 业务流水号
     * @return 业务流水号
     */
    public String getBusiSeqNo() {
        return busiSeqNo;
    }

    /**
     * 设置 业务流水号
     * @param busiSeqNo 业务流水号
     */
    public void setBusiSeqNo(String busiSeqNo) {
        this.busiSeqNo = busiSeqNo == null ? null : busiSeqNo.trim();
    }

    /**
     * 取得 发送次数
     * @return 发送次数
     */
    public Integer getSendNum() {
        return sendNum;
    }

    /**
     * 设置 发送次数
     * @param sendNum 发送次数
     */
    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    /**
     * 取得 发送时间
     * @return 发送时间
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 设置 发送时间
     * @param sendDate 发送时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 取得 发送状态
     * @return 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 指令接收状态
     * @return 指令接收状态
     */
    public String getOrderRecStatus() {
        return orderRecStatus;
    }

    /**
     * 设置 指令接收状态
     * @param orderRecStatus 指令接收状态
     */
    public void setOrderRecStatus(String orderRecStatus) {
        this.orderRecStatus = orderRecStatus == null ? null : orderRecStatus.trim();
    }

    /**
     * 取得 处理状态(MUREX导入状态)
     * @return 处理状态(MUREX导入状态)
     */
    public String getOrderRetStatus() {
        return orderRetStatus;
    }

    /**
     * 设置 处理状态(MUREX导入状态)
     * @param orderRetStatus 处理状态(MUREX导入状态)
     */
    public void setOrderRetStatus(String orderRetStatus) {
        this.orderRetStatus = orderRetStatus == null ? null : orderRetStatus.trim();
    }

    /**
     * 取得 MUREX合约号
     * @return MUREX合约号
     */
    public String getMurexAgreeNo() {
        return murexAgreeNo;
    }

    /**
     * 设置 MUREX合约号
     * @param murexAgreeNo MUREX合约号
     */
    public void setMurexAgreeNo(String murexAgreeNo) {
        this.murexAgreeNo = murexAgreeNo == null ? null : murexAgreeNo.trim();
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

    /**
     * 取得 发送数据
     * @return 发送数据
     */
    public String getSendData() {
        return sendData;
    }

    /**
     * 设置 发送数据
     * @param sendData 发送数据
     */
    public void setSendData(String sendData) {
        this.sendData = sendData == null ? null : sendData.trim();
    }
}