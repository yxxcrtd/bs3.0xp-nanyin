/*
 * 文件名：CustomerReportConfig001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.customerreport.config.controller.CustomerReportConfig001Controller;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.service.ValBooksetDateSwitch001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 报表定义一览画面
 * 
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig001Controller
 * @since 1.0
 */
@RestController(value="serviceValBooksetDateSwitch001Controller")
@RequestMapping("ValBooksetDateSwitch001")
public class ValBooksetDateSwitch001Controller extends BaseController {

    /**
     * 报表规则定义一览Service
     */
    @Autowired
    private ValBooksetDateSwitch001Service ValBooksetDateSwitch001Service;

    
//    
//    @Override
//    public String init(Model model) {
//        super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
//        return super.init(model);
//    }



    /**
     * 查询数据获得（同一个 Service 无法直接切换数据源调用）
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValBooksetDateSwitch001Dto> findPage(@RequestBody Map<String, Object> pageParam,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
//        Map<String, Object> params = RequestUtil.getPageParam();
        if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(pageParam.get("booksetDimension"))) {
          List<SysBatchGrpPrdlink> list = ValBooksetDateSwitch001Service.findAllProduct(pageParam);
          pageParam.put("booksetId", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
        } else {
            pageParam.put("booksetId", (String)pageParam.get("booksetGroup"));
        }
        return ValBooksetDateSwitch001Service.findPage(page,length,pageParam);
    }
    
    /**
     * 查询账套组
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @PostMapping("/findBooksetGroup/{page}/{length}")
    public Page<SelectOptionDto> findBooksetGroup(@RequestBody Map<String, Object> pageParam,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ValBooksetDateSwitch001Service.findBooksetGroup(page,length,pageParam);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetId/{page}/{length}")
    public Page<SelectOptionDto> getBooksetId2(@RequestBody Map<String, Object> pageParam,
                                               @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return ValBooksetDateSwitch001Service.getBooksetId(page,length,pageParam);
    }
}
