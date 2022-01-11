package com.joyintech.tams.report.submitmaintenance.salerideninfo.controller;

import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
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
import com.joyintech.tams.report.submitmaintenance.salerideninfo.service.SalerIdenInfoService;
import com.joyintech.tams.report.submitmaintenance.salerideninfo.dao.ChbSalerIdenInfoMapper;
import com.joyintech.tams.report.submitmaintenance.salerideninfo.dto.SalerIdenInfoDto;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("SalerIdenInfo001")
@RequestMapping("/report/submitmaintenance/salerideninfo/SalerIdenInfo001")
public class SalerIdenInfo001Controller extends BaseController {

    @Autowired
    private SalerIdenInfoService salerIdenInfoService;
    @Autowired
    private ChbSalerIdenInfoMapper chbSalerIdenInfoMapper;
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
        super.setSelectOptionToModel(model, DictCode.维护数据状态,DictCode.销售人员证件类型,DictCode.性别_中债);
        return super.init(model);
    }

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping(value = "findPage")
    public Page<SalerIdenInfoDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return salerIdenInfoService.searchForPage(param);
    }

    /**
     *check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid SalerIdenInfoDto SalerIdenInfoDto,
                            BindingResult br) {
        return commonCheck(br, SalerIdenInfoDto.getOperateType());
    }

    /**
     * 删除check
     * @param SalerIdenInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody SalerIdenInfoDto SalerIdenInfoDto) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        List<SalerIdenInfoDto> salerInsNoList = SalerIdenInfoDto.getSelectedItem();
        for(SalerIdenInfoDto info : salerInsNoList) {
            String salerInsNo = info.getSalerInsNo();
            if (chbSalerIdenInfoMapper.deleteCheck(salerInsNo) > 0) {
                JoyinMessage ms = new JoyinMessage("选择销售人员已维护过奖惩投诉记录信息，请确认是否删除全部相关信息?", MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
                break;
            }
        }
        return wr;
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
        List<SalerIdenInfoDto> listdto ;
        if (datas == null) {
            listdto = salerIdenInfoService.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, SalerIdenInfoDto.class);
        }
        File outFile = salerIdenInfoService.makeFile(
                "/com/joyintech/tams/report/submitmaintenance/salerideninfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "销售人员身份信息" + SystemGlobalConstant.OPEN_PARENTHESIS
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
        return salerIdenInfoService.checkConfirmMsgForImportData(fileName);
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
        return this.salerIdenInfoService.importData(fileName);
    }

    /**
     * 导入销售人员登记编码之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportCodeData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportCodeData(String fileName)  {
        return salerIdenInfoService.checkConfirmMsgForImportCodeData(fileName);
    }


    /**
     * 导入销售人员登记编码
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importCode")
    public List<ExcelValidDto> importCode(String fileName) {
        return this.salerIdenInfoService.importCode(fileName);
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
            ValidateGroupDelete.class}) SalerIdenInfoDto dto, BindingResult br)
            throws Exception {
        if(!(dto.getSelectedItem().isEmpty())){
                salerIdenInfoService.deleteByPrimaryKey(dto);
                return new AjaxResult("删除成功");
            }
        return new AjaxResult("删除失败");
    }

    /**
     * 理财销售人员姓名联想框
     */
    @ResponseBody
    @RequestMapping("/getSalerName")
    public List<SuggestDto> getSalerName()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerName", RequestUtil.getPageParam().get("salerName"));
        List<SuggestDto> temp = salerIdenInfoService.getSalerName(map);
        return temp;
    }
}
