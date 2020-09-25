/*
 * 文件名：AgencyAndPrivateBankProductReleaseRegistration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：范双虎
 * 修改时间：2018年7月30日
 * 修改内容：
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.controller;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.dto.RepFinProductOrgIssRegistDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.service.RepFinProductOrgIssRegistService;

/**
 * 机构和私人银行产品发行登记
 * @author wangzehui
 * @version 1.0 2018年7月30日
 * @see AgencyAndPrivateBankProductReleaseRegistration001
 * @since 1.0
 */

@Controller
@PageId("RepFinProductOrgIssRegist001")
@RequestMapping("/invest/report/debtreport/repfinproductorgissregist/RepFinProductOrgIssRegist001")
public class RepFinProductOrgIssRegist001Controller extends BaseController{
    @Autowired
    private RepFinProductOrgIssRegistService repFinProductOrgIssRegistService;
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model){
	    this.setSelectOptionToModel(model, DictCode.目标客户类型);
		return super.init(model);
	}
	/**
     * 产品名称的联想框
     * @return List List
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("/getRepFinprodName")
    public List<SelectOptionDto> getRepFinprodName(){
        return repFinProductOrgIssRegistService.getRepFinprodName(RequestUtil.getPageParam());
    }
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductOrgIssRegistDto> selectAll()
        throws Exception {
        return repFinProductOrgIssRegistService.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
        return repFinProductOrgIssRegistService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
