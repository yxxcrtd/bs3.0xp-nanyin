package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.UserTmpID;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.types.InterestCpnMo;
import com.jixu.common.types.InterestInitCode;
import com.jixu.common.types.InterfaceMethodType;
import com.jixu.common.types.NetAssetType;
import com.jixu.common.types.PerformanceFeeType;
import com.jixu.common.types.PortfolioTypes;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.UserPortfolioMapDao;
import com.jixu.job.service.Impl.TaxFeeJobFactory;
import com.jixu.msg.dto.StockTranDto;
import com.jixu.msg.service.trade.StockTranDtoMsg;
import com.jixu.mvc.control.model.PortfolioSummary;
import com.jixu.mvc.control.service.BankCfgService;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.FetchBasicInfoService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.PortfolioInterfaceMap;
import com.orm.obj.UserPortfolioMap;

@Controller
public class PortfolioController extends ExceptionControl{
	@Resource(name="basicinfoService")
	private FetchBasicInfoService basicinfoService;	
	
	@Resource(name="portCfgService")
	private PortfolioCfgService portCfgService;
	
	@Resource(name="bankCfgService")
	private BankCfgService bankCfgService;
	
	@Resource(name="configService")
	private ConfigService cfgService;
	
//	@Resource(name="exportDataSource")
//	private ExportDataSource expSource;
	
	@Resource(name="commfuncService")
	protected CommFuncService commService;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private TaxFeeJobFactory taxFeeJobFactory;
	
	@Autowired
	private UserPortfolioMapDao userPortfolioMapDao;
	
	@Autowired
	private StockTranDtoMsg stockTranDtoMsg;
	
	@RequestMapping(value="/port.do", method = RequestMethod.GET, name="投资组合-列表页面显示")
	public String port(){
		return "port";
	}
	
//	@RequestMapping(value="/valuation/valjixu/ValJixu001/{jsonfile}/init")
//    public String getInit(HttpServletRequest request, @PathVariable(name="jsonfile") String jsonfile,Model model) {
//		// 工作流ID
//         String workflowId = request.getParameter(GlobalConstant.WORKFLOW_ID);
//         
//         if (StringUtil.isNotEmpty(workflowId)) {
//        	 StockTranDto dto = stockTranDtoMsg.getBussinessEntityByWorkflowId(workflowId);                     
//        	 model.addAttribute("pageDTO", JsonUtil.objToString(dto));
//        	 model.addAttribute("jsonfile", "stock_buy");
//        	 return "valuation/valjixu/ValJixu002";
//         }
//         return "valuation/valjixu/ValJixu001";
//     }
	
	@RequestMapping(value="/valuation/valjixu/ValJixu001/**/init", method = RequestMethod.GET, name="测试界面-列表页面显示")
	public String test(){
		return "valuation/valjixu/ValJixu001";
	}
	
