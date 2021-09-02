/*
 * 文件名：FinStockInfoService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年9月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.asset.stock.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.stock.dto.FinStockInfoDto;
import com.joyintech.tams.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.business.dto.MstFileInputParameter;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.business.service.MstFileCommonService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    MstFileCommonService mstFileCommonService;

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
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/findPage",
            pageParam, FinStockInfoDto.class);
    }

    /**
     * 根据条件查询所有数据
     * @param pageParam
     * @return
     */
    public List<FinStockInfoDto> selectAllList(Map<String, Object> pageParam) {
        if (StringUtil.isNotEmpty((String) pageParam.get("vdate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)pageParam.get("vdate"));
            pageParam.put("vdatestart", dateList.get(0));
            pageParam.put("vdateend", dateList.get(2));
        }
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/selectAllList",
                pageParam, FinStockInfoDto.class);
    }

    public FinStockInfoDto getShares(Map<String,Object> map) {
        return
                restTemplate.postForObject(
                        ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/getShares"
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
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
                "/ValFinStockInfo/getFinprodMarketIdList?finprodMarketId="+ param,
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
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/selectByFinprodId?finprodId="
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
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/getBasicInfoByFinprodId", param,
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
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/getCalendarId?tradeMarket=" + tradeMarket,
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
            "/com/joyintech/tams/valuation/basicdata/asset/stock/conf/export_conf.xml").dataSource(
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
		    entity.setFinprodMarketId(entity.getFinprodMarketId());
		    entity.setFinprodId(commonBusinessService.getFinProdId(entity.getFinprodMarketId(), entity.getFinprodType(), null));
        }
        entity = encapBaseFinProductBaseInfoList(entity);
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
    		//新建股票资产时，自动创建交易对手 add by hxy 20200421 end
            action = "insert";
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getFinprodId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        } else {
            action = "update";
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getFinprodId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/" + action, entity, Integer.class);
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
        dto.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
        dto.setBlnArea(DictCodeList.境内外_境内.getValue());
        dto.setRegistOrg(DictCodeList.登记托管机构_中国证券登记结算有限责任公司.getValue());
        dto.setIsChl(DictCodeListExt2.是否_通道_否.getValue());

        // 分支序号
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
            CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE));
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
        if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())){
            finProductType.setType6(DictCodeListExt2.股票核算分类_股票_上海股票.getValue());
            finProductType.setType2(DictCodeListExt2.股票核算分类_股票_上海股票.getValue());
            finProductType.setType3(DictCodeListExt3.增值税资产分类_股票_上海股票.getValue());
            finProductType.setType1("01");
        }
        else if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
            finProductType.setType6(DictCodeListExt2.股票核算分类_股票_深圳股票.getValue());
            finProductType.setType2(DictCodeListExt2.股票核算分类_股票_深圳股票.getValue());
            finProductType.setType3(DictCodeListExt3.增值税资产分类_股票_深圳股票.getValue());
            finProductType.setType1("01");
        }
        else if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())){
            finProductType.setType6(DictCodeListExt2.股票核算分类_股票_沪港通.getValue());
            finProductType.setType2(DictCodeListExt2.股票核算分类_股票_沪港通.getValue());
            finProductType.setType3(DictCodeListExt3.增值税资产分类_股票_沪港通.getValue());
            finProductType.setType1("02");
        }
        else if(StringUtil.equals(dto.getShOrZh(), DictCodeListExt2.是否沪港通或深港通_深港通.getValue())){
            finProductType.setType6(DictCodeListExt2.股票核算分类_股票_深港通.getValue());
            finProductType.setType2(DictCodeListExt2.股票核算分类_股票_深港通.getValue());
            finProductType.setType3(DictCodeListExt3.增值税资产分类_股票_深港通.getValue());
            finProductType.setType1("02");
        }
        finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
        finProductType.setType18(DictCodeListExt1.证券品种_股票.getValue());
        // 金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
        // 金融产品扩展表 资产提供行入该表
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, dto);
//        //附件信息
//        if(null!=fileCommonService){
//            if(null!=dto.getFileCommonList()){
//                FileCommonDto fileCommonDto = assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList());
//                dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter());
//            }
//        }
        // 附件信息
        /*
         * if(null!=fileCommonService){ if(null!=dto.getFileCommonList()){ FileCommonDto fileCommonDto =
         * assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList()); dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter()); } }
         */

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

//    /**
//     * 查询接口表资产信息
//     * @param finprodMarketId 市场代码
//     * @return
//     * @throws ParseException
//     * @see
//     * @since 1.0
//     */
//    public FinStockInfoDto getInterfFundDto (String finprodMarketId) throws ParseException{
//        InfChinamutualfunddescriptionDto dto = new InfChinamutualfunddescriptionDto();
//        List<String> list = new ArrayList<>();
//        list.add(finprodMarketId);
//        dto.setfInfoWindcodeList(list);
//        dto = commonAutoService.autoFundCoupon(dto, finprodMarketId);
//        com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto baseInfoDto  = new com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto();
//        if (StringUtil.isNotEmpty(dto)) {
//            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
//        }
//        //基金基本信息
//        FinStockInfoDto finStockInfoDto = new FinStockInfoDto();
//        if (!StringUtil.isNotEmpty(baseInfoDto.getFinProduct())) {
//            return null;
//        }
//        BeanUtil.copyPropertiesIgnoreNull(finStockInfoDto, baseInfoDto.getFinProduct());
//        return finStockInfoDto;
//    }


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
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/findEntity"
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
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/getCustomerName?param=" + param,
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
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValFinStockInfo/getIsInWorkFlowReturnUser",param,String.class);
    }

    /**
     * 生成金融产品代码/市场代码
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto getFinprodId(FinStockInfoDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService != null ?
                commonBusinessService.getFinProdId(finprodMarketId, DictCodeListExt1.金融产品类型_股票.getValue(), dto.getChlFinprodId()) : IDGenerator.generate();
        dto.setFinprodId(finprodId);
        return dto;
    }
}
