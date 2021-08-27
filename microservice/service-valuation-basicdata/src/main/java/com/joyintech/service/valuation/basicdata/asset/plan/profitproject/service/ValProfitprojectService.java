/*
 * 文件名：FinPlanAssetCService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.plan.profitproject.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.plan.profitproject.dao.ValProfitprojecMapper;
import com.joyintech.service.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import com.joyintech.service.valuation.basicdata.base.service.BaseFinProductBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月7日
 * @see ValProfitprojectService
 * @since 1.0
 */
@Service(value="serviceValProfitprojectService")
public class ValProfitprojectService extends BaseService<ValProfitprojectDto> {
    @Autowired
    private ValProfitprojecMapper valProfitprojecMapper;

    //标的类金融产品基本信息组件
    @Autowired(required=false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;



    /**
     *
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<ValProfitprojectDto> selectForPage(int page, int length,Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page, length, () -> valProfitprojecMapper.selectForPage(pageParam));
    }

    public List<ValProfitprojectDto> selectForList(Map<String, Object> pageParam) throws Exception {
        return valProfitprojecMapper.selectForPage(pageParam);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(ValProfitprojectDto dto) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(dto.getFinprodId());
        if(baseFinProductBaseInfoService.delete(finprodIdList)){
            return 1;
        }
        return 0;
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
    @SuppressWarnings("unchecked")
    public int insert(ValProfitprojectDto dto) throws IllegalAccessException, InvocationTargetException {
//        Map<String,Object> param = getDto(dto);
//        //定义标的类金融产品dto
//        BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();
//
//        entity.setFinProduct((FinProduct)param.get("finProduct"));
//        entity.setFinProductAdd((FinProductAdd)param.get("finProductAdd"));
//        entity.setFinProductType((FinProductType)param.get("finProductType"));
//        entity.setFinCashInfoList((List<FinCashInfo>)param.get("finCashInfoList"));
//        if(param.get("finCashPayRuleList") != null){
//            entity.setFinCashPayRuleList((List<FinCashPayRule>)param.get("finCashPayRuleList"));
//        }
//        if(param.get("finCashCalcRuleList") != null){
//            entity.setFinCashCalcRuleList((List<FinCashCalcRule>)param.get("finCashCalcRuleList"));
//        }
//        if(param.get("finRateInfoList") != null){
//            entity.setFinRateInfoList((List<FinRateInfo>)param.get("finRateInfoList"));
//        }
//        // 增加推荐方与推荐费率 add by renping 20191014 start
//        if(param.get("finProductRecommentList") != null){
//            entity.setFinProductRecommentList((List<FinProductRecomment>)param.get("finProductRecommentList"));
//        }
//        // 增加推荐方与推荐费率 add by renping 20191014 end
//        //新建 标的类金融产品基本信息dto list
//        List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
//        list.add(entity);
//        baseFinProductBaseInfoService.insert(list);
        baseFinProductBaseInfoService.insert(dto.getBaseFinProductBaseInfoList());
        return 1;
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
    @SuppressWarnings("unchecked")
    public int update(ValProfitprojectDto dto) throws IllegalAccessException, InvocationTargetException {
//        Map<String,Object> param = getDto(dto);
//        List<String> finprodIdList = (List<String>)param.get("finprodIdList");
//        //定义标的类金融产品dto
//        BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();
//
//        entity.setFinProduct((FinProduct)param.get("finProduct"));
//        entity.setFinProductAdd((FinProductAdd)param.get("finProductAdd"));
//        entity.setFinProductType((FinProductType)param.get("finProductType"));
//        entity.setFinCashInfoList((List<FinCashInfo>)param.get("finCashInfoList"));
//        if(param.get("finCashPayRuleList") != null){
//            entity.setFinCashPayRuleList((List<FinCashPayRule>)param.get("finCashPayRuleList"));
//        }
//        if(param.get("finCashCalcRuleList") != null){
//            entity.setFinCashCalcRuleList((List<FinCashCalcRule>)param.get("finCashCalcRuleList"));
//        }
//        if(param.get("finRateInfoList") != null){
//            entity.setFinRateInfoList((List<FinRateInfo>)param.get("finRateInfoList"));
//        }
//        // 增加推荐方与推荐费率 add by renping 20191014 start
//        if(param.get("finProductRecommentList") != null){
//            entity.setFinProductRecommentList((List<FinProductRecomment>)param.get("finProductRecommentList"));
//        }
//        // 增加推荐方与推荐费率 add by renping 20191014 end
//        //新建 标的类金融产品基本信息dto list
//        List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
//        list.add(entity);
//        baseFinProductBaseInfoService.update(finprodIdList,list);

        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(dto.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, dto.getBaseFinProductBaseInfoList());
        return 1;
    }
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param dto
//     * @return
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     * @see
//     * @since 1.0
//     */
//    private Map<String, Object> getDto(ValProfitprojectDto dto) throws IllegalAccessException, InvocationTargetException {
//        Map<String,Object> param = new HashMap<String,Object>();
//        //标的类金融产品dto
//        FinProduct finProduct = new FinProduct();
//        if(!StringUtil.isNotEmpty(dto.getFinprodType())){
//            dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
//        }
//        BeanUtil.copyPropertiesIgnoreNull(finProduct,dto);
//        finProduct.setFinprodId(dto.getFinprodId());
//        if (dto.getMdate() != null) {
//            finProduct.setTermDays((dto.getMdate().getTime()-dto.getVdate().getTime())/1000/60/60/24);
//        }
//        finProduct.setBlnArea(dto.getBlnArea());
//        finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
//        //标的类金融产品附属表dto
//        FinProductAdd finProductAdd = new FinProductAdd();
//        finProductAdd.setFinprodId(finProduct.getFinprodId());
//        finProductAdd.setInvestNature(dto.getInvestNature());
//        finProductAdd.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());
//        /*  update by zhuyongkang 2019/09/29 start*/
//        if (StringUtil.isNotEmpty(dto.getPurSpeed())) {
//            finProductAdd.setPurSpeed(Long.parseLong(dto.getPurSpeed()));
//        }
//        if (StringUtil.isNotEmpty(dto.getRedSpeed())) {
//            finProductAdd.setRedSpeed(Long.parseLong(dto.getRedSpeed()));
//        }
//        /*  update by zhuyongkang 2019/09/29 END*/
//        finProductAdd.setIsCompoundInt(dto.getIsCompoundInt());
//        if (dto.getBussFinIntPayDto() != null){
//            finProductAdd.setIsCompoundInt(dto.getBussFinIntPayDto().getIsCompoundInt());
//        }
//        //金融产品分类表dto
//        FinProductType finProductType = new FinProductType();
//        finProductType.setFinprodId(finProduct.getFinprodId());
//        finProductType.setType1(dto.getAssetType());
//        finProductType.setType2(dto.getAccountintType());
//        finProductType.setType3(dto.getTaxType());
//        finProductType.setType18(dto.getType18());
//        finProductType.setBranch(dto.getBranch());
//        finProductType.setType6(dto.getCategory());
//        finProductType.setType7(dto.getType());
//        /*添加五级分类 lishuping 2019/7/23 start*/
//        finProductType.setType9(dto.getType9());
//        finProductType.setType10(dto.getType10());
//        finProductType.setType11(dto.getType11());
//        finProductType.setType12(dto.getType12());
//        /*添加五级分类 lishuping 2019/7/23 end*/
//        finProductType.setType8(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
//        //现金流信息List
//        List<FinCashInfo> finCashInfoList = new ArrayList<>();
//        //现金流计算规则dtolist
//        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<>();
//        //现金流信息-本金
//        FinCashInfo finCashInfo1 = new FinCashInfo();
//        finCashInfo1.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
//        finCashInfo1.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
//        finCashInfo1.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
//        finCashInfo1.setBranch(dto.getBranch());
//        finCashInfo1.setFinprodId(finProduct.getFinprodId());
//        finCashInfo1.setCcy(finProduct.getCcy());
//        finCashInfo1.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfo1.getFinprodType()
//                +(char)256+finCashInfo1.getBranch()+(char)256+finCashInfo1.getCashType2()));
//        //现金流信息-利息
//        FinCashInfo finCashInfo2 = new FinCashInfo();
//        BeanUtil.copyPropertiesIgnoreNull(finCashInfo2,finCashInfo1);
//        finCashInfo2.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
//        finCashInfo2.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
//        finCashInfo1.setCashId(EncryptUtil.getInstance().md5(dto.getFinprodId()+(char)256+finCashInfo2.getFinprodType()
//                +(char)256+finCashInfo2.getBranch()+(char)256+finCashInfo2.getCashType2()));
//        finCashInfoList.add(finCashInfo1);
//        finCashInfoList.add(finCashInfo2);
//        if(null!=finCashCalcRuleList&&!finCashCalcRuleList.isEmpty()){
//            //现金流利率信息表dtolist
//            List<FinRateInfo> finRateInfoList = new ArrayList<>();
//            //托管费
//            FinRateInfo finRateInfo1 = new FinRateInfo();
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo1,finCashCalcRuleList.get(0));
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo1,finProduct);
//            finRateInfo1.setRate(finCashCalcRuleList.get(0).getYield());
//            finRateInfo1.setEffDate(dto.getVdate());
////            finRateInfo1.setRateType(DictCodeList.利率类型_固定利率.getValue());
//            finRateInfo1.setBranch(dto.getBranch());
//            //管理费
//            FinRateInfo finRateInfo2 = new FinRateInfo();
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo2,finRateInfo1);
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo2,finCashCalcRuleList.get(1));
//            finRateInfo2.setRate(finCashCalcRuleList.get(1).getYield());
//            //其它费
//            FinRateInfo finRateInfo3 = new FinRateInfo();
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo3,finRateInfo1);
//            BeanUtil.copyPropertiesIgnoreNull(finRateInfo3,finCashCalcRuleList.get(2));
//            finRateInfo3.setRate(finCashCalcRuleList.get(2).getYield());
//            finRateInfoList.add(finRateInfo1);
//            finRateInfoList.add(finRateInfo2);
//            finRateInfoList.add(finRateInfo3);
//            param.put("finRateInfoList", finRateInfoList);
//        }
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
//            param.put("finCashPayRuleList", finCashPayRuleList);
//        }
//        //获取要修改的金融产品代码集合(修改时用)
//        List<String> finprodIdList = new ArrayList<String>();
//        finprodIdList.add(finProduct.getFinprodId());
//
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
//        // 增加推荐方与推荐费率 add by renping 20191014 end
//        param.put("finProduct", finProduct);
//        param.put("finProductAdd", finProductAdd);
//        param.put("finProductType", finProductType);
//        param.put("finCashInfoList", finCashInfoList);
//        param.put("finCashCalcRuleList", finCashCalcRuleList);
//        param.put("finprodIdList", finprodIdList);
//        return param;
//    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public ValProfitprojectDto getBasicInfoByFinprodId(Map<String, Object> param) {
        List<ValProfitprojectDto> dtoList =valProfitprojecMapper.getBasicInfoByFinprodId(param);
        if (dtoList.size() > 0){
            return dtoList.get(0);
        }
        return new ValProfitprojectDto();
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
    public String getFinprodId(String finprodMarketId){
        return valProfitprojecMapper.getFinprodId(finprodMarketId);
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/

    /*增加资产代码重复校验 add by zhuyongkang 20191021 start*/
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 查询到的市场代码
     * @see
     * @since 1.0
     */
    public int selectFinprodMarketId(String finprodMarketId){
        return valProfitprojecMapper.selectFinprodMarketId(finprodMarketId);
    }
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/

    public Page<SelectOptionDto> getCustomer(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
                () -> valProfitprojecMapper.getCustomer(param));
    }

    /**
     * 资产下拉框
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getAssetSelect(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
                () -> valProfitprojecMapper.getAssetSelect(param));
    }

    /**
     * 资产模糊下拉框
     * @param param
     * @return
     */
    public List<SuggestDto> getAssetId(Map<String, Object> param){
        return valProfitprojecMapper.getAssetId(param);
    }

    public Integer isDealCalendarHoliday(Map<String, Object> param) {
        String dateStr = (String) param.get("dateStr");
        String calId = (String) param.get("calId");
        return valProfitprojecMapper.selectDealCalendarHolidayCount(dateStr,calId);
    }
}
