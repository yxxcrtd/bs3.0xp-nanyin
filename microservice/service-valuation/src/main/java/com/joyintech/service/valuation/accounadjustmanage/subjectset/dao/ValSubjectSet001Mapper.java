/*
 * 文件名：ValSubjectSet001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.subjectset.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.dto.ValBokSubjectDto;

/**
 * 科目设置Mapper
 * 
 * @author 杨智
 * @version 1.0 2018年5月17日
 * @see ValSubjectSet001Mapper
 * @since 1.0
 */
@Repository(value="serviceValSubjectSet001Mapper")
public interface ValSubjectSet001Mapper {
    
//    /**
//     * 查询所有账套
//     * <br/> 用于所属账套控件初期化
//     * @return SelectOptionDto list
//     * @see
//     * @since 1.0
//     */
//    List<SelectOptionDto> findBooksets();
    
    /**
     * 检索上级科目代码
     * 
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFSubjectId();
    
    /**
     * 检索上级科目代码（支持传参）
     * 
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFSubjectId2(Map<String, Object> map);
    
    /**
     * 获取科目号联想文本输入框
     * 
     * @return 科目代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getSubjectId();
    
//    /**
//     * 获取科目号联想文本输入框
//     * 
//     * @return 科目代码列表
//     * @see
//     * @since 1.0
//     */
//    List<SuggestDto> getSubjectId2(Map<String, Object> map);
    
    /**
     * 获取科目号联想文本输入框
     * 
     * @return 科目代码列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSubjectId2(Map<String, Object> map);
    
    /**
     * 获取科目级别联动
     * 
     * @return 科目代码列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSubjectLevel(Map<String, Object> map);
    
    
    /**
     * 获取科目号下拉框
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryValBokSubject(Map<String, Object> map);
    
    
    /**
     * 检索所有科目
     * 
     * @param pageParam 检索参数
     * @return 科目列表
     * @see
     * @since 1.0
     */
    List<ValBokSubjectDto> selectAll(Map<String, Object> pageParam);
    /**
     * 根据主键检索科目
     * 
     * @param booksetId 账套代码
     * @param subjectNo 科目代码
     * @return 科目
     * @see
     * @since 1.0
     */
    ValBokSubjectDto selectValBokSubject(@Param("booksetId") String booksetId, @Param("subjectNo") String subjectNo);
    /**
     * check科目是否存在
     * 
     * @param dto 科目
     * @return 条目
     * @see
     * @since 1.0
     */
    int checkExists(ValBokSubjectDto dto);

    /**
     * 批量删除（删除当前科目及其子科目）
     * @param item
     * @see
     * @since 1.0
     */
    void deleteByFSubjectId(ValBokSubjectDto item);
}
