/*
 * 文件名：FinNCProductBuild001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.finncproductbuild.controller;

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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoAcctDto;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.finncproductbuild.service.FinNCProductBuild001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 产品信息管理
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild001Controller
 * @since 1.0
 */
@Controller
@PageId("FinNCProductBuild001")
@RequestMapping("/valuation/finncproductbuild/FinNCProductBuild001")
public class FinNCProductBuild001Controller extends BaseController {
    
    /**
     * FinNCProductBuild001Service
     */
    @Autowired
    private FinNCProductBuild001Service finNCProductBuild001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        //this.setSelectOptionToModel(model, DictCode.收益类型,DictCode.是否);
        String[] busiType={"04","02"};
        //参数过滤
        DictCode.checkDictCode(model,DictCode.收益类型,busiType,false);
        this.setSelectOptionToModel(model, DictCode.募集方式,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.开放类型,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.发行方式,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.投资性质,DictCode.是否);
        model.addAttribute("raiseType", DictCodeListExt1.募集方式_公募.getValue());
        model.addAttribute("issueType", DictCodeList.开放类型_封闭式.getValue());
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
    public Page<FinNCProductBuild001Dto> findPage() throws Exception{
        return finNCProductBuild001Service.findPage(RequestUtil.getPageParam());
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
        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finNCProductBuild001Service.getFinprodId(map);
    }
    
