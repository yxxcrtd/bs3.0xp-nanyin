/*
 * 文件名：FinProductInterfController.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 11:33
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;


/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @param <ValNetValueConfirm001Dto>
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/FinProductInterf")
public class FinProductInterfController<ValNetValueConfirm001Dto> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("sendNetValueProductInfoToSale")
    public Map<String, String> sendNetValueProductInfoToSale(@RequestBody List<String> finprodIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (String finProdId: finprodIds) {
        	logger.debug("发送净值：" + finProdId);
        	
        	//TODO
        	msg  += "发送净值: "  + finProdId + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送收益型产品到销售接口
     * @param finprodIds
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendProductBuildInfoToSale")
    public Map<String, String> sendProductBuildInfoToSale(@RequestBody List<String> finprodIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (String finProdId: finprodIds) {
        	logger.debug("发送净值：" + finProdId);
        	
        	//TODO
        	msg  += "发送净值: "  + finProdId + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送发送产品分红信息到销售接口
     * @param bonusPlanIds
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendProductBonusSchemeToSale")
    public Map<String, String> sendProductBonusSchemeToSale(@RequestBody List<String> bonusPlanIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (String bonusPlanId: bonusPlanIds) {
        	logger.debug("发送产品分红信息：" + bonusPlanId);
        	
        	//TODO
        	msg  += "发送产品分红信息: "  + bonusPlanId + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送到期日信息到销售接口
     * @param bonusPlanIds
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendMdateAdjustmentSale")
    public Map<String, String> sendMdateAdjustmentSale(@RequestBody List<String> finProdIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (String finProdId: finProdIds) {
            logger.debug("发送到期日信息：" + finProdId);
            
            //TODO
            msg  += "发送到期日信息: "  + finProdId + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送开放计划信息到销售接口
     * @param CashId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendOpenPlanSale")
    public Map<String, String> sendOpenPlanSale(@RequestBody List<FinOpenPlanMaintenanceDto> dtoList) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (FinOpenPlanMaintenanceDto dto: dtoList) {
            logger.debug("发送开放计划信息：" + dto.getCashId());
            
            //TODO
            msg  += "发送开放计划信息: "  + dto.getCashId() + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送浮动管理费报价调整信息到销售接口
     * @param CashId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendFloatMgtExpQuotationSale")
    public Map<String, String> sendFloatMgtExpQuotationSale(@RequestBody List<String> finProdIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (String finProdId: finProdIds) {
            logger.debug("发送：" + finProdId);
            
            //TODO
            msg  += "发送: "  + finProdId + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    
    @PostMapping("sendProdNetValueAlerttoSale")
    public Map<String, String> sendProdNetValueAlerttoSale(@RequestBody List<Map<String, String>> listMap) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (Map<String, String> sendInfo: listMap) {
            logger.debug("下发：" + sendInfo.get("finprodId"));
            
            //TODO
            msg  += "下发: "  + sendInfo.get("finprodId") + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送报价调整信息到销售接口
     * @param CashId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendProductQuotationToSale")
    public Map<String, String> sendProductQuotationToSale(@RequestBody List<Map<String, String>> listMap) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (Map<String, String> sendInfo: listMap) {
            logger.debug("下发：" + sendInfo.get("cashId"));
            
            //TODO
            msg  += "下发: "  + sendInfo.get("cashId") + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    /**
     * 发送业绩基准信息到销售接口
     * @param CashId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("sendProductBmQuotation")
    public Map<String, String> sendProductBmQuotation(@RequestBody List<Map<String, String>> listMap) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for (Map<String, String> sendInfo: listMap) {
            logger.debug("下发：" + sendInfo.get("baseRuleId"));
            
            //TODO
            msg  += "下发: "  + sendInfo.get("baseRuleId") + "成功！<br />";
        }
        

        //
        res.put("msg", msg);

        return  res;
    }
    
    
    
}
