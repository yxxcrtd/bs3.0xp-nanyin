/*
 * 文件名：FinPlanAssetCService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import com.joyintech.tams.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.asset.service.BussFinRepaymentService;
import com.joyintech.tams.valuation.component.asset.service.FinInterestFactorService;
import com.joyintech.tams.valuation.component.asset.service.FinPayInterestStdInfoService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月7日
 * @see ValProfitprojectService
 * @since 1.0
 */
@Service
public class ValProfitprojectService extends CommonExportService<ValProfitprojectDto> implements BaseProcessService<ValProfitprojectDto>{

    /**
     * 资产管理计划中债信息组件
     */
//    @Autowired(required=false)
//    private BussRepAssetMngProdInfoService bussRepAssetMngProdInfoService;
    /**
     * 共通--资产
     */
    //@Autowired(required=false)
    //private AssetCommonService assetCommonService;
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired(required = false)
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
     * 投管接口共通组件
     */
//    @Autowired
//    private InvestInterfCommService investInterfCommService;
    
    @Override
    public void doInsert(ValProfitprojectDto entity, OperationType type) {
        updateFiles(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValProfitprojectDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
//        finProduct.setProfitType(DictCodeList.收益类型_净值型.getValue());
//        finProduct.setTermDays((dto.getMdate().getTime()-dto.getVdate().getTime())/1000/60/60/24);
//        if (entity.getMdate() != null) {
//            finProduct.setTermDays((entity.getMdate().getTime()-entity.getVdate().getTime())/1000/60/60/24);
//        }
//        finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
//        entity.setBussRepAssetMngProdInfoDto(bussRepAssetMngProdInfoService.
//            getBussRepAssetMngProdInfoDto(entity.getBussRepAssetMngProdInfo()));
       /* update by zhuyongkang 20191009 start*/
//        entity.setBussParticipantInfoDto(assetCommonService.enBussParticipantInfoDto(finProduct,
//            entity.getBussParticipantInfoDto(),entity.getBranch()));
        /* update by zhuyongkang 20191009 end*/
        // entity.setSystemCode("SYS");
//        //add by dongzhiling start at 2019-02-22 :生成委外组合
//        PtlPortfolio ptlPortfolio = new PtlPortfolio();
//        ptlPortfolio.setPortfolioId(finProduct.getFinprodMarketId());
//        ptlPortfolio.setPortfolioType(DictCodeListExt1.组合类型_委外组合.getValue());
//        ptlPortfolio.setPortfolioName(finProduct.getFinprodName());
//        ptlPortfolio.setVdate(finProduct.getVdate());
//        ptlPortfolio.setMdate(finProduct.getMdate());
//        ptlPortfolio.setProfitType(DictCodeListExt2.收益类型_货币.getValue());
//        entity.setPtlPortfolio(ptlPortfolio);
        //add end

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
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/" + action, entity,
            Integer.class);

        // 推送数据到第三方接口

        //1.计划类要素信息

//        Map<String, Object> invMap =  JsonUtils.object2Map(entity);
//        invMap.put("pageId","FinPlanTargetAsset002S017");
//        investInterfCommService.doInvestInterf(invMap);

        //2.利率信息

//        invMap.put("pageId","FinPlanTargetAsset002S018");
//        investInterfCommService.doInvestInterf(invMap);

    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValProfitprojectDto> selectForPage(Map<String, Object> map) {
        map.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
        //pageParam.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
        map.put("cashFinprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());//现金流业务类型
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/findPage",
            map, ValProfitprojectDto.class);
    }

    public List<ValProfitprojectDto> selectForList(Map<String, Object> map) {
        map.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
        //pageParam.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
        map.put("cashFinprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());//现金流业务类型
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/selectForList",
                map, ValProfitprojectDto.class);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getIssuerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/ValProfitproject/getIssuer", map,
            SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getManagerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/ValProfitproject/getManager", map,
            SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getTrusteeId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
            + "/ValProfitproject/getTruster", map,
            SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getFinancier(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValProfitproject/getFinancier", map,
                SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getGuarantee(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValProfitproject/getGuarantee", map,
                SelectOptionDto.class);
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
    public String getFinprodId(String finprodMarketId){
      return  restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/getFinprodId?finprodMarketId="+finprodMarketId,String.class
        );
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/
    /*增加资产代码重复校验 add by zhuyongkang 20191021 start*/
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Integer selectFinprodMarketId(String finprodMarketId) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValProfitproject/selectFinprodMarketId?finprodMarketId="
                        + finprodMarketId,
                Integer.class);
    }
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public ValProfitprojectDto getBasicInfoByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/getBasicInfoByFinprodId", param,
                ValProfitprojectDto.class);
    }
    private void updateFiles(ValProfitprojectDto entity) {
        //List<SysUploadFileInfo> files = entity.getFileArray();
//        if(files.size()>0) {
//            List<String> fileNo = new ArrayList<>();
//            files.forEach(sysUploadFileInfo -> {
//                fileNo.add(sysUploadFileInfo.getSeqNo());
//            });
//            fileAttachmentProcess.updateBussinessNo(fileNo, entity.getFjId());
//        }
    }

    @Override
    public void doUpdate(ValProfitprojectDto entity) {
        //updateFiles(entity);
    }

    @Override
    public void doDelete(String workflowId) {
        ValProfitprojectDto finPlanProfitAssetDto = getBussinessEntityByWorkflowId(workflowId);
        List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(finPlanProfitAssetDto.getFjId());
        if(files.size()>0) {
            files.forEach(uploadFile -> {
                fileAttachmentProcess.remove(uploadFile.getFileNo());
            });
        }
    }
    /**
     * 获取所有资产id
     * <功能详细描述>
     * @param search
     * @param finprodType2
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(String search, String finprodType2, String profitType){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValProfitproject/getAssetId?search="
                        + search+"&finprodType2="+finprodType2+"&profitType="+profitType+"&child="+ RequestUtil.getParam("child"),
                SuggestDto.class);
    }

    public boolean isDealCalendarHoliday(Date date, String calId) {
        if (date == null || StringUtil.isEmpty(calId)) {
            return false;
        }
        String dateStr = DateUtil.format(date);
        return this.isDealCalendarHoliday(dateStr, calId);
    }
    /**
     * 判断日期参数是否交易日历的节假日
     *
     * @param dateStr 参数日期
     * @param calId 交易日历id
     * @return 是否交易日历的节假日判断结果
     */
    public boolean isDealCalendarHoliday(String dateStr, String calId) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(calId)) {
            return false;
        }

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dateStr", dateStr);
        param.put("calId", calId);
        Integer dataCount =  restTemplate.postForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValProfitproject/isDealCalendarHoliday", param,
                Integer.class);
        if (dataCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询资产下拉框
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getAssetSelect(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValProfitproject/getAssetSelect", map,
                SelectOptionDto.class);
    }

    /**
     * 生成金融产品代码/市场代码
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValProfitprojectDto getFinprodId(ValProfitprojectDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService != null ?
                commonBusinessService.getFinProdId(finprodMarketId, DictCodeListExt1.金融产品类型_股票.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        return dto;
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    private ValProfitprojectDto encapBaseFinProductBaseInfoList(ValProfitprojectDto dto, OperationType type) {
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE))?
                0:Long.parseLong(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE));
        dto.setBranch(branch);
        //标的类金融产品dto
        FinProduct finProduct = new FinProduct();
        //金融产品代码（估值核算）
        finProduct.setFinprodAbbr(finProduct.getFinprodName());
        finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        finProduct.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        dto.setFinprodAbbr(dto.getFinprodName());
        if(!StringUtil.isNotEmpty(dto.getFinprodType())){
            dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        }
        if(!StringUtil.isNotEmpty(dto.getFinprodType2())){
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        }
        BeanUtil.copyPropertiesIgnoreNull(finProduct,dto);
        finProduct.setFinprodId(dto.getFinprodId());
        // 实际到期日:先存成到期日
        finProduct.setActmdate(finProduct.getMdate());
        if (dto.getMdate() != null) {
            //finProduct.setTermDays((dto.getMdate().getTime()-dto.getVdate().getTime())/1000/60/60/24);
            finProduct.setTermDays(DateUtil.getBetweenDayNumber(dto.getVdate(),dto.getMdate()));
        }
        finProduct.setBlnArea(dto.getBlnArea());
        finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
        //标的类金融产品附属表dto
        FinProductAdd finProductAdd = new FinProductAdd();
        finProductAdd.setFinprodId(finProduct.getFinprodId());
        finProductAdd.setInvestNature(dto.getInvestNature());
        finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());
        /*  update by zhuyongkang 2019/09/29 start*/

        if(StringUtil.equals(DictCodeList.清算速度_T_0.getValue(), dto.getTransMode())) {
            finProductAdd.setPurSpeed(0L);
        }
        else if(StringUtil.equals(DictCodeList.清算速度_T_1.getValue(), dto.getTransMode())) {
            finProductAdd.setPurSpeed(1L);
        }
        else if(StringUtil.equals(DictCodeList.清算速度_T_2.getValue(), dto.getTransMode())) {
            finProductAdd.setPurSpeed(2L);
        }
        else {
            finProductAdd.setPurSpeed(0L);
        }
        if(StringUtil.equals(DictCodeListExt1.证券品种_其他债权类.getValue(), dto.getType18())) {
            finProductAdd.setIsQtzq(DictCodeList.是否_是.getValue());
        }
        else {
            finProductAdd.setIsQtzq(DictCodeList.是否_否.getValue());
        }
        finProductAdd.setBondRiskType(dto.getBondRiskType());
        finProductAdd.setIsEffPledge(dto.getIsEffPledge());
        finProductAdd.setIsCompoundInt(dto.getIsCompoundInt());
        if (dto.getBussFinIntPayDto() != null){
            finProductAdd.setIsCompoundInt(dto.getBussFinIntPayDto().getIsCompoundInt());
        }
        //金融产品分类表dto
        FinProductType finProductType = new FinProductType();
        finProductType.setFinprodId(finProduct.getFinprodId());
        finProductType.setType1(dto.getAssetType());
        finProductType.setType2(dto.getAccountintType());
        finProductType.setType3(dto.getTaxType());
        finProductType.setType18(dto.getType18());
        finProductType.setBranch(dto.getBranch());
        finProductType.setType6(dto.getCategory());
        finProductType.setType7(dto.getType());
        /*添加五级分类 lishuping 2019/7/23 start*/
        finProductType.setType9(dto.getType9());
        finProductType.setType10(dto.getType10());
        finProductType.setType11(dto.getType11());
        finProductType.setType12(dto.getType12());
        /*添加五级分类 lishuping 2019/7/23 end*/
        if(StringUtil.equals(DictCodeListExt1.证券品种_其他债权类.getValue(), dto.getType18())) {
            finProductType.setType8(DictCodeList.平安资产类型_项目类资产.getValue());
        }
        else {
            finProductType.setType8(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        }
        //现金流信息List
        List<FinCashInfo> finCashInfoList = new ArrayList<>();
//        //现金流计算规则dtolist
//        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<>();
        //现金流支付规则
        List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
        //现金流支付计划
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //现金流信息-本金
        FinCashInfo finCashInfoPrincipal = new FinCashInfo();
        finCashInfoPrincipal.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
        finCashInfoPrincipal.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
        finCashInfoPrincipal.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        finCashInfoPrincipal.setBranch(dto.getBranch());
        finCashInfoPrincipal.setFinprodId(finProduct.getFinprodId());
        finCashInfoPrincipal.setCcy(finProduct.getCcy());
        String cashId1 = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.金融产品类型_计划类资产.getValue(),
                branch, DictCodeListExt1.现金流二级类型_本金.getValue());
        finCashInfoPrincipal.setCashId(cashId1);
