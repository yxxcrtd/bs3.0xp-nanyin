package com.joyintech.tams.exchange.service.trade.management.service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.HistoryTrade;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.constant.PresentOrHistoryQryType;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.db.domain.TradeHistory;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.TradeHistoryService;
import com.joyintech.tams.exchange.datacommon.db.service.TradeService;
import com.joyintech.tams.exchange.service.trade.processor.TradeHelper;
import com.joyintech.tams.exchange.service.trade.processor.others.TerminalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TradeManagement {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeHelper tradeHelper;
    @Autowired
    private TerminalHelper terminalHelper;
    @Autowired
    private TradeHistoryService tradeHistoryService;
    @Autowired
    private CommonManagement commonManagement;

    /**
     * 根据Entrust行数据查询Trade表中数据
     */
    public ResultBean queryTrade(String userId, String entrustId, String instructionId, String accountId, String orderId) {
        List<String> ptlAuthList = commonManagement.getPtlAuthInfo(userId);//组合权限查询
        if (Objects.isNull(ptlAuthList) || ptlAuthList.size() == 0) {
            return new ResultBean(ErrorCode.SUCCESS, new ArrayList());
        }
        if (!Strings.isNullOrEmpty(entrustId)) {
            List<Trade> list = tradeService.queryTradeByEntrust(entrustId);
            list = list.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
            setFutureStockCode(list);
            return new ResultBean(ErrorCode.SUCCESS, list);
        }
        if (!Strings.isNullOrEmpty(orderId)) {
            List<Trade> list = tradeService.queryTradeByOrder(orderId);
            list = list.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
            setFutureStockCode(list);
            return new ResultBean(ErrorCode.SUCCESS, list);
        }
        if (!Strings.isNullOrEmpty(instructionId)) {
            List<Trade> list = tradeService.queryTradeByInstructionId(instructionId);
            list = list.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
            setFutureStockCode(list);
            return new ResultBean(ErrorCode.SUCCESS, list);
        }
        if (!Strings.isNullOrEmpty(accountId)) {
            List<Trade> list = tradeService.queryTradeByAccountId(accountId);
            list = list.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
            setFutureStockCode(list);
            return new ResultBean(ErrorCode.SUCCESS, list);
        } else {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        }
    }

    private void setFutureStockCode(List<Trade> list) {
        list.forEach(trade -> {
            if (InvestmentAssetType.isFutures(trade.getAssetType())) {
                trade.setStockCode(StockCodeUtils.getStockCode(trade.getStockCode()));
            }
        });
    }

    public ResultBean queryHistoryTrade(String startDay, String endDay, String accountId, String postStr, int pageSize, String userId) {
        List<String> ptlAuthList = commonManagement.getPtlAuthInfo(userId);//组合权限查询
        if (Objects.isNull(ptlAuthList) || ptlAuthList.size() == 0) {
            return new ResultBean(ErrorCode.SUCCESS, new ArrayList());
        }
        List<HistoryTrade> historyTrade = terminalHelper.queryHistoryTrade(startDay, endDay, accountId, postStr, pageSize);
        historyTrade = historyTrade.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
        return new ResultBean(ErrorCode.SUCCESS, historyTrade);
    }

    public ResultBean queryAllForPage(int pageNum, int pageSize, int historyType, String userId, String startDay, String endDay, String accountId, String market, String stockCode, String entrustProp, String entrustId) {
        List<String> ptlAuthList = commonManagement.getPtlAuthInfo(userId);//组合权限查询
        if (Objects.isNull(ptlAuthList) || ptlAuthList.size() == 0) {
            if (!Strings.isNullOrEmpty(entrustId)) {
                return new ResultBean(ErrorCode.SUCCESS, new ArrayList<>());
            }
            return new ResultBean(ErrorCode.SUCCESS, new PageInfo());
        }
        if (historyType == PresentOrHistoryQryType.PRESENT.value) {
            if (!Strings.isNullOrEmpty(entrustId)) {
                List<Trade> tradeList = tradeService.queryTradeByEntrust(entrustId);
                tradeList = tradeList.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
                return new ResultBean(ErrorCode.SUCCESS, tradeList);
            }
            PageInfo<Trade> tradePageInfo = tradeService.queryAllForPage(pageNum, pageSize, userId, accountId, market, stockCode, entrustProp, ptlAuthList);
            return new ResultBean(ErrorCode.SUCCESS, tradePageInfo);
        } else if (historyType == PresentOrHistoryQryType.HISTORY.value) {
            if (!Strings.isNullOrEmpty(entrustId)) {
                List<TradeHistory> tradeList = tradeHistoryService.queryTradeHistoryByEntrust(entrustId);
                tradeList = tradeList.stream().filter(trade -> ptlAuthList.contains(trade.getPortfolioId())).collect(Collectors.toList());
                return new ResultBean(ErrorCode.SUCCESS, tradeList);
            }
            PageInfo<TradeHistory> tradeHistoryPageInfo = tradeHistoryService.queryAllForPage(pageNum, pageSize, userId, startDay, endDay, accountId, market, stockCode, entrustProp, ptlAuthList);
            return new ResultBean(ErrorCode.SUCCESS, tradeHistoryPageInfo);
        } else {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        }
    }
}
