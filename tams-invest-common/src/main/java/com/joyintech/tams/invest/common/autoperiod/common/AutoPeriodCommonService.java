/*
 * 文件名：AutoPeriodCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.common.base.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflPaTrdFinDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflStlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.WflTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.common.dao.AutoPeriodCommonMapper;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

/**
 * 自动存续期共通service
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see AutoPeriodCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class AutoPeriodCommonService extends BaseService {

    /**
     * WflTrdFinProductDealTotalMapper
     */
    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    /**
     * WflStlPayOrdersMapper
     */
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    /**
     * WflPaTrdFinDealTotalMapper
     */
    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    /**
     * WflTrdProductDealMapper
     */
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    
    /**
     * TrdFinProductDealTotalMapper
     */
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    
    /**
     * StlPayOrdersMapper
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    
    /**
     * PaTrdFinProductDealTotalMapper
     */
    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
    
    /**
     * TrdProductDealMapper
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    /**
     * TrdProductDealAddMapper
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    
    /**
     * PtlTradeMapper
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * AutoPeriodCommonMapper
     */
    @Autowired
    private AutoPeriodCommonMapper autoPeriodCommonMapper;

    public static String change现金流类型To交易方向(String cashType) {
        String ps = null;
        if (StringUtil.isNotEmpty(cashType)) {
            if (cashType.contains(DictCodeList.现金流类型_到期还本.getValue())) {
                ps = DictCodeList.交易方向_到期.getValue();
            }
            else if (cashType.contains(DictCodeList.现金流类型_中间还本.getValue())) {
                ps = DictCodeList.交易方向_还本.getValue();
            }
            else if(cashType.contains(DictCodeList.现金流类型_中间收息.getValue())||cashType.contains(DictCodeList.现金流类型_到期收息.getValue())){
                ps = DictCodeList.交易方向_收息.getValue();
            }
            else {
                ps = DictCodeList.交易方向_提前支取.getValue();
            }
        }
        return ps;
    }

    public static Long changePsToBranch(String mDate, String ps) {
        String date = DateUtil.format(DateUtil.parse(mDate), DateUtil.DEFAULT_OTHER_FORMAT);
        if (DictCodeList.交易方向_到期.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)
        || DictCodeList.交易方向_到期续做.getValue().equals(ps)) {
            return Long.parseLong(date + "01");
        }
        else if (DictCodeList.交易方向_收息.getValue().equals(ps)||DictCodeList.交易方向_收息到账.getValue().equals(ps)) {
            return Long.parseLong(date + "02");
        }
        return null;
    }

    /**
     * 生成业务编号
     *
     * @param parameter 共通业务类型
     * @return 业务编号
     * @throws Exception
     */
    public String buildBussinessCode(BussinessCodeParameter parameter) {
        // 生成的业务编号
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/selectRule", parameter,
            String.class);
    }

    /**
     *
     * 根据汇总交易编号查询交易
     * <功能详细描述>
     * @param busiCode 汇总交易编号
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> findDealByTotalId( String busiCode){
        return restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/findDealByTotalId",
            busiCode, TrdProductDeal.class);
    }

    /**
     * 登录存续期流程数据
     *
     * @param baseTrdDurDbInertDto
     * @see
     * @since 1.0
     */
    public void insertWflData(BaseTrdDurDbInertDto baseTrdDurDbInertDto) {
        if (baseTrdDurDbInertDto == null) {
            return;
        }

        if (baseTrdDurDbInertDto.getWflTrdFinProductDealTotal() != null) {
            wflTrdFinProductDealTotalMapper.insert(baseTrdDurDbInertDto.getWflTrdFinProductDealTotal());
        }

        if (!StringUtil.isEmptyList(baseTrdDurDbInertDto.getWflStlPayOrdersList())) {
            for (WflStlPayOrders wflStlPayOrders : baseTrdDurDbInertDto.getWflStlPayOrdersList()) {
                wflStlPayOrdersMapper.insert(wflStlPayOrders);
            }
        }

        if (baseTrdDurDbInertDto.getWflPaTrdFinDealTotal() != null) {
            wflPaTrdFinDealTotalMapper.insert(baseTrdDurDbInertDto.getWflPaTrdFinDealTotal());
        }

        if (!StringUtil.isEmptyList(baseTrdDurDbInertDto.getWflTrdProductDealList())) {
            for (WflTrdProductDeal wflTrdProductDeal : baseTrdDurDbInertDto.getWflTrdProductDealList()) {
                wflTrdProductDealMapper.insert(wflTrdProductDeal);
            }
        }
    }

    /**
     * 登录交易相关有效数据
     *
     * @param baseTrdDurDbInertDto BaseTrdDurDbInertDto
     * @param finProductTradeDto FinProductTradeDto
     * @see
     * @since 1.0
     */
    public void insertDealData(BaseTrdDurDbInertDto baseTrdDurDbInertDto, FinProductTradeDto finProductTradeDto) {
        if (baseTrdDurDbInertDto == null) {
            return;
        }

        if (baseTrdDurDbInertDto.getTrdFinProductDealTotal() != null) {
            trdFinProductDealTotalMapper.insert(baseTrdDurDbInertDto.getTrdFinProductDealTotal());
        }

        if (!StringUtil.isEmptyList(baseTrdDurDbInertDto.getStlPayOrdersList())) {
            for (StlPayOrders stlPayOrders : baseTrdDurDbInertDto.getStlPayOrdersList()) {
                stlPayOrdersMapper.insert(stlPayOrders);
            }
        }

        if (baseTrdDurDbInertDto.getPaTrdFinProductDealTotal() != null) {
            paTrdFinProductDealTotalMapper.insert(baseTrdDurDbInertDto.getPaTrdFinProductDealTotal());
        }

        if (!StringUtil.isEmptyList(baseTrdDurDbInertDto.getTrdProductDealList())) {
            for (int i = 0; i < baseTrdDurDbInertDto.getTrdProductDealList().size(); i++ ) {
                TrdProductDeal trdProductDeal = baseTrdDurDbInertDto.getTrdProductDealList().get(
                    i);
                TrdProductDealAdd trdProductDealAdd = baseTrdDurDbInertDto.getTrdProductDealAdds().get(
                    i);
                PtlTrade ptlTrade = baseTrdDurDbInertDto.getPtlTrades().get(i);
                trdProductDealMapper.insert(trdProductDeal);
                trdProductDealAddMapper.insert(trdProductDealAdd);
                ptlTradeMapper.insert(ptlTrade);
                List<PtlTrade> ptlTradeList = new ArrayList<PtlTrade>();
                ptlTradeList.add(ptlTrade);
                if (StringUtil.isNotBlank(baseTrdDurDbInertDto.getActPortfolioId())
                    && StringUtil.isNotBlank(trdProductDealAdd.getPortfolioId())
                    && !StringUtil.equals(trdProductDealAdd.getPortfolioId(),
                        baseTrdDurDbInertDto.getActPortfolioId())) {
                    PtlTrade ptlTrade2 = new PtlTrade();
                    BeanUtil.copyPropertiesIgnoreNull(ptlTrade2, ptlTrade);
                    ptlTrade2.setPortfolioId(baseTrdDurDbInertDto.getActPortfolioId());
                    ptlTradeMapper.insert(ptlTrade2);
                    ptlTradeList.add(ptlTrade2);
                }
            }
            // 回购到期 调用lendMdateAdd估值接口
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(
                baseTrdDurDbInertDto.getTrdProductDealAdds().get(0).getPortfolioId());
            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
            inputDto.setTrdProductDealList(baseTrdDurDbInertDto.getTrdProductDealList());
            inputDto.setRepoType(finProductTradeDto.getAssetType2());
            inputDto.setStlPayOrders(baseTrdDurDbInertDto.getStlPayOrdersList().get(0));
            AllTradeCommonUtil.lendMdateAdd(inputDto);
            if (StringUtil.isNotEmpty(baseTrdDurDbInertDto.getActPortfolioId())
                && !StringUtil.equals(
                    baseTrdDurDbInertDto.getTrdProductDealAdds().get(0).getPortfolioId(),
                    baseTrdDurDbInertDto.getActPortfolioId())) {
                baseTrdDurDbInertDto.getTrdProductDealList().forEach(item -> {
                    item.setTradeId(item.getTradeId() + 'S');
                });
                inputDto.setProdid(baseTrdDurDbInertDto.getActPortfolioId());
                AllTradeCommonUtil.lendMdateAdd(inputDto);
            }
        }
        

    }

    /**
     * 删除流程数据
     *
     * @param busiType 业务类型
     * @param finProdType2 金融产品类型（投管）
     * @see
     * @since 1.0
     */
    public void deleteWflData(String busiType, String finProdType2) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("busiType", busiType);
        paramMap.put("finProdType2", finProdType2);

        autoPeriodCommonMapper.deleteWflStlPayOrdersData(paramMap);
        autoPeriodCommonMapper.deleteWflPaTrdFinDealTotalData(paramMap);
        autoPeriodCommonMapper.deleteWflTrdFinProductDealTotalData(paramMap);
        autoPeriodCommonMapper.deleteWflTrdProductDeal(paramMap);
    }
    /**
     * 根据组合信息获取子组合利息明息
     * @param map 参数
     * @return 组装结果
     */
    public List<FinAssetIntInfo> distributeDataByPercent(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/distributeDataByPercent", map,
                FinAssetIntInfo.class);
    }
    /**
     * 一个交易明细一个划款指令
     *
     * @param stlPayOrdersList 划款指令list
     * @param total 汇总交易entity
     * @param workflowId 工作流编号
     * @return 生成结果
     */
    public StlPayOrders gen划款指令流程(List<BussTrdTransferOrderDto> stlPayOrdersList, TrdFinProductDealTotal total,
                                   String workflowId) {

        StlPayOrders stlPayOrders = null;
        for (BussTrdTransferOrderDto dto : stlPayOrdersList) {
            if(StringUtil.equals(dto.getInvAim(),"999999")&&StringUtil.equals(total.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){ // 生成划款指令，无投资目的时设置成卖空，因为汇总表total就是这么处理的
                dto.setInvAim(DictCodeListExt1.投资目的_卖空.getValue());
            }
            if (dto.getPortfolioId().equals(total.getPortfolioId()) && ((StringUtil.isEmpty(dto.getInvAim()) && StringUtil.isEmpty(total.getInvAim()))
                    || (dto.getInvAim() != null && dto.getInvAim().equals(total.getInvAim())))) {
                stlPayOrders = dto;
                if(StringUtil.isNotEmpty(stlPayOrders.getSettleAmt())){
                    stlPayOrders.setSettleAmt(StringUtil.isNotEmpty(total.getFpriceAmt())?total.getFpriceAmt():total.getTradeAmt());
                }
                stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                stlPayOrders.setTradeId(total.getTotleTradeId());
                stlPayOrders.setWorkflowId(workflowId);
            }
        }
        return stlPayOrders;
    }
    public List<BussTrdTransferOrderDto> save(List<BussTrdTransferOrderDto> dto) {
        return dto.stream().map(item -> {
            BussTrdTransferOrderDto orders = new BussTrdTransferOrderDto();
            BeanUtil.copyPropertiesIgnoreNull(orders, item);
            if (StringUtil.equals(item.getPs(), DictCodeListExt1.收支方向_支出.getValue())) {
                orders.setMyAcctNo(item.getPayAcctNo());
                orders.setCounterAcctNo(item.getRecAcctNo());
            }
            else {
                orders.setMyAcctNo(item.getRecAcctNo());
                orders.setCounterAcctNo(item.getPayAcctNo());
            }
            if (StringUtil.isNotEmpty(orders.getIsNeedSend())) {
                if (DictCodeList.是否_是.getCodeName().equals(orders.getIsNeedSend())) {
                    orders.setIsNeedSend(DictCodeList.是否_是.getValue());
                }
                else if (DictCodeList.是否_否.getCodeName().equals(orders.getIsNeedSend())) {
                    orders.setIsNeedSend(DictCodeList.是否_否.getValue());
                }
            }
            return orders;
        }).collect(Collectors.toList());
    }

    /**
     * 查询交易本金（通过估值）
     * @param map 参数
     * @return
     */
    public BigDecimal genPrinAmtByVal(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoBuyoutDuration/genPrinAmtByVal", map,
                BigDecimal.class);
    }

    public List<PtlSecPosition> findAllProvided(Map<String, Object> params) {
        return autoPeriodCommonMapper.findAllProvided(params);
    }
}
