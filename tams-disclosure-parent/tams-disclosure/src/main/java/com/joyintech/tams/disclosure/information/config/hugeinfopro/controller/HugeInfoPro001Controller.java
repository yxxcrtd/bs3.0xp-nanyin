package com.joyintech.tams.disclosure.information.config.hugeinfopro.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;

import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.service.HugeInfoPro001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@PageId("HugeInfoPro001")
@Controller
@RequestMapping("/disclosure/information/config/hugeinfopro/HugeInfoPro001")
public class HugeInfoPro001Controller extends BaseController {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HugeInfoPro001Service hugeInfoPro001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<HugeInfoPro001Dto> selectAll(){
        Page<HugeInfoPro001Dto> page = hugeInfoPro001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) HugeInfoPro001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 行删除与删除按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br br
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) HugeInfoPro001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<HugeInfoPro001Dto> processParameter = new ProcessParameter<HugeInfoPro001Dto>();
        WorkFlowParameter<HugeInfoPro001Dto> params = new WorkFlowParameter<HugeInfoPro001Dto>();
        params.setPageId("HugeInfoPro002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdRegistNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_巨额赎回信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品登记编码"+ "(" + entity.getProdRegistNo()+ ")"
                        +  "-" + "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = hugeInfoPro001Service.start(processParameter,
                dto.getSelectedItem().toArray(new HugeInfoPro001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
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
        return hugeInfoPro001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }


    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return hugeInfoPro001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return hugeInfoPro001Service.importData(fileName);
    }

    @ResponseBody
    @RequestMapping("getRegistNoList")
    public Page<SelectOptionDto> getRegistNoList()
            throws Exception {
        Page<SelectOptionDto> temp = hugeInfoPro001Service.getRegistNoList(RequestUtil.getPageParam());
        return temp;
    }


    @ResponseBody
    @RequestMapping("getProdIdlist")
    public Page<SelectOptionDto> getProdIdlist()
            throws Exception {
        Page<SelectOptionDto> temp = hugeInfoPro001Service.getProdIdlist(RequestUtil.getPageParam());
        return temp;
    }
}
