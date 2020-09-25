/*
 * 文件名：ReconcileSchemeInfo002Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dao.ValAssetBook001Mapper;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dao.ValReconcileSchemeInfo002Mapper;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo002Dto;
import com.joyintech.tams.common.base.dao.BokBooksetPlanMapper;
import com.joyintech.tams.common.base.dao.BokSchemeInfoMapper;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokSchemeInfo;
import com.joyintech.tams.common.base.entity.BokSchemeInfoDetail;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.utils.DBUtil;


/**
 * 对账规则方案配置新建Service
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002Service
 * @since 1.0
 */
@Service(value="serviceValReconcileSchemeInfo002Service")
@Transactional(rollbackFor = Exception.class)
public class ValReconcileSchemeInfo002Service extends BaseService<BokSchemeInfoDetail> {

    /** 对账规则配置新建Mapper */
    @Autowired
    private ValReconcileSchemeInfo002Mapper reconcileSchemeInfo002Mapper;

    /**
     * 账套税务配置Mapper
     */
    @Autowired
    private BokSchemeInfoMapper bokSchemeInfoMapper;
    
    @Autowired
    private BokBooksetPlanMapper bokBooksetPlanMapper;
    
    /**
     * ValAssetBook001Mapper
     */
    @Autowired
    private ValAssetBook001Mapper valAssetBook001Mapper;
    
    @Autowired
    private DBUtil dBUtil;

    /**
     * 保存对账规则方案配置新建信息
     * 
     * @param record 对账规则方案配置新建信息
     * @since 1.0
     */
    public void doSave(ValReconcileSchemeInfo002Dto record) {
        String schemeId = dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列);
        // 账套估值方案
        BokBooksetPlan bokBooksetPlan = new BokBooksetPlan();
        bokBooksetPlan.setBooksetSchemeId(dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列));
        bokBooksetPlan.setBooksetId(record.getBooksetId());
        bokBooksetPlan.setSchemeId(schemeId);
        bokBooksetPlan.setSchemeType(record.getSchemeType());
        bokBooksetPlanMapper.insert(bokBooksetPlan);
        // 对账方案配置
        BokSchemeInfo insertInfo = new BokSchemeInfo();
        insertInfo.setSchId(schemeId);
        insertInfo.setSchName(record.getSchemeName());
        insertInfo.setSchemeType(record.getSchemeType());
        insertInfo.setIsDefault(record.getIsDefault());
        bokSchemeInfoMapper.insert(insertInfo);
        
    }

    /**
     * 校验方案名称是否已存在
     * 
     * @param schemeDto 方案信息
     * @return 已存在 true,否则 false
     * @since 1.0
     */
    public boolean checkSchemeName(ValReconcileSchemeInfo002Dto schemeDto) {
        return reconcileSchemeInfo002Mapper.findSchemeName(schemeDto) > 0;
    }
    
    /**
     * 根据方案类型检索已存在的默认方案名称，不校验当前操作的数据本身
     * 
     * @param infoDto 画面元素
     * @return 默认方案名称
     * @since 1.0
     */
    public ValReconcileSchemeInfo002Dto selectDefaultSchemeByType(ValReconcileSchemeInfo002Dto infoDto) {
        return reconcileSchemeInfo002Mapper.selectDefaultSchemeByType(infoDto);
    }
    
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId(String param) {
        String booksetType = DictCodeList.账套类别_账套模板.getValue();
       /* return queryForSuggest(() -> {
            return valAssetBook001Mapper.getBooksetId2(booksetType);
        }, null, param);*/
        return valAssetBook001Mapper.getBooksetId2(booksetType, param);
    }


}
