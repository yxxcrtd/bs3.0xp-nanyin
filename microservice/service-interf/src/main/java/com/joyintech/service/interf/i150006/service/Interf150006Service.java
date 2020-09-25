/*
 * 文件名：Interf150006Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150006.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i150006.dao.Interf150006Mapper;
import com.joyintech.service.interf.i150006.entity.Interf150006InEntity;
import com.joyintech.service.interf.i150006.entity.Interf150006OutEntity;
import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;
import com.joyintech.service.interf.ifeerate.service.FeeRateService;
import com.joyintech.service.interf.prodmodify.dto.ProdModifyDto;
import com.joyintech.service.interf.workflownode.service.WorkFlowNodeService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品确认通过时，实时推送产品基本信息到TA系统 -产品信息预创建接口（150006）
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see Interf150006Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_150006_00)
public class Interf150006Service extends SendEsbService<Interf150006InEntity, Interf150006OutEntity> {

    /**
     * 产品信息预创建接口（150006）查询mapper
     */
    @Autowired
    private Interf150006Mapper mapper;

    /**
     * 查询费率
     */
    @Autowired
    private FeeRateService feeService;

    /**
     * 查询流程节点处理人——详细处理
     */
    @Autowired
    private WorkFlowNodeService workFlowNodeService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf150006InEntity preparedLocalData(Object param)
        throws Exception {
        ProdModifyDto dto = (ProdModifyDto)param;
        String finProdId = dto.getFinProdId();
        // 金融产品代码， 平台时间
        FeeRateEntity feeRateEntity = new FeeRateEntity();
        feeRateEntity.setFinprodId(finProdId);
        feeRateEntity.setCdate(getSysDate());
        // 现金流一级类型02-利息，现金流二级类型C02-利息
        feeRateEntity.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        // 1.获取产品信息
        Interf150006InEntity inEntity = mapper.selectProdInfo(feeRateEntity);
        if (!StringUtil.isNotEmpty(inEntity)) {
            getLogger().error("金融产品代码为：{}的金融产品信息查询失败", finProdId);
            return inEntity;
        }
        // 2.获取分红信息
        Interf150006InEntity payEntity = mapper.selectCashPayRule(feeRateEntity);

        if (StringUtil.isNotEmpty(payEntity)) {
            inEntity.setPartType(payEntity.getPartType());
            inEntity.setPartCycle(payEntity.getPartCycle());
        }

        // 3.获取费用信息：销售费率、管理费率、托管费率、其他费率

        // 金融产品类型F16-理财产品，现金流一级类型03-费用，现金流二级类型C06-销售费
        feeRateEntity.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_销售费.getValue());
        String saleFeeRate = feeService.selectFeeRateStr(feeRateEntity);
        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_托管费.getValue());
        String consignFeerate = feeService.selectFeeRateStr(feeRateEntity);

        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_通道其他费.getValue());
        String otherFeerate = feeService.selectFeeRateStr(feeRateEntity);

        inEntity.setSaleFeeRate(saleFeeRate);
        inEntity.setConsignFeerate(consignFeerate);
        inEntity.setOtherFeerate(otherFeerate);

        // 设置流程处理人信息
        ProcessMark mark = new ProcessMark();
        mark.setPageId(dto.getPageId());
        mark.setBusinessDataPk2(inEntity.getShortName());
        mark.setBusinessDataPk3(EncryptUtil.getInstance().md5(inEntity.getProChShortName()));
        mark.setBusinessDataPk4(EncryptUtil.getInstance().md5(inEntity.getProductName()));
        BeanUtil.copyPropertiesIgnoreNull(inEntity, workFlowNodeService.selectWorkFlowNode(mark));
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf150006OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf150006OutEntity entity)
        throws Exception {
        return 0;
    }

}
