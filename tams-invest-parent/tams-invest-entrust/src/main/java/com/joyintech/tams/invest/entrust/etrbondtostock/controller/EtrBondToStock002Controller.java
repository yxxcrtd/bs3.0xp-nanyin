package com.joyintech.tams.invest.entrust.etrbondtostock.controller;

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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.tams.invest.entrust.etrbondtostock.service.EtrBondToStockService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月23日
 * @see EtrBondToStock002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBondToStock002")
@RequestMapping("/invest/entrust/etrbondtostock/EtrBondToStock002")
public class EtrBondToStock002Controller extends BaseController{
    
    /**
     * 自定义service
     */
    @Autowired
    private EtrBondToStockService firstService;
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        String dtoStr = (String)map.get("dtoStr");
        Date sysDate = DateUtil.platDateFromCache();
        if(StringUtil.isNotEmpty(dtoStr)) {
        	EtrBondToStockDto temp = JsonUtils.json2Object(dtoStr, EtrBondToStockDto.class);
        	String secAcctId = temp.getSecAcctId();
        	boolean yesOrNo = firstService.getYesOrNo(secAcctId);
        	if(yesOrNo==false) {
        		temp.setSecAcctId("");
        		temp.setChlId(secAcctId);
        	}
            temp.setPs(DictCodeListExt3.交易方向_债转股.getValue());
            temp.setVdate(sysDate);
            temp.setMdate(sysDate);
            model.addAttribute("MODEL_SHOW","01");
            model.addAttribute(GlobalConstant.ENTITY, temp);
        }else {
        	EtrBondToStockDto temp = new EtrBondToStockDto();
        	 temp.setPs(DictCodeListExt3.交易方向_债转股.getValue());
             temp.setVdate(sysDate);
             temp.setMdate(sysDate);
             model.addAttribute(GlobalConstant.ENTITY, temp);
        }
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.存款类资产类型);
        this.setSelectOptionToModel(model, DictCode.投资目的);
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = getParam("entrustId");
        String finprodId = getParam("finprodId");
        String portfolioId = getParam("portfolioId");
        //
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	EtrBondToStockDto etrBondToStockDto = firstService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, etrBondToStockDto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(etrBondToStockDto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", workflowId);
        }
        else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {
        	String date = DateUtil.format(sysDate);
        	EtrBondToStockDto etrBondToStockDto = firstService.selectByKey(entrustId,finprodId,portfolioId,date);
            model.addAttribute(GlobalConstant.ENTITY, etrBondToStockDto);
            model.addAttribute("tradeId", entrustId);
            model.addAttribute("person_entity", JsonUtils.object2Json(etrBondToStockDto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", etrBondToStockDto.getEntrustId());
            
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("etrPs",DictCodeListExt3.交易方向_债转股.getValue());
        model.addAttribute("PS_ZQMC",DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("PA_TYPE_ZQMC",DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
        model.addAttribute("PA_TYPE_ZZG",DictCodeListExt3.平安业务类型_债转股.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));     
        return super.init(model);
    }
    
    @RequestMapping("getPositionInfo")
    @ResponseBody
    public AjaxResult getPositionInfo(){
        AjaxResult ajaxResult = new AjaxResult();
        Map<String,Object> retMap = firstService.getPositionInfo(RequestUtil.getPageParam());
        ajaxResult.setData(retMap);
        return ajaxResult;
    }
    
    /**
     * 获取新建界面的转债代码联想下拉框框
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd() 
        throws Exception {
        Page<SelectOptionDto> temp = firstService.getFinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
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
        ValidateGroupEdit.class}) EtrBondToStockDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrBondToStockDto dto) {
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
    public AjaxResult save(@RequestBody EtrBondToStockDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_可转债转股委托));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrBondToStockDto> processParameter = new ProcessParameter<EtrBondToStockDto>();
        WorkFlowParameter<EtrBondToStockDto> params = new WorkFlowParameter<EtrBondToStockDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_可转债转股委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        //业务说明：债转股委托+"("+组合代码+")"+"-"+（委托日开始日："+委托开始日+"）-"+"（委托结束日："+委托结束日+"）
        params.setComment(
            entity -> getMessage("EtrBondToStock002")+ "(" + entity.getPortfolioId()+ ")-" 
                 +  "-("
                + getMessage("EtrBondToStock002.vdate")+"：" + DateUtil.format(entity.getVdate()) + ")-("
                + getMessage("EtrBondToStock002.mdate")+"：" + DateUtil.format(entity.getMdate()) + ")-"
                );
        processParameter.setWorkFlowParameter(params);
        String msg = firstService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
