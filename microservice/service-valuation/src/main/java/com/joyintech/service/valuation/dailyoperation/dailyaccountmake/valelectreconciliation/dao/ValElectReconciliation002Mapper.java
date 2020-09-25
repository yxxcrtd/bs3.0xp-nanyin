/*
 * 文件名：ValElectReconciliation002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation002Dto;

/**
 * 电子对账Mapper 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation002Mapper
 * @since 1.0
 */
@Repository
public interface ValElectReconciliation002Mapper {

    /**
     * 电子对账页面table查询 <功能详细描述>
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ValElectReconciliation002Dto> queryPageList(Map<String, Object> params);

}
