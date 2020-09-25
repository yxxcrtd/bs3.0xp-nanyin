/*
 * 文件名：RepFinProductOrgIssRegistController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductorgissregist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductorgissregist.dto.RepFinProductOrgIssRegistDto;
import com.joyintech.service.report.debtreport.repfinproductorgissregist.service.RepFinProductOrgIssRegistService;

/**
 * 机构和私人银行产品发行登记
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月21日
 * @see RepFinProductOrgIssRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductOrgIssRegist")
public class RepFinProductOrgIssRegistController {
    @Autowired
    private RepFinProductOrgIssRegistService repFinProductOrgIssRegistService;
    /**
     * 产品名称的联想框
     * @param param
     * @return
     */
    @PostMapping("/getRepFinprodName")
    public List<SelectOptionDto> getRepFinprodName(@RequestBody Map<String, Object> param) {
        return repFinProductOrgIssRegistService.getRepFinprodName(param);
    }
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<RepFinProductOrgIssRegistDto> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductOrgIssRegistService.selectForPage(page, length, param);
    }
}
