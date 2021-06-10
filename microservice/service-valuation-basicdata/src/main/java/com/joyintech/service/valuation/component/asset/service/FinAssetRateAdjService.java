/*
 * 文件名：FinPayInterestStdInfoService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月8日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.asset.service;

import com.joyintech.service.valuation.component.asset.dao.FinAssetRateAdjMapper;
import com.joyintech.service.valuation.component.asset.dto.AssetCommonInfoDto;
import com.joyintech.service.valuation.component.asset.dto.FinAssetRateAdjDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPayRuleDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 利率调整列表对应的业务类
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see FinAssetRateAdjService
 * @since 1.0
 */
@Service(value="serviceFinAssetRateAdjService")
public class FinAssetRateAdjService {


    @Autowired
    private FinAssetRateAdjMapper finAssetRateAdjMapper;

    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    /**
     * 计算规则
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

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
    public List<FinCashCalcRule> getFinAssetRateAdjByCashId(String finprodId, String finprodType, String cashId, String cashType, String isInitial){
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


    /**
     * 查询利率信息
     * @param param
     * @return
     */
    public List<FinAssetRateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> param){
        param.put("FINPROD_TYPE_F28", DictCodeListExt1.金融产品类型_通道.getValue());
        return finAssetRateAdjMapper.getfinAssetRateAdjByProductId(param);
    }

    /**
     * 重新查询利率信息
     * @param param
     * @return
     */
    public List<FinAssetRateAdjDto> selectInitAssetRateAdj(Map<String, Object> param){
        return finAssetRateAdjMapper.queryForPage(param);
    }

    /**
     * 利率调整(取资产的利率类型、息票品种)
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public AssetCommonInfoDto getAssetInfoToRateAdj(Map<String, Object> param){
        return finAssetRateAdjMapper.getAssetInfoToRateAdj(param);
    }

    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return finAssetRateAdjMapper.getMdateByFinproId(finprodId);
    }

    /**
     * 新增入库
     * @param dto
     */
    public void insert(FinAssetRateAdjDto dto){
        //根据cashId删除计算规则、计算规则扩展
        FinCashCalcRuleKey finCashCalcRuleKey = new FinCashCalcRuleKey();
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
    }

    /**
     * GET资产公共信息
     * @param param
     * @return
     */
    public AssetCommonInfoDto getAssetBasicInfoByFinprodId(Map<String, Object> param){
        return finAssetRateAdjMapper.getAssetBasicInfoByFinprodId(param);
    }


    /**
     * GET现金流计划
     */
    public List<FinCashPlanDto> getFinCashPlanByProductId(Map<String, Object> param){
        return finAssetRateAdjMapper.getFinCashPlanByProductId(param);
    }

    /**
     * GET资产公共信息
     * @param param
     * @return
     */
    public FinCashPayRuleDto getFinPayInterestStdInfoByProductId(Map<String, Object> param){
        return finAssetRateAdjMapper.getFinPayInterestStdInfoByProductId(param);
    }

    /**
     * GET现金流计划
     */
    public List<String> getDealCalendarHoliday(Map<String, Object> param){
        return finAssetRateAdjMapper.getDealCalendarHoliday(param);
    }
}
