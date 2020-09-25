/*
 * 文件名：EtrBoutRepo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrboutrepo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.invest.entrust.etrboutrepo.service.EtrBoutRepoService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月10日
 * @see EtrBoutRepo002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBoutRepo002")
@RequestMapping("/invest/entrust/etrboutrepo/EtrBoutRepo002")
public class EtrBoutRepo002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private EtrBoutRepoService etrBoutRepoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        EtrBoutRepoDto dto = new EtrBoutRepoDto();
        setSelectOptionToModel(model, DictCode.交易场所);
        setSelectOptionToModel(model, DictCode.回购拆借方向);
        setSelectOptionToModel(model, DictCode.币种);
        setSelectOptionToModel(model, DictCode.清算速度);
        setSelectOptionToModel(model, DictCode.回购期限品种);
        setSelectOptionToModel(model, DictCode.价格模式);
        model.addAttribute("PRICEMODEL_03", DictCodeListExt2.价格模式_市价指令.getValue());
        model.addAttribute("ps_03", DictCodeList.回购拆借方向_正回购.getValue());
        model.addAttribute("ps_04", DictCodeList.回购拆借方向_逆回购.getValue());
        model.addAttribute("tradeP01", DictCodeListExt1.交易场所_银行间.getValue());
//        model.addAttribute("tradeP02", DictCodeListExt1.交易场所_上海证券交易所.getValue());
//        model.addAttribute("tradeP03", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("speed0", DictCodeList.清算速度_T_0.getValue());
        model.addAttribute("speed1", DictCodeList.清算速度_T_1.getValue());
        model.addAttribute("GC001", DictCodeList.回购期限品种_GC001.getValue());
        model.addAttribute("GC002", DictCodeList.回购期限品种_GC002.getValue());
        model.addAttribute("GC003", DictCodeList.回购期限品种_GC003.getValue());
        model.addAttribute("GC004", DictCodeList.回购期限品种_GC004.getValue());
        model.addAttribute("GC007", DictCodeList.回购期限品种_GC007.getValue());
        model.addAttribute("GC014", DictCodeList.回购期限品种_GC014.getValue());
        model.addAttribute("GC028", DictCodeList.回购期限品种_GC028.getValue());
        model.addAttribute("GC091", DictCodeList.回购期限品种_GC091.getValue());
        model.addAttribute("GC182", DictCodeList.回购期限品种_GC182.getValue());
        model.addAttribute("R001", DictCodeList.回购期限品种_R001.getValue());
        model.addAttribute("R007", DictCodeList.回购期限品种_R007.getValue());
        model.addAttribute("R014", DictCodeList.回购期限品种_R014.getValue());
        model.addAttribute("R021", DictCodeList.回购期限品种_R021.getValue());
        model.addAttribute("RC001", DictCodeList.回购期限品种_RC001.getValue());
        model.addAttribute("RC003", DictCodeList.回购期限品种_RC003.getValue());
        model.addAttribute("RC007", DictCodeList.回购期限品种_RC007.getValue());
        model.addAttribute("R_001", DictCodeList.回购期限品种_R_001.getValue());
        model.addAttribute("R_002", DictCodeList.回购期限品种_R_002.getValue());
        model.addAttribute("R_003", DictCodeList.回购期限品种_R_003.getValue());
        model.addAttribute("R_004", DictCodeList.回购期限品种_R_004.getValue());
        model.addAttribute("R_007", DictCodeList.回购期限品种_R_007.getValue());
        model.addAttribute("R_014", DictCodeList.回购期限品种_R_014.getValue());
        model.addAttribute("R_028", DictCodeList.回购期限品种_R_028.getValue());
        model.addAttribute("R_091", DictCodeList.回购期限品种_R_091.getValue());
        model.addAttribute("R_182", DictCodeList.回购期限品种_R_182.getValue());
        model.addAttribute("R1M", DictCodeList.回购期限品种_R1M.getValue());
        model.addAttribute("R2M", DictCodeList.回购期限品种_R2M.getValue());
        model.addAttribute("R3M", DictCodeList.回购期限品种_R3M.getValue());
        model.addAttribute("R4M", DictCodeList.回购期限品种_R4M.getValue());
        model.addAttribute("R6M", DictCodeList.回购期限品种_R6M.getValue());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        RequestUtil.getPageParam();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = etrBoutRepoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("entity_bussRepoSecDto", JsonUtils.object2Json(dto.getBussRepoSec()));
            model.addAttribute("businessId",dto.getWorkflowId());
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            dto = etrBoutRepoService.selectByKey(entrustId);
            if(dto.getSettleSpeed()!=null){
                if(dto.getSettleSpeed()==0){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_0.getValue());
                }else if(dto.getSettleSpeed()==1){
                    dto.setSettleSpreed(DictCodeList.清算速度_T_1.getValue());
                }
            }
            dto.setEntrustAmt(dto.getEntrustNum());
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getEntrustId());
            model.addAttribute("entrustId", dto.getEntrustId());
        }
      //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            dto.setVdate(sysDate);
            dto.setMdate(sysDate);
            dto.setTradeMarket(DictCodeListExt1.交易场所_银行间.getValue());
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype_zhg", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        model.addAttribute("fintype_nhg", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F10",DictCodeList.平安资产类型_买断式回购.getValue());
        return super.init(model);
    }
    /**
     * 获取组合代码联想文本框信息 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getentrustIdAdd")
    public Page<SelectOptionDto> getentrustIdAdd()
        throws Exception {
        Page<SelectOptionDto> temp = etrBoutRepoService.getentrustIdAdd(RequestUtil.getPageParam());
        return temp;
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
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return etrBoutRepoService.getProAvailableCash(param);
    }
    /**
     * 
     * 查询标准券额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectStandardAmt")
    public BigDecimal selectStandardAmt(@RequestBody Map<String, Object> map){
        return etrBoutRepoService.selectStandardAmt(map);
    }
    /**
     * 查询证券托管户：联想框文本框
     *
     * @param params 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAcctId(@RequestParam Map<String, Object> params) {
        return etrBoutRepoService.getSecManageAcctId(params);
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
        ValidateGroupEdit.class}) EtrBoutRepoDto dto, BindingResult result) {
        String confirmMsg = "";
        String changeLineMark = "<br/>";
        // 指令可用持仓软校验 by 赵东东 20200121 start
        if(dto.getEntrustAmt() != null && dto.getAssetAvailablePosition() != null && dto.getEntrustAmt().divide(new BigDecimal(100)).compareTo(dto.getAssetAvailablePosition()) ==1&&StringUtil.equals(
            CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
            DictCodeListExt1.是或否_是.getValue())){
            confirmMsg= this.getMessage("ETR_X_011",this.getMessage("EtrBoutRepo002.entrustAmt"));
        }
        // 指令可用现金软校验 by 赵东东  20200121 start
        if (dto.getEntrustAmt() != null && dto.getProAvailableCash() != null
            && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())&&StringUtil.equals(
                CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_是.getValue())) {
            if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                confirmMsg= this.getMessage("ETR_X_010",this.getMessage("EtrRepo002.entrustAmt"));
            }
        }
        if(StringUtil.isNotEmpty(confirmMsg)) {
            confirmMsg +=changeLineMark;
            confirmMsg +=getMessage("请确认是否继续?");
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(confirmMsg);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrBoutRepoDto dto) {
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
    public AjaxResult save(@RequestBody EtrBoutRepoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrBoutRepoDto> processParameter = new ProcessParameter<EtrBoutRepoDto>();
        WorkFlowParameter<EtrBoutRepoDto> params = new WorkFlowParameter<EtrBoutRepoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrBoutRepo001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrBoutRepo002.ps") + "(" + SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,entity.getPs())  + ")"+"-" + "("
                      + this.getMessage("EtrBoutRepo002.vdate") + ":" +DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrBoutRepo002.mdate") + ":"
                      +DateUtil.format(entity.getMdate())+ ")-"
                      + this.getMessage("EtrBoutRepo002.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrBoutRepoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 判断是否节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("judgeIsHday")
    @ResponseBody
    public Map<String, Object> judgeIsHday(@RequestBody Map<String, Object> param){
        return etrBoutRepoService.judgeIsHday(param);
    }
}
