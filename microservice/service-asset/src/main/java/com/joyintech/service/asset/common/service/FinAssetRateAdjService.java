/*
 * 文件名：FinAssetRateAdjService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.dao.FinAssetRateAdjMapper;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.RateAdjDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 利率调整
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see FinAssetRateAdjService
 * @since 1.0
 */
@Service
public class FinAssetRateAdjService extends BaseService<RateAdjDto>{

    @Autowired
    private FinCashPlanMapper finCashPlanMapper;
    @Autowired
    private FinAssetRateAdjMapper finAssetRateAdjMapper;
    
    /**
     * 计算规则
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;
    
    /**
     * 计算规则扩展
     */
   /* @Autowired
    private PaFinCashCalcRuleMapper paFinCashCalcRuleMapper;*/
    
    public List<RateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> param){
        param.put("FINPROD_TYPE_TG_F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return finAssetRateAdjMapper.getfinAssetRateAdjByProductId(param);
    }
    
    public List<SelectOptionDto> queryFeeInfoSel(Map<String, Object> param){
    	param.put("FINPROD_TYPE_TG_F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
    	return finAssetRateAdjMapper.queryFeeInfoSel(param);
    }
    
    public Page<RateAdjDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finAssetRateAdjMapper.queryForPage(pageParam));
    }
    
    /**
     * 重新查询利率调整初始化信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<RateAdjDto> selectInitAssetRateAdj(@RequestBody Map<String, Object> param){
        return finAssetRateAdjMapper.queryForPage(param);
    }
    
    public void insert(RateAdjDto dto){
        //根据cashId删除计算规则、计算规则扩展
        FinCashCalcRuleKey finCashCalcRuleKey = new FinCashCalcRuleKey();
       /* PaFinCashCalcRuleKey paFinCashCalcRuleKey = new PaFinCashCalcRuleKey();*/
        /*paFinCashCalcRuleKey.setCashId(dto.getCashId());*/
       /* finAssetRateAdjMapper.deleteCashCalcRuleAddByCashId(paFinCashCalcRuleKey);*/
        //新增计算规则、计算规则扩展
        dto.getFinCashCalcRuleList().forEach(entity ->{
            finCashCalcRuleKey.setCashId(entity.getCashId());
            finAssetRateAdjMapper.deleteCashCalcRuleByCashId(finCashCalcRuleKey);
        });
        dto.getFinCashCalcRuleList().forEach(entity ->{
            finCashCalcRuleMapper.insert(entity);
        });
        //付息计划
        if(null!=dto.getFinCashPlanList()&&!dto.getFinCashPlanList().isEmpty()){
            dto.getFinCashPlanList().forEach(entity->{
                FinCashPlanKey key = new FinCashPlanKey();
                key.setCashId(entity.getCashId());
                key.setMdate(entity.getMdate());
                finCashPlanMapper.deleteByPrimaryKey(key);
            });
            dto.getFinCashPlanList().forEach(entity->{
                finCashPlanMapper.insert(entity);
            });
        }
        /*dto.getPaFinCashCalcRuleList().forEach(entity ->{
            paFinCashCalcRuleMapper.insert(entity);
        });*/
        
    }
    
    /**
     * 获取利率调整信息(报价--用于付息还本信息)
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @param finprodType
     * @param cashId
     * @param cashType
     * @param isInitial
     * @return
     * @see
     * @since 1.0
     */
    public List<FinCashCalcRule> getFinAssetRateAdjByCashId(String finprodId,String finprodType,String cashId,String cashType,String isInitial){
        return finAssetRateAdjMapper.getFinAssetRateAdjByCashId(finprodId,finprodType,cashId,cashType,isInitial);
    }
    
    /**
     * 获取利率行情信息
     * <功能详细描述>
     * @param indexId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinCashCalcRule> getMstRateInfo(String indexId){
        return finAssetRateAdjMapper.getMstRateInfo(indexId);
    }
    
    public AssetCommonInfoDto getAssetBasicInfoByFinprodId(Map<String,Object> map){
        return finAssetRateAdjMapper.getAssetBasicInfoByFinprodId(map);
    }
}
