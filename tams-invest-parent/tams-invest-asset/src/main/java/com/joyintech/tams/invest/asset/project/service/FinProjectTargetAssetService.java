/*
 * 文件名：FinProjectTargetAssetService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian
 * 修改时间：2018年10月24日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.project.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.asset.BussFinRepaymentService;
import com.joyintech.tams.component.business.asset.FinInterestFactorService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.project.*;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.component.business.report.asset.fund.BussRepFundInfoService;
import com.joyintech.tams.component.business.report.asset.plan.BussRepAssetMngProdInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetAstBillInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetCreditNonstdInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepEquityInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepOtherInvestTargetInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.common.dto.*;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.interfaceDto.*;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetCmsDto;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.util.MapParseUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目类资产 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectTargetAssetService
 * @since 1.0
 */
@Service
public class FinProjectTargetAssetService extends CommonExportService<FinProjectTargetAssetDto> implements BaseProcessService<FinProjectTargetAssetDto> {

    /*
     * @Autowired private FileAttachmentProcess fileAttachmentProcess;
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 业务编号
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private AssetCommonService assetCommonService;

    /**
     * 计息要素
     */
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;

    /**
     * 付息信息
     */
    @Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;

    /**
     * 还本信息
     */
    @Autowired(required = false)
    private BussFinRepaymentService bussFinRepaymentService;

    /**
     * 投后信息
     */
    @Autowired(required = false)
    private BussPostInvestInfoService bussPostInvestInfoService;

    /**
     * 减持信息
     */
    @Autowired(required = false)
    private BussReduceShareInfoService bussReduceShareInfoService;

    /**
     * 认购信息
     */
    @Autowired(required = false)
    private BussSubscriptionInfoService bussSubscriptionInfoService;

    @Autowired(required = false)
    private BussParticipantInfoService bussParticipantInfoService;

    /**
     * 非标资产(票据或信用)报送信息
     */
    @Autowired(required = false)
    private BussRepAssetAstBillInfoService bussRepAssetAstBillInfoService;

    /**
     * 信贷及非标资产报送
     */
    @Autowired(required = false)
    private BussRepAssetCreditNonstdInfoService bussRepAssetCreditNonstdInfoService;

    /**
     * 权益类--中债报送
     */
    @Autowired(required = false)
    private BussRepEquityInfoService bussRepEquityInfoService;

    /**
     * 其他资产报送信息
     */
    @Autowired(required = false)
    private BussRepOtherInvestTargetInfoService bussRepOtherInvestTargetInfoService;

    /**
     * 基金
     */
    @Autowired(required = false)
    private BussRepFundInfoService bussRepFundInfoService;

    /**
     * 债券
     */
    @Autowired(required = false)
    private BussFinBondRepInfoService bussFinBondRepInfoService;

    /**
     * 计划类
     */
    @Autowired(required = false)
    private BussRepAssetMngProdInfoService bussRepAssetMngProdInfoService;

