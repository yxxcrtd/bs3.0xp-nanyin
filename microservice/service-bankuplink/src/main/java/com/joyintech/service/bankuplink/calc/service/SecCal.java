package com.joyintech.service.bankuplink.calc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.cib.sam.algo.sec.DisSec;
import com.cib.sam.algo.sec.FiSec;
import com.cib.sam.algo.sec.FlSec;
import com.cib.sam.algo.sec.IAMSec;
import com.cib.sam.algo.sec.ZCOSec;
import com.cib.sam.algo.sec.model.AlgInterScheduleBond;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.bankuplink.calc.dto.SecCalInfoModel;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 类名：SecCal.java <br>
 * 说明：债券相关的算法类<br>
 * 备注：<br>
 * 作者：lvlei
 */
public class SecCal {

    /**
     * 二分法的精度
     */
    BigDecimal precision = new BigDecimal(1e-8);

    /**
     * 说明：设置二分法的精度<br>
     * 备注：<br>
     * 
     * @param precision 精度到小数点后几位
     */
    public void setPrecision(BigDecimal precision) {
        this.precision = precision;
    }

    /**
     * 计算未来现金流，对非付息券券，返回null
     * 
     * @param cdate 计算日
     * @param SaSecscheduleVO 债券的付息计划表
     * @param couponspecies 息票品种
     * @param interestfrequency 付息频率
     * @param basis 计息基础
     * @return 第一个元素为现金流的值，第二个元素为现金流剩余期限
     */
    public double[][] getForwadCashFlow(String cdate, List<FinCashPlan> listSecScheduleModel,
                                        String couponspecies, Integer interestfrequency,
                                        String basis) {

        // 未来现金流数组
        double[][] fwdEncashYearAndCashflow = null;
        // 按照债券类型分别计算未来现金流
        if (StringUtil.equals(BussinessDictCodeList.息票品种_零息.getValue(), couponspecies)) {
            // fwdEncashYearAndCashflow = ZCOSec.encashYearsAndCashFlow(cdate,
            // list_interScheduleBond);
        }
        else if (StringUtil.equals(BussinessDictCodeList.息票品种_贴现.getValue(), couponspecies)) {
            // fwdEncashYearAndCashflow = DisSec.encashYearsAndCashFlow(cdate,
            // list_interScheduleBond);
        }
        else if (StringUtil.equals(BussinessDictCodeList.息票品种_利随本清.getValue(), couponspecies)) {
            // fwdEncashYearAndCashflow = IAMSec.encashYearsAndCashFlow(cdate,
            // list_interScheduleBond);
        }
        else {
            // 类型转换，转换为计算对应的类型
            List<AlgInterScheduleBond> list_interScheduleBond = SecCalUtil.convertSecScheduleModelToAlgInterScheduleBond(
                listSecScheduleModel);

            fwdEncashYearAndCashflow = FiSec.encashYearsAndCashFlow(cdate, list_interScheduleBond,
                interestfrequency, basis);
        }

        return fwdEncashYearAndCashflow;
    }

    /**
     * 计算债券应计利息（不考虑债券还本，当前留给债券交易使用，后续准备作废）
     * 
     * @param cdate 计算日
     * @param listSecScheduleModel 债券的付息计划表
     * @param price 发行价
     * @param secInfoModel 债券基本资料
     * @return 债券应计利息
     */
    public BigDecimal calAccurInt(String cdate, List<FinCashPlan> listSecScheduleModel,
                                  SecCalInfoModel secInfoModel) {
        // 类型转换，转换为计算对应的类型
        List<AlgInterScheduleBond> list_interScheduleBond = SecCalUtil.convertSecScheduleModelToAlgInterScheduleBond(
            listSecScheduleModel);

        BigDecimal accurInt = calAccurIntExt(cdate, list_interScheduleBond, secInfoModel);

        /**
         * 需要对付息債情况下，对应计利息进行调整 因为在调用算法计算出来的应计利息没有考虑到还本的情况 1、找到计算日对应的付息区间 2、调整后应计利息 =
         * 调整前应计利息*计息本金/100
         *//*
           * if (BaseConstantsSam.COUPONSPECIES_NPV.equals(secInfoModel .getCouponspecies())){ //
           * 找到计算日位于哪一个付息区间 SaSecscheduleVO saSecscheduleVO = null; for (SaSecscheduleVO
           * intersaSecscheduleVO : listSecScheduleModel) { // 找到计算日位于起始日和结束日之间的周期 if
           * (cdate.compareTo(DateUtils.convertDateToString(intersaSecscheduleVO.
           * getIntereststartday())) > 0 &&
           * cdate.compareTo(DateUtils.convertDateToString(intersaSecscheduleVO.getInterestendday()
           * )) < 0) { saSecscheduleVO = intersaSecscheduleVO; break; } } if(saSecscheduleVO !=
           * null){ accurInt =
           * accurInt.multiply(saSecscheduleVO.getPrinamt()).divide(BaseConstantsSam.ONE_HUNDRED);
           * } }
           */

        return accurInt;
    }

