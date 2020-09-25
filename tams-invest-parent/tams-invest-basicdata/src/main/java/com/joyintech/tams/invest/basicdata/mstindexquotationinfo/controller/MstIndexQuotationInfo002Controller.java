/*
 * 文件名：MstIndexQuotationInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstindexquotationinfo.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.dao.MstExponentQuotationMapper;
import com.joyintech.tams.common.base.entity.MstExponentQuotation;
import com.joyintech.tams.common.base.entity.MstExponentQuotationKey;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.service.MstIndexQuotationInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 指数行情信息新建
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("MstIndexQuotationInfo002")
@RequestMapping("/invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002")
public class MstIndexQuotationInfo002Controller extends BaseController {

    /**
     * 注入实体类的Service类
     */
    @Autowired
    private MstIndexQuotationInfo001Service mstIndexQuotationInfo001Service;

    /**
     * 注入实体类的Mapper类
     */
    @Autowired
    private MstExponentQuotationMapper mstExponentQuotationMapper;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.指数行情);// 下拉框初始化
        String marketId = getParam("marketId");// 获取主键marketId
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);// 获取工作流Id
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {// 判断是否存在于工作流中
            MstIndexQuotationInfo001Dto dto = mstIndexQuotationInfo001Service.getBussinessEntityByWorkflowId(
                workflowId);// 启动工作流
            model.addAttribute("entity", dto);// 将获取的实体类加入到界面中
            // 判断逐渐是否为空
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(marketId)) {// 判断marketId是否为空
            String effDate = getParam("effDate");// 获取主键有效日
            MstExponentQuotationKey key = new MstExponentQuotationKey();// 新建主键实体类
            key.setMarketId(marketId);// 将主键marketId加入到主键实体类中
            Date date = new Date();// 新建日期处理类
            date.setTime(Long.valueOf(effDate));// 将前台获取的字符串转成日期
            key.setEffDate(date);// 将主键生效日加入到主键实体类
            MstExponentQuotation dto = mstExponentQuotationMapper.selectByPrimaryKey(key);// 通过主键进行查询
            model.addAttribute("entity", dto);// 将获取的实体类加入到界面中
        }
        return super.init(model);// 初始化界面
    }

    /**
     * 查询、修改和新建按钮点击时的check
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
        ValidateGroupEdit.class}) MstIndexQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());// 返回错误信息
    }

    /**
     * 数据保存操作
     * 
     * @param dto 请求实体类
     * @param br 验证的错误
     * @return AjaxResult 错误信息集合
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstIndexQuotationInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<MstIndexQuotationInfo001Dto> processParameter = new ProcessParameter<MstIndexQuotationInfo001Dto>();
        WorkFlowParameter<MstIndexQuotationInfo001Dto> params = new WorkFlowParameter<MstIndexQuotationInfo001Dto>();
        params.setPageId("MstIndexQuotationInfo002");// 设置页面id
        params.setElementId(dto.getElementId());// 设置业务参数elementId
        params.setBusinessCode(item -> item.getMarketId());// 设置业务id
        params.setBusinessType(DictCodeList.共通业务类型_指数行情信息.getValue());// 设置业务类型
        // 设置流程业务说明, entity即传入的dto
        //行情代码 +"("+行情代码 +")"+"-"+"生效日"+"-"+"生效日"
        params.setComment(entity -> this.getMessage("MstIndexQuotationInfo001.marketId") + "("
                                    + entity.getMarketId() + ")"
                                    + this.getMessage("MstIndexQuotationInfo001.effDate") + "("
                                    +DateUtil.format(entity.getEffDate())  + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);
        // 流程开始
        String msg = mstIndexQuotationInfo001Service.start(processParameter, dto);
        // 返回错误信息
        return new AjaxResult(msg);
    }
}
