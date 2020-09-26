/*
 * 文件名：MarketInformationController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzehui
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valbondquotationinfo.controller;

import java.util.List;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.valuation.basedata.marketdata.valbondquotationinfo.dto.ValBondQuotationInfoDto;
import com.joyintech.tams.valuation.basedata.marketdata.valbondquotationinfo.service.ValBondQuotationInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see ValBondQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValBondQuotationInfo001")
@RequestMapping("/valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo001")
public class ValBondQuotationInfo001Controller extends BaseController {

    /**
     * 
     */
    @Autowired
    private ValBondQuotationInfoService valBondQuotationInfoService;

    /**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.估值来源);
        return super.init(model);
    }

    /**
     * 债券代码下拉选择框
     * @param param 参数
     * @return list结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String param)
        throws Exception {
        List<SuggestDto> list = valBondQuotationInfoService.getSecIdOption(param);
        return list;
    }

    /**
     * 债券简称的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getFinProdAbbr")
    public List<SuggestDto> getFinProdAbbr(String param)
        throws Exception {
        return valBondQuotationInfoService.getFinProdAbbr(param);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValBondQuotationInfoDto> selectAll()
        throws Exception {
        Page<ValBondQuotationInfoDto> page = valBondQuotationInfoService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValBondQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValBondQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ValBondQuotationInfoDto> processParameter = new ProcessParameter<ValBondQuotationInfoDto>();
        WorkFlowParameter<ValBondQuotationInfoDto> params = new WorkFlowParameter<ValBondQuotationInfoDto>();
        params.setPageId("ValBondQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecId());
        params.setBusinessType(DictCodeList.共通业务类型_债券估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity -> SpringContextUtil.getTextValue("ValBondQuotationInfo001.secId") + "(" + entity.getSecId() + ")" + "-"
            + SpringContextUtil.getTextValue("ValBondQuotationInfo001.valueDate") + "("
            + DateUtil.format(entity.getValueDate()) + ")"+ this.getMessage("ValBondQuotationInfo001.valueSource") + "("
            + entity.getValueSourceStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = valBondQuotationInfoService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ValBondQuotationInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
        
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return valBondQuotationInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
