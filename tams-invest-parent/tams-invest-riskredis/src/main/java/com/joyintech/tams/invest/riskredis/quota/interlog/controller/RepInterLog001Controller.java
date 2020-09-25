package com.joyintech.tams.invest.riskredis.quota.interlog.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.interlog.dto.RepInterLogDto;
import com.joyintech.tams.invest.riskredis.quota.interlog.service.RepInterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;



/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLog001Controller
 * @since 1.0
 */
@Controller
@PageId("RepInterLog001")
@RequestMapping("/invest/riskredis/quota/interlog/RepInterLog001")
public class RepInterLog001Controller extends BaseController {

    /**
     * 接口日志查询service
     */
    @Autowired
    private RepInterLogService repInterLogService;

    /**
     * 接口日志查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, BussinessDictCodeRds.处理状态,BussinessDictCodeRds.接口系统ID,BussinessDictCodeRds.消费系统);
        model.addAttribute("platDate", DateUtil.getCurrentDate()+" - "+DateUtil.getCurrentDate());
        return super.init(model);
   }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepInterLogDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RepInterLogDto> page = repInterLogService.selectForPage(map);
        return page;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return repInterLogService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
    
    /**
     * 查询按钮点击时的check
     *
     * @param dto dto
     * @return check结果
     * @see
     * @since 1.0
     */
	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RepInterLogDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
}
