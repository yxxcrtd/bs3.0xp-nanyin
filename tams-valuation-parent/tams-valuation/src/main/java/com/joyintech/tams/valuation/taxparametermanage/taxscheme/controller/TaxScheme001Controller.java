/*
 * 文件名：TaxScheme001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.*;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.*;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 税务方案 - 主界面
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme001Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme001")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme001")
public class TaxScheme001Controller extends BaseController {

	@Autowired
	private TaxScheme001Service taxScheme001Service;

	/** 税务方案 **/
	@Autowired
	private TaxScheme002Service taxScheme002Service;

	/** 增值税则表 **/
	@Autowired
	private TaxScheme003Service taxScheme003Service;

	/** 附加税则表 **/
	@Autowired
	private TaxScheme004Service taxScheme004Service;
	
	/** 资产应税信息 **/
    @Autowired
    private TaxScheme005Service taxScheme005Service;
    
    /** 参数设置 **/
    @Autowired
    private TaxScheme006Service taxScheme006Service;
    
    /** 会计场景计税配置 **/
    @Autowired
    private TaxScheme007Service taxScheme007Service;

	/**
	 * 账套税务配置 - 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {

		super.setSelectOptionToModel(model, DictCode.增值税资产分类);
		super.setSelectOptionToModel(model, DictCode.本金收益特征);
		super.setSelectOptionToModel(model, DictCode.增值税类型2); // 收入类型
		super.setSelectOptionToModel(model, DictCode.免税收入类型);

		super.setSelectOptionToModel(model, DictCode.附加税类型);
		
		super.setSelectOptionToModel(model, DictCode.计税方式);
		super.setSelectOptionToModel(model, DictCode.维护方式);
		
		super.setSelectOptionToModel(model, DictCode.税务参数类型); // 参数设置 - 税务参数类型（二级类型）

        super.setSelectOptionToModel(model, DictCode.会计场景代码);
        super.setSelectOptionToModel(model, DictCode.增值税类型);

		return super.init(model);
	}

	/**
	 * 获取税务方案 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findScheme")
	public Page<TaxScheme001Dto> findScheme() throws Exception {
		return taxScheme001Service.findSchemeByPage(RequestUtil.getPageParam());
	}

	/**
	 * 获取增值税则表 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findVatTax")
	public Page<TaxScheme001Dto> findVatTax() throws Exception {
		return taxScheme001Service.findVatTaxByPage(RequestUtil.getPageParam());
	}

	/**
	 * 获取附加税则表 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findAdtTax")
	public Page<TaxScheme001Dto> findAdtTax() throws Exception {
		return taxScheme001Service.findAdtTaxByPage(RequestUtil.getPageParam());
	}

	/**
     * 获取资产应税表 - 分页
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findTaxable")
    public Page<TaxScheme001Dto> findTaxable() throws Exception {
        return taxScheme001Service.findTaxableByPage(RequestUtil.getPageParam());
    }
	
    /**
     * 获取参数设置 - 分页
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findConfig")
    public Page<TaxScheme001Dto> findConfig() throws Exception {
        return taxScheme001Service.findConfigByPage(RequestUtil.getPageParam());
    }

    /**
     * 获取会计场景计税配置 - 分页
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findTaxSchaBokConfig")
    public Page<TaxScheme001Dto> findTaxSchaBokConfig() throws Exception {
        return taxScheme001Service.findTaxSchaBokConfigByPage(RequestUtil.getPageParam());
    }
    
    /**
     * 下拉框 ： 资产代码
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findAsset")
    public List<SelectOptionDto> findAsset() throws Exception {
        return taxScheme001Service.findAsset(RequestUtil.getPageParam());
    }
    
    /**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findProject")
    public List<SelectOptionDto> findProject() throws Exception {
        return taxScheme006Service.findProject(RequestUtil.getPageParam());
    }
    
	/**
	 * 
	 * 删除通用 check
	 * 
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody TaxScheme001Dto dto, BindingResult result) throws Exception {
	    String msg = StringUtils.EMPTY;
        if("deleteScheme".equals(dto.getOperateType())) { // 删除税务方案验证：若已有账套使用了该税务方案或者默认方案，则该方案不允许删除
            TaxScheme002Dto entity = taxScheme002Service.findBySchId(dto.getSchId());
            if (DictCodeList.是否_是.getValue().equals(entity.getIsDefault())) {
                msg = "默认方案无法删除！";
                return new AjaxResult(msg, MessageType.错误消息);
            }
            
            int count = taxScheme002Service.countBokBookSetPlan(dto.getSchId());
            if (count > 0 || DictCodeList.是否_是.getValue().equals(entity.getIsDefault())) {
                msg = "该税务方案存在关联账套，无法删除！";
                return new AjaxResult(msg, MessageType.错误消息);
            }
        }
        //默认税务方案里面的内容（增值税则表，附加税则表等），不可删除，根据是否默认字段，‘Y’默认不可删，‘N’可以删除
        if(StringUtil.isNotEmpty(dto.getIsDefault())&&dto.getIsDefault().equals(DictCodeList.是否_是.getValue())){
             msg = "默认税务方案里面的内容，均不可删除";
             return new AjaxResult(msg, MessageType.错误消息);
        }
        return new AjaxResult();
	}



	/**
     * 删除方案 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteScheme", method = RequestMethod.POST)
    public AjaxResult deleteScheme(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme002Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<TaxScheme002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme002Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            dto = taxScheme002Service.findBySchId(dto.getSchId());// dto 为回显值
            
            workFlowParameter.setPageId("TaxScheme002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "税务方案" , entity.getSchId() , entity.getSchName()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

    /**
     * 删除增值税则表 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteVatTax", method = RequestMethod.POST)
    public AjaxResult deleteVatTax(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme003Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<TaxScheme003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme003Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            dto = taxScheme003Service.findByPrimaryKey(dto.getSeqNo());// dto 为回显值
            
            workFlowParameter.setPageId("TaxScheme003"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "增值税则表" , entity.getSeqNo()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme003Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
	
    /**
     * 删除附加税则表 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteAdtTax", method = RequestMethod.POST)
    public AjaxResult deleteAdtTax(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme004Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<TaxScheme004Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme004Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            dto = taxScheme004Service.findByPrimaryKey(dto.getTaxId());// dto 为回显值
            
            workFlowParameter.setPageId("TaxScheme004"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "附加税则表" , entity.getTaxId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme004Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 删除资产应税信息 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteTaxable", method = RequestMethod.POST)
    public AjaxResult deleteTaxable(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme005Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<TaxScheme005Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme005Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            dto = taxScheme005Service.findByPrimaryKey(dto.getSeqNo());// dto 为回显值
            
            workFlowParameter.setPageId("TaxScheme005"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "资产应税信息" , entity.getSeqNo()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme005Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 删除税务参数 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteConfig", method = RequestMethod.POST)
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme006Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<TaxScheme006Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme006Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            dto = taxScheme006Service.findByPrimaryKey(dto.getSeqId());// dto 为回显值
            
            workFlowParameter.setPageId("TaxScheme006"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "税务参数" , entity.getSeqId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme006Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 删除会计场景计税配置 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteTaxSchaBokConfig", method = RequestMethod.POST)
    public AjaxResult deleteTaxSchaBokConfig(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme007Dto dto, BindingResult br) {
        String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }

        ProcessParameter<TaxScheme007Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme007Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = taxScheme007Service.findByPrimaryKey(dto.getSeqNo());// dto 为回显值

            workFlowParameter.setPageId("TaxScheme007"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqNo());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                "会计场景计税配置" , entity.getSeqNo()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            taxScheme007Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
	/**
	 * 
	 * 导出：增值税则表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportVatTax")
	public ResponseEntity<byte[]> exportVatTax() throws Exception {

		List<TaxScheme001Dto> dtoList = taxScheme001Service.findVatTax(RequestUtil.getPageParam());
		File outFile = taxScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportvattax.xml",
				dtoList);
		return ExportUtil.export("增值税则表" + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}
	
	/**
	 * 
	 * 导出：附加税则表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportAdtTax")
	public ResponseEntity<byte[]> exportAdtTax() throws Exception {

		List<TaxScheme001Dto> dtoList = taxScheme001Service.findAdtTax(RequestUtil.getPageParam());
		File outFile = taxScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportadttax.xml",
				dtoList);
		return ExportUtil.export("附加税则表" + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	/**
     * 
     * 导出：资产应税信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("exportTaxable")
    public ResponseEntity<byte[]> exportTaxable() throws Exception {

        List<TaxScheme001Dto> dtoList = taxScheme001Service.findTaxable(RequestUtil.getPageParam());
        File outFile = taxScheme001Service.makeFile(
                "/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exporttaxable.xml",
                dtoList);
        return ExportUtil.export("资产应税信息" + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    
    /**
     * 
     * 导出：参数设置
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("exportConfig")
    public ResponseEntity<byte[]> exportConfig() throws Exception {

        List<TaxScheme001Dto> dtoList = taxScheme001Service.findConfig(RequestUtil.getPageParam());
        File outFile = taxScheme001Service.makeFile(
                "/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportconfig.xml",
                dtoList);
        return ExportUtil.export("参数设置" + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    /**
     *
     * 导出：会计场景计税配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("exportTaxSchaBokConfig")
    public ResponseEntity<byte[]> exportTaxSchaBokConfig() throws Exception {

        List<TaxScheme001Dto> dtoList = taxScheme001Service.findTaxSchaBokConfig(RequestUtil.getPageParam());
        File outFile = taxScheme001Service.makeFile(
            "/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exporttaxschabokconfig.xml",
            dtoList);
        return ExportUtil.export("会计场景计税配置" + SystemGlobalConstant.OPEN_PARENTHESIS
            + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
            + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
	
	 /**
     * 设置默认方案
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "setDefaultScheme", method = RequestMethod.POST)
    public AjaxResult setDefaultScheme(@RequestBody @Validated({ValidateGroupDelete.class}) TaxScheme002Dto dto, BindingResult br) {
        String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }

        ProcessParameter<TaxScheme002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = taxScheme002Service.findBySchId(dto.getSchId());// dto 为回显值
            dto.setIsDefault(DictCodeList.是否_是.getValue()); // 设置为默认

            workFlowParameter.setPageId("TaxScheme002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "税务方案" , entity.getSchId() , entity.getSchName()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            taxScheme002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
}
