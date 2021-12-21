package com.joyintech.tams.settle.batch.transdatagather.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalAdd;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalStatus;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.settle.batch.transdatagather.dto.StlSettleTransDataGatherDto;
import com.joyintech.tams.settle.batch.transdatagather.dto.StlSettleTransDataGatherParamDto;
import com.joyintech.tams.settle.batch.transdatagather.service.StlSettleTransDataGatherService;
import com.joyintech.tams.settle.common.service.SettleCommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author meifaxin
 * @date 2021/10/29
 * @see StlSettleTransDataGatherReader
 * @since 1.0
 */
@Service
@Lazy
public class StlSettleTransDataGatherReader extends BaseReader<StlSettleTransDataGatherParamDto> {
    @Autowired
    private StlSettleTransDataGatherService stlSettleTransDataGatherService;
    @Autowired(required = false)
    private SettleCommonService settleCommonService;

    @Override
    public List<StlSettleTransDataGatherParamDto> createList() {
        // 批处理执行信息
        SysBatchExecInfo execInfo = getBatchExecuteInfo();
        // 日期
        Date tradeDate = execInfo.getExecuteDate();
        //1、删除
        stlSettleTransDataGatherService.deleteTotalTradeAddByLastDeal(tradeDate);
        stlSettleTransDataGatherService.deleteTotalTradeByLastDeal(tradeDate);
        stlSettleTransDataGatherService.deleteTotalTradeStatusByLastDeal();
        //2、查询对账一致或者不需要对账的成交数据明细表数据
       List<StlSettleTransDataGatherDto> selectNeedTotalDataList = stlSettleTransDataGatherService.selectNeedTotalDataList(tradeDate);
        //3、查询得到的数据list按照 席位号 + 申请编号 的维度进行分组 得到List<Map<席位号 + 申请编号,StlSettleTransDataGatherDto>>
        Map<String,List<StlSettleTransDataGatherDto>> mapList=selectNeedTotalDataList
                .stream()
                .collect(Collectors.groupingBy(dto ->
                        dto.getApplyTradeId() + "_" + dto.getSeatNumber()));
        //4、循环当前循环的map的value
        List<SettleTransDataTotal> settleTransDataList=new ArrayList<>();
        List<SettleTransDataTotalAdd> settleTransDataAddList=new ArrayList<>();
        mapList.forEach((key, list) -> {
            SettleTransDataTotal dealTotal=new SettleTransDataTotal();
            StlSettleTransDataGatherDto settleTransDataDto=list.get(0);
            if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                    || StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
                    || (StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                        &&StringUtil.equals(settleTransDataDto.getPs(), DictCodeListExt1.交易方向_债转股.getValue()))){
                for (StlSettleTransDataGatherDto tempData : list) {
                    String checkResult = tempData.getCheckResult();
                    if (StringUtil.isEmpty(checkResult)
                            || StringUtil.equals(checkResult, DictCodeListExt3.对账状态结果_一致.getValue())
                            || StringUtil.equals(checkResult, DictCodeListExt3.对账状态结果_手工完成.getValue())) {
                        dealTotal = new SettleTransDataTotal();
                        BeanUtils.copyProperties(tempData, dealTotal);
                        //  汇总交易编号
                        dealTotal.setSeqId(IDGenerator.generate());
                        // 回购需要将成交明细表的成交编号登到交易汇总表的原始成交编号 fuyongsheng add 20211215 start
                        if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                                || StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
                            dealTotal.setTradeIdRaw(tempData.getCjbh());
                        }
                        // 回购需要将成交明细表的成交编号登到交易汇总表的原始成交编号 fuyongsheng add 20211215 end
                        settleTransDataList.add(dealTotal);
                        //当笔成交数据明细.附属表流水号 非空
                        if(StringUtil.isNotEmpty(tempData.getTradeIdAdd())){
                            SettleTransDataTotalAdd settleTransDataTotalAdd = new SettleTransDataTotalAdd();
                            BeanUtils.copyProperties(tempData, settleTransDataTotalAdd);
                            settleTransDataTotalAdd.setTradeId(dealTotal.getSeqId());
                            settleTransDataAddList.add(settleTransDataTotalAdd);
                        }
                    }
                }
            }else {
                //对账结果核对if 变量.temp_当前成交数据明细数据list 中所有数据都满足【对账结果 为空 或者为 对账一致 或者为 手工完成】
                boolean flag=true;
                for (StlSettleTransDataGatherDto item : list) {
                    String checkResult=item.getCheckResult();
                    if(!(StringUtil.isEmpty(checkResult)
                            || StringUtil.equals(checkResult, DictCodeListExt3.对账状态结果_一致.getValue())
                            || StringUtil.equals(checkResult, DictCodeListExt3.对账状态结果_手工完成.getValue())
                    )){
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    //  汇总交易编号
                    dealTotal.setSeqId(IDGenerator.generate());
                    //执行日期
                    dealTotal.setDealDate(tradeDate);
                    //证券品种
                    dealTotal.setSecType(settleTransDataDto.getSecType());
                    // 金融产品代码
                    dealTotal.setFinprodId(settleTransDataDto.getFinprodId());
                    // 金融产品类型（估值核算）
                    dealTotal.setFinprodType(settleTransDataDto.getFinprodType());
                    // 金融产品类型（投管分类）
                    dealTotal.setFinprodType2(settleTransDataDto.getFinprodType2());
                    // 分支序号
                    dealTotal.setBranch(settleTransDataDto.getBranch());
                    // 业务类型
                    dealTotal.setBusiType(settleTransDataDto.getBusiType());
                    // 交易日
                    dealTotal.setTradeDate(settleTransDataDto.getTradeDate());
                    // 交割日
                    dealTotal.setSettleDate(settleTransDataDto.getSettleDate());
                    // 起息日
                    dealTotal.setVdate(settleTransDataDto.getVdate());
                    // 到期日
                    dealTotal.setMdate(settleTransDataDto.getMdate());
                    //首期交付日
                    dealTotal.setVpayDate(settleTransDataDto.getVpayDate());
                    //到期交付日
                    dealTotal.setMpayDate(settleTransDataDto.getMpayDate());
                    //期限品种
                    dealTotal.setTermType(settleTransDataDto.getTermType());
                    //清算速度
                    dealTotal.setSettleSpeed(settleTransDataDto.getSettleSpeed());
                    // 交易币种
                    dealTotal.setCcy(settleTransDataDto.getCcy());
                    //单位净价
                    BigDecimal unitCprice=BigDecimal.ZERO;
                    //单位利息
                    BigDecimal unitInt=BigDecimal.ZERO;
                    //单位全价
                    BigDecimal unitFprice=BigDecimal.ZERO;
                    //净价总额
                    BigDecimal totalCpriceAmt=BigDecimal.ZERO;
                    //份额
                    BigDecimal totalShareAmt=BigDecimal.ZERO;
                    //利息总额
                    BigDecimal totalIntAmt=BigDecimal.ZERO;
                    //全价总额
                    BigDecimal totalFpriceamt=BigDecimal.ZERO;
                    //交易本金
                    BigDecimal prinAmt=BigDecimal.ZERO;
                    //总印花税
                    BigDecimal totalStampFee=BigDecimal.ZERO;
                    //总经手费
                    BigDecimal totalJsfAmt=BigDecimal.ZERO;
                    //总过户费
                    BigDecimal totalGhfAmt=BigDecimal.ZERO;
                    //总证管费
                    BigDecimal totalZgfAmt=BigDecimal.ZERO;
                    //总手续费
                    BigDecimal totalSxfAmt=BigDecimal.ZERO;
                    //总结算费
                    BigDecimal totalSettleFee=BigDecimal.ZERO;
                    //佣金
                    BigDecimal totalCommissionAmt=BigDecimal.ZERO;
                    //总交易金额
                    BigDecimal totalTradeAmt=BigDecimal.ZERO;
                    //总到期本金
                    BigDecimal totalmPrinAmt=BigDecimal.ZERO;
                    //总到期利息
                    BigDecimal totalmIntAmt=BigDecimal.ZERO;
                    //总到期金额
                    BigDecimal totalmTradeAmt=BigDecimal.ZERO;
                    //总印花税_原始
                    BigDecimal totalStampFeeRaw=BigDecimal.ZERO;
                    //总过户费_原始
                    BigDecimal totalJsfAmtRaw=BigDecimal.ZERO;
                    //总证管费_原始
                    BigDecimal totalZgfAmtRaw=BigDecimal.ZERO;
                    //总经手费_原始
                    BigDecimal totalSxfAmtRaw=BigDecimal.ZERO;
                    //总结算费_原始
                    BigDecimal totalSettleFeeRaw=BigDecimal.ZERO;
                    //总佣金_原始
                    BigDecimal totalCommissionAmtRaw=BigDecimal.ZERO;
                    //交易金额_原始
                    BigDecimal totaltradeAmtRaw=BigDecimal.ZERO;
                    for (StlSettleTransDataGatherDto item : list) {
                        totalCpriceAmt = ArithmeticUtil.doAdd(totalCpriceAmt, item.getCpriceAmt());
                        totalShareAmt = ArithmeticUtil.doAdd(totalShareAmt, item.getShareAmt());
                        totalIntAmt = ArithmeticUtil.doAdd(totalIntAmt, item.getIntAmt());
                        totalFpriceamt = ArithmeticUtil.doAdd(totalFpriceamt, item.getFpriceAmt());
                        totalStampFee= ArithmeticUtil.doAdd(totalStampFee, item.getStampFee());
                        totalJsfAmt= ArithmeticUtil.doAdd(totalJsfAmt, item.getJsfAmt());
                        totalGhfAmt= ArithmeticUtil.doAdd(totalGhfAmt, item.getGhfAmt());
                        totalZgfAmt= ArithmeticUtil.doAdd(totalZgfAmt, item.getZgfAmt());
                        totalSxfAmt= ArithmeticUtil.doAdd(totalSxfAmt, item.getSxfAmt());
                        totalSettleFee= ArithmeticUtil.doAdd(totalSettleFee, item.getSettleFee());
                        totalTradeAmt= ArithmeticUtil.doAdd(totalTradeAmt, item.getTradeAmt());
                        totalmPrinAmt= ArithmeticUtil.doAdd(totalmPrinAmt, item.getmPrinAmt());
                        totalmIntAmt= ArithmeticUtil.doAdd(totalmIntAmt, item.getmIntAmt());
                        totalmTradeAmt= ArithmeticUtil.doAdd(totalmTradeAmt, item.getmTradeAmt());
                        // ZGZGST-4156:计算佣金汇总 fuyongsheng 20211208 add start
                        totalCommissionAmt = ArithmeticUtil.doAdd(totalCommissionAmt, item.getCommissionAmt());
                        // ZGZGST-4156:计算佣金汇总 fuyongsheng 20211208 add end
                        totalStampFeeRaw=ArithmeticUtil.doAdd(totalStampFeeRaw, item.getStampFeeRaw());
                        totalJsfAmtRaw=ArithmeticUtil.doAdd(totalJsfAmtRaw, item.getJsfAmtRaw());
                        totalZgfAmtRaw=ArithmeticUtil.doAdd(totalZgfAmtRaw, item.getZgfAmtRaw());
                        totalSxfAmtRaw=ArithmeticUtil.doAdd(totalSxfAmtRaw, item.getSxfAmtRaw());
                        totalSettleFeeRaw=ArithmeticUtil.doAdd(totalSettleFeeRaw, item.getSettleFeeRaw());
                        // ZGZGST-4156:计算佣金汇总 fuyongsheng 20211208 modify start
                        // totalCommissionAmtRaw=ArithmeticUtil.doAdd(totalCommissionAmtRaw, item.getCommissionAmt());
                        totalCommissionAmtRaw=ArithmeticUtil.doAdd(totalCommissionAmtRaw, item.getCommissionAmtRaw());
                        // ZGZGST-4156:计算佣金汇总 fuyongsheng 20211208 modify end
                        totaltradeAmtRaw=ArithmeticUtil.doAdd(totaltradeAmtRaw, item.getTradeAmtRaw());
                    }
                    if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                            && (StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())
                            || StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_行权回售.getValue()))){
                        unitCprice = ArithmeticUtil.divide(totalCpriceAmt, totalShareAmt, 8);
                        dealTotal.setUnitCprice(unitCprice);
                        unitInt=settleTransDataDto.getUnitInt();
                        dealTotal.setUnitInt(unitInt);
                        dealTotal.setUnitFprice(ArithmeticUtil.doAdd(unitCprice,unitInt));
                        dealTotal.setCpriceAmt(totalCpriceAmt);
                        prinAmt=totalCpriceAmt;
                    }else  if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                            && (StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_申购.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_申购确认.getValue()))){
                        dealTotal.setUnitCprice(new BigDecimal(100));
                        dealTotal.setUnitFprice(new BigDecimal(100));
                        dealTotal.setCpriceAmt(totalCpriceAmt);
                        prinAmt=totalCpriceAmt;
                    }else  if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())
                            && (StringUtil.equals(settleTransDataDto.getPs(), DictCodeListExt2.交易方向_新股中签.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), DictCodeListExt2.交易方向_增发.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), DictCodeListExt2.交易方向_配股.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue()))){
                        unitCprice = ArithmeticUtil.divide(totalFpriceamt, totalShareAmt, 8);
                        dealTotal.setUnitCprice(unitCprice);
                        dealTotal.setCpriceAmt(totalFpriceamt);
                        prinAmt=totalFpriceamt;
                    }else  if(StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                            && (StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())
                            ||StringUtil.equals(settleTransDataDto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue()))) {
                        unitCprice = ArithmeticUtil.divide(totalFpriceamt, totalShareAmt, 8);
                        dealTotal.setUnitCprice(unitCprice);
                        dealTotal.setUnitFprice(unitCprice);
                    }
                    //单位面值  空
                    // 交易份额
                    dealTotal.setShareAmt(totalShareAmt);
                    // 交易本金=净价总额
                    if(prinAmt.compareTo(BigDecimal.ONE)!=0){
                        dealTotal.setPrinAmt(prinAmt);
                    }
                    // 利息总额
                    dealTotal.setIntAmt(totalIntAmt);
                    // 全价总额
                    dealTotal.setFpriceAmt(totalFpriceamt);
                    //印花税
                    dealTotal.setStampFee(totalStampFee);
                    //经手费
                    dealTotal.setJsfAmt(totalJsfAmt);
                    //过户费
                    dealTotal.setGhfAmt(totalGhfAmt);
                    //证管费
                    dealTotal.setZgfAmt(totalZgfAmt);
                    //手续费
                    dealTotal.setSxfAmt(totalSxfAmt);
                    //结算费
                    dealTotal.setJsfAmt(totalJsfAmt);
                    //佣金
                    dealTotal.setCommissionAmt(totalCommissionAmt);
                    //佣金是否随交易支付
                    dealTotal.setIsPayWithTrade(settleTransDataDto.getIsPayWithTrade());
                    // 交易金额
                    dealTotal.setTradeAmt(totalTradeAmt);
                    // 交易场所
                    dealTotal.setTradeMarket(settleTransDataDto.getTradeMarket());
                    //交易市场
                    dealTotal.setTradeMarketMode(settleTransDataDto.getTradeMarketMode());
                    //交易平台
                    dealTotal.setTradePlat(settleTransDataDto.getTradePlat());
                    //席位号
                    dealTotal.setSeatNumber(settleTransDataDto.getSeatNumber());
                    //组合代码
                    //证券账户
                    dealTotal.setSecAcctId(settleTransDataDto.getSecAcctId());
                    //资金账户
                    dealTotal.setAcctNo(settleTransDataDto.getAcctNo());
                    //批次号
                    dealTotal.setBatchNumber(settleTransDataDto.getBatchNumber());
                    //到期本金
                    dealTotal.setmPrinAmt(totalmPrinAmt);
                    //到期利息
                    dealTotal.setmIntAmt(totalmIntAmt);
                    //到期金额
                    dealTotal.setmIntAmt(totalmTradeAmt);
                    //利率 空
                    //计息基础
                    dealTotal.setBasis(settleTransDataDto.getBasis());
                    //业务场景分类
                    dealTotal.setBusiSceneCalss(settleTransDataDto.getBusiSceneCalss());
                    //交收方式
                    dealTotal.setSettleMode(settleTransDataDto.getSettleMode());
                    //印花税_原始
                    dealTotal.setStampFeeRaw(totalStampFeeRaw);
                    //过户费_原始
                    dealTotal.setJsfAmtRaw(totalJsfAmtRaw);
                    //证管费_原始
                    dealTotal.setZgfAmtRaw(totalZgfAmtRaw);
                    //经手费_原始
                    dealTotal.setSxfAmtRaw(totalSxfAmtRaw);
                    //结算费_原始
                    dealTotal.setSettleFeeRaw(totalSettleFeeRaw);
                    //佣金_原始
                    dealTotal.setCommissionAmtRaw(totalCommissionAmtRaw);
                    //交易金额_原始
                    dealTotal.setTradeAmtRaw(totaltradeAmtRaw);
                    //成交编号_原始
                    dealTotal.setTradeIdRaw(settleTransDataDto.getTradeIdRaw());
                    //临时表流水号
                    dealTotal.setSeqNo(settleTransDataDto.getSeqNo());
                    //是否优先配售
                    dealTotal.setIsPurchTypeYx(settleTransDataDto.getIsPurchTypeYx());
                    dealTotal.setApplyTradeId(settleTransDataDto.getApplyTradeId());
                    dealTotal.setPs(settleTransDataDto.getPs());
                    settleTransDataList.add(dealTotal);
                }
             }

        });
        StlSettleTransDataGatherParamDto dataGatherParamDto = new StlSettleTransDataGatherParamDto(settleTransDataList,settleTransDataAddList);
        List<StlSettleTransDataGatherParamDto> returnList=new ArrayList<>();
        returnList.add(dataGatherParamDto);
        return returnList;
    }
}
