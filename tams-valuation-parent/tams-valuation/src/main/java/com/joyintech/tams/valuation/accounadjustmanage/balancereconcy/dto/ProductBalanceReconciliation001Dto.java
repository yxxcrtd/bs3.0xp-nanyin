/*
 * 文件名：ProductBalanceReconciliation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.balancereconcy.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 产品余额表对账表格数据实体类
 * 
 * @author yangjian
 * @version 1.0 2018年11月16日
 * @see ProductBalanceReconciliation001Dto
 * @since 1.0
 */
public class ProductBalanceReconciliation001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品简称
     */
    private String prodName;

    /**
     * 估值日期
     */
    private String valueDate;

    /**
     * 对账状态
     */
    private String reconcileStatus;

    /**
     * 对账状态字符串
     */
    private String reconcileStatusString;

    /**
     * 对账结果
     */
    private String reconcileResult;

    /**
     * 对账结果字符串
     */
    private String reconcileResultString;

    /**
     * 处理说明
     */
    private String remark;

    /**
     * 本方文件路径
     */
    private String filePath1;

    /**
     * 对方文件路径
     */
    private String filePath2;

    /**
     * 对账结果文件
     */
    private String filePath3;

    /**
     * 获取 prodId
     * 
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     * 
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 prodName
     * 
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     * 
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 valueDate
     * 
     * @return valueDate.
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     * 设置 valueDate
     * 
     * @param valueDate valueDate
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 获取 reconcileStatus
     * 
     * @return reconcileStatus.
     */
    public String getReconcileStatus() {
        return reconcileStatus;
    }

    /**
     * 设置 reconcileStatus
     * 
     * @param reconcileStatus reconcileStatus
     */
    public void setReconcileStatus(String reconcileStatus) {
        this.reconcileStatus = reconcileStatus;
    }

    /**
     * 获取 reconcileStatusString
     * 
     * @return reconcileStatusString.
     */
    public String getReconcileStatusString() {
        
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.对账状态, reconcileStatusString))?
            SysDataUtil.getNameFromDataDict(DictCode.对账状态, reconcileStatusString):reconcileStatusString;
//        return SysDataUtil.getNameFromDataDict(DictCode.对账状态, reconcileStatusString);
    }

    /**
     * 设置 reconcileStatusString
     * 
     * @param reconcileStatusString reconcileStatusString
     */
    public void setReconcileStatusString(String reconcileStatusString) {
        this.reconcileStatusString = reconcileStatusString;
    }

    /**
     * 获取 reconcileResult
     * 
     * @return reconcileResult.
     */
    public String getReconcileResult() {
        return reconcileResult;
    }

    /**
     * 设置 reconcileResult
     * 
     * @param reconcileResult reconcileResult
     */
    public void setReconcileResult(String reconcileResult) {
        this.reconcileResult = reconcileResult;
    }

    /**
     * 获取 reconcileResultString
     * 
     * @return reconcileResultString.
     */
    public String getReconcileResultString() {
        
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.对账结果, reconcileResultString))?
            SysDataUtil.getNameFromDataDict(DictCode.对账结果, reconcileResultString):reconcileResultString;
//        return SysDataUtil.getNameFromDataDict(DictCode.对账结果, reconcileResultString);
    }

    /**
     * 设置 reconcileResultString
     * 
     * @param reconcileResultString reconcileResultString
     */
    public void setReconcileResultString(String reconcileResultString) {
        this.reconcileResultString = reconcileResultString;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 filePath1
     * 
     * @return filePath1.
     */
    public String getFilePath1() {
        return filePath1;
    }

    /**
     * 设置 filePath1
     * 
     * @param filePath1 filePath1
     */
    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    /**
     * 获取 filePath2
     * 
     * @return filePath2.
     */
    public String getFilePath2() {
        return filePath2;
    }

    /**
     * 设置 filePath2
     * 
     * @param filePath2 filePath2
     */
    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    /**
     * 获取 filePath3
     * 
     * @return filePath3.
     */
    public String getFilePath3() {
        return filePath3;
    }

    /**
     * 设置 filePath3
     * 
     * @param filePath3 filePath3
     */
    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

}
