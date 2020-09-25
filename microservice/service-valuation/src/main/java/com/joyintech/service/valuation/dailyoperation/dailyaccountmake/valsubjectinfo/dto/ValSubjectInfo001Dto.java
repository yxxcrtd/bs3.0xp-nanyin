package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class ValSubjectInfo001Dto extends BaseEntity implements ITableRow {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5064115751455891903L;

    /**
     * 业务流水号
     */
    private String serialNo;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 报文状态
     */
    private String xmlStatus;

    /**
     * 接口类型
     */
    private String interType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 接受信息
     */
    private String returnTxt;

    /**
     * 获取备注
     * 
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取接受信息
     * 
     * @return
     */
    public String getReturnTxt() {
        return returnTxt;
    }

    /**
     * 设置接受信息
     * 
     * @param returnTxt
     */
    public void setReturnTxt(String returnTxt) {
        this.returnTxt = returnTxt;
    }

    /**
     * 获取业务流水号
     * 
     * @return
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置业务流水号
     * 
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    /**
     * 获取产品代码
     * 
     * @return
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置产品代码
     * 
     * @param fundId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 获取产品名称
     * 
     * @return
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置产品名称
     * 
     * @param finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 获取报文状态
     * 
     * @return
     */
    public String getXmlStatus() {
        return xmlStatus;
    }

    /**
     * 设置报文状态
     * 
     * @param xmlStatus
     */
    public void setXmlStatus(String xmlStatus) {
        this.xmlStatus = xmlStatus;
    }

    /**
     * 获取 报文状态数据字典
     * 
     * @return 报文状态数据字典.
     */
    public String getXmlStatusFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.深圳通报文状态,
            this.getXmlStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.深圳通报文状态,
                this.getXmlStatus()) : this.getXmlStatus();
    }

    /**
     * 获取接口类型
     * 
     * @return
     */
    public String getInterType() {
        return interType;
    }

    /**
     * 设置接口类型
     * 
     * @param interType
     */
    public void setInterType(String interType) {
        this.interType = interType == null ? null : interType.trim();
    }

    /**
     * 获取 接口类型数据字典
     * 
     * @return 接口类型数据字典.
     */
    public String getInterTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型,
            this.getInterType())) ? SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型,
                this.getInterType()) : this.getInterType();
    }

}
