package com.joyintech.tams.report.system.chbmst.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.chbmst.service.ChbMstCounterParty001Service;
import com.joyintech.tams.report.system.chbmst.service.ChbMstCounterPartyService;
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
@PageId("ChbMstCounterParty001")
@RequestMapping("/svreport/system/chbmst/ChbMstCounterParty001")
public class ChbMstCounterParty001Controller extends BaseController {

    @Autowired
    private ChbMstCounterPartyService chbMstCounterPartyService;
    @Autowired
    private ChbMstCounterParty001Service chbMstCounterParty001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.客户二级类型, DictCode.维护数据状态,DictCode.客户一级类型);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ChbMstCounterParty> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return chbMstCounterPartyService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbMstCounterParty dto, BindingResult result) {
        String msg ="";
        List<ChbMstCounterParty> list=dto.getSelectedItem();
        List<ChbMstCounterParty> updateList=new ArrayList<>();
        List<ChbMstCounterParty> deleteList=new ArrayList<>();
        if(list.size()>0){
            for(ChbMstCounterParty chbMstCounterParty:list){
                if(StringUtil.isNotEmpty(chbMstCounterParty.getCounterId())){
                    updateList.add(chbMstCounterParty);
                }
                if(StringUtil.isNotEmpty(chbMstCounterParty.getUuid())){
                    deleteList.add(chbMstCounterParty);
                }
            }
            //获取产品代码、启用日期、信披费用类型不为空的数据进行更新操作
            if(updateList.size()>0){
                chbMstCounterPartyService.updateChbMstCounterParty(updateList);
            }
            //获取DATAUUID不为空的数据进行删除操作
            if(deleteList.size()>0){
                chbMstCounterPartyService.deleteChbMstCounterPartyErrorData(deleteList);
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
     * 交易对手下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCounterId")
    public List<SelectOptionDto> getCounterId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<ChbMstCounterParty> list = chbMstCounterPartyService.getCounterId(param);
        List<SelectOptionDto> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ChbMstCounterParty chbMstCounterParty : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(chbMstCounterParty.getCounterId());
                resultDto.setText(chbMstCounterParty.getCounterName());
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
        List<ChbMstCounterParty> dtoList = this.chbMstCounterPartyService.queryReportList(RequestUtil.getPageParam());
        dtoList.forEach(item -> {
            item.setCounterType2 (com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.客户二级类型, item.getCounterType2()));
        });
        File outFile = chbMstCounterParty001Service.makeFile(
                "/com/joyintech/tams/report/system/chbmst/conf/export_conf.xml", dtoList);
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
        return this.chbMstCounterPartyService.checkConfirmMsgForImportData(fileName);
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
        return this.chbMstCounterPartyService.importData(fileName);
    }
}
