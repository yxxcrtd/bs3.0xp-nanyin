/*
 * 文件名：SelectLimitAmtService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.entrust.common.dao.SelectLimitAmtMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月23日
 * @see SelectLimitAmtService
 * @since 1.0
 */
@Service
public class SelectLimitAmtService {

    @Autowired
    private SelectLimitAmtMapper selectLimitAmtMapper;
    /**
     * 
     * 查询授权额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectLimitAmt(Map<String, Object> map){
        map.put("right", DictCodeList.授权类型_授权.getValue());
        map.put("maxright", DictCodeList.授权类型_无限授权.getValue());
        map.put("rolepos",DictCodeList.角色岗位_投资经理.getValue());
        return selectLimitAmtMapper.selectLimitAmt(map);
    }
}
