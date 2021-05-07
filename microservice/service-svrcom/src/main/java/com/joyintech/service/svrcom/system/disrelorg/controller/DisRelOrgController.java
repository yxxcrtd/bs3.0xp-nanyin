package com.joyintech.service.svrcom.system.disrelorg.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.disrelorg.service.DisRelOrgInfo001Service;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Disrelorg")
public class DisRelOrgController {
    @Autowired
    private DisRelOrgInfo001Service disRelOrgInfo001Service;
    
    @PostMapping("findPage/{page}/{length}")
    public Page<DisAssociatedInstitutions> search(@RequestBody Map<String,Object> paramMap) {
        return disRelOrgInfo001Service.searchForPage(paramMap);
    }
    @PostMapping("updateDisAssociatedInstitutions")
    public void updateDisAssociatedInstitutions(@RequestBody List<DisAssociatedInstitutions> dtoList) {
        disRelOrgInfo001Service.updateDisAssociatedInstitutions(dtoList);
        return ;
    }
    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = disRelOrgInfo001Service.getSysDataDict(param);
        return list;
    }

    @PostMapping("queryReportList")
    public List<DisAssociatedInstitutions> queryReportList(@RequestBody Map<String, Object> param) {
        List<DisAssociatedInstitutions> list = disRelOrgInfo001Service.queryReportList(param);
        return list;
    }

    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return disRelOrgInfo001Service.checkConfirmMsgForImportData(dataList);
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
        return this.disRelOrgInfo001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    @PostMapping("selectOne")
    public List<DisAssociatedInstitutions> selectOne(@RequestBody String relorgId ) {
        List<DisAssociatedInstitutions> list = disRelOrgInfo001Service.selectOne(relorgId);
        return list;
    }
    @PostMapping("insertOne")
    public void insertOne(@RequestBody DisAssociatedInstitutions disAssociatedInstitutions){
        disRelOrgInfo001Service.insertOne(disAssociatedInstitutions);
        return ;
    }
    @PostMapping("updateOne")
    public void updateOne(@RequestBody DisAssociatedInstitutions disAssociatedInstitutions){
        disRelOrgInfo001Service.updateOne(disAssociatedInstitutions);
        return ;
    }
}
