/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirmParamDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
//@RestController(value="serviceValNetValueConfirm001Contoller")
@RestController("valNetValueConfirm001Ctl_dtx")
@RequestMapping("/ValNetValueConfirm001")
public class ValNetValueConfirm001Contoller extends BaseController {
    
    /**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ValNetValueConfirm001Service valNetValueConfirm001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        //产品收益类型排除结构化产品
        String[] psArr={"04"};
        DictCode.checkDictCode(model,DictCode.收益类型,psArr,false);
        this.setSelectOptionToModel(model, DictCode.确认状态,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.对账状态,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.对账结果,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.下发状态,DictCode.是否);
        model.addAttribute("confirmStatus", DictCodeList.确认状态_未确认.getValue());
        model.addAttribute("checkStatus", DictCodeList.对账状态_未对账.getValue());
        model.addAttribute("checkResult", DictCodeList.对账结果_对账不一致.getValue());
        model.addAttribute("noticeStatus", DictCodeListExt1.下发状态_未下发.getValue());
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            //model.addAttribute(GlobalConstant.ENTITY, valNetValueConfirm001Service.getBussinessEntityByWorkflowId(workflowId));  
            
            ValNetValueConfirm001Dto paDto= valNetValueConfirm001Service.getBussinessEntityByWorkflowId(workflowId);
            // 获取的实体类和对应的主键不为空
            if(paDto!=null && StringUtil.isNotEmpty( paDto.getFinprodId()) && StringUtil.isNotEmpty(paDto.getValDate()) && StringUtil.isNotEmpty(paDto.getValBalFlag())){
                model.addAttribute("finprodIdForFlow", paDto.getFinprodId());
                model.addAttribute("valDateForFlow", paDto.getValDate());
                model.addAttribute("valBalFlagCodeForFlow", paDto.getValBalFlagCode());
                
                return "/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm002";
            }

        }
        
        return super.init(model);
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValNetValueConfirm001Dto> findPage(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valNetValueConfirm001Service.findPage(page,length,map);
    }
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
//        Map<String,Object> map=RequestUtil.getPageParam();
//        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valNetValueConfirm001Service.getFinprodId(page,length,map);
    }
    /**
     * 净值下发确认 -- 【确认】和【撤销确认】更新
     *
     * @return 返回的数据
     * @throws IOException 
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @ResponseBody
    @RequestMapping(value = "/updateConfirmStatusAndNoticeStatus", method=RequestMethod.POST)
    public AjaxResult updateConfirmStatusAndNoticeStatus(@RequestBody ValNetValueConfirmParamDto paDto,HttpServletRequest request) throws IOException  {

        //当前操作为确认还是下发【确认："QR",下发："XF"】
        String confirmOrNotice = paDto.getConfirmOrNotice();
        
        // 获取勾选的数据
        String data=paDto.getData();
        List<ValNetValueConfirm001Dto> listdto = JsonUtils.json2List(data, ValNetValueConfirm001Dto.class);
        
        //默认返回值，发起成功
        String msg = getMessage("COMMON_I_0001");
        
        for(ValNetValueConfirm001Dto dto : listdto){
            
            //实体类中，带入当前操作状态
            dto.setConfirmOrNotice(confirmOrNotice);
            
            ProcessParameter<ValNetValueConfirm001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ValNetValueConfirm001Dto> params = new WorkFlowParameter<>();
            
            // 业务代码 (JZXF + 产品代码 + 估值日期 +估值表余额表标志 +确认或下发状态)
            // 确认操作
            if("QR".equals(confirmOrNotice)){
                params.setBusinessCode("JZXF-QR-" + dto.getFinprodId() +"-"+ dto.getValDate() +"-"+ dto.getValBalFlagCode()+ "-" + dto.getConfirmStatusCode());
            }
            // 下发操作
            else{
                params.setBusinessCode("JZXF-XF-" + dto.getFinprodId() +"-"+ dto.getValDate() +"-"+ dto.getValBalFlagCode()+ "-" + dto.getNoticeStatusCode());
            }
            
            // 业务类型
            params.setBusinessType(DictCodeList.共通业务类型_净值下发确认.getValue());
            // 页面id
            params.setPageId(this.getPageId());
            // 元素id
            params.setElementId(paDto.getElementId());
            
            //设置流程业务说明, entity即传入的dto
            // 确认操作
            if("QR".equals(confirmOrNotice)){
                params.setComment(entity -> WorkFlowUtil.concatComment("净值下发确认（确认）",entity.getFinprodAbbr(),entity.getValDate(),entity.getValBalFlag(),
                    entity.getConfirmStatus()));
            }
            // 下发操作
            else{
                params.setComment(entity -> WorkFlowUtil.concatComment("净值下发确认（下发）",entity.getFinprodAbbr(),entity.getValDate(),entity.getValBalFlag(),
                    entity.getNoticeStatus()));
            }
             
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
            
            try {
                msg = valNetValueConfirm001Service.start(proParam, dto);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }
        }
        
        //valNetValueConfirm001Service.updateConfirmStatus(listdto);
        
        return new AjaxResult(msg);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid ValNetValueConfirmParamDto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryPageList")
    public List<ValNetValueConfirm001Dto> queryPageList(@RequestBody Map<String, Object> params) {
        	 return valNetValueConfirm001Service.queryPageList(params);
    }
    
    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateNoticeStatus")
    public int updateNoticeStatus(@RequestBody ValNetValueConfirm001Dto dto) {
        return valNetValueConfirm001Service.updateNoticeStatus(dto);
    }
}

