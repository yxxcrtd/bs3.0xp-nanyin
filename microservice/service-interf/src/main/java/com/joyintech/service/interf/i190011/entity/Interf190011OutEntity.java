/*
 * 文件名：Interf190011OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i190011.entity;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.InterfBonusInfo;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 净值分红信息查询接口（190011）-输出
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf190011OutEntity
 * @since 1.0
 */
public class Interf190011OutEntity extends InterfBonusInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6264929535808916768L;

    /**
     * 数据条数
     */
    private Integer dataNum;

    /**
     * 净值分红信息数组
     */
    private List<Interf190011OutEntity> arrList;

    /**
     * 设置 数据条数
     * 
     * @param dataNum 数据条数
     */
    public void setDataNum(Integer dataNum) {
        this.dataNum = dataNum;
    }

    /**
     * 获取 数据条数
     * 
     * @return 数据条数.
     */
    public Integer getDataNum() {
        return dataNum;
    }

    /**
     * 设置 第几页数据
     * 
     * @param pageNo 第几页数据
     */
    @EsbName(value = "PAGE_NO")
    @EsbFieldType(value = EsbFieldType.INT)
    @Override
    public void setPageNo(Integer pageNo) {
        super.setPageNo(pageNo);
    }

    /**
     * 设置 页大小
     * 
     * @param pageSize 页大小
     */
    @EsbName(value = "PAGE_SIZE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setPageSize(Integer pageSize) {
        super.setPageSize(pageSize);
    }

    /**
     * 设置 产品代码
     * 
     * @param productCode 产品代码
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setProductCode(String productCode) {
        super.setProdId(productCode);
    }

    /**
     * 设置 母产品代码
     * 
     * @param motherProdCode 母产品代码
     */
    @EsbName(value = "MOTHER_PROD_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setMotherProdCode(String motherProdCode) {
        super.setmProdId(motherProdCode);
    }

    /**
     * 设置 产品期次
     * 
     * @param prodPeriodNo 产品期次
     */
    @EsbName(value = "PROD_PERIOD_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setProdPeriodNo(String prodPeriodNo) {
        super.setProdBatchNo(prodPeriodNo);
    }

    /**
     * 设置 权益登记日
     * 
     * @param registerDate 权益登记日
     */
    @EsbName(value = "RIGHTS_REGISTER_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRegisterDate(String registerDate) {
        super.setRegDate(registerDate);
    }

    /**
     * 设置 分红日
     * 
     * @param bonusDate 分红日
     */
    @EsbName(value = "BONUS_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBonusDate(String bonusDate) {
        super.setBonusDate(bonusDate);
    }

    /**
     * 设置 单位分红金额
     * 
     * @param unitBonusAmt 单位分红金额
     */
    @EsbName(value = "UNIT_BONUS_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setUnitBonusAmt(BigDecimal unitBonusAmt) {
        super.setUnitBonusVlu(unitBonusAmt);
    }

    /**
     * 设置 单位分红份额
     * 
     * @param unitBonusLot 单位分红份额
     */
    @EsbName(value = "UNIT_BONUS_LOT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setUnitBonusLot(BigDecimal unitBonusLot) {
        super.setUnitBonusVol(unitBonusLot);
    }

    /**
     * 设置 录入日期
     * 
     * @param inputDate 录入日期
     */
    @EsbName(value = "INPUT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setInputDate(String inputDate) {
        super.setInsertDate(inputDate);
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRemark(String remark) {
        super.setRemark(remark);
    }

    /**
     * 获取 净值分红信息数组
     * 
     * @return 净值分红信息数组.
     */
    public List<Interf190011OutEntity> getArrList() {
        return arrList;
    }

    /**
     * 设置 净值分红信息数组
     * 
     * @param arrList 净值分红信息数组
     */
    @EsbName(value = "NET_VALUE_BONUS_INFO_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setArrList(List<Interf190011OutEntity> arrList) {
        this.arrList = arrList;
    }
}
