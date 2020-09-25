/*
 * 文件名：MstTradeOpponent001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.msttradeopponent.controller;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.service.MstTradeOpponent001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
/**
 * 交易对手管理
 * @author fanshuanghu
 * @version 1.0 2018年8月30日
 * @see MstTradeOpponent001Controller
 * @since 1.0
 */
@Controller
@PageId("MstTradeOpponent001")
@RequestMapping("/invest/basicdata/msttradeopponent/MstTradeOpponent001")
public class MstTradeOpponent001Controller extends BaseController{
    /**
     * 注入交易对手service
     */
    @Autowired
    private MstTradeOpponent001Service mstTradeOpponent001Service;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.交易对手类型);
        return super.init(model);
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
        ValidateGroupDelete.class}) MstTradeOpponentDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 分页查询
     * @return 泛型后的分页实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstTradeOpponentDto> findPage() throws Exception{
        Page<MstTradeOpponentDto> page = mstTradeOpponent001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    /**
     * 导出
     * @return 返回导出的数据
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstTradeOpponent001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 获取交易对手类型
     * @return List<SuggestDto> 下拉框实体类集合
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCounterType") // 获取交易对手类型
    public List<SelectOptionDto> getCounterType()
        throws Exception {
        // 画面的输入值
        Map<String, Object> param = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = mstTradeOpponent001Service.getCounterType(param);// 获取交易对手类型
        return temp;// 返回取得的值
    }
    /**
     * 删除事件
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstTradeOpponentDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 设置留痕参数和工作流参数
        ProcessParameter<MstTradeOpponentDto> processParameter = new ProcessParameter<MstTradeOpponentDto>();
        WorkFlowParameter<MstTradeOpponentDto> params = new WorkFlowParameter<MstTradeOpponentDto>();
        params.setPageId("MstTradeOpponent002");
        params.setElementId(dto.getElementId());
        // counterId交易对手代码进行设置工作流代码
        params.setBusinessCode(item -> item.getCounterId());
        params.setBusinessType(DictCodeList.共通业务类型_交易对手管理.getValue());
        // 设置流程业务说明, entity即传入的流程信息
        // 交易对手名称 +"("+交易对手名称 +")"+"-"+"交易对手类型"+"-"+"交易对手类型"+"交易对手代码"+"-"+"
        params.setComment(entity -> this.getMessage("MstTradeOpponent001.counterName") + "("
                                    + entity.getCounterName() + ")"
                                    + this.getMessage("MstTradeOpponent001.counterType") + "("
                                    + entity.getCounterType() + ")"+ 
                                    this.getMessage("MstTradeOpponent001.counterId") + "("
                                    + entity.getCounterId() + ")");
        processParameter.setWorkFlowParameter(params);// 设置工作流注解
        String msg = mstTradeOpponent001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstTradeOpponentDto[dto.getSelectedItem().size()]));// 调用工作流事件
        return new AjaxResult(msg);// 返回工作流错误信息
    }
}
