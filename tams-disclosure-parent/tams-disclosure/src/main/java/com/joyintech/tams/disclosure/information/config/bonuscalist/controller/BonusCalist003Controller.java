package com.joyintech.tams.disclosure.information.config.bonuscalist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.BonusReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.BonusReportDto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.service.BonusCalist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@PageId("BonusCalist003")
@Controller
@RequestMapping("/disclosure/information/config/bonuscalist/BonusCalist003")
public class BonusCalist003Controller extends BaseController {
    @Autowired
    private BonusCalist001Service bonusCalist001Service;

    @Autowired
    private BonusReportMapper bonusReportMapper;

    @Override
    public String init(Model model){
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody BonusCalist001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        List<BonusCalist001Dto> paramList =new ArrayList<>();
        List<String> prodIds = StringUtil.splitToList(GlobalConstant.COMMA, dto.getProdId());
//        paramList.add(dto);
        for (String prodId : prodIds){
            dto.setProdId(prodId);
            paramList.add(dto);
            List<BonusCalist001Dto> list = bonusCalist001Service.selectBonusInfoByKey(paramList);
            if (list!=null && list.size()>0) {
                AjaxResult ajax=new AjaxResult();
                ajax.setMessage("计算数据已存在，是否覆盖！", MessageType.确认消息);
                return ajax;
            }
            HashMap map = new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("reportDate",dto.getProfitBaseDate());
            List<BonusReportDto> listReport = bonusReportMapper.selectBonusReport(map);
            for (BonusReportDto report : listReport) {
                if ((report.geteDate()==null)|| report.getAssetsValue()==null||report.getCapital()==null||
                        report.getSettDate()==null|| report.getAchievementBasisProp()==null||
                        report.getExcessAchievementProp()==null || report.getAchievementBasis()==null){
                    AjaxResult ajax=new AjaxResult();
                    ajax.setStatuCode(500);
                    ajax.setMessage("子产品代码"+prodId+"相关数据缺失!",MessageType.错误消息);
                    return ajax;
                }
            }
        }

            return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("calculateData")
    public int calculateData(@RequestBody BonusCalist001Dto dto){
       return bonusCalist001Service.calculateData(dto);
    }
}
