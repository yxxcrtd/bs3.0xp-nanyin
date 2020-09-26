/*
 * 文件名：ValBokBalanceProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeGenerateResult;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeTodoDto;
import com.joyintech.tams.valuation.batch.valscheme.service.ValSchemeHZBankService;
import com.joyintech.tams.valuation.batch.valscheme.service.ValSchemeHZLayerService;
import com.joyintech.tams.valuation.batch.valscheme.service.ValSchemeLayerService;
import com.joyintech.tams.valuation.batch.valscheme.service.ValSchemeService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 估值表生成
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月31日
 * @see ValSchemeProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValSchemeProcessor extends BaseProcessor<NeedOperateEntity, ValSchemeGenerateResult> {

    /**
     * 共通方法的 Service
     */
    @Autowired
    private ValSchemeService valSchemeService;

    /**
     * 共通方法的 Service
     */
    @Autowired
    private ValSchemeHZBankService valSchemeHZBankService;

    @Autowired
    private ValSchemeLayerService valSchemeLayerService;
    
    @Autowired
    private ValSchemeHZLayerService valSchemeHZLayerService;

    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ValSchemeGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        ValSchemeTodoDto acctEntity = (ValSchemeTodoDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        ValSchemeGenerateResult result = null;
        // 调用共通获取账套配置表中估值表模板字段
        List<String> paramCodeList = new ArrayList<String>();
        // 估值表模板
        paramCodeList.add(DictCode.估值表模板.getValue());
        Map<String, Object> getValBokBooksetExtMap = accountingCommonService.getValBokBooksetExtForMap(
            acctEntity.getBooksetId(), paramCodeList);
        String valTemplate = (String)getValBokBooksetExtMap.get(DictCode.估值表模板.getValue());
        // 是否使用分层估值表
        String layerSchemeSwitch = acctEntity.getBokSpecMode();
        // 若账套配置表中估值表模板不为空，则根据当前配置区分模板选择
        if (StringUtil.isNotEmpty(valTemplate)) {
            if (DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue().equals(valTemplate)) {
                // 杭州分层估值表
                if (StringUtils.isNotEmpty(layerSchemeSwitch)) {
                    // 分层估值表
                    result = valSchemeHZLayerService.execute(thisParams);
                }
                // 杭州估值表
                else {
                    result = valSchemeHZBankService.execute(thisParams);
                }
            }
            else {
                if (StringUtils.isNotEmpty(layerSchemeSwitch)) {
                    // 分层估值表
                    result = valSchemeLayerService.execute(thisParams);
                }
                else {
                    // 一般估值表
                    result = valSchemeService.execute(thisParams);
                }
            }
        }
        // 若为空，则根据系统参数开关判断
        else {
            // 估值模板选择
            String hzSchemeSwitch = CacheUtil.getSysParam("SCHEME_MODE");
            if (DictCodeList.估值模板_理财产品模板.getValue().equals(hzSchemeSwitch)) {
                // 杭州分层估值表
                if (StringUtils.isNotEmpty(layerSchemeSwitch)) {
                    // 分层估值表
                    result = valSchemeHZLayerService.execute(thisParams);
                }
                // 杭州估值表
                else {
                    result = valSchemeHZBankService.execute(thisParams);
                }
            }
            else {
                if (StringUtils.isNotEmpty(layerSchemeSwitch)) {
                    // 分层估值表
                    result = valSchemeLayerService.execute(thisParams);
                }
                else {
                    // 一般估值表
                    result = valSchemeService.execute(thisParams);
                }
            }
        }

        // 当为null时候，给予初始化
        if (result == null) {
            result = new ValSchemeGenerateResult();
        }

        result.setProdId(acctEntity.getBooksetId());

        // 取得科目余额
        return result;
    }

}
