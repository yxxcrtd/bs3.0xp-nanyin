/*
 * 文件名：FinDepositTargetService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年9月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.asset.depo.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.basicdata.business.dto.BussinessCodeParameter;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.asset.service.FinInterestFactorService;
import com.joyintech.tams.valuation.component.asset.service.FinPayInterestStdInfoService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存款类资产Service
 * 
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see com.joyintech.tams.valuation.basicdata.asset.depo.service.FinDepositTargetService
 * @since 1.0
 */
@Service
public class FinDepositTargetService extends CommonExportService<FinDepositTargetDto> implements BaseProcessService<FinDepositTargetDto> {

    /**
     * 共通--资产
     */
    @Autowired
    private ValAssetCommonService assetCommonService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 业务--计息要素
     */
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;

    /**
     * 业务--付息信息
     */
    @Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;


    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinDepositTargetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType", DictCodeListExt1.金融产品类型_存款.getValue());
        pageParam.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinDepositTarget/findPage", pageParam,
            FinDepositTargetDto.class);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(FinDepositTargetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 封装标的类金融产品基础组件需要的DTO
        entity = encapBaseFinProductBaseInfoList(entity);
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
        }

        // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
        if (!StringUtil.isEmptyList(entity.getBaseFinProductBaseInfoList())) {
            for (BaseFinProductBaseInfoDto productBaseInfoDto : entity.getBaseFinProductBaseInfoList()) {
                if (!StringUtil.isEmptyList(productBaseInfoDto.getFinCashPayRuleList())) {
                    for (FinCashPayRule finCashPayRule : productBaseInfoDto.getFinCashPayRuleList()) {
                        if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
                            finCashPayRule.setPayCycle(ValComponentConstant.DEFAULT_PAY_CYCLE);
                        }
                    }
                }
            }
        }

        restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinDepositTarget/" + action, entity, Integer.class);
    }

    /**
     * 封装基础组件-标的类金融产品需要的DTO
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto encapBaseFinProductBaseInfoList(FinDepositTargetDto dto) {
        // 分支序号
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
                CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        // 现金流信息List
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        // 现金流计算规则
        List<FinCashCalcRule> finCashCalcRuleAllList = new ArrayList<FinCashCalcRule>();
        // 资产公共信息
        // 标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        // 简称存全称
        finProduct.setFinprodAbbr(dto.getFinprodName());
        //默认本金收益特征为保本，默认为是，入库时转化成01
        finProduct.setCapiIncomeFeature(dto.getCapiIncomeFeature());
        //实际到期日
        finProduct.setActmdate(dto.getMdate());

        //存放同业、活期存放 F07
        if(DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(dto.getAssetType()) || DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getAssetType())){
            finProduct.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        }else {//同业借款  F21
            finProduct.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
        }
        finProduct.setFinprodType2(finProduct.getFinprodType());
//        if (StringUtil.equals(dto.getAssetType(), DictCodeList.存款类资产类型_GZ_存放同业.getValue())) {
//            finProduct.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
//        }
        // 金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType8(DictCodeList.平安资产类型_存款.getValue());
        finProductType.setType1(dto.getAssetType());//资产类型
        finProductType.setType2(dto.getAccountintType());//核算分类
        finProductType.setType3(dto.getTaxType());//税务分类
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
        if("submit".equals(dto.getOperateType()) && GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())){
            finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());
        }else if(StringUtil.isNotEmpty(dto.getDataSource())){
            finProductAdd.setDataSource(dto.getDataSource());
        }
        if (StringUtil.equals(dto.getAssetType(), DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue())) {//modify by sunss
            finProductAdd.setTermType(
                    getRepTermType(
                            DictCodeListExt1.金融产品类型_同业借款.getValue(), finProduct.getTermDays()));
        }
        // 金融产品扩展表 资产提供行入该表
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, dto);
        // 金融产品买断利率entiity
        PaFinProductBuyoutRate paFinProductBuyoutRate = new PaFinProductBuyoutRate();
        BeanUtil.copyPropertiesIgnoreNull(paFinProductBuyoutRate, dto);
        // 买断利率
        paFinProductBuyoutRate.setBuyoutRate(com.joyintech.framework.util.ArithmeticUtil.divide(dto.getBuyOutRate(), new BigDecimal(100), 14));
        paFinProductBuyoutRate.setEffDate(dto.getVdate());

        FinCashInfo finCashInfoPrincipal=new FinCashInfo();
        //存放同业、活期存放 F07
        if(DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(dto.getAssetType()) || DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getAssetType())){
            // 现金流信息Entity-本金
                 finCashInfoPrincipal = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_存款.getValue(),
                    DictCodeListExt1.现金流一级类型_本金.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue(), branch);
        }else {
            finCashInfoPrincipal = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_同业借款.getValue(),
                    DictCodeListExt1.现金流一级类型_本金.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue(), branch);
        }

        // 现金流信息Entity-利息
        FinCashInfo finCashInfoInterest=new FinCashInfo();
        if(DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(dto.getAssetType()) || DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getAssetType())){
             finCashInfoInterest = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_存款.getValue(),
                    DictCodeListExt1.现金流一级类型_利息.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue(), branch);
        }else {
            finCashInfoInterest = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_同业借款.getValue(),
                    DictCodeListExt1.现金流一级类型_利息.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue(), branch);
        }

        // 现金流信息Entity-资产推荐费
        FinCashInfo finCashInfoRefer=new FinCashInfo();
        if(DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(dto.getAssetType()) || DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getAssetType())){
             finCashInfoRefer = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_存款.getValue(),
                    DictCodeListExt1.现金流一级类型_费用.getValue(), DictCodeListExt1.现金流二级类型_资产推荐费.getValue(), branch);
        }else {
            finCashInfoRefer = encapFinCashInfo(dto, DictCodeListExt1.金融产品类型_同业借款.getValue(),
                    DictCodeListExt1.现金流一级类型_费用.getValue(), DictCodeListExt1.现金流二级类型_资产推荐费.getValue(), branch);
        }

        finCashInfoList.add(finCashInfoPrincipal);
        finCashInfoList.add(finCashInfoInterest);
        finCashInfoList.add(finCashInfoRefer);
        // 计息要素DTO 现金流利率信息 现金流计算规则
        if (null != finInterestFactorService) {
            if (null != dto.getFinInterestFactorDto()) {
                // 将主画面资产推荐费率赋值给计息要素业务组件DTO
                dto.getFinInterestFactorDto().setReferRate(dto.getReferRate());
                FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(
                        dto.getFinInterestFactorDto());
                finInterestFactorDto.setBuyOutRate(dto.getBuyOutRate());
                finInterestFactorDto = assetCommonService.encapFinInterestFactor(finProduct, finInterestFactorDto,
                        finCashInfoInterest, finCashInfoRefer, branch);
                baseFinProductBaseInfoDto.setFinRateInfoList(finInterestFactorDto.getRateList());
                 baseFinProductBaseInfoDto.setFinCashCalcRuleList(finInterestFactorDto.getCalcRuleList());
                finCashCalcRuleAllList.addAll(finInterestFactorDto.getCalcRuleList());
                dto.setFinInterestFactorDto(finInterestFactorDto);
            }
        }
        // 付息信息DTO 现金流支付规则 现金流支付支付计划
        if (null != finPayInterestStdInfoService) {
            if(dto.getFinPayInterestStdInfoDto() != null) {
                FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(
                        dto.getFinPayInterestStdInfoDto());
                finPayInterestStdInfoDto = assetCommonService.encapFinPayInterestStdInfo(finProduct, finPayInterestStdInfoDto,
                        finCashInfoPrincipal, finCashInfoInterest, branch);
                baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
                if (StringUtil.equals(dto.getAssetType(), DictCodeList.存款类资产类型_GZ_存放同业.getValue()) || StringUtil.equals(dto.getAssetType(), DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue())) {//modify by sunss
                    baseFinProductBaseInfoDto.setFinCashPlanList(finPayInterestStdInfoDto.getFinCashPlan2List());
                }
            }
        }

        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleAllList);

        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        dto.setPaFinProduct(paFinProduct);
        dto.setPaFinProductBuyoutRate(paFinProductBuyoutRate);
        return dto;
    }

    /**
     * 组装现金流信息
     *
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(FinDepositTargetDto dto, String commonBusinessType, String cashType1, String cashType2,
                                        Long branch) {
        FinCashInfo finCashInfo = new FinCashInfo();
        // 工作流ID
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), commonBusinessType, branch, cashType2);
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodType(commonBusinessType);
        return finCashInfo;
    }

    /**
     * 解析
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto encapFinDepositTargetDto(FinDepositTargetDto dto) {
        FinDepositTargetDto finDepositTargetDto = new FinDepositTargetDto();
        // 基本信息
        dto.getBaseFinProductBaseInfoList().forEach(baseFinProductBaseInfo -> {
            BeanUtil.copyPropertiesIgnoreNull(finDepositTargetDto, baseFinProductBaseInfo.getFinProduct());
            finDepositTargetDto.setType6(baseFinProductBaseInfo.getFinProductType().getType6());
            finDepositTargetDto.setType7(baseFinProductBaseInfo.getFinProductType().getType7());
        });
        // 计息要素
        if (null != finInterestFactorService) {
            FinInterestFactorDto finInterestFactorDto = new FinInterestFactorDto();
            dto.getBaseFinProductBaseInfoList().forEach(baseFinProductBaseInfo -> {
                baseFinProductBaseInfo.getFinCashCalcRuleList().forEach(finCashCalcRule -> {
                    finInterestFactorDto.setBasis(finCashCalcRule.getBasis());
                });
                baseFinProductBaseInfo.getFinRateInfoList().forEach(finRateInfo -> {
                    // 一体化对应etliu
                    // finInterestFactorDto.setRateType(finRateInfo.getRateType());
                    finInterestFactorDto.setIntType(finRateInfo.getIntType());
                });
            });
            finDepositTargetDto.setFinInterestFactorDto(finInterestFactorDto);
        }
        // 付息信息
        if (null != finPayInterestStdInfoService) {
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = new FinPayInterestStdInfoDto();
            dto.getBaseFinProductBaseInfoList().forEach(baseFinProductBaseInfo -> {
                baseFinProductBaseInfo.getFinCashPayRuleList().forEach(finCashPayRule -> {
                    finPayInterestStdInfoDto.setPayTdayRule(finCashPayRule.getPayTdayRule());
                    finPayInterestStdInfoDto.setPayCycle(finCashPayRule.getPayCycle());
                    finPayInterestStdInfoDto.setCalcRule(finCashPayRule.getCalcRule());
                    finPayInterestStdInfoDto.setFirstPayDate(finCashPayRule.getFirstPayDate());
                });
                List<FinCashPlan> temp = new ArrayList<FinCashPlan>();
                FinCashPlan tempDto = new FinCashPlan();
                tempDto.setVdate(new Date());
                temp.add(tempDto);
                finPayInterestStdInfoDto.setFinCashPlan2List(temp);
                // finPayInterestStdInfoDto.setFinCashPlanList(baseFinProductBaseInfo.getFinCashPlanList());
            });
            finDepositTargetDto.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
        }
        return finDepositTargetDto;
    }

    /**
     * 获取基本信息--修改/详细
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto getBasicInfo(String finprodId,String finprodMarketId,String assetType) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinDepositTarget/getBasicInfo?finprodId="
                                         + finprodId + "&finprodType=" + DictCodeListExt1.现金流业务类型_金融产品.getValue() + "&cashType="
                                         + DictCodeListExt1.现金流二级类型_资产推荐费.getValue()+"&finprodMarketId="+finprodMarketId+"&assetType="+assetType,
            FinDepositTargetDto.class);
    }

    /**
     * 生成金融产品代码/市场代码 <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto getFinprodId(FinDepositTargetDto dto) {
        String finprodMarketId = StringUtil.EMPTY;
        String finprodId = StringUtil.EMPTY;
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(dto.getAssetType(), DictCodeList.存款类资产类型_GZ_活期类资产.getValue())) {
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_存款类资产信息);
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_同业借款);
        }
        else {
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_存款类资产信息);
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_存放同业);
        }
        //YUCHUBS-564:资产代码可手工填写，若新建时不填写，则系统根据规则自动生成 guyunfei 2019/09/05 start
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
            finprodMarketId = dto.getFinprodMarketId();
        }else{
            finprodMarketId = IDGenerator.generate();
        }
        //YUCHUBS-564:资产代码可手工填写，若新建时不填写，则系统根据规则自动生成 guyunfei 2019/09/05 end
        finprodId = commonBusinessService != null ? commonBusinessService.getFinProdId(finprodMarketId,
                DictCodeListExt1.金融产品类型_投管用_存款.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        dto.setFinprodMarketId(finprodMarketId);
        return dto;
    }

    /**
     *
     * 根据条件查询全部信息
     * @param pageParam 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<FinDepositTargetDto> selectAll(Map<String, Object> pageParam){
        pageParam.put("finprodType", DictCodeListExt1.金融产品类型_存款.getValue());
        pageParam.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return restTemplate.postForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME+"/FinDepositTarget/selectAll",pageParam, FinDepositTargetDto.class);
    }

    /**
     * 存款机构
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    public List<SelectOptionDto> getCustomerName(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinDepositTarget/getCustomerName",
                map, SelectOptionDto.class);
    }
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/

    /**
     * 校验金融产品代码不重复
     * @param finprodId
     * @return
     */
    public int selectCountByfinprodId(String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinDepositTarget/selectCountByfinprodId?finprodId="+finprodId,
                Integer.class);
    }

    /**
     * 按规则生成回购期限品种
     */
    public static String getRepTermType(String finprodType, long termDay) {
        String termType = null;
        if (DictCodeListExt1.金融产品类型_债券质押式正回购.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue().equals(finprodType)) {
            termType = DictCodeList.期限品种_回购_未知.getValue();
            if (termDay == 1) {
                termType = DictCodeList.期限品种_回购_1天.getValue();
            }
            else if (termDay > 1 && termDay <= 7) {
                termType = DictCodeList.期限品种_回购_7天.getValue();
            }
            else if (termDay > 7 && termDay <= 14) {
                termType = DictCodeList.期限品种_回购_14天.getValue();
            }
            else if (termDay > 14 && termDay <= 21) {
                termType = DictCodeList.期限品种_回购_21天.getValue();
            }
            else if (termDay > 21 && termDay <= 30) {
                termType = DictCodeList.期限品种_回购_1个月.getValue();
            }
            else if (termDay > 30 && termDay <= 60) {
                termType = DictCodeList.期限品种_回购_2个月.getValue();
            }
            else if (termDay > 60 && termDay <= 91) {
                termType = DictCodeList.期限品种_回购_3个月.getValue();
            }
            else if (termDay > 91 && termDay <= 121) {
                termType = DictCodeList.期限品种_回购_4个月.getValue();
            }
            else if (termDay > 121 && termDay <= 182) {
                termType = DictCodeList.期限品种_回购_6个月.getValue();
            }
            else if (termDay > 182 && termDay <= 273) {
                termType = DictCodeList.期限品种_回购_9个月.getValue();
            }
            else if (termDay > 273 && termDay <= 365) {
                termType = DictCodeList.期限品种_回购_1年.getValue();
            }
            else {
                termType = DictCodeList.期限品种_回购_未知.getValue();
            }
        }
        else if (DictCodeListExt1.金融产品类型_拆入.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_拆出.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)) {
            if (termDay == 1) {
                termType = DictCodeList.期限品种_内部拆借_隔夜.getValue();
            }
            else if (termDay > 1 && termDay <= 7) {
                termType = DictCodeList.期限品种_内部拆借_7天.getValue();
            }
            else if (termDay > 7 && termDay <= 14) {
                termType = DictCodeList.期限品种_内部拆借_14天.getValue();
            }
            else if (termDay > 14 && termDay <= 21) {
                termType = DictCodeList.期限品种_内部拆借_21天.getValue();
            }
            else if (termDay > 21 && termDay <= 30) {
                termType = DictCodeList.期限品种_内部拆借_1个月.getValue();
            }
            else if (termDay > 30 && termDay <= 60) {
                termType = DictCodeList.期限品种_内部拆借_2个月.getValue();
            }
            else if (termDay > 60 && termDay <= 91) {
                termType = DictCodeList.期限品种_内部拆借_3个月.getValue();
            }
            else if (termDay > 91 && termDay <= 182) {
                termType = DictCodeList.期限品种_内部拆借_6个月.getValue();
            }
            else if (termDay > 182 && termDay <= 273) {
                termType = DictCodeList.期限品种_内部拆借_9个月.getValue();
            }
            else if (termDay > 273 && termDay <= 365) {
                termType = DictCodeList.期限品种_内部拆借_12个月.getValue();
            }
            else {
                termType = DictCodeList.期限品种_内部拆借_1年以上.getValue();
            }
        }

        return termType;
    }

    /**
     * 获取所有资产id
     * @param search
     * @param finprodType
     * @param profitType
     * @return
     */
    public List<SuggestDto> getAssetId(String search, String finprodType, String profitType){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinDepositTarget/getAssetId?search="
                        + search+"&finprodType="+finprodType+"&profitType="+profitType+"&child="+ RequestUtil.getParam("child"),
                SuggestDto.class);
    }

    /**
     * 核算分类
     * @param finProductClass
     * @return
     */
    public FinProductClass getFinanceProductClass(FinProductClass finProductClass) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinDepositTarget/getFinanceProductClass", finProductClass,
                FinProductClass.class);
    }
}
