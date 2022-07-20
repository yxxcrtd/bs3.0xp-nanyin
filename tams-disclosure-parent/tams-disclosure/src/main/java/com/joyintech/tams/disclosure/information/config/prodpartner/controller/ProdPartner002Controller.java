/*
 * 文件名：ProdPartner001Controller.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodpartner.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner001Service;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 合作机构信息维护
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdPartner002Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("ProdPartner002")
@RequestMapping("/disclosure/information/config/prodpartner/ProdPartner002")
public class ProdPartner002Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 合作机构信息维护service注入
     */
    @Autowired
    private ProdPartner002Service prodPartner002Service;
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
        // 流程
        String workflowId = this.getParam("workflowId");
        String prodPartenerId=getParam("prodPartenerId");
//        String prodId = getParam("prodId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ProdPartnerDto dto = prodPartner002Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(prodPartenerId)) {
            //通过主键查询数据
            ProdPartnerDto dto = prodPartner002Service.selectDetail(prodPartenerId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
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
            ValidateGroupEdit.class}) ProdPartnerDto dto, BindingResult result) {
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
            ValidateGroupEdit.class}) ProdPartnerDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getProdPartenerId())){
            dto.setProdPartenerId(IDGenerator.generate(36));
        }
        ProcessParameter<ProdPartnerDto> processParameter = new ProcessParameter<ProdPartnerDto>();
        WorkFlowParameter<ProdPartnerDto> params = new WorkFlowParameter<ProdPartnerDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_合作机构信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品代码" + "(" + entity.getProdId() + ")"
                + "合作机构" + "(" + dto.getPartnerName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = prodPartner002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }



}
