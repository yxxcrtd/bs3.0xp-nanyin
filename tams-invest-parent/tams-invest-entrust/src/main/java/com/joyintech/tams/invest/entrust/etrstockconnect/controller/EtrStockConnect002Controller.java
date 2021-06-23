
package com.joyintech.tams.invest.entrust.etrstockconnect.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect002Service;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * 港股通
 * @see EtrStockConnect002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrStockConnect002")
@RequestMapping("/invest/entrust/etrstockconnect/EtrStockConnect002")
public class EtrStockConnect002Controller extends BaseController {
	
	/**
     * 市场行情接口地址
     */
/*    @Value("${MarketInfoInterf.BaseUrl}")
    private String MarketInfoInterf_BaseUrl;*/
	/**
	 * 交易所行情接口地址
	 */
	@Value("${MarketInfoInterf.exchangeUrl}")
	private String MarketInfoInterf_exchangeUrl;
	@Autowired
	 private IPlatformDate iPlatformDate;
	
	/**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private EtrStockConnect002Service etrStockConnect002Service;

    @Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.目标类型, DictCode.价格模式, DictCode.是否沪港通或深港通);
		this.setConstSelectOption(model);

		boolean todoList = false;
		Date platformDate = DateUtil.platDateFromCache();
		model.addAttribute("iPlatformDate", DateUtil.format(platformDate));

		String entrustId = getParam("entrustId");
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

		EtrStockConnectDto entity = new EtrStockConnectDto();
		//流程代办
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			todoList = true;
			entity = etrStockConnect002Service.getBussinessEntityByWorkflowId(
					workflowId);
			model.addAttribute(GlobalConstant.ENTITY, entity);
			model.addAttribute("workflowId", workflowId);
		}
		//查看
		else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {
		}
		//新建页面初始化
		else{
			// 投资经理，当前用户
			entity.setInvManagerId(LoginUserUtil.getUserName());
			entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
			entity.setTradeMarket(DictCodeListExt1.交易场所_香港联合交易所.getValue());
			entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
			entity.setEntrustStatus(BussinessDictCodeList.委托状态_新建.getValue());
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		model.addAttribute("todoList", todoList);
        return super.init(model);
    }


	/**
	 * 设置画面的一些固定的数据字典明细
	 *
	 * @param model Model
	 */
	private void setConstSelectOption(Model model) {
		// 金融产品类型(投管用)
		model.addAttribute("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
		// 交易方向
		model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
		model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
		model.addAttribute("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
		model.addAttribute("PS_T47", DictCodeListExt2.交易方向_增发.getValue());
		model.addAttribute("PS_T104", DictCodeListExt2.交易方向_卖碎股.getValue());

		// 交易场所
		model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
		model.addAttribute("TRADE_PLACE_04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
		model.addAttribute("TRADE_PLACE_06", DictCodeListExt1.交易场所_香港联合交易所.getValue());
		// 价格模式
		model.addAttribute("PRICE_MODEL_PRM02", DictCodeListExt2.价格模式_限价指令.getValue());
		model.addAttribute("PRICE_MODEL_PRM03", DictCodeListExt2.价格模式_市价指令.getValue());
		model.addAttribute("PRICE_MODEL_PRM01", DictCodeListExt2.价格模式_指定价格.getValue());
		// 目标类型
		model.addAttribute("TARGET_CLASS_01", DictCodeListExt3.目标类型_数量.getValue());
		model.addAttribute("TARGET_CLASS_02", DictCodeListExt3.目标类型_金额.getValue());
		//沪港通或深港通
		model.addAttribute("HKSE_MARKET_01", DictCodeListExt2.是否沪港通或深港通_沪港通.getValue());
		model.addAttribute("HKSE_MARKET_02", DictCodeListExt2.是否沪港通或深港通_深港通.getValue());
		model.addAttribute("HKSE_MARKET_03", DictCodeListExt2.是否沪港通或深港通_否.getValue());
		model.addAttribute("HKSE_MARKET_04", DictCodeListExt2.是否沪港通或深港通_深港通_沪港通.getValue());
		//监督时分_委托监控
		model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
		//人民币
		model.addAttribute("default_ccy", DictCodeList.币种_人民币_CNY.getValue());
	}

	/**
	 *@title: getfinprodIdForAdd
	 *@description: 获取新建界面的资产代码联想下拉框
	 *@throws:
	 */
	@ResponseBody
	@RequestMapping("/getfinprodIdForAdd")
	public Page<SelectOptionDto> getfinprodIdForAdd() throws Exception {
		Page<SelectOptionDto> temp = etrStockConnect002Service.getfinprodIdForAdd(RequestUtil.getPageParam());
		return temp;
	}

    /**
     * 数量、指令金额
     */
    @RequestMapping("/calNumAndAmt")
    @ResponseBody
    public AjaxResult calNumAndAmt(@RequestBody EtrStockConnectDto dto){
        AjaxResult result = new AjaxResult();
        etrStockConnect002Service.calNumAndAmt(dto);
        result.setData(dto);
        return result;
    }

	/**
	 * 配售代码
	 */
	@RequestMapping("/getSaleCode")
	@ResponseBody
	public AjaxResult getSaleCode(@RequestBody Map<String, Object> map){
		AjaxResult result = new AjaxResult();
		String saleCode = etrStockConnect002Service.getSaleCode(map);
		result.setData(saleCode);
		return result;
	}

	/**
	 * 自动带出港股通市场信息
	 */
	@RequestMapping("/getFinProdInfo")
	@ResponseBody
	public AjaxResult getFinProdInfo(@RequestBody Map<String, Object> map){
		AjaxResult result = new AjaxResult();
		EtrStockConnectDto dto = etrStockConnect002Service.getFinProdInfo(map);
		result.setData(dto);
		return result;
	}

	/**
	 * 查询估值净价
	 * @author wangguiqi
	 */
	@ResponseBody
	@RequestMapping("getHkseClosePrice")
	public AjaxResult getClosePrice(){
		return etrStockConnect002Service.getHkseClosePrice(RequestUtil.getPageParam());
	}

	/**
	 * 参考汇率
	 */
	@RequestMapping(value="getReferenceRate")
	@ResponseBody
	public AjaxResult getReferenceRate(@RequestBody Map<String, Object> param) {
		AjaxResult result = new AjaxResult();
		EtrStockConnectDto data = etrStockConnect002Service.getReferenceRate(param);
		result.setData(data);
		return result;
	}

	/**
	 *@description: 组合可用现金查询
	 */
	@ResponseBody
	@RequestMapping("/getProAvailableCash")
	public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
		return etrStockConnect002Service.getProAvailableCash(param);
	}
	/**
	 *@description: 组合可用持仓查询
	 */
	@ResponseBody
	@RequestMapping("/selectAvailableNum")
	public BigDecimal selectAvailableNum(@RequestBody EtrSecPositionDto dto){
		return etrStockConnect002Service.selectAvailableNum(dto);
	}

	/**
	 *@description: 查询证券账户
	 */
	@ResponseBody
	@RequestMapping("/getSecManageAcctId")
	public StlSecAcctInfo getSecManageAcctId(@RequestBody Map<String, Object> param){
		return etrStockConnect002Service.getSecManageAcctId(param);
	}

	/**
     * 保存按钮点击时的check
     */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) EtrStockConnectDto dto,
                            BindingResult result) {
		if (result.hasErrors()) {
			return new AjaxResult(result);
		}

		String confirmMsg = "";
		String changeLineMark = "<br/>";
		// 可用持仓软校验
		if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())&& StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
				DictCodeListExt1.是或否_是.getValue())&&dto.getAssetAvailablePosition() != null&& StringUtil.isNotEmpty(dto.getEntrustNum())) {
			if(dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
				confirmMsg = this.getMessage("ETR_X_011", this.getMessage("EtrStockConnect002.entrustNum"))+changeLineMark;
			}
		// 可用现金软校验
		}else if((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()))&& StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
				DictCodeListExt1.是或否_是.getValue())&& dto.getProAvailableCash() != null && StringUtil.isNotEmpty(dto.getEntrustAmt())) {
			if(dto.getEntrustAmt().compareTo(dto.getProAvailableCash())>0) {
				confirmMsg = this.getMessage("ETR_X_010", this.getMessage("EtrStockConnect002.entrustAmt"))+changeLineMark;
			}
		}else if((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()))&& StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
				DictCodeListExt1.是或否_是.getValue())&& dto.getProAvailableCash() != null && StringUtil.isNotEmpty(dto.getEntrustNum())&& StringUtil.isNotEmpty(dto.getPrice())&& StringUtil.isNotEmpty(dto.getReferenceRate())) {
			BigDecimal amt = dto.getEntrustNum().multiply(dto.getPrice()).multiply(dto.getReferenceRate());
			if(amt.compareTo(dto.getProAvailableCash())>0) {
				confirmMsg = this.getMessage("ETR_X_010", this.getMessage("EtrStockConnect002.entrustAmt"))+changeLineMark;
			}
		}
		if(StringUtil.isNotEmpty(confirmMsg)) {
			confirmMsg +=changeLineMark;
			confirmMsg +=getMessage("请确认是否继续?");
			AjaxResult ajaxResult = new AjaxResult();
			ajaxResult.setData(confirmMsg);
			return ajaxResult;
		}
		return commonCheck(result, dto.getOperateType());
	}
	
    /**
	 * 保存/提交
	 */
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody EtrStockConnectDto dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}

		if(!StringUtil.isNotEmpty(dto.getEntrustId())){
			BussinessCodeParameter param = new BussinessCodeParameter();
			param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_港股通投资委托));
			String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
			dto.setEntrustId(code);
		}

		//预占
