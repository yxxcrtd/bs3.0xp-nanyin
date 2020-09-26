/*
 * 文件名：ValBondassetquery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery001Mapper
 * @since 1.0
 */
@Repository
public interface ValBondassetquery001Mapper {
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValBondassetquery001Dto>
     * @see
     * @since 1.0
     */
    List<ValBondassetquery001Dto> queryPageList(Map<String, Object> params);

    /**
     * 债券下拉框初始化
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery001Dto> getBondId(Map<String, Object> params);
    
    /**
     * 查询流程中数据
     * <功能详细描述>
     * @param processMark
     * @return
     * @see
     * @since 1.0
     */
    int getDataInWorklow(ProcessMark processMark);

}
