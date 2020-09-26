/*
 * 文件名：ValInertfLogController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.inertfBuildXml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.vasinterf.model.ValInertfBuildXml001Dto;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;

/**
 * 接口生成xml文件
 * 
 * @author leipeng
 * @version 1.0 2018年6月13日
 * @see ValInertfBuildXml001Controller
 * @since 1.0
 */
@Controller
@PageId("ValInertfBuildXml001")
@RequestMapping("/valuation/interf/inertfBuildXml/ValInertfBuildXml001")
public class ValInertfBuildXml001Controller extends BaseController {

	
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        return super.init(model);
    }
	
	/**
     * 接口启动
	 * @throws Exception 
     * 
     * @see
     * @since 1.0
     */
    @RequestMapping(value="runAs",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String runAs()
        throws Exception {
        String json = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put(InterfCommonConstant.接口_开始日, this.getParam("startDate"));
		map.put(InterfCommonConstant.接口_结束日, this.getParam("endDate"));
	    List<ValInertfBuildXml001Dto> list = new ArrayList<ValInertfBuildXml001Dto>();
	    list  = valInertfStartUp001Service.runInterf(map);
	    json =  JsonUtils.objectArray2Json(list).toString().substring(1,  JsonUtils.objectArray2Json(list).toString().length()-1);

        return json;
    }
  
}