    /**
     * 查找产品的资金账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodAcct")
    @ResponseBody
    public List<ValProductInfoAcctDto> getFinprodAcct() {
    	Map<String, Object> map = RequestUtil.getPageParam();
//      map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
    	String workflowId = (String) map.get("workflowId");
    	List<ValProductInfoAcctDto> resultList = new ArrayList<>();
    	if(workflowId != null && workflowId != ""){
    		FinNCProductBuild001Dto dto = finNCProductBuild001Service.getBussinessEntityByWorkflowId(workflowId);
    		resultList = dto.getListValProductInfoAcctDto_ZJ();
    	}else{
    		resultList = finNCProductBuild001Service.getFinprodAcct(map);
    	}
        return resultList;
    }
    
    /**
     * 查找资金账户名称列表
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getAcctName")
    @ResponseBody
    public List<SelectOptionDto> getAcctName() {
        Map<String,Object> map=RequestUtil.getPageParam();
        return finNCProductBuild001Service.getAcctName(map);
    }
    
    /**
     * 查找产品的证券账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getSecAccount")
    @ResponseBody
    public List<ValProductInfoAcctDto> getSecAccount() {
        Map<String,Object> map=RequestUtil.getPageParam();
        return finNCProductBuild001Service.getSecAccount(map);
    }
    /**
     * 
     * 费用查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryFeeList() throws Exception{
    	Map<String, Object> map = RequestUtil.getPageParam();
    	String workflowId = (String) map.get("workflowId");
    	List<ValProductInfoFeeDto> resultList = new ArrayList<>();
    	if(workflowId != null && workflowId != ""){
    		FinNCProductBuild001Dto dto = finNCProductBuild001Service.getBussinessEntityByWorkflowId(workflowId);
    		resultList = dto.getListValProductInfoFeeDto_GD();
    	}else{
    		resultList = finNCProductBuild001Service.queryFeeList(map);
    	}
        return resultList;
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
        List<FinNCProductBuild001Dto> dtoList;
        if (datas == null) {
            para.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
            dtoList = finNCProductBuild001Service.findAll(para);
        }
        else {
            dtoList = JsonUtils.json2List(datas, FinNCProductBuild001Dto.class);
        }
        
        File outFile = finNCProductBuild001Service.makeFile(
                "/com/joyintech/tams/valuation/finncproductbuild/config/export_finncproductbuild.xml",
                dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList() throws Exception{
        return finNCProductBuild001Service.queryfloatFeeList(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList1")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList1() throws Exception{
    	Map<String, Object> map = RequestUtil.getPageParam();
    	String workflowId = (String) map.get("workflowId");
    	List<ValProductInfoFeeDto> resultList = new ArrayList<>();
    	if(workflowId != null && workflowId != ""){
    		FinNCProductBuild001Dto dto = finNCProductBuild001Service.getBussinessEntityByWorkflowId(workflowId);
    		resultList = dto.getListValProductInfoFeeDto_FD1();
    	}else{
    		resultList = finNCProductBuild001Service.queryfloatFeeList1(map);
    	}
        return resultList;
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList2")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList2() throws Exception{
    	Map<String, Object> map = RequestUtil.getPageParam();
    	String workflowId = (String) map.get("workflowId");
    	List<ValProductInfoFeeDto> resultList = new ArrayList<>();
    	if(workflowId != null && workflowId != ""){
    		FinNCProductBuild001Dto dto = finNCProductBuild001Service.getBussinessEntityByWorkflowId(workflowId);
    		resultList = dto.getListValProductInfoFeeDto_FD2();
    	}else{
    		resultList = finNCProductBuild001Service.queryfloatFeeList2(map);
    	}
        return resultList;
    }
    
	/**
	 * 
	 * 删除验证
	 * 
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) FinNCProductBuild001Dto dto, BindingResult result) throws Exception {
		String operateType = dto.getOperateType();
        List<FinNCProductBuild001Dto> list = dto.getListFinNCProductBuildDto();
        List<FinNCProductBuild001Dto> checkList = new ArrayList<>();
        for(FinNCProductBuild001Dto item:list){
        	checkList = finNCProductBuild001Service.deleteCheck(item.getFinprodId());
        	if(!checkList.isEmpty()){
        		String msg = "部分产品存在估值数据，是否确认删除？";
				return new AjaxResult(msg, MessageType.提示消息);
        	}
        }
        return commonCheck(result, operateType);
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
    public AjaxResult deleteTypeAssets(@RequestBody FinNCProductBuild001Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<FinNCProductBuild001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinNCProductBuild001Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId("FinNCProductBuild002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(item -> item.getFinprodId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_产品信息管理.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    entity.getFinprodId(), "产品信息管理"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
                
	        List<FinNCProductBuild001Dto> list = dto.getListFinNCProductBuildDto();
	        List<FinNCProductBuild001Dto> showList = new ArrayList<>();
	        for(FinNCProductBuild001Dto item:list){
	    		Map<String, Object> params = Maps.newHashMap();
	    		params.put("finprodId", item.getFinprodId());
	    		params.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
	    		params.put("noCashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
	        	item = finNCProductBuild001Service.findByFinprodId(params);
	        	List<ValProductInfoAcctDto> resultListZJ = finNCProductBuild001Service.getFinprodAcct(params);
	        	List<ValProductInfoFeeDto>resultListFee = finNCProductBuild001Service.queryFeeList(params);
	        	List<ValProductInfoFeeDto> resultListFee1 = finNCProductBuild001Service.queryfloatFeeList1(params);
	        	List<ValProductInfoFeeDto> resultListFee2 = finNCProductBuild001Service.queryfloatFeeList2(params);
	        	List<ValProductInfoFeeDto> resultListFee0 = finNCProductBuild001Service.queryfloatFeeList(params);
	        	if(!resultListFee0.isEmpty()){
	        		ValProductInfoFeeDto FeeDto = resultListFee0.get(0);
		        	item.setActRule(FeeDto.getActRule());
		        	item.setBaseType(FeeDto.getBaseType());
		        	item.setActVdateType(FeeDto.getActVdateType());
		        	item.setFirCalculaDate(FeeDto.getFirCalculaDate());
		        	item.setEndCalculaDate(FeeDto.getEndCalculaDate());
		        	item.setAccrCycle(FeeDto.getAccrCycle());
	        	}
	        	item.setListValProductInfoAcctDto_ZJ(resultListZJ);
	        	item.setListValProductInfoFeeDto_GD(resultListFee);
	        	item.setListValProductInfoFeeDto_FD1(resultListFee1);
	        	item.setListValProductInfoFeeDto_FD2(resultListFee2);
	        	showList.add(item);
            }
            finNCProductBuild001Service.start(processParameter, showList.toArray(new FinNCProductBuild001Dto[showList.size()]));
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动产品信息删除流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }
    
}
