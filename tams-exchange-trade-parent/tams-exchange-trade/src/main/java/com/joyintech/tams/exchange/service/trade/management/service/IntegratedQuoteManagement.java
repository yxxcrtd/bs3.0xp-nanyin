package com.joyintech.tams.exchange.service.trade.management.service;

import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.trade.processor.others.TerminalHelper;
import com.joyintech.tams.exchange.service.trade.scheduled.quote.QuoteJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName IntegratedQuoteManagement
 * @Description 综合交易行情查询
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
@Component
public class IntegratedQuoteManagement {
    @Autowired
    private TerminalHelper terminalHelper;
    @Autowired
    private QuoteJob quoteJob;

    public List<IntegratedBulkQuoteBean> queryIntegratedBulkQuote(IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) {
        return terminalHelper.queryIntegratedBulkQuote(integratedBulkQuoteQryBean);
    }

    public List<IntegratedBulkCloseQuoteBean> queryIntegratedBulkCloseQuote(IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) {
        return terminalHelper.queryIntegratedBulkCloseQuote(integratedBulkCloseQuoteQryBean);
    }

    public List<IntegratedRepurchaseQuoteBean> queryIntegratedRepurchaseQuote(IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) {
        String entrustProp = integratedRepurchaseQuoteQryBean.getEntrustProp();
        if (!"".equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_TRADE.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_RENEWAL.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_RELEASE.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_EXCHANGE.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_TERMINATE.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_EXPIRE.value.equals(entrustProp) &&
                !EntrustProp.SH_REPURCHASE_INTENTION.value.equals(entrustProp)) {
            return null;
        }
        return terminalHelper.queryIntegratedRepurchaseQuote(integratedRepurchaseQuoteQryBean);
    }

    public List<IntegratedFixedIncomeQuoteBean> queryIntegratedFixedIncomeQuote(IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) {
        return terminalHelper.queryIntegratedFixedIncomeQuote(integratedFixedIncomeQuoteQryBean);
    }

    public ResultBean addIntegratedRepurchaseQuote(IntegratedRepurchaseQuoteBean repurchaseQuoteBean) {
        if (Objects.nonNull(repurchaseQuoteBean)) {
            quoteJob.addIntegratedRepurchaseQuote(repurchaseQuoteBean);
            return new ResultBean(ErrorCode.SUCCESS);
        } else {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        }
    }
}


