package com.joyintech.tams.cfets.bankuplink.bond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.service.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.service.bankuplink.bond.service.CfetsBondDealQuotedPriceService;
import com.joyintech.tams.cfets.bankuplink.bond.dao.CfetsIbuQuoteBondMapper;
import com.joyintech.tams.cfets.bankuplink.bond.dto.*;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.invest.common.component.business.basicdata.BaseTradeFeeCommService;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.BasicTradeFeeInputDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author zznn
 * @date 2021/11/9
 */
@Service
public class IbuQuoteBondCalService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(IbuQuoteBondService.class);

    /**
     * 递增维度(万元)
     */
    private static  BigDecimal faceDimension = new BigDecimal("0.1");

    /**
     * 递增维度(元)
     */
    private static  BigDecimal amtDimension = new BigDecimal("0.01");

    @Autowired(required = false)
    private DateCalService dateCalService;

    @Autowired
    private CfetsBondDealQuotedPriceService cfetsBondDealQuotedPriceService;

    @Autowired(required = false)
    private BaseTradeFeeCommService baseTradeFeeCommService;

    @Autowired
    private CfetsIbuQuoteBondMapper cfetsIbuQuoteBondMapper;

    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;

    @Autowired
    private FinProductMapper finProductMapper;
    /**
     * 数据联动计算
     * @param calRequest
     * @return
     */
    public CalRespone calculation(CalRequest calRequest){
        CalRespone calRespone = new CalRespone();
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = new CfetsBondDealDialogueQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealDialogueQuoteDto,calRequest);
        cfetsBondDealDialogueQuoteDto.setAvaNum(calRequest.getShareAmt());
        BeanUtil.copyPropertiesIgnoreNull(calRespone,cfetsBondDealQuotedPriceService.calcAmt(cfetsBondDealDialogueQuoteDto));
        if(null!=calRespone.getFpriceAmt()){
            calRequest.setFpriceAmt(calRespone.getFpriceAmt());
        }
        List<TrdTradeFeeDetail> trdTradeFeeDetails = calFee(calRequest);
        if(CollectionUtils.isNotEmpty(trdTradeFeeDetails)){
            for (TrdTradeFeeDetail item : trdTradeFeeDetails) {
                if(StringUtil.equals(item.getFeeType(),BussinessDictCodeList.费用类型_交易手续费.getValue())){
                    calRespone.setDealFeeAmt(item.getFeeAmt());
                    continue;
                }
                if(StringUtil.equals(item.getFeeType(),BussinessDictCodeList.费用类型_结算手续费.getValue())){
                    calRespone.setSettFeeAmt(item.getFeeAmt());
                    continue;
                }
            }
        }
        //计算偏离度
        if(StringUtil.isNotEmpty(calRequest.getFinprodId())&&(null!=calRespone.getPrice()||null!=calRequest.getPrice())){
            Map<String,Object> calParam = new HashMap<>();
            calParam.put("finprodId",calRequest.getFinprodId());
            calParam.put("finprodMarketId",finProductMapper.selectByPrimaryKey(calRequest.getFinprodId()).getFinprodMarketId());
            calParam.put("unitCprice",null!=calRequest.getPrice()?calRequest.getPrice():calRespone.getPrice());
            calRespone.setDeviationDegree(calcDeviationDegree(calParam));
        }
        return calRespone;
        /*return restTemplate.postForObject(
                    ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/calcAmt",
                    calRequest, CalRespone.class);*/
    }

    /**
     * 计算费用
     * @param calRequest
     * @return
     */
    public List<TrdTradeFeeDetail>  calFee(CalRequest calRequest){
        BasicTradeFeeInputDto inputDto = new BasicTradeFeeInputDto();
        inputDto.setTradeAmt(calRequest.getFpriceAmt());
        inputDto.setTradeDate(calRequest.getSettleDate());
        inputDto.setTradeMarket(BussinessDictCodeList.交易场所_银行间.getValue());
        inputDto.setFinprodType2(BussinessDictCodeList.平安资产类型_债券.getValue());
        inputDto.setPs(calRequest.getPs());// 交易方向
        inputDto.setFpriceAmt(calRequest.getFpriceAmt());// 全价总额
        inputDto.setPrinAmt(calRequest.getShareAmt());// 券面总额
        inputDto.setIsGrade("1");
        String registOrg = cfetsIbuQuoteBondMapper.getRegistOrg(calRequest.getSecManageAcctId());
        inputDto.setRegistOrg(registOrg);
        inputDto.setFeeTypeList(Arrays.asList(BussinessDictCodeList.费用类型_交易手续费.getValue(), BussinessDictCodeList.费用类型_结算手续费.getValue()));
        return baseTradeFeeCommService.innerBankFeeDeal(inputDto);
    }

    /**
     *  自动分配
     * @param autoAllocationRequest
     * @return
     */
    public List<PtlAllocationDto> autoAllocation(AutoAllocationRequest autoAllocationRequest){
        String sellPara = CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED");
        String ps = autoAllocationRequest.getPs();
        //全价总额和净价总额为空  处理(请求报价)
        if(null==autoAllocationRequest.getCpriceAmt()||BigDecimal.ZERO.compareTo(autoAllocationRequest.getCpriceAmt())==0){
            autoAllocationRequest.setCpriceAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(autoAllocationRequest.getShareAmt(),autoAllocationRequest.getPrice()),new BigDecimal(100),2));
        }
        if(null==autoAllocationRequest.getFpriceAmt()||BigDecimal.ZERO.compareTo(autoAllocationRequest.getFpriceAmt())==0){
            autoAllocationRequest.setFpriceAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(autoAllocationRequest.getShareAmt(),autoAllocationRequest.getUnitFprice()),new BigDecimal(100),2));
        }
        //备份剩余额度
        List<PtlAllocationDto> ptlAllocations = autoAllocationRequest.getPtlAllocations();
        try{
            //无分配模式
            if(StringUtil.isEmpty(autoAllocationRequest.getDistribution())){
                //通过各自的券面总额计算金额
                ptlAllocations = noneDistribution(autoAllocationRequest);
            }else{
                if(CollectionUtils.isNotEmpty(ptlAllocations)){
                    ptlAllocations.forEach(item ->{
                        item.setAvaNumOld(item.getAvaNum());
                        item.setAvaNum(ArithmeticUtil.divide(item.getAvaNum(),new BigDecimal(10000),1));
                    });
                }
                //平均分配
                if(StringUtil.equals(autoAllocationRequest.getDistribution(),"139")){
                    ptlAllocations = aveDistribution(autoAllocationRequest);
                }
                //比例分配
                if(StringUtil.equals(autoAllocationRequest.getDistribution(),"143")){
                    ptlAllocations = propDistribution(autoAllocationRequest);
                }
                if(CollectionUtils.isNotEmpty(ptlAllocations)){
                    ptlAllocations.forEach(item ->{
                        item.setAvaNum(item.getAvaNumOld());
                    });
                }
            }
        }catch (Exception e){
            logger.error("自动匹配计算异常:"+e.getMessage());
        }
        return ptlAllocations;
    }

    /**
     * 无分配模式
     * @param request
     * @return
     */
    public List<PtlAllocationDto> noneDistribution(AutoAllocationRequest request){
        List<PtlAllocationDto>  ptlAllocations = request.getPtlAllocations();
        ptlAllocations.forEach(item ->{
            CalRequest calRequest = new CalRequest();
            BeanUtil.copyPropertiesIgnoreNull(calRequest,request);
            calRequest.setShareAmt(item.getShareAmt());
            CalRespone calRespone = calculation(calRequest);
            item.setDealFeeAmt(calRespone.getDealFeeAmt());
            item.setSettFeeAmt(calRespone.getSettFeeAmt());
            item.setCpriceAmt(calRespone.getCpriceAmt());
            item.setFpriceAmt(calRespone.getFpriceAmt());
            item.setIntAmt(calRespone.getIntAmt());
        });
        return ptlAllocations;
    }
    /**
     * 平均分配
     * @return
     */
    public List<PtlAllocationDto> aveDistribution(AutoAllocationRequest request){
        //以万元为最小粒度计算
        BigDecimal shareAmt = ArithmeticUtil.divide(request.getShareAmt(),new BigDecimal(10000),1);
        List<PtlAllocationDto>  ptlAllocations = request.getPtlAllocations();
        //降序
        ptlAllocations.sort((p1,p2) -> p1.getAvaNum().compareTo(p2.getAvaNum()));
        BigDecimal avg = ArithmeticUtil.divide(shareAmt,new BigDecimal(ptlAllocations.size()),1,RoundingMode.DOWN);
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
        calOtherForPtlAllocation(ptlAllocations,request);
        calFee(ptlAllocations,ArithmeticUtil.multiply(shareAmt,new BigDecimal(10000)),request.getDealFeeAmt(),request.getSettFeeAmt());
        return ptlAllocations;
    }

    /**
     * 比例分配
     * @return
     */
    public List<PtlAllocationDto> propDistribution(AutoAllocationRequest request){
        //以万元为最小粒度计算
        BigDecimal shareAmt = ArithmeticUtil.divide(request.getShareAmt(),new BigDecimal(10000),1);
        List<PtlAllocationDto>  ptlAllocations = request.getPtlAllocations();
        //降序
        ptlAllocations.sort((p1,p2) -> p1.getAvaNum().compareTo(p2.getAvaNum()));
        //剩余额度汇总
        BigDecimal avaNumTotal = ptlAllocations.stream().map(PtlAllocationDto::getAvaNum).reduce(BigDecimal.ZERO, BigDecimal::add);
        //尾差
        BigDecimal tailDifference = shareAmt;
        for (PtlAllocationDto ptlAllocation : ptlAllocations) {
            ptlAllocation.setShareAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(shareAmt,ptlAllocation.getAvaNum()),avaNumTotal,1,RoundingMode.DOWN));
            tailDifference = ArithmeticUtil.subtract(tailDifference,ptlAllocation.getShareAmt());
        }
        handleTailDifference(ptlAllocations,tailDifference);
        calOtherForPtlAllocation(ptlAllocations,request);
        calFee(ptlAllocations,ArithmeticUtil.multiply(shareAmt,new BigDecimal(10000)),request.getDealFeeAmt(),request.getSettFeeAmt());
        return ptlAllocations;
    }

    /**
     *  尾差处理(券面)
     * @param ptlAllocations
     * @param tailDifference
     */
    public void handleTailDifference(List<PtlAllocationDto>  ptlAllocations,BigDecimal tailDifference){
        //递增维度
        BigDecimal addDimension = faceDimension;
        int size = ptlAllocations.size();
        //委托维度 已经完全分配的集合
        Set<String> set = new HashSet<>();
        while(tailDifference.compareTo(BigDecimal.ZERO)>0&&size>0){
            if(tailDifference.compareTo(new BigDecimal("1"))>=0){
                addDimension = ArithmeticUtil.divide(tailDifference,new BigDecimal(size),1,RoundingMode.DOWN);
            }
            for (PtlAllocationDto item : ptlAllocations) {
                if((item.getAvaNum().compareTo(BigDecimal.ZERO)==0||tailDifference.compareTo(BigDecimal.ZERO)<=0)) {
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
     * 尾差处理
     * @param ptlAllocations
     * @param tailDifference
     * @param type
     * type=B06  交易费 type=B08 结算费  type=01 净价总额 type=02 全价总额
     */
    public void handleFeeTailDifference(List<PtlAllocationDto>  ptlAllocations,BigDecimal tailDifference,String type){
        ptlAllocations.sort((p1,p2) -> p1.getShareAmt().compareTo(p2.getShareAmt()));
        //递增维度
        BigDecimal addDimension = amtDimension;
        int size = ptlAllocations.size();
        while(tailDifference.compareTo(BigDecimal.ZERO)>0&&size>0){
            if(tailDifference.compareTo(new BigDecimal("1"))>=0){
                addDimension = ArithmeticUtil.divide(tailDifference,size,2);
            }
            for (PtlAllocationDto item : ptlAllocations) {
                if(tailDifference.compareTo(BigDecimal.ZERO)>0){
                    if(StringUtil.equals(type,BussinessDictCodeList.费用类型_交易手续费.getValue())){
                        item.setDealFeeAmt(ArithmeticUtil.doAdd(item.getDealFeeAmt(),addDimension));
                    }
                    if(StringUtil.equals(type,BussinessDictCodeList.费用类型_结算手续费.getValue())){
                        item.setSettFeeAmt(ArithmeticUtil.doAdd(item.getSettFeeAmt(),addDimension));
                    }
                    if(StringUtil.equals(type,"01")){
                        item.setCpriceAmt(ArithmeticUtil.doAdd(item.getCpriceAmt(),addDimension));
                    }
                    if(StringUtil.equals(type,"02")){
                        item.setFpriceAmt(ArithmeticUtil.doAdd(item.getFpriceAmt(),addDimension));
                    }
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
     * @param settFeeAmt  结算费
     */
    public void calFee(List<PtlAllocationDto> ptlAllocations,BigDecimal shareAmt,BigDecimal dealFeeAmt,BigDecimal settFeeAmt){
        BigDecimal tailDifferenceDealFeeAmt = dealFeeAmt;BigDecimal tailDifferenceSettFeeamt = settFeeAmt;
        for (PtlAllocationDto item : ptlAllocations) {
            item.setDealFeeAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getShareAmt(),dealFeeAmt),shareAmt,2, RoundingMode.DOWN));
            item.setSettFeeAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getShareAmt(),settFeeAmt),shareAmt,2, RoundingMode.DOWN));
            tailDifferenceDealFeeAmt = ArithmeticUtil.subtract(tailDifferenceDealFeeAmt,item.getDealFeeAmt());
            tailDifferenceSettFeeamt = ArithmeticUtil.subtract(tailDifferenceSettFeeamt,item.getSettFeeAmt());
        }
        handleFeeTailDifference(ptlAllocations,tailDifferenceDealFeeAmt,BussinessDictCodeList.费用类型_交易手续费.getValue());
        handleFeeTailDifference(ptlAllocations,tailDifferenceSettFeeamt,BussinessDictCodeList.费用类型_结算手续费.getValue());
    }

    /**
     * 计算组合列表其他值(除费用)
     * @param ptlAllocations
     * @param request
     */
    public void calOtherForPtlAllocation(List<PtlAllocationDto>  ptlAllocations,AutoAllocationRequest request){
        //模式
        //String sellPara = CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED");
        //String ps = request.getPs();
        BigDecimal tailDifferenceFpriceAmt = request.getFpriceAmt();BigDecimal tailDifferenceCpriceAmt = request.getCpriceAmt();
        for (PtlAllocationDto item : ptlAllocations) {
            BigDecimal shareAmt = ArithmeticUtil.multiply(item.getShareAmt(),new BigDecimal(10000));
            item.setShareAmt(shareAmt);
            //TODO 需求变更
            //item.setOurEntNum(shareAmt);
            /*if(StringUtil.equals(sellPara,"02")){
                item.setOurEntNum(shareAmt);
                if(StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())||StringUtil.equals(ps, DictCodeList.交易方向_行权_回售.getValue())){
                    item.setLendinEntNum(shareAmt);
                }
                if(StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())){
                    item.setSellAbleNum(shareAmt);
                }
            }*/
            item.setCpriceAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(shareAmt,request.getCpriceAmt()),request.getShareAmt(),2,RoundingMode.HALF_DOWN));
            item.setFpriceAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(shareAmt,request.getFpriceAmt()),request.getShareAmt(),2,RoundingMode.HALF_DOWN));
            tailDifferenceCpriceAmt = ArithmeticUtil.subtract(tailDifferenceCpriceAmt,item.getCpriceAmt());
            tailDifferenceFpriceAmt = ArithmeticUtil.subtract(tailDifferenceFpriceAmt,item.getFpriceAmt());
        }
        handleFeeTailDifference(ptlAllocations,tailDifferenceCpriceAmt,"01");
        handleFeeTailDifference(ptlAllocations,tailDifferenceFpriceAmt,"02");
        ptlAllocations.forEach(item->{item.setIntAmt(ArithmeticUtil.subtract(item.getFpriceAmt(),item.getCpriceAmt()));});
    }


    public Date getNextWorkDay(Date tradeDate,int days) {
        if (dateCalService != null) {
            List<String> holiday = dateCalService.getDealCalendarHoliday(tradeDate,
                    DateUtil.addDays(tradeDate, 31), CacheUtil.getSysParam("ibuVar_calendarId"));
            return dateCalService.calculationTradingDay(tradeDate, days, holiday, "1");
        }
        return null;
    }

    /**
     * 计算费用（交易端 废弃）
     * @param entity
     * @return
     */
    public List<TrdTradeFeeDetail> calcAmt(CfetsBondDealQuoteCommonDto entity){
        /*TrdBondInvestTradeDto dto = new TrdBondInvestTradeDto();
        dto.setPrinAmt(entity.getShareAmt());
        dto.setIntegrateTradeAmt(entity.getLendinEntNum());
        dto.setTradeAmt(entity.getFpriceAmt());
        dto.setTradeMarket(entity.getTradeMarket());
        dto.setYield(entity.getMatureYield());
        dto.setExerYield(entity.getExerYield());
        dto.setFinprodId(entity.getFinprodId());
        dto.setPs(entity.getPs());
        dto.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
        dto.setSettleDate(entity.getSettleDate());
        dto.setUnitInt(entity.getUnitInt());
        dto.setUnitCprice(entity.getPrice());
        dto.setUnitFprice(entity.getUnitFprice());
        if(StringUtil.equals(entity.getCalculateField(),"01")){
            dto.setCalculateField("unitCprice");
        }
        if(StringUtil.equals(entity.getCalculateField(),"02")){
            dto.setCalculateField("yield");
        }
        if(StringUtil.equals(entity.getCalculateField(),"03")){
            dto.setCalculateField("exerYield");
        }
        trdBondInvestTrade002Service.calcAmt(dto);*/
        return null;
    }


    /**
     * 计算偏离度
     * @param param
     * @return
     */
    public String calcDeviationDegree(Map<String,Object> param){
        return  trdBondInvestTrade002Service.calcDeviationDegree(param);
    }
}

