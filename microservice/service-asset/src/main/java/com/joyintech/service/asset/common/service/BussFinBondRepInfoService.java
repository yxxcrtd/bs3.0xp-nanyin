/*
 * 文件名：BussFinBondRepInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.asset.common.dao.BussFinBondRepInfoMapper;
import com.joyintech.service.asset.common.dto.BussFinBondRepInfoDto;

/**
 *  债券--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoService
 * @since 1.0
 */
@Service
public class BussFinBondRepInfoService {

    @Autowired
    private BussFinBondRepInfoMapper bussFinBondRepInfoMapper;
    
    public BussFinBondRepInfoDto getBussFinBondRepInfoByProductId(String finprodId){
        return bussFinBondRepInfoMapper.getBussFinBondRepInfoByProductId(finprodId);
    }
}
