/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.service;

import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dao.PayTheVatQuery003Mapper;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery001Dto;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayTheVatQuery003Service extends CommonExportService<PayTheVatQuery001Dto> implements BaseProcessService<PayTheVatQuery001Dto> {

    @Autowired
    private PayTheVatQuery003Mapper payTheVatQuery003Mapper;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<PayTheVatQuery001Dto> findFinproduct(@Param("booksetId") String booksetId,
                                                     @Param("seitleDate") String seitleDate) {
        return payTheVatQuery003Mapper.findFinproduct(booksetId, seitleDate);
    }

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<PayTheVatQuery001Dto> findPtradeid(@Param("tradeId") String tradeId) {
        return payTheVatQuery003Mapper.findPtradeid(tradeId);
    }

    /**
     * 查询账套配置表
     * 
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public List<PayTheVatQuery001Dto> queryBooksetExt(@Param("tradeId") String tradeId) {
        return payTheVatQuery003Mapper.queryBooksetExt(tradeId);
    }

    /**
     * 撤销入库操作
     * 
     * @param params
     * @return
     * @throws Exception
     */

    public void doComplete(PayTheVatQuery001Dto dto, OperationType arg1) {

        // 初始化各个字段的值
        List<PayTheVatQuery001Dto> dto1 = queryBooksetExt(dto.getTradeId());
        // 交易编号生成
        dto.setNtradeId(interfCommonService.getTradeid(dto1.get(0).getTaxidenType(),
            dto.getCreateTime(), "A"));
        // 创建人
        dto.setCreateUser(dto.getCreateUser());
        // 金融产品代码
        List<PayTheVatQuery001Dto> dto2 = payTheVatQuery003Mapper.findCashid(
            dto.getBooksetId().toString(), dto1.get(0).getAcctsubjectType().toString(),
            DictCodeListExt1.现金流二级类型_税额.getValue());
        dto.setFinprodId(dto2.get(0).getCashId());
        // 交易金额
        dto.setTradeAmt(ArithmeticUtil.doAdd(dto.getAppreciationTax(),dto.getAdditionalTax()));
        // 账务明细代码
        dto.setBokDetailid(
            payTheVatQuery003Mapper.findbokDetailid(dto.getBooksetId(), dto.getFinprodId()));
        // 指令编号生成
        dto.setOrderId(interfCommonService.getTradeid(dto1.get(0).getTaxidenType(),
            dto.getCreateTime(), "B"));
        // 本方资金账户
        List<PayTheVatQuery001Dto> dto3 = payTheVatQuery003Mapper.findacctNo(dto.getBooksetId(),
            DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setAcctNo(dto3.get(0).getAcctNo());

        // 入金融产品交易扩展表(核算主体类别不同入库不同)
        if (DictCodeListExt1.金融产品类型_理财产品.getValue().equals(dto1.get(0).getAcctsubjectType())) {
            dto.setPortfolioId(dto.getBooksetId());
            dto.setProdId(null);
        }
        else {
            dto.setPortfolioId(null);
            dto.setProdId(dto.getBooksetId());
        }
        // 本位币
        dto.setStandardCcy(dto1.get(0).getStandardCcy());
        dto.setTaxidenType(dto1.get(0).getTaxidenType());
        if (DictCodeList.交易方向_税费缴税.getValue().equals(dto.getTaxidenType())) {
            dto.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_缴纳日_增值税缴纳.getValue());
        }
        else {
            dto.setOrderType(DictCodeListExt1.指令类型_增值税缴纳_退税日_退税.getValue());
        }
        // 入库
        // 入金融产品交易扩展表
        payTheVatQuery003Mapper.insertProdealext(dto);
        // 入金融产品交易表
        payTheVatQuery003Mapper.insertProdeal(dto);
        // 入入账信息表
        payTheVatQuery003Mapper.insertDealaccount(dto);
        // 入账务明细表
        payTheVatQuery003Mapper.insertDealaccountdetail(dto);
        // 入结算指令扩展表
        payTheVatQuery003Mapper.insertStlordersext(dto);
        // 入结算指令表
        payTheVatQuery003Mapper.insertStlorders(dto);

        // 入交易费用明细表表(TAX_TYPE数据字典,分6条数据分别存储金额数据)
        // TAX0102利息收入
        dto.setFeeAmt(dto.getInterestIncome());
        dto.setFeeType(DictCodeList.增值税类型_利息收入.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);

        // TAX0202红利收入
        dto.setFeeAmt(dto.getDividendIncome());
        dto.setFeeType(DictCodeList.增值税类型_红利收入.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);

        // TAX0302价差收入
        dto.setFeeAmt(dto.getDifbetIncome());
        dto.setFeeType(DictCodeList.增值税类型_价差收入.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);

        // ALT0102城建税附加
        dto.setFeeAmt(dto.getConstructionTax());
        dto.setFeeType(DictCodeList.附加税类型_城建税.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);

        // ALT0202教育税附加
        dto.setFeeAmt(dto.getEducationTax());
        dto.setFeeType(DictCodeList.附加税类型_教育税.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);

        // ALT0302地方教育费附加
        dto.setFeeAmt(dto.getPlaceeducationTax());
        dto.setFeeType(DictCodeList.附加税类型_地方教育税.getValue());
        payTheVatQuery003Mapper.insertTrdfeedetail(dto);
    }
}
