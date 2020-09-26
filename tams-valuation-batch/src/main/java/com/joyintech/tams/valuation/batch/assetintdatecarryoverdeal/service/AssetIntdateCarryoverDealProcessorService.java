/*
 * 文件名：AssetIntdateCarryoverProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryoverdeal.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dao.AssetIntdateCarryoverMapper;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverDeal;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderDetail;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderResult;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverResult;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductKey;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;

/**
 * 资产理论结息日结转- processor相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverDealProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDealProcessorService {

    @Autowired
    private AccountingCommonService accountingCommonService;

    @Autowired
    private FinProductCommonService finProductCommonService;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * reader方法的 Service
     */
    @Autowired
    private AssetIntdateCarryoverMapper assetIntdateCarryoverMapper;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    /**
     * process 数据处理
     * 
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public AssetIntdateCarryoverResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        AssetIntdateCarryoverReaderResult acctEntity = (AssetIntdateCarryoverReaderResult)param1.get(
            GlobalConstant.READER_RESULT);

        // 若reader传递的参数或计提基础信息列表为空，不进行后续处理
        if (acctEntity == null || acctEntity.getAssetIntdateCarryoverReaderDetailList() == null
            || acctEntity.getAssetIntdateCarryoverReaderDetailList().isEmpty()) {
            return null;
        }

        // 定义返回值list
        AssetIntdateCarryoverResult assetIntdateCarryoverResult = new AssetIntdateCarryoverResult();

        // 变量.最大分支序号 = 调用共通标的【最大分支序号查询（DTO.金融产品代码，DTO.金融产品类型）】
        Long maxBranch = finProductCommonService.queryValFinProductDealMaxBranch(
            acctEntity.getFinprodId(), acctEntity.getFinprodType());
        if (maxBranch == null) {
            maxBranch = new Long(0);
        }

        // 循环生成数据
        for (AssetIntdateCarryoverReaderDetail detail : acctEntity.getAssetIntdateCarryoverReaderDetailList()) {

            // 变量.本金分支号
            Long branchPri = BookConstant.DEFALUT_BRANCH;
            // 变量.利息分支号
            Long branchInt = BookConstant.DEFALUT_BRANCH;

            // 定义交易方向
            String glob_ps = null;

            // if（DTO.现金流一级类型 = "本金"）
            if (detail.getCashType1().equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
                // 变量.本金分支号 = 调用共通标的【当前计划对应分支序号查询（DTO.金融产品代码，DTO.金融产品类型，"本金"，DTO.调整后的计算结束日）】
                branchPri = finProductCommonService.queryValFinProductDealBranchNow(
                    detail.getFinprodId(), detail.getFinprodType(),
                    DictCodeList.分支类型_应收本金.getValue(), detail.getMdate());
                // 变量.利息分支号 = 调用共通标的【当前计划对应分支序号查询（DTO.金融产品代码，DTO.金融产品类型，"利息"，DTO.调整后的计算结束日）】
                branchInt = finProductCommonService.queryValFinProductDealBranchNow(
                    detail.getFinprodId(), detail.getFinprodType(),
                    DictCodeList.分支类型_应收利息.getValue(), detail.getMdate());

                // 一级类型为本金的时候，交易方向取还本
                glob_ps = DictCodeList.交易方向_还本.getValue();

            }
            else {
                // 变量.利息分支号 = 调用共通标的【当前计划对应分支序号查询（DTO.金融产品代码，DTO.金融产品类型，"利息"，DTO.调整后的计算结束日）】
                branchInt = finProductCommonService.queryValFinProductDealBranchNow(
                    detail.getFinprodId(), detail.getFinprodType(),
                    DictCodeList.分支类型_应收利息.getValue(), detail.getMdate());

                // 其他情况，交易方向取交易方向_收息
                glob_ps = DictCodeList.交易方向_收息.getValue();
            }

            // 1.是否存在已交易生成
            int dealCount = assetIntdateCarryoverMapper.queryDealCount(detail.getFinprodId(),
                detail.getFinprodType(), detail.getMdate(), detail.getBooksetId(), glob_ps);
            if (dealCount > 0) {
                continue;
            }

            // 2.成本查询
            // 2.1基础科目性质查询
            List<String> subjectNatureList = accountingCommonService.getValSubjectNatureList(
                    DictCodeListExt1.分组代码_成本类对应科目查询.getValue());

            // 2.2 最新余额查询
            SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
            searchDao.setHappenDate(detail.getBooksetDate());
            searchDao.setBookDate(detail.getBooksetDate());
            searchDao.setBooksetId(detail.getBooksetId());
            searchDao.setBusiId(detail.getFinprodId());
            searchDao.setOnwayDate(detail.getBooksetDate());
            searchDao.setBaseSubjectNatureList(subjectNatureList);
            List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
            if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                // 3.根据科目余额生成交易
                for (SubjectBalance subjectBalance : listSubjectBalance) {
                    // 变量.利息 = 0
                    BigDecimal intAmt = BigDecimal.ZERO;
                    // 变量.本金 = 0
                    BigDecimal printAmt = BigDecimal.ZERO;
                    // 变量.业务类型
                    String busiType = null;
                    // 变量.交易方向
                    String dealPs = null;
                    // 变量.单位面值 = 0
                    BigDecimal faceValue = BigDecimal.ZERO;

                    // if（变量.科目余额信息.科目余额 = 0）
                    if (ArithmeticUtil.equals(subjectBalance.getBalance(), BigDecimal.ZERO)) {
                        continue;
                    }
                    else {

                        // if(DTO.金融产品类型 = "债券")
                        if (detail.getFinprodType().equals(DictCodeListExt1.金融产品类型_拆出.getValue())||detail.getFinprodType().equals(DictCodeListExt1.金融产品类型_同业借款.getValue())) {

                            // 变量.业务类型 = "拆借预交易处理"
                            busiType = DictCodeList.业务类型_拆借预交易.getValue();
                            // 变量.当前本金 = 调用共通计算类共通【当前未还本金（DTO.账套日期,DTO.金融产品代码,DTO.名义本金）】 
                            BigDecimal printAmtCut = finProductCommonService.getUnpaidPrinAmt(detail.getBooksetDate(), detail.getFinprodId(),detail.getIssueAmt());
                            
                            if (detail.getCashType1().equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
                                //变量.本金 = DTO.计划支付本金/变量.名义本金 ---按单位净值方式保留小数位（ 高精度数字）
                                printAmt = ArithmeticUtil.divide(detail.getPrinAmt(), printAmtCut,NumberEnum.HIGH_PREC_NUM.getCode());
                                //变量.利息 = DTO.计划支付利息/变量.名义本金    ----按单位应计利息方式保留小数位（ 高精度数字）
                                intAmt = ArithmeticUtil.divide(detail.getIntAmt(), printAmtCut,NumberEnum.HIGH_PREC_NUM.getCode());
                                
                            }else{
                                //变量.利息 = DTO.计划支付利息/DTO.名义本金       ----按单位应计利息方式保留小数位（ 高精度数字）
                                intAmt = ArithmeticUtil.divide(detail.getIntAmt(), detail.getIssueAmt(),NumberEnum.HIGH_PREC_NUM.getCode());
                            }
                            
                            // 变量.业务类型 = "债券交易"
                           // busiType = DictCodeList.业务类型_拆借预交易.getValue();
//                            faceValue = finProductCommonService.getFaceValue(detail.getFinprodId(),
//                                DateUtil.addDay(detail.getBooksetDate(), -1));
//                            // if（DTO.现金流一级类型 = "本金"）
//                            if (detail.getCashType1().equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
//                                // 变量.本金 = DTO.计划支付本金
//                                printAmt = detail.getPrinAmt();
//                                // 变量.利息 = DTO.计划支付利息
//                                intAmt = detail.getIntAmt();
//                            }
//                            else {
//                                // 变量.利息 = DTO.计划支付利息
//                                intAmt = detail.getIntAmt();
//                            }
                            //变量.利息 = DTO.计划支付利息/DTO.名义本金
                            //intAmt = ArithmeticUtil.divide(detail.getIntAmt(), detail.getIssueAmt(),NumberEnum.ALL_PRECISION.getCode());
                        }
                    }
                    // 变量.账务明细代码信息 = 调用共通标的【账务明细代码信息查询（变量.科目余额信息.账务明细代码，变量.账套明细.账套代码）】
                    List<BokDetailId> listValBokBookdetail = finProductCommonService.bookdetailInforQuery(
                        detail.getBooksetId(), subjectBalance.getBokDetailId());
                    BokDetailId valBokBookdetail = new BokDetailId();
                    if (listValBokBookdetail != null && listValBokBookdetail.size() > 0) {
                        valBokBookdetail = listValBokBookdetail.get(0);
                    }

                    // if（变量.利息 != 0）
                    if (!ArithmeticUtil.equals(intAmt, BigDecimal.ZERO)) {
                        // 变量.交易方向 = "收息"
                        dealPs = DictCodeList.交易方向_收息.getValue();
                        // if（变量.利息分支号 = null）
                        if (branchInt == null) {
//                            // 变量.最大分支序号++
//                            maxBranch++ ;
                            // 变量.最大分支序号 =  （DTO.调整后的计算结束日 （'YYYYMMDD'）|| 02）转成数值型
                            maxBranch = new Long(DateUtil.format(detail.getMdate(),"yyyyMMdd")+"02");
                            // 创建交易信息("利息"，变量.利息，变量.业务类型，变量.账务明细代码信息，变量.最大分支序号，变量.交易方向，DTO)
                            AssetIntdateCarryoverDeal assetIntdateCarryoverDeal = creatCarryoverDeal(
                                DictCodeListExt1.现金流一级类型_利息.getValue(), intAmt, busiType,
                                valBokBookdetail, maxBranch, dealPs, detail, faceValue, true,
                                assetIntdateCarryoverResult);
                            assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList().add(
                                assetIntdateCarryoverDeal);
                        }
                        else {
                            // 创建交易信息("利息"，变量.利息，变量.业务类型，变量.账务明细代码信息，变量.利息分支号，变量.交易方向，DTO)
                            AssetIntdateCarryoverDeal assetIntdateCarryoverDeal = creatCarryoverDeal(
                                DictCodeListExt1.现金流一级类型_利息.getValue(), intAmt, busiType,
                                valBokBookdetail, branchInt, dealPs, detail, faceValue, false,
                                assetIntdateCarryoverResult);
                            assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList().add(
                                assetIntdateCarryoverDeal);
                        }
                    }
                    
                    
                    
                    // if（变量.本金 != 0）
                    if (!ArithmeticUtil.equals(printAmt, BigDecimal.ZERO)) {
                        // 变量.交易方向 = "还本"
                        dealPs = DictCodeList.交易方向_还本.getValue();
                        // if（DTO.实际到期日 = DTO.调整后的计算结束日）
                        if (detail.getActmdate().equals(detail.getMdate())) {
                            // 变量.交易方向 = "到期"
                            dealPs = DictCodeList.交易方向_到期.getValue();
                        }
                        // if（变量.本金分支号 = null）
                        if (branchPri == null) {
//                            // 变量.最大分支序号++
//                            maxBranch++ ;
                            // 变量.最大分支序号 =  （DTO.调整后的计算结束日 （'YYYYMMDD'）|| 01）转成数值型
                            maxBranch = new Long(DateUtil.format(detail.getMdate(),"yyyyMMdd")+"01");
                            // 创建交易信息("本金"，变量.利息，变量.业务类型，变量.账务明细代码信息，变量.最大分支序号，变量.交易方向，DTO)
                            AssetIntdateCarryoverDeal assetIntdateCarryoverDeal = creatCarryoverDeal(
                                DictCodeListExt1.现金流一级类型_本金.getValue(), printAmt, busiType,
                                valBokBookdetail, maxBranch, dealPs, detail, faceValue, true,
                                assetIntdateCarryoverResult);
                            assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList().add(
                                assetIntdateCarryoverDeal);
                        }
                        else {
                            // 创建交易信息("本金"，变量.利息，变量.业务类型，变量.账务明细代码信息，变量.本金分支号，变量.交易方向，DTO)
                            AssetIntdateCarryoverDeal assetIntdateCarryoverDeal = creatCarryoverDeal(
                                DictCodeListExt1.现金流一级类型_本金.getValue(), printAmt, busiType,
                                valBokBookdetail, branchPri, dealPs, detail, faceValue, false,
                                assetIntdateCarryoverResult);
                            assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList().add(
                                assetIntdateCarryoverDeal);
                        }
                    }
                    
                    

                }
            }
        }

        return assetIntdateCarryoverResult;

    }

    /**
     * 生成交易 <功能详细描述>
     * 
     * @param type 类型
     * @param amount 金额
     * @param busiType 业务类型
     * @param valBokBookdetail 账务明细信息
     * @param branch 分支序号
     * @param dealPs 交易方向
     * @param detail 结转信息
     * @param faceValue 单位面值
     * @param isCreat 是否生成交易类金融产品
     * @return
     * @see
     * @since 1.0
     */
    private AssetIntdateCarryoverDeal creatCarryoverDeal(String type, BigDecimal amount,
                                                         String busiType,
                                                         BokDetailId valBokBookdetail, Long branch,
                                                         String dealPs,
                                                         AssetIntdateCarryoverReaderDetail detail,
                                                         BigDecimal faceValue, boolean isCreat,
                                                         AssetIntdateCarryoverResult assetIntdateCarryoverResult) {

        // 资产理论结息日结转信息 实体类定义
        AssetIntdateCarryoverDeal dao = new AssetIntdateCarryoverDeal();

        // 【交易类金融产品】
        // 数据生成===============================================================================
        //重复数据不生成
        boolean isChongfu = false;
        if (assetIntdateCarryoverResult != null
            && assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList() != null
            && assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList().size() > 0) {
            for (AssetIntdateCarryoverDeal deal : assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList()) {
                FinTradeProduct trade = deal.getValTradeFinProduct();
                // 根据主键判断交易类金融产品表是否已经生成数据（多账套跑批时规避）
                if (trade != null && StringUtils.isNotEmpty(trade.getFinprodId())
                    && detail.getFinprodId().equals(trade.getFinprodId())
                    && ((branch != null && branch.equals(trade.getBranch())
                         || (branch == null && trade.getBranch() == null)))) {
                    isChongfu = true;
                }
            }
        }
        FinTradeProductKey finTradeProductKey = new FinTradeProductKey();
        finTradeProductKey.setFinprodId(detail.getFinprodId());
        finTradeProductKey.setBranch(branch);
        boolean flag = this.checkCreateFinTrdProduct(assetIntdateCarryoverResult,
            finTradeProductKey);
        if (isCreat && !isChongfu && flag) {
            // 交易类金融产品
            FinTradeProduct valTradeFinProduct = new FinTradeProduct();
            // 金融产品代码 DTO.金融产品代码
            valTradeFinProduct.setFinprodId(detail.getFinprodId());
            // 金融产品类型 DTO.金融产品类型
            valTradeFinProduct.setFinprodType(detail.getFinprodType());
            // 分支序号 参数.分支序号
            valTradeFinProduct.setBranch(branch);
            // 分支类型 decode（参数.类型,"本金","应收本金"数据字典，"利息"，"应收利息"数据字典）
            if (type.equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
                valTradeFinProduct.setBranchType(DictCodeList.分支类型_应收本金.getValue());
            }
            else if (type.equals(DictCodeListExt1.现金流一级类型_利息.getValue())) {
                valTradeFinProduct.setBranchType(DictCodeList.分支类型_应收利息.getValue());
            }
            // 通道代码 DTO.通道代码
            valTradeFinProduct.setChlAgrtId(detail.getChlFinprodId());

            // 本金币种 DTO.币种
            valTradeFinProduct.setCcy(detail.getCcy());
            // 起息日 DTO.调整后的计算开始日
            valTradeFinProduct.setVdate(detail.getVdate());
            // 到期日 DTO.调整后的计算结束日
            valTradeFinProduct.setMdate(detail.getMdate());
            // 期限(天) 空
            valTradeFinProduct.setTerm(null);
            // 利率类型 空
            valTradeFinProduct.setIntType(null);
            // 固定利率 空
            valTradeFinProduct.setIntRate(null);
            // 利率编号 空
            valTradeFinProduct.setIntRateId(null);
            // 计息基础 空
            valTradeFinProduct.setBasis(null);
            if (type.equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
                // 到期本金 DTO.计划支付本金
                valTradeFinProduct.setmPrinAmt(detail.getPrinAmt());
                // 到期金额
                valTradeFinProduct.setmTradeAmt(detail.getPrinAmt());
            }
            if (type.equals(DictCodeListExt1.现金流一级类型_利息.getValue())) {
                // 到期利息 DTO.计划支付利息
                valTradeFinProduct.setmIntAmt(detail.getIntAmt());
                // 到期金额
                valTradeFinProduct.setmTradeAmt(detail.getIntAmt());
            }
            // 名义本金 同到期金额
            valTradeFinProduct.setPrin(null);

            // 原金融产品代码 DTO.原金融产品代码
            valTradeFinProduct.setoFinprodId(detail.getFinprodId());

            
            //【一体化修改】：begin========================================================================= yangjian 2018/1/4
            //【一体化修改】：针对估值一体化变更【60：交易类金融产品表原字段【原金融产品类型】删除】
            // 原金融产品类型 DTO.原金融产品类型
            //valTradeFinProduct.setoFinprodType(detail.getFinprodType());

            
            
            
            //针对估值一体化变更【63：交易类金融产品表原字段【逾期交易编号】删除】
            // 逾期交易编号 空
            //valTradeFinProduct.setoTradeId(null);
            
          //【一体化修改】：end========================================================================= yangjian 2018/1/4
            
            
            
            // 创建时间 共通字段
            valTradeFinProduct.setCreateTime(new Date());
            // 更新时间 共通字段
            valTradeFinProduct.setUpdateTime(new Date());
            dao.setValTradeFinProduct(valTradeFinProduct);
        }

        // 【金融产品交易】
        // 数据生成===============================================================================
        // 金融产品交易
        TrdProductDeal valFinProductDeal = new TrdProductDeal();
        // 交易编号 自动生成
        valFinProductDeal.setTradeId(
            interfCommonService.getTradeid(dealPs, detail.getBooksetDate(), "A"));
        // 业务类型 参数.业务类型
        valFinProductDeal.setBusiType(busiType);
        // 金融产品代码 DTO.金融产品代码
        valFinProductDeal.setFinprodId(detail.getFinprodId());
        // 金融产品类型 DTO.金融产品类型
        valFinProductDeal.setFinprodType(detail.getFinprodType());
        // 分支序号 参数.分支序号
        valFinProductDeal.setBranch(branch);
        // 交易日 DTO.账套日
        valFinProductDeal.setTradeDate(detail.getBooksetDate());
        // 起息日 DTO.调整后的计算开始日
        valFinProductDeal.setVdate(detail.getVdate());
        // 到期日 DTO.调整后的计算结束日
        valFinProductDeal.setMdate(detail.getMdate());
        // 交割日 DTO.账套日
        valFinProductDeal.setSettleDate(detail.getBooksetDate());
        // 交易币种 DTO.币种
        valFinProductDeal.setCcy(detail.getCcy());
        // 净值日期 空
        valFinProductDeal.setCpriceDate(null);
        if (type.equals(DictCodeListExt1.现金流一级类型_本金.getValue())) {
            // 单位净价 空
            valFinProductDeal.setUnitCprice(amount);
        }
        if (type.equals(DictCodeListExt1.现金流一级类型_利息.getValue())) {
            // 单位利息 空
            valFinProductDeal.setUnitInt(amount);
        }

        // 单位全价 空
        valFinProductDeal.setUnitFprice(null);
        // 单位面值 参数.单位面值
        valFinProductDeal.setParValue(faceValue);
        // 净价总额 金额
        valFinProductDeal.setCpriceAmt(BigDecimal.ZERO);
        // 交易份额 参数.交易份额
        valFinProductDeal.setShareAmt(BigDecimal.ZERO);
        // 交易本金 金额
        valFinProductDeal.setPrinAmt(BigDecimal.ZERO);
        // 利息总额 金额
        valFinProductDeal.setIntAmt(BigDecimal.ZERO);
        // 全价总额 金额
        valFinProductDeal.setFpriceAmt(BigDecimal.ZERO);
        // 随交易支付费用 0
        valFinProductDeal.setFeeAmtPay(BigDecimal.ZERO);
        // 交易金额 = 全价总额
        valFinProductDeal.setTradeAmt(BigDecimal.ZERO);
        // 未随交易支付费用 0
        valFinProductDeal.setFeeAmtUnpay(BigDecimal.ZERO);
        // 总交易费用 0
        valFinProductDeal.setFeeAmt(BigDecimal.ZERO);
        // 交易方向 参数.交易方向
        valFinProductDeal.setPs(dealPs);
        // 到期收益率 空
        valFinProductDeal.setYield(null);
        // 行权收益率 空
        valFinProductDeal.setExerYield(null);
        // 投资目的 账务明细代码信息.投资目的
        valFinProductDeal.setInvAim(valBokBookdetail.getInvAim());
        // 交易状态 正常
        valFinProductDeal.setStatus(DictCodeList.冲正标识_正常交易.getValue());
        valFinProductDeal.setTradeStatus(DictCodeList.冲正标识_正常交易.getValue());
        // 原交易编号 空
        valFinProductDeal.setpTradeId(null);
        // 是否结清 空
        valFinProductDeal.setIsClean(null);
        // 汇总交易编号 空
        valFinProductDeal.setTotleTradeId(null);
        // 反向交易编号 空
        valFinProductDeal.setrTradeId(null);
        // 通道代码 DTO.通道代码
        valFinProductDeal.setChlId(detail.getChlFinprodId());
        // 交易对手 空
        valFinProductDeal.setCounterId(null);
        // 交易对手类型 "外部"
//        valFinProductDeal.setCounterType(DictCodeListExt1.交易对手类型_外部交易对手.getValue());
        // 一体化：交易对手类型改成是否外部交易
        valFinProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所 DTO.交易场所
        valFinProductDeal.setTradeMarket(detail.getTradeMarket());
        // 交易员 空
        valFinProductDeal.setTrader(null);
        // 证券管理户代码 账务明细代码信息.证券管理户代码
        valFinProductDeal.setSecManageAcctId(valBokBookdetail.getSecManageAcctId());
        // 关联系统交易编号 空
        valFinProductDeal.setfTradeId(null);
        // 创建时间 共通字段
        valFinProductDeal.setCreateTime(new Date());
        // 更新时间 共通字段
        valFinProductDeal.setUpdateTime(new Date());
        dao.setValFinProductDeal(valFinProductDeal);

        // 【估值核算交易扩展表】
        // 数据生成===============================================================================
        // 估值核算交易扩展表
        //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
        TrdProductDealAdd valFinProductDealExt = new TrdProductDealAdd();
        // 交易编号 金融产品交易已生成的交易编号
        valFinProductDealExt.setTradeId(valFinProductDeal.getTradeId());
        // 投资组合代码 空
        valFinProductDealExt.setPortfolioId(null);
        // 理财产品代码 空
        //valFinProductDealExt.setProdId(null);//【一体化注释：金融产品交易附属表TRD_PRODUCT_DEAL_ADD去除对PROD_ID字段】
//        // 是否有结算指令 否
//        valFinProductDealExt.setHasOrder(DictCodeList.是否_否.getValue());
        // 生成方式 "批处理生成"
        valFinProductDealExt.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());
        // 创建时间 共通字段
        valFinProductDealExt.setCreateTime(new Date());
        // 更新时间 共通字段
        valFinProductDealExt.setUpdateTime(new Date());
        dao.setValFinProductDealExt(valFinProductDealExt);

        // 【入账信息表】
        // 数据生成===============================================================================
        // 入账信息表
        BokDealAccounting valDealAccounting = new BokDealAccounting();
        // 账套代码 DTO.账套代码
        valDealAccounting.setBooksetId(detail.getBooksetId());
        // 业务编号 金融产品交易已生成的交易编号
        valDealAccounting.setBusiNo(valFinProductDeal.getTradeId());
        // 业务表 "金融产品表"
        valDealAccounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务类型 参数.业务类型
