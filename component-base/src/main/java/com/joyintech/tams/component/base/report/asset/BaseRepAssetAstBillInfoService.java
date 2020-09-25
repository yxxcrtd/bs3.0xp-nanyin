/*
 * 文件名：BaseRepAssetAstBillInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetAstBill;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetAstBillInfoDto;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetAstBillInfoService
 * @since 1.0
 */
public interface BaseRepAssetAstBillInfoService {

    /**
     * 新建
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<RepAssetAstBill> list);
    
    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(BaseRepAssetAstBillInfoDto dto);
    
    /**
     * 更新
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseRepAssetAstBillInfoDto dto);
    
    /**
     * 查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetAstBill> select(BaseRepAssetAstBillInfoDto dto);
}
