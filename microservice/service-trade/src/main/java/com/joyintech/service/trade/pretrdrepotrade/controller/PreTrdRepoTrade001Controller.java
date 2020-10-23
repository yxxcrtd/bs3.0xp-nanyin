
package com.joyintech.service.trade.pretrdrepotrade.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.pretrdrepotrade.dto.PreTrdRepoTrade001Dto;
import com.joyintech.service.trade.pretrdrepotrade.service.PreTrdRepoTrade001Service;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeCSTPDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeExportMailDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeReferenceDto;
import com.joyintech.service.trade.trdrepotrade.service.TrdRepoTradeService;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 质押式回购交易预录入
 */
@RestController("backPreTrdRepoTrade001Controller")
@RequestMapping("/PreTrdRepoTrade001")
public class PreTrdRepoTrade001Controller {

    @Autowired
    private PreTrdRepoTrade001Service preTrdRepoTrade001Service;

    /**
     * 回购资产交易查询画面，查询回购资产交易信息列表
     *
     * @return Page<PreTrdRepoTrade001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectForPage/{page}/{length}")
    public Page<PreTrdRepoTrade001Dto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        return preTrdRepoTrade001Service.selectForPage(page, length, param);
    }

    @RequestMapping("getPreTrdRepoTradeDto")
    public PreTrdRepoTrade001Dto getPreTrdRepoTradeDto(@RequestBody Map<String,Object> params){
        return preTrdRepoTrade001Service.getPreTrdRepoTradeDto(params);
    }

    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterAcct")
    public List<SelectOptionDto> getCounterAcct(@RequestBody Map<String,Object> map){
        return preTrdRepoTrade001Service.getCounterAcct(map);
    }


    /**
     * 获取资金账号下拉框
     *
     * @return 资产代码代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAcct")
    public List<SelectOptionDto> getAcct(@RequestBody Map<String, Object> params)
            throws Exception {

            return preTrdRepoTrade001Service.getAcct( params);
    }


    /**
     * 获取托管账号下拉框
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getSecAcct")
    public List<SelectOptionDto> getSecAcct(@RequestBody Map<String, Object> params)
            throws Exception {
        return preTrdRepoTrade001Service.getSecAcct( params);
    }



    /**
     * 获取资金账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAcctId")
    public PreTrdRepoTrade001Dto getAcctId(@RequestBody Map<String, Object> params)
            throws Exception {
        return preTrdRepoTrade001Service.getAcctId( params);
    }


    /**
     * 获取托管账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getSecAcctDto")
    public PreTrdRepoTrade001Dto getSecAcctDto(@RequestBody Map<String, Object> params)
            throws Exception {
        return preTrdRepoTrade001Service.getSecAcctDto( params);
    }
    /**
     * 获取下行交易匹配情况
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findTrdMatchInfo")
    public List<Map<String, Object>> findTrdMatchInfo(@RequestBody Map<String, Object> params)
            throws Exception {
        return preTrdRepoTrade001Service.findTrdMatchInfo( params);
    }
}
