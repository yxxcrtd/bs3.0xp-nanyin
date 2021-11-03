package com.joyintech.tams.cfets.bankuplink.bond.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.bankuplink.bond.dto.IbuQuoteBond002Dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CFETS债券交易-请求报价接口
 * 
 * @author yflv
 * @version 1.0 2021年11月02日
 * @see IbuQuoteBond003Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsBondDealQuotedPrice003")
@RequestMapping("/cfets/bankuplink/bond/IbuQuoteBond003Controller")
@Api(tags = "CFETS债券交易-请求报价接口")
public class IbuQuoteBond003Controller extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(IbuQuoteBond003Controller.class);
	
	@Override
    public String init(Model model) {
		//TODO 待办需要用到这里？
        return super.init(model);
    }
	
	@ResponseBody
    @ApiOperation(value = "交易执行--请求报价 新建保存/提交", notes = "交易执行--请求报价 新建保存/提交")
    @PostMapping("save")
    public Message<AjaxResult> save(@RequestBody IbuQuoteBond002Dto ibuQuoteBond002Dto)
            throws Exception {
		//1、check dto前端传入值
		//2、对应业务处理
		//3、启动流程处理
		return new Message<AjaxResult>().success();
    }

}
