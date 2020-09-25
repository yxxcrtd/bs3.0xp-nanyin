/*
 * 文件名：InterfMOPT1001InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.imopt1001.entity;

import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 查询交易导入结果信息MOPT1001 接口输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月21日
 * @see InterfMOPT1001InEntity
 * @since 1.0
 */
public class InterfMOPT1001InEntity {

    /**
     * 发送MUREX信息
     */
    private Map<String, Object> sendMurexInfo;

    public InterfMOPT1001InEntity(Map<String, Object> sendMurexInfo) {
        this.sendMurexInfo = sendMurexInfo;
    }

    /**
     * 获取 sendMurexInfo
     * 
     * @return sendMurexInfo.
     */
    public Map<String, Object> getSendMurexInfo() {
        return sendMurexInfo;
    }

    /**
     * 设置 sendMurexInfo
     * 
     * @param sendMurexInfo sendMurexInfo
     */
    public void setSendMurexInfo(Map<String, Object> sendMurexInfo) {
        this.sendMurexInfo = sendMurexInfo;
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "OUTER_TRAN_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getBusinessId() {
        return (String)sendMurexInfo.get("OUTER_TRAN_NO");
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSS_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getBusinessType() {
        return (String)sendMurexInfo.get("BUSS_TYPE");
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getDealType() {
        return (String)sendMurexInfo.get("TYPE");
    }

    /**
     * 获取 上游系统
     * 
     * @return 上游系统
     */
    @EsbName(value = "UPPER_SYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getUpperSys() {
        // return InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID)
        return "TRCMS-PFMS";
    }

    /**
     * 获取 对账日期
     * 
     * @return 对账日期
     */
    @EsbName(value = "CHECK_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getCheckDate() {
        return DateUtil.formatDate((String)sendMurexInfo.get("CHECK_DATE"));
    }

    /**
     * 获取 成交日
     * 
     * @return 成交日
     */
    @EsbName(value = "DEAL_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getDealDate() {
        return (String)sendMurexInfo.get("DEAL_DATE");
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REQUEST_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    public String getBizSeqNo() {
        return (String)sendMurexInfo.get("REQUEST_SEQ_NO");
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark() {
        return (String)sendMurexInfo.get("REMARK");
    }
}
