
package com.joyintech.service.entrust.etrstockconnect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrstockconnect.dao.EtrStockConnect001Mapper;
import com.joyintech.service.entrust.etrstockconnect.dao.EtrStockConnect002Mapper;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectPoDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrTrdProductDealDto;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *港股通指令
 * @see EtrStockConnect001Service
 * @since 1.0
 */
@Service("backEtrStockConnect001Service")
public class EtrStockConnect001Service extends BaseService {

    @Autowired
    private EtrStockConnect001Mapper etrStockConnect001Mapper;

    @Autowired
    private EtrStockConnect002Mapper etrStockConnect002Mapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 指令管理查询
     */
    public List<EtrStockConnectDto> selectEtrStockConnect(Map<String,Object> param){
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        param.put("TRADE_PLACE_06", DictCodeListExt1.交易场所_香港联合交易所.getValue());
        return etrStockConnect001Mapper.selectEtrStockConnect(param);
    }


    /**
     * 持仓信息查询
     */
    public Page<EtrStockConnectPoDto> findPositionsPage(int page, int length,
                                                        Map<String, Object> param) {

        String portfolioId = (String)param.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)) {
            param.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
        }
        String hkseMarket = (String)param.get("hkseMarket");
        if(StringUtil.equals(hkseMarket,DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())){
            param.put("tradePlace", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        }
        else if(StringUtil.equals(hkseMarket,DictCodeListExt2.是否沪港通或深港通_深港通.getValue())){
            param.put("tradePlace", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        }

        param.put("sysDate", iPlatformDate.getSysDate());
        param.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
        param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        param.put("PS_T01", BussinessDictCodeList.交易方向_买入.getValue());
        param.put("PS_T02", BussinessDictCodeList.交易方向_卖出.getValue());
        param.put("PS_T03", BussinessDictCodeList.交易方向_行权回售.getValue());
        param.put("PS_T05", BussinessDictCodeList.交易方向_申购确认.getValue());
        param.put("PS_T19", BussinessDictCodeList.交易方向_赎回确认.getValue());
        param.put("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
        param.put("PS_T47", DictCodeListExt2.交易方向_增发.getValue());
        param.put("PS_T48", DictCodeListExt2.交易方向_新股中签.getValue());
        param.put("PS_T49", DictCodeListExt3.交易方向_债转股.getValue());
        param.put("PS_T97", DictCodeListExt3.交易方向_配债.getValue());
        param.put("PS_T04", DictCodeList.交易方向_申购.getValue());
        param.put("TRADE_PLACE_06", DictCodeListExt1.交易场所_香港联合交易所.getValue());
        Page<EtrStockConnectPoDto> result = super.queryForPage(page, length, () -> etrStockConnect001Mapper.findPositionsPage(param));
        //根据资产代码查询相关所有的交易信息
        List<EtrTrdProductDealDto> tradeList = etrStockConnect001Mapper.getTradeListByFinprodId(param);
        if(!StringUtil.isEmptyList(result.getRoot())){
            for (EtrStockConnectPoDto dto : result.getRoot()) {
                //昨日持仓数量
                BigDecimal yesterDayPosAmount = dto.getCostPositionNum() == null ? BigDecimal.ZERO : dto.getCostPositionNum();
                //昨日综合成本
                BigDecimal yesterDaycostPosition = dto.getCostPosition() == null ? BigDecimal.ZERO : dto.getCostPosition();
                if (!StringUtil.isEmptyList(tradeList)) {
                    for (EtrTrdProductDealDto trdProductDeal : tradeList) {
                        if (trdProductDeal.getFinprodId().equals(dto.getFinprodId())
                                && trdProductDeal.getPortfolioId().equals(dto.getPortfolioId())) {
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_买入.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeList.交易方向_申购确认.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeList.交易方向_认购确认.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeListExt2.交易方向_增发.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())) {
                                BigDecimal shareAmt = trdProductDeal.getShareAmt() == null ? BigDecimal.ZERO : trdProductDeal.getShareAmt();
                                BigDecimal unitCprice = trdProductDeal.getUnitCprice() == null ? BigDecimal.ZERO : trdProductDeal.getUnitCprice();
                                if (ArithmeticUtil.doAdd(yesterDayPosAmount, shareAmt).compareTo(BigDecimal.ZERO) != 0) {
                                    //综合成本=昨日成本*昨日持仓数量+净价*份额
                                    yesterDaycostPosition = ArithmeticUtil.multiply(yesterDaycostPosition, yesterDayPosAmount);
                                    yesterDaycostPosition = ArithmeticUtil.doAdd(yesterDaycostPosition, ArithmeticUtil.multiply(shareAmt, unitCprice));
                                    //今日数量= 昨日持仓数量 + 份额
                                    yesterDayPosAmount = ArithmeticUtil.doAdd(yesterDayPosAmount, shareAmt);
                                    //单位成本= 综合成本 / 今日数量
                                    yesterDaycostPosition = ArithmeticUtil.divide(yesterDaycostPosition, yesterDayPosAmount, 12);
                                } else {
                                    yesterDayPosAmount = BigDecimal.ZERO;
                                    yesterDaycostPosition = BigDecimal.ZERO;
                                }
                            }
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_卖出.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeListExt2.交易方向_卖碎股.getValue())
                                    || trdProductDeal.getPs().equals(DictCodeList.交易方向_赎回确认.getValue())) {
                                yesterDayPosAmount = yesterDayPosAmount.subtract(trdProductDeal.getShareAmt() == null ? new BigDecimal(0) : trdProductDeal.getShareAmt());
                            }
                        }
                    }
                }
                //持仓单位成本
                dto.setCostPosition(yesterDaycostPosition);
                dto.setCostPositionNum(yesterDayPosAmount);
                //dto.setPositionsAmt(yesterDaycostPosition);
                //持仓成本
                dto.setPositionsAmt(ArithmeticUtil.multiply(dto.getPositionsNum(), yesterDaycostPosition));
            }
            // 计算浮动盈亏
            for(EtrStockConnectPoDto dto : result.getRoot()){
                // 最新净价*组合持仓.数量 - 持仓成本 as 浮动盈亏
                if(dto.getPrice() != null && dto.getPositionsNum() !=null && dto.getPositionsAmt()!=null){
                    BigDecimal floatPL = ArithmeticUtil.multiply(dto.getPrice(),dto.getPositionsNum());
                    floatPL = ArithmeticUtil.subtract(floatPL,dto.getPositionsAmt());
                    dto.setFloatingPL(floatPL);
                }else{
                    dto.setFloatingPL(null);
                }
            }
            // 港股通市场
            for(EtrStockConnectPoDto dto : result.getRoot()){
                Map<String, Object> paramSec = new HashMap<>();
                paramSec.put("secAcctId", dto.getSecAcctId());
                StlSecAcctInfo stlSecAcctInfo = etrStockConnect002Mapper.selectStlSecAcctInfo(paramSec);
                if(stlSecAcctInfo != null && StringUtil.equals(stlSecAcctInfo.getTradePlace(),DictCodeListExt1.交易场所_上海证券交易所.getValue())){
                    dto.setHkseMarket(DictCodeListExt2.是否沪港通或深港通_沪港通.getValue());
                }
                else if(stlSecAcctInfo != null && StringUtil.equals(stlSecAcctInfo.getTradePlace(),DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
                    dto.setHkseMarket(DictCodeListExt2.是否沪港通或深港通_深港通.getValue());
                }
            }
        }

        return result;

    }

}
