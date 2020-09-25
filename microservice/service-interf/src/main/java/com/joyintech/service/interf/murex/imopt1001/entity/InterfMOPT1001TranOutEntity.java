/*
 * 文件名：InterfMOPT1001TranOutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.imopt1001.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 查询交易导入结果信息 接口交易数据输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月21日
 * @see InterfMOPT1001TranOutEntity
 * @since 1.0
 */
public class InterfMOPT1001TranOutEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5772439256188104788L;

    /**
     * 外部交易号
     */
    private String outerTranNo;

    /**
     * MUREX合约号
     */
    private String murexAgreeNo;

    /**
     * MUREX导入状态
     */
    private String murexImportStatus;

    /**
     * 业务类型
     */
    private String bussType;

    /**
     * 类型
     */
    private String type;

    /**
     * 上游系统
     */
    private String upperSys;

    /**
     * 请求流水号
     */
    private String requestSeqNo;

    /**
     * 成交日
     */
    private String tranDate;

    /**
     * 对账日期
     */
    private String checkDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取 外部交易号
     * 
     * @return 外部交易号
     */
    public String getOuterTranNo() {
        return outerTranNo;
    }

    /**
     * 设置 外部交易号
     * 
     * @param outerTranNo 外部交易号
     */
    @EsbName(value = "OUTER_TRAN_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOuterTranNo(String outerTranNo) {
        this.outerTranNo = outerTranNo;
    }

    /**
     * 获取 MUREX合约号
     * 
     * @return MUREX合约号
     */
    public String getMurexAgreeNo() {
        return murexAgreeNo;
    }

    /**
     * 设置 MUREX合约号
     * 
     * @param murexAgreeNo MUREX合约号
     */
    @EsbName(value = "MUREX_AGREE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setMurexAgreeNo(String murexAgreeNo) {
        this.murexAgreeNo = murexAgreeNo;
    }

    /**
     * 获取 MUREX导入状态
     * 
     * @return MUREX导入状态
     */
    public String getMurexImportStatus() {
        return murexImportStatus;
    }

    /**
     * 设置 MUREX导入状态
     * 
     * @param murexImportStatus MUREX导入状态
     */
    @EsbName(value = "MUREX_IMPORT_STATUS")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setMurexImportStatus(String murexImportStatus) {
        this.murexImportStatus = murexImportStatus;
    }

    /**
     * 获取 业务类型
     * 
     * @return 业务类型
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * 设置 业务类型
     * 
     * @param bussType 业务类型
     */
    @EsbName(value = "BUSS_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    /**
     * 获取 类型
     * 
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型
     * 
     * @param type 类型
     */
    @EsbName(value = "TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 上游系统
     * 
     * @return 上游系统
     */
    public String getUpperSys() {
        return upperSys;
    }

    /**
     * 设置 上游系统
     * 
     * @param upperSys 上游系统
     */
    @EsbName(value = "UPPER_SYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setUpperSys(String upperSys) {
        this.upperSys = upperSys;
    }

    /**
     * 获取 请求流水号
     * 
     * @return 请求流水号
     */
    public String getRequestSeqNo() {
        return requestSeqNo;
    }

    /**
     * 设置 请求流水号
     * 
     * @param requestSeqNo 请求流水号
     */
    @EsbName(value = "REQUEST_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRequestSeqNo(String requestSeqNo) {
        this.requestSeqNo = requestSeqNo;
    }

    /**
     * 获取 交易日期
     * 
     * @return 交易日期
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * 设置 成交日
     * 
     * @param tranDate 成交日
     */
    @EsbName(value = "DEAL_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * 获取 交易日期
     * 
     * @return 交易日期
     */
    public String getCheckDate() {
        return checkDate;
    }

    /**
     * 设置 成交日
     * 
     * @param tranDate 成交日
     */
    @EsbName(value = "CHECK_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 获取 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
