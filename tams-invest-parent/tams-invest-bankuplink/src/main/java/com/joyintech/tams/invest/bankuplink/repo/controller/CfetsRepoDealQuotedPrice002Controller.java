/*
 * 文件名：CfetsRepoDealQuotedPrice002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.util.QuotaPageModelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice001Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice002Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice004Service;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * CFETS现券买卖报价
 *
 * @author zhangliang
 * @version 1.0 2020年5月7日
 * @see CfetsRepoDealQuotedPrice002Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsRepoDealQuotedPrice002")
@RequestMapping("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice002")
public class CfetsRepoDealQuotedPrice002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice002Service cfetsRepoDealQuotedPrice002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice004Service cfetsRepoDealQuotedPrice004Service;
    
    /**
     * {@linkplain}
     */
    @Autowired
	 private IPlatformDate iPlatformDate;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBussinessId bussGetBussinessIdService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.回购拆借方向, BussinessDictCode.清算速度,
            BussinessDictCode.结算方式, BussinessDictCode.清算类型, BussinessDictCode.币种,
            BussinessDictCode.计息基础, BussinessDictCode.回购期限品种);
        model.addAttribute("ACCOUNT_SPEED_1", BussinessDictCodeList.清算速度_T加1.getValue());
        model.addAttribute("yesNoFlag_yes", BussinessDictCodeList.是否_是.getValue());
        model.addAttribute("assetTypeOne_abs", BussinessDictCodeList.平安资产一级分类_资产支持证券.getValue());
        model.addAttribute("psZHG", BussinessDictCodeList.回购拆借方向_正回购.getValue());
        // 可否无委托回复
        model.addAttribute("ibuFlag_answerQuoteWithOutEntrust",
            CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"));
        // 本方交易员是否可编辑
        model.addAttribute("ibuFlag_ouTraderIdCanEdit",
            CacheUtil.getSysParam("ibuFlag_ouTraderIdCanEdit"));
        model.addAttribute("quoteRoleTypeEnquiry", BussinessDictCodeList.银行间上行本方角色_询价方.getValue());
        model.addAttribute("quoteRoleTypeReply", BussinessDictCodeList.银行间上行本方角色_回复方.getValue());
        String entrustId = this.getParam("entrustId");
        String operation = this.getParam("operation");
        String quoteId = this.getParam("quoteId");
        if (StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.reply")||StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.edit")) {
            model.addAttribute("operation", operation);
        }
        model.addAttribute("editOperation", this.getParam("editOperation"));//区分双击进去修改页面与点击修改进入页面
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        if (StringUtil.equals(this.getParam("workflowStatus"),DictCodeListExt1.有效状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList()
                && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID,getParam(GlobalConstant.WORKFLOW_ID));
            cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.getBussinessEntityByWorkflowId(
                getParam(GlobalConstant.WORKFLOW_ID));
            if (cfetsRepoDealQuotedPrice001Dto.getQuoteId() != null) {
            	// 画面模式
            	cfetsRepoDealQuotedPrice001Dto.setDialogueRecord(cfetsRepoDealQuotedPrice002Service.selectDialogueRecord(cfetsRepoDealQuotedPrice001Dto).getDialogueRecord());
                if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                	cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
                }
                //撤销流程中数据 查询有效数据
                /*if(StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getOperatorType(),"revoke")){
                    cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(cfetsRepoDealQuotedPrice001Dto.getQuoteId(), null);
                    cfetsRepoDealQuotedPrice001Dto.setWorkflowId(getParam(GlobalConstant.WORKFLOW_ID));
                }*/
                model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
            }
        }
        else if (StringUtil.isNotEmpty(quoteId)) {
        	cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(quoteId, operation);
        	// 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
            	cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
           model.addAttribute(GlobalConstant.ENTITY,cfetsRepoDealQuotedPrice001Dto);
        }
        else if (StringUtil.isNotEmpty(entrustId)) {
            cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(entrustId, null);
           // 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
            	cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
            // cfetsRepoDealQuotedPrice001Dto.setMySideTrader();// TODO 查询用户对应的交易员
            model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
        }else{
        	cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        	// 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
            	cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
            // cfetsRepoDealQuotedPrice001Dto.setMySideTrader();// TODO 查询用户对应的交易员
            model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
        }
        // 质押品超过剩余可质押面额是否继续
        model.addAttribute("ibuFlag_repoOverAvaCanContinue",
            CacheUtil.getSysParam("ibuFlag_repoOverAvaCanContinue"));
        model.addAttribute("settleSpreedT0", BussinessDictCodeList.清算速度_T加0.getValue());
        model.addAttribute("settleSpreedT1", BussinessDictCodeList.清算速度_T加1.getValue());
        cfetsRepoDealQuotedPrice002Service.单独处理页面质押券列表(cfetsRepoDealQuotedPrice001Dto);
        model.addAttribute("entity_bussRepoSecDto",cfetsRepoDealQuotedPrice001Dto.getBussRepoSec() == null ? null : JsonUtils.object2Json(cfetsRepoDealQuotedPrice001Dto.getBussRepoSec()));
        model.addAttribute("dialogueRecord",HtmlUtils.htmlUnescape(cfetsRepoDealQuotedPrice001Dto.getDialogueRecord()));
        return super.init(model);
    }

    /**
     * 如果我方作为回复方，在切换组合代码时需要根据画面显示的质押券关联显示可质押和已质押
     *
     * @param cfetsRepoDealQuotedPrice001Dto 报价信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("handlePledgeList")
    public CfetsRepoDealQuotedPrice001Dto handlePledgeList(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        if (!StringUtil.isEmptyList(cfetsRepoDealQuotedPrice001Dto.getPledgeBondList())
            && StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getPortfolioId())) {
            List<String> bondCodes = cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().stream().map(
                obj -> StringUtil.isNotEmpty(obj.getBondCode())?obj.getBondCode():obj.getFinprodId()).collect(Collectors.toList());
            // 查询债券相关信息
            List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = null;
            Map<String, Object> param = new HashMap<>();
            param.put("portfolioId", cfetsRepoDealQuotedPrice001Dto.getPortfolioId());
            param.put("mySide", cfetsRepoDealQuotedPrice001Dto.getMySide());
            param.put("quoteId", cfetsRepoDealQuotedPrice001Dto.getQuoteId());
            param.put("finprodMarketIds", bondCodes);
            if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                BussinessDictCodeList.回购拆借方向_正回购.getValue())) {
                // 正回购
                try {
					pledgeBondList = cfetsRepoDealQuotedPrice003Service.queryPledgeBondList(
					    param).getRoot();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            else if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                BussinessDictCodeList.回购拆借方向_逆回购.getValue())) {
                // 逆回购
                pledgeBondList = cfetsRepoDealQuotedPrice004Service.queryPledgeBondList(
                    param).getRoot();
            }
            if (!StringUtil.isEmptyList(pledgeBondList)) {
                for (CfetsRepoDealQuotedPrice003Dto pledge : pledgeBondList) {
                    // pledge.setPledgeNumber(ArithmeticUtil.divide(pledge.getPledgeNumber(), new BigDecimal(10000), 2));// 今日已质押券面（万）
                    // pledge.setAvaPledgeNumber(ArithmeticUtil.divide(pledge.getAvaPledgeNumber(), new BigDecimal(10000), 2));// 剩余可质押券面（万）
                    cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().forEach(obj -> {
                        if (obj.getBondCode().equals(pledge.getBondCode())) {
                            obj.setExerciseDate(pledge.getExerciseDate());
                            obj.setMdate(pledge.getMdate());
                            obj.setAvaPledgeNumber(pledge.getAvaPledgeNumber());
                            obj.setPledgeNumber(pledge.getPledgeNumber());
                            obj.setAvaPledgePrin(pledge.getAvaPledgePrin());
                            obj.setSecAcctName(pledge.getSecAcctName());
                            obj.setIsSus(pledge.getIsSus());
                            obj.setIsSecBond(pledge.getIsSecBond());
                        }
                    });
                }
            }
        }

        return cfetsRepoDealQuotedPrice001Dto;
    }

    /**
     * 根据报价代码查询报价信息
     *
     * @param quoteId 报价代码
     * @param operation 是否复制操作
     * @return 报价信息
     * @see
     * @since 1.0
     */
    private CfetsRepoDealQuotedPrice001Dto queryRepoInfoByQuoteId(String quoteId,
                                                                  String operation) {
        // 根据报价id查询数据
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByQuoteId(
            quoteId);
        if (StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.copy")
            && StringUtil.isNotEmpty(quoteId)) {
            cfetsRepoDealQuotedPrice001Dto.setQuoteId(null);
        }
        if (StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getEntrustId())) {
            CfetsRepoDealQuotedPrice001Dto dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(
                cfetsRepoDealQuotedPrice001Dto.getEntrustId(), null);
            cfetsRepoDealQuotedPrice001Dto.setSecAcctId(dto.getSecAcctId());
        }
        // 处理质押品信息
        this.handlePledgeList(cfetsRepoDealQuotedPrice001Dto);

        return cfetsRepoDealQuotedPrice001Dto;
    }

    /**
     * 根据查询查询符合条件的委托列表
     *
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryEntrustInfoList")
    public Page<SelectOptionDto> getentrustIdAdd() {
        return cfetsRepoDealQuotedPrice002Service.queryEntrustInfoList(RequestUtil.getPageParam());
    }

    /**
     * 保存验证
     *
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价对象
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ar = new AjaxResult();
        StringBuffer sb = new StringBuffer();
        String ps = cfetsRepoDealQuotedPrice001Dto.getPs();
        String isDzh = cfetsRepoDealQuotedPrice001Dto.getIsDzh();
        List<PtlAllocationDto> ptlAllocations = cfetsRepoDealQuotedPrice001Dto.getPtlAllocationList();
        // 委托为空的场合只有新建模式且系统参数为软校验
        if (!StringUtil.equals(isDzh,"Y")&&StringUtil.isEmpty(cfetsRepoDealQuotedPrice001Dto.getEntrustId())) {
            if ((StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getQuotaPageModel(), QuotaPageModelUtil.MODEL_NEW)||StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getQuotaPageModel(), QuotaPageModelUtil.MODEL_EDIT))
                    && StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")) {
                sb.append(this.getMessage("CFETS_COMMON_E_001"));
                sb.append(GlobalConstant.HTML_NEWLINE);
            }
        }
        if(StringUtil.equals("02", CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))){
            if(StringUtil.equals(isDzh,"Y")&&null!=ptlAllocations){
                List<String> entrustIdList = new ArrayList<>();
                BigDecimal fpriceAmtTotal = new BigDecimal(0);
                //最大或最小回购利率
                BigDecimal maxOrminprice = ptlAllocations.get(0).getPrice();
                for(PtlAllocationDto item : ptlAllocations) {
                    fpriceAmtTotal = ArithmeticUtil.doAdd(fpriceAmtTotal, item.getFpriceAmt());
                    entrustIdList.add(item.getEntrustId());
                    if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())){
                        if(maxOrminprice.compareTo(item.getPrice())>=0){
                            maxOrminprice = item.getPrice();
                        }
                    }
                    else if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
                        if(maxOrminprice.compareTo(item.getPrice())<=0){
                            maxOrminprice = item.getPrice();
                        }
                    }
                }
                if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())
                        && cfetsRepoDealQuotedPrice001Dto.getPrice() != null
                        && cfetsRepoDealQuotedPrice001Dto.getPrice().compareTo(maxOrminprice) == 1){
                    sb.append(this.getMessage("CFETS_COMMON_R_014",maxOrminprice.toPlainString()));
                    sb.append(GlobalConstant.HTML_NEWLINE);
                }
                else if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())
                        && cfetsRepoDealQuotedPrice001Dto.getPrice() != null
                        && cfetsRepoDealQuotedPrice001Dto.getPrice().compareTo(maxOrminprice) == -1){
                    sb.append(this.getMessage("CFETS_COMMON_R_015",maxOrminprice.toPlainString()));
                    sb.append(GlobalConstant.HTML_NEWLINE);
                }
            }
            else{
                if(StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getEntrustId())){
                    CfetsRepoDealQuotedPrice001Dto etrEntrust = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(
                            cfetsRepoDealQuotedPrice001Dto.getEntrustId(), null);
                    if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                            BussinessDictCodeList.回购拆借方向_正回购.getValue())) {
                        // 提示：“报价回购利率大于委托指令回购利率XX”(XX表示具体的回购利率)；（参数开关控制是否继续操作）
                        if (etrEntrust != null && etrEntrust.getPrice() != null
                                && cfetsRepoDealQuotedPrice001Dto.getPrice() != null
                                && cfetsRepoDealQuotedPrice001Dto.getPrice().compareTo(
                                etrEntrust.getPrice()) == 1) {
                            sb.append(this.getMessage("CFETS_COMMON_R_014",etrEntrust.getPrice().toPlainString()));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                    else if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                            BussinessDictCodeList.回购拆借方向_逆回购.getValue())) {
                        // 8) 交易方向为逆回购，价格模式为限价，需校验回购利率不能小于委托指令的回购利率，
                        // 若不满足，提示：“报价回购利率小于委托指令回购利率XX”(XX表示具体的回购利率)；（参数开关控制是否继续操作）
                        if (etrEntrust != null && etrEntrust.getPrice() != null
                                && cfetsRepoDealQuotedPrice001Dto.getPrice() != null
                                && cfetsRepoDealQuotedPrice001Dto.getPrice().compareTo(
                                etrEntrust.getPrice()) == -1) {
                            sb.append(this.getMessage("CFETS_COMMON_R_015",etrEntrust.getPrice().toPlainString()));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }
        }
        // 9) 交易方向为逆回购，需实时校验该笔报价的交易金额需要<=委托剩余报价额度，
        // 若不满足，提示：“交易金额需要小于或等于该笔委托的剩余报价额度XX”，（XX为具体的剩余报价额度）；（参数开关控制是否继续操作）
        if(StringUtil.equals("02", CacheUtil.getSysParam("ibuFlag_repoOverEntrustAvaAmt"))){
            if(StringUtil.equals(isDzh,"Y")&&null!=ptlAllocations){
                List<String> entrustIdList = new ArrayList<>();
                //分配列表  最新剩余额度
                for(PtlAllocationDto item : ptlAllocations) {
                    entrustIdList.add(item.getEntrustId());
                }
                Map<String,Object> entrustIdMap = new HashMap<>();
                entrustIdMap.put("entrustIdList",entrustIdList);
                entrustIdMap.put("workflowId",cfetsRepoDealQuotedPrice001Dto.getWorkflowId());
                List<PtlAllocationDto> newestPtlAllocations =cfetsRepoDealQuotedPrice003Service.selectPortfolioInit(entrustIdMap);
                for(PtlAllocationDto item : ptlAllocations) {
                    for(PtlAllocationDto item2 :newestPtlAllocations){
                        if(StringUtil.equals(item.getEntrustId(),item2.getEntrustId())
                                &&null!=item.getFpriceAmt()&&null!=item2.getAvaNum()
                                &&item2.getAvaNum().compareTo(item.getFpriceAmt())<0){
                            sb.append(this.getMessage("CFETS_COMMON_R_016")+"委托代码:"+item.getEntrustId());
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }
            else{
                if(StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getEntrustId())&&StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
                    if (StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getEntrustId())) {
                        CfetsRepoDealQuotedPrice001Dto etrEntrust = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(
                                cfetsRepoDealQuotedPrice001Dto.getEntrustId(), cfetsRepoDealQuotedPrice001Dto.getQuoteId());
                        if (etrEntrust != null && cfetsRepoDealQuotedPrice001Dto.getDealAmt() != null
                                && cfetsRepoDealQuotedPrice001Dto.getDealAmt().compareTo(
                                etrEntrust.getAvaNum()) == 1) {
                            sb.append(this.getMessage("CFETS_COMMON_R_016"));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }
        }

        if (sb.length() > 0) {
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else {
            if (StringUtil.equals("save",cfetsRepoDealQuotedPrice001Dto.getOperatorType())) {
                ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
            }
            else if (StringUtil.equals("submit",cfetsRepoDealQuotedPrice001Dto.getOperatorType())) {
                ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.submit")));
            }
            /*if (StringUtil.equals("CfetsRepoDealQuotedPrice002_btn_save",
                this.getParam("elementIdForLog"))) {
                ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
            }
            else if (StringUtil.equals("CfetsRepoDealQuotedPrice002_btn_submit",
                this.getParam("elementIdForLog"))) {
                ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.submit")));
            }*/
        }

        return ar;
    }

    /**
     * 保存提交
     *
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("saveData")
    public AjaxResult saveData(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto) {
        cfetsRepoDealQuotedPrice002Service.save(cfetsRepoDealQuotedPrice001Dto);
        return new AjaxResult(this.getMessage("CFETS_COMMON_SAVE"));
    }

    /**
     * 保存提交
     *
     * @param cfetsRepoDealQuotedPrice001Dto 对话报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto) {
        cfetsRepoDealQuotedPrice002Service.submit(cfetsRepoDealQuotedPrice001Dto);
        return new AjaxResult(this.getMessage("CFETS_COMMON_SUBMIT"));
    }
    
    @ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto, BindingResult br) throws WorkFlowException {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (!StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getQuoteId())) {
			BussinessCodeParameter codeParameter = new BussinessCodeParameter();
			codeParameter.setSeqNo("IBU_003");
			String code = bussGetBussinessIdService != null
					? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
					cfetsRepoDealQuotedPrice001Dto.setQuoteId(code);
		}
		ProcessParameter<CfetsRepoDealQuotedPrice001Dto> processParameter = new ProcessParameter<CfetsRepoDealQuotedPrice001Dto>();
		WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> params = cfetsRepoDealQuotedPrice002Service.getWorkFlowParameter(cfetsRepoDealQuotedPrice001Dto);
		params.setPageId(cfetsRepoDealQuotedPrice001Dto.getPageId());
		params.setElementId(cfetsRepoDealQuotedPrice001Dto.getElementId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
		params.setBusinessCode(cfetsRepoDealQuotedPrice001Dto.getQuoteId());
		params.setComment(entity -> getMessage("对话报价编号") + "(" + entity.getQuoteId() + ")-"
				+ "业务类型" + "(" + DictCodeListExt3.报价系统区分类型_质押式回购对话报价.getCodeName() + ")-" + "平台日" + "("
				+ DateUtil.format(iPlatformDate.getSysDate()) + ")");
		processParameter.setWorkFlowParameter(params);

		cfetsRepoDealQuotedPrice001Dto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
		cfetsRepoDealQuotedPrice001Dto.setCreateUser(LoginUserUtil.getUserName());// 用户名
		cfetsRepoDealQuotedPrice001Dto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
		cfetsRepoDealQuotedPrice001Dto.setCreateTime(new Date());// 创建时间
		String msg = cfetsRepoDealQuotedPrice002Service.start(processParameter, cfetsRepoDealQuotedPrice001Dto);
		//cfetsRepoDealQuotedPrice002Service.insertWflBondDialogueQuote(cfetsRepoDealQuotedPrice001Dto);
		return new AjaxResult(msg);
	}

    /**
     * 对话报价验证
     * 
     * @param entrustId 委托id
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkEdit")
    public AjaxResult checkEdit(@RequestBody Map<String, Object> param) {
        AjaxResult ar = new AjaxResult();
        //多组合
        if(param.containsKey("isDzh")&&StringUtil.equals(param.get("isDzh"),"Y")){
            String[] entrustIds = ((String) param.get("entrustId")).split(",");
            for (String entrustId : entrustIds) {
                param.put("entrustId",entrustId);
                param.put("pageId","CfetsRepoDealQuotedPrice003");
                ar = cfetsRepoDealQuotedPrice002Service.checkEdit(param);
                if(ar.getErrorList().size()>0){
                    return ar;
                }
            }
        }else{
            param.put("pageId","CfetsRepoDealQuotedPrice002");
            ar = cfetsRepoDealQuotedPrice002Service.checkEdit(param);
        }
        return ar;
    }
    
    /**
     * 对话报价验证
     * 
     * @param entrustId 委托id
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkEditList")
    public AjaxResult checkEditList(@RequestBody Map<String, Object> param) {
    	List<Map<String, Object>> dataList = (List<Map<String, Object>>)param.get("dataList");
    	AjaxResult ar = null;
    	for(Map<String, Object> data:dataList){
            //多组合标识或者本方询价方为回复方 均跳转多组合界面
            String isDzh = (String)param.get("isDzh");
            String quoteRoleType = (String)param.get("quoteRoleType");
            if(StringUtil.equals(isDzh,"Y")||StringUtil.equals(quoteRoleType,BussinessDictCodeList.银行间上行本方角色_回复方.getValue())){
                data.put("entrustId",StringUtil.EMPTY);
            }
            ar = cfetsRepoDealQuotedPrice002Service.checkEdit(data);
    		if(!StringUtil.isEmptyList(ar.getErrorList())){
    			return ar;
    		}
    	}
        return ar;
    }
    

    /**
     * 对话报价回复成交验证
     * 
     * @param param 参数
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkRepoDeal")
    public AjaxResult checkRepoDeal(@Valid @RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto,
                                    BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ar = cfetsRepoDealQuotedPrice002Service.checkRepoDeal(cfetsRepoDealQuotedPrice001Dto);
        return ar;
    }
    
    

    /**
     * 撤回
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("revert")
    public AjaxResult revert(@RequestBody Map<String, Object> param) {
        cfetsRepoDealQuotedPrice002Service.revert(param);
        return new AjaxResult(this.getMessage("CFETS_COMMON_REVOKE"));
    }
    
    /**
     * 撤回
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("revertList")
    public AjaxResult revertList(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> dataList = (List<Map<String, Object>>)param.get("dataList");
    	for(Map<String, Object> data:dataList){
    		cfetsRepoDealQuotedPrice002Service.revert(data);
    	}
        return new AjaxResult(this.getMessage("CFETS_COMMON_REVOKE"));
    }

    /**
     * 成交
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deal")
    public AjaxResult deal(@RequestBody Map<String, Object> param) {
        cfetsRepoDealQuotedPrice002Service.deal(param);
        return new AjaxResult(this.getMessage("CFETS_COMMON_TRADE"));
    }

    /**
     * 拒绝
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reject")
    public AjaxResult reject(@RequestBody Map<String, Object> param) {
        cfetsRepoDealQuotedPrice002Service.reject(param);
        return new AjaxResult(this.getMessage("CFETS_COMMON_SUCSESS"));
    }

    /**
     * 回退
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("restoreDayOff")
    public AjaxResult restoreDayOff(@RequestBody Map<String, Object> param) {
        cfetsRepoDealQuotedPrice002Service.restoreDayOff(param);
        return new AjaxResult(this.getMessage("CFETS_COMMON_SUCSESS"));
    }

    /**
     * 删除
     *
     * @param param 条件
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteDayOff")
    public AjaxResult deleteDayOff(@RequestBody Map<String, Object> param) {
        cfetsRepoDealQuotedPrice002Service.deleteDayOff(param);
        return new AjaxResult(this.getMessage("CFETS_COMMON_SUCSESS"));
    }

    /**
     * 根据委托代码查询委托基本信息
     * 
     * @param entrustId 委托代码
     * @return 委托基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryEntrustInfoByEntrustId")
    public AjaxResult queryEntrustInfoByEntrustId(String entrustId) {
        AjaxResult ar = new AjaxResult();
        ar.setData(cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(entrustId, null));
        return ar;
    }

    /**
     * 回复成交质押品数据校验
     * 
     * @param entrustId 委托id
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkPledgeBondList")
    public AjaxResult checkPledgeBondList(@RequestBody Map<String, Object> param) {
        AjaxResult ar = new AjaxResult();
        String quoteId = (String)param.get("quoteId");
        String entrustId = (String)param.get("entrustId");
        CfetsRepoDealQuotedPrice001Dto dto = null;
        if (StringUtil.isNotEmpty(entrustId)) {
            dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(entrustId, quoteId);
        }
        CfetsRepoDealQuotedPrice001Dto dayOffer = null;
        if (StringUtil.isNotEmpty(quoteId)) {
            dayOffer = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByQuoteId(quoteId);
        }
        ar.setData(cfetsRepoDealQuotedPrice002Service.checkPledgeBondList(dto, dayOffer));
        return ar;
    }

    /**
     * 现券买卖历史报价 撤销
     * 
     * @param dto
     * @param br
     * @return
     * @throws WorkFlowException
     */
    @ResponseBody
    @RequestMapping("repoRevoke")
    public AjaxResult bondRevoke(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto)
        throws WorkFlowException {
        ProcessParameter<CfetsRepoDealQuotedPrice001Dto> processParameter = new ProcessParameter<CfetsRepoDealQuotedPrice001Dto>();
        WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> workFlowParameter = new WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto>();
        workFlowParameter.setPageId("CfetsRepoDealQuotedPrice002");
        workFlowParameter.setElementId("CfetsRepoDealQuotedPrice002_btn_repoRevoke");
        workFlowParameter.setBusinessCode(cfetsRepoDealQuotedPrice001Dto.getQuoteId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
        workFlowParameter.setComment(
            entity -> getMessage("CFETSHIS_QUOTEID") + "(" + entity.getQuoteId() + ")");
        processParameter.setOperationType(OperationType.REVOKE);
        processParameter.setWorkFlowParameter(workFlowParameter);
        cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(cfetsRepoDealQuotedPrice001Dto.getQuoteId(), null);
        cfetsRepoDealQuotedPrice001Dto.setOperatorType("revoke");
        String msg = cfetsRepoDealQuotedPrice002Service.start(processParameter,
            cfetsRepoDealQuotedPrice001Dto);

        return new AjaxResult(msg);
    }
}
