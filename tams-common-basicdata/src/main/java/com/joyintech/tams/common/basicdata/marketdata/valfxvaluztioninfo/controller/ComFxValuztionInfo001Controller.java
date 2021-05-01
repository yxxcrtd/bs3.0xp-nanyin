package com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo001Service;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.lang3.StringUtils;
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

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 10:36
 */
@Controller
@PageId("ComFxValuztionInfo001")
@RequestMapping("/common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001")
public class ComFxValuztionInfo001Controller extends BaseController {


    @Autowired
    private ComFxValuztionInfo001Service comFxValuztionInfo001Service;

    @Autowired
    private ComFxValuztionInfo002Service comFxValuztionInfo002Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.估值来源);
        super.setSelectOptionToModel(model, DictCode.金融产品类型);
        return super.init(model);
    }

    /**
     * 交易编号获取
     *
     * @return 返回的数据
     */
    @RequestMapping("/getFinprodId2")
    @ResponseBody
    public List<SelectOptionDto> getFinprodId2(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return comFxValuztionInfo001Service.getFinprodId2(param);
    }
    
    
    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ComFxValuztionInfo001Dto> queryPageList(){
        return comFxValuztionInfo001Service.queryPageList(RequestUtil.getPageParam());
    }

    /**
     * 删除 check
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ComFxValuztionInfo002Dto dto,
                            BindingResult bindingResult)
            throws Exception {
        String msg = StringUtils.EMPTY;
        String operateType = dto.getOperateType();

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 删除外汇估值信息 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) ComFxValuztionInfo002Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ComFxValuztionInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ComFxValuztionInfo002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("finprodId2", dto.getFinprodId2());
            map.put("datasourceType", dto.getDatasourceType());
            map.put("valDate", dto.getValDate());
            map.put("valmodelId", dto.getValmodelId());
            dto = comFxValuztionInfo002Service.findByPrimaryKey(map);// dto 为回显值

            workFlowParameter.setPageId("ComFxValuztionInfo002"); // 页面id
            workFlowParameter.setElementId(this.getParam("elementId")); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_外汇估值信息.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "外汇估值信息" , entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            msg = comFxValuztionInfo002Service.start(processParameter, dto);
        } catch (Exception e) {
            this.getLogger().error("启动外汇估值信息流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
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
        List<ComFxValuztionInfo001Dto> listdto ;

        if (datas == null) {
            listdto = comFxValuztionInfo001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ComFxValuztionInfo001Dto.class);
        }
        File outFile = comFxValuztionInfo001Service.makeFile(
                "/com/joyintech/tams/common/basicdata/marketdata/valfxvaluztioninfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
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
        return comFxValuztionInfo001Service.checkConfirmMsgForImportData(fileName);
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
        return this.comFxValuztionInfo001Service.importData(fileName);

    }
}
