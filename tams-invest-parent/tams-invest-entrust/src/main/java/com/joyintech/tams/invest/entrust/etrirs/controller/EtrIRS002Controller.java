/*
 * 文件名：EtrIRS002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrirs.controller;

import java.util.Date;
import java.util.Map;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.tams.invest.entrust.etrirs.service.EtrIRSService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换委托新建页面 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11
 * @see EtrIRS002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrIRS002")
@RequestMapping("/invest/entrust/etrirs/EtrIRS002")
public class EtrIRS002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 画面端service
     */
    @Autowired
    private EtrIRSService etrIRSService;

    /**
     * 初始化利率互换委托新建页面
     */
    @Override
    public String init(Model model) {

    	this.setSelectOptionToModel(model, DictCode.币种);
    	this.setSelectOptionToModel(model, DictCode.利率行情);
        this.setSelectOptionToModel(model, DictCode.互换方式);
        this.setSelectOptionToModel(model, DictCode.期限品种_利率互换);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        Date sysDate = DateUtil.platDateFromCache();
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	EtrIRSDto dto = etrIRSService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity",
                JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", dto.getWorkflowId());
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
        	EtrIRSDto dto = etrIRSService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("businessId", dto.getEntrustId());
            model.addAttribute("person_entity",
                JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
        }
        // 新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
        	EtrIRSDto entity = new EtrIRSDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        model.addAttribute("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype_spotex", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F16",DictCodeListExt2.平安资产类型_利率互换.getValue());
        return super.init(model);
    }

    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrWtSpotexService.getProAvailableCash(param);
    }*/

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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrIRSDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        // 当交易方向为卖出时，交易货币金额不大于交易货币可用持仓；否则提交时弹框提醒但不禁止提交
        /*if (dto.getExpCorAmt() != null && dto.getCorCurPos() != null
            && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            if (dto.getExpCorAmt().compareTo(dto.getCorCurPos()) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                    this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrWtSpotex.expCorAmt"),
                        this.getMessage("EtrWtSpotex.corCurPos")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        // 当交易方向为买入时，预计对应货币金额不大于对应货币可用持仓；否则提交时弹框提醒但不禁止提交
        if (dto.getEntrustAmt() != null && dto.getProAvailableCash() != null
            && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                    this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrWtSpotex.entrustAmt"),
                        this.getMessage("EtrWtSpotex.proAvailableCash")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }
        // 当交易方向为卖出时，交易货币金额不大于交易货币可用持仓；否则提交时弹框提醒但不禁止提交
        if (dto.getEntrustAmt() != null && dto.getProAvailableCash() != null
            && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(this.getMessage("ETR_SPOTEX_001",
                    this.getMessage("EtrWtSpotex.entrustAmt"), this.getMessage("交易货币可用持仓")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }*/

        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrIRSDto dto) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) EtrIRSDto dto,
                           BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_利率互换投资委托));
            // 一级分类明细项？
            // param.setType1(DictCodeList.业务编号一级分类_理财直融);
            String code = bussGetBussinessIdService.buildBussinessCode(param);
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrIRSDto> processParameter = new ProcessParameter<EtrIRSDto>();
        WorkFlowParameter<EtrIRSDto> params = new WorkFlowParameter<EtrIRSDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
//        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换投资委托.getValue());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrIRS001") + "(" + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("EtrIRS.busiPs") + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.互换方式, entity.getBusiPs()) + ")" + "-"
                      + "(" + this.getMessage("EtrIRS.vdate") + ":"
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + this.getMessage("EtrIRS.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrIRS.entrustAmt") + ":" + entity.getEntrustAmt()
                      );
        processParameter.setWorkFlowParameter(params);
        String msg = etrIRSService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
