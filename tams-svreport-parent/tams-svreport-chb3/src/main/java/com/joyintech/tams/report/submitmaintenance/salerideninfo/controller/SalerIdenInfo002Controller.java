package com.joyintech.tams.report.submitmaintenance.salerideninfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.salerideninfo.service.SalerIdenInfoService;
import com.joyintech.tams.report.submitmaintenance.salerideninfo.dto.SalerIdenInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@PageId("SalerIdenInfo002")
@RequestMapping("/report/submitmaintenance/salerideninfo/SalerIdenInfo002")
public class SalerIdenInfo002Controller extends BaseController {
    
    /**
     * 业务entity
     */
    @Autowired
    private SalerIdenInfoService salerIdenInfoService;
    /**
    *
    * {@inheritDoc}
    */
   @Override
   @RequestMapping(PAGE_INIT)
   public String init(Model model) {
       super.setSelectOptionToModel(model, DictCode.销售人员证件类型,DictCode.销售人员学历,DictCode.销售人员学位,DictCode.性别_中债,DictCode.销售区域_中债
       ,DictCode.销售人员专业技术职称,DictCode.销售人员理财专业证书,DictCode.可销售产品风险等级,DictCode.销售人员当前状态);
       String salerInsNo = getParam("salerInsNo");
       String erroOrInfo = getParam("erroOrInfo");
       String workflowId = getParam("workflowId");
       if(WorkFlowUtil.isNotFromToDoList()) {
           SalerIdenInfoDto entity = salerIdenInfoService.selectDetail(salerInsNo,erroOrInfo,workflowId);
               // 设置到model中
               model.addAttribute("entity", entity);
       }
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
            ValidateGroupEdit.class}) SalerIdenInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存提交工作流
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) SalerIdenInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //新建保存
        if(dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            salerIdenInfoService.saveAndSubmit(dto);
            return new AjaxResult("提交成功");
        }
        //编辑保存
        if(dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            salerIdenInfoService.update(dto);
            return new AjaxResult("提交成功");
        }
        return new AjaxResult("提交失败");
    }

    /**
     * 获取本行中债机构代码
     * @return
     */
    @ResponseBody
    @RequestMapping("getIssuePartyId")
    public String getIssuePartyId() {
        //获取本行中债机构代码
        String orgCode = CommonUtil.getOrgCode();
        return salerIdenInfoService.selectIssuePartyId(orgCode);
    }

    /**
     * 所属总部或分支机构下拉框
     * @param
     * @return
     */
    @RequestMapping("selectSalerOrgCode")
    @ResponseBody
    public List<SelectOptionDto> selectSalerOrgCode() {
        return salerIdenInfoService.selectSalerOrgCode(RequestUtil.getPageParam());
    }
}
