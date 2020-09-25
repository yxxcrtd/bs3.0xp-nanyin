/*
 * 文件名：FinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductopenplanadjust.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.product.messageadjust.finproductopenplanadjust.dao.FinProductOpenPlanAdjustMapper;
import com.joyintech.service.product.messageadjust.finproductopenplanadjust.dto.FinProductOpenPlanAdjustDto;
import com.joyintech.service.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 信息调整管理_开放计划查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjustService
 * @since 1.0
 */
@Service("backFinProductOpenPlanAdjustService")
public class FinProductOpenPlanAdjustService extends BaseService<FinProductOpenPlanAdjustDto>{
    
    /**
     * 开放计划查询mapper
     */
    @Autowired
    private FinProductOpenPlanAdjustMapper finProductOpenPlanMapper;
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> pageParam){
      //数据字典缓存.开放模式.高频开放  
        //数据字典缓存.开放类型.开放式
        pageParam.put("openType", DictCodeList.开放类型_开放式.getValue());
        pageParam.put("openMode", DictCodeList.开放模式_高频开放.getValue());
        return finProductOpenPlanMapper.getFinprodId(pageParam);
    }
    
    /**
     * 
     * 开放计划查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<FinProductOpenPlanDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<FinProductOpenPlanAdjustDto> selectForPage(int page, int length,Map<String, Object> pageParam)
        throws Exception {
            pageParam.put("openDateStrStart", DateUtil.parse((String)pageParam.get("openDateStrStart")));
            pageParam.put("openDateStrEnd", DateUtil.parse((String)pageParam.get("openDateStrEnd")));
            return super.queryForPage(page, length, () -> finProductOpenPlanMapper.selectForPage(pageParam));
    }
}
