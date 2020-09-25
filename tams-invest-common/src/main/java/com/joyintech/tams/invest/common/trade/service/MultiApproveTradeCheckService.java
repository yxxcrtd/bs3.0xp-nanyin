/*
 * 文件名：RateAdjService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.trade.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.trade.dao.MultiApproveTradeCheckMapper;

/**
 *  交易及存续期交易，保存进待办，点批量审批时检查共通service
 * 〈功能详细描述〉
 * @author chenzhuo
 * @version 1.0 2019年4月2日
 * @see MultiApproveTradeCheckService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class MultiApproveTradeCheckService extends BaseService{

    @Autowired
    private MultiApproveTradeCheckMapper mapper;

    /**
     * 交易及存续期交易，保存进待办，点批量审批时候需要报错。
     * <功能详细描述>
     * @param entity
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public boolean multiApproveTradeCheck(String tradeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tradeId", tradeId);
        List<String> tradeIdList = mapper.getWorkflowTradeId(map);
        if (tradeIdList != null && tradeIdList.size() > 0) {
            return true;
        }
        return false;
    }
}
