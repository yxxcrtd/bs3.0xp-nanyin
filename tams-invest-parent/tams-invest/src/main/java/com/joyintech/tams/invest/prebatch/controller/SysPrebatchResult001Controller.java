package com.joyintech.tams.invest.prebatch.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResult001Dto;
import com.joyintech.tams.invest.prebatch.service.SysPrebatchResult001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("SysPrebatchResult001")
@RequestMapping("/invest/prebatch/SysPrebatchResult001")
public class SysPrebatchResult001Controller extends BaseController {


    @Autowired
    private SysPrebatchResult001Service sysPrebatchResult001Service;

    @Override
    public String init(Model model){

        setSelectOptionToModel(model, DictCode.检查项, DictCode.批前检查_执行状态);

        model.addAttribute("checkDate", DateUtil.format(DateUtil.platDateFromCache())+" - "+DateUtil.format(DateUtil.platDateFromCache()) );

        return super.init(model);
    }

    /**
     * 查询 <功能详细描述>
     *
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SysPrebatchResult001Dto> findPage()
            throws Exception {
        Page<SysPrebatchResult001Dto> page = sysPrebatchResult001Service.findPage(
                RequestUtil.getPageParam());
        return page;
    }


    @RequestMapping(value = "executePreBatch")
    @ResponseBody
    public AjaxResult executePreBatch() {

        String msg  = sysPrebatchResult001Service.executePreBatch(BatchDictCodeList.批处理启动模式_手动.getValue());
        if(StringUtil.isNotEmpty(msg)){
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setMessage(msg, MessageType.错误消息);
            return ajaxResult;
        }else {
            return new AjaxResult("批前检查启动成功");
        }
    }



}
