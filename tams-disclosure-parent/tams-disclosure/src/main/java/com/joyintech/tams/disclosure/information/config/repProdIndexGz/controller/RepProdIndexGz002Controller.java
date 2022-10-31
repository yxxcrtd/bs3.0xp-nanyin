package com.joyintech.tams.disclosure.information.config.repProdIndexGz.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.dto.RepProdIndexGzDto;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.service.RepProdIndexGzService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections4.CollectionUtils;
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
@PageId("RepProdIndexGz002")
@RequestMapping("/disclosure/information/config/repProdIndexGz/RepProdIndexGz002")
public class RepProdIndexGz002Controller extends BaseController {

    @Autowired
    private RepProdIndexGzService repProdIndexGzService;


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
                RepProdIndexGzDto dto = repProdIndexGzService.getBussinessEntityByWorkflowId(
                        workflowId);
                model.addAttribute(GlobalConstant.ENTITY, dto);
                // 判断逐渐是否为空
            } else {
                // 编辑
                RepProdIndexGzDto page = repProdIndexGzService.selectData(parm);
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
        return repProdIndexGzService.queryProdIdInfo2(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/selectProdInfo")
    public RepProdIndexGzDto selectProdInfo(String prodId){
        return repProdIndexGzService.selectProdInfo(prodId);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexGzDto dto,
                            BindingResult result) {
        if (CollectionUtils.isEmpty(result.getAllErrors())) {
            AjaxResult ajax=new AjaxResult();
            if(repProdIndexGzService.checkExistsData(dto)){
                ajax.setMessage("已存在母产品的净值信息，是否覆盖？", MessageType.确认消息);
                return ajax;
            }

        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 数据保存操作
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexGzDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<RepProdIndexGzDto> processParameter = new ProcessParameter<RepProdIndexGzDto>();
        WorkFlowParameter<RepProdIndexGzDto> params = new WorkFlowParameter<RepProdIndexGzDto>();
        params.setPageId("RepProdIndexGz002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品净值信息_估值.getValue());
        params.setComment(entity -> "子产品代码" + "(" + entity.getProdId() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = repProdIndexGzService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("checkExistData")
    public Boolean checkExistData(@RequestBody RepProdIndexGzDto dto){
        RepProdIndexGzDto RepProdIndexGzDto = repProdIndexGzService.checkExistData(dto);
        if(RepProdIndexGzDto == null){
            return false;
        }else{
            return true;
        }
    }
}
