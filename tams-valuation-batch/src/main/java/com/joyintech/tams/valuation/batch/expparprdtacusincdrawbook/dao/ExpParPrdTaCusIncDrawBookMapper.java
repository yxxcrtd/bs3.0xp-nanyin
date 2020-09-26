/*
 * 文件名：BookDetailSubjectCurrentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expparprdtacusincdrawbook.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.accounting.entity.StayDrawSubprdEntity;

/**
 * 客户收益计提
 * 
 * @author 王耕耘
 * @version 1.0 2018年6月1日
 * @see ExpParPrdTaCusIncDrawBookMapper
 * @since 1.0
 */
@Repository
public interface ExpParPrdTaCusIncDrawBookMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return 
     * @see
     * @since 1.0
     */
    StayDrawSubprdEntity queryBookdetailId(Map<String, Object> params);
    

}
