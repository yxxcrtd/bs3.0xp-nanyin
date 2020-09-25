package com.joyintech.interf.bankname.custodian.jobDetail.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.MessageType;

/**
 * 托管账户流水接口Service
 * 
 * @author zhangJin
 * @version 1.0 2019年7月27日
 * @see DepositAccountFlowService
 * @since 1.0
 */
@Service
public class DepositAccountFlowService {

    
   
    
    /**
     * 发送账户流水申请
     * 
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public String getAccountFlow(Map<String, Object> paramMap) {
    	String code = MessageType.提示消息.getCode();
    	
        return code;
    	
    }

}
