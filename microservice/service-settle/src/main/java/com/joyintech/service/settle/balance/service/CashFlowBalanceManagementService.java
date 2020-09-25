package com.joyintech.service.settle.balance.service;

import static com.joyintech.tams.invest.common.util.SettleCommonUtil.setParams;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.balance.dao.CashFlowBalanceManagementMapper;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseCashFlowBalanceManagementDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-03 13:59
 **/

@Service
public class CashFlowBalanceManagementService extends BaseService<BaseCashFlowBalanceManagementDto> {

    @Autowired
    private CashFlowBalanceManagementMapper cashFlowBalanceManagementMapper;

    public Page<SelectOptionDto> findCounterAcctNo(int page, int length, Map<String, Object> params) {
        return queryForPageForSelectReturn(page, length, () -> cashFlowBalanceManagementMapper.findCounterAcctNo(params));
    }

    public List<SuggestDto> findCounterAcctNoForSugg(String search) {
        return cashFlowBalanceManagementMapper.findCounterAcctNoForSugg(search);
    }
    
    public List<SuggestDto> findAcctNoByTg(String search) {
        return cashFlowBalanceManagementMapper.findAcctNoByTg(search);
    }
    
    public Page<BaseCashFlowBalanceManagementDto> findPage(int page, int length, Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("finprodId"))) {
            List<String> finprodId = StringUtil.splitToList(",", (String)params.get("finprodId"));
            params.put("finprodId",finprodId );
        }
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(params.get("prodManager"))){
            params.put("prodManager", StringUtil.splitToList(null, params.get("prodManager").toString()));
        } else {
            params.put("prodManager", null); 
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end  
        params.put("cdFlowFlag", DictCodeList.托管流水借贷方向_贷.getValue());
        Page<BaseCashFlowBalanceManagementDto> a = queryForPageForSelectReturn(page, length, () -> cashFlowBalanceManagementMapper.findPage(params));
        return a;
    }
}
