package com.joyintech.tams.invest.period.trdbuyoutduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.period.trdbuyoutduration.validator.TrdRepoBuyoutDuration002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 详情页面dto
 * @author: 强成西
 * @create: 2018-10-22 10:15
 **/
@PageId("TrdRepoBuyoutDuration002")
@ValidLogic(targetClass = TrdRepoBuyoutDuration002DtoValidator.class)
public class TrdRepoBuyoutDuration002Dto extends TrdRepoDurationDto implements ProcessEntity {

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_质押式回购存续期.getValue();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 划款指令信息
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    private String workflowId;

    private String elementId;

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

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
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
     * 获取 workflowId
     *
     * @return workflowId
     */
    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     *
     * @param workflowId workflowId
     */
    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 finprodType2String
     *
     * @return finprodType2String
     */
    public String getFinprodType2String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,
                getFinprodType2());
        return result == null ? getFinprodType2() : result;
    }

    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态,
                getStatusValid());
        return result == null ? getStatusValid() : result;
    }

    /**
     * 获取 type6String
     *
     * @return type6String
     */
    public String getType6String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.回购类型, getType6());
        return result == null ? getType6() : result;
    }

    /**
     * 获取 type6String
     *
     * @return type6String
     */
    public String getTradeMarketString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.交易场所, getTradeMarket());
        return result == null ? getTradeMarket() : result;
    }
}
