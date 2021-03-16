package com.joyintech.service.trade.transfer.netplantransfer.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.trade.transfer.netplantransfer.dao.NetPlanTransferMapper;
import com.joyintech.service.trade.transfer.netplantransfer.dto.NetPlanTransferDto;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 净值型计划资产转仓Service
 * @author liuwei
 * @version 1.0 2019年12月23日
 * @see NetPlanTransferService
 * @since 1.0
 */
@Service("backNetPlanTransferService")
@Transactional(rollbackFor=Exception.class)
public class NetPlanTransferService extends BaseService<NetPlanTransferDto> {

    /**
     * 注入净值型计划资产转仓mapper
     */
    @Autowired
    private NetPlanTransferMapper netPlanTransferMapper;

    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 单位净值
     * @see
     * @since 1.0
     */
    public BigDecimal getUnitValue(Map<String,Object> map){
        return netPlanTransferMapper.getUnitValue(map);
    }

    /**
     * 获取转仓信息—组合代码
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        map.put("PORTFOLIO_TYPE_01", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("sysDate", DateUtil.getSysDate());
        map.put("ACCT_TYPE_A01",DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return netPlanTransferMapper.getPortfolioId(map);
    }

    /**
     * 查询产品登记编码
     * @return
     * @see
     * @since 1.0
     */
    public String selProdCode(Map<String,Object> map){
        return netPlanTransferMapper.selProdCode(map);
    }

    /**
     * 查询收盘价
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selCloPrice(Map<String,Object> map){
        return netPlanTransferMapper.selCloPrice(map);
    }

    /**
     * 更新交易报送表
     * @return
     * @see
     * @since 1.0
     */
    public Integer updateRep(Map<String,Object> map){
        return netPlanTransferMapper.updateRep(map);
    }

    /**
     * 获取资产类型7
     * @return
     * @see
     * @since 1.0
     */
    public String selectType7(String finprodId){
        return netPlanTransferMapper.selectType7(finprodId);
    }

    /**
     * 获取单位净值列表
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> getUnitValueList(Map<String,Object> map){
        return netPlanTransferMapper.getUnitValueList(map);
    }
}
