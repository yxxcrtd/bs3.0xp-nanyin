/*
 * 文件名：ValRiftService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.creditPierce.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditLinesPierceMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesPierce;
import com.joyintech.tams.common.base.entity.CreditMstInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.batch.creditPierce.dao.CreditPierceMapper;
import com.joyintech.tams.invest.batch.creditPierce.dto.PtlSecPierceValDto;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
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

    /**
     * 授信估值穿透数据接入处理
     * <功能详细描述>
     * @param currentDate
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void run(Date currentDate, List<String> productIdList){
        CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);

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
            // 资产授信
            if (StringUtil.isNotEmpty(ptlSecPierceValutioListMap)) {
                for (String finprodId : ptlSecPierceValutioListMap.keySet()) {
                    if (!StringUtil.isNotEmpty(finprodId)){
                        continue;
                    }
                    Map <String, CreditLinesPierce> insAmtMap = new HashMap<String, CreditLinesPierce>();
                    for (PtlSecPierceValDto ptl : ptlSecPierceValutioListMap.get(finprodId)) {
                        if(insAmtMap.get(ptl.getInstitutionCode()) == null){
                            if (StringUtil.isNotEmpty(ptl.getAssetType())
                                    &&ptl.getAssetType().equals(DictCodeList.平安资产类型_债券.getValue())){
                                if (creditQuoteTypeCodeList.contains(ptl.getVarietyType1())){
                                    CreditLinesPierce dto = new CreditLinesPierce();
                                    dto.setCreditFlag("Y");
                                    dto.setSeqno(IDGenerator.generate());
                                    dto.setCustomerId(finprodId);
                                    dto.setCdate(currentDate);
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                                    dto.setCcy(ptlSecPierceValutioListMap.get(finprodId).get(0).getCcy());
                                    dto.setCreditMstId("0002");
                                    dto.setInstitutionCode(ptl.getInstitutionCode());
                                    dto.setCreditAmt(ptl.getDscCostAmt());
                                    insAmtMap.put(ptl.getInstitutionCode(),dto);
                                }
                            }else{
                                CreditLinesPierce dto = new CreditLinesPierce();
                                dto.setCreditFlag("Y");
                                dto.setSeqno(IDGenerator.generate());
                                dto.setCustomerId(finprodId);
                                dto.setCdate(currentDate);
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                                dto.setCcy(ptlSecPierceValutioListMap.get(finprodId).get(0).getCcy());
                                dto.setCreditMstId("0002");
                                dto.setInstitutionCode(ptl.getInstitutionCode());
                                dto.setCreditAmt(ptl.getDscCostAmt());
                                insAmtMap.put(ptl.getInstitutionCode(),dto);
                            }
                        }else{
                            if (StringUtil.isNotEmpty(ptl.getAssetType())
                                    &&ptl.getAssetType().equals(DictCodeList.平安资产类型_债券.getValue())){
                                if (creditQuoteTypeCodeList.contains(ptl.getVarietyType1())){
                                    insAmtMap.get(ptl.getInstitutionCode()).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(ptl.getInstitutionCode()).getCreditAmt(),ptl.getDscCostAmt()));
                                }
                            }else{
                                insAmtMap.get(ptl.getInstitutionCode()).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(ptl.getInstitutionCode()).getCreditAmt(),ptl.getDscCostAmt()));
                            }
                        }
                    }
                    for(String institutionCode : insAmtMap.keySet()){
                        result.add(insAmtMap.get(institutionCode));
                    }
                }
            }
            // 机构授信
            Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioOrgListMap = ptlSecPierceValutioList.stream()
                    .collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getOrganId));
            if (StringUtil.isNotEmpty(ptlSecPierceValutioOrgListMap)){
                for (String organId:ptlSecPierceValutioOrgListMap.keySet()){
                    if (!StringUtil.isNotEmpty(organId)){
                        continue;
                    }
                    Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioVariety1ListMap =
                            ptlSecPierceValutioOrgListMap.get(organId).stream().collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getVarietyType1));
                    Map <String, CreditLinesPierce> insAmtMapOrg = new HashMap<String, CreditLinesPierce>();
                    for (String varietyType1:ptlSecPierceValutioVariety1ListMap.keySet()){
                        if (!StringUtil.isNotEmpty(varietyType1)){
                            continue;
                        }
                        PtlSecPierceValDto entity = ptlSecPierceValutioVariety1ListMap.get(varietyType1).get(0);
                        if (StringUtil.isNotEmpty(getAssetTypeFirstLevel(entity.getAssetType()))&&!StringUtil.isEmptyList(creditMstInfoListMap.get(
                                getAssetTypeFirstLevel(entity.getAssetType()) + ":" + entity.getVarietyType1()))) {
                            Map <String, CreditLinesPierce> insAmtMap = new HashMap<String, CreditLinesPierce>();
                            for (PtlSecPierceValDto ptl : ptlSecPierceValutioVariety1ListMap.get(varietyType1)) {
                                String flag;
                                if(StringUtil.isNotEmpty(ptl.getFinprodId())){
                                    flag = ceLinesUpdate.getCreditFlag(ptl.getFinprodId(),currentDate);
                                }else{
                                    flag = "Y";
                                }
                                if(insAmtMap.get(ptl.getInstitutionCode()) == null){
                                    CreditLinesPierce dtoOne = new CreditLinesPierce();
                                    dtoOne.setSeqno(IDGenerator.generate());
                                    dtoOne.setCreditFlag(StringUtil.equals(dtoOne.getCreditFlag(), "Y") ? "Y" : flag);
                                    dtoOne.setCustomerId(organId);
                                    dtoOne.setCdate(currentDate);
                                    dtoOne.setCcy(ptlSecPierceValutioVariety1ListMap.get(varietyType1).get(0).getCcy());
                                    dtoOne.setCreditMstId(creditMstInfoListMap.get(
                                            getAssetTypeFirstLevel(entity.getAssetType()) + ":" + entity.getVarietyType1()).get(0).getSeqno());
                                    dtoOne.setInstitutionCode(ptl.getInstitutionCode());
                                    dtoOne.setCreditAmt(ptl.getDscCostAmt());
                                    insAmtMap.put(ptl.getInstitutionCode(),dtoOne);
                                }else{
                                    insAmtMap.get(ptl.getInstitutionCode()).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(ptl.getInstitutionCode()).getCreditAmt(),ptl.getDscCostAmt()));
                                    insAmtMap.get(ptl.getInstitutionCode()).setCreditFlag(StringUtil.equals(insAmtMap.get(ptl.getInstitutionCode()).getCreditFlag(), "Y") ? "Y" : flag);
                                }
                            }
                            for(String institutionCode : insAmtMap.keySet()){
                                if(insAmtMapOrg.get(institutionCode) == null){
                                    CreditLinesPierce dto = new CreditLinesPierce();
                                    dto.setCreditFlag(StringUtil.equals(dto.getCreditFlag(), "Y") ? "Y" : insAmtMap.get(institutionCode).getCreditFlag());
                                    dto.setSeqno(IDGenerator.generate());
                                    dto.setCustomerId(organId);
                                    dto.setCdate(currentDate);
                                    dto.setCreditMstId("0001");
                                    dto.setCcy(ptlSecPierceValutioOrgListMap.get(organId).get(0).getCcy());
                                    dto.setInstitutionCode(institutionCode);
                                    dto.setCreditAmt(insAmtMap.get(institutionCode).getCreditAmt());
                                    insAmtMapOrg.put(institutionCode, dto);
                                }else{
                                    insAmtMapOrg.get(institutionCode).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(institutionCode).getCreditAmt(), insAmtMapOrg.get(institutionCode).getCreditAmt()));

                                    insAmtMapOrg.get(institutionCode).setCreditFlag(StringUtil.equals(insAmtMapOrg.get(institutionCode).getCreditFlag(), "Y") ? "Y" : insAmtMap.get(institutionCode).getCreditFlag());
                                }
                                result.add(insAmtMap.get(institutionCode));
                            }
                        }
                    }
                    for(String institutionCode : insAmtMapOrg.keySet()){
                        result.add(insAmtMapOrg.get(institutionCode));
                        if (StringUtil.equals(insAmtMapOrg.get(institutionCode).getCreditFlag(),"Y")){
                            if(ceLinesUpdate.isKDQ(insAmtMapOrg.get(institutionCode).getCreditMstId(), insAmtMapOrg.get(institutionCode).getCustomerId(), insAmtMapOrg.get(institutionCode).getCdate(), institutionCode)<=0){
                                CreditLines creditLines = new CreditLines();
                                String seqNo = "";
                                if (baseGetBusinessId != null) {
                                    BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
                                    seqNo = baseGetBusinessId.buildBussinessCode(codeParameter);
                                }
                                else {
                                    seqNo= IDGenerator.generate();
                                }
                                creditLines.setSeqno(seqNo);
                                creditLines.setCreditAmt(BigDecimal.ZERO);
                                creditLines.setCreditMstId("0001");
                                String customerId = insAmtMapOrg.get(institutionCode).getCustomerId();
                                creditLines.setCustomerId(customerId);
                                creditLines.setCreditFlag("Y");
                                creditLines.setVdate(insAmtMapOrg.get(institutionCode).getCdate());
                                creditLines.setMdate(DateUtil.parse("2099-12-31"));
                                creditLines.setPflsh("空档期授信"+IDGenerator.generate().substring(0, 30));
                                creditLines.setCreateDept(insAmtMapOrg.get(institutionCode).getCreateDept());
                                creditLines.setCreateTime(insAmtMapOrg.get(institutionCode).getCreateTime());
                                creditLines.setCreateUser(insAmtMapOrg.get(institutionCode).getCreateUser());
                                creditLines.setUpdateTime(insAmtMapOrg.get(institutionCode).getUpdateTime());
                                creditLines.setUpdateUser(insAmtMapOrg.get(institutionCode).getUpdateUser());
                                creditLines.setInstitutionCode(insAmtMapOrg.get(institutionCode).getInstitutionCode());
                                creditLines.setHapCreditAmt(ceLinesUpdate.extendCreditLinesDeal(creditLines.getSeqno(), creditLines.getVdate(), creditLines.getCreditMstId(),creditLines.getCustomerId(),insAmtMapOrg.get(institutionCode).getInstitutionCode()));
                                creditMapper.insert(creditLines);
                            }
                        }
                    }
                    Map<String,List<PtlSecPierceValDto>> ptlSecPierceValutioVariety2ListMap =
                            ptlSecPierceValutioOrgListMap.get(organId).stream().collect(JoyinCollectors.groupingBy(PtlSecPierceValDto::getVarietyType2));
                    for (String varietyType2:ptlSecPierceValutioVariety2ListMap.keySet()){
                        if (!StringUtil.isNotEmpty(varietyType2)){
                            continue;
                        }
                        PtlSecPierceValDto entity = ptlSecPierceValutioVariety2ListMap.get(varietyType2).get(0);
                        if (StringUtil.isNotEmpty(getAssetTypeSecondLevel(entity.getAssetType()))&&!StringUtil.isEmptyList(creditMstInfoListMap.get(
                                getAssetTypeSecondLevel(entity.getAssetType()) + ":" + entity.getVarietyType2()))) {

                            Map <String, CreditLinesPierce> insAmtMap = new HashMap<String, CreditLinesPierce>();
                            for (PtlSecPierceValDto ptl : ptlSecPierceValutioVariety2ListMap.get(varietyType2)) {
                                String flag;
                                if(StringUtil.isNotEmpty(ptl.getFinprodId())){
                                    flag = ceLinesUpdate.getCreditFlag(ptl.getFinprodId(),currentDate);
                                }else{
                                    flag = "Y";
                                }
                                if(insAmtMap.get(ptl.getInstitutionCode()) == null){
                                    CreditLinesPierce dtoTwo = new CreditLinesPierce();
                                    dtoTwo.setCreditFlag(StringUtil.equals(dtoTwo.getCreditFlag(), "Y") ? "Y" : flag);
                                    dtoTwo.setSeqno(IDGenerator.generate());
                                    dtoTwo.setCustomerId(organId);
                                    dtoTwo.setCdate(currentDate);
                                    dtoTwo.setCreditMstId(creditMstInfoListMap.get(
                                            getAssetTypeSecondLevel(entity.getAssetType()) + ":" + entity.getVarietyType2()).get(0).getSeqno());
//                       dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).get);
                                    dtoTwo.setCcy(ptlSecPierceValutioVariety2ListMap.get(varietyType2).get(0).getCcy());
                                    dtoTwo.setInstitutionCode(ptl.getInstitutionCode());
                                    dtoTwo.setCreditAmt(ptl.getDscCostAmt());
                                    insAmtMap.put(ptl.getInstitutionCode(),dtoTwo);
                                }else{
                                    insAmtMap.get(ptl.getInstitutionCode()).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(ptl.getInstitutionCode()).getCreditAmt(),ptl.getDscCostAmt()));
                                    insAmtMap.get(ptl.getInstitutionCode()).setCreditFlag(StringUtil.equals(insAmtMap.get(ptl.getInstitutionCode()).getCreditFlag(), "Y") ? "Y" : flag);
                                }
                            }
                            for(String institutionCode : insAmtMap.keySet()){
                                result.add(insAmtMap.get(institutionCode));
                            }
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
                    BigDecimal creditAmt = BigDecimal.ZERO;
                    Map <String, CreditLinesPierce> insAmtMap = new HashMap<String, CreditLinesPierce>();
                    for(PtlSecPierceValDto ptl : ptlSecPierceValutiZgjhListMap.get(zgplanId)){
                        if(insAmtMap.get(ptl.getInstitutionCode()) == null){
                            CreditLinesPierce dto = new CreditLinesPierce();
                            dto.setCreditFlag("Y");
                            dto.setCustomerId(zgplanId);
                            dto.setCdate(currentDate);
                            dto.setCreditMstId("0002");
//                    dto.setPlanAssetId(ptlSecPierceValutioListMap.get(finprodId).get(0).getPlanAssetId());
                            dto.setCcy(ptlSecPierceValutiZgjhListMap.get(zgplanId).get(0).getCcy());
                            dto.setInstitutionCode(ptl.getInstitutionCode());
                            dto.setCreditAmt(ptl.getDscCostAmt());
                            insAmtMap.put(ptl.getInstitutionCode(),dto);
                        }else{
                            insAmtMap.get(ptl.getInstitutionCode()).setCreditAmt(ArithmeticUtil.doAdd(insAmtMap.get(ptl.getInstitutionCode()).getCreditAmt(),ptl.getDscCostAmt()));
                        }
                    }
                    for(String institutionCode : insAmtMap.keySet()){
                        result.add(insAmtMap.get(institutionCode));
                    }
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
