/*
 * 文件名：FinPlanProfitAsset003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.parentAssetManage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.parentAssetManage.dto.ParentAssetManage001Dto;
import com.joyintech.tams.invest.portfolio.parentAssetManage.service.ParentAssetManage001Service;

/**
 * 母资管计划控制类
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年11月20日
 * @see FinPlanProfitAsset003Controller
 * @since 1.0
 */
@Controller
@PageId("ParentAssetManage001")
@RequestMapping("/invest/portfolio/parentAssetManage/ParentAssetManage001")
public class ParentAssetManage001Controller extends BaseController{
	
	@Autowired
	private ParentAssetManage001Service parentAssetManage001Service;
	
    /**
     * 计划类资产（利率型）母资管计划查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	model.addAttribute("currentDate", DateUtil.format(DateUtil.platDateFromCache()));
    	model.addAttribute("title", "母资管计划"); 
    	return super.init(model);
    }
    
    /**
     * 画面查询方法
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<ParentAssetManage001Dto> selectForPage() throws Exception{
    	Page<ParentAssetManage001Dto> planInfos = parentAssetManage001Service.getParentPlanInfos(RequestUtil.getPageParam());
    	return planInfos;
    }
    
    
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception{
    	HttpServletRequest requestParam = getRequest();
    	Map<String, Object> map = RequestUtil.getParameters(requestParam);
    	return parentAssetManage001Service.export("母资管计划.xlsx", map);
    }
    
    /**
     * 分页查询 
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getAssetIdOption")
    public Page<SelectOptionDto> getAssetIdOption() {
        return parentAssetManage001Service.getAssetIdOption();
    }
 
}
