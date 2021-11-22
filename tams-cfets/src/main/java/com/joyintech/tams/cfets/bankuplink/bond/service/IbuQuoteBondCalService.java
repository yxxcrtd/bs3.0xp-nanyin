package com.joyintech.tams.cfets.bankuplink.bond.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.service.bankuplink.bond.service.CfetsBondDealQuotedPriceService;
import com.joyintech.tams.cfets.bankuplink.bond.dto.*;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author zznn
 * @date 2021/11/9
 */
@Service
public class IbuQuoteBondCalService extends BaseService {

    @Autowired(required = false)
    private DateCalService dateCalService;

    @Autowired
    private CfetsBondDealQuotedPriceService cfetsBondDealQuotedPriceService;
    /**
     * 数据联动计算
     * @param calRequest
     * @return
     */
    public CalRespone calculation(CalRequest calRequest){
        CalRespone calRespone = new CalRespone();
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = new CfetsBondDealDialogueQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealDialogueQuoteDto,calRequest);
        BeanUtil.copyPropertiesIgnoreNull(calRespone,cfetsBondDealQuotedPriceService.calcAmt(cfetsBondDealDialogueQuoteDto));
        return calRespone;
        /*return restTemplate.postForObject(
                    ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/calcAmt",
                    calRequest, CalRespone.class);*/
    }

    /**
     *  自动分配
     * @param autoAllocationRequest
     * @return
     */
    public List<PtlAllocationDto> autoAllocation(AutoAllocationRequest autoAllocationRequest){
        List<PtlAllocationDto> result = new ArrayList<>();
        //平均分配
        if(StringUtil.equals(autoAllocationRequest.getDistribution(),"139")){
            return aveDistribution(autoAllocationRequest);
        }
        //比例分配
        if(StringUtil.equals(autoAllocationRequest.getDistribution(),"143")){
            return propDistribution(autoAllocationRequest);
        }
        return result;
    }

    /**
     * 平均分配
     * @return
     */
    public List<PtlAllocationDto> aveDistribution(AutoAllocationRequest request){
        BigDecimal shareAmt = request.getShareAmt();
        List<PtlAllocationDto>  ptlAllocations = request.getPtlAllocations();
        //降序
        ptlAllocations.sort((p1,p2) -> p1.getAvaNum().compareTo(p2.getAvaNum()));
        BigDecimal avg = ArithmeticUtil.divide(shareAmt,ptlAllocations.size(),4);
        //剩余尾差
        BigDecimal tailDifference = shareAmt;
        for (PtlAllocationDto item : ptlAllocations) {
            if(item.getAvaNum().compareTo(avg)>=0){
                item.setShareAmt(avg);
            }else{
                item.setShareAmt(item.getAvaNum());
            }
            item.setAvaNum(ArithmeticUtil.subtract(item.getAvaNum(),item.getShareAmt()));
            tailDifference = ArithmeticUtil.subtract(tailDifference,item.getShareAmt());
        }
        handleTailDifference(ptlAllocations,tailDifference);
        calOtherForPtlAllocation(ptlAllocations,request.getPs(),request.getPrice(),request.getUnitFprice());
        calFee(ptlAllocations,shareAmt,request.getDealFeeAmt(),request.getSettFeeamt(),request.getIntAmt());
        return ptlAllocations;
    }

    /**
     * 比例分配
     * @return
     */
    public List<PtlAllocationDto> propDistribution(AutoAllocationRequest request){
        BigDecimal shareAmt = request.getShareAmt();
        List<PtlAllocationDto>  ptlAllocations = request.getPtlAllocations();
        //降序
        ptlAllocations.sort((p1,p2) -> p1.getAvaNum().compareTo(p2.getAvaNum()));
        //剩余额度汇总
        BigDecimal avaNumTotal = ptlAllocations.stream().map(PtlAllocationDto::getAvaNum).reduce(BigDecimal.ZERO, BigDecimal::add);
        //尾差
        BigDecimal tailDifference = avaNumTotal;
        for (PtlAllocationDto ptlAllocation : ptlAllocations) {
            ptlAllocation.setShareAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(shareAmt,ptlAllocation.getShareAmt()),avaNumTotal,4));
            tailDifference = ArithmeticUtil.subtract(tailDifference,ptlAllocation.getShareAmt());
        }
        handleTailDifference(ptlAllocations,tailDifference);
        calOtherForPtlAllocation(ptlAllocations,request.getPs(),request.getPrice(),request.getUnitFprice());
        calFee(ptlAllocations,shareAmt,request.getDealFeeAmt(),request.getSettFeeamt(),request.getIntAmt());
        return ptlAllocations;
    }

    /**
     *  尾差处理
     * @param ptlAllocations
     * @param tailDifference
     */
    public void handleTailDifference(List<PtlAllocationDto>  ptlAllocations,BigDecimal tailDifference){
        //递增维度
        BigDecimal addDimension = new BigDecimal("0.01");
        int size = ptlAllocations.size();
        //委托维度 已经完全分配的集合
        Set<String> set = new HashSet<>();
        while(tailDifference.compareTo(BigDecimal.ZERO)>0&&size>0){
            if(tailDifference.compareTo(new BigDecimal("1"))>=0){
                addDimension = ArithmeticUtil.divide(tailDifference,size,4);
            }
            for (PtlAllocationDto item : ptlAllocations) {
                if((item.getAvaNum().compareTo(item.getShareAmt())==0||tailDifference.compareTo(BigDecimal.ZERO)<=0)) {
                    if (set.add(item.getEntrustId())) {
                        size--;
                        continue;
                    }
                }
                else{
                    item.setShareAmt(ArithmeticUtil.doAdd(item.getShareAmt(),addDimension));
                    tailDifference = ArithmeticUtil.subtract(tailDifference,addDimension);
                }
            }
        }
    }

    /**
     *  计算费用
     * @param ptlAllocations
     * @param shareAmt 券面总额
     * @param dealFeeAmt 交易费
     * @param settFeeamt  结算费
     * @param intAmt  应计利息总额
     */
    public void calFee(List<PtlAllocationDto> ptlAllocations,BigDecimal shareAmt,BigDecimal dealFeeAmt,BigDecimal settFeeamt,BigDecimal intAmt){
        BigDecimal tailDifferenceIntAmt = intAmt;BigDecimal tailDifferenceDealFeeAmt = dealFeeAmt;BigDecimal tailDifferenceSettFeeamt = settFeeamt;
        for (PtlAllocationDto item : ptlAllocations) {
            item.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getShareAmt(),intAmt),shareAmt,4));
            item.setDealFeeAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getShareAmt(),dealFeeAmt),shareAmt,4));
            item.setSettFeeamt(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getShareAmt(),settFeeamt),shareAmt,4));
            tailDifferenceIntAmt = ArithmeticUtil.subtract(tailDifferenceIntAmt,item.getIntAmt());
            tailDifferenceDealFeeAmt = ArithmeticUtil.subtract(tailDifferenceDealFeeAmt,item.getDealFeeAmt());
            tailDifferenceSettFeeamt = ArithmeticUtil.subtract(tailDifferenceSettFeeamt,item.getSettFeeamt());
        }
        //尾差放在分在券面最大的委托上
        ptlAllocations.sort((p1,p2) -> p1.getShareAmt().compareTo(p2.getShareAmt()));
        for (PtlAllocationDto ptlAllocation : ptlAllocations) {
            ptlAllocation.setIntAmt(ArithmeticUtil.doAdd(ptlAllocation.getIntAmt(),tailDifferenceIntAmt));
            ptlAllocation.setDealFeeAmt(ArithmeticUtil.doAdd(ptlAllocation.getDealFeeAmt(),tailDifferenceDealFeeAmt));
            ptlAllocation.setSettFeeamt(ArithmeticUtil.doAdd(ptlAllocation.getSettFeeamt(),tailDifferenceSettFeeamt));
            break;
        }
    }
    /**
     * 计算组合列表其他值(除费用)
     * @param ptlAllocations
     * @param price  净价
     * @param unitFprice 全价
     */
    public void calOtherForPtlAllocation(List<PtlAllocationDto>  ptlAllocations,String ps,BigDecimal price,BigDecimal unitFprice){
        //模式
        String sellPara = CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED");
        for (PtlAllocationDto item : ptlAllocations) {
            if(StringUtil.equals(sellPara,"02")){
                item.setOurEntNum(item.getShareAmt());
                if(StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())){
                    item.setLendinEntNum(item.getShareAmt());
                }
                if(StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())){
                    item.setSellAbleNum(item.getShareAmt());
                }
            }
            item.setCpriceAmt(ArithmeticUtil.multiply(item.getShareAmt(),price));
            item.setFpriceAmt(ArithmeticUtil.multiply(item.getShareAmt(),unitFprice));
        }
    }

    public Date getNextWorkDay(Date tradeDate) {
        if (dateCalService != null) {
            List<String> holiday = dateCalService.getDealCalendarHoliday(tradeDate,
                    DateUtil.addDays(tradeDate, 31), CacheUtil.getSysParam("ibuVar_calendarId"));
            return dateCalService.calculationTradingDay(tradeDate, 1, holiday, "1");
        }
        return null;
    }
}

