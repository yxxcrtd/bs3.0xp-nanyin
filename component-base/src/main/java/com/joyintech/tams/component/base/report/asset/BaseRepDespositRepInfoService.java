/*
 * 文件名：BaseRepDespositRepInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;


import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;
import com.joyintech.tams.component.base.report.dto.BaseRepDespositAssetInfoDto;

/**
 * 存款资产报送信息组件
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月30日
 * @see BaseRepDespositRepInfoService
 * @since 1.0
 */
public interface BaseRepDespositRepInfoService {
    /**
     * 
     * 新建存款资产报送信息
     * <功能详细描述>
     * @param list List<RepAssetBankDeposit>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<RepAssetBankDeposit> list);
    /**
     * 
     * 删除存款资产报送信息
     * <功能详细描述>
     * @param dto BaseRepDespositAssetInfoDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean delete(BaseRepDespositAssetInfoDto dto);
    /**
     * 
     * 修改存款资产报送信息
     * <功能详细描述>
     * @param dto BaseRepDespositAssetInfoDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean update(BaseRepDespositAssetInfoDto dto);
    /**
     * 
     * 查询存款资产报送信息
     * <功能详细描述>
     * @param dto BaseRepDespositAssetInfoDto
     * @return List
     * @see
     * @since 1.0
     */                    
    List<RepAssetBankDeposit> select(BaseRepDespositAssetInfoDto dto);

}
