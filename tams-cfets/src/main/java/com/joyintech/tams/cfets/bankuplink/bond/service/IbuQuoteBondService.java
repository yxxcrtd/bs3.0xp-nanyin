package com.joyintech.tams.cfets.bankuplink.bond.service;

import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.cfets.bankuplink.bond.dao.CfetsIbuQuoteBondMapper;
import com.joyintech.tams.cfets.bankuplink.bond.dto.*;
import com.joyintech.tams.common.base.dao.EtrDistributeMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zznn
 * @date 2021/11/9
 */
@Service
public class IbuQuoteBondService extends BaseService {

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private CfetsIbuQuoteBondMapper cfetsIbuQuoteBondMapper;

    @Autowired(required = false)
    private BaseEtrInvestInfoServiceImpl etrInvesInfo;

    @Autowired
    private EtrDistributeMapper etrDistributeMapper;

    @Autowired
    private IbuQuoteBondCalService ibuQuoteBondCalService;

    @Autowired
    private FinProductMapper finProductMapper;

    /**
     *  初始化指令列表
     * @param dto
     * @return
     */
    public Page<EntrustDto> listEntrust(EntrustRequest dto){
        Map<String, Object> pageParam = BeanUtil.object2Map(dto);
        pageParam.put("loginUserId", LoginUserUtil.getUserName());
        pageParam.put("platDate", iPlatformDate.getSysDateStr());
        setParam(pageParam);
        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsIbuQuoteBondMapper.listEntrust(pageParam));
    }


    /**
     * 接收指令
     * @param param
     * @return
     */
    public List<ValidMessage> receiveEntrust(Map<String,Object> param){
        List<ValidMessage> errorList=new ArrayList<ValidMessage>();
        List<EtrDistribute> list=getDistributeTrader(param);
        List<String> traderList=new ArrayList<String>();
        for(EtrDistribute etrDis:list){
            errorList.addAll(etrInvesInfo.checkDelegateOperation(etrDis.getEntrustId(), DictCodeListExt1.委托操作类型_接收.getValue(), traderList, DictCodeListExt1.委托关闭方式_人工.getValue()));
            etrDis.setUpdateUser(etrDis.getTrader());
        }
        //无错误时，进行接收操作
        if(errorList.size() == 0){
            etrInvesInfo.receptionDelegate(list);
        }
        return errorList;
    }

    public List<EtrDistribute> getDistributeTrader(Map<String,Object> param){
        List<EtrDistribute> list=new ArrayList<EtrDistribute>();
        EtrDistributeKey etrKey=new EtrDistributeKey();
        etrKey.setTrader((String) param.get("trader"));
        List<String> entrustIds=(List<String>)param.get("entrustIds");
        for(String entrustId:entrustIds){
            etrKey.setEntrustId(entrustId);
            list.add(etrDistributeMapper.selectByPrimaryKey(etrKey));
        }
        return list;
    }

    /**
     * 初始化报价页面(委托代码)
     * @param cfetsBondDealQuoteRequest
     * @return
     */
    public CfetsBondDealQuoteCommonDto queryEntrustInfoById(CfetsBondDealQuoteRequest cfetsBondDealQuoteRequest) {
        CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto = new CfetsBondDealQuoteCommonDto();
        Map<String, Object> param = BeanUtil.object2Map(cfetsBondDealQuoteRequest);
        setParam(param);
        List<CfetsBondDealEntrustOrderDto> entrustDtos = cfetsIbuQuoteBondMapper.listEntrust(param);
        if (!StringUtil.isEmptyList(entrustDtos)) {
            BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealQuoteCommonDto, entrustDtos.get(0));
            //处理结算日期
            cfetsBondDealQuoteCommonDto.setTradeDate(iPlatformDate.getSysDate());
            cfetsBondDealQuoteCommonDto.setSettleDate(ibuQuoteBondCalService.getNextWorkDay(cfetsBondDealQuoteCommonDto.getTradeDate()));
            if(StringUtil.equals(cfetsBondDealQuoteCommonDto.getSettleSpreed(),BussinessDictCodeList.清算速度_T加2.getValue())){
                cfetsBondDealQuoteCommonDto.setSettleDate(ibuQuoteBondCalService.getNextWorkDay(cfetsBondDealQuoteCommonDto.getSettleDate()));
            }
            //处理锚定值
            Map<String,Object> anchoringValueMap = handcalAnchoringValue(entrustDtos);
            Set<String> set = anchoringValueMap.keySet();
            cfetsBondDealQuoteCommonDto.setPrice(null);
            cfetsBondDealQuoteCommonDto.setMatureYield(null);
            cfetsBondDealQuoteCommonDto.setExerYield(null);
            for (String key : set) {
                cfetsBondDealQuoteCommonDto.setCalculateField(key);
                if(StringUtil.equals(key,"01")){
                    cfetsBondDealQuoteCommonDto.setPrice((BigDecimal)anchoringValueMap.get(key));
                    break;
                }
                if(StringUtil.equals(key,"02")){
                    cfetsBondDealQuoteCommonDto.setMatureYield((BigDecimal)anchoringValueMap.get(key));
                    break;
                }
                if(StringUtil.equals(key,"03")){
                    cfetsBondDealQuoteCommonDto.setExerYield((BigDecimal)anchoringValueMap.get(key));
                    break;
                }
            }
        }
        return cfetsBondDealQuoteCommonDto;
    }

    /**
     * 组合列表添加
     * @param ptlAllocationAddRequest
     * @return
     */
    public List<PtlAllocationDto> ptlAllocationAdd(PtlAllocationAddRequest ptlAllocationAddRequest){
        Map<String, Object> param = BeanUtil.object2Map(ptlAllocationAddRequest);
        setParam(param);
        List<CfetsBondDealEntrustOrderDto> entrustDtos = cfetsIbuQuoteBondMapper.listEntrust(param);
        if (!StringUtil.isEmptyList(entrustDtos)) {
            return initPtlAllocation(entrustDtos);
        }
        return new ArrayList<>();
    }

    /**
     *  初始化组合分配列表(委托列表触发)
     * @param list
     * @return
     */
    public List<PtlAllocationDto>  initPtlAllocation(List<CfetsBondDealEntrustOrderDto> list){
        List<PtlAllocationDto> result = new ArrayList<>();
        for (CfetsBondDealEntrustOrderDto item : list) {
            PtlAllocationDto ptlAllocationDto = new PtlAllocationDto();
            BeanUtil.copyPropertiesIgnoreNull(ptlAllocationDto,item);
            result.add(ptlAllocationDto);
        }
        return result;
    }

    /**
     * 交易执行点击check
     * @param cfetsBondDealQuoteRequest
     * @return
     */
    public AjaxResult dealexecutioncheck(CfetsBondDealQuoteRequest cfetsBondDealQuoteRequest) {
        AjaxResult ajaxResult = new AjaxResult();
        String message = StringUtil.EMPTY;String ps = StringUtil.EMPTY;
        Map<String, Object> param = BeanUtil.object2Map(cfetsBondDealQuoteRequest);
        setParam(param);
        String exportShow = CacheUtil.getSysParam("EXPORT_SHOW");
        List<CfetsBondDealEntrustOrderDto> entrustDtos = cfetsIbuQuoteBondMapper.listEntrust(param);
        if (!StringUtil.isEmptyList(entrustDtos)) {
            CfetsBondDealEntrustOrderDto one = entrustDtos.get(0);
            ps = one.getPs();
             boolean psFlag = true;boolean settleSpreedFlag = true;boolean finprodIdFlag = true;boolean  secManageAcctIdFlag = true;boolean partyIdFlag = true;
            for (CfetsBondDealEntrustOrderDto item : entrustDtos) {
                if(StringUtil.isEmpty(item.getPartyId())){
                    message = "指令"+item.getEntrustId()+"下组合未关联交易账户，请至[银行间业务参数管理-CFETS关联账户]中维护";
                    ajaxResult.setMessage(message);
                    return ajaxResult;
                }
                if(psFlag&&!StringUtil.equals(one.getPs(),item.getPs())){
                    psFlag = false;
                    message = message+"交易方向OR";
                }
                if(settleSpreedFlag&&!StringUtil.equals(one.getSettleSpreed(),item.getSettleSpreed())){
                    settleSpreedFlag = false;
                    message = message+"清算速度OR";
                }
                if(finprodIdFlag&&!StringUtil.equals(one.getFinprodId(),item.getFinprodId())){
                    finprodIdFlag = false;
                    message = message+"债券标的OR";
                }
                if(secManageAcctIdFlag&&!StringUtil.equals(one.getSecManageAcctId(),item.getSecManageAcctId())){
                    secManageAcctIdFlag = false;
                    message = message+"证券账户OR";
                }
                //EXPORT_SHOW ==01 本币交易导出按钮配置为显示
                if(StringUtil.equals(exportShow,"01")){
                    //交易场所+交易方向+清算速度+标的+证券账户+交易账户必须一致
                    if(partyIdFlag&&!StringUtil.equals(one.getPartyId(),item.getPartyId())){
                        partyIdFlag = false;
                        message = message+"交易账户OR";
                    }
                }
            }
            //提示
            if(StringUtil.isNotEmpty(message)){
                ajaxResult.setMessage(message.substring(0, message.length() - 2));
                return ajaxResult;
            }
            //价格模式判断
            //所有数据以保留4位小数，第5位四舍五入
            entrustDtos.stream().forEach(item ->{
                if(null!=item.getPrice()){
                    item.setPrice(item.getPrice().setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                if(null!=item.getMatureYield()){
                    item.setMatureYield(item.getMatureYield().setScale(4, BigDecimal.ROUND_HALF_UP));

                }
                if(null!=item.getExerYield()){
                    item.setExerYield(item.getExerYield().setScale(4, BigDecimal.ROUND_HALF_UP));
                }
            });
            //按价格模式分组
            Map<String, List<CfetsBondDealEntrustOrderDto>> groupByPriceModel = entrustDtos.stream().collect(Collectors.groupingBy(CfetsBondDealEntrustOrderDto::getPriceModel));
            //按锚定值分组
            Map<String, List<CfetsBondDealEntrustOrderDto>> groupByCalculateField = entrustDtos.stream().collect(Collectors.groupingBy(CfetsBondDealEntrustOrderDto::getCalculateField));
            //锚定值是否一致
             boolean translateFlag = true;int i=0;String translateKey = StringUtil.EMPTY;
            for (Map.Entry<String, List<CfetsBondDealEntrustOrderDto>> entry : groupByCalculateField.entrySet()) {i++;translateKey = entry.getKey();}
            if(i!=1){ translateFlag = false;}
            if(groupByPriceModel.containsKey(DictCodeList.价格模式_指定价格.getValue())){
                List<CfetsBondDealEntrustOrderDto> cfetsBondDealEntrustOrderDtos = groupByPriceModel.get(DictCodeList.价格模式_指定价格.getValue());
                if(groupByPriceModel.containsKey(DictCodeList.价格模式_限价指令.getValue())){
                    //三维度是否一致标志
                    boolean priceFlag = true;boolean matureYieldFlag = true;boolean exerYieldFlag = true;
                    List<CfetsBondDealEntrustOrderDto> priceMode01lList = groupByPriceModel.get(DictCodeList.价格模式_指定价格.getValue());
                    List<CfetsBondDealEntrustOrderDto> priceMode02lList = groupByPriceModel.get(DictCodeList.价格模式_限价指令.getValue());
                    CfetsBondDealEntrustOrderDto priceModelOne = priceMode01lList.get(0);
                    //有限价指令模式   混合模式
                    //最值(最大值、最小值)
                    BigDecimal maximumPrice = BigDecimal.ZERO;BigDecimal maximumMatureYield = BigDecimal.ZERO;BigDecimal maximumExerYield = BigDecimal.ZERO;
                    if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_买入.getValue())){
                        //最小的价格
                        maximumPrice = priceMode02lList.stream().min((u1, u2) -> u1.getPrice().compareTo(u2.getPrice())).get().getPrice();
                        //最大的收益率
                        maximumMatureYield = priceMode02lList.stream().max((u1, u2) -> u1.getMatureYield().compareTo(u2.getMatureYield())).get().getMatureYield();
                        maximumExerYield = priceMode02lList.stream().max((u1, u2) -> u1.getExerYield().compareTo(u2.getExerYield())).get().getExerYield();
                    }
                    if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_卖出.getValue())){
                        //最大的价格
                        maximumPrice = priceMode02lList.stream().max((u1, u2) -> u1.getPrice().compareTo(u2.getPrice())).get().getPrice();
                        //最小的收益率
                        maximumMatureYield = priceMode02lList.stream().min((u1, u2) -> u1.getMatureYield().compareTo(u2.getMatureYield())).get().getMatureYield();
                        maximumExerYield = priceMode02lList.stream().min((u1, u2) -> u1.getExerYield().compareTo(u2.getExerYield())).get().getExerYield();
                    }
                    if(translateFlag){
                        for (CfetsBondDealEntrustOrderDto item : priceMode01lList) {
                                if(StringUtil.equals(translateKey,"01")&&priceModelOne.getPrice().compareTo(item.getPrice())!=0){
                                    priceFlag = false;
                                    message = "存在多条指定价格指令，净价需一致";
                                    break;
                                }
                                if(StringUtil.equals(translateKey,"02")&&priceModelOne.getMatureYield().compareTo(item.getMatureYield())!=0){
                                    matureYieldFlag = false;
                                    message = "存在多条指定价格指令，到期收益率需一致";
                                    break;
                                }
                                if(StringUtil.equals(translateKey,"03")&&priceModelOne.getExerYield().compareTo(item.getExerYield())!=0){
                                    exerYieldFlag = false;
                                    message = "存在多条指定价格指令，行权收益率需一致";
                                    break;
                                }
                        }
                        if(StringUtil.isNotEmpty(message)){
                            ajaxResult.setMessage(message);
                            return ajaxResult;
                        }
                        if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_买入.getValue())){
                            if(priceFlag&&StringUtil.equals(translateKey,"01")&&priceModelOne.getPrice().compareTo(maximumPrice)>0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的净价不能大于限价指令中净价的最小值";
                            }
                            if(matureYieldFlag&&StringUtil.equals(translateKey,"02")&&priceModelOne.getMatureYield().compareTo(maximumMatureYield)<0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的到期收益率不能小于限价指令中到期收益率的最大值";
                            }
                            if(exerYieldFlag&&StringUtil.equals(translateKey,"03")&&priceModelOne.getExerYield().compareTo(maximumExerYield)<0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的行权收益率不能小于限价指令中行权收益率的最大值";
                            }
                        }
                        if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_卖出.getValue())){
                            if(priceFlag&&StringUtil.equals(translateKey,"01")&&priceModelOne.getPrice().compareTo(maximumPrice)<0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的净价不能小于限价指令中净价的最大值";
                            }
                            if(matureYieldFlag&&StringUtil.equals(translateKey,"02")&&priceModelOne.getMatureYield().compareTo(maximumMatureYield)>0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的到期收益率不能大于限价指令中到期收益率的最小值";
                            }
                            if(exerYieldFlag&&StringUtil.equals(translateKey,"03")&&priceModelOne.getExerYield().compareTo(maximumExerYield)>0){
                                message = "同时存在指定价格指令和限价指令，指定价格指令的行权收益率不能大于限价指令中行权收益率的最小值";
                            }
                        }
                        if(StringUtil.isNotEmpty(message)){
                            ajaxResult.setMessage(message);
                            return ajaxResult;
                        }
                    }else{
                        //三者其中之一一致
                        boolean threeFlag = true;
                        for (CfetsBondDealEntrustOrderDto item : priceMode01lList) {
                            if(priceModelOne.getPrice().compareTo(item.getPrice())!=0&&priceModelOne.getMatureYield().compareTo(item.getMatureYield())!=0&&priceModelOne.getExerYield().compareTo(item.getExerYield())!=0){
                                threeFlag = false;
                                message = "存在多条指定价格指令，净价/到期收益率/行权收益率三者中任一数据需一致";
                                break;
                            }
                        }
                        if(StringUtil.isNotEmpty(message)){
                            ajaxResult.setMessage(message);
                            return ajaxResult;
                        }
                        //
                        if(threeFlag){
                            if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_买入.getValue())){
                                if(priceModelOne.getPrice().compareTo(maximumPrice)>0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的净价不能大于限价指令中净价的最小值";
                                }
                                if(priceModelOne.getMatureYield().compareTo(maximumMatureYield)<0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的到期收益率不能小于限价指令中到期收益率的最大值";
                                }
                                if(priceModelOne.getExerYield().compareTo(maximumExerYield)<0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的行权收益率不能小于限价指令中行权收益率的最大值";
                                }
                            }
                            if(StringUtil.equals(ps, com.joyintech.tams.constant.DictCodeList.交易方向_卖出.getValue())){
                                if(priceModelOne.getPrice().compareTo(maximumPrice)<0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的净价不能小于限价指令中净价的最大值";
                                }
                                if(priceModelOne.getMatureYield().compareTo(maximumMatureYield)>0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的到期收益率不能大于限价指令中到期收益率的最小值";
                                }
                                if(priceModelOne.getExerYield().compareTo(maximumExerYield)>0){
                                    message = "同时存在指定价格指令和限价指令，指定价格指令的行权收益率不能大于限价指令中行权收益率的最小值";
                                }
                            }
                        }
                        if(StringUtil.isNotEmpty(message)){
                            ajaxResult.setMessage(message);
                            return ajaxResult;
                        }
                    }
                }else{
                    //无限制指令模式
                    //1.存在多条指定价格指令，净价/到期收益率/行权收益率需一致
                    //2.存在多条指定价格指令，净价/到期收益率/行权收益率三者中任一数据需一致
                    //3.存在多条指定价格指令，净价需一致
                    CfetsBondDealEntrustOrderDto priceModelOne = cfetsBondDealEntrustOrderDtos.get(0);
                    if(StringUtil.isNotEmpty(message)){
                        ajaxResult.setMessage(message);
                        return ajaxResult;
                    }
                    for (CfetsBondDealEntrustOrderDto item : entrustDtos) {
                        //锚定值一致
                        if(translateFlag){
                            //01 净价  02 到期收益率 03 行权收益率
                            if(StringUtil.equals(translateKey,"01")&&priceModelOne.getPrice().compareTo(item.getPrice())!=0){
                                message = "存在多条指定价格指令，净价需一致";
                                break;
                            }
                            if(StringUtil.equals(translateKey,"02")&&priceModelOne.getMatureYield().compareTo(item.getMatureYield())!=0){
                                message = "存在多条指定价格指令，到期收益率需一致";
                                break;
                            }
                            if(StringUtil.equals(translateKey,"03")&&priceModelOne.getExerYield().compareTo(item.getExerYield())!=0){
                                message = "存在多条指定价格指令，行权收益率需一致";
                                break;
                            }
                        }else{
                            if(priceModelOne.getPrice().compareTo(item.getPrice())!=0&&priceModelOne.getMatureYield().compareTo(item.getMatureYield())!=0&&priceModelOne.getExerYield().compareTo(item.getExerYield())!=0){
                                message = "存在多条指定价格指令，净价/到期收益率/行权收益率三者中任一数据需一致";
                                break;
                            }
                        }
                    }
                    if(StringUtil.isNotEmpty(message)){
                        ajaxResult.setMessage(message);
                        return ajaxResult;
                    }
                }
            }
        }
        return ajaxResult;
    }

    /**
     * 构造生成交易编号的参数
     * @param ps
     * @return
     */
    public static BussinessCodeParameter getBussinessCodeParameter(String ps) {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(com.joyintech.tams.constant.DictCodeList.交易方向_买入.getValue(), ps)) {
            codeParameter.setCommonBusinessType(com.joyintech.tams.constant.DictCodeList.共通业务类型_债券买入);
        }
        else {
            codeParameter.setCommonBusinessType(com.joyintech.tams.constant.DictCodeList.共通业务类型_债券卖出);
        }
        return codeParameter;
    }


    /**
     * 构建汇总费用实体
     * @param bussinessId
     * @param feeType
     * @param amount
     * @return
     */
    public RegTradeFeeTotal prepareRegTradeFeeTotal(String bussinessId,String feeType,BigDecimal amount){
        RegTradeFeeTotal regTradeFeeTotal = new RegTradeFeeTotal();
        regTradeFeeTotal.setTradeId(bussinessId);
        regTradeFeeTotal.setFeeType(feeType);
        regTradeFeeTotal.setIsPayWithTrade(com.joyintech.tams.constant.DictCodeList.是否_否.getValue());
        regTradeFeeTotal.setFeeAmt(amount);
        return regTradeFeeTotal;
    }

    /**
     * 构建明细费用实体
     * @param bussinessId
     * @param feeType
     * @param amount
     * @return
     */
    public RegTradeFee prepareRegTradeFee(String bussinessId, String feeType, BigDecimal amount){
        RegTradeFee regTradeFee = new RegTradeFee();
        regTradeFee.setTradeId(bussinessId);
        regTradeFee.setFeeType(feeType);
        regTradeFee.setIsPayWithTrade(com.joyintech.tams.constant.DictCodeList.是否_否.getValue());
        regTradeFee.setFeeAmt(amount);
        return regTradeFee;
    }

    public Map<String,Object> handcalAnchoringValue(List<CfetsBondDealEntrustOrderDto> entrustDtos){
        Map<String,Object> result = new HashMap<>();
        //按价格模式分组
        Map<String, List<CfetsBondDealEntrustOrderDto>> groupByPriceModel = entrustDtos.stream().collect(Collectors.groupingBy(CfetsBondDealEntrustOrderDto::getPriceModel));
        //按锚定值分组
        Map<String, List<CfetsBondDealEntrustOrderDto>> groupByCalculateField = entrustDtos.stream().collect(Collectors.groupingBy(CfetsBondDealEntrustOrderDto::getCalculateField));
        //锚定值是否一致
        boolean translateFlag = true;int i=0;String translateKey = StringUtil.EMPTY;
        //是否有默认值
        boolean initValueFlag = true;
        for (Map.Entry<String, List<CfetsBondDealEntrustOrderDto>> entry : groupByCalculateField.entrySet()) {i++;translateKey = entry.getKey();}
        if(i!=1){ translateFlag = false;}
        if(translateFlag){
            //是否显示初始值
            if(groupByPriceModel.containsKey(DictCodeList.价格模式_限价指令.getValue())){
                initValueFlag = false;
            }
        }else{
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(entrustDtos.get(0).getFinprodId());
            if(StringUtil.equals(finProduct.getIsRight(), com.joyintech.tams.constant.DictCodeList.是否_是.getValue())){
                //行权收益率
                translateKey = "03";
            }else{
                //到期收益率
                translateKey = "02";
            }
            initValueFlag = false;
        }
        CfetsBondDealEntrustOrderDto resultDto = groupByCalculateField.get(translateKey).get(0);
        if(StringUtil.equals(translateKey,"01")){
            result.put(translateKey,initValueFlag?resultDto.getPrice():null);
            return result;
        }
        if(StringUtil.equals(translateKey,"02")){
            result.put(translateKey,initValueFlag?resultDto.getMatureYield():null);
            return result;
        }
        if(StringUtil.equals(translateKey,"03")){
            result.put(translateKey,initValueFlag?resultDto.getExerYield():null);
            return result;
        }
        return null;
    }

    private void setParam(Map<String,Object> param){
        param.put("finprodType2", BussinessDictCodeList.金融产品类型_债券.getValue());
        param.put("tradeMarket01", BussinessDictCodeList.交易场所_银行间.getValue());
        param.put("tradeMarket04", BussinessDictCodeList.交易场所_商业银行柜台市场.getValue());
        param.put("tradeMarketMode", BussinessDictCodeList.交易市场_二级市场.getValue());
        param.put("entrustStatusUnAccept", BussinessDictCodeList.委托状态_待接收.getValue());
        param.put("entrustStatusAccept", BussinessDictCodeList.委托状态_已接收.getValue());
        param.put("entrustStatusComple", BussinessDictCodeList.委托状态_已完成.getValue());
        param.put("secAcctSource", BussinessDictCodeList.账户来源_自营.getValue());
        param.put("psT01", BussinessDictCodeList.交易方向_买入.getValue());
        param.put("psT02", BussinessDictCodeList.交易方向_卖出.getValue());
    }
}

