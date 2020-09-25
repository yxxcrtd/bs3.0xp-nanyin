/*
 * 文件名：OccupyRelParamService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOccupyRelParam;

/**
 * 预占/实占参数信息服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月22日
 * @see OccupyRelParamService
 * @since 1.0
 */
@Service
public class OccupyRelParamService extends RedisBaseService<IOccupyRelParam> {

    public IOccupyRelParam getOccupyRelParamMap(String ocpCode, int type) {
        List<String> ocpList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(ocpCode).append(GlobalConstant.COLON).append(type);
        String field = builder.toString();
        ocpList.add(field);
        Map<String, IOccupyRelParam> map = getMapFromRedis(RedisKeyEnum.预占实占信息.key(), ocpList);
        return CollectionUtils.isEmpty(map) ? null : map.get(field);
    }
}
