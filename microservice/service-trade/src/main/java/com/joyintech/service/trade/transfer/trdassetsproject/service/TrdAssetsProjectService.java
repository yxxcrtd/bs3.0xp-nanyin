package com.joyintech.service.trade.transfer.trdassetsproject.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.trdassetsproject.dao.TrdAssetsProjectMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("backTrdAssetsProjectService")
public class TrdAssetsProjectService {

    @Autowired
    TrdAssetsProjectMapper trdAssetsProjectMapper;
    /**
     * 查询已拆分本金金额
     * @param param 参数
     * @return 已拆分本金金额
     */
    public com.joyintech.service.trade.transfer.trdassetsproject.dto.ProjSplitInfoDto selectSplitInfo(Map<String, Object> param) {
        return trdAssetsProjectMapper.selectSplitInfo(param);
    }

    public List<SelectOptionDto> selectPortfolioId(Map<String, Object> param) {
        param.put("capacctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return trdAssetsProjectMapper.selectPortfolioId(param);
    }

    /**
     * 查询划款指令
     * @param param 参数
     * @return 划款指令
     */
    public List<BussTrdTransferOrderDto> selectBussTrdTraOrderByTradeId(Map<String, Object> param) {
        return trdAssetsProjectMapper.selectBussTrdTraOrderByTradeId(param);
    }
}
