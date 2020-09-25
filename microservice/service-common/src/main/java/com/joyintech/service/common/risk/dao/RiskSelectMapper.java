/*
 * 文件名：RiskSelectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.common.risk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.service.common.risk.dto.RiskSelectDto;

/**
 * 获取风险试算的结果
 * @author fanshuanghu
 * @version 1.0 2019年1月17日
 * @see RiskSelectMapper
 * @since 1.0
 */
@Repository
public interface RiskSelectMapper {
    /**
     * 风险结果查询
     * @param param 查询的交易编号
     * @return 风险试算结果
     */
    List<RiskSelectDto> select(Map<String, Object> param);
    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 金融产品估值信息
     * @see
     * @since 1.0
     */
    FinProductValuation getValue(Map<String,Object> map);
}
