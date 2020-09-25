package com.joyintech.service.product.productduration.netproductbonusscheme.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusDealDto;
import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.service.product.productduration.netproductbonusscheme.service.FinNetProductBonusSchemeService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 产品存续期-净值型产品分红方案
 * @author : liuwei
 * @version V1.0
 * @Project: microservice
 */
@RestController("FinNetProductBonusSchemeCtl_dtx")
@RequestMapping("/FinNetProductBonusScheme")
public class FinNetProductBonusSchemeController {
    /**
     * 净值型产品分红方案Service
     */
    @Autowired
    private FinNetProductBonusSchemeService finNetProductBonusSchemeService;

    /**
     * 组合可用持仓和实际持仓查询更新组件实现类
     */
    @Autowired(required=false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required=false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     *
     */
    @Autowired(required=false)
    private BaseTradeProductDealService baseTradeProductDealService;
    /**
     * 产品代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getFinprodMarketId(map);
    }

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinNetProductBonusSchemeDto> selectAll(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finNetProductBonusSchemeService.selectForPage(page, length, param);
    }

    /**
     * 删除方法
     *
     * @param dto 画面参数
     * @return 删除结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinNetProductBonusSchemeDto dto){
        return finNetProductBonusSchemeService.delete(dto);
    }

    /**
     * 产品代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getFinprodMarketIdList(map);
    }

    /**
     * 查询产品简称和产品名称
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAbbrAndName")
    public FinNetProductBonusSchemeDto getAbbrAndName(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getAbbrAndName(map);
    }

    /**
     * 插入
     * @param dto 页面选中的数据
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinNetProductBonusSchemeDto dto)
            throws Exception {
        return finNetProductBonusSchemeService.insert(dto);
    }

    /**
     * 修改
     * @param dto 页面选中的数据
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinNetProductBonusSchemeDto dto)
            throws Exception {
        return finNetProductBonusSchemeService.update(dto);
    }

    /**
     * 查询分红方案编号
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("selectBonuScalcFlag")
    public int selectBonuScalcFlag(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.selectBonuScalcFlag(map);
    }

    /**
     * 产品是否已派发红利
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("IsDividend")
    public String IsDividend(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.IsDividend(map);
    }

    /**
     * 查询交易日历编号
     *
     * @param finprodId 金融产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("selectCalendarId")
    public String selectCalendarId(@RequestBody String finprodId){
        return finNetProductBonusSchemeService.selectCalendarId(finprodId);
    }

    /**
     * 验证同一除权日是否重复登录
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("countDelDate")
    public int countDelDate(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.countDelDate(map);
    }

    /**
     * 修改或双击查看界面
     *
     * @param bonusPlanId 分红方案编号
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @GetMapping("selectInfo")
    public FinNetProductBonusSchemeDto selectInfo(String bonusPlanId){
        return finNetProductBonusSchemeService.selectInfo(bonusPlanId);
    }

    /**
     * 查询基准日未分配余额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getTdyUnpaidIntincexp")
    public BigDecimal getTdyUnpaidIntincexp(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getTdyUnpaidIntincexp(map);
    }

    /**
     * 查询净值参考日
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getVluRefDate")
    public Date getVluRefDate(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getVluRefDate(map);
    }

    /**
     * 查询参考日净值和份额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getTdyUnitValueAndTdyShareAmt")
    public FinNetProductBonusSchemeDto getTdyUnitValueAndTdyShareAmt(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.getTdyUnitValueAndTdyShareAmt(map);
    }

    /**
     * 查询产品是否存在有效的现金分红或红利再投交易
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("isExistBonusDeal")
    public int isExistBonusDeal(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.isExistBonusDeal(map);
    }

    /**
     * 查询产品的分红模式
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryBonusMode")
    public String queryBonusMode(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.queryBonusMode(map);
    }

    /**
     * 调用组合可用持仓和实际持仓查询更新组件
     *
     * @return 结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertPosition")
    public int insertPosition(@RequestBody PtlPositionHoldDto ptlPositionHoldDto) {
        if (ptlPositionHoldDto != null) {
            if (ptlPositionHoldDto.getPtlSecPositionFlows() != null
                && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                    basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlow);
                }
            }
            if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
                && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                    basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
                }
            }
        }
        return 0;
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertProductDeal")
    public int insertProductDeal(@RequestBody List<BaseTradeProductDealDto> baseTradeProductDealList) {
        baseTradeProductDealService.insert(baseTradeProductDealList);
        return 0;
    }

    @PostMapping("queryBonusDetail")
    public List<FinNetProductBonusDealDto> queryBonusDetail(@RequestBody Map<String, Object> param) throws Exception {
        return finNetProductBonusSchemeService.queryBonusDetail(param);
    }

    @PostMapping("queryBonusPlanId")
    public String queryBonusPlanId(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.queryBonusPlanId(map);
    }

    /**
     * 查询 交易明细数据，查看、撤销调用
     * @param map
     * @return
     */
    @PostMapping("selectDetailInfo")
    public FinNetProductBonusSchemeDto selectDetailInfo(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.selectDetailInfo(map);
    }

    @PostMapping("idBonusAmtConfirm")
    public String idBonusAmtConfirm(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.idBonusAmtConfirm(map);
    }

    @PostMapping("queryAfterTrdFinProductDealTotal")
    public List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(@RequestBody FinNetProductBonusSchemeDto dto){
        return finNetProductBonusSchemeService.queryAfterTrdFinProductDealTotal(dto);
    }
    
    /**
     * 通过产品id查询 标的类金融产品附属表
     *
     * @param finprodId 产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @GetMapping("selectFinProductADD")
    public FinProductAdd selectFinProductADD(String finprodId){
        return finNetProductBonusSchemeService.selectFinProductADD(finprodId);
    }
    /**
     * 查询产品是否已完全有效的现金分红或红利再投交易
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("isExceedBonusDeal")
    public BigDecimal isExceedBonusDeal(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.isExceedBonusDeal(map);
    }
    
    @PostMapping("findCycleDataTableInfo")
    public List<FinNetProductBonusSchemeDto> findCycleDataTableInfo(@RequestBody Map<String, Object> param) throws Exception {
        
    	List<FinNetProductBonusSchemeDto> list = finNetProductBonusSchemeService.findCycleDataTableInfo(param);
    	return list;
    }

    @PostMapping("queryTradeIdByTotle")
    public String queryTradeIdByTotle(@RequestBody Map<String,Object> map){
        return finNetProductBonusSchemeService.queryTradeIdByTotle(map);
    }
}
