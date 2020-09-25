package com.joyintech.service.chb.monthreport.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.service.ChbMonthReport001Service;
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
 * 中债银行月度报表报送登服务 〈功能详细描述〉
 * 
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbMonthReportController
 * @since 1.0
 */
@RestController("ChbMonthReportCtr_dtx")
@RequestMapping("/ChbMonthReport")
public class ChbMonthReportController {

    /**
     * service
     */
    @Autowired
    private ChbMonthReport001Service chbMonthReport001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbMonthReport> searchPage(@RequestBody Map<String, Object> param) {
        Page<ChbMonthReport> page = chbMonthReport001Service.searchPage(param);
        return page;
    }

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("searchPageBysubmitDate")
    public ChbMonthReport searchPageBysubmitDate(@RequestBody Map<String, Object> param) {
        return chbMonthReport001Service.searchPageBysubmitDate(param);
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
        chbMonthReport001Service.updateSumStaus(map);
    }

    // @PostMapping("doAseetBatch")
    // public void doAssetbatch(@RequestBody Map<String, Object> map) {
    // chbMonthReport001Service.doAseetBatch(map);
    // }
    @Transactional
    @DTXTransaction
    @PostMapping("recalReport")
    public String recalReport(@RequestBody Map<String, Object> map) {
        String message = chbMonthReport001Service.recalReport(map);
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
        chbMonthReport001Service.cancelMannul(map);
    }

	@Transactional
	@DTXTransaction
    @PostMapping("createData")
    public Map<Object, Object> createData(@RequestBody Map<String, Object> selectParam) {
        return chbMonthReport001Service.createData(selectParam);
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
            keyList = chbMonthReport001Service.getCheckRepList(param);
        }
        else {
            keyList = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据", null));
            return checkResult;
        }
        return chbMonthReport001Service.checkData(keyList);
    }
}
