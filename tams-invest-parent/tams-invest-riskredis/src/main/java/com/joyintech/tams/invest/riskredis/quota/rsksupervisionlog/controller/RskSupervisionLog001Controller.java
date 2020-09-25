package com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.controller;


import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.service.RskSupervisionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.dto.RskSupervisionLogDto;

/**
 * 监控日志
 * @author 裴家俊
 * @version 1.0 2018年12月29日
 * @see RskSupervisionLog001Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionLog001")
@RequestMapping("/invest/riskredis/quota/rsksupervisionlog/RskSupervisionLog001")
public class RskSupervisionLog001Controller extends BaseController  {

    /**
     * 监督值查询service
     */
    @Autowired
    private RskSupervisionLogService rskSupervisionLogService;

    /**
     * 监督日志查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, BussinessDictCodeRds.日志级别, BussinessDictCodeRds.批机能类型);
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskSupervisionLogDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RskSupervisionLogDto> page = rskSupervisionLogService.selectForPage(map);
        return page;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return rskSupervisionLogService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
