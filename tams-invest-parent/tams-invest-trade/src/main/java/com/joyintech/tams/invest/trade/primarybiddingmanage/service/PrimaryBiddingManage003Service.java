package com.joyintech.tams.invest.trade.primarybiddingmanage.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BidOrderBidPriceMapper;
import com.joyintech.tams.common.base.dao.BidOrderMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade004Dto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingPortfolioDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author : 裴家俊
 * @version V1.0
 */
@Service
public class PrimaryBiddingManage003Service extends BaseService<TrdBiddingManage003Dto> implements BaseProcessService<TrdBiddingManage003Dto> {

    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;

    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private BidOrderMapper bidOrderMapper;
    @Autowired
    private  BidOrderBidPriceMapper bidOrderBidPriceMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdBiddingManage003Dto entity, OperationType type) {
        String action =  StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            //根据投标编号查询指令确认状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bidId", entity.getBidId());
            PrimaryBiddingManageDto primaryBiddingManageOriginalDto = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage002/getPrimaryBiddingManageOriginal",
                map, PrimaryBiddingManageDto.class);
            //投标指令
            BidOrder bidOrder = new BidOrder();
            bidOrder.setBidId(entity.getBidId());
            //确认状态
            String tenderConfirmStatus = "";
            if (StringUtil.equals(primaryBiddingManageOriginalDto.getTenderconfirmstatus(), BussinessDictCodeList.确认状态_已中标.getValue())) {
                tenderConfirmStatus = null;
            }
            bidOrder.setIsWin(tenderConfirmStatus);
            bidOrder.setFinprodId(entity.getFinprodId());
            bidOrder.setBidDate(entity.getBidDate());
            //更新投标指令表
//            bussCommonMapperDelegateService.updateByPrimaryKey(BidOrderMapper.class, bidOrder);
            bidOrderMapper.updateByPrimaryKey(bidOrder);
            //更新投标指令标位表
            if (!StringUtil.isEmptyList(entity.getBidOrderBidPriceList())) {
                //投标指令标位表
                for (BidOrderBidPriceDto bidOrderBidPriceDto : entity.getBidOrderBidPriceList()) {
                    BidOrderBidPrice bidOrderBidPrice = new BidOrderBidPrice();
                    bidOrderBidPrice.setBidId(entity.getBidId());
                    bidOrderBidPrice.setEntrustId(bidOrderBidPriceDto.getEntrustId());
                    bidOrderBidPrice.setBidPrice(bidOrderBidPriceDto.getBidPrice().divide(BigDecimal.valueOf(100)));
                    bidOrderBidPrice.setIsWin(tenderConfirmStatus);
//                    bussCommonMapperDelegateService.updateByPrimaryKey(BidOrderBidPriceMapper.class, bidOrderBidPrice);
                    bidOrderBidPriceMapper.updateByPrimaryKey(bidOrderBidPrice);
                }
            }
            return;
        } else if (OperationType.NEW.equals(type)) {
            action = "update";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/" + action, entity,
            Integer.class);
    }

    public List<BidOrderBidPriceDto> findBidPrice(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/findBidPrice", params,
            BidOrderBidPriceDto.class);
    }

    public List<FinProduct> findFinProduct(Map<String,Object> params){
        return restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findFinProductByOFinprodId", params,
                FinProduct.class);
    }


