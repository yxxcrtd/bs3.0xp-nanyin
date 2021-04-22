package com.joyintech.tams.exchange.service.trade.management.service;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.DBSaveType;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.datacommon.db.service.AccountRiskParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountRiskParameterManagement {

    @Autowired
    private AccountRiskParameterService accountRiskParameterService;

    public ResultBean queryAccountRiskParam(String accountId) {
        if (!Strings.isNullOrEmpty(accountId))
            return new ResultBean(ErrorCode.SUCCESS, accountRiskParameterService.findById(accountId));
        else return new ResultBean(ErrorCode.SUCCESS, accountRiskParameterService.findAll());
    }


    public ResultBean addAccountRiskParam(AccountRiskParameter accountRiskParameter) {
        // 该账户存在于投管系统
        if (Objects.nonNull(accountRiskParameter)) {
            accountRiskParameterService.asyncUpdate(accountRiskParameter, DBSaveType.SINGLE.value);
        } else {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "缺少风控参数不存在");
        }
        return new ResultBean(ErrorCode.SUCCESS, accountRiskParameter);
    }


    public ResultBean updateAccountRiskParam(AccountRiskParameter accountRiskParameter) {
        if (Objects.nonNull(accountRiskParameter)) {
            accountRiskParameterService.asyncUpdate(accountRiskParameter, DBSaveType.SINGLE.value);
        } else {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "缺少风控参数");
        }
        return new ResultBean(ErrorCode.SUCCESS, accountRiskParameter);
    }

    public ResultBean deleteAccountRiskParam(String accountId) {
        if (Strings.isNullOrEmpty(accountId))
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        AccountRiskParameter accountRiskParameter = accountRiskParameterService.findById(accountId);
        if (Objects.nonNull(accountRiskParameter)) {
            accountRiskParameterService.asyncDel(accountId);
            return new ResultBean(ErrorCode.SUCCESS, accountId);
        } else {
            return new ResultBean(ErrorCode.ACCOUNT_RISK_PARAMETER_NOT_EXIT, accountId);
        }
    }
}
