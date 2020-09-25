/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.order.dto.BussSettleOrderResultDto;
import com.joyintech.service.settle.order.service.BussSettleOrderCommService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

@RestController
@RequestMapping("/BussSettleOrderComm")
public class BussSettleOrderCommController {

    @Autowired
    private BussSettleOrderCommService bussSettleOrderCommService;

    @PostMapping("generateSettleOrder")
    public BussSettleOrderResultDto generateSettleOrder(@RequestBody TrdProductDeal deal,
                                                        String isTotalTrade,
                                                        String bankLendingType,String dealMode,String ieFlag, String portfolioId,
                                                        String LENDING_ACCT_NO,
                                                        String LENDING_ACCT_NAME,
                                                        String PROD_TRU_ACCT_NO,
                                                        String PROD_TRU_ACCT_NAME,
                                                        String SETTLE_ACCT_NO,
                                                        String SETTLE_ACCT_NAME,
                                                        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NO,
                                                        String CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME,
                                                        String IRS_ACCOUNT_NO,
                                                        String IRS_ACCOUNT_NAME) {
        Map<String, String> cacheMap = new HashMap<String, String>();
        cacheMap.put(BussinessConstant.LENDING_ACCT_NO_KEY, LENDING_ACCT_NO);
        cacheMap.put(BussinessConstant.LENDING_ACCT_NAME_KEY, LENDING_ACCT_NAME);
        cacheMap.put(BussinessConstant.PROD_TRU_ACCT_NO_KEY, PROD_TRU_ACCT_NO);
        cacheMap.put(BussinessConstant.PROD_TRU_ACCT_NAME_KEY, PROD_TRU_ACCT_NAME);
        cacheMap.put(BussinessConstant.SETTLE_ACCT_NO_KEY, SETTLE_ACCT_NO);
        cacheMap.put(BussinessConstant.SETTLE_ACCT_NAME_KEY, SETTLE_ACCT_NAME);
        cacheMap.put(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NO_KEY, CENTRAL_NET_LIQUIDATION_ACCOUNT_NO);
        cacheMap.put(BussinessConstant.CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME_KEY, CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME);
        // add by renping 利率互换划款指令用行内统一账户 20190826 start
        cacheMap.put(BussinessConstant.IRS_ACCOUNT_NO_KEY, IRS_ACCOUNT_NO);
        cacheMap.put(BussinessConstant.IRS_ACCOUNT_NAME_KEY, IRS_ACCOUNT_NAME);
        // add by renping 利率互换划款指令用行内统一账户 20190826 end
        return bussSettleOrderCommService.generateSettleOrder(deal, isTotalTrade, bankLendingType, portfolioId, dealMode,ieFlag, cacheMap);
    }

    @PostMapping("selectOrdersByBizCode")
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(@RequestBody String bizCode) {
        return bussSettleOrderCommService.selectOrdersByBizCode(bizCode);
    }

    @PostMapping("selectOrdersByBizCodeZhichu")
    public List<BussTrdTransferOrderBase> selectOrdersByBizCodeZhichu(@RequestBody Map<String,Object> map) {
        String effectStatus = (String) map.get("effectStatus");
        if(StringUtil.equals(DictCodeListExt1.有效状态_流程中.getValue(), effectStatus)){
            return bussSettleOrderCommService.selectWflOrdersByBizCode((String)map.get("bizCode"));
        }
        return bussSettleOrderCommService.selectOrdersByBizCodeZhichu((String)map.get("bizCode"));
    }

    /**
     * 收付款账户下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("queryAcctList")
    public List<SelectOptionDto> queryAcctList(@RequestBody Map<String, Object> paramMap) {
        return bussSettleOrderCommService.queryAcctList(paramMap);
    }

    /**
     * 收款账户下拉框
     * @param ccy 币种
     * @param busiAcctNo 账号
     * @return 下拉框列表
     * @throws Exception
     */
    @GetMapping("queryStlCapacctInfoByBusiAcctNo")
    public StlCapacctInfo queryStlCapacctInfoByBusiAcctNo(String ccy, String busiAcctNo) {
        return bussSettleOrderCommService.queryStlCapacctInfoByBusiAcctNo(ccy, busiAcctNo);
    }

    /**
     * 根据产品找对应账户
     * @param ccy 币种
     * @param busiAcctNo 账号
     * @return 下拉框列表
     * @throws Exception
     */
    @PostMapping("selectProdCapacctInfoList")
    public List<StlCapacctInfo> selectProdCapacctInfoList(@RequestBody Map<String, Object> paramMap) {
        return bussSettleOrderCommService.selectProdCapacctInfoList(paramMap);
    }
    /**
     * 根据证券托管户和通道代码查询账号信息
     * @param paramMap 参数
     * @return 账号信息
     */
    @PostMapping("getAcctNoByInfo")
    public List<StlCapacctInfo> getAcctNoByInfo(@RequestBody Map<String, Object> paramMap) {
        return bussSettleOrderCommService.getAcctNoByInfo(paramMap);
    }
    /**
     * 根据证券托管户和通道代码查询账号信息
     * @param paramMap 参数
     * @return 账号信息
     */
    @PostMapping("selectCounterCapacctInfoList")
    public List<StlCapacctInfo> selectCounterCapacctInfoList(@RequestBody Map<String, Object> paramMap) {
        return bussSettleOrderCommService.selectCounterCapacctInfoList(paramMap);
    }
}
