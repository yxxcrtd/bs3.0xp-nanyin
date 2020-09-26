/*
 * 文件名：ValAssetBook002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.controller;

import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.service.ValVoucherQuery001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetbook002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 新建账套
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook002Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetBook002")
@RequestMapping("/valuation/accounadjustmanage/assetbook/ValAssetBook002")
public class ValAssetBook002Controller extends BaseController {

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private ValAssetBook002Service valAssetBook002Service;

    @Autowired
    private BaseCommonService baseCommonService;

    @Autowired
    private ValVoucherQuery001Service valVoucherQuery001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String booksetId = this.getParam("booksetId");
//        String standardCcy = DictCodeList.币种_人民币_CNY.getValue();
//        String initDate = DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd");
//        String endDate = "";
        ValAssetbook002Dto dto = new ValAssetbook002Dto();
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        // 判断是修改操作还是新建操作
        if (StringUtil.isNotEmpty(booksetId)) {
            dto = valAssetBook002Service.getBookSetById(booksetId);
            // 修改操作
            String status = this.getParam("status");
            if(StringUtil.isNotEmpty(status)&&"02".equals(status)){
                model.addAttribute("EditCheck", true);
            }else{
                model.addAttribute("EditCheck", false); 
            }
            model.addAttribute("newOrEdit", true);
            dto.setIsEdit(DictCodeList.是否_是.getValue());
           
            // 查询该账套是否已生成凭证
            int voucherCount = valAssetBook002Service.queryVoucherCount(booksetId);
            model.addAttribute("hasVoucher", (voucherCount > 0 ? true : false));
            
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = valAssetBook002Service.getBussinessEntityByWorkflowId(workflowId);
        }else {
            // 新建操作
            model.addAttribute("EditCheck", false); 
            model.addAttribute("newOrEdit", false);
        }
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.账套类别);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        // 会计分录
        model.addAttribute("ZJ",
            JsonUtils.objectArray2Json(valAssetBook002Service.getAcctStandard().toArray()));
        model.addAttribute("isDefault", DictCodeList.是否_否.getValue());
        model.addAttribute("standardCcy", dto.getStandardCcy());
        model.addAttribute("initDate", DateUtil.format(dto.getStartDate(), "yyyy-MM-dd"));
        model.addAttribute("endDate", DateUtil.format(dto.getEndDate(), "yyyy-MM-dd"));

        model.addAttribute(GlobalConstant.ENTITY, dto);
        
        return super.init(model);
    }

    // /**
    // * 提交 插入
    // *
    // * @return boolean
    // * @see
    // * @since 1.0
    // */
    // @RequestMapping("doCommit")
    // @ResponseBody
    // public AjaxResult doCommit(@Valid @RequestBody ValAssetbook002Dto dto,
    // BindingResult result) {
    // if (result != null && result.hasErrors()) {
    // return new AjaxResult(result);
    // }
    // valAssetBook002Service.insert(dto);
    // return new AjaxResult(getMessage("COMMON_I_0001"));
    // }

    /**
     * 获取会计准则 <功能详细描述>
     * 
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getAcctStandard")
    @ResponseBody
    public List<SelectOptionDto> getAcctStandard() {
        return valAssetBook002Service.getAcctStandard();
    }

    /**
     * 检查账套代码是否重复 <功能详细描述>
     * 
     * @return boolean
     * @see
     * @since 1.0
     */
    @RequestMapping("checkBooksetId")
    @ResponseBody
    public boolean checkBooksetId() {
        String id = this.getParam("id");
        return valAssetBook002Service.checkBooksetId(id);

    }

    /**
     * 下拉框：所属估值方案
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("getScheme")
    public List<SelectOptionDto> getScheme() {
        return valAssetBook002Service.findScheme(RequestUtil.getPageParam());
    }

    /**
     * 修改凭证摘要
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    public AjaxResult doUpdate(ValAssetbook002Dto dto) {
        ProcessParameter<ValAssetbook002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetbook002Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getBooksetId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账套管理.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("账套管理修改：", entity.getBooksetId(), // 账套代码
            entity.getBooksetName()// 账套名称
        ));

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetBook002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid ValAssetbook002Dto dto, BindingResult result,
                            String operateType) {
        return commonCheck(result, operateType);
    }
    
    

  
      /**
       * 
       * 税务方案
       * @return
       * @see
       * @since 1.0
       */
      @ResponseBody
      @RequestMapping("getTaxScheme")
      public List<SelectOptionDto> getTaxScheme() {
          return valAssetBook002Service.findTaxScheme(RequestUtil.getPageParam());
      }
      
      /**
       * 
       * 影子定价方案
       * @return
       * @see
       * @since 1.0
       */
      @ResponseBody
      @RequestMapping("getShadowPriceScheme")
      public List<SelectOptionDto> getShadowPriceScheme() {
          return valAssetBook002Service.findShadowPriceScheme(RequestUtil.getPageParam());
      }

    @RequestMapping("selectRecondSize")
    @ResponseBody
    public Boolean findPage(ValVoucherQuery001SearchDto dto) throws Exception {
          if(valVoucherQuery001Service.findPage(dto).getRoot().size()>0)
                return true;
          else
                return false;

    }

}
