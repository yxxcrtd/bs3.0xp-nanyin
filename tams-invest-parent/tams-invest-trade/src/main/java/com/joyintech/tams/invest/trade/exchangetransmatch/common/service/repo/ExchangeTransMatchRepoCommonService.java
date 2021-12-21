package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.repo;

import com.joyintech.background.component.common.pack.common.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.TrdProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeTransMatchRepoCommonService extends BaseService {
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;



    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        //生成金融产品交易信息
        baseTradeProductDealDto.setTrdProductDeal(this.genTrdProductDeal(dealDto,entrustDto,baseTradeProductDealDto.getTrdFinProductDealTotal()));
        //生成金融产品交易附属表信息
        baseTradeProductDealDto.setTrdProductDealAdd(this.genTrdProductDealAdd(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成组合交易表数据
        baseTradeProductDealDto.setPtlTrade(this.generatePtlTrade(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成费用信息list
        baseTradeProductDealDto.setTrdTradeFeeDetailList(this.genTrdTradeFeeDetailList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成金融产品交易扩展
        baseTradeProductDealDto.setPaTrdProductDeal(this.genPaTrdProductDeal(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成划款指令
        baseTradeProductDealDto.setStlPayOrdersList(this.generateStlPayOrdersList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));

        return baseTradeProductDealDto;
    }
    /**
     * 生成金融产品交易明细
     */

    public TrdProductDeal genTrdProductDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
        return createDeal(dealDto,entrustDto,trdFinProductDealTotal);
    }
    /**
     * 生成交易信息
     *
     * @param dealDto
     * @param entrustDto
     * @return
     */
    protected TrdProductDeal createDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdFinProductDealTotal trdFinProductDealTotal) {
        return null;
    }
    /**
     * 生成金融产品交易明细
     */
    protected TrdProductDealDto createProductDeal(ExchangeTransMatchCommonDto settleTransData) {
        TrdProductDealDto trdProductDeal = new TrdProductDealDto();
        // 交易编号，按现有的交易编号生成规则，自动生成
        if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), settleTransData.getFinprodType2())) {
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_正回购录入);
            trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
        }   else {
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_逆回购录入);
            trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
        }

        // 业务类型
        trdProductDeal.setBusiType(null);
        // 金融产品类型（投管分类）
        trdProductDeal.setFinprodType2(settleTransData.getFinprodType2());
        // 金融产品类型
        trdProductDeal.setFinprodType(settleTransData.getFinprodType());
        // 分支序号
        trdProductDeal.setBranch(0L);
        // 交易日
        trdProductDeal.setTradeDate(settleTransData.getTradeDate());
        // 起息日
        trdProductDeal.setVdate(settleTransData.getVdate());
        // 到期日
        trdProductDeal.setMdate(settleTransData.getMdate());
        // 交付日
        trdProductDeal.setPayDate(settleTransData.getMpayDate());
        // 交割日
        trdProductDeal.setSettleDate(settleTransData.getSettleDate());
        // 清算速度
        trdProductDeal.setSettleSpeed(settleTransData.getSettleSpeed());
        // 交易币种
        trdProductDeal.setCcy(settleTransData.getCcy());
        // 净值日期
        trdProductDeal.setCpriceDate(null);
        // 单位净价
        trdProductDeal.setUnitCprice(null);
        // 单位利息
        trdProductDeal.setUnitInt(null);
        // 单位全价
        trdProductDeal.setUnitFprice(null);
        // 单位面值
        trdProductDeal.setParValue(null);
        // 净价总额
        trdProductDeal.setCpriceAmt(null);
        // 投资目的
        trdProductDeal.setInvAim(null);
        // 交易方向
        trdProductDeal.setPs(settleTransData.getPs());
        // 交易状态
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 是否已撤销
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 原交易编号
        trdProductDeal.setpTradeId(null);
        // 是否结清
        trdProductDeal.setIsClean(null);
        // 汇总交易编号（父类中设置）
        // 反向交易编号
        trdProductDeal.setrTradeId(null);
        // 通道代码
        trdProductDeal.setChlId(null);
        // 交易对手
        trdProductDeal.setCounterId(null);
        // 是否外部交易
        trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        trdProductDeal.setTradeMarket(settleTransData.getTradeMarket());
        // 交易平台
        trdProductDeal.setTradePlat(settleTransData.getTradePlat());
        // 交易市场
        trdProductDeal.setTradeMarketMode(settleTransData.getTradeMarketMode());

        if(StringUtil.equals(settleTransData.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        }
        else if(StringUtil.equals(settleTransData.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        }
        // 本方交易员
        trdProductDeal.setTrader(null);
        // 对手方交易员
        trdProductDeal.setCounterTrader(null);
        // 对手方联系方式
        trdProductDeal.setCounterContact(null);
        // 证券管理户代码
        trdProductDeal.setSecManageAcctId(settleTransData.getSecAcctId());
        // 关联系统交易编号
        trdProductDeal.setfTradeId(null);
        // 现金流代码
        trdProductDeal.setCashId(null);
        // 登记托管机构
        trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
        // 备注
        trdProductDeal.setRemark(null);
        // 撤销说明
        trdProductDeal.setCancelRemark(null);
        // 销售代码
        trdProductDeal.setSaleCode(null);
        // 计提份额
        trdProductDeal.setAccrNum(null);
        // 交收方式
        trdProductDeal.setSettlemenType(settleTransData.getSettleMode());
        // 席位号
        trdProductDeal.setSeatId(settleTransData.getSeatNumber());
        // 投资组合代码
        trdProductDeal.setPortfolioId(settleTransData.getPortfolioId());

        return trdProductDeal;
    }

    /**
     * 金融产品交易附属表
     */
    public TrdProductDealAdd genTrdProductDealAdd(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, trdProductDeal);
        trdProductDealAdd.setEntrustId(entrustDto.getEntrustId());
        trdProductDealAdd.setPortfolioId(entrustDto.getPortfolioId());

        if(StringUtil.equals(DictCodeListExt3.下行交易匹配类型_自动匹配.getValue(),dealDto.getMatchType())) {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());
        }
        else {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        }
        return trdProductDealAdd;
    }

    /**
     * 金融产品交易附属表
     */
    public PaTrdProductDeal genPaTrdProductDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal, trdProductDeal);
        paTrdProductDeal.setPaBussType(entrustDto.getEntrustId());
        if (StringUtil.equals(dealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_正回购.getValue());
            paTrdProductDeal.setBackLendDirection(DictCodeList.回购拆借方向_正回购.getValue());
        }
        else {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_逆回购.getValue());
            paTrdProductDeal.setBackLendDirection(DictCodeList.回购拆借方向_逆回购.getValue());
        }
        return paTrdProductDeal;
    }

    public List<PtlTrade> generatePtlTrade(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDealDto) {
            List<PtlTrade> ptlTradeList = new ArrayList<>();
            PtlTrade ptlTrade = new PtlTrade();
            // 组合代码
            ptlTrade.setPortfolioId(entrustDto.getPortfolioId());
            // 业务编号
            ptlTrade.setBusiNo(trdProductDealDto.getTradeId());
            // 业务表
            ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
            // 业务明细代码
            ptlTrade.setBusiId(trdProductDealDto.getFinprodId());
            // 投资目的
            ptlTrade.setInvAim(trdProductDealDto.getInvAim());
            // 证券托管户代码
            ptlTrade.setSecManageAcctId(trdProductDealDto.getSecManageAcctId());
            // 实际发生日
            ptlTrade.setSettleDate(trdProductDealDto.getSettleDate());
            // 业务类型
            ptlTrade.setBusiType(DictCodeList.共通业务类型_质押式回购交易.getValue());
            ptlTradeList.add(ptlTrade);
            return ptlTradeList;
    }

    /**
     * 金融产品交易费用明细
     * @param
     * @return
     */
    public TrdTradeFeeDetail genTrdTradeFeeDetail(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        return null;
    }

    public List<TrdTradeFeeDetail> genTrdTradeFeeDetailList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<TrdTradeFeeDetail> feeDetailList = new ArrayList<>(7);
            if (dealDto.getJsfAmt() != null && dealDto.getJsfAmt().compareTo(new BigDecimal(0)) > 0) {
                // 经手费
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_经手费.getValue(),DictCodeList.是否_是.getValue(),dealDto.getJsfAmt(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            if (dealDto.getGhfAmt() != null && dealDto.getGhfAmt().compareTo(new BigDecimal(0)) > 0) {
                // 过户费
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_过户费.getValue(),DictCodeList.是否_是.getValue(),dealDto.getGhfAmt(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            if (dealDto.getZgfAmt() != null && dealDto.getZgfAmt().compareTo(new BigDecimal(0)) > 0) {
                // 证管费
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_证管费.getValue(),DictCodeList.是否_是.getValue(),dealDto.getZgfAmt(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            if (dealDto.getSxfAmt() != null && dealDto.getSxfAmt().compareTo(new BigDecimal(0)) > 0) {
                // 手续费
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_交易手续费.getValue(),DictCodeList.是否_是.getValue(),dealDto.getSxfAmt(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            if (dealDto.getSettleFee() != null && dealDto.getSettleFee().compareTo(new BigDecimal(0)) > 0) {
                // 结算费
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_结算费.getValue(),DictCodeList.是否_是.getValue(),dealDto.getSettleFee(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            if (dealDto.getStampFee() != null && dealDto.getStampFee().compareTo(new BigDecimal(0)) > 0) {
                // 樱花谁
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_印花税.getValue(),DictCodeList.是否_是.getValue(),dealDto.getStampFee(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
            // 佣金
            if (dealDto.getCommissionAmt() != null && dealDto.getCommissionAmt().compareTo(new BigDecimal(0)) > 0) {
                feeDetailList.add(buildTrdTradeFeeDetail(trdProductDeal.getTradeId(),DictCodeListExt1.一次性费用类型_佣金.getValue(),dealDto.getIsPayWithTrade(),dealDto.getCommissionAmt(),entrustDto.getMarchQty(),dealDto.getRemainNum(),trdProductDeal.getRegistOrg()));
            }
        return feeDetailList;
    }

    /**
     * 划款指令明细list
     */
    public List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        return null;
    }

    /**
     * 根据交易明细创建指令
     * @param dealDto 交易明细
     * @return
     */
    protected StlPayOrdersDto createStlPayOrders(ExchangeTransMatchCommonDto dealDto,ExchangeTransMatchCommonEntrustDto entrustDto,TrdProductDeal trdProductDeal) {
        // 指令
        StlPayOrdersDto stlPayOrders = new StlPayOrdersDto();
        // 指令编号
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setSeqNo("9998");
        stlPayOrders.setOrderId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
        // 结算日期
        stlPayOrders.setSettleDate(trdProductDeal.getSettleDate());
        // 本方资金账户
        stlPayOrders.setMyAcctNo(dealDto.getAcctNo());
        // 结算金额
        stlPayOrders.setSettleAmt(trdProductDeal.getTradeAmt());
        // 币种
        stlPayOrders.setCcy(trdProductDeal.getCcy());
        // 是否需要发送
        stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
        // 是否汇总交易
        stlPayOrders.setIsTotalTrade(DictCodeList.是否_是.getValue());
        // 交易编号
        stlPayOrders.setTradeId(trdProductDeal.getTradeId());
        // 指令状态
        stlPayOrders.setOrderType(DictCodeListExt1.交易状态_初始.getValue());
        // 投资组合代码
        stlPayOrders.setPortfolioId(dealDto.getPortfolioId());
        // 理财产品代码
        stlPayOrders.setProdId(trdProductDeal.getFinprodId());
        // 备注
        stlPayOrders.setRemark("匹配交收处理");
        // 划款类型
        stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
        return stlPayOrders;
    }

    /**
     * 创建费用明细
     * @param tradeId 交易编号
     * @param feeType 费用类型
     * @param payWithTrade 是否随交易支付
     * @param feeAmt 费用金额
     * @return 费用明细
     */
    private TrdTradeFeeDetail buildTrdTradeFeeDetail(String tradeId, String feeType, String payWithTrade, BigDecimal feeAmt, BigDecimal marchQty,BigDecimal remainNum,String registOrg) {
        // 经手费
        TrdTradeFeeDetail feeDetail = new TrdTradeFeeDetail();
        // 交易编号
        feeDetail.setTradeId(tradeId);
        // 费用类型
        feeDetail.setFeeType(feeType);
        // 是否随交易支付
        feeDetail.setIsPayWithTrade(payWithTrade);
        // 费用金额
        feeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(marchQty,remainNum,4),feeAmt));
        // 登记托管机构
        feeDetail.setRegistOrg(registOrg);
        return feeDetail;
    }

    /**
     * 生成金融产品交易标的
     * 对于交易方向为‘买入’的数据生成1条数据
     * @param trdProductDeal
     * @return
     */
    public FinTradeProduct genFinTradeProduct(SettleTransData settleTransData,TrdProductDeal trdProductDeal) {
        FinTradeProduct finTradeProduct = new FinTradeProduct();
        finTradeProduct.setFinprodId(trdProductDeal.getFinprodId());
        finTradeProduct.setBranch(trdProductDeal.getBranch());
        finTradeProduct.setFinprodType(trdProductDeal.getFinprodType());
        finTradeProduct.setFinprodType2(trdProductDeal.getFinprodType2());
        finTradeProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
        finTradeProduct.setPrin(trdProductDeal.getPrinAmt());
        finTradeProduct.setCcy(trdProductDeal.getCcy());
        finTradeProduct.setVdate(trdProductDeal.getVdate());
        finTradeProduct.setMdate(trdProductDeal.getMdate());
        finTradeProduct.setVpayDate(settleTransData.getVpayDate());
        finTradeProduct.setMpayDate(settleTransData.getMpayDate());
        if(null != trdProductDeal.getVdate() && null != trdProductDeal.getMdate()){
            finTradeProduct.setTerm(DateUtil.getBetweenDayNumber(trdProductDeal.getVdate(),trdProductDeal.getMdate()));
        }
        finTradeProduct.setIntType(DictCodeList.利率类型_固定利率.getValue());
        finTradeProduct.setIntRate(settleTransData.getUnitCprice());
        finTradeProduct.setBasis(DictCodeList.计息基础_A_365.getValue());
        finTradeProduct.setmDeliveryType("DVP");
        finTradeProduct.setmPrinAmt(trdProductDeal.getShareAmt());
        finTradeProduct.setmIntAmt(settleTransData.getmIntAmt());
        finTradeProduct.setmPrinAmt(settleTransData.getmPrinAmt());
        finTradeProduct.setTradeMarket(trdProductDeal.getTradeMarket());
        finTradeProduct.setCalendarId(StringUtil.equals(trdProductDeal.getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue());
        finTradeProduct.setTermType(settleTransData.getTermType());
        if(null != finTradeProduct.getVpayDate() && null != finTradeProduct.getMpayDate()){
            finTradeProduct.setActCapDays(DateUtil.getBetweenDayNumber(finTradeProduct.getVpayDate(),finTradeProduct.getMpayDate()));
        }
        return finTradeProduct;
    }

    public FinProductType genFinProductType(ExchangeTransMatchCommonDto dealDto,TrdProductDeal trdProductDeal) {
        FinProductType finProductType = new FinProductType();
        finProductType.setFinprodId(trdProductDeal.getFinprodId());
        finProductType.setBranch(trdProductDeal.getBranch());
        finProductType.setType6(dealDto.getSettleTransDataTotalAdd().getRepoType());
        if(StringUtil.equals(dealDto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())) {
            finProductType.setType7(DictCodeList.回购拆借方向_正回购.getValue());
        }
        else {
            finProductType.setType7(DictCodeList.回购拆借方向_逆回购.getValue());
        }

        finProductType.setType8(DictCodeList.平安资产类型_质押式回购.getValue());
        return finProductType;
    }

}
