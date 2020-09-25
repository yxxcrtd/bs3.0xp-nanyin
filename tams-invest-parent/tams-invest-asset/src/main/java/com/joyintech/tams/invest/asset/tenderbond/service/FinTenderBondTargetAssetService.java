/*
 * 文件名：FinTenderBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.tenderbond.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.component.business.asset.BussFinRepaymentService;
import com.joyintech.tams.component.business.asset.FinInterestFactorService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.bond.BusiFinBondRatingInfoService;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.bond.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.component.business.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.core.service.MstFileCommonService;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.tenderbond.dto.FinTenderBondTargetAssetDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 一级债券标的信息新建
 * @author wangpeng
 * @version 1.0 2019年7月4日
 * @see FinTenderBondTargetAssetService
 * @since 1.0
 */
@Service
public class FinTenderBondTargetAssetService extends CommonExportService<FinTenderBondTargetAssetDto>
implements BaseProcessService<FinTenderBondTargetAssetDto>{
    @Autowired
    private CommonAutoCouponService commonAutoService;


    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 共通--资产
     */
    @Autowired
    private AssetCommonService assetCommonService;

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
     * 业务--评级信息
     */
    @Autowired(required = false)
    private BusiFinBondRatingInfoService busiFinBondRatingInfoService;
    /**
     * 业务--中债报送
     */
    @Autowired(required = false)
    private BussFinBondRepInfoService bussFinBondRepInfoService;
    /**
     * 附件
     */
    @Autowired(required = false)
    private MstFileCommonService fileCommonService;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinTenderBondTargetAssetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        pageParam.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
        pageParam.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
        if (StringUtil.isNotEmpty(pageParam.get("type4"))) {
            pageParam.put("type4", StringUtil.splitToList(null, pageParam.get("type4").toString()));
        }else{
            pageParam.remove("type4");
        }
        // 新增资产二、三、四级分类 王贵奇 2019/7/31 开始
        if (StringUtil.isNotEmpty(pageParam.get("type9"))) {
            pageParam.put("type9", StringUtil.splitToList(null, pageParam.get("type9").toString()));
        }else{
            pageParam.remove("type9");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type10"))) {
            pageParam.put("type10", StringUtil.splitToList(null, pageParam.get("type10").toString()));
        }else{
            pageParam.remove("type10");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type11"))) {
            pageParam.put("type11", StringUtil.splitToList(null, pageParam.get("type11").toString()));
        }else{
            pageParam.remove("type11");
        }
        // 新增资产二、三、四级分类 王贵奇 2019/7/31 结束
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinTenderBondTargetAsset/findPage",
            pageParam, FinTenderBondTargetAssetDto.class);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(FinTenderBondTargetAssetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 当一级新债日历新建与一级标的新建调用不同方法 by 赵东东  20191116 start
        if(StringUtil.isNotEmpty(entity.getId())) {
        	entity = enFinProduct(entity);
        }
        else{entity = encapBaseFinProductBaseInfoList(entity);}
        // 当一级新债日历新建与一级标的新建调用不同方法 by 赵东东  20191116 start
        /*String basis = StringUtil.EMPTY;
        String basisStyle = entity.getFinInterestFactorDto().getBasisStyle();
        if(StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_平均分配.getValue())){
            basis = DictCodeList.计息基础_A_A_平均分配.getValue();
        }else if(StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_按照实际天数分配.getValue())){
            basis = DictCodeList.计息基础_A_A_按实际天数分配.getValue();
        }else{
            basis = entity.getFinInterestFactorDto().getBasis();
        }
        for(BaseFinProductBaseInfoDto baseFinProductBaseInfoEntity:entity.getBaseFinProductBaseInfoList()){
            for(FinRateInfo finRateInfo:baseFinProductBaseInfoEntity.getFinRateInfoList()){
                finRateInfo.setBasis(basis);
            }
            for(FinCashCalcRule finCashCalcRule:baseFinProductBaseInfoEntity.getFinCashCalcRuleList()){
                finCashCalcRule.setBasis(basis);
            }
        }*/
        // 资产负债报送信息的市场代码和金融产品名称设值
        /*if (entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList() != null && entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList().size() > 0) {
        	entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList().get(0).setFinprodMarketId(entity.getFinprodMarketId());
        	entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList().get(0).setRepFinprodName(entity.getFinprodName());
        }*/
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
       /* if (!StringUtil.isEmptyList(entity.getBaseFinProductBaseInfoList())) {
        	for (BaseFinProductBaseInfoDto productBaseInfoDto : entity.getBaseFinProductBaseInfoList()) {
				if (!StringUtil.isEmptyList(productBaseInfoDto.getFinCashPayRuleList())) {
					for (FinCashPayRule finCashPayRule : productBaseInfoDto.getFinCashPayRuleList()) {
						if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
							finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
						}
					}
				}
			}
        }*/
        // 判断Id是否空，来确定走哪个数据入库方法
        // 一级市场新债日历创建标的走的入库方法 by 赵东东 20191116
      if(StringUtil.isNotEmpty(entity.getId())) {
    	  restTemplate.postForObject(
    			  ServiceApplication.ASSET_SERVICE_NAME + "/FinTenderBondTargetAsset/" + "getInsert", entity,
    	            Integer.class);
      }
      //一级市场标的新建标的走的入库方法 by 赵东东 20191116
      else{ restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinTenderBondTargetAsset/" + action, entity,
            Integer.class);}
        

    }

    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinTenderBondTargetAssetDto getBasicInfo(String finprodId){
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/FinTenderBondTargetAsset/getBasicInfo?finprodId="
            + finprodId,
            FinTenderBondTargetAssetDto.class);
    }

    /**
     * 封装基础组件-标的类金融产品需要的DTO
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinTenderBondTargetAssetDto encapBaseFinProductBaseInfoList(FinTenderBondTargetAssetDto dto){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        //现金流信息
       /* List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        //现金流支付计划
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();*/
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
            finProduct.setExeDate(DictCodeList.含权债摊销日_最近行权日.getValue());
        }
        // 实际到期日:先存成到期日
        finProduct.setActmdate(finProduct.getMdate());
        // 一体化对应etliu end

        //金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType6(dto.getType4());
        finProductType.setType7(dto.getType5());
        finProductType.setType8(DictCodeListExt2.平安资产类型_一级市场债.getValue());
        //金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        //现金流信息Entity-本金
