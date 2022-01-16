package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.TrdProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDetailDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.dao.ExchangeTransMatchCommonMapper;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.dto.ExchangeTransMatchBaseTradeDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ExchangeTransMatchBondCommonService extends BaseService {

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private ExchangeTransMatchCommonMapper exchangeTransMatchCommonMapper;

    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;

    @Value("${application.micorservice.contextPath.commonService:http://commonService}")
    private String commonServiceName;

    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;
    /**
     * 交易信息实体
     * @param dealDto
     * @param entrustDto
     * @return
     */
    public List<ExchangeTransMatchBaseTradeDto> genFinProductTradeDto(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        List<ExchangeTransMatchBaseTradeDto> baseTradeProductDealDtoList = new ArrayList<>();
        //生成金融产品汇总交易信息
        TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotal(dealDto,entrustDto);

        if(StringUtil.equals(entrustDto.getEntrustClass(),DictCodeListExt3.投资指令类型_多组合单资产指令.getValue())) {
            for (ExchangeTransMatchCommonEntrustDetailDto detailDto:entrustDto.getEntrustDetailDtoList()) {
                ExchangeTransMatchCommonEntrustDto entrustDtoTmp = new ExchangeTransMatchCommonEntrustDto();
                BeanUtil.copyPropertiesIgnoreNull(entrustDtoTmp,entrustDto);
                entrustDtoTmp.setPortfolioId(detailDto.getPortfolioId());
                entrustDtoTmp.setMarchQty(detailDto.getMarchQty());
                entrustDtoTmp.setMarchAmt(detailDto.getMarchAmt());
                entrustDtoTmp.setMarchFeeAmtUnPay(detailDto.getMarchFeeAmtUnPay());
                entrustDtoTmp.setMarchFeeAmtPay(detailDto.getMarchFeeAmtPay());

                ExchangeTransMatchBaseTradeDto baseTradeProductDealDto = new ExchangeTransMatchBaseTradeDto();
                //生成金融产品汇总交易信息
                baseTradeProductDealDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
                //生成金融产品汇总交易附属信息
                baseTradeProductDealDto.setTrdDealTotalAdd(this.generateDealTotalAdd(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdFinProductDealTotal()));
				//生成金融产品费用汇总表数据
	        	baseTradeProductDealDto.setTrdTradeFeeTotalList(this.genTrdTradeFeeTotalList(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdFinProductDealTotal()));
                //生成金融产品交易信息
                baseTradeProductDealDto.setTrdProductDeal(this.genTrdProductDeal(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdFinProductDealTotal()));
                //生成金融产品交易附属表信息
                baseTradeProductDealDto.setTrdProductDealAdd(this.genTrdProductDealAdd(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));
                //生成组合交易表数据
                baseTradeProductDealDto.setPtlTrade(this.generatePtlTrade(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));
                //生成费用信息
                baseTradeProductDealDto.setTrdTradeFeeDetail(this.genTrdTradeFeeDetail(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));
                //生成费用信息list
                baseTradeProductDealDto.setTrdTradeFeeDetailList(this.genTrdTradeFeeDetailList(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));
                //生成划款指令
                baseTradeProductDealDto.setStlPayOrdersList(this.generateStlPayOrdersList(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));
                //金融产品实物交易表
                baseTradeProductDealDto.setTrdFinProductBarterList(this.generateDealBarterList(dealDto,entrustDtoTmp,baseTradeProductDealDto.getTrdProductDeal()));

                baseTradeProductDealDtoList.add(baseTradeProductDealDto);
            }

        }
        else {
            ExchangeTransMatchBaseTradeDto baseTradeProductDealDto = new ExchangeTransMatchBaseTradeDto();
            //生成金融产品汇总交易信息
            baseTradeProductDealDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
            //生成金融产品汇总交易附属信息
            baseTradeProductDealDto.setTrdDealTotalAdd(this.generateDealTotalAdd(dealDto,entrustDto,baseTradeProductDealDto.getTrdFinProductDealTotal()));
	        //生成金融产品费用汇总表数据
	        baseTradeProductDealDto.setTrdTradeFeeTotalList(this.genTrdTradeFeeTotalList(dealDto,entrustDto,baseTradeProductDealDto.getTrdFinProductDealTotal()));
            //生成金融产品交易信息
            baseTradeProductDealDto.setTrdProductDeal(this.genTrdProductDeal(dealDto,entrustDto,baseTradeProductDealDto.getTrdFinProductDealTotal()));
            //生成金融产品交易附属表信息
            baseTradeProductDealDto.setTrdProductDealAdd(this.genTrdProductDealAdd(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            //生成组合交易表数据
            baseTradeProductDealDto.setPtlTrade(this.generatePtlTrade(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            //生成费用信息
            baseTradeProductDealDto.setTrdTradeFeeDetail(this.genTrdTradeFeeDetail(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            //生成费用信息list
            baseTradeProductDealDto.setTrdTradeFeeDetailList(this.genTrdTradeFeeDetailList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            //生成划款指令
            baseTradeProductDealDto.setStlPayOrdersList(this.generateStlPayOrdersList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            //金融产品实物交易表
            baseTradeProductDealDto.setTrdFinProductBarterList(this.generateDealBarterList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
            baseTradeProductDealDtoList.add(baseTradeProductDealDto);
        }


        return baseTradeProductDealDtoList;
    }

    /**
     * 生成汇总交易
     * @param dealDto
     * @param entrustDto
     * @return
     */
    public TrdFinProductDealTotal genTrdFinProductDealTotal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto) {
        TrdFinProductDealTotal dealTotal = new TrdFinProductDealTotal();
        // 汇总交易编号
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        else if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        else if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        else if (StringUtil.equals(DictCodeList.交易方向_申购.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        else if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        else if (StringUtil.equals(DictCodeListExt1.交易方向_债转股.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_可转债转股交易);
        }
        else if (StringUtil.equals(DictCodeListExt3.交易方向_配债.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        else if (StringUtil.equals(DictCodeListExt3.交易方向_质押券入库.getValue(),entrustDto.getPs())
                || StringUtil.equals(DictCodeListExt3.交易方向_质押券出库.getValue(),entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_质押券出入库交易);
        }

        dealTotal.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        // 交易金额
        dealTotal.setTradeAmt(ArithmeticUtil.subtract(entrustDto.getMarchAmt(),entrustDto.getMarchFeeAmtPay()));
        // 设置汇总交易信息
        setDealTotal(dealDto, entrustDto ,dealTotal);
        // 金融产品代码
        dealTotal.setFinprodId(entrustDto.getFinprodId());
        // 金融产品类型（投管分类）
        dealTotal.setFinprodType2(entrustDto.getFinprodType2());
        // 分支序号
        dealTotal.setBranch(0L);
        // 交易日
        dealTotal.setTradeDate(dealDto.getTradeDate());
        // 起息日
        dealTotal.setVdate(null);
        // 到期日
        dealTotal.setMdate(null);
        // 交付日
//        dealTotal.setPayDate(dealDto.getPayDate());
        // 交割日
        dealTotal.setSettleDate(dealDto.getSettleDate());
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entrustDto.getPs())) {
            // 交割日期是缴款日期
            dealTotal.setSettleDate(exchangeTransMatchCommonMapper.getNextWorkDay(dealDto.getTradeDate(),StringUtil.equals(dealDto.getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue()));
        }
        // 清算速度
        dealTotal.setSettleSpeed(dealDto.getSettleSpeed());
        // 交易币种
        dealTotal.setCcy(dealDto.getCcy());
        // 交易份额
        dealTotal.setShareAmt(entrustDto.getMarchQty());
        // 随交易支付费用
        dealTotal.setFeeAmtPay(entrustDto.getMarchFeeAmtPay());
        // 未随交易支付费用
        dealTotal.setFeeAmtUnpay(entrustDto.getMarchFeeAmtPay());
        // 总交易费用
        dealTotal.setFeeAmt(ArithmeticUtil.doAdd(entrustDto.getMarchFeeAmtPay(),entrustDto.getMarchFeeAmtPay()));
        // 交易方向
        dealTotal.setPs(entrustDto.getPs());
        // 投资目的
        if(StringUtil.isNotEmpty(entrustDto.getInvAim())){
            dealTotal.setInvAim(entrustDto.getInvAim());
        }else{
            // 查询持仓的投资目的
            Map<String,Object> param = new HashMap<>();
            param.put("dealDate", dealTotal.getTradeDate());
            param.put("finProductId",dealTotal.getFinprodId());
            param.put("secManageAcctId",dealDto.getSecAcctId());
            List<PtlSecPosition> ptlSecPositions = exchangeTransMatchCommonMapper.searchPtlSecPositionList(param);
            if(CollectionUtils.isNotEmpty(ptlSecPositions)){
                dealTotal.setInvAim(ptlSecPositions.get(0).getInvAim());
            }
        }
        // 交易状态
        dealTotal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
        // 是否已撤销
        dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 证券管理户代码
        dealTotal.setSecManageAcctId(dealDto.getSecAcctId());
        // 前置交易编号
        dealTotal.setbTradeId(null);
        // 是否外部交易
        dealTotal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        dealTotal.setTradeMarket(dealDto.getTradeMarket());

        if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
            dealTotal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        }
        else if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
            dealTotal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        }

        // 交易平台
        dealTotal.setTradePlat(dealDto.getTradePlat());
        // 交易市场
        dealTotal.setTradeMarketMode(dealDto.getTradeMarketMode());
        // 投资组合代码
        dealTotal.setPortfolioId(entrustDto.getPortfolioId());
        // 备注
        dealTotal.setRemark(entrustDto.getRemark());
        // 投管业务类型
//        dealTotal.setTgBusiType(trdProductDealDto.getTgBusiType());
        // 利率
        dealTotal.setRate(null);
        // 回购期限(天)
        dealTotal.setRepoTerm(null);
        // 期限品种
        dealTotal.setTermType(null);
        return dealTotal;
    }

    /**
     * 生成汇总附属交易
     * @param dealDto
     * @param entrustDto
     * @return
     */
    public TrdDealTotalAdd generateDealTotalAdd(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdFinProductDealTotal trdFinProductDealTotal ) {
        return null;
    }


    /**
     * 生成金融产品交易明细
     */

    public TrdProductDeal genTrdProductDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdFinProductDealTotal trdFinProductDealTotal) {
        return createDeal(dealDto,entrustDto,trdFinProductDealTotal);
    }

    public TrdProductDeal createProductDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdFinProductDealTotal trdFinProductDealTotal) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        if (StringUtil.equals(DictCodeList.交易方向_申购.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeListExt1.交易方向_债转股.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_可转债转股交易);
        }
        if (StringUtil.equals(DictCodeListExt3.交易方向_配债.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));

        // 业务类型
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        // 金融产品代码
        trdProductDeal.setFinprodId(entrustDto.getFinprodId());
        // 金融产品类型（投管分类）
        trdProductDeal.setFinprodType2(entrustDto.getFinprodType2());
        // 金融产品类型
        trdProductDeal.setFinprodType(dealDto.getFinprodType());
        // 分支序号
        trdProductDeal.setBranch(0L);
        // 交易日
        trdProductDeal.setTradeDate(dealDto.getTradeDate());
        // 起息日
        trdProductDeal.setVdate(dealDto.getVdate());
        // 到期日
        trdProductDeal.setMdate(dealDto.getMdate());
        // 交付日
        trdProductDeal.setPayDate(dealDto.getVpayDate());
        // 交割日
        trdProductDeal.setSettleDate(dealDto.getSettleDate());
        // 交易币种
        trdProductDeal.setCcy(dealDto.getCcy());
        // 净值日期
        trdProductDeal.setCpriceDate(null);
        // 投资目的
        if(StringUtil.isNotEmpty(entrustDto.getInvAim())){
            trdProductDeal.setInvAim(entrustDto.getInvAim());
        }else{
            // 查询持仓的投资目的
            Map<String,Object> param = new HashMap<>();
            param.put("dealDate", trdProductDeal.getTradeDate());
            param.put("finProductId",trdProductDeal.getFinprodId());
            param.put("secManageAcctId",dealDto.getSecAcctId());
            List<PtlSecPosition>  ptlSecPositions = exchangeTransMatchCommonMapper.searchPtlSecPositionList(param);
            if(CollectionUtils.isNotEmpty(ptlSecPositions)){
                trdProductDeal.setInvAim(ptlSecPositions.get(0).getInvAim());
            }
        }

        // 交易状态
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 是否已撤销
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 原交易编号
        trdProductDeal.setpTradeId(null);
        // 是否结清
        trdProductDeal.setIsClean(null);
        // 汇总交易编号
        trdProductDeal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        // 反向交易编号
        trdProductDeal.setrTradeId(null);
        // 通道代码
        trdProductDeal.setChlId(null);
        // 交易对手
        trdProductDeal.setCounterId(null);
        // 是否外部交易
        trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        trdProductDeal.setTradeMarket(dealDto.getTradeMarket());
        // 交易平台
        trdProductDeal.setTradePlat(dealDto.getTradePlat());
        // 交易市场
        trdProductDeal.setTradeMarketMode(dealDto.getTradeMarketMode());

        if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        }
        else if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        }
        // 本方交易员
        trdProductDeal.setTrader(null);
        // 对手方交易员
        trdProductDeal.setCounterTrader(null);
        // 对手方联系方式
        trdProductDeal.setCounterContact(null);
        // 证券管理户代码
        trdProductDeal.setSecManageAcctId(dealDto.getSecAcctId());
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
        trdProductDeal.setSettlemenType(dealDto.getSettleMode());
        // 席位号
        trdProductDeal.setSeatId(dealDto.getSeatNumber());
        // 投资组合代码
        trdProductDeal.setPortfolioId(dealDto.getPortfolioId());

        return trdProductDeal;
    }

    /**
     * 金融产品交易附属表
     */
    public TrdProductDealAdd genTrdProductDealAdd(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdProductDeal trdProductDeal) {
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, trdProductDeal);
        trdProductDealAdd.setEntrustId(entrustDto.getEntrustId());
        trdProductDealAdd.setPortfolioId(entrustDto.getPortfolioId());

        // 申购模式
        if ((DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(trdProductDeal.getFinprodType2()))
                // GZYHTZJY-1000: 未对申购方向的申购模式进行赋值,导致撤销批处理查询未查到 fuyongsheng modify 20211230 start
//                && DictCodeList.交易方向_申购确认.getValue().equals(trdProductDeal.getPs())) {
                && (DictCodeList.交易方向_申购确认.getValue().equals(trdProductDeal.getPs())
                    || DictCodeList.交易方向_申购.getValue().equals(trdProductDeal.getPs()))) {
            // GZYHTZJY-1000: 未对申购方向的申购模式进行赋值,导致撤销批处理查询未查到 fuyongsheng modify 20211230 end
            trdProductDealAdd.setPurchModle(DictCodeListExt2.申购方式_网上申购.getValue());
        }
        if(StringUtil.equals(DictCodeListExt3.下行交易匹配类型_自动匹配.getValue(),dealDto.getMatchType())) {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());
        }
        else {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        }
        return trdProductDealAdd;
    }

    /**
     * 金融产品交易费用明细
     * @param
     * @return
     */
    public TrdTradeFeeDetail genTrdTradeFeeDetail(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        return null;
    }

    /**
     * 金融产品交易费用明细list
     * @param
     * @return
     */
    public List<TrdTradeFeeTotal> genTrdTradeFeeTotalList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
        List<TrdTradeFeeTotal> trdTradeFeeTotalList = new ArrayList<>();

        if (dealDto.getSxfAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getSxfAmt()) < 0) {
            TrdTradeFeeTotal stampFee = new TrdTradeFeeTotal();
            stampFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            stampFee.setFeeType(DictCodeListExt1.一次性费用类型_印花税.getValue());
            stampFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            stampFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
            trdTradeFeeTotalList.add(stampFee);
        }

        if (dealDto.getJsfAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getJsfAmt()) < 0) {
            TrdTradeFeeTotal jsfFee = new TrdTradeFeeTotal();
            jsfFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            jsfFee.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
            jsfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            jsfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getJsfAmt()));
            trdTradeFeeTotalList.add(jsfFee);
        }

        if (dealDto.getGhfAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getGhfAmt()) < 0) {
            TrdTradeFeeTotal ghfFee = new TrdTradeFeeTotal();
            ghfFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            ghfFee.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
            ghfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            ghfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getGhfAmt()));
            trdTradeFeeTotalList.add(ghfFee);
        }

        if (dealDto.getZgfAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getZgfAmt()) < 0) {
            TrdTradeFeeTotal zgfFee = new TrdTradeFeeTotal();
            zgfFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            zgfFee.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
            zgfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            zgfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getZgfAmt()));
            trdTradeFeeTotalList.add(zgfFee);
        }

        if (dealDto.getSxfAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getSxfAmt()) < 0) {
            TrdTradeFeeTotal sxfFee = new TrdTradeFeeTotal();
            sxfFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            sxfFee.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
            sxfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            sxfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
            trdTradeFeeTotalList.add(sxfFee);
        }

        if (dealDto.getSettleFee() != null && BigDecimal.ZERO.compareTo(dealDto.getSettleFee()) < 0) {
            TrdTradeFeeTotal settleFee = new TrdTradeFeeTotal();
            settleFee.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            settleFee.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
            settleFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            settleFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSettleFee()));
            trdTradeFeeTotalList.add(settleFee);
        }

        if (dealDto.getCommissionAmt() != null && BigDecimal.ZERO.compareTo(dealDto.getCommissionAmt()) < 0) {
            TrdTradeFeeTotal commissionAmt = new TrdTradeFeeTotal();
            commissionAmt.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            commissionAmt.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
            commissionAmt.setIsPayWithTrade(dealDto.getIsPayWithTrade());
            if (StringUtil.isBlank(commissionAmt.getIsPayWithTrade())) {
                commissionAmt.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            }
            commissionAmt.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getCommissionAmt()));
            trdTradeFeeTotalList.add(commissionAmt);
        }
        return trdTradeFeeTotalList;
    }

    /**
     * 金融产品交易费用明细list
     * @param
     * @return
     */
    public List<TrdTradeFeeDetail> genTrdTradeFeeDetailList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<TrdTradeFeeDetail> trdTradeFeeDetailList = new ArrayList<>();
        TrdTradeFeeDetail stampFee = new TrdTradeFeeDetail();
        stampFee.setTradeId(trdProductDeal.getTradeId());
        stampFee.setFeeType(DictCodeListExt1.一次性费用类型_印花税.getValue());
        stampFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        stampFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
        stampFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail jsfFee = new TrdTradeFeeDetail();
        jsfFee.setTradeId(trdProductDeal.getTradeId());
        jsfFee.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
        jsfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        jsfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getJsfAmt()));
        jsfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail ghfFee = new TrdTradeFeeDetail();
        ghfFee.setTradeId(trdProductDeal.getTradeId());
        ghfFee.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
        ghfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        ghfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getGhfAmt()));
        ghfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail zgfFee = new TrdTradeFeeDetail();
        zgfFee.setTradeId(trdProductDeal.getTradeId());
        zgfFee.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
        zgfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        zgfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getZgfAmt()));
        zgfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail sxfFee = new TrdTradeFeeDetail();
        sxfFee.setTradeId(trdProductDeal.getTradeId());
        sxfFee.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        sxfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        sxfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
        sxfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail settleFee = new TrdTradeFeeDetail();
        settleFee.setTradeId(trdProductDeal.getTradeId());
        settleFee.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
        settleFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        settleFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSettleFee()));
        settleFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail commissionAmt = new TrdTradeFeeDetail();
        commissionAmt.setTradeId(trdProductDeal.getTradeId());
        commissionAmt.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
        commissionAmt.setIsPayWithTrade(dealDto.getIsPayWithTrade());
        commissionAmt.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getCommissionAmt()));
        commissionAmt.setRegistOrg(trdProductDeal.getRegistOrg());

        trdTradeFeeDetailList.add(stampFee);
        trdTradeFeeDetailList.add(jsfFee);
        trdTradeFeeDetailList.add(ghfFee);
        trdTradeFeeDetailList.add(zgfFee);
        trdTradeFeeDetailList.add(sxfFee);
        trdTradeFeeDetailList.add(settleFee);
        trdTradeFeeDetailList.add(commissionAmt);
        return trdTradeFeeDetailList;
    }
    /**
     * 组合交易表数据
     * @param dealDto
     * @param entrustDto
     * @param trdProductDealDto
     * @return
     */
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
        ptlTrade.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());

        ptlTradeList.add(ptlTrade);
        return ptlTradeList;
    }

    /**
     * 划款指令明细list
     */
    public List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        return null;
    }

    protected StlPayOrdersDto createStlPayOrders(ExchangeTransMatchCommonDto dealDto,ExchangeTransMatchCommonEntrustDto entrustDto,TrdProductDeal trdProductDeal) {
        // 指令
        StlPayOrdersDto stlPayOrders = new StlPayOrdersDto();
        // 指令编号
        stlPayOrders.setOrderId(this.buildBussinessIdBySeqno("9998"));
        // 指令类型
        stlPayOrders.setOrderType(null);
        // 结算日期
        stlPayOrders.setSettleDate(trdProductDeal.getSettleDate());
        // 本方资金账户
        stlPayOrders.setMyAcctNo(dealDto.getAcctNo());
        // 结算金额
        stlPayOrders.setSettleAmt(null != trdProductDeal.getTradeAmt() ? trdProductDeal.getTradeAmt() : BigDecimal.ZERO);
        // 币种
        stlPayOrders.setCcy(trdProductDeal.getCcy());
        // 是否需要发送
        stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
        // 是否汇总交易
        stlPayOrders.setIsTotalTrade(DictCodeList.是否_是.getValue());
        // 交易编号
        stlPayOrders.setTradeId(StringUtil.isNotEmpty(trdProductDeal.getTotleTradeId())?trdProductDeal.getTotleTradeId():trdProductDeal.getTradeId());
        // 指令状态
        stlPayOrders.setOrderType(DictCodeListExt1.交易状态_初始.getValue());
        // 投资组合代码
        stlPayOrders.setPortfolioId(entrustDto.getPortfolioId());
        // 备注
        stlPayOrders.setRemark("数据清算自动处理");
        // 划款类型
        stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
        return stlPayOrders;
    }

    public List<TrdFinProductBarterTotal> generateDealBarterTotal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
        return null;
    }

    protected List<TrdFinProductBarter> generateDealBarterList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        return null;
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
     * 生成交易汇总表信息
     * @param dealDto
     * @param entrustDto
     * @param trdFinProductDealTotal
     */
    protected void setDealTotal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdFinProductDealTotal trdFinProductDealTotal) {

    }

    /**
     * 计算债券到期收益率和行权收益率
     * @param productDeal
     */
    public void calc(TrdProductDeal productDeal) {
        // 设置计算参数
        TrdBondInvestTradeDto  dealParam = new TrdBondInvestTradeDto();
        dealParam.setFinprodId(productDeal.getFinprodId());
        dealParam.setTradeAmt(productDeal.getShareAmt().multiply(BigDecimal.valueOf(100)));
        dealParam.setSettleDate(productDeal.getSettleDate());
        dealParam.setVdate(productDeal.getVdate());
        dealParam.setTradeMarket(productDeal.getTradeMarket());
        dealParam.setTradeMarketMode(productDeal.getTradeMarketMode());
        dealParam.setPs(productDeal.getPs());
        dealParam.setUnitInt(productDeal.getUnitInt());
        dealParam.setUnitFprice(productDeal.getUnitFprice());

        // 设置计算类型为全价总额（根据全价总额计算到期收益率和行权收益率）
        dealParam.setCalculateField("fpriceAmt");
        dealParam.setFpriceAmt(productDeal.getFpriceAmt());
        dealParam.setIntAmt(productDeal.getIntAmt());
        dealParam.setInvAim(productDeal.getInvAim());

        // 调用债券交易计算逻辑
        AjaxResult ajaxResult = trdBondInvestTrade002Service.calculateData(dealParam);

        if (ajaxResult != null && StringUtil.isNotEmpty(ajaxResult.getData())) {
            Object result = ajaxResult.getData();
            if(result instanceof Map) {
                Map<String, Object> calcResult = (Map)result;
                Object yield = calcResult.get("yield");
                Object exerYield = calcResult.get("exerYield");
                if(yield instanceof BigDecimal) {
                    productDeal.setYield((BigDecimal)yield);
                }
                else if (yield != null) {
                    productDeal.setYield(new BigDecimal(yield.toString()));
                }
                if(exerYield instanceof BigDecimal) {
                    productDeal.setExerYield((BigDecimal)exerYield);
                }
                else if (exerYield != null) {
                    productDeal.setExerYield(new BigDecimal(exerYield.toString()));
                }
                if (productDeal.getYield() != null){
                    productDeal.setYield(productDeal.getYield().divide(BigDecimal.valueOf(100)));
                }
                if (productDeal.getExerYield() != null){
                    productDeal.setExerYield(productDeal.getExerYield().divide(BigDecimal.valueOf(100)));
                }
            }
        }
    }

    protected void calcFeeAmtPay(ExchangeTransMatchCommonDto dealDto,ExchangeTransMatchCommonEntrustDto entrustDto,TrdProductDeal trdProductDeal) {
        // 净价总额
        trdProductDeal.setCpriceAmt(ArithmeticUtil.multiply(dealDto.getUnitCprice(),entrustDto.getMarchQty()));
        // 交易份额
        trdProductDeal.setShareAmt(entrustDto.getMarchQty());
        // 交易本金
        trdProductDeal.setPrinAmt(ArithmeticUtil.multiply(entrustDto.getMarchQty(),new BigDecimal(100)));
        // 利息总额
        trdProductDeal.setIntAmt(ArithmeticUtil.multiply(entrustDto.getMarchQty(),dealDto.getUnitInt()));
        // 全价总额
        trdProductDeal.setFpriceAmt(entrustDto.getMarchAmt());
        // 随交易支付费用
        trdProductDeal.setFeeAmtPay(entrustDto.getMarchFeeAmtPay());
        // 交易金额
        trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(trdProductDeal.getFpriceAmt(),trdProductDeal.getFeeAmtPay()));
        // 未随交易支付费用
        trdProductDeal.setFeeAmtUnpay(entrustDto.getMarchFeeAmtUnPay());
        // 总交易费用
        trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(entrustDto.getMarchFeeAmtPay(), entrustDto.getMarchFeeAmtUnPay()));
    }

    /**
     * 生成业务编号
     * @param
     * @return 业务编号
     * @throws Exception
     */
    public String buildBussinessIdBySeqno(String seqno) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("seqNo", seqno);
        // 生成的业务编号(此处对性能影响比较大)
        if (isSingle) {
            return simpleRestTemplate.postForObject(
                    commonServiceName + "/core/selectRule", param,
                    String.class);
        }
        else {
            return restTemplate.postForObject(
                    commonServiceName + "/core/selectRule", param,
                    String.class);
        }

    }

    /**
     * 获取投管业务类型
     * @param ps 交易方向
     * @return
     */
    protected String getTgBusiType(String ps) {
        if(StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
            return DictCodeListExt1.平安业务类型_债券类资产买入.getValue();
        }
        else if(StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
            return DictCodeListExt1.平安业务类型_债券类资产卖出.getValue();
        }
        else if(StringUtil.equals(ps, DictCodeList.交易方向_行权_回售.getValue())) {
            return DictCodeListExt1.平安业务类型_债券类资产行权.getValue();
        }
        return null;
    }

    /**
     * 查询前置交易编号
     * @param
     * @return 前置交易编号
     */
    public String selectBTradeId(String applyNo, String ps) {
        Map<String, String> paraMap = new HashMap<>(2);
        paraMap.put("ps", ps);
        paraMap.put("tradeId", applyNo);
        return exchangeTransMatchCommonMapper.selectBTradeId(paraMap);
    }

    /**
     * 根据交易编号查询汇总附属信息
     *
     * @param tradeId 交易编号
     * @return 汇总附属信息
     * @see
     * @since 1.0
     */
    public TrdDealTotalAdd selectTotleDealByTotleId(String tradeId){
        return exchangeTransMatchCommonMapper.queryTotalAddByTradeId(tradeId);
    }
}
