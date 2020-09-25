/*
 * 文件名：TradeOrClearController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.trade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.controller.BaseController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.trade.dto.TradeClearDto;

/**
 * 交易端清算端Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月22日
 * @see TradeOrClearController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class TradeOrClearController extends BaseController<TradeClearDto> {

    /**
     * 调用模块
     */
    private static final String CALLER_TRADE = "caller";

    /**
     * 产品成立同步
     * 
     * @param param 请求参数
     * @return List<String> 成立产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/trusterOrder")
    public List<TradeClearDto> trusterOrder(@RequestBody Map<String, Object> param) {
        // 取得产品列表
        List<TradeClearDto> tradeList = getDataList(param);
        setSysDate((String)param.get(InterfConstant.SYS_DATE));
        setControllerInfo(CALLER_TRADE, param.get(InterfConstant.CALL_MODEL));
        // 调用接口
        excuteService(tradeList, true);

        return tradeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(TradeClearDto dto, SendEsbService<?, ?> service) {
        dto.setResult(service.isHandleOK());
        dto.setErrMsg(service.getErrMsg());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(TradeClearDto t) {
        return ServiceMapperUtil.getTradeService((String)getControllerInfo(CALLER_TRADE));
    }

    /**
     * 获取 交易编号指令编号列表
     * 
     * @param param 请求参数
     * @return 交易编号指令编号列表
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<TradeClearDto> getDataList(Map<String, Object> param) {
        List<TradeClearDto> resultList = new ArrayList<>();
        if (param != null) {
            for (Map<String, Object> obj : (List<Map<String, Object>>)param.get(
                InterfConstant.DATA_LIST)) {
                resultList.add(BeanUtil.mapToObject(obj, TradeClearDto.class));
            }
        }
        return resultList;
    }
}
