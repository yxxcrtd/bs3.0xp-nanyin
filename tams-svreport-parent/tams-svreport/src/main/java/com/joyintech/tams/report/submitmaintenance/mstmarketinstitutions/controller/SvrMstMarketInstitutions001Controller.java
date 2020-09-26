/*
 * 文件名：MstMarketInstitutions002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrMstMarketInstitutionsMService;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrPaRskCustomerRatingService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 市场机构的基本信息管理
 * 显示市场机构信息的基本页面
 * @author Wangzehui
 * @version 1.0 2018年7月23日
 * @see SvrMstMarketInstitutions001Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("MstMarketInstitutions001")
@RequestMapping("/submitmaintenance/mstmarketinstitutions/MstMarketInstitutions001")
public class SvrMstMarketInstitutions001Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 市场机构管理service注入
     */
    @Autowired
    private SvrMstMarketInstitutionsMService mstMarketInstitutionsMService;
    /**
     * 
     * {@inheritDoc}
     */
    @Autowired
    private SvrPaRskCustomerRatingService paRskCustomerRatingService;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
    
    /**
     * 
     * 市场机构页面模糊查询
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SvrMstMarketInstitutionsDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<SvrMstMarketInstitutionsDto> page = mstMarketInstitutionsMService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 
     * 客户代码联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCustomerId")
    public List<SuggestDto> getCustomerId()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        //List<SuggestDto> list = new ArrayList<SuggestDto>();
        String param = super.getRequest().getParameter("param");
        map.put("customerId", param);
        List<SuggestDto> temp = mstMarketInstitutionsMService.getCustomerId(map);
        return temp;
    }
    
    /**
     * 
     * 客户名称联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String param = super.getRequest().getParameter("param");
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        map.put("customerName", param);
        map.put("customerId", RequestUtil.getPageParam().get("customerId"));
        List<SuggestDto> temp = mstMarketInstitutionsMService.getCustomerName(map);
        return temp;
    }
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) SvrMstMarketInstitutionsDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto MstMarketInstitutionsDto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete() throws Exception{
    	String msg = this.getMessage("REPORT_COMMON_I_0006");
    	String keyStr = super.getParam("deleteKeys");
    	if (StringUtil.isNotEmpty(keyStr)) {
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, keyStr);
            keys.forEach(key -> {
                List<String> keyList = StringUtil.splitToList("|",key);
                SvrMstMarketInstitutionsDto dto = new SvrMstMarketInstitutionsDto();
                dto.setCustomerId(keyList.get(0));
                mstMarketInstitutionsMService.doComplete(dto,OperationType.DELETE);
            });
        }
    	return new AjaxResult(msg);
    }
    
    /**
     * 
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	if(param.containsKey("keyList")){
        	String keyList = param.get("keyList").toString();
			try {
				List<SvrMstMarketInstitutionsDto> investList = JsonUtils.json2List(keyList,SvrMstMarketInstitutionsDto.class);
				param.put("investList", investList);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			List<SvrMstMarketInstitutionsDto> dtoList = mstMarketInstitutionsMService.selectForExport(param);
			File outFile = mstMarketInstitutionsMService.makeFile(
					"/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf_i.xml", dtoList);
		         return ExportUtil.export(
		             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
		                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
		                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
		             outFile, true);
        }else{
        	return mstMarketInstitutionsMService.export(this.getPageId(),param);
        }
    }
}
