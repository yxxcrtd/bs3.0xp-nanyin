
/*
 * 文件名：MstBondFirstMarketCalendar001Service.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhaodongdong
 * 修改时间：2019年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.basicdata.mstbondfirstmarketcalendar.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstbondfirstmarketcalendar.dao.MstBondFirstMarketCalendar001Mapper;
import com.joyintech.service.basicdata.mstbondfirstmarketcalendar.dto.MstBondFirstMarketCalendar001Dto;

import com.joyintech.tams.constant.DictCodeListExt1;

@Service("backMstBondFirstMarketCalendar001Service")
public class MstBondFirstMarketCalendar001Service extends BaseService<MstBondFirstMarketCalendar001Dto> {

	
	@Autowired
	 private MstBondFirstMarketCalendar001Mapper mstBondFirstMarketCalendar001Mapper;
	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<MstBondFirstMarketCalendar001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> mstBondFirstMarketCalendar001Mapper.select(pageParam));
    }
    /**
     * 获取finprodId
     * @param finprodId
     * @return
     */
    public String getFinprodId(String finprodId){
        return mstBondFirstMarketCalendar001Mapper.getFinprodId(finprodId);
    }
}