//        finCashInfo1.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfo1.getFinprodType()
//                +(char)256+finCashInfo1.getBranch()+(char)256+finCashInfo1.getCashType2()));
        //现金流信息-利息
        FinCashInfo finCashInfoInterest = new FinCashInfo();
        BeanUtil.copyPropertiesIgnoreNull(finCashInfoInterest,finCashInfoPrincipal);
        finCashInfoInterest.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        finCashInfoInterest.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        String cashId2 = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.金融产品类型_计划类资产.getValue(),
                branch, DictCodeListExt1.现金流二级类型_利息.getValue());
        finCashInfoInterest.setCashId(cashId2);
//        finCashInfo1.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfo2.getFinprodType()
//                +(char)256+finCashInfo2.getBranch()+(char)256+finCashInfo2.getCashType2()));
        finCashInfoList.add(finCashInfoPrincipal);
        finCashInfoList.add(finCashInfoInterest);
        //计息要素DTO 现金流利率信息  现金流计算规则
        if(null != finInterestFactorService){
            if(null != dto.getFinInterestFactorDto()){
                FinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(dto.getFinInterestFactorDto());
                finInterestFactorDto = assetCommonService.encapFinInterestFactor(finProduct,finInterestFactorDto, finCashInfoInterest,null, branch);
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
                //baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
                finCashPlanList.addAll(finPayInterestStdInfoDto.getFinCashPlan2List());
                finCashPayRuleList.addAll(finPayInterestStdInfoDto.getFinCashPayRuleList());
            }
        }
        //还本信息DTO 现金流支付支付计划
        if(null != bussFinRepaymentService){
            if(dto.getBussFinRepaymentDto() != null) {
                BussFinRepaymentDto bussFinRepaymentDto = assetCommonService.encapBussFinRepayment(finProduct, dto.getBussFinRepaymentDto(), finCashInfoPrincipal, branch);
                finCashPlanList.addAll(bussFinRepaymentDto.getFinCashPlan2List());
                finCashPayRuleList.add(bussFinRepaymentDto.getFinCashPayRule());
            }
        }

