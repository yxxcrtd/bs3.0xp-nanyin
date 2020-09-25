package com.joyintech.tams.invest.entrust.etrinstruction.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrinstruction.dto.EtrInstructionDto;
import com.joyintech.tams.invest.entrust.etrinstruction.service.EtrInstructionService;
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

import java.util.Date;

@Controller
@PageId("EtrInstruction002")
@RequestMapping("/invest/entrust/etrinstruction/EtrInstruction002")
public class EtrInstruction002Controller extends BaseController {

    @Autowired EtrInstructionService instructionService;
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    @Override public String init(Model model) {
        //获取系统平台日
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = getParam("entrustId");
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.币种, DictCode.计划类资产一级分类,DictCode.计划类资产二级分类
               });

        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {//流程中界面
            EtrInstructionDto entity = instructionService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(entity.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", workflowId);

        }
        else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {//修改
            EtrInstructionDto dto = instructionService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", dto.getEntrustId());

        }if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) { //新建
            EtrInstructionDto entity = new EtrInstructionDto();
            entity.setvDate(sysDate);
            entity.setmDate(sysDate);
            entity.setCcy("CNY");
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrInstructionDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 委托存款保存业务流程
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) EtrInstructionDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_立项指令));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrInstructionDto> processParameter = new ProcessParameter<EtrInstructionDto>();
        WorkFlowParameter<EtrInstructionDto> params = new WorkFlowParameter<EtrInstructionDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getEntrustId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_立项指令.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrInstruction001.finprodMarketId") + "(" + entity.getFinprodMarketId() + ")"
                      +  "-" + "("
                      + this.getMessage("EtrInstruction001.vDate") + ":" +DateUtil.format(entity.getvDate())
                      + ")-(" + this.getMessage("EtrInstruction001.vDate") + ":"
                      +DateUtil.format(entity.getmDate())+ ")-"
                      + this.getMessage("EtrInstruction001.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = instructionService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
