package com.joyintech.service.period.trdoptionduration.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdlendduration.dto.TrdLendDurationDto;
import com.joyintech.service.period.trdoptionduration.service.TrdOptionDurationService;

import com.joyintech.tams.invest.common.dto.period.trdoptionduration.OptionDurationDetailDto;
import com.joyintech.tams.invest.common.dto.period.trdoptionduration.TrdOptionDurationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 期权存续期
 *
 * @author liutf
 * @version 2019年12月25日
 * @see TrdOptionDurationController
 */
@RestController("TrdOptionDurationCtr_dtx")
@RequestMapping("/TrdOptionDuration")
public class TrdOptionDurationController {

    @Autowired
    private TrdOptionDurationService trdOptionDurationService;
    /**
     * 画面列表查询
     *
     * @author panbin
     * @date 2019年6月20日
     * @param param
     * @param page
     * @param length
     * @return Page<TrdOptionDurationDto>
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdOptionDurationDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                               @PathVariable("length") int length) {
        Page<TrdOptionDurationDto> page1 = trdOptionDurationService.findPage(page, length, param);
        return page1;
    }

    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody OptionDurationDetailDto optionDurationDetailDto) {
        return trdOptionDurationService.doComplete(optionDurationDetailDto);
    }

    /**
     * 获取合约标的
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @RequestMapping("getoption/{page}/{length}")
    public Page<SelectOptionDto> getoption(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return trdOptionDurationService.getoption(page, length, param);
    }

    /**
     * 获取entity
     * <功能详细描述>
     *
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    @RequestMapping("/getEntityByMap")
    public OptionDurationDetailDto getEntityByMap(@RequestBody Map<String, Object> map) {
        return trdOptionDurationService.getEntityByMap(map);
    }
    
    @RequestMapping("/revokeCheck")
    public int revokeCheck(@RequestBody Map<String, Object> param){
    	return trdOptionDurationService.revokeCheck(param);
    }

}
