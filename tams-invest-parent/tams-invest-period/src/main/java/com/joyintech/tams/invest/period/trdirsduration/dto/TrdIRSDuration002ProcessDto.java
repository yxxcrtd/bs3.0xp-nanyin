package com.joyintech.tams.invest.period.trdirsduration.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.tams.invest.period.trdirsduration.validator.TrdIRSDuration002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 利率互换存续期交易Entity
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.dto
 * @Description: 页面查询参数
 * @date Date : 2019年08月10日
 */
@PageId("TrdIRSDuration002")
@ValidLogic(targetClass = TrdIRSDuration002DtoValidator.class, groups = { ValidateGroupDelete.class,
        ValidateGroupEdit.class })
public class TrdIRSDuration002ProcessDto extends TrdIRSDurationDto implements ProcessEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9087164315779376712L;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

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
     * 交易编号
     * 
     * @return
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 获取 mdateStr
     * 
     * @return mdateStr.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getMdateStr() {
        if (super.getFixMdate() == null) {
            if (super.getFloatMdate() == null) {
                return "";
            }
            return DateUtil.format(super.getFloatMdate());
        }
        return DateUtil.format(super.getFixMdate());
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    @NotEmpty(fieldName = "划款指令", groups = { ValidateGroupEdit.class })
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        if (bussTrdTransferOrder == null || bussTrdTransferOrder.isEmpty()) {
            return null;
        }
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder
     *            bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
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
}
