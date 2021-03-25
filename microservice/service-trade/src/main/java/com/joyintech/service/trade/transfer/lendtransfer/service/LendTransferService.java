package com.joyintech.service.trade.transfer.lendtransfer.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.trade.transfer.lendtransfer.dao.LendTransferMapper;
import com.joyintech.service.trade.transfer.lendtransfer.dto.LendTransferDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 拆借资产转仓Service
 * @author liuwei
 * @version 1.0 2020年02月18日
 * @see LendTransferService
 * @since 1.0
 */
@Service("backLendTransferService")
@Transactional(rollbackFor=Exception.class)
public class LendTransferService extends BaseService<LendTransferDto> {

    /**
     * 注入拆借资产转仓mapper
     */
    @Autowired
    private LendTransferMapper lendTransferMapper;
    
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 获取转仓基本信息
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public LendTransferDto getTradeInfo(String tradeId){
        Map<String,Object> map = new HashMap<>();
        map.put("tradeId",tradeId);
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YESORNO_Y", DictCodeListExt1.是否_是.getValue());
        map.put("sysDate", DateUtil.getSysDate());
        return lendTransferMapper.getTradeInfo(map);
    }

    /**
     * 获取转仓信息—组合代码
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        map.put("PORTFOLIO_TYPE_01", DictCodeListExt1.组合类型_投资类组合.getValue());
        //map.put("sysDate", DateUtil.getSysDate());
        map.put("ACCT_TYPE_A01",DictCodeListExt1.资金账户类型_产品托管户.getValue());
        List<SelectOptionDto> result =  lendTransferMapper.getPortfolioId(map);
        if(basePtlCashPositionInfoService != null){
            Iterator<SelectOptionDto> iterator =  result.iterator();
            while(iterator.hasNext()) {
            	SelectOptionDto dto = iterator.next();
                PtlCashPositionFlowDto pcpdto = new PtlCashPositionFlowDto();
                Date sysdate = DateUtil.parse((String)map.get("tradeDate"));
                pcpdto.setCdate(sysdate);
                List<String> acctTypeList = new ArrayList<>();
                acctTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
                acctTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
                pcpdto.setAcctType(acctTypeList);
                pcpdto.setPortfolioId(dto.getId());
                pcpdto.setCcy((String)map.get("ccy"));
                BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(pcpdto);
                if(cashT.compareTo(new BigDecimal(0))<=0) {
                	iterator.remove();
                }
            }
        }
        return result;
    }
    
    /**
     * 获取处于流程中的持仓（但是未记录在在持仓流水中）
     * @return 处于流程中的持仓（但是未记录在在持仓流水中）
     * @see
     * @since 1.0
     */
    public BigDecimal selectWflAmtLend(Map<String,Object> map){
        return lendTransferMapper.selectWflAmtLend(map);
    }
}
