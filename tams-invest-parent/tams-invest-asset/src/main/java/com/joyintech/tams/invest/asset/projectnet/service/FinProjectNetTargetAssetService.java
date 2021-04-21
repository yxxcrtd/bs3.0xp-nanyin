/*
 * 文件名：FinProjectTargetAssetService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian
 * 修改时间：2018年10月24日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.projectnet.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.project.BussParticipantInfoService;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.tams.invest.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权益类资产
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAssetService
 * @since 1.0
 */
@Service
public class FinProjectNetTargetAssetService extends CommonExportService<FinProjectNetTargetAssetDto> implements BaseProcessService<FinProjectNetTargetAssetDto> {

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 业务编号
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private AssetCommonService assetCommonService;

    @Autowired(required = false)
    private BussParticipantInfoService bussParticipantInfoService;

    @Autowired(required = false)
    private BussFinBondRepInfoService bussFinBondRepInfoService;

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
    public Page<FinProjectNetTargetAssetDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
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
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectNetTargetAsset/findPage", pageParam,
                FinProjectNetTargetAssetDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProjectNetTargetAssetDto entity, OperationType type) {
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
        ptlPortfolio.setProfitType(StringUtils.isEmpty(entity.getProfitType()) ? DictCodeList.收益类型_收益型.getValue()
				: entity.getProfitType());
        entity.setPtlPortfolio(ptlPortfolio);
        //add end
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
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
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectNetTargetAsset/" + action, entity,
            Integer.class);

        // 推送数据到第三方接口
        if (!OperationType.DELETE.equals(type)) {
            //1.项目要素信息
            Map<String, Object> invmap = JsonUtils.object2Map(entity);
            invmap.put("pageId", "FinProjectNetTargetAsset002S062");
            investInterfCommService.doInvestInterf(invmap);
        }
        // 标的删除接口发送 add by yufei 20200828 start
        else {
            Map<String, Object> map = JsonUtils.object2Map(entity);
//            investInterfCommService.doSendDelFinProductToInterf(map);
        }
        // 标的删除接口发送 add by yufei 20200828 end


    }

    /**
     * 封装基础组件-标的类金融产品需要的DTO
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectNetTargetAssetDto encapBaseFinProductBaseInfoList(FinProjectNetTargetAssetDto dto) {
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
        // 项目类资产 简称存全称
        finProduct.setFinprodAbbr(dto.getFinprodName());
        finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
        finProduct.setProfitType(DictCodeList.收益类型_净值型.getValue());
        // 金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
        finProductType.setType8(DictCodeListExt3.平安资产类型_权益类资产.getValue());
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        // 金融产品扩展entity
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, dto);
        paFinProduct.setActualCreditProvid(dto.getActualCreditProvid());
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
        return dto;
    }


    /**
     * 生成金融产品代码/市场代码 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectNetTargetAssetDto getFinprodId(FinProjectNetTargetAssetDto dto) {
        String finprodMarketId = StringUtil.EMPTY;
        String finprodId = StringUtil.EMPTY;
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产信息);
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
            finprodMarketId = dto.getFinprodMarketId();
        }else{
            finprodMarketId = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate();
        }
        finprodId = commonBusinessService != null ? commonBusinessService.getFinProdId(finprodMarketId,
                DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
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
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectNetTargetAsset/getBasicInfo", paramMap,
            FinProjectTargetAssetDto.class);
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.getSelectParam().put("finprodType2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/invest/asset/projectnet/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
		}else {
			exportParam.config("/com/joyintech/tams/invest/asset/projectnet/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
		}
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProjectNetTargetAssetDto> selectPageData(Map<String, Object> selectParam) {
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
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinProjectNetTargetAsset/checkMarketExists", finprodMarketId,
                String.class);
    }
}
