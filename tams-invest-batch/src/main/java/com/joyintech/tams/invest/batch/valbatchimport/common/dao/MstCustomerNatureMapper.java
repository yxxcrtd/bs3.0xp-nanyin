/*
 * 文件名：MstCustomerNatureMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.valbatchimport.common.dao;

import com.joyintech.tams.invest.batch.valbatchimport.common.dto.MstCustomerNatureDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 客户性质
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwei
 * @version 1.0 2018年8月16日
 * @see MstCustomerNatureMapper
 * @since 1.0
 */
@Repository
public interface MstCustomerNatureMapper {
    /**
     * 通过主键修改方法
     * @param record MstCustomerNatureDto
     * @return int
     */
    int updateByPrimaryKey(MstCustomerNatureDto record);

    /**
     * 通过主键查询方法 <一句话功能简述> <功能详细描述>
     * 
     * @param param map
     * @return MstCustomerNatureDto
     * @see
     * @since 1.0
     */
    MstCustomerNatureDto selectByPrimaryKey(Map<String, Object> param);
    
    String getIssuer(@Param("linkId1") String linkId1);

}
