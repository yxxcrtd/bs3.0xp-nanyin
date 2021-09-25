
package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service.MstCurveDefManage001Service;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service.MstCurveDefManage002Service;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收益率曲管理详情
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-07-12
 * @see MstCurveDefManage002Controller
 * @since 1.0
 */
@Controller
@PageId("MstCurveDefManage002")
@RequestMapping("/valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002")
public class MstCurveDefManage002Controller extends BaseController{

    @Autowired
    private MstCurveDefManage002Service mstCurveDefManage002Service;
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

        MstCurveDefManageDto dto = new MstCurveDefManageDto();
        String curveCode = getParam("curveCode");
        String workflowId = this.getParam(com.joyintech.tams.valuation.common.constants.GlobalConstant.WORKFLOW_ID);// 工作流ID
        if(StringUtils.isNotEmpty(curveCode)){
            Map<String,Object> queryParam = new HashMap<>();
            queryParam.put("curveCode",this.getParam("curveCode"));
            dto = mstCurveDefManage002Service.selectByKey(queryParam);
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = mstCurveDefManage002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        //插入部分定值
        dto.setInterMethod("线性插值"); //线性插值
        dto.setDayRules("A/365"); //计息基础
        dto.setCalender("银行间交易日期"); //日历
        dto.setInterestDateRule("向后调整");//付息日准则
        dto.setInterestCalMethod("按年复利"); //计息方式
        dto.setInterestFreq("年付");//付息频率


        model.addAttribute("entity",dto);
        return super.init(model);
    }

    /**
     * @Description:获取收益率曲线 N K值
     * @auther: yuzj
     * @date: 2021/9/11
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/getNkSelect")
    public List<SelectOptionDto> getNkSelect(){
        return mstCurveDefManage002Service.getNkSelect();
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstCurveDefManageDto dto, BindingResult result) {

        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstCurveDefManage002");
        wflValidInputParameter.setBusinessDataPk1(dto.getCurveCode());
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
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
    public AjaxResult save(@RequestBody MstCurveDefManageDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        /*if(StringUtil.isEmpty(dto.getCurveCode())){
            dto.setSeqNo(IDGenerator.generate());
        }*/
        String businessCode = dto.getCurveCode();
        ProcessParameter<MstCurveDefManageDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<MstCurveDefManageDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_收益率曲线管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "曲线代码" + "-" + entity.getCurveCode()+ "当前日期" + "-"+ DateUtil.getDateTimestamp() );
        processParameter.setWorkFlowParameter(params);
        String msg ="";
        if(dto.getSelectedItem() !=null && dto.getSelectedItem().size()>0){
            msg = mstCurveDefManage002Service.start(processParameter,
                    dto.getSelectedItem().toArray(
                            new MstCurveDefManageDto[dto.getSelectedItem().size()]));
        }else{
            msg = mstCurveDefManage002Service.start(processParameter, dto);
        }

        return new AjaxResult(msg);
    }


    /**
     * @Description:获取曲线族下拉框
     * @auther: yuzj
     * @date: 2021/9/11
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/getCurveGroupSelect")
    public Page<SelectOptionDto> getCurveGroupSelect(){
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        return mstCurveDefManage002Service.getCurveGroupSelect(map);
    }


}
