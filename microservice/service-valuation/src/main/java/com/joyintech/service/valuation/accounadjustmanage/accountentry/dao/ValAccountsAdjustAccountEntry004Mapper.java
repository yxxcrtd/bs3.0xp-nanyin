/*
 * 文件名：AccountsAdjustAccountEntry004Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry004Dto;

/**
 * 查看会计分录详情Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年7月18日
 * @see ValAccountsAdjustAccountEntry004Mapper
 * @since 1.0
 */
@Repository(value="serviceValAccountsAdjustAccountEntry004Mapper")
public interface ValAccountsAdjustAccountEntry004Mapper {

    /**
     * 查询会计分录详情（场景科目关系表）
     * @param booksetId 账套代码
     * @param sceneNodeId 场景分支节点
     * @return 会计分录详情
     * @see
     * @since 1.0
     */
    List<ValAccountsAdjustAccountEntry004Dto> searchList(@Param("booksetId") String booksetId,
                                           @Param("sceneNodeId") String sceneNodeId);
    
    
    String  getAllSceneNodeId(@Param("booksetId") String booksetId,
                            @Param("sceneNodeId") String sceneNodeId);
}
