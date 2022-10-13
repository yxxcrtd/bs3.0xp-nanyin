package com.joyintech.tams.disclosure.information.config.repProdIndex.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.service.RepProdIndexService;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
import java.util.List;
import java.util.Map;

@Controller
@PageId("RepProdIndex002")
@RequestMapping("/disclosure/information/config/repProdIndex/RepProdIndex002")
public class RepProdIndex002Controller extends BaseController {

    @Autowired
    private RepProdIndexService repProdIndexService;


    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.产品费用交易费用类型);
        Map<String, Object> parm = RequestUtil.getPageParam();
        String status = getParam("status");
        parm.put("status",status);
        String pageModel = this.getParam("pageModel");
        if(parm.containsKey("valueDate")){
            parm.put("valueDate", DateUtil.parse(parm.get("valueDate").toString()));
        }
        if (!StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, pageModel)) {
            String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
            if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
                RepProdIndexDto dto = repProdIndexService.getBussinessEntityByWorkflowId(
                        workflowId);
                model.addAttribute(GlobalConstant.ENTITY, dto);
                // 判断逐渐是否为空
            } else {
                // 编辑
                RepProdIndexDto page = repProdIndexService.selectData(parm);
                model.addAttribute(GlobalConstant.ENTITY, page);
            }
        }

        return super.init(model);
    }
    /**
     * 根据产品代码模糊查询子产品下拉框
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo2")
    public List<SelectOptionDto> queryProdIdInfo2() {
        return repProdIndexService.queryProdIdInfo2(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/selectProdInfo")
    public RepProdIndexDto selectProdInfo(String prodId){
        return repProdIndexService.selectProdInfo(prodId);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 数据保存操作
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<RepProdIndexDto> processParameter = new ProcessParameter<RepProdIndexDto>();
        WorkFlowParameter<RepProdIndexDto> params = new WorkFlowParameter<RepProdIndexDto>();
        params.setPageId("RepProdIndex002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品净值信息.getValue());
        params.setComment(entity -> "子产品代码" + "(" + entity.getProdId() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = repProdIndexService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("checkExistData")
    public Boolean checkExistData(@RequestBody RepProdIndexDto dto){
        RepProdIndexDto repProdIndexDto = repProdIndexService.checkExistData(dto);
        if(repProdIndexDto == null){
            return false;
        }else{
            return true;
        }
    }
}
