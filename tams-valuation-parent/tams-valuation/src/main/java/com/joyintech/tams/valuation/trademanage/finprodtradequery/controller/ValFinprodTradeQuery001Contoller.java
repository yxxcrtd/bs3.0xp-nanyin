/*
 * 文件名：ValFinprodTradeQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.finprodtradequery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Contoller
 * @since 1.0
 */
@Controller
@PageId("ValFinprodTradeQuery001")
@RequestMapping("/valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001")
public class ValFinprodTradeQuery001Contoller extends BaseController {

    /**
     * ValFinprodTradeQuery001Service
     */
    @Autowired
    private ValFinprodTradeQuery001Service valFinprodTradeQuery001Service;

    @Autowired
    private ValProductInfoQuery001Service valProductInfoQuery001Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 业务类别下拉
        this.setSelectOptionToModel(model, DictCode.业务类型);
        // 交易方向下拉
        this.setSelectOptionToModel(model, DictCode.交易方向);

        return super.init(model);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValFinprodTradeQuery001Dto> findPage()
        throws Exception {
        return valFinprodTradeQuery001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valProductInfoQuery001Service.getFinprodId(map);
    }

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型 <一句话功能简述> <功能详细描述>
     * 
     * @return 日期区间
     * @throws ParseException ParseException
     */
    public Map<String, Object> getRequestMap()
        throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String startDate = this.getParam("startDate");
        String endDate = this.getParam("endDate");
        if (StringUtil.isNotEmpty(startDate)) {
            paraMap.put("startDate", DateUtil.parseDate(startDate, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            paraMap.put("endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
        }

        return paraMap;
    }

    /**
     * 导出数据 <一句话功能简述> <功能详细描述>
     * 
     * @return List<ValFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {

        String datas = this.getParam("datas");
        Map<String, Object> params = this.getRequestMap();
        List<ValFinprodTradeQuery001Dto> listdto;
        if (datas != null) {
            listdto = JsonUtils.json2List(datas, ValFinprodTradeQuery001Dto.class);
        }
        else {
            listdto = valFinprodTradeQuery001Service.queryPageList(params);
        }

        File outFile = valFinprodTradeQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/trademanage/finprodtradequery/conf/export_valFinprodTradeQuery001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除前判断
     * 
     * @param dto
     * @param result
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody @Valid ValFinprodTradeQuery001Dto dto,
            BindingResult result) throws Exception {

        List<ValFinprodTradeQuery001Dto> list = dto.getListValFinprodTradeQuery001Dto();
        if(list==null||list.size()<=0){
            return new AjaxResult("请选择数据", MessageType.错误消息);
        }
        for(ValFinprodTradeQuery001Dto item:list){
            String bookDate = item.getBookDate();
            String busiType = dto.getBusiTypeCode();
            if (StringUtil.isNotEmpty(bookDate)) {
                Date bDate = DateUtil.parseDate(bookDate, "yyyy-MM-dd");
                Date pDate = new Date();
                if(busiType.equals(DictCodeList.业务类型_股票交易.getValue())&&DateUtil.compareDate(pDate, bDate) > 0){
                	return new AjaxResult("交易已关账，不能删除！", MessageType.错误消息);            	  
                }else if (DateUtil.compareDate(pDate, bDate) > 0) {
                    return new AjaxResult("平台日之前的交易数据记录，是不是确定删除？", MessageType.确认消息);
                }
                else {
                    return new AjaxResult("确定要删除？", MessageType.确认消息);
                }
            }

            // 判断当前流程是否存在
            ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
                item);
            List<String> resultList = valFinprodTradeQuery001Service.getBusinessDataInFlow(
                processMark);
            if (!StringUtil.isEmptyList(resultList)) {
                // 流程存在情况
                return new AjaxResult("该条数据在流程中，不能删除", MessageType.错误消息);
            }
            
            
        }
//        String bookDate = dto.getBookDate();
//        if (StringUtil.isNotEmpty(bookDate)) {
//            Date bDate = DateUtil.parseDate(bookDate, "yyyy-MM-dd");
//            Date pDate = new Date();
//            if (DateUtil.compareDate(pDate, bDate) > 0) {
//                return new AjaxResult("平台日之前的交易数据记录，是不是确定删除？", MessageType.确认消息);
//            }
//            else {
//                return new AjaxResult("确定要删除？", MessageType.确认消息);
//            }
//        }
//
//        // 判断当前流程是否存在
//        ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
//            dto);
//        List<String> resultList = valFinprodTradeQuery001Service.getBusinessDataInFlow(
//            processMark);
//        if (!StringUtil.isEmptyList(resultList)) {
//            // 流程存在情况
//            return new AjaxResult("该条数据在流程中，不能删除", MessageType.错误消息);
//        }
        return new AjaxResult();
    }

    // /**
    // * 删除 金融交易
    // *
    // * @param dto 业务实体
    // * @return 画面地址
    // * @throws Exception
    // * @see
    // * @since 1.0
    // */
    // @ResponseBody
    // @RequestMapping(value = "delete", method = RequestMethod.POST)
    // public AjaxResult delete(@RequestBody ValFinprodTradeQuery001Dto dto) {
    // String msg = StringUtils.EMPTY;
    // int row = valFinprodTradeQuery001Service.delete(dto);
    // if (row > 0) {
    // msg = "删除成功";
    // return new AjaxResult(msg);
    // }
    // msg = "删除失败";
    // return new AjaxResult(msg, MessageType.错误消息);
    //
    // }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Valid ValFinprodTradeQuery001Dto dto, BindingResult br)
        throws Exception {

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        // 启动流程
        ProcessParameter<ValFinprodTradeQuery001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValFinprodTradeQuery001Dto> params = new WorkFlowParameter<>();

        // 业务代码（凭证流水号）
        params.setBusinessCode(dto.getTradeId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_金融交易删除.getValue());
        // 页面id
        params.setPageId("ValFinprodTradeQuery002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("金融交易查询删除：", dto.getTradeId(), // 交易编号
            dto.getBooksetId(), // 账套代码
            dto.getBusiTable()// 业务类型
        ));

        // 塞入流程参数
        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valFinprodTradeQuery001Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

}
