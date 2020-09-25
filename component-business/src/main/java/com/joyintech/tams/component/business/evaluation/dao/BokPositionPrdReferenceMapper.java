/*
 * 文件名：EvalIndexAlgorithmMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.component.business.evaluation.dto.BokPositionPrd;

/**
 * 2019年10月9日
 * yangxiaofeng
 * 估值信息列表查询
 */
@Repository
public interface BokPositionPrdReferenceMapper {

    /**
     * 估值信息列表查询
     */
    List<BokPositionPrd> getBokPositionPrdList(Map<String,Object> parMap);

    /**
     * 查询某一天的估值信息
     * @param parMap
     * @return
     */
    BokPositionPrd getBokPositionPrdOfDay(Map<String,Object> parMap);

}
