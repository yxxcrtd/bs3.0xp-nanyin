package com.joyintech.tams.invest.common.transmatch.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.transmatch.dao.ExchangeTransMatchCommonMapper;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 场内成交管理共通service
 */
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

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private TrdDealTotalAddMapper trdDealTotalAddMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

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
                    BigDecimal entrustQty = ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),2);
                    commonEntrustDto.setMarchQty(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getShareAmt(),entrustQty))>0 ? dto.getShareAmt() : entrustQty);
                    //已匹配金额
                    BigDecimal entrustAmt = ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),2),dto.getUnitCprice());
                    commonEntrustDto.setMarchAmt(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getTradeAmt(),entrustAmt))>0?dto.getTradeAmt() : entrustAmt);
                    //指令已匹配份额
                    BigDecimal dealAmtEn = ArithmeticUtil.multiply(dto.getRemainNum(),dto.getUnitCprice());
                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dealAmtEn,commonEntrustDto.getEntrustAmt()))>0?dealAmtEn : commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),2));
                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dealAmtEn,commonEntrustDto.getEntrustNum()))>0?dealAmtEn : commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice()));
                }

                if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum())) >=0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),2))) >=0)) {
                    matchStatus = DictCodeListExt3.下行交易匹配状态_匹配完成.getValue();
                }
                else if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getEntrustNum())) < 0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getUnitCprice(),2))) < 0)){
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
                        commonEntrustDto.setMarchQty(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getCpriceAmt(),2));
                        commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),dto.getCpriceAmt(),2),dto.getUnitFprice()));
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
                            commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),2));
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
                    //系统参数.匹配规则 = 按照指令下达时间顺序匹配
                    if(StringUtil.equals(matchRule,"01")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getUpdateTime)).collect(Collectors.toList());
                        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                            //已完全匹配的可匹配份额 大于 剩余成交数量 时不再继续分配
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) >= 0){
                                break;
                            }

                            totalFinishMarchQty = ArithmeticUtil.doAdd(totalFinishMarchQty,commonEntrustDto.getMarchQty());
                            totalFinishMarchAmt = ArithmeticUtil.doAdd(totalFinishMarchAmt,commonEntrustDto.getMarchAmt());

                            //能完全分配数据的指令
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) < 0) {
                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getEntrustNum());
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getEntrustNum(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),2));
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getEntrustAmt());
                                }
                            }
                            //最后一笔未能完全分配数据指令
                            else {
                                //成交列表选择记录.剩余成交数量 - 匹配列表选择记录中已经完全匹配的可匹配份额
                                commonEntrustDto.setMarchQty(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty),commonEntrustDto.getMarchQty()));
                                //成交列表选择记录.剩余成交金额 - 匹配列表选择记录中已经完全匹配的可匹配金额
                                commonEntrustDto.setMarchAmt(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainAmount(),totalFinishMarchAmt),commonEntrustDto.getMarchAmt()));
                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getMarchQty());
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchAmt(),commonEntrustDto.getPrice(),2));
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getMarchAmt());
                                }
                            }
                            commonEntrustDtoListTmp.add(commonEntrustDto);
                        }
                    }
                    //系统参数.匹配规则 = 按照指令金额从高到低匹配
                    else if(StringUtil.equals(matchRule,"02")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getEntrustAmt).reversed()).collect(Collectors.toList());
                        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                            //已完全匹配的可匹配份额 大于 剩余成交数量 时不再继续分配
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) > 0){
                                break;
                            }

                            totalFinishMarchQty = ArithmeticUtil.doAdd(totalFinishMarchQty,commonEntrustDto.getMarchQty());
                            totalFinishMarchAmt = ArithmeticUtil.doAdd(totalFinishMarchAmt,commonEntrustDto.getMarchAmt());

                            //能完全分配数据的指令
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) < 0) {
                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getEntrustNum());
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getEntrustNum(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getEntrustAmt(),commonEntrustDto.getPrice(),2));
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getEntrustAmt());
                                }
                            }
                            //最后一笔未能完全分配数据指令
                            else {
                                //成交列表选择记录.剩余成交数量 - 匹配列表选择记录中已经完全匹配的可匹配份额
                                commonEntrustDto.setMarchQty(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty),commonEntrustDto.getMarchQty()));
                                //成交列表选择记录.剩余成交金额 - 匹配列表选择记录中已经完全匹配的可匹配金额
                                commonEntrustDto.setMarchAmt(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(dto.getRemainAmount(),totalFinishMarchAmt),commonEntrustDto.getMarchAmt()));
                                if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getMarchQty());
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    //指令已匹配份额
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchAmt(),commonEntrustDto.getPrice(),2));
                                    //指令已匹配金额
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getMarchAmt());
                                }
                            }
                            commonEntrustDtoListTmp.add(commonEntrustDto);
                        }

                    }
                    //系统参数.匹配规则 = 按照指令金额总体占比分配
                    else if(StringUtil.equals(matchRule,"03")) {
                        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                            commonEntrustDto.setMarchQty(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,2),dto.getRemainNum()));
                            commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,2),dto.getRemainAmount()));
                            if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                commonEntrustDto.setMarchQtyEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,2),dto.getRemainNum()));
                                commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,2),dto.getRemainNum(),commonEntrustDto.getPrice()));
                            }
                            else {
                                commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),totalMarchQty,2),dto.getRemainNum(),dto.getUnitCprice()));
                                commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchAmtEn(),commonEntrustDto.getPrice(),2));
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
            FinProductTradeDto finProductTradeDto = this.handleFinProductTradeDto(dto,commonEntrustDto);

            //登录:汇总成交数据匹配结果表（SETTLE_TRANS_DATA_TOTAL_MATCH）
            SettleTransDataTotalMatch settleTransDataTotalMatch = this.genSettleTransDataTotalMatch(dto,commonEntrustDto);
            settleTransDataTotalMatchMapper.insertSelective(settleTransDataTotalMatch);

            //登录:成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
            SettleTransTgMapping settleTransTgMapping = this.genSettleTransTgMapping(dto,"");
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

    private FinProductTradeDto handleFinProductTradeDto(ExchangeTransMatchCommonDto dto,ExchangeTransMatchCommonEntrustDto commonEntrustDto){
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDtos.add(baseTradeProductDealDto);

        String busiType = "";
        if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            busiType = DictCodeList.共通业务类型_债券类资产交易.getValue();
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
        || StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
            busiType = DictCodeList.共通业务类型_质押式回购交易.getValue();
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            busiType = DictCodeList.共通业务类型_基金类资产交易.getValue();
        }
        else if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
            busiType = DictCodeListExt2.共通业务类型_股票投资交易.getValue();
        }

        if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            //交易汇总表
            TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotal(dto,commonEntrustDto,busiType);
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
            //交易汇总附属表
            TrdDealTotalAdd trdDealTotalAdd = this.genTrdDealTotalAdd(trdFinProductDealTotal,commonEntrustDto);
            trdDealTotalAddMapper.insertSelective(trdDealTotalAdd);

            PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
            paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
            paTrdFinProductDealTotal.setPaBussType(getPaBussType(commonEntrustDto.getPs()));
            paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        }

        return finProductTradeDto;
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

    /**
     *
     * @param parameter
     * @return
     */
    private String buildBussinessCode(BussinessCodeParameter parameter) {
        BussinessCodeParameter req = new BussinessCodeParameter();
        req.setMapInfo(parameter.initMapInfo());
        // 生成的业务编号
        return restTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/core/selectRule", req,
                String.class);
    }

    private TrdFinProductDealTotal genTrdFinProductDealTotal(ExchangeTransMatchCommonDto dto,ExchangeTransMatchCommonEntrustDto commonEntrustDto,String busiType){
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if(StringUtil.equals(busiType,DictCodeList.共通业务类型_债券类资产交易.getValue())) {
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), commonEntrustDto.getPs())) {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
            }
            else {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
            }
        }

        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(this.buildBussinessCode(codeParameter));
        trdFinProductDealTotal.setBusiType(busiType);
        trdFinProductDealTotal.setFinprodId(commonEntrustDto.getFinprodId());
        if(StringUtil.equals(commonEntrustDto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
            trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            trdFinProductDealTotal.setTgBusiType(this.getPaBussType(commonEntrustDto.getPs()));
        }
        trdFinProductDealTotal.setBranch(0L);
        trdFinProductDealTotal.setTradeDate(dto.getTradeDate());
        trdFinProductDealTotal.setVdate(dto.getVdate());
        trdFinProductDealTotal.setMdate(dto.getMdate());
        trdFinProductDealTotal.setSettleDate(dto.getSettleDate());
        trdFinProductDealTotal.setCcy(dto.getCcy());
        trdFinProductDealTotal.setUnitCprice(dto.getUnitCprice());
        trdFinProductDealTotal.setUnitInt(dto.getUnitInt());
        trdFinProductDealTotal.setUnitFprice(dto.getUnitFprice());
        trdFinProductDealTotal.setParValue(dto.getParValue());
        trdFinProductDealTotal.setCpriceAmt(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(),dto.getUnitCprice()));
        trdFinProductDealTotal.setShareAmt(commonEntrustDto.getMarchQty());
        trdFinProductDealTotal.setPrinAmt(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(),dto.getParValue()));
        trdFinProductDealTotal.setIntAmt(ArithmeticUtil.multiply(commonEntrustDto.getMarchQty(), dto.getUnitInt()));
        trdFinProductDealTotal.setFpriceAmt(commonEntrustDto.getMarchAmt());
        trdFinProductDealTotal.setFeeAmtPay(commonEntrustDto.getMarchFeeAmtPay());
        trdFinProductDealTotal.setTradeAmt(ArithmeticUtil.subtract(commonEntrustDto.getMarchAmt(),commonEntrustDto.getMarchFeeAmtPay()));
        trdFinProductDealTotal.setFeeAmtUnpay(commonEntrustDto.getMarchFeeAmtUnPay());
        trdFinProductDealTotal.setFeeAmt(ArithmeticUtil.doAdd(commonEntrustDto.getMarchFeeAmtPay(),commonEntrustDto.getMarchFeeAmtUnPay()));
        trdFinProductDealTotal.setPs(commonEntrustDto.getPs());
        trdFinProductDealTotal.setYield(dto.getIntRate());
        trdFinProductDealTotal.setInvAim(commonEntrustDto.getInvAim());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setIsClean(DictCodeList.是否_是.getValue());
        trdFinProductDealTotal.setSecManageAcctId(dto.getSecAcctId());
        trdFinProductDealTotal.setCounterId(commonEntrustDto.getCounterId());
        trdFinProductDealTotal.setIsOutTrade(DictCodeList.是否_是.getValue());
        trdFinProductDealTotal.setTradeMarket(dto.getTradeMarket());
        trdFinProductDealTotal.setTradePlat(dto.getTradePlat());
        trdFinProductDealTotal.setTradeMarketMode(dto.getTradeMarketMode());
        trdFinProductDealTotal.setTrader(commonEntrustDto.getTrader());
        trdFinProductDealTotal.setCounterTrader(commonEntrustDto.getCounterTrader());
        trdFinProductDealTotal.setPortfolioId(commonEntrustDto.getPortfolioId());
        trdFinProductDealTotal.setRemark(commonEntrustDto.getRemark());

        return trdFinProductDealTotal;
    }

    private TrdDealTotalAdd genTrdDealTotalAdd(TrdFinProductDealTotal trdFinProductDealTotal,ExchangeTransMatchCommonEntrustDto commonEntrustDto) {
        TrdDealTotalAdd trdDealTotalAdd = new TrdDealTotalAdd();
        trdDealTotalAdd.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        trdDealTotalAdd.setOurShareAmt(ArithmeticUtil.divide(trdFinProductDealTotal.getTradeAmt(), 100, 4));
        trdDealTotalAdd.setLendinShareAmt(BigDecimal.ZERO);
        trdDealTotalAdd.setOurFpriceAmt(trdFinProductDealTotal.getFpriceAmt());
        trdDealTotalAdd.setLendinFpriceAmt(BigDecimal.ZERO);
        trdDealTotalAdd.setOurCpriceAmt(trdFinProductDealTotal.getCpriceAmt());
        trdDealTotalAdd.setLendinCpriceAmt(BigDecimal.ZERO);
        trdDealTotalAdd.setOurFpriceAmt2(BigDecimal.ZERO);
        trdDealTotalAdd.setLendinFpriceAmt2(BigDecimal.ZERO);
        trdDealTotalAdd.setEntrustId(commonEntrustDto.getEntrustId());
        return trdDealTotalAdd;
    }

    private String getPaBussType(String 交易方向) {
        // 平安业务类型(数据字典缓存.平安业务类型（按交易方向取对应值）)
        if (com.joyintech.framework.util.StringUtil.equals(DictCodeList.交易方向_买入.getValue(), 交易方向)) {
            return DictCodeListExt1.平安业务类型_债券类资产买入.getValue();
        }
        else if (com.joyintech.framework.util.StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), 交易方向)) {
            return DictCodeListExt1.平安业务类型_债券类资产卖出.getValue();
        }
        // 一体化对应etliu start
        // else if (StringUtil.equals(DictCodeList.交易方向_行权.getValue(), 交易方向)) {
        else if (com.joyintech.framework.util.StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), 交易方向)
                || com.joyintech.framework.util.StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), 交易方向)) {
            // 一体化对应etliu end
            return DictCodeListExt1.平安业务类型_债券类资产行权.getValue();
        }
        return null;
    }
}
