/*
 * 文件名：GateWayRequestMsgDTO.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caoguo
 * @version 1.0 2020年5月14日
 * @see GateWayReqMsgDTO
 * @since 1.0
 */
public class GateWayReqMsgDTO {
    /**
     * 明细编号
     */
    private String detId;;
    
    /**
     * 请求编号
     */
    private String reqId;
    
    /**
     * 对手机构代码21位编号
     */
    private String cpPartyId;

    /**
     * 获取 detId
     * @return detId.
     */
    public String getDetId() {
        return detId;
    }

    /**
     * 设置 detId
     * @param detId detId
     */
    public void setDetId(String detId) {
        this.detId = detId;
    }

    /**
     * 获取 reqId
     * @return reqId.
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * 获取 cpPartyId
     * @return cpPartyId.
     */
    public String getCpPartyId() {
        return cpPartyId;
    }

    
    /**
     * 设置 reqId
     * @param reqId reqId
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    
    /**
     * 设置 cpPartyId
     * @param cpPartyId cpPartyId
     */
    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId;
    }
    
}
