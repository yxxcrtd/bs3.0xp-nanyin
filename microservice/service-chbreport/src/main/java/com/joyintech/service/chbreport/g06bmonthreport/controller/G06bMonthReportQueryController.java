package com.joyintech.service.chbreport.g06bmonthreport.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chbreport.g06bmonthreport.service.G06bMonthReportQuery001Service;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bDetail;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * G06b风险资本计算表查询服务
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("G06bMonthReportQueryCtr_dtx")
@RequestMapping("/G06bMonthReportQuery")
public class G06bMonthReportQueryController {

    /**
     * service
     */
    @Autowired
    private G06bMonthReportQuery001Service G06bMonthReportQuery001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbreportPrdInfoDto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return G06bMonthReportQuery001Service.selectForPage(param);
    }

    /**
     * 查询导出华夏利润表
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectG06bMonthReportList")
    public List<ChbMonthReportG06bDetail> selectG06bMonthReportList(@RequestBody Map<String, Object> param) throws Exception {
        return G06bMonthReportQuery001Service.selectG06bMonthReportList(param);
    }

}
