/*
 * 文件名：CustomerReportConfig001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.customerreport.config.controller.CustomerReportConfig001Controller;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.service.ValBooksetDateSwitch001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;

/**
 * 报表定义一览画面
 * 
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("ValBooksetDateSwitch001")
@RequestMapping("/valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch001")
public class ValBooksetDateSwitch001Controller extends BaseController {

    /**
     * 报表规则定义一览Service
     */
    @Autowired
    private ValBooksetDateSwitch001Service valBooksetDateSwitch001Service;

    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;
    
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
        // 是否显示查看数据字典明细按钮，多级法人改造 add by hxy 20200623
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));

        return super.init(model);
    }



    /**
     * 查询数据获得（同一个 Service 无法直接切换数据源调用）
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValBooksetDateSwitch001Dto> findPage() throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
        return valBooksetDateSwitch001Service.findPage(params);
        
//        if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(params.get("booksetDimension"))) {
//          List<SysBatchGrpPrdlink> list = ValBooksetDateSwitch001Service.findAllProduct(params);
//          params.put("booksetId", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
//        }
//        return ValBooksetDateSwitch001Service.findPage(params);
    }
    
    /**
     * 查询账套组
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @RequestMapping("findBooksetGroup")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetGroup() throws Exception {
        return valBooksetDateSwitch001Service.findBooksetGroup(RequestUtil.getPageParam());
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId2()
        throws Exception {
        return valBooksetDateSwitch001Service.getBooksetId(RequestUtil.getPageParam());
    }
    
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
        HashMap<String, Object> map = (HashMap<String, Object>) RequestUtil.getPageParam();
        String booksetType = String.valueOf(map.get("booksets"));
        String search = null;
        if (map.containsKey("search")) {
            search = String.valueOf(map.get("search"));
        } else {
            map.put("search", search);
        }
    	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(booksetType)) {
    		return valAccountHandle001Service.getBooksetIdByGrp(RequestUtil.getPageParam());
    	} else {
    		return valAccountHandle001Service.getBookSetId2(RequestUtil.getPageParam());
    	}
    }
}
