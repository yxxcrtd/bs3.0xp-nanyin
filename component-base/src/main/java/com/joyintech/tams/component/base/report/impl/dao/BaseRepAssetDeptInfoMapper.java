/*
 * 文件名：BaseRepAssetDeptInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;


/**
 * 资产负债信息报送组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月4日
 * @see BaseRepAssetDeptInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepAssetDeptInfoMapper {
    
    /**
     * 
     * 资产负债信息批量删除
     * <功能详细描述>
     * @param item
     * @return
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKeyList(List<String> finprodIdList);
    
    /**
     * 
     * 资产负债信息新建
     * <功能详细描述>
     * @param baseRepAssetDeptInfoDto
     * @return
     * @see
     * @since 1.0
     */
    int insert(RepAssetDept repAssetDept);
    
    /**
     * 
     * 资产负债信息批量新建
     * <功能详细描述>
     * @param BaseRepAssetDeptInfoList
     * @return
     * @see
     * @since 1.0
     */
    int insertList(List<RepAssetDept> repAssetDept);
    
    /**
     * 通过标的金融产品代码查询
     * @param repFinprodId
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDept> selectRepAssetDeptInfo(BaseRepAssetDeptInfoDto dto);

}
