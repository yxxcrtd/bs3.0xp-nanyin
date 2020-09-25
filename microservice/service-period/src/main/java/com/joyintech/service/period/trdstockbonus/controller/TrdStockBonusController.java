/**
 * 
 */
package com.joyintech.service.period.trdstockbonus.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdstockbonus.service.TrdStockBonusService;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.TrdStockBonusDto;

/**
 * 股票存续期
 * 
 * @author panbin
 * @version 2019年6月19日
 * @see TrdStockBonusController
 */
@RestController("TrdStockBonusCtr_dtx")
@RequestMapping("/TrdStockBonus")
public class TrdStockBonusController {

    @Autowired
    private TrdStockBonusService trdStockBonusService;

    /**
     * 画面列表查询
     *
     * @author panbin
     * @date 2019年6月20日
     * @param param
     * @param page
     * @param length
     * @return Page<TrdRepoDurationDto>
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdStockBonusDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return trdStockBonusService.findPage(page, length, param);
    }

    /**
     * 业务编码
     * 
     * @return Page<SelectOptionDto>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length)
        throws Exception {
        return trdStockBonusService.getBusiCode(page, length, param);
    }

    /**
     * 证券账户
     *
     * @author panbin
     * @date 2019年6月21日
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception Page<SelectOptionDto>
     */
    @PostMapping("getSecManageAcctId/{page}/{length}")
    public Page<SelectOptionDto> getSecManageAcctId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdStockBonusService.getSecManageAcctId(page, length, param);
    }

    /**
     * 市场代码
     *
     * @author panbin
     * @date 2019年6月20日
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception Page<SelectOptionDto>
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdStockBonusService.getFinprodMarketId(page, length, param);
    }

    /**
     * 撤销时验证数据状态
     *
     * @author panbin
     * @date 2019年6月24日
     * @param param
     * @return
     * @throws Exception Page<Map<String,String>>
     */
    @PostMapping("revokeCheck")
    public int revokeCheck(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockBonusService.revokeCheck(param);
    }

    /**
     * 获取股票分红信息（有效、撤销）
     *
     * @author panbin
     * @date 2019年6月24日
     * @param param
     * @return TrdFinProductDealTotal
     */
    @PostMapping("findEffectiveInfo")
    public TrdStockBonusDto findEffectiveInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockBonusService.findEffectiveInfo(param);
    }

    /**
     * 查询有效的子组合的除权登记的交易
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findEffectiveT13Deal")
    public List<BaseTradeProductDealDto> findEffectiveT13Deal(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockBonusService.findEffectiveT13Deal(param);
    }

    /**
     * 获取股票分红信息（流程中）
     * 
     * @return TrdRepoDurationDto
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findProcessInfo")
    public TrdStockBonusDto findProcessInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockBonusService.findProcessInfo(param);
    }

    /**
     * 获取金融产品交易Entity
     *
     * @author panbin
     * @date 2019年6月24日
     * @param busiCode
     * @return List<TrdProductDeal>
     * @throws Exception
     */
    @PostMapping("finPrdDealInfo")
    public List<TrdProductDeal> finPrdDealInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockBonusService.finPrdDealInfo(busiCode);
    }

    /**
     * 获取组合交易Entity
     *
     * @author panbin
     * @date 2019年6月24日
     * @param tradeId
     * @return PtlTrade
     * @throws Exception
     */
    @PostMapping("findPtlTradeInfo")
    public PtlTrade findPtlTradeInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockBonusService.findPtlTradeInfo(tradeId);
    }

    /**
     * 获取金融产品交易附属Entity
     *
     * @author panbin
     * @date 2019年6月24日
     * @param tradeId
     * @return TrdProductDealAdd
     * @throws Exception
     */
    @PostMapping("findPrdDealAddInfo")
    public TrdProductDealAdd findPrdDealAddInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockBonusService.findPrdDealAddInfo(tradeId);
    }

    /**
     * 获取划款指令Entity
     *
     * @author panbin
     * @date 2019年6月24日
     * @return List<StlPayOrders>
     * @param tradeId
     * @throws Exception
     */
    @PostMapping("findStlPayOrdInfo")
    public List<StlPayOrders> findStlPayOrdInfo(@RequestBody String tradeId)
        throws Exception {
        return trdStockBonusService.findStlPayOrdInfo(tradeId);
    }

    /**
     * 获取分红明细list-有效
     * 
     * @return List<DividDetailInfo>
     * @param busiCode
     * @return
     * @throws Exception
     */
    @PostMapping("findDividDetailInfo")
    public List<FinAssetIntInfo> findDividDetailInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockBonusService.findDividDetailInfo(busiCode);
    }

    /**
     * 获取分红明细list-流程中
     * 
     * @param busiCode
     * @return
     * @throws Exception
     */
    @PostMapping("findProcessDividInfo")
    public List<FinAssetIntInfo> findProcessDividInfo(@RequestBody String busiCode)
        throws Exception {
        return trdStockBonusService.findProcessDividInfo(busiCode);
    }

    /**
     * 股票持仓查询
     * 
     * @return List<PtlSecPosition>
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findividInfo")
    public List<FinAssetIntInfo> findividInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdStockBonusService.findividInfo(param);
    }

    /**
     * 撤销
     * 
     * @return String
     * @param dto
     * @return
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdStockBonusDto dto) {
        return trdStockBonusService.doRevoke(dto);

    }

    /**
     * @param dto
     * @return
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doInsert")
    public String doInsert(@RequestBody BaseTotalTradeInfoDto dto) {
        return trdStockBonusService.doInsert(dto);

    }

    /**
     * @param dto
     * @return
     */
	@Transactional
	@DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdStockBonusDto dto) {
        return trdStockBonusService.doComplete(dto);
    }

    /**
     * 获取生效的交易信息
     * 
     * @param dto
     * @return
     */
	 @PostMapping("getRTrdProductDeals")
	 public List<TrdStockBonusDto> getRTrdProductDeals(@RequestBody Map<String, Object> param) {
	    return trdStockBonusService.getRTrdProductDeals(param);
	 }
}
