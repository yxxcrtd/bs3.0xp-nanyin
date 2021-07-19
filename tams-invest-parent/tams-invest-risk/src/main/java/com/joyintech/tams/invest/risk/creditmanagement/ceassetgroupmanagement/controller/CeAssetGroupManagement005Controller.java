package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.controller;


import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement001Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement002Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement005Service;
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
import java.util.Map;

@Controller
@PageId("CeAssetGroupManagement005")
@RequestMapping("/invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement005")
public class CeAssetGroupManagement005Controller extends BaseController {

    @Autowired(required = false)
    private CeAssetGroupManagement001Service ceAssetGroupManagement001Service;

    @Autowired(required = false)
    private CeAssetGroupManagement002Service ceAssetGroupManagement002Service;

    @Autowired(required = false)
    private CeAssetGroupManagement005Service ceAssetGroupManagement005Service;


    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        model.addAttribute("DATALIST", JSONArray.parseArray((String) map.get ("dataList")));

        String workflowId = getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            model.addAttribute ("workflowId", workflowId);
            CeAssetGroupManagement001Dto ceAssetGroupManagement001Dto = ceAssetGroupManagement002Service.getBussinessEntityByWorkflowId(workflowId);
            HashMap<String, Object> maps = new HashMap<> ();
            maps.put("seqno",ceAssetGroupManagement001Dto.getSeqno ());
            Page<CeAssetGroupManagement001Dto> pageList = ceAssetGroupManagement001Service.getAssetBySeqno(maps);
                model.addAttribute("PAGELIST", pageList.getRoot ());
        }


        return super.init(model);
    }

    /**
     *
     * <一句话功能简述>
     * 页面查询
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CeAssetGroupManagement001Dto> findPage() {
            return ceAssetGroupManagement001Service.getAssetBySeqno (RequestUtil.getParameters(getRequest()));
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto
     *            dto
     * @param result
     *            BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) CeAssetGroupManagement001Dto dto,
                            BindingResult result) {
        if (result.getAllErrors().isEmpty()) {
            AjaxResult ar = commonCheck(result, dto.getOperateType());
            return ar;
        }
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @param
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) CeAssetGroupManagement001Dto dto, BindingResult br)
            throws Exception {
        if(br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CeAssetGroupManagement001Dto> processParameter = new ProcessParameter<CeAssetGroupManagement001Dto>();
        // 工作流参数设置
        WorkFlowParameter<CeAssetGroupManagement001Dto> params = new WorkFlowParameter<CeAssetGroupManagement001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 业务代码生成参数
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产系列授信);
            dto.getSeqno();
        }
        //设置业务编号
        String businessCode = dto.getSeqno();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        //设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeAssetGroupManagement001.seqno"),
                    GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                    getMessage("CeAssetGroupManagement002.name"));
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroupManagement005Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }


}
