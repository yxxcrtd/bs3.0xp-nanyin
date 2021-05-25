package com.joyintech.service.svrcom.system.investmentdeal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import com.joyintech.tams.report.system.investmentdeal.service.InvestmentDealInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/InvestmentDealInfo")
public class InvestmentDealInfoController {

    @Autowired
    private InvestmentDealInfo001Service investmentDealInfo001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<InvestmentDealInfoDto> search(@RequestBody Map<String,Object> paramMap) {
        return investmentDealInfo001Service.searchForPage(paramMap);
    }

    @PostMapping("queryReportList")
    public List<InvestmentDealInfoDto> queryReportList(@RequestBody Map<String, Object> param) {
        List<InvestmentDealInfoDto> list = investmentDealInfo001Service.queryReportList(param);
        return list;
    }
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return investmentDealInfo001Service.checkConfirmMsgForImportData(dataList);
    }
    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.investmentDealInfo001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    @PostMapping("selectOneDate")
    public InvestmentDealInfoDto selectOneDate(@RequestBody Map<String, Object> param) {
        InvestmentDealInfoDto dto = investmentDealInfo001Service.selectOneDate(param);
        return dto;
    }
    @PostMapping("selectInvestmentDealInfoErrordata")
    public ChbInvestTradeErrordataWithBLOBs selectInvestmentDealInfoErrordata(String datauuid) {
        return investmentDealInfo001Service.selectInvestmentDealInfoErrordata(datauuid);
    }

    @PostMapping("getProdId")
    public List<SelectOptionDto> getProdId(@RequestBody Map<String, Object> param) {
        List<SelectOptionDto> list = investmentDealInfo001Service.getProdId(param);
        return list;
    }

    @PostMapping("save")
    public void save(@RequestBody InvestmentDealInfoDto dto)  {
        dto.setDataSource(DictCodeListExt2.数据来源_系统新建.getValue());
         investmentDealInfo001Service.save(dto);
    }

    @PostMapping("getTradeCounter")
    public List<SelectOptionDto> getTradeCounter(@RequestBody Map<String, Object> param) {
        List<SelectOptionDto> list = investmentDealInfo001Service.getTradeCounter(param);
        return list;
    }

    @PostMapping("delData")
    public void delData(@RequestBody InvestmentDealInfoDto dto)  {
         investmentDealInfo001Service.delData(dto);
    }

}
