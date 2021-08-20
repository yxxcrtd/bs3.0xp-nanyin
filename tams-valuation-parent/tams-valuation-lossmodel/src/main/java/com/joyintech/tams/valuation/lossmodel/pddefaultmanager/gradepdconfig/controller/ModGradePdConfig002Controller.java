/*
 * 文件名：ModGradePdConfig002Controller.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */
package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
//import com.joyintech.tams.model.impairment.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
//import com.joyintech.tams.model.impairment.pddefaultmanager.gradepdconfig.service.ModGradePdConfigService;
//import com.joyintech.tams.model.impairment.pddefaultmanager.pdfactorconfig.service.PdFactorConfig001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.service.ModGradePdConfigService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Description:评级PD维护新建
 * @auther: yuzj
 * @date: 2021/7/1
 * @param:
 * @return:
 */
@Controller
@PageId("ModGradePdConfig002")
@RequestMapping("/model/impairment/pddefaultmanager/gradepdconfig/ModGradePdConfig002")
public class ModGradePdConfig002Controller extends BaseController {

    /**
     * 注入评级PD维护service
     */
    @Autowired
    private ModGradePdConfigService modGradePdConfigService;

    /**
     * @Description:页面初始化
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        ModGradePdConfigDto dto = new ModGradePdConfigDto();
        this.setSelectOptionToModel(model, DictCode.评级类型);
        this.setSelectOptionToModel(model, DictCode.评级机构);
        this.setSelectOptionToModel(model, DictCode.内部评级);
        String seqNo = getParam("seqNo");
        String workflowId = this.getParam(com.joyintech.tams.valuation.common.constants.GlobalConstant.WORKFLOW_ID);// 工作流ID
        if(StringUtils.isNotEmpty(seqNo)){
            ModGradePdConfigDto pddto = new ModGradePdConfigDto();
            pddto.setSeqNo(seqNo);
            dto = modGradePdConfigService.selectByPrimaryKey(pddto);
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = modGradePdConfigService.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute(GlobalConstant.ENTITY,dto);
        return super.init(model);
    }

    /**
     * 查询、修改和新建按钮点击时的check
     * 
     * @param dto 验证的实体类
     * @param result 错误结果
     * @return AjaxResult 错误结果信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ModGradePdConfigDto dto, BindingResult result) {

        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        //根据重复性校验（年份、季度、评级类型、评级结果）
        //注意：编辑（校验排除注意自身）新增则库中不可存在重复项
        String message="";
        Boolean flag = modGradePdConfigService.getRepeatCheck(dto);
        //true 表示有重复 false表示无重复
        if(flag){
            message = "年份、评级结果有重复，不可提交！";
            return new AjaxResult(message, MessageType.错误消息);
        }

        //校验前瞻前年度边际PD%（小数位后可保留四位，区间为（0，100】，左开右闭）
        BigDecimal minDec = new BigDecimal (0);
        BigDecimal maxDec = new BigDecimal (100);
        if(dto.getForwardYearPd().compareTo(maxDec) >0 || dto.getForwardYearPd().compareTo(minDec) <=0){
            message = "评级PD维护信息不符合规范！前瞻前年度边际PD%区间应为(0，100]";
            return new AjaxResult(message, MessageType.错误消息);
        }

        //如果是编辑
        if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            Map<String, Object> map = RequestUtil.getPageParam();
            map.put("seqNo",dto.getSeqNo());
            map.put("configType",ModelDictCodeList.模型_减值配置类型_评级PD配置.getValue());//03为评级PD维护配置类型
            int exists = modGradePdConfigService.checkBySeqNoAndType(map);
            //评级PD维护是否被PD生成批处理调用过
            if(exists>0) {
                ajaxResult.setData(this.getMessage("本次修改是否影响历史数据？", this.getMessage("SysCommon.save")));
                return ajaxResult;
            }
        }

        return commonCheck(result, dto.getOperateType());
    }

    /**
     * @Description:工作流，处理数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param: 
     * @return: 
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody ModGradePdConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getSeqNo())){
            dto.setSeqNo(IDGenerator.generate());
        }
        String businessCode = dto.getSeqNo();
        ProcessParameter<ModGradePdConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ModGradePdConfigDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_评级PD维护信息.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "年份" + "-" + entity.getVouchYear()+ "当前日期" + "-"+DateUtil.getDateTimestamp() );
        processParameter.setWorkFlowParameter(params);
        String msg ="";
        if(dto.getSelectedItem() !=null && dto.getSelectedItem().size()>0){
            msg = modGradePdConfigService.start(processParameter,
                    dto.getSelectedItem().toArray(
                            new ModGradePdConfigDto[dto.getSelectedItem().size()]));
        }else{
            msg = modGradePdConfigService.start(processParameter, dto);
        }

        return new AjaxResult(msg);
    }
}
