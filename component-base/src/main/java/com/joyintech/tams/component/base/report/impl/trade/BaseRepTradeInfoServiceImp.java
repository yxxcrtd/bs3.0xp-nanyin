/*
 * 文件名：BaseRepTradeInfoServiceImp.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.impl.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepTradeMapper;
import com.joyintech.tams.common.base.entity.RepAssetAstBill;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.report.dto.BaseRepTradeInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepTradeInfoMapper;
import com.joyintech.tams.component.base.report.trade.BaseRepTradeInfoService;

/**
 * 交易报送信息组件实现类
 *
 * @author 刘二通
 * @version 1.0 2018年11月4日
 * @see BaseRepTradeInfoServiceImp
 * @since 1.0
 */
@Service
public class BaseRepTradeInfoServiceImp implements BaseRepTradeInfoService {

    /**
     * RepTradeMapper
     */
    @Autowired
    private RepTradeMapper repTradeMapper;

    /**
     * BaseRepTradeInfoMapper
     */
    @Autowired
    private BaseRepTradeInfoMapper baseRepTradeInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepTrade> list) {
        if (!StringUtil.isEmptyList(list)) {
            for (RepTrade repTrade : list) {
            	if (!StringUtil.isNotEmpty(repTrade.getRepDelCode())) {
            		repTrade.setRepDelCode(repTrade.getRepBankTradeCode());
            	}
            	// 资金流动类型说明
            	repTrade.setRepMoneyFlowTypeExp("无");
            	// 交易对手方
            	repTrade.setRepTradeComponent(baseRepTradeInfoMapper.selectTradeComponent(repTrade.getRepTradeComponent()));
                repTradeMapper.insert(repTrade);
            }
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(BaseRepTradeInfoDto dto) {
        if (dto != null && !StringUtil.isEmptyList(dto.getBankTradeCodeList())) {
            baseRepTradeInfoMapper.delete(dto.getBankTradeCodeList());
            // 删除金融衍生品的报送信息
            baseRepTradeInfoMapper.deleteRepAssetDeri(dto.getBankTradeCodeList());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(BaseRepTradeInfoDto dto) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RepAssetAstBill> select(BaseRepTradeInfoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
