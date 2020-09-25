package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.ActiveType;
import com.jixu.common.types.CarryOverType;
import com.jixu.common.types.CashFlowFloatMethod;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.InterestInitCode;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.dao.intf.CashFlowPaymentDao;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.service.ConfigService;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowPar;
import com.orm.obj.CashFlowPayment;
import com.orm.obj.CashFlowRate;

@Controller
public class CashFlowController extends ExceptionControl {

	@Resource(name = "configService")
	private ConfigService configService;

	@Resource(name = "commfuncService")
	private CommFuncService commService;
	
	@Autowired
	private CashFlowPaymentDao cashFlowPaymentDao;
	
	@RequestMapping(value = "/config/cfList.do", method = RequestMethod.POST, name = "管理-现金流配置-查询")
	public void cfList(
			@RequestParam(value = "name", required = false) String name,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "名称", "类型", "计提开始", "计提结束",
				"计提结转", "合同利率", "日计数方法", "付款频率", "是否有效");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		List<CashFlowCfg> cfgList = configService.loadCashFlowCfgList(name);

		for (CashFlowCfg cfg : cfgList) {
			List<Object> item = new ArrayList<Object>();
			item.add(cfg.getCashFlowID());
			item.add(cfg.getCashFlowName());
			item.add(DBCR.optionsHM.get(cfg.getRecOrPay()));
			item.add("T" + cfg.getStartNDays());
			item.add("T-" + cfg.getEndNDays());
			item.add("T+" + cfg.getCpnDelayDateNum());
			item.add(cfg.getCoupon());
			item.add(InterestInitCode.intInitCodeHM.get(cfg.getIntCode()));
			item.add(CouponData.intCpnfReqTypeHM.get(cfg.getCpnFreq()));
			item.add(ActiveType.activeTypeHM.get(cfg.getActiveFlag()));
			data.add(item);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfShortList.do", method = RequestMethod.POST)
	public void cfShoList(
			@RequestParam(value = "name", required = false) String name,
			HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(100, "cf1");
		result.put(101, "cf2");
		result.put(102, "cf3");
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/cf.do", method = RequestMethod.GET, name = "管理-现金流配置-页面显示")
	public String cf() {
		return "cf";
	}

	@RequestMapping(value = "/config/cfCreate.do", method = RequestMethod.POST, name = "管理-现金流配置-创建")
	public void cfCreate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {

		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	@RequestMapping(value = "/config/cfCreate_new.do", method = RequestMethod.POST)
	public void cfCreate_new(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {

		MsgContainer container = commService.executeMsg(request, json);
		HashMap<String, Object> status = new HashMap<String, Object>();
		HashMap<String,Object> result=new HashMap<String, Object>();
		if(container.isCorrectFlag()){
			status.put("success", 1);
		}else{
			status.put("success", 0);
		}
		status.put("message", container.getErrorMsg());
		status.put("code", container.getErrorCode());
		result.put("status", status);
		result.put("cashflowcfg", container.getEntityID());
		result.put("cashflowcfg2", container.getEntityID());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfDetail.do", method = RequestMethod.POST, name = "管理-现金流配置-详情")
	public void cfDetail(@RequestParam(value = "id", required = true) String cfID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "名称", "类型", "假期安排", "是否生效",
				"计提开始(T+N)", "日期类型", "计提结束(T-N)", "日期类型", "是否为外部利率?", "合同利率",
				"有效利率", "固定计提", "日计数方法", "结转方式", "付款频率", "付款月份", "付款日",
				"实际付款日", "日期类型");
		result.put("titles", titles);

		CashFlowCfg cfg = configService.loadCashFlowCfg(cfID);

		List<Object> data = new ArrayList<Object>();
		data.add(cfg.getCashFlowID());
		data.add(cfg.getCashFlowName());
		data.add(cfg.getRecOrPay());
		data.add(cfg.getCpnType());
		data.add(DateUtil.convertD2YYYY_MM_DD(cfg.getFirstIntDate()));
		data.add(cfg.getHolidaySD());
		data.add(cfg.getActiveFlag());

		data.add(cfg.getStartNDaysBase());
		data.add(cfg.getStartNDays());
		data.add(cfg.getStartNDaysType());

		data.add(cfg.isExternalRateFlag());
		data.add(cfg.getCoupon());
		data.add(cfg.getFixedIncome());
		data.add(cfg.getIntCode());

		data.add(cfg.getPaymentKind());
		data.add(cfg.getCpnFreq());
		data.add(cfg.getCpnMo());
		data.add(cfg.getCpnDate());
		data.add(cfg.getCpnDelayDateNum());
		data.add(cfg.getCpnDelayDateType());

		data.add(cfg.getEndNDaysBase());
		data.add(cfg.getEndNDays());
		data.add(cfg.getEndNdaysType());

		data.add(1); // 等额本金，等额本息？
		data.add(cfg.isAutoSetFlag());
		data.add(cfg.isAutoInterestFlag());

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	 @RequestMapping(value = "/config/newCfDetail.do", method = RequestMethod.POST, name = "管理-现金流配置-详情")
	 public void newCfDetail(@RequestParam(value = "id", required = true) String cfID,
	   HttpServletResponse response) {
	  HashMap<String, Object> result = new HashMap<String, Object>();
	  CashFlowCfg cfg = configService.loadCashFlowCfg(cfID);
	  if(cfg!=null) {
		  result.put("basis", cfg.getStartNDaysBase());
		  result.put("accrStartN", cfg.getStartNDays());
		  result.put("accrStartType", cfg.getHolidaySD());
		  result.put("contractRate", cfg.getCoupon());
		  result.put("dayCount", cfg.getIntCode());
		  result.put("frequency", cfg.getCpnFreq());
		  result.put("paymentMonth", cfg.getCpnMo());
		  result.put("paymentDay", cfg.getCpnDate());
		  result.put("paymentNType",cfg.getEndNdaysType());
		  result.put("type",cfg.getRecOrPay());
		  result.put("accurStartDate", DateUtil.convertD2YYYY_MM_DD(cfg.getFirstIntDate()));
		  result.put("status", cfg.getActiveFlag());
		  if(cfg.getCpnType()==CouponData.cpn_float){
			  result.put("base", cfg.getVrBase());
			  result.put("base2", cfg.getVrBase());
			  result.put("status2", cfg.getActiveFlag());
			  result.put("vrMethod", cfg.getVrMethod());
			  result.put("factor", cfg.getVrBasisPtAdjustment());
			  result.put("factor2", cfg.getVrBasisPtAdjustment());
			  result.put(MsgFields.max, cfg.getVrMax());
			  result.put(MsgFields.min, cfg.getVrMin());
			  result.put("resetfrequency", cfg.getVrResetFreq());
			  result.put(MsgFields.month, cfg.getVrResetMo());
			  result.put(MsgFields.day, cfg.getVrResetDate());
			  result.put("vrResetNDays", cfg.getVrResetNDays());
			  result.put("vrDeterAdjMo", cfg.getVrDeterAdjMo());
			  result.put("vrDeterAdjDays", cfg.getVrDeterAdjDays());
		  }
	  }
	  JsonUtil.writeObjToResponse(response, result);
	 }
	 
	 

	@RequestMapping(value = "/config/cfUpdate.do", method = RequestMethod.POST, name = "管理-现金流配置-更新")
	public void cfUpdate(String json, HttpServletRequest request, HttpServletResponse response) {

		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	
	@RequestMapping(value = "/config/newCfUpdate.do", method = RequestMethod.POST, name = "管理-互换现金流配置-更新")
	public void newCfUpdate(
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {

		MsgContainer container = commService.executeMsg(request, json);
		HashMap<String, Object> status = new HashMap<String, Object>();
		HashMap<String,Object> result=new HashMap<String, Object>();
		if(container.isCorrectFlag()){
			status.put("success", 1);
		}else{
			status.put("success", 0);
		}
		status.put("message", container.getErrorMsg());
		status.put("code", container.getErrorCode());
		result.put("status", status);
		result.put("cashflowcfg", container.getObjID());
		result.put("cashflowcfg2", container.getObjID());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfRateCreate.do", method = RequestMethod.POST, name = "管理-现金流利率-创建")
	public void cfRateCreate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	@RequestMapping(value = "/config/cfRateUpdate.do", method = RequestMethod.POST, name = "管理-现金流利率-更新")
	public void cfRateUpdate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	@RequestMapping(value = "/config/cfRateRemove.do", method = RequestMethod.POST, name = "管理-现金流利率-删除")
	public void cfRateRemove(
			@RequestParam(value = "pid", required = true) int cfID,
			@RequestParam(value = "id", required = true) int rateID,
			HttpServletRequest request, HttpServletResponse response) {
		int userID = JixuUserUtil.getUserIDbyRequest(request);

		MsgContainer container = new MsgContainer();
		HashMap<String, Object> fieldsHM = new HashMap<String, Object>();
		String header = "cashFlowRateMsg";
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(rateID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);

		container = MsgFactory.executeMsgBean(container);

		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, "");
		} else {
			JsonUtil.writeObjToResponse(response, genErrorResult(container));
		}
	}

	@RequestMapping(value = "/config/cfRateDetail.do", method = RequestMethod.POST, name = "管理-现金流利率-详情")
	public void cfRateDetail(
			@RequestParam(value = "id", required = true) String cfID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "起息日期", "利率");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		List<CashFlowRate> cfrList = configService.loadCashFlowRateByCfID(cfID);
		for (CashFlowRate cfr : cfrList) {
			List<Object> item = new ArrayList<Object>();
			item.add(cfr.getId());
			item.add(DateUtil.convertD2YYYY_MM_DD(cfr.getCfDate()));
			item.add(cfr.getCfRate());
			data.add(item);

		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfPaymentCreate.do", method = RequestMethod.POST, name = "管理-现金流付息计划-创建")
	public void cfPaymentCreate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	@RequestMapping(value = "/config/cfPaymentUpdate.do", method = RequestMethod.POST, name = "管理-现金流付息计划-更新")
	public void cfPaymentUpdate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	@RequestMapping(value = "/config/cfPaymentRemove.do", method = RequestMethod.POST, name = "管理-现金流付息计划-删除")
	public void cfPaymentRemove(
			@RequestParam(value = "pid", required = true) int cfID,
			@RequestParam(value = "id", required = true) int paymentID,
			HttpServletRequest request, HttpServletResponse response) {
		int userID = JixuUserUtil.getUserIDbyRequest(request);

		MsgContainer container = new MsgContainer();
		HashMap<String, Object> fieldsHM = new HashMap<String, Object>();
		String header = "cashFlowPaymentMsg";
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(paymentID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);

		container = MsgFactory.executeMsgBean(container);

		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, "");
		} else {
			JsonUtil.writeObjToResponse(response, genErrorResult(container));
		}
	}

	@RequestMapping(value = "/config/cfPaymentDetail.do", method = RequestMethod.POST, name = "管理-现金流提前偿还-详情")
	 public void cfPaymentDetail(
	   @RequestParam(value = "cashflowID", required = false) String cashflowID,
	   HttpServletResponse response) {
	  HashMap<String, Object> result = new HashMap<String, Object>();
	  List<Object> data = new ArrayList<Object>();
	  
	  if(cashflowID!=null && !cashflowID.equals("") && !cashflowID.equals("undefined")){
	   List<CashFlowPayment> sechDateList=cashFlowPaymentDao.loadCashFlowPaymentByCfID(cashflowID);
	      for(CashFlowPayment cp:sechDateList){
	         HashMap<String, Object> map=new HashMap<String, Object>();
	         map.put("settleDate", DateUtil.convertD2YYYY_MM_DD(cp.getPayDate()));
	         data.add(map);
	       }
	        result.put("data", data);
	  }
	        JsonUtil.writeObjToResponse(response, result);
	 }

	
	@RequestMapping(value = "/config/cfFloatCreate.do", method = RequestMethod.POST, name = "管理-现金流浮动利率重置-创建")
	public void cfFloatCreate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	
	@RequestMapping(value = "/config/cfFloatUpdate.do", method = RequestMethod.POST, name = "管理-现金流浮动利率重置-更新")
	public void cfFloatUpdate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	
	@RequestMapping(value = "/config/cfFloatRemove.do", method = RequestMethod.POST, name = "管理-现金流浮动利率重置-删除")
	public void cfFloatRemove(
			@RequestParam(value = "pid", required = true) int cfID,
			@RequestParam(value = "id", required = true) int floatID,
			HttpServletRequest request, HttpServletResponse response) {
		int userID = JixuUserUtil.getUserIDbyRequest(request);

		MsgContainer container = new MsgContainer();
		HashMap<String, Object> fieldsHM = new HashMap<String, Object>();
		String header = "cashFlowFloatRateMsg";
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(floatID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);

		container = MsgFactory.executeMsgBean(container);

		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, "");
		} else {
			JsonUtil.writeObjToResponse(response, genErrorResult(container));
		}
	}

	@RequestMapping(value = "/config/cfFloatDetail.do", method = RequestMethod.POST, name = "管理-现金流浮动利率重置-详情")
	public void cfFloatDetail(
			@RequestParam(value = "id", required = true) String cfID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = configService.getCashFlowFloatDetail(cfID);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	@RequestMapping(value = "/config/cfParCreate.do", method = RequestMethod.POST, name = "管理-现金流还本计划-创建")
	public void cfParCreate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}

	
	@RequestMapping(value = "/config/cfParUpdate.do", method = RequestMethod.POST, name = "管理-现金流还本计划-更新")
	public void cfParUpdate(
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}


	@RequestMapping(value = "/config/cfParRemove.do", method = RequestMethod.POST, name = "管理-现金流还本计划-删除")
	public void cfParRemove(
			@RequestParam(value = "pid", required = true) int cfID,
			@RequestParam(value = "id", required = true) int parID,
			HttpServletRequest request, HttpServletResponse response) {
		int userID = JixuUserUtil.getUserIDbyRequest(request);

		MsgContainer container = new MsgContainer();
		HashMap<String, Object> fieldsHM = new HashMap<String, Object>();
		String header = "cashFlowParMsg";
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(parID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);

		container = MsgFactory.executeMsgBean(container);

		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, "");
		} else {
			JsonUtil.writeObjToResponse(response, genErrorResult(container));
		}
	}


	@RequestMapping(value = "/config/cfParDetail.do", method = RequestMethod.POST, name = "管理-现金流还本计划-详情")
	public void cfParDetail(
			@RequestParam(value = "id", required = true) String cfID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "还本日期", "还本比例(%)");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		CashFlowCfg cfc=configService.loadCashFlowCfg(cfID);
		Set<CashFlowPar> parsSet=cfc.getCashFlowPar();
		Iterator<CashFlowPar> pars=parsSet.iterator();
		while(pars.hasNext()){
			CashFlowPar cfp=pars.next();
			List<Object> item = new ArrayList<Object>();
			item.add(cfp.getId());
			item.add(DateUtil.convertD2YYYY_MM_DD(cfp.getPaymentDate()));
			item.add(DateUtil.convertD2YYYY_MM_DD(cfp.getSettleDate()));
			item.add(cfp.getTranType());
			item.add(cfp.getPaymentRate());
			data.add(item);

		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfPrepaymentMethods.do", method = RequestMethod.POST)
	public void cfPrepaymentMethods(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "还本付息");
		result.put(1, "仅还本");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/cfTypes.do", method = RequestMethod.POST)
	public void lotMethods(HttpServletResponse response) {
		HashMap<Integer, String> result = CouponData.cpnInterestTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 利率互换的现金流类型选择
	 * @param response
	 */
	@RequestMapping(value = "/config/cfSwapTypes.do", method = RequestMethod.POST)
	public void cfSwapTypes(HttpServletResponse response) {
		HashMap<Integer, String> result = CouponData.cfSwapTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	//根据收入端不同类型现金流返回不同的URL
	 @RequestMapping(value = "/config/cfURL.do", method = RequestMethod.POST)
	 public void cfURL(@RequestParam(value = "type", required = true) int type,
			 @RequestParam(value = "operation", required = true) String operation,
	   HttpServletResponse response) {
	  String cashFlowURL="";
	  //利率互换新界面      固定 浮动利率返回不同的现金流URL
	  if (type == CouponData.cpn_fixed){
		  if(operation.equalsIgnoreCase(MsgFields.add)) {
			  cashFlowURL = "interest_swap_cf_fixing";
		  }else {
			  cashFlowURL = "interest_swap_cf_fixing_edit";
		  }
	 
	  }else if(type == CouponData.cpn_float){
		  if(operation.equalsIgnoreCase(MsgFields.add)) {
			  cashFlowURL = "interest_swap_cf_floating";
		  }else {
			  cashFlowURL = "interest_swap_cf_floating_edit";
		  }
	  }
	  JsonUtil.writeObjToResponse(response, cashFlowURL);
	 }
	 
	//根据支出端不同类型现金流返回不同的URL
	@RequestMapping(value = "/config/cfURL2pay.do", method = RequestMethod.POST)
	public void cfURL2pay(@RequestParam(value = "type", required = true) int type,
			@RequestParam(value = "operation", required = true) String operation,
		  HttpServletResponse response) {
		String cashFlowURL="";
		//利率互换新界面      固定 浮动利率返回不同的现金流URL
		if (type == CouponData.cpn_fixed){
			if(operation.equalsIgnoreCase(MsgFields.add)) {
				  cashFlowURL = "interest_swap_cf_fixing_pay";
			  }else {
				  cashFlowURL = "interest_swap_cf_fixing_pay_edit";
			  }
		}else if(type == CouponData.cpn_float){
			if(operation.equalsIgnoreCase(MsgFields.add)) {
				  cashFlowURL = "interest_swap_cf_floating_pay";
			  }else {
				  cashFlowURL = "interest_swap_cf_floating_pay_edit";
			  }
		 }
		  JsonUtil.writeObjToResponse(response, cashFlowURL);
		}

	@RequestMapping(value = "/config/cfBasisClass.do", method = RequestMethod.POST)
	public void cfBasisClass(HttpServletResponse response) {
		//反射    界面显示基准类service
		HashMap<String, String> result = commService.getFloatRateIDClassMap();
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/cfCarryOverMethods.do", method = RequestMethod.POST)
	public void carryOverMethods(HttpServletResponse response) {
		HashMap<Integer, String> result = CarryOverType.methodsHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/parMethods.do", method = RequestMethod.POST)
	public void parMethods(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(TransTypes.Bond_HBFX_Principal, "按面额");
		result.put(TransTypes.Bond_HBFX_Shares, "按数量");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 浮动利率的计算方法
	 * @param response
	 */
	@RequestMapping(value = "/config/cashFlowFloatMethod.do", method = RequestMethod.POST)
	public void parCashFlowFloatMethod(HttpServletResponse response) {
		HashMap<Integer, String> result = CashFlowFloatMethod.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 基准利率的获得 shibo
	 * @param response
	 */
	@RequestMapping(value = "/config/basisRates.do", method = RequestMethod.POST)
	public void basisRates(HttpServletResponse response) {
		HashMap<String, String> result=new HashMap<String, String>();
		if(JixuUserUtil.isJoyinTablesFlag()) {
			DataDict dataDict = CacheUtil.getDictCode("SECRATE_DATA");
			List<? extends DataDictItem> itemList = dataDict.getItems(); 
			for (DataDictItem dictItem : itemList) { 
				result.put(dictItem.getItemCode(), dictItem.getItemValue());
			}
		}else {
			result = configService.getBasePointList();
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 浮动利率的重置频率选择
	 * @param response
	 */
	@RequestMapping(value = "/config/cashFlowFloatFrequency.do", method = RequestMethod.POST)
	public void parCashFlowFloatFrequency(HttpServletResponse response) {
		HashMap<Integer, String> result = CouponData.cashFlowFloatReqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	private HashMap<String, Object> genErrorResult(ResultContainer container) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> issue = new HashMap<String, Object>();
		issue.put("code", container.getHeader());
		issue.put("level", "fail");
		issue.put("detail", container.getErrorMsg());
		result.put("issue", issue);
		return result;
	}
}
