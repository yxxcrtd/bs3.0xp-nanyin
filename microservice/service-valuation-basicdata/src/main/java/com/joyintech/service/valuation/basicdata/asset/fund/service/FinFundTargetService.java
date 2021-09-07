/*
 * 文件名：FinFundTargetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.fund.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.service.valuation.basicdata.asset.fund.dao.FinFundTargetMapper;
import com.joyintech.service.valuation.basicdata.asset.fund.dto.BussFinIntPayDto;
import com.joyintech.service.valuation.basicdata.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.service.valuation.basicdata.asset.fund.dto.FinFundTargetDto;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.service.valuation.basicdata.base.service.BaseFinProductBaseInfoService;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金类标的信息的微服务service
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月10日
 * @see FinFundTargetService
 * @since 1.0
 */
@Service(value="serviceFinFundTargetService")
public class FinFundTargetService extends BaseService<FinFundTargetDto> {
    @Autowired
    private FinFundTargetMapper finFundTargetMapper;
    //标的类金融产品基本信息组件
    @Autowired(required=false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
  //标的共通service
    @Autowired(required=false)
    private ValAssetCommonService assetCommonService;
    @Autowired(required=false)
    private PaFinProductMapper paFinProductMapper;
    /**
     * 组合表mapper
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    /**
     * 查询是否自动创建组合AssetTypeConfigMapper
     */
    @Autowired
    private AssetTypeConfigMapper assetTypeConfigMapper;

    @Autowired
    private RepAssetOtherMapper repAssetOtherMapper;
    /* add by zhangpuyu 2019/10/24 start */
    /**
     * 投后信息
     */
    @Autowired
    private FinPostInvestInfoMapper finPostInvestInfoMapper;
    /* add by zhangpuyu 2019/10/24 start */
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<FinFundTargetDto> selectForPage(int page, int length, Map<String, Object> param) 
        throws Exception {
        param.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("FIN_CASH_BUSINESS_TYPE", DictCodeListExt1.金融产品类型_基金.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_基金.getValue());
        return super.queryForPage(page, length, () -> finFundTargetMapper.selectForPage(param));
    }

    public List<FinFundTargetDto> selectForList(Map<String, Object> param)
            throws Exception {
        param.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("FIN_CASH_BUSINESS_TYPE", DictCodeListExt1.金融产品类型_基金.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_基金.getValue());
        return finFundTargetMapper.selectForPage(param);
    }

    
    /**
     * 查询基金管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getIssuer() {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        return finFundTargetMapper.getIssuer(param);
    }

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        param.put("finProdMarketId", finprodMarketId);
        return finFundTargetMapper.getFinProdMarketIdList(param);
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public int insert(FinFundTargetDto dto) throws IllegalAccessException, InvocationTargetException {
        Map<String,Object> param = getDto(dto);
        //定义标的类金融产品dto
        BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();

        entity.setFinProduct((FinProduct)param.get("finProduct"));
        entity.setFinProductAdd((FinProductAdd)param.get("finProductAdd"));
        entity.setFinProductType((FinProductType)param.get("finProductType"));
        
        entity.setFinCashInfoList((List<FinCashInfo>)param.get("finCashInfoList"));
        if(param.get("finCashPayRuleList") != null){
            entity.setFinCashPayRuleList((List<FinCashPayRule>)param.get("finCashPayRuleList"));
        }
        //新建 标的类金融产品基本信息dto list
        List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
        list.add(entity);
        baseFinProductBaseInfoService.insert(list);
        return 1;
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public int update(FinFundTargetDto dto) throws IllegalAccessException, InvocationTargetException {
        if (!StringUtil.isNotEmpty(dto.getFinprodType())){
            dto.setFinprodType("F06");    // 设置为基金类型
        }
        Map<String,Object> param = getDto(dto);
        List<String> finprodIdList = (List<String>)param.get("finprodIdList");
      //定义标的类金融产品dto
        BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();
        entity.setFinProduct((FinProduct)param.get("finProduct"));
        entity.setFinProductAdd((FinProductAdd)param.get("finProductAdd"));
        entity.setFinProductType((FinProductType)param.get("finProductType"));
        
        entity.setFinCashInfoList((List<FinCashInfo>)param.get("finCashInfoList"));
        entity.setFinCashPayRuleList((List<FinCashPayRule>)param.get("finCashPayRuleList"));
      //修改标的类金融产品基本信息dto list
        List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
        list.add(entity);
        baseFinProductBaseInfoService.update(finprodIdList, list);
        return 1;
    }
    /**
     * 流程结束后，构建数据结构
     * 将画面传入的数据分配到各dto中
     * @param dto
     * @see
     * @since 1.0
     */
    private Map<String,Object> getDto(FinFundTargetDto dto) throws IllegalAccessException, InvocationTargetException {
        Map<String,Object> param = new HashMap<String,Object>();
        //标的类金融产品dto
        FinProduct finProduct = new FinProduct();
        if(!StringUtil.isNotEmpty(dto.getFinprodType())){
            dto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        }
        BeanUtil.copyProperties(finProduct,dto);
        finProduct.setFinprodId(dto.getFinprodId());
//        finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
        if(StringUtil.equals(dto.getFundType(), DictCodeList.基金类资产类型_货币基金.getValue())){
            finProduct.setProfitType(DictCodeList.收益类型_货币型.getValue());
        }
        else if(StringUtil.equals(dto.getFundType(), DictCodeList.基金类资产类型_非货币基金.getValue())){
            finProduct.setProfitType(DictCodeList.收益类型_净值型.getValue());
            if(StringUtil.equals(dto.getSecondType(), DictCodeList.基金类资产二级类型_其他公募基金.getValue())){
                finProduct.setCapiIncomeFeature(null);
            }
        }
        //标的类金融产品附属表dto
        FinProductAdd finProductAdd = new FinProductAdd();
        //add by zhangnian  20191106 start
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        //add by zhangnian  20191106 end
        finProductAdd.setFinprodId(finProduct.getFinprodId());
        if (dto.getBussFinIntPayDto() != null){
            finProductAdd.setIsCompoundInt(dto.getBussFinIntPayDto().getIsCompoundInt());
        }
        // 接口接入的数据，修改完成后，数据来源应该还是不变 为‘资管系统’，不能变为手工.   1为编辑页面
        if (StringUtil.equals(dto.getPageModel(),"1") && StringUtil.equals(dto.getDataSource(),DictCodeListExt3.估值数据来源_资管系统.getValue())){
            finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_资管系统.getValue());
        }else {
            finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());
        }
        //申购确认速速
        finProductAdd.setPurSpeed(Long.parseLong(dto.getPurSpeed()));
        //赎回确认速度
        finProductAdd.setRedSpeed(Long.parseLong(dto.getRedSpeed()));
        if(StringUtil.equals(dto.getTradeMarket(),DictCodeListExt1.交易场所_OTC场外.getValue())){
            finProductAdd.setFaceValue(new BigDecimal(1));
        }
        // 投资性质
        finProductAdd.setInvestNature(dto.getInvestNature());
        //场外基金FIN_PRODUCT_ADD表中FACE_VALUE（面值）应存为1
        //金融产品分类表dto
        FinProductType finProductType = new FinProductType();
        finProductType.setFinprodId(finProduct.getFinprodId());
        long i = 0;
        finProductType.setBranch(i);
        finProductType.setType2(dto.getHsfl());
        finProductType.setType3(dto.getSwfl());
        finProductType.setType6(dto.getFundType());
        /** modify by yanya on 20210324 基金类型存储在type1**/
        finProductType.setType1(dto.getFundType());
        finProductType.setType7(dto.getSecondType());
        finProductType.setType8(DictCodeList.平安资产类型_基金.getValue());
        // 4 new types added by wangguiqi on 2019/7/22 -start
        finProductType.setType9(dto.getType9());
        finProductType.setType10(dto.getType10());
        finProductType.setType11(dto.getType11());
        finProductType.setType12(dto.getType12());
        //modify by yanya on 20210902 分类表增加证券品种type18字段入库
        finProductType.setType18(DictCodeListExt1.证券品种_基金.getValue());//证券品种
        // 4 new types added by wangguiqi on 2019/7/22 -start
        //现金流信息Entity-本金
        FinCashInfo finCashInfoCapital = new FinCashInfo();
        finCashInfoCapital.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfoCapital.getFinprodType()
        +(char)256+finCashInfoCapital.getBranch()+(char)256+finCashInfoCapital.getCashType2()));
        finCashInfoCapital.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
        finCashInfoCapital.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
        finCashInfoCapital.setCcy(finProduct.getCcy());
        finCashInfoCapital.setFinprodId(finProduct.getFinprodId());
