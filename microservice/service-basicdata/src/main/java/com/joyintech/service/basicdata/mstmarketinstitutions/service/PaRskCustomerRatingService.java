/*
 * 文件名：PaRskCustomerRatingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstmarketinstitutions.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstmarketinstitutions.dao.MstMarketInstitutionsMapper;
import com.joyintech.service.basicdata.mstmarketinstitutions.dto.PaRskCustomerRatingDto;
/**
 * 
 * 客户评级
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see PaRskCustomerRatingService
 * @since 1.0
 */
@Service("backPaRskCustomerRatingService")
public class PaRskCustomerRatingService extends BaseService<PaRskCustomerRatingDto>{
    
    /**
     * 市场机构mapper注入
     */
    @Autowired
    private MstMarketInstitutionsMapper mstMarketInstitutionsMapper;
    /**
     * 
     * 编辑查询页面客户评级表查询分页
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page<PaRskCustomerRating>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<PaRskCustomerRatingDto> selectPaRskCustomerPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
       /* return super.queryForPage(
            () -> mstMarketInstitutionsMapper.selectPaRskCustomerPage(pageParam));*/
        return super.queryForPage(page,length,() -> mstMarketInstitutionsMapper.selectMstBondCreditPage(pageParam));
    }
}
