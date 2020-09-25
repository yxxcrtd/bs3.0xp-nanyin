/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.assets.bondasset.dao.ValBondassetquery001Mapper;
import com.joyintech.service.valuation.assets.bondasset.dao.ValBondassetquery002Mapper;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetHBDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dao.InterfCommonMapper;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery002Service
 * @since 1.0
 */
@Service(value="serviceValBondassetquery002Service")
@Transactional(rollbackFor = Exception.class)
public class ValBondassetquery002Service extends CommonExportService<ValBondassetquery002Dto> {
    
    /**
     * ValBondassetquery001Mapper
     */
    @Autowired
    private ValBondassetquery002Mapper mapper;
    
    @Autowired
    private ValBondassetquery001Mapper valBondassetquery001Mapper;
    
    @Autowired  
    private InterfCommonMapper commonMapper;
    
    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValBondassetquery002Dto queryBondasetByfinprodId(Map<String, Object> map) {
        
        return mapper.queryBondasetByfinprodId(map);
        
    }

    /**
     * 详情页表单数据的获取-行权信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery002Dto> queryXqinfo(Map<String, Object> map) {
        return mapper.queryXqinfo(map);
    }

    /**
     * 详情页表单数据的获取-债券利率报价信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery002Dto> queryintrate(Map<String, Object> map) {
        return mapper.queryintrate(map);
    }

    /**
     * 详情页表单数据的获取-付息计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery002Dto> queryintpay(Map<String, Object> map) {
        return mapper.queryintpay(map);
    }

    /**
     * 详情页表单数据的获取-还本计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery002Dto> queryrepay(Map<String, Object> map) {
        return mapper.queryrepay(map);
    }
    /**
     * 详情页表单数据的获取-现金流列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery002Dto> querycashflow(Map<String, Object> map) {
        return mapper.querycashflow(map);
    }
    
    /**
     * 查询是否交易数据
     * 
     * <功能详细描述>
     * @param finprodid
     * @return
     * @see
     * @since 1.0
     */
    public int selectDealCount(String finprodid) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("finprodid", finprodid);
        return mapper.selectDealCountByFinprodid(map);
    }
    
    /**
     * 查询是否交易数据：行权交易
     * 
     * <功能详细描述>
     * @param finprodid
     * @return
     * @see
     * @since 1.0
     */
    public int selectDealCount(Map<String, Object> map) {
        return mapper.selectDealCountByFinprodid(map);
    }

    /**
     * {@inheritDoc}
     */
    public void doComplete(ValBondassetquery002Dto entity) {
        
        // 更新标的类金融产品表
        mapper.updateFinProduct(entity);
        // 更新标的类金融产品扩展表
        mapper.updateFinProductExt(entity);
        // 更新金融产品分类表
//        Map<String,Object> typeMap = new HashMap<String,Object>();
//        typeMap.put("type1", entity.getBondtype());
//        FinProductClass finProductClass = new FinProductClass();
//        finProductClass.setFinprodType(entity.getFinprodType());
//        finProductClass.setBlnArea(entity.getBlnArea());
//        finProductClass.setAssetType(DictCodeListExt1.金融产品类型_债券.getValue());
//        typeMap.put("type2", commonMapper.getFinanceProductClass(finProductClass));
//        typeMap.put("type3", commonMapper.getFinanceProductTaxClass(finProductClass));
//        typeMap.put("finprodId", entity.getFinprodId());
//        mapper.updateFinProductType(typeMap);
        
        // 更新行权计划表
        /**modify by wujie on 20190121 start
        String cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_行权.getValue());
        **/
        String cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(), DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue());
        /**modify by wujie on 20190121 end**/
        mapper.deleteFinExercisePlanByFinProdId(entity.getFinprodId());
        if (!entity.getComponentDto().getXqList().isEmpty()) {
            mapper.insertFinExercisePlan(entity.getComponentDto().getXqList(), cashId, entity.getFinprodId());
        }
        // 更新现金流计算规则表(报价信息列表)
        mapper.deleteFinCashCalcRuleByFinProdId(entity.getFinprodId());
        if (!entity.getComponentDto().getBjList().isEmpty()) {
            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue());
            mapper.insertFinCashCalcRule(entity.getComponentDto().getBjList(), cashId, entity.getFinprodId(), entity.getRatetype(), entity.getBasis());
        }
        // 更新现金流支付规则表(付息信息)
        mapper.updateFinCashPayRuleByFinProdId(entity);
        // 更新现金流计划表（付息、还本）
        mapper.deleteFinCashPlanByFinProdId(entity.getFinprodId());
        if (!entity.getComponentDto().getFxList().isEmpty()) {
            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
            mapper.insertFinCashPlanFX(entity.getComponentDto().getFxList(), cashId, entity.getFinprodId());
        }
        if (!entity.getComponentDto().getHbList().isEmpty()) {
            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue());
            List<ValBondassetHBDto> retList = entity.getComponentDto().getHbList();
            List<ValBondassetHBDto> cashPlanList = new ArrayList<ValBondassetHBDto>();
            int i = 0;
            Date jsdatestr = null;
            for (ValBondassetHBDto hb : retList) {
            	ValBondassetHBDto dto = new ValBondassetHBDto();
                if(i==0){
                	dto.setJsdatestr(hb.getVdate1());
                	jsdatestr = hb.getJsdateend();
                }else{
                	dto.setJsdatestr(jsdatestr);
                	jsdatestr = hb.getJsdateend();
                }
                dto.setJsdateend(hb.getJsdateend());
                dto.setPaydate(hb.getPaydate());
                dto.setRemoney(hb.getRemoney());
                cashPlanList.add(dto);
                i++;
            }
            mapper.insertFinCashPlanHB(cashPlanList, cashId, entity.getFinprodId());
        }
    }

    /**
     * 查询交易日历下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getTradeCalendar() {
        List<SelectDto> list = mapper.selectTradeCalendar();
        String ret = JsonUtils.objectArray2Json(list.toArray());
        ret = ret.replaceAll("name", "text");
        ret = ret.replaceAll("code", "id");
        return ret;
    }
    
    /**
     * 获取发行人下拉框
     * 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public String getIssure() {
        List<SelectDto> list = mapper.selectIssure();
        String ret = JsonUtils.objectArray2Json(list.toArray());
        ret = ret.replaceAll("name", "text");
        ret = ret.replaceAll("code", "id");
        return ret;
    }


    
    /**
     * 查询流程中数据
     * <功能详细描述>
     * @param processMark
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public int workflowCheck(ProcessMark processMark) throws Exception {
        return valBondassetquery001Mapper.getDataInWorklow(processMark);
    }

    /**
     * 查询大于等于actDateEnd的第一个工作日
     * 
     * <功能详细描述>
     * @param actDateEnd
     * @param tradMarketId
     * @return
     * @see
     * @since 1.0
     */
    public Date getNextWorkDay(Map<String,Object> map) {
        return mapper.selectNextWorkDay(map);
    }
    
    /**
     * 判断资产代码是否存在
     * 
     */
    public int selectByPrimaryKey(String finprodId) {
        return mapper.selectByPrimaryKey(finprodId);
    }
    
}

