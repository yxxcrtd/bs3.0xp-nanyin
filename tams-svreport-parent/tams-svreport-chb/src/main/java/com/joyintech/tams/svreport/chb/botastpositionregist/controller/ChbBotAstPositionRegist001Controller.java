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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.component.svrreport.chb.dto.ChbBotAstPosSubDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.svreport.chbsd.bottomastsub.service.SdBotAssetPosRegBatchService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SdBotAssetPosRegBatchService sdBotAssetPosRegBatchService;
    
	
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
    public Map<String,Object> reportCheck() {
		 return chbBotAstPositionRegistMService.reportCheck(RequestUtil.getPageParam());
    }

	/**start  中债一二期直连代码  add  by  wangzehui  20201012*/

	/**
	 * 中债直连报送
	 *
	 * @param map
	 *            传入需要报送的主键
	 * @return 反馈信息
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/submitChb", method = RequestMethod.POST)
	public AjaxResult doSubmit(@RequestBody Map<String, Object> param) throws Exception{

		List<ChbBotAstPositionRegist001Dto> list=new ArrayList<>();
		// 选择行数据时
		if (param.containsKey("keyList")) {
			String keyList = param.get("keyList").toString();
			try {
				list = JsonUtils.json2List(keyList,
						ChbBotAstPositionRegist001Dto.class);
			}
			catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else{
			Page<ChbBotAstPositionRegist001Dto> page = chbBotAstPositionRegistMService
					.searchPage(RequestUtil.getPageParam());
			if(page!=null&&page.getRoot()!=null&&page.getRoot().size()>0){
				list = page.getRoot();
			}
			//查询数据
		}
		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbBotAstPosSubDto> dataList = new ArrayList<ChbBotAstPosSubDto>();
		for(ChbBotAstPositionRegist001Dto sdDto:list){
			ChbBotAstPosSubDto target = new ChbBotAstPosSubDto();
			BeanUtil.copyPropertiesIgnoreNull(target, sdDto);
			dataList.add(target);
		}

		sdBotAssetPosRegBatchService.sendBatchCommon(dataList);

		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0018"));
	}

	/**end  中债一二期直连代码  add  by  wangzehui  2020100910*/
	
}
