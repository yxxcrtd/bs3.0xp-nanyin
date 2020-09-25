package com.joyintech.service.disclosure.information.reveal.reportconfirm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage003Service;
import com.joyintech.tams.common.base.entity.RepReveal;

/**
 * 附件上传
 * 
 * @author Administrator
 */
@RestController(value="serviceReportConfirmManage003Contoller")
@RequestMapping("/ReportConfirmManage003")
public class ReportConfirmManage003Contoller extends BaseController {

    /**
     * 
     */
    @Autowired
    private ReportConfirmManage003Service reportConfirmManage003Service;

    /**
     * 进度条组件
     */
    @Autowired(required = false)
    @Qualifier("commonProgressBarService")
    private ProgressBarService progressBarService;

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<BokFileTransfer003Dto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        // return valNetValueConfirm003Service.findPage(param); // TODO
        return null;
    }

    /**
     * 查询相同报表数据数据是否为已确定
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("/selectConfirmStatus")
    public boolean selectConfirmStatus(@RequestBody RepReveal repReveal)
        throws Exception {
        return reportConfirmManage003Service.selectConfirmStatus(repReveal);
    }

    /**
     * 批量上传后更新文件信息
     * 
     * @param repReveal
     * @return
     * @throws Exception
     */
    @PostMapping("/updateRepRevealByInfo")
    public int updateRepRevealByInfo(@RequestBody RepReveal repReveal)
        throws Exception {
        return reportConfirmManage003Service.updateRepRevealByInfo(repReveal);
    }

}
