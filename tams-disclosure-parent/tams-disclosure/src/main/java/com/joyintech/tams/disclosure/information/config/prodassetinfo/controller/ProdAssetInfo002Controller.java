/*
 * 文件名：ProdPartner001Controller.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodassetinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.service.ProdAssetInfo002Service;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 合作机构信息维护
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdAssetInfo002Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("ProdAssetInfo002")
@RequestMapping("/disclosure/information/config/prodassetinfo/ProdAssetInfo002")
public class ProdAssetInfo002Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 合作机构信息维护service注入
     */
    @Autowired
    private ProdAssetInfo002Service prodAssetInfo002Service;
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
        model.addAttribute("workflowId",workflowId);
        String prodAssetId=getParam("prodAssetId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ProdAssetInfoDto dto = prodAssetInfo002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(prodAssetId)) {
            //通过主键查询数据
            ProdAssetInfoDto dto = prodAssetInfo002Service.selectDetail(prodAssetId);
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
            ValidateGroupEdit.class}) ProdAssetInfoDto dto, BindingResult result) {
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
            ValidateGroupEdit.class}) ProdAssetInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getProdAssetId())){
            dto.setProdAssetId(IDGenerator.generate(36));
        }
        ProcessParameter<ProdAssetInfoDto> processParameter = new ProcessParameter<ProdAssetInfoDto>();
        WorkFlowParameter<ProdAssetInfoDto> params = new WorkFlowParameter<ProdAssetInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品资产组合情况维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("产品代码") + "(" + entity.getProdId() + ")"
                + "生效区间" + "(" + DateUtil.format(entity.getEffDate(),"yyyyMMdd") +"-"+DateUtil.format(entity.getEffEndDate(),"yyyyMMdd")+ ")");
        processParameter.setWorkFlowParameter(params);
        String msg = prodAssetInfo002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }



}
