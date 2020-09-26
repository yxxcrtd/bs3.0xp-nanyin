/*
 * 文件名：SystemFeedback001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.debtsubmitinfo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.system.debtsubmitinfo.dto.DebtSubmitInfo001Dto;
import com.joyintech.tams.report.system.debtsubmitinfo.service.DebtSubmitInfoMService;

/**
 * 中债系统报送信息反馈
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年9月21日
 * @see DebtSubmitInfo001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/system/debtsubmitinfo/DebtSubmitInfo001")
@PageId("DebtSubmitInfo001")
public class DebtSubmitInfo001Controller extends BaseController{
    /**
     * 报送信息反馈Service
     */
    @Autowired
    private DebtSubmitInfoMService debtSubmitInfoMService;
    
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.报表名称);
    	return super.init(model);
    }
    /**
    *
    * 取得查询数据
    *
    * @return 存在于查询条件的数据
    */
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    @ResponseBody
    public Page<DebtSubmitInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return debtSubmitInfoMService.searchInfo(param);
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
        List<SelectOptionDto> resultList = new ArrayList<>();
        if(MapUtils.isNotEmpty(reportCodeMap)){
            for (String key : reportCodeMap.keySet()) {
                if (DictCodeListExt2.报表名称_业务明细信息登记.getValue().equals(key)
                		||DictCodeListExt2.报表名称_机构和私人银行产品发行登记.getValue().equals(key)
                		||DictCodeListExt2.报表名称_从业人员登记.getValue().equals(key)
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
}
