package com.joyintech.tams.report.system.prorate.controller;

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
import java.util.*;


@Controller
@PageId("DisPrdFeeRate001")
@RequestMapping("/svreport/system/prorate/DisPrdFeeRate001")
public class DisPrdFeeRate001Controller extends BaseController {

    @Autowired
    private ProrateService prorateService;

    @Autowired
    private ProRateInfo001Service ProRateInfo001Service;

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
    public Page<DisRepCashYield> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
            return prorateService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisRepCashYield dto, BindingResult result) {
        String msg ="";
        List<DisRepCashYield> list=dto.getSelectedItem();
        List<DisRepCashYield> updateList=new ArrayList<>();
        List<DisRepCashYield> deleteList=new ArrayList<>();
        if(list.size()>0){
            for(DisRepCashYield disRepCashYield:list){
                if(StringUtil.isNotEmpty(disRepCashYield.getBusiId())
                        && StringUtil.isNotEmpty(disRepCashYield.getStartDate())
                        && StringUtil.isNotEmpty(disRepCashYield.getCashType2())){
                    updateList.add(disRepCashYield);
                }
                if(StringUtil.isNotEmpty(disRepCashYield.getDatauuid())){
                    deleteList.add(disRepCashYield);
                }
            }
            //获取产品代码、启用日期、信披费用类型不为空的数据进行更新操作
            if(updateList.size()>0){
                prorateService.updateDisRepCashYield(updateList);
            }
            //获取DATAUUID不为空的数据进行删除操作
            if(deleteList.size()>0){
                prorateService.deleteDisRepCashYieldErrorData(deleteList);
            }
            msg="删除成功";
        }else{
            msg="请选择要删除的数据";
        }
        //获取DATAUUID不为空的数据进行删除
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
        List<ChbPrdBaseinfo> list = prorateService.getProdinfo(param);
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
     *
     * 信披费用类型下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdFeeType")
    public List<SelectOptionDto> getPrdFeeType() {
        Map<String , Object> map =new HashMap<>();
        map.put("code", "DIS_PRD_FEE_TYPE_2");
        List<SysDataDict> list = prorateService.getSysDataDict(map);
        List<SelectOptionDto> resultList = new ArrayList<>();
        commenData(list,resultList);
        return resultList;
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
        List<SysDataDict> list = prorateService.getSysDataDict(map);
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
        List<ChbPrdBaseinfo> list = prorateService.getProdinfo(RequestUtil.getPageParam());
        Map<String,String> map=new HashMap<String,String>();
        if(null!=list && list.size()>0){
            for(ChbPrdBaseinfo chbPrdBaseinfo:list){
                map.put(chbPrdBaseinfo.getProdId(),chbPrdBaseinfo.getProdName());
            }
        }
        List<DisRepCashYield> dtoList = this.prorateService.queryReportList(RequestUtil.getPageParam());
        dtoList.forEach(item -> {
            item.setCashType2(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.信披费用类型, item.getCashType2()));
            item.setBusiId(map.get(item.getBusiId()));
        });
        File outFile = ProRateInfo001Service.makeFile(
                "/com/joyintech/tams/report/system/prorate/conf/export_conf.xml", dtoList);
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
        return this.prorateService.checkConfirmMsgForImportData(fileName);
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
        return this.prorateService.importData(fileName);

    }
}