    /**
     * 计算债券应计利息(市场通用做法，考虑债券还本)
     * 
     * @author wot_dongenkuo
     * @param cdate 计算日
     * @param listSecScheduleModel 债券的付息计划表
     * @param secInfoModel 债券基本资料
     * @return 债券应计利息
     */
    public BigDecimal calAccurIntForMarket(String cdate, List<FinCashPlan> listSecScheduleModel,
                                           SecCalInfoModel secInfoModel) {
        // 类型转换，转换为计算对应的类型
        List<AlgInterScheduleBond> list_interScheduleBond = SecCalUtil.convertSecScheduleModelToAlgInterScheduleBond(
            listSecScheduleModel);

        BigDecimal accurInt = calAccurIntExt(cdate, list_interScheduleBond, secInfoModel);

        /**
         * 需要对付息債情况下，对应计利息进行调整 因为在调用算法计算出来的应计利息没有考虑到还本的情况 1、找到计算日对应的付息区间 2、调整后应计利息 =
         * 调整前应计利息*计息本金/100
         */
        // if (BussinessDictCodeList.息票品种_附息.getValue().equals(secInfoModel
        // .getCouponspecies())){
        // // 找到计算日位于哪一个付息区间
        // FinCashPlan saSecscheduleVO = null;
        // for (FinCashPlan intersaSecscheduleVO : listSecScheduleModel) {
        // // 找到计算日位于起始日和结束日之间的周期 这里为什么没有等于号，需要debug下看看
        // // 没有等于号的话，找不到付息计划说明计算日等于付息日，利息为0
        // if (cdate.compareTo(DateUtil.format(intersaSecscheduleVO.getVdate())) > 0
        // && cdate.compareTo(DateUtil.format(intersaSecscheduleVO.getMdate())) < 0) {
        // saSecscheduleVO = intersaSecscheduleVO;
        // break;
        // }
        // }
        // if(saSecscheduleVO != null){
        // accurInt =
        // accurInt.multiply(secInfoModel.getFacevalue()).divide(SecCalUtil.ONE_HUNDRED);
        // }
        // }
        accurInt = accurInt.multiply(secInfoModel.getFacevalue()).divide(SecCalUtil.ONE_HUNDRED);

        return accurInt;
    }

