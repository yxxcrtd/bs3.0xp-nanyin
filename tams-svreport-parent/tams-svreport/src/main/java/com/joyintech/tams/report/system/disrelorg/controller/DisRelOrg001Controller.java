package com.joyintech.tams.report.system.disrelorg.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.disrelorg.service.DisRelOrgInfo001Service;
import com.joyintech.tams.report.system.disrelorg.service.DisRelOrgService;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

@Controller
@PageId("DisRelOrg001")
@RequestMapping("/svreport/system/disrelorg/DisRelOrg001")
public class DisRelOrg001Controller extends BaseController {

    @Autowired
    private DisRelOrgService disRelOrgService;

    @Autowired
    private DisRelOrgInfo001Service disRelOrgInfo001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<DisAssociatedInstitutions> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return disRelOrgService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisAssociatedInstitutions dto, BindingResult result) {
        String msg ="";
        List<DisAssociatedInstitutions> list=dto.getSelectedItem();
        //此处删除为更新数据状态操作,不做delete操作
        if(list.size()>0){
                disRelOrgService.updateDisAssociatedInstitutions(list);
            msg="删除成功";
        }else{
            msg="请选择要删除的数据";
        }
        return new AjaxResult(msg);
    }

    /**
     *
     * 数据状态下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getDataStatus")
    public List<SelectOptionDto> getDataStatus() {
        Map<String , Object> map =new HashMap<>();
        map.put("code", "MAINTAIN_STATUS");
        List<SysDataDict> list = disRelOrgService.getSysDataDict(map);
        List<SelectOptionDto> resultList = new ArrayList<>();
        commenData(list,resultList);
        return resultList;
    }

    public void commenData(List<SysDataDict> list,List<SelectOptionDto> resultList){
        if (CollectionUtils.isNotEmpty(list)) {
            for (SysDataDict sysDataDict : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(sysDataDict.getItemCode());
                resultDto.setText(sysDataDict.getItemValue());
                resultList.add(resultDto);
            }
        }
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<DisAssociatedInstitutions> disAssociatedInstitutionslist = this.disRelOrgService.queryReportList(RequestUtil.getPageParam());

        File outFile = disRelOrgInfo001Service.makeFile(
                "/com/joyintech/tams/report/system/disrelorg/conf/export_conf.xml", disAssociatedInstitutionslist);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                        + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
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
        return this.disRelOrgService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.disRelOrgService.importData(fileName);

    }

}
