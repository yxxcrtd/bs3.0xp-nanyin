package com.joyintech.tams.report.submitmaintenance.salerinstinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.dto.SalerInstInfoDto;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.service.SalerInstInfoService;
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

@Controller
@PageId("SalerInstInfo002")
@RequestMapping("/report/submitmaintenance/salerinstinfo/SalerInstInfo002")
public class SalerInstInfo002Controller extends BaseController {
    
    /**
     * 业务entity
     */
    @Autowired
    private SalerInstInfoService salerInstInfoService;
    /**
    *
    * {@inheritDoc}
    */
   @Override
   @RequestMapping(PAGE_INIT)
   public String init(Model model) {
       super.setSelectOptionToModel(model, DictCode.机构层级);
       String salerOrgCode = getParam("salerOrgCode");
       String erroOrInfo = getParam("erroOrInfo");
       if(WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(salerOrgCode) && StringUtil.isNotEmpty(erroOrInfo)) {
           SalerInstInfoDto entity = salerInstInfoService.selectDetail(salerOrgCode,erroOrInfo);
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
            ValidateGroupEdit.class}) SalerInstInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存提交工作流
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) SalerInstInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //新建保存
        if(dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            salerInstInfoService.saveAndSubmit(dto);
            return new AjaxResult("提交成功");
        }
        //编辑保存
        if(dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            salerInstInfoService.update(dto);
            return new AjaxResult("提交成功");
        }
        return new AjaxResult("提交失败");
    }

    /**
     * 获取本行中债机构代码
     * @return
     */
    @ResponseBody
    @RequestMapping("getSalerInstCode")
    public String getSalerInstCode() {
        //获取本行中债机构代码
        String orgCode = CommonUtil.getOrgCode();
        return salerInstInfoService.selectIssuePartyId(orgCode);
    }
}
