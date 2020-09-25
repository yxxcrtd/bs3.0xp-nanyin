/*
 * 文件名：BaseRepDespositAssetInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;

/**
 * 存款资产报送信息组件mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月3日
 * @see BaseRepDespositAssetInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepDespositAssetInfoMapper {
    /**
     * 
     * 查询存款资产报送信息
     * <功能详细描述>
     * @param finprodIdList List<String>
     * @return List
     * @see
     * @since 1.0
     */
    List<RepAssetBankDeposit> select(@Param("finprodIdList")List<String> finprodIdList);
/**
     * 
     * 删除存款资产报送信息
     * <功能详细描述>
     * @param dto BaseRepDespositAssetInfoDto
     * @return int
     * @see
     * @since 1.0
     */
    int delete(@Param("finprodIdList")List<String> finprodIdList);
   
}
