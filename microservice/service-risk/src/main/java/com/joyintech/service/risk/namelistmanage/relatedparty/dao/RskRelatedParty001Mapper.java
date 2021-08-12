/*
 * 文件名：RskRelatedParty001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.namelistmanage.relatedparty.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;
import com.joyintech.tams.common.base.entity.RskRelatedParty;

/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedParty001Mapper
 * @since 1.0
 */
@Repository
public interface RskRelatedParty001Mapper {

    /**
     * 查询关联方管理记录
     * 
     * @param param 查询条件
     * @return 关联方管理记录
     */
    List<RskRelatedPartyDto> queryForPage(Map<String, Object> param);

    /**
     * 查询关联方名称列表
     * 
     * @param param 查询条件
     * @return 关联方名称列表
     */
    List<SuggestDto> selectInstituteName(Map<String, Object> param);

    /**
     * 批量登录关联方管理记录
     * 
     * @param dtoList 关联方管理记录
     * @return 处理结果
     */
    int insertList(List<RskRelatedPartyDto> dtoList);

    /**
     * 批量删除关联方管理记录
     * 
     * @param codeList 关联方管理记录
     * @return 处理结果
     */
    int deleteList(List<String> codeList);

    /**
     * 查询关联方信息
     * 
     * @param codeList 机构代码
     * @return 关联方信息
     */
    List<RskRelatedParty> queryInfoByPrimaryKey(List<String> codeList);

    /**
     * 从机构信息标中查询社会统一信用代码
     * 
     * @param codeList 社会统一信用代码
     * @return 社会统一信用代码
     */
    List<String> selectInstituteCode(List<String> codeList);

    int update(RskRelatedPartyDto dto);
}
