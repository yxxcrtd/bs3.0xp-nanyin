package com.joyintech.service.trade.projectnet.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.projectnet.dto.TrdProjectNetInvestTradeDto;
import com.joyintech.service.trade.projectnet.service.TrdProjectNetInvestTradeService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController("TrdProjectNetInvestTradeCtr_dtx")
@RequestMapping("/TrdProjectNetInvestTrade")
public class TrdProjectNetInvestTradeController {
    @Autowired
    private TrdProjectNetInvestTradeService trdProjectNetInvestTradeService;

    /**
     *
     * 分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdProjectNetInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                          @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return trdProjectNetInvestTradeService.selectForPage(page, length, param);
    }

    /**
     *
     * 资产代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) {
        return trdProjectNetInvestTradeService.getFinprodId(page, length,param);
    }

    /**
     *
     * 提交进入流程时调用方法
     * <功能详细描述>
     * @param entity
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doInsert")
    public void doInsert(@RequestBody TrdProjectNetInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdProjectNetInvestTradeService.doInsert(entity);
    }
    /**
     *
     * 流程处理结束时调用方法
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doComplete")
    public void doComplete(@RequestBody TrdProjectNetInvestTradeDto entity){
        trdProjectNetInvestTradeService.doComplete(entity);
    }
    /**
     *
     * 经代办修改业务流程表方法
     * <功能详细描述>
     * @param entity
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doUpdate")
    public void doUpdate(@RequestBody TrdProjectNetInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdProjectNetInvestTradeService.doUpdate(entity);
    }
    /**
     *
     * 流程作废时删除业务流程表方法
     * <功能详细描述>
     * @param workflowId
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doDelete")
    public void doDelete(String workflowId){
        trdProjectNetInvestTradeService.doDelete(workflowId);
    }

    /**
     *
     * 查询组合可用现金
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return trdProjectNetInvestTradeService.getProAvailableCash(param);
    }
    /**
     *
     * 查询组合可用持仓
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getPtlSecPosition")
    public BigDecimal getPtlSecPosition(@RequestBody PtlSecPositionFlowDto dto){
        return trdProjectNetInvestTradeService.getPtlSecPosition(dto);
    }
    /**
     *
     * 获取交易dto
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getFinDto")
    public List<BaseTradeProductDealDto> getFinDto(String tradId){
        return trdProjectNetInvestTradeService.getFinDto(tradId);
    }

    /**
     *
     * 查询反显数据
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTrdFundDto")
    public TrdProjectNetInvestTradeDto getTrdFundDto(String tradId){
        return trdProjectNetInvestTradeService.getTrdFundDto(tradId);
    }

    /**
     *
     * 查询单位净值
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getPrice")
    public BigDecimal getPrice(@RequestBody Map<String, Object> map){
        return trdProjectNetInvestTradeService.getPrice(map);
    }
    /**
     *
     * 查询投资委托DTO
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getEtrustDto")
    public BaseEtrInvestInfoDto getEtrustDto(String entrustId){
        return trdProjectNetInvestTradeService.getEtrustDto(entrustId);
    }

    /**
     *
     * 查询资产代码的交易方式
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getPurSpeed")
    public Map<String, Object> getPurSpeed(@RequestBody Map<String, Object> param){
        return trdProjectNetInvestTradeService.getPurSpeed(param);
    }
    /**
     *
     * 插入交易到账信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsertRedemption")
    public void doInsertRedemption(@RequestBody TrdProjectNetInvestTradeDto entity){
        trdProjectNetInvestTradeService.doInsertRedemption(entity);
    }

    /**
     *
     * 插入交易到账信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDeleteRedemption")
    public void doDeleteRedemption(@RequestBody TrdProjectNetInvestTradeDto entity){
        trdProjectNetInvestTradeService.doDeleteRedemption(entity);
    }

    /**
     *
     * 查询到账交易是否托管匹配
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selRedemption")
    public Integer selRedemption(String tradeId){
        return trdProjectNetInvestTradeService.selRedemption(tradeId);
    }
    /**
     *
     * 查询撤销数据是否有后继交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("revokeCheck")
    public boolean revokeCheck(@RequestBody TrdProjectNetInvestTradeDto dto){
        return trdProjectNetInvestTradeService.revokeCheck(dto);
    }
    /**
     *
     * 查询交易到账数据
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTrdFundDtoForAccount")
    public TrdProjectNetInvestTradeDto getTrdFundDtoForAccount(String tradeId){
        return trdProjectNetInvestTradeService.getTrdFundDtoForAccount(tradeId);
    }
    /**
     *
     * 查询业务流程表中的划款指令数据
     * <功能详细描述>
     * @param bizCode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectOrdersByBizCode")
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(String bizCode) {
        return trdProjectNetInvestTradeService.selectOrdersByBizCode(bizCode);
    }
    /**
     *
     * 根据赎回确认交易编号查询赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAccountTrdId")
    public Map<String, Object> selectAccountTrdId(@RequestBody Map<String, Object> param){
        return trdProjectNetInvestTradeService.selectAccountTrdId(param);
    }
    /**
     *
     * 根据赎回确认交易编号查询赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAccountTrdIdinwfl")
    public Map<String, Object> selectAccountTrdIdinwfl(String tradId){
        return trdProjectNetInvestTradeService.selectAccountTrdIdinwfl(tradId);
    }
    /**
     *
     * 插入交易到账撤销信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @PostMapping("revokeAccount")
    @Transactional
    @DTXTransaction
    public void revokeAccount(@RequestBody TrdProjectNetInvestTradeDto entity){
        trdProjectNetInvestTradeService.revokeAccount(entity);
    }
    /**
     *
     * 取得反显费用明细
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTradeRefrence")
    public Map<String, Object> getTradeRefrence(String tradId){
        return trdProjectNetInvestTradeService.getTradeRefrence(tradId);
    }
    /**
     *
     * 查询委托可用金额
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectEntrustAmt")
    public Map<String, Object> selectEntrustAmt(@RequestBody Map<String, Object> param){
        return trdProjectNetInvestTradeService.selectEntrustAmt(param);
    }

    /**
     * 确认页面组合分配列表查询
     * @param param
     * @return
     */
    @RequestMapping("selectTrdTransfenpeiList")
    public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(@RequestBody Map<String, Object> param) {
        return trdProjectNetInvestTradeService.selectTrdTransfenpeiList(param);
    }

    /**
     * 根据资产代码查询持有金额
     * @param param
     * @return
     */
    @GetMapping("queryHoldAmt")
    public BigDecimal queryHoldAmt(String finprodId) {
        return trdProjectNetInvestTradeService.queryHoldAmt(finprodId);
    }

    /**
     * 根据资产代码查询发行金额
     * @param param
     * @return
     */
    @GetMapping("queryIssueAmt")
    public BigDecimal queryIssueAmt(String finprodId) {
        return trdProjectNetInvestTradeService.queryIssueAmt(finprodId);
    }

    @RequestMapping("getFinprodMarketId")
    public List<SuggestDto> getAssetIdMngNet(@RequestBody Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        return trdProjectNetInvestTradeService.getFinprodMarketId(param);
    }
}
