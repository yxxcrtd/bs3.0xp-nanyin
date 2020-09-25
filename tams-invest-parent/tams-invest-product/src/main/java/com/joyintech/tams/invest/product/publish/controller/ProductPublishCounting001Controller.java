/*
 * 文件名：FinProductDurDeal001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.publish.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.busicommon.productRating.service.CommonProductRatingService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.publish.dto.ProductPublishCounting001Dto;
import com.joyintech.tams.invest.product.publish.service.ProductPublishCounting001Service;
import com.joyintech.tams.invest.product.publish.service.ProductPublishCounting002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品发行规划试算
 * @version 1.0 2018年12月18日
 * @see ProductPublishCounting001Controller
 * @since 1.0
 */
@Controller
@PageId("ProductPublishCounting001")
@RequestMapping("/invest/product/publish/ProductPublishCounting001")
public class ProductPublishCounting001Controller extends BaseController {
	
	@Autowired
	private ProductPublishCounting001Service productPublishCounting001Service;
	
	@Autowired
	private ProductPublishCounting002Service productPublishCounting002Service;
	
	/**
     * 页面初始化
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }
    /**
     * 产品系列page
     * @see
     * @since 1.0
     */
    @RequestMapping("findPrdPage")
    @ResponseBody
    public Page<ProductPublishCounting001Dto> findPrdPage() throws Exception {
    	return productPublishCounting001Service.findPrdPage(RequestUtil.getPageParam());
    }
    /**
     * 查询资产大类page
     * @see
     * @since 1.0
     */
    @RequestMapping("findAssetPage")
    @ResponseBody
    public Page<ProductPublishCounting001Dto> findAssetPage() throws Exception {
    	
    	return productPublishCounting001Service.findAssetPage(RequestUtil.getPageParam());
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ProductPublishCounting001Dto dto
    		, BindingResult result) {
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
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ProductPublishCounting001Dto dto
    		, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ProductPublishCounting001Dto> processParameter = new ProcessParameter<ProductPublishCounting001Dto>();
        WorkFlowParameter<ProductPublishCounting001Dto> params = new WorkFlowParameter<ProductPublishCounting001Dto>();
        params.setPageId("ProductPublishCounting002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getPrdIssueplanCode());
        params.setBusinessType(DictCodeListExt3.共通业务类型_发行规划试算.getValue());
        // 设置流程业务说明, entity即传入的dto
        //BSTOT-202 guyunfei 20200108 start
        params.setComment(
            entity -> "规划名称"+ "(" + entity.getPrdIssueplanName()+")" +  "-" 
            		+ "起始日"+ "(" + DateUtil.format(entity.getVdate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")"+ "-"
                    + "结束日"+ "(" + DateUtil.format(entity.getEdate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")");
        //BSTOT-202 guyunfei 20200108 end
        processParameter.setWorkFlowParameter(params);
        String msg = productPublishCounting002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    @ResponseBody
    @RequestMapping("/getPrdIssueplanCode")
    public Page<SelectOptionDto> getPrdIssueplanCode() throws Exception{
    	return productPublishCounting001Service.getPrdIssueplanCode(RequestUtil.getPageParam());
    }
    /**
     * 导出信息excel
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	return productPublishCounting001Service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