	@RequestMapping(value="/port/portfolioShortList.do", method = RequestMethod.POST)
	public void portfolioShortList(@RequestParam(value = "client", required = false) String client,
								   @RequestParam(value = "ports", required = false) String ports,	
							       HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> result = new HashMap<String, String>();		
		if(client != null && !client.equals("")){
			int clientID=0;
			if(!client.equals("all")){
				clientID=Integer.valueOf(client);
			}
			List<PortfolioHdr> portfolios=null;
			if(JixuUserUtil.getUserNamebyRequest(request).toLowerCase().equals(UserTmpID.superUser) || !JixuUserUtil.jixuUserSystemFlag()){
				portfolios=basicinfoService.getPortfoliosByClientNum(clientID);
			}else{
				portfolios=basicinfoService.getPortfoliosByClientNum(clientID, JixuUserUtil.getUserIDbyRequest(request));
			}

			for(PortfolioHdr portfolio:portfolios){
				result.put(portfolio.getId(),portfolio.getShortName());
			}
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 用户权限--组合配置，左边显示用户下所有的组合
	 * @param client
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/port/portfoliosAllByClient.do", method = RequestMethod.POST)
	public void portfoliosAllByClient(@RequestParam(value = "client", required = false) int client,
							       HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> result = new HashMap<String, String>();		
		List<PortfolioHdr> portfolios=basicinfoService.getPortfoliosByClientNum(client);
		for(PortfolioHdr portfolio:portfolios){
			result.put(portfolio.getId(),portfolio.getShortName());
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/portfolioSingleList.do", method = RequestMethod.POST)
	public void portfolioSingleList(@RequestParam(value = "client", required = true) int client,
							  HttpServletResponse response){
		List<PortfolioHdr> portfolios=basicinfoService.getPortfoliosByClientNum(client);
		HashMap<String, String> result = new HashMap<String, String>();
		result.put(String.valueOf(0), "无");
		for(PortfolioHdr portfolio:portfolios){
			result.put(portfolio.getId(),portfolio.getShortName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/portfolioList.do", method = RequestMethod.POST, name="投资组合-查询")
	public void portfolioList(@RequestParam(value = "client", required = true) int client,
			 				  @RequestParam(value = "type", required = true) int type,
			 				  @RequestParam(value = "name", required = true) String name,
			 				  @RequestParam(value = "start", required = false) String start,
			 				  @RequestParam(value = "end", required = false) String end,
			 				  HttpServletRequest request,
			 				  HttpServletResponse response){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","组合代码", "简称", "类型", "客户", "记账本位币", "首个做账日", "当前期末","当前财务年末", "当前单位净资产", "当前累计净值", "当前净资产", "当前份额");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		int clientCode=client;
		int portType=type;
		String portName=name.trim();
		List<PortfolioSummary> summaryList=null;
		if(JixuUserUtil.getUserNamebyRequest(request).toLowerCase().equals(UserTmpID.superUser) || !JixuUserUtil.jixuUserSystemFlag()){
			summaryList=portCfgService.getPortfolioSummary(clientCode, portType, portName);
		}else{
			summaryList=portCfgService.getPortfolioSummary(JixuUserUtil.getUserIDbyRequest(request), clientCode, portType, portName);
		}
		for(PortfolioSummary summary:summaryList){
			List<Object> item = new ArrayList<Object>();
			item.add(summary.getPortnum());
			item.add(summary.getPortCode());
			item.add(summary.getPortShortName());
			item.add(summary.getPortType());
			item.add(summary.getClientName());
			item.add(summary.getCurCode());
			item.add(summary.getPortInitDate());
			item.add(summary.getNextCloseDate());
			item.add(summary.getNextCloseYearDate());
			item.add(summary.getNav());
			item.add(summary.getAccNav());
			item.add(summary.getNetAsset());
			item.add(summary.getShares());
			
			data.add(item);
		}
		
		result.put("data", data);
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 查看投资组合记账货币(本位币)
	 * @param portID
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioCurrency.do", method = RequestMethod.POST)
	public void portfolioCurrency(@RequestParam(value = "id", required = true) String portID,
								HttpServletResponse response) {
		PortfolioHdr portfolio=commService.loadPortfolio(portID);
		JsonUtil.writeObjToResponse(response, portfolio.getCurID());
	}
	
	/**
	 * 查看投资组合明细
	 * @param portID
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioDetail.do", method = RequestMethod.POST, name="投资组合-详情")
	public void portfolioDetail(@RequestParam(value = "id", required = true) String portID,
								HttpServletResponse response) {
		//Construct Portfolio Basic Information
		HashMap<String, Object> basicConfig = new HashMap<String, Object>();
		List<String> basicTitles = Arrays.asList("客户", "名称", "代码", "国家", "省份", "货币", "Lot选择", "假期安排", "成立时间", "类型", "会计科目表", "总账组", "NAV精度", "周末计提", "周末估值");
		basicConfig.put("titles", basicTitles);
		
		PortfolioHdr portfolio=portCfgService.loadPortfolio(portID);
		
		List<Object> basicData = new ArrayList<Object>();
		basicData.add(portfolio.getId());
		basicData.add(portfolio.getPortfolioClient());
		basicData.add(portfolio.getShortName());
		basicData.add(portfolio.getShortCode());
		basicData.add(portCfgService.loadCountryIDByProvinceID(portfolio.getProvince().getProID()));
		basicData.add(portfolio.getProvince().getProID());
		basicData.add(portfolio.getCurID());
		basicData.add(portfolio.getLotSelection());
		basicData.add(portfolio.getHolidaySD());
		basicData.add(DateUtil.convertD2YYYY_MM_DD(portfolio.getInceptionDate()));
		basicData.add(portfolio.getPortType());
		basicData.add(portfolio.getCoaahdr());
		basicData.add(portfolio.getCoaaGroup().getGroupNum());
		basicData.add(portfolio.getNavPrecision());
		basicData.add(portfolio.getBookNBDays());
		basicData.add(portfolio.getWkndValuationClose());
		basicData.add(portfolio.getTradeSettleLedger());
		basicData.add(portfolio.getClassid());
		basicData.add(portfolio.getManagerGroup());
		basicData.add(portfolio.isJzgyFlag());
		basicData.add(portfolio.isSyzfeFlag());
		
		basicConfig.put("data", basicData);
		
		JsonUtil.writeObjToResponse(response, basicConfig);
	}
	
	/**
	 * 创建投资组合
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioCreate.do", method = RequestMethod.POST, name="投资组合-创建")
	public void portfolioCreate(@RequestParam(value = "params", required = true) String params,
								@RequestParam(value = "json", required = true) String json,
								HttpServletRequest request,
								HttpServletResponse response) {
		
		MsgContainer container=commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	/**
	 * 更新投资组合基本信息
	 * @param portID
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioUpdate.do", method = RequestMethod.POST, name="投资组合-更新")
	public void portfolioUpdate(@RequestParam(value = "id", required = true) String portID,
							    @RequestParam(value = "params", required = true) String params,
							    @RequestParam(value = "json", required = true) String json,
							    HttpServletRequest request,
								HttpServletResponse response) {
		
		MsgContainer container=commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	/**
	 * 更新投资组合银行基本信息
	 * @param portID
	 * @param id
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioAccountUpdate.do", method = RequestMethod.POST, name="投资组合-账户更新")
	public void portfolioAccountUpdate(@RequestParam(value = "pid", required = true) String portID,
									 @RequestParam(value = "id", required = true) int id,
									 @RequestParam(value = "params", required = true) String params,
								     HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		System.out.println(param_list);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/port/portfolioExpenseUpdate.do", method = RequestMethod.POST, name="投资组合-费用配置更新")
	public void portfolioExpenseUpdate(@RequestParam(value = "json", required = true) String json,
								     HttpServletRequest request,
									 HttpServletResponse response) {
		MsgContainer container=commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	@RequestMapping(value="/port/portfolioExpenseCreate.do", method = RequestMethod.POST, name="投资组合-费用配置创建")
	public void portfolioExpenseCreate(@RequestParam(value = "json", required = true) String json,
									HttpServletRequest request,
									HttpServletResponse response){
		MsgContainer container=commService.executeMsg(request, json);
		commService.controllerReturn(response, container);
	}
	
	@RequestMapping(value="/port/portfolioExpenseDetail.do", method = RequestMethod.POST, name="投资组合-费用配置详情")
	public void portfolioExpenseDetail(@RequestParam(value = "id", required = true) int expenseID,
								       HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		PortExpenseCfg expense=portCfgService.loadPortExpenseCfgByID(expenseID);
		
		data.add(expenseID);
		data.add(expense.getFixFeeFlag());
		data.add(expense.getPortFeeName());
		data.add(expense.getClassService());
		data.add(expense.getBaseAcct());
		data.add(expense.getDrAcct());
		data.add(expense.getCrAcct());
		data.add(expense.getExpenseAmount());
		data.add(expense.getLowestFee());
		data.add(expense.getHighestFee());
		data.add(expense.getIntCode());
		data.add(expense.getCpnfReq());
		data.add(expense.getCpnMo());
		data.add(expense.getCpnDate());
		data.add(DateUtil.convertD2YYYY_MM_DD(expense.getStartDate()));
		data.add(DateUtil.convertD2YYYY_MM_DD(expense.getExpireDate()));
		if(expense.getNewNavFeeFlag()==ExpenseBaseType.UnNewAssetExpense){
			data.add(false);
		}else{
			data.add(true);
		}
		data.add(expense.getOrderNum());
		if(expense.getActionItems()!=null){
			String[] items=expense.getActionItems().split(",");
			List<Integer> assets = new ArrayList<Integer>();
			for(String item:items){
				if(!item.equals("")){
					assets.add(Integer.valueOf(item));
				}
			}
			data.add(assets);
		}
		result.put("data", data);
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/portfolioExpenseList.do", method = RequestMethod.POST, name="投资组合-费用配置查询")
	public void portfolioExpenseList(@RequestParam(value = "pid", required = true) String portID,
								       HttpServletResponse response) {
		//Construct Expense Related Configuration
		HashMap<String, Object> expenseConfig = new HashMap<String, Object>();
		List<String> expenseTitles = Arrays.asList("编号", "类型", "描述", "费用类型", "基准科目", "借方科目", "贷方科目", "金额|费率");
		expenseConfig.put("titles", expenseTitles);
		List<Object> expData = new ArrayList<Object>();
		
		List<PortExpenseCfg> expenseList=portCfgService.loadExpenseListByPortnum(portID);
		for(PortExpenseCfg exp:expenseList){
			List<Object> expItem = new ArrayList<Object>();
			expItem.add(exp.getPortFeeID());
			expItem.add(ExpenseBaseType.expenseTypeHM.get(exp.getFixFeeFlag()));
			expItem.add(exp.getPortFeeName());
			expItem.add(taxFeeJobFactory.getTaxFeeJobsNameMap().get(exp.getClassService()));
			expItem.add(exp.getBaseAcct());
			expItem.add(exp.getDrAcct());
			expItem.add(exp.getCrAcct());
			expItem.add(exp.getExpenseAmount());
			expData.add(expItem);
		}
		expenseConfig.put("data", expData);
		JsonUtil.writeObjToResponse(response, expenseConfig);
	}
	
	@RequestMapping(value="/port/portfolioAccountList.do", method = RequestMethod.POST, name="投资组合-账户查询")
	public void portfolioAccountList(@RequestParam(value = "pid", required = true) String portID,
								       HttpServletResponse response) {
		//Construct Bank Related Configuration
		HashMap<String, Object> bankConfig = new HashMap<String, Object>();
		List<String> bankTitles = Arrays.asList("编号", "托管行", "账户");
		bankConfig.put("titles", bankTitles);
		List<Object> bankData = new ArrayList<Object>();
		PortfolioHdr portfolio=portCfgService.loadPortfolio(portID);
		List<BkCashAcct> bankAcctList=bankCfgService.getBankCashAcctByPortNum(portfolio.getId());
		for(BkCashAcct bankacct:bankAcctList){
			List<Object> item = new ArrayList<Object>();
			item.add(bankacct.getId());
			item.add(bankacct.getBkinfo().getBankDesc());
			item.add(bankacct.getBankdesc());
			bankData.add(item);
		 }
				
		bankConfig.put("data", bankData);
		JsonUtil.writeObjToResponse(response, bankConfig);
	}
	
	@RequestMapping(value="/port/portfolioAccountShortList.do", method = RequestMethod.POST)
	public void portfolioAccountShortList(@RequestParam(value = "portID", required = false) String portID,
								       HttpServletResponse response) {
		//Construct Bank Related Configuration
		HashMap<Integer, String> bankConfig = new HashMap<Integer, String>();
		
		PortfolioHdr portfolio=portCfgService.loadPortfolio(portID);
		List<BkCashAcct> bankAcctList=bankCfgService.getBankCashAcctByPortNum(portfolio.getId());
		for(BkCashAcct bankacct:bankAcctList){
			bankConfig.put(bankacct.getId(), bankacct.getBankdesc());
		 }
				
		JsonUtil.writeObjToResponse(response, bankConfig);
	}
	
	@RequestMapping(value="/port/portfolioInterfaceList.do", method = RequestMethod.POST, name="投资组合-接口配置查询")
	public void portfolioInterfaceList(@RequestParam(value = "pid", required = true) String portID,
								       HttpServletResponse response) {
		//Construct Interface Related Configuration
		HashMap<String, Object> interfaceConfig = new HashMap<String, Object>();
		List<String> interfaceTitles = Arrays.asList("编号", "接口类型", "资产类别", "配置选择", "默认导入方式");
		interfaceConfig.put("titles", interfaceTitles);
		List<Object> intData = new ArrayList<Object>();
		
		List<PortfolioInterfaceMap> maps=portCfgService.loadPortInterfaceMapImport(portID);
		if(maps!=null){
			for(PortfolioInterfaceMap piMap:maps){
				List<Object> intItem = new ArrayList<Object>();
				intItem.add(piMap.getId());
				intItem.add(piMap.getIneterfaceSource().getSourceType());
				intItem.add(piMap.getRpSecType());
				intItem.add(piMap.getIneterfaceSource().getSourceID());
				intItem.add(piMap.getDefaultImportType());
				intData.add(intItem);
			}
			interfaceConfig.put("data", intData);
		}
		
		
		JsonUtil.writeObjToResponse(response, interfaceConfig);
	}
	
	@RequestMapping(value="/port/portfolioInterfaceUpdate.do", method = RequestMethod.POST, name="投资组合-接口配置更新")
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void portfolioInterfaceUpdate(@RequestParam(value = "pid", required = true) String portID,
									   @RequestParam(value = "id", required = true) int id,
									   @RequestParam(value = "params", required = true) String params,
								       HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		
		int mapID=id;
		String portnum=portID;
		int sourceType=Integer.valueOf(param_list.get(0).toString());
		int rpSecType=0;
		if(!param_list.get(1).toString().toLowerCase().equals("all")){
			rpSecType=Integer.valueOf(param_list.get(1).toString());
		}
		
		int sourceID=Integer.valueOf(param_list.get(2).toString());
		int importType=Integer.valueOf(param_list.get(3).toString());
		
		portCfgService.updatePortInterfaceMap(mapID, portnum, sourceType, rpSecType, sourceID, importType);
		
		
		JsonUtil.writeObjToResponse(response, "");
	}

	@RequestMapping(value="/port/portfolioAccountRemove.do", method = RequestMethod.POST, name="投资组合-账户删除")
	public void portfolioAccountRemove(@RequestParam(value = "pid", required = true) String portID,
									   @RequestParam(value = "id", required = true) int id,
								       HttpServletResponse response) {
		System.out.println(portID + " 's account - " + id + " has been removed.");
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/port/portfolioExpenseRemove.do", method = RequestMethod.POST, name="投资组合-费用配置删除")
	public void portfolioExpenseRemove(@RequestParam(value = "pid", required = true) String portID,
									   @RequestParam(value = "id", required = true) int id,
								       HttpServletResponse response) {
		portCfgService.deletePortExpense(id);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/port/portfolioInterfaceRemove.do", method = RequestMethod.POST, name="投资组合-接口配置删除")
	public void portfolioInterfaceRemove(@RequestParam(value = "pid", required = true) String portID,
									     @RequestParam(value = "id", required = true) int id,
								         HttpServletResponse response) {
		portCfgService.deletePortfolioInterfaceMap(id);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/port/portfolioAccountCreate.do", method = RequestMethod.POST, name="投资组合-账户创建")
	public void portfolioAccountCreate(@RequestParam(value = "pid", required = true) String portID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		System.out.println(param_list);
		//int CashAcctNum=Integer.valueOf(param_list.get(1).toString());
		Random rand = new Random();
		int randomNo = rand.nextInt(100 - 5) + 5;
		JsonUtil.writeObjToResponse(response, randomNo + "");
	}
	
	@RequestMapping(value="/port/portfolioInterfaceCreate.do", method = RequestMethod.POST, name="投资组合-接口配置创建")
	public void portfolioInterfaceCreate(@RequestParam(value = "pid", required = true) String portID,
									@RequestParam(value = "params", required = true) String params,
							   	    HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		
		PortfolioInterfaceMap interfacemap=portCfgService.createPortInterfaceMap(portID, param_list);
		JsonUtil.writeObjToResponse(response, interfacemap.getId());
	}
	
	
	@RequestMapping(value="/port/portfolioExportShortList.do", method = RequestMethod.POST)
	public void portfolioExportShortList(@RequestParam(value = "pid", required = true) String portID,
		       HttpServletResponse response) {
		//Construct Interface Related Configuration
		HashMap<Integer, String> interfaceConfig = portCfgService.loadInterfaceSourceByPortnum(portID);
		JsonUtil.writeObjToResponse(response, interfaceConfig);
	}
	

	@RequestMapping(value="/port/portfolioExportList.do", method = RequestMethod.POST, name="投资组合-导出配置查询")
	public void portfolioExportList(@RequestParam(value = "pid", required = true) String portID,
		       HttpServletResponse response) {
		//Construct Interface Related Configuration
		HashMap<String, Object> interfaceConfig = new HashMap<String, Object>();
		List<String> interfaceTitles = Arrays.asList("编号", "导出类型", "导出配置", "执行步骤", "默认导入方式");
		interfaceConfig.put("titles", interfaceTitles);
		List<Object> intData = new ArrayList<Object>();
		
		List<PortfolioInterfaceMap> maps=portCfgService.loadPortInterfaceMapExport(portID);
		if(maps!=null){
			for(PortfolioInterfaceMap piMap:maps){
				List<Object> intItem = new ArrayList<Object>();
				intItem.add(piMap.getId());
				//独立适配器出来，暂时把这里注释，应该以后可以单独配置适配器导出所需数据
				//intItem.add(expSource.getClassIDMap().get(piMap.getIneterfaceSource().getExpSourceClass()));
				intItem.add("暂时注销");
				intItem.add(piMap.getIneterfaceSource().getSourceID());
				intItem.add(piMap.getOperationStep());
				intItem.add(piMap.getDefaultImportType());
				intData.add(intItem);
			}
		}
		interfaceConfig.put("data", intData);
		JsonUtil.writeObjToResponse(response, interfaceConfig);
	}
	
	
	@RequestMapping(value="/port/portfolioExportCreate.do", method = RequestMethod.POST, name="投资组合-导出配置创建")
	public void portfolioExportCreate(@RequestParam(value = "pid", required = true) String portID,
									@RequestParam(value = "params", required = true) String params,
							   	    HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		
		PortfolioInterfaceMap interfacemap=portCfgService.createPortInterfaceExpMap(portID, param_list);
		JsonUtil.writeObjToResponse(response, interfacemap.getId());
	}
	
	
	@RequestMapping(value="/port/portfolioExportRemove.do", method = RequestMethod.POST, name="投资组合-导出配置删除")
	public void portfolioExportRemove(@RequestParam(value = "pid", required = true) String portID,
									  @RequestParam(value = "id", required = true) int id,
								      HttpServletResponse response) {
		portCfgService.deletePortfolioInterfaceMap(id);
		JsonUtil.writeObjToResponse(response, "");
	}

	
	@RequestMapping(value="/port/portfolioExportUpdate.do", method = RequestMethod.POST, name="投资组合-导出配置更新")
	public void portfolioExportUpdate(@RequestParam(value = "pid", required = true) String portID,
									   @RequestParam(value = "id", required = true) int id,
									   @RequestParam(value = "params", required = true) String params,
								       HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		
		portCfgService.updatePortInterfaceExpMap(id, param_list);
		JsonUtil.writeObjToResponse(response, id);
	}
	
	@RequestMapping(value="/portfolio.do", method = RequestMethod.GET, name="投资组合-详情页面显示")
	public String portfolio(@RequestParam(value="id") String portID, 
							@RequestParam(value="expense", required = false) Boolean expense,
							ModelMap model) {
		PortfolioHdr portfolio=portCfgService.loadPortfolio(portID);
		model.addAttribute("portID", portID);
		model.addAttribute("portName", portfolio.getShortName());
		if (expense != null && expense.booleanValue() == true){
			model.addAttribute("expense", true);
		}
		else{
			model.addAttribute("expense", false);
		}
		return "portfolio";
	}
	
	@RequestMapping(value="/port/portfolioCustomiseList.do", method = RequestMethod.POST, name="投资组合-自定义配置查询")
	public void portfolioCustomiseList(@RequestParam(value = "pid", required = true) String portID,
								       HttpServletResponse response) {
		
		HashMap<String, Object> config = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "自定义主键", "自定义值");
		config.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		List<Object> item = new ArrayList<Object>();
		item.add(132322);
		item.add("投资组合别名");
		item.add("abcd");
		data.add(item);
		
		List<Object> item1 = new ArrayList<Object>();
		item1.add(132323);
		item1.add("席位号");
		item1.add("123,234");
		data.add(item1);
		
		config.put("data", data);
		
		JsonUtil.writeObjToResponse(response, config);
	}
	
	@RequestMapping(value="/port/portfolioCustomiseCreate.do", method = RequestMethod.POST, name="投资组合-自定义配置创建")
	public void portfolioCustomiseCreate(@RequestParam(value = "pid", required = true) String portID,
									@RequestParam(value = "params", required = true) String params,
							   	    HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		System.out.println(param_list);
		Random rand = new Random();
		int randomNo = rand.nextInt(100 - 5) + 5;
		JsonUtil.writeObjToResponse(response, randomNo + "");
	}
	
	@RequestMapping(value="/port/portfolioCustomiseUpdate.do", method = RequestMethod.POST, name="投资组合-自定义配置更新")
	public void portfolioCustomiseUpdate(@RequestParam(value = "pid", required = true) String portID,
									   @RequestParam(value = "id", required = true) int id,
									   @RequestParam(value = "params", required = true) String params,
								       HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		System.out.println(portID + " interface save. Interface info below- interface number - " + id);
		for (Object param : param_list){
			System.out.println(param);
		}
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/port/portfolioCustomiseRemove.do", method = RequestMethod.POST, name="投资组合-自定义配置删除")
	public void portfolioCustomiseRemove(@RequestParam(value = "pid", required = true) String portID,
									     @RequestParam(value = "id", required = true) int id,
								         HttpServletResponse response) {
		System.out.println(portID + " interface delete. Interface info below- interface number - " + id);
		JsonUtil.writeObjToResponse(response, "");
	}
	

	@RequestMapping(value = "/port/subPorts.do", method = RequestMethod.POST, name="投资组合-子组合查询")
	public void subPorts(
			@RequestParam(value = "id", required = true) String portID,
			HttpServletResponse response) {
		//查询投资组合和子投资组合的map
		HashMap<String, String> result = new HashMap<String, String>();
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			List<PortfolioHdr> plist=portfolioHdrDao.loadPortfolioByClassID(portID);
			for (PortfolioHdr ph : plist) {
				result.put(ph.getId(), ph.getShortName());
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	

	@RequestMapping(value = "/port/allPorts.do", method = RequestMethod.POST, name="投资组合-全部组合查询")
	public void allPorts(
			@RequestParam(value = "id", required = true) String portID,
			HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			//获取当前登录用户
			//获取当前登录用户权限下投资组合
			List<UserPortfolioMap> upflist=userPortfolioMapDao.loadUserPortMapListByUserID(JixuUserUtil.getUserIDbyRequest(request));
			List<String> portList=new ArrayList<String>();
			for(UserPortfolioMap upm:upflist){
				portList.add(upm.getPortfolio());
			}
			
			//取得该client下所有的投资组合
			List<PortfolioHdr> portfolios=portCfgService.loadPortfolios(portID);
			for (PortfolioHdr ph : portfolios) {
				//遍历投资组合  判断是否在该权限下
				if(portList.contains(ph.getId())){
					//显示该权限下所有的classID为0的可选组合 
					if(ph.getClassid()==0){
						result.put(ph.getId(), ph.getShortName());
					}
				}
			}
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	@RequestMapping(value = "/port/subPortsUpdate.do", method = RequestMethod.POST, name="投资组合-子组合更新")
	public void roleUpdate(
			@RequestParam(value = "id", required = true) String portID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		
		List<Object> port_list = JsonUtil.stringToList(params);
		//创建投资组合和子投资组合的map关系
		portCfgService.createPortfolioClassIDMap(portID,DataConvert.convert2StringList(port_list));
		JsonUtil.writeObjToResponse(response, "");
	}
	
	/**
	 * 列出所有的基金类型
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioTypeList.do", method = RequestMethod.POST)
	 public void portfolioTypeList(HttpServletResponse response){
		HashMap<Integer, String> result = PortfolioTypes.portfolioTypesHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values expense_add_form.json--"values": ["固定", "浮动"],
	//option-select-values expense_table_form.json--"values": ["固定", "浮动"],
	@RequestMapping(value="/port/expenseTypeOption.do", method = RequestMethod.POST)
	public void getExpenseTypeOption(HttpServletResponse response){
		HashMap<Integer, String> result = ExpenseBaseType.expenseTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values expense_add_form.json--"values": ["净资产", "总净值", "总收入"],
	//option-select-values expense_table_form.json--"values": ["净资产", "总净值", "总收入"],
	@RequestMapping(value="/port/netAsstTypeOption.do", method = RequestMethod.POST)
	public void getNetAsstTypeOption(HttpServletResponse response){
		HashMap<Integer, String> result = NetAssetType.netAssetHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//option-select-values interface_add_form.json--"values": ["手动", "自动"],
	//option-select-values interface_table_form.json--"values": ["手动", "自动"],
	@RequestMapping(value="/port/interfaceImportTypeOption.do", method = RequestMethod.POST)
	public void getInterfaceMethodTypeOption(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceMethodType.interfaceMethodTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/expenseDayCount.do", method = RequestMethod.POST)
	public void expenseDayCounts(HttpServletResponse response){
		HashMap<Integer, String> result = InterestInitCode.intInitCodeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	@RequestMapping(value="/port/expenseFrequency.do", method = RequestMethod.POST)
	public void expenseFrequency(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.intCpnfReqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//新界面  付款频率不需要 每N天 和 计划
	@RequestMapping(value="/port/newExpenseFrequency.do", method = RequestMethod.POST)
	public void newExpenseFrequency(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.newCpnfReqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/acctDateOption.do", method = RequestMethod.POST)
	public void acctDateOption(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "交易日");
		result.put(1, "结算日");
		result.put(2, "交易日+1");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/expenseMonth.do", method = RequestMethod.POST)
	public void expenseMonth(@RequestParam(value = "freq", required = false) String freqStr, HttpServletResponse response){
		
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		if(freqStr!=null && !freqStr.equals("")){
			int freq=Integer.valueOf(freqStr);
			if (freq == CouponData.Quarterly){
				result=InterestCpnMo.quarterHM;
			}else if (freq == CouponData.SemiAnnual){
				result=InterestCpnMo.semiAnnualHM;
			}else if (freq == CouponData.Annual){
				result=InterestCpnMo.annualHM;
			}else if (freq == CouponData.Monthly){
				result=InterestCpnMo.monthlyHM;
			}else if (freq == CouponData.Scheduled){
				result.put(0, "-");
			}else if (freq == CouponData.EveryNDays){
				result.put(0, "-");
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/newExpenseMonth.do", method = RequestMethod.POST)
	public void newExpenseMonth(@RequestParam(value = "freq", required = false) String freqStr, HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		if(freqStr!=null && !freqStr.equals("")){
			int freq=Integer.valueOf(freqStr);
			if (freq == CouponData.Quarterly){
				result=InterestCpnMo.quarterHM;
			}else if (freq == CouponData.SemiAnnual){
				result=InterestCpnMo.semiAnnualHM;
			}else if (freq == CouponData.Annual){
				result=InterestCpnMo.annualHM;
			}else if (freq == CouponData.Monthly){
				result=InterestCpnMo.monthlyHM;
			}else if(freq == CouponData.AtMaturity || freq == CouponData.Scheduled){
				//前端发现有-999的key   则把付款月份和付款日disabled
				result.put(-999, "");
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/resourcesInPorts.do", method = RequestMethod.POST)
	public void resourcesInPorts(@RequestParam(value = "ports", required = false) String portsStr,
								 @RequestParam(value = "type", required = false) int interfaceType,
							     HttpServletResponse response){
		//pending to-do
		List<Object> port_list = JsonUtil.stringToList(portsStr);
		String[] ports=new String[port_list.size()];
		for(int i=0;i<port_list.size();i++){
			ports[i]=port_list.get(i).toString();
		}
		
		List<InterfaceSource> sourceList=portCfgService.loadInterfaceSource(ports, interfaceType);
		
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		for(InterfaceSource source:sourceList){
			result.put(source.getSourceID(), source.getSourceName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/managersInPort.do", method = RequestMethod.POST)
	public void managersInPort(@RequestParam(value = "port", required = false) String port,
							     HttpServletResponse response){
		HashMap<Integer, String> result = portCfgService.loadManagersByPortnum(port);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/smfsInPort.do", method = RequestMethod.POST)  
    public void smfsInPort(@RequestParam(value = "port", required = true) String port,
    					   @RequestParam(value = "date", required = true) String date,
    					   HttpServletResponse response) {
		List<String> positionList=portCfgService.loadPortfolioPosition(port, date);
		JsonUtil.writeObjToResponse(response, positionList);
	}
	
	@RequestMapping(value="/port/portsMap.do", method = RequestMethod.POST)
	public void portsMap(@RequestParam(value = "ports", required = false) String ports,
					     HttpServletResponse response){
		List<Object> port_list = JsonUtil.stringToList(ports);
		List<PortfolioHdr> portfolioList=portCfgService.loadPortfolios(port_list);
		HashMap<String, String> result = new HashMap<String, String>();
		
		for(PortfolioHdr port:portfolioList){
			result.put(port.getId(), port.getShortName());
		}
		//to-do list
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/clientByPort.do", method = RequestMethod.POST)
	public void clientByPortNum(@RequestParam(value = "portID", required = true) String portnum,
					     HttpServletResponse response){
		PortfolioHdr portfolio=portCfgService.loadPortfolio(portnum);
		int clientid=portfolio.getPortfolioClient();
		JsonUtil.writeObjToResponse(response, clientid);
	}
	
	@RequestMapping(value = "/port/taxFeeClassType.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void getTaxFeeClassType(HttpServletResponse response){
		Map<String, String> result=taxFeeJobFactory.getTaxFeeJobsNameMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/expensePriority.do", method = RequestMethod.POST)
	 public void expensePriority(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(1, "优先级1");
		result.put(2, "优先级2");
		result.put(3, "优先级3");
		result.put(4, "优先级4");
		result.put(5, "优先级5");
		result.put(4, "优先级6");
		result.put(5, "优先级7");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/port/secLevel3Type.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void secLevel3Type(HttpServletResponse response){
		HashMap<Integer, String> result=new HashMap<Integer, String>();
		result.put(1, "普通股");
		result.put(2, "优先股");
		result.put(3, "债券");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/batch.do", method = RequestMethod.GET, name="投资组合-批次页面显示")
	public String portSub(){
		return "batch";
	}
	
	@RequestMapping(value="/port/portfolioSubList.do", method = RequestMethod.POST, name="投资组合-申购批次查询")
	public void portfolioSubList(@RequestParam(value = "portfolio", required = true) String portnum,
			 				  	 @RequestParam(value = "start", required = true) String startDate,
			 				  	 @RequestParam(value = "end", required = true) String end,
			 				  	 HttpServletRequest request,
			 				  	 HttpServletResponse response){
		
		
		Date endDate=DateUtil.convertStr2DateAll(end);
		HashMap<String, Object> result=portCfgService.getBankZiGuanInfo(portnum, endDate, endDate);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 创建投资组合
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/port/portfolioCopy.do", method = RequestMethod.POST, name="投资组合-复制")
	public void portfolioCopy(@RequestParam(value = "json", required = true) String msg, 
			HttpServletRequest request,
			HttpServletResponse response) {
		MsgContainer container=commService.executeMsg(request, msg);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value="/port/performanceFeeCfg.do", method = RequestMethod.POST, name="投资组合-绩效费配置")
	public void performanceFeeCfg(@RequestParam(value = "port", required = true) String portID,
			 					  @RequestParam(value = "secid", required = true) String secid,
			 					  @RequestParam(value = "contract", required = true) String lotNum,
								  @RequestParam(value = "json", required = true) String jsonMsg, 
								  HttpServletRequest request,
								  HttpServletResponse response) {
		HashMap<String, String> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		fieldsMap.put(MsgFields.portnum, portID);
		fieldsMap.put(MsgFields.secid, secid);
		fieldsMap.put(MsgFields.contract, lotNum);
		
		MsgContainer container=commService.executeMsg(request, JSONObject.toJSONString(fieldsMap));
		commService.controllerReturn(response, container);
	}
	
	@RequestMapping(value="/port/performanceFeeDetail.do", method = RequestMethod.POST, name="投资组合绩-绩效费详情")  
    public void portPerformanceFeeDetail(@RequestParam(value = "port", required = true) String portID,
    									 @RequestParam(value = "secid", required = true) String secids,
    		                			 @RequestParam(value = "contract", required = true) int lotNum,
    					    HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String secid=secids.split("\\|")[1];
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		PerformanceFeeCfg pfc=portCfgService.loadPerformanceFeeCfg(portID, sec.getId(), lotNum);
		
		//如果该批次存在绩效费用
		if (pfc!=null){
			HashMap<String, Object> hm =new HashMap<String, Object>();
			hm.put("id", pfc.getId());
			hm.put("benchMark", pfc.getBenchMark());
			hm.put("returnRateType", pfc.getReturnRateType());
			hm.put("expenseBasisType", pfc.getExpenseBasisType());
			hm.put("minLevel", pfc.getMinLevel());
			hm.put("maxLevel", pfc.getMaxLevel());
			hm.put("clawBackFlag", pfc.isClawBackFlag());
			hm.put("clawBackExpenseType", pfc.getClawBackExpenseType());
			hm.put("inPercentage", pfc.getInPercentage());
			hm.put("outPercentage", pfc.getOutPercentage());
			hm.put("drAcct", pfc.getDrAcct());
			hm.put("crAcct", pfc.getCrAcct());
			hm.put("activeFlag", pfc.isActiveFlag());
			result.put("data", hm);
		}else{
			result.put("data", null);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/port/expenseFeeReturnRateType.do", method = RequestMethod.POST)
	public void expenseFeeReturnRateType(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, PerformanceFeeType.returnRateOption);
	}
	
	@RequestMapping(value="/port/expenseFeeExpenseBasisType.do", method = RequestMethod.POST)
	public void expenseBasisType(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, PerformanceFeeType.expenseBasisOption);
	}
}
