/*
 * 文件名：IbuComponentRiskService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.ibu.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDetDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteCrepDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteEtrReDto;
import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultDto;
import com.joyintech.background.component.uplink.interbank.business.service.IbuMainService;
import com.joyintech.background.component.uplink.interbank.common.DictCodeList;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.bankuplink.common.service.CfetsQuotedCommonService;
import com.joyintech.service.bankuplink.ibu.dto.risk.ReqRiskOccupy;
import com.joyintech.service.bankuplink.ibu.dto.risk.RiskOccupyBean;
import com.joyintech.service.bankuplink.ibu.dto.risk.RiskOccupyParam;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 银行间上行风险处理Service
 * @author etliu
 * @version 1.0 2021年1月26日
 * @see IbuComponentRiskService
 * @since 1.0
 */
@Service
public class IbuComponentRiskService extends BaseService {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsQuotedCommonService cfetsQuotedCommonService;
    
    /**
     * 银行间上行组件服务统一接口
     */
    @Autowired
    private IbuMainService ibuMainService;
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IbuComponentRiskService.class);

    /**
     * 成交后风险处理
     * @param quoteResultDto 参数
     */
    public void excuteRiskHandle(QuoteResultDto quoteResultDto) {
        List<ReqRiskOccupy> reqList = new ArrayList<ReqRiskOccupy>();

        // 现券对话报价
        if (quoteResultDto.getBondDialogueQuote() != null) {
            if (StringUtil.equals(DictCodeList.银行间上行报价状态_已成交.getValue(),
                quoteResultDto.getBondDialogueQuote().getIbuQuoteBondDto().getTradeStatus())) {
                // 银行间报价主信息
                IbuQuoteBondDto ibuQuoteBondDto = quoteResultDto.getBondDialogueQuote().getIbuQuoteBondDto();
                // 银行间报价金额基对手信息
                IbuQuoteBondDetDto ibuQuoteBondDetDto = quoteResultDto.getBondDialogueQuote().getIbuQuoteBondDetDto();

                // modify by etliu 20210318 银行间上下行多组合 start
                // 交易对手代码
                String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                    ibuQuoteBondDetDto.getCpPartyId());
                if (StringUtil.isEmptyList(quoteResultDto.getIbuQuoteEtrReDtoList())) {
                    // 单组合处理
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteBondDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                    reqRiskOccupy.setEventId(ibuQuoteBondDto.getQuoteId());
                    reqRiskOccupy.setPortfolioCode(ibuQuoteBondDto.getPortfolioId());
                    reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                    reqRiskOccupy.setAmount(ibuQuoteBondDetDto.getFpriceAmt());
                    reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setTradeNum(ibuQuoteBondDetDto.getShareAmt());
                    if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                    }
                    else {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                    }
                    reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                    reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    reqRiskOccupy.setTradeId(ibuQuoteBondDto.getQuoteId());
                    reqList.add(reqRiskOccupy);
                }
                else {
                    // 多组合处理
                    for (IbuQuoteEtrReDto ibuQuoteEtrReDto : quoteResultDto.getIbuQuoteEtrReDtoList()) {
                        ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                        reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                        reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                        String eventId = ibuQuoteBondDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                        reqRiskOccupy.setEventId(eventId);
                        reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                        reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                        reqRiskOccupy.setAssetType(
                            com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                        reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                        reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                        reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                        if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                            reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                        }
                        else {
                            reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                        }
                        reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                        reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                        reqRiskOccupy.setEventType("1");
                        reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                        reqRiskOccupy.setCounterId(counterId);
                        reqRiskOccupy.setTradeId(eventId);
                        reqList.add(reqRiskOccupy);
                    }
                }
             // modify by etliu 20210318 银行间上下行多组合 end
            }
        }
        // 现券请求报价
        else if (quoteResultDto.getBondRequestQuote() != null) {
            if (StringUtil.equals(DictCodeList.银行间上行报价状态_已成交.getValue(),
                quoteResultDto.getBondRequestQuote().getIbuQuoteBondDto().getTradeStatus())) {
                // 银行间报价主信息
                IbuQuoteBondDto ibuQuoteBondDto = quoteResultDto.getBondRequestQuote().getIbuQuoteBondDto();
                // 银行间报价金额基对手信息列表
                List<IbuQuoteBondDetDto> ibuQuoteBondDetDtoList = quoteResultDto.getBondRequestQuote().getIbuQuoteBondDetDtoList();
                IbuQuoteBondDetDto ibuQuoteBondDetDto = ibuQuoteBondDetDtoList.stream().filter(
                    x -> StringUtil.equals(DictCodeList.银行间上行报价状态_已成交.getValue(),
                        x.getTradeStatus())).findFirst().get();

                // modify by etliu 20210318 银行间上下行多组合 start
                // 交易对手代码
                String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                    ibuQuoteBondDetDto.getCpPartyId());

                if (StringUtil.isEmptyList(quoteResultDto.getIbuQuoteEtrReDtoList())) {
                    // 单组合处理
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteBondDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                    reqRiskOccupy.setEventId(ibuQuoteBondDto.getQuoteId());
                    reqRiskOccupy.setPortfolioCode(ibuQuoteBondDto.getPortfolioId());
                    reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                    reqRiskOccupy.setAmount(ibuQuoteBondDetDto.getFpriceAmt());
                    reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setTradeNum(ibuQuoteBondDetDto.getShareAmt());
                    if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                    }
                    else {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                    }
                    reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                    reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    reqRiskOccupy.setTradeId(ibuQuoteBondDto.getQuoteId());
                    reqList.add(reqRiskOccupy);
                }
                else {
                    // 多组合处理
                    for (IbuQuoteEtrReDto ibuQuoteEtrReDto : quoteResultDto.getIbuQuoteEtrReDtoList()) {
                        ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                        reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                        reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                        String eventId = ibuQuoteBondDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                        reqRiskOccupy.setEventId(eventId);
                        reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                        reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                        reqRiskOccupy.setAssetType(
                            com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                        reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                        reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                        reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                        if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                            reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                        }
                        else {
                            reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                        }
                        reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                        reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                        reqRiskOccupy.setEventType("1");
                        reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                        reqRiskOccupy.setCounterId(counterId);
                        reqRiskOccupy.setTradeId(eventId);
                        reqList.add(reqRiskOccupy);
                    }
                }
             // modify by etliu 20210318 银行间上下行多组合 end
            }
        }
        // 质押式回购报价
        else if (quoteResultDto.getRepoDialogueQuote() != null) {
            if (StringUtil.equals(DictCodeList.银行间上行报价状态_已成交.getValue(),
                quoteResultDto.getRepoDialogueQuote().getIbuQuoteCrepDto().getTradeStatus())) {
                // 回购报价主信息
                IbuQuoteCrepDto ibuQuoteCrepDto = quoteResultDto.getRepoDialogueQuote().getIbuQuoteCrepDto();

             // modify by etliu 20210318 银行间上下行多组合 start
                // 交易对手代码
                String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                    ibuQuoteCrepDto.getCpPartyId());

                if (StringUtil.isEmptyList(quoteResultDto.getIbuQuoteEtrReDtoList())) {
                    // 单组合处理
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteCrepDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteCrepDto.getQuoteDate()));
                    reqRiskOccupy.setEventId(ibuQuoteCrepDto.getQuoteId());
                    reqRiskOccupy.setPortfolioCode(ibuQuoteCrepDto.getPortfolioId());
                    reqRiskOccupy.setPs(ibuQuoteCrepDto.getRepoPs());
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_质押式回购.getValue());
                    reqRiskOccupy.setPriceAmt(ibuQuoteCrepDto.getRate());
                    reqRiskOccupy.setTradeNum(ibuQuoteCrepDto.getShareAmt());
                    reqRiskOccupy.setAssetType2(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                    reqRiskOccupy.setRepurchaseType(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                    reqRiskOccupy.setAmount(ibuQuoteCrepDto.getFpriceAmt());
                    // 投管要求协议回购这里传入起息日,到期日
                    reqRiskOccupy.setVdate(DateUtil.format(ibuQuoteCrepDto.getvSettleDate()));
                    reqRiskOccupy.setMdate(DateUtil.format(ibuQuoteCrepDto.getmSettleDate()));
                    reqRiskOccupy.setAssetRate(ArithmeticUtil.multiply(ibuQuoteCrepDto.getRate(), BigDecimal.valueOf(100)));
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                    reqRiskOccupy.setCcy(ibuQuoteCrepDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    reqRiskOccupy.setTradeId(ibuQuoteCrepDto.getQuoteId());
                    reqList.add(reqRiskOccupy);
                }
                else {
                    // 多组合处理
                    for (IbuQuoteEtrReDto ibuQuoteEtrReDto : quoteResultDto.getIbuQuoteEtrReDtoList()) {
                        ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                        reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                        reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteCrepDto.getQuoteDate()));
                        String eventId = ibuQuoteCrepDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                        reqRiskOccupy.setEventId(eventId);
                        reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                        reqRiskOccupy.setPs(ibuQuoteCrepDto.getRepoPs());
                        reqRiskOccupy.setAssetType(
                            com.joyintech.tams.constant.DictCodeList.平安资产类型_质押式回购.getValue());
                        reqRiskOccupy.setPriceAmt(ibuQuoteCrepDto.getRate());
                        reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                        reqRiskOccupy.setAssetType2(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                        reqRiskOccupy.setRepurchaseType(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                        reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                        // 投管要求协议回购这里传入起息日,到期日
                        reqRiskOccupy.setVdate(DateUtil.format(ibuQuoteCrepDto.getvSettleDate()));
                        reqRiskOccupy.setMdate(DateUtil.format(ibuQuoteCrepDto.getmSettleDate()));
                        reqRiskOccupy.setAssetRate(ArithmeticUtil.multiply(ibuQuoteCrepDto.getRate(), BigDecimal.valueOf(100)));
                        reqRiskOccupy.setEventType("1");
                        reqRiskOccupy.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
                        reqRiskOccupy.setCcy(ibuQuoteCrepDto.getSettCcy());
                        reqRiskOccupy.setCounterId(counterId);
                        reqRiskOccupy.setTradeId(eventId);
                        reqList.add(reqRiskOccupy);
                    }
                }
            }
        }

        if (reqList.size() > 0) {
            RiskOccupyParam riskOccupyParam = new RiskOccupyParam();
            riskOccupyParam.setAssetType(reqList.get(0).getAssetType());
            riskOccupyParam.setEventType("1");
            riskOccupyParam.setRskList(reqList);
            riskOccupyParam.setIsNeedRiskCheck(DictCodeList.是否_否.getValue());
            logger.info("银行间上行成交后风险处理 参数:{}", JSONObject.toJSONString(riskOccupyParam));
            try {
                RiskOccupyBean riskOccupyBean = restTemplate.postForObject(
                    ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/risk/doOccupyBatchData",
                    riskOccupyParam, RiskOccupyBean.class);
                logger.info("银行间上行成交后风险处理 结果:{}", JSONObject.toJSONString(riskOccupyBean));
            }
            catch (Exception e) {
                e.printStackTrace();
                logger.error("银行间上行成交后风险处理异常, 异常信息:{}", e.getMessage());
            }
        }
    }
    
    /**
     * 历史报价撤销风险处理
     * @param quoteResultDto 参数
     */
    public void excuteRiskHandleForHisRevoke(QuoteResultDto quoteResultDto) {
        List<ReqRiskOccupy> reqList = new ArrayList<ReqRiskOccupy>();
        
        // 现券对话报价
        if (quoteResultDto.getBondDialogueQuote() != null) {

            // 银行间报价主信息
            IbuQuoteBondDto ibuQuoteBondDto = quoteResultDto.getBondDialogueQuote().getIbuQuoteBondDto();
            // 银行间报价金额基对手信息
            IbuQuoteBondDetDto ibuQuoteBondDetDto = quoteResultDto.getBondDialogueQuote().getIbuQuoteBondDetDto();
            
            // modify by etliu 20210318 银行间上下行多组合 start
            // 银行间报价指令关系业务表
            List<IbuQuoteEtrReDto> ibuQuoteEtrReDtoList = quoteResultDto.getIbuQuoteEtrReDtoList();
            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                ibuQuoteEtrReDtoList = ibuMainService.selectQuoteEtrReDataByQuoteId(ibuQuoteBondDto.getQuoteId());
            }
            // 交易对手代码
            String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                ibuQuoteBondDetDto.getCpPartyId());
            
            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                // 单组合处理
                ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                reqRiskOccupy.setEntrustCode(ibuQuoteBondDto.getEntrustId());
                reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                reqRiskOccupy.setEventId(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setTradeId(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setSeqNo(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setRevokeFlg(DictCodeList.是否_是.getValue());
                reqRiskOccupy.setPortfolioCode(ibuQuoteBondDto.getPortfolioId());
                if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                    reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                }
                else {
                    reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                }
                reqRiskOccupy.setAssetType(
                    com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                reqRiskOccupy.setAmount(ibuQuoteBondDetDto.getFpriceAmt());
                reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                reqRiskOccupy.setTradeNum(ibuQuoteBondDetDto.getShareAmt());
                reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                reqRiskOccupy.setEventType("1");
                reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                reqRiskOccupy.setCounterId(counterId);
                
                reqList.add(reqRiskOccupy);
            }
            else {
                // 多组合处理
                for (IbuQuoteEtrReDto ibuQuoteEtrReDto : ibuQuoteEtrReDtoList) {
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                    String eventId = ibuQuoteBondDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                    reqRiskOccupy.setEventId(eventId);
                    reqRiskOccupy.setTradeId(eventId);
                    reqRiskOccupy.setSeqNo(eventId);
                    reqRiskOccupy.setRevokeFlg(DictCodeList.是否_是.getValue());
                    reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                    if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                    }
                    else {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                    }
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                    reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                    reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                    reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                    reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                    reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    
                    reqList.add(reqRiskOccupy);
                }
            }
         // modify by etliu 20210318 银行间上下行多组合 end
        }
        // 现券请求报价
        else if (quoteResultDto.getBondRequestQuote() != null) {

            // 银行间报价主信息
            IbuQuoteBondDto ibuQuoteBondDto = quoteResultDto.getBondRequestQuote().getIbuQuoteBondDto();
            // 银行间报价金额基对手信息列表
            List<IbuQuoteBondDetDto> ibuQuoteBondDetDtoList = quoteResultDto.getBondRequestQuote().getIbuQuoteBondDetDtoList();
            IbuQuoteBondDetDto ibuQuoteBondDetDto = ibuQuoteBondDetDtoList.stream().filter(
                x -> StringUtil.isNotBlank(x.getExecid())).findFirst().get();
            
            if (ibuQuoteBondDetDto == null) {
                return;
            }
            
            // modify by etliu 20210318 银行间上下行多组合 start
            // 银行间报价指令关系业务表
            List<IbuQuoteEtrReDto> ibuQuoteEtrReDtoList = quoteResultDto.getIbuQuoteEtrReDtoList();
            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                ibuQuoteEtrReDtoList = ibuMainService.selectQuoteEtrReDataByQuoteId(ibuQuoteBondDto.getQuoteId());
            }
            // 交易对手代码
            String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                ibuQuoteBondDetDto.getCpPartyId());

            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                // 单组合处理
                ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                reqRiskOccupy.setEntrustCode(ibuQuoteBondDto.getEntrustId());
                reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                reqRiskOccupy.setEventId(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setTradeId(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setSeqNo(ibuQuoteBondDto.getQuoteId());
                reqRiskOccupy.setRevokeFlg(DictCodeList.是否_是.getValue());
                reqRiskOccupy.setPortfolioCode(ibuQuoteBondDto.getPortfolioId());
                if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                    reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                }
                else {
                    reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                }
                reqRiskOccupy.setAssetType(
                    com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                reqRiskOccupy.setAmount(ibuQuoteBondDetDto.getFpriceAmt());
                reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                reqRiskOccupy.setTradeNum(ibuQuoteBondDetDto.getShareAmt());
                reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                reqRiskOccupy.setEventType("1");
                reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                reqRiskOccupy.setCounterId(counterId);
                
                reqList.add(reqRiskOccupy);
            }
            else {
                // 多组合处理
                for (IbuQuoteEtrReDto ibuQuoteEtrReDto : ibuQuoteEtrReDtoList) {
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteBondDto.getQuoteDate()));
                    String eventId = ibuQuoteBondDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                    reqRiskOccupy.setEventId(eventId);
                    reqRiskOccupy.setTradeId(eventId);
                    reqRiskOccupy.setSeqNo(eventId);
                    reqRiskOccupy.setRevokeFlg(DictCodeList.是否_是.getValue());
                    reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                    if (StringUtil.isNotBlank(ibuQuoteBondDto.getFinprodMarketId())) {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodMarketId());
                    }
                    else {
                        reqRiskOccupy.setAssetCode(ibuQuoteBondDto.getFinprodId());
                    }
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_债券.getValue());
                    reqRiskOccupy.setPs(ibuQuoteBondDto.getPs());
                    reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                    reqRiskOccupy.setPriceAmt(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                    reqRiskOccupy.setNetPrice(ibuQuoteBondDetDto.getUnitCprice());
                    reqRiskOccupy.setAllPrice(ibuQuoteBondDetDto.getUnitFprice());
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setCcy(ibuQuoteBondDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    
                    reqList.add(reqRiskOccupy);
                }
            }
         // modify by etliu 20210318 银行间上下行多组合 end
        }
        // 质押式回购报价
        else if (quoteResultDto.getRepoDialogueQuote() != null) {

            // 回购报价主信息
            IbuQuoteCrepDto ibuQuoteCrepDto = quoteResultDto.getRepoDialogueQuote().getIbuQuoteCrepDto();
            
         // modify by etliu 20210318 银行间上下行多组合 start
            // 银行间报价指令关系业务表
            List<IbuQuoteEtrReDto> ibuQuoteEtrReDtoList = quoteResultDto.getIbuQuoteEtrReDtoList();
            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                ibuQuoteEtrReDtoList = ibuMainService.selectQuoteEtrReDataByQuoteId(ibuQuoteCrepDto.getQuoteId());
            }
            // 交易对手代码
            String counterId = cfetsQuotedCommonService.selectCounterIdByCpPartyId(
                ibuQuoteCrepDto.getCpPartyId());

            if (StringUtil.isEmptyList(ibuQuoteEtrReDtoList)) {
                // 单组合处理
                ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                reqRiskOccupy.setEntrustCode(ibuQuoteCrepDto.getEntrustId());
                reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteCrepDto.getQuoteDate()));
                reqRiskOccupy.setTradeId(ibuQuoteCrepDto.getQuoteId());
                reqRiskOccupy.setEventId(ibuQuoteCrepDto.getQuoteId());
                reqRiskOccupy.setSeqNo(ibuQuoteCrepDto.getQuoteId());
                reqRiskOccupy.setPortfolioCode(ibuQuoteCrepDto.getPortfolioId());
                reqRiskOccupy.setAssetType(
                    com.joyintech.tams.constant.DictCodeList.平安资产类型_质押式回购.getValue());
                reqRiskOccupy.setAssetType2(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                reqRiskOccupy.setRepurchaseType(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                reqRiskOccupy.setAmount(ibuQuoteCrepDto.getFpriceAmt());
                // 投管要求协议回购这里传入起息日,到期日
                reqRiskOccupy.setVdate(DateUtil.format(ibuQuoteCrepDto.getvSettleDate()));
                reqRiskOccupy.setMdate(DateUtil.format(ibuQuoteCrepDto.getmSettleDate()));
                reqRiskOccupy.setPs(ibuQuoteCrepDto.getRepoPs());
//                reqRiskOccupy.setAmount(ibuQuoteCrepDto.getFpriceAmt());
                reqRiskOccupy.setPriceAmt(ibuQuoteCrepDto.getRate());
                reqRiskOccupy.setTradeNum(ibuQuoteCrepDto.getShareAmt());
                reqRiskOccupy.setAssetRate(ArithmeticUtil.multiply(ibuQuoteCrepDto.getRate(), BigDecimal.valueOf(100)));
                reqRiskOccupy.setEventType("1");
                reqRiskOccupy.setCcy(ibuQuoteCrepDto.getSettCcy());
                reqRiskOccupy.setCounterId(counterId);
                
                reqList.add(reqRiskOccupy);
            }
            else {
                // 多组合处理
                for (IbuQuoteEtrReDto ibuQuoteEtrReDto : ibuQuoteEtrReDtoList) {
                    ReqRiskOccupy reqRiskOccupy = new ReqRiskOccupy();
                    reqRiskOccupy.setEntrustCode(ibuQuoteEtrReDto.getEntrustId());
                    reqRiskOccupy.setEventDate(DateUtil.format(ibuQuoteCrepDto.getQuoteDate()));
                    String eventId = ibuQuoteCrepDto.getQuoteId() + "_" + ibuQuoteEtrReDto.getEntrustId();
                    reqRiskOccupy.setTradeId(eventId);
                    reqRiskOccupy.setEventId(eventId);
                    reqRiskOccupy.setSeqNo(eventId);
                    reqRiskOccupy.setPortfolioCode(ibuQuoteEtrReDto.getPortfolioId());
                    reqRiskOccupy.setAssetType(
                        com.joyintech.tams.constant.DictCodeList.平安资产类型_质押式回购.getValue());
                    reqRiskOccupy.setAssetType2(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                    reqRiskOccupy.setRepurchaseType(BussinessDictCodeList.回购类型_质押式协议回购.getValue());
                    reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                    // 投管要求协议回购这里传入起息日,到期日
                    reqRiskOccupy.setVdate(DateUtil.format(ibuQuoteCrepDto.getvSettleDate()));
                    reqRiskOccupy.setMdate(DateUtil.format(ibuQuoteCrepDto.getmSettleDate()));
                    reqRiskOccupy.setPs(ibuQuoteCrepDto.getRepoPs());
//                    reqRiskOccupy.setAmount(ibuQuoteEtrReDto.getFpriceAmt());
                    reqRiskOccupy.setPriceAmt(ibuQuoteCrepDto.getRate());
                    reqRiskOccupy.setTradeNum(ibuQuoteEtrReDto.getShareAmt());
                    reqRiskOccupy.setAssetRate(ArithmeticUtil.multiply(ibuQuoteCrepDto.getRate(), BigDecimal.valueOf(100)));
                    reqRiskOccupy.setEventType("1");
                    reqRiskOccupy.setCcy(ibuQuoteCrepDto.getSettCcy());
                    reqRiskOccupy.setCounterId(counterId);
                    
                    reqList.add(reqRiskOccupy);
                }
            }
        }
        
        if (reqList.size() > 0) {
            RiskOccupyParam riskOccupyParam = new RiskOccupyParam();
            riskOccupyParam.setAssetType(reqList.get(0).getAssetType());
            riskOccupyParam.setEventType("1");
            riskOccupyParam.setRskList(reqList);
            riskOccupyParam.setIsNeedRiskCheck(DictCodeList.是否_否.getValue());
            logger.info("历史报价撤销风险处理 参数:{}", JSONObject.toJSONString(riskOccupyParam));
            try {
                RiskOccupyBean riskOccupyBean = restTemplate.postForObject(
                    ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/risk/doOccupyReleasBatchData",
                    riskOccupyParam, RiskOccupyBean.class);
                logger.info("历史报价撤销风险处理 结果:{}", JSONObject.toJSONString(riskOccupyBean));
            }
            catch (Exception e) {
                e.printStackTrace();
                logger.error("历史报价撤销风险处理异常, 异常信息:{}", e.getMessage());
            }
        }
    }
}
