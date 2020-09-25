/*
 * 文件名：BaseRepAssetAstBillInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetAstBill;

/**
 *  非标资产(票据或信用)中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetAstBillInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepAssetAstBillInfoMapper {

    /**
     * 查询
     * <功能详细描述>
     * @param finprodIdList
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetAstBill> select(@Param("finprodIdList") List<String> finprodIdList);
    
    /**
     * 删除
     * <功能详细描述>
     * @param finprodIdList
     * @return
     * @see
     * @since 1.0
     */
    int delete(@Param("finprodIdList") List<String> finprodIdList);
}
