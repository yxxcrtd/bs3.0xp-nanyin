/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.service.OwnerEquityChange001Service;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 所有者权益变动Controller
 * 
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Controller
 * @since 1.0
 */
@RestController(value="serviceOwnerEquityChange001Controller")
@RequestMapping("OwnerEquityChange001")
public class OwnerEquityChange001Controller  extends BaseController {

    @Autowired
    private OwnerEquityChange001Service ownerEquityChange001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    @Autowired
    private ValProfitQuery001Service valProfitQuery001Service;
    

    /**
     * 
     * 分頁查詢
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectList")
    public List<OwnerEquityChange001Dto> selectList(@RequestBody Map<String, Object> pageParam)
        throws Exception {

        OwnerEquityChange001Dto dto = null;
        if(pageParam.get("OwnerEquityChange001Dto")!=null){
            dto = (OwnerEquityChange001Dto) pageParam.get("OwnerEquityChange001Dto");
        }

        return ownerEquityChange001Service.selectList(pageParam,dto);
         
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
    @RequestMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        return valAssetBook001Service.getBooksetId2(param);
    }
    
    /**
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getTermLink")
    public List<SelectOptionDto> getTradeMarket() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        String reportTermType = (String) param.get("reportTermType");

        param.put("dict_code_parent", DictCode.报表期限类型.getValue());
        param.put("item_code_parent", param.get("reportTermType"));
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.半年.getValue());
            return valProfitQuery001Service.getLinkSelectOption(param);
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.季度.getValue());
            return valProfitQuery001Service.getLinkSelectOption(param);
        }
        return null;
    }
    
    /**
     * 获取默认日期信息
     * 
     * @return OwnerEquityChange001Dto
     * @throws Exception
     */
    @PostMapping("getDateInfo")
    public OwnerEquityChange001Dto getDateInfo(@RequestBody Map<String, Object> pageParam) {
        return ownerEquityChange001Service.getDateInfo(pageParam);
    }
}
