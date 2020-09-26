/*
 * 文件名：ValInertfLogController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.inertfLog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.interf.inertfLog.dto.ValInertfLog001Dto;
import com.joyintech.tams.valuation.interf.inertfLog.service.ValInertfLog001Service;
import com.mxgraph.util.svg.ParseException;

/**
 * 接口查询 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月13日
 * @see ValInertfLog002Controller
 * @since 1.0
 */
@Controller
@PageId("ValInertfLog002")
@RequestMapping("/valuation/interf/inertfLog/ValInertfLog002")
public class ValInertfLog002Controller extends BaseController {

    /**
     * ValInertfLog001Service
     */
    @Autowired
    private ValInertfLog001Service valInertfLog001Service;

    /**
     * 初始化表格 <功能详细描述>
     * 
     * @return List<Interflog>
     * @throws ParseException
     * @throws java.text.ParseException
     * @see
     * @since 1.0
     */
    @RequestMapping("initTable")
    @ResponseBody
    public Page<ValInertfLog001Dto> search()
        throws ParseException, java.text.ParseException , Exception{
        String tmpName = this.getParam("tmpName");
        String fileName = this.getParam("fileName");
        String exDate = this.getParam("exDate");
        Date startDate;
        Date endDate;
        if (exDate != null && !"".equals(exDate)) {
            startDate = DateUtil.parse(exDate);
            endDate = DateUtil.addDay(startDate, 1);
        }
        else {
            startDate = null;
            endDate = null;
        }
        String check = this.getParam("check");
        return valInertfLog001Service.search(tmpName, fileName, startDate, endDate, check);
    }
}
