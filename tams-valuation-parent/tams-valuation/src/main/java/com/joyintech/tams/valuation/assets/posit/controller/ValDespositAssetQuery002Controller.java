package com.joyintech.tams.valuation.assets.posit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.valuation.assets.posit.service.ValDespositAssetQuery001Service;
import com.joyintech.tams.valuation.assets.posit.service.ValDespositAssetQuery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类资产详情页面
 * 
 * @author wangting
 * @version 1.0 2018年7月24日
 * @see ValDespositAssetQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("ValDespositAssetQuery002")
@RequestMapping("/valuation/assets/posit/ValDespositAssetQuery002")
public class ValDespositAssetQuery002Controller extends BaseController {

    /**
     * ValDespositAssetQuery002Service
     */
    @Autowired
    private ValDespositAssetQuery002Service valDespositAssetQuery002Service;

    /**
     * ValDespositAssetQuery001Service
     */
    @Autowired
    private ValDespositAssetQuery001Service valDespositAssetQuery001Service;

    /**
     * 详情页初始化页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        this.setSelectOptionToModel(model, DictCode.收益类型);
        this.setSelectOptionToModel(model, DictCode.计息基础);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.是否);
        model.addAttribute("yesOrNo", DictCodeList.是否_是.getValue());
        this.setSelectOptionToModel(model, DictCode.收益标识);
        this.setSelectOptionToModel(model, DictCode.境内外);
        this.setSelectOptionToModel(model, DictCode.营业日准则);
        this.setSelectOptionToModel(model, DictCode.推算方式);
        this.setSelectOptionToModel(model, DictCode.本金收益特征);
        this.setSelectOptionToModel(model, DictCode.利率类型);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        /** modify by yanya on 20191108 修改存款类型数据字典
        this.setSelectOptionToModel(model, DictCode.存款类资产类型);start**/
        this.setSelectOptionToModel(model, DictCode.存款类资产类型_GZ);
        /** modify by yanya on 20191108 修改存款类型数据字典 end**/
        this.setSelectOptionToModel(model, DictCode.存款核算分类);
        this.setSelectOptionToModel(model, DictCode.频率);
        //this.setSelectOptionToModel(model, DictCode.交易日历);
        // 交易日历下拉框
        List<SelectOptionDto> options = valDespositAssetQuery002Service.getCalendarInfoList();
        model.addAttribute("TRANSACTION_CALENDAR", JsonUtils.objectArray2Json(options.toArray()));
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
        String finprodId = getParam("finprodId");
        // 判断是不是新建操作
        if (StringUtil.isNotEmpty(finprodId)) {
            // 双击查看或者修改
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", finprodId);
            map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());//add by dongjun on 20181201
            ValDespositAssetQuery002Dto ValDespositAssetQuery002Dto = valDespositAssetQuery002Service.fingByfinprodId(
                map);
            // 修改标志
            String status = getParam("status");
            if (StringUtil.isNotEmpty(status)) {
                // 修改
                // 存在交易
                if (valDespositAssetQuery001Service.checkFinprodId(finprodId)) {
                    model.addAttribute("newOrEdit", true);
                    model.addAttribute("notEditOrgCode", true);
                }
                else {
                    model.addAttribute("newOrEdit", false);
                    model.addAttribute("notEditOrgCode", true);
                }

                ValDespositAssetQuery002Dto.setIsEdit(DictCodeList.是否_否.getValue());

                model.addAttribute("entity", ValDespositAssetQuery002Dto);
                model.addAttribute("status", "Y");
            }
            else {
                // 双击查看
                model.addAttribute("newOrEdit", true);
                model.addAttribute("notEditOrgCode", false);
                model.addAttribute("entity", ValDespositAssetQuery002Dto);
            }
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            ValDespositAssetQuery002Dto comdto = valDespositAssetQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", comdto);
            // 双击查看
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("newOrEdit", true);
            model.addAttribute("notEditOrgCode", false);

        }

        else {
            // 新建操作
            model.addAttribute("newOrEdit", false);
            model.addAttribute("notEditOrgCode", false);
            model.addAttribute("status", "Y");
            ValDespositAssetQuery002Dto ValDespositAssetQuery002Dto = new ValDespositAssetQuery002Dto();
            // 本金收益标志
            ValDespositAssetQuery002Dto.setCapiIncomeFeature(DictCodeListExt1.本金收益特征_保本.getValue());
            // 设置币种
            ValDespositAssetQuery002Dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            ValDespositAssetQuery002Dto.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
            ValDespositAssetQuery002Dto.setBlnArea(DictCodeList.境内外_境内.getValue());
            ValDespositAssetQuery002Dto.setIsEdit(DictCodeList.是否_是.getValue());
            model.addAttribute("entity", ValDespositAssetQuery002Dto);

        }

        return super.init(model);
    }
   
 
    /**
     * 利率表初始化
     * 
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTable1")
    public List<DateTableDto> getTable1() {
        String workflowId = getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            ValDespositAssetQuery002Dto comdto = valDespositAssetQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            return comdto.getTable1();
        }
        else {
            String finprodId = getParam("finprodId");
            return valDespositAssetQuery002Service.getTable1(finprodId);
        }

    }

    /**
     * 付息计划表初始化
     * 
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTable2")
    public List<DateTableDto> getTable2() {
        String workflowId = getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            ValDespositAssetQuery002Dto comdto = valDespositAssetQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            return comdto.getTable2();
        }
        else {
            /** modfiy by dongjun on 20181201 start **/
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", getParam("finprodId"));
            map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
            return valDespositAssetQuery002Service.getTable2(map);
            /** modfiy by dongjun on 20181201 start **/
        }
    }

    /**
     * 还本计划表初始化
     * 
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    /** modfiy by dongjun on 20181201 start **/
    @ResponseBody
    @RequestMapping("getTable3")
    public List<DateTableDto> getTable3() {
        String workflowId = getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            ValDespositAssetQuery002Dto comdto = valDespositAssetQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            List<DateTableDto> listDto=new ArrayList<>();
            if(comdto.getTable3()!=null){
            	listDto=comdto.getTable3();
            }
            return listDto;
        }
        else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", getParam("finprodId"));
            map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
            return valDespositAssetQuery002Service.getTable3(map);
        }
    }
    /** modfiy by dongjun on 20181201 end **/

    /**
     * 利率表 付息计划表 添加数据
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @param result BindingResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doAddtable1")
    public AjaxResult doAddTable1(@RequestBody ValDespositAssetQuery002Dto dto,
                                  BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        // ajaxResult.setData(.initTableDto(dto));
        List<DateTableDto> table2 = dto.getTable2();

        // dto1.setEffDate(dto.getVdate());
        ajaxResult.setData(valDespositAssetQuery002Service.setDateTableDto(table2, dto));
        return ajaxResult;
    }

    /**
     * 提交 插入 修改
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @param result AjaxResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @RequestMapping("doCommit")
    @ResponseBody
    public AjaxResult doCommit(@RequestBody ValDespositAssetQuery002Dto dto,
                               BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // List<DateTableDto> table2 = dto.getTable2();
        // // 进行升序排列
        // Collections.sort(table2, new Comparator<DateTableDto>() {
        //
        // public int compare(DateTableDto o1, DateTableDto o2) {
        // return o1.getVdate().compareTo(o2.getVdate());
        // }
        // });
        //
        // for(DateTableDto item:table2){
        // System.out.println(item.getVdateFt());
        //
        // }

        // valDespositAssetQuery002Service.doCommit(dto);

        ProcessParameter<ValDespositAssetQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValDespositAssetQuery002Dto> params = new WorkFlowParameter<>();
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产信息.getValue());
        params.setPageId("ValDespositAssetQuery002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 新建
        if (DictCodeList.是否_是.getValue().equals(dto.getIsEdit())) {
            // 业务代码
            params.setBusinessCode(dto.getFinprodId());
            // 设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("存款类资产新建：", dto.getFinprodId(),
                dto.getFinprodType()));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
        }
        else {
            // 业务代码
            params.setBusinessCode(dto.getFinprodId());
            // 设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("存款类资产修改：", dto.getFinprodId(),
                dto.getFinprodType()));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
        }
        //
        // return new AjaxResult(getMessage("COMMON_I_0001"));
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valDespositAssetQuery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * 提交前检查
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("commitCheck")
    public AjaxResult check(@Valid @RequestBody ValDespositAssetQuery002Dto dto,
                            BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

}
