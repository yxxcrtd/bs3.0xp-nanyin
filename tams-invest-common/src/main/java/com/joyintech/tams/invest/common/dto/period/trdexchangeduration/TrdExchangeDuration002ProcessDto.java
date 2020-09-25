package com.joyintech.tams.invest.common.dto.period.trdexchangeduration;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 外汇远期掉期到期交易自动生成流程Dto
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @Project: tams-invest-parent
 * @Package com.joyintech.tams.invest.period.trdexchangeduration.dto
 * @Description: 页面查询参数
 */
@PageId("TrdForwexInvestTrade002")
public class TrdExchangeDuration002ProcessDto extends TrdExchangeDurationDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -312651500800648347L;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    public String getBusiType() {
        return BussinessDictCodeList.共同业务类型_债券存续期交易.getValue();
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    @Override
    public String getBusiCode() {
        return super.getBusiCode();
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    @NotEmpty(fieldName = "划款指令", groups = {ValidateGroupEdit.class})
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        if (bussTrdTransferOrder == null || bussTrdTransferOrder.isEmpty()) {
            return null;
        }
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
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
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
