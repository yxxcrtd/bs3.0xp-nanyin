package com.joyintech.tams.invest.trade.bondmarkettrade.dto;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdFinProductBarterTotal;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.bondmarkettrade.validator.TrdBondMarketTradeDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 债券上市流通交易Dto
 *
 * @author 马数凯
 * @version 1.0 2021年3月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto
 * @since 1.0
 */
@PageId("TrdBondMarketTradeDto")
@ValidLogic(targetClass = TrdBondMarketTradeDtoValidator.class, groups = ValidateGroupEdit.class)
public class TrdBondMarketTradeDto extends TrdFinProductDealTotal implements ProcessEntity {
    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 发行代码
     */
    private String finprodMarketId;

    /**
     * 发行简称
     */
    private String finprodAbbr;

    /**
     * 上市代码
     */
    private String appearMarketId;

    /**
     * 上市简称
     */
    private String appearMarketAbbr;

    /**
     * 是否加券
     */
    private String isAdded;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 产品交易Dto
     */
    FinProductTradeDto finProductTradeDto;


    /**
     * 金融产品实物汇总交易DTO
     */
    TrdFinProductBarterTotal trdFinProductBarterTotal;

    /**
     * 序号
     */
    private String rowNum;

    /**
     * 指令可用持仓流水
     */
    List<EtrSecPositionFlowDto> etrSecPositionFlows;

    /**
     * 组合可用持仓流水
     */
    List<PtlSecPositionFlowDto> ptlSecPositionFlows;

    /**
     * 组装后交易数据
     */
    List<BaseTradeInfoDto> baseTradeInfoDtos = new ArrayList<BaseTradeInfoDto>();

    /**
     * 获取组装后交易数据
     */
    public List<BaseTradeInfoDto> getBaseTradeInfoDtos() {
        return baseTradeInfoDtos;
    }

    /**
     * 设置组装后交易数据
     */
    public void setBaseTradeInfoDtos(List<BaseTradeInfoDto> baseTradeInfoDtos) {
        this.baseTradeInfoDtos = baseTradeInfoDtos;
    }

    /**
     * 获取指令可用持仓流水
     */
    public List<EtrSecPositionFlowDto> getEtrSecPositionFlows() {
        return etrSecPositionFlows;
    }
    
    /**
     * 设置指令可用持仓流水
     */
    public void setEtrSecPositionFlows(List<EtrSecPositionFlowDto> etrSecPositionFlows) {
        this.etrSecPositionFlows = etrSecPositionFlows;
    }

    /**
     * 获取组合可用持仓流水
     */
    public List<PtlSecPositionFlowDto> getPtlSecPositionFlows() {
        return ptlSecPositionFlows;
    }

    /**
     * 设置组合可用持仓流水
     */
    public void setPtlSecPositionFlows(List<PtlSecPositionFlowDto> ptlSecPositionFlows) {
        this.ptlSecPositionFlows = ptlSecPositionFlows;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * 获取金融产品实物汇总交易DTO
     */
    public TrdFinProductBarterTotal getTrdFinProductBarterTotal() {
        return trdFinProductBarterTotal;
    }

    /**
     * 设置金融产品实物汇总交易DTO
     */
    public void setTrdFinProductBarterTotal(TrdFinProductBarterTotal trdFinProductBarterTotal) {
        this.trdFinProductBarterTotal = trdFinProductBarterTotal;
    }

    /**
     * 获取产品交易Dto
     */
    public FinProductTradeDto getFinProductTradeDto() {
        return finProductTradeDto;
    }


    /**
     * 设置产品交易Dto
     */
    public void setFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        this.finProductTradeDto = finProductTradeDto;
    }

    /**
     * 获取操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取画面模式
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置画面模式
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(String isAdded) {
        this.isAdded = isAdded;
    }

    /**
     * 获取资产代码
     */
    @Override
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置资产代码
     */
    @Override
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取发行代码
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置发行代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取发行简称
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置发行简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取上市代码
     */
    public String getAppearMarketId() {
        return appearMarketId;
    }

    /**
     * 设置上市代码
     */
    public void setAppearMarketId(String appearMarketId) {
        this.appearMarketId = appearMarketId;
    }

    /**
     * 获取上市简称
     */
    public String getAppearMarketAbbr() {
        return appearMarketAbbr;
    }

    /**
     * 设置上市简称
     */
    public void setAppearMarketAbbr(String appearMarketAbbr) {
        this.appearMarketAbbr = appearMarketAbbr;
    }

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 获取撤销说明
     */
    public String getRevokeReason() {
        return revokeReason;
    }
    /**
     * 设置撤销说明
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     *
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodType2() {
        return super.getFinprodType2();
    }

    /**
     * 取得 交易编号
     *
     * @return 交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTotleTradeId() {
        return super.getTotleTradeId();
    }

    /**
     * 获取 tradeMarketText
     *
     * @return tradeMarketText.
     */
    public String getTradeMarketText() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所,
                this.getTradeMarket());
    }
}
