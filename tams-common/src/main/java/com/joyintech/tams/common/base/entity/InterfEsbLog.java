package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfEsbLog extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 处理识别号
     */
    private String uuid;

    /**
     * 报文流水号
     */
    private String bizSeqNo;

    /**
     * URL
     */
    private String url;

    /**
     * UID
     */
    private String usid;

    /**
     * 验证秘钥
     */
    private String masterKey;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 服务代码
     */
    private String serviceCode;

    /**
     * 服务场景
     */
    private String serviceScene;

    /**
     * 出入方向
     */
    private String sendReceive;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 返回代码
     */
    private String retCode;

    /**
     * 返回内容
     */
    private String retMsg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段1
     */
    private String remark2;

    /**
     * 备用字段1
     */
    private String remark3;

    /**
     * 备用字段1
     */
    private String remark4;

    /**
     * 备用字段1
     */
    private String remark5;

    /**
     * 备用字段1
     */
    private String remark6;

    /**
     * 备用字段1
     */
    private String remark7;

    /**
     * 备用字段1
     */
    private String remark8;

    /**
     * 备用字段1
     */
    private String remark9;

    /**
     * 备用字段1
     */
    private String remark10;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 处理识别号
     * @return 处理识别号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 处理识别号
     * @param uuid 处理识别号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 报文流水号
     * @return 报文流水号
     */
    public String getBizSeqNo() {
        return bizSeqNo;
    }

    /**
     * 设置 报文流水号
     * @param bizSeqNo 报文流水号
     */
    public void setBizSeqNo(String bizSeqNo) {
        this.bizSeqNo = bizSeqNo == null ? null : bizSeqNo.trim();
    }

    /**
     * 取得 URL
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 URL
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 取得 UID
     * @return UID
     */
    public String getUsid() {
        return usid;
    }

    /**
     * 设置 UID
     * @param usid UID
     */
    public void setUsid(String usid) {
        this.usid = usid == null ? null : usid.trim();
    }

    /**
     * 取得 验证秘钥
     * @return 验证秘钥
     */
    public String getMasterKey() {
        return masterKey;
    }

    /**
     * 设置 验证秘钥
     * @param masterKey 验证秘钥
     */
    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey == null ? null : masterKey.trim();
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }

    /**
     * 取得 服务代码
     * @return 服务代码
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * 设置 服务代码
     * @param serviceCode 服务代码
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    /**
     * 取得 服务场景
     * @return 服务场景
     */
    public String getServiceScene() {
        return serviceScene;
    }

    /**
     * 设置 服务场景
     * @param serviceScene 服务场景
     */
    public void setServiceScene(String serviceScene) {
        this.serviceScene = serviceScene == null ? null : serviceScene.trim();
    }

    /**
     * 取得 出入方向
     * @return 出入方向
     */
    public String getSendReceive() {
        return sendReceive;
    }

    /**
     * 设置 出入方向
     * @param sendReceive 出入方向
     */
    public void setSendReceive(String sendReceive) {
        this.sendReceive = sendReceive == null ? null : sendReceive.trim();
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
     * 取得 返回代码
     * @return 返回代码
     */
    public String getRetCode() {
        return retCode;
    }

    /**
     * 设置 返回代码
     * @param retCode 返回代码
     */
    public void setRetCode(String retCode) {
        this.retCode = retCode == null ? null : retCode.trim();
    }

    /**
     * 取得 返回内容
     * @return 返回内容
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * 设置 返回内容
     * @param retMsg 返回内容
     */
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg == null ? null : retMsg.trim();
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
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * 设置 备用字段1
     * @param remark1 备用字段1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 设置 备用字段1
     * @param remark2 备用字段1
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 设置 备用字段1
     * @param remark3 备用字段1
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 设置 备用字段1
     * @param remark4 备用字段1
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark5() {
        return remark5;
    }

    /**
     * 设置 备用字段1
     * @param remark5 备用字段1
     */
    public void setRemark5(String remark5) {
        this.remark5 = remark5 == null ? null : remark5.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark6() {
        return remark6;
    }

    /**
     * 设置 备用字段1
     * @param remark6 备用字段1
     */
    public void setRemark6(String remark6) {
        this.remark6 = remark6 == null ? null : remark6.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark7() {
        return remark7;
    }

    /**
     * 设置 备用字段1
     * @param remark7 备用字段1
     */
    public void setRemark7(String remark7) {
        this.remark7 = remark7 == null ? null : remark7.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark8() {
        return remark8;
    }

    /**
     * 设置 备用字段1
     * @param remark8 备用字段1
     */
    public void setRemark8(String remark8) {
        this.remark8 = remark8 == null ? null : remark8.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark9() {
        return remark9;
    }

    /**
     * 设置 备用字段1
     * @param remark9 备用字段1
     */
    public void setRemark9(String remark9) {
        this.remark9 = remark9 == null ? null : remark9.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark10() {
        return remark10;
    }

    /**
     * 设置 备用字段1
     * @param remark10 备用字段1
     */
    public void setRemark10(String remark10) {
        this.remark10 = remark10 == null ? null : remark10.trim();
    }
}