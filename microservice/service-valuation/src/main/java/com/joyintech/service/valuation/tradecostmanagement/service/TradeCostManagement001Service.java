package com.joyintech.service.valuation.tradecostmanagement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.tradecostmanagement.dao.TradeCostManagement001Mapper;
import com.joyintech.service.valuation.tradecostmanagement.dto.TradeCostManagement001Dto;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易费用管理 查询 删除
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@Service("backTradeCostManagement001Service")
public class TradeCostManagement001Service extends BaseService<TradeCostManagement001Dto> {


    @Autowired
    private TradeCostManagement001Mapper tradeCostManagement001Mapper;

    // 接口共通
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 判断是否已进行预提待摊设置
     *
     * @param tradeId 交易编号
     * @return 查询结果> 0 返回 true , 否则 false
     */
    public boolean judgeIsPrepaid(String tradeId) {
        int i = tradeCostManagement001Mapper.judgeIsPrepaid(tradeId);
        return i > 0;
    }

    /**
     * 分页查询
     *
     * @param page   页数
     * @param length 长度
     * @param param  查询参数
     * @return 交易费用分页数据
     */
    public Page<TradeCostManagement001Dto> queryPageList(int page, int length, Map<String, Object> param)
            throws Exception {
        //  一次性费用	佣金 账户维护费 其他费用 交易手续费 结算手续费
        List<String> feeType0 = new ArrayList<String>();
        feeType0.add(DictCodeListExt1.一次性费用类型_佣金.getValue());
        feeType0.add(DictCodeListExt1.一次性费用类型_账户维护费.getValue());
        feeType0.add(DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        feeType0.add(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        feeType0.add(DictCodeListExt1.一次性费用类型_其他费用.getValue());
        param.put("feeType0", feeType0);
        param.put("tradeStatus0", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("ps0", DictCodeList.交易方向_付费.getValue());
        return super.queryForPage(page, length, () -> tradeCostManagement001Mapper.queryPageList(param));

    }


    /**
     * 批量删除交易
     *
     * @param rows 选中数据行
     */
    public Integer delete(List<TradeCostManagement001Dto> rows) {

        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
        for (TradeCostManagement001Dto row : rows) {
            String tradeId = row.getTradeId();
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(tradeId);
            trdProductDealList.add(trdProductDeal);
            if (judgeIsPrepaid(tradeId)) {// 是待摊 删除相关表
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("booksetId", row.getBookSetId());
                map.put("busiId", tradeId);
                tradeCostManagement001Mapper.deleteBokDetailId(map);// 账务明细代码表
                tradeCostManagement001Mapper.deleteFinProductType(tradeId);// 金融产品分类表
                tradeCostManagement001Mapper.deleteFinTradeProduct(tradeId);// 交易类金融产品表
                tradeCostManagement001Mapper.deleteFinTradeProductAdd(tradeId);// 交易类金融产品附属表
            }
        }

        // 调用共通批量删除交易 (此共通方法原定义是私有的private)
        interfCommonService.deleteFinprodDealByTradeId(trdProductDealList);
        return 1;

    }

    /**
     * 批量删除交易
     *
     * @param row 选中数据行
     */
    public Integer delete(TradeCostManagement001Dto row) {

        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
            String tradeId = row.getTradeId();
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(tradeId);
            trdProductDealList.add(trdProductDeal);
            if (judgeIsPrepaid(tradeId)) {// 是待摊 删除相关表
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("booksetId", row.getBookSetId());
                map.put("busiId", tradeId);
                tradeCostManagement001Mapper.deleteBokDetailId(map);// 账务明细代码表
                tradeCostManagement001Mapper.deleteFinProductType(tradeId);// 金融产品分类表
                tradeCostManagement001Mapper.deleteFinTradeProduct(tradeId);// 交易类金融产品表
                tradeCostManagement001Mapper.deleteFinTradeProductAdd(tradeId);// 交易类金融产品附属表
            }

        // 调用共通批量删除交易 (此共通方法原定义是私有的private)
        interfCommonService.deleteFinprodDealByTradeId(trdProductDealList);
        return 1;

    }

}
