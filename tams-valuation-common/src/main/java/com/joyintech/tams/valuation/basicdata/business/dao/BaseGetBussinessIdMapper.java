/*
 * 文件名：BussinessCodeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.business.dao;

import com.joyintech.tams.common.base.entity.MstBusinessIdRule;
import com.joyintech.tams.valuation.basicdata.business.dto.BussinessCodeParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务编号生成使用mapper
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月14日
 * @see BaseGetBussinessIdMapper
 * @since 1.0
 */
@Repository
public interface BaseGetBussinessIdMapper {

    /**
     * 查询所需要的参数
     * <功能详细描述>
     * @param parameter 参数
     * @return 规则
     * @see
     * @since 1.0
     */
    // 多条规则时直接取第一条 modify by renping 20190924 start
    List<MstBusinessIdRule> selectRuleByParameter(BussinessCodeParameter parameter);
    // 多条规则时直接取第一条 modify by renping 20190924 end

    /**
     * 通过sequence名称获取序列值
     * <功能详细描述>
     * @param sequenceName sequence名称
     * @return 获取的值
     * @see
     * @since 1.0
     */
    String getNextValBySequenceName(@Param("sequenceName") String sequenceName);

}
