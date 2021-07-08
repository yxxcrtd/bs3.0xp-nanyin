package com.joyintech.tams.invest.entrust.etrstockconnect.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect002Service;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;


/**
 * 指令导入
 * 〈功能详细描述〉
 */
@Controller
@PageId("EtrStockConnect003")
@RequestMapping("/invest/entrust/etrstockconnect/EtrStockConnect003")
public class EtrStockConnect003Controller extends BaseController {

	/**
	 * 业务编号组件
	 */
	@Autowired(required = false)
	private BaseGetBusinessId bussGetBussinessIdService;

	@Autowired
	private EtrStockConnect003Service etrStockConnect003Service;

	@Autowired
	private EtrStockConnect002Service etrStockConnect002Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
		model.addAttribute("iPlatformDate", DateUtil.format(sysDate));
		String operateType = getParam("operateType");
		if (StringUtil.equals(operateType, "import")) {
			String tempList = getParam("tempList");
			List<EtrStockConnectDto> etrStockConnectDtoList = etrStockConnect003Service.getEntrustListData(
					tempList);
			model.addAttribute("etrStockConnectDtoList", StringUtil.isEmptyList(
					etrStockConnectDtoList) ? null : JsonUtils.object2Json(etrStockConnectDtoList));
		}
		model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
		return super.init(model);
	}

	/**
	 * 导入excel数据时check
	 *
	 * @param fileName
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("checkImportData")
	public AjaxResult checkImportData(String fileName) {
		return etrStockConnect003Service.checkImportData(fileName);
	}
	/**
	 * 保存按钮点击时的check
	 *
	 * @param dtoList
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody List<EtrStockConnectDto> dtoList, BindingResult result) {
		List<ValidMessage> messages = new ArrayList<>();
		StringBuffer confirmMessage = new StringBuffer();
		// 授信软控 start
		List<JoyinMessage> joyinMessages = new ArrayList<>();
		Map<String, BigDecimal> cusMap  = new HashMap<String, BigDecimal>();
		return commonCheck(result, dtoList.get(0).getOperateType());
	}

	/**
	 * 保存/提交
	 *
	 * @param allDto allDto
	 * @param br br
	 * @return 结果
	 * @throws Exception 异常
	 */
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody EtrStockConnectDto allDto, BindingResult br)
			throws Exception {
		List<EtrStockConnectDto> dtoList = allDto.getSelectedItem();
		AjaxResult ajaxResult = this.check(dtoList, br);
		if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
			return ajaxResult;
		}
		for (EtrStockConnectDto dto : dtoList) {
			if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
				BussinessCodeParameter param = new BussinessCodeParameter();
				param.setCommonBusinessExtType(
						DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_港股通投资委托));
				String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
						param) : IDGenerator.generate();
				dto.setEntrustId(code);
			}
		}

		//预占
//		if (StringUtil.equals(BussinessConstant.SUBMIT, allDto.getOperateType())) {
//			AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(allDto.getSelectedItem(),
//					DictCodeListExt2.平安资产类型_股票.getValue(),
//					BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
//					false, null);
//			if (StringUtil.isNotEmpty(ar)) {
//				return ar;
//			}
//		}

		ProcessParameter<EtrStockConnectDto> processParameter = new ProcessParameter<EtrStockConnectDto>();
		WorkFlowParameter<EtrStockConnectDto> params = new WorkFlowParameter<EtrStockConnectDto>();
		params.setPageId("EtrStockConnect002");
		params.setElementId(dtoList.get(0).getElementId());
		params.setBusinessCode(EtrStockConnectDto::getEntrustId);
		params.setBusinessType(DictCodeListExt3.共通业务类型_港股通投资委托.getValue());
		for (EtrStockConnectDto dto : dtoList) {
			if (dto.getEntrustNum() != null) {
				params.setComment(
						entity -> this.getMessage("EtrStockConnect002") + "(" + entity.getPortfolioId() + ")"
								+ "-" + this.getMessage("EtrStockConnect002.ps") + "("
								+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())
								+ ")" + "-" + "(" + this.getMessage("EtrStockConnect002.vdate") + ":"
								+ DateUtil.format(entity.getVdate()) + ")-("
								+ this.getMessage("EtrStockConnect002.mdate") + ":"
								+ DateUtil.format(entity.getMdate()) + ")-("
								+ this.getMessage("EtrStockConnect002.entrustNum") + ":"
								+ entity.getEntrustNum() + ")-("
								+ this.getMessage("EtrStockConnect002.price") + ":" + entity.getPrice()
								+ ")");
			}
			else {
				params.setComment(
						entity -> this.getMessage("EtrStockConnect002") + "(" + entity.getPortfolioId() + ")"
								+ "-" + this.getMessage("EtrStockConnect002.ps") + "("
								+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())
								+ ")" + "-" + "(" + this.getMessage("EtrStockConnect002.vdate") + ":"
								+ DateUtil.format(entity.getVdate()) + ")-("
								+ this.getMessage("EtrStockConnect002.mdate") + ":"
								+ DateUtil.format(entity.getMdate()) + ")-("
								+ this.getMessage("EtrStockConnect002.entrustAmt") + ":"
								+ entity.getEntrustAmt() + ")");
			}
		}
		processParameter.setWorkFlowParameter(params);
		String msg = etrStockConnect002Service.start(processParameter,
				dtoList.toArray(new EtrStockConnectDto[dtoList.size()]));
		return new AjaxResult(msg);
	}
}
