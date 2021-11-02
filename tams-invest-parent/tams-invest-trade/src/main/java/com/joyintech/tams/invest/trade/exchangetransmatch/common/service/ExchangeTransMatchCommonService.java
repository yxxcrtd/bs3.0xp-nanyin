package com.joyintech.tams.invest.trade.exchangetransmatch.common.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.dao.ExchangeTransMatchCommonMapper;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.Fund.ExchangeTransMatchFund1Service;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond.*;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.repo.ExchangeTransMatchRepo1Service;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.shares.ExchangeTransMatchShares1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 场内成交管理共通service
 */
@Service
public class ExchangeTransMatchCommonService extends BaseService {
    
    @Autowired
    private ExchangeTransMatchCommonMapper exchangeTransMatchCommonMapper;
    
    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;

    @Autowired
    private SettleTransDataTotalMatchMapper settleTransDataTotalMatchMapper;

    @Autowired
    private SettleTransTgMappingMapper settleTransTgMappingMapper;

    @Autowired
    private SettleTransDataTotalStatusMapper settleTransDataTotalStatusMapper;

    /**
     * 金融产品交易共通业务
     */
    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    @Autowired
    private ExchangeTransMatchBond1Service exchangeTransMatchBond1Service;

    @Autowired
    private ExchangeTransMatchBond2Service exchangeTransMatchBond2Service;

    @Autowired
    private ExchangeTransMatchBond3Service exchangeTransMatchBond3Service;

    @Autowired
    private ExchangeTransMatchBond4Service exchangeTransMatchBond4Service;

    @Autowired
    private ExchangeTransMatchBond5Service exchangeTransMatchBond5Service;
    @Autowired
    private ExchangeTransMatchRepo1Service exchangeTransMatchRepo1Service;
    @Autowired
    private ExchangeTransMatchShares1Service exchangeTransMatchShares1Service;
    @Autowired
    private ExchangeTransMatchFund1Service exchangeTransMatchFund1Service;
	@Autowired
    private SettleTransDataTotalAddMapper settleTransDataTotalAddMapper;
	@Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
	@Autowired
	private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
	@Autowired
    private TrdDealTotalAddMapper trdDealTotalAddMapper;
	@Autowired
    private TrdProductDealMapper trdProductDealMapper;
	@Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
	@Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
	@Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    @Autowired
    private TrdFinProductBarterMapper trdFinProductBarterMapper;
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    /**
     * 批量处理成交匹配数据
     * @param dtoList
     */
    public void transactionMacthBatch(List<ExchangeTransMatchCommonDto> dtoList){

        for(ExchangeTransMatchCommonDto dto:dtoList) {
            this.transactionMacth(dto);
        }
    }

