package com.joyintech.tams.invest.product.rddesign.productelementdes.controller;
/**
 * 产品要素设计-查询
 * @author jiangyunlong
 * @date 2019-9-25
 */

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes002Dto;
import com.joyintech.tams.invest.product.rddesign.productelementdes.service.ProductelementdesService002;
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

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@PageId("Productelementdes002")
@RequestMapping("/invest/product/rddesign/productelementdes/productelementdes002")
public class Productelementdes002Controller extends BaseController {

    /**
     * 产品报价管理查询service
     */
    @Autowired
    private ProductelementdesService002 productelementdesService002;
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        setSelectOptionToModel(model, DictCode.募集方式,DictCode.投资性质,DictCode.产品运作模式,
                DictCode.目标客户类型,DictCode.产品风险等级);
        String requirementId = this.getParam("requirementId");
        String dataStatus=this.getParam("dataStatus");
        String prdDesignId=this.getParam("prdDesignId");
        String workflowId = this.getParam("workflowId");
        String pdId="";
        if(StringUtil.isNotEmpty(requirementId)){
            pdId=requirementId.substring(0,1);}
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyMMddHHmmss");
        pdId=pdId+(int)((Math.random()*10)+1)+simpleDateFormat.format(new Date());
        Productelementdes002Dto entity=new Productelementdes002Dto();
        entity.setRequirementId(requirementId);
        entity.setDataStatus(dataStatus);
        entity.setPrdDesignId(pdId);
        if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(prdDesignId)){
             entity = new Productelementdes002Dto();
             entity.setRequirementId(requirementId);
            entity.setDataStatus(dataStatus);
            entity.setPrdDesignId(pdId);
        }else if(WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(prdDesignId)){
             entity = productelementdesService002.queryByPrdDesignId(prdDesignId);

        }else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
             entity = productelementdesService002.getBussinessEntityByWorkflowId(workflowId);

        }

     model.addAttribute("entity",entity);
        return super.init(model);
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
            ValidateGroupEdit.class}) Productelementdes002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     *
     * @param dto 保存对象
     * @param br 验证信息
     * @return 操作结果
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated Productelementdes002Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<Productelementdes002Dto> processParameter = new ProcessParameter<Productelementdes002Dto>();
        WorkFlowParameter<Productelementdes002Dto> params = new WorkFlowParameter<Productelementdes002Dto>();
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setUpdateUser(LoginUserUtil.getUserName());
        dto.setCreateTime(DateUtil.now());
        dto.setUpdateTime(DateUtil.now());
        //判断是否在工作流中
        String prdDesignId = dto.getPrdDesignId();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(this.getPageId());
        parameter.setBusinessDataPk1(prdDesignId);
        if(mstWorkflowCommonService.isInWflProcess(parameter)){
            AjaxResult result = new AjaxResult();
            result.setMessage("不能操作流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        //如果不在流程中，就在要素设计表中插入信息
        productelementdesService002.createProductelementdes002(dto);
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());

        params.setBusinessCode(dto.getPrdDesignId());
        params.setBusinessType(DictCodeList.共通业务类型_产品要素设计.getValue());
        params.setComment(entity -> this.getMessage("Productelementdes001.prdDesignId") + "("
                + entity.getPrdDesignId() +")" + this.getMessage("Productelementdes002.createTime") + "("
                + DateUtil.format(DateUtil.now()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = productelementdesService002.start(processParameter, dto);
        return new AjaxResult(msg);
    }


}
