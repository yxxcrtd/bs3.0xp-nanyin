/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstshareprice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstnetassetquotationinfo.dto.MstNetAssetQuotationInfo001Dto;
import com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto;

/**
 * 股票行情管理mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see MstSharePrice001Mapper
 * @since 1.0
 */

@Repository
public interface MstSharePrice001Mapper {
    
	MstSharePriceDto getLatelyShareById(Map<String,Object> key);
    
    List<MstSharePriceDto> select(Map<String,Object> key);

    List<SelectOptionDto> getFinprodIdForAdd(Map<String, String> map);
    
    /**
    * 查询新增数据主键是否重复
    * @param list 产品list
    * @return list
    * @see
    * @since 1.0
    */

   String getRegistExcelList( Map<String,Object> param );

   /**
    * 查询新增数据产品代码是否存在
    * @param finprodId 产品代码
    * @param finprodType2 金融产品类型
    * @return list
    * @see
    * @since 1.0
    */

   String  checkFinprod( Map<String,Object> param) ;

   String  checkFinprodIdAndFinprodName( Map<String,Object> param) ;
}