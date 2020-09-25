/*
 * 文件名：ValDocumentManagement001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.documentmanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.documentmanagement.dto.DocumentManagement001Dto;


/**
 * 文档列表mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月24日
 * @see DocumentManagement001Mapper
 * @since 1.0
 */
@Repository(value="serviceDocumentManagement001Mapper")
public interface DocumentManagement001Mapper {
	/**
	 * 查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 */
	List<DocumentManagement001Dto> queryPageList(Map<String, Object> params);
	
	  /**
     * 根据主键查询文件信息
     * @param seqNo
     * @return
     */
	DocumentManagement001Dto qryBokDocInfoByFileId(String fileId);
 
}
