/*
 * 文件名：InfoDiscAutoConfirmMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.BokValTableDataForIndexDto;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
import com.joyintech.tams.common.base.entity.RepProdInfo;



/**
 * 产品指标表批处理mapper
 * @author leipeng
 * @version 1.0 2019年4月29日
 * @see DisReportProdIndex
 * @since 1.0
 */
@Repository
public interface DisReportProdIndexMapper {
	
	int insert(DisReportProdIndexResult record);

    int insertSelective(DisReportProdIndexResult record);

    int insertBasicByBasicInfo(DisReportProdIndexResult record);
    
    
    
    int updateprodIndex(DisReportProdIndexResult record);
	
	/**
	 * 重置产品信息
	 * @param dis
	 * @return
	 */
	int updateRepoprodIndex(Map<String,Object> map);
	
	/**
	 * 更新产品信息
	 * @param record
	 * @return
	 */
	int updateRepoprodIndexALL(DisReportProdIndexResult record);
	
	
	/**
	 * 获取产品指标表信息
	 * @param map
	 * @return
	 */
	List<DisReportProdIndexResult> getDisReportProdIndexVal(Map<String,Object> map);
	
	
	/**
	 * 获取单挑产品指标表数据(昨日)
	 * @param map
	 * @return
	 */
	DisReportProdIndexResult getDisReportProdIndexValDate(Map<String,Object> map);
	
	/**
	 * 投资组合平均到期日
	 * @param map
	 * @return
	 */
	Integer getAverageMdate(Map<String,Object> map);
	
	/**
	 * 获取预期收益型产品预期收益率
	 * @param map
	 * @return
	 */
	BigDecimal getYeildE(Map<String,Object> map);
	
	
	/**
	 * 获取产品基本信息
	 * @param prodid
	 * @return
	 */
	List<RepProdInfo> getPrdInfo(String prodid);
	
	
	/**
	 * 获取估值表指标数据
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokValTableDataForIndexDto> getBokValTableDataForIndex(Map<String, Object> map);
}
