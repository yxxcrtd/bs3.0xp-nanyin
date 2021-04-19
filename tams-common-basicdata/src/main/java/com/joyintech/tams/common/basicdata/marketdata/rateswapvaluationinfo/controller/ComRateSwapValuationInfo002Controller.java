/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.constant.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service.ComRateSwapValuationInfo001Service;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service.ComRateSwapValuationInfo002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换估值信息
 * 
 * @author zhangwei
 * @version 1.0 2019-07-26
 * @see ComRateSwapValuationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ComRateSwapValuationInfo002")
@RequestMapping("/common/basicdata/marketdata/rateswapvaluationinfo/ComRateSwapValuationInfo002")
public class ComRateSwapValuationInfo002Controller extends BaseController {

    @Autowired
    private ComRateSwapValuationInfo002Service comRateSwapValuationInfo002Service;

    @Autowired
    private ComRateSwapValuationInfo001Service comRateSwapValuationInfo001Service;
    /**
     * 税务方案 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String finprodId2 = getParam("finprodId2");
        ComRateSwapValuationInfo002Dto dto = new ComRateSwapValuationInfo002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(finprodId2)) { // 查看 、 修改
            dto = comRateSwapValuationInfo002Service.findByPrimaryKey(RequestUtil.getPageParam());

        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = comRateSwapValuationInfo002Service.getBussinessEntityByWorkflowId(workflowId);
        }

        model.addAttribute(GlobalConstant.ENTITY, dto);
        //
        super.setSelectOptionToModel(model, DictCode.币种);
        super.setSelectOptionToModel(model, DictCode.估值来源);
        super.setSelectOptionToModel(model, DictCode.估值方法);
        super.setSelectOptionToModel(model, DictCode.录入方式);
        return super.init(model);
    }

    /**
     * 交易编号获取
     *
     * @return 返回的数据
     */
    @RequestMapping("/getFinprodId2")
    @ResponseBody
    public List<SelectOptionDto> getFinprodId2(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        param.put("finprodType", DictCodeListExt1.金融产品类型_利率互换.getValue());
        return comRateSwapValuationInfo001Service.getFinprodId2(param);
    }
    
    /**
     * 提交/保存 check
     * 
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ComRateSwapValuationInfo002Dto dto, BindingResult bindingResult)
        throws Exception {
        String operateType = dto.getOperateType();

        if (!bindingResult.hasErrors()) {
            /** modify by yanya on 20191105 修改页面不作数据唯一性校验**/
            if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
                // 判断数据库是否存在：主键check
                Map<String, Object> map = Maps.newHashMap();
                map.put("finprodId2", dto.getFinprodId2());
                map.put("datasourceType", dto.getDatasourceType());
                map.put("valDate", dto.getValDate());
                ComRateSwapValuationInfo002Dto entity = comRateSwapValuationInfo002Service.findByPrimaryKey(map);
                if (entity != null) {
                    return new AjaxResult("已存在交易编号_估值来源_估值日期相同的数据，请确保数据唯一性。", MessageType.错误消息);
                }
            }
        }

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 新建或修改 并启动流程
     * 
     * @param dto
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ComRateSwapValuationInfo002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<ComRateSwapValuationInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ComRateSwapValuationInfo002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_利率互换估值信息.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("利率互换估值信息", entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            comRateSwapValuationInfo002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动利率互换估值信息流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }
}
