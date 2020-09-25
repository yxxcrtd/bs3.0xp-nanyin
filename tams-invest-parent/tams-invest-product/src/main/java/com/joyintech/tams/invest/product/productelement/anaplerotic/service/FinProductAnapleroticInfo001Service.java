/*
 * 文件名：ValBokClawbackInfoQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：李俊
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.anaplerotic.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;

/**
 * 回补信息查询
 * 
 * @author 李俊
 * @version 1.0 2019年4月22日
 * @see FinProductAnapleroticInfo001Service
 * @since 1.0
 */
@Service
public class FinProductAnapleroticInfo001Service extends CommonExportService<FinProductAnapleroticInfoDto> {


    /**
     * 查询排期所有数据
     * 
     * @param map 参数map
     * @return 排期所有数据
     * @see
     * @since 1.0
     */
    public Page<FinProductAnapleroticInfoDto> findPage(Map<String, Object> map) {
        //不为空
        String finprodIds = (String)map.get("finprodIds");
        if(StringUtil.isNotEmpty(finprodIds)){
            map.put("finprodIds", Arrays.asList(finprodIds.split(",")));
        }
        String prodStyle = (String)map.get("prodStyle");//支持分层
        if(StringUtil.isNotEmpty(prodStyle)){
        	map.put("prodStyleList", StringUtil.splitToList(GlobalConstant.COMMA,prodStyle));
        }
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/selectForPage", map,
            FinProductAnapleroticInfoDto.class);
    }
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo (Map<String, Object> map){
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryProdIdInfo",
            map, SelectOptionDto.class);
    }
    
   public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoById(Map<String, Object> map){
       return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
           + "/FinProductAnaplerotic/queryAnapleroticInfoById",map,FinProductAnapleroticInfoDto.class);
   }
   /**
    * 导出
    */
   @Override
   protected void buildExportParam(ExportParam exportParam) {
       exportParam.config(
           "/com/joyintech/tams/invest/product/productelement/anaplerotic/conf/export_conf.xml").dataSource(
               DataSourceType.MASERT);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Page<FinProductAnapleroticInfoDto> selectPageData(Map<String, Object> selectParam) {
       return findPage(selectParam);
   }
}
