/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.common.valfilegendel.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;


/**
 * 估值表生成Mapper
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValFileGenDelMapper
 * @since 1.0
 */
@Repository
public interface ValFileGenDelMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param valFileGenDelProcessorDto
     * @see
     * @since 1.0
     */
//    void deleteValFileGen(Map<String, Object> params);
    
    void deleteBalTableData(Map<String, Object> params);
    
    void deleteValTableData(Map<String, Object> params);
    
    void deleteTableLog(Map<String, Object> params);
    
    void deleteTableStatus(Map<String, Object> params);

    void deleteTableStatusExt(Map<String, Object> params);
}
