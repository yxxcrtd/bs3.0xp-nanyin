/*
 * 文件名：BussRepAssetAstBillInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.asset.common.dto.BussRepAssetAstBillInfoDto;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepAssetAstBillInfoMapper
 * @since 1.0
 */
@Repository
public interface BussRepAssetAstBillInfoMapper {

    BussRepAssetAstBillInfoDto getBussRepAssetAstBillInfoByProductId(@Param("finprodId") String finprodId);
}
