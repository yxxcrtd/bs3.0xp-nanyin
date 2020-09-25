/*
 * 文件名：FinDisclosureModuleMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月31日
 * @see FinDisclosureModuleMapper
 * @since 1.0
 */
@Repository
public interface FinDisclosureModuleMapper {
    /**
     * @param param param
     * @return list集合
     */
    List<String> getRevealFileIdList(Map<String, Object> param);
    /**
     * 在base库中查询文件名
     * 
     * @param param 查询条件
     * @return 文本框
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFileNameByBase(Map<String, Object> param);
//    /**
//     * 画面查询方法
//     * <功能详细描述>
//     * @param param 查询条件
//     * @return 实体类集合
//     * @see
//     * @since 1.0
//     */
//    List<FinDisclosureModuleDto> getPageFileNameByBase(Map<String, Object> param);
    /**
     * 根据文档类型查询信息披露表中的信息
     * <功能详细描述>
     * @param pageParam 页面.文档类型
     * @return list集合
     * @see
     * @since 1.0
     */
    List<FinDisclosureModuleDto> getFileList(Map<String, Object> pageParam);
    /**
     * 通过披露文档代码查询数据
     * <功能详细描述>
     * @param map 披露文档代码
     * @return dto
     * @see
     * @since 1.0
     */
    FinDisclosureModuleDto getDataByRevealFileId(Map<String, Object> map);
//    /**
//     * 通过披露文档代码查询文件名
//     * <功能详细描述>
//     * @param map 披露文档代码
//     * @return 文件名
//     * @see
//     * @since 1.0
//     */
//    String getFileNameByRevealFileId(Map<String, Object> map);
//    /**
//     * 将文件名，流水号等信息存入文件信息表中
//     * @param dto dto
//     * @see
//     * @since 1.0
//     */
//    void insertIntoSysUploadFileInfo(FinDisclosureModuleDto dto);
    /**
     * 删除时判断选中的记录是否已经被使用
     * <功能详细描述>
     * @param revealFileId 披露文档代码
     * @return  String
     * @see
     * @since 1.0
     */
    String isUsed(String revealFileId);
   

}
