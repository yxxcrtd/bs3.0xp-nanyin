
package com.joyintech.tams.invest.trade.trdstockconnect.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.trade.common.aop.TradeRdsCommonAop;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.business.basicdata.BaseTradeFeeCommService;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.BasicTradeFeeInputDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectDto;
import com.joyintech.tams.invest.trade.trdstockconnect.service.TrdStockConnect002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 港股通交易新建
 *
 * @author hechuan
 * @version 1.0 2021年4月14日
 * @see TrdStockConnect002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdStockConnect002")
@RequestMapping("/invest/trade/trdstockconnect/TrdStockConnect002")
public class TrdStockConnect002Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
	@Autowired
	private TrdStockConnect002Service trdStockConnect002Service;
	@Autowired(required = false)
	private BaseGetBusinessId baseGetBusinessId;
    /**
     * 费用查询
     */
    @Autowired(required = false)
    private BaseTradeFeeCommService baseTradeFeeCommService;

	@Autowired(required = false)
	private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
	
	@Autowired
	private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;
    
    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.上市板, DictCode.是否有锁定期, DictCode.交易方向
        		, DictCode.外汇交易模式, DictCode.币种, DictCode.交易场所, DictCode.是否沪港通或深港通);
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("DEAL_MODL_04", DictCodeListExt2.外汇交易模式_大宗交易专场.getValue());
        model.addAttribute("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
		model.addAttribute("TRADE_PLACE_02", DictCodeListExt1.交易场所_上海证券交易所.getValue());
		model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
		model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
		model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
		model.addAttribute("PS_T47", DictCodeListExt2.交易方向_增发.getValue());
		model.addAttribute("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
		model.addAttribute("PS_T48", DictCodeListExt2.交易方向_新股中签.getValue());
		model.addAttribute("PS_T104", DictCodeListExt2.交易方向_卖碎股.getValue());
		//给是否锁定配置值
		model.addAttribute("WITH_LOCK_PERIOD_T01",DictCodeListExt2.是否有锁定期_是.getValue());
		model.addAttribute("WITH_LOCK_PERIOD_T02",DictCodeListExt2.是否有锁定期_否.getValue());
		//给上市板配置值
		model.addAttribute("BOARD_BD03", DictCodeList.上市板_科创板.getValue());
		//给交易方式配置值
		model.addAttribute("DEAL_MODE_03", DictCodeListExt2.外汇交易模式_盘后定价交易.getValue());
		model.addAttribute("DEAL_MODE_02", DictCodeListExt2.外汇交易模式_大宗交易.getValue());
		model.addAttribute("DEAL_MODE_04", DictCodeListExt2.外汇交易模式_大宗交易专场.getValue());
		
		model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
		model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
		model.addAttribute("FINPROD_TYPE_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
		model.addAttribute("F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
		model.addAttribute("FINPROD_TYPE_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
		TrdStockConnectDto dto = new TrdStockConnectDto();
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
//        String validStatus = this.getParam("validStatus");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdStockConnect002Service.getBussinessEntityByWorkflowId(
                workflowId);
			//dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getParentPortfolioId()));
			//dto.setPortfolioId(dto.getActPortfolioId());
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("workflowId",workflowId);
        }
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdStockConnect002Service.getTrdProductDealDto(tradeId);
        }
        else  if (StringUtil.isNotEmpty(entrustId)){
        	TrdStockConnectDto etrdto = trdStockConnect002Service.getEntrustInfo(entrustId);
            BeanUtil.copyPropertiesIgnoreNull(dto, etrdto);
			if(StringUtil.isNotBlank(dto.getRemark())&&StringUtil.contains(dto.getRemark(),"退回原因")){
				dto.setRemark("");
			}
        	if (StringUtil.isNotEmpty(etrdto.getPrice())) {
                dto.setUnitCprice(etrdto.getPrice());
            }
        	if (StringUtil.isNotEmpty(etrdto.getEntrustNum())) {
        		if(StringUtil.isNotEmpty(etrdto.getFinishAmt())){
        			dto.setShareAmt(ArithmeticUtil.subtract(etrdto.getEntrustNum(), etrdto.getFinishAmt()));
        		}else{
        			dto.setShareAmt(etrdto.getEntrustNum());
        		}
            }
        }else {
//        	Date platDate = platformDate.getSysDate();
//        	String settleDate = DateUtil.format(dealHalfDay(platDate));
//        	model.addAttribute("settleDate", settleDate);
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        }
        else {
            model.addAttribute("tradeId", dto.getTradeId()==null?this.getParam("entrustId"):dto.getTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }

		if (StringUtil.isBlank(dto.getParentPortfolioId())) {
			dto.setParentPortfolioId(tradeCommonService.selectParentPortfolioIdByPortfolioId(
					dto.getPortfolioId()
			));
		}
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
	//----add by jjpei 20190718--------交易监控风险试算-----------start--------
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //----add by jjpei 20190718--------交易监控风险试算-----------end--------
        model.addAttribute("dealMode", DictCodeListExt2.外汇交易模式_竞价交易.getValue());
 
        return super.init(model);
    }

	/**
	 * 查询证券托管户：联想框文本框
	 *
	 * @param params
	 *            画面参数
	 * @return 证券托管户：联想框文本框
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getSecManageAcctId")
	@ResponseBody
	public List<SelectOptionDto> getSecManageAcctId(@RequestParam Map<String, Object> params) {
		return trdStockConnect002Service.getSecManageAcctId(params);
	}

	/**
	 * 资产代码：下拉框
	 *
	 * @param params
	 *            画面参数
	 * @return 资产代码：下拉框
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getFinprodId")
	@ResponseBody
	public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params) {
		return trdStockConnect002Service.getFinprodId(params);
	}

	/**
	 * 保存提交前的数据检查
	 *
	 * @param dto
	 *            画面输入参数
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("check")
	@ResponseBody
	public AjaxResult check(@Validated({ ValidateGroupEdit.class }) @RequestBody TrdStockConnectDto dto,
			BindingResult result) {
		//有check错误直接返回
		if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
		
		// 确认提醒
        if (result.getErrorCount() == 0) {
            AjaxResult ajaxResult = null;
            String confirmMsg = "";
            String changeLineMark = "<br/>";

            //买入时，check组合T日可用现金、资金账户可用现金 add by hpp 2020-07-23 start
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
            		|| StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_配股.getValue())
            		|| StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_增发.getValue())
            		|| StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_新股中签.getValue())){
            	PtlCashPositionFlowDto pcpfdto = new PtlCashPositionFlowDto();
            	pcpfdto.setCdate(dto.getTradeDate());
                List<String> acctTypeList = new ArrayList<>();
                acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
                acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
                pcpfdto.setAcctType(acctTypeList);
                pcpfdto.setPortfolioId(dto.getPortfolioId());
                pcpfdto.setCcy(dto.getCcy());
            	BigDecimal proAvailableCash = basePtlCashPositionInfoService.queryUsableCashPosition(pcpfdto);
            	if (dto.getTradeAmt() != null && proAvailableCash != null
                        && dto.getTradeAmt().compareTo(proAvailableCash) > 0) {
                    if (StringUtil.isNotBlank(confirmMsg)) {
                        confirmMsg += changeLineMark;
                    }
                    confirmMsg += "组合T日可用现金不足,当前最多可用金额为:<br/>" + proAvailableCash + "请确认是否继续？";
                }
            	
            	BigDecimal tradeMarketAccAvailableCash = BigDecimal.ZERO;
                if (StringUtil.isNotEmpty(dto.getSecManageAcctId())) {
                	//查询账户可用现金
                	StlSecAcctInfo secAcct = stlSecAcctInfoMapper.selectByPrimaryKey(dto.getSecManageAcctId());
                	if(secAcct!=null&&StringUtil.isNotEmpty(secAcct.getTrdAcctId())){
                		pcpfdto.setAcctType(null);
                		pcpfdto.setAcctId(secAcct.getTrdAcctId());
                		tradeMarketAccAvailableCash = basePtlCashPositionInfoService.queryUsableCashPosition(pcpfdto);
                	}
                }
            	if (dto.getTradeAmt() != null && tradeMarketAccAvailableCash != null
                        && dto.getTradeAmt().compareTo(tradeMarketAccAvailableCash) > 0) {
            		if (StringUtil.isNotBlank(confirmMsg)) {
                        confirmMsg += changeLineMark;
                    }
                    confirmMsg += "资金账户可用现金不足,当前最多可用金额为:<br/>" + tradeMarketAccAvailableCash + "请确认是否继续？";
                }
            }
            //买入时，check组合T日可用现金、资金账户可用现金 add by hpp 2020-07-23 end
            
            if (StringUtil.isNotBlank(confirmMsg)) {
                ajaxResult = new AjaxResult();
                ajaxResult.setData(confirmMsg);
            }
            if (ajaxResult != null) {
                return ajaxResult;
            }
        }
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存提交
	 *
	 * @param dto
	 * @return 画面输入参数
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("save")
	@ResponseBody
	public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdStockConnectDto dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
            return new AjaxResult(br);
        }
		if (baseGetBusinessId != null) {
			BussinessCodeParameter param = new BussinessCodeParameter();
	        param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票投资交易));
			dto.setTradeId(baseGetBusinessId.buildBussinessCode(param));
		} else {
			dto.setTradeId(IDGenerator.generate());
		}
		//----------add by jjpei 20200114 核风险版本 start----------
		if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
			AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
					BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeListExt2.平安资产类型_股票.getValue(),
					(en)->interfRiskRdsService.sendRiskRdsData(dto,en));
			if (StringUtil.isNotEmpty(ar)) {
				return ar;
			}
			// add by zhangrui1 start
			// 将实占信息设置到线程缓存出错时撤销用
//			TradeRdsCommonAop.setLocalCache(dto);
			// add by zhangrui1 end
		}
		//----------add by jjpei 20200114 核风险版本 end----------
		ProcessParameter<TrdStockConnectDto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<TrdStockConnectDto> workFlowParameter = new WorkFlowParameter<>();
		processParameter.setWorkFlowParameter(workFlowParameter);
		processParameter.setPageId(this.getPageId());
		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			processParameter.setOperationType(OperationType.NEW);
		} else {
			processParameter.setOperationType(OperationType.MODIFY);
		}
		workFlowParameter.setPageId(this.getPageId());
		workFlowParameter.setElementId(this.getParam("elementId"));
		workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
		workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_港股通交易.getValue());
		workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
				getMessage("TrdStock002.tradeId") + GlobalConstant.COLON + entity.getTradeId(),
				getMessage("TrdStock002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
				getMessage("TrdStock002.tradeDate") + GlobalConstant.COLON
						+ DateUtil.format(entity.getTradeDate())));

		String msg = trdStockConnect002Service.start(processParameter,
				dto);
		return new AjaxResult(msg);
	}


	/**
     *查询费用
     * <功能详细描述>
     * @param
     * @return
	 * @throws ParseException 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFeeDetail")
    public Map<String,Object> getFeeDetail() throws ParseException {
    	Map<String, Object> param = RequestUtil.getPageParam();
    	BasicTradeFeeInputDto inputDto=new BasicTradeFeeInputDto();
    	
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)param.get("tradeDate"));
        inputDto.setTradeDate(date);//交易日期
        inputDto.setTradeMarket((String)param.get("tradeMarket"));//交易场所
        inputDto.setFinprodType2(DictCodeListExt2.平安资产类型_股票.getValue());
        inputDto.setPs((String)param.get("ps"));//交易方向
        inputDto.setFpriceAmt(new BigDecimal((String)param.get("fpriceAmt")));//全价总额
        inputDto.setPrinAmt(new BigDecimal((String)param.get("prinAmt")));//券面总额
        inputDto.setIsGrade("1");
    	inputDto.setSecAcctId((String)param.get("secManageAcctId"));//画面.证券账户
    	List<String> list = new ArrayList<>();
    	list.add(DictCodeListExt1.一次性费用类型_佣金.getValue());
    	inputDto.setFeeTypeList(list);
    	List<TrdTradeFeeDetail> tradeFeeList=baseTradeFeeCommService.bourseFeeDeal(inputDto);
    	Map<String, Object> map = new HashMap<>();
    	if(tradeFeeList!=null&&!tradeFeeList.isEmpty()){
    		map.put("commission", tradeFeeList.get(0));
    	}else{
    		map.put("commission", "");
    	}
    	
    	list.clear();
    	list.add(DictCodeListExt1.一次性费用类型_印花税.getValue());
    	inputDto.setSecAcctId(null);
    	inputDto.setFeeTypeList(list);
    	tradeFeeList=baseTradeFeeCommService.bourseFeeDeal(inputDto);
    	if(tradeFeeList!=null&&!tradeFeeList.isEmpty()){
    		map.put("stampDuty", tradeFeeList.get(0));
    	}else{
    		map.put("stampDuty", "");
    	}
    	return map;
    }
    
    /**
     * 通道可用现金查询
     * @param
     * @return
     * @see
     */
    @ResponseBody
    @RequestMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String,Object> map) throws Exception{
		map.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        return trdStockConnect002Service.getTongDaoCash(map);
    }
    
    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 10:18   
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
		param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        return trdStockConnect002Service.getProAvailableCash(param);
    }
    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 10:18   
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto){
        return trdStockConnect002Service.getAssetAvailablePosition(dto);
    }
	/**
	 * 查询债券cstp交易下拉框
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryRepoCstpInfoList")
	public List<SelectOptionDto> queryRepoCstpInfoList() {
		List<SelectOptionDto> dtos = new ArrayList<>();
		Map<String, Object> param = RequestUtil.getPageParam();
		List<SuggestDto> suggestDtos = trdStockConnect002Service.queryRepoCstpInfoList(param);
		for (SuggestDto dto:suggestDtos) {
			SelectOptionDto optionDto = new SelectOptionDto();
			optionDto.setId(dto.getCode());
			optionDto.setText(dto.getCode());
			optionDto.setObject("N");
			dtos.add(optionDto);
		}
		return dtos;
	}

	@ResponseBody
	@RequestMapping("querySecCstpInfoByNo")
	public TrdStockConnectDto querySecCstpInfoByNo(String fedealno){
		TrdStockConnectDto dto = trdStockConnect002Service.querySecCstpInfoByNo(fedealno);
		return dto;
	}
	
	@ResponseBody
    @RequestMapping("/dealSettleDate")
    public Date dealSettleDate(@RequestBody Map<String, Object> param){
		Date tradeDate = DateUtil.parse((String)param.get("tradeDate"));
		String hkMarket = (String)param.get("hkMarket");
		Date settleDate = dealHalfDay(tradeDate, hkMarket);
        return settleDate;
    }
	
	public Date dealHalfDay(Date tradeDate, String hkMarket){
		Date settleDate = null;
		String result = getHalfDay(tradeDate);
		if(StringUtil.isNotEmpty(result)){
			Date T日 = null;
			if(StringUtil.equals(result, "T")){
				T日 = tradeDate;
				Date settleDate1 = trdStockConnect002Service.queryWorkDay(T日, hkMarket);
				settleDate = trdStockConnect002Service.queryWorkDay(settleDate1, hkMarket);
			}else if(StringUtil.equals(result, "T-1")){
				T日 = DateUtil.addDay(tradeDate, 1);
				Date settleDate1 = trdStockConnect002Service.queryWorkDay(T日, hkMarket);
				settleDate = trdStockConnect002Service.queryWorkDay(settleDate1, hkMarket);
			}else if(StringUtil.equals(result, "T-2")){
				T日 = DateUtil.addDay(tradeDate, 2);
				settleDate = trdStockConnect002Service.queryWorkDay(T日, hkMarket);
			}
		}else{
			Date settleDate1 = trdStockConnect002Service.queryWorkDay(tradeDate, hkMarket);
			settleDate = trdStockConnect002Service.queryWorkDay(StringUtil.isNotEmpty(settleDate1)?settleDate1:tradeDate, hkMarket);
		}
		return settleDate;
	}
	
	public String getHalfDay(Date tradeDate){
		String result = "";
		//若圣诞前夕（12月24日）、元旦前夕（12月31日）和农历新年前一天不是周末时，即为工作日时，则为半日市；
		String year = DateUtil.getYearStr(DateUtil.format(tradeDate));
		//获取春节时间
		
		String dateStr1 = year + "-12-24";
		String dateStr2 = year + "-12-31";
		Date date1 = DateUtil.parse(dateStr1);
		Date date2 = DateUtil.parse(dateStr2);
		Date date3 = DateUtil.addDay(trdStockConnect002Service.getSpringFestivaDate(year), -1);
		int weekDay1 = DateUtil.getWeekDay(date1);
		int weekDay2 = DateUtil.getWeekDay(date2);
		int weekDay3 = DateUtil.getWeekDay(date3);
		
		if(DateUtil.isSameDate(tradeDate, date1) && (weekDay1 != 6 || weekDay1 != 7)){
			result = "T";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date1, -1)) && (weekDay1 != 6 || weekDay1 != 7)){
			result = "T-1";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date1, -2)) && (weekDay1 != 6 || weekDay1 != 7)){
			result = "T-2";
		}else if(DateUtil.isSameDate(tradeDate, date2) && (weekDay2 != 6 || weekDay2 != 7)){
			result = "T";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date2, -1)) && (weekDay2 != 6 || weekDay2 != 7)){
			result = "T-1";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date2, -2)) && (weekDay2 != 6 || weekDay2 != 7)){
			result = "T-2";
		}else if(DateUtil.isSameDate(tradeDate, date3) && (weekDay3 != 6 || weekDay3 != 7)){
			result = "T";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date3, -1)) && (weekDay3 != 6 || weekDay3 != 7)){
			result = "T-1";
		}else if(DateUtil.isSameDate(tradeDate, DateUtil.addDay(date3, -2)) && (weekDay3 != 6 || weekDay3 != 7)){
			result = "T-2";
		}
		return result;
	}
	
	/**
     * 计算费用
     * <功能详细描述>
     * @return
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/calcFeeDetail")
    public Map<String,Object> calcFeeDetail() {
    	Map<String, Object> map = new HashMap<>();
    	Map<String, Object> param = RequestUtil.getPageParam();
    	
    	BigDecimal unitCprice = new BigDecimal((String)param.get("unitCprice"));//交易价格
    	BigDecimal shareAmt = new BigDecimal((String)param.get("shareAmt"));//交易数量
    	String hkMarket = (String)param.get("hkMarket");//交易场所
    	String portfolioId = (String)param.get("portfolioId");//组合代码
    	String ps = (String)param.get("ps");//交易方向
    	BigDecimal buySettleRate = new BigDecimal("0");//买入结算汇兑比率
    	if(StringUtil.isNotEmpty((String)param.get("buySettleRate"))){
    		buySettleRate = new BigDecimal((String)param.get("buySettleRate"));
    	}
    	BigDecimal sellSettleRate = new BigDecimal("0");//卖出结算汇兑比率
    	if(StringUtil.isNotEmpty((String)param.get("sellSettleRate"))){
    		sellSettleRate = new BigDecimal((String)param.get("sellSettleRate"));
    	}
    	BigDecimal commission = new BigDecimal("0");//佣金
    	if(StringUtil.isNotEmpty((String)param.get("commission"))){
    		commission = new BigDecimal((String)param.get("commission"));
    	}
    	
    	//印花税
    	BigDecimal stampDuty = ArithmeticUtil.multiply(unitCprice, shareAmt, new BigDecimal("0.001"));
    	stampDuty = ArithmeticUtil.round(stampDuty, 0, null);
    	if(stampDuty.compareTo(new BigDecimal("1")) < 0){
    		stampDuty = new BigDecimal("1");
    	}
    	map.put("stampDuty", stampDuty);
    	
    	//交易征费
    	BigDecimal transactionLevy = ArithmeticUtil.multiply(unitCprice, shareAmt, new BigDecimal("0.000027"));
    	transactionLevy = ArithmeticUtil.round(transactionLevy, 2, null);
    	map.put("transactionLevy", transactionLevy);
    	
    	//交易费
    	BigDecimal transactionFee = ArithmeticUtil.multiply(unitCprice, shareAmt, new BigDecimal("0.00005"));
    	transactionFee = ArithmeticUtil.round(transactionFee, 2, null);
    	map.put("transactionFee", transactionFee);
    	
    	//交易系统使用费
    	BigDecimal transactionSystemFee = new BigDecimal("0.5");
    	map.put("transactionSystemFee", transactionSystemFee);
    	
    	//股份交收费
    	BigDecimal sharePaymentFee = ArithmeticUtil.multiply(unitCprice, shareAmt, new BigDecimal("0.00002"));
    	sharePaymentFee = ArithmeticUtil.round(sharePaymentFee, 2, null);
    	if(sharePaymentFee.compareTo(new BigDecimal("2")) < 0){
    		sharePaymentFee = new BigDecimal("2");
    	}else if(sharePaymentFee.compareTo(new BigDecimal("100")) > 0) {
    		sharePaymentFee = new BigDecimal("100");
    	}
    	map.put("sharePaymentFee", sharePaymentFee);
    	
    	//成交金额
    	BigDecimal dealAmt = ArithmeticUtil.multiply(unitCprice, shareAmt);
    	map.put("dealAmt", dealAmt);
    	
    	BigDecimal tradeAmt = new BigDecimal("0");
    	//是否随交易支付
    	List<String> list = new ArrayList<String>();
    	list = trdStockConnect002Service.isPayWithTrade(portfolioId, hkMarket, ps);
    	if(StringUtil.isEmptyList(list)){
    		list = trdStockConnect002Service.isPayWithTrade(null, hkMarket, ps);
    	}
    	String isPayWithTrade = "";
    	if(!StringUtil.isEmptyList(list)){
    		isPayWithTrade = list.get(0);
    	}else {
    		isPayWithTrade = "N";
    	}
    	BigDecimal part = ArithmeticUtil.doAdd(stampDuty, transactionLevy, transactionFee, transactionSystemFee, sharePaymentFee);
    	if(StringUtil.equals(isPayWithTrade, "N")){
    		//当交易方向为买入/配股/增发（配售）时为+
    		if(StringUtil.equals(ps, "T01") || StringUtil.equals(ps, "T46") || StringUtil.equals(ps, "T47")){
    			if(sellSettleRate != null){
    				tradeAmt = ArithmeticUtil.multiply(ArithmeticUtil.doAdd(dealAmt, part), sellSettleRate);
    				map.put("tradeAmt", tradeAmt);
    			}
    		}else if(StringUtil.equals(ps, "T02") || StringUtil.equals(ps, "T104")){//交易方向为卖出/卖碎股时为-
    			if(buySettleRate != null){
    				tradeAmt = ArithmeticUtil.multiply(ArithmeticUtil.subtract(dealAmt, part), buySettleRate);
    				map.put("tradeAmt", tradeAmt);
    			}
    		}
    	}else if(StringUtil.equals(isPayWithTrade, "Y")){
    		//当交易方向为买入/配股/增发（配售）时为+
    		if(StringUtil.equals(ps, "T01") || StringUtil.equals(ps, "T46") || StringUtil.equals(ps, "T47")){
    			if(sellSettleRate != null && commission != null){
    				tradeAmt = ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ArithmeticUtil.doAdd(dealAmt, part), sellSettleRate), commission);
    				map.put("tradeAmt", tradeAmt);
    			}
    		}else if(StringUtil.equals(ps, "T02") || StringUtil.equals(ps, "T104")){//交易方向为卖出/卖碎股时为-
    			if(buySettleRate != null && commission != null){
    				tradeAmt = ArithmeticUtil.subtract(ArithmeticUtil.multiply(ArithmeticUtil.subtract(dealAmt, part), buySettleRate), commission);
    				map.put("tradeAmt", tradeAmt);
    			}
    		}
    	}
    	
    	return map;
    }
    
    @ResponseBody
    @RequestMapping("/getSettleRate")
    public Map<String, Object> getSettleRate(@RequestBody Map<String, Object> param){
    	Map<String, Object> result = new HashMap<>();
		String hkMarket = (String)param.get("hkMarket");
		if(StringUtil.equals(hkMarket, DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())){
			param.put("hksPriceType", "HKS01");
			BigDecimal buySettleRate = trdStockConnect002Service.getSettleRate(param);
			result.put("buySettleRate", buySettleRate);
			param.put("hksPriceType", "HKS02");
			BigDecimal sellSettleRate = trdStockConnect002Service.getSettleRate(param);
			result.put("sellSettleRate", sellSettleRate);
		}else if(StringUtil.equals(hkMarket, DictCodeListExt2.是否沪港通或深港通_深港通.getValue())){
			param.put("hksPriceType", "HKS05");
			BigDecimal buySettleRate = trdStockConnect002Service.getSettleRate(param);
			result.put("buySettleRate", buySettleRate);
			param.put("hksPriceType", "HKS06");
			BigDecimal sellSettleRate = trdStockConnect002Service.getSettleRate(param);
			result.put("sellSettleRate", sellSettleRate);
		}
        return result;
    }
	
}
