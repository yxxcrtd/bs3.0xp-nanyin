package com.joyintech.service.chb.transmonthreport.controller;

import java.text.ParseException;
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
import com.joyintech.service.chb.monthreport.controller.ChbMonthReportController;
import com.joyintech.tams.svreport.chb.transmonthreport.service.ChbTransMonthReport001Service;

/**
 * 理财产品转型情况月度统计表报送登服务 〈功能详细描述〉
 * 
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbMonthReportController
 * @since 1.0
 */
@RestController("ChbTransMonthReportCtr_dtx")
@RequestMapping("/ChbTransMonthReport")
public class ChbTransMonthReportController {
    /**
     * service
     */
    @Autowired
    private ChbTransMonthReport001Service chbTransMonthReport001Service;
    @Transactional
	@DTXTransaction
    @PostMapping("createData")
    public Map<Object, Object> createData(@RequestBody Map<String, Object> selectParam) throws ParseException {
        return chbTransMonthReport001Service.createData(selectParam);
    }
    
    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    @PostMapping("/getChbTransOrg/{page}/{length}")
    public Page<SelectOptionDto> getChbTransOrg(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return chbTransMonthReport001Service.getChbTransOrg(page,length,pageParam);
    }
}
