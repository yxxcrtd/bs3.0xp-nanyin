/*
 * 文件名：FinStageManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.entity.FinCashPayRuleKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaFinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.PaFinProductBuyoutRateMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.asset.common.dao.FinStageManagementMapper;
import com.joyintech.service.asset.common.dto.FinStageManagementDto;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.common.asset.dto.RateAdjDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 期次管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementService
 * @since 1.0
 */
@Service("backFinStageManagementService")
public class FinStageManagementService extends BaseService<RateAdjDto>{

   @Autowired
   private FinStageManagementMapper finStageManagementMapper;
   
   @Autowired
   private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
   
   @Autowired
   private PaFinProductBuyoutRateMapper paFinProductBuyoutRateMapper;
   
   @Autowired
   private PaFinCashCalcRuleMapper paFinCashCalcRuleMapper;

   
   public List<FinStageManagementDto> getfinStageManagementByProductId(Map<String,Object> map){
       return finStageManagementMapper.getfinStageManagementByProductId(map);
   }
   
   public Map<String,Object> getRates(Map<String,Object> map){
       return finStageManagementMapper.getRates(map);
   }
   
   public void insert(FinStageManagementDto dto){
       //全部删除
       deleteAll(dto);
       //构建标的类金融产品dto
       dto.getFinPlanProfitAssetList().forEach(item ->{
           BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
           baseFinProductBaseInfoDto.setFinProduct(item.getFinProduct());
           baseFinProductBaseInfoDto.setFinProductAdd(item.getFinProductAdd());
           //--add by 裴家俊  2019/03/29--------start-----------------
           baseFinProductBaseInfoDto.setFinProductType(item.getFinProductType());
           //--add by 裴家俊  2019/03/29--------end-----------------
           baseFinProductBaseInfoDto.setFinCashInfoList(item.getFinCashInfoList());
           baseFinProductBaseInfoDto.setFinCashCalcRuleList(item.getFinCashCalcRuleList());
           baseFinProductBaseInfoDto.setFinRateInfoList(item.getFinRateInfoList());
           baseFinProductBaseInfoDto.setFinCashPlanList(item.getFinCashPlanList());
           /**
            * add by xr for TISHKBS-718 start
            */
           baseFinProductBaseInfoDto.setFinCashPayRuleList(item.getFinCashPayRuleList());
           /**
            * add by xr for TISHKBS-718 end
            */
           List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
           list.add(baseFinProductBaseInfoDto); 
           //调用标的类金融产品基础组件进行新增
           baseFinProductBaseInfoService.insert(list);
           //金融产品买断利率(单独针对平安)
           if(null!=item.getPaFinProductBuyoutRate()){
               paFinProductBuyoutRateMapper.insert(item.getPaFinProductBuyoutRate());
           }
           //计算规则扩展(单独针对平安)
           if(null!=item.getPaFinCashCalcRule()){
               paFinCashCalcRuleMapper.insert(item.getPaFinCashCalcRule());
           }
           //wangyang1 2020年3月31日 资产投资性质字段未在FIN_PRODUCT_ADD里存值 start
           finStageManagementMapper.updateInvestNatureByPFinprodId(item.getFinProduct().getFinprodId());
           //wangyang1 2020年3月31日 end
       });
   }
   
   private void deleteAll(FinStageManagementDto dto){
       /**
        * add by xr for TISHKBS-718 start
        */
       //删除计算规则表
       finStageManagementMapper.deleteFinCashPayRule(dto.getFinprodId());
       /**
        * add by xr for TISHKBS-718 end
        */
       //删除标的附属表
       finStageManagementMapper.deleteFinProductAdd(dto.getFinprodId());
       //删除现金流计算规则扩展信息
       finStageManagementMapper.deletePaFinCashCalRule(dto.getFinprodId());
       //删除现金流计划
       finStageManagementMapper.deleteFinCashPlan(dto.getFinprodId());
       //删除现金流利率信息
       finStageManagementMapper.deleteFinRateInfo(dto.getFinprodId());
       //删除金融产品买断利率
       finStageManagementMapper.deletepaFinProductBuyoutRate(dto.getFinprodId());
       //删除现金流计算规则信息
       finStageManagementMapper.deleteFinCashCalRule(dto.getFinprodId());
       //删除现金流信息
       finStageManagementMapper.deleteFinCashInfo(dto.getFinprodId());
       //删除标的分类---add by 裴家俊  2019/03/29------start-----
       finStageManagementMapper.deleteFinProductType(dto.getFinprodId());
       //删除标的分类---add by 裴家俊  2019/03/29--------end---
       //删除标的信息
       finStageManagementMapper.deleteFinProduct(dto.getFinprodId());
   }

    /**
     * 校验有没子期次做过到期，如果有，则不允许修改到期日
     * @param dto
     * @return
     */
   public boolean isCheckMature(FinStageManagementDto dto){     
       int count = finStageManagementMapper.isCheckMature(dto.getFinprodId(), dto.getMdate());
       return count> 0;  
   }

    /**
     * 期次计划流程中的数据
     * @param map
     * @return
     */
    public List<ProcessMark> isWorkFlowCheck(Map<String, Object> map) {
        return finStageManagementMapper.isWorkFlowCheck(map);
    }
}
