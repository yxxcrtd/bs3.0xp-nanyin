/*
 * 文件名：FinBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.asset.bond.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.asset.service.BusiFinExercisePlanService;
import com.joyintech.tams.valuation.component.asset.service.BussFinRepaymentService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 债券标的信息新建
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetService
 * @since 1.0
 */
@Service
public class FinBondTargetAssetService extends CommonExportService<FinBondTargetAssetDto>
implements BaseProcessService<FinBondTargetAssetDto>{
//    @Autowired
//    private CommonAutoCouponService commonAutoService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 共通--资产
     */
    @Autowired
    private ValAssetCommonService assetCommonService;

    /**
     * 业务组件--计息要素
     */
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;

    /**
     * 业务--付息信息
     */
    @Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;

    /**
     * 业务--还本信息
     */
    @Autowired(required = false)
    private BussFinRepaymentService bussFinRepaymentService;
    /**
     * 业务--行权计划
     */
    @Autowired(required = false)
    private BusiFinExercisePlanService busiFinExercisePlanService;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinBondTargetAssetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        pageParam.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
        //pageParam.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
        pageParam.put("cashFinprodType", DictCodeListExt1.金融产品类型_债券.getValue());//现金流业务类型
        //添加是否新增债券查询条件 add by liupeilin 2020-01-15 end
        Page<FinBondTargetAssetDto> page = restTemplate.postForPage(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/findPage",
                pageParam, FinBondTargetAssetDto.class);
        return page;
    }

    /**
     * 根据条件查询所有数据
     * @param pageParam
     * @return
     */
    public List<FinBondTargetAssetDto> selectAllList(Map<String, Object> pageParam) {
        pageParam.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        pageParam.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
        pageParam.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
        //添加是否新增债券查询条件 add by liupeilin 2020-01-15 end
        List<FinBondTargetAssetDto> list = restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/selectAllList",
                pageParam, FinBondTargetAssetDto.class);
        return list;
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(FinBondTargetAssetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        entity = encapBaseFinProductBaseInfoList(entity, type);
        String basis = StringUtil.EMPTY;
        if(entity.getFinInterestFactorDto() != null) {
            String basisStyle = entity.getFinInterestFactorDto().getBasisStyle();
            if (StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_平均分配.getValue())) {
                basis = DictCodeList.计息基础_A_A_平均分配.getValue();
            } else if (StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_按照实际天数分配.getValue())) {
                basis = DictCodeList.计息基础_A_A_按实际天数分配.getValue();
            } else {
                basis = entity.getFinInterestFactorDto().getBasis();
            }
        }
        for(BaseFinProductBaseInfoDto baseFinProductBaseInfoEntity:entity.getBaseFinProductBaseInfoList()){
            /** 增加为空的判定，防止删除时没有数据**/
            if(!StringUtil.isEmptyList(baseFinProductBaseInfoEntity.getFinRateInfoList())) {
                for (FinRateInfo finRateInfo : baseFinProductBaseInfoEntity.getFinRateInfoList()) {
                    finRateInfo.setBasis(basis);
                }
            }
            if(!StringUtil.isEmptyList(baseFinProductBaseInfoEntity.getFinCashCalcRuleList())) {
                for (FinCashCalcRule finCashCalcRule : baseFinProductBaseInfoEntity.getFinCashCalcRuleList()) {
                    finCashCalcRule.setBasis(basis);
                }
            }
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

        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/" + action, entity,
            Integer.class);

    }

    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinBondTargetAssetDto getBasicInfo(String finprodId){
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/FinBondTargetAsset/getBasicInfo?finprodId="
            + finprodId,
            FinBondTargetAssetDto.class);
    }

    /**
     * 封装基础组件-标的类金融产品需要的DTO
     * @param dto
     * @param type
     * @return
     * @see
     * @since 1.0
     */
    public FinBondTargetAssetDto encapBaseFinProductBaseInfoList(FinBondTargetAssetDto dto, OperationType type){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        //现金流信息
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        //现金流支付计划
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        //期限
        finProduct.setTermDays(DateUtil.getBetweenDayNumber(dto.getVdate(),dto.getMdate()));
        finProduct.setIssueAmt(ArithmeticUtil.multiply(dto.getIssueAmt(),new BigDecimal("10000")));
        /*if(null!=bussFinBondRepInfoService){
            if(null!=dto.getBussFinBondRepInfoDto()){
                finProduct.setRegistOrg(dto.getBussFinBondRepInfoDto().getRepRegistOrg());
            }
        }*/
        // 一体化对应etliu start
        // 债券新建画面，入库时，标的类金融产品表的含权债摊销日字段，当含权债时，登数据字典【含权债摊销日.最近行权日】
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), finProduct.getIsRight())) {
            finProduct.setExeDate(CacheUtil.getSysParam("ISSECTOEXEDATE") == null ?
                    DictCodeList.含权债摊销日_最近行权日.getValue() : CacheUtil.getSysParam("ISSECTOEXEDATE"));
        }
        // 实际到期日:先存成到期日
        finProduct.setActmdate(finProduct.getMdate());
        // 一体化对应etliu end

        //金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType8(DictCodeList.平安资产类型_债券.getValue());
        finProductType.setType1(dto.getAssetType());//资产类型
        finProductType.setType2(dto.getAccountintType());//核算分类
        finProductType.setType3(dto.getTaxType());//税务分类
        //金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
        if (OperationType.NEW.equals(type)) {
            finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());
        }
        else if(StringUtil.isNotEmpty(dto.getDataSource())){
            finProductAdd.setDataSource(dto.getDataSource());
        }
        //现金流信息Entity-本金
        FinCashInfo finCashInfoPrincipal = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_本金.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue(),branch);
        //现金流信息Entity-利息
        FinCashInfo finCashInfoInterest = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_利息.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue(),branch);
        finCashInfoList.add(finCashInfoPrincipal);
        finCashInfoList.add(finCashInfoInterest);
        //计息要素DTO 现金流利率信息  现金流计算规则
        if(null != finInterestFactorService){
            if(null != dto.getFinInterestFactorDto()){
                FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(dto.getFinInterestFactorDto());
                finInterestFactorDto = assetCommonService.encapFinInterestFactor(finProduct,finInterestFactorDto,finCashInfoInterest,null, branch);
                //息票品种
                finProduct.setCouponSpecies(finInterestFactorDto.getCouponSpecies());
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                finRateInfoList.addAll(finInterestFactorDto.getRateList());
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                finCashCalcRuleList.addAll(finInterestFactorDto.getCalcRuleList());
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);
                baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
            }
        }

        //付息信息DTO 现金流支付规则 现金流支付支付计划
        if(null != finPayInterestStdInfoService){
            if(dto.getFinPayInterestStdInfoDto() != null) {
                FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(dto.getFinPayInterestStdInfoDto());
                finPayInterestStdInfoDto = assetCommonService.encapFinPayInterestStdInfo(finProduct, finPayInterestStdInfoDto, finCashInfoPrincipal, finCashInfoInterest, branch);
                baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
                finCashPlanList.addAll(finPayInterestStdInfoDto.getFinCashPlan2List());
            }
        }
        //还本信息DTO 现金流支付支付计划
        if(null != bussFinRepaymentService){
            if(dto.getBussFinRepaymentDto() != null) {
                BussFinRepaymentDto bussFinRepaymentDto = assetCommonService.encapBussFinRepayment(finProduct, dto.getBussFinRepaymentDto(), finCashInfoPrincipal, branch);
                finCashPlanList.addAll(bussFinRepaymentDto.getFinCashPlan2List());
            }
        }
        //行权计划DTO 行权计划
        if(null != busiFinExercisePlanService){
            if(null!=dto.getBusiFinExercisePlanDto()){
                BusiFinExercisePlanDto busiFinExercisePlanDto= assetCommonService.encapBusiFinExercisePlan(finProduct, dto.getBusiFinExercisePlanDto(), finCashInfoPrincipal, branch);
                baseFinProductBaseInfoDto.setFinExercisePlanList(busiFinExercisePlanDto.getFinExercisePlanList());
            }
        }
        //转债信息
        if(StringUtil.equals(dto.getAssetType(),DictCodeList.债券类型_可转债.getValue())
                || StringUtil.equals(dto.getAssetType(),DictCodeList.债券类型_可交换债.getValue())){
            /** 增加为空的判定**/
            if(dto.getFinProductConvertible() != null) {
                dto.getFinProductConvertible().setFinprodId(dto.getFinprodId());
            }
        }
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        return dto;
    }

    /**
     * 组装现金流信息
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(FinBondTargetAssetDto dto,String cashType1,String cashType2,Long branch){
        FinCashInfo finCashInfo = new FinCashInfo();
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        //String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, cashType2);
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.金融产品类型_债券.getValue(), branch, cashType2);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        //finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        finCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        return  finCashInfo;
    }


    /**
     * 生成金融产品代码/市场代码
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinBondTargetAssetDto getFinprodId(FinBondTargetAssetDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService != null ?
                commonBusinessService.getFinProdId(finprodMarketId, DictCodeListExt1.金融产品类型_债券.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        return dto;
    }

    /**
     *  市场代码是否重复
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodMarketId(String finprodMarketId){
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/selectCountByfinprodMarketId?finprodMarketId="+finprodMarketId,
            Integer.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinBondTargetAssetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }


    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * 查询业务表资产信息
     *
     * @param finprodMarketId
     * @return 资产信息
     * @see
     * @since 1.0
     */
    public FinBondTargetAssetDto getBondDto(String finprodMarketId) {
        //债券基本信息
        FinBondTargetAssetDto bondTargetDto = getBasicInfo(finprodMarketId);
        if (bondTargetDto == null) {
            return bondTargetDto;
        }
        bondTargetDto = restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/searchData",
            bondTargetDto, FinBondTargetAssetDto.class);


        // 现金流计算规则
        // 根据主键获取业务实体--付息信息
        FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            bondTargetDto.getFinprodId(), bondTargetDto.getvdateStr(),
            DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
        bondTargetDto.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
        // 现金流支付规则
        BussFinRepaymentDto bussFinRepaymentDto = new BussFinRepaymentDto();
        finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            bondTargetDto.getFinprodId(), bondTargetDto.getvdateStr(),
            DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
        if (null != finPayInterestStdInfoDto) {
            bussFinRepaymentDto.setPayCycle(finPayInterestStdInfoDto.getPayCycle());
            bussFinRepaymentDto.setRemark(finPayInterestStdInfoDto.getRemark());
            bondTargetDto.setBussFinRepaymentDto(bussFinRepaymentDto);
        }
        // 行权计划
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", bondTargetDto.getFinprodId());
        param.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
        param.put("execType01", DictCodeList.行权状态_未行权.getValue());
        param.put("execType02", DictCodeList.行权状态_已行权.getValue());
        List<FinExercisePlan> finExercisePlanList = busiFinExercisePlanService.getfinExercisePlanByProductId(
            param);
        BusiFinExercisePlanDto busiFinExercisePlanDto = new BusiFinExercisePlanDto();
        busiFinExercisePlanDto.setFinExercisePlanList(finExercisePlanList);
        bondTargetDto.setBusiFinExercisePlanDto(busiFinExercisePlanDto);
        if (bondTargetDto != null && bondTargetDto.getFinInterestFactorDto() != null) {
            FinInterestFactorDto finInterestFactorDto = bondTargetDto.getFinInterestFactorDto();
            if (StringUtil.equals(finInterestFactorDto.getBasis(),
                DictCodeList.计息基础_A_A_平均分配.getValue())) {
                finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_平均分配.getValue());
                finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
            }
            else if (StringUtil.equals(finInterestFactorDto.getBasis(),
                DictCodeList.计息基础_A_A_按实际天数分配.getValue())) {
                finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
                finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
            }
        }
        return bondTargetDto;
    }
    // 债券新建新增快捷录入功能 add by renping 20191010 end

    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                                       + "/FinBondTargetAsset/getfinprodIdForAdd"
                                       , param,
            SelectOptionDto.class);

	}

	public int isHqTrade(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/isHqTrade?finprodId=" + finprodId,
	            Integer.class);
	}
    /**
     * 校验金融产品代码不重复
     * @param finprodId
     * @return
     */
    public int selectCountByfinprodId(String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/selectCountByfinprodId?finprodId="+finprodId,
                Integer.class);
    }

    /**
     * 校验上市流通代码是否重复
     * @param finprodId
     * @return
     */
    public int selectCountByCirculationCode(String circulationCode, String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinBondTargetAsset/selectCountByCirculationCode?circulationCode="
                        +circulationCode+"&finprodId=" + finprodId,
                Integer.class);
    }
}