//        valDealAccounting.setBusiType(busiType);
        // 录入日 DTO.账套日
        valDealAccounting.setBooksetDate(detail.getBooksetDate());
        // 入账日 DTO.账套日
        valDealAccounting.setBookDate(detail.getBooksetDate());
        // 实际发生日 DTO.账套日
        valDealAccounting.setHappenDate(detail.getBooksetDate());
        // 账务明细代码 账务明细代码信息.账务明细代码
//        valDealAccounting.setBokDetailId(valBokBookdetail.getBokDetailId());
        // 是否已入账 否
        valDealAccounting.setIsAccounted(DictCodeList.是否_否.getValue());
        // 入账凭证编号 空
        valDealAccounting.setBookId(null);

        dao.setValDealAccounting(valDealAccounting);

        // 【入账账务明细表】
        // 数据生成===============================================================================
        BokDealAccountingDetail valDealAccountingDetail = new BokDealAccountingDetail();
        valDealAccountingDetail.setBooksetId(detail.getBooksetId());// 账套代码
        valDealAccountingDetail.setBusiNo(valFinProductDeal.getTradeId());// 业务编码
        valDealAccountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());// 业务表，指令表、交易表等
        valDealAccountingDetail.setBusiType(busiType);// 业务类型，债券交易、债券存续交易、基金交易、收划款等
        valDealAccountingDetail.setBokDetailId(valBokBookdetail.getBokDetailId());// 账务明细代码

        dao.setValDealAccountingDetail(valDealAccountingDetail);

        return dao;
    }
    
    /**
     * 生成 交易类金融产品 前校验是否已生成过
     * 
     * @param assetIntdateCarryoverResult
     * @param finTradeProductKey
     * @return
     * @see
     * @since 1.0
     */
    private boolean checkCreateFinTrdProduct(AssetIntdateCarryoverResult assetIntdateCarryoverResult,
                                             FinTradeProductKey finTradeProductKey) {
        // 查询 交易类标的
        FinTradeProduct finTradeProduct = finTradeProductMapper.selectByPrimaryKey(
            finTradeProductKey);
        // 交易类标的不为空
        if (finTradeProduct != null) {
            // 生成标志 = false，不重新生成交易类标的
            return false;
        }
        else {
            // 获取 返回结果中的所有交易
            List<AssetIntdateCarryoverDeal> listDeal = assetIntdateCarryoverResult.getAssetIntdateCarryoverDealList();
            // 判断 交易中是否有生成过与当前相同的标的信息
            for (AssetIntdateCarryoverDeal item : listDeal) {
                // 金融产品代码、金融产品类型、分支序号一致，则已生成过，则不需重复生成
                if (item.getValTradeFinProduct() != null
                    && StringUtil.equals(finTradeProductKey.getFinprodId(),
                        item.getValTradeFinProduct().getFinprodId())
//                    && StringUtil.equals(finTradeProductKey.getFinprodType(),
//                        item.getValTradeFinProduct().getFinprodType())
                    && finTradeProductKey.getBranch().equals(
                        item.getValTradeFinProduct().getBranch())) {
                    return false;
                }
            }
        }
        return true;
    }

}
