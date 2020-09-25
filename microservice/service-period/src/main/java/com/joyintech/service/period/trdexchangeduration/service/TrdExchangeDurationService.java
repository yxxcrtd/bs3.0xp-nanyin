package com.joyintech.service.period.trdexchangeduration.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.period.trdexchangeduration.dao.TrdExchangeDurationMapper;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;

/**
 * 外汇远期掉期到期时待处理数据用微服务
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @Project: microservice
 */
@Service
public class TrdExchangeDurationService extends BaseService {

    @Autowired
    private TrdExchangeDurationMapper trdExchangeDurationMapper;

    /**
     * 注入业务主键生产类
     */
    @Autowired(required = false)
    private BaseGetBussinessId baseGetBusinessId;

    /**
     * 查询外汇远期/掉期待处理的数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<TrdExchangeDurDbInertDto> findAllData(@RequestBody Map<String, Object> param) {
        this.setParamForFindAllData(param);
        param.put("ASSET_ACCT_TYPE_01", DictCodeListExt2.标的账户类型_本方账户.getValue());
        param.put("ASSET_ACCT_TYPE_02", DictCodeListExt2.标的账户类型_对手方账户.getValue());
        param.put("STATUS_A", DictCodeList.数据状态_新建中.getValue());
        param.put("STATUS_P", DictCodeList.数据状态_删除中.getValue());
        List<TrdExchangeDurDbInertDto> list = trdExchangeDurationMapper.findPage(param);
        doCreateEndDeal(list);
        return list;
    }

    /**
     * 根据买入交易信息生成到期交易
     *
     * @param list 交易信息
     * @see
     * @since 1.0
     */
    public List<TrdExchangeDurDbInertDto> doCreateEndDeal(List<TrdExchangeDurDbInertDto> list) {
        if (!StringUtil.isEmptyList(list)) {
            list.forEach(trdExchangeDurDbInertDto -> {
                TrdProductDeal trdProductDeal = trdExchangeDurDbInertDto.getTrdProductDeal();
                TrdProductDealAdd trdProductDealAdd = trdExchangeDurDbInertDto.getTrdProductDealAdd();
                FinTradeProduct finTradeProduct = trdExchangeDurDbInertDto.getFinTradeProduct();
                // 金融产品交易
                TrdProductDeal deal = setTrdProductDeal(trdExchangeDurDbInertDto, trdProductDeal,
                    finTradeProduct);
                // 金融产品交易附属表
                setTrdProductDealAdd(trdExchangeDurDbInertDto, trdProductDealAdd, finTradeProduct,
                    deal);
                // 组合交易表
                trdExchangeDurDbInertDto.setPtlTrade(
                    setPtlTrade(trdProductDealAdd, finTradeProduct, deal));
                // 划款指令表

                // 金融产品交易扩展
                setPaTrdProductDeal(deal, trdExchangeDurDbInertDto);

            });
        }
        return list;
    }

