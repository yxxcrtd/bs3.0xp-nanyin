/*
 * 文件名：PtlManagementInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.PtlValParamMapper;
import com.joyintech.tams.common.base.entity.PtlValParam;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.NodeDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo004Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo004Service;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组合管理新建
 * @author gejiaao
 * @version 1.0 2019年8月15日
 * @see PtlManagementInfo004Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManagementInfo004")
@RequestMapping("/invest/portfolio/ptlmanagementinfo/PtlManagementInfo004")
public class PtlManagementInfo004Controller extends BaseController{
    @Autowired
    private PtlManagementInfo004Service ptlManagementInfo004Service;
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;
    @Autowired
    private PtlValParamMapper ptlValParamMapper;


    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.组合类型,DictCode.收益类型
                ,DictCode.募集方式,DictCode.投资性质
                ,DictCode.开放类型,DictCode.是否,DictCode.尾数处理方式);
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        String portfolioId = getParam("portfolioId");
        // 判断是否存在于留痕表
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        PtlManagementInfo004Dto dto = null;
        if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
            dto = ptlManagementInfo004Service.getBussinessEntityByWorkflowId(workflowId);

        }else if (StringUtil.isNotEmpty(portfolioId)) {
            Map<String, Object> map = RequestUtil.getParameters(getRequest());
            //通过组合代码查询
            dto = ptlManagementInfo004Service.getInitInfo(map);

        }
        if (null != dto){
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlManagementInfo004Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlManagementInfo004Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //如果子组合代码为空，则根据自动生成，生成规则为（母组合+自增数）
        if (StringUtil.isEmpty(dto.getChildPortfolioId())){
            //使用母组合创建sequence
            String childPortfolioId = ptlManagementInfo004Service.createChildPortfolioId(dto.getPortfolioId());
            dto.setChildPortfolioId(childPortfolioId);
        }
        ProcessParameter<PtlManagementInfo004Dto> processParameter = new ProcessParameter<PtlManagementInfo004Dto>();
        WorkFlowParameter<PtlManagementInfo004Dto> params = new WorkFlowParameter<PtlManagementInfo004Dto>();
        params.setPageId("PtlManagementInfo004");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(DictCodeList.共通业务类型_组合管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
            entity -> this.getMessage("PtlManagementInfo001.portfolioName") + "("
                      + entity.getPortfolioName() + ")" + "-"
                      + this.getMessage("PtlManagementInfo001.portfolioId") + "("
                      + entity.getPortfolioId() + ")"
                    + this.getMessage("PtlManagementInfo004.childPortfolioId") + "("
                    + entity.getChildPortfolioId() + ")" + "-"
                    + this.getMessage("PtlManagementInfo004.childPortfolioName") + "("
                    +  entity.getChildPortfolioName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlManagementInfo004Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
