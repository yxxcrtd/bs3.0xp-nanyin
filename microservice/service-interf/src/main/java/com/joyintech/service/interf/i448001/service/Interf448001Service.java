/*
 * 文件名：Inter448001service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448001.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i448001.dao.Interf448001Mapper;
import com.joyintech.service.interf.i448001.entity.Interf448001InEntity;
import com.joyintech.service.interf.i448001.entity.Interf448001OutEntity;
import com.joyintech.service.interf.prodmodify.dto.ProdModifyDto;
import com.joyintech.service.interf.workflownode.service.WorkFlowNodeService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品确认接口（448001）-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月18日
 * @see Interf448001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_448001_01)
public class Interf448001Service extends SendEsbService<Interf448001InEntity, Interf448001OutEntity> {

    /**
     * 接口448001的查询Mapper
     */
    @Autowired
    private Interf448001Mapper mapper;

    /**
     * 查询流程节点处理人——详细处理
     */
    @Autowired
    private WorkFlowNodeService workFlowNodeService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf448001InEntity preparedLocalData(Object param)
        throws Exception {
        ProdModifyDto dto = (ProdModifyDto)param;
        Map<String, Object> map = new HashMap<>();
        // 金融产品代码， 平台时间
        String finProdId = dto.getFinProdId();
        map.put("prodId", finProdId);
        map.put("sysDate", getSysDate());
        // 金融产品类型F16-理财产品，现金流一级类型03-费用，现金流二级类型C06-销售费
        map.put("prodType", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put(InterfConstant.CASHTYPE1, DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put(InterfConstant.CASHTYPE2, DictCodeListExt1.现金流二级类型_销售费.getValue());
        // 1.获取产品信息
        Interf448001InEntity inEntity = mapper.selectProdInfo(map);
        // 2.获取分红信息
        // 现金流一级类型02-利息，现金流二级类型C02-利息
        map.put(InterfConstant.CASHTYPE1, DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put(InterfConstant.CASHTYPE2, DictCodeListExt1.现金流二级类型_利息.getValue());
        Interf448001InEntity payEntity = mapper.selectCashPayRule(map);
        if (StringUtil.isNotEmpty(payEntity)) {
            inEntity.setPartType(payEntity.getPartType());
            inEntity.setPartCycle(payEntity.getPartCycle());
        }
        // 3.根据画面id和业务主键,获取经办人信息
        ProcessMark mark = new ProcessMark();
        mark.setPageId(dto.getPageId());
        mark.setBusinessDataPk2(inEntity.getShortName());
        mark.setBusinessDataPk3(EncryptUtil.getInstance().md5(inEntity.getProdName()));
        mark.setBusinessDataPk4(EncryptUtil.getInstance().md5(inEntity.getProductName()));
        BeanUtil.copyPropertiesIgnoreNull(inEntity, workFlowNodeService.selectWorkFlowNode(mark));
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf448001OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf448001OutEntity entity)
        throws Exception {
        return 0;
    }

}
