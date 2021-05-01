/*
 * 文件名：MstCoinFundQuotationInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.mstcoinfundquotationinfo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.mstcoinfundquotationinfo.service.ComCoinFundQuotationInfo001Service;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 货币型基金行情信息新建
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月17日
 * @see ComCoinFundQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ComCoinFundQuotationInfo002")
@RequestMapping("/common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002")
public class ComCoinFundQuotationInfo002Controller extends BaseController {

    /**
     * 向service注值
     */
    @Autowired
    private ComCoinFundQuotationInfo001Service comCoinFundQuotationInfo001Service;

    /**
     * 新建画面 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String finprodMarketId = getParam("finprodMarketId");
        // 判断是否存在于留痕表
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ComCoinFundQuotationInfo001Dto dto = comCoinFundQuotationInfo001Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
            // 判断逐渐是否为空
        }
        else if (StringUtil.isNotEmpty(finprodMarketId)) {
            // 将金融代码和固定格式的日期字符串传入参数集合
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
            map.put("yes", DictCodeList.是否_是.getValue());
            map.put("finprodId", finprodMarketId);
            Date date = new Date();
            date.setTime(Long.valueOf(getParam("valDate")));
            String val = DateUtil.format(date);
            map.put("valDate", val);
            // 根据估值日和金融代码进行查找
            List<ComCoinFundQuotationInfo001Dto> dto;
            try {
                dto = comCoinFundQuotationInfo001Service.selectPageInfo(map);
             // 将获取值传入前台（仅能获取一个值，所以可以直接取第一个值就行）
                ComCoinFundQuotationInfo001Dto dto1 = dto.get(0);
//                model.addAttribute("pageModel", dto1.getPageModel());
                model.addAttribute("entity", dto1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 初始化
        return super.init(model);
    }

    /**
     * 查询、修改和新建按钮点击时的check
     * 
     * @param dto dto
     * @param result BindingResult
     * @param  操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ComCoinFundQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 数据保存操作
     * @param dto 请求实体类
     * @param br 验证的错误
     * @return 错误集合
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) ComCoinFundQuotationInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<ComCoinFundQuotationInfo001Dto> processParameter = new ProcessParameter<ComCoinFundQuotationInfo001Dto>();
        WorkFlowParameter<ComCoinFundQuotationInfo001Dto> params = new WorkFlowParameter<ComCoinFundQuotationInfo001Dto>();
        params.setPageId("ComCoinFundQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // "基金代码"+"("+基金代码+")"+"-""估值日"+"("+估值日+")"
        params.setComment(entity -> this.getMessage("ComCoinFundQuotationInfo001.finprodId") + "("
                                    + entity.getFinprodId() + ")"
                                    + this.getMessage("ComCoinFundQuotationInfo001.valDate")
                                    + "(" + DateUtil.format(entity.getValDate()) + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);
        
        //add wangwei1 需求优化 默认导入日= 录入日 =平台日
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        
       // 流程开始
        String msg = comCoinFundQuotationInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取画面联动的基金管理人和基金类型
     * 
     * @return MstCoinFundQuotationInfo001Dto 基金行情信息实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getManageAndType")
    public ComCoinFundQuotationInfo001Dto getManageAndType() {
        Map<String,Object> map = RequestUtil.getPageParam();
        return comCoinFundQuotationInfo001Service.getManageAndType(map);// 获取基金代码和基金管理人
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
    @RequestMapping("/queryFinprod")
    public List<SuggestDto> queryFinprod()
        throws Exception {
        // 画面的输入值param前台的输入值，为了进行模糊查询
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取根据输入值检索到的结果
        List<SuggestDto> temp = comCoinFundQuotationInfo001Service.queryFinprod(param);// 获取基金代码
        return temp;// 返回基金代码
    }
    
    @ResponseBody
    @RequestMapping("/getIplatDate")
    public String getIplatDate(){
        return comCoinFundQuotationInfo001Service.getIplatDate();
    }
}
