/*
 * 文件名：PtlAnalyzeAfterInvest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.3.1
 * 修改人：wangguiqi
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.3.1
 */
package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest001Dto;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.service.PtlAnalyzeAfterInvest001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活期结息查询 
 * @author wangguiqi
 * @version 1.0 2019年10月23日
 * @see PtlAnalyzeAfterInvest001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlAnalyzeAfterInvest001")
@RequestMapping("/invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest001")
public class PtlAnalyzeAfterInvest001Controller extends BaseController{
    @Autowired
    PtlAnalyzeAfterInvest001Service ptlAnalyzeAfterInvest001Service = new PtlAnalyzeAfterInvest001Service();
	
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.质押券状态);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlAnalyzeAfterInvest001Dto> findPage()
            throws Exception {
        return ptlAnalyzeAfterInvest001Service.selectForPage(RequestUtil.getParameters(getRequest()));
    }

    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlAnalyzeAfterInvest001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
