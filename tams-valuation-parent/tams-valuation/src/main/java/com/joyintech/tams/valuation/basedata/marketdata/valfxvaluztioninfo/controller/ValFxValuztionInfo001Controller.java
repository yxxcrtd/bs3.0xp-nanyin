package com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.tams.constant.DictCode;
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
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.service.ValFxValuztionInfo001Service;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.service.ValFxValuztionInfo002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
@PageId("ValFxValuztionInfo001")
@RequestMapping("/valuation/basedata/marketdata/valfxvaluztioninfo/ValFxValuztionInfo001")
public class ValFxValuztionInfo001Controller extends BaseController {


    @Autowired
    private ValFxValuztionInfo001Service valFxValuztionInfo001Service;

    @Autowired
    private ValFxValuztionInfo002Service valFxValuztionInfo002Service;

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
        return valFxValuztionInfo001Service.getFinprodId2(param);
    }
    
    
    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValFxValuztionInfo001Dto> queryPageList(){
        return valFxValuztionInfo001Service.queryPageList(RequestUtil.getPageParam());
    }

    /**
     * 删除 check
     *
     * @param dto
     * @param result
     * @param operateType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ValFxValuztionInfo002Dto dto,
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
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) ValFxValuztionInfo002Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ValFxValuztionInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValFxValuztionInfo002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("finprodId2", dto.getFinprodId2());
            map.put("datasourceType", dto.getDatasourceType());
            map.put("valDate", dto.getValDate());
            map.put("valmodelId", dto.getValmodelId());
            dto = valFxValuztionInfo002Service.findByPrimaryKey(map);// dto 为回显值

            workFlowParameter.setPageId("ValFxValuztionInfo002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_外汇估值信息.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "外汇估值信息" , entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            msg = valFxValuztionInfo002Service.start(processParameter, dto);
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
        List<ValFxValuztionInfo001Dto> listdto ;

        if (datas == null) {
            listdto = valFxValuztionInfo001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ValFxValuztionInfo001Dto.class);
        }
        File outFile = valFxValuztionInfo001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valfxvaluztioninfo/conf/export_valfxvaluztioninfo.xml",
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
        return valFxValuztionInfo001Service.checkConfirmMsgForImportData(fileName);
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
        return this.valFxValuztionInfo001Service.importData(fileName);

    }
}
