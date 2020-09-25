/*
 * 文件名：BusiFinBondRatingInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.bond;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;

/**
 * 评级信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月13日
 * @see BusiFinBondRatingInfoService
 * @since 1.0
 */
public interface BusiFinBondRatingInfoService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "busiFinBondRatingInfoDto";
    
    List<MstBondCreditRating> getmstBondCreditRatingByProductId(Map<String,Object> param);
    
    List<MstBondCreditRating> getmstBondCreditRatingByOcode(Map<String,Object> param);
}
