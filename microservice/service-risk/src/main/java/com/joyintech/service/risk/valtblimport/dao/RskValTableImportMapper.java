/*
 * 文件名：RskValTableImportMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.dao;

import java.util.Map;

import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;

/**
 *  估值表导入
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月7日
 * @see RskValTableImportMapper
 * @since 1.0
 */
public interface RskValTableImportMapper {

    int selectCount(Map<String,Object> paramMap);
    
    void deleteValTable(RskValtblAssetSubDto dto);
    
    void deleteSumValTable(RskValtblAssetSubDto dto);
     
}
