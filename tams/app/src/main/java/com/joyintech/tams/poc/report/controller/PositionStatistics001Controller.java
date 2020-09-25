/*
 * 文件名：SecAssetStatistics001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.report.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.poc.report.dto.PositionStatistics001Dto;
import com.joyintech.tams.poc.report.service.PositionStatistics001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * 头寸统计表Controller
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatistics001Controller
 * @since 1.0
 */
@Controller
@PageId("PositionStatistics001")
@RequestMapping("/poc/report/PositionStatistics001")
public class PositionStatistics001Controller extends BaseController{
    @Autowired
    private PositionStatistics001Service positionStatistics001Service;
    
    @Override
    public String init(Model model) { 
        //平台日
        model.addAttribute("platformDate",DateUtil.format(DateUtil.platDateFromCache()));
        //平台日期前一日
        model.addAttribute("currentDate",DateUtil.addDay(DateUtil.format(DateUtil.platDateFromCache()),-1));
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);

        model.addAttribute("isEleShow", isEleShow);
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
    public Page<PositionStatistics001Dto> findPage() throws Exception{
        return positionStatistics001Service.findPage(RequestUtil.getPageParam());
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
        return positionStatistics001Service.export("头寸统计表.xlsx",
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 理财经理联想下拉框
     * 
     * @return 理财经理列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryManagerInfo")
    public List<SelectOptionDto> queryManagerInfo() {
        return positionStatistics001Service.queryManagerInfo(RequestUtil.getPageParam());
    } 
}