//        //现金流信息-参与方信息（多条数据）以及 现金流计算规则dtolist
//        if(null!=dto.getBussParticipantInfoDto()){
//            BussParticipantInfoDto bussParticipantInfoDto = dto.getBussParticipantInfoDto();
//            finCashCalcRuleList.addAll(bussParticipantInfoDto.getFinCashCalcRuleList());
//            finCashInfoList.addAll(bussParticipantInfoDto.getFinCashInfoList());
//        }
//        //现金流支付规则表Entity/分红计划组件
//        //分红计划组件dto
//        if(dto.getBussFinIntPayDto() != null){
//            FinCashPayRule finCashPayRule = new FinCashPayRule();
//            BeanUtil.copyProperties(finCashPayRule,dto.getBussFinIntPayDto());
//            finCashPayRule.setCashId(finCashInfo2.getCashId());
//            finCashPayRule.setEffDate(dto.getVdate());
//            finCashPayRule.setFinprodId(finProduct.getFinprodId());
//            finCashPayRule.setFinprodType(finProduct.getFinprodType());
//            finCashPayRule.setFinprodType2(finProduct.getFinprodType2());
//            finCashPayRule.setBranch(dto.getBranch());
//            if(DictCodeList.收益结转频率_按月结转.getValue().equals(dto.getBussFinIntPayDto().getPayCycle())){
//                finCashPayRule.setDayType(DictCodeListExt1.日期类型_几号.getValue());
//            }
//            //List<现金流支付规则表Entity>/List<分红计划组件Entity>
//            List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
//            finCashPayRuleList.add(finCashPayRule);
//        }
//        //获取要修改的金融产品代码集合(修改时用)
//        List<String> finprodIdList = new ArrayList<String>();
//        finprodIdList.add(finProduct.getFinprodId());

//        // 增加推荐方与推荐费率 add by renping 20191014 start
//        if (StringUtil.isNotEmpty(dto.getRecommender())) {
//            List<FinProductRecomment> finProductRecommentList = new ArrayList<>();
//            FinProductRecomment finProductRecomment = new FinProductRecomment();
//            finProductRecomment.setFinprodId(dto.getFinprodId());
//            finProductRecomment.setRecommender(dto.getRecommender());
//            finProductRecomment.setWorkflowId(dto.getWorkflowId());
//            finProductRecommentList.add(finProductRecomment);
//            param.put("finProductRecommentList", finProductRecommentList);
//        }
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
        baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRuleList);
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        return dto;
    }
}
