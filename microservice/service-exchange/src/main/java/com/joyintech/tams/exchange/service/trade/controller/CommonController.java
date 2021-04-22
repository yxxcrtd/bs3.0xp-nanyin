package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.SysParamBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/joyin")
public class CommonController {
    @Autowired
    private CommonManagement commonManagement;

    /**
     * 市场合约代码查询
     *
     * @param userId        用户Id
     * @param market        市场
     * @param stockCodeList 合约代码list
     * @return ResultBean
     */
    @RequestMapping(value = "/common/instrument", method = RequestMethod.POST)
    public ResultBean queryInstrument(@RequestParam(value = "user_id") String userId,
                                      @RequestParam(value = "market", required = false) String market,
                                      @RequestBody(required = false) List<String> stockCodeList) {
        return commonManagement.queryInstrument(market, stockCodeList);
    }

    /**
     * 期货市场合约代码查询
     *
     * @param stockCode 合约
     * @return ResultBean
     */
    @RequestMapping(value = "/common/futuresInstrument", method = RequestMethod.POST)
    public ResultBean queryInstrument(@RequestParam(value = "stock_code", required = false, defaultValue = "") String stockCode) {
        return commonManagement.queryFuturesInstrument(stockCode);
    }

    @RequestMapping(value = "/common/fairtrade", method = RequestMethod.GET)
    public ResultBean queryFairTradeParam(@RequestParam(value = "user_id") String userId) {
        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("systemCode", "TRD");
        pageParam.put("paraCode", "TRD_CHECK_FAIR");
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.querySysPara(pageParam));
    }

    /**
     * 获取系统平台日
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/common/sysdate", method = RequestMethod.GET)
    public ResultBean queryInvestSystemDate(@RequestParam(value = "user_id") String userId) {
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.queryInvestSystemDate());
    }

    /**
     * 获取系统平台日
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/common/platdate", method = RequestMethod.POST)
    public ResultBean queryPlatDate(@RequestParam(value = "user_id") String userId) {
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.queryPlatDate());
    }

    /**
     * 获取系统参数
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/common/sysparam", method = RequestMethod.POST)
    public ResultBean querySysParam(@RequestParam(value = "user_id") String userId,
                                    @RequestParam(value = "para_code") String paraCode,
                                    @RequestParam(value = "system_code") String systemCode) {
        SysParamBean bean = new SysParamBean(paraCode, systemCode);
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.querySysParam(bean));
    }

    @RequestMapping(value = "/common/service/node", method = RequestMethod.GET)
    public ResultBean queryServiceNode(@RequestParam(value = "node_name") String nodeName) {
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.queryServiceNode(nodeName));
    }

    @RequestMapping(value = "/common/eureka/services", method = RequestMethod.GET)
    public ResultBean getEurekaServices(@RequestParam(value = "service_name") String serviceName) {
        return new ResultBean(ErrorCode.SUCCESS, commonManagement.getEurekaServices(serviceName));
    }

    /**
     * 当前的master节点查询
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/common/master", method = RequestMethod.GET)
    public ResultBean queryMasterAddress() {
        return commonManagement.queryMaster();
    }

    /**
     * 参数控制，是否启用redis以及是否同步写入db
     * 支持redis和写入db之前的搭配操作
     *
     * @param userId   用户id
     * @param useRedis 是否启用redis  false：不启用redis   true：启用redis
     * @param asyncDb  是否异步写入数据库 false: 同步写入数据库  true： 异步写入数据库
     * @return ResultBean
     */
    @RequestMapping(value = "/common/redis/use", method = RequestMethod.POST)
    public ResultBean changeDbType(@RequestParam(value = "user_id") String userId,
                                   @RequestParam(value = "use_redis", required = false) boolean useRedis,
                                   @RequestParam(value = "async_db", required = false) boolean asyncDb) {
        return commonManagement.setDataServiceConfig(userId, asyncDb, useRedis);
    }

    @RequestMapping(value = "/common/position/check", method = RequestMethod.POST)
    public ResultBean changePositionCheck(@RequestParam(value = "user_id") String userId,
                                          @RequestParam(value = "position_check_flag", required = false) boolean positionCheckFlag) {
        return commonManagement.setPositionCheckFlag(userId, positionCheckFlag);
    }


    /**
     * 取交易日
     *
     * @param tradeCalendarType 交易日类型        3-上交所 4-深交所
     * @param CDATE             日期              格式：yyyyMMdd，如20200512
     * @param NDay              第N个交易日       可以传入正数、负数、0
     * @return ResultBean
     */
    @RequestMapping(value = "/common/trade/date", method = RequestMethod.GET)
    public ResultBean queryTradeDate(@RequestParam(value = "calender_type") String tradeCalendarType,
                                     @RequestParam(value = "current_date") String CDATE,
                                     @RequestParam(value = "days") int NDay) {
        return commonManagement.getTradeDate(tradeCalendarType, CDATE, NDay);
    }

    /**
     * @param tradeMarket 交易场所
     * @param tradePlat   交易平台
     * @param assetType   资产一级分类
     * @param assetType1  资产二级分类
     * @param assetType2  资产三级分类
     * @param finProdId   金融产品代码
     * @return ResultBean
     * @Description: 查询交易时间段数据
     */
    @RequestMapping(value = "/common/trade/time", method = RequestMethod.GET)
    public ResultBean queryTradeTime(@RequestParam(value = "trade_market", required = false) String tradeMarket,
                                     @RequestParam(value = "trade_plat", required = false) String tradePlat,
                                     @RequestParam(value = "asset_type", required = false) String assetType,
                                     @RequestParam(value = "asset_type1", required = false) String assetType1,
                                     @RequestParam(value = "asset_type2", required = false) String assetType2,
                                     @RequestParam(value = "declaration_type", required = false) String declarationType,
                                     @RequestParam(value = "fin_prod_id", required = false) String finProdId) {
        return commonManagement.queryTradeTime(tradeMarket, tradePlat, assetType, assetType1, assetType2, declarationType, finProdId);
    }

    /**
     * @return ResultBean
     * @Description: 交易时间段刷新到缓存
     */
    @RequestMapping(value = "/common/trade/time/flush", method = RequestMethod.POST)
    public ResultBean tradeTimeCacheFlush() {
        return commonManagement.tradeTimeCache();
    }
}
