package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.controller;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint002Service;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 组合关联监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPoint002Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionPortfPoint002")
@RequestMapping("/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint002")
public class RskSupervisionPortfPoint002Controller extends BaseController {
	
	/**
     * RskSupervisionPortfPoint002Service
     */
    @Autowired(required = false)
    private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;
    /**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.指标类型,
        BussinessDictCodeRds.监督类型,BussinessDictCodeRds.单位,BussinessDictCodeRds.金融产品类型,
        BussinessDictCodeRds.WIND一级分类_含债券一级分类等,BussinessDictCodeRds.基金类资产二级类型,BussinessDictCodeRds.资产评级,BussinessDictCodeRds.申万行业一级类型
        };

    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DICT_CODES);
    	 RskPortfolioIndicator002Dto rskPortfolioIndicator002Dto=new RskPortfolioIndicator002Dto();
         // 待办跳转
         if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
        	 String workflowId = getParam("workflowId");
             model.addAttribute("workflowId", workflowId);
             rskPortfolioIndicator002Dto = rskSupervisionPortfPoint002Service.getBussinessEntityByWorkflowId(workflowId);
             model.addAttribute(GlobalConstant.ENTITY, rskPortfolioIndicator002Dto);
            
             model.addAttribute("formTodoList", true);
         } else{
        	 rskPortfolioIndicator002Dto.setPortfolioId(getParam("portfolioId"));
             rskPortfolioIndicator002Dto.setPortfolioName(getParam("portfolioName"));
             rskPortfolioIndicator002Dto.setPortfolioLevel(getParam("portfolioLevel"));
             model.addAttribute(GlobalConstant.ENTITY,rskPortfolioIndicator002Dto);
         }
         if(StringUtil.equals(rskPortfolioIndicator002Dto.getPortfolioLevel(), BussinessDictCodeListRds.组合层次_组合包.getValue()))
         {
        	 model.addAttribute("portfolioMixName",rskSupervisionPortfPoint002Service.getPortfolioConst(rskPortfolioIndicator002Dto.getPortfolioId()));
         }
        model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue());
        return super.init(model);
    }
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryRelatedIndList")
    public Page<RskSupervisionIndicator002Dto> queryRelatedIndList()
        throws Exception {
        return rskSupervisionPortfPoint002Service.queryRelatedIndList(
            RequestUtil.getParameters(getRequest()));
    }
   
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskPortfolioIndicator002Dto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskPortfolioIndicator002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_限额组合关联监督指标管理);
        ProcessParameter<RskPortfolioIndicator002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskPortfolioIndicator002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        String businessCode = dto.getPortfolioId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_限额组合关联监督指标管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionPortfPoint002.portfolioId"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionPortfPoint002.portfolioName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getPortfolioName(), GlobalConstant.CLOSE_PARENTHESIS,getMessage("RskSupervisionPortfPoint001.portfolioLevel"), GlobalConstant.OPEN_PARENTHESIS,
                SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.组合层次, entity.getPortfolioLevel()), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionPortfPoint002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
