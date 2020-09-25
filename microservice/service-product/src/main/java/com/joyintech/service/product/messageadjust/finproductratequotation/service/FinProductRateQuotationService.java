/*
 * 文件名：FinProductRateQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductratequotation.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.finproductratequotation.dao.FinProductRateQuotationMapper;
import com.joyintech.service.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.component.base.core.BaseFinProductCashCalcRuleService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashCalcRuleDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 信息调整管理_产品费率报价管理Service
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotationService
 * @since 1.0
 */
@Service("backFinProductRateQuotationService")
public class FinProductRateQuotationService extends BaseService<FinProductRateQuotationDto>{
    
    /**
     * 现金流计算规则
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;
    
    /**
     * 现金流计算规则组件
     */
    @Autowired(required=false)
    private BaseFinProductCashCalcRuleService baseFinProductCashCalcRuleService;
    
    /**
     * 产品费率报价管理mapper
     */
    @Autowired
    private FinProductRateQuotationMapper finProductRateQuotationMapper;
    
    /**
     * 产品代码联想框
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> param) {
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        List<String> list = new ArrayList<String>();
        list.add(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        list.add(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        param.put("list", list);
        return finProductRateQuotationMapper.getFinprodId(param);
    }
    
    /**
     * 
     * 产品费率报价管理-查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<FinProductRateQuotationDto> selectForPage(int page, int length,Map<String, Object> pageParam)
        throws Exception {
            //得到产品代码
            String finprodId = (String)pageParam.get("finprodId");
            if(StringUtil.isNotEmpty(finprodId)){
                pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
            }
            List<String> listFinprodType2 = new ArrayList<String>();
            listFinprodType2.add(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            listFinprodType2.add(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
            pageParam.put("listFinprodType2", listFinprodType2);
            List<String> listCashType2 = new ArrayList<String>();
            listCashType2.add(DictCodeListExt1.现金流二级类型_托管费.getValue());
            listCashType2.add(DictCodeListExt1.现金流二级类型_销售费.getValue());
            listCashType2.add(DictCodeListExt1.现金流二级类型_固定管理费.getValue());
            listCashType2.add(DictCodeListExt2.现金流二级类型_投资顾问费.getValue());
            listCashType2.add(DictCodeListExt1.现金流二级类型_外包服务费.getValue());
            listCashType2.add(DictCodeListExt1.现金流二级类型_代销费.getValue());
            pageParam.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
            pageParam.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
            pageParam.put("listCashType2", listCashType2);
            pageParam.put("releaseDateStart", DateUtil.parse((String)pageParam.get("releaseDateStart")));
            pageParam.put("releaseDateEnd", DateUtil.parse((String)pageParam.get("releaseDateEnd")));
            pageParam.put("effDateStart", DateUtil.parse((String)pageParam.get("effDateStart")));
            pageParam.put("effDateEnd", DateUtil.parse((String)pageParam.get("effDateEnd")));
            return super.queryForPage(page, length, () -> finProductRateQuotationMapper.selectForPage(pageParam));
    }
    
    /**
     * 实现docomplement方法
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public void doComplete(FinProductRateQuotationDto entity) throws IllegalAccessException, InvocationTargetException {
        String type = entity.getOperateType();
        //删除
        if (StringUtil.equals(type, "DELETE")) {
            if(baseFinProductCashCalcRuleService != null){
                BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                //设置现金流代码
                baseFinProductCashCalcRuleDto.setCashId(entity.getCashId());
                //设置生效日期
                baseFinProductCashCalcRuleDto.setEffDate(entity.getEffDate());
                baseFinProductCashCalcRuleService.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
            }
        }//新建
        else if (StringUtil.equals(type, "NEW")) {
            if(baseFinProductCashCalcRuleService != null){
                
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("finprodId", entity.getFinprodId());
                FinProductRateQuotationDto dto = finProductRateQuotationMapper.getVdateAndMdate(param);
               
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                for(FinProductRateQuotationDto temp:entity.getSelectedItem()){
                    //查询原数据
                    FinCashCalcRule key = new FinCashCalcRule();
                    key.setCashId(temp.getCashIdStr());
                    key.setEffDate(dto.getVdate());
                    key = finCashCalcRuleMapper.selectByPrimaryKey(key);
                    //设置页面所得数据
                    FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                    //BeanUtil.copyProperties(finCashCalcRule, temp);
                    finCashCalcRule.setCashId(temp.getCashIdStr());
                    finCashCalcRule.setFinprodId(temp.getFinprodId());
                    // 一体化对应etliu
//                    finCashCalcRule.setRelaeseDate(entity.getRelaeseDate());
                    finCashCalcRule.setEffDate(entity.getEffDate());
                    finCashCalcRule.setYield(temp.getYield().divide(new BigDecimal(100)));
                    finCashCalcRule.setRemark(entity.getRemark());
                    //是否初始利率                                  数据字典.是否.否
                    finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
                    if(key != null){
                      
                        //金融产品类型（估值核算）                                    初始利率所对应的金融产品类型（估值核算）
                        finCashCalcRule.setFinprodType(key.getFinprodType());
                        //金融产品类型（投管分类）                                    初始利率所对应的金融产品类型（投管分类）
                        finCashCalcRule.setFinprodType2(key.getFinprodType2());
                        //分支序号                                    初始利率所对应的分支序号  
                        if(key.getBranch() != null){
                            finCashCalcRule.setBranch(key.getBranch()); 
                        }
                        else{
                            finCashCalcRule.setBranch((long)0); 
                        }
                        //计算类型                                    初始利率所对应的计算类型
                        finCashCalcRule.setCalcType(key.getCalcType());
                        //基数类型                                    初始利率所对应的基数类型 
                        finCashCalcRule.setBaseType(key.getBaseType());
                        //基数日期类型                                  初始利率所对应的基数日期类型 
                        finCashCalcRule.setBaseDateType(key.getBaseDateType());
                        //计算基础                                    初始利率所对应的计算基础
                        finCashCalcRule.setBasis(key.getBasis()); 
                    }
                    else{
                        finCashCalcRule.setBranch((long)0); 
                    }
                    finCashCalcRuleList.add(finCashCalcRule);
                }
                baseFinProductCashCalcRuleService.insert(finCashCalcRuleList);
            }
        }//修改
        else{
            if(baseFinProductCashCalcRuleService != null){
                
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("finprodId", entity.getFinprodId());
                FinProductRateQuotationDto dto = finProductRateQuotationMapper.getVdateAndMdate(param);
                
                BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                for(FinProductRateQuotationDto temp:entity.getSelectedItem()){
                    //查询原数据
                    FinCashCalcRule key = new FinCashCalcRule();
                    key.setCashId(temp.getCashIdStr());
                    key.setEffDate(dto.getVdate());
                    key = finCashCalcRuleMapper.selectByPrimaryKey(key);
                    //设置页面所得数据
                    FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                    //BeanUtil.copyProperties(finCashCalcRule, temp);
                    finCashCalcRule.setCashId(temp.getCashIdStr());
                    finCashCalcRule.setFinprodId(temp.getFinprodId());
                    // 一体化对应etliu
//                    finCashCalcRule.setRelaeseDate(entity.getRelaeseDate());
                    finCashCalcRule.setEffDate(entity.getEffDate());
                    finCashCalcRule.setYield(temp.getYield().divide(new BigDecimal(100)));
                    finCashCalcRule.setRemark(entity.getRemark());
                    //是否初始利率                                  数据字典.是否.否
                    finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
                    if(key != null){
                        //金融产品类型（估值核算）                                    初始利率所对应的金融产品类型（估值核算）
                        finCashCalcRule.setFinprodType(key.getFinprodType());
                        //金融产品类型（投管分类）                                    初始利率所对应的金融产品类型（投管分类）
                        finCashCalcRule.setFinprodType2(key.getFinprodType2());
                        //分支序号                                    初始利率所对应的分支序号 
                        if(key.getBranch() == null){
                            finCashCalcRule.setBranch(key.getBranch()); 
                        }
                        else{
                            finCashCalcRule.setBranch((long)0); 
                        }
                        //计算类型                                    初始利率所对应的计算类型
                        finCashCalcRule.setCalcType(key.getCalcType());
                        //基数类型                                    初始利率所对应的基数类型 
                        finCashCalcRule.setBaseType(key.getBaseType());
                        //基数日期类型                                  初始利率所对应的基数日期类型 
                        finCashCalcRule.setBaseDateType(key.getBaseDateType());
                        //计算基础                                    初始利率所对应的计算基础
                        finCashCalcRule.setBasis(key.getBasis()); 
                    }
                    else{
                        finCashCalcRule.setBranch((long)0); 
                    }
                    finCashCalcRuleList.add(finCashCalcRule);
                    baseFinProductCashCalcRuleDto.setCashId(temp.getCashId());
                    baseFinProductCashCalcRuleDto.setEffDate(entity.getEffDateOld());
                    baseFinProductCashCalcRuleDto.setFinCashCalcRuleList(finCashCalcRuleList);
                    baseFinProductCashCalcRuleService.updateBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto); 
                }
            }
        }
    }
    
    /**
     * 
     * 新建页面产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectFinprodId(Map<String, Object> param){
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        param.put("args0", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//        param.put("args1", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        param.put("profitType_02", DictCodeList.收益类型_收益型.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("dealMode_10", DictCodeList.处理模式_周期型.getValue());
        return finProductRateQuotationMapper.getSelectFinprodId(param);
    }
    /**
     * 
     * 新建页面产品代码下联动适用款型拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectProdStyleByFinprodId(Map<String, Object> param){
        param.put("cashType1_03", DictCodeListExt1.现金流一级类型_费用.getValue());
        param.put("pfinprodId", DictCodeListExt1.金融产品类型_理财产品.getValue() + "_"+ param.get("finprodMarketId"));
        return finProductRateQuotationMapper.getSelectProdStyleByFinprodId(param);
    }
    
    /**
     * 
     * 新建页面画面联动
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<FinProductRateQuotationDto> selectOther(int page, int length,Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        pageParam.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
        List<String> listCashType2 = new ArrayList<String>();
        listCashType2.add(DictCodeListExt1.现金流二级类型_托管费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_销售费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        listCashType2.add(DictCodeListExt2.现金流二级类型_投资顾问费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_代销费.getValue());
        pageParam.put("listCashType2", listCashType2);
            return super.queryForPage(page, length, () -> finProductRateQuotationMapper.selectOther(pageParam));
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public FinProductRateQuotationDto selectDetail(Map<String, Object> param){
        param.put("effDate", DateUtil.parse((String)param.get("effDate")));
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        List<String> listCashType2 = new ArrayList<String>();
        listCashType2.add(DictCodeListExt1.现金流二级类型_托管费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_销售费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        listCashType2.add(DictCodeListExt2.现金流二级类型_投资顾问费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_代销费.getValue());
        param.put("listCashType2", listCashType2);
        return finProductRateQuotationMapper.selectDetail(param);
    }
    
    /**
     * 
     * 新建check
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public List<FinProductRateQuotationDto> selectAlreadyHava(Map<String, Object> param){
        param.put("effDate", DateUtil.parse((String)param.get("effDate")));
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        List<String> listCashType2 = new ArrayList<String>();
        listCashType2.add(DictCodeListExt1.现金流二级类型_托管费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_销售费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        listCashType2.add(DictCodeListExt2.现金流二级类型_投资顾问费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        listCashType2.add(DictCodeListExt1.现金流二级类型_代销费.getValue());
        param.put("listCashType2", listCashType2);
        return finProductRateQuotationMapper.selectAlreadyHava(param);
    }
    
    /**
     * 
     * 根据金融产品代码拿到起息日、到期日
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public FinProductRateQuotationDto getVdateAndMdate(Map<String, Object> param){
        return finProductRateQuotationMapper.getVdateAndMdate(param);
    }
}
