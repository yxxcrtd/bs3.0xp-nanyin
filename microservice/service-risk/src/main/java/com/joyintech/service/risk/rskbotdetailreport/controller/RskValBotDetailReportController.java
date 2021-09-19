package com.joyintech.service.risk.rskbotdetailreport.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.rskbotdetailreport.dto.RskValBotDetailReportDto;
import com.joyintech.service.risk.rskbotdetailreport.service.RskValBotDetailReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  穿透后底层明细表
 * 〈功能详细描述〉
 * @author lingxq
 * @version 1.0 2021年06月21日
 * @see RskValBotDetailReportController
 * @since 1.0
 */
@RestController("rskValBotDetailReport_dtx")
@RequestMapping("/RskValBotDetailReport")
public class RskValBotDetailReportController {

    @Autowired
    private RskValBotDetailReportService rskValBotDetailReportService;

    /**
     * 查询
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectData/{page}/{length}")
    public Page<RskValBotDetailReportDto> selectData(@RequestBody Map<String, Object> param,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValBotDetailReportService.selectForPage(page, length,param);
    }
}
