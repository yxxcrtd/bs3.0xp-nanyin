package com.joyintech.tams.settle.batch.datagather.processor.bond;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.service.StlDataGatherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 债券交易处理
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherBond3Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherBond3Processor extends StlDataGatherBondBaseProcessor {

    @Autowired
    private StlDataGatherService stlDataGatherService;

    @Override
    protected void setDealTotal(TotalTrdProductDealDto dealTotal, TrdProductDealDto trdProductDealDto) {
        // 业务类型
        dealTotal.setBusiType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        // 到期收益率
        dealTotal.setYield(trdProductDealDto.getYield());
        // 行权收益率
        dealTotal.setExerYield(trdProductDealDto.getExerYield());
        // 首期结算方式
        dealTotal.setDeliveryType("DVP");
        // 交收方式
        dealTotal.setSettlemenType(trdProductDealDto.getSettlemenType());
        // 净价总额
        dealTotal.setCpriceAmt(trdProductDealDto.getCpriceAmt());
        // 交易本金
        dealTotal.setTradeAmt(trdProductDealDto.getTradeAmt());
        // 利息总额
        dealTotal.setIntAmt(trdProductDealDto.getIntAmt());
        // 全价总额
        dealTotal.setFpriceAmt(trdProductDealDto.getFpriceAmt());
        // 申购方式
        dealTotal.setPurchModel(DictCodeListExt2.申购方式_网上申购.getValue());
    }

    @Override
    protected List<TrdTradeFeeDetail> generateTradeFeeDetail(List<TrdProductDealDto> dealList) {
        return createTradeFeeDetail(dealList,true,null);
    }
    
    @Override
    protected List<TrdDealTotalAdd> generateDealTotalAdd(List<TotalTrdProductDealDto> totleList) {
        List<TrdDealTotalAdd> totleAddList = new ArrayList<TrdDealTotalAdd>();
        totleList.forEach(x ->{
            TrdDealTotalAdd trdDealTotalAdd =  new TrdDealTotalAdd();
            // 根据前置交易编号去申购数据
            TrdDealTotalAdd trdDealTotalAddSQ = stlDataGatherService.selectTotleDealByTotleId(x.getbTradeId());
            if (trdDealTotalAddSQ != null){
                BeanUtil.copyPropertiesIgnoreNull(trdDealTotalAdd, trdDealTotalAddSQ);
            }
            BeanUtil.copyPropertiesIgnoreNull(trdDealTotalAdd, x);
            trdDealTotalAdd.setTotleTradeId(x.getTotleTradeId());
            totleAddList.add(trdDealTotalAdd);
        });
        return totleAddList;
    }

    @Override
    protected List<StlPayOrders> generateStlPayOrdersList(List<TotalTrdProductDealDto> dealTotalList, List<TrdProductDealDto> dealList, List<SettleTransDataDto> settleTransDataDtos) {
        List<StlPayOrders> payOrdersList = new ArrayList<>(dealTotalList.size());
        if(CollectionUtils.isNotEmpty(dealTotalList)) {
            for (int i = 0; i < dealTotalList.size(); i++) {
                TrdFinProductDealTotal total = dealTotalList.get(i);
                TrdProductDealDto dealDto = dealList.get(i);
                StlPayOrders stlPayOrders = createStlPayOrders(total, dealDto, settleTransDataDtos);
                // 收付方向
                stlPayOrders.setPs(DictCodeListExt1.收付方向_支出.getValue());
                // 理财产品代码
                stlPayOrders.setProdId(total.getProdId());
                payOrdersList.add(stlPayOrders);
            }
        }
        return payOrdersList;
    }

    /**
     * 可转债申购确认
     * @param settleTransDataDtoList
     * @return
     */
    @Override
    protected List<TrdProductDealDto> createDeal(List<SettleTransDataDto> settleTransDataDtoList, boolean buildTradeId) {
        SettleTransDataDto settleTransDataDto = settleTransDataDtoList.get(0);
        // 交易字段设置共通
        TrdProductDealDto trdProductDeal = createProductDeal(settleTransDataDto, buildTradeId);
        // 交易编号（共通方法已设置）
        // 业务类型（共通方法已设置）
        // 金融产品代码（共通方法已设置）
        // 金融产品类型（投管分类）（共通方法已设置）
        // 分支序号（共通方法已设置）
        // 交易日（共通方法已设置）
        // 起息日（共通方法已设置）
        // 到期日（共通方法已设置）
        // 交付日（共通方法已设置）
        // 交割日（共通方法已设置）
        // 清算速度
//        trdProductDeal.setSettleSpeed(null);
        // 交易币种（共通方法已设置）
        // 净值日期（共通方法已设置）
        // 净值日期
//        trdProductDeal.setCpriceDate(null);
        // 单位净价
        trdProductDeal.setUnitCprice(new BigDecimal(100));
        // 单位利息
        trdProductDeal.setUnitInt(BigDecimal.ZERO);
        // 单位全价
        trdProductDeal.setUnitFprice(new BigDecimal(100));
        // 单位面值
        trdProductDeal.setParValue(new BigDecimal(100));
        // 净价总额，交易份额，交易本金，利息总额，全价总额，随交易支付费用，交易金额，未随交易支付费用，总交易费用，成交编号
        calcFeeAmtPay(trdProductDeal, settleTransDataDtoList);
        // 可转债申购确认交易本金是中签券面总额，不是净价总额
        BigDecimal prinAmt = BigDecimal.ZERO;
        for (SettleTransDataDto item : settleTransDataDtoList) {
            prinAmt = ArithmeticUtil.doAdd(prinAmt, item.getShareAmt());
        }
        trdProductDeal.setPrinAmt(prinAmt.multiply(new BigDecimal(100)));
        // 交易方向
        trdProductDeal.setPs(DictCodeList.交易方向_申购确认.getValue());
        // 到期收益率和行权收益率
        stlDataGatherService.calc(trdProductDeal);
        // 投资目的（共通方法已设置）
        // 结算方式
        trdProductDeal.setSettMode(null);
        // 交易状态（共通方法已设置）
        // 是否已撤销（共通方法已设置）
        // 原交易编号（共通方法已设置）
        // 是否结清（共通方法已设置）
        // 汇总交易编号（共通方法已设置）
        // 反向交易编号（共通方法已设置）
        // 反向交易投资目的（共通方法已设置）
        // 前置交易编号
        trdProductDeal.setbTradeId(stlDataGatherService.selectBTradeId(settleTransDataDto.getApplyTradeId(), DictCodeList.交易方向_申购.getValue()));
        // 通道代码（共通方法已设置）
        // 交易对手（共通方法已设置）
        // 是否外部交易（共通方法已设置）
        // 交易场所（共通方法已设置）
        // 交易平台（共通方法已设置）
        // 交易市场（共通方法已设置）
        // 本方交易员（共通方法已设置）
        // 对手方交易员（共通方法已设置）
        // 对手方联系方式（共通方法已设置）
        // 证券管理户代码（共通方法已设置）
        // 关联系统交易编号（共通方法已设置）
        // 现金流代码（共通方法已设置）
        // 登记托管机构（共通方法已设置）
        // 备注（共通方法已设置）
        // 撤销说明（共通方法已设置）
        // 投管业务类型
        trdProductDeal.setTgBusiType(DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
        // 销售代码（共通方法已设置）
        // 计提份额（共通方法已设置）
        // 交收方式（共通方法已设置）
        // 席位号（共通方法已设置）
        // 投资组合代码（共通方法已设置）
        // 委托代码（共通方法已设置）
        // 成交编号（共通方法已设置）
        // 权益登记日（共通方法已设置）
        // 权益登记日份额（共通方法已设置）
        // 金融产品代码2
//        trdProductDeal.setFinprodId2(null);
//        // 金融产品类型2
//        trdProductDeal.setFinprodTypeExt(null);
//        // 分支序号2
//        trdProductDeal.setBranch2(null);
//        // 单位净价2
//        trdProductDeal.setUnitCprice2(null);
//        // 交易本金2
//        trdProductDeal.setPrinAmt2(null);
//        // 交易份额2
//        trdProductDeal.setShareAmt2(null);
//        // 利息总额2
//        trdProductDeal.setIntAmt2(null);
//        // 投资目的2
//        trdProductDeal.setInvAim2(null);
        return Arrays.asList(trdProductDeal);
    }
}