//        finCashInfoCapital.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        finCashInfoCapital.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        finCashInfoCapital.setBranch(i);
       //现金流信息Entity-利息
        FinCashInfo finCashInfoInterest = new FinCashInfo();
        finCashInfoInterest.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfoCapital.getFinprodType()
        +(char)256+finCashInfoCapital.getBranch()+(char)256+finCashInfoCapital.getCashType2()));
        finCashInfoInterest.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        finCashInfoInterest.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        finCashInfoInterest.setCcy(finProduct.getCcy());
        finCashInfoInterest.setFinprodId(finProduct.getFinprodId());
//        finCashInfoInterest.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        finCashInfoInterest.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        finCashInfoInterest.setBranch(i);
        //List<现金流信息Entity>
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        finCashInfoList.add(finCashInfoCapital);
        finCashInfoList.add(finCashInfoInterest);
        //现金流支付规则表Entity/分红计划组件
        //分红计划组件dto
        
        if(dto.getBussFinIntPayDto() != null){
            FinCashPayRule finCashPayRule = new FinCashPayRule();
            BeanUtil.copyProperties(finCashPayRule,dto.getBussFinIntPayDto());
            finCashPayRule.setCashId(finCashInfoInterest.getCashId());
            finCashPayRule.setEffDate(dto.getVdate());
            finCashPayRule.setFinprodId(finProduct.getFinprodId());
            finCashPayRule.setFinprodType(finProduct.getFinprodType());
            finCashPayRule.setFinprodType2(finProduct.getFinprodType2());
            finCashPayRule.setBranch(i);
            if(DictCodeList.收益结转频率_按月结转.getValue().equals(dto.getBussFinIntPayDto().getPayCycle())){
                finCashPayRule.setDayType(DictCodeListExt1.日期类型_几号.getValue());
            }
            
            // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
            if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
//				finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
			}
            
            //List<现金流支付规则表Entity>/List<分红计划组件Entity>
            List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
            finCashPayRuleList.add(finCashPayRule);
            param.put("finCashPayRuleList", finCashPayRuleList);
        }
        
        //获取要修改的金融产品代码集合(修改时用)
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(finProduct.getFinprodId());
        param.put("finProduct", finProduct);
        param.put("finProductAdd", finProductAdd);
        param.put("finProductType", finProductType);
        param.put("finCashInfoList", finCashInfoList);
