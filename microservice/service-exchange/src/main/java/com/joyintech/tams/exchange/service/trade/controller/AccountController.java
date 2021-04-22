package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.service.trade.management.service.AccountManagement;
import com.joyintech.tams.exchange.service.trade.management.service.AccountRiskParameterManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/joyin")
public class AccountController {
    @Autowired
    private AccountManagement accountManagement;

    @Autowired
    private AccountRiskParameterManagement accountRiskParameterManagement;

    /**
     * 账号查询
     *
     * @param userId    用户Id
     * @param accountId 账户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResultBean queryAccount(@RequestParam(value = "user_id") String userId,
                                   @RequestParam(value = "account_id", required = false) String accountId) {
        return accountManagement.queryAccount(accountId);
    }

    /**
     * 账号连接
     *
     * @param userId
     * @param accountId
     * @param connectStatus
     * @return
     */
    @RequestMapping(value = "/account/connect", method = RequestMethod.POST)
    public ResultBean connectAccount(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "account_id") String accountId,
                                     @RequestParam(value = "status") int connectStatus) {
        return accountManagement.connectAccount(accountId, connectStatus);
    }

    /**
     * 账号连接
     *
     * @param userId
     * @param accountId
     * @param compareType
     * @return
     */
    @RequestMapping(value = "/account/compare", method = RequestMethod.POST)
    public ResultBean compareAccount(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "account_id") String accountId,
                                     @RequestParam(value = "type") int compareType) {
        return accountManagement.compareAccount(accountId, compareType);
    }

    /**
     * 查询账户资金
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/account/fund", method = RequestMethod.GET)
    public ResultBean queryAccountFund(@RequestParam(value = "account_id") String accountId) {
        return accountManagement.queryAccountFund(accountId);
    }


    @RequestMapping(value = "/account/position", method = RequestMethod.GET)
    public ResultBean queryAccountPosition(@RequestParam(value = "account_id") String accountId,
                                           @RequestParam(value = "stock_code", required = false) String stockCode,
                                           @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                           @RequestParam(value = "position_str", required = false, defaultValue = "") String positionStr) {
        return accountManagement.queryAccountPosition(accountId, stockCode, pageSize, positionStr);
    }

    /**
     * 查询质押券折算率
     *
     * @param accountId 账户编号
     * @param market    市场
     * @param stockCode 合约代码
     * @return ResultBean
     */
    @RequestMapping(value = "/account/discount", method = RequestMethod.GET)
    public ResultBean queryAccountDiscount(@RequestParam(value = "account_id") String accountId,
                                           @RequestParam(value = "market", required = false) String market,
                                           @RequestParam(value = "stock_code", required = false) String stockCode) {
        return accountManagement.queryAccountDiscount(accountId, market, stockCode);
    }

    /**
     * 资金账户下标准券明细查询
     *
     * @param accountId 账户id
     * @param market    市场
     * @param stockCode 合约代码
     * @return ResultBean
     */
    @RequestMapping(value = "/account/standard/bonds", method = RequestMethod.GET)
    public ResultBean queryAccountStandardBonds(@RequestParam(value = "account_id") String accountId,
                                                @RequestParam(value = "market", required = false) String market,
                                                @RequestParam(value = "stock_code", required = false) String stockCode) {
        return accountManagement.queryAccountStandardBonds(accountId, market, stockCode);
    }

    /**
     * 查询资金账户下深证或上海可用标准券总数
     *
     * @param accountId 账户id
     * @param market    市场
     * @return ResultBean
     */
    @RequestMapping(value = "/account/standard/bond/available", method = RequestMethod.GET)
    public ResultBean queryAccountStandardBonds(@RequestParam(value = "account_id") String accountId,
                                                @RequestParam(value = "market", required = false) String market) {
        return accountManagement.queryAccountAvailableStandardBonds(accountId, market);
    }


    /**
     *  ---------账号风控管理接口----------
     */

    /**
     * 查询账号风控参数
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/account/risk", method = RequestMethod.GET)
    public ResultBean queryAccountRiskParam(@RequestParam(value = "account_id", required = false) String accountId) {
        return accountRiskParameterManagement.queryAccountRiskParam(accountId);
    }

    /**
     * 添加账号风控参数
     *
     * @param userId
     * @param accountRiskParameter
     * @return
     */
    @RequestMapping(value = "/account/risk/add", method = RequestMethod.POST)
    public ResultBean addAccountRiskParam(@RequestParam(value = "user_id") String userId,
                                          @RequestBody AccountRiskParameter accountRiskParameter) {
        return accountRiskParameterManagement.addAccountRiskParam(accountRiskParameter);
    }

    /**
     * 修改账号风控参数
     *
     * @param userId
     * @param accountRiskParameter
     * @return
     */
    @RequestMapping(value = "/account/risk/update", method = RequestMethod.POST)
    public ResultBean updateAccountRiskParam(@RequestParam(value = "user_id") String userId,
                                             @RequestBody AccountRiskParameter accountRiskParameter) {
        return accountRiskParameterManagement.updateAccountRiskParam(accountRiskParameter);
    }

    /**
     * 删除账号风控参数
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/account/risk/delete", method = RequestMethod.POST)
    public ResultBean deleteAccountRiskParam(@RequestParam(value = "account_id") String accountId) {
        return accountRiskParameterManagement.deleteAccountRiskParam(accountId);
    }

    @RequestMapping(value = "/account/futuresPosition", method = RequestMethod.GET)
    public ResultBean queryAccountFuturesPosition(@RequestParam(value = "account_id") String accountId,
                                                  @RequestParam(value = "stock_code", required = false) String stockCode,
                                                  @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                                  @RequestParam(value = "position_str", required = false, defaultValue = "") String positionStr) {
        return accountManagement.queryAccountFuturesPosition(accountId, stockCode, pageSize, positionStr);
    }

    @RequestMapping(value = "/account/futuresPositionDetail", method = RequestMethod.GET)
    public ResultBean queryAccountFuturesPositionDetail(@RequestParam(value = "account_id") String accountId,
                                                        @RequestParam(value = "stock_code", required = false) String stockCode,
                                                        @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                                        @RequestParam(value = "position_str", required = false, defaultValue = "") String positionStr) {
        return accountManagement.queryAccountFuturesPositionDetail(accountId, stockCode, pageSize, positionStr);
    }
}
