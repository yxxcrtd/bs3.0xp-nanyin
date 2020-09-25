package com.joyintech.interf.bankname.custodian.jobDetail.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.interf.bankname.custodian.jobDetail.service.DepositAccountFlowService;

/**
 * 托管接口服务
 * 
 * @author 永丰基地
 * @version 1.0 2019年7月13日
 * @see InterfCustodianController
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/custodian")
public class InterfCustodianController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

 
    /**
     * 托管接口-账户流水处理Service
     */
    @Autowired
    private DepositAccountFlowService depositAccountFlowService;

    

    /**
     * 托管账户流水申请
     * 
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    @PostMapping("/getAccountFlow")
    public String getAccountFlow(@RequestBody Map<String, Object> paramMap) {
        logger.info("调用接口：托管账户流水申请!");
        return depositAccountFlowService.getAccountFlow(paramMap);
    }

   

}
