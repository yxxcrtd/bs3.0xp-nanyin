package com.joyintech.tams.exchange.terminal.trade.ufx.builder;

import com.google.common.base.Strings;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.constant.ExchangeType;
import com.joyintech.tams.exchange.common.constant.FullPriceConst;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.db.domain.ClientStkacct;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;
import com.joyintech.tams.exchange.common.utils.IntegratedEntrustUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxConverter;

import java.util.List;

public abstract class BaseUfxDataSetBuilder {
    protected long senderId = 5;
    protected String op_entrust_way = "D";
    protected long eventId = 1;
    protected String op_station;
    protected boolean isQueryNextTrade = true;

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public void setOp_entrust_way(String op_entrust_way) {
        this.op_entrust_way = op_entrust_way;
    }

    public String getOp_entrust_way() {
        return op_entrust_way;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public void setOp_station(String op_station) {
        this.op_station = op_station;
    }

    public boolean getIsQueryNextTrade() {
        return isQueryNextTrade;
    }

    public IEvent genEvent(String funcCode, IDataset dataset) {
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(funcCode, EventType.ET_REQUEST);
        event.setServiceId(funcCode); //打入功能号
        event.putEventData(dataset); //入参打包
        event.setIntegerAttributeValue(EventTagdef.TAG_SENDERID, senderId); //分配节点为5
        event.setIntegerAttributeValue(EventTagdef.TAG_EVENT_ID, eventId);
        return event;
    }

    public IEvent genSyncEvent(String funcCode, IDataset dataset) {
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(funcCode, EventType.ET_REQUEST);
        event.setServiceId(funcCode); //打入功能号
        event.putEventData(dataset); //入参打包
        return event;
    }

    public IDataset genEntrustDataSet(Entrust entrust, String password, LoginInfo loginInfo) {

        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        entrustDataSetAddColumns(dataset);
        dataset.appendRow();
        entrustDataSetSetValues(entrust, password, loginInfo, dataset);
        return dataset;
    }

    public IDataset genFixedIncomeEntrustDataSet(IntegratedEntrust integratedEntrust, LoginInfo loginInfo, String password) {
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        fixedIncomeEntrustDataSetAddColumns(integratedEntrust, loginInfo, dataset);
        dataset.appendRow();
        fixedIncomeEntrustDataSetSetValues(integratedEntrust, loginInfo, password, dataset);
        return dataset;
    }

    public IDataset genRepurchaseSHDataSet(IntegratedEntrust integratedEntrust, LoginInfo loginInfo, String password) {
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        repurchaseSHDataSetAddColumns(integratedEntrust, dataset);
        dataset.appendRow();
        repurchaseSHDataSetSetValues(integratedEntrust, loginInfo, password, dataset);
        return dataset;
    }

    protected void entrustDataSetAddColumns(IDataset dataset) {
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("entrust_amount");//委托数量
        dataset.addColumn("entrust_price");//委托价格
        dataset.addColumn("entrust_bs");//买卖方向
        dataset.addColumn("entrust_prop");//委托属性
        dataset.addColumn("batch_no");//外部委托号，8位数字
        dataset.addColumn("entrust_reference");//委托引用
    }

    protected void entrustDataSetSetValues(Entrust entrust, String password, LoginInfo loginInfo, IDataset dataset) {
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        dataset.updateString("op_branch_no", "");//操作分支机构
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (Market.SZSE.value.equals(entrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(entrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        String stockCode = StockCodeUtils.getStockCode(entrust.getStockCode());
        dataset.updateString("stock_code", stockCode);
        dataset.updateLong("entrust_amount", entrust.getEntrustVolume());
        if (FullPriceConst.FULL_PRICE.value.equals(entrust.getIsFullPrice())) {//全价
            dataset.updateDouble("entrust_price", entrust.getFullPrice().doubleValue());
        } else {//净价
            dataset.updateDouble("entrust_price", entrust.getEntrustPrice().doubleValue());
        }
        dataset.updateString("entrust_bs", entrust.getEntrustBs());
        dataset.updateString("entrust_prop", entrust.getEntrustProp());
        dataset.updateString("batch_no", entrust.getLocalEntrustId());
        dataset.updateString("entrust_reference", entrust.getId());
    }


    protected void fixedIncomeEntrustDataSetAddColumns(IntegratedEntrust integratedEntrust, LoginInfo loginInfo,
                                                       IDataset dataset) {
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("entrust_price");//委托价格
        dataset.addColumn("entrust_prop");//委托属性
        dataset.addColumn("entrust_amount");//委托数量
        dataset.addColumn("entrust_bs");//买卖方向
        if (IntegratedEntrustUtils.isFixedIncome_BS(integratedEntrust.getEntrustProp())) {//指定对手方
            dataset.addColumn("trader_id");//交易员编号
            dataset.addColumn("oppo_trader_id");//对方交易员
            dataset.addColumn("oppo_agency");//对方销售商
            dataset.addColumn("cbpconfer_id");//约定号
        } else if (IntegratedEntrustUtils.isFixedIncome_ChenJiao(integratedEntrust.getEntrustProp())
                || IntegratedEntrustUtils.isFixedIncome_BaoJia(integratedEntrust.getEntrustProp())) {//最优价成交 可转换成交申报  点击成交申报  定价申报
            dataset.addColumn("trader_id");//交易员编号
        } else {
            dataset.addColumn("oppo_seatno");//对方席位号
        }
    }

    protected void fixedIncomeEntrustDataSetSetValues(IntegratedEntrust integratedEntrust, LoginInfo loginInfo,
                                                      String password, IDataset dataset) {
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
        }
        String stockCode = StockCodeUtils.getStockCode(integratedEntrust.getStockCode());
        dataset.updateString("stock_code", stockCode);
        if (FullPriceConst.FULL_PRICE.value.equals(integratedEntrust.getIsFullPrice())) {
            dataset.updateDouble("entrust_price", integratedEntrust.getFullPrice().doubleValue());
        } else {
            dataset.updateDouble("entrust_price", integratedEntrust.getEntrustPrice().doubleValue());
        }
        dataset.updateString("entrust_prop", integratedEntrust.getEntrustProp());
        dataset.updateLong("entrust_amount", integratedEntrust.getEntrustVolume());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        if (IntegratedEntrustUtils.isFixedIncome_BS(integratedEntrust.getEntrustProp())) {//指定对手方
            dataset.updateString("trader_id", integratedEntrust.getLiaisonTel());
            dataset.updateString("oppo_trader_id", integratedEntrust.getLiaisonName());
            dataset.updateString("oppo_agency", integratedEntrust.getPropSeatNo());
            dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        } else if (IntegratedEntrustUtils.isFixedIncome_ChenJiao(integratedEntrust.getEntrustProp())
                || IntegratedEntrustUtils.isFixedIncome_BaoJia(integratedEntrust.getEntrustProp())) {//点击成交申报 最优价成交 可转换成交  定价申报
            dataset.updateString("trader_id", integratedEntrust.getLiaisonTel());
        } else {
            dataset.updateString("oppo_seatno", integratedEntrust.getPropSeatNo());
        }
    }

    protected void repurchaseSHDataSetAddColumns(IntegratedEntrust integratedEntrust, IDataset dataset) {
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("entrust_amount");//委托数量
        dataset.addColumn("entrust_bs");//买卖方向
        if (IntegratedEntrustUtils.isRepurchase_SH_ConfirmOrRefuse(integratedEntrust.getEntrustProp(),//债券质押式协议回购_上交所_确认/拒绝
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {
            dataset.addColumn("cbpconfer_id");//约定号
        }
        dataset.addColumn("entrust_balance");//委托金额
        dataset.addColumn("repurchase_date");//购回期限
        dataset.addColumn("expire_year_rate");//到期年收益率
        dataset.addColumn("entrust_prop");//委托属性
        if (IntegratedEntrustUtils.isRepurchaseRenewalSH(integratedEntrust.getEntrustProp(),
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {//到期续作
            dataset.addColumn("bprsrc_status");//协议回购质权人类型
        }
        if (IntegratedEntrustUtils.isRepurchaseUNSQSH(integratedEntrust.getEntrustProp(),
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {//非首期
            dataset.addColumn("orig_cbpconfer_id");//原约定号
            dataset.addColumn("orig_entrust_date");//原申请日期
        }
        dataset.addColumn("oppo_agency_name");//对方销售商名称
        dataset.addColumn("agency_no");//销售商代码
        dataset.addColumn("trader_id");//交易员编号
        dataset.addColumn("oppo_agency");//对方销售商
        dataset.addColumn("oppo_trader_id");//对方交易员
        if (!Strings.isNullOrEmpty(integratedEntrust.getRemark())) {
            dataset.addColumn("remark");//补充条款
        }
        /*if (EntrustProp.SH_REPURCHASE_TRADE.value.equals(integratedEntrust.getEntrustProp())) {//成交申报
            dataset.addColumn("relation_name");//联系人姓名
        }*/
    }

    protected void repurchaseSHDataSetSetValues(IntegratedEntrust integratedEntrust, LoginInfo loginInfo,
                                                String password, IDataset dataset) {
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        String stockCode = StockCodeUtils.getStockCode(integratedEntrust.getStockCode());
        dataset.updateString("stock_code", stockCode);
        dataset.updateLong("entrust_amount", integratedEntrust.getEntrustVolume());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        if (IntegratedEntrustUtils.isRepurchase_SH_ConfirmOrRefuse(integratedEntrust.getEntrustProp(),//债券质押式协议回购_上交所_确认/拒绝
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {
            dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        }
        dataset.updateDouble("entrust_balance", integratedEntrust.getEntrustBalance().doubleValue());
        dataset.updateString("repurchase_date", integratedEntrust.getBackDate());
        dataset.updateDouble("expire_year_rate", UfxConverter.convertExpireYearRate(integratedEntrust.getEntrustPrice()));
        dataset.updateString("entrust_prop", integratedEntrust.getEntrustProp());
        if (IntegratedEntrustUtils.isRepurchaseRenewalSH(integratedEntrust.getEntrustProp(),
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {//到期续作
            dataset.updateString("bprsrc_status", integratedEntrust.getTransCode());//协议回购质权人类型
        }
        if (IntegratedEntrustUtils.isRepurchaseUNSQSH(integratedEntrust.getEntrustProp(),
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {//非首期
            dataset.updateString("orig_cbpconfer_id", integratedEntrust.getOrigSerialNo());//原约定号
            dataset.updateString("orig_entrust_date", integratedEntrust.getFirstSettleDate());//原申请日期
        }
        dataset.updateString("oppo_agency_name", integratedEntrust.getPropSeatName());
        dataset.updateString("agency_no", integratedEntrust.getSellerNo().substring(1, 4));
        dataset.updateString("trader_id", integratedEntrust.getSellerNo());
        dataset.updateString("oppo_agency", integratedEntrust.getPropSeatNo());
        dataset.updateString("oppo_trader_id", integratedEntrust.getLiaisonTel());//借用成交标号 处理对方交易员
        if (!Strings.isNullOrEmpty(integratedEntrust.getRemark())) {
            dataset.updateString("remark", integratedEntrust.getRemark());
        }
        /*if (EntrustProp.SH_REPURCHASE_TRADE.value.equals(integratedEntrust.getEntrustProp())) {//成交申报
            dataset.updateString("relation_name", integratedEntrust.getLiaisonName());//联系人姓名
        }*/
    }
}