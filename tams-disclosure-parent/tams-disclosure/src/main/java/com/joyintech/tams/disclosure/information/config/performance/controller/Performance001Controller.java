package com.joyintech.tams.disclosure.information.config.performance.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import com.joyintech.tams.disclosure.information.config.performance.service.Performance001Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
@PageId("Performance001")
@RequestMapping("/disclosure/information/config/performance/Performance001")
public class Performance001Controller extends BaseController {

    @Autowired
    private  Performance001Service performance001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<Performance001Dto> selectAll(){
        Page<Performance001Dto> page = performance001Service.selectForPage1(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) Performance001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return performance001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) Performance001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<Performance001Dto> processParameter = new ProcessParameter<Performance001Dto>();
        WorkFlowParameter<Performance001Dto> params = new WorkFlowParameter<Performance001Dto>();
        params.setPageId("Performance002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品业绩比较基准维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = performance001Service.start(processParameter,
                dto.getSelectedItem().toArray(new Performance001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }


    //    @ResponseBody
//    @RequestMapping("getProdIdlist")
//    public Page<SelectOptionDto> getProdIdlist()
//            throws Exception {
//        Page<SelectOptionDto> temp = performance001Service.getProdIdlist(RequestUtil.getPageParam());
//        return temp;
//    }
    @ResponseBody
    @RequestMapping("getProdIdlist")
    public List<SelectOptionDto> getProdIdlist()
            throws Exception {

        return performance001Service.getProdIdlist(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return performance001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName) throws ParseException {
        return performance001Service.importData(fileName);
    }
}
