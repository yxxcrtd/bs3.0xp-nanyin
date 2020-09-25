/*
 * 文件名：BaseRepBondAssetInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetBond;

/**
 * 债券资产信息报送组件mapper 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年9月3日
 * @see BaseRepBondAssetInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepBondAssetInfoMapper {

    /**
     * 债券资产信息报送组件查询 <功能详细描述>
     * 
     * @param dto 债券资产信息报送组件dto
     * @return 债券资产信息报送实体类list
     * @see
     * @since 1.0
     */
    List<RepAssetBond> select(@Param("finprodIdList") List<String> finprodIdList);
//
//    /**
//     * 债券资产信息报送组件查询 <功能详细描述>
//     * 
//     * @param dto 债券资产信息报送组件dto
//     * @return 债券资产信息报送实体类list
//     * @see
//     * @since 1.0
//     */
//    int insert(@Param("repAssetBondList") List<RepAssetBond> repAssetBondList);

    /**
     * 债券资产信息报送组件查询 <功能详细描述>
     * 
     * @param dto 债券资产信息报送组件dto
     * @return 债券资产信息报送实体类list
     * @see
     * @since 1.0
     */
    int delete(@Param("finprodIdList") List<String> finprodIdList);
}
