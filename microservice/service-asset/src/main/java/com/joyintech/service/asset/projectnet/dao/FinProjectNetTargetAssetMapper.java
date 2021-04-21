/*
 * 文件名：FinPorjectTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.projectnet.dao;

import com.joyintech.service.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 项目类资产标的信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinProjectNetTargetAssetMapper {

    List<FinProjectNetTargetAssetDto> queryForPage(Map<String, Object> pageParam);

    FinProjectNetTargetAssetDto getBasicInfo(Map<String, Object> param);

    String checkMarketExists(Map<String, Object> map);
}
