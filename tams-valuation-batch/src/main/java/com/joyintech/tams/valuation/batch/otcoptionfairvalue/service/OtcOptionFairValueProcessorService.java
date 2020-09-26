/*
 * 文件名：OtcOptionFairValueProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.otcoptionfairvalue.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2020年1月2日
 * @see OtcOptionFairValueProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OtcOptionFairValueProcessorService {
    
    /**
     * 公用方法 标的类共通方法 services
     */
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;
    
    /**
     * 公用方法 账务类共通方法 services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;
    
    /**
     * 计算类共通方法service
     */
    @Autowired
    private CalculationCommonService calculationCommonService;
    
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;
    
    public BookGenerateResultWithBokSummary execute(DrawBaseInfoSelectComResult acctEntity,
                                      Map<String, Object> param1){
        
        // 若reader传递的参数或交易明细为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListDrawBaseInfoSelectComDTO() == null || acctEntity.getListDrawBaseInfoSelectComDTO().isEmpty()) {
            return null;
        }
        
        // 定义返回值
        BookGenerateResultWithBokSummary result = new BookGenerateResultWithBokSummary();
        
        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String bokDetailIdNow = acctEntity.getBokDetailId();//账务明细代码
        TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 会计场景
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();  // 入账日
        List<DrawBaseInfoSelectComDTO> listDrawBaseInfoSelectComDTONow = acctEntity.getListDrawBaseInfoSelectComDTO(); // 计提基础信息list
