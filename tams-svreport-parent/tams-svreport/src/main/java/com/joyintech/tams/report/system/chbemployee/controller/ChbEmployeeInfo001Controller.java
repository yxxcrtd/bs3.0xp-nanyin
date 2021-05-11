package com.joyintech.tams.report.system.chbemployee.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbemployee.service.ChbEmployeeInfo001Service;
import com.joyintech.tams.report.system.chbemployee.service.ChbEmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("ChbEmployeeInfo001")
@RequestMapping("/svreport/system/chbemployee/ChbEmployeeInfo001")
public class ChbEmployeeInfo001Controller extends BaseController {

    @Autowired
    private ChbEmployeeInfoService chbEmployeeInfoService;
    @Autowired
    private ChbEmployeeInfo001Service chbEmployeeInfo001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.从业人员类型, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ChbEmployeeInfo> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return chbEmployeeInfoService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbEmployeeInfo dto, BindingResult result) {
        String msg ="";
        List<ChbEmployeeInfo> list=dto.getSelectedItem();
        if(list.size()>0){
            chbEmployeeInfoService.updateChbEmployeeInfo(list);
            msg="删除成功";
        }else{
            msg="请选择要删除的数据";
        }
        return new AjaxResult(msg);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<ChbEmployeeInfo> dtoList = this.chbEmployeeInfoService.queryReportList(RequestUtil.getPageParam());
        dtoList.forEach(item -> {
            item.setEmployeeTypeStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.从业人员类型, item.getEmployeeType() ));
            item.setEmployeeSexStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.性别_中债, item.getEmployeeSex()));
            item.setBelongsBankStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.所属总行或分支行, item.getBelongsBank()));
            item.setBelongsPlaceStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.销售区域_中债, item.getBelongsPlace()));
            item.setEducationStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.学历, item.getEducation()));
            item.setAcademicDegreeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.学位, item.getAcademicDegree()));
            item.setProfessionalTitleStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.专业技术职称, item.getProfessionalTitle()));
            item.setFinProfessionalCrtfStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.理财专业证书, item.getFinProfessionalCrtf()));
            item.setRegBusiTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.登记业务分类, item.getRegBusiType()));
            item.setRegEmployeeTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.登记人员类别, item.getRegEmployeeType()));
        });
        File outFile = chbEmployeeInfo001Service.makeFile(
                "/com/joyintech/tams/report/system/chbemployee/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                        + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

}
