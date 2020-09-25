/*
 * 文件名：InvestFeePay001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay001Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay001Service;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 投资端费用支付管理
 * 
 * @author malinlin
 * @version 1.0 2019年12月7日
 * @see InvestFeePay001Controller
 * @since 1.0
 */
@Controller
@PageId("InvestFeePay001")
@RequestMapping("/invest/portfolio/feepay/InvestFeePay001")
public class InvestFeePay001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay001Service investFeePay001Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay002Service investFeePay002Service;

    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
//        setSelectOptionToModel(model, DictCode.有效状态);
        /*费用类型 guyunfei 20190923 start*/
        setSelectOptionToModel(model, DictCode.投资端费用品种类型);
        /*费用类型 guyunfei 20190923 end*/
        model.addAttribute("statusUndo", DictCodeListExt1.有效状态_流程中.getValue());
        
//        setSelectOptionToModel(model, DictCode.有效状态);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.有效状态, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt1.有效状态_有效.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt1.有效状态_撤销.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt1.有效状态_流程中.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.有效状态.getValue(),
            JsonUtils.objectArray2Json(optionsNew.toArray()));
        
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
        	model.addAttribute("feeType", (String)map.get("feeType"));
        	model.addAttribute("remType", map.get("remType"));
        	String quaryDate = (String)map.get("quaryDate");
        	model.addAttribute("quaryDate", quaryDate+" - "+DateUtil.addDay(quaryDate, Integer.parseInt((String)map.get("days"))));
        }
        return super.init(model);
    }

    /**
     * 查询产品费用支付信息
     * 
     * @return 费用支付信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<InvestFeePay001Dto> selectForPage() {
        return investFeePay001Service.selectForPage(RequestUtil.getPageParam());
    }
    
    /**
     * 证券账户账号联想文本框
     * @param param String
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecAcctId")
    public List<SuggestDto> getSecAcctId(String param)
        throws Exception {
        List<SuggestDto> temp = investFeePay001Service.getSecAcctId(param);
        return temp;
    }
    
    /**
     * 证券账户账号联想文本框
     * @param search String
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecAcctIdS")
    public List<SelectOptionDto> getSecAcctIdS(@RequestParam(required = false) String search)
        throws Exception {
        //Map<String, Object> param1 = RequestUtil.getPageParam();
        List<SuggestDto> temp = investFeePay001Service.getSecAcctId(search);
        List<SelectOptionDto> select = new ArrayList<SelectOptionDto>();
        for(SuggestDto dto : temp){
        	SelectOptionDto sdto = new SelectOptionDto();
        	sdto.setId(dto.getCode());
        	sdto.setText(dto.getShortName());
        	select.add(sdto);
        }
        return select;
    }

    /**
     * check
     * 
     * @param finProductFeePay001Dto check对象
     * @param br 验证信息
     * @return check结果
     * @see
     * @since 1.0
     */
