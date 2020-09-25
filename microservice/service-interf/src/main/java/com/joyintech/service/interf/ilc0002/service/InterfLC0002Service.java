package com.joyintech.service.interf.ilc0002.service;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;
import com.joyintech.service.interf.ibillinfo.service.InterfBillInfoService;
import com.joyintech.service.interf.ilc0002.dao.InterfLC0002Mapper;
import com.joyintech.service.interf.ilc0002.entity.InterfLC0002InEntity;
import com.joyintech.service.interf.ilc0002.entity.InterfLC0002OutEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 接口LC0002查询到期单据信息 详细处理
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfLC0002Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0002)
public class InterfLC0002Service extends ReceivedEsbService<InterfLC0002OutEntity, InterfLC0002InEntity> {

    /**
     * 接口LC0002查询Mapper
     */
    @Autowired
    private InterfLC0002Mapper iLC0002Mapper;

    /**
     * 单据信息处理详细
     */
    @Autowired
    private InterfBillInfoService billService;

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLC0002InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLC0002InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLC0002OutEntity selectResponseData(InterfLC0002InEntity entity)
        throws Exception {
        // 销售部门数据字典转换
        billService.changeSaleDeptCode(entity);

        InterfBillInEntity finProdInfo = billService.selectFinProdInfo(entity);
        // 判断产品信息是否存在
        if (null == finProdInfo) {
            doException(new Exception(), entity.getProdId() + "产品信息不存在！");
            return null;
        }
        String finProdId = finProdInfo.getFinProdId();
        entity.setVdate(entity.getStartdate());
        entity.setFinProdId(finProdId);
        entity.setPabussType(DictCodeListExt1.平安业务类型_产品到期.getValue());
        // 判断到期单据是否存在,查询汇总编号
        String totleTradeId = billService.selectTotleTradeId(entity);
        if (StringUtil.isEmpty(totleTradeId)) {
            throw new InterfException(
                entity.getProdId() + "在日期（" + entity.getEnddate() + "）产品未有到期单据！");
        }

        entity.setTotleTradeId(totleTradeId);
        // 获取产品信息
        InterfLC0002OutEntity outEntity = iLC0002Mapper.selectProdInfo(entity);
        entity.setTradePS(DictCodeList.交易方向_产品到期.getValue());
        if (BussinessDictCodeList.处理模式_净值型.getValue().equals(finProdInfo.getDealType())
            || BussinessDictCodeList.处理模式_货币型.getValue().equals(finProdInfo.getDealType())) {
            entity.setTradePS2(DictCodeList.交易方向_产品现金分红.getValue());
        }
        else {
            entity.setTradePS2(DictCodeList.交易方向_中间付息.getValue());
        }

        // 获取分层金额和利率
        billService.selectLayAmt(entity, outEntity);
        // 检索分行机构名称，需要切换数据源，查询base库
        String branchName = billService.selectBranchgName(outEntity.getAcctBranchId());
        outEntity.setBranchName(branchName);
        // 设置费率
        outEntity.setFinProdId(finProdId);
        outEntity.setEnddate(entity.getStartdate());
        billService.setFeeRate(outEntity);

        // 设置名称
        billService.setCodeName(outEntity);

        // 设置流程处理人
        billService.setWkfUser(outEntity, DictCodeList.共通业务类型_收益型产品赎回到期管理);

        return outEntity;
    }
}
