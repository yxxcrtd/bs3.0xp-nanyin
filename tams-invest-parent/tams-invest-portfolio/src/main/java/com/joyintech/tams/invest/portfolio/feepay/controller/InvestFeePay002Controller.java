/*
 * 文件名：InvestFeePay002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotalKey;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay001Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay003Dto;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 投资端费用支付管理-新建
 * 
 * @author malinlin
 * @version 1.0 2019年12月8日
 * @see InvestFeePay002Controller
 * @since 1.0
 */
@Controller
@PageId("InvestFeePay002")
@RequestMapping("/invest/portfolio/feepay/InvestFeePay002")
public class InvestFeePay002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay002Service investFeePay002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
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
        this.setSelectOptionToModel(model, DictCode.投资端费用品种类型);
        
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.登记托管机构, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt2.登记备案机构_中央国债登记结算有限责任公司.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt2.登记备案机构_上海清算所.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt2.登记备案机构_中国证券登记结算有限责任公司.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.登记托管机构.getValue(),
            JsonUtils.objectArray2Json(optionsNew.toArray()));
        
        
        model.addAttribute("yesFlag", DictCodeList.是否_是.getValue());
        model.addAttribute("ps", DictCodeList.交易方向_产品付费.getValue());

        model.addAttribute("feeType_settle", DictCodeList.费用品种类型_结算服务费.getValue());
        model.addAttribute("feeType_acctno", DictCodeList.费用品种类型_账户维护费.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        // 平台日期
        model.addAttribute("platDate", DateUtil.getSysDate());

        if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
            ProcessMark pro = investFeePay002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                InvestFeePay002Dto entity = (InvestFeePay002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), InvestFeePay002Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
                model.addAttribute(GlobalConstant.WORKFLOW_ID,
                    this.getParam(GlobalConstant.WORKFLOW_ID));
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            InvestFeePay001Dto finProductFeePay001Dto = BeanUtil.mapToObject(
                RequestUtil.getPageParam(), InvestFeePay001Dto.class);
            InvestFeePay002Dto finProductFeePay002Dto = new InvestFeePay002Dto();
            if(StringUtil.isNotEmpty(finProductFeePay001Dto.getFeeType())){
                finProductFeePay002Dto.setTradeId(finProductFeePay001Dto.getTradeId());
                finProductFeePay002Dto.setSecAcctId(finProductFeePay001Dto.getSecAcctId());
                finProductFeePay002Dto.setFeeId(finProductFeePay001Dto.getFeeId());
                finProductFeePay002Dto.setFeeType(finProductFeePay001Dto.getFeeType());
                finProductFeePay002Dto.setCashId(finProductFeePay001Dto.getCashId());
                finProductFeePay002Dto.setCcy(finProductFeePay001Dto.getCcy());
            }
            // 根据交易编号查询交易信息
            if(this.getParam("effectStatus").equals(DictCodeListExt1.有效状态_流程中.getValue())) {
                WflTrdFinProductDealTotalKey key = new WflTrdFinProductDealTotalKey();
                key.setTotleTradeId(finProductFeePay001Dto.getTradeId());
                key.setWorkflowStatus(DictCodeListExt1.有效状态_有效.getValue());
                WflTrdFinProductDealTotal trdFinProductDealTotal = wflTrdFinProductDealTotalMapper.selectByPrimaryKey(key);
                if (trdFinProductDealTotal != null) {
                    model.addAttribute("workflowId", trdFinProductDealTotal.getWorkflowId());
                    finProductFeePay002Dto.setStartDate(trdFinProductDealTotal.getVdate());
                    finProductFeePay002Dto.setEndDate(trdFinProductDealTotal.getMdate());
                    finProductFeePay002Dto.setPayDate(trdFinProductDealTotal.getSettleDate());
                    finProductFeePay002Dto.setActPayAmt(trdFinProductDealTotal.getTradeAmt());
                    finProductFeePay002Dto.setRegTrustOrg(investFeePay002Service.querySecAcctOrg(finProductFeePay001Dto.getSecAcctId()));
                    
//                    if (!StringUtil.equals(finProductFeePay001Dto.getFeeType(),
//                        DictCodeList.费用品种类型_账户维护费.getValue())) {
                        // 查询费用待支付费用
                        finProductFeePay002Dto.setPayAmt(new BigDecimal(this.getParam("feeAmt")));
                        // 实付 - 理论
                        finProductFeePay002Dto.setAdjustAmt(
                            ArithmeticUtil.subtract(finProductFeePay002Dto.getActPayAmt(),
                                finProductFeePay002Dto.getPayAmt()));
//                    }
                    finProductFeePay002Dto.setBussTrdTransferOrder(investFeePay002Service.selectPayOrders(trdFinProductDealTotal.getTotleTradeId()));
                    finProductFeePay002Dto.setRemark(trdFinProductDealTotal.getRemark());
                }
            } else {
                TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(finProductFeePay001Dto.getTradeId());
                if (trdFinProductDealTotal != null) {
                    finProductFeePay002Dto.setStartDate(trdFinProductDealTotal.getVdate());
                    finProductFeePay002Dto.setEndDate(trdFinProductDealTotal.getMdate());
                    finProductFeePay002Dto.setPayDate(trdFinProductDealTotal.getSettleDate());
                    finProductFeePay002Dto.setActPayAmt(trdFinProductDealTotal.getTradeAmt());
                    List<TrdProductDeal> feeInfoList = investFeePay002Service.queryTrdProductDealByTotleTradeId(
                        finProductFeePay001Dto.getTradeId());
                    if (!StringUtil.isEmptyList(feeInfoList)) {
                        finProductFeePay002Dto.setRegTrustOrg(feeInfoList.get(0).getRegistOrg());
                    }
                    
                    if (!StringUtil.equals(finProductFeePay001Dto.getFeeType(),
                        DictCodeList.费用品种类型_账户维护费.getValue())) {
                        // 查询费用待支付费用
                        finProductFeePay002Dto.setPayAmt(investFeePay002Service.queryUnpayPosition(
                            finProductFeePay001Dto.getSecAcctId(), finProductFeePay001Dto.getFeeType(),
                            DateUtil.format(trdFinProductDealTotal.getMdate()), finProductFeePay002Dto.getRegTrustOrg()));
                        // 实付 - 理论
                        finProductFeePay002Dto.setAdjustAmt(
                            ArithmeticUtil.subtract(finProductFeePay002Dto.getActPayAmt(),
                                finProductFeePay002Dto.getPayAmt()));
                    } else {
                        finProductFeePay002Dto.setPayAmt(finProductFeePay001Dto.getFeeAmt());
                        finProductFeePay002Dto.setAdjustAmt(ArithmeticUtil.subtract(finProductFeePay002Dto.getActPayAmt(),
                                finProductFeePay002Dto.getPayAmt()));
                    }
                    finProductFeePay002Dto.setBussTrdTransferOrder(investFeePay002Service.selectPayOrders(trdFinProductDealTotal.getTotleTradeId()));
                    finProductFeePay002Dto.setRemark(trdFinProductDealTotal.getRemark());
                }
            }
            
            model.addAttribute(GlobalConstant.ENTITY, finProductFeePay002Dto);
            model.addAttribute("tradeId", this.getParam("tradeId"));
        }
        else {
            InvestFeePay002Dto finProductFeePay002Dto = new InvestFeePay002Dto();
            String confirmFlag = this.getParam("confirmFlag");
            if (StringUtil.isNotEmpty(confirmFlag) && StringUtil.equals(confirmFlag, "confirm")) {
                String secAcctId = this.getParam("secAcctId");
                String feeName = this.getParam("feeName");
                Date positionStartDate = DateUtil.parse(this.getParam("positionStartDateStr"));
                Date positionEndDate = DateUtil.parse(this.getParam("positionEndDateStr"));
                Date payDate =  DateUtil.parse(this.getParam("payDateStr"));
                String ccy = this.getParam("ccy");
                String feeType = this.getParam("feeType1");
                String finprodMarketId = this.getParam("finprodMarketId");
                String basis = this.getParam("basis");
                String feeAmt = this.getParam("feeAmt");
                String actPayAmt = this.getParam("actPayAmt") == null ? this.getParam("feeAmt") : this.getParam("actPayAmt");
                String org = investFeePay002Service.querySecAcctOrg(secAcctId);
                finProductFeePay002Dto.setSecAcctId(secAcctId);
                finProductFeePay002Dto.setFeeType(feeType);
                finProductFeePay002Dto.setBasis(basis);
                finProductFeePay002Dto.setStartDate(positionStartDate);
                finProductFeePay002Dto.setEndDate(positionEndDate);
                finProductFeePay002Dto.setPayDate(payDate);
                finProductFeePay002Dto.setFinprodMarketId(finprodMarketId);
                finProductFeePay002Dto.setFeeName(feeName);
                finProductFeePay002Dto.setCcy(ccy);
                finProductFeePay002Dto.setPayAmt(new BigDecimal(feeAmt));
                finProductFeePay002Dto.setActPayAmt(new BigDecimal(actPayAmt));
                finProductFeePay002Dto.setAdjustAmt(new BigDecimal(actPayAmt).subtract(new BigDecimal(feeAmt)));
                finProductFeePay002Dto.setRegTrustOrg(org);
            }
            finProductFeePay002Dto.setConfirmFlag(confirmFlag);
            model.addAttribute(GlobalConstant.ENTITY, finProductFeePay002Dto);
        }
        return super.init(model);
    }

    /**
     * 查询证券账户下产品关联的费用
     * 
     * @return 关联的费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList() {
        return investFeePay002Service.queryFeeInfoList(RequestUtil.getPageParam());
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
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @return 上次计提结束日
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryLastPayDate")
    public String queryLastPayDate(String secAcctId, String feeType, String operateType) {
        return investFeePay002Service.queryLastPayDate(secAcctId, feeType, operateType);
    }

    /**
     * 账户维护费 查询费用上次计提结束日
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @return 上次计提结束日
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryStartDate")
    public String queryStartDate(String secAcctId) {
        return investFeePay002Service.queryStartDate(secAcctId);
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
    public List<InvestFeePay003Dto> queryPayFeeInfoList() {
        if(getParam("flag").equals("false"))return new ArrayList<>();
    	// 待办事项
        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            ProcessMark pro = investFeePay002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                InvestFeePay002Dto entity = (InvestFeePay002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), InvestFeePay002Dto.class);
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
            return investFeePay002Service.queryFeeDetailByTradeId(this.getParam("tradeId"), this.getParam("payDate"));
        }
        if(StringUtil.isEmpty(this.getParam("feeType")))return new ArrayList<>();
        // 新建或者待确认
        return investFeePay002Service.queryPayFeeInfoList(RequestUtil.getPageParam());
    }

    /**
     * 查询费用待支付费用(一次性费用)
     * 
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param regTrustOrg 登记托管机构
     * @return 待支付费用
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryUnpayPosition")
    public BigDecimal queryUnpayPosition(String secAcctId, String feeType, String cdate, String regTrustOrg) {
        return investFeePay002Service.queryUnpayPosition(secAcctId, feeType, cdate, regTrustOrg);
    }
    
    /**
     * 结算服务费、交易手续费、佣金联动带出默认费用
     * @param secAcctId
     * @param feeType
     * @param cdate
     * @return
     */
    @ResponseBody
    @RequestMapping("queryPayTradeAmt")
    public BigDecimal queryPayTradeAmt(String secAcctId, String org, String feeType, String cdate, String startDate, String confirmFlag) {
        return investFeePay002Service.queryPayTradeAmt(secAcctId, org, cdate, startDate, feeType, confirmFlag);
    }
    
    /**
     * 查询证券账户登记托管机构
     * @param secAcctId 证券账户
     * @return 登记托管机构
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySecAcctOrg")
    public String querySecAcctOrg(String secAcctId) {
        return investFeePay002Service.querySecAcctOrg(secAcctId);
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
    public AjaxResult check(@Valid @RequestBody InvestFeePay002Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
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
    public AjaxResult save(@RequestBody InvestFeePay002Dto dto, BindingResult br)
        throws Exception {
        ProcessParameter<InvestFeePay002Dto> processParameter = new ProcessParameter<InvestFeePay002Dto>();
        WorkFlowParameter<InvestFeePay002Dto> params = new WorkFlowParameter<InvestFeePay002Dto>();
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
                entity -> this.getMessage("InvestFeePay002.secAcctId") + "(" + entity.getSecAcctId()
                          + ")" + this.getMessage("InvestFeePay002.feeName") + "("
                          + entity.getFeeName() + ")"
                          + this.getMessage("InvestFeePay002.payDate") + "("
                          + DateUtil.format(entity.getPayDate()) + ")");
        } else {
            // 设置工作流注解
            params.setComment(
                entity -> this.getMessage("InvestFeePay002.secAcctId") + "(" + entity.getSecAcctId()
                          + ")" + this.getMessage("InvestFeePay002.feeName") + "("
                          + entity.getFeeName() + ")"
                          + this.getMessage("InvestFeePay002.positionStartDate") + "("
                          + DateUtil.format(entity.getStartDate()) + ")"
                          + this.getMessage("InvestFeePay002.positionEndDate") + "("
                          + DateUtil.format(entity.getEndDate()) + ")");
        }

        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = investFeePay002Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
    /**
     * 获取组合名称
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortFolioName")
    public List<SelectOptionDto> getPortFolioName() {
        return investFeePay002Service.getPortFolioName(RequestUtil.getPageParam());
    }
}
