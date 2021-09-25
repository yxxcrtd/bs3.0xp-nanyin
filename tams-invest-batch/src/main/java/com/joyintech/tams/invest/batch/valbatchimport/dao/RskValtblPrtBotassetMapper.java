/*
 * 文件名：RskValtblPrtBotassetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valbatchimport.dao;

import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValtblPrtBotassetMapDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetMapper
 * @since 1.0
 */
@Repository
public interface RskValtblPrtBotassetMapper {

    List<RskValtblPrtBotassetMapDto>  selectBotAssetMap(@Param("ptlId") String ptlId);
}
