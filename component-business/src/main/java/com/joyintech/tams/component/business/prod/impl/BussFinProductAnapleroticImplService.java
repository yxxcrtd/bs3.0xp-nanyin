/*
 * 文件名：BussFinProductAnapleroticImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.component.business.prod.BussFinProductAnapleroticService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月21日
 * @see BussFinProductAnapleroticImplService
 * @since 1.0
 */
@Service
public class BussFinProductAnapleroticImplService extends BaseService<BussBenchMarkInfoDto>  implements BussFinProductAnapleroticService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductAnapleroticInfoDto> queryFinProductAnapleroticInfoById(String ptlId) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        List<BussFinProductAnapleroticInfoDto> finProductAnapleroticlist = simpleRestTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryAnapleroticInfoById",
            map,BussFinProductAnapleroticInfoDto.class);
        return finProductAnapleroticlist;
    }

}
