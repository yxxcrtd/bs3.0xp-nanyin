/*
 * 文件名：ValPlannedassetquery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.plan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001SearchDto;
import com.joyintech.tams.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.tams.valuation.assets.plan.dto.ValPlannedassetquery001SearchDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValPlannedassetquery001Mapper
 * @since 1.0
 */
@Repository
public interface ValPlannedassetquery001Mapper {
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValPlannedassetquery001Dto>
     * @see
     * @since 1.0
     */
    List<ValPlannedassetquery001Dto> queryPageList(Map<String, Object> params);

    /**
     * 债券下拉框初始化
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBondId(Map<String, Object> params);

}
