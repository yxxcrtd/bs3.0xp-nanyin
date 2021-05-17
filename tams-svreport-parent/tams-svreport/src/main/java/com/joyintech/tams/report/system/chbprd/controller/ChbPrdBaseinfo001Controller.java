package com.joyintech.tams.report.system.chbprd.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfo001Service;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfoService;
import com.joyintech.tams.report.system.prorate.service.ProRateInfo001Service;
import com.joyintech.tams.report.system.prorate.service.ProrateService;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
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
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@PageId("ChbPrdBaseinfo001")
@RequestMapping("/svreport/system/chbprd/ChbPrdBaseinfo001")
public class ChbPrdBaseinfo001Controller extends BaseController {

    @Autowired
    private ChbPrdBaseinfoService chbPrdBaseinfoService;

    @Autowired
    private ChbPrdBaseinfo001Service chbPrdBaseinfo001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ChbPrdBaseinfo> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
            return chbPrdBaseinfoService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbPrdBaseinfo dto, BindingResult result) {
        String msg ="";
        List<ChbPrdBaseinfo> list=dto.getSelectedItem();
        List<ChbPrdBaseinfo> updateList=new ArrayList<>();
        List<ChbPrdBaseinfo> deleteList=new ArrayList<>();
        if(list.size()>0){
            for(ChbPrdBaseinfo chbPrdBaseinfo:list){
                if(StringUtil.isNotEmpty(chbPrdBaseinfo.getProdId())){
                    updateList.add(chbPrdBaseinfo);
                }
                if(StringUtil.isNotEmpty(chbPrdBaseinfo.getDataUuid())){
                    deleteList.add(chbPrdBaseinfo);
                }
            }
            //获取产品代码不为空的数据进行更新操作
            if(updateList.size()>0){
                chbPrdBaseinfoService.updateChbPrdBaseinfo(updateList);
            }
            //获取DATAUUID不为空的数据进行删除操作
            if(deleteList.size()>0){
                chbPrdBaseinfoService.deleteChbPrdBaseinfoErrorData(deleteList);
            }
            msg="删除成功";
        }else{
            msg="请选择要删除的数据";
        }
        return new AjaxResult(msg);
    }

    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProdID")
    public List<SelectOptionDto> getProdID() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<ChbPrdBaseinfo> list = chbPrdBaseinfoService.getProdinfo(param);
        List<SelectOptionDto> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ChbPrdBaseinfo chbPrdBaseinfo : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(chbPrdBaseinfo.getProdId());
                resultDto.setText(chbPrdBaseinfo.getProdName());
                resultList.add(resultDto);
            }
        }
        return resultList;
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        //SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        List<ChbPrdBaseinfo> dtoList = this.chbPrdBaseinfoService.queryReportList(RequestUtil.getPageParam());
        dtoList.forEach(item -> {
           /* item.setRaiseVdateStr(dfs.format(item.getRaiseVdateStr()));
            item.setRaiseEnddateStr(dfs.format(item.getRaiseEnddateStr()));
            item.setVdateStr(dfs.format(item.getVdateStr()));
            item.setMdateStr(dfs.format(item.getMdateStr()));
            item.setFirstOpenDayStr(dfs.format(item.getFirstOpenDayStr()));*/
            item.setManageTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.管理方式_发行登记 , item.getManageType()));
            item.setIsConstracteName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsConstracte()));
            item.setOpenModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.开放模式_中债 , item.getOpenMode()));
            item.setOpenCircleName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.规律开放周期 , item.getOpenCircle()));
            item.setHolidayOnFlagName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getHolidayOnFlag()));
            item.setOpenBussName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.开放期业务 , item.getOpenBuss()));
            item.setProdtypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品类型_中债 , item.getProdtype()));
            item.setBasicName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.计息基础 , item.getBasic()));
            item.setFiduciaryDutyName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品受托职责_中债 , item.getFiduciaryDuty()));
            item.setCustomerTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.人行客户类型 , item.getCustomerType()));
            item.setIsTransferName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsTransfer()));
            item.setIsFullyCompliantName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsFullyCompliant()));
            item.setIsYllccpName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsYllccp()));
            item.setIsSyqzccpName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsSyqzccp()));
            item.setIssuingStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.发行状态 , item.getIssuingStatus()));
        });
        File outFile = chbPrdBaseinfo001Service.makeFile(
                "/com/joyintech/tams/report/system/chbprd/conf/export_conf.xml", dtoList);
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
        return this.chbPrdBaseinfoService.checkConfirmMsgForImportData(fileName);
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
        return this.chbPrdBaseinfoService.importData(fileName);

    }
}