//    @SuppressWarnings("unchecked")
//    @ResponseBody
//    @RequestMapping("checkConfirm")
//    public AjaxResult checkConfirm(@Valid @RequestBody List<InvestFeePay001Dto> finProductFeePay001Dto, BindingResult br) {
//    	AjaxResult result = new AjaxResult();
//        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
//    	for(InvestFeePay001Dto dto : finProductFeePay001Dto) {
//    		if(!StringUtil.equals(dto.getEffectStatusStr(),
//    	            DictCodeListExt1.有效状态_待处理.getCodeName())) {
//    				errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
//    	                   DictCodeListExt1.有效状态_待处理.getCodeName())));
//    		}
//    		//判断方案是否在流程中
//            ProcessMark processMarkb = new ProcessMark();
//            processMarkb.setPageId("FinProductFeePay004");
//            processMarkb.setBusinessDataPk1(dto.getFeeId());
//            processMarkb.setBusinessDataPk2(dto.getFinprodId());
//            processMarkb.setBusinessDataPk3(dto.getFeeType());
//            List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
//            if (!StringUtil.isEmptyList(resultListb)) {
//                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getFeeId(),dto.getFinprodId())));
//            }
//    	}	
//    	List<String> feeId = finProductFeePay001Dto.stream().
//    			map(InvestFeePay001Dto::getFeeId).distinct().collect(Collectors.toList());
//    	if(feeId.size()>1) {
//            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0095")));
//    	}
//    	List<String> feeAccount = finProductFeePay001Dto.stream().
//    			map(InvestFeePay001Dto::getFeeAccount).distinct().collect(Collectors.toList());
//    	if(feeAccount.size()>1) {
//            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0098")));
//    	}
//    	List<String> manageAcctount = finProductFeePay001Dto.stream().
//    			map(InvestFeePay001Dto::getManageAcctount).distinct().collect(Collectors.toList());
//    	if(manageAcctount.size()>1) {
//            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0097")));
//    	}
//    	
//        if (StringUtil.isEmptyList(errorList)) {
//            String btnName = "";
//            result.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
//        }
//        else {
//            result.setErrorList(errorList);
//        }
//      
//        if (br != null && br.hasErrors()) {
//            return new AjaxResult(br);
//        }
//        
//        return result;
//    }
    
    /**
     * check
     * 
     * @param investFeePay001Dto check对象
     * @param br 验证信息
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody InvestFeePay001Dto investFeePay001Dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        return new AjaxResult();
    }

    /**
     * 撤销
     * 
     * @param inputDto 画面输入参数dto
     * @param finProductFeePay001Dto 
     * @return 撤销结果
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody InvestFeePay002Dto inputDto, TrdProductDeal finProductFeePay001Dto) throws Exception {

        ProcessParameter<InvestFeePay002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<InvestFeePay002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("InvestFeePay002");
        processParameter.setOperationType(OperationType.REVOKE);
        // 根据交易编号查询交易信息
        TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(inputDto.getTradeId());
        if (trdFinProductDealTotal != null) {
            inputDto.setStartDate(trdFinProductDealTotal.getVdate());
            inputDto.setEndDate(trdFinProductDealTotal.getMdate());
            inputDto.setPayDate(trdFinProductDealTotal.getSettleDate());
            inputDto.setActPayAmt(trdFinProductDealTotal.getTradeAmt());
            inputDto.setFeeInfoList(investFeePay002Service.queryFeeDetailByTradeId(
                inputDto.getTradeId(), DateUtil.format(inputDto.getPayDate())));
            // 结算服务费、账户维护费
            List<TrdProductDeal> feeInfoList = investFeePay002Service.queryTrdProductDealByTotleTradeId(
                inputDto.getTradeId());
            if (!StringUtil.isEmptyList(feeInfoList)) {
                inputDto.setRegTrustOrg(feeInfoList.get(0).getRegistOrg());
            }
            if (!StringUtil.equals(inputDto.getFeeType(),
                DictCodeList.费用品种类型_账户维护费.getValue())) {
                // 查询费用待支付费用
                inputDto.setPayAmt(investFeePay002Service.queryUnpayPosition(
                    inputDto.getSecAcctId(), inputDto.getFeeType(),
                    DateUtil.format(trdFinProductDealTotal.getMdate()), inputDto.getRegTrustOrg()));
                // 实付 - 理论
                inputDto.setAdjustAmt(
                    ArithmeticUtil.subtract(inputDto.getActPayAmt(),
                        inputDto.getPayAmt()));
            }

            
            inputDto.setRemark(trdFinProductDealTotal.getRemark());
        }
        workFlowParameter.setPageId("InvestFeePay002");
        workFlowParameter.setElementId(inputDto.getElementId());
        workFlowParameter.setBusinessCode((entity)->entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("InvestFeePay001.table.tradeId") + GlobalConstant.OPEN_PARENTHESIS
                                                                            + entity.getTradeId()
                                                                            + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage("InvestFeePay001.table.secAcctId" ) + GlobalConstant.OPEN_PARENTHESIS
                                                                            + entity.getSecAcctId()
                                                                            + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage("InvestFeePay001.table.payDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(
                    entity.getPayDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = investFeePay002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return investFeePay001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoByFeeId")
    public MstFeeinfo queryFeeInfoByFeeId(String feeId) {
        return mstFeeinfoMapper.selectByPrimaryKey(feeId);
    }
}
