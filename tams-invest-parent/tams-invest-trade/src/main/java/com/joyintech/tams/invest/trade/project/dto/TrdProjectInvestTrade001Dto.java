package com.joyintech.tams.invest.trade.project.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.trade.project.TrdProjectInvestTradeDto;
import com.joyintech.tams.invest.trade.project.validator.TrdProjectInvestTradeDeleteDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

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
    // add by meifaxin 2021-9-27  格式化 start
    public String getPrinAmtStr() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00");
        return getPrinAmt()==null? null:df2.format(getPrinAmt());
    }

    public String getUnitCpriceStr() {
        return getUnitCprice()==null? null:getUnitCprice().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getUnitFpriceStr() {
        return getUnitFprice()==null? null:getUnitFprice().setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getTradeAmtStr() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00");
        return getTradeAmt()==null? null:df2.format(getTradeAmt());
    }
    // add by meifaxin 2021-9-27  格式化 end
}
