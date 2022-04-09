/*
 * 文件名：MarketCalendarInfoController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dto.ChbCalendarInfo001Dto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dto.ChbCalendarInfoDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.service.ChbCalendarInfo001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;


/**
 * 市场日历信息
 *
 * @author zhoubin
 * @version 2022年3月28日
 */

@Controller
@PageId("ChbCalendarInfo001")
@RequestMapping("/chb/businessdatamaintain/chbcalendarInfo/ChbCalendarInfo001")
public class ChbCalendarInfo001Controller extends BaseController {
    /**
     *
     */
    @Resource
    private ChbCalendarInfo001Service chbTradingCalendarInfo001Service;

    /**
     * 初始化页面信息
     * @return 处理结果
     */
    @Override
    public String init(Model model) {
        //初始化是否节假日下拉框
        this.setSelectOptionToModel(model, DictCode.是否);
        //数据的状态
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        //初始化日历类型下拉框
        List<SelectOptionDto> listCalendar = new LinkedList<>();
        Map<String, Object> map = new HashMap<>(1);
        map.put("calendarType", "");
        List<ChbCalendarInfoDto> list = chbTradingCalendarInfo001Service.selectCalendarInfo(map);
        SelectOptionDto dto = null;
        for (ChbCalendarInfoDto e : list) {
            dto = new SelectOptionDto();
            dto.setId(e.getCalendarId());
            dto.setText(e.getCalendarName());
            dto.setCol("");
            listCalendar.add(dto);
        }
        model.addAttribute("calendarType", JsonUtils.objectArray2Json(listCalendar.toArray()));
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", sysDate);
        model.addAttribute("_yes", 1);
        model.addAttribute("_no", 0);
        return super.init(model);
    }

    /**
     * 市场日历信息查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryList")
    public Page<ChbCalendarInfo001Dto> selectInfo() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return chbTradingCalendarInfo001Service.selectInfo(param);
    }

    /**
     * 将工作日设置为节假日
     * @param dto 实体类集合
     * @return 返回处理信息
     */
    @ResponseBody
    @RequestMapping("sethday")
    public AjaxResult sethday(@RequestBody List<ChbCalendarInfo001Dto> dto) {
        ProcessParameter<ChbCalendarInfo001Dto> processParam = new ProcessParameter<>();
        processParam.setPageId(this.getPageId());
        processParam.setOperationType(OperationType.MODIFY);
        Date sysDate = DateUtil.platDateFromCache();
        //判断日期是否小于平台日
        for (ChbCalendarInfo001Dto mci : dto) {
            if (mci.getCdate().before(sysDate)) {
                AjaxResult aj = new AjaxResult();
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage(getMessage("MST_INFO_E_0003")));
                aj.setErrorList(errorList);
                return aj;
            }
        }
        for (ChbCalendarInfo001Dto mci : dto) {
            chbTradingCalendarInfo001Service.doComplete(mci);
        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }

    /**
     * 将节假日设为工作日
     * @param dto 实体类集合
     * @return 返回处理信息
     */
    @ResponseBody
    @RequestMapping("setwday")
    public AjaxResult setWDay(@RequestBody List<ChbCalendarInfo001Dto> dto) {
        ProcessParameter<ChbCalendarInfo001Dto> processParam = new ProcessParameter<ChbCalendarInfo001Dto>();
        processParam.setPageId(this.getPageId());
        processParam.setOperationType(OperationType.MODIFY);
        Date sysDate = DateUtil.platDateFromCache();
        //判断日期是否小于平台日
        for (ChbCalendarInfo001Dto mci : dto) {
            if (mci.getCdate().before(sysDate)) {
                AjaxResult aj = new AjaxResult();
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage(getMessage("MST_INFO_E_0003")));
                aj.setErrorList(errorList);
                return aj;
            }
        }
        for (ChbCalendarInfo001Dto mci : dto) {
            chbTradingCalendarInfo001Service.doComplete(mci);
        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }


    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return chbTradingCalendarInfo001Service.checkConfirmMsgForImportData(fileName);
    }
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName) throws FileNotFoundException, ParseException {
        return chbTradingCalendarInfo001Service.importData(fileName);
    }

    /**
     * 导出
     * <功能详细描述>
     * @return 返回导出报错信息
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        return chbTradingCalendarInfo001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

}