//        Long taskPri = listDrawBaseInfoSelectComDTONow.get(0).getTaskPri();// 任务优先级（信息列表的任务优先级都是一致的，故取第一个）
        
        for(DrawBaseInfoSelectComDTO drawBaseInfoSelectComDTO:listDrawBaseInfoSelectComDTONow) {

            //1.1 变量定义
//            //定义  变量.公允价值变动科目余额  = 0
//            BigDecimal varGyjzbdSubjectAmt = BigDecimal.ZERO;
            //定义   变量.公允价值变动 = 0
            BigDecimal varGyjzbdAmt = BigDecimal.ZERO; 
//            //定义   变量.估值  = 0
//            BigDecimal valAmount = BigDecimal.ZERO;
            
            //1.2 查询标的信息  
            //调用共通设计->【详细设计-标的类共通方法设计.xlsx】->交易信息查询SHEET
            /** 计提类共通未设值BusiDetailType 暂设值固定值**/
            if(StringUtil.isEmpty(acctEntity.getBusiDetailType())) {
                acctEntity.setBusiDetailType(DictCodeListExt3.金融产品类型_期权.getValue());
            }
            /** 计提类共通未设值Branch暂设值固定值**/
            if(acctEntity.getBranch() == null) {
                acctEntity.setBranch(BookConstant.DEFALUT_BRANCH);
            }
            List<TradeInfoDto> listTradeInfoDto = finProductCommonBatchService.getTradeInfoDtoList(acctEntity.getBusiId(), acctEntity.getBusiDetailType(), acctEntity.getBranch());
            if(StringUtil.isEmptyList(listTradeInfoDto)) {
                return null;
            }
            //定义 变量.标的信息 = 上述返回值.get(0);
            TradeInfoDto tradeInfoDto = listTradeInfoDto.get(0);
            //本金币种
            String oCcy = tradeInfoDto.getCcy();
            
            //if(计提类账务处理结果.发生日  < 标的信息.起息日 or 计提类账务处理结果.发生日  >= 标的信息.到期日 or 计提类账务处理结果.科目余额 = 0) return
            if(DateUtil.compareDate(happenDateNow, tradeInfoDto.getVdate())<0
                || DateUtil.compareDate(happenDateNow, tradeInfoDto.getMdate())>=0
                || drawBaseInfoSelectComDTO.getoAmtHappenDate() == null
                || drawBaseInfoSelectComDTO.getoAmtHappenDate().compareTo(BigDecimal.ZERO) == 0) {
                return null;
            }
            
            //1.3 行情处理
            MstValIrs mstValIrs = finProductCommonBatchService.getFundMarketPriceForOtc(happenDateNow, acctEntity.getBusiId());
            //定义 变量.当日期权价格
            BigDecimal currentOtcPrice = null;
            Date valDate = null;
            if(StringUtil.isNotEmpty(mstValIrs)) {
                //变量.当日期权价格 = 每日估值
                currentOtcPrice =  mstValIrs.getValuation();
                valDate = mstValIrs.getValDate();
            }
            
            //1.4 汇总信息查询
            //调用共通 账务类共通方法设计 - 统计分析账务明细汇总查询
            //变量.当日统计分析账务明细汇总信息 = 上述返回值
            BokStatDetPosiiton bokSummaryTableNew = accountingCommonService.getCountAnalysis(happenDateNow,happenDateNow,bookDateNow,booksetIdNow,acctEntity.getBokDetailId(),null,null);
            
//            //1.5.根据金融产品代码查询对应的交易期权费
//            TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(acctEntity.getBusiId());
////            if(acctEntity.getBusiId().equals("JYOPTION201912120025") && !StringUtil.isNotEmpty(trdProductDeal)){
////                trdProductDeal = trdProductDealMapper.selectByPrimaryKey("JYOPTION201912120026");
////            }
//            if(!StringUtil.isNotEmpty(trdProductDeal)) {
//                return null;
//            }

            //定义   变量.期权费 = 变量.交易信息.全价总额
            //BigDecimal QQfeeAmount = trdProductDeal.getFpriceAmt();
            BigDecimal QQfeeAmount = BigDecimal.ZERO;
            //期权费 = NVL(变量.标的信息.期权费,0)
            if(tradeInfoDto != null && tradeInfoDto.getOptFeeAmt() != null) {
                QQfeeAmount = tradeInfoDto.getOptFeeAmt();

            }

            /** add by yanya on 20200209  公允价值变动算法变更，为差额计提，改为减去前一天公允价值变动余额算法 start**/
            //1.5.查询公允价值变动余额
            //定义		变量.公允价值变动科目余额
            BigDecimal 公允价值变动科目余额 = BigDecimal.ZERO;
//            //if（标的信息.起息日 = 计提类账务处理结果.发生日）{	// 第一天为减去期权费
//            if(DateUtil.compareDate(tradeInfoDto.getVdate(), happenDateNow) == 0) {
//                //变量.公允价值变动科目余额	=	NVL(变量.标的信息.期权费,0)
//                公允价值变动科目余额 = QQfeeAmount;
//            } else
            //if（标的信息.外汇交易方向 = "买入"）{
            if(StringUtil.equals(tradeInfoDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
                /**
                    调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    参数名					发生日最新余额
                    发生日					交易类账务处理结果.发生日
                    入账日					交易类账务处理结果.入账日
                    账务明细代码					交易明细.账务明细代码
                    账套代码					交易类账务处理结果.账套代码
                    基础科目性质					买入期权-公允价值变动
                    任务优先级					批处理参数.任务优先级
                    期待余额方向					借方
                    返回值：					DTO
                 **/
                SubjectBalance subjectBalancDto = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow,
                                DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.买入期权公允价值变动.getValue(), drawBaseInfoSelectComDTO.getTaskPri());
                //变量.公允价值变动科目余额 = DTO.科目余额
                if(subjectBalancDto != null) {
                    公允价值变动科目余额 = subjectBalancDto.getBalance();
                }
            }
            else {
            /**
                调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                参数名					发生日最新余额
                发生日					交易类账务处理结果.发生日
                入账日					交易类账务处理结果.入账日
                账务明细代码					交易明细.账务明细代码
                账套代码					交易类账务处理结果.账套代码
                基础科目性质					卖出期权-公允价值变动
                任务优先级					批处理参数.任务优先级
                期待余额方向					贷方
                返回值：					DTO**/
                //变量.公允价值变动科目余额 = DTO.科目余额
                SubjectBalance subjectBalancDto = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow,
                        DictCodeList.余额方向_贷方.getValue(), DictCodeBaseSubjectNatrue.卖出期权公允价值变动.getValue(), drawBaseInfoSelectComDTO.getTaskPri());
                //变量.公允价值变动科目余额 = DTO.科目余额
                if(subjectBalancDto != null) {
                    公允价值变动科目余额 = subjectBalancDto.getBalance();
                }
            }
            /** add by yanya on 20200209 公允价值变动算法变更，为差额计提，改为减去前一天公允价值变动余额算法 end**/

            //2.公允价值变动计算
            //varGyjzbdAmt = ArithmeticUtil.subtract(currentOtcPrice, QQfeeAmount);
            //NVL(变量.当日期权价格,期权费)
            BigDecimal sub1 = BigDecimal.ZERO;
            if(currentOtcPrice != null) {
                sub1 = currentOtcPrice;
            }
            else {
                sub1 = QQfeeAmount;
            }
