/*
 * 文件名：CheckReport001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitexamination.checkreport.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbReportCheck;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReportMService;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 报送前检查画面
 *
 * @author zuoyinyi
 * @version 1.0 2017年9月28日
 * @see CheckReport001Controller
 * @since 1.0
 */
@Controller
@PageId("CheckReport001")
@RequestMapping("/svreport/submitexamination/checkreport/CheckReport001")
public class CheckReport001Controller extends BaseController{
    /**
     * 报送前检查service
     */
    @Autowired
    private CheckReportMService checkReportMService;
    /**
     * 画面的初始化的函数
     * @param model model
     * @return String
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String myOrgCode=CommonUtil.getOrgCode();
        if(StringUtil.isNotEmpty(myOrgCode)){
            model.addAttribute("MY_AGENCY_CODE",myOrgCode);
        }
        super.setSelectOptionToModel(model,DictCode.报表名称,DictCode.检查结果);
        model.addAttribute("REPORT_CODE_RESULT", JsonUtils.objectArray2Json(getReportCode().getRoot().toArray()));
        Map<String, Object> map = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(map.get("checkResult"))) {
            model.addAttribute("checkResult", map.get("checkResult").toString().trim());
            model.addAttribute("searchDataStart", map.get("searchDataStart"));
            model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
            model.addAttribute("isFromMain", "01");
            if(StringUtil.isNotEmpty(map.get("repCode"))){
                model.addAttribute("repCode", map.get("repCode").toString().trim());
            }
        }else{
            model.addAttribute("CHECK_RESULT_NG", DictCodeListExt2.检查结果_检查不通过.getValue());
            model.addAttribute("title01", this.getMessage("MENU_003003001"));
            model.addAttribute("title02", this.getMessage("InvestorsHoldInfoRegist001"));
            model.addAttribute("repCode01", DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
            model.addAttribute("repCode02", DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
            Calendar calendar = Calendar.getInstance();
            Date reportStartDate = calendar.getTime();
            Date reportEndDate = calendar.getTime();
            model.addAttribute("searchDataStart", DateUtil.format(reportStartDate));
            model.addAttribute("searchDataEnd", DateUtil.format(reportEndDate));
        }
        return super.init(model);
    }
    /**
     * 查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTable")
    public Page<CheckReport001Dto> searchTable(){
        return checkReportMService.searchTable(RequestUtil.getPageParam());
    }

    /**
     * 功能:获取报表code
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月16日
     * @return
     * List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("/getReportCode")
    public Page<SelectOptionDto> getReportCode(){
        Map<String, String> reportCodeMap = SvrDataUtil.getSysDictDataMap(DictCode.报表名称);
        reportCodeMap.putAll(SvrDataUtil.getSysDictDataMap(DictCode.人行报表名称));
        List<SelectOptionDto> resultList = new ArrayList<>();
        if(MapUtils.isNotEmpty(reportCodeMap)){
            for (String key : reportCodeMap.keySet()) {
                if (DictCodeListExt2.报表名称_业务明细信息登记.getValue().equals(key)
                        ||DictCodeListExt2.报表名称_机构和私人银行产品发行登记.getValue().equals(key)
                        ||DictCodeListExt2.报表名称_从业人员登记.getValue().equals(key)
                        ||DictCodeListExt2.报表名称_人行月度统计报表.getValue().equals(key)
                        ||DictCodeListExt2.报表名称_分类总量信息登记.getValue().equals(key)){
                    continue;
                }
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(key);
                resultDto.setText(reportCodeMap.get(key));
                resultList.add(resultDto);
            }
        }

        Page<SelectOptionDto> page = new Page();
        page.setRoot(resultList);
        return page;
    }


    /**
     *     导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @param parameter 页面参数
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        return checkReportMService.export(this.getPageId(), RequestUtil.getPageParam());
    }
}
