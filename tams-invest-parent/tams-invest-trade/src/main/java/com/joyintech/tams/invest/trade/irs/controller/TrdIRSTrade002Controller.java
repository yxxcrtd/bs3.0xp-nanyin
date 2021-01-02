/*
 * 文件名：TrdIRSTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.irs.dto.TrdIRSTradeDto;
import com.joyintech.tams.invest.trade.irs.service.TrdIRSTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换交易新建
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月10日
 * @see TrdIRSTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdIRSTrade002")
@RequestMapping("/invest/trade/irs/TrdIRSTrade002")
public class TrdIRSTrade002Controller extends BaseController{
	
	@Autowired
	private TrdIRSTrade002Service trdIRSTrade002Service;
	
	@Autowired
	private TradeCommonService tradeCommonService;
	
	@Autowired
	private BaseGetBusinessId bussGetBussinessIdService;
	
	@Autowired
	private DateCalService dateCalService;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;
    
	/**
	 * 初始化界面
	 * {@inheritDoc}
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,  DictCode.互换方式,DictCode.利率行情,DictCode.期限品种_利率互换,
        		DictCode.计息基础,DictCode.币种, DictCode.持有目的);
        //营业日准则 （计息营业日准则，付息营业日准则 下拉框不能直接取值营业日准则，需要特殊处理）
        this.setSelectOptionToModelEx(DictCode.营业日准则, model);
        // 付息频率 （重置频率 枚举值没有到期一次 需要特殊处理）
        this.setSelectOptionToModelEx(DictCode.频率, model);
        
        model.addAttribute("acctTypeA01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("acctTypeA05", DictCodeListExt1.资金账户类型_交易对手账户.getValue());
        //----add by jjpei 20190718--------交易监控风险试算-----------start--------
        model.addAttribute("FINPROD_TYPE_F14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //----add by jjpei 20190718--------交易监控风险试算-----------end--------
        TrdIRSTradeDto dto = new TrdIRSTradeDto();
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
                dto = trdIRSTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
                model.addAttribute("workflowId",workflowId);
                model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
//                if (StringUtil.isBlank(dto.getTradeId())) {
//                    // 根据工作流代码查询交易编号
//                    dto.setTradeId(trdForwexInvestTrade002Service.selectTradeIdByWorkflowId(workflowId));
//                }
            }
        
        
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdIRSTrade002Service.getIRSTradeDto(tradeId);
        }
        else if (StringUtil.isNotEmpty(entrustId)) {
            dto = trdIRSTrade002Service.getDealInfoByEntrustId(entrustId);
            dto.setEntrustId(entrustId);
        }

        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        }
        else {
            model.addAttribute("tradeId", dto.getTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }

        model.addAttribute("businessId", dto.getTradeId());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        dto.setFinprodType(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("PINAN_ASSET_TYPE_F16",DictCodeListExt2.平安资产类型_利率互换.getValue());
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
	 * 利率互换  两个控件 公用一个参数  但是下拉枚举值不同 需要特殊处理
	 * <功能详细描述>
	 * @param dictCode
	 * @param model
	 * @see
	 * @since 1.0
	 */
	public void setSelectOptionToModelEx(IDictCode dictCode, Model model) {
        SelectDto selectDto = this.getSelectDataFromDataDic(dictCode, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            if (dictCode.equals(BaseDictCode.数据状态)) {
                for (int index = options.size() - 1; index >= 0; index-- ) {
                    SelectOptionDto option = options.get(index);
                    if (StringUtil.equals(option.getId(), BaseDictCodeList.数据状态_新建.getValue())
                        || StringUtil.equals(option.getId(),
                            BaseDictCodeList.数据状态_删除中.getValue())|| StringUtil.equals(option.getId(),
                                BaseDictCodeList.数据状态_修改中.getValue())) {
                        options.remove(option);
                    }
                }
            }
            
            //营业日准则
            if(StringUtil.equals(DictCode.营业日准则.getValue(),dictCode.getValue())){
	            // 计息营业日准则
	            List<SelectOptionDto> fixOptions = new ArrayList<SelectOptionDto>();
	            for(SelectOptionDto selectOptionDto:options) {
	            	if(StringUtil.equals(selectOptionDto.getId(),DictCodeListExt2.营业日准则_按实际天数调整.getValue())
	            			||StringUtil.equals(selectOptionDto.getId(),DictCodeList.营业日准则_不调整.getValue())) {
	            		fixOptions.add(selectOptionDto);
	            	}
	            }
	            model.addAttribute(dictCode.getValue() + "_END", JsonUtils.objectArray2Json(fixOptions.toArray()));
	            model.addAttribute(dictCode.getValue() + "_default_END", DictCodeListExt2.营业日准则_按实际天数调整.getValue());
	            
	            // 付息营业日准则
	            List<SelectOptionDto> floatOptions = new ArrayList<SelectOptionDto>();
	            for(SelectOptionDto selectOptionDto:options) {
	            	if(StringUtil.equals(selectOptionDto.getId(),DictCodeList.营业日准则_向后调整.getValue())
	            			||StringUtil.equals(selectOptionDto.getId(),DictCodeList.营业日准则_向前调整.getValue())
	            			||StringUtil.equals(selectOptionDto.getId(),DictCodeList.营业日准则_调整的向后调整.getValue())
	            			||StringUtil.equals(selectOptionDto.getId(),DictCodeList.营业日准则_不调整.getValue())) {
	            		floatOptions.add(selectOptionDto);
	            	}
	            }
	            model.addAttribute(dictCode.getValue() + "_PAY", JsonUtils.objectArray2Json(floatOptions.toArray()));
	            model.addAttribute(dictCode.getValue() + "_default_PAY", DictCodeList.营业日准则_向后调整.getValue());
            }
            
            //频率
            if(StringUtil.equals(DictCode.频率.getValue(),dictCode.getValue())){
	            // 付息频率
	            List<SelectOptionDto> fixOptions = new ArrayList<SelectOptionDto>();
	            fixOptions.addAll(options);
	            model.addAttribute(dictCode.getValue(), JsonUtils.objectArray2Json(fixOptions.toArray()));
	            
	            // 付息营业日准则
	            List<SelectOptionDto> floatOptions = new ArrayList<SelectOptionDto>();
	            floatOptions.addAll(options);
	            for(int index=0;index<floatOptions.size();index++) {
	            	SelectOptionDto selectOptionDto = floatOptions.get(index);
	            	if(StringUtil.equals(selectOptionDto.getId(),DictCodeList.频率_到期一次.getValue())) {
	            		floatOptions.remove(index);
	            	}
	            }
	            model.addAttribute(dictCode.getValue() + "_RESET", JsonUtils.objectArray2Json(floatOptions.toArray()));
            }
        }
    }
	
	
	
	/**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdIRSTradeDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        
        // 节假日判断
        // 起息日
        if(dateCalService.isDealCalendarHoliday(dto.getVdate(), dto.getCalendarId())){
	            AjaxResult ar = new AjaxResult();
                ar.setData(
                    getMessage("BUSS_COM_E_0039",
                        getMessage("TrdIRSTrade.vdate")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
        }
        // 到期日
        if(dateCalService.isDealCalendarHoliday(dto.getMdate(), dto.getCalendarId())){
	            AjaxResult ar = new AjaxResult();
                ar.setData(
                    getMessage("BUSS_COM_E_0039",
                        getMessage("TrdIRSTrade.mdate")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
        }
        
        // 交易日
        if(dateCalService.isDealCalendarHoliday(dto.getTradeDate(), dto.getCalendarId())){
	            AjaxResult ar = new AjaxResult();
                ar.setData(
                    getMessage("BUSS_COM_E_0039",
                        getMessage("TrdIRSTrade.tradeDate")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
        }
        
        
        //huwenbin 指令接收交易录单验证数据已存在流程中
        /*if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
    		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
    		if(StringUtil.isNotEmpty(str)){
    			AjaxResult res= new AjaxResult();
				res.setMessage(str, MessageType.错误消息);
    			return res;
    		}
        	// 查询交易流程中录入的总金额	王贵奇	2019/9/3
        	BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
            BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
            if(entrustNum.compareTo(wflPrinAmt.add(dto.getFpriceAmt())) == -1) {
            	AjaxResult res= new AjaxResult();
                res.setMessage("可用委托金额不足："+ entrustNum.subtract(wflPrinAmt), MessageType.错误消息);
                return res;
            }
    	}*/
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdIRSTradeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        
        
        ProcessParameter<TrdIRSTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSTradeDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam("elementId"));
        if (StringUtil.isEmpty(dto.getTradeId())) {
	        BussinessCodeParameter param = new BussinessCodeParameter();
	        param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_利率互换交易));
	        String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
	        dto.setTradeId(code);
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeListExt2.平安资产类型_利率互换.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("交易编号：" + dto.getTradeId(),
        		"互换方式：" + dto.getIrsTypeStr(),
            "交易日：" + entity.getTradeDateStr()));
        processParameter.setWorkFlowParameter(params);
        String msg = trdIRSTrade002Service.start(processParameter, dto);
        
        //授信占用额度
//        trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),dto.getTradeId());
        return new AjaxResult(msg);
    }
    
    
    
    /**
     * 计算结算费用
     * 
     * @return 交易结算费用金额
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("calFeeAmtB07")
    public BigDecimal calFeeAmtB07(@RequestBody TrdIRSTradeDto dto) {
        return trdIRSTrade002Service.calFeeAmtB07(dto);
    }
    
    
}
