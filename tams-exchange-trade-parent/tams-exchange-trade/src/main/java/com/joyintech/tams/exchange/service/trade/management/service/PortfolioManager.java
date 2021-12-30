package com.joyintech.tams.exchange.service.trade.management.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeList;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeListExt1;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionDetail;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.request.PortfolioInfoRequest;
import com.joyintech.tams.exchange.common.response.PortfolioCalculateResponse;
import com.joyintech.tams.exchange.common.response.PortfolioInfoResponse;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionDetailService;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
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
            calculateResponse.setInstructionLeftAmt(BigDecimal.ZERO.equals(instruction.getEntrustAmt()) ? BigDecimal.ZERO : instruction.getEntrustLeftAmt());
            calculateResponse.setInstructionLeftNum(instruction.getEntrustNum() == 0L ? 0L : instruction.getEntrustLeftAmt().longValue());
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
                entrustVolume = Integer.valueOf(ArithmeticUtil.divide(ArithmeticUtil.divide(entrustAmt, bean.getEntrustPrice(),12), BigDecimal.valueOf(minStep), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(minStep)).toString());
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

        return ResultBean.success(list);
    }
}
