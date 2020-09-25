/*
 * 文件名：RepDeptAssetInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;

/**
 * 资产负债信息报送组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see BaseRepAssetDeptInfoService
 * @since 1.0
 */
public interface BaseRepAssetDeptInfoService {
    
    /**
     * 
     * 资产负债信息删除
     * <功能详细描述>
     * @param repFinprodId
     * @return
     * @see
     * @since 1.0
     */
    boolean deleteByPrimaryKey(BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto);
    
    /**
     * 
     * 资产负债信息新建
     * <功能详细描述>
     * @param baseRepAssetDeptInfoDto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<RepAssetDept> repAssetDeptList);
    
    /**
     * 
     * 资产负债信息更新
     * <功能详细描述>
     * @param baseRepAssetDeptInfoDto
     * @return
     * @see
     * @since 1.0
     */
    boolean updateBaseRepAssetDeptInfoDto(BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto);
    
    List<RepAssetDept> selectRepAssetDeptInfo(BaseRepAssetDeptInfoDto dto);
    
}
