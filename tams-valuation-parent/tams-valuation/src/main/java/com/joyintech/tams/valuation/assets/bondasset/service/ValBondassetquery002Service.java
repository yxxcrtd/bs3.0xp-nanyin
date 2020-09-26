/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.assets.bondasset.dao.ValBondassetquery001Mapper;
import com.joyintech.tams.valuation.assets.bondasset.dao.ValBondassetquery002Mapper;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.dao.InterfCommonMapper;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValBondassetquery002Service extends CommonExportService<ValBondassetquery002Dto> implements BaseProcessService<ValBondassetquery002Dto> {
    
    /**
     * ValBondassetquery001Mapper
     */
//    @Autowired
//    private ValBondassetquery002Mapper mapper;
//
//    @Autowired
//    private ValBondassetquery001Mapper valBondassetquery001Mapper;
//
//    @Autowired
//    private InterfCommonMapper commonMapper;
    
    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValBondassetquery002Dto queryBondasetByfinprodId(Map<String, Object> map) {
        
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/queryBondasetByfinprodId",
                map, ValBondassetquery002Dto.class);    
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
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/queryXqinfo",
                map, ValBondassetquery002Dto.class);    
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
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/queryintrate",
                map, ValBondassetquery002Dto.class);   
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
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/queryintpay",
                map, ValBondassetquery002Dto.class); 
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
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/queryrepay",
                map, ValBondassetquery002Dto.class); 
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
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/querycashflow",
                map, ValBondassetquery002Dto.class); 
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
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/selectDealCount",
                finprodid, Integer.class);
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
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/selectDealCount2",
                map, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValBondassetquery002Dto entity, OperationType type) {
        
//        // 更新标的类金融产品表
//        mapper.updateFinProduct(entity);
//        // 更新标的类金融产品扩展表
//        mapper.updateFinProductExt(entity);
//        // 更新金融产品分类表
////        Map<String,Object> typeMap = new HashMap<String,Object>();
////        typeMap.put("type1", entity.getBondtype());
////        FinProductClass finProductClass = new FinProductClass();
////        finProductClass.setFinprodType(entity.getFinprodType());
////        finProductClass.setBlnArea(entity.getBlnArea());
////        finProductClass.setAssetType(DictCodeListExt1.金融产品类型_债券.getValue());
////        typeMap.put("type2", commonMapper.getFinanceProductClass(finProductClass));
////        typeMap.put("type3", commonMapper.getFinanceProductTaxClass(finProductClass));
////        typeMap.put("finprodId", entity.getFinprodId());
////        mapper.updateFinProductType(typeMap);
//        
//        // 更新行权计划表
//        /**modify by wujie on 20190121 start
//        String cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_行权.getValue());
//        **/
//        String cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue());
//        /**modify by wujie on 20190121 end**/
//        mapper.deleteFinExercisePlanByFinProdId(entity.getFinprodId());
//        if (!entity.getComponentDto().getXqList().isEmpty()) {
//            mapper.insertFinExercisePlan(entity.getComponentDto().getXqList(), cashId, entity.getFinprodId());
//        }
//        // 更新现金流计算规则表(报价信息列表)
//        mapper.deleteFinCashCalcRuleByFinProdId(entity.getFinprodId());
//        if (!entity.getComponentDto().getBjList().isEmpty()) {
//            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue());
//            mapper.insertFinCashCalcRule(entity.getComponentDto().getBjList(), cashId, entity.getFinprodId(), entity.getRatetype(), entity.getBasis());
//        }
//        // 更新现金流支付规则表(付息信息)
//        mapper.updateFinCashPayRuleByFinProdId(entity);
//        // 更新现金流计划表（付息、还本）
//        mapper.deleteFinCashPlanByFinProdId(entity.getFinprodId());
//        if (!entity.getComponentDto().getFxList().isEmpty()) {
//            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
//            mapper.insertFinCashPlanFX(entity.getComponentDto().getFxList(), cashId, entity.getFinprodId());
//        }
//        if (!entity.getComponentDto().getHbList().isEmpty()) {
//            cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(),DictCodeListExt1.金融产品类型_债券.getValue(), DictCodeListExt1.现金流二级类型_本金.getValue());
//            List<ValBondassetHBDto> retList = entity.getComponentDto().getHbList();
//            List<ValBondassetHBDto> cashPlanList = new ArrayList<ValBondassetHBDto>();
//            int i = 0;
//            Date jsdatestr = null;
//            for (ValBondassetHBDto hb : retList) {
//            	ValBondassetHBDto dto = new ValBondassetHBDto();
//                if(i==0){
//                	dto.setJsdatestr(hb.getVdate1());
//                	jsdatestr = hb.getJsdateend();
//                }else{
//                	dto.setJsdatestr(jsdatestr);
//                	jsdatestr = hb.getJsdateend();
//                }
//                dto.setJsdateend(hb.getJsdateend());
//                dto.setPaydate(hb.getPaydate());
//                dto.setRemoney(hb.getRemoney());
//                cashPlanList.add(dto);
//                i++;
//            }
//            mapper.insertFinCashPlanHB(cashPlanList, cashId, entity.getFinprodId());
//        }
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery002/doComplete",
                entity, Integer.class);
    }

    /**
     * 查询交易日历下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getTradeCalendar() {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery002/getTradeCalendar",
                null,  String.class);
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
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery002/getIssure",
                null,  String.class);
    }

    /**
     * 修改操作相关校验
     * 
     * <功能详细描述>
     * @param result 
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public AjaxResult doUpdateCheck(BindingResult result, ValBondassetquery002Dto dto) throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult wr = new AjaxResult();
        
        // 判断资产代码是否存在
        int countFinprod = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery002/selectByPrimaryKey",
                dto.getFinprodId(),  Integer.class);
        if (countFinprod == 0) {
//        if (mapper.selectByPrimaryKey(dto.getFinprodId()) == 0) {
            wr = new AjaxResult(this.getMessage("VAL_COMMON_E_0001.1"), MessageType.错误消息);
            return wr;
        }
        // 起息日不能大于到期日
        if (dto.getMdate1().compareTo(dto.getVdate1()) < 0) {
            wr = new AjaxResult(this.getMessage("VAL_COMMON_E_0001", "起息日", "到期日"), MessageType.错误消息);
            return wr;
        }
        // 资产代码不存在
        if (countFinprod == 0) {
//        if (mapper.selectByPrimaryKey(dto.getFinprodId()) == 0) {
            wr = new AjaxResult("资产代码不存在", MessageType.错误消息);
            return wr;
        }
        // 有交易数据
        int count = selectDealCount(dto.getFinprodId());
        if (count > 0) {
            wr = new AjaxResult();
            wr.setData("该资产已存在交易数据，是否确定修改资产定义信息？");
            return wr;
        }
        
        wr.setData(this.getMessage("COMMON_C_0001", "提交"));
        return wr;
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
    public int workflowCheck(ValBondassetquery002Dto dto) throws Exception {
        //判断当前流程是否存在
        ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery002/workflowCheck",
                processMark,  Integer.class);
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
    public Date getNextWorkDay(Date actDateEnd, String tradMarketId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("actdateend", DateFormatUtils.ISO_DATE_FORMAT.format(actDateEnd));
        map.put("tradeMarketId", tradMarketId);
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery002/getNextWorkDay",
                map,  Date.class);
    }
}

