package com.joyintech.tams.disclosure.information.config.repprodinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-05-09 11:14
 */
@Controller
@PageId("ProdInfo001")
@RequestMapping("/disclosure/information/config/repprodinfo/ProdInfo001")
public class ProdInfo001Controller extends BaseController {
    @Autowired
    private ProdInfoService prodInfoService;


    @SuppressWarnings("rawtypes")
    @Autowired
    private CommonExportService commonExportService;


    /*    */

    /**
     * 产品名称
     *
     * @return
     *//*
    @ResponseBody
    @RequestMapping("/getProdIdAndProdName")
    public List<SuggestDto> getProdIdAndProdName()
    {
        return prodInfoService.getProdIdAndProdName(RequestUtil.getPageParam());
    }*/
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.信披_募集方式);
        this.setSelectOptionToModel(model, DictCode.是或否);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品运作方式);
        this.setSelectOptionToModel(model, DictCode.维护数据状态);

        return super.init(model);
    }

    /**
     * 检索页面列表
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ProdInfoDto> selectAll() {

        return prodInfoService.findByPage(RequestUtil.getPageParam());
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPprodIds")
    public Page<SelectOptionDto> queryPprodIds() {
        return prodInfoService.queryPprodIds(RequestUtil.getPageParam());
    }

    /**
     * 分页查询产品下拉框
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("queryPprodName")
    @ResponseBody
    public Page<SelectOptionDto> queryPprodName() {
        Map<String, Object> param= RequestUtil.getPageParam();
        return  prodInfoService.queryPprodName(param);
    }
    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ProdInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ProdInfoDto> processParameter = new ProcessParameter<ProdInfoDto>();
        WorkFlowParameter<ProdInfoDto> params = new WorkFlowParameter<ProdInfoDto>();
        params.setPageId("ProdInfo002");
        params.setElementId("ProdInfo001_btn_delete");
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeList.共通业务类型_产品基本信息.getValue());
        params.setComment(entity -> this.getMessage("产品基本信息") + "("
                + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        dto.getSelectedItem().forEach(e -> {
            e.setCreateUser(CommonUtil.getUserName());
            e.setCreateTime(DateUtil.now());
            e.setUpdateUser(CommonUtil.getUserName());
            e.setUpdateTime(DateUtil.now());
        });
        String msg = prodInfoService.start(processParameter, dto.getSelectedItem().toArray(new ProdInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }

    /**
     * 修改、删除相关check
     *
     * @param br 验证信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProdInfoDto dto, BindingResult br) {
        if (br.hasErrors()) {
            AjaxResult ar = new AjaxResult(br);
            if (StringUtil.equals(dto.getOperateType(), "issueStatus")) {
                List<ValidMessage> errorList = ar.getErrorList();
                // 流程中数据判断
                for (ValidMessage validMessage : errorList) {
                    if (StringUtil.indexOf(validMessage.getMessage(), this.getMessage("COMMON_E_0012").substring(0,
                            this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
                        // 由于PK值的设定，此处特殊处理
                        validMessage.setMessage(StringUtil.splitToList(",", validMessage.getMessage()).get(0));
                    }
                }
            }
            return ar;
        } else {
            return new AjaxResult();
        }
    }


    /**
     * 导出 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */

    @SuppressWarnings("unchecked")
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws IOException {
        // Map<String, Object> paraMap = RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        List<ProdInfoDto> listdto = null;
        if (datas == null) {
            listdto = prodInfoService.findAll(RequestUtil.getPageParam());
        } else {
            listdto = JsonUtils.json2List(datas, ProdInfoDto.class);
        }
        File outFile = commonExportService.makeFile(
                "/com/joyintech/tams/disclosure/information/config/repprodinfo/export_prodinfo001.xml",
                listdto);
        String exportFileName = this.getPageId();
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if (null != page) {
            exportFileName = page.getName()
                    + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                    + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                    + FileNameUtil.getFileExtName(outFile);
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return prodInfoService.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return prodInfoService.importData(fileName);
    }

}
