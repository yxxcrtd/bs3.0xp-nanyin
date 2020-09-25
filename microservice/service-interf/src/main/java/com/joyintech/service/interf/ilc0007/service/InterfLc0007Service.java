/*
 * 文件名：InterfLc0007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0007.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.ilc0007.dao.InterfLc0007Mapper;
import com.joyintech.service.interf.ilc0007.entity.InterfLc0007InEntity;
import com.joyintech.service.interf.ilc0007.entity.InterfLc0007OutEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 实时查询余额更新接口Lc0007处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0007Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0007)
public class InterfLc0007Service extends ReceivedEsbService<InterfLc0007OutEntity, InterfLc0007InEntity> {

    /**
     * 实时查询余额更新接口Lc0007mapper
     */
    @Autowired
    private InterfLc0007Mapper interfLc0007Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLc0007OutEntity selectResponseData(InterfLc0007InEntity entity)
        throws Exception {
        // 判断 放款指令编号 与 业务品种编号 是否正确
        if (interfLc0007Mapper.selectCmsInfoCnt(entity) < 1) {
            throw new InterfException("放款指令编号（" + entity.getPutoutorderno() + "）或业务品种编号（"
                                      + entity.getBussvarietyno() + "）错误");
        }
        // 判断 放款指令编号 与 借据流水号 是否正确
        InterfLc0007OutEntity outentity = interfLc0007Mapper.selectDuebillAmt(entity);
        if (outentity == null) {
            throw new InterfException("放款指令编号（" + entity.getPutoutorderno() + "）或借据流水号（"
                                      + entity.getDuebillseqno() + "）错误");
        }
        // 设置输出outentity
        entity.setCdate(getSysDate());
        entity.setBenjiCode(DictCodeListExt1.现金流二级类型_本金.getValue());
        // 取得借据余额数量
        BigDecimal amount = interfLc0007Mapper.selectDuebillBalanceAmount(entity);
        if (amount == null) {
            throw new InterfException("借据余额信息不存在");
        }
        // 借据余额
        BigDecimal duebillBalance = null;
        // 债券资产取借据余额
        if (StringUtil.equals(outentity.getFinprodType(),
            DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            // 取得借据余额
            duebillBalance = amount.multiply(interfLc0007Mapper.selectFaceValue(entity));
        }
        // 债券质押式正，逆回购资产取借据余额
        else if (StringUtil.equals(outentity.getFinprodType(),
            DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                 || StringUtil.equals(outentity.getFinprodType(),
                     DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            duebillBalance = amount.multiply(new BigDecimal(100));
        }
        // 其他资产类型借据余额
        else {
            duebillBalance = amount;
        }
        outentity.setDuebillbalance(duebillBalance);
        outentity.setSellbalance("0");
        return outentity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLc0007InEntity entity)
        throws Exception {
        // 查询接口无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLc0007InEntity entity)
        throws Exception {
        // 查询接口无登录操作
        return 0;
    }

}
