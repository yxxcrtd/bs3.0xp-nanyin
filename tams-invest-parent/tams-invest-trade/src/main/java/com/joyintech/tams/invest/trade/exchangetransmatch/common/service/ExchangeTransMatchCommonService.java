package com.joyintech.tams.invest.trade.exchangetransmatch.common.service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonRevokeDto;
import com.joyintech.tams.invest.trade.api.PositionUpdateParam;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.dao.ExchangeTransMatchCommonMapper;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.Fund.ExchangeTransMatchFund1Service;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond.*;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.repo.ExchangeTransMatchRepo1Service;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.shares.ExchangeTransMatchShares1Service;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 场内成交管理共通service
 */
@Service
public class ExchangeTransMatchCommonService extends BaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ExchangeTransMatchCommonService.class);
    
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
    private SettleTransDataTotalMapper settleTransDataTotalMapper;

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
    private TrdTradeFeeTotalMapper trdTradeFeeTotalMapper;
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
    @Autowired
    private TrdFinProductBarterTotalMapper trdFinProductBarterTotalMapper;
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Value("${application.micorservice.contextPath.tamsServer:http://tams-server}")
    private String tamsSerever;

    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    /**
     * 交易完成后，调用共通修改SETTLE_TRANS_TG_MAPPING，SETTLE_TRANS_DATA_TOTAL_STATUS
     * @param seqId
     * @param tradeId
     */
    public void transactionRecord(String seqId, String tradeId, OperationType type) {
        if(!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            //登录:成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
            logger.error("交易完成,调用共通，成交流水号"+ seqId +",交易编号:"+tradeId +",开始");
            SettleTransDataTotal settleTransDataTotal = settleTransDataTotalMapper.selectByPrimaryKey(seqId);
            if(StringUtil.isNotEmpty(settleTransDataTotal)) {
                ExchangeTransMatchCommonDto dto = new ExchangeTransMatchCommonDto();
                BeanUtil.copyPropertiesIgnoreNull(dto,settleTransDataTotal);
                List<SettleTransTgMapping> settleTransTgMappingList = this.genSettleTransTgMapping(dto,tradeId);
                if(!StringUtil.isEmptyList(settleTransTgMappingList)) {
                    for(SettleTransTgMapping settleTransTgMapping:settleTransTgMappingList) {
                        settleTransTgMappingMapper.insertSelective(settleTransTgMapping);
                    }
                }

            //更新:汇总成交数据状态表（SETTLE_TRANS_DATA_TOTAL_STATUS）
            SettleTransDataTotalStatus settleTransDataTotalStatus = settleTransDataTotalStatusMapper.selectByPrimaryKey(dto.getSeqId());
            if(StringUtil.isNotEmpty(settleTransDataTotalStatus)) {
                settleTransDataTotalStatus.setMatchStatus(DictCodeListExt3.下行交易匹配状态_匹配完成.getValue());
                settleTransDataTotalStatus.setUpdateTime(new Date());
                settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                settleTransDataTotalStatusMapper.updateByPrimaryKey(settleTransDataTotalStatus);
            } else {
                settleTransDataTotalStatus = new SettleTransDataTotalStatus();
                settleTransDataTotalStatus.setSeqNo(dto.getSeqId());
                settleTransDataTotalStatus.setMatchStatus(DictCodeListExt3.下行交易匹配状态_匹配完成.getValue());
                settleTransDataTotalStatus.setUpdateTime(new Date());
                settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                settleTransDataTotalStatusMapper.insert(settleTransDataTotalStatus);
            }
        }
        else {
            logger.error("没有查询到成交信息,成交流水号:"+ seqId +",交易编号:"+tradeId);
        }

            logger.error("交易完成,调用共通，成交流水号"+ seqId +",交易编号:"+tradeId +",结束");
        }
        else {
            logger.error("交易撤销,调用共通,交易编号:"+tradeId +",开始");
            if(StringUtil.endsWith(tradeId,"R")) {
                tradeId = StringUtil.substring(tradeId,0,tradeId.length()-1);
            }

            String totalSeqId = exchangeTransMatchCommonMapper.getTotalSeqId(tradeId);
            if(StringUtil.isNotEmpty(totalSeqId)) {
                //如果存在委托代码，需要删除汇总成交数据匹配结果表数据
                String entrunstId = exchangeTransMatchCommonMapper.getEntrunstId(tradeId);
                if(StringUtil.isNotEmpty(entrunstId)) {
                    Map<String, String> paraMap = new HashMap<>();
                    paraMap.put("totalSeqId",totalSeqId);
                    paraMap.put("entrunstId",entrunstId);
                    exchangeTransMatchCommonMapper.deleteSettleTransDataTotalMatch(paraMap);
                }

                //更新汇总成交数据状态表
                SettleTransDataTotalStatus settleTransDataTotalStatus = settleTransDataTotalStatusMapper.selectByPrimaryKey(totalSeqId);
                if(StringUtil.isNotEmpty(settleTransDataTotalStatus)) {
                    //取得剩余匹配份额
                    BigDecimal remainNum = exchangeTransMatchCommonMapper.getRemainNum(totalSeqId);
                    settleTransDataTotalStatus.setMatchStatus(BigDecimal.ZERO.compareTo(remainNum) >=0
                            ?DictCodeListExt3.下行交易匹配状态_未匹配.getValue():DictCodeListExt3.下行交易匹配状态_部分匹配.getValue());
                    settleTransDataTotalStatus.setUpdateTime(new Date());
                    settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                    settleTransDataTotalStatusMapper.updateByPrimaryKey(settleTransDataTotalStatus);
                } else {
                    settleTransDataTotalStatus = new SettleTransDataTotalStatus();
                    settleTransDataTotalStatus.setSeqNo(totalSeqId);
                    //取得剩余匹配份额
                    BigDecimal remainNum = exchangeTransMatchCommonMapper.getRemainNum(totalSeqId);
                    settleTransDataTotalStatus.setMatchStatus(BigDecimal.ZERO.compareTo(remainNum) >=0
                            ?DictCodeListExt3.下行交易匹配状态_未匹配.getValue():DictCodeListExt3.下行交易匹配状态_部分匹配.getValue());
                    settleTransDataTotalStatus.setUpdateTime(new Date());
                    settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                    settleTransDataTotalStatusMapper.insert(settleTransDataTotalStatus);
                }

                //成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）：删除
                exchangeTransMatchCommonMapper.deleteSettleTransTgMapping(tradeId);
            }

            logger.error("交易撤销,调用共通,交易编号:"+tradeId +",结束");

        }

    }

    /**
     * 批量处理成交匹配数据
     * @param dtoList
     */
    public void transactionMacthBatch(List<ExchangeTransMatchCommonDto> dtoList){
        logger.error("批量处理成交匹配数据---开始，批量成交数据:"+ JsonUtils.objectArray2Json(dtoList));
        for(ExchangeTransMatchCommonDto dto:dtoList) {
            this.transactionMacth(dto);
        }
        logger.error("批量处理成交匹配数据---结束");
    }

    /**
     * 处理成交匹配数据
     * @param dto
     */
    public void transactionMacth(ExchangeTransMatchCommonDto dto) {
        logger.error("处理成交匹配数据---开始，成交数据:"+ JsonUtils.object2Json(dto));
        //查询汇总成交数据附属表
        dto.setSettleTransDataTotalAdd(settleTransDataTotalAddMapper.selectByPrimaryKey(dto.getSeqId()));
        //查询匹配的委托信息list
        Map<String,Object> entrustMap = new HashMap<>();
        entrustMap.put("entrustIdList",dto.getEntrustIdList());
        entrustMap.put("ENTRUST_STATUS_03",DictCodeList.委托状态_已接收.getValue());
        List<ExchangeTransMatchCommonEntrustDto> commonEntrustDtoList = exchangeTransMatchCommonMapper.getEtrEntrustByIdList(entrustMap);
        if(!StringUtil.isEmptyList(commonEntrustDtoList)) {
            //只有一条匹配指令时
            String matchStatus = DictCodeListExt3.下行交易匹配状态_未匹配.getValue();
            //计算 已匹配份额，已匹配金额，指令已匹配份额，指令已匹配金额
            if(commonEntrustDtoList.size() == 1) {
                ExchangeTransMatchCommonEntrustDto commonEntrustDto = commonEntrustDtoList.get(0);
                //特殊处理质押式竞价回购的分配逻辑
                if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                        || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                    //已匹配份额
                    BigDecimal remainQty = ArithmeticUtil.divide(dto.getRemainNum(),new BigDecimal(100),2);
                    BigDecimal marchQty= BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(remainQty,commonEntrustDto.getRemainEntrustNum()))>0 ? remainQty : commonEntrustDto.getRemainEntrustNum();
                    commonEntrustDto.setMarchQty(ArithmeticUtil.multiply(marchQty,new BigDecimal(100)));

                    //已匹配金额
                    commonEntrustDto.setMarchAmt(commonEntrustDto.getMarchQty());

                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getMarchAmt());
                    //指令已匹配份额
                    commonEntrustDto.setMarchQtyEn(marchQty);

                }
                else if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                    //已匹配份额
                    BigDecimal marchQty = BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(), commonEntrustDto.getRemainEntrustNum()))>0?dto.getRemainNum() : commonEntrustDto.getRemainEntrustNum();
                    commonEntrustDto.setMarchQty(marchQty);
                    //已匹配金额
                    if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                        commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(marchQty,dto.getUnitFprice()));
                    }
                    else {
                        commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(marchQty,dto.getUnitCprice()));
                    }

                    //指令已匹配份额
                    BigDecimal marchQtyEn = BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getRemainEntrustNum()))>0?dto.getRemainNum() : commonEntrustDto.getRemainEntrustNum();
                    commonEntrustDto.setMarchQtyEn(marchQtyEn);
                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(marchQtyEn,commonEntrustDto.getPrice()));
                }
                else {
                    //已匹配份额
                    BigDecimal entrustQty = ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),dto.getUnitCprice(),4);
                    commonEntrustDto.setMarchQty(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),entrustQty))>0 ? dto.getRemainNum() : entrustQty);
                    //已匹配金额
                    BigDecimal entrustAmt = BigDecimal.ZERO;
                    if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                         entrustAmt = ArithmeticUtil.multiply(entrustQty,dto.getUnitFprice());
                    }
                    else {
                        entrustAmt = ArithmeticUtil.multiply(entrustQty,dto.getUnitCprice());
                    }
                    commonEntrustDto.setMarchAmt(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainAmount(),entrustAmt))>0?dto.getRemainAmount() : entrustAmt);

                    //指令已匹配份额
                    BigDecimal marchAmtEn = ArithmeticUtil.multiply(dto.getRemainNum(),dto.getUnitCprice());
                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(marchAmtEn,commonEntrustDto.getRemainEntrustAmt()))>0?marchAmtEn : commonEntrustDto.getRemainEntrustAmt(),commonEntrustDto.getPrice(),4));
                    //指令已匹配金额
                    commonEntrustDto.setMarchAmtEn(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(marchAmtEn,commonEntrustDto.getRemainEntrustAmt()))>0?marchAmtEn : commonEntrustDto.getRemainEntrustAmt());


                }

                if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                        || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                    if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getRemainEntrustAmt())) >=0) {
                        matchStatus = DictCodeListExt3.下行交易匹配状态_匹配完成.getValue();
                    }
                    else {
                        matchStatus = DictCodeListExt3.下行交易匹配状态_部分匹配.getValue();
                    }
                }
                else if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getRemainEntrustNum())) >=0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                            && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),dto.getUnitCprice(),4))) >=0)) {
                    matchStatus = DictCodeListExt3.下行交易匹配状态_匹配完成.getValue();
                }
                else if((StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),commonEntrustDto.getRemainEntrustNum())) < 0)
                        || (StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_金额.getValue())
                        && BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),dto.getUnitCprice(),4))) < 0)){
                    matchStatus = DictCodeListExt3.下行交易匹配状态_部分匹配.getValue();
                }
            }
            //多条指令匹配时
            else {
                //处理可分配份额和可分配金额
                BigDecimal totalMarchQty = BigDecimal.ZERO;
                matchStatus = DictCodeListExt3.下行交易匹配状态_未匹配.getValue();
                for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                    if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                            || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                        commonEntrustDto.setMarchQty(commonEntrustDto.getRemainEntrustAmt());
                        commonEntrustDto.setMarchAmt(commonEntrustDto.getRemainEntrustAmt());
                    }
                    else if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                        commonEntrustDto.setMarchQty(commonEntrustDto.getRemainEntrustNum());
                        if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                            commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(commonEntrustDto.getRemainEntrustNum(),dto.getUnitFprice()));
                        }
                        else {
                            commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(commonEntrustDto.getRemainEntrustNum(),dto.getUnitCprice()));
                        }

                    }
                    else {
                        commonEntrustDto.setMarchQty(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),dto.getUnitCprice(),4));
                        if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                            commonEntrustDto.setMarchAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),dto.getUnitCprice(),4),dto.getUnitFprice()));
                        }
                        else {
                            commonEntrustDto.setMarchAmt(commonEntrustDto.getRemainEntrustAmt());
                        }

                    }
                    totalMarchQty = ArithmeticUtil.doAdd(totalMarchQty,commonEntrustDto.getMarchQty());
                }

                if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalMarchQty))>=0) {
                    for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                        if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                                || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                            commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustNum(),new BigDecimal(100),2));
                            commonEntrustDto.setMarchAmtEn(commonEntrustDto.getRemainEntrustAmt());
                        }
                        else if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                            commonEntrustDto.setMarchQtyEn(commonEntrustDto.getRemainEntrustNum());
                            commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getRemainEntrustNum(),commonEntrustDto.getPrice()));
                        }
                        else {
                            commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),commonEntrustDto.getPrice(),4));
                            commonEntrustDto.setMarchAmtEn(commonEntrustDto.getRemainEntrustAmt());
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
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getRemainEntrustAmt).reversed()).collect(Collectors.toList());
                    }

                    if(StringUtil.equals(matchRule,"01") || StringUtil.equals(matchRule,"02")) {
                        commonEntrustDtoList = commonEntrustDtoList.stream().sorted(Comparator.comparing(ExchangeTransMatchCommonEntrustDto::getUpdateTime)).collect(Collectors.toList());
                        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
                            //已完全匹配的可匹配份额 大于 剩余成交数量 时不再继续分配
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty)) >= 0){
                                break;
                            }

                            //能完全分配数据的指令
                            //能完全分配数据的指令
                            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getRemainNum(),ArithmeticUtil.doAdd(totalFinishMarchQty,commonEntrustDto.getMarchQty()))) < 0) {
                                if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                                        || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustNum(),new BigDecimal(100),2));
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getRemainEntrustAmt());
                                }
                                else if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(commonEntrustDto.getRemainEntrustNum());
                                    commonEntrustDto.setMarchAmtEn(ArithmeticUtil.multiply(commonEntrustDto.getRemainEntrustNum(),commonEntrustDto.getPrice()));
                                }
                                else {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getRemainEntrustAmt(),commonEntrustDto.getPrice(),4));
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getRemainEntrustAmt());
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
                                commonEntrustDto.setMarchQty(ArithmeticUtil.subtract(dto.getRemainNum(),totalFinishMarchQty));
                                //成交列表选择记录.剩余成交金额 - 匹配列表选择记录中已经完全匹配的可匹配金额
                                commonEntrustDto.setMarchAmt(ArithmeticUtil.subtract(dto.getRemainAmount(),totalFinishMarchAmt));

                                commonEntrustDto.setMarchFeeAmtPay(ArithmeticUtil.subtract(dto.getTotalFeeAmtPay(),totalFinishMarchFeeAmtPay));
                                commonEntrustDto.setMarchFeeAmtUnPay(ArithmeticUtil.subtract(dto.getTotalUnFeeAmtPay(),totalFinishMarchUnFeeAmtPay));

                                if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                                        || StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                                    commonEntrustDto.setMarchQtyEn(ArithmeticUtil.divide(commonEntrustDto.getMarchQty(),new BigDecimal(100),2) );
                                    commonEntrustDto.setMarchAmtEn(commonEntrustDto.getMarchAmt());
                                }
                                else if(StringUtil.equals(commonEntrustDto.getTargetType(),DictCodeListExt3.目标类型_数量.getValue())) {
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
        logger.error("处理成交匹配数据---结束");
    }

    /**
     * 处理匹配成交相关的表
     * @param dto
     * @param commonEntrustDtoList
     * @param matchStatus
     */
    private void handleSettleTransTableInfo(ExchangeTransMatchCommonDto dto,List<ExchangeTransMatchCommonEntrustDto> commonEntrustDtoList,String matchStatus) {
        logger.error("处理匹配成交相关的表---开始,委托list:"+JsonUtils.objectArray2Json(commonEntrustDtoList));
        for(ExchangeTransMatchCommonEntrustDto commonEntrustDto : commonEntrustDtoList) {
            //处理业务交易表

            String tradeId = this.handleFinProductTradeDto(dto,commonEntrustDto);

            //登录:汇总成交数据匹配结果表（SETTLE_TRANS_DATA_TOTAL_MATCH）
            SettleTransDataTotalMatch settleTransDataTotalMatch = this.genSettleTransDataTotalMatch(dto,commonEntrustDto);
            settleTransDataTotalMatchMapper.insertSelective(settleTransDataTotalMatch);

            //登录:成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
            List<SettleTransTgMapping> settleTransTgMappingList = this.genSettleTransTgMapping(dto,tradeId);
            if(!StringUtil.isEmptyList(settleTransTgMappingList)) {
                for(SettleTransTgMapping settleTransTgMapping:settleTransTgMappingList) {
                    settleTransTgMappingMapper.insertSelective(settleTransTgMapping);
                }
            }

        }

        //更新:汇总成交数据状态表（SETTLE_TRANS_DATA_TOTAL_STATUS）
        SettleTransDataTotalStatus settleTransDataTotalStatus = settleTransDataTotalStatusMapper.selectByPrimaryKey(dto.getSeqId());
        if(StringUtil.isNotEmpty(settleTransDataTotalStatus)) {
            settleTransDataTotalStatus.setMatchStatus(matchStatus);
            settleTransDataTotalStatus.setUpdateTime(new Date());
            settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
            settleTransDataTotalStatusMapper.updateByPrimaryKey(settleTransDataTotalStatus);
        } else {
            settleTransDataTotalStatus = new SettleTransDataTotalStatus();
            settleTransDataTotalStatus.setSeqNo(dto.getSeqId());
            settleTransDataTotalStatus.setMatchStatus(matchStatus);
            settleTransDataTotalStatusMapper.insert(settleTransDataTotalStatus);
        }
        logger.error("处理匹配成交相关的表---结束");
    }

    private String handleFinProductTradeDto(ExchangeTransMatchCommonDto dto,ExchangeTransMatchCommonEntrustDto commonEntrustDto){
        logger.error("处理业务交易表---开始");
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        finProductTradeDto.setNeedSum(true);
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
            BaseTradeProductDealDto baseTradeProductDealDto = exchangeTransMatchRepo1Service.genFinProductTradeDto(dto,commonEntrustDto);
            baseTradeProductDealDtos.add(baseTradeProductDealDto);
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

            if(!StringUtil.isEmptyList(baseTradeProductDealDto.getTrdTradeFeeTotalList())) {
                for(TrdTradeFeeTotal trdTradeFeeTotal:baseTradeProductDealDto.getTrdTradeFeeTotalList()) {
                    trdTradeFeeTotalMapper.insertSelective(trdTradeFeeTotal);
                }
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdDealTotalAdd())){
                trdDealTotalAddMapper.insertSelective(baseTradeProductDealDto.getTrdDealTotalAdd());
            }

            if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
            && StringUtil.equals(commonEntrustDto.getPs(), DictCodeListExt1.交易方向_债转股.getValue())) {
                List<TrdFinProductBarterTotal> trdFinProductBarterTotalList = exchangeTransMatchBond4Service.generateDealBarterTotal(dto,commonEntrustDto,baseTradeProductDealDto.getTrdFinProductDealTotal());
                if(!StringUtil.isEmptyList(trdFinProductBarterTotalList)) {
                    for(TrdFinProductBarterTotal trdFinProductBarterTotal:trdFinProductBarterTotalList) {
                        trdFinProductBarterTotalMapper.insertSelective(trdFinProductBarterTotal);
                    }
                }
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
                    if(StringUtil.isNotEmpty(TrdTradeFeeDetail.getFeeAmt()) && BigDecimal.ZERO.compareTo(TrdTradeFeeDetail.getFeeAmt())<0) {
                        trdTradeFeeDetailMapper.insertSelective(TrdTradeFeeDetail);
                    }
                }
            }

            if(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdTradeFeeDetail())){
                trdTradeFeeDetailMapper.insertSelective(baseTradeProductDealDto.getTrdTradeFeeDetail());
            }

            if(!StringUtil.isEmptyList(baseTradeProductDealDto.getPtlTrade())) {
                for(PtlTrade ptlTrade:baseTradeProductDealDto.getPtlTrade()) {
                    ptlTradeMapper.insertSelective(ptlTrade);
                }
            }

            if(StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                || StringUtil.equals(commonEntrustDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                SettleTransData settleTransData = new SettleTransData();
                BeanUtil.copyPropertiesIgnoreNull(settleTransData,dto);
                FinTradeProduct finTradeProduct = exchangeTransMatchRepo1Service.genFinTradeProduct(settleTransData, baseTradeProductDealDto.getTrdProductDeal());
                finTradeProductMapper.insertSelective(finTradeProduct);

                FinProductType finProductType = exchangeTransMatchRepo1Service.genFinProductType(dto,baseTradeProductDealDto.getTrdProductDeal());

                finProductTypeMapper.insertSelective(finProductType);
            }

        }

        logger.error("交易表数据:"+JsonUtils.object2Json(finProductTradeDto));
        //处理头寸和指令完成度
        baseTradeProductDealCommService.execute(finProductTradeDto);

        logger.error("处理业务交易表---结束,交易编号:"+tradeId);
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
        settleTransDataTotalMatch.setMatchType(etrEntrust.getTargetType());
        //数据字典.下行交易匹配方式（TRD_MATCH_TYPE）.手动匹配
        settleTransDataTotalMatch.setMatchClass(dealDto.getMatchType());
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
    private List<SettleTransTgMapping> genSettleTransTgMapping(ExchangeTransMatchCommonDto dealDto,String tradeId) {
        List<SettleTransTgMapping> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("seqId",dealDto.getSeqId());
        if(StringUtil.equals(dealDto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
            || StringUtil.equals(dealDto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            map.put("tradeIdRaw",dealDto.getTradeIdRaw());
        }
        List<SettleTransData> settleTransDataList = exchangeTransMatchCommonMapper.getSettleTransDataList(map);
        if(!StringUtil.isEmptyList(settleTransDataList)) {
            for(SettleTransData settleTransData:settleTransDataList) {
                SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
                settleTransTgMapping.setTradeId(settleTransData.getTradeId());
                settleTransTgMapping.setTradeIdTg(tradeId);
                settleTransTgMapping.setDealDate(dealDto.getDealDate());
                settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
                list.add(settleTransTgMapping);
            }
        }

        return list;
    }

    /**
     * 批量取消成交匹配
     * @param dtoList
     */
    public void revokeTransactionMacthBatch(List<SettleTransDataTotalMatch> dtoList) {
        logger.error("批量取消成交匹配数据---开始，批量成交数据:"+ JsonUtils.objectArray2Json(dtoList));
        if(!StringUtil.isEmptyList(dtoList)) {
            for (SettleTransDataTotalMatch dto : dtoList) {
                this.revokeTransactionMacth(dto);
            }
        }
        logger.error("批量取消成交匹配数据---结束");
    }

    /**
     * 取消成交匹配
     * @param dto
     */
    public void revokeTransactionMacth(SettleTransDataTotalMatch dto) {
        logger.error("取消成交匹配---开始，成交数据:"+ JsonUtils.object2Json(dto));
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("tgDataStatus",DictCodeListStl.投管交易数据状态_处理结束.getValue());
        paraMap.put("seqNo",dto.getSeqNo());
        paraMap.put("entrunstId",dto.getEntrunstTradeId());
        paraMap.put("matchClass",dto.getMatchClass());
        List<ExchangeTransMatchCommonRevokeDto> exchangeTransMatchCommonRevokeDtoList = exchangeTransMatchCommonMapper.getSettleTransTgMapping(paraMap);
        logger.error("查询取消匹配的成交数据与投管交易关系:"+ JsonUtils.objectArray2Json(exchangeTransMatchCommonRevokeDtoList));
        if(!StringUtil.isEmptyList(exchangeTransMatchCommonRevokeDtoList)) {
            List<String> tradeIdList = new ArrayList<>();
            List<String> totalTradeIdList = new ArrayList<>();
            for(ExchangeTransMatchCommonRevokeDto revokeDto:exchangeTransMatchCommonRevokeDtoList) {
                if (StringUtil.equals(revokeDto.getIsTotalDeal(), DictCodeList.是否_是.getValue())) {
                    if(!totalTradeIdList.contains(revokeDto.getTradeIdTg())) {
                        totalTradeIdList.add(revokeDto.getTradeIdTg());
                    }

                } else {
                    if(!tradeIdList.contains(revokeDto.getTradeIdTg())) {
                        tradeIdList.add(revokeDto.getTradeIdTg());
                    }
                }
            }

            PositionUpdateParam param = new PositionUpdateParam();
            if(!StringUtil.isEmptyList(totalTradeIdList)) {
                param.setTotalTradeIdList(totalTradeIdList);
            }
            else {
                param.setTradeIdList(tradeIdList);
            }
            param.setStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
            param.setBusiType(dto.getMatchType());
            param.setUpdateUser(GlobalConstant.SYSTEM_USER);
            param.setRevokeReason("取消成交匹配");

            // 处理头寸
            logger.error("取消成交匹配---处理头寸开始，参数数据:"+ JsonUtils.object2Json(param));
            if(isSingle) {
                simpleRestTemplate.postForObject(tamsSerever + "/invest/trade/api/execute", param, Map.class);
            }
            else {
                restTemplate.postForObject(tamsSerever + "/invest/trade/api/execute", param, Map.class);
            }
            logger.error("取消成交匹配---处理头寸结束");

            // 撤销交易
            logger.error("取消成交匹配---撤销交易开始，参数数据:"+ JsonUtils.object2Json(param));
            if(isSingle) {
                simpleRestTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
            }
            else {
                restTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
            }
            logger.error("取消成交匹配---撤销交易寸结束");

            for(ExchangeTransMatchCommonRevokeDto revokeDto:exchangeTransMatchCommonRevokeDtoList) {
               // 成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）：删除
                SettleTransTgMappingKey key = new SettleTransTgMappingKey();
                key.setTradeId(revokeDto.getTradeId());
                key.setTradeIdTg(revokeDto.getTradeIdTg());
                settleTransTgMappingMapper.deleteByPrimaryKey(key);
                //汇总成交数据匹配结果表（SETTLE_TRANS_DATA_TOTAL_MATCH）：数据删除
                settleTransDataTotalMatchMapper.deleteByPrimaryKey(dto);
            }

            //更新汇总成交数据状态表
            SettleTransDataTotalStatus settleTransDataTotalStatus = settleTransDataTotalStatusMapper.selectByPrimaryKey(dto.getSeqNo());
            if(StringUtil.isNotEmpty(settleTransDataTotalStatus)) {
                //取得剩余匹配份额
                BigDecimal remainNum = exchangeTransMatchCommonMapper.getRemainNum(dto.getSeqNo());
                settleTransDataTotalStatus.setMatchStatus(BigDecimal.ZERO.compareTo(remainNum) >=0
                        ?DictCodeListExt3.下行交易匹配状态_未匹配.getValue():DictCodeListExt3.下行交易匹配状态_部分匹配.getValue());
                settleTransDataTotalStatus.setUpdateTime(new Date());
                settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                settleTransDataTotalStatusMapper.updateByPrimaryKey(settleTransDataTotalStatus);
            }  else {
                settleTransDataTotalStatus = new SettleTransDataTotalStatus();
                settleTransDataTotalStatus.setSeqNo(dto.getSeqNo());
                //取得剩余匹配份额
                BigDecimal remainNum = exchangeTransMatchCommonMapper.getRemainNum(dto.getSeqNo());
                settleTransDataTotalStatus.setMatchStatus(BigDecimal.ZERO.compareTo(remainNum) >=0
                        ?DictCodeListExt3.下行交易匹配状态_未匹配.getValue():DictCodeListExt3.下行交易匹配状态_部分匹配.getValue());
                settleTransDataTotalStatus.setUpdateTime(new Date());
                settleTransDataTotalStatus.setUpdateUser(StringUtil.isNotEmpty(LoginUserUtil.getUserName())?LoginUserUtil.getUserName():"system");
                settleTransDataTotalStatusMapper.insert(settleTransDataTotalStatus);
            }

        }
        logger.error("取消成交匹配---结束");
    }

}
