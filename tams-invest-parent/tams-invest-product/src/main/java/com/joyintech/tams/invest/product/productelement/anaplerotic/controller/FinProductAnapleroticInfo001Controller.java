/*
 * 文件名：ValBokClawbackInfoQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：李俊
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.anaplerotic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.service.FinProductAnapleroticInfo001Service;
import com.joyintech.tams.invest.product.productelement.anaplerotic.service.FinProductAnapleroticInfo002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 回补信息查询
 *
 * @author 李俊
 * @version 1.0 2019年4月22日
 * @see FinProductAnapleroticInfo001Controller
 * @since 1.0
 */
@PageId("FinProductAnapleroticInfo001")
@Controller
@RequestMapping("/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo001")
public class FinProductAnapleroticInfo001Controller extends BaseController {
    @Autowired
    private FinProductAnapleroticInfo001Service finProductAnapleroticInfo001Service;
    @Autowired
    private FinProductAnapleroticInfo002Service finProductAnapleroticInfo002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductAnapleroticInfoDto> findPage() {
        return finProductAnapleroticInfo001Service.findPage(RequestUtil.getPageParam());
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
        return finProductAnapleroticInfo001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }
    /**
     * 删除业绩回补数据数据
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteByPrimaryKey")
    public AjaxResult deleteByPrimaryKey(@RequestBody FinProductAnapleroticInfoDto dtop) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("anapleroticId", dtop.getAnapleroticId());
        map.put("effDate",dtop.getEffDateStr());
        List<FinProductAnapleroticInfoDto> list = finProductAnapleroticInfo001Service.queryAnapleroticInfoById(map);
        if(!StringUtil.isEmptyList(list)){
            FinProductAnapleroticInfoDto dto = list.get(0).getFinProductAnapleroticInfoDto();
            dto.setOperateType(GlobalConstant.OPERATE_TYPE_DELETE);
            ProcessParameter<FinProductAnapleroticInfoDto> processParameter = new ProcessParameter<FinProductAnapleroticInfoDto>();
            WorkFlowParameter<FinProductAnapleroticInfoDto> params = new WorkFlowParameter<FinProductAnapleroticInfoDto>();
            params.setElementId(dtop.getElementId());
            params.setPageId("FinProductAnapleroticInfo002");
            params.setBusinessCode(dto.getFinprodId());
            params.setBusinessType(DictCodeListExt1.共通业务类型_回补信息调整.getValue());
            // 设置工作流注解
            params.setComment(
                    entity -> this.getMessage("FinProductAnapleroticInfo.finprodId") + "("
                            + entity.getFinprodId() + ")"
                            + this.getMessage("FinProductAnapleroticInfo.finprodAbbr") + "("
                            + entity.getFinprodAbbr() + ")" + this.getMessage("FinProductAnapleroticInfo.effDate")
                            + "(" + DateUtil.format(entity.getEffDate()) + ")"
            );
            processParameter.setWorkFlowParameter(params);

            // 调用工作流事件
            String msg = finProductAnapleroticInfo002Service.start(processParameter, dto);
            // 返回工作流错误信息
            return new AjaxResult(msg);
        }
        return new AjaxResult();
    }
    /**
     * 数据验证
     * <功能详细描述>
     * @param Dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid FinProductAnapleroticInfoDto Dto , BindingResult result) {
        if (result != null && result.hasErrors()) {
            if(StringUtil.isNotEmpty(Dto.getOperateType())){
                return commonCheck(result, Dto.getOperateType());
            }
            return new AjaxResult(result);
        }
    	/*   if (result.hasErrors()) {
               AjaxResult ar = new AjaxResult(result);
               return ar;
           }*/
        return new AjaxResult();
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
    public ResponseEntity<byte[]> export() throws Exception {
        return finProductAnapleroticInfo001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
}