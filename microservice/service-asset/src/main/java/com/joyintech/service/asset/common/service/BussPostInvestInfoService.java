/*
 * 文件名：BussPostInvestInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinPostInvestInfoMapper;
import com.joyintech.tams.common.base.entity.FinPostInvestInfo;

/**
 *  投后信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussPostInvestInfoService
 * @since 1.0
 */
@Service
public class BussPostInvestInfoService {

    @Autowired
    private FinPostInvestInfoMapper  finPostInvestInfoMapper;
    
    
    public FinPostInvestInfo getBussPostInvestInfoByProductId(String finprodId){
        return finPostInvestInfoMapper.selectByPrimaryKey(finprodId);
    }
}
