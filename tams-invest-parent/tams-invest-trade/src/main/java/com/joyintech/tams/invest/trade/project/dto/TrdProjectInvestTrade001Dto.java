package com.joyintech.tams.invest.trade.project.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.trade.project.TrdProjectInvestTradeDto;
import com.joyintech.tams.invest.trade.project.validator.TrdProjectInvestTradeDeleteDtoValidator;
import com.joyintech.tams.invest.trade.project.validator.TrdProjectInvestTradeEditDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @program: tams-invest-parent
 * @description: 项目类资产 存续期 查询页面 grid dto
 * @author: 强成西
 * @create: 2018-10-10 17:15
 **/
@PageId("TrdProjectInvestTrade001")
@ValidLogic(targetClass = TrdProjectInvestTradeDeleteDtoValidator.class, groups = {ValidateGroupDelete.class})
public class TrdProjectInvestTrade001Dto extends TrdProjectInvestTradeDto {

    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, getStatusValid());
    }

    public String getProjectTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, getProjectType());
    }

    public String getPsString() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPs());
    }

    /**
     * code Name
     */
    private String statusValidString;

    /**
     * 划款指令信息
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_项目类资产交易.getValue();
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBizCode() {
        return super.getBizCode();
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
}