    /**
     * 组合交易表
     * 
     * @param dto 外汇掉期投资交易实体类
     * @return 组合交易表
     * @see
     * @since 1.0
     */
    public PtlTrade setPtlTrade(TrdProductDealAdd trdProductDealAdd,
                                FinTradeProduct finTradeProduct, TrdProductDeal deal) {
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(trdProductDealAdd.getPortfolioId());
        ptlTrade.setBusiNo(deal.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(deal.getFinprodId());
        if (StringUtil.equals(deal.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
        	ptlTrade.setBusiType(DictCodeListExt2.共通业务类型_外汇远期投资交易.getValue());
        }else {
            ptlTrade.setBusiType(DictCodeListExt2.共通业务类型_外汇掉期投资交易.getValue());
        }
        ptlTrade.setSettleDate(deal.getTradeDate());
        return ptlTrade;
    }

    /**
     * 金融产品交易扩展
     * 
     * @param deal 交易
     * @param paTrdProductDeal 金融产品交易扩展
     * @return 金融产品交易扩展
     * @see
     * @since 1.0
     */
    public PaTrdProductDeal setPaTrdProductDeal(TrdProductDeal deal,
                                                TrdExchangeDurDbInertDto trdExchangeDurDbInertDto) {
        PaTrdProductDeal result = new PaTrdProductDeal();
        result.setTradeId(deal.getTradeId());
        if (StringUtil.equals(deal.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
            result.setPaBussType(DictCodeListExt2.平安业务类型_外汇远期到期.getValue());
        }else {
            result.setPaBussType(DictCodeListExt2.平安业务类型_外汇掉期到期.getValue());
        }
        result.setDeptCode(trdExchangeDurDbInertDto.getPaTrdProductDeal().getDeptCode());
        trdExchangeDurDbInertDto.setPaTrdProductDeal(result);
        return result;
    }

    /**
     * 交易附属表做成
     * 
     * @param trdExchangeDurDbInertDto 参数
     * @param trdProductDealAdd 交易附属表
     * @param finTradeProduct 产品表
     * @param deal 交易表
     * @see
     * @since 1.0
     */
    private void setTrdProductDealAdd(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto,
                                      TrdProductDealAdd trdProductDealAdd,
                                      FinTradeProduct finTradeProduct, TrdProductDeal deal) {
        TrdProductDealAdd dealAdd = new TrdProductDealAdd();
        dealAdd.setTradeId(deal.getTradeId());
        dealAdd.setPortfolioId(trdProductDealAdd.getPortfolioId());
        dealAdd.setEntrustId(trdProductDealAdd.getEntrustId());
        dealAdd.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());
        dealAdd.setExcDealType(trdProductDealAdd.getExcDealType());
        dealAdd.setCurPair(trdProductDealAdd.getCurPair());
        dealAdd.setUsdAmt(finTradeProduct.getUsdAmt());
        dealAdd.setTermType(finTradeProduct.getTermType());
        dealAdd.setOurRole(trdProductDealAdd.getOurRole());
        dealAdd.setDealMode(trdProductDealAdd.getDealMode());
        dealAdd.setQuotPre(trdProductDealAdd.getQuotPre());
        dealAdd.setRefRate(trdProductDealAdd.getRefRate());
        trdExchangeDurDbInertDto.setTrdProductDealAdd(dealAdd);
    }

    /**
     * 交易表作成
     * 
     * @param trdExchangeDurDbInertDto 参数
     * @param trdProductDeal 交易表
     * @param finTradeProduct 产品表
     * @return 交易表
     * @see
     * @since 1.0
     */
    private TrdProductDeal setTrdProductDeal(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto,
                                             TrdProductDeal trdProductDeal,
                                             FinTradeProduct finTradeProduct) {
        // 到期交易
        TrdProductDeal deal = new TrdProductDeal();
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(trdProductDeal.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
            bussinessCodeParameter.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇远期投资交易));
            deal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
            deal.setBusiType(DictCodeListExt2.共通业务类型_外汇远期投资交易.getValue());
        }
        else {
            bussinessCodeParameter.setCommonBusinessExtType(
                DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇掉期投资交易));
            deal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
            deal.setBusiType(DictCodeListExt2.共通业务类型_外汇掉期投资交易.getValue());
        }
        deal.setBusiType(trdProductDeal.getBusiType());
        deal.setFinprodId(trdProductDeal.getFinprodId());
        deal.setFinprodType(trdProductDeal.getFinprodType());
        deal.setFinprodType2(trdProductDeal.getFinprodType2());
        deal.setInvAim(trdProductDeal.getInvAim());
        deal.setBranch(trdProductDeal.getBranch());
        deal.setUnitCprice(trdProductDeal.getUnitCprice());
        deal.setUnitInt(finTradeProduct.getmUnitInt());
        deal.setUnitFprice(finTradeProduct.getmUnitFprice());
        deal.setParValue(trdProductDeal.getParValue());
        deal.setCcy(trdProductDeal.getCcy());
        deal.setPs(DictCodeList.交易方向_到期.getValue());
        deal.setTradeDate(finTradeProduct.getMdate());
        deal.setSettleDate(finTradeProduct.getMdate());
        deal.setPrinAmt(finTradeProduct.getPrin());
        deal.setFpriceAmt(finTradeProduct.getmTradeAmt());
        deal.setTradeAmt(finTradeProduct.getmTradeAmt());
        deal.setDeliveryType(trdProductDeal.getDeliveryType());
        deal.setIsClean(DictCodeList.是否_是.getValue());
        deal.setTrader(trdProductDeal.getTrader());
        deal.setCounterId(trdProductDeal.getCounterId());
        deal.setCounterTrader(trdProductDeal.getCounterTrader());
        deal.setCounterContact(trdProductDeal.getCounterContact());
        deal.setIsOutTrade(trdProductDeal.getIsOutTrade());
        deal.setRemark(trdProductDeal.getRemark());
        deal.setbTradeId(trdProductDeal.getTradeId());
        deal.setParValue(BigDecimal.ONE);
        deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        deal.setIsCancel(DictCodeList.是否_否.getValue());
        deal.setFeeAmt(trdProductDeal.getFeeAmt());
        trdExchangeDurDbInertDto.setTrdProductDeal(deal);
        return deal;
    }

    /**
     * 查询参数设置
     * 
     * @param param 参数
     * @see
     * @since 1.0
     */
    private void setParamForFindAllData(Map<String, Object> param) {
        param.put("finprodType2F12", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        param.put("finprodType2F13", DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
        param.put("psBuy", DictCodeList.交易方向_买入.getValue());
        param.put("validStatus_01", DictCodeListExt1.交易状态_初始.getValue());
    }
}
