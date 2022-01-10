package com.joyintech.tams.cfets.common.controller;

import com.joyintech.tams.cfets.trdconfirm.service.CfetsTrdConfirmService;
import com.joyintech.tams.common.base.entity.InterfCstpTrade;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 共通
 * @author zznn
 * @date 2021/11/26
 */
@Controller
@RequestMapping("/cfets/common/CfetsIbuQuoteBatchCommon")
public class CfetsIbuQuoteBatchCommonController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CfetsTrdConfirmService cfetsTrdConfirmService;

    /**
     * 回溯登记成交确认
     * @param parm
     */
    @ResponseBody
    @PostMapping("resaleregautodeal")
    public void resaleregautodeal(@RequestBody Map<String,Object> parm) throws Exception {
        List<String> totleTradeIds = (List<String>) parm.get("totleTradeIds");
        try{
            totleTradeIds.forEach(item ->{
                cfetsTrdConfirmService.doCompleteMethod(cfetsTrdConfirmService.prepareEntity(item, DictCodeListExt1.生成方式_批处理生成.getValue()), OperationType.NEW);
            });
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("回售登记数据自动生成成交数据异常:"+e.getMessage());
        }
    }



    @ResponseBody
    @PostMapping("cstpAutoTrade")
    public void cstpAutoTrade(@RequestBody Map<String,Object> parm) throws Exception {
        try{
            InterfCstpTrade interfCstpTrade =  (InterfCstpTrade)parm.get("interfCstpTrade");
            if(parm.containsKey("interfTradSecdeal")){
                InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal)parm.get("interfTradSecdeal");
                cfetsTrdConfirmService.cstpAutoMatchMethod(interfTradSecdeal,null,interfCstpTrade);
            }
            if(parm.containsKey("interfOpicsTradRepo")){
                InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo)parm.get("interfOpicsTradRepo");
                cfetsTrdConfirmService.cstpAutoMatchMethod(null,interfOpicsTradRepo,interfCstpTrade);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("cstp成交回报自动匹配异常:"+e.getMessage());
        }
    }
}

