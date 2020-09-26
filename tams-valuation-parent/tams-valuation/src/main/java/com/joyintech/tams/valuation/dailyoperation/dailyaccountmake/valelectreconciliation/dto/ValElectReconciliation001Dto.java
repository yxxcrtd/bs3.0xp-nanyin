package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;

public class ValElectReconciliation001Dto extends BaseEntity implements ITableRow {

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
    @NotEmpty(fieldName = "产品代码")
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
     * 对账对象
     */
    private String interType;

    /**
     * 对账状态
     */
    private String checkStatus;

    /**
     * 估值日期
     */
    @NotEmpty(fieldName = "估值日期")
    private String valDate;

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
     * 获取对账对象
     * 
     * @return
     */
    public String getInterType() {
        return interType;
    }

    /**
     * 设置对账对象
     * 
     * @param interType
     */
    public void setInterType(String interType) {
        this.interType = interType == null ? null : interType.trim();
    }

    /**
     * 获取 对账对象数据字典
     * 
     * @return 对账对象数据字典.
     */
    public String getInterTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型,
            this.getInterType())) ? SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型,
                this.getInterType()) : this.getInterType();
    }

    /**
     * 获取对账状态
     * 
     * @return
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置对账状态
     * 
     * @param checkStatus
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    /**
     * 获取 对账状态数据字典
     * 
     * @return 对账状态数据字典.
     */
    public String getCheckStatusFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.深圳通对账状态,
            this.getCheckStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.深圳通对账状态,
                this.getCheckStatus()) : this.getCheckStatus();
    }

    /**
     * 获取估值日期
     * 
     * @return
     */
    public String getValDate() {
        return valDate;
    }

    /**
     * 设置估值日期
     * 
     * @param valDate
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }
}
