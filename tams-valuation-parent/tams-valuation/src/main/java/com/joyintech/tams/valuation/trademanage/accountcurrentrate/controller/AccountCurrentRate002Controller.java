/*
 * 文件名：AccountCurrentRate002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.accountcurrentrate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.service.AccountCurrentRate002Service;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 帐户活期利率 新建修改 双击查看
 * 
 * @author HuangJian
 * @version 1.0 2018年11月13日
 * @see AccountCurrentRate002Controller
 * @since 1.0
 */
@Controller
@PageId("AccountCurrentRate002")
@RequestMapping("/valuation/trademanage/accountcurrentrate/AccountCurrentRate002")
public class AccountCurrentRate002Controller extends BaseController {

    /**
     * accountCurrentRate002Service
     */
    @Autowired
    private AccountCurrentRate002Service accountCurrentRate002Service;

    /**
     * accountCurrentRateService
     */
    @Autowired
    private AccountCurrentRateService accountCurrentRateService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        this.setSelectOptionToModel(model, DictCode.资金账户类型);
        this.setSelectOptionToModel(model, DictCode.利率类型);
        this.setSelectOptionToModel(model, DictCode.计息基础);
        this.setSelectOptionToModel(model, DictCode.利率结构);
        String workflowId = getParam("workflowId");
        Map<String, Object> map = new HashMap<String, Object>();
        model.addAttribute("AcctNo", null);
        // JsonUtils.objectArray2Json(accountCurrentRate002Service.getAcctNo(map).toArray()));

        // TODO 一体化问题8：表删除（行情信息（MST_RATE_INFO）） 修改：基准利率从数据字典取
        // model.addAttribute("BasicRate",
        // JsonUtils.objectArray2Json(accountCurrentRate002Service.getBasicRate().toArray()));
        // 现金流代码
        String cashId = this.getParam("cashId");
        if (StringUtil.isNotEmpty(cashId)) {
            // 双击查看或者修改

            // Map<String, Object> map = new HashMap<String, Object>();
            map.put("cashId", cashId);
            map.put("effDate", this.getParam("effDate"));
            map.put("booksetId", this.getParam("booksetId"));
            map.put("ccy", this.getParam("ccy"));
            AccountCurrentRate002Dto dto = accountCurrentRate002Service.getfind002Dto(map);
            dto.setIntRateStructure(DictCodeListExt1.利率结构_单层结构.getValue());
            if ("01".equals(dto.getIntRateStructure()) && "01".equals(dto.getIntType())) {
                model.addAttribute("IntRateStructure", true);
                model.addAttribute("IntType", true);
            }
            else if ("01".equals(dto.getIntRateStructure()) && "02".equals(dto.getIntType())) {
                model.addAttribute("IntRateStructure", true);
                model.addAttribute("IntType", false);
            }
            else {
                model.addAttribute("IntRateStructure", false);
                model.addAttribute("IntType", false);
            }
            model.addAttribute("entity", dto);
            // 修改标志
            String status = getParam("status");
            if (StringUtil.isNotEmpty(status)) {
                // 修改
                model.addAttribute("newOrEdit", false);
                model.addAttribute("notEditOrgCode", true);
                model.addAttribute("entity", dto);
            }
            else {
                // 双击查看
                // 双击查看
                model.addAttribute("newOrEdit", true);
                model.addAttribute("notEditOrgCode", false);
                model.addAttribute("entity", dto);
            }

        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            AccountCurrentRateDto comdto = accountCurrentRateService.getBussinessEntityByWorkflowId(workflowId);
            AccountCurrentRate002Dto dto = new AccountCurrentRate002Dto();
            dto.setAccountId(comdto.getAcctNo());
            dto.setAccountType(comdto.getAcctType());
            dto.setBasicRate(dto.getBasicRate());
            dto.setRate(comdto.getRate());
            dto.setBasis(comdto.getBasis());
            dto.setIntType(comdto.getIntType());
            dto.setEffDate(comdto.getEffDate());
            model.addAttribute("entity", dto);
            // 双击查看
            // 双击查看
            model.addAttribute("newOrEdit", true);
            model.addAttribute("notEditOrgCode", false);
            model.addAttribute("IntRateStructure", false);
            model.addAttribute("IntType", false);
            model.addAttribute("entity", dto);
        }

