/*
 * 文件名：BusiFinBondRatingInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.bond.BusiFinBondRatingInfoService;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.component.business.project.BusiFinProjectRatingInfoService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *  项目类评级信息
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2020年11月17日
 * @see BusiFinProjectRatingInfoImplService
 * @since 1.0
 */
@Service
public class BusiFinProjectRatingInfoImplService extends BaseService<BusiFinBondRatingInfoDto> implements BusiFinProjectRatingInfoService {


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
