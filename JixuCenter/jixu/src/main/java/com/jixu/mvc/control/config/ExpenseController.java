package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.ActiveType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeTypeMap;

@Controller
public class ExpenseController extends ExceptionControl {
	@Resource(name = "configService")
	private ConfigService configService;

	@Autowired
	private CommFuncService commfuncService;

	@RequestMapping(value = "/config/expenseList.do", method = RequestMethod.POST, name = "管理-交易费用-查询")
	public void expenseList(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "费用名称", "费用化比例%", "现金付款比例%",
				"成本总账栏", "费用总账栏", "证券清算款总账栏", "应付应收总账栏", "状态");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		List<TranFeeDef> feeDefList = configService.loadTranFeeDefList();

		for (TranFeeDef feeDef : feeDefList) {
			List<Object> item1 = new ArrayList<Object>();

			item1.add(feeDef.getId());
			item1.add(feeDef.getFeename());
			item1.add(feeDef.getUnCapitalizedPercent());
			item1.add(feeDef.getCashPercent());
			item1.add(GLACCT.optionHM().get(feeDef.getCostGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getFeeGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getSecPayGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getRcpyGlCol()));
			item1.add(ActiveType.activeTypeHM.get(feeDef.getStatus()));
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/newExpenseList.do", method = RequestMethod.POST, name = "新交易费用-查询")
	public void newExpenseList(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();

		List<TranFeeDef> feeDefList = configService.loadTranFeeDefList();

		for (TranFeeDef feeDef : feeDefList) {
			List<Object> item1 = new ArrayList<Object>();

			item1.add(feeDef.getId());
			item1.add(feeDef.getFeename());
			//新界面显示费用   添加一列  费用类型
			item1.add(ExpenseBaseType.expenseType2HM.get(feeDef.getOneTimeFeeFlag()));
			item1.add(feeDef.getUnCapitalizedPercent());
			item1.add(feeDef.getCashPercent());
			item1.add(GLACCT.optionHM().get(feeDef.getCostGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getFeeGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getSecPayGlCol()));
			item1.add(GLACCT.optionHM().get(feeDef.getRcpyGlCol()));
			item1.add(ActiveType.activeTypeHM.get(feeDef.getStatus()));
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/expense.do", method = RequestMethod.GET, name = "管理-交易费用-页面显示")
	public String expense() {
		return "expense";
	}

	@RequestMapping(value = "/config/expenseCreate.do", method = RequestMethod.POST, name = "管理-交易费用-创建")
	public void expenseCreate(
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commfuncService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}

	// TODO-可返回JSON模式
	@RequestMapping(value = "/config/expenseDetail.do", method = RequestMethod.POST, name = "管理-交易费用-详情")
	public void expenseDetail(
			@RequestParam(value = "id", required = true) int expenseID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		TranFeeDef feeDef = configService.loadTranFeeDef(expenseID);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", feeDef.getId());
		data.put("name", feeDef.getFeename());
		data.put("type", feeDef.getOneTimeFeeFlag());
		data.put("expenseRatio", feeDef.getUnCapitalizedPercent());
		data.put("cashRatio", feeDef.getCashPercent());
		data.put("principalGL", feeDef.getCostGlCol());
		data.put("expenseGL", feeDef.getFeeGlCol());
		data.put("settleGL", feeDef.getSecPayGlCol());
		data.put("rcpyGL", feeDef.getRcpyGlCol());
		data.put("rateType", feeDef.getFixedFlag());
		data.put("cf", feeDef.getCashFlowID());
		data.put("status", feeDef.getStatus());

		Iterator<TranFeeTypeMap> iterator = feeDef.getTranFeeTypeMaps().iterator();

		List<String> types = new ArrayList<String>();
		while (iterator.hasNext()) {
			types.add(iterator.next().getAssetType());
		}

		ArrayList<Integer> trades = new ArrayList<Integer>();
		trades.add(17);
		data.put("assetTypes", types);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//通过费用类型(每日计提或一次计提的不同类型  返回不同的url进入不同的json)
	@RequestMapping(value = "/config/newExpenseDetailURL.do", method = RequestMethod.POST)
	public void newExpenseDetail(
			@RequestParam(value = "id", required = true) int expenseID,
			HttpServletResponse response) {
		String formURL="";
		HashMap<String, Object> result = new HashMap<String, Object>();
		TranFeeDef feeDef = configService.loadTranFeeDef(expenseID);
		if(feeDef.getOneTimeFeeFlag()==ExpenseBaseType.onetimeFee) 
			formURL="trade_expense_once_edit";
		else
			formURL="trade_expense_daily_edit";
		JsonUtil.writeObjToResponse(response, formURL);
	}

	@RequestMapping(value = "/config/expenseUpdate.do", method = RequestMethod.POST, name = "管理-交易费用-更新")
	public void expenseUpdate(
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		MsgContainer container = commfuncService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}

	@RequestMapping(value = "/config/expenseStatus.do", method = RequestMethod.POST)
	public void expenseStatus(HttpServletResponse response) {
		HashMap<Integer, String> result = ActiveType.activeTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/expenseRPType.do", method = RequestMethod.POST)
	public void expenseRPType(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(DBCR.receive, DBCR.optionsHM.get(DBCR.receive));
		result.put(DBCR.pay, DBCR.optionsHM.get(DBCR.pay));
		JsonUtil.writeObjToResponse(response, result);
	}

	//返回应收应付名称  新框架前端费用显示需要
	 @RequestMapping(value = "/config/rpTypeByCode.do", method = RequestMethod.POST)
	 public void rpTypeByCode(@RequestParam(value = "rpType", required = true) int expenseCode,
	   HttpServletResponse response) {
		String expenseType = DBCR.optionsHM.get(expenseCode);
		if (expenseType != null) {
			JsonUtil.writeObjToResponse(response, expenseType);
	  }
	 }
	
	
	
	@RequestMapping(value = "/config/expenseTypes.do", method = RequestMethod.POST)
	public void expenseTypes(HttpServletResponse response) {
		/*
		 * result.put(0,"一次性费用"); result.put(1,"计提型费用");
		 */
		HashMap<Integer, String> result = ExpenseBaseType.expenseType2HM;
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/expenseRateTypes.do", method = RequestMethod.POST)
	public void expenseRateTypes(HttpServletResponse response) {
		/*
		 * result.put(0,"固定"); result.put(1,"浮动");
		 */
		HashMap<Integer, String> result = ExpenseBaseType.expenseTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	// 给出expenseID，返回该expense是一次性费用，还是计提型费用
	@RequestMapping(value = "/config/checkExpenseType.do", method = RequestMethod.POST)
	public void checkExpenseType(
			@RequestParam(value = "id", required = true) int expenseID,
			HttpServletResponse response) {
		//int sumlum = configService.checkFeeFixed(expenseID);
		JsonUtil.writeObjToResponse(response, 0);
	}
	

	@RequestMapping(value = "/config/subExpenseTypes.do", method = RequestMethod.POST)
	public void subExpenseTypes(@RequestParam(value = "port", required = true) String portID,
								@RequestParam(value = "secid", required = true) String secID,
								@RequestParam(value = "contract", required = true) int lotNum,
							     HttpServletResponse response) {
		String[] smfArr=secID.split("\\|");
		String secid="";
		if(smfArr.length==3){
			secid=smfArr[1];
		}else{
			secid=secID;
		}
		HashMap<Integer, String> result=new HashMap<Integer, String>();
		List<TranFeeDef> tranFeeDefList =configService.subExpenseTypes(portID, secid, lotNum);
		for (TranFeeDef tranFeeDef : tranFeeDefList) {
			result.put(tranFeeDef.getId(), tranFeeDef.getFeename());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
}
