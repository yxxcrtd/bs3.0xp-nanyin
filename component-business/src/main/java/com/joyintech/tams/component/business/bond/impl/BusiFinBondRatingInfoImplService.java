/*
 * 文件名：BusiFinBondRatingInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.bond.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.bond.BusiFinBondRatingInfoService;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月13日
 * @see BusiFinBondRatingInfoImplService
 * @since 1.0
 */
@Service
public class BusiFinBondRatingInfoImplService extends BaseService<BusiFinBondRatingInfoDto> implements BusiFinBondRatingInfoService{


    /**
     * {@inheritDoc}
     */
    @Override
    public List<MstBondCreditRating> getmstBondCreditRatingByProductId(Map<String, Object> param) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BusiFinBondRatingInfo/getmstBondCreditRatingByProductId?finprodId="
            + param.get("finprodId").toString(),
            MstBondCreditRating.class);
    }

	@Override
	public List<MstBondCreditRating> getmstBondCreditRatingByOcode(Map<String, Object> param) {
		return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
	            + "/BusiFinBondRatingInfo/getmstBondCreditRatingByOcode?oCode="
	            + param.get("oCode").toString(),
	            MstBondCreditRating.class);
	}
    
}
