/*
 * 文件名：FinStockInfoService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年9月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.stock.service;

import com.jixu.common.type.ResultContainer;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.component.business.common.dto.InfChinamutualfunddescriptionDto;
import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetCreditNonstdInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepEquityInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepAssetEquityInfoDto;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.interfaceDto.S031Dto;
import com.joyintech.tams.invest.asset.stock.dto.FinStockInfoDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.common.TrdCommDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.util.MapParseUtil;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.valuation.api.constant.BussinessJixuConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 股票标的Service
 *
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see FinStockInfoService
 * @since 1.0
 */
@Service
public class FinStockInfoService extends CommonExportService<FinStockInfoDto> implements BaseProcessService<FinStockInfoDto> {

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
     * 信贷及非标资产报送
     */
    @Autowired(required = false)
    private ValuationInterfService valuationInterfService;

    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 权益类--中债报送
     */
    @Autowired(required = false)
    private BussRepEquityInfoService bussRepEquityInfoService;
    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    @Autowired
    private CommonAutoCouponService commonAutoService;
    /**
     * 附件
     */
    @Autowired(required = false)
    private MstFileCommonService fileCommonService;

    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;
    /**
     * @title: FinStockInfoService
     * @package com.joyintech.tams.invest.asset.stock.service
     * @description: 股票标的分页查询
     * @author: yegang
     * @date: 2019-06-19
     */
    //来自：FinCreditProjectService
    public Page<FinStockInfoDto> selectForPage(Map<String, Object> pageParam) {
        if (StringUtil.isNotEmpty((String)pageParam.get("vdate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)pageParam.get("vdate"));
            pageParam.put("vdatestart", dateList.get(0));
            pageParam.put("vdateend", dateList.get(2));
        }
        if(iPlatformDate != null){
            pageParam.put("sysDate", DateUtil.getSysDate());
           // pageParam.put("sysDate", DateUtil.format(iPlatformDate.getSysDate(),"yyyy-MM-dd hh24:mi:ss"));
        }
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/findPage",
            pageParam, FinStockInfoDto.class);
    }

    public FinStockInfoDto getShares(Map<String,Object> map) {
        if(iPlatformDate != null){
            map.put("sysDate", DateUtil.getSysDate());
        }
        return
                restTemplate.postForObject(
                        ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getShares"
                        ,map,FinStockInfoDto.class);
    }



    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String param) {
        return restTemplate.getForList(
            ServiceApplication.ASSET_SERVICE_NAME +
                "/FinStockInfo/getFinprodMarketIdList?finprodMarketId="+ param,
            SuggestDto.class);
    }
    /**
     * 修改/双击查看根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto selectByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/selectByFinprodId?finprodId="
                +finprodId,FinStockInfoDto.class);
    }
//
    /**
     * <一句话功能简述>
     * <check中用的>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto getBasicInfoByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getBasicInfoByFinprodId", param,
            FinStockInfoDto.class);
    }
    /**
     * <发行人>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
//    public Page<SelectOptionDto> getIssuer(Map<String,Object> param) {
//        return restTemplate.postForPage(
//            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getIssuer",param,
//            SelectOptionDto.class);
//    }
    /**
     * 交易日历
     *
     * @param
     * @param tradeMarket
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCalendarId(String tradeMarket) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getCalendarId?tradeMarket=" + tradeMarket,
            SuggestDto.class);
    }
//    public FinStockInfoDto getCalendarId (String tradeMarket) throws ParseException{
//        FinStockInfoDto dto = restTemplate.get(ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getCalendarId?tradeMarket=" + tradeMarket,
//                FinStockInfoDto.class);
//        return dto;

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/asset/stock/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }
    @Override
    public Page<FinStockInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(FinStockInfoDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 封装标的类金融产品基础组件需要的DTO
        if (!OperationType.DELETE.equals(type)) {
		    entity.setFinprodMarketId(entity.getFinprodMarketId()+entity.getTradeMarketId());
		    entity.setFinprodId(commonBusinessService.getFinProdId(entity.getFinprodMarketId(), entity.getFinprodType2(), null));
        }
        entity = encapBaseFinProductBaseInfoList(entity);
        
        // jixu 接口 add by wangpeng start
        TrdCommDto trdCommDto = new TrdCommDto();
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_DELETE);
        } else if (OperationType.NEW.equals(type)) {
           	//新建股票资产时，自动创建交易对手 add by hxy 20200421 start
    		assetCommonService.autoCreateTradeCounter(entity.getIssuer());
    		//新建股票资产时，自动创建交易对手 add by hxy 20200421 end
            action = "insert";
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_ADD);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getFinprodId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        } else {
            action = "update";
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_UPDATE);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getFinprodId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/" + action, entity, Integer.class);
        if (entity.getBaseFinProductBaseInfoList() != null && !entity.getBaseFinProductBaseInfoList().isEmpty()) {
            if (!trdCommDto.getOperateType().equals(BussinessConstant.OPERATE_TYPE_DELETE)) { 
                FinProduct finProduct = entity.getBaseFinProductBaseInfoList().get(0).getFinProduct();
                if (StringUtil.isNotEmpty(finProduct)) {
                    finProduct.setFinprodName(entity.getFinprodAbbr());
                }
                trdCommDto.setFinProduct(finProduct);
                trdCommDto.setFinProductAdd(entity.getBaseFinProductBaseInfoList().get(0).getFinProductAdd());
                trdCommDto.setHeader(BussinessJixuConstant.消息头_股票标的信息);
                ResultContainer resultContainer = valuationInterfService.runValInvest2Jixu(trdCommDto);
            } 
//            System.out.println(resultContainer);
        }
    // jixu 接口 add by wangpeng end

        // 推送数据到第三方接口
        //1.股票要素信息
        Map<String, Object> invmap =  JsonUtils.object2Map(entity);
        invmap.put("pageId","FinStockTargetAsset002S031");
        investInterfCommService.doInvestInterf(invmap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(FinStockInfoDto entity, OperationType type){
        //文件
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(entity.getFileArray());
        mstFileInputParameter.setBussinessId(entity.getWorkflowId());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinStockInfoDto entity){
        //文件
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(entity.getFileArray());
        mstFileInputParameter.setBussinessId(entity.getWorkflowId());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * 封装基础组件-标的类金融产品需要的DTO
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto encapBaseFinProductBaseInfoList(FinStockInfoDto dto) {
        //登表
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
        dto.setBlnArea(DictCodeList.境内外_境内.getValue());
        dto.setRegistOrg(DictCodeList.登记托管机构_中国证券登记结算有限责任公司.getValue());
        dto.setIsChl(DictCodeListExt2.是否_通道_否.getValue());

        // 分支序号
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
            CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
//        // 现金流信息List
//        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        // 标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        // 简称存全称
        finProduct.setFinprodAbbr(dto.getFinprodName());
        // 金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
        finProductType.setBranch(branch);
//        DictCode
        if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_否.getValue())
        		&&StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())){
        	finProductType.setType6(DictCodeListExt2.股票核算分类_股票_上海股票.getValue());
        }
        else if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_否.getValue())
        		&&StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
        	finProductType.setType6(DictCodeListExt2.股票核算分类_股票_深圳股票.getValue());
        }
        else if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())
        		&&StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())){
        	finProductType.setType6(DictCodeListExt2.股票核算分类_股票_沪港通.getValue());
        }
        else if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_深港通.getValue())
        		&&StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
        	finProductType.setType6(DictCodeListExt2.股票核算分类_股票_深港通.getValue());
        }
       finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
        // 金融产品扩展表 资产提供行入该表
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, dto);
        //附件信息
        if(null!=fileCommonService){
            if(null!=dto.getFileCommonList()){
                FileCommonDto fileCommonDto = assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList());
                dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter());
            }
        }
        // 附件信息
        /*
         * if(null!=fileCommonService){ if(null!=dto.getFileCommonList()){ FileCommonDto fileCommonDto =
         * assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList()); dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter()); } }
         */
        // 权益类资产中债报送
        if (null != bussRepEquityInfoService) {
//            if (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())
//                || StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())) {
                if (null != dto.getBussRepEquityInfoDto()) {
                    dto.getBussRepEquityInfoDto().setRepSharesId(dto.getFinprodMarketId());
                    BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto = assetCommonService.encapBaseRepAssetEquityInfoDto(
                        finProduct, dto.getBussRepEquityInfoDto());
                    // 负债
                    BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = assetCommonService.encapbaseRepAssetDeptInfoDto(finProduct,
                        DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue(), dto.getTradeMarket());
                    dto.setBaseRepAssetEquityInfoDto(baseRepAssetEquityInfoDto);
                    dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
                }
//            }
//            else {
//                dto.setBaseRepAssetEquityInfoDto(null);
//            }
        }
//        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
//        dto.setFinprodMarketId(dto.getFinprodMarketId()+"."+dto.getTradeMarketId());}
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
//        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
   //     dto.setPaFinProduct(paFinProduct);
        return dto;
    }

    /**
     * 查询接口表资产信息
     * @param finprodMarketId 市场代码
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public FinStockInfoDto getInterfFundDto (String finprodMarketId) throws ParseException{
        InfChinamutualfunddescriptionDto dto = new InfChinamutualfunddescriptionDto();
        List<String> list = new ArrayList<>();
        list.add(finprodMarketId);
        dto.setfInfoWindcodeList(list);
        dto = commonAutoService.autoFundCoupon(dto, finprodMarketId);
        com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto baseInfoDto  = new com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto();
        if (StringUtil.isNotEmpty(dto)) {
            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
        }
        //基金基本信息
        FinStockInfoDto finStockInfoDto = new FinStockInfoDto();
        if (!StringUtil.isNotEmpty(baseInfoDto.getFinProduct())) {
            return null;
        }
        BeanUtil.copyPropertiesIgnoreNull(finStockInfoDto, baseInfoDto.getFinProduct());
        return finStockInfoDto;
    }


    /**
     * 资产代码判重复查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto findEntity(Map<String,Object> map) {
        return
            restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/findEntity"
                ,map,FinStockInfoDto.class);
    }
    /**
     * 存款机构
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCustomerName(String param) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getCustomerName?param=" + param,
                SuggestDto.class);
    }

    /**
     * 获取当前资产是否有用户编辑 如果有 返回用户
     * @param param
     * @param 
     * @return
     * @see
     * @since 1.0
     */
    public String getIsInWorkFlowReturnUser(Map<String,Object> param){
        return restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinStockInfo/getIsInWorkFlowReturnUser"
                ,param,String.class);
    }
}
