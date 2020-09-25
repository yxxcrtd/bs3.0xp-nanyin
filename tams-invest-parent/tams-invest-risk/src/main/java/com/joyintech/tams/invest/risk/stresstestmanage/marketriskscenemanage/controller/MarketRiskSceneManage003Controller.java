/*
 * 文件名：MarketRiskSceneManage003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.controller;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage003Service;

/**
 * 市场性风险-情景详情
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月20日
 * @see MarketRiskSceneManage003Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskSceneManage003")
@RequestMapping("/invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage003")
public class MarketRiskSceneManage003Controller extends BaseController {

	/**
     * 情景新建002service注入
     */
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;
    
    /**
     * 情景新建002service注入
     */
    @Autowired
    private MarketRiskSceneManage003Service marketRiskSceneManage003Service;
	
	/**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	//初始化需要判断来源，如果来源是修改页面，则需要根据提供的数据重新计算，如果是管理页面则直接查询并显示
    	String origin = getParam("origin");
    	if("manage".equals(origin)){		//情景管理页面，此时情景已通过审核
    		String sceneId = getParam("sceneId");
        	MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Service.querySceneById(sceneId);
        	model.addAttribute("sceneId", sceneId);
        	model.addAttribute(GlobalConstant.ENTITY, dto);
        	
        	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        	model.addAttribute("stDate", f.format(dto.getStartDate()));
        	model.addAttribute("edDate", f.format(dto.getEndDate()));
    	}else{			//新建的情景或修改的情景
    		String marketRiskScStr = getParam("object");
    		MarketRiskSceneManage002Dto dto = JSON.parseObject(marketRiskScStr, new TypeReference<MarketRiskSceneManage002Dto>(){});
    		if(StringUtil.isEmpty(dto.getSceneId())){
    			String sceneId = "Mrk1"+StringUtil.leftPad(marketRiskSceneManage003Service.getNewSceneId(), 4, "0");
    			dto.setSceneId(sceneId);		//Mrk表示市场类型，1表示历史情景
    		}
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		model.addAttribute("stDate",format.format(dto.getStartDate()));
    		model.addAttribute("edDate",format.format(dto.getEndDate()));
    		model.addAttribute("sceneId", dto.getSceneId());
        	model.addAttribute(GlobalConstant.ENTITY, dto);
    	}
    	model.addAttribute("origin", origin);
    	return super.init(model);
    }
    
    /** 因子变化信息查询 */
    @ResponseBody
    @RequestMapping("/queryfactorList")
    public List<MarketRiskSceneManage003Dto> queryFactorInfoList() throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        String origin = getParam("origin");
        map.put("origin", origin);
    	if("manage".equals(origin)){		//情景管理页面，此时情景已通过审核
    		String sceneId = (String) map.get("sceneId");
    		map.put("sceneId", sceneId);
    	}else{
    		MarketRiskSceneManage002Dto dto = JSON.parseObject((String) map.get("entity"), new TypeReference<MarketRiskSceneManage002Dto>(){});
            Iterator<MarketRiskSceneFactorTypeDto> ftrTypeIt = dto.getFtrTypeList().iterator();
            while (ftrTypeIt.hasNext()) {
            	MarketRiskSceneFactorTypeDto factorTypeDto = ftrTypeIt.next();
    			if(!factorTypeDto.isFtrChecked()){
    				ftrTypeIt.remove();
    			}
    		}
            map.put("entity", dto);
    	}
        
        
        
        return marketRiskSceneManage003Service.queryFactorInfoList(map);
    }
    
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return marketRiskSceneManage003Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
    
}