    /**
     * 计算债券应计利息
     * 
     * @param cdate 计算日
     * @param list_interScheduleBond 债券的付息计划表
     * @param price 发行价
     * @param secInfoModel 债券基本资料
     * @return 债券应计利息
     */
    public BigDecimal calAccurIntExt(String cdate,
                                     List<AlgInterScheduleBond> list_interScheduleBond,
                                     SecCalInfoModel secInfoModel) {
        double accurInt = 0;
        // 计算应计利息
        String firstrateday = DateUtil.format(
            secInfoModel.getFirstrateday() == null ? secInfoModel.getVdate() : secInfoModel.getFirstrateday());
        // 按照债券类型分别计算应计利息
        if (BussinessDictCodeList.息票品种_零息.getValue().equals(secInfoModel.getCouponspecies())) {
            accurInt = ZCOSec.accureirByPrice(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate,
                secInfoModel.getIssueprice().doubleValue(),
                secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_贴现.getValue().equals(
            secInfoModel.getCouponspecies())) {
            accurInt = DisSec.accureirByPrice(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate,
                secInfoModel.getIssueprice().doubleValue(),
                secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_利随本清.getValue().equals(
            secInfoModel.getCouponspecies())) {
            accurInt = IAMSec.accureirByPaperIr(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate,
                secInfoModel.getPaperir().doubleValue(), secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue(), firstrateday);
        }
        else {

            // 如果是固定利率
            if (BussinessDictCodeList.利率类型_固定利率.getValue().equals(
                secInfoModel.getInterestrate())) {
                // 计算应计利息
                accurInt = FiSec.accureirByPaperIrAndSchedule(list_interScheduleBond, cdate,
                    secInfoModel.getInterestfrequency(), secInfoModel.getRatebasic(),
                    secInfoModel.getIntpayrule(), secInfoModel.getFacevalue().doubleValue(),
                    firstrateday);
            }
            // 如果是浮动利率
            else {
                // 计算应计利息
                accurInt = FlSec.accureirByPaperIrForSimpleSche(list_interScheduleBond, cdate,
                    secInfoModel.getInterestfrequency(), secInfoModel.getRatebasic(),
                    secInfoModel.getIntpayrule(), secInfoModel.getFacevalue().doubleValue(),
                    firstrateday);
            }

        }
        return new BigDecimal(accurInt);
    }

    /**
     * 根据全价计算收益率
     * 
     * @param cdate 计算日
     * @param fwdEncashYearAndCashflow 付未来现金流数组，对非付息券可为null
     * @param price 全价
     * @param secInfoModel 债券基本资料
     * @return
     */
    public BigDecimal ytmByPrice(String cdate, double[][] fwdEncashYearAndCashflow, double price,
                                 SecCalInfoModel secInfoModel) {
        double douYield = 0;
        // 按照债券类型分别计算到期收益率
        if (BussinessDictCodeList.息票品种_零息.getValue().equals(secInfoModel.getCouponspecies())) {
            douYield = ZCOSec.ytmByPrice(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, price,
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_贴现.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douYield = DisSec.ytmByPrice(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, price,
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_利随本清.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douYield = IAMSec.yieldByFullPrice(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, price,
                secInfoModel.getPaperir().doubleValue(), secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue(), DateUtil.format(
                    secInfoModel.getFirstrateday() == null ? secInfoModel.getVdate() : secInfoModel.getFirstrateday()));
        }
        else {
            // 固息或浮息
            // 根据未来现金流和全价计算到期收益率
            douYield = FiSec.ytmByPrice(fwdEncashYearAndCashflow[0], fwdEncashYearAndCashflow[1],
                price, secInfoModel.getInterestfrequency());
        }

        return new BigDecimal(douYield);
    }

    /**
     * 根据收益率计算全价
     * 
     * @param cdate 计算日
     * @param fwdEncashYearAndCashflow 未来现金流数组，对非付息券可为null
     * @param ytm 收益率
     * @param secInfoModel 债券基本资料
     * @return
     */
    public BigDecimal priceByYtm(String cdate, double[][] fwdEncashYearAndCashflow, double ytm,
                                 SecCalInfoModel secInfoModel) {
        double douPrice = 0;
        // 按照债券类型分别计算价格
        if (BussinessDictCodeList.息票品种_零息.getValue().equals(secInfoModel.getCouponspecies())) {
            douPrice = ZCOSec.priceByYtm(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, ytm, secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_贴现.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douPrice = DisSec.priceByYtm(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, ytm, secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue());
        }
        else if (BussinessDictCodeList.息票品种_利随本清.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douPrice = IAMSec.fullPriceByYield(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, ytm,
                secInfoModel.getPaperir().doubleValue(), secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue(), DateUtil.format(
                    secInfoModel.getFirstrateday() == null ? secInfoModel.getVdate() : secInfoModel.getFirstrateday()));
        }
        else {
            // 固息或浮息
            // 根据未来现金流和全价计算到期收益率
            douPrice = FiSec.priceByYtm(fwdEncashYearAndCashflow[0], fwdEncashYearAndCashflow[1],
                ytm, secInfoModel.getInterestfrequency());
        }

        return new BigDecimal(douPrice);
    }

    /**
     * 计算当前交易成本净价 还本按照卖出处理
     * 
     * @param BigDecimal[] 上一个净价以及对应的头寸
     * @param cleanprice 交易净价
     * @param prinamt 交易头寸
     * @param yield 到期收益率
     * @param ps 本次交易方向
     * @param iPrinamt 本次交易头寸
     * @param iCleanprice 本次交易净价
     * @param iYield 本次到期收益率
     * @return 返回当前净价以及剩余头寸
     * @throws ServiceException
     */
    public BigDecimal[] calCleanPrice(BigDecimal prinamt, BigDecimal cleanprice, BigDecimal yield,
                                      String ps, BigDecimal iPrinamt, BigDecimal iCleanprice,
                                      BigDecimal iYield) {
        BigDecimal returnPrinamt = prinamt;
        BigDecimal returnCleanprice = cleanprice;
        BigDecimal returnYield = yield;
        /**
         * 计算返回头寸:买入相加 卖出相减
         */
        if (ps.equals(BussinessDictCodeList.交易方向_卖出.getValue())) {
            returnPrinamt = returnPrinamt.subtract(iPrinamt);
        }
        else {
            returnPrinamt = returnPrinamt.add(iPrinamt);
        }

        /**
         * 计算返回净价:如果卖空则返回0；否则与上一个数据进行平均
         */
        if (prinamt.compareTo(BigDecimal.ZERO) <= 0) {
            // 如果目前没有存量头寸
            if (ps.equals(BussinessDictCodeList.交易方向_卖出.getValue())) {
                returnCleanprice = BigDecimal.ZERO;
                returnCleanprice = BigDecimal.ZERO;
            }
            else {
                // 如果既有头寸为负数，需要判断本次买入是否大于负数的绝对值
                if (prinamt.add(iPrinamt).compareTo(BigDecimal.ZERO) > 0) {
                    returnCleanprice = iCleanprice;
                    returnYield = iYield;
                }
                else {
                    returnCleanprice = BigDecimal.ZERO;
                    returnYield = BigDecimal.ZERO;
                }
            }
        }
        else {
            // 如果有头寸
            if (ps.equals(BussinessDictCodeList.交易方向_卖出.getValue())) {
                if (prinamt.subtract(iPrinamt).compareTo(BigDecimal.ZERO) > 0) {
                    // 如果卖出以后还有头寸，则不影响净价
                    returnCleanprice = cleanprice;
                    returnYield = yield;
                }
                else {
                    // 如果卖空，则净价为0
                    returnCleanprice = BigDecimal.ZERO;
                    returnYield = BigDecimal.ZERO;
                }
            }
            else {
                // 净价=（上次净价*上次头寸+本次净价*本次头寸）/(上次头寸+本次头寸)
                returnCleanprice = prinamt.multiply(cleanprice).add(
                    iPrinamt.multiply(iCleanprice)).divide(prinamt.add(iPrinamt),
                        SecCalUtil.SCAL_PRICE, RoundingMode.HALF_UP);

                // 收益率=（上次收益率*上次头寸+本次收益率*本次头寸）/(上次头寸+本次头寸)
                returnYield = prinamt.multiply(yield).add(iPrinamt.multiply(iYield)).divide(
                    prinamt.add(iPrinamt), SecCalUtil.SCAL_PRICE, RoundingMode.HALF_UP);
            }
        }
        return new BigDecimal[] {returnPrinamt, returnCleanprice, returnYield};
    }

    // delete by zhangliang 20200519 start
    /**
     * 根据交易数据计算持有期利息
     * 
     * @param id 交易编号
     * @return
     * @throws ServiceException
     */
    // 此方法如修改请修改后台11号批处理的SecCal的calCYQLX方法
    // public BigDecimal calCYQLX(List<SaLxdQylDealVO> listdealVO) throws Exception {
    // try {
    // //持有期利息
    // BigDecimal cyqlx = BigDecimal.ZERO;
    // //剩余持仓
    // BigDecimal restPrinamt = BigDecimal.ZERO;
    // //买入单位应计利息
    // BigDecimal buyAccuir = BigDecimal.ZERO;
    // //冲买入应计利息
    // BigDecimal changeAccuirAmt = BigDecimal.ZERO;
    // for(int i=0;i<listdealVO.size();i++){
    // SaLxdQylDealVO vo = listdealVO.get(i);
    // //如果剩余持仓小于0。按照0处理
    // if(restPrinamt.compareTo(BigDecimal.ZERO)<=0){
    // restPrinamt = BigDecimal.ZERO;
    // }
    // if(vo.getPs().equals(BussinessDictCodeList.交易方向_买入.getValue())){
    // //买入单位应计利息 = （原剩余持仓*原单位应计利息 + 应计利息）/（原剩余持仓 + 本次买入券面）
    // buyAccuir = restPrinamt.multiply(buyAccuir).add(
    // vo.getAccuiramt()).divide(
    // restPrinamt.add(vo.getPrinamt()),
    // SecCalUtil.SCAL_PRICE_SEC, RoundingMode.HALF_UP);
    // //冲买入应计利息 买入不变
    // changeAccuirAmt = BigDecimal.ZERO;
    // //剩余持仓 买入增加
    // restPrinamt = restPrinamt.add(vo.getPrinamt());
    // //持有期利息 买入为0
    // cyqlx = BigDecimal.ZERO;
    // }else if(vo.getPs().equals(BussinessDictCodeList.交易方向_卖出.getValue())){
    // //卖出时：单位应计利息不变
    //// buyAccuir = buyAccuir;
    // //冲买入应计利息 卖出时=交易面额*买入单位应计利息
    // changeAccuirAmt = buyAccuir.multiply(vo.getPrinamt());
    // //剩余持仓 卖出减少
    // restPrinamt = restPrinamt.subtract(vo.getPrinamt());
    // //持有期利息 = 应计利息 - 冲买入应计利息
    // cyqlx = vo.getAccuiramt().subtract(changeAccuirAmt);
    // }else if(vo.getPs().equals("HB")){
    // //还本时：单位应计利息不变
    //// buyAccuir = buyAccuir;
    // //冲买入应计利息 还本时=交易面额*买入单位应计利息
    // changeAccuirAmt = buyAccuir.multiply(vo.getPrinamt());
    // //剩余持仓 还本减少
    // restPrinamt = restPrinamt.subtract(vo.getPrinamt());
    // //持有期利息 = 应计利息 - 冲买入应计利息
    // cyqlx = vo.getAccuiramt().subtract(changeAccuirAmt);
    // }else if(vo.getPs().equals("SX")){
    // //收息时：单位应计利息不变
    //// buyAccuir = buyAccuir;
    // //冲买入应计利息 收息时=交易面额（取收息对应的资产头寸）*买入单位应计利息
    // changeAccuirAmt = buyAccuir.multiply(vo.getPrinamt());
    // //剩余持仓 收息不变
    //// restPrinamt = restPrinamt;
    // //持有期利息 = 应计利息 - 冲买入应计利息
    // cyqlx = vo.getAccuiramt().subtract(changeAccuirAmt);
    // }
    // }
    // //返回持有期利息：保存两位小数
    // return cyqlx.setScale(SecCalUtil.SCAL_CASH, RoundingMode.HALF_UP);
    // } catch (Exception e) {
    // throw new Exception(e);
    // }
    // }
    // delete by zhangliang 20200519 end
//
//    /**
//     * 根据票面利率重新计算应计利息
//     * 
//     * @param secInfoVO 债券基本信息
//     * @param saSecscheduleVO 债券付息计划
//     * @return 应计利息
//     */
//    public BigDecimal calSingleSecScheduleModel(SecCalInfoModel secInfoVO,
//                                                FinCashPlan finCashPlan) {
//        // 应计利息总额
//        double accrueintamt = 0;
//        // 息票品种为固定利率
//        if (BussinessDictCodeList.息票品种_附息.getValue().equals(secInfoVO.getCouponspecies())) {
//            if (BussinessDictCodeList.利率类型_固定利率.getValue().equals(secInfoVO.getInterestrate())) {
//                accrueintamt = FiSec.accureirByPaperIrForSche(
//                    DateUtil.format(finCashPlan.getVdateTerm()),
//                    DateUtil.format(finCashPlan.getMdateTerm()),
//                    DateUtil.format(finCashPlan.getVdate()),
//                    DateUtil.format(finCashPlan.getMdate()), secInfoVO.getPaperir().doubleValue(),
//                    secInfoVO.getInterestfrequency(), secInfoVO.getRatebasic(),
//                    secInfoVO.getIntpayrule(), secInfoVO.getFacevalue().doubleValue(),
//                    DateUtil.format(
//                        secInfoVO.getFirstrateday() == null ? secInfoVO.getVdate() : secInfoVO.getFirstrateday()));
//            }
//            else {
//                accrueintamt = FlSec.accureirByPaperIrForSche(
//                    DateUtil.format(finCashPlan.getVdateTerm()),
//                    DateUtil.format(finCashPlan.getMdateTerm()),
//                    DateUtil.format(finCashPlan.getVdate()),
//                    DateUtil.format(finCashPlan.getMdate()), secInfoVO.getPaperir().doubleValue(),
//                    secInfoVO.getInterestfrequency(), secInfoVO.getRatebasic(),
//                    secInfoVO.getIntpayrule(), secInfoVO.getFacevalue().doubleValue(),
//                    DateUtil.format(
//                        secInfoVO.getFirstrateday() == null ? secInfoVO.getVdate() : secInfoVO.getFirstrateday()));
//            }
//        }
//        else {
//            return finCashPlan.getAccrueintamt();
//        }
//        return BigDecimal.valueOf(accrueintamt);
//    }
//
//    /**
//     * 针对有还本情况的债券，考虑其还本后的数据，需要重新生成器付息计划 （不考虑还本情况）
//     * 
//     * @param secInfoModel
//     * @param List saSecscheduleVO
//     * @return
//     */
//    public List<FinCashPlan> calListSecScheduleModel(SecCalInfoModel secInfoVO,
//                                                         List<FinCashPlan> saSecscheduleList) {
//        List<FinCashPlan> listResult = new ArrayList<FinCashPlan>();
//
//        for (int i = 0; i < saSecscheduleList.size(); i++ ) {
//            FinCashPlan vo = new FinCashPlan();
//            BeanUtil.copyPropertiesIgnoreNull(vo, saSecscheduleList.get(i));
//            /**
//             * 应计利息和
//             */
//            BigDecimal accrueintamt = this.calSingleSecScheduleModel(secInfoVO, vo);
//            vo.setAccrueintamt(accrueintamt);
//            /**
//             * 支付本金
//             */
//            if (i == saSecscheduleList.size() - 1) {
//                // 如果是最后一条记录
//                /**
//                 * modi by donghao at 2017-03-31 面值非定值100 取 faceValue
//                 */
//                // vo.setPrinamtpay(BaseConstantsSam.ONE_HUNDRED);
//                vo.setPrinamtpay(secInfoVO.getFacevalue());
//            }
//            else {
//                vo.setPrinamtpay(BigDecimal.ZERO);
//            }
//            /**
//             * 支付现金流
//             */
//            vo.setCashpay(vo.getAccrueintamt().add(vo.getPrinamtpay()));
//            /**
//             * 计息本金
//             */
//            /**
//             * modi by donghao at 2017-03-31 面值非定值100 取 faceValue
//             */
//            // vo.setPrinamt(BaseConstantsSam.ONE_HUNDRED);
//            vo.setPrinamt(secInfoVO.getFacevalue());
//            listResult.add(vo);
//        }
//
//        return listResult;
//    }
//    
//    /**
//     * 重新赋值付息计划
//     * 
//     * @param secInfoModel
//     * @param List saSecscheduleVO
//     * @return
//     */
//    public SaSecscheduleVO CopySaSecscheduleVO(SaSecscheduleVO saSecschedule) {
//        SaSecscheduleVO record = new SaSecscheduleVO();
//        record.setId(saSecschedule.getId());
//        record.setPseqno(saSecschedule.getPseqno());
//        record.setSeccode(saSecschedule.getSeccode());
//        record.setSchedulenum(saSecschedule.getSchedulenum());
//        record.setTotalintereststartday(saSecschedule.getTotalintereststartday());
//        record.setTotalinterestendday(saSecschedule.getTotalinterestendday());
//        record.setIntereststartday(saSecschedule.getIntereststartday());
//        record.setInterestendday(saSecschedule.getInterestendday());
//        record.setInterestpayday(saSecschedule.getInterestpayday());
//        record.setCouponrate(saSecschedule.getCouponrate());
//        record.setAccrueintamt(saSecschedule.getAccrueintamt());
//        record.setPrinamtpay(saSecschedule.getPrinamtpay());
//        record.setCashpay(saSecschedule.getCashpay());
//        record.setPrinamt(saSecschedule.getPrinamt());
//        record.setBasicrate(saSecschedule.getBasicrate());
//        record.setCoefficient(saSecschedule.getCoefficient());
//        record.setSpreadrate8(saSecschedule.getSpreadrate8());
//        record.setEffectflag(saSecschedule.getEffectflag());
//        record.setCreatorid(saSecschedule.getCreatorid());
//        record.setCreatedtime(saSecschedule.getCreatedtime());
//        record.setLastmodifierid(saSecschedule.getLastmodifierid());
//        record.setLastmodifiedtime(saSecschedule.getLastmodifiedtime());
//
//        return record;
//    }
    // delete by zhangliang 20200519 end

    /**
     * 说明：计算债券的久期、凸性、pvbp<br>
     * 备注：如果不是浮动利率债券，计算久期、凸性、pvbp<BR>
     * 如果是浮动利率债券，计算利率久期、利差久期、利率凸性、利差凸性、pvbp<BR>
     * 
     * @param cdate 计算日
     * @param fwdEncashYearAndCashflow 未来现金流（付息券时传入，其他券可传空）
     * @param secInfoModel 债券基本资料
     * @param dprice 价格（非付息券时传入，付息券可传空）
     * @param yield 收益率（付息券时传入，其他券可传空）
     * @return 每个元素依次为：<BR>
     *         如果不是浮动利率债券，计算久期、凸性、pvbp<BR>
     *         如果是浮动利率债券，计算利率久期、利差久期、利率凸性、利差凸性、pvbp<BR>
     */
    public BigDecimal[] durAndPvbp(String cdate, double[][] fwdEncashYearAndCashflow,
                                   SecCalInfoModel secInfoModel, BigDecimal dprice,
                                   BigDecimal yield) {
        /**
         * 如果不是浮动利率债券，计算久期(修正久期)、凸性、pvbp<BR>
         * 如果是浮动利率债券，计算利率久期、利率凸性、利差久期、利差凸性、pvbp<BR>
         */
        // 久期凸性
        double[] durs = null;
        // pvbp
        double pvbp = 0;
        // 返回值
        BigDecimal[] result = null;
        // 按照债券类型分别计算到期收益率
        // 贴现
        if (BussinessDictCodeList.息票品种_贴现.getValue().equals(secInfoModel.getCouponspecies())) {
            durs = DisSec.duration(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());
            pvbp = DisSec.PVBP(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());
        }
        // 零息
        else if (BussinessDictCodeList.息票品种_零息.getValue().equals(
            secInfoModel.getCouponspecies())) {
            durs = ZCOSec.duration(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());
            pvbp = ZCOSec.PVBP(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getRatebasic(), secInfoModel.getFacevalue().doubleValue());

        }
        // 利随本清
        else if (BussinessDictCodeList.息票品种_利随本清.getValue().equals(
            secInfoModel.getCouponspecies())) {
            durs = IAMSec.duration(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getPaperir().doubleValue(), secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue(), DateUtil.format(
                    secInfoModel.getFirstrateday() == null ? secInfoModel.getVdate() : secInfoModel.getFirstrateday()));
            pvbp = IAMSec.PVBP(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, dprice.doubleValue(),
                secInfoModel.getPaperir().doubleValue(), secInfoModel.getRatebasic(),
                secInfoModel.getFacevalue().doubleValue(), DateUtil.format(
                    secInfoModel.getFirstrateday() == null ? secInfoModel.getVdate() : secInfoModel.getFirstrateday()));
        }
        // 付息
        else {

            // 如果是固定利率
            if (BussinessDictCodeList.利率类型_固定利率.getValue().equals(
                secInfoModel.getInterestrate())) {

                // 计算久期和凸性
                durs = FiSec.durationByFwdCashflow(yield.doubleValue(),
                    fwdEncashYearAndCashflow[0], fwdEncashYearAndCashflow[1],
                    secInfoModel.getInterestfrequency());
                pvbp = FiSec.getCashflowPVPB(yield.doubleValue(), fwdEncashYearAndCashflow[0],
                    fwdEncashYearAndCashflow[1], secInfoModel.getInterestfrequency());
            }
            // 如果是浮动利率
            else {
                // 计算利率利差久期凸性
                durs = FlSec.float_duration(yield.doubleValue(), fwdEncashYearAndCashflow[0],
                    fwdEncashYearAndCashflow[1], secInfoModel.getInterestfrequency(),
                    secInfoModel.getRatecode());
                pvbp = FlSec.getCashflowPVPB(yield.doubleValue(), fwdEncashYearAndCashflow[0],
                    fwdEncashYearAndCashflow[1], secInfoModel.getInterestfrequency());
            }

        }

        // 如果为浮息债
        if (BussinessDictCodeList.息票品种_附息.getValue().equals(secInfoModel.getCouponspecies())
            && BussinessDictCodeList.利率类型_浮动利率.getValue().equals(secInfoModel.getInterestrate())) {
            result = new BigDecimal[5];
            if (Double.isNaN(durs[0])) {
                // 利率久期
                result[0] = BigDecimal.ZERO;
            }
            else {
                // 利率久期
                result[0] = BigDecimal.valueOf(durs[0]);
            }

            if (Double.isNaN(durs[1])) {
                // 利率凸性
                result[1] = BigDecimal.ZERO;
            }
            else {
                // 利率凸性
                result[1] = BigDecimal.valueOf(durs[1]);
            }

            if (Double.isNaN(durs[2])) {
                // 利差久期
                result[2] = BigDecimal.ZERO;
            }
            else {
                // 利差久期
                result[2] = BigDecimal.valueOf(durs[2]);
            }
            if (Double.isNaN(durs[3])) {
                // 利差凸性
                result[3] = BigDecimal.ZERO;
            }
            else {
                // 利差凸性
                result[3] = BigDecimal.valueOf(durs[3]);
            }

            if (Double.isNaN(pvbp)) {
                // PVBP
                result[4] = BigDecimal.ZERO;
            }
            else {
                // PVBP
                result[4] = BigDecimal.valueOf(pvbp);
            }
        }
        // 非浮息债
        else {

            result = new BigDecimal[3];
            if (Double.isNaN(durs[1])) {
                // 修正久期
                result[0] = BigDecimal.ZERO;
            }
            else {
                // 修正久期
                result[0] = BigDecimal.valueOf(durs[1]);
            }
            if (Double.isNaN(durs[2])) {
                // 凸性
                result[1] = BigDecimal.ZERO;
            }
            else {
                // 凸性
                result[1] = BigDecimal.valueOf(durs[2]);
            }
            if (Double.isNaN(pvbp)) {
                // PVBP
                result[2] = BigDecimal.ZERO;
            }
            else {
                // PVBP
                result[2] = BigDecimal.valueOf(pvbp);
            }

        }

        return result;
    }

    /**
     * 计算债券的剩余期限
     * 
     * @param add by zhangyaling 20170606 投资池试算界面取剩余期限作为久期，算法从批处理算法文件复制过来的begin 计算日
     * @param secInfoModel 债券基本资料
     * @return
     */
    public BigDecimal getEncashYears(String cdate, SecCalInfoModel secInfoModel) {
        double douYield = 0;
        // 按照债券类型分别计算到期收益率
        if (BussinessDictCodeList.息票品种_零息.getValue().equals(secInfoModel.getCouponspecies())) {
            douYield = ZCOSec.encashYears(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, secInfoModel.getRatebasic());
        }
        else if (BussinessDictCodeList.息票品种_贴现.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douYield = DisSec.encashYears(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, secInfoModel.getRatebasic());
        }
        else if (BussinessDictCodeList.息票品种_利随本清.getValue().equals(
            secInfoModel.getCouponspecies())) {
            douYield = IAMSec.encashYears(DateUtil.format(secInfoModel.getVdate()),
                DateUtil.format(secInfoModel.getMdate()), cdate, secInfoModel.getRatebasic());
        }
        else {
            // 固息
            if (BussinessDictCodeList.利率类型_固定利率.getValue().equals(
                secInfoModel.getInterestrate())) {
                douYield = FiSec.encashYears(DateUtil.format(secInfoModel.getVdate()),
                    DateUtil.format(secInfoModel.getMdate()), cdate,
                    secInfoModel.getInterestfrequency(), secInfoModel.getRatebasic());
            }
            // 浮息
            else {
                douYield = FlSec.encashYears(DateUtil.format(secInfoModel.getVdate()),
                    DateUtil.format(secInfoModel.getMdate()), cdate,
                    secInfoModel.getInterestfrequency(), secInfoModel.getRatebasic());
            }

        }

        return BigDecimal.valueOf(douYield);
    }
    // add by zhangyaling 20170606 投资池试算界面取剩余期限作为久期，算法从批处理算法文件复制过来的end

}
