/*
 * 文件名：RepBondAssetInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.component.base.report.dto.BaseRepBondAssetInfoDto;

/**
 * 债券资产报送信息基础组件
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年8月29日
 * @see BaseRepBondAssetInfoService
 * @since 1.0
 */
public interface BaseRepBondAssetInfoService {
    /**
     * 
     * 新建债券资产报送信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<RepAssetBond> list);
    /**
     * 
     * 删除债券资产报送信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(BaseRepBondAssetInfoDto dto);
    /**
     * 
     * 修改债券资产报送信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseRepBondAssetInfoDto dto);
    /**
     * 
     * 根据金融产品代码查询债券资产报送信息
     * <功能详细描述>
     * @param repFinprodId
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetBond> select(BaseRepBondAssetInfoDto dto);
}
