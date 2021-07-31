package com.joyintech.service.entrust.etrastconfigtransfer.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrastconfigtransfer.dao.EtrAssetConfigTransferMapper;
import com.joyintech.service.entrust.etrastconfigtransfer.dto.EtrAssetConfigDto;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 资产配置快速转仓指令
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransferService
 * @since 1.0
 */
@Service("backEtrAssetConfigTransferService")
public class EtrAssetConfigTransferService extends BaseService<PtlPortfolio>{

    /**
     * 自定义mapper
     */
    @Autowired
    private EtrAssetConfigTransferMapper etrAssetConfigTransferMapper;

    public Page<PtlPortfolio> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        return queryForPage(page, length, () -> etrAssetConfigTransferMapper.selectForPage(param));
    }


    public List<EtrAssetConfigDto> getAstPosition(Map<String, Object> param){
        param.put("POSITION_TYPE_030506", RiskUtil.convertPortfCode(DictCodeList.持仓类型_实际.getValue()+GlobalConstant.COMMA+DictCodeList.持仓类型_融入.getValue()+GlobalConstant.COMMA+DictCodeList.持仓类型_融出.getValue()));
        param.put("ACCT_TYPE_A01",DictCodeListExt1.资金账户类型_产品托管户.getValue());
        param.put("CASH_TYPE_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        param.put("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        param.put("FIN_CASH_BUSINESS_TYPE_01", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        param.put("TRADE_PLACE_04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        param.put("VALUATION_SOURCE_CBC", DictCodeList.估值来源_中债登_CBC.getValue());
        param.put("VALUATION_SOURCE_CSD", DictCodeList.估值来源_中证登_CSD.getValue());
        return etrAssetConfigTransferMapper.getAstPosition(param);
    }

}
