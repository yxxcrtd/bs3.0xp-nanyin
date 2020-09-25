package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.ActiveType;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.BankAcctSetCurrency;
import com.jixu.common.types.BankAcctType;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.CpnDateDelayType;
import com.jixu.common.types.IntPeriodConv;
import com.jixu.common.types.InterestCpnMo;
import com.jixu.common.types.InterestInitCode;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.model.BankInterestModel;
import com.jixu.mvc.control.service.BankCfgService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.BkInteRatio;

@Controller
public class BankController extends ExceptionControl{

	@Resource(name="bankCfgService")
	private BankCfgService bankCfgService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Resource(name = "commfuncService")
	private CommFuncService commService;
	
	@RequestMapping(value="/bank.do", method = RequestMethod.GET, name="管理-银行-页面显示")
	public String bank() {
		return "bank";
	}
	
	/**
	 * 获取所有银行列表
	 * @param response
	 */
	@RequestMapping(value="/config/bankList.do", method = RequestMethod.POST, name="管理-银行-查询")
	 public void bankList(HttpServletResponse response){
		HashMap<String, String> result = bankCfgService.loadBankList();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankDetail.do", method = RequestMethod.POST, name="管理-银行-详情")  
    public void bankDetail(@RequestParam(value = "id", required = true) String bankID,
    					      HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//远期交易数据
		
		List<String> titles = Arrays.asList("编号", "银行代码", "银行名称",  "所在国家", "联系人", "地址", "电话", "传真");
		result.put("titles", titles);
		
		BkInfo bank=bankCfgService.loadBkInfoByID(bankID);
		
		List<Object> item = new ArrayList<Object>();
		item.add(bank.getBankID());
		item.add(bank.getBankCode());
		item.add(bank.getBankDesc());
		item.add(bank.getCountry());
		item.add(bank.getContractor());
		item.add(bank.getAddress());
		item.add(bank.getPhoneNum());
		item.add(bank.getPhoneFax());
		
		result.put("data", item);	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankDetail_new.do", method = RequestMethod.POST)  
    public void bankDetail_new(@RequestParam(value = "id", required = true) String bankID,
    					      HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		BkInfo bank=bankCfgService.loadBkInfoByID(bankID);
		result.put("bank_id", bank.getBankID());
		result.put("code", bank.getBankCode());
		result.put("name", bank.getBankDesc());
		result.put("country", bank.getCountry());
		result.put("contact", bank.getContractor());
		result.put("address", bank.getAddress());
		result.put("tel", bank.getPhoneNum());
		result.put("fax", bank.getPhoneFax());	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 更新银行的基本数据
	 * @param bankID
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/config/bankUpdate.do", method = RequestMethod.POST, name="管理-银行-更新")
	public void bankUpdate(@RequestParam(value = "id", required = true) String bankID,
			 				 @RequestParam(value = "params", required = false) String params,
			 				 @RequestParam(value = "json", required = true) String json,
			 				 HttpServletRequest request,
			 			     HttpServletResponse response){
		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	/**
	 * 新建银行
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/config/bankCreate.do", method = RequestMethod.POST, name="管理-银行-创建")
	public void bankCreate(@RequestParam(value = "json", required = true) String json,
			 			     HttpServletResponse response){
		MsgContainer container=null;
		if(JixuUserUtil.joyinUserSystemFlag())
			container = commService.executeNewProcessMsg(json);
		else
			container = commService.executeMsg(json);
		controllerReturnNew(response, container);
	}
	
	/**
	 * 获取某个银行的所有账户
	 * @param bank
	 * @param response
	 */
	@RequestMapping(value="/config/bankAccountList.do", method = RequestMethod.POST, name="管理-银行账户-查询")
	 public void bankAccountList(@RequestParam(value = "bank", required = true) String bank,
			 					 HttpServletResponse response){
		HashMap<Integer, String> result = bankCfgService.loadBankAcct(bank);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 获取某个银行没有和任何投资组合关联的所有账户
	 * @param bank
	 * @param response
	 */
	@RequestMapping(value="/config/unMapBankAccountList.do", method = RequestMethod.POST)
	 public void unMapBankAccountList(@RequestParam(value = "bank", required = true) String bank,
			 					 HttpServletResponse response){
		HashMap<Integer, String> result = bankCfgService.unMapBankAccountList(bank);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 获取某个银行下的所有账户
	 * @param bank
	 * @param response
	 */
	@RequestMapping(value="/config/mapBankAccountList.do", method = RequestMethod.POST)
	 public void mapBankAccountList(@RequestParam(value = "bank", required = true) String bank,
			 					 HttpServletResponse response){
		HashMap<Integer, String> result = bankCfgService.loadBankAcct(bank);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	// 返回银行账户名称 新框架前端费用显示需要
	@RequestMapping(value = "/config/bankAcctNameByActID.do", method = RequestMethod.POST)
	public void bankAcctNameByActID(
			@RequestParam(value = "bankAccount", required = true) String acctIDStr,
			HttpServletResponse response) {
		if(acctIDStr!=""){
			int acctID=Integer.valueOf(acctIDStr);
			BkCashAcct acct = bankCfgService.loadAcctDetail(acctID);
			if (acct != null) {
				JsonUtil.writeObjToResponse(response, acct.getBankdesc());
			}
		}else{
			JsonUtil.writeObjToResponse(response, "");
		}
	}
	
	@RequestMapping(value="/config/bankAccountDetail.do", method = RequestMethod.POST, name="管理-银行账户-详情")  
    public void bankAccountDetail(@RequestParam(value = "id", required = true) String acctID,
    					      HttpServletResponse response) {
		if(!commService.checkIDExist(response, acctID)){
			return;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//远期交易数据
		
		List<String> titles = Arrays.asList("编号", "账户","银行","账户描述", "账户货币", "状态", "结算货币", "关联组合", "利息配置", "当前账户余额", "当前账户余额日期");
		result.put("titles", titles);
		
		List<Object> item = new ArrayList<Object>();
		
		BkCashAcct acct=bankCfgService.loadAcctDetail(Integer.parseInt(acctID));
		
		item.add(acct.getId());
		item.add(acct.getExternalacctnumber());
		item.add(acct.getBkinfo().getBankID());
		item.add(acct.getSeatNumber());
		item.add(acct.getBankdesc());
		item.add(acct.getFxcurrency());
		item.add(acct.getStatus());
		item.add(acct.getSttlmntcurrdefault());
		item.add(acct.getPortfolio().getId());
		item.add(acct.getIntCshflwMasterRec());
		item.add(acct.getLastBalance());
		item.add(DateUtil.convertD2YYYY_MM_DD(acct.getLastBalDate()));
		
		result.put("data", item);	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankAccountDetail_new.do", method = RequestMethod.POST)  
    public void bankAccountDetail_new(@RequestParam(value = "id", required = true) String acctID, HttpServletResponse response) {
		if(!commService.checkIDExist(response, acctID)){
			return;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		BkCashAcct acct=bankCfgService.loadAcctDetail(Integer.parseInt(acctID));
		
		result.put("bankAccount_id", acct.getId());
		result.put("acct", acct.getExternalacctnumber());
		result.put("bank_id", acct.getBkinfo().getBankID());
		result.put("code", acct.getSeatNumber());
		result.put("desc", acct.getBankdesc());
		result.put("currency", acct.getFxcurrency());
		result.put("status", acct.getStatus());
		result.put("settlement", acct.getSttlmntcurrdefault());
		result.put("port", acct.getPortfolio().getId());
		result.put("interest", acct.getIntCshflwMasterRec());
		result.put("balance", acct.getLastBalance());
		result.put("date", DateUtil.convertD2YYYY_MM_DD(acct.getLastBalDate()));
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankAccountUpdate.do", method = RequestMethod.POST, name="管理-银行账户-更新")
	 public void bankAccountUpdate(@RequestParam(value = "id", required = true) int acctID,
			 				 @RequestParam(value = "params", required = false) String params,
			 				 @RequestParam(value = "json", required = true) String json,
			 				 HttpServletRequest request,
			 			     HttpServletResponse response){
		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/config/bankAccountCreate.do", method = RequestMethod.POST, name="管理-银行账户-创建")
	 public void bankAccountCreate(
			 			     @RequestParam(value = "coa", required = true) String coa,
			 				 @RequestParam(value = "params", required = false) String params,
			 				 @RequestParam(value = "json", required = true) String json,
			 				 HttpServletRequest request,
			 			     HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put("operation", MsgFields.add);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setUserID(userID);
		container=MsgFactory.executeMsgBean(container);
		controllerReturnNew(response, container);
	}
	
	/**
	 * 银行利息 
	 * @param bank
	 * @param response
	 */
	@RequestMapping(value="/config/bankInterestList.do", method = RequestMethod.POST, name="管理-银行利息-查询")
	 public void bankInterestList(@RequestParam(value = "bank", required = true) String bank,
			 					  HttpServletResponse response){
		HashMap<Integer, String> result = bankCfgService.loadBankInteAcct(bank);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankInterestListWithNo.do", method = RequestMethod.POST)
	 public void bankInterestListWithNo(@RequestParam(value = "bank", required = true) String bank,
			 					  		HttpServletResponse response){
		HashMap<Integer, String> result = bankCfgService.loadBankInteAcct(bank);
		result.put(0, "无利息");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankInterestDetail.do", method = RequestMethod.POST, name="管理-银行利息-详情")  
    public void bankInterestDetail(@RequestParam(value = "id", required = true) String intID,
    					          HttpServletResponse response) {
		if(!commService.checkIDExist(response, intID)){
			return;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//TODO: 需要支持利率和开始日期两个新字段
		List<String> titles = Arrays.asList("编号", "利息代码", "利息名称", "银行", "利率(支持阶梯输入)", "结算推迟天数", "利息结算方法", "天数计算方法", "期间方法", "付息频度", "付息月份", "付息日", "开始日期");
		result.put("titles", titles);
		
		int interestID=Integer.parseInt(intID);
		BankInterestModel model=bankCfgService.loadBankInteAcctDetail(interestID);
		
		List<Object> item = new ArrayList<Object>();
		item.add(model.getId());
		item.add(model.getCode());
		item.add(model.getName());
		item.add(model.getBankid());
		item.add(model.getPmtDelay());
		item.add(model.getCpnDateConv());
		item.add(model.getIntCode());
		item.add(model.getIntperiodconv());
		item.add(model.getCpnFreq());
		item.add(model.getCpnMo());
		item.add(model.getCpnDate());
		
		result.put("data", item);	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO
	@RequestMapping(value="/config/bankInterestDetail_new.do", method = RequestMethod.POST)  
    public void bankInterestDetail_new(@RequestParam(value = "id", required = true) String intID,
    					          HttpServletResponse response) {
		if(!commService.checkIDExist(response, intID)){
			return;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//TODO: 需要支持利率和开始日期两个新字段
		//List<String> titles = Arrays.asList("编号", "利息代码", "利息名称", "银行", "利率(支持阶梯输入)", "结算推迟天数", "利息结算方法", "天数计算方法", "期间方法", "付息频度", "付息月份", "付息日", "开始日期");
		//result.put("titles", titles);
		
		int interestID=Integer.parseInt(intID);
		BankInterestModel model=bankCfgService.loadBankInteAcctDetail(interestID);
		
		result.put("interest_id", model.getId());
		result.put("code", model.getCode());
		result.put("name", model.getName());
		result.put("bank_id", model.getBankid());
		result.put("delayDays", model.getPmtDelay());
		result.put("settleMethod", model.getCpnDateConv());
		result.put("dayCounts", model.getIntCode());
		result.put("period", model.getIntperiodconv());
		result.put("frequency", model.getCpnFreq());
		result.put("interestMonth", model.getCpnMo());
		result.put("day", model.getCpnDate());
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankInterestUpdate.do", method = RequestMethod.POST, name="管理-银行利息-更新")
	 public void bankInterestUpdate(@RequestParam(value = "id", required = true) int intID,
			 				        @RequestParam(value = "params", required = false) String params,
			 				        @RequestParam(value = "json", required = true) String json,
			 				        HttpServletRequest request,
			 			            HttpServletResponse response){
		
		MsgContainer container = commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/config/bankInterestCreate.do", method = RequestMethod.POST, name="管理-银行利息-新建")
	 public void bankInterestCreate(@RequestParam(value = "params", required = false) String params,
			 						@RequestParam(value = "rates", required = true) String rates,
			 						@RequestParam(value = "json", required = true) String json,
				 				    HttpServletRequest request,
			 						HttpServletResponse response){
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put("operation", MsgFields.add);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setUserID(userID);
		container=MsgFactory.executeMsgBean(container);
		controllerReturnNew(response, container);
	}
	
	//option-select-values bankAccount_add_form--"label": "状态",
	//option-select-values bankAccount_edit_form
	@RequestMapping(value="/config/bankAcctStatusOption.do", method = RequestMethod.POST)
	 public void getAcctStatusOption(HttpServletResponse response){
		HashMap<Integer, String> result = ActiveType.activeTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankAccountTypes.do", method = RequestMethod.POST)
	 public void bankAccountTypes(HttpServletResponse response){
		HashMap<Integer, String> result = BankAcctType.acctTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values bankAccount_add_form--"label": "结算货币",
	//option-select-values bankAccount_edit_form
	@RequestMapping(value="/config/AcctSetCurrencyOption.do", method = RequestMethod.POST)
	public void getAcctSetCurrencyOption(HttpServletResponse response){
		HashMap<Integer, String> result = BankAcctSetCurrency.setCurrencyHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values bankInterest_add_form--"label": "利息结算方法",
	//option-select-values bankInterest_edit_form
	@RequestMapping(value="/config/IntCpnDateConvOption.do", method = RequestMethod.POST)
	public void getIntCpnDateConvOption(HttpServletResponse response){
		HashMap<Integer, String> result = CpnDateDelayType.cpnDateDelayHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values bankInterest_add_form--"label": "天数计算方法",
	//option-select-values bankInterest_edit_form
	@RequestMapping(value="/config/IntIntInitCodeList.do", method = RequestMethod.POST)
	public void getIntInitCodeList(HttpServletResponse response){
		HashMap<Integer, String> result = InterestInitCode.intInitCodeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values bankInterest_add_form--"label": "期间方法",
	//option-select-values bankInterest_edit_form
	@RequestMapping(value="/config/IntPeriodConvOption.do", method = RequestMethod.POST)
	public void getIntPeriodConvOption(HttpServletResponse response){
		HashMap<Integer, String> result = IntPeriodConv.intPeriodConvHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values bankInterest_add_form--"label": "付息频度",
	//option-select-values bankInterest_edit_form
	@RequestMapping(value="/config/IntCpnfReqTypeOption.do", method = RequestMethod.POST)
	public void getCpnfReqTypeOption(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.cpnBankfreqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankInterestMonth.do", method = RequestMethod.POST)
	public void bankInterestMonth(@RequestParam(value = "freq", required = false) String freqStr, HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		if(freqStr!=null && !freqStr.equals("")){
			int freq=Integer.valueOf(freqStr);
			if (freq == CouponData.Quarterly){
				result=InterestCpnMo.quarterHM;
			}else if (freq == CouponData.SemiAnnual){
				result=InterestCpnMo.semiAnnualHM;
			}else if (freq == CouponData.Annual){
				result=InterestCpnMo.annualHM;
			}else if(freq == CouponData.Monthly){
				result=InterestCpnMo.monthlyHM;
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//通过银行账户，得到该账户的货币代码
	@RequestMapping(value="/config/bankAccountCurrency.do", method = RequestMethod.POST)
	public void bankAccountCurrency(@RequestParam(value = "id", required = false) String idStr, HttpServletResponse response){
		String curid="";
		if(idStr!=null&&!idStr.equals("")){
			curid=bankCfgService.loadFxCurrencyByCashAcct(Integer.valueOf(idStr));
		}
		JsonUtil.writeObjToResponse(response, curid);
	}

	@RequestMapping(value="/config/bankAccountCats.do", method = RequestMethod.POST)
	public void bankAccountCats(HttpServletResponse response){
		HashMap<Integer, String> result = BankAccountType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/bankAccountCatById.do", method = RequestMethod.POST)
	public void bankAccountCatById(@RequestParam(value = "category", required = true) int bankAcctId,
			HttpServletResponse response){
		HashMap<Integer, String> result = BankAccountType.optionHM;
		String str="";
		if(result.containsKey(bankAcctId)){
			str=result.get(bankAcctId);
		}
		JsonUtil.writeObjToResponse(response, str);
	}
	
	@RequestMapping(value = "/config/bankCOADetail.do", method = RequestMethod.POST)
	public void bankCOADetail(@RequestParam(value = "id", required = true) int acctID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "业务类型", "会计科目");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        HashMap<Integer,String> bankCOAHM=bankCfgService.loadBankAcctCOA(acctID);
        Iterator<Entry<Integer, String>> bankCOAIter=bankCOAHM.entrySet().iterator();
        
        while(bankCOAIter.hasNext()){
        	Entry<Integer, String> coaEntry=bankCOAIter.next();

        	List<Object> item1 = new ArrayList<Object>();
            item1.add(coaEntry.getKey());
            item1.add(coaEntry.getKey());
            item1.add(coaEntry.getValue());
            data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO: Need to update, 为啥id还是key呢
	@RequestMapping(value = "/config/bankCOADetail_new.do", method = RequestMethod.POST)
	public void bankCOADetail_new(@RequestParam(value = "id", required = true) int acctID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "业务类型", "会计科目");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        HashMap<Integer,String> bankCOAHM=bankCfgService.loadBankAcctCOA(acctID);
        Iterator<Entry<Integer, String>> bankCOAIter=bankCOAHM.entrySet().iterator();
        
        while(bankCOAIter.hasNext()){
        	Entry<Integer, String> coaEntry=bankCOAIter.next();

        	HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("category", coaEntry.getKey());
            item1.put("bankacct_acct", coaEntry.getValue());
            data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/bankCOACreate.do", method = RequestMethod.POST)
	public void bankCOACreate(@RequestParam(value = "pid", required = true) int acctID,
								   @RequestParam(value = "params", required = true) String params,
								   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int typeid=Integer.valueOf(param_list.get(0).toString());
		String acct=param_list.get(1).toString().split("\\|")[0];
    	bankCfgService.updateBankacctGL(acctID, typeid, acct);
        JsonUtil.writeObjToResponse(response, acctID + "");
	}
	//TODO
	@RequestMapping(value = "/config/bankCOACreate_new.do", method = RequestMethod.POST)
	public void bankCOACreate_new(@RequestParam(value = "pid", required = true) int acctID,
								   @RequestParam(value = "category", required = true) String category,
								   @RequestParam(value = "bankacct_acct", required = true) String acct,
								   HttpServletResponse response) {
		
		int typeid=Integer.valueOf(category);
		String acctnum=acct.toString().split("\\|")[0];
    	bankCfgService.updateBankacctGL(acctID, typeid, acctnum);
        JsonUtil.writeObjToResponse(response, acctID + "");
	}
	
	@RequestMapping(value = "/config/bankCOAUpdate.do", method = RequestMethod.POST)
	public void bankCOAUpdate(@RequestParam(value = "pid", required = true) int cashAcctID,
								 @RequestParam(value = "id", required = true) String secAcctID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int typeid=Integer.valueOf(param_list.get(0).toString());
		String acct=param_list.get(1).toString().split("\\|")[0];
    	bankCfgService.updateBankacctGL(cashAcctID, typeid, acct);
        JsonUtil.writeObjToResponse(response, cashAcctID + "");
	}
	
	//TODO
	@RequestMapping(value = "/config/bankCOAUpdate_new.do", method = RequestMethod.POST)
	public void bankCOAUpdate_new(@RequestParam(value = "pid", required = true) int acctID,
								  @RequestParam(value = "id", required = true) int coaID,
								  @RequestParam(value = "category", required = true) String category,
								  @RequestParam(value = "bankacct_acct", required = true) String acct,
								  HttpServletResponse response) {
		
		int typeid=Integer.valueOf(category);
		String acctnum=acct.toString().split("\\|")[0];
		bankCfgService.updateBankacctGL(acctID, typeid, acctnum);
        JsonUtil.writeObjToResponse(response, acctID + "");
	}
	
	@RequestMapping(value = "/config/bankCOARemove.do", method = RequestMethod.POST)
	public void bankCOARemove(@RequestParam(value = "pid", required = true) int acctID,
							  @RequestParam(value = "id", required = true) int coaID,
							  HttpServletResponse response) {
		
		bankCfgService.removeBankacctGL(acctID,coaID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/config/rateDetail.do", method = RequestMethod.POST)
	public void rateDetail(@RequestParam(value = "id", required = true) int intID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "起息时间", "利率(支持阶梯)");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<BkInteRatio> ratioList=bankCfgService.loadRatioList(intID);
        for(BkInteRatio ratio:ratioList){
        	List<Object> item1 = new ArrayList<Object>();
        	item1.add(ratio.getId());
        	item1.add(DateUtil.convertD2YYYY_MM_DD(ratio.getStartdate()));
        	item1.add(ratio.getPrice());
            data.add(item1);
        }
        
        /*item1.add(1);
        item1.add("2011-01-01");
        item1.add("0,5000,1.4|5001,,1.5");*/
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO
	@RequestMapping(value = "/config/rateList.do", method = RequestMethod.POST)
	public void rateList(@RequestParam(value = "id", required = true) int intID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "起息时间", "利率(支持阶梯)");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<BkInteRatio> ratioList=bankCfgService.loadRatioList(intID);
        for(BkInteRatio ratio:ratioList){
        	List<Object> item1 = new ArrayList<Object>();
        	item1.add(ratio.getId());
        	item1.add(DateUtil.convertD2YYYY_MM_DD(ratio.getStartdate()));
        	item1.add(ratio.getPrice());
            data.add(item1);
        }
        
        /*item1.add(1);
        item1.add("2011-01-01");
        item1.add("0,5000,1.4|5001,,1.5");*/
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	//TODO
	@RequestMapping(value = "/config/rateDetail_new.do", method = RequestMethod.POST)
	public void rateDetail_new(@RequestParam(value = "interestID", required = true) int intID, 
							   @RequestParam(value = "rateID", required = true) int rateID, HttpServletResponse response) {
		
        List<Object> result = new ArrayList<Object>();
        HashMap<String, Object> item1;
        
        List<BkInteRatio> ratioList=bankCfgService.loadRatioList(intID);
        for(BkInteRatio ratio:ratioList){
        	if (ratio.getId() == rateID){
        		item1 = new HashMap<String, Object>();
            	item1.put("rate_id", ratio.getId());
            	item1.put("startDate", DateUtil.convertD2YYYY_MM_DD(ratio.getStartdate()));
            	item1.put("rate", ratio.getPrice());
        	}
        }
        
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/rateCreate.do", method = RequestMethod.POST)
	public void rateCreate(@RequestParam(value = "pid", required = true) int intID,
							  @RequestParam(value = "params", required = true) String params,
							  HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int rationum=bankCfgService.createIntereRatio(intID, param_list);
        JsonUtil.writeObjToResponse(response, rationum);
	}
	
	@RequestMapping(value = "/config/rateCreate_new.do", method = RequestMethod.POST)
	public void rateCreate_new(@RequestParam(value = "interestID", required = true) int intID,
							  @RequestParam(value = "startDate", required = true) String startDate,
							  @RequestParam(value = "rate", required = true) double rate,
							  HttpServletResponse response) {
		
		List<Object> param_list = Arrays.asList(startDate, rate);
		int rationum=bankCfgService.createIntereRatio(intID, param_list);
        JsonUtil.writeObjToResponse(response, rationum);
	}
	
	@RequestMapping(value = "/config/rateUpdate.do", method = RequestMethod.POST)
	public void rateUpdate(@RequestParam(value = "pid", required = true) int intID,
						   @RequestParam(value = "id", required = true) int rateID,
						   @RequestParam(value = "params", required = true) String params,
						   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int ratioID=bankCfgService.updateIntereRatio(rateID, param_list);
        JsonUtil.writeObjToResponse(response, ratioID + "");
	}
	//TODO
	@RequestMapping(value = "/config/rateUpdate_new.do", method = RequestMethod.POST)
	public void rateUpdate_new(@RequestParam(value = "id", required = true) int rateID,
			                   @RequestParam(value = "startDate", required=true) String startDate,
						       @RequestParam(value = "rate", required = true) double rate,
						       HttpServletResponse response) {
		List<Object> param_list = Arrays.asList(startDate, rate);
		int ratioID=bankCfgService.updateIntereRatio(rateID, param_list);
        JsonUtil.writeObjToResponse(response, ratioID + "");
	}
	
	@RequestMapping(value = "/config/rateRemove.do", method = RequestMethod.POST)
	public void rateRemove(@RequestParam(value = "id", required = true) int rateID,
						   HttpServletResponse response) {
		 bankCfgService.deleteInteRatio(rateID);
        JsonUtil.writeObjToResponse(response, "");
	}
}
