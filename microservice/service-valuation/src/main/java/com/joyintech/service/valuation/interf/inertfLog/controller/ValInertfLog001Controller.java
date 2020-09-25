/*
 * 文件名：ValInertfLogController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.interf.inertfLog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.interf.inertfLog.dto.ValInertfLog001Dto;
import com.joyintech.service.valuation.interf.inertfLog.service.ValInertfLog001Service;

/**
 * 接口查询 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月13日
 * @see ValInertfLog001Controller
 * @since 1.0
 */
@RestController(value="serviceValInertfLog001Controller")
@RequestMapping("/ValInertfLog001")
public class ValInertfLog001Controller extends BaseController {

    /**
     * ValInertfLog001Service
     */
    @Autowired
    private ValInertfLog001Service valInertfLog001Service;

//    /**
//     * 初始化表格 <功能详细描述>
//     * 
//     * @return List<Interflog>
//     * @throws ParseException
//     * @throws java.text.ParseException
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping("initTable")
//    @ResponseBody
//    public Page<ValInertfLog001Dto> search()
//        throws ParseException, java.text.ParseException , Exception{
//        String tmpName = this.getParam("tmpName");
//        String fileName = this.getParam("fileName");
//        String exDate = this.getParam("exDate");
//        Date startDate;
//        Date endDate;
//        if (exDate != null && !"".equals(exDate)) {
//            startDate = DateUtil.parse(exDate);
//            endDate = DateUtil.addDay(startDate, 1);
//        }
//        else {
//            startDate = null;
//            endDate = null;
//        }
//        String check = this.getParam("check");
//        return valInertfLog001Service.search(tmpName, fileName, startDate, endDate, check);
//    }
    
    @PostMapping("/initTable/{page}/{length}")
    public Page<ValInertfLog001Dto> findScheme(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        
        return valInertfLog001Service.findSchemeByPage(page, length, param);
    }
}
