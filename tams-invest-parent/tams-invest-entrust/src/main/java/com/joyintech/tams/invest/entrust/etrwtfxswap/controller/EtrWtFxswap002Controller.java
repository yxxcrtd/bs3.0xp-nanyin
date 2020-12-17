/*
 * 文件名：EtrWtFxswap002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月12S日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrwtfxswap.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.tams.invest.entrust.etrwtfxswap.service.EtrWtFxswapService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇掉期委托新建Controller
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月12日
 * @see EtrWtFxswap002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrWtFxswap002")
@RequestMapping("/invest/entrust/etrwtfxswap/EtrWtFxswap002")
public class EtrWtFxswap002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private EtrWtFxswapService etrWtFxswapService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        EtrWtFxswapDto dto = new EtrWtFxswapDto();
        setSelectOptionToModel(model, DictCode.货币对);
        setSelectOptionToModel(model, DictCode.币种);
        setSelectOptionToModel(model, DictCode.外汇交易类型);
        setSelectOptionToModel(model, DictCode.交易方向);
        setSelectOptionToModel(model, DictCode.委托状态);
        setSelectOptionToModel(model, DictCode.外汇掉期期限类型);
        setSelectOptionToModel(model, DictCode.货币对);
        model.addAttribute("tradePs", DictCodeListExt2.交易方向_卖买.getValue());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        RequestUtil.getPageParam();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = etrWtFxswapService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getWorkflowId());
            model.addAttribute("entrustId", dto.getEntrustId());
            //显示交易货币账户下拉框的值
            Map<String, Object> param=new HashMap<>();
            param.put("portfolioId", dto.getPortfolioId());
            param.put("ccy", dto.getTradeCcy());
            param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
            List<SelectOptionDto> tradeCcyAcct= etrWtFxswapService.selectPtlCapAcctNo(param);
            for (SelectOptionDto selectOptionDto : tradeCcyAcct) {
                String id=selectOptionDto.getId();
                String text=selectOptionDto.getText();
                selectOptionDto.setId(id);
                selectOptionDto.setText(text);
            }
            model.addAttribute("TRADE_CCY_ACCT",JsonUtils.objectArray2Json(tradeCcyAcct.toArray()));
           //显示对应货币账户下拉框的值
            param.put("ccy", dto.getContraCcy());
            List<SelectOptionDto> ContraCcyAcct= etrWtFxswapService.selectPtlCapAcctNo(param);
            for (SelectOptionDto selectOptionDto : ContraCcyAcct) {
                String id=selectOptionDto.getId();
                String text=selectOptionDto.getText();
                selectOptionDto.setId(id);
                selectOptionDto.setText(text);
            }
            model.addAttribute("CONTRA_CCY_ACCT",JsonUtils.objectArray2Json(ContraCcyAcct.toArray()));
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            dto = etrWtFxswapService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getEntrustId());
            model.addAttribute("entrustId", dto.getEntrustId());
            //显示交易货币账户下拉框的值
            Map<String, Object> param=new HashMap<>();
            param.put("portfolioId", dto.getPortfolioId());
            param.put("ccy", dto.getTradeCcy());
            param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
            List<SelectOptionDto> tradeCcyAcct= etrWtFxswapService.selectPtlCapAcctNo(param);
            for (SelectOptionDto selectOptionDto : tradeCcyAcct) {
                String id=selectOptionDto.getId();
                String text=selectOptionDto.getText();
                selectOptionDto.setId(id);
                selectOptionDto.setText(text);
            }
            model.addAttribute("TRADE_CCY_ACCT",JsonUtils.objectArray2Json(tradeCcyAcct.toArray()));
            //显示对应货币账户下拉框的值
            param.put("ccy", dto.getContraCcy());
            List<SelectOptionDto> ContraCcyAcct= etrWtFxswapService.selectPtlCapAcctNo(param);
            for (SelectOptionDto selectOptionDto : ContraCcyAcct) {
                String id=selectOptionDto.getId();
                String text=selectOptionDto.getText();
                selectOptionDto.setId(id);
                selectOptionDto.setText(text);
            }
            model.addAttribute("CONTRA_CCY_ACCT",JsonUtils.objectArray2Json(ContraCcyAcct.toArray()));
        }
      //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            dto.setVdate(sysDate);
            dto.setMdate(sysDate);
            dto.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        model.addAttribute("termType1", DictCodeListExt2.外汇期限类型_远期对远期掉期.getValue());
        model.addAttribute("termType2", DictCodeListExt2.外汇期限类型_即期对远期掉期.getValue());
        model.addAttribute("termType3", DictCodeListExt2.外汇期限类型_Overnight.getValue());
        model.addAttribute("termType4", DictCodeListExt2.外汇期限类型_Spot.getValue());
        model.addAttribute("termType5", DictCodeListExt2.外汇期限类型_Tom.getValue());
        model.addAttribute("COUNTER_TYPE_01", DictCodeListExt1.交易对手类型_市场机构.getValue());
        //model.addAttribute("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        //---------add by jjpei 20190718 风险试算----start-----------
        model.addAttribute("MY_SUP_TIME_TYPE_01", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //---------add by jjpei 20190718 风险试算----end-----------
        model.addAttribute("PINAN_ASSET_TYPE_F15",DictCodeListExt2.平安资产类型_外汇掉期.getValue());
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
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) EtrWtFxswapDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrWtFxswapDto dto) {
        //授权管理  委托授权额度check
        if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH),"01")){
            EtrCommonController etrCommonController = SpringContextUtil.getBean(
                EtrCommonController.class);
            return etrCommonController.授权是否匹配(dto);
        }
        return new AjaxResult();
    }
    
    /**
     * 保存/提交
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrWtFxswapDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇掉期投资委托));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrWtFxswapDto> processParameter = new ProcessParameter<EtrWtFxswapDto>();
        WorkFlowParameter<EtrWtFxswapDto> params = new WorkFlowParameter<EtrWtFxswapDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇掉期投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrWtFxswap002") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrWtFxswap002.tradePs") + "(" + SysDataUtil.getNameFromDataDict(DictCode.交易方向,entity.getTradePs())  + ")"+"-" + "("
                      + this.getMessage("EtrWtFxswap002.vdate") + ":" +DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrWtFxswap002.mdate") + ":"
                      +DateUtil.format(entity.getMdate())+ ")-"
                      + this.getMessage("EtrWtFxswap002.tradeAmt") + ":"
                      + entity.getTradeAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrWtFxswapService.start(processParameter, dto);
        return new AjaxResult("处理完成");
    }
    
    
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public BigDecimal getProAvailableCash(@RequestBody Map<String, Object> param){
        return etrWtFxswapService.getProAvailableCash(param);
    }
    
    /**
     * 
     * 查询币种账户信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectPtlCapAcctNo")
    public List<SelectOptionDto> selectPtlCapAcctNo (@RequestBody Map<String, Object> param){
        param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return etrWtFxswapService.selectPtlCapAcctNo(param);
    }
}
