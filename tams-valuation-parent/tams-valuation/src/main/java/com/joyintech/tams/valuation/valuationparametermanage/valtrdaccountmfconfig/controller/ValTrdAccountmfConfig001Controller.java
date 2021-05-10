/*
 * 文件名：ValTrdAccountmfConfig001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：tanxuan
 * 修改时间：2021年3月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.service.ValTrdAccountmfConfig001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 
 * 费用预提设置
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001
 * @since 1.0
 */
@Controller
@PageId("ValTrdAccountmfConfig001")
@RequestMapping("/valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig001")
public class ValTrdAccountmfConfig001Controller extends BaseController {
    
    /**
     * 费用预提设置service
     */
    @Autowired
    private ValTrdAccountmfConfig001Service valTrdAccountmfConfig001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.一次性费用类型);
    	super.setSelectOptionToModel(model, DictCode.登记托管机构);
        return super.init(model);
    }
    
    /**
     * 页面查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValTrdAccountmfConfig001Dto> queryPageList() throws Exception{
        Map<String, Object> params= RequestUtil.getPageParam();
        return valTrdAccountmfConfig001Service.queryPageList(params);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId()
        throws Exception {
        return valTrdAccountmfConfig001Service.getBooksetId(RequestUtil.getPageParam());
    }
    
    /**
     * check
     *
     * @param dto
     * @param bindingResult
     * @return ajaxResult
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValTrdAccountmfConfig001Dto dto,
                            BindingResult bindingResult)
        throws Exception {
        String operateType = dto.getOperateType();
        if (!bindingResult.hasErrors()) {// 基础验证通过
        	// 获取账套当前日期
        	Map<String,Object> params = new HashMap<>();
        	params.put("booksetId", dto.getBooksetId());
        	BokBooksetDate bokBooksetDate = valTrdAccountmfConfig001Service.getBooksetDate(params);
        	if("edit".equals(operateType)){
        		// 选择记录.计提结束日 < 账套日期表.当前日期
            	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionEndDate()),bokBooksetDate.getBooksetDate())<0){
                    return new AjaxResult("该配置已计提结束，如确需修改，请切换账套日期，并在修改后重新登账！", MessageType.错误消息);
            	}
        	}else{
        		// 选择记录.计提开始日 < 账套日期表.当前日期
            	if(DateUtil.compareDate(DateUtil.parse(dto.getPositionStartDate()),bokBooksetDate.getBooksetDate())<0){
                    return new AjaxResult("已入账，请先将账套日期切换到计提开始日之前，并在删除后重新登账！", MessageType.错误消息);
            	}
            	else{
                    return new AjaxResult("请确认是否要删除?", MessageType.确认消息);
            	}
        	}
        }
        return commonCheck(bindingResult, operateType);
    }
    
    /**
     * 启动费用预提设置信息删除流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) ValTrdAccountmfConfig001Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ValTrdAccountmfConfig001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValTrdAccountmfConfig001Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId("ValTrdAccountmfConfig002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqNo());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_预提费用配置.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "预提费用配置" , entity.getSeqNo()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            msg = valTrdAccountmfConfig001Service.start(processParameter, dto);
        } catch (Exception e) {
            this.getLogger().error("启动预提费用配置流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<ValTrdAccountmfConfig001Dto> listdto ;
        if (datas == null) {
            listdto = valTrdAccountmfConfig001Service.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValTrdAccountmfConfig001Dto.class);
        }
        File outFile = valTrdAccountmfConfig001Service.makeFile(
            "/com/joyintech/tams/valuation/valuationparametermanage/valtrdaccountmfconfig/config/export_valtrdaccountmfconfig.xml",
            listdto);
        //修改导出文件名称
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

}

