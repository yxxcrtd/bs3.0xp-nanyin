package com.joyintech.tams.exchange.service.trade.management.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeList;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeListExt1;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionDetail;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.dto.PortfolioInstructionContextDto;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.request.PortfolioInfoRequest;
import com.joyintech.tams.exchange.common.response.PortfolioAllocationResponse;
import com.joyintech.tams.exchange.common.response.PortfolioCalculateResponse;
import com.joyintech.tams.exchange.common.response.PortfolioInfoResponse;
import com.joyintech.tams.exchange.common.utils.EntrustUtils;
import com.joyintech.tams.exchange.common.utils.InvestmentUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionDetailService;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
import com.joyintech.tams.exchange.util.EntrustPropUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 组合信息Service
 *
 * @author swwan
 * @date 2021/11/23 - 10:12
 */
@Service
public class PortfolioManager extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(PortfolioManager.class);

    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    @Autowired
    private InstructionService instructionService;

    @Autowired
    private InstructionDetailService instructionDetailService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private QuoteTickService quoteTickService;

    /**
     * 查询组合信息
     */
    public ResultBean queryPortfolioInfo(PortfolioInfoRequest request) {
        log.info("queryPortfolioInfo() called with parameters => 【request = {}】", request.toString());

        // 1. 获取组合列表
        String[] portfolioIdArray = StrUtil.split(request.getPortfolioId(), StrUtil.COMMA);

        // 返回的map： key 组合代码； value 组合现金持仓
        Map<String, PortfolioInfoResponse> result = new HashMap<>(8);

        // 查询入参
        Map<String, Object> paramMap = BeanUtil.beanToMap(request);
        paramMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        paramMap.put("acctId", request.getAcctNo());
        String calId = this.getCalId(request.getTradeMarket());
        if (StrUtil.isBlank(calId)) {
            return ResultBean.error(ErrorCode.REQ_PARAMETER_ERROR);
        }
        paramMap.put("calId", calId);

        // 2. 遍历查询组合信息
        try {
            for (int i = 0, length = portfolioIdArray.length; i < length; i++) {
                paramMap.put("portfolioId", portfolioIdArray[i]);

                PortfolioInfoResponse portfolioInfoResponse = ((PortfolioInfoResponse) queryPortfolioCash(paramMap).getData());
                PortfolioInfoResponse positionPortfolioInfoResponse = ((PortfolioInfoResponse) queryPortfolioPosition(paramMap).getData());

                portfolioInfoResponse.setProAvailablePositionT0(positionPortfolioInfoResponse.getProAvailablePositionT0());
                portfolioInfoResponse.setProAvailablePositionT1(positionPortfolioInfoResponse.getProAvailablePositionT1());

                result.put(portfolioIdArray[i], portfolioInfoResponse);
            }
        } catch (Exception e) {
            log.error("queryPortfolioInfo() called with exception => 【request = {}】", request);
            log.error("exception info : ", e);
            return ResultBean.error(ErrorCode.API_RETURN_ERROR);
        }
        return ResultBean.success(result);
    }

    /**
     * 查询组合现金信息
     *
     * @param portfolioIds 组合代码
     * @param acctNo       资金账户账号
     * @return 组合现金信息
     */
    public ResultBean queryPortfolioCash(String portfolioIds, String acctNo) {
        log.info("queryPortfolioCash() called with parameters => 【portfolioIds = {}】, 【acctNo = {}】", portfolioIds, acctNo);

        // 1. 获取组合列表
        String[] portfolioIdArray = StrUtil.split(portfolioIds, StrUtil.COMMA);

        // 返回的map： key 组合代码； value 组合现金信息
        Map<String, PortfolioInfoResponse> result = new HashMap<>(8);

        // 查询入参
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        paramMap.put("acctId", acctNo);

        // 2. 遍历查询组合现金
        try {
            for (int i = 0, length = portfolioIdArray.length; i < length; i++) {
                paramMap.put("portfolioId", portfolioIdArray[i]);
                PortfolioInfoResponse portfolioInfoResponse = ((PortfolioInfoResponse) queryPortfolioCash(paramMap).getData());
                result.put(portfolioIdArray[i], portfolioInfoResponse);
            }
        } catch (Exception e) {
            log.error("queryPortfolioCash() called with exception => 【portfolioIds = {}】, 【acctNo = {}】", portfolioIds, acctNo);
            log.error("exception info : ", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR);
        }

        return ResultBean.success(result);
    }

    /**
     * 查询组合持仓信息
     *
     * @param portfolioIds 组合代码
     * @param finprodId    产品代码
     * @param secAcctId    证券账户
     * @param tradeMarket  交易场所
     * @return 组合持仓信息
     */
    public ResultBean queryPortfolioPosition(String portfolioIds, String finprodId, String secAcctId, String tradeMarket) {
        log.info("queryPortfolioPosition() called with parameters => 【portfolioIds = {}】, 【finprodId = {}】, 【secAcctId = {}】, 【tradeMarket = {}】",
                portfolioIds, finprodId, secAcctId, tradeMarket);

        // 1. 获取组合列表
        String[] portfolioIdArray = StrUtil.split(portfolioIds, StrUtil.COMMA);

        // 返回的map： key 组合代码； value 组合持仓
        Map<String, PortfolioInfoResponse> result = new HashMap<>();

        // 查询入参
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("finprodId", finprodId);
        paramMap.put("secAcctId", secAcctId);
        String calId = this.getCalId(tradeMarket);
        if (StrUtil.isBlank(calId)) {
            return ResultBean.error(ErrorCode.REQ_PARAMETER_ERROR);
        }
        paramMap.put("calId", calId);

        // 2. 遍历查询组合持仓
        try {
            for (int i = 0; i < portfolioIdArray.length; i++) {
                paramMap.put("portfolioId", portfolioIdArray[i]);
                PortfolioInfoResponse portfolioInfoResponse = ((PortfolioInfoResponse) queryPortfolioPosition(paramMap).getData());
                result.put(portfolioIdArray[i], portfolioInfoResponse);
            }
        } catch (Exception e) {
            log.error("queryPortfolioPosition() called with exception => 【portfolioIds = {}】, 【finprodId = {}】, 【secAcctId = {}】, 【tradeMarket = {}】",
                    portfolioIds, finprodId, secAcctId, tradeMarket);
            log.error("exception info : ", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR);
        }

        return ResultBean.success(result);
    }

    /**
     * 微服务查询组合现金信息
     *
     * @param paramMap 入参
     * @return 组合信息对象
     */
    private ResultBean queryPortfolioCash(Map<String, Object> paramMap) {
        // 查询URL
        String url = ServiceApplication.INSTRUCTION_SERVICE + TradeServiceURL.getPortfolioCash;

        // 查询结果
        Map<String, Object> resultMap = postForMap(url, paramMap);
        if (Objects.isNull(resultMap) || resultMap.size() == 0) {
            log.warn("调用投管接口失败: url = [{}], param = [{}]", url, JSONUtil.toJsonStr(paramMap));
        }

        // 封装结果
        PortfolioInfoResponse portfolioInfoResponse = new PortfolioInfoResponse();
        portfolioInfoResponse.setPortfolioId((String) paramMap.get("portfolioId"));
        portfolioInfoResponse.setProAvailableCashT0(getValueFromMap(resultMap, "cashT"));
        portfolioInfoResponse.setProAvailableCashT1(getValueFromMap(resultMap, "cashT1"));
        portfolioInfoResponse.setSecManageAvailableCashT0(getValueFromMap(resultMap, "cashT3"));
        portfolioInfoResponse.setSecManageAvailableCashT1(getValueFromMap(resultMap, "cashTT1"));

        return ResultBean.success(portfolioInfoResponse);
    }

    /**
     * 微服务查询组合持仓信息
     *
     * @param paramMap 入参
     * @return 组合信息对象
     */
    public ResultBean queryPortfolioPosition(Map<String, Object> paramMap) {
        log.info("queryPortfolioPosition() called with parameters => 【paramMap = {}】", paramMap);

        // 查询URL
        String url = ServiceApplication.INSTRUCTION_SERVICE + TradeServiceURL.getPortfolioPosition;

        // 查询结果
        Map<String, Object> resultMap = postForMap(url, paramMap);
        if (Objects.isNull(resultMap) || resultMap.size() == 0) {
            log.warn("调用投管接口失败: url = [{}], param = [{}]", url, JSONUtil.toJsonStr(paramMap));
        }

        // 封装结果
        PortfolioInfoResponse portfolioInfoResponse = new PortfolioInfoResponse();
        portfolioInfoResponse.setPortfolioId((String) paramMap.get("portfolioId"));
        portfolioInfoResponse.setProAvailablePositionT0(getValueFromMap(resultMap, "position"));
        portfolioInfoResponse.setProAvailablePositionT1(getValueFromMap(resultMap, "positionT1"));

        return ResultBean.success(portfolioInfoResponse);
    }

    private BigDecimal getValueFromMap(Map<String, Object> resultMap, String code) {
        if (resultMap == null) {
            return BigDecimal.ZERO;
        }
        return Objects.isNull(resultMap.get(code)) ? BigDecimal.ZERO : new BigDecimal((String) resultMap.get(code));
    }

    /**
     * 调用微服务
     *
     * @param url      接口url
     * @param paramMap 入参
     * @return 查询结果
     */
    private Map postForMap(String url, Map<String, Object> paramMap) {

        if (isSingle) {
            return simpleRestTemplate.postForObject(url, paramMap, Map.class);
        }
        return restTemplate.postForObject(url, paramMap, Map.class);
    }

    /**
     * 根据交易场所获取交易日历
     *
     * @param tradeMarket 交易场所
     * @return 交易日历
     */
    private String getCalId(String tradeMarket) {
        if (DictCodeListExt1.交易场所_上海证券交易所.getValue().equals(tradeMarket)) {
            return "04";
        } else if (DictCodeListExt1.交易场所_深圳证券交易所.getValue().equals(tradeMarket)) {
            return "05";
        }
        return StrUtil.EMPTY;
    }

    /**
     * 组合计算
     */
    public ResultBean queryPortfolioCalculate(OrderPlaceBean bean) {
        log.info("queryPortfolioCalculate() called with parameters => 【bean = {}】", bean);
        // 获取指令
        String instructionId = bean.getInstructionId();
        Instruction instruction = instructionService.findById(instructionId);
        if (Objects.isNull(instruction)) {
            log.warn("未找到指令信息: InstructionId [{}]", instructionId);
            return ResultBean.error(ErrorCode.INSTRUCTIONS_NOT_EXIST);
        }


        // 获取多组合
        List<InstructionDetail> instructionDetails = instructionDetailService.queryInstructionDetailsByInstructionId(instructionId);

        if (Objects.isNull(instructionDetails) || instructionDetails.size() == 0) {
            // 单组合时处理
            InstructionDetail instructionDetail = new InstructionDetail();
            instructionDetail.setEntrustId(instruction.getEntrustId());
            instructionDetail.setPortfolioId(instruction.getPortfolioId());
            instructionDetail.setPortfolioName(instruction.getPortfolioName());
            instructionDetail.setDisRate("1");
            instructionDetail.setEntrustAmt(instruction.getEntrustAmt().toString());
            instructionDetail.setEntrustNum(String.valueOf(instruction.getEntrustNum()));
            instructionDetail.setTargetType(instruction.getEntrustNum() == 0 ? "02" : "01");
            instructionDetails.add(instructionDetail);
        }

        // - 查询组合信息
        PortfolioInfoRequest portfolioInfoRequest = new PortfolioInfoRequest();
        List<String> portfolios = instructionDetails.stream().map(InstructionDetail::getPortfolioId).collect(Collectors.toList());
        portfolioInfoRequest.setPortfolioId(ArrayUtil.join(portfolios.toArray(), StrUtil.COMMA));
        portfolioInfoRequest.setFinprodId(instruction.getFinprodId());
        portfolioInfoRequest.setSecAcctId(instruction.getSecAcctId());
        portfolioInfoRequest.setAcctNo(instruction.getAcctNo());
        portfolioInfoRequest.setTradeMarket(instruction.getTradeMarket());
        portfolioInfoRequest.setVdate(DateUtil.platDateFromCache());

        ResultBean portfolioInfoResult = this.queryPortfolioInfo(portfolioInfoRequest);
        Map<String, PortfolioInfoResponse> portfolioInfoResponseMap = (Map<String, PortfolioInfoResponse>) portfolioInfoResult.getData();


        // 计算结果列表
        List<PortfolioCalculateResponse> list = new ArrayList<>(8);
        // 计算结果
        PortfolioCalculateResponse calculateResponse = null;

        Instrument instrument = instrumentService.queryByInnerCode(bean.getStockCode());
        MinTrdUnitLimit minTrdUnitLimit = instrumentService.getMinTrdUnitLimit(instruction.getTradePlat(), instruction.getTradeMarket(), instrument, instruction.getPs());
        // 最低交易数量：大于等于
        Integer minNum;
        // 最小交易单元：整数倍
        Integer minStep;
        // 最低交易金额：大于等于
        BigDecimal minAmt;
        if (minTrdUnitLimit == null) {
            minNum = 100;
            minStep = 100;
            minAmt = BigDecimal.ZERO;
        } else {
            if (EntrustProp.isMarketPrice(bean.getEntrustProp())) {
                minNum = minTrdUnitLimit.getMinNumMarket();
            } else {
                minNum = minTrdUnitLimit.getMinNum();
            }
            minStep = minTrdUnitLimit.getMinStep();
            minAmt = minTrdUnitLimit.getMinAmt();
        }
        //市价委托填充反向涨跌停价
        BigDecimal price = bean.getEntrustPrice();
        if (EntrustProp.isMarketPrice(bean.getEntrustProp())) {
            QuoteProto.TickBean tick = quoteTickService.getTickFromCache(bean.getStockCode());
            if (Objects.nonNull(tick)) {
                if (bean.getEntrustBs().equals(EntrustBs.SELL.value)) {
                    price = TradeUtils.getBigDecimal(tick.getLowerLimitPrice(), 4);
                } else {
                    price = TradeUtils.getBigDecimal(tick.getUpperLimitPrice(), 4);
                }
            }
        }

        String portfolioId;
        Integer entrustVolume = 0;
        BigDecimal entrustAmt = BigDecimal.ZERO;

        boolean isVolume = instruction.getEntrustNum() > 0L;

        for (InstructionDetail detail : instructionDetails) {
            calculateResponse = new PortfolioCalculateResponse();

            portfolioId = detail.getPortfolioId();
            PortfolioInfoResponse infoResponse = portfolioInfoResponseMap.get(portfolioId);
            calculateResponse.setPortfolioInfo(infoResponse);

            calculateResponse.setPortfolioId(instruction.getPortfolioId());
            calculateResponse.setPortfolioName(instruction.getPortfolioName());
            calculateResponse.setFinprodMarketId(instruction.getFinprodMarketId());
            calculateResponse.setEntrustPrice(bean.getEntrustPrice());
            calculateResponse.setFinprodName(instruction.getFinprodName());
            calculateResponse.setDisRate(new BigDecimal(detail.getDisRate()));
            calculateResponse.setEntrustProp(bean.getEntrustProp());
            calculateResponse.setInstructionLeftAmt(isVolume ? BigDecimal.ZERO : ArithmeticUtil.subtract(ArithmeticUtil.subtract(instruction.getEntrustAmt(), instruction.getEntrustLeftAmt()), instruction.getFrozenQuantity()));
            calculateResponse.setInstructionLeftNum(isVolume ? instruction.getEntrustNum() - instruction.getEntrustLeftAmt().longValue() - instruction.getFrozenQuantity().longValue() : 0L);
            calculateResponse.setInstructionTotalAmt(instruction.getEntrustAmt());
            calculateResponse.setInstructionTotalNum(instruction.getEntrustNum());
            calculateResponse.setFullPrice(Objects.nonNull(instruction.getFullPrice()) ? instruction.getFullPrice().toString() : BigDecimal.ZERO.toString());
            calculateResponse.setPriceMode(instruction.getPriceMode());
            calculateResponse.setAcctNo(instruction.getAcctName());

            // - 计算组合委托额度
            // 数量
            BigDecimal volume = ArithmeticUtil.multiply(BigDecimal.valueOf(bean.getEntrustVolume()), new BigDecimal(detail.getDisRate()));
            if (volume.compareTo(BigDecimal.valueOf(minNum)) == 0) {
                entrustVolume = minNum;
                entrustAmt = ArithmeticUtil.multiply(BigDecimal.valueOf(entrustVolume), bean.getEntrustPrice());
            } else if (volume.compareTo(BigDecimal.valueOf(minNum)) > 0) {
                entrustVolume = Integer.valueOf(ArithmeticUtil.divide(volume, BigDecimal.valueOf(minStep), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(minStep)).toString());
                entrustAmt = ArithmeticUtil.multiply(BigDecimal.valueOf(entrustVolume), bean.getEntrustPrice());
            }
            if ("02".equals(detail.getTargetType())) {
                entrustAmt = ArithmeticUtil.multiply(BigDecimal.valueOf(entrustVolume), price);
                entrustVolume = Integer.valueOf(ArithmeticUtil.divide(ArithmeticUtil.divide(entrustAmt, bean.getEntrustPrice(), 12), BigDecimal.valueOf(minStep), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(minStep)).toString());
            }
            calculateResponse.setEntrustAmt(entrustAmt);
            calculateResponse.setEntrustNum(Long.valueOf(entrustVolume));

            list.add(calculateResponse);
        }

        // 尾差处理
        PortfolioCalculateResponse maxPortfolioCalculateResponse = list.stream().max(Comparator.comparing(PortfolioCalculateResponse::getDisRate)).get();
        if ("01".equals(instructionDetails.get(0).getDisRate())) {
            long sum = list.stream().filter(response -> !StrUtil.equals(response.getPortfolioId(), maxPortfolioCalculateResponse.getPortfolioId()))
                    .mapToLong(PortfolioCalculateResponse::getEntrustNum)
                    .sum();
            maxPortfolioCalculateResponse.setEntrustNum(bean.getEntrustVolume() - sum);
        }

        if (isVolume && DictCodeListExt3.交易方向_债转股.getValue().equals(instruction.getPs())) {
            long bondPaperValue = 100L;
            for (PortfolioCalculateResponse response : list) {
                response.setConNum(response.getInstructionLeftNum() * bondPaperValue / response.getEntrustPrice().longValue());
            }
        }

        return ResultBean.success(list);
    }

    /**
     * 组合计算——兼容公平模式
     */
    public ResultBean queryPortfolioCalculateWithFair(OrderPlaceBean bean) {
        log.info("queryPortfolioCalculateWithFair() called with parameters => 【bean = {}】", bean);

        // 方法返回
        PortfolioAllocationResponse result = new PortfolioAllocationResponse();

        // 获取指令
        String instructionId = bean.getInstructionId();
        Instruction instruction = instructionService.findById(instructionId);
        if (Objects.isNull(instruction)) {
            log.warn("未找到指令信息: InstructionId [{}]", instructionId);
            result.setInfoLevel("1");
            result.setInfoDetails(ErrorCode.getStr(ErrorCode.INSTRUCTIONS_NOT_EXIST));
            return ResultBean.success(result);
        }

        // 过滤公平相关指令
        // 1. 同交易员 2. 同证券编码 3. 同交易方向
        // 4. 指令状态——已接收 5. 交易平台——沪深竞价撮合平台 6. 指令进度——未100%
        List<Instruction> simpleFiltingList = instructionService.queryInstructionByUserIdAndStockCode(instruction.getTradeId(), instruction.getFinprodMarketId())
                .stream().filter(item ->
                        instruction.getPs().equals(item.getPs())
                                && InstructionStatus.ALREADY_RECEIVE.value.equals(item.getEntrustStatus())
                                && InvestmentUtils.isNormalTradeFromInstruction(item.getTradePlat())
                                && !InvestmentUtils.isInstructionProcessComplete(item))
                .collect(Collectors.toList());
        log.debug("初步过滤出公平相关指令：{}", JSONUtil.toJsonStr(simpleFiltingList));
        result.setList(simpleFiltingList);

        // 判断进入公平交易
        if (!EntrustUtils.isFairOrder(bean, instruction)) {
            log.warn("非公平交易模式，无法进行公平交易！param = {}", JSONUtil.toJsonStr(bean));
            result.setInfoLevel("1");
            result.setInfoDetails(ErrorCode.getStr(ErrorCode.NOT_FAIR_INSTRUCTION_ERROR));
            return ResultBean.success(result);
        }

        // 行情数据
        QuoteProto.TickBean tick = quoteTickService.getTickFromCache(bean.getStockCode());
        if (Objects.isNull(tick)) {
            log.warn("未找到行情信息: stockId [{}]", instruction.getFinprodMarketId());
            result.setInfoLevel("1");
            result.setInfoDetails(ErrorCode.getStr(ErrorCode.QUOTE_NOT_EXIT));
            return ResultBean.success(result);
        }

        // 系统参数：未接收指令的公平处理模式 01：是；02：否
        /*
            01 若存在部分指令未接收则提示“存在需要执行公平交易但未接收的指令，请先接收指令！”，
                需要等所有指令均接收之后，方可下达委托;
            02 忽略未接收的部分指令
         */
        if (EntrustUtils.isfairModeWithUnacceptInstructionForbid()) {
            List<Instruction> waitReceiveList =
                    instructionService.queryInstructionByUserIdAndStockCode(instruction.getTradeId(), instruction.getFinprodMarketId())
                            .stream()
                            .filter(item ->
                                    instruction.getPs().equals(item.getPs())
                                            && InstructionStatus.WAIT_RECEIVE.value.equals(item.getEntrustStatus())
                                            && InvestmentUtils.isNormalTradeFromInstruction(item.getTradePlat())
                                            && !InvestmentUtils.isInstructionProcessComplete(item))
                            .collect(Collectors.toList());

            if (waitReceiveList.size() > 0) {
                log.warn("未接收且影响公平交易的相关指令: {}", JsonUtils.getJsonFromObject(waitReceiveList));
                result.setInfoLevel("1");
                result.setInfoDetails(ErrorCode.getStr(ErrorCode.FAIR_INSTRUCTIONS_UNACCEPT_ERROR));
                return ResultBean.success(result);
            }
        }

        // 获取公平交易相关指令
        List<PortfolioInstructionContextDto> fairList = getFairList(bean, instruction, tick, result);
        log.debug(JSONUtil.toJsonStr(fairList));

        // 获取多组合的指令
        List<Instruction> instructionList = getFairInstructionList(fairList, result);
        result.setList(instructionList);

        return ResultBean.success(result);
    }

    /**
     * 获取公平交易相关指令
     */
    private List<PortfolioInstructionContextDto> getFairList(OrderPlaceBean bean, Instruction instruction, QuoteProto.TickBean tick, PortfolioAllocationResponse result) {

        // 涨停价
        BigDecimal upperLimitPrice = TradeUtils.getBigDecimal(tick.getUpperLimitPrice(), 4);
        // 跌停价
        BigDecimal lowerLimitPrice = TradeUtils.getBigDecimal(tick.getLowerLimitPrice(), 4);

        // 特殊处理 质押式回购交易，将委托价格设置为100
        if (EntrustUtils.isPledgeRepurchase(instruction)) {
            bean.setEntrustPrice(BigDecimal.valueOf(100L));
        }

        // 构造返回列表
        List<PortfolioInstructionContextDto> resultList = new ArrayList<>();
        // 调整后的价格
        BigDecimal adjustedPrice = bean.getEntrustPrice();
        result.setPrice(adjustedPrice);

        // 获取买卖方向
        String entrustBs = EntrustBsProp.getEntrustBs(instruction.getPs());
        // 获取委托属性
        String entrustProp = EntrustPropUtils.getEntrustProp(instruction, bean.getEntrustProp());
        // 获取委托价格
        BigDecimal entrustPrice = bean.getEntrustPrice();

        // 过滤公平相关指令
        // 1. 同交易员 2. 同证券编码 3. 同交易方向
        // 4. 指令状态——已接收 5. 交易平台——沪深竞价撮合平台 6. 指令进度——未100%
        List<Instruction> questList = instructionService.queryInstructionByUserIdAndStockCode(instruction.getTradeId(), instruction.getFinprodMarketId())
                .stream().filter(item ->
                        instruction.getPs().equals(item.getPs())
                                && InstructionStatus.ALREADY_RECEIVE.value.equals(item.getEntrustStatus())
                                && InvestmentUtils.isNormalTradeFromInstruction(item.getTradePlat())
                                && !InvestmentUtils.isInstructionProcessComplete(item))
                .collect(Collectors.toList());
        log.debug("初步过滤出公平相关指令：{}", JSONUtil.toJsonStr(questList));

        // questList 是从内存拿出的数据，不能直接使用
        List<Instruction> tempList = new ArrayList<>(questList);

        // 委托价格为0的指令（市价指令），将涨跌停价填充到委托价格
        for (Instruction ins : tempList) {
            if (EntrustProp.isMarketPrice(entrustProp) && BigDecimal.ZERO.compareTo(ins.getEntrustPrice()) == 0) {
                if (EntrustBs.BUY.value.equals(entrustBs)) {
                    ins.setEntrustPrice(TradeUtils.getBigDecimal(tick.getUpperLimitPrice(), 4));
                } else {
                    ins.setEntrustPrice(TradeUtils.getBigDecimal(tick.getLowerLimitPrice(), 4));
                }
                log.debug("将涨跌停价填充到委托价格为0的指令（市价指令）：{}", JSONUtil.toJsonStr(tempList));
            }
        }

        // 系统参数：委托价格不优于指令价格时的处理模式
        if (EntrustUtils.isfairModeEntrustPriceBestThanInstructionForbid()) {
            // 01 若输入委托价格不是优于所有指令价格时，
            // 提示：“委托价格必须优于所有指令价格”，
            // 并自动将委托价格调整为能够满足所有指令价格的最低（高）价格

            // 获取指令列表中最低（高）价格
            if (EntrustBs.isBuy(entrustBs)) {
                BigDecimal min = tempList.stream()
                        .map(Instruction::getEntrustPrice)
                        .min(Comparator.naturalOrder())
                        .orElse(upperLimitPrice);
                if (min.compareTo(upperLimitPrice) > 0) {
                    adjustedPrice = upperLimitPrice;
                } else {
                    adjustedPrice = min;
                }

                // 不能比委托价格更高
                if (entrustPrice.compareTo(BigDecimal.ZERO) != 0 && adjustedPrice.compareTo(entrustPrice) > 0) {
                    adjustedPrice = entrustPrice;
                }

            } else {
                BigDecimal max = tempList.stream()
                        .map(Instruction::getEntrustPrice)
                        .max(Comparator.naturalOrder())
                        .orElse(TradeUtils.getBigDecimal(tick.getLowerLimitPrice(), 4));
                if (max.compareTo(lowerLimitPrice) < 0) {
                    adjustedPrice = lowerLimitPrice;
                } else {
                    adjustedPrice = max;
                }

                // 不能比委托价格更低
                if (entrustPrice.compareTo(BigDecimal.ZERO) != 0 && adjustedPrice.compareTo(entrustPrice) < 0) {
                    adjustedPrice = entrustPrice;
                }
            }

            // 提示：“委托价格必须优于所有指令价格”
            if (adjustedPrice.compareTo(bean.getEntrustPrice()) != 0) {
                result.setInfoLevel("2");
                result.setInfoDetails("委托价格必须优于所有指令价格");
                result.setPrice(adjustedPrice);
            }
        } else {
            // 02时，剔除该指令并继续时
            if (EntrustBs.isBuy(entrustBs)) {
                // 买入，过滤价格小于指令价格的指令
                tempList = tempList.stream().filter(item -> entrustPrice.compareTo(item.getEntrustPrice()) >= 0).collect(Collectors.toList());
            } else {
                // 卖出，过滤价格大于指令价格的指令
                tempList = tempList.stream().filter(item -> entrustPrice.compareTo(item.getEntrustPrice()) <= 0).collect(Collectors.toList());
            }
        }

        /*
            a)计算每条指令剩余数量：
                数量指令，指令剩余数量即为实际指令剩余数量；
                金额指令，根据指令剩余金额÷委托价格计算得到，需要取整；
                    若委托价格模式为非限价时，股票、基金交易委托价格取涨跌停价格，
                        买入取涨停价，
                        卖出取跌停价；
            b)计算总的指令剩余数：
                每条指令剩余数量之和；
            c)计算每条指令比例：
                每条指令剩余数量÷总的指令剩余数量；
            d)计算每条指令委托数量：
                交易员输入的委托数量×每条指令比例，
                以四舍五入的方式按最小交易单位取整，如股票计算得到的数量为555股，则实际委托数量为600股；若计算得到的委托数量大于指令剩余数量，则取委托数量取指令剩余数量；
         */
        PortfolioInstructionContextDto dto = null;
        for (Instruction ins : tempList) {
            dto = new PortfolioInstructionContextDto();
            dto.setInstruction(ins);
            dto.setEntrustPrice(adjustedPrice);

            if (EntrustUtils.isVolume(ins)) {
                dto.setLeftVolume(ins.getEntrustNum() - ins.getEntrustLeftAmt().longValue() - ins.getFrozenQuantity().longValue());
            } else {
                dto.setLeftVolume(TradeUtils.divide(TradeUtils.subtract(ins.getEntrustAmt(), ins.getEntrustLeftAmt(), ins.getFrozenQuantity(), 2), adjustedPrice, 2).longValue());
            }
            resultList.add(dto);
        }
        // 总剩余指令数量
        Long totalLeftVolume = getTotalLeftVolume(resultList);
        result.setTotalLeftVolume(totalLeftVolume);
        // 首次调用，特殊处理
        if (bean.getEntrustVolume() == 0L) {
            bean.setEntrustVolume(totalLeftVolume);
        }

        // 计算指令的委托数量
        for (PortfolioInstructionContextDto context : resultList) {
            Long realVolume = 0L;
            if (EntrustUtils.isBond(context.getInstruction())) {
                realVolume = getEntrustVolume(totalLeftVolume, bean.getEntrustVolume(), context.getLeftVolume(), 1);
            } else if (EntrustUtils.isPledgeRepurchase(context.getInstruction())) {
                if (Market.SZSE.investValue.equals(context.getInstruction().getTradeMarket())) {
                    realVolume = getEntrustVolume(totalLeftVolume, bean.getEntrustVolume(), context.getLeftVolume(), 1);
                } else {
                    realVolume = getEntrustVolume(totalLeftVolume, bean.getEntrustVolume(), context.getLeftVolume(), 3);
                }
            } else if (EntrustUtils.isFutures(context.getInstruction())) {
                realVolume = getEntrustVolume(totalLeftVolume, bean.getEntrustVolume(), context.getLeftVolume(), 0);
            } else {
                realVolume = getEntrustVolume(totalLeftVolume, bean.getEntrustVolume(), context.getLeftVolume(), 2);
            }
            context.setEntrustVolume(Math.min(context.getLeftVolume(), realVolume));
            // 分配到指令的委托数量，借用转股数量字段
            context.getInstruction().setConNum(BigDecimal.valueOf(Math.min(context.getLeftVolume(), realVolume)));
        }

        // 计算总委托数量和总委托金额
        long sumEntrustVolume = getTotalEntrustVolume(resultList);
        result.setTotalEntrustVolume(sumEntrustVolume);
        result.setTotalEntrustAmt(result.getPrice().multiply(BigDecimal.valueOf(sumEntrustVolume)));
        // 首次调用，特殊处理
        if (bean.getEntrustVolume() == 0L) {
            result.setTotalEntrustVolume(result.getTotalLeftVolume());
            result.setTotalEntrustAmt(result.getPrice().multiply(BigDecimal.valueOf(result.getTotalLeftVolume())));
        }
        for (PortfolioInstructionContextDto context : resultList) {
            // 分配到指令的委托数量比例，借用转股价格字段
            context.getInstruction().setConPrice(ArithmeticUtil.divide(BigDecimal.valueOf(context.getEntrustVolume()), BigDecimal.valueOf(sumEntrustVolume), 4));
        }
        log.debug("公平委托相关指令: {}", JsonUtils.getJsonFromObject(resultList));

        return resultList;
    }

    /**
     * 计算总委托数量——试算数量
     */
    private long getTotalEntrustVolume(List<PortfolioInstructionContextDto> resultList) {
        return resultList.stream().mapToLong(PortfolioInstructionContextDto::getEntrustVolume).sum();
    }

    /**
     * 计算总剩余委托数量
     */
    private long getTotalLeftVolume(List<PortfolioInstructionContextDto> list) {
        return list.stream().mapToLong(PortfolioInstructionContextDto::getLeftVolume).sum();
    }

    private Long getEntrustVolume(Long totalLeftVolume, Long orderVolume, Long leftVolume, int unit) {
        return TradeUtils.formatLong(orderVolume.doubleValue() * leftVolume.doubleValue() / totalLeftVolume.doubleValue(), -unit, BigDecimal.ROUND_UP);
    }

    private Long getEntrustVolume(BigDecimal disRate, BigDecimal volume, int unit) {
        return TradeUtils.formatLong(disRate.doubleValue() * volume.doubleValue(), -unit, BigDecimal.ROUND_UP);
    }


    private List<Instruction> getFairInstructionList(List<PortfolioInstructionContextDto> fairList, PortfolioAllocationResponse result) {

        List<Instruction> list = new ArrayList<>();

        // 获取多组合
        for (PortfolioInstructionContextDto context : fairList) {
            Instruction ins = context.getInstruction();
            List<InstructionDetail> instructionDetails = instructionDetailService.queryInstructionDetailsByInstructionId(ins.getEntrustId());

            if (Objects.isNull(instructionDetails) || instructionDetails.size() == 0 || instructionDetails.size() == 1) {
                // 处理单组合
                handleFields(result, ins);
                list.add(ins);
                continue;
            }

            // 单指令多组合扩展列表
            List<Instruction> singleList = new ArrayList<>();
            for (InstructionDetail detail : instructionDetails) {
                // 生成多条指令，替换组合信息，将分配比例存入转股价格（conPrice字段）
                Instruction copyInstruction = new Instruction();
                BeanUtil.copyProperties(ins, copyInstruction);
                copyInstruction.setPortfolioId(detail.getPortfolioId());
                copyInstruction.setPortfolioName(detail.getPortfolioName());
                copyInstruction.setConPrice(ins.getConPrice().multiply(new BigDecimal(detail.getDisRate())));

                Long realVolume = 0L;
                if (EntrustUtils.isBond(copyInstruction)) {
                    realVolume = getEntrustVolume(copyInstruction.getConPrice(), copyInstruction.getConNum(), 1);
                } else if (EntrustUtils.isPledgeRepurchase(copyInstruction)) {
                    if (Market.SZSE.investValue.equals(copyInstruction.getTradeMarket())) {
                        realVolume = getEntrustVolume(copyInstruction.getConPrice(), copyInstruction.getConNum(), 1);
                    } else {
                        realVolume = getEntrustVolume(copyInstruction.getConPrice(), copyInstruction.getConNum(), 3);
                    }
                } else if (EntrustUtils.isFutures(copyInstruction)) {
                    realVolume = getEntrustVolume(copyInstruction.getConPrice(), copyInstruction.getConNum(), 0);
                } else {
                    realVolume = getEntrustVolume(copyInstruction.getConPrice(), copyInstruction.getConNum(), 2);
                }
                copyInstruction.setConNum(BigDecimal.valueOf(realVolume));

                singleList.add(copyInstruction);
            }

            // 尾插处理
            Instruction maxInstruction = singleList.stream().max(Comparator.comparing(Instruction::getConPrice)).get();
            long totalExceptMaxVolume = singleList.stream().filter(item -> !item.getEntrustId().equals(maxInstruction.getEntrustId()))
                    .mapToLong(item -> item.getConNum().longValue()).sum();
            maxInstruction.setConNum(BigDecimal.valueOf(ins.getEntrustNum() - totalExceptMaxVolume));
            // 重新计算占比；赋值委托数量，委托金额
            for (Instruction item : singleList) {
                item.setConPrice(ArithmeticUtil.divide(item.getConNum(), BigDecimal.valueOf(result.getTotalEntrustVolume()), 4));
                handleFields(result, item);
            }

            list.addAll(singleList);
        }
        return list;
    }

    /**
     * 配置参数
     */
    private void handleFields(PortfolioAllocationResponse result, Instruction ins) {
        // 借用termDays存放委托数量
        ins.setTermDays(ins.getConNum());
        // 借用minAmt存放委托金额
        ins.setMinAmt(ArithmeticUtil.multiply(ins.getConNum(), result.getPrice()));
        if (EntrustUtils.isVolume(ins)) {
            // 借用mIntAmt存放剩余总数量
            BigDecimal leftNum = BigDecimal.valueOf(ins.getEntrustNum() - ins.getEntrustLeftAmt().longValue() - ins.getFrozenQuantity().longValue());
            ins.setmIntAmt(leftNum.compareTo(BigDecimal.ZERO) > 0 ? leftNum : BigDecimal.ZERO);
        } else {
            // 借用mTradeAmt存放剩余总金额
            BigDecimal leftAmt = TradeUtils.subtract(ins.getEntrustAmt(), ins.getEntrustLeftAmt(), ins.getFrozenQuantity(), 2);
            ins.setmTradeAmt(leftAmt.compareTo(BigDecimal.ZERO) > 0 ? leftAmt : BigDecimal.ZERO);
        }
    }
}
