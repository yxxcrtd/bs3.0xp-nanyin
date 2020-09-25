/*
 * 文件名：DocumentManagement002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.documentmanagement.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.documentmanagement.dto.DocumentManagement001Dto;


@Repository(value="serviceDocumentManagement002Mapper")
public interface DocumentManagement002Mapper {
	
	/**
	 * 新增
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param record
	 * @return
	 */
	int insert(DocumentManagement001Dto record);
}
