package com.joyintech.tams.invest.period.trdplanintduration.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;
import com.joyintech.tams.invest.period.trdplanintduration.validator.TrdPlanInterestDurationEditDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @program: tams-invest-parent
 * @description: 计划类利率型 存续期 查询页面 grid dto
 * @author: 强成西
 * @create: 2018-10-10 17:15
 **/
@PageId("TrdPlanInterestDuration002")
@ValidLogic(targetClass = TrdPlanInterestDurationEditDtoValidator.class, groups = {ValidateGroupDelete.class, ValidateGroupEdit.class})
public class TrdPlanInterestDurationProcessDto extends TrdPlanInterestDurationDto implements ProcessEntity {

    /**
     * 页面元素
     */
    private String elementId;

    public String getType6String() {
        if (getFinAssetInfo() != null && getFinAssetInfo().getType6() != null) {
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, getFinAssetInfo().getType6());
        }
        return StringUtil.EMPTY;
    }

    public String getType7String() {
        if (getFinAssetInfo() != null && getFinAssetInfo().getType7() != null) {
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产二级分类, getFinAssetInfo().getType7());
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取 PaBussTypeString
     *
     * @return PaBussTypeString
     */
    public String getPaBussTypeString() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_中间还本.getCodeName();
            }
            else if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())) {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_到期.getCodeName();
            }
            else {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息.getCodeName();
            }
        }
        else {
            return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBussType());
        }
    }

    @Override
    public String getPaBussType() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_中间还本.getValue();
            }
            else if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())) {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_到期.getValue();
            }
            else {
                return DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息.getValue();
            }
        }
        else {
            return super.getPaBussType();
        }
    }



    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue();
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getBusiCode() {
        return super.getBusiCode();
    }

    @Override
    @NotEmpty(fieldName = "TrdPlanInterestDuration002.returnAmount", groups = {ValidateGroupEdit.class})
    public BigDecimal getReturnAmount() {
        return super.getReturnAmount();
    }

    @Override
    @NotEmpty(fieldName = "TrdPlanInterestDuration002.fpriceAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getFpriceAmt() {
        return super.getFpriceAmt();
    }

    @Override
    @NotEmpty(fieldName = "TrdPlanInterestDuration002.arrivalDate", groups = {ValidateGroupEdit.class})
    public String getArrivalDate() {
        return super.getArrivalDate();
    }

    public String getStatusValidString() {
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, super.getStatusValid());
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
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
