/*
 * 文件名：FinProductFeePay002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay001Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay002Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay003Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay004Dto;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品费用支付管理-新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月8日
 * @see FinProductFeePay002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductFeePay002")
@RequestMapping("/invest/product/feepay/FinProductFeePay002")
public class FinProductFeePay002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay002Service finProductFeePay002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.登记托管机构);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.是否);
        model.addAttribute("yesFlag", DictCodeList.是否_是.getValue());
        model.addAttribute("noFlag", DictCodeList.是否_否.getValue());
        model.addAttribute("ps", DictCodeList.交易方向_产品付费.getValue());
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

        model.addAttribute("feeType_trustFee", DictCodeList.费用品种类型_托管费.getValue());
        model.addAttribute("feeType_investFee", DictCodeListExt2.费用品种类型_投资顾问费.getValue());
        model.addAttribute("feeType_saleFee", DictCodeList.费用品种类型_销售手续费.getValue());
        model.addAttribute("feeType_fixFee", DictCodeList.费用品种类型_固定管理费.getValue());
        model.addAttribute("feeType_diffMid", DictCodeList.费用品种类型_浮动管理费.getValue());
        model.addAttribute("feeType_tax", DictCodeList.费用品种类型_增值附加税费.getValue());
        model.addAttribute("feeType_settle", DictCodeList.费用品种类型_结算服务费.getValue());
        model.addAttribute("feeType_acctno", DictCodeList.费用品种类型_账户维护费.getValue());
        model.addAttribute("feeType_fdcezs", DictCodeList.费用品种类型_浮动差额中收.getValue());
        //add by yzg 20191218 start
        model.addAttribute("feeType_tradeFee",DictCodeList.费用品种类型_交易手续费.getValue());
        //add by yzg 20191218 end
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        // 平台日期
        model.addAttribute("platDate", DateUtil.getSysDate());
        model.addAttribute("feeChargetype_norule", DictCodeList.收费类型_不规则费用.getValue());
        model.addAttribute("feeChargetype_prov", DictCodeList.收费类型_计提式费用.getValue());

        //增加收
        model.addAttribute("DEAL_MODE_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("IS_NEEDSEND_YES",DictCodeList.是否_是.getValue());
        model.addAttribute("IS_NEEDSEND_YES_NAME",DictCodeList.是否_是.getCodeName());
        
        if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
            ProcessMark pro = finProductFeePay002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductFeePay002Dto entity = (FinProductFeePay002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductFeePay002Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
                model.addAttribute(GlobalConstant.WORKFLOW_ID,
                    this.getParam(GlobalConstant.WORKFLOW_ID));
                if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
                    model.addAttribute("entity_bussTrdTransferOrder",
                        JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
                }
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            FinProductFeePay001Dto finProductFeePay001Dto = BeanUtil.mapToObject(
                RequestUtil.getPageParam(), FinProductFeePay001Dto.class);
            FinProductFeePay002Dto finProductFeePay002Dto = new FinProductFeePay002Dto();
            if(StringUtil.isNotEmpty(finProductFeePay001Dto.getFeeId())){
                finProductFeePay002Dto.setTradeId(finProductFeePay001Dto.getTradeId());
                finProductFeePay002Dto.setPtlId(finProductFeePay001Dto.getPtlId());
                finProductFeePay002Dto.setFeeId(finProductFeePay001Dto.getFeeId());
                finProductFeePay002Dto.setFeeType(finProductFeePay001Dto.getFeeType());
                finProductFeePay002Dto.setCashId(finProductFeePay001Dto.getCashId());
                finProductFeePay002Dto.setCcy(finProductFeePay001Dto.getCcy());
                finProductFeePay002Dto.setIsPerpaid(finProductFeePay001Dto.getIsPerpaid());
                //增加根据组合id查询产品收益模式和处理模式字段
                FinProductFeePay001Dto finProductFeePay001DtoT = finProductFeePay002Service.queryByPtlId(finProductFeePay001Dto.getPtlId());
                if(finProductFeePay001DtoT!=null){
                	finProductFeePay002Dto.setProfitType(finProductFeePay001DtoT.getProfitType());
                	finProductFeePay002Dto.setDealMode(finProductFeePay001DtoT.getDealMode());
                }
            }else{
                //BS19 zhangnian
                finProductFeePay002Dto =finProductFeePay002Service.selectFeeByTradeId(this.getParam("tradeId"));
                finProductFeePay001Dto.setFeeId(finProductFeePay002Dto.getFeeId());
            }
            finProductFeePay002Dto.setChargeType(queryFeeInfoByFeeId(finProductFeePay002Dto.getFeeId()).getChargeType());
            // 根据交易编号查询交易信息
            TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(finProductFeePay001Dto.getTradeId());
            if (trdFinProductDealTotal != null) {
                finProductFeePay002Dto.setStartDate(trdFinProductDealTotal.getVdate());
                finProductFeePay002Dto.setEndDate(trdFinProductDealTotal.getMdate());
                finProductFeePay002Dto.setPayDate(trdFinProductDealTotal.getSettleDate());
                finProductFeePay002Dto.setActPayAmt(trdFinProductDealTotal.getTradeAmt());
                if (!StringUtil.equals(finProductFeePay001Dto.getFeeType(),
                    DictCodeList.费用品种类型_账户维护费.getValue())) {
                    // 查询费用待支付费用
                    finProductFeePay002Dto.setPayAmt(finProductFeePay002Service.queryUnpayPosition(
                        finProductFeePay001Dto.getPtlId(), finProductFeePay001Dto.getFeeType(),
                        DateUtil.format(trdFinProductDealTotal.getMdate())));
                    // 实付 - 理论
                    finProductFeePay002Dto.setAdjustAmt(
                        ArithmeticUtil.subtract(finProductFeePay002Dto.getActPayAmt(),
                            finProductFeePay002Dto.getPayAmt()));
                }

                // 结算服务费、账户维护费
                if (StringUtil.equals(finProductFeePay001Dto.getFeeType(),
                    DictCodeList.费用品种类型_账户维护费.getValue())
                    || StringUtil.equals(finProductFeePay001Dto.getFeeType(),
                        DictCodeList.费用品种类型_结算服务费.getValue())) {
                    List<TrdProductDeal> feeInfoList = finProductFeePay002Service.queryTrdProductDealByTotleTradeId(
                        finProductFeePay001Dto.getTradeId());
                    if (!StringUtil.isEmptyList(feeInfoList)) {
                        finProductFeePay002Dto.setRegTrustOrg(feeInfoList.get(0).getRegistOrg());
                    }
                }
                finProductFeePay002Dto.setRemark(trdFinProductDealTotal.getRemark());
            }
            model.addAttribute(GlobalConstant.ENTITY, finProductFeePay002Dto);
            model.addAttribute("tradeId", this.getParam("tradeId"));
        }
        else {
            FinProductFeePay002Dto finProductFeePay002Dto = new FinProductFeePay002Dto();
            String confirmFlag = this.getParam("confirmFlag");
            if (StringUtil.isNotEmpty(confirmFlag) && StringUtil.equals(confirmFlag, "confirm")) {
                String feeId = this.getParam("feeId");
                //String mdate = this.getParam("mdate");
                
                String ptlId = this.getParam("ptlId");
                String finprodId = this.getParam("finprodId");
                String feeName = this.getParam("feeName");
                Date positionStartDate = DateUtil.parse(this.getParam("positionStartDateStr"));
                Date positionEndDate = DateUtil.parse(this.getParam("positionEndDateStr"));
                Date payDate =  DateUtil.parse(this.getParam("payDateStr"));
                String ccy = this.getParam("ccy");
                String feeType = this.getParam("feeType");
                String finprodMarketId = this.getParam("finprodMarketId");
                String basis = this.getParam("basis");
                //add by yzg 20191227 start
                String cashId = this.getParam("cashId");
                finProductFeePay002Dto.setCashId(cashId);
                //add by yzg 20191227 end
                finProductFeePay002Dto.setPtlId(ptlId);
                finProductFeePay002Dto.setFeeId(feeId);
                finProductFeePay002Dto.setFeeType(feeType);
                finProductFeePay002Dto.setBasis(basis);
                finProductFeePay002Dto.setStartDate(positionStartDate);
                finProductFeePay002Dto.setEndDate(positionEndDate);
                finProductFeePay002Dto.setPayDate(payDate);
                finProductFeePay002Dto.setFinprodId(finprodId);
                finProductFeePay002Dto.setFinprodMarketId(finprodMarketId);
                finProductFeePay002Dto.setFeeName(feeName);
                finProductFeePay002Dto.setCcy(ccy);

                finProductFeePay002Dto.setChargeType(queryFeeInfoByFeeId(finProductFeePay002Dto.getFeeId()).getChargeType());
                //增加根据组合id查询产品收益模式和处理模式字段
                FinProductFeePay001Dto finProductFeePay001DtoT = finProductFeePay002Service.queryByPtlId(finProductFeePay002Dto.getPtlId());
                if(finProductFeePay001DtoT!=null){
                	finProductFeePay002Dto.setProfitType(finProductFeePay001DtoT.getProfitType());
                	finProductFeePay002Dto.setDealMode(finProductFeePay001DtoT.getDealMode());
                }
            }
            finProductFeePay002Dto.setConfirmFlag(confirmFlag);
            model.addAttribute(GlobalConstant.ENTITY, finProductFeePay002Dto);
        }
        if ("004".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
        	model.addAttribute("CUSTOMER_BANK_CODE", CacheUtil.getSysParam("CUSTOMER_BANK_CODE"));
        	model.addAttribute("feePayShow", true);
        }
        return super.init(model);
    }

    /**
     * 查询组合下产品关联的费用
     * 
     * @return 关联的费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList() {
        return finProductFeePay002Service.queryFeeInfoList(RequestUtil.getPageParam());
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

    /**
     * 查询费用上次计提结束日
     * 
     * @param ptlId 组合代码
     * @param feeId 费用代码
     * @return 上次计提结束日
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryLastPayDate")
    public String queryLastPayDate(String ptlId, String feeId,String operateType,String org) {
        return finProductFeePay002Service.queryLastPayDate(ptlId, feeId,operateType,org);
    }

    /**
     * 查询费用明细列表
     * 
     * @return 费用明细列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPayFeeInfoList")
    public List<FinProductFeePay003Dto> queryPayFeeInfoList() {
    	// 待办事项
        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            ProcessMark pro = finProductFeePay002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductFeePay002Dto entity = (FinProductFeePay002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductFeePay002Dto.class);
                if (entity != null && !StringUtil.isEmptyList(entity.getFeeInfoList())) {
                    return entity.getFeeInfoList();
                }
                else if (entity != null && !StringUtil.isEmptyList(entity.getFeelayeredInfoList())){
                	return entity.getFeelayeredInfoList();
                }
            }
            //防止前台提醒 必须返回个list
            return new ArrayList<>();
        }
        // 交易成功
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            return finProductFeePay002Service.queryFeeDetailByTradeId(this.getParam("tradeId"),this.getParam("ptlId"),this.getParam("payDate"));
        }
        // 新建或者待确认
        return finProductFeePay002Service.queryPayFeeInfoList(RequestUtil.getPageParam());
    }

    /**
     * 查询费用待支付费用(一次性费用)
     * 
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @return 待支付费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryUnpayPosition")
    public BigDecimal queryUnpayPosition(String ptlId, String feeType, String cdate) {
        return finProductFeePay002Service.queryUnpayPosition(ptlId, feeType, cdate);
    }
   /**
    * 结算服务费、账户维护费联动带出默认费用
    * @param ptlId
    * @param feeType
    * @param cdate
    * @return
    */
    @ResponseBody
    @RequestMapping("queryPayTradeAmt")
    public BigDecimal  queryPayTradeAmt(String ptlId, String org,String feeType, String cdate) {
        return finProductFeePay002Service.queryPayTradeAmt(ptlId, org, cdate,feeType);
    }
    /**
     * 查询增值税待支付费用(增值税费用)
     * 
     * @param ptlId 组合代码
     * @param cdate 查询日
     * @return 增值税待支付费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryTaxUnpayPosition")
    public List<FinProductFeePay004Dto> queryTaxUnpayPosition(String ptlId, String cdate) {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductFeePay002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductFeePay002Dto entity = (FinProductFeePay002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductFeePay002Dto.class);
                if (entity != null) {
                    return entity.getTaxPositionList();
                }
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            return finProductFeePay002Service.queryTaxDetailByTradeId(this.getParam("tradeId"));
        }
        return finProductFeePay002Service.queryTaxUnpayPosition(ptlId, cdate);
    }

    /**
     * 保存check
     * 
     * @param dto 费用新建信息
     * @param br 验证消息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductFeePay002Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        //如果费用类型是固定管理费或者是浮动管理费.在到期日前应提示
        if (StringUtil.equals(DictCodeList.费用品种类型_浮动管理费.getValue(), dto.getFeeType()) || StringUtil.equals(DictCodeList.费用品种类型_固定管理费.getValue(), dto.getFeeType())) {
            if(dto.getMdate() !=null && DateUtil.compareDate(dto.getMdate(), dto.getPayDate()) > 0){
            	//modify by wm 2019-09-03 修改提示方式 start
            	AjaxResult result2 = new AjaxResult();
            	result2.setData(this.getMessage("FIN_PROD_E_0131"));	
            	return result2;
//                return new AjaxResult(getMessage("FIN_PROD_E_0131"),MessageType.确认消息);
            	//modify by wm 2019-09-03 修改提示方式 end
            }
            
        }
        return this.commonCheck(br, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br 验证信息
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductFeePay002Dto dto, BindingResult br)
        throws Exception {
        ProcessParameter<FinProductFeePay002Dto> processParameter = new ProcessParameter<FinProductFeePay002Dto>();
        WorkFlowParameter<FinProductFeePay002Dto> params = new WorkFlowParameter<FinProductFeePay002Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品费用支付管理);
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
        if (dto.getStartDate() == null) {
            // 设置工作流注解
            params.setComment(
                entity -> this.getMessage("FinProductFeePay002.portFolioId") + "(" + entity.getPtlId()
                          + ")" + this.getMessage("FinProductFeePay002.feeName") + "("
                          + entity.getFeeName() + ")"
                          + this.getMessage("FinProductFeePay002.payDate") + "("
                          + DateUtil.format(entity.getPayDate()) + ")");
        } else {
            // 设置工作流注解
            params.setComment(
                entity -> this.getMessage("FinProductFeePay002.portFolioId") + "(" + entity.getPtlId()
                          + ")" + this.getMessage("FinProductFeePay002.feeName") + "("
                          + entity.getFeeName() + ")"
                          + this.getMessage("FinProductFeePay002.positionStartDate") + "("
                          + DateUtil.format(entity.getStartDate()) + ")"
                          + this.getMessage("FinProductFeePay002.positionEndDate") + "("
                          + DateUtil.format(entity.getEndDate()) + ")");
        }

        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = finProductFeePay002Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
    
    /**
     * 查询组合产品的产品代码以及募集币种
     * 
     * @param ptlId 组合代码
     * @return 募集币种
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryByPtlId")
    public FinProductFeePay001Dto queryByPtlId(String ptlId) {
        return finProductFeePay002Service.queryByPtlId(ptlId);
    }
    
    /**
     * 查询组合剩余现金
     * @param params
     * @return
     */
    @RequestMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> params){
    	return finProductFeePay002Service.getProAvailableCash(params);
    }
    /**
     * 	查询组合起息日
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("queryVdate")
        public String queryVdate(String ptlId) {
            return finProductFeePay002Service.queryVdate(ptlId);
        }
}
