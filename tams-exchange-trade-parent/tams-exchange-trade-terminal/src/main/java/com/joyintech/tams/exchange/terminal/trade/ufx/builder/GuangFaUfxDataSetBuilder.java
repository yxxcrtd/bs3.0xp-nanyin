package com.joyintech.tams.exchange.terminal.trade.ufx.builder;

import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;

public class GuangFaUfxDataSetBuilder extends BaseUfxDataSetBuilder {

    @Override
    protected void repurchaseSHDataSetAddColumns(IntegratedEntrust integratedEntrust, IDataset dataset) {
        super.repurchaseSHDataSetAddColumns(integratedEntrust, dataset);
        dataset.addColumn("entrust_reference"); //委托引用
    }

    @Override
    protected void repurchaseSHDataSetSetValues(IntegratedEntrust integratedEntrust, LoginInfo loginInfo, String password, IDataset dataset) {
        super.repurchaseSHDataSetSetValues(integratedEntrust, loginInfo, password, dataset);
        dataset.updateString("entrust_reference", integratedEntrust.getId()); //委托引用
    }

    @Override
    protected void fixedIncomeEntrustDataSetAddColumns(IntegratedEntrust integratedEntrust, LoginInfo loginInfo, IDataset dataset) {
        super.fixedIncomeEntrustDataSetAddColumns(integratedEntrust, loginInfo, dataset);
        dataset.addColumn("entrust_reference"); //委托引用
    }

    @Override
    protected void fixedIncomeEntrustDataSetSetValues(IntegratedEntrust integratedEntrust, LoginInfo loginInfo, String password, IDataset dataset) {
        super.fixedIncomeEntrustDataSetSetValues(integratedEntrust, loginInfo, password, dataset);
        dataset.updateString("entrust_reference", integratedEntrust.getId()); //委托引用
    }
}
