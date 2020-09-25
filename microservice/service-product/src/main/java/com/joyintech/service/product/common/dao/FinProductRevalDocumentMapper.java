/*
 * 文件名：FinProductRevalDocumentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.FinProductFileInfoDto;

/**
 * 信息披露文档模板组件mapper
 * 
 * @author wangzehui
 * @version 1.0 2018年10月19日
 * @see FinProductRevalDocumentMapper
 * @since 1.0
 */
@Repository
public interface FinProductRevalDocumentMapper {

	/**
	 * 根据金融产品代码删除
	 * 
	 * @param finprodId 金融产品代码
	 * @return 删除记录数
	 */
	int delete(String finprodId);

	/**
	 * 根据金融产品代码查询所有信息披露文件信息
	 * 
	 * @param finprodId
	 *            金融产品代码
	 * @return 信息披露文件列表
	 */
	List<FinProductFileInfoDto> selectFileList(String finprodId);

	/**
	 * 根据金融产品代码查询所有信息披露文件信息
	 * 
	 * @param fileType 文件类型
	 * @param initValue 初始值
	 * @return 信息披露文件列表
	 */
	List<FinProductFileInfoDto> selectRevealList(@Param("fileType") String fileType, @Param("initValue") String initValue);

}
