/*
 * 文件名：ServiceInstitutionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;

/**
 * 用户管理Mapper
 * 
 * @author 杨智
 * @version 1.0 2018年1月29日
 * @see ServiceInstitutionMapper
 * @since 1.0
 */
@Repository("serviceInstitutionMapper")
public interface ServiceInstitutionMapper {

    List<SuggestDto> getInstitutionName();

    List<SuggestDto> getAllInstitutionName();
}
