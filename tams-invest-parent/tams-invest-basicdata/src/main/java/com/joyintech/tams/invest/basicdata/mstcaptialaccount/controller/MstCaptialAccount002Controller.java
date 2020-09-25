/*
 * 文件名：MstCaptialAccount002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstcaptialaccount.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.service.MstCaptialAccountService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年7月23日
 * @see MstCaptialAccount002Controller
 * @since 1.0
 */
@Controller
@PageId("MstCaptialAccount002")
@RequestMapping("/invest/basicdata/mstcaptialaccount/MstCaptialAccount002")
public class MstCaptialAccount002Controller extends BaseController{
    /**
     * 
     */
    @Autowired
    private MstCaptialAccountService mstCaptialAccountService;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    /**
     * 新建页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.币种,DictCode.资产来源);
        //网点号/大额支付系统号
        String acctNo = getParam("acctNo");
        //币种
        String ccy = getParam("ccy");
        String workflowId = getParam("workflowId");
//        model.addAttribute("FileArray", "");
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(acctNo) && StringUtil.isNotEmpty(ccy)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("acctNo", acctNo);
            map.put("ccy", ccy);
            MstCaptialAccountDto mstCaptialAccountDto = mstCaptialAccountService.selectDetail(map);
//            List<SysUploadFileInfo> fileArray = mstCaptialAccountService.selectFileByBusinessId(acctNo);
            model.addAttribute("entity", mstCaptialAccountDto);
//            model.addAttribute("FileArray", fileArray);
        }
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            MstCaptialAccountDto mstCaptialAccountDto = mstCaptialAccountService.getBussinessEntityByWorkflowId(workflowId);
//            List<UploadFile> fileArray = mstCaptialAccountDto.getFileArray();
            model.addAttribute("entity", mstCaptialAccountDto);
//            model.addAttribute("FileArray", fileArray);
            //model.addAttribute("pageModel",mstCaptialAccountDto.getPageModel());
        }
        if (!StringUtil.isNotEmpty(acctNo) && !StringUtil.isNotEmpty(ccy) && !StringUtil.isNotEmpty(workflowId)) {
            MstCaptialAccountDto entity = new MstCaptialAccountDto();
            //新建设置币种为人名币为初始值
            entity.setCcy("CNY");
            model.addAttribute("entity",entity);
        } 
        else {
           /* model.addAttribute("notEditOrgCode", true);*/
        }
        model.addAttribute("CNY", DictCodeList.币种_人民币_CNY.getValue());
        return super.init(model);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstCaptialAccountDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) MstCaptialAccountDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<MstCaptialAccountDto> processParameter = new ProcessParameter<MstCaptialAccountDto>();
        WorkFlowParameter<MstCaptialAccountDto> params = new WorkFlowParameter<MstCaptialAccountDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getLargePayno()+item.getBusiAcctNo()+item.getCcy());
        params.setBusinessType(DictCodeList.共通业务类型_资金账户管理.getValue());

        dto.setAcctNo(dto.getLargePayno() + GlobalConstant.COLON + dto.getBusiAcctNo());
        
        //多级法人改造  增加seqno字段  add by caojin 2020/6/15   start
        dto.setSeqNo(IDGenerator.generate());
        //多级法人改造  增加seqno字段  add by caojin 2020/6/15   end
        if (CollectionUtils.isNotEmpty(dto.getFileArray())
            && !StringUtil.equals(dto.getAcctNo(), GlobalConstant.COLON)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            fileAttachmentProcess.updateBussinessNo(
                dto.getFileArray().stream().map(UploadFile::getFileNo).collect(
                    Collectors.toList()),
                dto.getAcctNo());
        }
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "网点账号"+ "(" + entity.getLargePayno()+entity.getBusiAcctNo()+ ")" 
                +  "-" + "账户名称"+ "(" + entity.getAcctName() + ")"  +  "-" + "币种"+ 
                "(" + SysDataUtil.getNameFromDataDict(DictCode.币种,entity.getCcy()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstCaptialAccountService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
