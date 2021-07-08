/**
 * 
 */
package com.joyintech.tams.invest.entrust.etribrepo.controller;

import javax.validation.Valid;

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
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoinfo002Dto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoRk002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 资产系列新建
 * 
 * @author dongzhiling
 * @version 1.0 2019年1月7日
 * @see CeAssetGroupManagement001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrIbRepoRk002")
@RequestMapping("/invest/entrust/etribrepo/EtrIbRepoRk002")
public class EtrIbRepoRk002Controller extends BaseController {
    
    /**
     * BaseGetBusinessId
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    @Autowired
    private EtrIbRepoRk002Service etrIbRepoRk002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 保存按钮按下时check
     * @param dto
     * @param result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) EtrIbRepoRk004Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return 保存处理结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody EtrIbRepoRk004Dto dto,BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return new AjaxResult(result);
        }
        ProcessParameter<EtrIbRepoRk004Dto> processParameter = new ProcessParameter<>();
        // 工作流参数设置
        WorkFlowParameter<EtrIbRepoRk004Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 业务代码生成参数
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setSeqNo("0666");
//            parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产系列授信);
            dto.setPledgeStockId(baseGetBusinessId.buildBussinessCode(parameter));
        }
        //设置业务编号
        String businessCode = dto.getPledgeStockId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        //设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("质押库代码"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("质押库名称"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getPledgeStockName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = etrIbRepoRk002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
