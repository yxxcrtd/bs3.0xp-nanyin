package com.joyintech.tams.invest.product.messageadjust.finproductquotation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品报价管理-新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月28日
 * @see FinProductQuotation002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinProductQuotation002Service extends BaseService<FinProductQuotation002Dto> implements BaseProcessService<FinProductQuotation002Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 产品代码联想框 <功能详细描述>
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId2(String finprodId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/getFinprodId2",
            param, SelectOptionDto.class);
    }

    /**
     * 根据产品代码查询产品基本信息
     * 
     * @param finprodId 金融产品代码
     * @return 基本信息
     * @see
     * @since 1.0
     */
    public FinProductQuotation001Dto queryProdInfoByFinprodId(String finprodId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("profitType", DictCodeList.收益类型_收益型.getValue());
        param.put("interest1", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("interest2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("branch", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductQuotation001/queryProdInfoByFinprodId",
            param, FinProductQuotation001Dto.class);
    }

    /**
     * 净值、货币产品生效日判断 <功能详细描述>
     * 
     * @param dto dto
     * @return int
     * @see
     * @since 1.0
     */
    public int getValueDate(FinProductQuotation001Dto dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", dto.getFinprodId());
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("valDate", dto.getEffDate());
        param.put("inputType", DictCodeListExt1.录入方式_手工录入.getValue());
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/getValueDate",
            param, Integer.class);
    }

    /**
     * 检查是否存在生效日的报价
     * 
     * @param finprodId 金融产品代码
     * @param effDate 生效日
     * @return 是否存在生效日的报价
     * @see
     * @since 1.0
     */
    public int checkEffDateQuoteExists(String finprodId, String effDate) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductQuotation001/checkEffDateQuoteExists",
            param, Integer.class);
    }

    /**
     * 根据产品代码得到起息日和到期日 <一句话功能简述> <功能详细描述>
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductQuotation001Dto getVdateAndMdate(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductQuotation001/getVdateAndMdate?finprodId="
                                         + finprodId,
            FinProductQuotation001Dto.class);
    }

    /**
     * 得到业务编码 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getBussinessId() {
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品报价调整);
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/getBussinessId",
            parameter, String.class);
    }

    @Override
    public void doComplete(FinProductQuotation002Dto entity, OperationType type) {
        if (type == OperationType.NEW) {
            /*if (!StringUtil.isEmptyList(entity.getLayTypeInfoList())) {
                for (int i = 0; i < entity.getLayTypeInfoList().size(); i++ ) {
                    // 设置分层型产品的金融产品代码
                    entity.getLayTypeInfoList().get(i).setFinprodId(
                        commonBusinessService != null ? commonBusinessService.getFinProdId(
                            entity.getFinprodMarketId(),
                            DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue(), null).concat(
                                GlobalConstant.UNDER_LINE).concat(entity.getEffDateStr()).concat(
                                    GlobalConstant.UNDER_LINE).concat(
                                        i + "") : IDGenerator.generate());
                }
            }*/
            
            restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/doInsert",
                    entity, Integer.class);
            }
            else if (type == OperationType.DELETE) {
                restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/doDelete",
                    entity, Integer.class);
            }
        else if (type == OperationType.MODIFY) {
            if (!StringUtil.isEmptyList(entity.getLayTypeInfoList())) {
                for (int i = 0; i < entity.getLayTypeInfoList().size(); i++ ) {
                    // 设置分层型产品的金融产品代码
                    if (StringUtil.isEmpty(entity.getLayTypeInfoList().get(i).getFinprodId())) {
                        entity.getLayTypeInfoList().get(i).setFinprodId(
                            commonBusinessService != null ? commonBusinessService.getFinProdId(
                                entity.getFinprodMarketId(),
                                DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue(), null).concat(
                                    GlobalConstant.UNDER_LINE).concat(
                                        entity.getEffDateStr()).concat(
                                            GlobalConstant.UNDER_LINE).concat(
                                                i + "") : IDGenerator.generate());
                    }
                }
            }

            restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/doUpdate",
                entity, Integer.class);
        }

    }

    /**
     * 根据金融产品代码和报价日查询报价信息
     * 
     * @param finprodId 金融产品代码
     * @param effDate 报价日
     * @return 报价信息
     * @see
     * @since 1.0
     */
    public FinProductQuotation002Dto queryQuoteInfoByFinprodIdEffDate(String finprodId,
                                                                      String effDate) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductQuotation001/queryQuoteInfoByFinprodIdEffDate",
            param, FinProductQuotation002Dto.class);
    }

    /**
     * 根据金融产品代码查询分层信息
     * 
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分层信息
     * @see
     * @since 1.0
     */
    public List<FinProductQuotation003Dto> queryLayerDetailByFinprodId(String finprodId,
                                                                       String effDate) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductQuotation001/queryLayerDetailByFinprodId?finprodId="
                                       + finprodId + "&effDate=" + effDate,
            FinProductQuotation003Dto.class);
    }

    /**
                  * 根据金融产品代码查询分层报价信息
     * 
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分层信息
     * @see
     * @since 1.0
     */
    public List<FinProductQuotation003Dto> queryLayFinProductQuotationList(String finprodId,
                                                                       String effDate) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductQuotation001/queryLayFinProductQuotationList?finprodId="
                                       + finprodId + "&effDate=" + effDate,
            FinProductQuotation003Dto.class);
    }
    /**
     * 根据金融产品代码和报价日查询分层报价信息
     * 
     * @param finprodId 金融产品代码
     * @param effDate 报价日
     * @return 报价信息
     * @see
     * @since 1.0
     */
    public List<FinProductQuotation003Dto> queryQuoteLayDetailInfoList (String finprodId,
                                                                      String effDate) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        param.put("interest1", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("interest2", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("branch", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductQuotation001/queryQuoteLayDetailInfoList",
            param, FinProductQuotation003Dto.class);
    }
}
