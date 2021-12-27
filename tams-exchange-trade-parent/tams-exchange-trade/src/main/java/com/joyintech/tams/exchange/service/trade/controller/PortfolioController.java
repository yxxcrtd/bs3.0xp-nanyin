package com.joyintech.tams.exchange.service.trade.controller;

import cn.hutool.core.bean.BeanUtil;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.request.PortfolioInfoRequest;
import com.joyintech.tams.exchange.common.request.PortfolioPositionRequest;
import com.joyintech.tams.exchange.service.trade.management.service.PortfolioManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author swwan
 * @date 2021/11/22 - 16:52
 */
@RestController
@RequestMapping("/trade/joyin/portfolio")
@ResponseResultBody
@Api(tags = "场内交易-组合查询接口")
public class PortfolioController extends BaseController {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    private PortfolioManager portfolioManager;

    @PostMapping
    @ApiOperation(value = "查询组合信息", notes = "查询组合信息")
    public ResultBean queryPortfolioInfo(@RequestBody @Validated PortfolioInfoRequest bean) {
        return portfolioManager.queryPortfolioInfo(bean);
    }

    @PostMapping("/cash")
    @ApiOperation(value = "查询组合现金信息", notes = "查询组合现金信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "portfolioId", value = "组合编号", paramType = "query"),
            @ApiImplicitParam(required = false, name = "acctNo", value = "资金账户编号", paramType = "query")
    })
    public ResultBean queryPortfolioCash(@RequestParam @NotBlank(message = "组合编号不可为空") String portfolioId,
                                         @RequestParam @NotBlank(message = "资金账户编号不可为空") String acctNo) {
        return portfolioManager.queryPortfolioCash(portfolioId, acctNo);
    }

    @PostMapping("/position")
    @ApiOperation(value = "查询组合持仓信息", notes = "查询组合持仓信息")
    public ResultBean queryPortfolioPosition(@RequestBody @Validated PortfolioPositionRequest request) {
        return portfolioManager.queryPortfolioPosition(BeanUtil.beanToMap(request));
    }

    @PostMapping(value = "/calculate")
    @ApiOperation(value = "查询组合计算信息", notes = "查询组合计算信息")
    public ResultBean queryPortfolioCalculate(@RequestBody @Validated OrderPlaceBean bean) {
        return portfolioManager.queryPortfolioCalculate(bean);
    }
}