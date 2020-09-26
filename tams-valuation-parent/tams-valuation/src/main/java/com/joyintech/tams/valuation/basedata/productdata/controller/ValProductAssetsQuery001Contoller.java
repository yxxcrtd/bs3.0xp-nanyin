/*
 * 文件名：ValProductAssetsQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productdata.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.productdata.dto.ValProductAssetsQuery001Dto;
import com.joyintech.tams.valuation.basedata.productdata.service.ValProductAssetsQuery001Service;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValProductAssetsQuery001Contoller
 * @since 1.0
 */
@Controller
@PageId("ValProductAssetsQuery001")
@RequestMapping("/valuation/basedata/productdata/ValProductAssetsQuery001")
public class ValProductAssetsQuery001Contoller extends BaseController {
    
    /**
     * ValProductAssetsQuery001Service
     */
    @Autowired
    private ValProductAssetsQuery001Service valProductAssetsQuery001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.境内外);
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
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
    public Page<ValProductAssetsQuery001Dto> findPage() throws Exception{
        return valProductAssetsQuery001Service.findPage(RequestUtil.getPageParam());
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
        List<ValProductAssetsQuery001Dto> valList = new ArrayList<ValProductAssetsQuery001Dto>();
        String datas = this.getParam("datas");
        if(datas!=null){
            valList = JsonUtils.json2List(datas,ValProductAssetsQuery001Dto.class);
        }
        else {
            valList = valProductAssetsQuery001Service.queryPageList(RequestUtil.getPageParam());
        }
        File outFile  = valProductAssetsQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/productdata/conf/export_productdata.xml",
            valList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
