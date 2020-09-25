/*
 * 文件名：AutoPeriodExchangeCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurationDto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 外汇远期掉期到期交易自动生成共通Service
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see AutoPeriodExchangeCommonService
 * @since 1.0
 */
@Service
public class AutoPeriodExchangeCommonService {

    /**
     * 现金流共通用的交易信息生成
     * 
     * @param entity 参数
     * @return 交易List
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> genTrdProductDealForCashFlow(TrdExchangeDurationDto entity) {
        List<WflTrdProductDeal> wflDeals = entity.getTrdExchangeDurDbInertDto().getWflTrdProductDealList();
        Map<String, List<WflTrdProductDeal>> invAimMap = wflDeals.stream().collect(
            JoyinCollectors.groupingBy(wflTrdProductDeal -> wflTrdProductDeal.getInvAim()));
        List<TrdProductDeal> deals = new ArrayList<>();
        invAimMap.forEach((s, wflTrdProductDeals) -> {
            if (wflTrdProductDeals.size() == 1) {
                TrdProductDeal deal = new TrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(deal, wflTrdProductDeals.get(0));
                deal.setPs(deal.getStatus());
                deals.add(deal);
            }
            else if (wflTrdProductDeals.size() > 1) {
                TrdProductDeal deal = new TrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(deal, wflTrdProductDeals.get(0));
                if (!BussinessDictCodeList.交易方向_收息.getValue().equals(deal.getStatus())) {
                    deal.setPs(deal.getStatus());
                }
                for (int i = 1; i < wflTrdProductDeals.size(); i++ ) {
                    deal.setPrinAmt(ArithmeticUtil.doAdd(deal.getPrinAmt(),
                        wflTrdProductDeals.get(i).getPrinAmt()));
                    deal.setIntAmt(ArithmeticUtil.doAdd(deal.getIntAmt(),
                        wflTrdProductDeals.get(i).getIntAmt()));
                    deal.setTradeAmt(ArithmeticUtil.doAdd(deal.getTradeAmt(),
                        wflTrdProductDeals.get(i).getTradeAmt()));
                    if (!BussinessDictCodeList.交易方向_收息.getValue().equals(
                        wflTrdProductDeals.get(i).getStatus())) {
                        deal.setPs(wflTrdProductDeals.get(i).getStatus());
                    }
                }
                deals.add(deal);
            }
        });
        return deals;
    }

    // /**
    // * 取得业务说明方法
    // *
    // * @return 业务说明方法
    // * @see
    // * @since 1.0
    // */
    // public CommentFunction<TrdExchangeDurDbInertDto, String> getbussinessmessage() {
    // return entity -> WorkFlowUtil.concatComment("交易编号：" + entity.getBusiCode(),
    // "货币对：" + entity.getCurPair(), "交易日：" + DateUtil.format(entity.getTradeDate()));
    // }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    public void genExchangeDataForInsertDto(TrdExchangeDurationDto entity) {
        TrdExchangeDurDbInertDto trdExchangeDurDbInertDto = entity.getTrdExchangeDurDbInertDto();
        TrdProductDeal deal = trdExchangeDurDbInertDto.getTrdProductDeal();
        TrdProductDealAdd dealAdd = trdExchangeDurDbInertDto.getTrdProductDealAdd();
        trdExchangeDurDbInertDto.setTradeDate(deal.getSettleDate());
        trdExchangeDurDbInertDto.setCurPair(dealAdd.getCurPair());
        trdExchangeDurDbInertDto.setBusiCode(deal.getTradeId());
        // trdExchangeDurDbInertDto.setMessage(getbussinessmessage());

        // 金融产品交易Entity -> 金融产品交易业务流程表
        WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, deal);
        wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        trdExchangeDurDbInertDto.setWflTrdProductDeal(wflTrdProductDeal);
        WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, dealAdd);
        wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        trdExchangeDurDbInertDto.setWflTrdProductDealAdd(wflTrdProductDealAdd);

        // 金融产品交易扩展业务流程
        WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal,
            trdExchangeDurDbInertDto.getPaTrdProductDeal());
        wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        trdExchangeDurDbInertDto.setWflPaTrdProductDeal(wflPaTrdProductDeal);

