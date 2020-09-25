/*
 * 文件名：BusiFinBondRatingInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.service.asset.common.dao.BusiFinBondRatingInfoMapper;

/**
 * 评级信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月13日
 * @see BusiFinBondRatingInfoService
 * @since 1.0
 */
@Service
public class BusiFinBondRatingInfoService {

    @Autowired
    private BusiFinBondRatingInfoMapper busiFinBondRatingInfoMapper;
    
    /**
     * 修改/查看初始化
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<MstBondCreditRating> getmstBondCreditRatingByProductId(String finprodId){
        return busiFinBondRatingInfoMapper.getmstBondCreditRatingByProductId(finprodId);
    }
    
    /**
     * By资产ID删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public void deleteMstBondCreditRatingByProductId(String finprodId){
        busiFinBondRatingInfoMapper.deleteMstBondCreditRatingByProductId(finprodId);
    }
    
    /**
     * 修改/查看初始化
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<MstBondCreditRating> getmstBondCreditRatingByOcode(String oCode){
        return busiFinBondRatingInfoMapper.getmstBondCreditRatingByOcode(oCode);
    }

    /**
     * 删除跟主键不一样的数据
     * @param mstBondCreditRatingList
     */
	public void delDifferentDateByUnique(List<MstBondCreditRating> mstBondCreditRatingList,String finprodMarketId) {
		
		 busiFinBondRatingInfoMapper.delDifferentDateByUnique(mstBondCreditRatingList,finprodMarketId);
	}

	//查找主键+评级结果 的数据
	public int getMstBondCreditRatingByUnique(MstBondCreditRating mstBondCreditRatingEntity) {
		return busiFinBondRatingInfoMapper.getMstBondCreditRatingByUnique(mstBondCreditRatingEntity);
	}

}
