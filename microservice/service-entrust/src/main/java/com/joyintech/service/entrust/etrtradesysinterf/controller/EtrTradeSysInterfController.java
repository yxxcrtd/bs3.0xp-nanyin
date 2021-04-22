/*
 * 文件名：EtrTradeSysInterfController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chaowei
 * 修改时间：2020年3月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrtradesysinterf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrTradePledgeDto;
import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrTradeSysInterfDto;
import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrTradeSysInterfTotalDto;
import com.joyintech.service.entrust.etrtradesysinterf.service.EtrTradeSysInterfService;
/**
 * 
 * 指令查询接口controller入口
 * 〈功能详细描述〉
 * @author 潮伟
 * @version 1.0 2020年3月16日
 * @see EtrTradeSysInterfController
 * @since 1.0
 */
@RestController("EtrTradeSysInterfCtr_dtx")
@RequestMapping("/EtrTradeSysInterf")
public class EtrTradeSysInterfController {
    
    /**
     * 所有投资委托微服务service
     */
    @Autowired
    private EtrTradeSysInterfService etrTradeSysInterfService;
    
    /**
     * 查询指令接口
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/queryEtrentrustList")
    public EtrTradeSysInterfTotalDto selectForPage(@RequestBody Map<String, Object> param) 
        throws Exception {
    	EtrTradeSysInterfTotalDto dto = new EtrTradeSysInterfTotalDto();
    	if(param.get("amount")==null || 0==((Integer)param.get("amount")))
    	{	
    		List<EtrTradeSysInterfDto> selectList = etrTradeSysInterfService.selectList(param);
    		dto.setEtr_entrust_list(selectList);
    		dto.setEtr_pledge_list(etrTradeSysInterfService.selectPledgeList(selectList));
    		if(selectList!=null && !selectList.isEmpty()) {
    			dto.setAMOUNT(selectList.size());
        		dto.setPageCount(1);
    		}else {
    			dto.setAMOUNT(0);
        		dto.setPageCount(0);
    		}
    	} else {
    		Integer length=  (Integer)param.get("amount");
    		Integer page= param.get("pageNo")==null   ? 1: (Integer)param.get("pageNo");
        	Page<EtrTradeSysInterfDto> etrTradepage = etrTradeSysInterfService.selectForPage(page,length, param);
        	if(etrTradepage!=null && etrTradepage.getRoot().size()>0) {
        		dto.setAMOUNT(etrTradepage.getRoot().size());
        		dto.setPageCount(etrTradepage.getTotal());
        		dto.setEtr_entrust_list(etrTradepage.getRoot());
        		dto.setEtr_pledge_list(etrTradeSysInterfService.selectPledgeList(etrTradepage.getRoot()));
        	} else {
        		dto.setEtr_entrust_list(new ArrayList<EtrTradeSysInterfDto>());
        		dto.setEtr_pledge_list(new ArrayList<EtrTradePledgeDto>());
        		dto.setAMOUNT(0);
        		dto.setPageCount(0);
        	}
    	}
        return dto;
    }
    
    /**
     * 指令接收|拒绝
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/handleEntrust")
    public Map<String, Object> handleEntrust(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	if(param!=null && param.size() >0) {
    		List<Map<String, Object>> entrustOpeList = (List<Map<String, Object>>) param.get("entrustOpeList");
    		for(Map<String, Object> tmp : entrustOpeList) {
    			//委托代码
    			String entrustId = (String)tmp.get("entrustId");
    			//接收状态
    			String recStatus = (String)tmp.get("recStatus");
    			//交易员
    			String trader = (String)tmp.get("trader");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("entrustId",entrustId);
    			try {
					int result = this.etrTradeSysInterfService.handleEntrust(entrustId, recStatus,trader);
					if(result>0) {
						map.put("status","0");
					} else {
						map.put("status","1");
					}
					list.add(map);
				} catch (Exception e) {
					map.put("status","1");
					list.add(map);
				}
    		}
    	}
		resultMap.put("entrustOpeList",list);
		return resultMap;
    }
    
   
}
