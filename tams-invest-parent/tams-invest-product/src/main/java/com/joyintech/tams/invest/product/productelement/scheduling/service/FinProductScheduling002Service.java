/*
 * 文件名：FinProductScheduling001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品排期管理-新建、修改、查看
 * 
 * @author zhangliang
 * @version 1.0 2018年9月6日
 * @see FinProductScheduling002Service
 * @since 1.0
 */
@Service
public class FinProductScheduling002Service extends BaseService<PrdIssueScheduleDto> implements BaseProcessService<PrdIssueScheduleDto> {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(PrdIssueScheduleDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            this.convertDtoInfo(entity);
            action = "insert";
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.convertDtoInfo(entity);
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductScheduling/" + action, entity,
            Integer.class);
    }

    /**
     * 排期信息对象转换
     * 
     * @param entity 排期信息对象
     * @see
     * @since 1.0
     */
    private void convertDtoInfo(PrdIssueScheduleDto entity) {
        entity.setFinprodId(
            entity.getFinprodId() != null ? entity.getFinprodId() : IDGenerator.generate());
        entity.setToffStartLow(
            ArithmeticUtil.multiply(entity.getToffStartLow(), PrdIssueScheduleDto.TENTOU));
        entity.setToffStartMid(
            ArithmeticUtil.multiply(entity.getToffStartMid(), PrdIssueScheduleDto.TENTOU));
        entity.setToffStartHig(
            ArithmeticUtil.multiply(entity.getToffStartHig(), PrdIssueScheduleDto.TENTOU));
        entity.setYieldLow(
            ArithmeticUtil.divide(entity.getYieldLow(), PrdIssueScheduleDto.HUNDREND, 14));
        entity.setYieldMid(
            ArithmeticUtil.divide(entity.getYieldMid(), PrdIssueScheduleDto.HUNDREND, 14));
        entity.setYieldHig(
            ArithmeticUtil.divide(entity.getYieldHig(), PrdIssueScheduleDto.HUNDREND, 14));
        entity.setSaleFeeRate(
            ArithmeticUtil.divide(entity.getSaleFeeRate(), PrdIssueScheduleDto.HUNDREND, 14));
        entity.setTrustFeeRate(
            ArithmeticUtil.divide(entity.getTrustFeeRate(), PrdIssueScheduleDto.HUNDREND, 14));

//        if (entity.getBussRepFinProductInfoDto() != null) {
//            if (StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepLowYield())) {
//                entity.getBussRepFinProductInfoDto().setRepLowYield(
//                    ArithmeticUtil.divide(entity.getBussRepFinProductInfoDto().getRepLowYield(),
//                        PrdIssueScheduleDto.HUNDREND, 14));
//            }
//            if (StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepExpAvgYield())) {
//                entity.getBussRepFinProductInfoDto().setRepExpAvgYield(
//                    ArithmeticUtil.divide(entity.getBussRepFinProductInfoDto().getRepExpAvgYield(),
//                        PrdIssueScheduleDto.HUNDREND, 14));
//            }
//            if (StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepHighYield())) {
//                entity.getBussRepFinProductInfoDto().setRepHighYield(
//                    ArithmeticUtil.divide(entity.getBussRepFinProductInfoDto().getRepHighYield(),
//                        PrdIssueScheduleDto.HUNDREND, 14));
//            }
//        }
    }

    /**
     * 查询产品模板下拉框
     * 
     * @param map 参数
     * @return 产品模板
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdTempleteInfo(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME
                                        + "/FinProductScheduling/queryProdTempleteInfo",
            map, SelectOptionDto.class);
    }

    /**
     * 查询产品申报方案下拉框
     * 
     * @param map 参数
     * @return 产品申报方案
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryDeclareSchemeInfo(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                        + "/FinProductScheduling/queryDeclareSchemeInfo",
            map, SelectOptionDto.class);
    }

    /**
     * 根据产品金融代码查询排期信息
     * 
     * @param finprodId 产品金融代码
     * @return 排期信息
     * @see
     * @since 1.0
     */
    public PrdIssueScheduleDto queryIssueInfoByFinprodId(String finprodId) {
        PrdIssueScheduleDto entity = restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductScheduling/queryIssueInfoByFinprodId?finprodId="
                                                               + finprodId,
            PrdIssueScheduleDto.class);
        entity.setToffStartLow(
            ArithmeticUtil.divide(entity.getToffStartLow(), PrdIssueScheduleDto.TENTOU, 6));
        entity.setToffStartMid(
            ArithmeticUtil.divide(entity.getToffStartMid(), PrdIssueScheduleDto.TENTOU, 6));
        entity.setToffStartHig(
            ArithmeticUtil.divide(entity.getToffStartHig(), PrdIssueScheduleDto.TENTOU, 6));
        entity.setYieldLow(
            ArithmeticUtil.multiply(entity.getYieldLow(), PrdIssueScheduleDto.HUNDREND));
        entity.setYieldMid(
            ArithmeticUtil.multiply(entity.getYieldMid(), PrdIssueScheduleDto.HUNDREND));
        entity.setYieldHig(
            ArithmeticUtil.multiply(entity.getYieldHig(), PrdIssueScheduleDto.HUNDREND));
        entity.setSaleFeeRate(
            ArithmeticUtil.multiply(entity.getSaleFeeRate(), PrdIssueScheduleDto.HUNDREND));
        entity.setTrustFeeRate(
            ArithmeticUtil.multiply(entity.getTrustFeeRate(), PrdIssueScheduleDto.HUNDREND));

        return entity;
    }

    /**
     * 查询产品基本信息是否存在
     * 
     * @param finprodMarketId 产品代码
     * @param finprodAbbr 产品简称
     * @param finprodName 产品名称
     * @param finprodId 金融产品代码
     * @return 是否存在
     * @see
     * @since 1.0
     */
    public int checkProdInfoExist(String finprodMarketId, String finprodAbbr, String finprodName,
                                  String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductScheduling/checkProdInfoExist?finprodMarketId="
                                         + StringUtil.defaultString(finprodMarketId) + "&finprodAbbr=" + StringUtil.defaultString(finprodAbbr)
                                         + "&finprodName=" + StringUtil.defaultString(finprodName) + "&finprodId="
                                         + StringUtil.defaultString(finprodId),
            Integer.class);
    }
}
