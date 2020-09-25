package com.joyintech.service.settle.netting.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.netting.dao.NettingManagementMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-19 15:32
 */

@Service
public class TransferMessageService extends BaseService<TransferMessageDto> {

    @Autowired
    private NettingManagementMapper nettingManagementMapper;


    public Page<TransferMessageDto> findTransferMessage(int page, int length, Map<String, Object> param)
            throws Exception {
        setParams(param);
        return queryForPage(page, length, () -> nettingManagementMapper.transferMessage(param));
    }



    private void setParams(Map<String, Object> param) {


        if (param.get("portid") != null) {
            param.put("portid", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("portid")));
        }
        else {
            param.put("portid", null);
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



}
