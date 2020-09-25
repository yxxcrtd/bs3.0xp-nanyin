/*
 * 文件名：BussRepDlRepoInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.asset.common.dao.BussRepDlRepoInfoMapper;
import com.joyintech.service.asset.common.dto.BussRepDlRepoInfoDto;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepDlRepoInfoService
 * @since 1.0
 */
@Service
public class BussRepDlRepoInfoService {

    @Autowired
    private BussRepDlRepoInfoMapper bussRepDlRepoInfoMapper;
    
    public BussRepDlRepoInfoDto getBussRepDlRepoInfoByProductId(String finprodId){
        return bussRepDlRepoInfoMapper.getBussRepDlRepoInfoByProductId(finprodId);
    }
}
