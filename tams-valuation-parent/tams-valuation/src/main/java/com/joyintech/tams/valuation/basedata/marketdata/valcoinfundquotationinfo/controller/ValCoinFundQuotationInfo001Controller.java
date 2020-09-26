/*
 * 文件名：ValCoinFundQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valcoinfundquotationinfo.controller;

import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.valuation.basedata.marketdata.valcoinfundquotationinfo.dto.ValCoinFundQuotationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valcoinfundquotationinfo.service.ValCoinFundQuotationInfo001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 货币型基金行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月16日
 * @see ValCoinFundQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValCoinFundQuotationInfo001")
@RequestMapping("/valuation/basedata/marketdata/valcoinfundquotationinfo/ValCoinFundQuotationInfo001")
public class ValCoinFundQuotationInfo001Controller extends BaseController {

    /**
     * service注入
     */
    @Autowired
    private ValCoinFundQuotationInfo001Service valCoinFundQuotationInfo001Service;

    /**
     * 新建货币型基金行情信息画面
     * 
     * @param model 参数Model
     * @return String 返回请求的界面
     * @see
     * @since 1.0 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 画面查询方法
     * 
     * @return Page<ValCoinFundQuotationInfo001Dto> 分页实体类结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValCoinFundQuotationInfo001Dto> selectAllCurrencyFund()
        throws Exception {
        Page<ValCoinFundQuotationInfo001Dto> page = valCoinFundQuotationInfo001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

//    /**
//     * 画面导出功能实现
//     * 
//     * @return ResponseEntity 确认的实体类
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping("export")
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        return valCoinFundQuotationInfo001Service.export(this.getPageId(),
//            RequestUtil.getParameters(getRequest()));
//    }
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
        return valCoinFundQuotationInfo001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 删除按钮点击时的check
     * 
     * @param dto 实体类
     * @param result BindingResult 条件验证结果
     * @param 操作类型
     * @return commonCheck check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValCoinFundQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        ValidateGroupDelete.class}) ValCoinFundQuotationInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 设置留痕参数和工作流参数
        ProcessParameter<ValCoinFundQuotationInfo001Dto> processParameter = new ProcessParameter<ValCoinFundQuotationInfo001Dto>();
        WorkFlowParameter<ValCoinFundQuotationInfo001Dto> params = new WorkFlowParameter<ValCoinFundQuotationInfo001Dto>();
        params.setPageId("ValCoinFundQuotationInfo002");
        params.setElementId(dto.getElementId());
        // finprodId基金代码进行设置工作流代码
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的流程信息
        // "基金代码"+"("+基金代码+")"+"-""估值日"
        // +"("+估值日+")"
        params.setComment(entity -> this.getMessage("ValCoinFundQuotationInfo001.finprodId") + "("
                                    + entity.getFinprodId() + ")"
                                    + this.getMessage("ValCoinFundQuotationInfo001.valDate") + "("
                                    + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);// 设置工作流注解
        String msg = valCoinFundQuotationInfo001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ValCoinFundQuotationInfo001Dto[dto.getSelectedItem().size()]));// 调用工作流事件
        return new AjaxResult(msg);// 返回工作流错误信息
    }

    /**
     * 获取基金代码的联想文本框的值
     * 
     * @return List<SuggestDto>联想文本框实体类的列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCodeName")
    public List<SuggestDto> getSuggestDataByName()
        throws Exception {
        // 画面的输入值param前台的输入值，为了进行模糊查询
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取根据输入值检索到的结果
        List<SuggestDto> temp = valCoinFundQuotationInfo001Service.selectForCode(param);// 获取基金代码
        return temp;// 返回基金代码
    }

    /**
     * 获取基金管理人联想文本框的值
     * 
     * @return List<SuggestDto>联想文本框的列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManagement") // 获取基金管理人的名称
    public List<SuggestDto> getManagement()
        throws Exception {
        // 画面的输入值
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取根据输入值检索到的结果
        List<SuggestDto> temp = valCoinFundQuotationInfo001Service.getManagement(param);// 获取基金管理人
        return temp;// 返回取得的值
    }
}
