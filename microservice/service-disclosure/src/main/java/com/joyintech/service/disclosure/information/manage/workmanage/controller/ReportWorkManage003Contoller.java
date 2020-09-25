package com.joyintech.service.disclosure.information.manage.workmanage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.service.ReportWorkManage003Service;

/**
 * 
 * 报表作业管理-预览产品查询
 * @author wanghaibin
 * @version 1.0 2018年12月05日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportWorkManage003Contoller")
@RequestMapping("/ReportWorkManage003")
public class ReportWorkManage003Contoller extends BaseController{
	/**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ReportWorkManage003Service reportWorkManage003Service;
//	@Autowired
//    private BaseCommonService baseCommonService;

    /**
     * 获取产品信息
     *
     * @return 返回的数据
     * @throws IOException 
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/findProdPage")
    public List<ReportWorkManage003Dto> findProdPage(@RequestBody Map<String, Object> map){
        return reportWorkManage003Service.findProdPage(map);
    }
}
