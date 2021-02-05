/*
 * 文件名：FinProductFeePay001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
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
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay001Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay002Dto;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay001Service;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品费用支付管理
 * 
 * @author zhangliang
 * @version 1.0 2018年11月7日
 * @see FinProductFeePay001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductFeePay001")
@RequestMapping("/invest/product/feepay/FinProductFeePay001")
public class FinProductFeePay001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay001Service finProductFeePay001Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay002Service finProductFeePay002Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;

    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;

    /**
     * 交易基础组件调用Service
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
//        setSelectOptionToModel(model, DictCode.有效状态);
        /*费用类型 guyunfei 20190923 start*/
        setSelectOptionToModel(model, DictCode.币种,DictCode.费用品种类型);
        /*费用类型 guyunfei 20190923 end*/
        model.addAttribute("statusUndo", DictCodeListExt1.有效状态_待处理.getValue());
        
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.有效状态, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt1.有效状态_待处理.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt1.有效状态_有效.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.有效状态.getValue(),
            JsonUtils.objectArray2Json(optionsNew.toArray()));
        
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
        	model.addAttribute("finprodId", (String)map.get("finprodId"));
        	model.addAttribute("feeId", (String)map.get("feeId"));
        	model.addAttribute("remType", map.get("remType"));
        	String quaryDate = (String)map.get("mdate");
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
    public Page<FinProductFeePay001Dto> selectForPage() {
        return finProductFeePay001Service.selectForPage(RequestUtil.getPageParam());
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
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("checkConfirm")
    public AjaxResult checkConfirm(@Valid @RequestBody List<FinProductFeePay001Dto> finProductFeePay001Dto, BindingResult br) {
    	AjaxResult result = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
    	for(FinProductFeePay001Dto dto : finProductFeePay001Dto) {
    		if(!StringUtil.equals(dto.getEffectStatusStr(),
    	            DictCodeListExt1.有效状态_待处理.getCodeName())) {
    				errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
    	                   DictCodeListExt1.有效状态_待处理.getCodeName())));
    		}
    		//判断方案是否在流程中
            ProcessMark processMarkb = new ProcessMark();
            processMarkb.setPageId("FinProductFeePay004");
            processMarkb.setBusinessDataPk1(dto.getFeeId());
            processMarkb.setBusinessDataPk2(dto.getFinprodId());
            processMarkb.setBusinessDataPk3(dto.getFeeType());
            List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
            if (!StringUtil.isEmptyList(resultListb)) {
                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getFeeId(),dto.getFinprodId())));
            }
    	}	
    	List<String> feeId = finProductFeePay001Dto.stream().
    			map(FinProductFeePay001Dto::getFeeId).distinct().collect(Collectors.toList());
    	if(feeId.size()>1) {
            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0095")));
    	}
    	List<String> feeAccount = finProductFeePay001Dto.stream().
    			map(FinProductFeePay001Dto::getFeeAccount).distinct().collect(Collectors.toList());
    	if(feeAccount.size()>1) {
            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0098")));
    	}
    	List<String> manageAcctount = finProductFeePay001Dto.stream().
    			map(FinProductFeePay001Dto::getManageAcctount).distinct().collect(Collectors.toList());
    	if(manageAcctount.size()>1) {
            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0097")));
    	}
    	
        if (StringUtil.isEmptyList(errorList)) {
            String btnName = "";
            result.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        else {
            result.setErrorList(errorList);
        }
      
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        return result;
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
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductFeePay001Dto finProductFeePay001Dto, BindingResult br) {
        if (StringUtil.equals(finProductFeePay001Dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(finProductFeePay001Dto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
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
    AjaxResult doRevoke(@RequestBody FinProductFeePay002Dto inputDto, TrdProductDeal finProductFeePay001Dto) throws Exception {

        ProcessParameter<FinProductFeePay002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductFeePay002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("FinProductFeePay002");
        processParameter.setOperationType(OperationType.REVOKE);
        //增加根据组合id查询产品收益模式和处理模式字段
        FinProductFeePay001Dto finProductFeePay001DtoT = finProductFeePay002Service.queryByPtlId(inputDto.getPtlId());
        if(StringUtil.isNotEmpty(inputDto.getFeeId())){
            if(inputDto!=null){
                inputDto.setProfitType(finProductFeePay001DtoT.getProfitType());
                inputDto.setDealMode(finProductFeePay001DtoT.getDealMode());
            }
        }else{
            //BS19 zhangnian
            inputDto =finProductFeePay002Service.selectFeeByTradeId(inputDto.getTradeId());
        }
        inputDto.setChargeType(queryFeeInfoByFeeId(inputDto.getFeeId()).getChargeType());
        // 根据交易编号查询交易信息
        TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(inputDto.getTradeId());
        if (trdFinProductDealTotal != null) {
            inputDto.setStartDate(trdFinProductDealTotal.getVdate());
            inputDto.setEndDate(trdFinProductDealTotal.getMdate());
            inputDto.setPayDate(trdFinProductDealTotal.getSettleDate());
            inputDto.setActPayAmt(trdFinProductDealTotal.getTradeAmt());
            inputDto.setFeeInfoList(finProductFeePay002Service.queryFeeDetailByTradeId(
                inputDto.getTradeId(),inputDto.getPtlId(),DateUtil.format(inputDto.getPayDate())));
            if (!StringUtil.equals(inputDto.getFeeType(),
                DictCodeList.费用品种类型_账户维护费.getValue())) {
                // 查询费用待支付费用
                inputDto.setPayAmt(finProductFeePay002Service.queryUnpayPosition(
                    inputDto.getPtlId(), inputDto.getFeeType(),
                    DateUtil.format(trdFinProductDealTotal.getMdate())));
                // 实付 - 理论
                inputDto.setAdjustAmt(
                    ArithmeticUtil.subtract(inputDto.getActPayAmt(),
                        inputDto.getPayAmt()));
            }

            // 结算服务费、账户维护费
            if (StringUtil.equals(inputDto.getFeeType(),
                DictCodeList.费用品种类型_账户维护费.getValue())
                || StringUtil.equals(inputDto.getFeeType(),
                    DictCodeList.费用品种类型_结算服务费.getValue())) {
                List<TrdProductDeal> feeInfoList = finProductFeePay002Service.queryTrdProductDealByTotleTradeId(
                    inputDto.getTradeId());
                if (!StringUtil.isEmptyList(feeInfoList)) {
                    inputDto.setRegTrustOrg(feeInfoList.get(0).getRegistOrg());
                }
            }
            inputDto.setRemark(trdFinProductDealTotal.getRemark());
        }
        workFlowParameter.setPageId("FinProductFeePay002");
        workFlowParameter.setElementId(inputDto.getElementId());
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("FinProductFeePay002.tradeId") + GlobalConstant.OPEN_PARENTHESIS
                                                                            + entity.getTradeId()
                                                                            + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage("FinProductFeePay001.table.portFolioId" ) + GlobalConstant.OPEN_PARENTHESIS
                                                                                                                + entity.getPtlId()
                                                                                                                + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage(
                "FinProductFeePay001.table.payDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(
                    entity.getPayDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = finProductFeePay002Service.start(processParameter, inputDto);

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
        return finProductFeePay001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfoIssueSuc")
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc() {
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        return productCommonService.queryProdIdInfoIssueSuc(map);
    }
    
    /**
     * 费用账户联想下拉框
     * 
     * @return 费用账户列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeAccountList")
    public Page<SelectOptionDto> queryFeeAccountList() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return productCommonService.queryFeeAccountList(map);
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

    @ResponseBody
    @RequestMapping("/checkHasAfterTrade")
    public boolean checkHasAfterTrade(@RequestBody FinProductFeePay001Dto dto){
//        String finprodId = StringUtil.isNotEmpty(dto.getpFinprodId())?dto.getpFinprodId():dto.getFinprodId();//支持分层
//        FinProduct finProduct = finProductFeePay001Service.getFinProduct(finprodId);
//        boolean flag = finProductFeePay001Service.isDealCalendarHoliday(dto.getfAccrDate(),finProduct.getCalendarId());
//        String msg = null;
//        if(!flag){
//            msg = getMessage("BUSS_COM_E_0039",getMessage("FinFloatMgtExpQuotation002.fAccrDate")+dto.getfAccrDate());
//        }

        Map<String,Object> param = new HashMap<>();
        param.put("tradeId",dto.getTradeId());
        param.put("feeId",dto.getFeeId());
        param.put("mDate",dto.getMdateStr());
        param.put("payDate",dto.getPayDateStr());
        int result = finProductFeePay001Service.checkHasAfterTrade(param);
        if(result > 0 ){
            return true;
        }
        return false;
    }
}
