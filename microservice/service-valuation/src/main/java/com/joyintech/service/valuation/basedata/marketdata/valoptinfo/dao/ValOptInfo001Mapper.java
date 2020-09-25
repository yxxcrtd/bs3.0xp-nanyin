/*
 * 文件名：ValOptInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年03月05日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valoptinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;

/**
 * 场外期权标的
 * 
 * @author tanxuan
 * @version 1.0 2020年03月05日
 * @see ValOptInfo001Mapper
 * @since 1.0
 */
@Repository(value="serviceValOptInfo001Mapper")
public interface ValOptInfo001Mapper {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return List<ValOtcOpt001Dto>
     * @see
     * @since 1.0
     */
    List<ValOptInfo001Dto> findPage(Map<String, Object> params);
    
    /**
     * 交易编号联想框
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> findFinprodId(Map<String, Object> map);

}
