/*
 * 文件名：FinStageManagementService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2019年3月12日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.common.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.asset.FinInterestFactorService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.common.dto.AssetCommonInvRateAdjustDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.project.BussParticipantInfoService;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.dto.FinStageManagementDto;
import com.joyintech.tams.invest.asset.plan.planProfit.dto.FinPlanProfitAssetDto;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDetailDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.calculator.RepayScheduleCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 期次管理 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementService
 * @since 1.0
 */
@Service
public class FinStageManagementService extends CommonExportService<FinStageManagementDto> implements BaseProcessService<FinStageManagementDto> {

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private AssetCommonService assetCommonService;

    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;

    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    private DateCalService dateCalService;
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private BussParticipantInfoService bussParticipantInfoService;

    /**
     * 共通Mapper的通用代理方案
     */

    @SuppressWarnings("unchecked")
    public Map<String, Object> getRates(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/getRates", map, Map.class);
    }

    public List<FinStageManagementDto> getfinStageManagementByProductId(Map<String, Object> paramMap) {
        List<FinStageManagementDto> finStageManagementList = restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/getfinStageManagementByProductId", paramMap,
            FinStageManagementDto.class);
        for (FinStageManagementDto entity : finStageManagementList) {
            String updateFlg = StringUtil.EMPTY;
            if (DateUtil.compareDate(entity.getMdate(), iPlatformDate.getSysDate()) == -1) {
                updateFlg = "02";
            }
            else {
                updateFlg = "01";
            }
            entity.setUpdateFlg(updateFlg);
        }
        return finStageManagementList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinStageManagementDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 重新计算现金流计划
        if (null != entity.getSelectedItem() && !entity.getSelectedItem().isEmpty()) {
            entity = encapFinStageManagementDto(entity);
        }
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/" + action, entity,
            Integer.class);
        // 二级法人权限处理 wanngyang1 2020年7月8日
        entity.getFinPlanProfitAssetList().forEach(item ->{ 
            if(MultilevelDataAuthorityUtil.isEnabled()) {
                //创建机构代码
                String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(item.getFinProduct().getCreateUser());
                item.setCreateInst(createInst);
                // 有查询权限的机构代码
                List<String> searchOrgCodeList = null;
                // 有编辑权限的机构代码
                List<String> editOrgCodeList = null;
                // 新建或修改时重新设置二级法人权限，删除时则清空
                if (!OperationType.DELETE.equals(type)) {
                    //获取所有父级机构。  拥有查看权限
                    searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                    // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                    editOrgCodeList = Arrays.asList(item.getCreateInst());
                }
                // 记录二级法人权限
                insertDataAuthTypeInst(item.getFinProduct().getFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_计划类资产_利率型_信息.getValue());
             }
        });
    }

    private FinStageManagementDto encapFinStageManagementDto(FinStageManagementDto dto) {
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
            CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        List<FinPlanProfitAssetDto> finPlanProfitAssetList = new ArrayList<FinPlanProfitAssetDto>();
        // 获取标的基本信息---add by 裴家俊----start------
        FinProduct finProductEntry = finProductMapper.selectByPrimaryKey(dto.getFinprodId());
        FinProductType baseTypeEntry = new FinProductType();
        baseTypeEntry.setFinprodId(dto.getFinprodId());
        baseTypeEntry.setBranch(branch);
        FinProductType finProductTypeEntry = finProductTypeMapper.selectByPrimaryKey(baseTypeEntry);
        // 获取标的基本信息---add by 裴家俊----end------
        // 计息规则
        // 根据主键获取业务实体--计息要素基本信息
        FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorByProductId(dto.getFinprodId(),
            DateUtil.format(dto.getVdate()));
        // 根据主键获取业务实体--付息信息
        FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            dto.getFinprodId(), DateUtil.format(dto.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue(),
            DictCodeListExt1.现金流业务类型_金融产品.getValue());
        if (null != dto.getSelectedItem() && !dto.getSelectedItem().isEmpty()) {
            dto.getSelectedItem().forEach(entity -> {
                FinPlanProfitAssetDto finPlanProfitAssetDto = new FinPlanProfitAssetDto();
                BigDecimal rate = ArithmeticUtil.divide(entity.getInvestRate(), new BigDecimal(100), 14);
                BigDecimal loadRate = ArithmeticUtil.divide(entity.getLoadRate(), new BigDecimal(100), 14);
                BigDecimal buyOutRate = ArithmeticUtil.divide(entity.getBuyoutRate(), new BigDecimal(100), 14);
                // 标的信息
                FinProduct finProduct = new FinProduct();
                finProduct.setFinprodName(dto.getFinprodName() + "_" + entity.getPeriod());
                finProduct.setFinprodAbbr(dto.getFinprodName() + "_" + entity.getPeriod());
                finProduct.setpFinprodId(dto.getFinprodId());
                finProduct.setFinprodMarketId(dto.getFinprodMarketId() + "_" + entity.getPeriod());
                finProduct.setCalendarId(dto.getCalendarId());
                if (StringUtil.isNotEmpty(entity.getFinprodId())) {
                    finProduct.setFinprodId(entity.getFinprodId());
                }
                else {
                    finProduct.setFinprodId(commonBusinessService.getFinProdId(finProduct.getFinprodMarketId(),
                        DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue(), null));
                }
                finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
                finProduct.setVdate(entity.getVdate());
                finProduct.setMdate(entity.getMdate());
                finProduct.setIssueAmt(entity.getIssueAmt());
                finProduct.setCcy(dto.getCcy());
                // ---add by 裴家俊----start------
                finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
                // ---add by 裴家俊----end------
                //add  by  zhagnnian 20190911 start
                finProduct.setTrusteeId(finProductEntry.getTrusteeId());
                finProduct.setIssuer(finProductEntry.getIssuer());
                finProduct.setTermDays(DateUtil.getBetweenDayNumber(entity.getVdate(),entity.getMdate()));
                finProduct.setManager(finProductEntry.getManager());
                finProduct.setCapiIncomeFeature(finProductEntry.getCapiIncomeFeature());
                //add  by  zhagnnian 20190911 end
                finPlanProfitAssetDto.setFinProduct(finProduct);
                // 标的附属
                FinProductAdd finProductAdd = new FinProductAdd();
                finProductAdd.setFinprodId(finProduct.getFinprodId());
                finProductAdd.setSalePeriod(Long.parseLong(entity.getPeriod()));
                finPlanProfitAssetDto.setFinProductAdd(finProductAdd);
                // add by 裴家俊 2019/03/29------start---------金融产品分类表
                FinProductType finProductType = new FinProductType();
                finProductType.setFinprodId(finProduct.getFinprodId());
                finProductType.setBranch(branch);
                finProductType.setType6(finProductTypeEntry.getType6());
                finProductType.setType7(finProductTypeEntry.getType7());
                finProductType.setType8(finProductTypeEntry.getType8());
                //add  by  zhagnnian 20190911 start
                finProductType.setType9(finProductTypeEntry.getType9());
                finProductType.setType10(finProductTypeEntry.getType10());
                finProductType.setType11(finProductTypeEntry.getType11());
                finProductType.setType12(finProductTypeEntry.getType12());
                //add  by  zhagnnian 20190911 end
                finPlanProfitAssetDto.setFinProductType(finProductType);
                // add by 裴家俊 2019/03/29------end---------金融产品分类表
                // 现金流信息
                List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
                // 现金流信息Entity-本金
                FinCashInfo finCashInfoPrincipal = assetCommonService.encapFinCashInfo(finProduct,
                    DictCodeListExt1.金融产品类型_理财产品.getValue(), DictCodeListExt1.现金流一级类型_本金.getValue(),
                    DictCodeListExt1.现金流二级类型_本金.getValue(), branch, null);
                // 现金流信息Entity-利息
                FinCashInfo finCashInfoInterest = assetCommonService.encapFinCashInfo(finProduct,
                    DictCodeListExt1.金融产品类型_理财产品.getValue(), DictCodeListExt1.现金流一级类型_利息.getValue(),
                    DictCodeListExt1.现金流二级类型_利息.getValue(), branch, null);
                if (StringUtil.isNotEmpty(entity.getIntCashId())) {
                    finCashInfoInterest.setCashId(entity.getIntCashId());
                }
                if (StringUtil.isNotEmpty(entity.getPriCashId())) {
                    finCashInfoPrincipal.setCashId(entity.getPriCashId());
                }
                // ----add by 裴家俊--2019/03/29-------start----
                finCashInfoPrincipal.setCcy(StringUtil.isNotEmpty(
                    finProductEntry) ? StringUtil.isNotEmpty(finProductEntry.getCcy()) ? finProductEntry.getCcy() : null : null);
                finCashInfoInterest.setCcy(StringUtil.isNotEmpty(
                    finProductEntry) ? StringUtil.isNotEmpty(finProductEntry.getCcy()) ? finProductEntry.getCcy() : null : null);
                // ----add by 裴家俊--2019/03/29-------end----
                finCashInfoList.add(finCashInfoPrincipal);
                finCashInfoList.add(finCashInfoInterest);
                finPlanProfitAssetDto.setFinCashInfoList(finCashInfoList);
                // 现金流利率信息
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                FinRateInfo finRateInfo = new FinRateInfo();
                finRateInfo.setCashId(finCashInfoInterest.getCashId());
                finRateInfo.setEffDate(entity.getVdate());
                finRateInfo.setIntType(finInterestFactorDto.getIntType());
                finRateInfo.setBasis(finInterestFactorDto.getBasis());
                finRateInfo.setRate(rate);
                finRateInfo.setBranch(branch);
                BeanUtil.copyPropertiesIgnoreNull(finRateInfo, finProduct);
                finRateInfoList.add(finRateInfo);
                finPlanProfitAssetDto.setFinRateInfoList(finRateInfoList);
				
				  /**
                 * add by xr 增加现金流支付规则表 start
                 */
                List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
                FinCashPayRule   finCashPayRule=new FinCashPayRule();
                finCashPayRule.setCashId(finCashInfoInterest.getCashId());
                finCashPayRule.setEffDate(entity.getVdate());
                finCashPayRule.setPayCycle(BussinessDictCodeList.频率_到期一次.getValue());
                finCashPayRule.setPayTdayRule(BussinessDictCodeList.营业日准则_向后调整.getValue());
                finCashPayRule.setCalcRule(BussinessDictCodeList.推算方式_起息日向后.getValue());
                finCashPayRule.setFinprodId(finProduct.getFinprodId());
                finCashPayRule.setFinprodType(finProduct.getFinprodType());
                finCashPayRule.setFinprodType2(finProduct.getFinprodType2());
                finCashPayRule.setBranch(finCashInfoInterest.getBranch());
                finCashPayRule.setPayType("01");

                finCashPayRuleList.add(finCashPayRule);

                finPlanProfitAssetDto.setFinCashPayRuleList(finCashPayRuleList);
                /**
                 * add by xr 增加现金流支付规则表 end
                 */
				
				
                // 现金流计算规则
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                finCashCalcRule.setCashId(finCashInfoInterest.getCashId());
                finCashCalcRule.setEffDate(entity.getVdate());
                finCashCalcRule.setCalcType(finInterestFactorDto.getIntType());
                finCashCalcRule.setBasis(finInterestFactorDto.getBasis());
                finCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
                finCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
                finCashCalcRule.setIsInitial(DictCodeList.是否_是.getValue());
                finCashCalcRule.setYield(rate);
                BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, finProduct);
                finCashCalcRule.setBranch(branch);
                finCashCalcRuleList.add(finCashCalcRule);
                finPlanProfitAssetDto.setFinCashCalcRuleList(finCashCalcRuleList);
                // 计算规则扩展表
                PaFinCashCalcRule paFinCashCalcRule = new PaFinCashCalcRule();
                paFinCashCalcRule.setCashId(finCashInfoInterest.getCashId());
                paFinCashCalcRule.setEffDate(entity.getVdate());
                paFinCashCalcRule.setLoadRate(loadRate);
                paFinCashCalcRule.setInvestRate(rate);
                finPlanProfitAssetDto.setPaFinCashCalcRule(paFinCashCalcRule);
                // 金融产品买断利率
                PaFinProductBuyoutRate paFinProductBuyoutRate = new PaFinProductBuyoutRate();
                paFinProductBuyoutRate.setFinprodId(finProduct.getFinprodId());
                paFinProductBuyoutRate.setEffDate(entity.getVdate());
                paFinProductBuyoutRate.setBuyoutRate(buyOutRate);
                finPlanProfitAssetDto.setPaFinProductBuyoutRate(paFinProductBuyoutRate);
                // 生成一条到期还本计划并生成利息
                try {
                    List<FinCashPlan> finCashPlanList = getFinCashPlan(entity, finPayInterestStdInfoDto, finCashInfoPrincipal,
                        finProduct, branch);
                    // ---
                    FinCashPlan finCashPlan = new FinCashPlan();
                    BeanUtil.copyPropertiesIgnoreNull(finCashPlan, entity);
                    finCashPlan.setRangeYield(rate);
                    finCashPlan.setCashId(finCashInfoInterest.getCashId());
                    finCashPlan.setCashType(DictCodeList.现金流类型_到期收息.getValue());
                    finCashPlan.setCashBaseamt(entity.getIssueAmt());
                    finCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
                    finCashPlan.setFinprodId(finProduct.getFinprodId());
                    finCashPlan.setFinprodType(finProduct.getFinprodType());
                    finCashPlan.setFinprodType2(finProduct.getFinprodType2());
                    finCashPlan.setMdateUnadjust(finCashPlan.getMdate());
                    finCashPlan.setVdateUnadjust(finCashPlan.getVdate());
                    finCashPlan.setPayDate(finCashPlan.getMdate());
                    BigDecimal basis;
                    if (StringUtil.equals(finInterestFactorDto.getBasis(), DictCodeList.计息基础_A_360.getValue())) {
                        basis = new BigDecimal(360);
                    }
                    else {
                        basis = new BigDecimal(365);
                    }
                    BigDecimal intAmt = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(ArithmeticUtil.multiply(entity.getIssueAmt(),
                            new BigDecimal(DateUtil.getBetweenDayNumber(entity.getVdate(), entity.getMdate()))), rate),
                        basis, 2);
                    finCashPlan.setIntAmt(intAmt);
                    finCashPlan.setBranch(branch);
                    finCashPlan.setPayType(DictCodeList.分红方式_现金分红.getValue());
                    finCashPlan.setCashAmt(intAmt);
                    long termdays = DateUtil.getBetweenDayNumber(DateUtil.format(entity.getVdate()),
                        DateUtil.format(entity.getMdate()));
                    finCashPlan.setTermdays(termdays);
                    finCashPlanList.add(finCashPlan);

                    // ---
                    finPlanProfitAssetDto.setFinCashPlanList(finCashPlanList);
                    //add by zhangnian  20191017 start
                    //通道信息
                    List<BussParticipantInfoDto> bussParticipantInfoList = new ArrayList<BussParticipantInfoDto>();
                    BussParticipantInfoDto bussParticipantInfoDtoParam = new BussParticipantInfoDto();
                    Map<String,Object> paramMap = new HashMap<String,Object>();
                    paramMap.put("finprodId", dto.getFinprodId());
                    paramMap.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                    paramMap.put("isChl", DictCodeList.是否_是.getValue());
                    paramMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
                    paramMap.put("cashType02c11", DictCodeListExt1.现金流二级类型_通道托管费.getValue());
                    paramMap.put("cashType02c12", DictCodeListExt1.现金流二级类型_通道管理费.getValue());
                    paramMap.put("cashType02c13", DictCodeListExt1.现金流二级类型_通道其他费.getValue());
                    bussParticipantInfoList =  bussParticipantInfoService.queryBussParticipantInfo(paramMap);
                    bussParticipantInfoDtoParam.setBussParticipantInfoDtoList(bussParticipantInfoList);
                    BussParticipantInfoDto bussParticipantInfoDto = assetCommonService.enBussParticipantInfoDto(finProduct,
                            bussParticipantInfoDtoParam, branch);
                    finCashInfoList.addAll(bussParticipantInfoDto.getFinCashInfoList());
                    finCashCalcRuleList.addAll(bussParticipantInfoDto.getFinCashCalcRuleList());
                    //add by zhangnian  20191017 end
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finPlanProfitAssetList.add(finPlanProfitAssetDto);
            });
        }
        dto.setFinPlanProfitAssetList(finPlanProfitAssetList);
        return dto;
    }

    /**
     * 构建计划且计算利息 <功能详细描述>
     * 
     * @param dto
     * @param finPayInterestStdInfoDto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private List<FinCashPlan> getFinCashPlan(FinStageManagementDto dto, FinPayInterestStdInfoDto finPayInterestStdInfoDto,
                                             FinCashInfo finCashInfoPrincipal, FinProduct finProduct, Long branch)
        throws Exception {
        // 还本计划(return)
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        // 还本计划(cal)
        List<FinCashPlanDto> invRepayList = new ArrayList<FinCashPlanDto>();
        // 资产公共DTO
        AssetCommonInfoDto scai = new AssetCommonInfoDto();
        scai.setVdate(dto.getVdate());
        scai.setMdate(dto.getMdate());
        scai.setAmount(dto.getIssueAmt());
        // 付息规则
        FinCashPayRule is = new FinCashPayRule();
        BeanUtil.copyPropertiesIgnoreNull(is, finPayInterestStdInfoDto); // 复制付息规则
        // 到期日+15天
        Date mdate = DateUtil.parse(DateUtil.plusDays(DateUtil.format(scai.getMdate()), 15));
        // 获取节假日
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(), mdate, scai.getCalendarId());
        // 报价信息
        List<AssetCommonInvRateAdjustDto> rateList = new ArrayList<AssetCommonInvRateAdjustDto>();
        AssetCommonInvRateAdjustDto assetCommonInvRateAdjustDto = new AssetCommonInvRateAdjustDto();
        assetCommonInvRateAdjustDto.setYield(ArithmeticUtil.divide(dto.getInvestRate(), new BigDecimal(100), 14));
        assetCommonInvRateAdjustDto.setEffDate(dto.getVdate());
        rateList.add(assetCommonInvRateAdjustDto);
        // 付息营业日规则
        String payTdayRule = finPayInterestStdInfoDto.getPayTdayRule();
        FinCashPlanDto newFinCashPlan = new FinCashPlanDto();
        newFinCashPlan.setCashId(finCashInfoPrincipal.getCashId());
        newFinCashPlan.setVdateUnadjust(dto.getVdate());
        newFinCashPlan.setMdateUnadjust(dto.getMdate());
        newFinCashPlan.setVdate(dto.getVdate());
        newFinCashPlan.setMdate(dto.getMdate());
        newFinCashPlan.setPrinAmt(dto.getIssueAmt());
        newFinCashPlan.setIntPrinAmt(dto.getIssueAmt());
        newFinCashPlan.setCashBaseamt(dto.getIssueAmt());
        newFinCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
        newFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        newFinCashPlan.setCashType(DictCodeList.现金流类型_到期还本.getValue());
        newFinCashPlan.setPayType(DictCodeList.分红方式_现金分红.getValue());
        newFinCashPlan.setBranch(branch);
        BeanUtil.copyPropertiesIgnoreNull(newFinCashPlan, finProduct);
        String payDate = AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(dto.getMdate()), payTdayRule, holidayList);
        newFinCashPlan.setPayDate(DateUtil.parse(payDate));
        invRepayList.add(newFinCashPlan);
        invRepayList = RepayScheduleCal.cal(scai, invRepayList, is, rateList, scai.getAmount(), FinCashPlanDto.class,
            FinCashPlanDetailDto.class);
        invRepayList.forEach(item -> {
            item.setCashAmt(item.getPrinAmt());
            item.setIntAmt(BigDecimal.ZERO);
            long termdays = DateUtil.getBetweenDayNumber(DateUtil.format(item.getVdate()), DateUtil.format(item.getMdate()));
            item.setRangeYield(ArithmeticUtil.divide(dto.getInvestRate(), new BigDecimal(100), 14));
            item.setTermdays(termdays);
            finCashPlanList.add(item);
        });
        return finCashPlanList;
    }
    /**
     * 校验有没子期次做过到期，如果有，则不允许修改到期日
     * @param dto
     * @return
     * @throws Exception
     */ 
    public boolean isCheckMature(FinStageManagementDto dto){
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/isCheckMature", dto, Boolean.class);
        //restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/getRates", map, Map.class);
    }

    /**
     * 期次计划流程中的数据
     * @param dto
     * @return
     */
    public List<ProcessMark> isWorkFlowCheck(FinStageManagementDto dto) {
        Map map = new HashMap();
        map.put("finprodId",dto.getFinprodId());
        map.put("pageId","FinStageManagement");
        return restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/isWorkFlowCheck",map, ProcessMark.class);
    }
    /**
     * 根据产品代码得到起息日和到期日 add by yujiaping 2020-11-10
     * <功能详细描述>
     * @param finprodId String
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public FinStageManagementDto getVdateAndMdate(String finprodId){
        return simpleRestTemplate.getForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinStageManagement/getVdateAndMdate?finprodId=" +finprodId, 
                FinStageManagementDto.class);
    }
}
