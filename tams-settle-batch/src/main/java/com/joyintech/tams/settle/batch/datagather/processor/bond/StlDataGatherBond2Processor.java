package com.joyintech.tams.settle.batch.datagather.processor.bond;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.service.StlDataDealProcessCommonService;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 债券交易处理
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherBond2Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherBond2Processor extends StlDataGatherBondBaseProcessor {

    @Autowired
    protected StlDataDealProcessCommonService stlDataDealProcessCommonService;

    @Override
    protected void setDealTotal(TotalTrdProductDealDto dealTotal, TrdProductDealDto trdProductDealDto) {
        // 业务类型
        dealTotal.setBusiType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SHJYS_CALID);
        Date nextWorkDate = stlDataDealProcessCommonService.getNextWorkDay(trdProductDealDto.getTradeDate(), calendarId);
        // 预计申购确认日
        dealTotal.setPurCfmDate(nextWorkDate);
        // 预计申购缴款日
        dealTotal.setPurPayDate(stlDataDealProcessCommonService.getNextWorkDay(nextWorkDate, calendarId));
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
        // 申购类型
        dealTotal.setPurchType(DictCodeListExt2.申购类型_普通申购.getValue());
        if (StringUtil.equals(trdProductDealDto.getIsPurchTypeYx(), DictCodeList.是否_是.getValue())) {
        	dealTotal.setPurchType(DictCodeListExt2.申购类型_普通申购.getValue());
        }
        // 交易本金
        dealTotal.setPrinAmt(trdProductDealDto.getShareAmt().multiply(new BigDecimal(100)));
    }

    @Override
    protected List<TrdTradeFeeDetail> generateTradeFeeDetail(List<TrdProductDealDto> dealList) {
        return null;
    }

    @Override
    protected List<StlPayOrders> generateStlPayOrdersList(List<TotalTrdProductDealDto> dealTotalList, List<TrdProductDealDto> dealList, List<SettleTransDataDto> settleTransDataDtos) {
        return null;
    }
    
    @Override
    protected List<TrdDealTotalAdd> generateDealTotalAdd(List<TotalTrdProductDealDto> totleList) {
        List<TrdDealTotalAdd> totleAddList = new ArrayList<TrdDealTotalAdd>();
        totleList.forEach(x ->{
            TrdDealTotalAdd trdDealTotalAdd =  new TrdDealTotalAdd();
            BeanUtil.copyPropertiesIgnoreNull(trdDealTotalAdd, x);
            trdDealTotalAdd.setTotleTradeId(x.getTotleTradeId());
            trdDealTotalAdd.setPurchModle(DictCodeListExt2.申购方式_网上申购.getValue());
            trdDealTotalAdd.setPurchDate(x.getTradeDate());
            trdDealTotalAdd.setPurAmt(x.getPrinAmt());
            totleAddList.add(trdDealTotalAdd);
        });
        return totleAddList;
    }

    /**
     * 可转债申购
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
        // 净价总额，交易份额，交易本金，利息总额，全价总额，随交易支付费用，交易金额，未随交易支付费用，总交易费用
        calcFeeAmtPay(trdProductDeal, settleTransDataDtoList);
        // 交易方向
        // trdProductDeal.setPs(DictCodeListExt1.交易方向_保证金划款.getValue());
        trdProductDeal.setPs(DictCodeList.交易方向_申购.getValue());
        // 到期收益率
//        trdProductDeal.setYield(null);
//        // 行权收益率
//        trdProductDeal.setExerYield(null);
//        // 投资目的（共通方法已设置）
//        // 结算方式
        trdProductDeal.setSettMode(null);
        // 交易状态（共通方法已设置）
        // 是否已撤销（共通方法已设置）
        // 原交易编号（共通方法已设置）
        // 是否结清（共通方法已设置）
        // 汇总交易编号（共通方法已设置）
        // 反向交易编号（共通方法已设置）
        // 反向交易投资目的（共通方法已设置）
        // 前置交易编号
//        trdProductDeal.setbTradeId(null);
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
        trdProductDeal.setTgBusiType(DictCodeListExt2.平安业务类型_保证金划款.getValue());
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
