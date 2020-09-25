package com.joyintech.tams.invest.period.trdirsduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 利率互换存续期交易Entity
 *
 * @author : xkni
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.dto
 * @Description: 页面查询参数
 * @date Date : 2019年08月15日
 */
@PageId("TrdIRSDuration004")

public class TrdIRSDuration004ProcessDto extends TrdIRSDurationDto implements ProcessEntity {

    /**
     * 页面元素
     */
    private String elementId;


    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, getStatusValid());
        return result == null ? getStatusValid() : result;
    }

    /**
     * 获取 PaBussTypeString
     *
     * @return PaBussTypeString
     */
    public String getPaBussTypeString() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null
                    && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_债券中间还本.getCodeName();
            } else if (getCashType() != null
                    && (getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())
                    || getCashType().contains(DictCodeList.现金流类型_到期收息.getValue()))) {
                return DictCodeListExt1.平安业务类型_债券到期.getCodeName();
            } else {
                return DictCodeListExt1.平安业务类型_债券中间收息.getCodeName();
            }
        } else {
            return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBussType());
        }
    }

    @Override
    public String getPaBussType() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null
                    && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_债券中间还本.getValue();
            } else if (getCashType() != null
                    && (getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())
                    || getCashType().contains(DictCodeList.现金流类型_到期收息.getValue()))) {
                return DictCodeListExt1.平安业务类型_债券到期.getValue();
            } else {
                return DictCodeListExt1.平安业务类型_债券中间收息.getValue();
            }
        } else {
            return super.getPaBussType();
        }
    }

    /**
     * 交易编号
     *
     * @return
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    @PrimaryKey(Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_债券类资产存续期.getValue();
    }

    @Override
    @PrimaryKey(Field.PK_3)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode.
     */
    @Override
    @PrimaryKey(Field.PK_5)
    public String getBusiCode() {
        return super.getBusiCode();
    }


    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId
     *            elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    private   List<TrdIRSDurationDto> fixedPaymentPlanTable;
    private   List<TrdIRSDurationDto> floatPaymentPlanTable;

    public List<TrdIRSDurationDto> getFixedPaymentPlanTable() {
        return fixedPaymentPlanTable;
    }

    public void setFixedPaymentPlanTable(List<TrdIRSDurationDto> fixedPaymentPlanTable) {
        this.fixedPaymentPlanTable = fixedPaymentPlanTable;
    }

    public List<TrdIRSDurationDto> getFloatPaymentPlanTable() {
        return floatPaymentPlanTable;
    }

    public void setFloatPaymentPlanTable(List<TrdIRSDurationDto> floatPaymentPlanTable) {
        this.floatPaymentPlanTable = floatPaymentPlanTable;
    }

    /**
     * 固息现金流编号
     *
     * @return fixedCashId
     */
    private  String fixedCashId;
    /**
     * 浮息现金流编号
     *
     * @return floatCashId
     */
    private  String floatCashId;

    public String getFixedCashId() {
        return fixedCashId;
    }

    public void setFixedCashId(String fixedCashId) {
        this.fixedCashId = fixedCashId;
    }

    @Override
    public String getFloatCashId() {
        return floatCashId;
    }

    @Override
    public void setFloatCashId(String floatCashId) {
        this.floatCashId = floatCashId;
    }

    /**
     * 现金流计划信息
     *
     * @return list.
     */
    private   List<FinCashPlanEntity> paymentPlanList;
    private  String paymentPlanListMsg;

    public String getPaymentPlanListMsg() {
        return paymentPlanListMsg;
    }

    public void setPaymentPlanListMsg(String paymentPlanListMsg) {
        this.paymentPlanListMsg = paymentPlanListMsg;
    }

    public List<FinCashPlanEntity> getPaymentPlanList() {
        return paymentPlanList;
    }

    public void setPaymentPlanList(List<FinCashPlanEntity> paymentPlanList) {
        this.paymentPlanList = paymentPlanList;
    }
}
