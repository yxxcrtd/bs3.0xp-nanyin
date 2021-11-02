package com.joyintech.tams.cfets.bankuplink.bond.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.bankuplink.bond.dto.EntrustDto;
import com.joyintech.tams.cfets.bankuplink.bond.dto.IbuQuoteBond002Dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CFETS债券交易-常用查询类接口
 * 
 * @author yflv
 * @version 1.0 2021年11月02日
 * @see IbuQuoteBond001Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsBondDealQuotedPrice001")
@RequestMapping("/cfets/bankuplink/bond/IbuQuoteBond001Controller")
@Api(tags = "CFETS债券交易-常用查询类接口")
public class IbuQuoteBond001Controller {
	
	@ApiOperation(value = "初始化登记", notes = "初始化登记")
	@PostMapping("/initRegister")
	@ResponseBody
	public Message<IbuQuoteBond002Dto> initRegister(@RequestBody List<EntrustDto> dtoList)
	        throws Exception {
		//TODO 根据传入的指令dto做check，并返回登记页面所需参数值
		return null;
	}
	
	@ApiOperation(value = "初始化对话报价", notes = "初始化对话报价")
    @PostMapping("/initDialogue")
    @ResponseBody
    public Message<IbuQuoteBond002Dto> initDialogue(@RequestBody List<EntrustDto> dtoList)
            throws Exception {
		//TODO 根据传入的指令dto做check，并返回登记页面所需参数值
		return null;
	}
	
	@ApiOperation(value = "初始化请求报价", notes = "初始化请求报价")
    @PostMapping("/initRequest")
    @ResponseBody
    public Message<IbuQuoteBond002Dto> initRequest(@RequestBody List<EntrustDto> dtoList)
            throws Exception {
		//TODO 根据传入的指令dto做check，并返回登记页面所需参数值
		return null;
	}

}
