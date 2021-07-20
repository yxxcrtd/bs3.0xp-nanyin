
package com.joyintech.service.period.trdstockconnectbonus.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdstockconnectbonus.service.TrdStockConnectBonusService;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdstockconnectbonus.TrdStockConnectBonusDto;

/**
 * 港股通存续期
 * @see TrdStockConnectBonusController
 */
@RestController("TrdStockConnectBonusCtr_dtx")
@RequestMapping("/TrdStockConnectBonus")
public class TrdStockConnectBonusController {

    @Autowired
    private TrdStockConnectBonusService trdStockConnectBonusService;

    /**
     * 画面列表查询
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdStockConnectBonusDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return trdStockConnectBonusService.findPage(page, length, param);
    }

    /**
     * 业务编码
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length)
        throws Exception {
        return trdStockConnectBonusService.getBusiCode(page, length, param);
    }

    /**
     * 证券账户
     */
    @PostMapping("getSecManageAcctId/{page}/{length}")
    public Page<SelectOptionDto> getSecManageAcctId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdStockConnectBonusService.getSecManageAcctId(page, length, param);
    }

    /**
     * 市场代码
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdStockConnectBonusService.getFinprodMarketId(page, length, param);
    }

    /**
     * 撤销时验证数据状态
     */
    @PostMapping("revokeCheck")
    public int revokeCheck(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockConnectBonusService.revokeCheck(param);
    }

    /**
     * 获取股票分红信息（待处理）
     */
    @PostMapping("findNeedDealInfo")
    public TrdStockConnectBonusDto findNeedDealInfo(@RequestBody Map<String, Object> param)
            throws Exception {
        return trdStockConnectBonusService.findNeedDealInfo(param);
    }

    /**
     * 获取股票分红信息（有效、撤销）
     */
    @PostMapping("findEffectiveInfo")
    public TrdStockConnectBonusDto findEffectiveInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockConnectBonusService.findEffectiveInfo(param);
    }

    /**
     * 查询有效的子组合的除权登记的交易
     */
    @PostMapping("findEffectiveT13Deal")
    public List<BaseTradeProductDealDto> findEffectiveT13Deal(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockConnectBonusService.findEffectiveT13Deal(param);
    }

    /**
     * 获取股票分红信息（流程中）
     */
    @PostMapping("findProcessInfo")
    public TrdStockConnectBonusDto findProcessInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockConnectBonusService.findProcessInfo(param);
    }

    /**
     * 获取金融产品交易Entity
     */
    @PostMapping("finPrdDealInfo")
    public List<TrdProductDeal> finPrdDealInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockConnectBonusService.finPrdDealInfo(busiCode);
    }

    /**
     * 获取组合交易Entity
     */
    @PostMapping("findPtlTradeInfo")
    public PtlTrade findPtlTradeInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockConnectBonusService.findPtlTradeInfo(tradeId);
    }

    /**
     * 获取金融产品交易附属Entity
     */
    @PostMapping("findPrdDealAddInfo")
    public TrdProductDealAdd findPrdDealAddInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockConnectBonusService.findPrdDealAddInfo(tradeId);
    }

    /**
     * 获取划款指令Entity
     */
    @PostMapping("findStlPayOrdInfo")
    public List<StlPayOrders> findStlPayOrdInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockConnectBonusService.findStlPayOrdInfo(tradeId);
    }

    /**
     * 获取分红明细list-待处理
     */
    @PostMapping("findNeedDealDetailInfo")
    public List<FinAssetIntInfo> findNeedDealDetailInfo(@RequestBody Map<String, Object> param)
            throws Exception {
        return trdStockConnectBonusService.findNeedDealDetailInfo(param);
    }

    /**
     * 获取分红明细list-有效
     */
    @PostMapping("findDividDetailInfo")
    public List<FinAssetIntInfo> findDividDetailInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockConnectBonusService.findDividDetailInfo(busiCode);
    }

    /**
     * 获取分红明细list-流程中
     */
    @PostMapping("findProcessDividInfo")
    public List<FinAssetIntInfo> findProcessDividInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockConnectBonusService.findProcessDividInfo(busiCode);
    }

    /**
     * 股票持仓查询
     */
    @PostMapping("findividInfo")
    public List<FinAssetIntInfo> findividInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockConnectBonusService.findividInfo(param);
    }

    /**
     * 撤销
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdStockConnectBonusDto dto) {
        return trdStockConnectBonusService.doRevoke(dto);

    }

    /**
     * 流程
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doInsert")
    public String doInsert(@RequestBody BaseTotalTradeInfoDto dto) {
        return trdStockConnectBonusService.doInsert(dto);

    }

    /**
     * doComplete
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdStockConnectBonusDto dto) {
        return trdStockConnectBonusService.doComplete(dto);
    }

    /**
     * 获取生效的交易信息
     */
	 @PostMapping("getRTrdProductDeals")
	 public List<TrdStockConnectBonusDto> getRTrdProductDeals(@RequestBody Map<String, Object> param) {
	    return trdStockConnectBonusService.getRTrdProductDeals(param);
	 }
}
