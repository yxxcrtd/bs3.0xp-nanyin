/*
 * 文件名：TrdStock002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.stockdeal.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.apache.commons.codec.binary.StringUtils;
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
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
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
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.tams.invest.trade.stockdeal.service.TrdStock002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 股票交易新建
 *
 * @author wangpeng
 * @version 1.0 2019年7月29日
 * @see TrdStock002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdStock002")
@RequestMapping("/invest/trade/stockdeal/TrdStock002")
public class TrdStock002Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
	@Autowired
	private TrdStock002Service trdStock002Service;
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

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.上市板, DictCode.是否有锁定期, DictCode.交易方向
        		, DictCode.外汇交易模式, DictCode.币种, DictCode.交易场所);
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
        TrdStockDto dto = new TrdStockDto();
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
//        String validStatus = this.getParam("validStatus");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdStock002Service.getBussinessEntityByWorkflowId(
                workflowId);
			dto.setPortfolioId(dto.getActPortfolioId());
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("workflowId",workflowId);
        }
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdStock002Service.getTrdProductDealDto(tradeId);
        }
        else  if (StringUtil.isNotEmpty(entrustId)){
        	TrdStockDto etrdto = trdStock002Service.getEntrustInfo(entrustId);
            BeanUtil.copyPropertiesIgnoreNull(dto, etrdto);
			if(StringUtil.isNotBlank(dto.getRemark())&&StringUtil.contains(dto.getRemark(),"退回原因")){
				dto.setRemark("");
			}
        	if (StringUtil.isNotEmpty(etrdto.getPrice())) {
                dto.setUnitCprice(etrdto.getPrice());
            }
        	/*if (StringUtil.isNotEmpty(etrdto.getEntrustAmt())) {
        		if(StringUtil.isNotEmpty(etrdto.getFinishAmt())){
        			dto.setShareAmt(ArithmeticUtil.subtract(etrdto.getEntrustAmt(), etrdto.getFinishAmt()));
        		}else{
        			dto.setShareAmt(etrdto.getEntrustAmt());
        		}
            }*/
        	if (StringUtil.isNotEmpty(etrdto.getEntrustNum())) {
        		if(StringUtil.isNotEmpty(etrdto.getFinishAmt())){
        			dto.setShareAmt(ArithmeticUtil.subtract(etrdto.getEntrustNum(), etrdto.getFinishAmt()));
        		}else{
        			dto.setShareAmt(etrdto.getEntrustNum());
        		}
            }
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
 
		PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
		pageElementConfigDto.setPageId("BaseRiskMathComm");
		Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
		pageElementConfigDto.setPageId(this.getPageId());
		Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
		isEleShow.putAll(map);
		model.addAttribute("isEleShow", isEleShow);
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
		return trdStock002Service.getSecManageAcctId(params);
	}

	/**
	 * 获取持仓、可用现金等
	 * 
	 * @return 金额集合
	 * @see
	 * @since 1.0
	 */
	/*@ResponseBody
	@RequestMapping("available")
	public List<SuggestDto> available() {
		Map<String, Object> map = RequestUtil.getPageParam();
		List<SuggestDto> list = trdStock002Service.available(map);
		return list;
	}*/

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
		return trdStock002Service.getFinprodId(params);
	}

	/**
	 * 查询委托信息
	 *
	 * @param entrustId
	 *            委托编号
	 * @return 委托信息
	 * @see
	 * @since 1.0
	 */
	/*@ResponseBody
	@RequestMapping("getEntrustInfo")
	public BaseEtrInvestInfoDto getEntrustInfo(String entrustId) {
		return trdStock002Service.getEntrustInfo(entrustId);
	}*/

	/**
	 * 获取下一个工作日
	 *
	 * @param params
	 *            画面参数
	 * @return 计算交割日
	 * @see
	 * @since 1.0
	 */
	/*@RequestMapping("getNextWorkDay")
	@ResponseBody
	public String getNextWorkDay(@RequestParam Map<String, Object> params) {
		return trdStock002Service.getNextWorkDay(params);
	}*/

	/**
	 * 1.7计算按钮点击事件
	 *
	 * @param inputDto
	 *            画面参数
	 * @return 计算结果
	 * @see
	 * @since 1.0
	 */
	/*@RequestMapping("calculateData")
	@ResponseBody
	public AjaxResult calculateData(@RequestBody TrdProductDealDto inputDto) {
		return trdStock002Service.calculateData(inputDto);
	}*/

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
	public AjaxResult check(@Validated({ ValidateGroupEdit.class }) @RequestBody TrdStockDto dto,
			BindingResult result) {
		//有check错误直接返回
		if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
		// huwenbin 指令接收交易录单验证数据已存在流程中
		// add by zhujun @2019-05-16 投资委托交易退回时，此check阻塞修改保存操作
		/*if (StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()) {
    		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
    		if(StringUtil.isNotEmpty(str)){
    			AjaxResult res= new AjaxResult();
				res.setMessage(str, MessageType.错误消息);
    			return res;
    		}
        	// 查询交易流程中录入的总金额	王贵奇	2019/9/3
        	BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
            BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
            if(entrustNum.compareTo(wflPrinAmt.add(dto.getShareAmt())) == -1) {
            	AjaxResult res= new AjaxResult();
            	 List<ValidMessage> errorList = new ArrayList<>();
            	 ValidMessage validMessage = new ValidMessage("shareAmt",
            			 "可用委托数量不足,当前最多可委托数量为:"+ entrustNum.subtract(wflPrinAmt));
                     errorList.add(validMessage);
                res.setErrorList(errorList);
                res.setMessageType(MessageType.错误消息);
                return res;
            }
		}*/
		// 确认提醒
//		AjaxResult ajaxResult = trdStock002Service.checkConfirmInfo(dto, result);
//		if (ajaxResult != null) {
//			return ajaxResult;
//		}
		
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
	public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdStockDto dto, BindingResult br) throws Exception {
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
		}
		//----------add by jjpei 20200114 核风险版本 end----------
		ProcessParameter<TrdStockDto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<TrdStockDto> workFlowParameter = new WorkFlowParameter<>();
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
		workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_股票投资交易.getValue());
		workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
				getMessage("TrdStock002.tradeId") + GlobalConstant.COLON + entity.getTradeId(),
				getMessage("TrdStock002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
				getMessage("TrdStock002.tradeDate") + GlobalConstant.COLON
						+ DateUtil.format(entity.getTradeDate())));

		String msg = trdStock002Service.start(processParameter,
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
        return trdStock002Service.getTongDaoCash(map);
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
        return trdStock002Service.getProAvailableCash(param);
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
        return trdStock002Service.getAssetAvailablePosition(dto);
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
		List<SuggestDto> suggestDtos = trdStock002Service.queryRepoCstpInfoList(param);
		for (SuggestDto dto:suggestDtos) {
			SelectOptionDto optionDto = new SelectOptionDto();
			optionDto.setId(dto.getCode());
			optionDto.setText(dto.getCode());
			optionDto.setObject("N");
			dtos.add(optionDto);
		}
		return dtos;
	}


//	/**
//	 * 查询债券cstp交易下拉框
//	 * @param
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("queryRepoCstpInfoList")
//	public List<SuggestDto> queryRepoCstpInfoList() {
//		Map<String, Object> param = RequestUtil.getPageParam();
//		List<SuggestDto> suggestDtos = trdStock002Service.queryRepoCstpInfoList(param);
//		return suggestDtos;
//	}

	@ResponseBody
	@RequestMapping("querySecCstpInfoByNo")
	public TrdStockDto querySecCstpInfoByNo(String fedealno){
		TrdStockDto dto = trdStock002Service.querySecCstpInfoByNo(fedealno);
		return dto;
	}
}
