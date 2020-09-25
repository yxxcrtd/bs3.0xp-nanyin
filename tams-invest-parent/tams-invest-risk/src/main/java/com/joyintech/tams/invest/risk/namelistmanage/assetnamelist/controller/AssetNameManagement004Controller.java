package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.AssetNameManagement004Dto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 名单管理--资产名单明细维护
 * @author wangzhuangzhuang
 * @version 1.0 2019年07月16日
 * @see AssetNameManagement004Controller
 * @since 1.0
 */

@Controller
@PageId("AssetNameManagement004")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagement004")
public class AssetNameManagement004Controller extends BaseController {
    @Autowired
    private AssetNameManagementService assetNameManagementService;   
    /**
     * ProgressBarService
     */
    @Autowired
    private ProgressBarService progressBarService;
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    /**
     * 定义service
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    public String init(Model model) {
        
        String listId = this.getParam("listId");
        String workflowId = getParam("workflowId");
        String listName = this.getParam("listName");
        String listDetailType =  this.getParam("listDetailType");
        String operateStyle = this.getParam("operateStyle");
        String listType = this.getParam("listType");
        String listLevel = this.getParam("listLevel");
        RskAccessListDto rskAccessListDto = null;
        //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(listId)) {
            //根据主键获取业务实体--标的金融产品基本信息
           /* RskAccessListDto finBondTargetAssetDto = finBondTargetAssetService.getBasicInfo(
                finprodId);
            commonBusinessService.setTradeMarketId(finBondTargetAssetDto,model);*/
            model.addAttribute("listName",listName);
            model.addAttribute("listId",listId);
            model.addAttribute("entity", new RskAccessListDto());
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
             rskAccessListDto = assetNameManagementService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("entity", rskAccessListDto);
            model.addAttribute("listName",rskAccessListDto.getListName());
            model.addAttribute("listId",rskAccessListDto.getListId());
            model.addAttribute("revealFileId", rskAccessListDto.getRevealFileId());
            model.addAttribute("inputType", rskAccessListDto.getInputType());
        }
        
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isEmpty(getParam("revealFileId"))) {
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setSeqNo("9997");
            model.addAttribute("revealFileId", baseGetBusinessId.buildBussinessCode(parameter));
        }
        model.addAttribute("listDetailType",listDetailType);
        model.addAttribute("operateStyle",StringUtil.isNotEmpty(operateStyle)?operateStyle: DictCodeListExt2.名单操作方式_批量.getValue());
        model.addAttribute("workflowId",workflowId);
        
        model.addAttribute("RSK_OPERATE_PS_01", DictCodeListExt2.名单操作方向_移出名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_02", DictCodeListExt2.名单操作方向_移入名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_99", DictCodeListExt2.名单操作方向_不变.getValue());
        model.addAttribute("RSK_OPERATE_PS_03", DictCodeListExt2.名单操作方向_修改.getValue());
        if(null != rskAccessListDto && rskAccessListDto.getRevealFileId() != null) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(rskAccessListDto.getRevealFileId());
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        model.addAttribute("listType",listType);
        model.addAttribute("listLevel",listLevel);
        return super.init(model);
    }
    
    /**
     * <一句话功能简述> 导出模板
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return assetNameManagementService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public AssetNameManagement004Dto importData(String fileName,String inputType,String listId,String revealFileId,String fileNo)
        throws Exception{
        if(StringUtil.isNotEmpty(fileName) && StringUtil.isNotEmpty(revealFileId)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            fileAttachmentProcess.updateBussinessNo(Arrays.asList(fileNo), revealFileId);
        }
        return assetNameManagementService.importData(fileName,inputType,listId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 去除上传进度条
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(String fileNo, String revealFileId) {
        if(StringUtil.isNotEmpty(fileNo) && StringUtil.isNotEmpty(revealFileId)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            fileAttachmentProcess.updateBussinessNo(Arrays.asList(fileNo), revealFileId);
        }
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
    
    /**
     * check--保存
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskAccessListDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        //有check错误直接返回
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }  
        List<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.isNotEmpty(dto.getRskAccessListDetailList())){ 
             for (RskAccessListDetailDto detailDto : dto.getRskAccessListDetailList()) {
                if (!StringUtil.isNotEmpty(detailDto.getListDetailId())) { 
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0057",getMessage("AssetNameManagement003.listDetailId"))));
                    ajaxResult.setErrorList(errorList);
                    return ajaxResult;
                }
                if (!StringUtil.isNotEmpty(detailDto.getListDetailName())) { 
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0057",getMessage("AssetNameManagement003.listDetailName"))));
                    ajaxResult.setErrorList(errorList);
                    return ajaxResult;
                }
            }
             
        } 
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
    *
    * 保存
    * @param dto
    * @param br
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping(PAGE_SAVE)
   public AjaxResult save(@RequestBody @Validated({
       ValidateGroupEdit.class}) RskAccessListDto dto, BindingResult br)
       throws Exception {
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       WorkFlowParameter<RskAccessListDto> params = new WorkFlowParameter<RskAccessListDto>();
       params.setPageId("AssetNameManagement003");
       params.setElementId(dto.getElementId());
       params.setBusinessCode(dto.getListId());
       params.setBusinessType(DictCodeList.共通业务类型_资产名单管理.getValue());
       // 设置流程业务说明
       params.setComment(entity -> {
           return WorkFlowUtil.concatComment(getMessage("AssetNameManagement001.listId")+
               GlobalConstant.OPEN_PARENTHESIS+entity.getListId(), GlobalConstant.CLOSE_PARENTHESIS+
               getMessage("AssetNameManagement001.listName")+GlobalConstant.OPEN_PARENTHESIS+
               entity.getListName()+GlobalConstant.CLOSE_PARENTHESIS);
       });
       // 给参数变成ProcessParameter而非WorkFlowParameter
       ProcessParameter<RskAccessListDto> parameter = new ProcessParameter<RskAccessListDto>();
       parameter.setWorkFlowParameter(params);
       // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
       String msg = assetNameManagementService.start(parameter, dto);
       return new AjaxResult(msg);
   }

}
