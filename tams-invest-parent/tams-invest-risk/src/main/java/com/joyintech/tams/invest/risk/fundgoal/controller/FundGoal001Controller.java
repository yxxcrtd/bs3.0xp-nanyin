package com.joyintech.tams.invest.risk.fundgoal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.fundgoal.dto.FundGoalDto;
import com.joyintech.tams.invest.risk.fundgoal.service.FundGoalService;
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

import java.util.List;

/**
 * @Classname FundGoal001Controller
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 11:06 
 * @version 1.0 
 */

@Controller
@PageId("FundGoal001")
@RequestMapping("/invest/risk/fundgoal/FundGoal001")
public class FundGoal001Controller extends BaseController {


    @Autowired FundGoalService fundGoalService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }


    /** 分页查询
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FundGoalDto> findPage()
            throws Exception {
        Page<FundGoalDto> page = fundGoalService.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) FundGoalDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * @Description //资产代码下拉框
     * @Param [param]
     * @return java.util.List<com.joyintech.framework.select.SuggestDto>
     * @version 1.0
     * @see
    **/

    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList()
            throws Exception{
        return fundGoalService.getFinprodMarketIdList(RequestUtil.getPageParam());
    }



    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto FundGoalDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  FundGoalDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FundGoalDto> processParameter = new ProcessParameter<FundGoalDto>();
        WorkFlowParameter<FundGoalDto> params = new WorkFlowParameter<FundGoalDto>();
        params.setPageId("FundGoal002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_基金投向维护.getValue());
//        params.setBusinessType("MST30");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("FundGoal001.finprodId")+"("+ entity.getFinprodId() +")"+"-"+SpringContextUtil.getTextValue("FundGoal002.goalDate") + "(" + DateUtil.format(entity.getGoalDate()) + ")"

        );
        processParameter.setWorkFlowParameter(params);
        String msg = fundGoalService.start(processParameter,
                dto.getSelectedItem().toArray(
                        new FundGoalDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }



}
