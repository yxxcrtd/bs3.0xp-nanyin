package com.joyintech.tams.report.submitmaintenance.salerinstinfo.controller;

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
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.dao.ChbSalerInstInfoMapper;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.dto.SalerInstInfoDto;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.service.SalerInstInfoService;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto.SalerInstInfoRegistDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("SalerInstInfo001")
@RequestMapping("/report/submitmaintenance/salerinstinfo/SalerInstInfo001")
public class SalerInstInfo001Controller extends BaseController {

    @Autowired
    private SalerInstInfoService salerInstInfoService;
    @Autowired
    private ChbSalerInstInfoMapper chbSalerInstInfoMapper;
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
        super.setSelectOptionToModel(model, DictCode.维护数据状态,DictCode.机构层级);
        return super.init(model);
    }

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping(value = "findPage")
    public Page<SalerInstInfoDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        Page<SalerInstInfoDto> pageList=salerInstInfoService.searchForPage(param);
        return pageList;
    }

    /**
     *check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid SalerInstInfoDto salerInstInfoDto,
                            BindingResult br) {
        return commonCheck(br, salerInstInfoDto.getOperateType());
    }

    /**
     * 删除check
     * @param SalerInstInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody SalerInstInfoDto SalerInstInfoDto) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        List<SalerInstInfoDto> salerOrgCodeList = SalerInstInfoDto.getSelectedItem();
        for(SalerInstInfoDto info : salerOrgCodeList) {
            String salerOrgCode = info.getSalerOrgCode();
            if (chbSalerInstInfoMapper.deleteCheck(salerOrgCode) > 0) {
                JoyinMessage ms = new JoyinMessage("该机构已维护过销售人员身份信息，请确认是否删除全部相关信息?", MessageType.确认消息);
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
        List<SalerInstInfoDto> listdto ;
        if (datas == null) {
            listdto = salerInstInfoService.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, SalerInstInfoDto.class);
        }
        File outFile = salerInstInfoService.makeFile(
                "/com/joyintech/tams/report/submitmaintenance/salerinstinfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "总部或分支机构信息" + SystemGlobalConstant.OPEN_PARENTHESIS
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
        return salerInstInfoService.checkConfirmMsgForImportData(fileName);
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
        return this.salerInstInfoService.importData(fileName);
    }

    /**
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
            ValidateGroupDelete.class}) SalerInstInfoDto dto, BindingResult br)
            throws Exception {
        if(!(dto.getSelectedItem().isEmpty())){
                salerInstInfoService.deleteByPrimaryKey(dto);
                return new AjaxResult("删除成功");
            }
        return new AjaxResult("删除失败");
    }

    /**
     * 总部或分支机构编码联想框
     */
    @ResponseBody
    @RequestMapping("/getSalerOrgCode")
    public List<SuggestDto> getSalerOrgCode()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerOrgCode", RequestUtil.getPageParam().get("salerOrgCode"));
        List<SuggestDto> temp = salerInstInfoService.getSalerOrgCode(map);
        return temp;
    }
}
