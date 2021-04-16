package com.joyintech.tams.invest.basicdata.mstfundbonusnotice.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.service.MstFundBonusNotice001Service;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNotice002Controller
 * @Description 基金分红公告页面新建
 * @author zhuhanlu 
 * @date 2020/8/10 15:09 
 * @version 1.0 
 */
@Controller
@PageId("MstFundBonusNotice002")
@RequestMapping("/invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002")
public class MstFundBonusNotice002Controller extends BaseController {

    @Autowired private MstFundBonusNotice001Service mstShareBonusNotice001Service;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        String fundId = getParam("fundId");
        String regDateString = getParam("regDate");

        if (StringUtil.isNotEmpty(fundId)) {
            MstFundBonusNotice001Dto mstFundBonusNotice001Dto = null;
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("fundsId", fundId);
            pageParam.put("regDate", regDateString);
            List<MstFundBonusNotice001Dto> selectAllList = mstShareBonusNotice001Service.selectAllList(pageParam);
            if(selectAllList!=null && selectAllList.size()>0){
                mstFundBonusNotice001Dto = selectAllList.get(0);
            }
            model.addAttribute("entity", mstFundBonusNotice001Dto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
            MstFundBonusNotice001Dto mstFundBonusNotice001Dto = mstShareBonusNotice001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", mstFundBonusNotice001Dto);
        }

        return super.init(model);
    }

    /**
     *
     * @Description 新建check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstFundBonusNotice001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存提交
     * @param dto MstFundBonusNotice001Dto
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody MstFundBonusNotice001Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        WorkFlowParameter<MstFundBonusNotice001Dto> params = new WorkFlowParameter<MstFundBonusNotice001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFundId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_基金分红公告.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstFundBonusNotice001.fundId")+"("+ entity.getFundId() +")"+"-"+SpringContextUtil.getTextValue("MstShareBonusNotice001.regDate") + "(" + DateUtil.format(entity.getRegDate()) + ")"
        );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<MstFundBonusNotice001Dto> parameter = new ProcessParameter<MstFundBonusNotice001Dto>();
        parameter.setWorkFlowParameter(params);
        //系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = mstShareBonusNotice001Service.start(parameter, dto);
        return new AjaxResult(msg);
    }
}