//        List<StlPayOrders> stlPayOrdersList = trdExchangeDurDbInertDto.getStlPayOrdersList();
//        List<WflStlPayOrders> list = new ArrayList<>();
//        for (StlPayOrders stlPayOrders : stlPayOrdersList) {
//            WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
//            BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, stlPayOrders);
//            wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
//            list.add(wflStlPayOrders);
//        }
//        trdExchangeDurDbInertDto.setWflStlPayOrdersList(list);
    }

    /**
     * 将一笔外汇交易转化成两笔划款指令共通用的交易
     * 
     * @param dto 参数
     * @param psDeal 交易货币交易方向
     * @param psCor 对应货币交易方向
     * @return 两个币种的两笔交易数据
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> setStlDealData(TrdProductDeal dto, String dealCcy, String psDeal,
                                                String psCor) {
        List<TrdProductDeal> resultList = new ArrayList<>();
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(dto.getTradeId());
        trdProductDeal.setFinprodType2(dto.getFinprodType2());
        trdProductDeal.setPs(psDeal);
        trdProductDeal.setTradeDate(dto.getSettleDate());
        trdProductDeal.setSettleDate(dto.getSettleDate());
        trdProductDeal.setTradeAmt(dto.getPrinAmt());
        trdProductDeal.setCcy(dealCcy);
        trdProductDeal.setCounterId(dto.getCounterId());
        trdProductDeal.setDeliveryType(dto.getDeliveryType());
        resultList.add(trdProductDeal);
        trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(dto.getTradeId());
        trdProductDeal.setFinprodType2(dto.getFinprodType2());
        trdProductDeal.setPs(psCor);
        trdProductDeal.setTradeDate(dto.getSettleDate());
        trdProductDeal.setSettleDate(dto.getSettleDate());
        trdProductDeal.setTradeAmt(dto.getTradeAmt());
        trdProductDeal.setCcy(dto.getCcy());
        trdProductDeal.setCounterId(dto.getCounterId());
        trdProductDeal.setDeliveryType(dto.getDeliveryType());
        resultList.add(trdProductDeal);
        return resultList;
    }

    /**
     * 将一笔外汇交易转化成两笔划款指令共通用的交易
     * 
     * @param dto 参数
     * @return 两个币种的两笔交易数据
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> getDealForStlOrders(TrdProductDeal dto, TrdProductDealAdd dtoAdd,
                                                     FinTradeProduct productDto) {
        List<TrdProductDeal> resultList = new ArrayList<>();
        String[] arr = dtoAdd.getCurPair().split("/");
        // 买/卖的场合
        if (StringUtil.equals(productDto.getExcPs(), DictCodeListExt2.交易方向_卖买.getValue())
            || StringUtil.equals(productDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
            if (arr[0].equals(productDto.getCcy())) {
                resultList = setStlDealData(dto, productDto.getCcy(),
                    DictCodeList.交易方向_卖出.getValue(), DictCodeList.交易方向_买入.getValue());
            }
            else {
                resultList = setStlDealData(dto, productDto.getCcy(),
                    DictCodeList.交易方向_买入.getValue(), DictCodeList.交易方向_卖出.getValue());
            }
        }
        else {
            if (arr[0].equals(productDto.getCcy())) {
                resultList = setStlDealData(dto, productDto.getCcy(),
                    DictCodeList.交易方向_买入.getValue(), DictCodeList.交易方向_卖出.getValue());
            }
            else {
                resultList = setStlDealData(dto, productDto.getCcy(),
                    DictCodeList.交易方向_卖出.getValue(), DictCodeList.交易方向_买入.getValue());
            }
        }
        return resultList;
    }

    /**
     * 为调用共通组件组装的entity
     *
     * @param entity 参数
     * @return 交易List
     */
    public List<TrdProductDeal> getTrdProductDeal(TrdExchangeDurationDto entity) {
        TrdExchangeDurDbInertDto trdExchangeDurDbInertDto = entity.getTrdExchangeDurDbInertDto();
        return getDealForStlOrders(trdExchangeDurDbInertDto.getTrdProductDeal(),
            trdExchangeDurDbInertDto.getTrdProductDealAdd(),
            trdExchangeDurDbInertDto.getFinTradeProduct());
    }
}
