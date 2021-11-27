package com.joyintech.service.chb.transmonthreport.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDto;
import com.joyintech.tams.svreport.chb.transmonthreport.service.ChbTransMonthReportService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 理财产品转型情况月度报表报送登服务
 *
 * @author 杜保军
 * @version 2020年6月4日
 * @see ChbTransMonthReportController
 * @since 1.0
 */
@RestController("ChbTransMonthReportCtr_dtx")
@RequestMapping("/ChbTransMonthReport")
public class ChbTransMonthReportController {

    /**
     * service
     */
    @Autowired
    private ChbTransMonthReportService chbTransMonthReportService;

    /**
     * 业务画面查询事件
     *
     * @param param 参数
     * @return 分页集合
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbTransMonthReportSubDto> searchPage(@RequestBody Map<String, Object> param) {
        Page<ChbTransMonthReportSubDto> page = chbTransMonthReportService.searchPage(param);
        return page;
    }

    /**
     * 业务画面查询事件
     *
     * @param param 参数
     * @return 单条数据
     * @see
     * @since 1.0
     */
    @PostMapping("searchPageBysubmitDate")
    public ChbTransMonthReportSubDto searchPageBysubmitDate(@RequestBody Map<String, Object> param) {
        return chbTransMonthReportService.searchPageBysubmitDate(param);
    }

    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String, Object> map) {
        chbTransMonthReportService.updateSumStaus(map);
    }

    /**
     * 重新生成报表
     *
     * @param map 生成日期
     * @return 生成结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recalReport")
    public String recalReport(@RequestBody Map<String, Object> map) {
        String message = chbTransMonthReportService.recalReport(map);
        return message;
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("cancelMannul")
    public void cancelMannul(@RequestBody Map<String, Object> map) {
        chbTransMonthReportService.cancelMannul(map);
    }

    /**
     * 根据查询参数获取对应的数据，做成Map供画面查看或导出。
     *
     * @param selectParam 查询参数
     * @return 报表数据
     */
    @Transactional
    @DTXTransaction
    @PostMapping("createData")
    public Map<Object, Object> createData(@RequestBody Map<String, Object> selectParam) {
        return chbTransMonthReportService.createData(selectParam);
    }

    /**
     * 报送检查
     *
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        List<String> keyList = new ArrayList<String>();
        Map<String, Object> param = map;
        if (!param.containsKey("checkKeys")) {
            keyList = chbTransMonthReportService.getCheckRepList(param);
        } else {
            keyList = StringUtil.splitToList(GlobalConstant.COMMA, (String) param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据", null));
            return checkResult;
        }
        return chbTransMonthReportService.checkData(keyList);
    }
}
