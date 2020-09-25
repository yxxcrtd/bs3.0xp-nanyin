/*
 * 文件名：InfoDiscAutoConfirmMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;



/**
 * 产品指标表批处理mapper
 * @author leipeng
 * @version 1.0 2019年4月29日
 * @see DisReportProdIndex
 * @since 1.0
 */
@Repository
public interface DisReportAssetIndexMapper {
	
	 int insert(DisReportAssetIndexResult record);

    int insertSelective(DisReportAssetIndexResult record);

    int insertBasicByBasicInfo(DisReportAssetIndexResult record);
	
	/**
	 * 
	 * @param dis
	 * @return
	 */
	int updateRepoAssetIndex(Map<String,Object> map);
	
	
	
	/**
	 * 
	 * @param dis
	 * @return
	 */
	int updateRepoAssetIndexAll(DisReportAssetIndexResult record);
	
	
	
	List<DisReportAssetIndexResult> getDisReportAssetIndexVal(Map<String,Object> map);
	
	
}
