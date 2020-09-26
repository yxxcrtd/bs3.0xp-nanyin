/*
 * 文件名：ProdChannelFeeCalBookMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.prodchannelfeecal.dto.ProdChannelFeeCalBookReaderDto;

/**
 * 利润分配批处理 - 数据库数据获取
 * 
 * @author chengzhong
 * @version 1.0 2018年5月24日
 * @see ProdChannelFeeCalBookMapper
 * @since 1.0
 */
@Repository
public interface ProdChannelFeeCalBookMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ProdChannelFeeCalBookReaderDto> searchProdChannelFeeTodo(Map<String, Object> params);


}