        else {
            // 新建
            model.addAttribute("newOrEdit", false);
            model.addAttribute("notEditOrgCode", false);
            AccountCurrentRate002Dto dto = new AccountCurrentRate002Dto();
            dto.setAccountType(DictCodeListExt1.资金账户类型_产品托管户.getValue());
            dto.setBasis(DictCodeList.计息基础_A_360.getValue());
            dto.setIntRateStructure(DictCodeListExt1.利率结构_单层结构.getValue());
            dto.setIntType(DictCodeList.利率类型_固定利率.getValue());
            model.addAttribute("IntRateStructure", true);
            model.addAttribute("IntType", true);
            model.addAttribute("entity", dto);
        }

        return super.init(model);
    }

    // /**
    // * 帐户类型设值 AccountCurrentRate002Dto
    // *
    // * @param dto BindingResult
    // * @param result AjaxResult
    // * @return
    // * @see
    // * @since 1.0
    // */
    // @ResponseBody
    // @RequestMapping("searchType")
    // public AjaxResult searchType(@RequestBody AccountCurrentRate002Dto dto, BindingResult
    // result) {
    // if (result != null && result.hasErrors()) {
    // return new AjaxResult(result);
    // }
    // AjaxResult ajaxResult = new AjaxResult();
    //
    // // dto1.setEffDate(dto.getVdate());
    // // if(StringUtil.isNotEmpty(accountCurrentRate002Service.searchType(dto))){
    // ajaxResult.setData(accountCurrentRate002Service.searchType(dto));
    // return ajaxResult;
    //
    // }

    /**
     * 提交 插入 修改
     * 
     * @param dto AccountCurrentRate002Dto
     * @param result AjaxResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @RequestMapping("doCommit")
    @ResponseBody
    public AjaxResult doCommit(@Valid @RequestBody AccountCurrentRate002Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        ProcessParameter<AccountCurrentRateDto> proParam = new ProcessParameter<>();
        WorkFlowParameter<AccountCurrentRateDto> params = new WorkFlowParameter<>();
        AccountCurrentRateDto dto1 = accountCurrentRate002Service.getAccountCurrentRateDto(dto);

        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账户活期利率.getValue());
        params.setPageId("AccountCurrentRate002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        if (StringUtil.isNotEmpty(dto.getCashId())) {
            // 业务代码
            params.setBusinessCode(dto1.getAcctNo() + dto1.getEffDate1());
            // 设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("活期利率修改：", dto1.getAcctNo(), dto1.getEffDate1()));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
        }
        else {
            // 业务代码
            params.setBusinessCode(dto1.getAcctNo() + dto1.getEffDate());
            // 设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("活期利率新建：", dto1.getAcctNo(), dto1.getEffDate()));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
        }

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = accountCurrentRateService.start(proParam, dto1);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * check
     * 
     * @param dto AccountCurrentRate002Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("commitCheck")
    public AjaxResult check(@Valid @RequestBody AccountCurrentRate002Dto dto, BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 帐户代码
     *
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAccNo")
    public List<SelectOptionDto> getAccNo(HttpServletRequest request)
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        String accountType = this.getParam("accountType");
        map.put("accountType", accountType);
        List<SelectOptionDto> list = accountCurrentRate002Service.getAcctNo(map);
        return list;
    }

    /**
     * 帐户代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAccNo1")
    public List<SuggestDto> getBusiId() {
        // 业务类型
        Map<String, Object> map = RequestUtil.getPageParam();
        String param = super.getRequest().getParameter("param");
        String accountType = this.getParam("accountType");
        map.put("accountType", accountType);
        map.put("accountId", param);
        List<SuggestDto> list = accountCurrentRate002Service.getAcctNo1(map, param);
        return list;
    }
}
