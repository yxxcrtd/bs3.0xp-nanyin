/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportreveal.controller;

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
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.service.disclosure.information.reveal.reportreveal.service.ReportRevealManage001Service;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月11日
 * @see ReportRevealManage001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportRevealManage001Contoller_dtx")
@RequestMapping("/ReportRevealManage001")
public class ReportRevealManage001Contoller extends BaseController {

    @Autowired
    private ReportRevealManage001Service reportRevealManage001Service;

    @Autowired
    private GetPrdInfoSelectService getPrdInfoSelectService;

    /**
     * 分页查询产品下拉框
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPrdInfoSelect/{page}/{length}")
    public Page<SelectOptionDto> getPrdInfoSelect(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        // return getPrdInfoSelectService.getPrdInfoSelect(page, length, param); // TODO
        return getPrdInfoSelectService.getPrdInfoSelect(param);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ReportConfirmManage001Dto> findPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return reportRevealManage001Service.findPage(page, length, param);
    }

    /**
     * 不分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryPageList")
    public List<ReportConfirmManage001Dto> queryPageList(@RequestBody Map<String, Object> param)
        throws Exception {
        return reportRevealManage001Service.queryPageList(param);
    }

    /**
     * @param param
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateRevealStatus")
    public boolean updateRevealStatus(@RequestBody ReportConfirmManageParamDto dto)
        throws Exception {
        return reportRevealManage001Service.updateRevealStatus(dto);
    }

    /**
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/updateConfirmStatusWithGoBack")
    public boolean updateConfirmStatusWithGoBack(@RequestBody ReportConfirmManageParamDto dto)
        throws Exception {
        return reportRevealManage001Service.updateConfirmStatusWithGoBack(dto);
    }

    /**
     * 流程完成后的操作
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody ReportConfirmManage001Dto dto, @PathVariable("action") String action) {
        reportRevealManage001Service.doComplete(dto, OperationType.valueOf(action));
    }

}
