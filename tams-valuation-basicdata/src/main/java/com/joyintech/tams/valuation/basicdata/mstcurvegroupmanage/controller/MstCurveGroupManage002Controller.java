
package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.service.MstCurveGroupManage002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:收益率曲线族管理（新增，修改，详情）
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@Controller
@PageId("MstCurveGroupManage002")
@RequestMapping("/valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002")
public class MstCurveGroupManage002Controller extends BaseController{

    @Autowired
    private MstCurveGroupManage002Service mstCurveGroupManage002Service;
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型);
        this.setSelectOptionToModel(model, DictCode.估值来源);
        this.setSelectOptionToModel(model, DictCode.曲线来源);
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.评级结果);
        this.setSelectOptionToModel(model, DictCode.币种);
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());

        MstCurveGroupManageDto dto = new MstCurveGroupManageDto();
        String curveCode = getParam("curveGroupCode");
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
        if(StringUtils.isNotEmpty(curveCode)){
            Map<String,Object> queryParam = new HashMap<>();
            queryParam.put("curveGroupCode",this.getParam("curveGroupCode"));
            dto = mstCurveGroupManage002Service.selectByKey(queryParam);
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = mstCurveGroupManage002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute("entity",dto);
        return super.init(model);
    }



    /**
     * 查询、修改和新建按钮点击时的check
     *
     * @param dto 验证的实体类
     * @param result 错误结果
     * @return AjaxResult 错误结果信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstCurveGroupManageDto dto, BindingResult result) {

        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstCurveDefManage002");
        wflValidInputParameter.setBusinessDataPk1(dto.getCurveGroupCode());
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }

        //唯一性校验
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            Map<String,Object> map = new HashMap();
            map.put("curveGroupCode",dto.getCurveGroupCode());
            MstCurveGroupManageDto repDto = mstCurveGroupManage002Service.selectByKey(map);
            if(repDto !=null){
                String message = "曲线代码与库中有重复，请修改！";
                return new AjaxResult(message, MessageType.错误消息);
            }
        }


        return ajaxResult ;
    }


    /**
     * @Description:工作流，处理数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody MstCurveGroupManageDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        String businessCode = dto.getCurveGroupCode();
        ProcessParameter<MstCurveGroupManageDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<MstCurveGroupManageDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_收益率曲线管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "曲线族代码" + "-" + entity.getCurveGroupCode()+ "当前日期" + "-"+ DateUtil.getDateTimestamp() );
        processParameter.setWorkFlowParameter(params);
        String msg ="";
        if(dto.getSelectedItem() !=null && dto.getSelectedItem().size()>0){
            msg = mstCurveGroupManage002Service.start(processParameter,
                    dto.getSelectedItem().toArray(
                            new MstCurveGroupManageDto[dto.getSelectedItem().size()]));
        }else{
            msg = mstCurveGroupManage002Service.start(processParameter, dto);
        }

        return new AjaxResult(msg);
    }


}
