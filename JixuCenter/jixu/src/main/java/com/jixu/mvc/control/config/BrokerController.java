package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.ActiveType;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.SeatNumberDao;
import com.jixu.mvc.control.model.SecAccountModel;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.CoaaGroup;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SeatNumber;
import com.orm.obj.SecAcct;

@Controller
public class BrokerController extends ExceptionControl {
	@Resource(name = "configService")
	private ConfigService configService;

	@Resource(name = "portCfgService")
	private PortfolioCfgService portCfgService;

	@Autowired
	private SeatNumberDao seatNumberDao;

	@Resource(name = "commfuncService")
	private CommFuncService commService;

	@RequestMapping(value = "/broker.do", method = RequestMethod.GET, name = "管理-券商-页面显示")
	public String trade() {
		return "broker";
	}

	@RequestMapping(value = "/config/brokerList.do", method = RequestMethod.POST, name = "管理-券商-查询")
	public void brokerList(@RequestParam(value = "portID", required = false) String portID,
			@RequestParam(value = "ports", required = false) String ports, HttpServletResponse response) {
		List<String> portsList = new ArrayList<String>();
		if (portID != null && !portID.equals("")) {
			portsList.add(portID);
		} else if (ports != null) {
			List<String> portsStrList = JSON.parseArray(ports, String.class);
			for (String port : portsStrList) {
				portsList.add(port);
			}
		}

		HashMap<String, String> result = configService.loadBrokerListByPortNums(portsList);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/allBrokers.do", method = RequestMethod.POST, name = "管理-券商-全部显示")
	public void allBrokers(HttpServletResponse response) {
		HashMap<String, String> result = configService.loadAllBrokerList();
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/brokerDetail.do", method = RequestMethod.POST, name = "管理-券商-详情")
	public void brokerDetail(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "券商主代码", "BIC代码", "FIN代码", "券商名称", "所在国家", "联系人", "地址", "电话", "传真");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();

		Broker broker = configService.loadBrokerByID(id);

		data.add(broker.getBrokerID());
		data.add(broker.getBrokerCode());
		data.add(broker.getBicCode());
		data.add(broker.getFinCode());
		data.add(broker.getBrokerName());
		data.add(broker.getCountry());
		data.add(broker.getContractor());
		data.add(broker.getAddress());
		data.add(broker.getPhoneNum());
		data.add(broker.getFaxNum());
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// NEW ADD
	@RequestMapping(value = "/config/brokerDetail_new.do", method = RequestMethod.POST)
	public void brokerDetail_new(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// List<String> titles = Arrays.asList("编号", "券商主代码", "BIC代码", "FIN代码",
		// "券商名称", "所在国家", "联系人", "地址", "电话", "传真");

		Broker broker = configService.loadBrokerByID(id);

		result.put("broker_id", broker.getBrokerID());
		result.put("code", broker.getBrokerCode());
		result.put("bic", broker.getBicCode());
		result.put("fin", broker.getFinCode());
		result.put("name", broker.getBrokerName());
		result.put("country", broker.getCountry());
		result.put("contact", broker.getContractor());
		result.put("address", broker.getAddress());
		result.put("tel", broker.getPhoneNum());
		result.put("fax", broker.getFaxNum());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/brokerUpdate.do", method = RequestMethod.POST, name = "管理-券商-更新")
	public void brokerUpdate(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "params", required = true) String params, HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		Broker broker = new Broker();
		broker.setBrokerID(id);
		broker.setBrokerCode(param_list.get(0).toString());
		broker.setBicCode(param_list.get(1).toString());
		broker.setFinCode(param_list.get(2).toString());
		broker.setBrokerName(param_list.get(3).toString());
		broker.setCountry(param_list.get(4).toString());
		broker.setContractor(param_list.get(5).toString());
		broker.setAddress(param_list.get(6).toString());
		broker.setPhoneNum(param_list.get(7).toString());
		broker.setFaxNum(param_list.get(8).toString());

		configService.updateBroker(broker);

		JsonUtil.writeObjToResponse(response, broker.getBrokerID());
	}

	@RequestMapping(value = "/config/brokerUpdate_new.do", method = RequestMethod.POST)
	public void brokerUpdate_new(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "json", required = true) String json, HttpServletResponse response) {
		HashMap<String, Object> json_map = (HashMap<String, Object>) JsonUtil.stringToMap(json);

		Broker broker = new Broker();
		broker.setBrokerID(id);
		broker.setBrokerCode(json_map.get("code").toString());
		broker.setBicCode(json_map.get("bic").toString());
		broker.setFinCode(json_map.get("fin").toString());
		broker.setBrokerName(json_map.get("name").toString());
		broker.setCountry(json_map.get("country").toString());
		broker.setContractor(json_map.get("contact").toString());
		broker.setAddress(json_map.get("address").toString());
		broker.setPhoneNum(json_map.get("tel").toString());
		broker.setFaxNum(json_map.get("fax").toString());

		configService.updateBroker(broker);

		JsonUtil.writeObjToResponse(response, broker.getBrokerID());
	}

	@RequestMapping(value = "/config/brokerCreate.do", method = RequestMethod.POST, name = "管理-券商-创建")
	public void brokerCreate(@RequestParam(value = "json", required = true) String json,
			HttpServletResponse response) {
		MsgContainer container = null;
		if(JixuUserUtil.isJoyinTablesFlag()) {
			container=commService.executeNewProcessMsg(json);
		}else {
			container = commService.executeMsg(json);
		}
		controllerReturnNew(response, container);
	}

	@RequestMapping(value = "/config/brokerCreate_new.do", method = RequestMethod.POST)
	public void brokerCreate_new(@RequestParam(value = "json", required = true) String json,
			HttpServletResponse response) {
		MsgContainer container = commService.executeNewProcessMsg(json);
		controllerReturnNew(response, container);
	}

	@RequestMapping(value = "/config/brokerStatus.do", method = RequestMethod.POST)
	public void countryStatus(HttpServletResponse response) {
		HashMap<Integer, String> result = ActiveType.activeTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	// 返回证券账户名称 新框架前端费用显示需要
	@RequestMapping(value = "/config/secAcctNameByAcctID.do", method = RequestMethod.POST)
	public void secAcctNameByAcctID(@RequestParam(value = "secAcct", required = true) String id,
			HttpServletResponse response) {
		SecAcct acct = configService.loadSecAcct(id);
		if (acct != null) {
			JsonUtil.writeObjToResponse(response, acct.getAcctDesc());
		}
	}

	// TODO:增加是否为投资组合主证券账户
	@RequestMapping(value = "/config/secAcctDetail.do", method = RequestMethod.POST, name = "管理-证券账户-详情")
	public void secAccountDetail(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "账户名称", "所属券商", "账户描述", "关联组合", "总账组");
		result.put("titles", titles);

		SecAcct acct = configService.loadSecAcct(id);
		List<Object> data = new ArrayList<Object>();
		data.add(acct.getAcctID());
		data.add(acct.getAcctCode());
		data.add(acct.getBrokerCode());
		data.add(acct.getSeatNumber());
		data.add(acct.getAcctDesc());
		data.add(acct.getPortfolio());
		data.add(acct.getGlGrop().getGroupNum());
		// 查询是否为主账户

		PortfolioHdr portfolio = commService.loadPortfolio(acct.getPortfolio());
		if (portfolio.getDefaultSecAcct().equals(acct.getAcctID())) {
			data.add(true);
		} else {
			data.add(false);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// New_Add
	@RequestMapping(value = "/config/secAcctDetail_new.do", method = RequestMethod.POST)
	public void secAccountDetail_new(@RequestParam(value = "id", required = true) String id,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// List<String> titles = Arrays.asList("编号", "账户名称", "所属券商", "账户描述",
		// "关联组合", "总账组");

		SecAcct acct = configService.loadSecAcct(id);
		result.put("secacct_id", acct.getAcctID());
		result.put("acct", acct.getAcctCode());
		result.put("broker_id", acct.getBrokerCode());
		result.put("code", acct.getSeatNumber());
		result.put("desc", acct.getAcctDesc());
		result.put("port", acct.getPortfolio());
		result.put("gl", acct.getGlGrop().getGroupNum());
		// 查询是否为主账户

		PortfolioHdr portfolio = commService.loadPortfolio(acct.getPortfolio());
		if (portfolio.getDefaultSecAcct().equals(acct.getAcctID())) {
			result.put("majorAcct", true);
		} else {
			result.put("majorAcct", false);
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	// TODO: 增加是否为投资组合主证券账户
	@RequestMapping(value = "/config/secAcctUpdate.do", method = RequestMethod.POST, name = "管理-证券账户-更新")
	public void secAccountUpdate(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "params", required = true) String params, HttpServletResponse response) {
		// System.out.println("Update security account id -" + id + ". Detail is
		// as below - ");
		List<Object> param_list = JsonUtil.stringToList(params);
		SecAcct secacct = new SecAcct();
		secacct.setAcctID(id);
		secacct.setAcctCode(param_list.get(0).toString());
		secacct.setBrokerCode(param_list.get(1).toString());
		secacct.setSeatNumber(param_list.get(2).toString());
		secacct.setAcctDesc(param_list.get(3).toString());
		secacct.setPortfolio(param_list.get(4).toString());
		secacct.setGlGrop(new CoaaGroup(Integer.valueOf(param_list.get(5).toString())));
		// 设置为主账户并和投资组合默认证券账户相关联
		if (param_list.get(6).toString().equals("true")) {
			PortfolioHdr por = commService.loadPortfolio(param_list.get(4).toString());
			por.setDefaultSecAcct(id);
			portCfgService.updatePortfolio(por);
		}

		configService.updateSecAcct(secacct);
		JsonUtil.writeObjToResponse(response, secacct.getAcctID());
	}

	// New_Add
	@RequestMapping(value = "/config/secAcctUpdate_new.do", method = RequestMethod.POST)
	public void secAccountUpdate_new(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "json", required = true) String json, HttpServletResponse response) {
		// System.out.println("Update security account id -" + id + ". Detail is
		// as below - ");
		HashMap<String, Object> json_map = (HashMap<String, Object>) JsonUtil.stringToMap(json);
		SecAcct acct = configService.loadSecAcct(id);
		acct.setAcctCode(json_map.get("acct").toString());
		acct.setBrokerCode(json_map.get("broker_id").toString());
		acct.setSeatNumber(json_map.get("code").toString());
		acct.setAcctDesc(json_map.get("desc").toString());
		acct.setPortfolio(json_map.get("port").toString());
		acct.setGlGrop(new CoaaGroup(Integer.valueOf(json_map.get("gl").toString())));
		// 设置为主账户并和投资组合默认证券账户相关联
		PortfolioHdr por = commService.loadPortfolio(json_map.get("port").toString());
		if (json_map.get("majorAcct").toString().equals("1")) {
			por.setDefaultSecAcct(id);
			portCfgService.updatePortfolio(por);
		} else {
			por.setDefaultSecAcct("0");
			portCfgService.updatePortfolio(por);
		}
		configService.updateSecAcct(acct);
		JsonUtil.writeObjToResponse(response, acct.getAcctID());
	}

	// TODO: 增加是否为投资组合主证券账户
	@RequestMapping(value = "/config/secAcctCreate.do", method = RequestMethod.POST, name = "管理-证券账户-创建")
	public void secAccountCreate(@RequestParam(value = "pid", required = true) String brokerID,
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "secCash", required = true) String secCash, HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		List<Object> cash_list = JsonUtil.stringToList(secCash);

		ResultContainer container = configService.createSecAcct(param_list, cash_list);
		// 设置为主账户并和投资组合默认证券账户相关联
		controllerReturnNew(response, container);
	}

	@RequestMapping(value = "/config/secAcctsInBroker.do", method = RequestMethod.POST)
	public void secAcctsInBroker(@RequestParam(value = "pid", required = false) String brokerID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "账户名称", "所属券商", "账户描述", "关联组合", "总账组", "关联现金账户个数");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		List<SecAccountModel> acctList = configService.loadSecAcctByBrokerID(brokerID);

		for (SecAccountModel acct : acctList) {
			List<Object> item1 = new ArrayList<Object>();
			item1.add(acct.getAcctID());
			item1.add(acct.getAcctCode());
			item1.add(acct.getBrokerName());
			item1.add(acct.getAcctDesc());
			item1.add(acct.getPortfolioName());
			item1.add(acct.getCoaaName());
			item1.add(acct.getCashAcctNum());
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// TODO:增加是否为证券主账户信息
	@RequestMapping(value = "/config/secAcctsInPort.do", method = RequestMethod.POST)
	public void secAcctsInPortList(@RequestParam(value = "pid", required = false) String portID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "账户名称", "所属券商", "账户描述", "关联组合", "总账组", "关联现金账户个数", "是否为主证券账户");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();
		List<SecAccountModel> acctList = configService.loadSecAcctByPortnum(portID);
		PortfolioHdr por = commService.loadPortfolio(portID);

		for (SecAccountModel acct : acctList) {
			List<Object> item1 = new ArrayList<Object>();
			item1.add(acct.getAcctID());
			item1.add(acct.getAcctCode());
			item1.add(acct.getBrokerName());
			item1.add(acct.getAcctDesc());
			item1.add(acct.getPortfolioName());
			item1.add(acct.getCoaaName());
			item1.add(acct.getCashAcctNum());
			// TODO
			if (por.getDefaultSecAcct().equals(acct.getAcctID())) {
				item1.add("是");
			} else {
				item1.add("否");
			}
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/secAcctListInBroker.do", method = RequestMethod.POST)
	public void secAcctListInBroker(@RequestParam(value = "pid", required = false) String brokerID,
			HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/secAcctListInPort.do", method = RequestMethod.POST)
	public void secAcctListInPortList(@RequestParam(value = "pid", required = false) String portID,
			HttpServletResponse response) {

		HashMap<String, String> result=configService.loadSecAccstByPortnum(portID);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/secCashList.do", method = RequestMethod.POST, name = "管理-现金账户-查询")
	public void secCashList(@RequestParam(value = "secAcct", required = false) String secAcctID,
			HttpServletResponse response) {
		
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		if(secAcctID!=null && !secAcctID.equals("")){
			List<BkCashAcct> acctList = configService.loadBkCashAcctBySecAcct(secAcctID);
			for (BkCashAcct acct : acctList) {
				result.put(acct.getId(), acct.getBankdesc());
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/secCashDetail.do", method = RequestMethod.POST, name = "管理-现金账户-详情")
	public void secCashDetail(@RequestParam(value = "id", required = true) String secAcctID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "银行", "银行账户", "账户类型");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();

		List<BkCashAcct> acctList = configService.loadBkCashAcctBySecAcct(secAcctID);
		SecAcct secacct = configService.loadSecAcct(secAcctID);
		for (BkCashAcct acct : acctList) {
			List<Object> item1 = new ArrayList<Object>();

			item1.add(acct.getId());
			item1.add(acct.getBkinfo().getBankID());
			item1.add(acct.getId());

			if (secacct.getDefaultCashAcct() == acct.getId()) {
				item1.add(1);
			} else {
				item1.add(0);
			}
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// NEW ADD
	@RequestMapping(value = "/config/secCashDetailList.do", method = RequestMethod.POST)
	public void secCashDetailList(@RequestParam(value = "id", required = true) String secAcctID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "银行", "银行账户", "账户类型");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();

		List<BkCashAcct> acctList = configService.loadBkCashAcctBySecAcct(secAcctID);
		SecAcct secacct = configService.loadSecAcct(secAcctID);
		for (BkCashAcct acct : acctList) {
			List<Object> item1 = new ArrayList<Object>();

			item1.add(acct.getId());
			item1.add(acct.getBkinfo().getBankDesc());
			item1.add(acct.getExternalacctnumber());

			if (secacct.getDefaultCashAcct() == acct.getId()) {
				item1.add("主账户");
			} else {
				item1.add("普通账户");
			}
			data.add(item1);
		}

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// NEW ADD
	@RequestMapping(value = "/config/secCashDetail_new.do", method = RequestMethod.POST)
	public void secCashDetail_new(@RequestParam(value = "secAcctID", required = true) String secAcctID,
			@RequestParam(value = "secCashID", required = true) int secCashID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// List<String> titles = Arrays.asList("编号", "银行", "银行账户","账户类型");
		// result.put("titles", titles);

		List<BkCashAcct> acctList = configService.loadBkCashAcctBySecAcct(secAcctID);
		SecAcct secacct = configService.loadSecAcct(secAcctID);
		for (BkCashAcct acct : acctList) {
			if (acct.getId() == secCashID) {
				result.put("seccash_id", acct.getId());
				result.put("secacct_bank", acct.getBkinfo().getBankDesc());
				result.put("seccash_bankAccount", acct.getId());
				if (secacct.getDefaultCashAcct() == acct.getId()) {
					result.put("seccash_type", true);
				} else {
					result.put("seccash_type", false);
				}
				break;
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/secCashCreate.do", method = RequestMethod.POST, name = "管理-现金账户-创建")
	public void secCashCreate(@RequestParam(value = "pid", required = true) String secAcctID,
			@RequestParam(value = "params", required = true) String params, HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		String[] fields = param_list.toArray(new String[param_list.size()]);
		int cashacctID = Integer.valueOf(fields[1]);
		int defaultCashacctID = Integer.valueOf(fields[2]);
		boolean defaultFlag = true;
		if (defaultCashacctID == 0) {
			defaultFlag = false;
		}

		ResultContainer container = new ResultContainer();
		configService.createSecCashAcctMap(secAcctID, cashacctID, defaultFlag, container);

		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, container.getObjID());
		} else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}

	@RequestMapping(value = "/config/newSecCashCreate.do", method = RequestMethod.POST, name = "管理-新现金账户-创建")
	public void newSecCashCreate(@RequestParam(value = "pid", required = true) String secAcctID,
			@RequestParam(value = "params", required = true) String params, HttpServletResponse response) {
		
		MsgContainer container = new MsgContainer();
		List<Object> param_list = JsonUtil.stringToList(params);
		String[] fields = param_list.toArray(new String[param_list.size()]);
		int cashacctID = 0;
		if(fields[1]!=""){
			cashacctID=Integer.valueOf(fields[1]);
			int defaultCashacctID = Integer.valueOf(fields[2]);
			boolean defaultFlag = true;
			if (defaultCashacctID == 0) {
				defaultFlag = false;
			}
			configService.createSecCashAcctMap(secAcctID, cashacctID, defaultFlag, container);
			controllerReturnNew(response, container);
		}else{
			container.setCorrectFlag(false);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_port_sec_notexist));
			controllerReturnNew(response, container);
		}
	}

	@RequestMapping(value = "/config/secCashUpdate.do", method = RequestMethod.POST, name = "管理-现金账户-更新")
	public void secCashUpdate(@RequestParam(value = "pid", required = true) String secAcctID,
			@RequestParam(value = "id", required = true) String cashAcctID,
			@RequestParam(value = "params", required = true) String params, HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		System.out.println(param_list);
		Random rand = new Random();
		int randomNo = rand.nextInt(100 - 5) + 5;
		JsonUtil.writeObjToResponse(response, randomNo + "");
	}

	@RequestMapping(value = "/config/secCashRemove.do", method = RequestMethod.POST, name = "管理-现金账户-删除")
	public void secCashRemove(@RequestParam(value = "id", required = true) int cashAcctID,
			HttpServletResponse response) {

		ResultContainer container = configService.unMapCashSecAcct(cashAcctID);
		if (container.isCorrectFlag()) {
			JsonUtil.writeObjToResponse(response, true);
		} else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}

	@RequestMapping(value = "/config/secCashTypes.do", method = RequestMethod.POST)
	public void secCashTypes(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(1, "主账户");
		result.put(0, "普通账户");
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/seatNumbers.do", method = RequestMethod.POST)
	public void issuers(@RequestParam(value = "broker", required = true) String broker, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "席位编号", "席位名称", "席位描述");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		List<SeatNumber> snList = seatNumberDao.loadSeatNumbersByBrokerID(broker);

		for (SeatNumber sn : snList) {
			List<Object> item1 = new ArrayList<Object>();
			item1.add(sn.getId());
			item1.add(sn.getSeatNumberAcct());
			item1.add(sn.getSeatNumberName());
			item1.add(sn.getSeatNumberDesc());

			data.add(item1);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/seatNumberRemove.do", method = RequestMethod.POST)
	@Transactional(value = "jixuTXManager")
	public void issuerRemove(@RequestParam(value = "id", required = true) int seatNumberID,
			@RequestParam(value = "json", required = true) String json, HttpServletRequest request,
			HttpServletResponse response) {

		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}

	// TODO-添加券商下的席位号
	@RequestMapping(value = "/config/seatNumberUpdate.do", method = RequestMethod.POST)
	@Transactional(value = "jixuTXManager")
	public void issuerUpdate(@RequestParam(value = "id", required = true) int seatNumberID,
			@RequestParam(value = "json", required = true) String json,
			@RequestParam(value = "params", required = true) String params,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}

	@RequestMapping(value = "/config/seatNumberCreate.do", method = RequestMethod.POST)
	@Transactional(value = "jixuTXManager")
	public void issuerCreate(@RequestParam(value = "broker", required = true) String broker,
			@RequestParam(value = "params", required = true) String params,
			@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);;
	}
}
