package com.joyintech.service.basicdata.futuresdepositpercent.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;
import com.joyintech.service.basicdata.futuresdepositpercent.service.FuturesDepositPercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Classname FuturesDepositPercentController
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 18:45 
 * @version 1.0 
 */

@RestController("FuturesDepositPercentCtr_dtx")
@RequestMapping("/FuturesDepositPercent")
public class FuturesDepositPercentController {

    @Autowired
    private FuturesDepositPercentService futuresDepositPercentService;

    /**
     *
     * @Description //分页查询
     * @Param [param, page, length]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto>
     * @version 1.0
     * @see
    **/
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FuturesDepositPercentDto> selectForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page")int page, @PathVariable("length")int length)throws Exception{
        return futuresDepositPercentService.selectForPage(page,length,param);

    }


    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     *
     * @param  Map<String, Object> dto type
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{operationType}")
    public void doComplete(@RequestBody FuturesDepositPercentDto dto, @PathVariable("operationType") String type) {
        dto.setOperateType(type);
        futuresDepositPercentService.doComplete(dto);
    }

    @PostMapping("selectByPrimaryKey")
    public FuturesDepositPercentDto selectByPrimaryKey(@RequestBody Map<String, Object> param)
            throws Exception {
        return futuresDepositPercentService.selectByPrimaryKey(param);
    }


}
