package com.joyintech.tams.cfets.bankuplink.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.business.common.dto.PageMode;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.bankuplink.bond.dto.*;
import com.joyintech.tams.cfets.bankuplink.bond.service.IbuQuoteBondCalService;
import com.joyintech.tams.cfets.bankuplink.bond.service.IbuQuoteBondService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  初始化查询页面
 */
@Controller
@PageId("IbuQuoteBond")
@RequestMapping("/cfets/bankuplink/bond/IbuQuoteBond")
@Api(tags = "CFETS债券交易-常用查询类接口")
public class CfetsIbuQuoteBondController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CfetsIbuQuoteBondController.class);

	@Autowired
	private IbuQuoteBondService ibuQuoteBondService;

	@Autowired
	private IbuQuoteBondCalService ibuQuoteBondCalService;

	@ApiOperation(value = "查询报价页面初始化参数", notes = "查询报价页面初始化参数")
	@PostMapping("/getPageParams")
	@ResponseBody
	public Message<Map<String, Object>> getPageParams(){
		Map<String, Object> initPageParams = new HashMap<>();
		initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
		initPageParams.put("ACCOUNT_SPEED", this.getSelectDataFromDataDic(DictCode.清算速度, null));
		initPageParams.put("SETT_MODE", this.getSelectDataFromDataDic(DictCode.结算方式, null));
		initPageParams.put("CLEAR_TYPE", this.getSelectDataFromDataDic(DictCode.清算类型, null));
		initPageParams.put("CCY", this.getSelectDataFromDataDic(DictCode.币种, null));
		initPageParams.put("ACCOUNT_SPEED_0",BussinessDictCodeList.清算速度_T加0.getValue());
		initPageParams.put("ACCOUNT_SPEED_1",BussinessDictCodeList.清算速度_T加1.getValue());
		initPageParams.put("ACCOUNT_SPEED_2",BussinessDictCodeList.清算速度_T加2.getValue());
		initPageParams.put("yesNoFlag_yes",BussinessDictCodeList.是否_是.getValue());
		initPageParams.put("assetTypeOne_abs",BussinessDictCodeList.平安资产一级分类_资产支持证券.getValue());
		initPageParams.put("calcTypeUnitCprice",BussinessDictCodeList.计算选择方式_净价.getValue());
		initPageParams.put("calcTypeYield",BussinessDictCodeList.计算选择方式_到期收益率.getValue());
		initPageParams.put("calcTypeExecYield",BussinessDictCodeList.计算选择方式_行权收益率.getValue());
		initPageParams.put("PRICE_MODEL_PRM01",DictCodeListExt2.价格模式_指定价格.getValue());
		initPageParams.put("ouTraderIdCanEditFlag",CacheUtil.getSysParam("ibuFlag_ouTraderIdCanEdit"));
		initPageParams.put("ibuFlag_startDialogueQuoteWithNoEntrust",CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"));
		initPageParams.put("SHORT_SELLING_SUPPORTED",CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
		initPageParams.put("pageId", "CfetsBondDealQuotedPrice002");
		return new Message<Map<String, Object>>(initPageParams).success();
	}

	@ApiOperation(value = "查询指令列表分页数据", notes = "查询指令列表分页数据")
	@PostMapping("/pageEntrust")
	@ResponseBody
	public Message<Page<EntrustDto>> pageEntrust(@RequestBody EntrustRequest dto){
		try {
			Page<EntrustDto> list = ibuQuoteBondService.listEntrust(dto);
			return new Message<Page<EntrustDto>>().success().setData(list);
		}catch (Exception e) {
			logger.error("查询指令列表分页数据异常",e);
			return new Message<Page<EntrustDto>>().fail().setMessage("查询指令列表分页数据异常");
		}
	}


	@ApiOperation(value = "接收委托(批量)", notes = "接收委托(批量)")
	@PostMapping("receiveEntrust")
	@ResponseBody
	public Message<List<ValidMessage>> recallDelegate(@RequestBody Map<String,Object> param){
		try{
			List<ValidMessage> validMessages = ibuQuoteBondService.receiveEntrust(param);
			return new Message<List<ValidMessage>>().success().setData(validMessages);
		}catch (Exception e){
			logger.error("接收委托(批量)异常",e);
			return new Message<List<ValidMessage>>().fail().setMessage("接收委托(批量)异常");
		}
	}

	@ApiOperation(value = "交易执行初始化检查", notes = "交易执行初始化检查")
	@PostMapping("/dealexecutioncheck")
	@ResponseBody
	public Message<AjaxResult> dealexecutioncheck(@RequestBody CfetsBondDealQuoteRequest cfetsBondDealQuoteRequest){
		AjaxResult ajaxResult = ibuQuoteBondService.dealexecutioncheck(cfetsBondDealQuoteRequest);
		return new Message<AjaxResult>().success().setData(ajaxResult);
	}


	@ApiOperation(value = "交易执行初始化", notes = "交易执行初始化")
	@PostMapping("/dealexecutioninit")
	@ResponseBody
	public Message<CfetsBondDealQuoteCommonDto> dealexecutioninit(@RequestBody CfetsBondDealQuoteRequest cfetsBondDealQuoteRequest){
		CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto = ibuQuoteBondService.queryEntrustInfoById(cfetsBondDealQuoteRequest);
		return new Message<CfetsBondDealQuoteCommonDto>().success().setData(cfetsBondDealQuoteCommonDto);
	}


	@ApiOperation(value = "组合分配添加初始化", notes = "组合分配添加初始化")
	@PostMapping("/ptlAllocationAdd")
	@ResponseBody
	public Message<List<PtlAllocationDto>> ptlAllocationAdd(@RequestBody PtlAllocationAddRequest ptlAllocationAddRequest) {
		try {
			List<PtlAllocationDto> list = ibuQuoteBondService.ptlAllocationAdd(ptlAllocationAddRequest);
			return new Message<List<PtlAllocationDto>>().success().setData(list);
		} catch (Exception e) {
			logger.error("组合分配添加初始化异常", e);
			return new Message<List<PtlAllocationDto>>().fail().setMessage("组合分配添加初始化异常");
		}
	}

	@ApiOperation(value = "数据计算", notes = "净价、到期收益率、行权收益率联动计算")
	@PostMapping("/calculation")
	@ResponseBody
	public Message<CalRespone> calculation(@RequestBody CalRequest calRequest) {
		try {
			CalRespone calculation = ibuQuoteBondCalService.calculation(calRequest);
			return new Message<CalRespone>().success().setData(calculation);
		} catch (Exception e) {
			logger.error("数据计算异常", e);
			return new Message<CalRespone>().fail().setMessage("数据计算异常");
		}
	}


	@ApiOperation(value = "自动分配", notes = "自动分配")
	@PostMapping("/autoAllocation")
	@ResponseBody
	public Message<List<PtlAllocationDto>> autoAllocation(@RequestBody AutoAllocationRequest autoAllocationRequest) {
		try {
			List<PtlAllocationDto> list = ibuQuoteBondCalService.autoAllocation(autoAllocationRequest);
			return new Message<List<PtlAllocationDto>>().success().setData(list);
		} catch (Exception e) {
			logger.error("自动分配异常", e);
			return new Message<List<PtlAllocationDto>>().fail().setMessage("自动分配异常");
		}
	}

	public static void main(String[] args) {
		EntrustRequest dto = new EntrustRequest();
		PageMode pageMode = new PageMode();
		pageMode.setPageNo(1);
		pageMode.setPageSize(10);
		pageMode.setSort("name");
		pageMode.setDirection("asc");
		dto.setPageMode(pageMode);
		System.out.println(JsonUtils.object2Json(dto));


	}
}