//		if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())) {
//			AjaxResult ar = EntrustRdsCommonAop.doPreOccupy(
//					dto,
//					BussinessDictCodeListRds.接口系统ID_预占接口.getValue(),
//					DictCodeListExt2.平安资产类型_股票.getValue());
//			if (StringUtil.isNotEmpty(ar)) {
//				return ar;
//			}
//		}

		ProcessParameter<EtrStockConnectDto> processParameter = new ProcessParameter<EtrStockConnectDto>();
		WorkFlowParameter<EtrStockConnectDto> params = new WorkFlowParameter<EtrStockConnectDto>();
		params.setPageId(this.getPageId());
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getEntrustId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_港股通投资委托.getValue());
		
		// 设置流程业务说明, entity即传入的dto
		if(dto.getEntrustNum() != null){
			params.setComment(
					entity -> this.getMessage("EtrStockConnect002") + "(" + entity.getPortfolioId() + ")"
							+ "-" + this.getMessage("EtrStockConnect002.ps")+"("+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
							+ this.getMessage("EtrStockConnect002.vdate") + ":" + DateUtil.format(entity.getVdate())
							+ ")-(" + this.getMessage("EtrStockConnect002.mdate") + ":"
							+ DateUtil.format(entity.getMdate()) + ")-("
							+ this.getMessage("EtrStockConnect002.entrustNum") + ":"
							+ entity.getEntrustNum() + ")-(" + this.getMessage("EtrStockConnect002.price") + ":" + entity.getPrice() + ")");
		}else{
			params.setComment(
					entity -> this.getMessage("EtrStockConnect002") + "(" + entity.getPortfolioId() + ")"
							+ "-" + this.getMessage("EtrStockConnect002.ps")+"("+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
							+ this.getMessage("EtrStockConnect002.vdate") + ":" + DateUtil.format(entity.getVdate())
							+ ")-(" + this.getMessage("EtrStockConnect002.mdate") + ":"
							+ DateUtil.format(entity.getMdate()) + ")-("
							+ this.getMessage("EtrStockConnect002.entrustAmt") + ":"
							+ entity.getEntrustAmt() + ")");
		}
		processParameter.setWorkFlowParameter(params);
        String msg = etrStockConnect002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

}
	