//        param.put("repFund", repFund);
//        param.put("repAssetDept", repAssetDept);
        param.put("finprodIdList", finprodIdList);
        return param;
    }




    /**
     * 调用组件删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(FinFundTargetDto dto) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(dto.getFinprodId());
      //删除委外组合 add wangpeng 2019.07.23
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
        	 ptlPortfolioMapper.deleteByPrimaryKey(dto.getFinprodMarketId());
        }
      //删除授信关联
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(dto.getFinprodId());
//        assetCommonService.deleteCredit(creditLines);
        if(baseFinProductBaseInfoService.delete(finprodIdList)){
            return 1;
        }
        /* add by zhangpuyu 2019/10/24 start */
        //投后信息
        if(null!=dto.getFinprodId()){
            finPostInvestInfoMapper.deleteByPrimaryKey(dto.getFinprodId());
        }
        /* add by zhangpuyu 2019/10/24 start */
        return 0;
    }


    /**
     * 根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto selectByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("FIN_CASH_BUSINESS_TYPE", DictCodeListExt1.金融产品类型_理财产品.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_基金.getValue());
        return finFundTargetMapper.selectByFinprodId(param);
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto findEntity(Map<String, Object> param) {
        param.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("FIN_CASH_BUSINESS_TYPE", DictCodeListExt1.金融产品类型_理财产品.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_基金.getValue());
        return finFundTargetMapper.findEntity(param);
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public BussFinIntPayDto getIntPayByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        // 基金类资产和计划类资产
        int index = finprodId.indexOf("_");
        String finprodType = StringUtil.EMPTY;
        if (index > 0){
            finprodType = finprodId.substring(0, index);
        }
        if (StringUtil.equals(finprodType,DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            param.put("FIN_CASH_BUSINESS_TYPE_01", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        }else {
            param.put("FIN_CASH_BUSINESS_TYPE_01", DictCodeListExt1.金融产品类型_基金.getValue());
        }
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        return finFundTargetMapper.getIntPayByFinprodId(param);
    }


    /**
     * 给基金中债报送页面赋值
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    public BussRepFundInfoDto getFundInfoByFinprodId(String finprodId) throws IllegalAccessException, InvocationTargetException {
        return null;
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity FinFundTargetDto
     * @see
     * @since 1.0
     */
    public void doInsert(FinFundTargetDto entity) {
      //文件
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        mstFileInputParameter.setFileList(entity.getFileArray());
//        mstFileInputParameter.setBussinessId(entity.getWorkflowId());
//        if(mstFileCommonService != null){
//            mstFileCommonService.updateBusinessId(mstFileInputParameter);
//        }
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public BussRepFundInfoDto getInfoByIssuer(Map<String, Object> pageParam) {
        return finFundTargetMapper.getInfoByIssuer(pageParam);
    }

    public List<SelectOptionDto> getTrmFundTypeList(Map<String, Object> param) {
        return finFundTargetMapper.getTrmFundTypeList(param);
    }

    /**
     * 通过上市流通代码查询Dto
     */
    public FinFundTargetDto selectBycirculationCode(String circulationCode) {
        Map<String,Object> param = new HashMap<String,Object>();
        if (StringUtil.isNotEmpty(circulationCode)){
            param.put("circulationCode", circulationCode);
        }else {
            param.put("circulationCode", "不存在");
        }
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_基金.getValue());
        return finFundTargetMapper.selectBycirculationCode(param);
    }

    /**
     * 查询交易日历信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getCalendarIdList(Map<String, Object> param) {
        return finFundTargetMapper.getCalendarIdList(param);
    }
}
