/*
 * 文件名：RepCheckInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.RepCheckInfoDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.RepProdCheckInfo;

/**
 * 信批内容校验
 * @author wanghb
 * @version 1.0 2019年3月20日
 * @see RepCheckInfoMapper
 * @since 1.0
 */
@Repository
public interface RepCheckInfoMapper {
    /**
     * 根据报表编号和校验位置查询校验内容
     * @param param 条件
     * @return 结果
     * @see
     * @since 1.0
     */
    List<RepCheckInfoDto> getCheckInfo(Map<String,Object> param);
    /**
     * 查询产品信息
     * @param param 条件
     * @return 产品信息
     * @see
     * @since 1.0
     */
    RepProdCheckInfo selectProdInfo(Map<String, Object> param);
}
