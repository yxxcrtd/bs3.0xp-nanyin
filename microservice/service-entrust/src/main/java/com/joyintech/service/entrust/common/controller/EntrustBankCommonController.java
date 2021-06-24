package com.joyintech.service.entrust.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.service.EntrustBankCommonService;

@RestController("EntrustBankCommonCtr_dtx")
@RequestMapping("/EntrustBankCommon")
public class EntrustBankCommonController {
	
	@Autowired
	private EntrustBankCommonService entrustBankCommonService;

    /**
     * 委托关闭check
     * @param param
     * @return
     */
	@PostMapping("/entrustCheckCloseList")
	public List<ValidMessage> entrustCheckCloseList(@RequestBody Map<String,Object> param){
    	List<ValidMessage> errorList=new ArrayList<ValidMessage>();
    	@SuppressWarnings("unchecked")
		List<String> entrustIds=(List<String>)param.get("entrustIds");
    	if(StringUtil.isNotEmpty(entrustIds)){
    		for(String entrustId : entrustIds){
    			String flag  = entrustBankCommonService.entrustCheckCloseList(entrustId);
    			if(StringUtil.equals("1", flag)){
        			errorList.add(new ValidMessage("已接收、已退回和已撤回,才能执行关闭操作！"+"指令编号："+entrustId));
        		}
    			else if(StringUtil.equals("2", flag)){
        			errorList.add(new ValidMessage("已接收指令，指令完成度要为0，且不存在在途委托/报价/交易，才可进行关闭。"+"指令编号："+entrustId));
        		}
        	}
    	}
		return errorList;
	}
}
