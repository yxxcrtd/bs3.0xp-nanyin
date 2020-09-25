/*
 * 文件名：ValSubjectInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.service.ValSubjectInfo001Service;

/**
 * 科目信息表controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValSubjectInfo001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/ValSubjectInfo001")
public class ValSubjectInfo001Controller {

    @Autowired
    private ValSubjectInfo001Service valSubjectInfo001Service;

    /**
     * 数据表格查询方法
     * 
     * @return Page<ValSubjectInfo001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValSubjectInfo001Dto> findPage(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page,
                                               @PathVariable("length") int length)
        throws Exception {
        return valSubjectInfo001Service.findPage(page, length, param);
    }

    /**
     * 页面产品下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length) {
        return valSubjectInfo001Service.getFinprodId(page, length, param);
    }

}
