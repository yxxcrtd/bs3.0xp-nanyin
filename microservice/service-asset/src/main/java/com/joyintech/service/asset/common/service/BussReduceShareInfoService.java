/*
 * 文件名：BussReduceShareInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.service.asset.common.dao.BussReduceShareInfoMapper;

/**
 *  减持信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoService
 * @since 1.0
 */
@Service
public class BussReduceShareInfoService {

    @Autowired
    private BussReduceShareInfoMapper bussReduceShareInfoMapper;
    
    /**
     * 初始化(修改/查看)
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinReduceShareInfo> getBussReduceShareInfoByProductId(String finprodId){
        return bussReduceShareInfoMapper.getBussReduceShareInfoByProductId(finprodId);
    }
}
