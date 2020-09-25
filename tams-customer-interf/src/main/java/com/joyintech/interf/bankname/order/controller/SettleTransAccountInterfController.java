package com.joyintech.interf.bankname.order.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *    清结算过渡户接口
 * @author wangqidong
 *
 */
@RestController("SettleTransAccountInterf_dtx")
@RequestMapping("/interf/SettleTransAccountInterf")
public class SettleTransAccountInterfController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("TransAccountInfo")
	@Async
    public Map<String, String> TransAccountInfo(@RequestBody List<String>  tradeIds) {	
        for(String tradeId : tradeIds){
            logger.debug("交易编号：" + tradeId); 
            
        }    
        return  null;
    }
}
