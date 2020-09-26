/*
 * 文件名：ValCashIntInfoBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinRateInfo;

@Repository
public interface ValCashIntInfoBatchMapper {
 
	 int insertBatch(List<FinRateInfo> list);
	 /**
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param rstValCashIntInfolist
	  * @return
	  */
	 int deleteBatch(List<FinRateInfo> list);
}
