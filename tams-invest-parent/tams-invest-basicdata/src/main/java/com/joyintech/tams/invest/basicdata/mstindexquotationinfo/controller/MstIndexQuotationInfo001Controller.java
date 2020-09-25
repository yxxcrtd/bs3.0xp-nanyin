/*
 * 文件名：MstIndexQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstindexquotationinfo.controller;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.service.MstIndexQuotationInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 指数行情信息查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("MstIndexQuotationInfo001")
@RequestMapping("/invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo001")
public class MstIndexQuotationInfo001Controller extends BaseController {

    /**
     * 注入Service类
     */
    @Autowired
    private MstIndexQuotationInfo001Service mstIndexQuotationInfo001Service;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.指数行情);// 下拉框初始化
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return Page<MstCoinFundQuotationInfo001Dto> 分页实体类结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstIndexQuotationInfo001Dto> selectPage()
        throws Exception {
        Page<MstIndexQuotationInfo001Dto> page = mstIndexQuotationInfo001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
     * 
     * @param dto 实体类
     * @param result BindingResult 条件验证结果
     * @return commonCheck check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstIndexQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 画面导出功能实现
     * 
     * @return ResponseEntity 确认的实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstIndexQuotationInfo001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 画面删除操作
     * 
     * @param dto 实体类
     * @param br 要检查的错误集
     * @return AjaxResult 条件验证结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstIndexQuotationInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 设置留痕参数和工作流参数
        ProcessParameter<MstIndexQuotationInfo001Dto> processParameter = new ProcessParameter<MstIndexQuotationInfo001Dto>();
        WorkFlowParameter<MstIndexQuotationInfo001Dto> params = new WorkFlowParameter<MstIndexQuotationInfo001Dto>();
        // 设置页面id
        params.setPageId("MstIndexQuotationInfo002");
        // 设置请求的elementId
        params.setElementId(dto.getElementId());
        // marketId指数代码进行设置工作流代码
        params.setBusinessCode(item -> item.getMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_指数行情信息.getValue());
        // 设置流程业务说明, entity即传入的流程信息
        // "行情代码" + "(" + 行情代码 + ")" + "-" + "生效日" + "(" + 生效日 + ")"
        params.setComment(entity -> this.getMessage("MstIndexQuotationInfo001.marketId") + "("
                                    + entity.getMarketId() + ")"
                                    + this.getMessage("MstIndexQuotationInfo001.effDate") + "("
                                    + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);// 设置工作流注解
        String msg = mstIndexQuotationInfo001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstIndexQuotationInfo001Dto[dto.getSelectedItem().size()]));// 调用工作流事件
        return new AjaxResult(msg);// 返回工作流错误信息
    }
}
