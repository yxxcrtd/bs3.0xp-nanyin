package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch003Dto;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.service.ValBooksetDateSwitch003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 账套日期管理 - 批量修改
 * 
 * @author zhangwei
 * @version 1.0 2018年2月18日
 * @see ValBooksetDateSwitch003Controller
 * @since 1.0
 */
//@RestController(value="serviceValBooksetDateSwitch003Controller")
@RestController("valBooksetDateSwitch003Ctl_dtx")
@RequestMapping("ValBooksetDateSwitch003")
public class ValBooksetDateSwitch003Controller extends BaseController {

    @Autowired
    private ValBooksetDateSwitch003Service valBooksetDateSwitch003Service;

//    @Override
//    public String init(Model model) {
//        model.addAttribute("booksetId", getParam("booksetId"));
//        return super.init(model);
//    }
    
    /**
     * 查询数据获得
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @PostMapping("/findList")
    public List<ValBooksetDateSwitch003Dto> findList(@RequestBody Map<String , Object> parmars) throws Exception {
        return valBooksetDateSwitch003Service.findList(parmars);

    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("/modify")
    public int modify(@RequestBody ValBooksetDateSwitch003Dto entity) throws Exception {
        return valBooksetDateSwitch003Service.modify(entity);

    }
//    /**
//     * 保存按钮点击时的check
//     *
//     * @param dto dto
//     * @param result BindingResult
//     * @param operateType 操作类型
//     * @return check结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ValBooksetDateSwitch003Dto dto, BindingResult result, String operateType) {
//        return commonCheck(result, operateType);
//    }
//    
//    @ResponseBody
//    @RequestMapping(value = "doUpdate", method=RequestMethod.POST)
//    public AjaxResult doUpdate(@RequestBody ValBooksetDateSwitch003Dto dto) {
//        List<ValBooksetDateSwitch003Dto> list = dto.getSelectedItem();
//        
//        ProcessParameter<ValBooksetDateSwitch003Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValBooksetDateSwitch003Dto> params = new WorkFlowParameter<>();
//        // 业务代码
//        params.setBusinessCode(entity -> entity.getBooksetId());
//        // 业务类型
//        params.setBusinessType(DictCodeList.共通业务类型_账套日期.getValue());
//        params.setPageId("ValBooksetDateSwitch002");
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//      //设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> WorkFlowUtil.concatComment(entity.getBooksetId(),
//            entity.getBooksetDate()));
//        
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0003");
//        try {
//            msg = valBooksetDateSwitch003Service.start(proParam, list.toArray(new ValBooksetDateSwitch003Dto[list.size()]));
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//        return new AjaxResult(msg);
//    }

}
