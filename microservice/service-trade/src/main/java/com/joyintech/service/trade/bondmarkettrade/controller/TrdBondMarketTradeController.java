package com.joyintech.service.trade.bondmarkettrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.bond.controller.TrdBondInvestTradeController;
import com.joyintech.service.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.service.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.service.trade.bondmarkettrade.service.TrdBondMarketTradeService;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.joyintech.framework.validation.ValidUtil.commonCheck;

/**
 * 债券交易微服务
 *
 * @author 马数凯
 * @version 1.0 2021年4月6日
 * @see TrdBondMarketTradeController
 * @since 1.0
 */
@RestController
@RequestMapping("/TrdBondMarketTrade")
public class TrdBondMarketTradeController {
    @Autowired
    TrdBondMarketTradeService trdBondMarketTradeService;
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdBondMarketTradeDto> findPage(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return trdBondMarketTradeService.findPage(page, length, param);
    }

    
    /**
     * 根据汇总交易编号查询交易数据
     * @param totalTradeId
     * @return
     */
    @PostMapping("selectByTotalTradeId")
    public TrdBondMarketTradeDto selectByTotalTradeId(@RequestBody String totalTradeId){
    
        return trdBondMarketTradeService.selectByTotalTradeId(totalTradeId);
    }
    
    /**
     * 发行代码：下拉框
     *
     * @param param 画面参数
     * @return 发行代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return trdBondMarketTradeService.getFinprodId(page, length, param);
    }

    /**
     * 发行代码：下拉框
     *
     * @param param 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getAppearMarketId/{page}/{length}")
    public Page<SelectOptionDto> getAppearMarketId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return trdBondMarketTradeService.getAppearMarketId(page, length, param);
    }

    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    @PostMapping("getPosition")
    public Map<String,Object> getPosition(@RequestBody Map<String, Object> map){
        
        return trdBondMarketTradeService.getPosition(map);
    }

    /**
     * 数据入库
     * @param tradeInfoDtoList
     * @return
     */
    @PostMapping("doInsert")
    public void doInsert(@RequestBody List<BaseTradeInfoDto> tradeInfoDtoList){
        trdBondMarketTradeService.doInsert(tradeInfoDtoList);
    }

    /**
     * 数据入库
     * @param entity
     * @return
     */
    @PostMapping("doInsertTotal")
    public void doInsertTotal(@RequestBody TrdBondMarketTradeDto entity){
        trdBondMarketTradeService.doInsertTotal(entity);
    }

    
    /**
     * 撤销数据处理
     * @param entity
     * @return
     */
    @PostMapping("doCompleteRevoke")
    public void doCompleteRevoke(@RequestBody TrdBondMarketTradeDto entity){
        trdBondMarketTradeService.doCompleteRevoke(entity);
    }
    
    /**
     * 债券上市流通自动加券
     * @param dto
     * @return
     */
    @PostMapping("autoAddBond")
    public void autoAddBond(@RequestBody TrdBondMarketTradeDto dto){
        trdBondMarketTradeService.autoAddBond(dto);
    }

    /**
     * 查询上市代码是否有其他流程中数据
     *
     * @param dto 画面参数dto
     * @see
     * @since 1.0
     */
    @PostMapping("selectIsInWorkflow")
    public int selectIsInWorkflow(@RequestBody TrdBondMarketTradeDto dto){
        return trdBondMarketTradeService.selectIsInWorkflow(dto);
    }

    /**
     * 查询是否有当日交易数据
     * @param dto
     * @return
     */
    @PostMapping("selectDealNum")
    public int selectDealNum(@RequestBody TrdBondMarketTradeDto dto){
        return trdBondMarketTradeService.selectDealNum(dto);
    }

    /**
     * 查询交易是否存在流程中的撤销数据
     *
     * @param dto 画面参数dto
     * @see
     * @since 2.1.7
     */
    @PostMapping("selectRevokeDeal")
    public int selectRevokeDeal(@RequestBody TrdBondMarketTradeDto dto){
        return trdBondMarketTradeService.selectRevokeDeal(dto);
    }

    /**
     * 查询是否有当日以及后续交易数据
     * @param dto
     * @return
     */
    @PostMapping("selectDealNums")
    public int selectDealNums(@RequestBody TrdBondMarketTradeDto dto){
        return trdBondMarketTradeService.selectDealNums(dto);
    }
    
}
