package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

public interface AccountRiskParameterService extends AsyncService<String, AccountRiskParameter> {

    AccountRiskParameter queryAccountRiskParameterByAccountId(String accountId);

}