//        FinCashInfo finCashInfoPrincipal = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_本金.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue(),branch);
        //现金流信息Entity-利息
//        FinCashInfo finCashInfoInterest = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_利息.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue(),branch);
//        finCashInfoList.add(finCashInfoPrincipal);finCashInfoList.add(finCashInfoInterest);
        //计息要素DTO 现金流利率信息  现金流计算规则
       /* if(null!=finInterestFactorService){
            if(null!=dto.getFinInterestFactorDto()){
                FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(dto.getFinInterestFactorDto());
                finInterestFactorDto = assetCommonService.encapFinInterestFactor(finProduct,finInterestFactorDto,finCashInfoInterest,null, branch);
                //息票品种
                finProduct.setCouponSpecies(finInterestFactorDto.getCouponSpecies());
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                finRateInfoList.addAll(finInterestFactorDto.getRateList());
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                finCashCalcRuleList.addAll(finInterestFactorDto.getCalcRuleList());
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
            }
        }*/

        //付息信息DTO 现金流支付规则 现金流支付支付计划
       /* if(null!=finPayInterestStdInfoService){
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(dto.getFinPayInterestStdInfoDto());
            finPayInterestStdInfoDto = assetCommonService.encapFinPayInterestStdInfo(finProduct,finPayInterestStdInfoDto,finCashInfoPrincipal, finCashInfoInterest, branch);
            baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
            finCashPlanList.addAll(finPayInterestStdInfoDto.getFinCashPlan2List());
        }*/
        //还本信息DTO 现金流支付支付计划
        /*if(null!=bussFinRepaymentService){
            BussFinRepaymentDto bussFinRepaymentDto = assetCommonService.encapBussFinRepayment(finProduct, dto.getBussFinRepaymentDto(), finCashInfoPrincipal, branch);
            finCashPlanList.addAll(bussFinRepaymentDto.getFinCashPlan2List());
        }*/
        //行权计划DTO 行权计划
        /*if(null!=busiFinExercisePlanService){
            if(null!=dto.getBusiFinExercisePlanDto()){
                BusiFinExercisePlanDto busiFinExercisePlanDto= assetCommonService.encapBusiFinExercisePlan(finProduct, dto.getBusiFinExercisePlanDto(), finCashInfoPrincipal, branch);
                baseFinProductBaseInfoDto.setFinExercisePlanList(busiFinExercisePlanDto.getFinExercisePlanList());
            }
        }*/
        //评级信息
       /* if(null!=busiFinBondRatingInfoService){
            if(null!=dto.getBusiFinBondRatingInfoDto()){
                BusiFinBondRatingInfoDto busiFinBondRatingInfoDto= assetCommonService.encapBusiFinBondRatingInfo(finProduct, dto.getBusiFinBondRatingInfoDto());
                dto.setBusiFinBondRatingInfoDto(busiFinBondRatingInfoDto);
            }
        }*/
        /*//中债报送
        if(null!=bussFinBondRepInfoService){
            if(null!=dto.getBussFinBondRepInfoDto()){
                //债券资产报送
                BaseRepBondAssetInfoDto baseRepBondAssetInfoDto = assetCommonService.encapBaseRepBondAssetInfoDto(finProduct, dto.getBussFinBondRepInfoDto());
                //负债
                BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,dto.getBussFinBondRepInfoDto().getRepAssetType(),dto.getBussFinBondRepInfoDto().getRepTradeMarket());
                dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                dto.setBaseRepBondAssetInfoDto(baseRepBondAssetInfoDto);
            }
        }*/
      //附件信息
        if(null!=fileCommonService){
            if(null!=dto.getFileCommonList()){
                FileCommonDto fileCommonDto = assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList());
                dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter());
            }
        }
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
//        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
//        baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        return dto;
    }
    // 一级新债日历 封装基础组件 by 赵东东 20191116 start
    /**
     * 一级新债日历封装基础组件-标的类金融产品需要的DTO
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinTenderBondTargetAssetDto enFinProduct(FinTenderBondTargetAssetDto dto){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
       
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        //期限
        if(StringUtil.isNotEmpty(dto.getVdate())) {
        finProduct.setTermDays(DateUtil.getBetweenDayNumber(dto.getVdate(),dto.getMdate()));
        }
        finProduct.setIssueAmt(ArithmeticUtil.multiply(dto.getIssueAmt(),new BigDecimal("10000")));
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), finProduct.getIsRight())) {
            finProduct.setExeDate(DictCodeList.含权债摊销日_最近行权日.getValue());
        }
        // 实际到期日:先存成到期日
        finProduct.setActmdate(finProduct.getMdate());
        // 一体化对应etliu end

        //金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType6(dto.getType4());
        finProductType.setType7(dto.getType5());
        finProductType.setType8(DictCodeListExt2.平安资产类型_一级市场债.getValue());
        //金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
      //附件信息
        if(null!=fileCommonService){
            if(null!=dto.getFileCommonList()){
                FileCommonDto fileCommonDto = assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList());
                dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter());
            }
        }
        dto.setFinProduct(finProduct);
        dto.setFinProductAdd(finProductAdd);
        dto.setFinProductType(finProductType);
        return dto;
    }
 // 一级新债日历 封装基础组件 by 赵东东 20191116 end
    /**
     * 组装现金流信息
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(FinTenderBondTargetAssetDto dto,String cashType1,String cashType2,Long branch){
        FinCashInfo finCashInfo = new FinCashInfo();
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, cashType2);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
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
    public FinTenderBondTargetAssetDto getFinprodId(FinTenderBondTargetAssetDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService!=null?commonBusinessService.getFinProdId(finprodMarketId, BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue(), dto.getChlFinprodId()):IDGenerator.generate();
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
            ServiceApplication.ASSET_SERVICE_NAME + "/FinTenderBondTargetAsset/selectCountByfinprodMarketId?finprodMarketId="+finprodMarketId,
            Integer.class);
    }

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.getSelectParam().put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
		String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/invest/asset/tenderbond/conf/export_conf.xml")
					.dataSource(DataSourceType.MASERT);
		} else {
			exportParam.config("/com/joyintech/tams/invest/asset/tenderbond/conf/export_conf002.xml")
					.dataSource(DataSourceType.MASERT);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<FinTenderBondTargetAssetDto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

    /**
     * 查询接口表资产信息
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public FinTenderBondTargetAssetDto getInterfBondDto(String finprodMarketId) {
        InterfPayhsrcsecinfoDto dto = new InterfPayhsrcsecinfoDto();
        List<String> list = new ArrayList<>();
        list.add(finprodMarketId);
        dto.setSecidList(list);
        dto = commonAutoService.autoBondCoupon(dto, finprodMarketId);
        com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto baseInfoDto =
            new com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto();
        if (StringUtil.isNotEmpty(dto)) {
            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
        }else {
            return null;
        }
        //债券基本信息
        FinTenderBondTargetAssetDto bondTargetDto = new FinTenderBondTargetAssetDto();
        //金融产品基本信息表
        FinProduct finProduct =  baseInfoDto.getFinProduct();
        if (StringUtil.isNotEmpty(finProduct)) {
            BeanUtil.copyPropertiesIgnoreNull(bondTargetDto,finProduct);
        }
        //类型
        if (StringUtil.isNotEmpty(baseInfoDto.getFinProductType())) {
            bondTargetDto.setType4(baseInfoDto.getFinProductType().getType4());
            bondTargetDto.setType5(baseInfoDto.getFinProductType().getType5());
        }
        //附属表
        if (StringUtil.isNotEmpty(baseInfoDto.getFinProductAdd())) {
            bondTargetDto.setIsAutoUpdate(baseInfoDto.getFinProductAdd().getIsAutoUpdate());
        }
        //
        BaseFinProductBaseInfoDto baseInfoDto2 = new BaseFinProductBaseInfoDto();
        BeanUtil.copyPropertiesIgnoreNull(baseInfoDto2, baseInfoDto);
        List<BaseFinProductBaseInfoDto> baseList = new ArrayList<>();
        baseList.add(baseInfoDto2);
        bondTargetDto.setBaseFinProductBaseInfoList(baseList);
        //现金流利率信息表
        List<FinRateInfo> finRateInfos = baseInfoDto2.getFinRateInfoList();
        //计息要素业务
        if (StringUtil.isNotEmpty(finRateInfos)) {
            FinInterestFactorDto finInterestFactorDto = new FinInterestFactorDto();
            finInterestFactorDto.setCouponSpecies(finProduct.getCouponSpecies());
            BeanUtil.copyPropertiesIgnoreNull(finInterestFactorDto, finRateInfos.get(0));
            bondTargetDto.setFinInterestFactorDto(finInterestFactorDto);
        }
        //现金流计算规则
        List<FinCashCalcRule>  finCashCalcRuleList = baseInfoDto2.getFinCashCalcRuleList();


        if (StringUtil.isNotEmpty(finCashCalcRuleList)) {
          //付息信息业务
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = new FinPayInterestStdInfoDto();
          //还本信息业务
            BussFinRepaymentDto bussFinRepaymentDto = new BussFinRepaymentDto();
            finPayInterestStdInfoDto.setCouponSpecies(finProduct.getCouponSpecies());
          //现金流计划表
            List<FinCashPlan> finCashPlanList = baseInfoDto2.getFinCashPlanList();
            if (StringUtil.isNotEmpty(finCashPlanList)) {
                List<FinCashPlanDto> finCashPlanDtos = new ArrayList<>();
                for (FinCashPlan finCashPlan : finCashPlanList) {
                    FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                    BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto,finCashPlan);
                    finCashPlanDtos.add(finCashPlanDto);
                }
                finPayInterestStdInfoDto.setFinCashPlanList(finCashPlanDtos);
                bussFinRepaymentDto.setFinCashPlanList(finCashPlanDtos);
            }

            bondTargetDto.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
            bondTargetDto.setBussFinRepaymentDto(bussFinRepaymentDto);
        }

        // 行权计划
        BusiFinExercisePlanDto busiFinExercisePlanDto = new BusiFinExercisePlanDto();
        busiFinExercisePlanDto.setFinExercisePlanList(baseInfoDto2.getFinExercisePlanList());
        // 评级信息
//        BusiFinBondRatingInfoDto busiFinBondRatingInfoDto = new BusiFinBondRatingInfoDto()
        // 中债报送
//        BussFinBondRepInfoDto bussFinBondRepInfoDto = new BussFinBondRepInfoDto()
        return bondTargetDto;
    }

}