    /**
     * 增信信息
     * */
    @Autowired(required = false)
    private BussFinProductCreditService bussFinProductCreditService;
    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinProjectTargetAssetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        pageParam.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        pageParam.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());// 现金流业务类型
        if (StringUtil.isNotEmpty(pageParam.get("type6"))) {
            pageParam.put("type6", StringUtil.splitToList(null, pageParam.get("type6").toString()));
        }
        // 项目类资产五级分类改造 add by renping 2019-07-25 start
        if (StringUtil.isNotEmpty(pageParam.get("type9"))) {
            pageParam.put("type9", StringUtil.splitToList(null, pageParam.get("type9").toString()));
        }
        if (StringUtil.isNotEmpty(pageParam.get("type10"))) {
            pageParam.put("type10", StringUtil.splitToList(null, pageParam.get("type10").toString()));
        }
        if (StringUtil.isNotEmpty(pageParam.get("type11"))) {
            pageParam.put("type11", StringUtil.splitToList(null, pageParam.get("type11").toString()));
        }
        if (StringUtil.isNotEmpty(pageParam.get("type12"))) {
            pageParam.put("type12", StringUtil.splitToList(null, pageParam.get("type12").toString()));
        }
        // 项目类资产五级分类改造 add by renping 2019-07-25 end
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/findPage", pageParam,
            FinProjectTargetAssetDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProjectTargetAssetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 封装标的类金融产品基础组件需要的DTO
        entity = encapBaseFinProductBaseInfoList(entity);
        //add by wangpeng start at 2019-07-15 :生成委外组合
        PtlPortfolio ptlPortfolio = new PtlPortfolio();
        ptlPortfolio.setPortfolioId(entity.getFinprodMarketId());
        ptlPortfolio.setPortfolioType(DictCodeListExt1.组合类型_委外组合.getValue());
        ptlPortfolio.setPortfolioName(entity.getFinprodName());
        ptlPortfolio.setVdate(entity.getVdate());
        ptlPortfolio.setMdate(entity.getMdate());
        ptlPortfolio.setProfitType(entity.getProfitType());
        entity.setPtlPortfolio(ptlPortfolio);
        //add end
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
        	//新建项目类资产时，自动创建交易对手 add by hxy 20200421 start
    		assetCommonService.autoCreateTradeCounter(entity.getFinancier());
    		//新建项目类资产时，自动创建交易对手 add by hxy 20200421 end
            // 针对资产 入库前,金融产品id需要重新生成
            /*
             * String oldFinprodId = entity.getFinprodId(); String oldfinprodMarketId = entity.getFinprodMarketId(); if(null!=entity){ entity =
             * getFinprodId(entity); String jsonStr = JsonUtils.object2Json(entity); jsonStr = StringUtil.replaceAll(jsonStr, oldFinprodId,
             * entity.getFinprodId()); jsonStr = StringUtil.replaceAll(jsonStr, oldfinprodMarketId, entity.getFinprodMarketId()); try { entity =
             * (FinProjectTargetAssetDto)JsonUtils.OBJECT_MAPPER.readValue(jsonStr, FinProjectTargetAssetDto.class); } catch (Exception e) { e.printStackTrace();
             * } } //文件信息 if (CollectionUtils.isNotEmpty(entity.getFileArray()) && StringUtil.isNotEmpty(entity.getFinprodId())) { // 将附件和当前业务对象绑定（更新附件的业务代码）
             * fileAttachmentProcess.updateBussinessNo( entity.getFileArray().stream().map(UploadFile::getFileNo).collect( Collectors.toList()),
             * entity.getFinprodId()); }
             */
            action = "insert";
        }
        else {
            action = "update";
        }
        
     // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
            entity.setCreateInst(createInst);
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(entity.getFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_项目类资产信息.getValue());
            //modify by wj 20200624 增加组合权限
            insertDataAuthTypeInst(entity.getFinprodMarketId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_组合管理.getValue());

        }
        
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/" + action, entity,
            Integer.class);

        // 推送数据到第三方接口

        //1.项目要素信息
        Map<String, Object> invmap = JsonUtils.object2Map(entity);
        invmap.put("pageId","FinProjectTargetAsset002S012");
        investInterfCommService.doInvestInterf(invmap);

        //2.利率信息
        invmap.put("pageId","FinProjectTargetAsset002S013");
        investInterfCommService.doInvestInterf(invmap);
        //3.现金流计划

        invmap.put("pageId","FinProjectTargetAsset002S014");
        investInterfCommService.doInvestInterf(invmap);

    }

    /**
     * 封装基础组件-标的类金融产品需要的DTO
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectTargetAssetDto encapBaseFinProductBaseInfoList(FinProjectTargetAssetDto dto) {
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
            CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        // 现金流信息
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        // 现金流计算规则
        List<FinCashCalcRule> finCashCalcRuleAllList = new ArrayList<FinCashCalcRule>();
        // 现金流支付规则
        List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
        // 现金流支付计划
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        // 标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        // 金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType8(DictCodeList.平安资产类型_项目类资产.getValue());
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        // 金融产品扩展entity
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, dto);
        // paFinProduct.setCashOutNo(dto.getCashOutNo());
        paFinProduct.setActualCreditProvid(dto.getActualCreditProvid());
        // 增加推荐方与推荐费率 add by renping 20191014 start
        if (dto.getBussFinRecommentDto() != null && !StringUtil.isEmptyList(
            dto.getBussFinRecommentDto().getFinProductRecommentList())) {
            dto.getBussFinRecommentDto().getFinProductRecommentList().forEach(
                finProductRecomment -> {
                    finProductRecomment.setFinprodId(dto.getFinprodId());
                });
            baseFinProductBaseInfoDto.setFinProductRecommentList(
                dto.getBussFinRecommentDto().getFinProductRecommentList());
        }
        // 增加推荐方与推荐费率 add by renping 20191014 end
        // 金融产品买断利率entiity
        PaFinProductBuyoutRate paFinProductBuyoutRate = new PaFinProductBuyoutRate();
        BeanUtil.copyPropertiesIgnoreNull(paFinProductBuyoutRate, dto);
        // 买断利率
        paFinProductBuyoutRate.setBuyoutRate(ArithmeticUtil.divide(dto.getBuyOutRate(), new BigDecimal(100), 14));
        paFinProductBuyoutRate.setEffDate(dto.getVdate());
        // 现金流信息Entity-本金
        FinCashInfo finCashInfoPrincipal = encapFinCashInfo(dto, DictCodeListExt1.现金流一级类型_本金.getValue(),
            DictCodeListExt1.现金流二级类型_本金.getValue(), branch);
        // 现金流信息Entity-利息
        FinCashInfo finCashInfoInterest = encapFinCashInfo(dto, DictCodeListExt1.现金流一级类型_利息.getValue(),
            DictCodeListExt1.现金流二级类型_利息.getValue(), branch);
        finCashInfoList.add(finCashInfoPrincipal);
        finCashInfoList.add(finCashInfoInterest);
        // 计息要素DTO 现金流利率信息 现金流计算规则
        if (null != finInterestFactorService) {
            if (null != dto.getFinInterestFactorDto()) {
                FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(
                    dto.getFinInterestFactorDto());
                finInterestFactorDto.setBuyOutRate(dto.getBuyOutRate());
                finInterestFactorDto = assetCommonService.encapFinInterestFactor(finProduct, finInterestFactorDto,
                    finCashInfoInterest, null, branch);
                // 息票品种
                finProduct.setCouponSpecies(finInterestFactorDto.getCouponSpecies());
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                finRateInfoList.addAll(finInterestFactorDto.getRateList());
                finCashCalcRuleAllList.addAll(finInterestFactorDto.getCalcRuleList());
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);
            }
        }

        // 付息信息DTO 现金流支付规则 现金流支付支付计划
        FinPayInterestStdInfoDto finPayInterestStdInfoDto = new FinPayInterestStdInfoDto();
        if (null != finPayInterestStdInfoService) {
            finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(
                dto.getFinPayInterestStdInfoDto());
            finPayInterestStdInfoDto = assetCommonService.encapFinPayInterestStdInfo(finProduct, finPayInterestStdInfoDto,
                finCashInfoPrincipal, finCashInfoInterest, branch);
            finCashPayRuleList.addAll(finPayInterestStdInfoDto.getFinCashPayRuleList());
            // finCashPlanList.addAll(finPayInterestStdInfoDto.getFinCashPlan2List());
        }
        // 还本信息DTO 现金流支付支付计划
        BussFinRepaymentDto bussFinRepaymentDto = new BussFinRepaymentDto();
        if (null != bussFinRepaymentService) {
            bussFinRepaymentDto = assetCommonService.encapBussFinRepayment(finProduct, dto.getBussFinRepaymentDto(),
                finCashInfoPrincipal, branch);
            finCashPayRuleList.add(bussFinRepaymentDto.getFinCashPayRule());
            // finCashPlanList.addAll(bussFinRepaymentDto.getFinCashPlan2List());
        }
        // 重新计算现金流 add by 2019/02/27 by张年
        if (null != finPayInterestStdInfoService && null != bussFinRepaymentService) {
            finCashPlanList.addAll(assetCommonService.calCashBaseamt(finProduct, finPayInterestStdInfoDto.getFinCashPlan2List(),
                bussFinRepaymentDto.getFinCashPlan2List()));
        }
        // 投后信息
        if (null != bussPostInvestInfoService) {
            if (null != dto.getBussPostInvestInfoDto()) {
                dto.getBussPostInvestInfoDto().setFinprodId(dto.getFinprodId());
            }
        }
        // 减持信息
        if (null != bussReduceShareInfoService) {
            if (null != dto.getBussReduceShareInfoDto()) {
                for (FinReduceShareInfo finReduceShareInfo : dto.getBussReduceShareInfoDto().getFinReduceShareInfoList()) {
                    BigDecimal reducePer = ArithmeticUtil.divide(finReduceShareInfo.getReducePer(), new BigDecimal(100), 14);
                    finReduceShareInfo.setReducePer(reducePer);
                    finReduceShareInfo.setFinprodId(dto.getFinprodId());
                }
            }
        }
        // 认购信息
        if (null != bussSubscriptionInfoService) {
            if (null != dto.getBussSubscriptionInfoDto()) {
                for (FinSubscriptionInfo finSubscriptionInfo : dto.getBussSubscriptionInfoDto().getFinSubscriptionInfoList()) {
                    BigDecimal subscribRate = ArithmeticUtil.divide(finSubscriptionInfo.getSubscribRate(), new BigDecimal(100),
                        14);
                    finSubscriptionInfo.setSubscribRate(subscribRate);
                    finSubscriptionInfo.setFinprodId(dto.getFinprodId());
                }
            }
        }
        // 参与方信息
        if (null != bussParticipantInfoService) {
            if (null != dto.getBussParticipantInfoDto()) {
                BussParticipantInfoDto bussParticipantInfoDto = assetCommonService.enBussParticipantInfoDto(finProduct,
                    dto.getBussParticipantInfoDto(), branch);
                finCashCalcRuleAllList.addAll(bussParticipantInfoDto.getFinCashCalcRuleList());
                finCashInfoList.addAll(bussParticipantInfoDto.getFinCashInfoList());
            }
        }
        //增信信息
        if(null!=bussFinBondRepInfoService){
            if(null!=dto.getBussFinProductCreditDto()){
                BussFinProductCreditDto bussFinProductCreditDto= assetCommonService.encapBussFinProductCredit(finProduct, dto.getBussFinProductCreditDto(),branch);
                dto.setBussFinProductCreditDto(bussFinProductCreditDto);
            }
        }
        // 中债报送
        // 非标资产(票据或信用)报送信息
        if (null != bussRepAssetAstBillInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_票据类.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信用证.getValue())) {
                    if (null != dto.getBussRepAssetAstBillInfoDto()) {
                        BaseRepAssetAstBillInfoDto baseRepAssetAstBillInfoDto = assetCommonService.encapBussRepAssetAstBillInfoDto(
                                finProduct, dto.getBussRepAssetAstBillInfoDto());
                        RepAssetOther repAssetOther = new RepAssetOther();
                        BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussRepAssetAstBillInfoDto());
                        BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct,repAssetOther);
                        // 负债
                        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                                dto.getBussRepAssetAstBillInfoDto().getRepAssetType(),
                                dto.getBussRepAssetAstBillInfoDto().getRepTradeMarket());
                        dto.setBaseRepAssetAstBillInfoDto(baseRepAssetAstBillInfoDto);
                        dto.setBaseRepOtherDto(baseRepOtherDto);
                        dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                    }
            }
            else {
                dto.setBaseRepAssetAstBillInfoDto(null);
            }
        }
        // 信贷及非标资产报送
        if (null != bussRepAssetCreditNonstdInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信托贷款.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_委托贷款.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产转让.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_收_受益权.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_委托债权.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_应收账款.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_带回购条款的股权性融资.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_债权融资类产品.getValue())
            //中债新增 2202小微企业信贷资产流转和收益权转让 20200410 
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_小微企业信贷资产流转和收益权转让.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产流转项目.getValue())) {
                    if (null != dto.getBussRepAssetCreditNonstdInfoDto()) {
                        BussRepAssetCreditNonstdInfoDto creditNonstdInfoDto = dto.getBussRepAssetCreditNonstdInfoDto();
                        FinInterestFactorDto factorDto = dto.getFinInterestFactorDto();
                        //计息基础
                        if (StringUtil.equals(factorDto.getBasis(), DictCodeList.计息基础_A_360.getValue())){
                            creditNonstdInfoDto.setRepBasis(BussinessDictCodeList.中债_计息基础_ACT360.getValue());
                        }else if (StringUtil.equals(factorDto.getBasis(), DictCodeList.计息基础_A_365.getValue())){
                            creditNonstdInfoDto.setRepBasis(BussinessDictCodeList.中债_计息基础_ACT365.getValue());
                        }
                            
                        
                        //项目收益率
                        creditNonstdInfoDto.setRepProjYield(factorDto.getLoadRate());
                        //基准利率种类
                        creditNonstdInfoDto.setRepBasisType(factorDto.getIntType());
                        //浮动因子  （系数-1）*100 如果是固定利率不存值
                        if (StringUtil.equals(factorDto.getIntType(), DictCodeList.利率类型_浮动利率.getValue())){
                            creditNonstdInfoDto.setRepFloatFactor(ArithmeticUtil.multiply(
                                    ArithmeticUtil.subtract(factorDto.getCoefficient(), new BigDecimal(1)),new BigDecimal(100)));
                        }                       
                        //利差
                        creditNonstdInfoDto.setRepSpreadRate(factorDto.getSpreadRate());
                        //融资总费率= 托管费率+管理费率+其他费率 bussParticipantInfoDto.bussParticipantInfoDtoList
                        if (StringUtil.isNotEmpty(dto.getBussParticipantInfoDto())){
                            List<BussParticipantInfoDto> participantInfoDtos =  dto.getBussParticipantInfoDto().getBussParticipantInfoDtoList();
                            BigDecimal feeYield = new BigDecimal(0);
                            for (BussParticipantInfoDto b: participantInfoDtos) {                               
                                feeYield =ArithmeticUtil.doAdd( ArithmeticUtil.doAdd( ArithmeticUtil.doAdd(b.getClientYield(),
                                        b.getManagerYield()),b.getOtherYield()), feeYield);
                                
                            }
                            creditNonstdInfoDto.setRepTotalFeeRate(feeYield);
                        }
                        
                        //creditNonstdInfoDto.setRepTotalFeeRate(finProduct.getre);
                        BaseRepAssetCreditNonstdInfoDto baseRepAssetCreditNonstdInfoDto = assetCommonService.encapBaseRepAssetCreditNonstdInfoDto(
                            finProduct, creditNonstdInfoDto);
                        RepAssetOther repAssetOther = new RepAssetOther();
                        BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussRepAssetCreditNonstdInfoDto());
                        BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct, repAssetOther);
                        // 负债
                        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(
                            finProduct, dto.getBussRepAssetCreditNonstdInfoDto().getRepAssetType(),
                            dto.getBussRepAssetCreditNonstdInfoDto().getRepTradeMarket());
                        dto.setBaseRepAssetCreditNonstdInfoDto(baseRepAssetCreditNonstdInfoDto);
                        dto.setBaseRepOtherDto(baseRepOtherDto);
                        dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                    }
            }
            else {
                dto.setBaseRepAssetCreditNonstdInfoDto(null);
            }
        }
        // 权益类资产中债报送
        if (null != bussRepEquityInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_股权.getValue())) {
                if (null != dto.getBussRepEquityInfoDto()) {
                    BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto = assetCommonService.encapBaseRepAssetEquityInfoDto(
                        finProduct, dto.getBussRepEquityInfoDto());
                    RepAssetOther repAssetOther = new RepAssetOther();
                    BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussRepEquityInfoDto());
                    BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct, repAssetOther);
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        dto.getBussRepEquityInfoDto().getRepAssetType(), dto.getBussRepEquityInfoDto().getRepTradeMarket());
                    dto.setBaseRepAssetEquityInfoDto(baseRepAssetEquityInfoDto);
                    dto.setBaseRepOtherDto(baseRepOtherDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
            }
            else {
                dto.setBaseRepAssetEquityInfoDto(null);
            }
        }
        // 其他资产报送信息
        if (null != bussRepOtherInvestTargetInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他非标准化债权类投资.getValue())
             ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他新增可投资资产.getValue())) {
                if (null != dto.getBussRepOtherInvestTargetInfoDto()) {
                    //项目收益率
                    if (StringUtil.isNotEmpty(dto.getFinInterestFactorDto())){
                        dto.getBussRepOtherInvestTargetInfoDto().setRepProjectYieldRate(dto.getFinInterestFactorDto().getLoadRate());
                    }                    
                    BaseRepOtherInvestTargetInfoDto baseRepOtherInvestTargetInfoDto = assetCommonService.encapBaseRepOtherInvestTargetInfoDto(
                        finProduct, dto.getBussRepOtherInvestTargetInfoDto());
                    RepAssetOther repAssetOther = new RepAssetOther();
                    BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussRepOtherInvestTargetInfoDto());
                    BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct, repAssetOther);
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        dto.getBussRepOtherInvestTargetInfoDto().getRepAssetType(),
                        dto.getBussRepOtherInvestTargetInfoDto().getRepTradeMarket());
                    dto.setBaseRepOtherInvestTargetInfoDto(baseRepOtherInvestTargetInfoDto);
                    dto.setBaseRepOtherDto(baseRepOtherDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
            }
            else {
                dto.setBaseRepOtherInvestTargetInfoDto(null);
            }
        }
        // 基金资产报送信息
        if (null != bussRepFundInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_基础设施基金.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_货币市场基金.getValue())
            ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他私募股权投资基金.getValue())) {
                if (null != dto.getBussRepFundInfoDto()) {
                    BaseRepFundAssetInfoDto baseRepFundAssetInfoDto = assetCommonService.encapBaseRepFundAssetInfoDto(finProduct,
                        dto.getBussRepFundInfoDto());
                    RepAssetOther repAssetOther = new RepAssetOther();
                    BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussRepFundInfoDto());
                    BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct, repAssetOther);
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        dto.getBussRepFundInfoDto().getRepAssetType(), dto.getBussRepFundInfoDto().getRepTradeMarket());
                    dto.setBaseRepFundAssetInfoDto(baseRepFundAssetInfoDto);
                    dto.setBaseRepOtherDto(baseRepOtherDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
            }
            else {
                dto.setBaseRepFundAssetInfoDto(null);
            }
        }
        // 债券资产报送
        if (null != bussFinBondRepInfoService) {
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_理财直接融资工具.getValue())) {
                if (null != dto.getBussFinBondRepInfoDto()) {
                    // 债券资产报送
                    BaseRepBondAssetInfoDto baseRepBondAssetInfoDto = assetCommonService.encapBaseRepBondAssetInfoDto(finProduct,
                        dto.getBussFinBondRepInfoDto());
                    RepAssetOther repAssetOther = new RepAssetOther();
                    BeanUtil.copyPropertiesIgnoreNull(repAssetOther,dto.getBussFinBondRepInfoDto());
                    BaseRepOtherDto baseRepOtherDto = assetCommonService.encapBaseRepOtherDto(finProduct,repAssetOther);
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        dto.getBussFinBondRepInfoDto().getRepAssetType(), dto.getBussFinBondRepInfoDto().getRepTradeMarket());
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                    dto.setBaseRepOtherDto(baseRepOtherDto);
                    dto.setBaseRepBondAssetInfoDto(baseRepBondAssetInfoDto);
                }
            }
            else {
                dto.setBaseRepBondAssetInfoDto(null);
            }
        }
        //计划类资产报送
        if(null != bussRepAssetMngProdInfoService){
            if (StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_保险资产管理计划.getValue())) {
                if (null != dto.getBussRepAssetMngProdInfo()) {
                    // 计划类资产报送
                    BaseRepMngAssetPlanInfoDto baseRepMngAssetPlanInfoDto = assetCommonService.encapBaseRepMngAssetPlanInfoDto(finProduct,
                            dto.getBussRepAssetMngProdInfo());
                    BaseRepOtherDto baseRepOtherDto = new BaseRepOtherDto();
                    List<RepAssetOther> repAssetOtherList = new ArrayList<RepAssetOther>();
                    List<String> finprodIdList = new ArrayList<String>();
                    RepAssetOther repAssetOther =new RepAssetOther();
                    repAssetOther.setRepFinprodId(finProduct.getFinprodId());
                    repAssetOther.setRepPrivateprdFlag(dto.getBussRepAssetMngProdInfo().getRepPrivateprdFlag());
                    repAssetOther.setRepSmallcredit(dto.getBussRepAssetMngProdInfo().getRepSmallcredit());
                    repAssetOther.setRepBondtostockFlag(dto.getBussRepAssetMngProdInfo().getRepBondtostockFlag());
                    repAssetOther.setRepPppflag(dto.getBussRepAssetMngProdInfo().getRepPppflag());
                    repAssetOther.setRepPrivatefundFlag(dto.getBussRepAssetMngProdInfo().getRepPrivatefundFlag());
                    repAssetOtherList.add(repAssetOther);finprodIdList.add(finProduct.getFinprodId());
                    baseRepOtherDto.setRepAssetOtherList(repAssetOtherList);
                    baseRepOtherDto.setRepFinprodIdList(finprodIdList);
                    //负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                            dto.getBussFinBondRepInfoDto().getRepAssetType(), dto.getBussRepAssetMngProdInfo().getRepTradeMarket());
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                    dto.setBaseRepOtherDto(baseRepOtherDto);
                    dto.setBaseRepMngAssetPlanInfoDto(baseRepMngAssetPlanInfoDto);
                }
            }
            else {
                dto.setBaseRepMngAssetPlanInfoDto(null);
            }
        }
        // 项目类资产 简称存全称
        if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
            finProduct.setFinprodAbbr(dto.getFinprodName());
            finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
        }
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleAllList);
        baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
        baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRuleList);
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
    public FinCashInfo encapFinCashInfo(FinProjectTargetAssetDto dto, String cashType1, String cashType2, Long branch) {
        FinCashInfo finCashInfo = new FinCashInfo();
        // 工作流ID
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), dto.getFinprodType2(), branch, cashType2);
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return finCashInfo;
    }

    /**
     * 生成金融产品代码/市场代码 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectTargetAssetDto getFinprodId(FinProjectTargetAssetDto dto) {
        String finprodMarketId = StringUtil.EMPTY;
        String finprodId = StringUtil.EMPTY;
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_项目类资产信息);
        if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_非标债权.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_非标债权);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_理财直接融资工具.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_理财直融);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_信贷资产流转项目.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_信贷资产流转项目);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_基础设施基金.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_基础设施基金);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_其他产业投资基金.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_其他产业投资基金);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_结构化证券);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_股票定向增发);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商收益凭证.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_券商收益凭证);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票质押式回购.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_股票质押式回购);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_其他权益类投资.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_其他权益类投资);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商债权收益权投资.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_资产受益权投资);
        }
        else if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_保险资产管理计划.getValue())) {
            bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_保险资产管理计划);
        }
        //YUCHUBS-564:资产代码可手工填写，若新建时不填写，则系统根据规则自动生成 guyunfei 2019/09/05 start
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
            finprodMarketId = dto.getFinprodMarketId();
        }else{
            finprodMarketId = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate();
        }
        //YUCHUBS-564:资产代码可手工填写，若新建时不填写，则系统根据规则自动生成 guyunfei 2019/09/05 end
        finprodId = commonBusinessService != null ? commonBusinessService.getFinProdId(finprodMarketId,
            DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        dto.setFinprodMarketId(finprodMarketId);
        return dto;
    }

    /**
     * 获取基本信息 <功能详细描述>
     * 
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectTargetAssetDto getBasicInfo(Map<String, Object> paramMap) {
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/getBasicInfo", paramMap,
            FinProjectTargetAssetDto.class);
    }

    /**
     * 根据出账编号查出CMS信息 <功能详细描述>
     * 
     * @param cashOutNo
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectTargetAssetCmsDto getCmsInfoBycashOutNo(String cashOutNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cashOutNo", cashOutNo);
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/getCmsInfoBycashOutNo",
            map, FinProjectTargetAssetCmsDto.class);
    }

    public List<PaFinProductAssetAttach> getBusinessIdByFinprodId(String finprodId) {
        return restTemplate.getForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/getBusinessIdByFinprodId?finprodId=" + finprodId,
            PaFinProductAssetAttach.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.getSelectParam().put("finprodType2", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/invest/asset/project/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
		}else {
			exportParam.config("/com/joyintech/tams/invest/asset/project/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
		}
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProjectTargetAssetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinancier(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getFinancier", map,
            SelectOptionDto.class);
    }

    /**
     * 获取担保人
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getGuarantee(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getGuarantee", map,
                SelectOptionDto.class);
    }

    public String checkMarketExists(String finprodMarketId) {
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectTargetAsset/checkMarketExists", finprodMarketId,
                String.class);
    }
}
