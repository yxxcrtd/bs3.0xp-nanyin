/*
 * 文件名：ChbBotAstPositionRegist001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zhangzhongliang
 * 修改时间：2017年11月24日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.svreport.chb.botastpositionregist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.service.ChbBotAstPositionRegist001Service;
import com.joyintech.tams.svreport.chb.botastpositionregist.service.ChbBotAstPositionRegistMService;


/**
 * 底层资产持仓信息登记界面Controller
 * 〈功能详细描述〉
 * @author 张忠良
 * @version 1.0 2019年12月24日
 * @see ChbBotAstPositionRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrChbBotAstPositionRegist001")
@RequestMapping("/chb/botastpositionregist/ChbBotAstPositionRegist001")
public class ChbBotAstPositionRegist001Controller extends BaseController{
    /**
     * service
     */
	@Autowired
    private ChbBotAstPositionRegist001Service chbBotAstPositionRegist001Service;
    @Autowired
    private ChbBotAstPositionRegistMService chbBotAstPositionRegistMService;
    
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		super.setSelectOptionToModel(model, DictCode.报送状态, DictCode.报送数据状态, DictCode.报送反馈状态);

		return super.init(model);
	}



	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ChbBotAstPositionRegist001Dto> searchPage() {
		Page<ChbBotAstPositionRegist001Dto> page = chbBotAstPositionRegistMService
				.searchPage(RequestUtil.getPageParam());
		return page;
	}

	
	/**
	 *
	 * 业务画面导出事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() {
		return chbBotAstPositionRegist001Service.export(this.getPageId(), RequestUtil.getPageParam());
	}

	/**
	 * 手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("doManual")
	public AjaxResult doUpdate(@RequestBody Map<String, Object> map) {
		chbBotAstPositionRegistMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
		String result = chbBotAstPositionRegistMService.cancelMannul(map);
		if ("fail".equals(result)) {
			AjaxResult ajaxresult = new AjaxResult("有未报送的数据", MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
	
	@ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
		 return chbBotAstPositionRegistMService.reportCheck(RequestUtil.getPageParam());
    }
	
}
