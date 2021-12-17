package com.joyintech.tams.entrust.etrexchangetrade.controller;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *  初始化查询页面
 */
@Controller
@PageId("etrExchangeTradeMain")
@RequestMapping("/exchange/etrexchangetrade/etrExchangeTradeMain")
@Api(tags = "竞价交易-主画面查询类接口")
public class EtrExchangeTouguanMainController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(EtrExchangeTouguanMainController.class);



	@ApiOperation(value = "初始化参数", notes = "初始化参数")
	@PostMapping("/initParams")
	@ResponseBody
	public Message<Map<String, Object>> initParams(){
		Map<String, Object> initPageParams = new HashMap<>();
		initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
		initPageParams.put("PAGE_ID_REGIT","IbuQuoteBond004");
		initPageParams.put("PAGE_ID_DIALOG","CfetsBondDealQuotedPrice004");
		initPageParams.put("PAGE_ID_REQUEST","CfetsBondDealQuotedPrice005");
		//01: 开启银行间报价 02:关闭银行间报价（事后录入）
		return new Message<Map<String, Object>>(initPageParams).success();
	}

}
