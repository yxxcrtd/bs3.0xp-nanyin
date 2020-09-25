/*
 * 文件名：repAssetDept001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repassetdeptregist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.report.debtreport.repassetdeptregist.dto.RepAssetDeptRegistDto;
import com.joyintech.tams.invest.report.debtreport.repassetdeptregist.service.RepAssetDeptRegist001Service;

/**
 * 资产要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月29日
 * @see RepAssetDeptRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("RepAssetDeptRegist001")
@RequestMapping("/invest/report/debtreport/repassetdeptregist/RepAssetDeptRegist001")
public class RepAssetDeptRegist001Controller extends BaseController{
    /**
     * 注入service
     */
    @Autowired
    private RepAssetDeptRegist001Service repAssetDept001Service;
    /**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.资产_负债类别, DictCode.资产_负债二级类别);
        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<RepAssetDeptRegistDto> searchPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repAssetDept001Service.searchPage(map);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return repAssetDept001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    /**
     * 获取资产代码集合
     * @return 资产代码集合
     * @see
     * @since 1.0
     */
    @RequestMapping("getRepAssetCode")
    @ResponseBody
    public List<SelectOptionDto> getRepAssetCode(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repAssetDept001Service.getRepAssetCode(map);
    }
}
