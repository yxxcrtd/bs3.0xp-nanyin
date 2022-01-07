package com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.controller;

import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.dto.SalerRewPenaInfoDto;
import com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.service.SalerRewPenaInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("SalerRewPenaInfo001")
@RequestMapping("/report/submitmaintenance/salerrewpenainfo/SalerRewPenaInfo001")
public class SalerRewPenaInfo001Controller extends BaseController {

    @Autowired
    private SalerRewPenaInfoService salerRewPenaInfoService;
    /**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.记录分类,DictCode.奖惩单位);
        return super.init(model);
    }

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping(value = "findPage")
    public Page<SalerRewPenaInfoDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return salerRewPenaInfoService.searchForPage(param);
    }

    /**
     *check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid SalerRewPenaInfoDto salerRewPenaInfoDto,
                            BindingResult br) {
        return commonCheck(br, salerRewPenaInfoDto.getOperateType());
    }

    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<SalerRewPenaInfoDto> listdto ;
        if (datas == null) {
            listdto = salerRewPenaInfoService.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, SalerRewPenaInfoDto.class);
        }
        File outFile = salerRewPenaInfoService.makeFile(
                "/com/joyintech/tams/report/submitmaintenance/salerrewpenainfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "销售人员奖惩投诉记录" + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
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
        return salerRewPenaInfoService.checkConfirmMsgForImportData(fileName);
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName) {
        return salerRewPenaInfoService.importData(fileName);
    }

    /**
     *
     * 逻辑删除
     * <功能详细描述>
     * @param dto 删除参数
     * @param br 删除参数
     * @return 删除返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) SalerRewPenaInfoDto dto, BindingResult br)
            throws Exception {
        if(!(dto.getSelectedItem().isEmpty())){
                salerRewPenaInfoService.deleteByPrimaryKey(dto);
                return new AjaxResult("删除成功");
            }
        return new AjaxResult("删除失败");
    }

    /**
     * 内部编号联想框
     */
    @ResponseBody
    @RequestMapping("/getSalerInsNo")
    public List<SuggestDto> getSalerInsNo()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerInsNo", RequestUtil.getPageParam().get("salerInsNo"));
        List<SuggestDto> temp = salerRewPenaInfoService.getSalerInsNo(map);
        return temp;
    }
}
