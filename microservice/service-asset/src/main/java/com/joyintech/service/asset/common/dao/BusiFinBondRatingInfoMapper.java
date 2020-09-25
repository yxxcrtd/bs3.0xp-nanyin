/*
 * 文件名：BusiFinBondRatingInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;

/**
 * 评级信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月13日
 * @see BusiFinBondRatingInfoMapper
 * @since 1.0
 */
@Repository
public interface BusiFinBondRatingInfoMapper {

    List<MstBondCreditRating> getmstBondCreditRatingByProductId(@Param("finprodId") String finprodId);
    
    void deleteMstBondCreditRatingByProductId(@Param("finprodId") String finprodId);
    
    List<MstBondCreditRating> getmstBondCreditRatingByOcode(@Param("oCode") String oCode);

	void delDifferentDateByUnique(@Param("list")List<MstBondCreditRating> mstBondCreditRatingList, @Param("finprodMarketId") String finprodMarketId);

	int getMstBondCreditRatingByUnique(@Param("dto")MstBondCreditRating mstBondCreditRatingEntity);

}
