/*
 * 文件名：BaseRepMngAssetPlanInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetMngPlan;

/**
 * 资产管理产品中债报送信息组件mapper
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see BaseRepMngAssetPlanInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepMngAssetPlanInfoMapper {

    

    /**
     * 查询
     * <功能详细描述>
     * @param idList
     * @see
     * @since 1.0
     */
    List<RepAssetMngPlan> search(List<String> idList);

    /**
     * 删除
     * <功能详细描述>
     * @param entityList
     * @return
     * @see
     * @since 1.0
     */
    Integer insert(List<RepAssetMngPlan> entityList);

    /**
     * 删除
     * <功能详细描述>
     * @param idList
     * @return
     * @see
     * @since 1.0
     */
    Integer delete(List<String> idList);

}
