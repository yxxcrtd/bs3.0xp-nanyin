/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribbondinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etribbondinfo.dao.EtrIbBondinfo001Mapper;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrIbBondinfo001Dto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrTrdProductDealDto;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.systemtools.interf.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbBondinfo001Service
 * @since 1.0
 */
@Service("backEtrIbBondinfo001Service")
public class EtrIbBondinfo001Service extends BaseService{

    @Autowired
    private EtrIbBondinfo001Mapper etrIbBondinfo001Mapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbBondinfo001Dto> findPositionsPage(int page, int length,
                                                       Map<String, Object> pageParam) {
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", BussinessDictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", BussinessDictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", BussinessDictCodeList.持仓类型_融出.getValue());
        pageParam.put("VALUATION_SOURCE_CSD", BussinessDictCodeList.估值来源_中证登.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        
        addCondionTradeMarket(pageParam); // 交易场所，仅限银行间指令
        
        Page<EtrIbBondinfo001Dto> result = super.queryForPage(page, length, () -> etrIbBondinfo001Mapper.findPositionsPage(pageParam));

        //根据资产代码查询相关所有的交易信息
        List<EtrTrdProductDealDto> tradeList = etrIbBondinfo001Mapper.getTradeListByFinprodId(pageParam);
        if (result != null && !result.getRoot().isEmpty()) {
            for (EtrIbBondinfo001Dto dto : result.getRoot()) {
                //昨日持仓数量
                BigDecimal yesterDayPosAmount = dto.getCostPositionNum() == null ? BigDecimal.ZERO : dto.getCostPositionNum();
                //昨日综合成本
                BigDecimal yesterDaycostPosition = dto.getCostPosition() == null ? BigDecimal.ZERO : dto.getCostPosition();
                if (!StringUtil.isEmptyList(tradeList)) {
                    for (EtrTrdProductDealDto trdProductDeal : tradeList) {
                        if (trdProductDeal.getFinprodId().equals(dto.getFinprodId())
                            && trdProductDeal.getPortfolioId().equals(dto.getPortfolioId())) {
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
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
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_卖出.getValue())) {
                                yesterDayPosAmount = yesterDayPosAmount.subtract(trdProductDeal.getShareAmt() == null ? new BigDecimal(0) : trdProductDeal.getShareAmt());
                            }
                        }
                    }
                }
                //持仓成本=持仓券面总额(万元)*持仓单位成本*100
                dto.setPositionsAmt(ArithmeticUtil.multiply(dto.getShareAmt(), yesterDaycostPosition, new BigDecimal(100)));
                //浮动盈亏 = 持仓市值-持仓成本
                dto.setFloatingPl(ArithmeticUtil.subtract(dto.getPositionsPrice(), dto.getPositionsAmt()));
            }
        }
        return result;

    }

    /**
     * 只包含交易场所：01 银行间 
     * @param pageParam 查询参数
     */
    private void addCondionTradeMarket(Map<String, Object> pageParam) {
        List<String> tradeMarketList = new ArrayList<>();
        tradeMarketList.add(DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("tradeMarketList", tradeMarketList);
    }

    /**
     * 债券质押持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbBondinfo001Dto> findZqzyPositionsPage(int page, int length,
            Map<String, Object> pageParam) {
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_MARKET_01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("ENTRUST_STATUS_08", DictCodeList.委托状态_流程中.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        List<EtrIbBondinfo001Dto> list = etrIbBondinfo001Mapper.findZqzyPositionsPage(pageParam);

        return new Page<EtrIbBondinfo001Dto>(list);
    }

    /**
     * 获得债券信息
     * @param pageParam
     * @return
     */
    public EtrFinProductDto getEtrFinProductInfo(Map<String, Object> pageParam) {
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        EtrFinProductDto dto = etrIbBondinfo001Mapper.getEtrFinProductInfo(pageParam);
        if (dto != null) {
            //剩余期限为负统一为0
            if (dto.getLastqx() != null && dto.getLastqx().compareTo(BigDecimal.ZERO) < 0) {
                dto.setLastqx(BigDecimal.ZERO);
            }
            if (dto.getLastqx() != null) {
                dto.setLastqx(ArithmeticUtil.divide(dto.getLastqx(), new BigDecimal(365), 2));
            }
            String xqfangshi = "";
            List<String> plan = etrIbBondinfo001Mapper.getEtrFinExerciseInfo(pageParam);
            if (plan != null && plan.size() > 0) {
                xqfangshi = StringUtil.joinString(plan,";");
            }
            dto.setXqFlg(xqfangshi);
        }
        return dto;
    }
}
