package com.joyintech.service.settle.netting.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.dvporder.dao.DvpOrdersManagementMapper;
import com.joyintech.service.settle.netting.dao.NettingManagementMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-06 17:36
 */
@Service
public class NettingManagementService extends BaseService<NettingManagementDto> {

    @Autowired
    private NettingManagementMapper nettingManagementMapper;


    public Page<NettingManagementDto> findNettingTableData(int page, int length, Map<String, Object> param)
            throws Exception {
        setParams(param);
        return queryForPage(page, length, () -> nettingManagementMapper.findNettingTableData(param));
    }





    private void setParams(Map<String, Object> param) {
        if (param.get("portfolioId1") != null) {
            param.put("portfolioId1", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("portfolioId1")));
        }
        else {
            param.put("portfolioId1", null);
        }



        if (param.get("orderType") != null) {
            System.out.println(param.get("orderType"));
            param.put("orderType", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("orderType")));
        }
        else {
            param.put("orderType", null);
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


    public List<NettingManagementDto> findTradeData(Map<String, Object> params) {
        return nettingManagementMapper.findTradeData(params);
    }

    public NettingOrdersDto findDvpOrdersByOrderId(String orderId) {
        Map<String, Object> param = new HashMap<>();
        param.put("PS01", DictCodeListExt1.收支方向_收入.getValue());
        param.put("PS02", DictCodeListExt1.收支方向_支出.getValue());
        param.put("orderId", orderId);
        param.put(BussinessConstant.交易经办, BussinessConstant.交易经办);
        param.put(BussinessConstant.交易复核, BussinessConstant.交易复核);
        param.put("orderId", orderId);
        return nettingManagementMapper.findNettingOrdersByOrderId(param);
    }

    public int findMatchedNum(List<NettingManagementDto> tradeDatas) {
        return nettingManagementMapper.findMatchedNum(tradeDatas);
    }

    public String findOrderIdByTotleTradeId(String TOTLE_TRADE_ID)
    {
        return nettingManagementMapper.findOrderIdByTotleTradeId(TOTLE_TRADE_ID);
    }

    public int updateStlDvpOrdersStatus(Map<String, Object> param){
        return nettingManagementMapper.updateStlDvpOrdersStatus(param);
    }

    public int updateStlDvpTradeMatchStatus(Map<String, Object> param){
        return nettingManagementMapper.updateStlDvpTradeMatchStatus(param);
    }
}
