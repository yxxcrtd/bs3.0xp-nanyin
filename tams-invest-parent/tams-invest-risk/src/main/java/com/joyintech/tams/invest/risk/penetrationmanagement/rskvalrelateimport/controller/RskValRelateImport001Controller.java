/*
 * 文件名：RskValRelateImport001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImport001Dto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImport001Service;

/**
 * 估值表组合关联导入映射查询
 * @author fanshuanghu
 * @version 1.0 2018年12月18日
 * @see RskValRelateImport001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValRelateImport001")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport001")
public class RskValRelateImport001Controller extends BaseController{
    
    /**
     * RskValRelateImport001Service
     */
    @Autowired
    private RskValRelateImport001Service rskValRelateImport001Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 画面查询方法 <功能详细描述>
     * 
     * @return 所有数据
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValRelateImport001Dto> selectAll()
        throws Exception {
        return rskValRelateImport001Service.selecForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 
     * <一句话功能简述>
     * 组合代码联想下拉框
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryGroupIdInfo")
    public List<SelectOptionDto> queryGroupIdInfo() {
        return rskValRelateImport001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }
    
    /**
     * <一句话功能简述> 批量删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody RskValRelateImport001Dto dto) {
        rskValRelateImport001Service.delete(dto);
        return new AjaxResult(this.getMessage("RSK_W_0026"));
    }
    
    /**
     * <一句话功能简述> 导出
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return rskValRelateImport001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