    public void transactionMacth(ExchangeTransMatchCommonDto dto) {
        //查询汇总成交数据附属表
        dto.setSettleTransDataTotalAdd(settleTransDataTotalAddMapper.selectByPrimaryKey(dto.getSeqId()));
        //查询匹配的委托信息list
        List<ExchangeTransMatchCommonEntrustDto> commonEntrustDtoList = exchangeTransMatchCommonMapper.getEtrEntrustByIdList(dto.getEntrustIdList());
        if(!StringUtil.isEmptyList(commonEntrustDtoList)) {
            //只有一条匹配指令时
            String matchStatus = DictCodeListExt3.下行交易匹配状态_未匹配.getValue();
            //计算 已匹配份额，已匹配金额，指令已匹配份额，指令已匹配金额
            if(commonEntrustDtoList.size() == 1) {
                ExchangeTransMatchCommonEntrustDto commonEntrustDto = commonEntrustDtoList.get(0);
                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                    //已匹配份额
                    commonEntrustDto.setMarchQty(commonEntrustDto.getEntrustNum());
                    //已匹配金额
                    commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum()))>0?dto.getRemainNum() : commonEntrustDto.getEntrustNum(),dto.getUnitCprice()));
                    //指令已匹配份额
                    commonEntrustDto.setMarchQtyEn(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum()))>0?dto.getRemainNum() : commonEntrustDto.getEntrustNum());
                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum()))>0?dto.getRemainNum() : commonEntrustDto.getEntrustNum(),commonEntrustDto.getPrice()));
                }
                else {
                    //已匹配份额
                    BigDecimal entrustQty = ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),4);
                    commonEntrustDto.setMarchQty(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getShareAmt(),entrustQty))>0 ? dto.getShareAmt() : entrustQty);
                    //已匹配金额
                    BigDecimal entrustAmt = ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),4),dto.getUnitCprice());
                    commonEntrustDto.setMarchAmt(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getTradeAmt(),entrustAmt))>0?dto.getTradeAmt() : entrustAmt);
                    //指令已匹配份额
                    BigDecimal dealAmtEn = ArithmeticUtil.multiply(dto.getRemainNum(),dto.getUnitCprice());
                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dealAmtEn,commonEntrustDto.getEntrustAmt()))>0?dealAmtEn : commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),4));
                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dealAmtEn,commonEntrustDto.getEntrustNum()))>0?dealAmtEn : commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice()));
                }

                if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum())) >=0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),4))) >=0)) {
                    matchStatus = DictCodeListExt3.下行交易匹配状态_匹配完成.getValue();
                }
                else if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum())) < 0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),4))) < 0)){
                    matchStatus = DictCodeListExt3.下行交易匹配状态_部分匹配.getValue();
                }

                commonEntrustDtoList.add(commonEntrustDto);
            }
            //多条指令匹配时
            else {
                //处理可分配份额和可分配金额
                BigDecimal totalMarchQty = BigDecimal.ZERO;
                matchStatus = DictCodeListExt3.下行交易匹配状态_未匹配.getValue();
                for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                    if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                        commonEntrustDto.setMarchQty(commonEntrustDto.getEntrustNum());
                        commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(commonEntrustDto.getEntrustNum(),dto.getUnitFprice()));
                    }
                    else {
                        commonEntrustDto.setMarchQty(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getCpriceAmt(),4));
                        commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getCpriceAmt(),4),dto.getUnitFprice()));
                    }
                    totalMarchQty = ArithmeticUtil.doAdd(totalMarchQty,commonEntrustDto.getMarchQty());
                }

                if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalMarchQty))<=0) {
                    for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                        if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                            commonEntrustDto.setMarchQtyEn(commonEntrustDto.getEntrustNum());
                            commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getEntrustNum(),commonEntrustDto.getPrice()));
                        }
                        else {
                            commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),4));
                            commonEntrustDto.setMarchAmtEn(commonEntrustDto.getEntrustAmt());
                        }
                    }
                    matchStatus = DictCodeListExt3.下行交易匹配状态_匹配完成.getValue();
                }
                else {
                    //临时存储
                    List<ExchangeTransMatchCommonEntrustDto> commonEntrustDtoListTmp = new ArrayList<>();
                    String matchRule = CacheUtil.getSysParam("MATCH_RULE");
                    //已经完全匹配的可匹配份额
                    BigDecimal totalFinishMarchQty = BigDecimal.ZERO;
                    //已经完全匹配的可匹配金额
                    BigDecimal totalFinishMarchAmt = BigDecimal.ZERO;
                    //已经完全匹配的可分配随交易费用
                    BigDecimal totalFinishMarchFeeAmtPay = BigDecimal.ZERO;
                    //已经完全匹配的可分配不随交易费用
                    BigDecimal totalFinishMarchUnFeeAmtPay = BigDecimal.ZERO;


                    //系统参数.匹配规则 = 按照指令下达时间顺序匹配
                    if(StringUtil.equals(matchRule,"01")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getUpdateTime)).collect(Collectors.toList());
                    }
                    //系统参数.匹配规则 = 按照指令金额从高到低匹配
                    else if(StringUtil.equals(matchRule,"02")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getEntrustAmt).reversed()).collect(Collectors.toList());
                    }

                    if(StringUtil.equals(matchRule,"01") || StringUtil.equals(matchRule,"02")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getUpdateTime)).collect(Collectors.toList());
                        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                            //已完全匹配的可匹配份额 大于 剩余成交数量 时不再继续分配
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) >= 0){
                                break;
                            }

                            //能完全分配数据的指令
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) < 0) {
                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getEntrustNum());
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getEntrustNum(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),4));
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getEntrustAmt());
                                }

                                commonEntrustDto.setMarchFeeAmtPay(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),dto.getRemainNum(),4),dto.getTotalFeeAmtPay()));
                                commonEntrustDto.setMarchFeeAmtUnPay(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),dto.getRemainNum(),4),dto.getTotalUnFeeAmtPay()));

                                totalFinishMarchQty = ArithmeticUtil.doAdd(totalFinishMarchQty,commonEntrustDto.getMarchQty());
                                totalFinishMarchAmt = ArithmeticUtil.doAdd(totalFinishMarchAmt,commonEntrustDto.getMarchAmt());
                                totalFinishMarchFeeAmtPay = ArithmeticUtil.doAdd(totalFinishMarchFeeAmtPay,commonEntrustDto.getMarchFeeAmtPay());
                                totalFinishMarchUnFeeAmtPay = ArithmeticUtil.doAdd(totalFinishMarchUnFeeAmtPay,commonEntrustDto.getMarchFeeAmtUnPay());
                                
                                commonEntrustDto.setLastEntrust(false);
                            }
                            //最后一笔未能完全分配数据指令
                            else {
                                //成交列表选择记录.剩余成交数量 - 匹配列表选择记录中已经完全匹配的可匹配份额
                                commonEntrustDto.setMarchQty(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty),commonEntrustDto.getMarchQty()));
                                //成交列表选择记录.剩余成交金额 - 匹配列表选择记录中已经完全匹配的可匹配金额
                                commonEntrustDto.setMarchAmt(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainAmount(),totalFinishMarchAmt),commonEntrustDto.getMarchAmt()));

                                commonEntrustDto.setMarchFeeAmtPay(ArithmeticUtil.subtract(dto.getTotalFeeAmtPay(),totalFinishMarchFeeAmtPay));
                                commonEntrustDto.setMarchFeeAmtUnPay(ArithmeticUtil.subtract(dto.getTotalUnFeeAmtPay(),totalFinishMarchUnFeeAmtPay));

                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getMarchQty());
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(),commonEntrustDto.getPrice()));

                                }
                                else {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchAmt(),commonEntrustDto.getPrice(),4));
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getMarchAmt());
                                }
                                commonEntrustDto.setLastEntrust(true);
                            }
                            commonEntrustDtoListTmp.add(commonEntrustDto);
                        }
                    }
                    //系统参数.匹配规则 = 按照指令金额总体占比分配
                    else if(StringUtil.equals(matchRule,"03")) {
                        for(int i = 0 ;i< commonEntrustDtoList.size(); i++) {
                            ExchangeTransMatchCommonEntrustDto commonEntrustDto = commonEntrustDtoList.get(i);
                            BigDecimal marchQty = BigDecimal.ZERO;
                            BigDecimal marchAmt = BigDecimal.ZERO;

                            if(i<commonEntrustDtoList.size()-1) {
                                marchQty = ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,4),dto.getRemainNum());
                                marchAmt = ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,4),dto.getRemainAmount());

                                commonEntrustDto.setMarchFeeAmtPay(ArithmeticUtil.multiply(ArithmeticUtil.divide(marchQty,dto.getRemainNum(),4),dto.getTotalFeeAmtPay()));
                                commonEntrustDto.setMarchFeeAmtUnPay(ArithmeticUtil.multiply(ArithmeticUtil.divide(marchQty,dto.getRemainNum(),4),dto.getTotalUnFeeAmtPay()));

                                totalFinishMarchQty = ArithmeticUtil.doAdd(totalFinishMarchQty,marchQty);
                                totalFinishMarchAmt = ArithmeticUtil.doAdd(totalFinishMarchAmt,marchAmt);

                                totalFinishMarchFeeAmtPay = ArithmeticUtil.doAdd(totalFinishMarchFeeAmtPay,commonEntrustDto.getMarchFeeAmtPay());
                                totalFinishMarchUnFeeAmtPay = ArithmeticUtil.doAdd(totalFinishMarchUnFeeAmtPay,commonEntrustDto.getMarchFeeAmtUnPay());

                                commonEntrustDto.setLastEntrust(false);
                            }
                            else {
                                marchQty = ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty);
                                marchAmt = ArithmeticUtil.subtract(dto.getRemainAmount(),totalFinishMarchAmt);

                                commonEntrustDto.setMarchFeeAmtPay(ArithmeticUtil.subtract(dto.getTotalFeeAmtPay(),totalFinishMarchFeeAmtPay));
                                commonEntrustDto.setMarchFeeAmtUnPay(ArithmeticUtil.subtract(dto.getTotalUnFeeAmtPay(),totalFinishMarchUnFeeAmtPay));

                                commonEntrustDto.setLastEntrust(true);
                            }

                            commonEntrustDto.setMarchQty(marchQty);
                            commonEntrustDto.setMarchAmt(marchAmt);
                            if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                commonEntrustDto.setMarchQtyEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,4),dto.getRemainNum()));
                                commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,4),dto.getRemainNum(),commonEntrustDto.getPrice()));
                            }
                            else {
                                commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,4),dto.getRemainNum(),dto.getUnitCprice()));
                                commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchAmtEn(),commonEntrustDto.getPrice(),4));
                            }

                            commonEntrustDtoListTmp.add(commonEntrustDto);
                        }
                    }

                    commonEntrustDtoList.clear();
                    commonEntrustDtoList.addAll(commonEntrustDtoListTmp);
                    matchStatus = DictCodeListExt3.下行交易匹配状态_部分匹配.getValue();
                }
            }

            //处理匹配成交相关的表
            this.handleSettleTransTableInfo(dto,commonEntrustDtoList,matchStatus);
        }

    }

    /**
     * 处理匹配成交相关的表
     * @param dto
     * @param commonEntrustDtoList
     * @param matchStatus
     */
    private void handleSettleTransTableInfo(ExchangeTransMatchCommonDto dto,List<ExchangeTransMatchCommonEntrustDto> commonEntrustDtoList,String matchStatus) {

        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
            //处理业务交易表
            String tradeId = this.handleFinProductTradeDto(dto,commonEntrustDto);

            //登录:汇总成交数据匹配结果表（SETTLE_TRANS_DATA_TOTAL_MATCH）
            SettleTransDataTotalMatch settleTransDataTotalMatch = this.genSettleTransDataTotalMatch(dto,commonEntrustDto);
            settleTransDataTotalMatchMapper.insertSelective(settleTransDataTotalMatch);

            //登录:成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
            SettleTransTgMapping settleTransTgMapping = this.genSettleTransTgMapping(dto,tradeId);
            settleTransTgMappingMapper.insertSelective(settleTransTgMapping);
        }

        //更新:汇总成交数据状态表（SETTLE_TRANS_DATA_TOTAL_STATUS）
        SettleTransDataTotalStatus settleTransDataTotalStatus = settleTransDataTotalStatusMapper.selectByPrimaryKey(dto.getSeqId());
        if(StringUtil.isNotEmpty(settleTransDataTotalStatus)) {
            settleTransDataTotalStatus.setMatchStatus(matchStatus);
            settleTransDataTotalStatus.setUpdateTime(new Date());
            settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
            settleTransDataTotalStatusMapper.updateByPrimaryKey(settleTransDataTotalStatus);
        }
    }

    private String handleFinProductTradeDto(ExchangeTransMatchCommonDto dto,ExchangeTransMatchCommonEntrustDto commonEntrustDto){
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);

        String busiType = "";
        if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            if(StringUtil.equals(commonEntrustDto.getPs(),DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(commonEntrustDto.getPs(),DictCodeList.交易方向_卖出.getValue())
        || StringUtil.equals(commonEntrustDto.getPs(),DictCodeList.交易方向_行权_回售.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchBond1Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
            else if (StringUtil.equals(commonEntrustDto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchBond2Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
            else if (StringUtil.equals(commonEntrustDto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchBond3Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
            else if(StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt1.交易方向_债转股.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchBond4Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
            else if(StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt3.交易方向_配债.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchBond5Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
        || StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
               if (StringUtil.equals(commonEntrustDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchRepo1Service.genFinProductTradeDto(dto,commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchFund1Service.genFinProductTradeDto(dto,commonEntrustDto);
            baseTradeProductDealDtos.add(baseTradeProductDealDto);
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
            if (StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt2.交易方向_新股中签.getValue())
                    || StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt2.交易方向_增发.getValue())
                    || StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt2.交易方向_配股.getValue())
                    || StringUtil.equals(commonEntrustDto.getPs(), DictCodeList.交易方向_买入.getValue())
                    || StringUtil.equals(commonEntrustDto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchShares1Service.genFinProductTradeDto(dto, commonEntrustDto);
                baseTradeProductDealDtos.add(baseTradeProductDealDto);
            }
        }
        //交易入库
        String tradeId = "";
        for(BaseTradeProductDealDto baseTradeProductDealDto: finProductTradeDto.getBaseTradeProductDealDtos()) {
            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdFinProductDealTotal())){
                trdFinProductDealTotalMapper.insertSelective(baseTradeProductDealDto.getTrdFinProductDealTotal());
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getPaTrdFinProductDealTotal())){
                paTrdFinProductDealTotalMapper.insertSelective(baseTradeProductDealDto.getPaTrdFinProductDealTotal());
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdDealTotalAdd())){
                trdDealTotalAddMapper.insertSelective(baseTradeProductDealDto.getTrdDealTotalAdd());
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdProductDeal())){
                tradeId = StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdProductDeal().getTotleTradeId())
                ?baseTradeProductDealDto.getTrdProductDeal().getTotleTradeId() : baseTradeProductDealDto.getTrdProductDeal().getTradeId();
                trdProductDealMapper.insertSelective(baseTradeProductDealDto.getTrdProductDeal());
            }
            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdProductDealAdd())){
                trdProductDealAddMapper.insertSelective(baseTradeProductDealDto.getTrdProductDealAdd());
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getPaTrdProductDeal())){
                paTrdProductDealMapper.insertSelective(baseTradeProductDealDto.getPaTrdProductDeal());
            }

            if(!StringUtil.isEmptyList(baseTradeProductDealDto.getStlPayOrdersList())){
                for(StlPayOrdersDto stlPayOrdersDto:baseTradeProductDealDto.getStlPayOrdersList()) {
                    stlPayOrdersMapper.insertSelective(stlPayOrdersDto);
                }
            }

            if(!StringUtil.isEmptyList(baseTradeProductDealDto.getTrdFinProductBarterList())){
                for(TrdFinProductBarter trdFinProductBarter:baseTradeProductDealDto.getTrdFinProductBarterList()) {
                    trdFinProductBarterMapper.insertSelective(trdFinProductBarter);
                }
            }

            if(!StringUtil.isEmptyList(baseTradeProductDealDto.getTrdTradeFeeDetailList())){
                for(TrdTradeFeeDetail TrdTradeFeeDetail:baseTradeProductDealDto.getTrdTradeFeeDetailList()) {
                    trdTradeFeeDetailMapper.insertSelective(TrdTradeFeeDetail);
                }
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdTradeFeeDetail())){
                trdTradeFeeDetailMapper.insertSelective(baseTradeProductDealDto.getTrdTradeFeeDetail());
            }

        }

        //处理头寸和指令完成度
        baseTradeProductDealCommService.execute(finProductTradeDto);

        return tradeId;
    }

    /**
     * 构造 汇总成交数据匹配结果表
     * @param dealDto
     * @param etrEntrust
     * @return
     */
    private SettleTransDataTotalMatch genSettleTransDataTotalMatch(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto etrEntrust) {
        SettleTransDataTotalMatch settleTransDataTotalMatch = new SettleTransDataTotalMatch();
        //成交列表选择记录.流水号
        settleTransDataTotalMatch.setSeqNo(dealDto.getSeqId());
        //匹配列表选择记录.业务编号
        settleTransDataTotalMatch.setEntrunstTradeId(etrEntrust.getEntrustId());
        //匹配列表选择记录.目标类型
        settleTransDataTotalMatch.setMatchClass(etrEntrust.getTargetType());
        //数据字典.下行交易匹配方式（TRD_MATCH_TYPE）.手动匹配
        settleTransDataTotalMatch.setMatchType(dealDto.getMatchType());
        //已匹配份额
        settleTransDataTotalMatch.setMarchQty(etrEntrust.getMarchQty());
        //已匹配金额
        settleTransDataTotalMatch.setMarchAmt(etrEntrust.getMarchAmt());
        //指令已匹配份额
        settleTransDataTotalMatch.setMarchQtyEn(etrEntrust.getMarchQtyEn());
        //指令已匹配金额
        settleTransDataTotalMatch.setMarchAmtEn(etrEntrust.getMarchAmtEn());
        //币种
        settleTransDataTotalMatch.setCcy(dealDto.getCcy());
        return settleTransDataTotalMatch;
    }


    /**
     * 构造 成交数据与投管交易关系
     * @param dealDto
     * @param tradeId
     * @return
     */
    private SettleTransTgMapping genSettleTransTgMapping(ExchangeTransMatchCommonDto dealDto,String tradeId) {
        SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
        settleTransTgMapping.setTradeId(dealDto.getSeqId());
        settleTransTgMapping.setTradeIdTg(tradeId);
        settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());

        return settleTransTgMapping;
    }
}
