package com.joyintech.service.settle.dvporder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.dvporder.dao.DvpOrdersManagementMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-03 11:28
 **/
@Service
public class DvpOrdersManagementService extends BaseService<DvpOrdersManagementDto> {

    @Autowired
    private DvpOrdersManagementMapper dvpOrdersManagementMapper;

    public Page<DvpOrdersManagementDto> findTransferTableData(int page, int length, Map<String, Object> param)
        throws Exception {
        setParams(param);
        return queryForPage(page, length, () -> dvpOrdersManagementMapper.findTransferTableData(param));
    }

    public Page<DvpOrdersManagementDto> findDvpTransferTableData(int page, int length, Map<String, Object> param)
        throws Exception {
        setParams(param);
        return queryForPage(page, length, () -> dvpOrdersManagementMapper.findDvpTransferTableData(param));
    }

    private void setParams(Map<String, Object> param) {
        if (param.get("portfolioId") != null) {
            param.put("portfolioId", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("portfolioId")));
        }
        else {
            param.put("portfolioId", null);
        }
        if (param.get("paBusinessType") != null) {
            param.put("paBusinessType", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("paBusinessType")));
        }
        else {
            param.put("paBusinessType", null);
        }
        param.put("PS01", DictCodeListExt1.收支方向_收入.getValue());
        param.put("PS02", DictCodeListExt1.收支方向_支出.getValue());
        param.put("T03", DictCodeList.交易方向_行权_回售.getValue());
        param.put("NO", DictCodeList.是否_否.getValue());
        param.put("TS_01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("DISPOSE_STATUS_01", DictCodeList.处理状态_已处理.getValue());
        param.put("DISPOSE_STATUS_02", DictCodeList.处理状态_未处理.getValue());
        param.put("A08", DictCodeListExt1.资金账户类型_甲类户.getValue());
        param.put("A09", DictCodeListExt1.资金账户类型_上清所DVP账户.getValue());
        param.put("A10", DictCodeListExt1.资金账户类型_中债登DVP账户.getValue());
        param.put("ACCOUNT_SOURCE_01", DictCodeListExt1.账户来源_理财.getValue());
        param.put("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
        param.put("PINAN_BUSINESS_TYPE_09", DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
        param.put("PINAN_BUSINESS_TYPE_10", DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
        param.put("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_19", DictCodeListExt1.平安业务类型_逆回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_75", DictCodeListExt1.平安业务类型_买断式逆回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_74", DictCodeListExt1.平安业务类型_买断式正回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_52", DictCodeListExt1.平安业务类型_正回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_51", DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_77", DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_76", DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_11", DictCodeListExt1.平安业务类型_债券类资产行权.getValue());
        param.put("F29", DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue());
        param.put("F30", DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue());
    }

    public List<DvpOrdersManagementDto> findTradeData(Map<String, Object> params) {
        return dvpOrdersManagementMapper.findTradeData(params);
    }

    public DvpOrdersDto findDvpOrdersByOrderId(String orderId) {
        Map<String, Object> param = new HashMap<>();
        param.put("PS01", DictCodeListExt1.收支方向_收入.getValue());
        param.put("PS02", DictCodeListExt1.收支方向_支出.getValue());
        param.put("orderId", orderId);
        param.put(BussinessConstant.交易经办, BussinessConstant.交易经办);
        param.put(BussinessConstant.交易复核, BussinessConstant.交易复核);
        param.put("orderId", orderId);
        return dvpOrdersManagementMapper.findDvpOrdersByOrderId(param);
    }

    public int findMatchedNum(List<DvpOrdersManagementDto> tradeDatas) {
        return dvpOrdersManagementMapper.findMatchedNum(tradeDatas);
    }
    
    public int updateStlDvpOrdersStatus(Map<String, Object> param){
    	return dvpOrdersManagementMapper.updateStlDvpOrdersStatus(param);
    }
    
    public int updateStlDvpTradeMatchStatus(Map<String, Object> param){
    	return dvpOrdersManagementMapper.updateStlDvpTradeMatchStatus(param);
    }
    
    public int deleteStlDvpOrdersStatus(Map<String, Object> param){
        return dvpOrdersManagementMapper.deleteStlDvpOrdersStatus(param);
    }
    
    public int deleteStlDvpTradeMatchStatus(Map<String, Object> param){
        return dvpOrdersManagementMapper.deleteStlDvpTradeMatchStatus(param);
    }
}
