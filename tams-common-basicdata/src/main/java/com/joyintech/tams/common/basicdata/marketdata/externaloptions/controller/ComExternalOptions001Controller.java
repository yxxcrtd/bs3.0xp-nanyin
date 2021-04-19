package com.joyintech.tams.common.basicdata.marketdata.externaloptions.controller;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.service.ComExternalOptions001Service;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.service.ComExternalOptions002Service;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.dto.ComExternalOptions001Dto;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.dto.ComExternalOptions002Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-26 16:56
 */
@Controller
@PageId("ComExternalOptions001")
@RequestMapping("/common/basicdata/marketdata/externaloptions/ComExternalOptions001")
public class ComExternalOptions001Controller extends BaseController {

    @Autowired
    ComExternalOptions001Service comExternalOptions001Service;

    @Autowired
    ComExternalOptions002Service comExternalOptions002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ComExternalOptions001Dto> queryPageList(){

        return comExternalOptions001Service.queryPageList(RequestUtil.getPageParam());
    }


    @RequestMapping("/findTargetId")
    @ResponseBody
    public List<SuggestDto> findTargetId() {
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BENCHMARK_TYPE2",str);
        return comExternalOptions001Service.findTargetId(param);
    }

    @RequestMapping("/findFinprodId")
    @ResponseBody
    public List<SuggestDto> findFinprodId() {
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BENCHMARK_TYPE2",str);
        return comExternalOptions001Service.findFinprodId(param);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ComExternalOptions002Dto dto,
                            BindingResult bindingResult)
            throws Exception {
        String msg = StringUtils.EMPTY;
        String operateType = dto.getOperateType();

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 删除信息 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) ComExternalOptions002Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ComExternalOptions002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ComExternalOptions002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("finprodId2", dto.getFinprodId2());
            map.put("valDate", dto.getValDate());
           // map.put("valmodelId", dto.getValmodelId());
            dto = comExternalOptions002Service.findByPrimaryKey(map);// dto 为回显值

            workFlowParameter.setPageId("ComExternalOptions002"); // 页面id
            workFlowParameter.setElementId(this.getParam("elementId")); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场外期权.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "场外期权" , entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            msg = comExternalOptions002Service.start(processParameter, dto);
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
        List<ComExternalOptions001Dto> listdto ;

        if (datas == null) {
            listdto = comExternalOptions001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ComExternalOptions001Dto.class);
        }
        File outFile = comExternalOptions001Service.makeFile(
                "/com/joyintech/tams/common/basicdata/marketdata/externaloptions/conf/export_conf.xml",
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
        return comExternalOptions001Service.checkConfirmMsgForImportData(fileName);
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
        return this.comExternalOptions001Service.importData(fileName);

    }
}
