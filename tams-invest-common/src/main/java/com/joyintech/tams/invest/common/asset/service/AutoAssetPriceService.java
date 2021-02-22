package com.joyintech.tams.invest.common.asset.service;


import java.util.Map;

public interface AutoAssetPriceService {

    /**
     * 根据金融产品代码判断是否需要自动从接口表向业务表生成行情数据
     * @param map
     * @return
     */
    boolean checkIsNeedAutoPrice(Map<String, Object> map);


    /**
     * 自动处理    从接口表向业务表生成行情数据
     */
    void autoProcess(Map<String, Object> map);


}
