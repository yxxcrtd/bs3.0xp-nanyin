/*
 * 文件名：FinDepositTargetService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年9月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.depo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.project.BussParticipantInfoService;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.component.business.project.dto.BussPostInvestInfoDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.interfaceDto.*;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.util.MapParseUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.api.service.ValInterfCommonServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.component.business.asset.FinInterestFactorService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDlRepoInfoService;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepDespositAssetInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepDlRepoInfoDto;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 存款类资产Service
 * 
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see FinDepositTargetService
 * @since 1.0
 */
@Service
public class FinDepositTargetService extends CommonExportService<FinDepositTargetDto> implements BaseProcessService<FinDepositTargetDto> {

    /*
     * @Autowired private FileAttachmentProcess fileAttachmentProcess;
     */
    /**
     * 共通--资产
     */
    @Autowired
    private AssetCommonService assetCommonService;

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
     * 存款资产中债报送信息
     */
    @Autowired(required = false)
    private BussRepDepositInfoService bussRepDepositInfoService;

    /**
     * 拆放同业及买入返售报送信息
     */
    @Autowired(required = false)
    private BussRepDlRepoInfoService bussRepDlRepoInfoService;

    /*
     * @Autowired(required = false) private MstFileCommonService fileCommonService;
     */

    /**
     * 业务编号
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired(required = false)
    private BussParticipantInfoService bussParticipantInfoService;

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
    public Page<FinDepositTargetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        pageParam.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        // type9-11 created by wangguiqi on 2019/8/1 -start
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
        // type9-11 created by wangguiqi on 2019/8/1 -end
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/findPage", pageParam,
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
        	//新建存款类资产时，自动创建交易对手 add by hxy 20200421 start
    		assetCommonService.autoCreateTradeCounter(entity.getIssuer());
    		//新建存款类资产时，自动创建交易对手 add by hxy 20200421 end
            /*
             * //针对资产 入库前,金融产品id需要重新生成 String oldFinprodId = entity.getFinprodId(); String oldfinprodMarketId = entity.getFinprodMarketId(); if(null!=entity){
             * entity = getFinprodId(entity); String jsonStr = JsonUtils.object2Json(entity); jsonStr = StringUtil.replaceAll(jsonStr, oldFinprodId,
             * entity.getFinprodId()); jsonStr = StringUtil.replaceAll(jsonStr, oldfinprodMarketId, entity.getFinprodMarketId()); try { entity =
             * (FinDepositTargetDto)JsonUtils.OBJECT_MAPPER.readValue(jsonStr, FinDepositTargetDto.class); } catch (Exception e) { e.printStackTrace(); } } //文件信息
             * if (CollectionUtils.isNotEmpty(entity.getFileArray()) && StringUtil.isNotEmpty(entity.getFinprodId())) { // 将附件和当前业务对象绑定（更新附件的业务代码）
             * fileAttachmentProcess.updateBussinessNo( entity.getFileArray().stream().map(UploadFile::getFileNo).collect( Collectors.toList()),
             * entity.getFinprodId()); }
             */
            action = "insert";
        } else {
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
            insertDataAuthTypeInst(entity.getFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_存款类资产信息.getValue());
        }

        // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
        if (!StringUtil.isEmptyList(entity.getBaseFinProductBaseInfoList())) {
            for (BaseFinProductBaseInfoDto productBaseInfoDto : entity.getBaseFinProductBaseInfoList()) {
                if (!StringUtil.isEmptyList(productBaseInfoDto.getFinCashPayRuleList())) {
                    for (FinCashPayRule finCashPayRule : productBaseInfoDto.getFinCashPayRuleList()) {
                        if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
                            finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
                        }
                    }
                }
            }
        }

        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/" + action, entity, Integer.class);
        // 推送数据到第三方接口

            //1.存款类要素信息
            Map<String, Object> invMap = JsonUtils.object2Map(entity);
            invMap.put("pageId", "FinDepositTargetAsset002S026");
            investInterfCommService.doInvestInterf(invMap);
            //2.利率信息
            invMap.put("pageId", "FinDepositTargetAsset002S027");
            investInterfCommService.doInvestInterf(invMap);
            //3.现金流计划
            invMap.put("pageId", "FinDepositTargetAsset002S028");
            investInterfCommService.doInvestInterf(invMap);
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
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
            CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
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
        finProduct.setCapiIncomeFeature(DictCodeList.是否_是.getValue());
        if (StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
            finProduct.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
        }
        // 金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType8(DictCodeList.平安资产类型_存款.getValue());
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        //----add by jjpei 20190727 添加投资性质----start----
        finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
        //----add by jjpei 20190727 添加投资性质----end----
        if (StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
            finProductAdd.setTermType(
                    ValInterfCommonServiceApi.getRepTermType(
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
        // 现金流信息Entity-本金
        FinCashInfo finCashInfoPrincipal = encapFinCashInfo(dto, DictCodeListExt1.现金流业务类型_金融产品.getValue(),
            DictCodeListExt1.现金流一级类型_本金.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue(), branch);
        // 现金流信息Entity-利息
        FinCashInfo finCashInfoInterest = encapFinCashInfo(dto, DictCodeListExt1.现金流业务类型_金融产品.getValue(),
            DictCodeListExt1.现金流一级类型_利息.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue(), branch);
        // 现金流信息Entity-资产推荐费
        FinCashInfo finCashInfoRefer = encapFinCashInfo(dto, DictCodeListExt1.现金流业务类型_金融产品.getValue(),
            DictCodeListExt1.现金流一级类型_费用.getValue(), DictCodeListExt1.现金流二级类型_资产推荐费.getValue(), branch);
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
               // baseFinProductBaseInfoDto.setFinCashCalcRuleList(finInterestFactorDto.getCalcRuleList());
                finCashCalcRuleAllList.addAll(finInterestFactorDto.getCalcRuleList());
                dto.setFinInterestFactorDto(finInterestFactorDto);
            }
        }
        // 付息信息DTO 现金流支付规则 现金流支付支付计划
        if (null != finPayInterestStdInfoService) {
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(
                dto.getFinPayInterestStdInfoDto());
            finPayInterestStdInfoDto = assetCommonService.encapFinPayInterestStdInfo(finProduct, finPayInterestStdInfoDto,
                finCashInfoPrincipal, finCashInfoInterest, branch);
            baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
            if(StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())
                    ||StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_存放同业.getValue())){
                baseFinProductBaseInfoDto.setFinCashPlanList(finPayInterestStdInfoDto.getFinCashPlan2List());
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
        // 附件信息
        /*
         * if(null!=fileCommonService){ if(null!=dto.getFileCommonList()){ FileCommonDto fileCommonDto =
         * assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList()); dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter()); } }
         */
        // TODO
        // 中债信息
        if (null != bussRepDepositInfoService && null != bussRepDlRepoInfoService) {
            BigDecimal rate = BigDecimal.ZERO;
            String basis = StringUtil.EMPTY;
            if (null != finInterestFactorService) {
                if (null != dto.getFinInterestFactorDto()) {
                    rate = ArithmeticUtil.divide(dto.getFinInterestFactorDto().getLoadRate(), 100, 12);
                    basis = dto.getFinInterestFactorDto().getBasis();
                }
                else {
                    rate = null;
                }
            }
            // 同业借款
            if (StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
                // 拆放同业及买入返售报送信息
                if (null != dto.getBussRepDlRepoInfoDto()) {
                    BaseRepDlRepoInfoDto baseRepDlRepoInfoDto = assetCommonService.encapBussRepDlRepoInfoDto(finProduct,
                        dto.getBussRepDlRepoInfoDto(), rate, basis);
                    // 负债
                    // TODO 数据字典：拆出 设计:拆放同业
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        DictCodeListExt2.资产_负债二级类别_拆放同业.getValue(), dto.getBussRepDlRepoInfoDto().getRepTradeMarket());
                    dto.setBaseRepDlRepoInfoDto(baseRepDlRepoInfoDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
            }
            else {
                // 存业同放
                if (null != dto.getBussRepDepositInfoDto()) {
                    // 存款资产中债报送
                    BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto = assetCommonService.encapbaseRepDespositAssetInfoDto(
                        finProduct, dto.getBussRepDepositInfoDto(), rate, basis);
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        dto.getBussRepDepositInfoDto().getRepAssetType(), dto.getBussRepDepositInfoDto().getRepTradeMarket());
                    dto.setBaseRepDespositAssetInfoDto(baseRepDespositAssetInfoDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
            }
        }
        //add by  zhangnian 20191016 start
        //投后信息
        if(StringUtil.isNotEmpty(dto.getPostOrg())){
            BussPostInvestInfoDto bussPostInvestInfoDto = new BussPostInvestInfoDto();
            bussPostInvestInfoDto.setPostOrg(dto.getPostOrg());
            bussPostInvestInfoDto.setFinprodId(dto.getFinprodId());
            dto.setBussPostInvestInfoDto(bussPostInvestInfoDto);
        }else{dto.setBussPostInvestInfoDto(null);}
        //add by  zhangnian 20191016 end
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleAllList);

        // 增加推荐方与推荐费率 add by renping 20191014 start
        if (dto.getBussFinRecommentDto() != null && !StringUtil.isEmptyList(
            dto.getBussFinRecommentDto().getFinProductRecommentList())) {
            dto.getBussFinRecommentDto().getFinProductRecommentList().forEach(item -> {
                item.setFinprodId(dto.getFinprodId());
            });
            baseFinProductBaseInfoDto.setFinProductRecommentList(
                dto.getBussFinRecommentDto().getFinProductRecommentList());
        }
        // 增加推荐方与推荐费率 add by renping 20191014 end
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
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
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

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.getSelectParam().put("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        String isShowType = (String) exportParam.getSelectParam().get("isShowType");
        if(StringUtil.equals(isShowType, "01")) {
        	exportParam.config("/com/joyintech/tams/invest/asset/depo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
        }else {
        	exportParam.config("/com/joyintech/tams/invest/asset/depo/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinDepositTargetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 获取基本信息--修改/详细
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto getBasicInfo(String finprodId,String finprodMarketId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/getBasicInfo?finprodId="
                                         + finprodId + "&finprodType=" + DictCodeListExt1.现金流业务类型_金融产品.getValue() + "&cashType="
                                         + DictCodeListExt1.现金流二级类型_资产推荐费.getValue()+"&finprodMarketId="+finprodMarketId,
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
        if (StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
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
            finprodMarketId = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate();
        }
        //YUCHUBS-564:资产代码可手工填写，若新建时不填写，则系统根据规则自动生成 guyunfei 2019/09/05 end
        finprodId = commonBusinessService != null ? commonBusinessService.getFinProdId(finprodMarketId,
            DictCodeListExt1.金融产品类型_投管用_存款.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        dto.setFinprodMarketId(finprodMarketId);
        return dto;
    }

    /**
     * 交易对手下拉框
     * 
     * @param param
     * @param counterType
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTradeOpponent(String param, String counterType) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/getTradeOpponent?param=" + param
                                       + "&counterType=" + counterType,
            SuggestDto.class);
    }

    /**
     * 交易日历
     * 
     * @param param
     * @param isTradeCalendar
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTradeCalendarName(String param, String isTradeCalendar) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/getTradeCalendarName?param="
                                       + param + "&isTradeCalendar=" + isTradeCalendar,
            SuggestDto.class);
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
                ServiceApplication.ASSET_SERVICE_NAME + "/FinDepositTarget/getCustomerName",
                map, SelectOptionDto.class);
    }
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/

}