//            //NVL(变量.公允价值变动科目余额,期权费)
//            BigDecimal sub2 = BigDecimal.ZERO;
//            if(公允价值变动科目余额 != null) {
//                sub2 = 公允价值变动科目余额;
//            }
//            else {
//                sub2 = QQfeeAmount;
//            }
//            varGyjzbdAmt = ArithmeticUtil.subtract(currentOtcPrice, 公允价值变动科目余额);
//            变量.公允价值变动 = NVL(变量.当日期权价格,期权费) - NVL(变量.公允价值变动科目余额,期权费)
            //变量.公允价值变动 = NVL(变量.当日期权价格,期权费) - 期权费 - 变量.公允价值变动科目余额
            varGyjzbdAmt = ArithmeticUtil.doSub(sub1, QQfeeAmount, 公允价值变动科目余额);
            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();  
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();
            
            if(StringUtil.equals(tradeInfoDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
                /** modify by yanya on 20200121 tableId修正 start**/
                //ad.setTableId("期权交易_买入每日估值");
                ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_买入每日估值.getValue());
                /** modify by yanya on 20200121 tableId修正 end**/
            }
            else {
                /** modify by yanya on 20200121 tableId修正 start**/
                //ad.setTableId("期权交易_卖出每日估值");
                ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_卖出每日估值.getValue());
                /** modify by yanya on 20200121 tableId修正 end**/
            }
            // 塞入记账场景树节点 = 会计场景
            ad.setTreeNode(bookSetTreeNodNow);
            
            //accountSceneMap设置
            accountSceneMap = bookCommonLogic.accrualElementAssign(acctEntity, drawBaseInfoSelectComDTO);
            accountSceneMap.put(BookConstant.TYPE_1, tradeInfoDto.getType1());// 分类1 - 变量.交易信息.分类1
            if(StringUtil.equals(tradeInfoDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
                accountSceneMap.put(BookConstant.BOOK_REMARK, "期权交易-买入每日估值");// 备注 
            } else {
                accountSceneMap.put(BookConstant.BOOK_REMARK, "期权交易-卖出每日估值");// 备注
            }
            
            //accountAmountMap设置
            if(BigDecimal.ZERO.compareTo(varGyjzbdAmt) < 0) {
                if(StringUtil.equals(tradeInfoDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
                    //公允价值变动_借 <-变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_D, varGyjzbdAmt);
                    //公允价值变动损益_贷 <- 变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_1_C, varGyjzbdAmt);
                } else {
                    //公允价值变动_贷 <-变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_C, varGyjzbdAmt);
                    //公允价值变动损益_借 <- 变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_1_D, varGyjzbdAmt);
                }
            } 
            else {
                if(StringUtil.equals(tradeInfoDto.getExcPs(), DictCodeList.交易方向_买入.getValue())) {
                    //公允价值变动_贷 <-变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_C, ArithmeticUtil.multiply(varGyjzbdAmt, new BigDecimal(-1)));
                    //公允价值变动损益_借 <- 变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_1_D, ArithmeticUtil.multiply(varGyjzbdAmt, new BigDecimal(-1)));
                } else {
                    //公允价值变动_借 <-变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.multiply(varGyjzbdAmt, new BigDecimal(-1)));
                    //公允价值变动损益_贷 <- 变量.公允价值变动               
                    accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.multiply(varGyjzbdAmt, new BigDecimal(-1)));
                } 
            }
            
            //定义        变量.汇率 = 1;
          //调用计算类方法共通【查询汇率】;
            //BigDecimal eExRate = calculationCommonService.queryExchangeRateForTrade(trdProductDeal.getCcy(), drawBaseInfoSelectComDTO.getStandardCcy(), happenDateNow);
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            BigDecimal eExRate = BigDecimal.ONE;
            if(!oCcy.equals(drawBaseInfoSelectComDTO.getStandardCcy())) {
                List<CurrencyMarketDto> currencyMarketList = finProductCommonBatchService.getCurrencyMarket(bookDateNow, booksetIdNow,
                        drawBaseInfoSelectComDTO.getStandardCcy(), oCcy);
                if (!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null) {
                    eExRate = currencyMarketList.get(0).getClosePrice();
                }
            }
            //定义 本币金额元素Map
            Map<String, BigDecimal> bCyyAccountAmountMap = new HashMap<String, BigDecimal>();
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, oCcy);// value值塞入当前交易币种
                //本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);
                bCyyAccountAmountMap.put(keyNow + BookConstant.BCCYAMT,
                        ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(keyNow), eExRate),NumberEnum.MONEY));
            }
            accountAmountMap.putAll(bCyyAccountAmountMap);
            
            //accountCurrencyMap设置            
            //本位币 <-计提类账务处理结果.本位币                           
            accountCurrencyMap.put(BookConstant.B_CCY, drawBaseInfoSelectComDTO.getStandardCcy());
            
            
            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            result.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
            
            //3.2 统计分析账务明细汇总信息赋值
            if(!StringUtil.isNotEmpty(bokSummaryTableNew)) {
                bokSummaryTableNew = new BokStatDetPosiiton();
                //变量.当日统计分析账务明细汇总信息.当前持仓 =  变量.标的信息.名义本金  //变量.交易信息.交易份额
                bokSummaryTableNew.setPosAmt(tradeInfoDto.getPrin());
                //变量.当日统计分析账务明细汇总信息.当日份额 = 变量.标的信息.名义本金  //变量.交易信息.交易份额
                bokSummaryTableNew.setTdyPosition(tradeInfoDto.getPrin());
                //变量.当日统计分析账务明细汇总信息.投资目的 = 计提类账务处理结果.投资目的
                bokSummaryTableNew.setInvAim(acctEntity.getInvAim());
                //变量.当日统计分析账务明细汇总信息.当日成本 = 变量.标的信息.期权费  //变量.交易信息.全价总额
                bokSummaryTableNew.setTdyCostAmt(tradeInfoDto.getOptFeeAmt());
                //变量.当日统计分析账务明细汇总信息.当日公允价值变动 = 变量.公允价值变动     
                bokSummaryTableNew.setTdyFloatIngpl(varGyjzbdAmt);
                //变量.当日统计分析账务明细汇总信息.当日累计公允价值变动 = 变量.公允价值变动
                bokSummaryTableNew.setTdyFloatIngplExp(varGyjzbdAmt);
                //变量.当日统计分析账务明细汇总信息.金融产品类型 = 变量.标的信息.金融产品类型   
                bokSummaryTableNew.setFinprodType(acctEntity.getBusiDetailType());
                //变量.当日统计分析账务明细汇总信息.起息日 = 变量.标的信息.起息日 
                bokSummaryTableNew.setVdate(tradeInfoDto.getVdate());
                //变量.当日统计分析账务明细汇总信息.到期日 = 变量.标的信息.实际到期日   
                bokSummaryTableNew.setMdate(tradeInfoDto.getMdate());
                //变量.当日统计分析账务明细汇总信息.行情日期 = 变量.估值日期     
                bokSummaryTableNew.setValDate(valDate);
                //变量.当日统计分析账务明细汇总信息.业务明细代码 = 交易类账务处理结果.业务明细代码
                bokSummaryTableNew.setBusiId(acctEntity.getBusiId());
                //变量.当日统计分析账务明细汇总信息.当日公允价值变动_本位币 = 变量.公允价值变动 * 变量.汇率 
                bokSummaryTableNew.setTdyFloatIngplB(ArithmeticUtil.round(calculationCommonService.doMultiply(varGyjzbdAmt, eExRate),NumberEnum.MONEY));
                //变量.当日统计分析账务明细汇总信息.当日累计公允价值变动_本位币 = 变量.公允价值变动 * 变量.汇率                                                                                                            
                bokSummaryTableNew.setTdyFloatIngplExpB(ArithmeticUtil.round(calculationCommonService.doMultiply(varGyjzbdAmt,eExRate),NumberEnum.MONEY));
                // 账套代码
                bokSummaryTableNew.setBooksetId(acctEntity.getBooksetId());
                // 发生日期
                bokSummaryTableNew.setHappenDate(acctEntity.getHappenDate());
                // 入账日期
                bokSummaryTableNew.setBookDate(acctEntity.getBookDate());
                // 账务明细代码
                bokSummaryTableNew.setBokDetailId(acctEntity.getBokDetailId());
                // 账套日期
                bokSummaryTableNew.setBooksetDate(drawBaseInfoSelectComDTO.getBooksetDate());
                // 处理序列
                bokSummaryTableNew.setBookSummaryOrder(
                        new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.账务汇总表_处理序列)));
                bokSummaryTableNew.setCcy(tradeInfoDto.getCcy());
            } else {
                //变量.当日统计分析账务明细汇总信息.当日公允价值变动 = 变量.公允价值变动 
                bokSummaryTableNew.setTdyFloatIngpl(varGyjzbdAmt);
                //变量.当日统计分析账务明细汇总信息.行情日期 = 1.3返回.估值日期
                bokSummaryTableNew.setValDate(valDate);
                //变量.当日统计分析账务明细汇总信息.当日累计公允价值变动 = 变量.当日统计分析账务明细汇总信息.当日累计公允价值变动  + 变量.公允价值变动 
                bokSummaryTableNew.setTdyFloatIngpl(ArithmeticUtil.doAdd(bokSummaryTableNew.getTdyFloatIngpl(),varGyjzbdAmt));
                //变量.当日统计分析账务明细汇总信息.当日公允价值变动_本位币 = 变量.公允价值变动 * 变量.汇率
                bokSummaryTableNew.setTdyFloatIngplB(ArithmeticUtil.round(calculationCommonService.doMultiply(varGyjzbdAmt,eExRate),NumberEnum.MONEY));
                //变量.当日统计分析账务明细汇总信息.当日累计公允价值变动_本位币 = 变量.当日统计分析账务明细汇总信息.当日累计公允价值变动_本位币   + 变量.当日统计分析账务明细汇总信息.当日公允价值变动_本位币
                bokSummaryTableNew.setTdyFloatIngplExpB(ArithmeticUtil.doAdd(bokSummaryTableNew.getTdyFloatIngplExpB(),ArithmeticUtil.round(calculationCommonService.doMultiply(varGyjzbdAmt, eExRate),NumberEnum.MONEY)));
            }
            
            // 添加到返回值中
            result.getBokSummaryList().add(bokSummaryTableNew);
        }
        return result;
    }


    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     *
     * @param booksetId 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDate 发生日
     * @param expBalanceDirect 期待余额方向
     * @param bookDate 入账日
     * @param baseSubjectNature 基础科目性质
     * @param taskPri 任务优先级
     * @return subjectBalance 科目余额信息
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetId, String bokDetailId, Date happenDate, Date bookDate,
                                             String expBalanceDirect, String baseSubjectNature, Long taskPri) {
        // 定义返回实体类
        SubjectBalance subjectBalance = null;
        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }

        // 设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDate);
        searchDto.setBookDate(bookDate);
        searchDto.setTaskPri(taskPri);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询 - 期待方向】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto,
                expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            subjectBalance = subjectBalanceList.get(0);
        }

        return subjectBalance;
    }
}
