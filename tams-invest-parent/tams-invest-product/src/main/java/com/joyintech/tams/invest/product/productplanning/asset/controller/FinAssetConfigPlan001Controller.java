/*
 * 文件名：FinAssetConfigPlan001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.asset.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetConfigPlan001Dto;
import com.joyintech.tams.invest.product.productplanning.asset.service.FinAssetConfigPlan001Service;
import com.joyintech.tams.invest.product.productplanning.asset.service.FinAssetConfigPlan002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2018年11月1日
 * @see FinAssetConfigPlan001Controller
 * @since 1.0
 */
@Controller
@PageId("FinAssetConfigPlan001")
@RequestMapping("/invest/product/productplanning/asset/FinAssetConfigPlan001")
public class FinAssetConfigPlan001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinAssetConfigPlan001Service finAssetConfigPlan001Service;
    @Autowired
    private FinAssetConfigPlan002Service finAssetConfigPlan002Service;
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 初始化页面
     *
     * @param Model model
     * @return String
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, new DictCode[] {DictCode.资产大类});
        
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
		SelectOptionDto option = new SelectOptionDto();
		option.setId("01");
		option.setText("有效");
		options.add(option);
        option = new SelectOptionDto();
        option.setId("02");
        option.setText("删除");
        options.add(option);       
		model.addAttribute("VALID_STA", JsonUtils.objectArray2Json(options.toArray()));

        return super.init(model);
    }
    
    /**
     * 发起查询
     *
     * @return Page<FinAssetConfigPlan001Dto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<FinAssetConfigPlan001Dto> selectForPage() {
        return finAssetConfigPlan001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
    	List<FinAssetConfigPlan001Dto> dtoList = finAssetConfigPlan001Service.selectForList(RequestUtil.getPageParam());
            File outFile= null;
        	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
            pageElementConfigDto.setPageId(this.getPageId());
            	outFile = finAssetConfigPlan001Service.makeFile(
                        "/com/joyintech/tams/invest/product/productplanning/asset/conf/export_conf.xml",
                        dtoList);
            return ExportUtil.export(this.getMessage(this.getPageId()) + GlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
    /**
     * 检查记录有效
     *
     * @param FinAssetConfigPlan001Dto dto
     * @param BindingResult result
     * @return AjaxResult结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinAssetConfigPlan001Dto dto, BindingResult result) {
    	return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除记录
     *
     * @param FinAssetConfigPlan001Dto dto
     * @param BindingResult br
     * @return AjaxResult结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody FinAssetConfigPlan001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto =  dto.getSelectedItem().get(0);
        ProcessParameter<FinAssetConfigPlan001Dto> processParameter = new ProcessParameter<FinAssetConfigPlan001Dto>();
        WorkFlowParameter<FinAssetConfigPlan001Dto> params = new WorkFlowParameter<FinAssetConfigPlan001Dto>();
        params.setPageId("FinAssetConfigPlan002");
        params.setElementId(dto.getElementId());
        String assetConfigCode = dto.getAssetConfigCode();
        //查询数据库，得到FinAssetConfigPlan001Dto
        dto = finAssetConfigPlan002Service.getAssetConfigPlan(assetConfigCode);
        params.setBusinessCode(assetConfigCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产配置计划.getValue());
        params.setComment(entity -> this.getMessage("FinAssetConfigPlan001.assetConfigCode") + "("
            + entity.getAssetConfigCode() +")");
        processParameter.setWorkFlowParameter(params);
        String msg = finAssetConfigPlan001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
