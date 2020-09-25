package com.joyintech.service.valuation.tradecostmanagement.dao;


import com.joyintech.service.valuation.tradecostmanagement.dto.TradeCostManagement001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 交易费用管理 查询
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@Repository("serviceTradeCostManagement001Mapper")
public interface TradeCostManagement001Mapper {


    /**
     * 用于判断交易是否已进行预提待摊设置
     * @param tradeId 交易编号
     * @return > 0 ?
     */
    int judgeIsPrepaid(String tradeId);

    /**
     * 画面分页查询
     *
     * @param map 查询参数
     * @return
     */
    List<TradeCostManagement001Dto> queryPageList(Map<String, Object> map);


    /**
     * 删除账务明细代码表
     * @param map 账套代码 业务编号
     */
    void deleteBokDetailId(Map<String, Object> map);

    /**
     * 删除金融产品分类表
     * @param finprodId 金融产品代码
     */
    void deleteFinProductType(String finprodId);

    /**
     * 删除交易类金融产品附属表
     * @param finprodId 金融产品代码
     */
    void deleteFinTradeProductAdd(String finprodId);

    /**
     * 删除交易类金融产品表
     * @param finprodId 金融产品代码
     */
    void deleteFinTradeProduct(String finprodId);


}
