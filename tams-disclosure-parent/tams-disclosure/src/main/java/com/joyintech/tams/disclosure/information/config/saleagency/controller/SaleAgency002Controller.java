/*
 * 文件名：ProdPartner001Controller.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.saleagency.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.service.SaleAgency002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 代销机构信息维护
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency002Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("SaleAgency002")
@RequestMapping("/disclosure/information/config/saleagency/SaleAgency002")
public class SaleAgency002Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 代销机构信息维护service注入
     */
    @Autowired
    private SaleAgency002Service saleAgency002Service;
    /**
     * 
     * {@inheritDoc}
     */

    /**
     * 新建界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.信披_代销机构_维护类型);
        // 流程
        String workflowId = this.getParam("workflowId");
        String sequenceNo=getParam("sequenceNo");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            SaleAgencyDto dto = saleAgency002Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(sequenceNo)) {
            //通过主键查询数据
            SaleAgencyDto dto = saleAgency002Service.selectDetail(sequenceNo);
            dto.setMaintenanceType(DictCodeListExt5.维护类型_代销机构.getValue());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
    }

    /**
     * 产品代码联想下拉框
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return saleAgency002Service.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     * 获取指标树结构数据
     * @return 画面元素
     * @throws Exception Exception
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("/getAllProdIds")
    public List<TreeNode> getAllProdIds()
            throws Exception {
        String agencyId=super.getRequest().getParameter("agencyId");
        String workflowId=this.getParam("workflowId");
        //流程中查看数据反显
        if(StringUtil.isNotEmpty(workflowId) && !workflowId.equals("null")){
            SaleAgencyDto dto = saleAgency002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            return saleAgency002Service.getProductTree_wfl(dto.getNodes());
        }

        if(StringUtil.isNotEmpty(agencyId)){//查看、修改
            return saleAgency002Service.getProductTreeByAgencyId(agencyId);
        }else{//新建
            return saleAgency002Service.getProductTree();
        }
    }

    /**
     * 保存数据check
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) SaleAgencyDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());

    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) SaleAgencyDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(DictCodeListExt5.维护类型_代销机构.getValue().equals(dto.getMaintenanceType()) && StringUtil.isEmpty(dto.getSequenceNo())){
            dto.setSequenceNo(IDGenerator.generate(16).replace("-",""));
        }
        ProcessParameter<SaleAgencyDto> processParameter = new ProcessParameter<SaleAgencyDto>();
        WorkFlowParameter<SaleAgencyDto> params = new WorkFlowParameter<SaleAgencyDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getAgencyName());
        //todo
        params.setBusinessType(DictCodeListExt4.共通业务类型_代销机构维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(DictCodeListExt5.维护类型_代销机构.getValue().equals(dto.getMaintenanceType())){
            params.setComment(entity -> "代销机构" + "(" + entity.getAgencyName() + ")");
        }else{
            params.setComment(entity -> "产品代码"+"("+entity.getProdId()+")"+"代销机构" + "(" + entity.getAgencyIds() + ")");
        }

        processParameter.setWorkFlowParameter(params);
        String msg = saleAgency002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