//    @Override
//    public void doUpdate(TrdBiddingManage003Dto entity) {
//        if (!StringUtil.equals("revoke", entity.getOperateType())
//                && !StringUtil.equals("delete", entity.getOperateType())) {
//            // 先删除流程中数据
//            this.doDelete(entity.getWorkflowId());
//
//            this.doComplete(entity,OperationType.NEW);
//        }
//    }


    /**
     * 计算 中标净价
     * @return
     */
    public BigDecimal calculateWinCprice(Map<String, Object> params,BigDecimal winPrice,BigDecimal winPrinAmt,String entrustId){
        params.put("oFinprodId",params.get("finprodId"));
        params.put("entrustId",entrustId);
        //得到债券资产信息
        List<FinProduct> finProducts = restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findFinProductByOFinprodId", params,
                FinProduct.class);
        FinProduct finProduct = finProducts.get(0);
        EtrEntrust etrEntrust = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME +
                "/PrimaryBiddingManage003/selectInfoByEntrustId", params, EtrEntrust.class);
        TrdBondInvestTradeDto inputDto = new TrdBondInvestTradeDto();
        inputDto.setCalculateField("yield");
        inputDto.setCcy(finProduct.getCcy());
        inputDto.setFinprodId(finProduct.getFinprodId());
        inputDto.setFinprodType2(finProduct.getFinprodType2());
        inputDto.setInvAim(etrEntrust.getInvAim());
        inputDto.setMdate(finProduct.getMdate());
        inputDto.setVdate(finProduct.getVdate());
        inputDto.setSettleDate(DateUtil.platDateFromCache());
        inputDto.setTradeMarket("01");
        inputDto.setTradeAmt(winPrinAmt);
        inputDto.setTradeDate(DateUtil.platDateFromCache());
        inputDto.setStatus("N");
        inputDto.setWindtypeone("B01");
        inputDto.setYield(winPrice);
        AjaxResult ajaxResult = trdBondInvestTrade002Service.calculateData(inputDto);
        TrdBondInvestTradeDto data = (TrdBondInvestTradeDto)ajaxResult.getData();
        return data.getUnitCprice();
    }

    public List<BidOrderBidPriceDto> getBidDetailByBidId(String params) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bidId", params);
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/getBidDetailByBidId", map,
            BidOrderBidPriceDto.class);
    }

    /**
     * 根据投标编号查询相关信息
     *
     * @param
     * @return 相关信息
     */
    public TrdBiddingManage003Dto getBussinessEntityByBidId(String bidId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bidId", bidId);
        return restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/getBussinessEntityByBidId",
            map, TrdBiddingManage003Dto.class);
    }

    public String findProcessStatusByWorkflowId(String workflowId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workflowId", workflowId);
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/findProcessStatusByWorkflowId",
                map, String.class);
    }
    public Boolean findTradeIdByBidId(String bidId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bidId", bidId);
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage003/findTradeIdByBidId",
                map, Boolean.class);
    }

    /**
     * 调整中签量
     * @param dtoList
     * @return
     */
    public List<BidOrderBidPriceDto> adjustWinAmt(List<BidOrderBidPriceDto> dtoList, BigDecimal winPrinAmt){
        String adjustWinAmt = CacheUtil.getSysParam(BussinessConstant.ADJUST_WINNING_AMOUNT) == null ? "N" : CacheUtil.getSysParam(BussinessConstant.ADJUST_WINNING_AMOUNT);
        //如果设置了调整中签量
        if (!StringUtil.equals(adjustWinAmt,"N")) {
            Collections.sort(dtoList, new Comparator<BidOrderBidPriceDto>() {
                @Override
                public int compare(BidOrderBidPriceDto o1, BidOrderBidPriceDto o2) {
                    int i = o1.getWinPrinAmt().compareTo(o2.getWinPrinAmt());
                    if(i == 0){
                        return o1.getWinPrinAmt().compareTo(o2.getWinPrinAmt());
                    }
                    return i;
                }
            });

            //尾差
            double tailDifference = winPrinAmt.doubleValue() % Double.parseDouble(adjustWinAmt);
            //得到可以分配的中签量
            List<BidOrderBidPriceDto> bidOrderBidPriceDtos = new ArrayList<>();
            for (BidOrderBidPriceDto bidOrderBidPriceDto : dtoList) {
                //如果投标标位小于系统参数中配置的调整中签量，则全部舍去
                if (bidOrderBidPriceDto.getBidPrinAmt().compareTo(BigDecimal.valueOf(Double.parseDouble(adjustWinAmt))) < 0){
                    tailDifference = tailDifference + Double.parseDouble(adjustWinAmt);
                    bidOrderBidPriceDto.setWinPrice(null);
                    bidOrderBidPriceDto.setWinPrinAmt(null);
                    bidOrderBidPriceDto.setTheWinPrinAmt(null);
                    bidOrderBidPriceDto.setWinCprice(null);
                }
                bidOrderBidPriceDtos.add(bidOrderBidPriceDto);
            }
            //调整中签量
            for (BidOrderBidPriceDto bidOrderBidPriceDto : bidOrderBidPriceDtos) {

            }

        }
        return null;
    }

    /**
     * 取余
     * @param orgNum 被除数
     * @param divNum 除数
     * @return
     */
    private BigDecimal takeSurplus(BigDecimal orgNum,BigDecimal divNum){
        return ArithmeticUtil.subtract(orgNum,ArithmeticUtil.multiply(divNum,ArithmeticUtil.divide(orgNum,divNum,0, RoundingMode.DOWN)));
    }
    public void adjustBidOrderList(List<BidOrderBidPriceDto> tempList, BigDecimal unitAdjustAmt) {
        List<BidOrderBidPriceDto> sortList = tempList.stream().sorted((x,y)->y.getBidPrinAmt().compareTo(x.getBidPrinAmt())).collect(Collectors.toList());
        BigDecimal sumAdjustAmt = BigDecimal.ZERO;
        for(BidOrderBidPriceDto temp : sortList){
            BigDecimal surNum = takeSurplus(temp.getWinPrinAmt(),unitAdjustAmt);
            if(surNum.compareTo(BigDecimal.ZERO)>0){
                temp.setWinPrinAmt(ArithmeticUtil.subtract(temp.getWinPrinAmt(),surNum));
                temp.setTheWinPrinAmt(temp.getWinPrinAmt());
                sumAdjustAmt = ArithmeticUtil.doAdd(sumAdjustAmt,surNum);
            }
        }
//        存在需要重新分配的金额
        if(sumAdjustAmt.compareTo(BigDecimal.ZERO)>0){
            for(BidOrderBidPriceDto temp : sortList){
                if(temp.getWinPrinAmt().compareTo(temp.getBidPrinAmt())==0){
                    continue;
                }
                else if(sumAdjustAmt.compareTo(BigDecimal.ZERO)>0&&ArithmeticUtil.doAdd(temp.getWinPrinAmt(),sumAdjustAmt).compareTo(temp.getBidPrinAmt())<=0){
                    temp.setWinPrinAmt(ArithmeticUtil.doAdd(temp.getWinPrinAmt(),sumAdjustAmt));
                    temp.setTheWinPrinAmt(temp.getWinPrinAmt());
                    sumAdjustAmt = BigDecimal.ZERO;
                }
                else if(sumAdjustAmt.compareTo(BigDecimal.ZERO)>0){
                    BigDecimal adjustAmt = ArithmeticUtil.subtract(temp.getBidPrinAmt(),temp.getWinPrinAmt());
                    temp.setWinPrinAmt(temp.getBidPrinAmt());
                    temp.setTheWinPrinAmt(temp.getBidPrinAmt());
                    sumAdjustAmt = ArithmeticUtil.subtract(sumAdjustAmt,adjustAmt);
                }
                if(BigDecimal.ZERO.compareTo(temp.getWinPrinAmt())==0){
                    temp.setWinPrinAmt(null);
                    temp.setWinCprice(null);
                    temp.setTheWinPrinAmt(null);
                }
            }
        }
    }
}
