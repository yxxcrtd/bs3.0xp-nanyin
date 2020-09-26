/*
 * 文件名：ValOptInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.service.ValOptInfo001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 场外期权标的
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOptInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValOptInfo001")
@RequestMapping("/valuation/basedata/marketdata/valoptinfo/ValOptInfo001")
public class ValOptInfo001Controller extends BaseController {
    
    /**
     * ValOptInfo001Service
     */
    @Autowired
    private ValOptInfo001Service valOptInfo001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.金融产品类型_投管用);
    	super.setSelectOptionToModel(model, DictCode.交易方向);
    	super.setSelectOptionToModel(model, DictCode.币种);
    	super.setSelectOptionToModel(model, DictCode.期权费支付类型);
    	super.setSelectOptionToModel(model, DictCode.估值交易市场);
    	super.setSelectOptionToModel(model, DictCode.结构类型);
        return super.init(model);
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValOptInfo001Dto> findPage() throws Exception{
        return valOptInfo001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 交易编号联想框
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/findFinprodId")
    @ResponseBody
    public List<SuggestDto> findFinprodId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valOptInfo001Service.findFinprodId(param);
    }
    
	/**
	 * 删除验证
	 * 
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ValOptInfo001Dto dto, BindingResult result) throws Exception {
        return commonCheck(result, dto.getOperateType());
	}
	
    /**
     * 启动删除流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody ValOptInfo001Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<ValOptInfo001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValOptInfo001Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId("ValOptInfo002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(item -> item.getFinprodId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_期权标的管理.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    entity.getFinprodId(), "场外期权标的"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
                
	        List<ValOptInfo001Dto> list = dto.getListValOptInfo001Dto();
	        List<ValOptInfo001Dto> showList = new ArrayList<>();
	        for(ValOptInfo001Dto item:list){
	    		Map<String, Object> params = Maps.newHashMap();
	    		params.put("finprodId", item.getFinprodId());
	        	item = valOptInfo001Service.findByTargetId(params).get(0);
	        	showList.add(item);
            }
	        valOptInfo001Service.start(processParameter, showList.toArray(new ValOptInfo001Dto[showList.size()]));
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动场外期权标的信息删除流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> para= RequestUtil.getPageParam();
        List<ValOptInfo001Dto> dtoList = new ArrayList<>();
        if (datas == null) {
            dtoList = valOptInfo001Service.findByTargetId(para);
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValOptInfo001Dto.class);
        }
        
        File outFile = valOptInfo001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valoptinfo/config/export_valoptinfo.xml",
                dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
}
