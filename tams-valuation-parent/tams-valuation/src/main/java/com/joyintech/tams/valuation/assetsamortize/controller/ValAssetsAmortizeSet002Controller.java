/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.joyintech.framework.grid.Page;
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
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.valuation.assetsamortize.service.ValAssetsAmortizeSet001Service;
import com.joyintech.tams.valuation.assetsamortize.service.ValAssetsAmortizeSet002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 资产摊销设置
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet002Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetsAmortizeSet002")
@RequestMapping("/valuation/assetsamortize/ValAssetsAmortizeSet002")
public class ValAssetsAmortizeSet002Controller extends BaseController {

    /**
     * valAssetsAmortizeSet001Service
     */
    @Autowired
    private ValAssetsAmortizeSet001Service valAssetsAmortizeSet001Service;

    /**
     * valAssetsAmortizeSet002Service
     */
    @Autowired
    private ValAssetsAmortizeSet002Service valAssetsAmortizeSet002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
//        model.addAttribute("FINPROD_ID",
//            JsonUtils.objectArray2Json(valAssetsAmortizeSet001Service.getFinprodId().toArray()));
        String workflowId = getParam("workflowId");
        setSelectOptionToModel(model, DictCode.重算方式);
        if (StringUtil.isNotEmpty(workflowId)) {
            ValAssetsAmortizeSet001Dto dto = valAssetsAmortizeSet002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("finprodId", dto.getBusiId());
            // 双击查看
            model.addAttribute("workflowId", workflowId);

        }
        else {
            model.addAttribute("finprodId", null);
            model.addAttribute("workflowId", null);
        }
        return super.init(model);
    }

    /**
     * 新建表格初始化 不要求分页
     * 
     * @return List<ValAssetsAmortizeSet001Dto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<ValAssetsAmortizeSet001Dto> findPage()
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        String workflowId = (String)map.get("workflowIdNow");
        if (StringUtil.isNotEmpty(workflowId)) {
            ValAssetsAmortizeSet001Dto comdto = valAssetsAmortizeSet002Service.getBussinessEntityByWorkflowId(
                workflowId);
            List<ValAssetsAmortizeSet001Dto> list = new ArrayList<ValAssetsAmortizeSet001Dto>();
            comdto.setRecalType(
                SysDataUtil.getNameFromDataDict(DictCode.重算方式, comdto.getRecalTypeVal()));
            list.add(comdto);
            return list;
        }
        else {

            return valAssetsAmortizeSet002Service.findAll(map);
        }
    }

    /**
     * 表格 下拉框加载
     * 
     * @return List<SelectOptionDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getRecalType")
    public List<SelectOptionDto> getRecalType()
        throws Exception {
        return valAssetsAmortizeSet002Service.getRecalType();
    }

    /**
     * 删除前判断
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCommitCheck")
    public AjaxResult check(@Valid @RequestBody ValAssetsAmortizeSet001Dto dto,
                            BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 新建走流程
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     */
    @RequestMapping("doCommit")
    @ResponseBody
    public AjaxResult doCommit(@RequestBody @Valid ValAssetsAmortizeSet001Dto dto,
                               BindingResult br)
        throws Exception {

        // 启动流程
        ProcessParameter<ValAssetsAmortizeSet001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetsAmortizeSet001Dto> params = new WorkFlowParameter<>();

        // 业务代码（资产代码）
        params.setBusinessCode(item -> item.getBusiId() + item.getBooksetId() + item.getCdate());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_资产摊销设置.getValue());
        // 页面id
        params.setPageId(this.getPageId());
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> entity.getBusiId() + entity.getBooksetId() + entity.getCdate());
        proParam.setWorkFlowParameter(params);
        String msg = valAssetsAmortizeSet002Service.start(proParam,
            dto.getListValAssetsAmortizeSet001Dto().toArray(
                new ValAssetsAmortizeSet001Dto[dto.getListValAssetsAmortizeSet001Dto().size()]));

        return new AjaxResult(msg);
    }

    /**
     * 资产代码下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getSubjectNo(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return valAssetsAmortizeSet001Service.getFinprodId2(param);
    }

}
