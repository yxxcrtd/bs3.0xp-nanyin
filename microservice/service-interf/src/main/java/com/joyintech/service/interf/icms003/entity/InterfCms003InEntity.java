/*
 * 文件名：InterfCms003InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icms003.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * CMS出账信息撤销接口-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月15日
 * @see InterfCms003InEntity
 * @since 1.0
 */
public class InterfCms003InEntity {

    /**
     * 指令序号
     */
    private String orderSeqNo;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 客户编号
     */
    private String clientNum;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 取得 指令序号
     * 
     * @return 指令序号
     */
    @EsbName(value = "ORDER_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public String getOrderSeqNo() {
        return orderSeqNo;
    }

    /**
     * 取得 撤销原因
     * 
     * @return 撤销原因
     */
    @EsbName(value = "REASON")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Required
    public String getReason() {
        return reason;
    }

    /**
     * 取得 客户编号
     * 
     * @return 客户编号
     */
    @EsbName(value = "CLIENT_NUM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 80)
    public String getClientNum() {
        return clientNum;
    }

    /**
     * 取得 客户姓名
     * 
     * @return 客户姓名
     */
    @EsbName(value = "CLIENT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    public String getClientName() {
        return clientName;
    }

    /**
     * 设置 指令序号
     * 
     * @param orderSeqNo 指令序号
     */
    public void setOrderSeqNo(String orderSeqNo) {
        this.orderSeqNo = orderSeqNo;
    }

    /**
     * 设置 撤销原因
     * 
     * @param reason 撤销原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 设置 客户编号
     * 
     * @param clientNum 客户编号
     */
    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }

    /**
     * 设置 客户姓名
     * 
     * @param clientName 客户姓名
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
