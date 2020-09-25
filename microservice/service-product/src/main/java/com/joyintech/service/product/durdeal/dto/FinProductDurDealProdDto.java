/*
 * 文件名：FinProductDurDealProdDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品存续期交易查询 同步产品数据dto
 *
 * @author etliu
 * @version 1.0 2018年12月26日
 * @see FinProductDurDealProdDto
 * @since 1.0
 */
public class FinProductDurDealProdDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7554350265450641701L;

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 销售部门
     */
    private String saleDept;

    /**
     * 处理模式
     */
    private String type;

    /**
     * 交易日/成立日/到期日/生效日：查询日期（yyyyMMdd）
     */
    private String cdate;

    /**
     * 销售开始日
     */
    private String startdate;

    /**
     * 销售截止日
     */
    private String enddate;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 处理结果
     */
    private boolean isHandleOK;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 获取 finProdId
     *
     * @return finProdId.
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 finProdId
     *
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

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
     * 获取 saleDept
     *
     * @return saleDept.
     */
    public String getSaleDept() {
        return saleDept;
    }

    /**
     * 设置 saleDept
     *
     * @param saleDept saleDept
     */
    public void setSaleDept(String saleDept) {
        this.saleDept = saleDept;
    }

    /**
     * 获取 type
     *
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 type
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 cdate
     *
     * @return cdate.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     *
     * @param cdate cdate
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 startdate
     *
     * @return startdate.
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 startdate
     *
     * @param startdate startdate
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 enddate
     *
     * @return enddate.
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 enddate
     *
     * @param enddate enddate
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * 获取 interfId
     *
     * @return interfId.
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 interfId
     *
     * @param interfId interfId
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId;
    }

    /**
     * 获取 isHandleOK
     *
     * @return isHandleOK.
     */
    public boolean isHandleOK() {
        return isHandleOK;
    }

    /**
     * 设置 isHandleOK
     *
     * @param isHandleOK isHandleOK
     */
    public void setHandleOK(boolean isHandleOK) {
        this.isHandleOK = isHandleOK;
    }

    /**
     * 获取 errMsg
     *
     * @return errMsg.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 errMsg
     *
     * @param errMsg errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
