/*
 * 文件名：TradeCommonValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.common.service;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 副总裁/总裁助理授权额度检查相关处理
 * 
 * @author wangyang1
 * @version 1.0 2020年4月27日
 * @see RiskAuthorizeListCommonValidatorService
 * @since 1.0
 */
@Service
public class RiskAuthorizeListCommonValidatorService {

    /**
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(RiskAuthorizeListCommonValidatorService.class);

    /**
     * 验证方法
     *
     * @param value 需要被验证的业务对象
     * @return 验证消息，如果为空则表示验证通过
     * @since 1.0
     */
    public boolean 授权是否匹配(TrdProductDeal inputDto) {
        // 副总裁/总裁助理授权额度检查相关处理
        String counterId = null;
        BigDecimal prinAmt = null;
        String 平安资产类型 = null;
        String oth平安资产类型 = null;
        String tradeMarket = null;
        String ps = null;
        String chlId = null;
        String finprodId = null;
        boolean isNumCheck = true;
        // 债券投资交易
        if (StringUtil.equals(inputDto.getFinprodType2(), DictCodeList.平安资产类型_债券.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt();
            平安资产类型 = DictCodeList.平安资产类型_债券.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 拆借投资交易
        else if (StringUtil.equals(inputDto.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getPrinAmt();
            平安资产类型 = BussinessDictCodeList.平安资产类型_拆借.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
        }
        // 存款类资产投资交易
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            // 交易金额
            prinAmt = inputDto.getTradeAmt();
            平安资产类型 = DictCodeList.平安资产类型_存款.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 基金类投资交易
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt();
            if (prinAmt == null || prinAmt.compareTo(BigDecimal.ZERO) <= 0) {
                prinAmt = inputDto.getShareAmt();
            }
            平安资产类型 = DictCodeList.平安资产类型_基金.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //赎回时不对份额进行授权校验
            if (DictCodeList.交易方向_赎回.getValue().equals(ps)){
                isNumCheck = false;
            }
        }
        // 计划类资产（净值型）交易
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeList.平安资产类型_计划类资产_净值型.getValue())) {
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt();
            if (prinAmt == null || prinAmt.compareTo(BigDecimal.ZERO) <= 0) {
                prinAmt = inputDto.getShareAmt();
            }
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
            平安资产类型 = DictCodeList.平安资产类型_计划类资产_净值型.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //赎回时不对份额进行授权校验
            if (DictCodeList.交易方向_赎回.getValue().equals(ps) && (inputDto.getTradeAmt() == null || inputDto.getTradeAmt().compareTo(BigDecimal.ZERO) <= 0)){
                isNumCheck = false;
            }
        }
        // 计划类资产（利率型）交易
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeList.平安资产类型_计划类资产_利率型.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt();
            平安资产类型 = DictCodeList.平安资产类型_计划类资产_利率型.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 回购资产交易
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeList.平安资产类型_质押式回购.getValue())) {
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getPrinAmt();
            平安资产类型 = DictCodeList.平安资产类型_质押式回购.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 项目类资产交易
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {

            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getTradeAmt();
            平安资产类型 = DictCodeList.平安资产类型_项目类资产.getValue();
            tradeMarket = null;
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            finprodId = inputDto.getFinprodId();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 计划类资产（货币型）交易
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeList.平安资产类型_计划类资产_货币型.getValue())) {
            chlId = inputDto.getChlId();
            // 副总裁/总裁助理授权额度检查相关处理
            counterId = inputDto.getCounterId();
            prinAmt = inputDto.getPrinAmt();
            if (prinAmt == null || prinAmt.compareTo(BigDecimal.ZERO) <= 0) {
                prinAmt = inputDto.getShareAmt();
            }
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
            平安资产类型 = DictCodeList.平安资产类型_计划类资产_货币型.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            finprodId = inputDto.getFinprodId();
            
            //赎回时不对份额进行授权校验
            if (DictCodeList.交易方向_赎回.getValue().equals(ps) && (inputDto.getPrinAmt() == null || inputDto.getPrinAmt().compareTo(BigDecimal.ZERO) <= 0)){
                isNumCheck = false;
            }
        }
        // 买断式回购
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeList.平安资产类型_买断式回购.getValue())) {
            chlId = inputDto.getChlId();
            prinAmt = inputDto.getPrinAmt();
            ps = inputDto.getPs();
            平安资产类型 = DictCodeList.平安资产类型_买断式回购.getValue();
            tradeMarket = inputDto.getTradeMarket();
            //多组合时用最大的券面总额
            if (null != inputDto.getMaxTradeamt()){
                prinAmt = inputDto.getMaxTradeamt();
            }
        }
        // 债券借贷
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeListExt2.平安资产类型_债券借贷.getValue())) {
            chlId = inputDto.getChlId();
            prinAmt = StringUtil.isNotEmpty(inputDto.getShareAmt()) ? ArithmeticUtil.multiply(
                inputDto.getShareAmt(), BigDecimal.valueOf(10000)) : BigDecimal.ZERO;
            平安资产类型 = DictCodeListExt2.平安资产类型_债券借贷.getValue();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            finprodId = "";
        }
        // 股票
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeListExt1.金融产品类型_股票.getValue())) {
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeListExt2.平安资产类型_股票.getValue();
            prinAmt = inputDto.getTradeAmt();
            counterId = inputDto.getCounterId();
            tradeMarket = inputDto.getTradeMarket();
            ps = inputDto.getPs();
            if (!StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
                ps = DictCodeList.交易方向_买入.getValue();
            }
            finprodId = inputDto.getFinprodId();
        }
        // 外汇即期
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeListExt2.平安资产类型_外汇即期.getValue())) {
            counterId = inputDto.getCounterId();
            if (inputDto.getCcy().contains(DictCodeList.币种_人民币_CNY.getValue())) {
                if (StringUtil.equals(inputDto.getRemark(), DictCodeList.币种_人民币_CNY.getValue())) {
                    prinAmt = inputDto.getPrinAmt();
                }
                else {
                    prinAmt = inputDto.getTradeAmt();
                }
            }
            else {
                CommonBusinessService commonBusinessService = SpringContextUtil.getBean(
                    CommonBusinessService.class);
                if (commonBusinessService != null) {
                    prinAmt = commonBusinessService.calExchangeAmt(inputDto.getPrinAmt(),
                        DateUtil.addDay(inputDto.getTradeDate(), -1), inputDto.getCcy(),
                        DictCodeList.币种_人民币_CNY.getValue());
                }
            }
            tradeMarket = null;
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeListExt2.平安资产类型_外汇即期.getValue();
        }
        // 外汇远期
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeListExt2.平安资产类型_外汇远期.getValue())) {
            counterId = inputDto.getCounterId();
            if (inputDto.getCcy().contains(DictCodeList.币种_人民币_CNY.getValue())) {
                if (StringUtil.equals(inputDto.getRemark(), DictCodeList.币种_人民币_CNY.getValue())) {
                    prinAmt = inputDto.getPrinAmt();
                }
                else {
                    prinAmt = inputDto.getFpriceAmt();
                }
            }
            else {
                CommonBusinessService commonBusinessService = SpringContextUtil.getBean(
                    CommonBusinessService.class);
                if (commonBusinessService != null) {
                    prinAmt = commonBusinessService.calExchangeAmt(inputDto.getPrinAmt(),
                        DateUtil.addDay(inputDto.getTradeDate(), -1), inputDto.getCcy(),
                        DictCodeList.币种_人民币_CNY.getValue());
                }
            }
            tradeMarket = null;
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeListExt2.平安资产类型_外汇远期.getValue();
        }
        // 外汇掉期
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeListExt2.平安资产类型_外汇掉期.getValue())) {
            counterId = inputDto.getCounterId();
            if (inputDto.getCcy().contains(DictCodeList.币种_人民币_CNY.getValue())) {
                if (StringUtil.equals(inputDto.getRemark(), DictCodeList.币种_人民币_CNY.getValue())) {
                    prinAmt = inputDto.getTradeAmt();
                }
                else {
                    prinAmt = inputDto.getPrinAmt();
                }
            }
            else {
                CommonBusinessService commonBusinessService = SpringContextUtil.getBean(
                    CommonBusinessService.class);
                if (commonBusinessService != null) {
                    prinAmt = commonBusinessService.calExchangeAmt(inputDto.getPrinAmt(),
                        DateUtil.addDay(inputDto.getTradeDate(), -1), inputDto.getCcy(),
                        DictCodeList.币种_人民币_CNY.getValue());
                }
            }
            tradeMarket = null;
            ps = inputDto.getPs();
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeListExt2.平安资产类型_外汇掉期.getValue();
        }
        // 利率互换
        else if (StringUtil.equals(inputDto.getInputValue1(),
            DictCodeListExt2.平安资产类型_利率互换.getValue())) {
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeListExt2.平安资产类型_利率互换.getValue();
            prinAmt = inputDto.getPrinAmt().multiply(BigDecimal.valueOf(10000));
            counterId = inputDto.getCounterId();
            tradeMarket = null;
            ps = inputDto.getPs();
        }
        // 可转债
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeList.平安资产类型_债券.getValue())) {
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeList.平安资产类型_债券.getValue();
            prinAmt = inputDto.getPrinAmt();
            counterId = inputDto.getCounterId();
            tradeMarket = null;
            ps = DictCodeList.交易方向_买入.getValue();
            finprodId = inputDto.getFinprodId();
        }
        // 债转股
        else if (StringUtil.equals(inputDto.getFinprodType2(),
            DictCodeList.平安资产类型_债券.getValue())) {
            chlId = inputDto.getChlId();
            平安资产类型 = DictCodeList.平安资产类型_债券.getValue();
            oth平安资产类型 = DictCodeListExt2.平安资产类型_股票.getValue();
            prinAmt = ArithmeticUtil.multiply(inputDto.getShareAmt(), new BigDecimal(100));
            counterId = inputDto.getCounterId();
            tradeMarket = null;
            ps = DictCodeList.交易方向_卖出.getValue();

        }
        // 授权额度检查（当本金金额高于副总裁/总裁助理相应资产的授权额度时，提示消息）
        if (prinAmt != null && isNumCheck) {
            CommonBusinessService commonBusinessService = SpringContextUtil.getBean(
                CommonBusinessService.class);
            Map<String, Object> params = CommonBusinessService.setParams(counterId, prinAmt, LoginUserUtil.getUserName(),
            		平安资产类型, null, tradeMarket, ps,finprodId, null);
            if (commonBusinessService != null && commonBusinessService.是否超过副总额额度(params)) {
                return true;
            }
        }
        // 其他交易信息（例:债转股）
        // if (prinAmt != null && othTrdProductDeal!=null) {
        // CommonBusinessService commonBusinessService =
        // SpringContextUtil.getBean(CommonBusinessService.class);
        // if (commonBusinessService != null && commonBusinessService.是否超过副总额额度(counterId,
        // othTrdProductDeal.getPrinAmt(), oth平安资产类型, windtypeone, tradeMarket,
        // othTrdProductDeal.getPs()
        // // 资产类型改为授权类型 add by renping 2019-08-06 start
        // , othTrdProductDeal.getFinprodId()
        // // 资产类型改为授权类型 add by renping 2019-08-06 end
        // )) {
        // // 交易本金金额超过副总裁/总裁助理授权额度，无法发起交易!
        // ValidMessage validMessage = new ValidMessage(getMessage("TRD_E_0016", "副总裁/总裁助理"));
        // validMessageList.add(validMessage);
        // }
        // }
        // ----add by jjpei 20190710----授权开头控制----end----

        return false;
    }
}
