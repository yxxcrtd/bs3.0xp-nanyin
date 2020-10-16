/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.service.FlowRiskStressTestView001Service;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView001Dto;

/**
 * 流动性-压力测试概览
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@RestController("backFlowRiskStressTestView001Controller")
@RequestMapping("/FlowRiskStressTestView001")
public class FlowRiskStressTestView001Controller{
	
	@Autowired
    private FlowRiskStressTestView001Service FlowRiskStressTestView001Service;
	
	  @PostMapping("findPage/{page}/{length}")
	  public Page<FlowRiskStressTestView001Dto> selectAll(@RequestBody Map<String, Object> param,
	            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		 Map<String, Object> param1=param;
		 Object st=param1.get("testId");
		 Object sce=param1.get("sceneId");
		 if(st!=""&&st!=null){
		     String [] str=((String) st).split(",");
             List<String> list=Arrays.asList(str);
             param1.replace("testId", list);
    		 
 	     }else{
             param1.remove("testId");
 	     }
		 if(sce!=""&&sce!=null){
             String [] strS=((String) sce).split(",");
             List<String> listS=Arrays.asList(strS);
             param1.replace("sceneId", listS); 
         }else{
             param1.remove("sceneId");
         }
		 //wangyang1 压力测试二级法人改造 start
		 if (MultilevelDataAuthorityUtil.isEnabled()) {
           param1.put("institution", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));   
         }
		 //wangyang1 压力测试二级法人改造 end
		 return FlowRiskStressTestView001Service.selectForPage(page, length, param1);
		 
	   }
	 @PostMapping("selectTestListMap")
	 public List<SelectOptionDto> selectTestListMap(@RequestBody Map<String, Object> param) throws Exception {
    	     //wangyang1 压力测试二级法人改造 start
             if (MultilevelDataAuthorityUtil.isEnabled()) {
//               param.put("institution", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));   
                 List<String> institutionList = LoginUserUtil.getTreeInstCode(); 
                 param.put("institutionList",institutionList); 
             }
             //wangyang1 压力测试二级法人改造 end
	        return FlowRiskStressTestView001Service.selectTestListMap(param);
	    }
	 @PostMapping("initPage")
	    public List<FlowRiskStressTestView001Dto> initPage(@RequestBody Map<String, Object> param) throws Exception {
	       
	        return FlowRiskStressTestView001Service.initPage(param);
	    }
	 
	 
	     @PostMapping("fuzzySelectScene")
	     public List<SelectOptionDto> fuzzySelectScene(@RequestBody Map<String, Object> param) throws Exception {
    	        //CommonUtil.hasChinese(fuzzyText)
    	        List<SelectOptionDto> resultList = new ArrayList<>();
    	        List<SttScene> dataList = null;
    	        
    	        Object sceneStr = param.get("sceneStr");
    	        if(sceneStr==null){
    	            sceneStr = "";
    	        }
    	        
    	        dataList = FlowRiskStressTestView001Service.fuzzyQueryBySceneNameOrId(sceneStr.toString());
    
    	        if (CollectionUtils.isNotEmpty(dataList)) {
    	            dataList.forEach(p -> {
    	                SelectOptionDto option = new SelectOptionDto();
    	                option.setId(p.getSceneId());
    	                option.setText(p.getSceneId()+":"+p.getSceneName());
    	                
    	                resultList.add(option);
    	            });
    	        }
    
    	        return resultList;
	     }
}

