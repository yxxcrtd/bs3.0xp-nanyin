package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.service.SvrProdFeeDealInterfService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 产品交易信息维护
 * @author shengning
 *
 */

@Controller
@PageId("SvrProdFeeDealInterf001")
@RequestMapping("/disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001")
public class SvrProdFeeDealInterf001Controller extends BaseController {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SvrProdFeeDealInterfService svrProdFeeDealInterfService;

    /**
     * 初始化页面信息
     *
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.产品费用交易费用类型,DictCode.维护数据状态);
        return super.init(model);
    }
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public Page<SelectOptionDto> queryProdIdInfo() {
        return svrProdFeeDealInterfService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     * 产品交易信息维护查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryList")
    public Page<SvrProdFeeDealInterfDto> selectInfo() {
        Map<String,Object> param = RequestUtil.getPageParam();
        return svrProdFeeDealInterfService.selectInfo(param);
    }

//    /**
//     * 产品交易信息维护导出查询
//     *
//     * @return 处理结果
//     * @throws Exception 异常
//     */
//    public List<SvrProdFeeDealInterfDto> selectForExport(Map<String, Object> param) {
//        return svrProdFeeDealInterfService.selectForExport(param);
//    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) SvrProdFeeDealInterfDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupDelete.class}) SvrProdFeeDealInterfDto dto, BindingResult br)
            throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setUpdateUser(LoginUserUtil.getUserName());
        ProcessParameter<SvrProdFeeDealInterfDto> processParameter = new ProcessParameter<SvrProdFeeDealInterfDto>();
        WorkFlowParameter<SvrProdFeeDealInterfDto> params = new WorkFlowParameter<SvrProdFeeDealInterfDto>();
        params.setPageId("SvrProdFeeDealInterf002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品费用交易信息维护.getValue());
        params.setComment(entity ->  "产品主代码" + "(" + entity.getProdId() + ")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        for(SvrProdFeeDealInterfDto dd :dto.getSelectedItem()){
            dd.setCreateUser(LoginUserUtil.getUserName());
            dd.setCreateTime(new Date());
        }
        String msg =svrProdFeeDealInterfService.start(processParameter, dto.getSelectedItem().toArray(new SvrProdFeeDealInterfDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     *
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
//        Map<String, Object> param = RequestUtil.getParameters(getRequest());
//        if(param.containsKey("selectData")) {
//            String keyList = param.get("selectData").toString();
//            try {
//                List<SvrProdFeeDealInterfDto> investList = JsonUtils.json2List(keyList,
//                        SvrProdFeeDealInterfDto.class);
//                param.put("investList", investList);
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//        List<SvrProdFeeDealInterfDto> dtoList = this.svrProdFeeDealInterfService.selectForExport(RequestUtil.getPageParam());
//        File outFile = svrProdFeeDealInterfService.makeFile(
//                "/com/joyintech/tams/disclosure/information/config/prodfeedealInterf/conf/export_conf.xml", dtoList);
//
//        return ExportUtil.export(
//                "产品费用交易信息维护" + GlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
//                        + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
        return svrProdFeeDealInterfService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
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
        return svrProdFeeDealInterfService.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param)  {
        return svrProdFeeDealInterfService.importFile(param);
    }
}
