/*
 * 文件名：EtrWtSpotex002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年6月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrwtspotex.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

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
import com.joyintech.tams.invest.entrust.etrwtspotex.dto.EtrWtSpotexDto;
import com.joyintech.tams.invest.entrust.etrwtspotex.service.EtrWtSpotexService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇即期委托新建页面 〈功能详细描述〉
 * 
 * @author wangguoxian
 * @version 1.0 2019年6月24日
 * @see EtrWtSpotex002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrWtSpotex002")
@RequestMapping("/invest/entrust/etrwtspotex/EtrWtSpotex002")
public class EtrWtSpotex002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 画面端service
     */
    @Autowired
    private EtrWtSpotexService etrWtSpotexService;

    /**
     * 初始化外汇即期委托新建页面
     */
    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.货币对);
        this.setSelectOptionToModel(model, DictCode.金融产品类型_投管用);
        this.setSelectOptionToModel(model, DictCode.外汇交易类型);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.清算速度);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        model.addAttribute("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("JPY_CNY", DictCodeList.货币对_JPY_CNY.getValue());

        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            EtrWtSpotexDto dto = etrWtSpotexService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity",
                JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", dto.getWorkflowId());
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            EtrWtSpotexDto dto = etrWtSpotexService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("businessId", dto.getEntrustId());
            model.addAttribute("person_entity",
                JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
        }
        // 新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            EtrWtSpotexDto entity = new EtrWtSpotexDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        model.addAttribute("fintype_spotex", DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        //----add by jjpei 20190718--------委托监控风险试算-----------start--------
        model.addAttribute("MY_SUP_TIME_TYPE_01", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //----add by jjpei 20190718--------委托监控风险试算-----------end--------
        model.addAttribute("PINAN_ASSET_TYPE_F13",DictCodeListExt2.平安资产类型_外汇即期.getValue());
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
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrWtSpotexService.getProAvailableCash(param);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrWtSpotexDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        // 货币对
        String curPair = dto.getCurPair();
        // 交易货币
        String dealCur = dto.getCcy();
        // 基准货币金额
        BigDecimal baseCurAmt = null;
        // 基准货币可用持仓
        BigDecimal baseCurPos = null;
        // 非基准货币金额
        BigDecimal noBaseCurAmt = null;
        // 非基准货币可用持仓
        BigDecimal noBaseCurPos = null;

        if (curPair.indexOf(dealCur) == 0){
            // 交易货币是基准货币
            baseCurAmt = dto.getEntrustAmt();
            baseCurPos = dto.getProAvailableCash();
            // 对应货币是非基准货币
            noBaseCurAmt = dto.getExpCorAmt();
            noBaseCurPos = dto.getCorCurPos();

        }
        else if (curPair.indexOf(dealCur) > 0){
            // 交易货币是非基准货币
            noBaseCurAmt = dto.getEntrustAmt();
            noBaseCurPos = dto.getProAvailableCash();
            // 对应货币是基准货币
            baseCurAmt = dto.getExpCorAmt();
            baseCurPos = dto.getCorCurPos();
        }

        // 当交易方向为买入时，非基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            if (noBaseCurAmt!=null&&noBaseCurAmt.compareTo(noBaseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrWtSpotex.t01"),
                                this.getMessage("EtrWtSpotex.noBaseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        // 当交易方向为卖出时，基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (baseCurAmt!=null&&baseCurAmt.compareTo(baseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrWtSpotex.t02"),
                                this.getMessage("EtrWtSpotex.baseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }


        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrWtSpotexDto dto) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) EtrWtSpotexDto dto,
                           BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇即期投资委托));
            // 一级分类明细项？
            // param.setType1(DictCodeList.业务编号一级分类_理财直融);
            String code = bussGetBussinessIdService.buildBussinessCode(param);
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrWtSpotexDto> processParameter = new ProcessParameter<EtrWtSpotexDto>();
        WorkFlowParameter<EtrWtSpotexDto> params = new WorkFlowParameter<EtrWtSpotexDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇即期投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrWtSpotex001") + "(" + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("EtrWtSpotex.ps") + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs()) + ")" + "-"
                      + "(" + this.getMessage("EtrWtSpotex.vdate") + ":"
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + this.getMessage("EtrWtSpotex.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrWtSpotex.entrustAmt") + ":" + entity.getEntrustAmt()
                      + entity.getCcyName());
        processParameter.setWorkFlowParameter(params);
        String msg = etrWtSpotexService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
