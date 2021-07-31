package com.joyintech.service.risk.fundgoal.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.fundgoal.dto.FundGoalDto;
import com.joyintech.service.risk.fundgoal.service.FundGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname FundGoalController
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 11:44 
 * @version 1.0 
 */

@RestController("FundGoalController_dtx")
@RequestMapping("/FundGoal")
public class FundGoalController {
    @Autowired
    FundGoalService fundGoalService;

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(@RequestBody Map<String, Object> param)
            throws Exception {
        return fundGoalService.getFinprodMarketIdList(param);
    }

    @PostMapping("selectByPrimaryKey")
    public FundGoalDto selectByPrimaryKey(@RequestBody Map<String, Object> param)
            throws Exception {
        return fundGoalService.selectByPrimaryKey(param);
    }

    @PostMapping("getCalendarId")
    public String getCalendarId(@RequestBody Map<String, Object> param)
            throws Exception {
        return fundGoalService.getCalendarId(param);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FundGoalDto> findPage(@RequestBody Map<String, Object> param,
                                      @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return fundGoalService.selectForPage(page, length, param);
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
    public void doComplete(@RequestBody FundGoalDto dto, @PathVariable("operationType") String type) {
        dto.setOperateType(type);
        fundGoalService.doComplete(dto);
    }
}
