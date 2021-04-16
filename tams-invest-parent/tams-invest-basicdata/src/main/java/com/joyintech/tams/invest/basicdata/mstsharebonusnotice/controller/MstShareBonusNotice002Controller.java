package com.joyintech.tams.invest.basicdata.mstsharebonusnotice.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.service.MstShareBonusNotice001Service;
import com.joyintech.tams.invest.basicdata.mstshareprice.dto.MstSharePriceDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstShareBonusNotice002Controller
 * @Description 股票分红新建页面
 * @author zhuhanlu 
 * @date 2020/8/10 15:06 
 * @version 1.0 
 */

@Controller
@PageId("MstShareBonusNotice002")
@RequestMapping("/invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002")
public class MstShareBonusNotice002Controller extends BaseController{


    @Autowired
    private MstShareBonusNotice001Service mstShareBonusNotice001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        String sharesId = getParam("sharesId");
        String regDateString = getParam("regDate");

        //修改时
        if (StringUtil.isNotEmpty(sharesId)) {
            MstShareBonusNotice001Dto mstShareBonusNotice001Dto = null;
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("sharesId", sharesId);
            pageParam.put("regDateString", regDateString);
            List<MstShareBonusNotice001Dto> selectAllList = mstShareBonusNotice001Service.selectAllList(pageParam);
            if(selectAllList!=null && selectAllList.size()>0){
                mstShareBonusNotice001Dto = selectAllList.get(0);
            }
            model.addAttribute("entity", mstShareBonusNotice001Dto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
            MstShareBonusNotice001Dto mstShareBonusNotice001Dto = mstShareBonusNotice001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", mstShareBonusNotice001Dto);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstShareBonusNotice001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存提交
     * @param dto MstShareBonusNotice001Dto
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody MstShareBonusNotice001Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        WorkFlowParameter<MstShareBonusNotice001Dto> params = new WorkFlowParameter<MstShareBonusNotice001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票分红公告.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstShareBonusNotice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("MstShareBonusNotice001.regDate") + "(" + DateUtil.format(entity.getRegDate()) + ")"
        );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<MstShareBonusNotice001Dto> parameter = new ProcessParameter<MstShareBonusNotice001Dto>();
        parameter.setWorkFlowParameter(params);
        //系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = mstShareBonusNotice001Service.start(parameter, dto);
        return new AjaxResult(msg);
    }


}
