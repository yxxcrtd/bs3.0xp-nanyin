/*
 * 文件名：FinProductBuild003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild003Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.service.FinNetValueProductBuild001Service;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.service.FinNetValueProductBuild003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 发行状态管理
 * 
 * @author zhangliang
 * @version 1.0 2018年10月29日
 * @see FinNetValueProductBuild003Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetValueProductBuild003")
@RequestMapping("/invest/product/productelement/netvalueproduct/FinNetValueProductBuild003")
public class FinNetValueProductBuild003Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinNetValueProductBuild003Service finNetValueProductBuild003Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinNetValueProductBuild001Service finNetValueProductBuild001Service;

    /**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.发行状态);

        model.addAttribute("workflowId", StringUtil.defaultString(this.getParam("workflowId")));
        // 金融产品代码Str
        model.addAttribute("finprodIdStr", this.getParam("finprodIdStr"));

        return super.init(model);
    }

    /**
     * 发行状态管理查询
     * 
     * @return 发行状态管理列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryForList")
    public List<FinNetValueProductBuild003Dto> queryForList() {
        List<FinNetValueProductBuild003Dto> dtoList = new ArrayList<FinNetValueProductBuild003Dto>();
        String workflowId = this.getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            ProcessMark pro = finNetValueProductBuild003Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinNetValueProductBuild003Dto entity = (FinNetValueProductBuild003Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinNetValueProductBuild003Dto.class);
                if (!StringUtil.isEmptyList(entity.getSelectedItem())) {
                    dtoList = entity.getSelectedItem();
                }
                else {
                    dtoList.add(entity);
                }
            }
        }
        else {
            dtoList = finNetValueProductBuild003Service.selectForList(this.getParam("finprodIdStr"));
        }
        return dtoList;
    }

    /**
     * 发行状态逻辑check
     * 
     * @param finProductBuild003Dto 列表对象
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid FinNetValueProductBuild003Dto finProductBuild003Dto) {
        AjaxResult ar = new AjaxResult();
        List<ValidMessage> validList = new ArrayList<>();
        if (!StringUtil.isEmptyList(finProductBuild003Dto.getSelectedItem())) {
            StringBuffer sb = new StringBuffer();
            for (FinNetValueProductBuild003Dto entity : finProductBuild003Dto.getSelectedItem()) {
                if (!StringUtil.isNotEmpty(entity.getNewIssueStatus())) {
                    validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0040")));
                }
                if (StringUtil.isNotEmpty(entity.getIssueStatusRemark())) {
                    if (entity.getIssueStatusRemark().length() > 300) {
                        validList.add(new ValidMessage(this.getMessage("FW_E_0007",
                            this.getMessage("FinProductBuild003.table.issueStatusRemark"), 300)));
                    }
                }
                // 流程中数据check
                WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
                List<String> pageIdList = new ArrayList<String>();
                pageIdList.add("FinNetValueProductBuild002");
                pageIdList.add("FinNetValueProductBuild003");
                wflValidInputParameter.setPageIdList(pageIdList);
                wflValidInputParameter.setWorkflowId(entity.getWorkflowId());
                wflValidInputParameter.setBusinessDataPk1(
                    DictCodeListExt1.金融产品类型_理财产品.getValue());
                wflValidInputParameter.setBusinessDataPk2(finProductBuild003Dto.getFinprodId());
                if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                    validList.add(new ValidMessage(
                        getMessage("BUSS_COM_E_0027", getMessage("FinProductScheduling002.prodId"),
                            finProductBuild003Dto.getFinprodId())));
                }

                if (finNetValueProductBuild001Service.queryProdCountByFinprodId(
                    entity.getFinprodId()) == 0) {
                    sb.append(entity.getFinprodId());
                    sb.append(GlobalConstant.COMMA);
                }
            }
            if (StringUtil.isNotEmpty(sb)) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0022",
                    this.getMessage("FinProductBuild003.table.finprodMarketId"),
                    sb.substring(0, sb.length() - GlobalConstant.COMMA.length()))));
            }
        }

        if (StringUtil.isEmptyList(validList)){
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, finProductBuild003Dto.getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, finProductBuild003Dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        else{
            ar.setErrorList(validList);
        }
        
        return ar;
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinNetValueProductBuild003Dto dto)
        throws Exception {
        String msgId = "";
        for (FinNetValueProductBuild003Dto entity : dto.getSelectedItem()) {
            entity.setUpdateUser(dto.getUpdateUser());
            entity.setUpdateTime(dto.getUpdateTime());
            entity.setCreateUser(dto.getCreateUser());
            entity.setCreateTime(dto.getCreateTime());
            entity.setCreateDept(dto.getCreateDept());
            
            ProcessParameter<FinNetValueProductBuild003Dto> processParameter = new ProcessParameter<FinNetValueProductBuild003Dto>();
            WorkFlowParameter<FinNetValueProductBuild003Dto> params = new WorkFlowParameter<FinNetValueProductBuild003Dto>();
            params.setElementId(dto.getElementId());
            params.setPageId(dto.getPageId());
            params.setBusinessCode(entity.getFinprodMarketId());
            params.setBusinessType(DictCodeList.共通业务类型_产品要素管理.getValue());
            // 设置工作流注解
            params.setComment(prodInfo -> {
                return WorkFlowUtil.concatComment(getMessage("FinProductBuild001.table.prodId"),
                    GlobalConstant.OPEN_PARENTHESIS, prodInfo.getFinprodMarketId(),
                    GlobalConstant.CLOSE_PARENTHESIS,
                    getMessage("FinProductBuild003.table.newIssueStatus"),
                    GlobalConstant.OPEN_PARENTHESIS,
                    SysDataUtil.getNameFromDataDict(DictCode.发行状态,
                        prodInfo.getNewIssueStatus()),
                    GlobalConstant.CLOSE_PARENTHESIS);
            });
            processParameter.setWorkFlowParameter(params);
            // 调用工作流事件
            finNetValueProductBuild003Service.start(processParameter, entity);

            msgId = msgId + entity.getFinprodMarketId() + ",";
        }
        if (msgId.length() > 0) {
            msgId = msgId.substring(0, msgId.length() - 1);
        }
        // 返回工作流错误信息
        String msg = this.getMessage("COMMON_I_0004",
            getMessage("FinProductBuild001.table.prodId"), msgId);
        return new AjaxResult(msg);
    }
}
