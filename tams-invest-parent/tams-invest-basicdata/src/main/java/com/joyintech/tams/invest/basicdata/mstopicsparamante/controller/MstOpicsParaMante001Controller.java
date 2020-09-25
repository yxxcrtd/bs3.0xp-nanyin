/*
 * 文件名：MstOpicsParaMante001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstopicsparamante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.service.MstOpicsParaManteService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * OPICS参数维护查询 
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaMante001Controller
 * @since 1.0
 */
@Controller
@PageId("MstOpicsParaMante001")
@RequestMapping("/invest/basicdata/mstopicsparamante/MstOpicsParaMante001")
public class MstOpicsParaMante001Controller extends BaseController {

    /**
     * OPICS参数维护业务层
     */
    @Autowired
    private MstOpicsParaManteService mstOpicsParaManteService;

    /**
     * 初始化方法{@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        /*
         * 交易状态的下拉框赋值
         */
        setSelectOptionToModel(model, DictCode.OPICS参数类型);
        return super.init(model);
    }

    /**
     * 查询按钮的查询方法 
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectOpicsParaManteInfo")
    public Page<MstOpicsParaManteDto> selectOpicsParaManteInfo()
        throws Exception {
        Page<MstOpicsParaManteDto> page = mstOpicsParaManteService.selectOpicsParaManteInfo(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 删除按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstOpicsParaManteDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * @param dto 实体类
     * @param br 结果集
     * @return AjaxResult
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstOpicsParaManteDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstOpicsParaManteDto> processParameter = new ProcessParameter<MstOpicsParaManteDto>();
        WorkFlowParameter<MstOpicsParaManteDto> params = new WorkFlowParameter<MstOpicsParaManteDto>();
        params.setPageId("MstOpicsParaMante002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getItemId());

        params.setBusinessType(DictCodeList.共通业务类型_OPICS参数维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("MstOpicsParaMante001.itemId") + "(" + entity.getItemId()
                      + ")" + "-" + this.getMessage("MstOpicsParaMante001.itemName") + "("
                      + entity.getItemName() + ")"
                      + this.getMessage("MstOpicsParaMante001.paraType") + "("
                      + entity.getParaType() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstOpicsParaManteService.start(processParameter,
            dto.getSelectedItem().toArray(new MstOpicsParaManteDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出
     * 
     * @return ResponseEntity
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstOpicsParaManteService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}