/*
 * 文件名：RegistrationOfTheUnderlyingPositionOfThePlan001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：范双虎
 * 修改时间：2018年7月31日
 * 修改内容：
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.report.debtreport.repbottomasset.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.report.debtreport.repbottomasset.service.RepBottomAsset001Service;

/**
 * 资管计划底层持仓登记
 * @author fanshuanghu
 * @version 1.0 2018年7月31日
 * @see RegistrationOfTheUnderlyingPositionOfThePlan001
 * @since 1.0
 */
@Controller
@PageId("RepBottomAsset001")
@RequestMapping("/invest/report/debtreport/repbottomasset/RepBottomAsset001")
public class RepBottomAsset001Controller extends BaseController {
    /**
     * 注入Service
     */
    @Autowired
    private RepBottomAsset001Service repBottomAsset001Service;

    /**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepBottomAsset> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repBottomAsset001Service.findPage(map);
    }
	/**
	 * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
	 * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
	 * @see
	 * @since 1.0
	 */
    @ResponseBody
    @RequestMapping("getRepAssetCode")
    public List<SelectOptionDto> getRepAssetCode(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repBottomAsset001Service.getRepAssetCode(map);
    }
    /**
     * 获取底层资产行内资产/负债编码（底层资产代码）
     * @return 底层资产行内资产/负债编码（底层资产代码）集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepBottomCode2")
    public List<SelectOptionDto> getRepBottomCode2(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repBottomAsset001Service.getRepBottomCode2(map);
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
        return repBottomAsset001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
