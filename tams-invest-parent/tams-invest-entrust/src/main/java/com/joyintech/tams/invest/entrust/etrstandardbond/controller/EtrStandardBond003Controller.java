/*
 * 文件名：TrdStandardBond002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrstandardbond.controller;

import java.util.List;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.tams.invest.entrust.etrstandardbond.service.EtrStandardBond003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 质押券优先级设置
 * 〈功能详细描述〉
 *
 * @author lizhi
 * @version 1.0 2020年1月2日
 * @see EtrStandardBond003Controller
 * @since 1.0
 */
@PageId("EtrStandardBond003")
@RequestMapping("/invest/entrust/etrstandardbond/EtrStandardBond003")
@Controller
public class EtrStandardBond003Controller extends BaseController {

    @Autowired(required = false)
    private EtrStandardBond003Service etrStandardBond003Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易场所,DictCode.WIND一级分类_含债券一级分类等,
            DictCode.WIND二级分类_含债券二级分类等,DictCode.质押优先级);
        
//        setSelectOptionToModel(model,
//            new DictCode[] {DictCode.WIND一级分类_含债券一级分类等});
        model.addAttribute("NO",DictCodeListExt1.是否_否.getValue());
        model.addAttribute("YES",DictCodeListExt1.是否_是.getValue());
        EtrStandardBond003Dto dto =  etrStandardBond003Service.selectEtrSecPriDetailList();
        model.addAttribute(GlobalConstant.ENTITY,dto) ;
        return super.init(model);
    }

    /**
     * 查询已经设置的优先级数据
     *
     * @return List<EtrStandardBond003Dto>
     */
    @RequestMapping("selectEtrSecPriDetailList")
    @ResponseBody
    public List<EtrSecPriorityDetail> selectEtrSecPriDetailList() {
        
        EtrStandardBond003Dto dto =  etrStandardBond003Service.selectEtrSecPriDetailList();
        if(StringUtil.isNotEmpty(dto)){
            return dto.getDetailList() ;
        }
        return null ;
    }

    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("check")
    @ResponseBody
    AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody EtrStandardBond003Dto dto, BindingResult result)
            throws Exception {
        return commonCheck(result, null);
    }

//    @RequestMapping("etrSecPrioritySave")
//    @ResponseBody
//    AjaxResult etrSecPrioritySave(@Validated({ValidateGroupEdit.class}) @RequestBody EtrStandardBond003Dto dto, BindingResult result)
//            throws Exception {
//        Long outFlat = etrStandardBond003Service.etrSecPrioritySave(dto);
//        // 标记大于0，保存成功
//        if(outFlat > 0){
//            return new AjaxResult(this.getMessage("EtrStandardBond003.success"));
//        }else{
//            return new AjaxResult(this.getMessage("EtrStandardBond003.fail"));
//        }
//    }
    
    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@Validated({ValidateGroupEdit.class}) @RequestBody EtrStandardBond003Dto dto, BindingResult result)
            throws Exception {
        if (result != null && result.hasErrors()) {
            return commonCheck(result, null);
        }

        ProcessParameter<EtrStandardBond003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<EtrStandardBond003Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> dto.getConfigId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment("质押券优先级设置"));
        String msg = etrStandardBond003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
