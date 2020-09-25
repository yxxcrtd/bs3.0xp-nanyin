package com.joyintech.service.portfolio.actualtimecapital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.actualtimecapital.dto.ActualTimeCapitalDto;
import com.joyintech.service.portfolio.actualtimecapital.dto.CashArtDealDto;
import com.joyintech.service.portfolio.actualtimecapital.service.ActualTimeCapitalService;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeCapitalController
 * @author: yanya
 * @create: 2019-10-15
 **/
@RestController("backActualTimeCapitalController_dtx")
@RequestMapping("/ActualTimeCapital")
public class ActualTimeCapitalController {

    /**
     * 实时资产头寸Service
     */
    @Autowired
    private ActualTimeCapitalService actualTimeCapitalService;

    /**
     * 资金账户账户下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAcctInfo")
    public List<SelectOptionDto> getAcctInfo(@RequestBody Map<String,Object> map){
        return actualTimeCapitalService.getAcctInfo(map);
    }

    /**
     * 获取现金余额调整
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getCashArtDealInfo")
    public CashArtDealDto getCashArtDealInfo(@RequestBody CashArtDealDto dto){
        return actualTimeCapitalService.getCashArtDealInfo(dto);
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
    public Page<ActualTimeCapitalDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return actualTimeCapitalService.selectForPage(page, length, param);
    }
    
    /**
     * 现金余额调整，如存在更新不存在就插入
     * <功能详细描述>
     * @param cashArtDealDto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertAdjustmentBalance")
    public void insertAdjustmentBalance(@RequestBody CashArtDealDto cashArtDealDto) throws Exception{
        actualTimeCapitalService.insertAdjustmentBalance(cashArtDealDto);
    }
}
