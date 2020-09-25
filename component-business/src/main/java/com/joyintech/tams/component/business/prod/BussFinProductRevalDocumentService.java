/*
 * 文件名：BussFinProductRevalDocumentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.common.base.entity.PrdFileKey;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;

/**
 * 信息披露组件
 * 
 * @author qiangchengxi
 * @version 1.0 2018年10月19日
 * @see BussFinProductRevalDocumentService
 * @since 1.0
 */
public interface BussFinProductRevalDocumentService {

    /**
     * 删除金融产品披露信息
     * 
     * @param finprodId 金融产品代码
     * @return 删除记录数
     */
    int delete(String finprodId);

    /**
     * 删除金融产品披露信息
     * 
     * @param key 信息披露主键
     * @return 删除记录数
     */
    int deleteByPrimaryKey(PrdFileKey key);

    /**
     * 查询信息披露文档信息
     * @param finprodId 金融产品代码
     * @return 披露文档列表
     */
    List<BussFinProductRevalDocumentDto> selectByProductId(String finprodId);

    /**
     * 查询信息披露文档信息
     * @param finprodId 金融产品代码
     * @return 披露文档列表
     */
    List<BussFinProductRevalDocumentDto> selectByWorkflowId(String workflowId, String beanName);

    /**
     * 查询信息披露文档信息
     * @param fileType 文档类型
     * @param initValue 初始值
     * @return 披露文档列表
     */
    List<SelectOptionDto> selectByFileType(String fileType, String initValue);
}
