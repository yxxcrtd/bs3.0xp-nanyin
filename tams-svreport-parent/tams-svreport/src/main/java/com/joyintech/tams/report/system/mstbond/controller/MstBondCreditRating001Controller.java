package com.joyintech.tams.report.system.mstbond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.mstbond.service.MstBondCreditRating001Service;
import com.joyintech.tams.report.system.mstbond.service.MstBondCreditRatingNewService;
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
@PageId("MstBondCreditRating001")
@RequestMapping("/svreport/system/mstbond/MstBondCreditRating001")
public class MstBondCreditRating001Controller extends BaseController {

    @Autowired
    private MstBondCreditRatingNewService mstBondCreditRatingNewService;
    @Autowired
    private MstBondCreditRating001Service mstBondCreditRating001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.评级类型, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<MstBondCreditRating> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return mstBondCreditRatingNewService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) MstBondCreditRating dto, BindingResult result) {
        String msg ="";
        List<MstBondCreditRating> list=dto.getSelectedItem();
        if(list.size()>0){
                mstBondCreditRatingNewService.updateMstBondCreditRating(list);
            msg="删除成功";
        }else{
            msg="请选择要删除的数据";
        }
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
    @RequestMapping("/getoCode")
    public List<SelectOptionDto> getoCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<ChbAssetInfo> list = mstBondCreditRatingNewService.getoCode(param);
        List<SelectOptionDto> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ChbAssetInfo chbAssetInfo : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(chbAssetInfo.getAssetCode());
                resultDto.setText(chbAssetInfo.getAssetName());
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
        List<MstBondCreditRating> dtoList = this.mstBondCreditRatingNewService.queryReportList(RequestUtil.getPageParam());
        /*Map<String, Object> param = RequestUtil.getPageParam();
        List<ChbAssetInfo> list = mstBondCreditRatingNewService.getoCode(param);
        Map<String,Object>map=new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ChbAssetInfo chbAssetInfo : list) {
                map.put(chbAssetInfo.getAssetCode(),chbAssetInfo.getAssetName());
            }
        }*/
        dtoList.forEach(item -> {
            item.setGradeOrgIdName(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.评级机构, item.getGradeOrgId()));
            item.setoTypeName(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.评级类型, item.getoType()));
            item.setGradeResultName(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.主体评级_中债, item.getGradeResult()));
        });
        File outFile = mstBondCreditRating001Service.makeFile(
                "/com/joyintech/tams/report/system/mstbond/conf/export_conf.xml", dtoList);
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
        return mstBondCreditRatingNewService.checkConfirmMsgForImportData(fileName);
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
        return this.mstBondCreditRatingNewService.importData(fileName);
    }
}
