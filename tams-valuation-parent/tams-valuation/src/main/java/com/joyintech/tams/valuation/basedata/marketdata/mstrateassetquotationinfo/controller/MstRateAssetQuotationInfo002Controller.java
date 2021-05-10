/*
 * 文件名：MstRateAssetQuotationInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lby
 * 修改时间：2020年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.mstrateassetquotationinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basedata.marketdata.mstrateassetquotationinfo.dto.MstRateAssetQuotationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.mstrateassetquotationinfo.service.MstRateAssetQuotationInfo001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 新建利率型资产信息
 *
 * @author lby
 * @version 1.0 2020年10月22日
 * @see MstRateAssetQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("MstRateAssetQuotationInfo002")
@RequestMapping("/valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002")
public class MstRateAssetQuotationInfo002Controller extends BaseController {


    @Autowired
    private MstRateAssetQuotationInfo001Service mstRateAssetQuotationInfo001Service;
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 新建、编辑、查看界面的初始化
     *
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String finprodId = getParam("finprodId");
        String workflowId = getParam("workflowId");
        setSelectOptionToModel(model, DictCode.收益类型);
        if (StringUtil.isNotEmpty(finprodId)) {
            //将输入的估值日期格式化
            String valDate = DateUtil.format(new Date(Long.valueOf(getParam("valDate"))));
            Map<String, Object> param = new HashMap<>();
            //将需要的数据字典和参数添加到map中
            param.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
            param.put("finprodId", finprodId);
            param.put("valDate", valDate);
            //根据主键获取业务实体
            MstRateAssetQuotationInfo001Dto dto = mstRateAssetQuotationInfo001Service.selectMstRateAsset(param);
            //设置到model中
            model.addAttribute("entity", dto);
            model.addAttribute("finprodId", finprodId);
        }
        //来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            //根据工作流与留痕处理提供的共通方法获取业务实体
            MstRateAssetQuotationInfo001Dto mstRateAssetQuotationInfo001Dto;
            mstRateAssetQuotationInfo001Dto = mstRateAssetQuotationInfo001Service.
                    getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("pageModel", mstRateAssetQuotationInfo001Dto.getPageModel());
            model.addAttribute("entity", mstRateAssetQuotationInfo001Dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("/getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList()
            throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        //map.put("finProdMarketId", param);getAssetCodeList

        param.put("PROFITTYPE_VALUE_02", DictCodeList.收益类型_收益型.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        param.put("TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        List<SelectOptionDto> temp = mstRateAssetQuotationInfo001Service.getAssetCodeList(param);
        return temp;
    }

    /**
     * 资产代码画面联动
     * 通过输入的资产代码联动出资产名称和管理人
     *
     * @param finProdMarketId 市场代码
     * @return dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinProdNameAndFinprodId")
    public MstRateAssetQuotationInfo001Dto getFinProdNameAndFinprodId(String finProdMarketId, String profitType) {
        //将需要的数据字典和参数添加到map中
        Map<String, Object> map = new HashMap<>();
        map.put("finProdMarketId", getParam("finProdMarketId"));
        map.put("PROFITTYPE_VALUE_02", DictCodeList.收益类型_收益型.getValue());
        map.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        return mstRateAssetQuotationInfo001Service.getFinProdNameAndFinprodId(map);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstRateAssetQuotationInfo001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstRateAssetQuotationInfo002");
        wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
        wflValidInputParameter.setBusinessDataPk2(dto.getValDateStr());
        boolean is = WorkFlowUtil.isNotFromToDoList();
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(dto.getSelectedItem()!=null||dto.getSelectedItem().size()>0){
                    Date sysDate = DateUtil.platDateFromCache();
                    if (StringUtil.isNotEmpty(dto.getImportDate()) && DateUtil.compareDate(DateUtil.format(DateUtil.parse(dto.getImportDate())), DateUtil.format(sysDate)) != 0) {
                        errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                    }
                }
            }
            else{
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getValDate())) {
                        Map<String, Object> map = RequestUtil.getPageParam();
                        map.put("finprodId", dto.getFinprodId());
                        map.put("valDate", dto.getValDateStr());
                        map.put("finprodType2", dto.getFinprodType2());
                        /** modify by yanya on 20210329 使用估值侧类型进行过滤**/
                        map.put("finprodType", dto.getFinprodType());
                        //新建时，资产代码+估值日期检索基金行情信息，数据已存在的情况下，报错
                        List<MstRateAssetQuotationInfo001Dto> list = mstRateAssetQuotationInfo001Service.selectByIdAndDate(map);
                        if (!StringUtil.isEmptyList(list)) {
                            errorList.add(new ValidMessage("finProdMarketId", "资产代码" + dto.getFinProdMarketId() + " 估值日期" + dto.getValDateStr() + " 数据不能重复。"));
                        }
                    }
                }
                else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                    Date sysDate = DateUtil.platDateFromCache();
                    if (StringUtil.isNotEmpty(dto.getImportDate()) && DateUtil.compareDate(DateUtil.format(DateUtil.parse(dto.getImportDate())), DateUtil.format(sysDate)) != 0) {
//                        errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                    }
                }
            }
        }
        else{
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getValDate())) {
                    Map<String, Object> map = RequestUtil.getPageParam();
                    map.put("finprodId", dto.getFinprodId());
                    map.put("valDate", dto.getValDateStr());
                    map.put("finprodType2", dto.getFinprodType2());
                    /** modify by yanya on 20210329 使用估值侧类型进行过滤**/
                    map.put("finprodType", dto.getFinprodType());
                    //新建时，资产代码+估值日期检索基金行情信息，数据已存在的情况下，报错
                    List<MstRateAssetQuotationInfo001Dto> list = mstRateAssetQuotationInfo001Service.selectByIdAndDate(map);
                    if (!StringUtil.isEmptyList(list)) {
                        errorList.add(new ValidMessage("finProdMarketId", "资产代码" + dto.getFinProdMarketId() + " 估值日期" + dto.getValDateStr() + " 数据不能重复。"));
                    }
                }
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br  BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) MstRateAssetQuotationInfo001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstRateAssetQuotationInfo001Dto> processParameter = new ProcessParameter<MstRateAssetQuotationInfo001Dto>();
        WorkFlowParameter<MstRateAssetQuotationInfo001Dto> params = new WorkFlowParameter<MstRateAssetQuotationInfo001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getFinprodId())) {
            dto.setFinprodId(IDGenerator.generate());
        }
        params.setBusinessCode(dto.getFinprodId());

        params.setBusinessType(DictCodeListExt3.共通业务类型_项目类_计划类利率型行情信息.getValue());

        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
                entity -> "资产代码" + "(" + entity.getFinProdMarketId() + ")"
                        + "估值日期" + "(" + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        // 默认导入日= 录入日 =平台日
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);

        // 流程开始
        String msg = mstRateAssetQuotationInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
