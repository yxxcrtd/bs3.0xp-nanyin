/*
 * 文件名：ValRiftService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.creditPierce.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.creditPierce.dao.CreditPierceMapper;
import com.joyintech.tams.batch.creditPierce.dto.PtlSecPierceValDto;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditLinesPierceMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesPierce;
import com.joyintech.tams.common.base.entity.CreditMstInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.CelinesUpdateFactory;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 *  授信估值穿透数据接入处理
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2020年12月14日
 * @see CreditPierceService
 * @since 1.0
 */
@Service
public class CreditPierceService {

    @Autowired
    private CreditPierceMapper creditPierceMapper;

    @Autowired
    private CreditLinesPierceMapper creditLinesPierceMapper;

    @Autowired(required = false)
    private BaseGetBussinessId baseGetBusinessId;

    @Autowired
    private CreditLinesMapper creditMapper;

    @Autowired
    private CelinesUpdateFactory celinesUpdateFactory;

    /**
     * 授信估值穿透数据接入处理
     * <功能详细描述>
     * @param currentDate
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void run(Date currentDate, List<String> productIdList){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        paramMap.put("currentDate", sdf.format(currentDate));
        paramMap.put("productIdList", productIdList);
        paramMap.put("fzq", DictCodeList.平安资产类型_债券.getValue());
        paramMap.put("fck", DictCodeList.平安资产类型_存款.getValue());
        paramMap.put("fxml", DictCodeList.平安资产类型_项目类资产.getValue());
        paramMap.put("fhg", DictCodeList.平安资产类型_质押式回购.getValue());
        paramMap.put("gp", BussinessDictCodeList.平安资产类型_股票.getValue());
        paramMap.put("zqjz", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        paramMap.put("whjq", DictCodeListExt2.平安资产类型_外汇即期.getValue());
        paramMap.put("whyq", DictCodeListExt2.平安资产类型_外汇远期.getValue());
        paramMap.put("whdq", DictCodeListExt2.平安资产类型_外汇掉期.getValue());
        //穿透组合
        List<PtlSecPierceValDto> ptlSecPierceValutioList = creditPierceMapper.selectPtlSecPierceValutionList(paramMap);
        List<CreditMstInfo> creditMstInfoList = creditPierceMapper.selectCreditMstInfoList();
        List<CreditLinesPierce> result = new ArrayList<CreditLinesPierce>();
        Map<String,List<CreditMstInfo>> creditMstInfoListMap = creditMstInfoList.stream()
                .collect(JoyinCollectors.groupingBy(item->item.getCreditQuoteTypeCode()+":"+item.getCreditQuoteTypeValue()));
        List<String> creditQuoteTypeCodeList = creditMstInfoList.stream().map(CreditMstInfo::getCreditQuoteTypeValue).collect(Collectors.toList());
        if (!StringUtil.isEmptyList(ptlSecPierceValutioList)){
            Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioListMap = ptlSecPierceValutioList.stream()
                    .collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getFinprodId));
            if (StringUtil.isNotEmpty(ptlSecPierceValutioListMap)) {
                for (String finprodId : ptlSecPierceValutioListMap.keySet()) {
                    if (!StringUtil.isNotEmpty(finprodId)){
                        continue;
                    }
                    CreditLinesPierce dto = new CreditLinesPierce();
                    dto.setSeqno(IDGenerator.generate());
                    dto.setCustomerId(finprodId);
                    dto.setCdate(currentDate);
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                    dto.setCcy(ptlSecPierceValutioListMap.get(finprodId).get(0).getCcy());
                    dto.setCreditMstId("0002");
                    BigDecimal creditAmt = BigDecimal.ZERO;
                    for (PtlSecPierceValDto ptl : ptlSecPierceValutioListMap.get(finprodId)) {
                        if (StringUtil.isNotEmpty(ptl.getAssetType())
                                &&ptl.getAssetType().equals(DictCodeList.平安资产类型_债券.getValue())){
                            if (creditQuoteTypeCodeList.contains(ptl.getVarietyType1())){
                                creditAmt = ArithmeticUtil.doAdd(creditAmt, ptl.getDscCostAmt());
                            }
                        }else{
                            creditAmt = ArithmeticUtil.doAdd(creditAmt, ptl.getDscCostAmt());
                        }
                    }
                    dto.setCreditAmt(creditAmt);
                    result.add(dto);
                }
            }
            Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioOrgListMap = ptlSecPierceValutioList.stream()
                    .collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getOrganId));
            if (StringUtil.isNotEmpty(ptlSecPierceValutioOrgListMap)){
                for (String organId:ptlSecPierceValutioOrgListMap.keySet()){
                    if (!StringUtil.isNotEmpty(organId)){
                        continue;
                    }
                    CreditLinesPierce dto = new CreditLinesPierce();
                    dto.setSeqno(IDGenerator.generate());
                    dto.setCustomerId(organId);
                    dto.setCdate(currentDate);
                    dto.setCreditMstId("0001");
                    if(celinesUpdateFactory.isKDQ(dto.getCreditMstId(), dto.getCustomerId(), dto.getCdate(),dto.getInstitutionCode())<=0){
                        CreditLines creditLines = new CreditLines();
                        String customerId = dto.getCustomerId();
                        String seqNo = "";
                        if (baseGetBusinessId != null) {
                            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
//                    if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())
//                            ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                            ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
//                    }else {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产授信);
//                    }
                            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
                            seqNo = baseGetBusinessId.buildBussinessCode(codeParameter);
                        }
                        else {
                            seqNo= IDGenerator.generate();
                        }
                        creditLines.setSeqno(seqNo);
                        creditLines.setCreditAmt(BigDecimal.ZERO);
//                if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())
//                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
//                    creditLines.setCreditMstId("0001");
//                    customerId = model.getCounterId();
//                    creditLines.setCustomerId(customerId);
//                    creditLines.setCreditFlag("Y");
//                }else {
//                    creditLines.setCreditMstId("0002");
//                    creditLines.setCustomerId(customerId);
//                    creditLines.setParentCustomerId(creditLines.getCustomerId());
//                    creditLines.setCreditFlag("N");
//                }
                        creditLines.setCreditMstId("0001");
                        customerId = dto.getCustomerId();
                        creditLines.setCustomerId(customerId);
                        creditLines.setCreditFlag("Y");
                        creditLines.setVdate(dto.getCdate());
                        creditLines.setMdate(DateUtil.parse("2099-12-31"));
                        creditLines.setPflsh("空档期授信");
                        creditLines.setCreateDept(dto.getCreateDept());
                        creditLines.setCreateTime(dto.getCreateTime());
                        creditLines.setCreateUser(dto.getCreateUser());
                        creditLines.setUpdateTime(dto.getUpdateTime());
                        creditLines.setUpdateUser(dto.getUpdateUser());
                        creditLines.setInstitutionCode(dto.getInstitutionCode());
                        creditLines.setHapCreditAmt(celinesUpdateFactory.extendCreditLinesDeal(creditLines.getSeqno(), creditLines.getVdate(), creditLines.getCreditMstId(),creditLines.getCustomerId(),dto.getInstitutionCode()));
                        creditMapper.insert(creditLines);
                    }
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                    dto.setCcy(ptlSecPierceValutioOrgListMap.get(organId).get(0).getCcy());
                    BigDecimal creditAmt = BigDecimal.ZERO;
//                    for(PtlSecPierceValDto ptl : ptlSecPierceValutioOrgListMap.get(organId)){
//                    }
                    Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioVariety1ListMap =
                            ptlSecPierceValutioOrgListMap.get(organId).stream().collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getVarietyType1));
                    for (String varietyType1:ptlSecPierceValutioVariety1ListMap.keySet()){
                        if (!StringUtil.isNotEmpty(varietyType1)){
                            continue;
                        }
                        PtlSecPierceValDto entity = ptlSecPierceValutioVariety1ListMap.get(varietyType1).get(0);
                        if (StringUtil.isNotEmpty(getAssetTypeFirstLevel(entity.getAssetType()))&&!StringUtil.isEmptyList(creditMstInfoListMap.get(
                                getAssetTypeFirstLevel(entity.getAssetType()) + ":" + entity.getVarietyType1()))) {
                            CreditLinesPierce dtoOne = new CreditLinesPierce();
                            dtoOne.setSeqno(IDGenerator.generate());
                            dtoOne.setCustomerId(organId);
                            dtoOne.setCdate(currentDate);
                            dtoOne.setCreditMstId(creditMstInfoListMap.get(
                                    getAssetTypeFirstLevel(entity.getAssetType()) + ":" + entity.getVarietyType1()).get(0).getSeqno());
//                       dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                            dtoOne.setCcy(ptlSecPierceValutioVariety1ListMap.get(varietyType1).get(0).getCcy());
                            BigDecimal creditAmtOne = BigDecimal.ZERO;
                            for (PtlSecPierceValDto ptl : ptlSecPierceValutioVariety1ListMap.get(varietyType1)) {
                                creditAmtOne = ArithmeticUtil.doAdd(creditAmtOne,  ptl.getDscCostAmt());
                                creditAmt = ArithmeticUtil.doAdd(creditAmt, ptl.getDscCostAmt());
                            }
                            dtoOne.setCreditAmt(creditAmtOne);
                            result.add(dtoOne);
                        }
                    }
                    dto.setCreditAmt(creditAmt);
                    result.add(dto);
                    Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioVariety2ListMap =
                            ptlSecPierceValutioOrgListMap.get(organId).stream().collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getVarietyType2));
                    for (String varietyType2:ptlSecPierceValutioVariety2ListMap.keySet()){
                        if (!StringUtil.isNotEmpty(varietyType2)){
                            continue;
                        }
                        PtlSecPierceValDto entity = ptlSecPierceValutioVariety2ListMap.get(varietyType2).get(0);
                        if (StringUtil.isNotEmpty(getAssetTypeSecondLevel(entity.getAssetType()))&&!StringUtil.isEmptyList(creditMstInfoListMap.get(
                                getAssetTypeSecondLevel(entity.getAssetType()) + ":" + entity.getVarietyType2()))) {
                            CreditLinesPierce dtoTwo = new CreditLinesPierce();
                            dtoTwo.setSeqno(IDGenerator.generate());
                            dtoTwo.setCustomerId(organId);
                            dtoTwo.setCdate(currentDate);
                            dtoTwo.setCreditMstId(creditMstInfoListMap.get(
                                    getAssetTypeSecondLevel(entity.getAssetType()) + ":" + entity.getVarietyType2()).get(0).getSeqno());
//                       dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                            dtoTwo.setCcy(ptlSecPierceValutioVariety2ListMap.get(varietyType2).get(0).getCcy());
                            BigDecimal creditAmtOne = BigDecimal.ZERO;
                            for (PtlSecPierceValDto ptl : ptlSecPierceValutioVariety2ListMap.get(varietyType2)) {
                                creditAmtOne = ArithmeticUtil.doAdd(creditAmtOne,  ptl.getDscCostAmt());
                            }
                            dtoTwo.setCreditAmt(creditAmtOne);
                            result.add(dtoTwo);
                        }
                    }
                }
            }
            Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutiZgjhListMap = ptlSecPierceValutioList.stream()
                    .collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getZgplanId));
            if (StringUtil.isNotEmpty(ptlSecPierceValutiZgjhListMap)){
                for (String zgplanId:ptlSecPierceValutiZgjhListMap.keySet()){
                    if (!StringUtil.isNotEmpty(zgplanId)){
                        continue;
                    }
                    CreditLinesPierce dto = new CreditLinesPierce();
                    dto.setSeqno(IDGenerator.generate());
                    dto.setCustomerId(zgplanId);
                    dto.setCdate(currentDate);
                    dto.setCreditMstId("0002");
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).getPlanAssetId());
                    dto.setCcy(ptlSecPierceValutiZgjhListMap.get(zgplanId).get(0).getCcy());
                    BigDecimal creditAmt = BigDecimal.ZERO;
                    for(PtlSecPierceValDto ptl : ptlSecPierceValutiZgjhListMap.get(zgplanId)){
                        creditAmt = ArithmeticUtil.doAdd(creditAmt,  ptl.getDscCostAmt());
                    }
                    dto.setCreditAmt(creditAmt);
                    result.add(dto);
                }
            }
            deleteCreditPierce(currentDate,result);
            writeCreditPierce(result);
        }
    }

    private String getAssetTypeFirstLevel(String assetType){
        if (StringUtil.equals(assetType,DictCodeList.平安资产类型_债券.getValue())){
            return "WINDTYPEONE";
        }
        if (StringUtil.equals(assetType,DictCodeList.平安资产类型_存款.getValue())){
            return "DEPO_ASSET_TYPE";
        }
        if (StringUtil.equals(assetType,DictCodeList.平安资产类型_基金.getValue())){
            return "FUND_ASSET_TYPE";
        }
        if (StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_净值型.getValue())
        || StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_利率型.getValue())
        || StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_货币型.getValue())){
            return "PLAN_ASSET_TYPE";
        }
        if (StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_项目类资产.getValue())){
            return "ASSET_TYPE_PROJECT";
        }
        if (StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_股票.getValue())
          ||StringUtil.equals(assetType, DictCodeListExt2.平安资产类型_债券借贷.getValue())
          ||StringUtil.equals(assetType, DictCodeListExt2.平安资产类型_外汇即期.getValue())
          ||StringUtil.equals(assetType, DictCodeListExt2.平安资产类型_外汇远期.getValue())
          ||StringUtil.equals(assetType, DictCodeListExt2.平安资产类型_外汇掉期.getValue())){
            return "PINAN_ASSET_TYPE";
        }
        return null;
    }

    private String getAssetTypeSecondLevel(String assetType){
        if (StringUtil.equals(assetType,DictCodeList.平安资产类型_债券.getValue())){
            return "WINDTYPETWO";
        }
        if (StringUtil.equals(assetType,DictCodeList.平安资产类型_基金.getValue())){
            return "FUND_ASSET_SECOND_TYPE";
        }
        if (StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_净值型.getValue())
                || StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_利率型.getValue())
                || StringUtil.equals(assetType,BussinessDictCodeList.平安资产类型_计划类资产_货币型.getValue())){
            return "PLAN_ASSET_SEC_TYPE";
        }
        return null;
    }


    /**
     * 保存穿透后的授信数据
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    private void  writeCreditPierce(List<CreditLinesPierce> dtoList){
        for(CreditLinesPierce dto : dtoList){
            creditLinesPierceMapper.insert(dto);
        }
    }

    /**
     * 删除已跑批的授信数据
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    private void deleteCreditPierce(Date currentDate,List<CreditLinesPierce> dtoList){
        for (CreditLinesPierce dto:dtoList){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId", dto.getCustomerId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paramMap.put("currentDate", sdf.format(currentDate));
            creditPierceMapper.deleteCreditPierce(paramMap);
        }
    }
}
