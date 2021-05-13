package com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo001Service;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

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
    @RequestMapping(ACTION_DELETE)
    public AjaxResult deleteConfig(@RequestBody @Validated({
    	ValidateGroupDelete.class}) ComFxValuztionInfo002Dto dto, BindingResult br)
    	throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComFxValuztionInfo002Dto> processParameter = new ProcessParameter<ComFxValuztionInfo002Dto>();
        WorkFlowParameter<ComFxValuztionInfo002Dto> params = new WorkFlowParameter<ComFxValuztionInfo002Dto>();
        params.setPageId("ValFxValuztionInfo002");
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        params.setBusinessCode(item -> item.getFinprodId2());
        params.setBusinessType(DictCodeList.共通业务类型_外汇估值信息.getValue());
     // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity -> SpringContextUtil.getTextValue("ValFxValuztionInfo002.finprodId2") + "(" + entity.getFinprodId2() + ")" + "-"
            + SpringContextUtil.getTextValue("ValFxValuztionInfo002.datasourceType") + "("
            + entity.getDatasourceType() + ")"+ this.getMessage("ValFxValuztionInfo002.valDate") + "("
            + entity.getValDate() + ")"+ this.getMessage("ValFxValuztionInfo002.ccy") + "("
            + entity.getCcy() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = comFxValuztionInfo002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ComFxValuztionInfo002Dto[dto.getSelectedItem().size()]));
        
//        String msg = StringUtils.EMPTY;
//
//        ProcessParameter<ComFxValuztionInfo002Dto> processParameter = new ProcessParameter<>();
//        WorkFlowParameter<ComFxValuztionInfo002Dto> workFlowParameter = new WorkFlowParameter<>();
//
//        try {
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("finprodId2", dto.getFinprodId2());
//            map.put("datasourceType", dto.getDatasourceType());
//            map.put("valDate", dto.getValDate());
//            map.put("valmodelId", dto.getValmodelId());
//            dto = comFxValuztionInfo002Service.findByPrimaryKey(map);// dto 为回显值
//
//            workFlowParameter.setPageId("ValFxValuztionInfo002"); // 页面id
//            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
//            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
//            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_外汇估值信息.getValue());// 业务类型
//            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
//                    "外汇估值信息" , entity.getFinprodId2()));// 当前工作流备注
//            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
//
//            msg = comFxValuztionInfo002Service.start(processParameter, dto);
//        } catch (Exception e) {
//            this.getLogger().error("启动外汇估值信息流程失败：", e);
//            msg = e.getMessage();
//        }
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
     * 导入（必填项校验）
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return this.comFxValuztionInfo001Service.importData(fileName);
    }
    
    
    /**
     * 导入（重复数据获取）
     * 
     * @param fileName 导入的文件名
     * @return checkResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("duplicateCheck")
    public List<Integer> duplicateCheck(String fileName)
        throws Exception {
         List<List<String>> dataList = comFxValuztionInfo001Service.dataAnalysis(fileName);
         List<Integer> checkResult = new ArrayList<>();
         if (null != dataList && dataList.size() > 0) {
             // 导入文件重复数据检查
        	 checkResult = comFxValuztionInfo001Service.duplicateCheck(dataList);
         }
        return checkResult;
    }
    
    /**
     * 导入（数据保存）
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("importFileSave")
    public List<Integer> importFileSave(String fileName)
        throws Exception {
        List<List<String>> dataList = comFxValuztionInfo001Service.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
       	 checkResult = comFxValuztionInfo001Service.duplicateCheck(dataList);
       	 List<ComFxValuztionInfo001Dto> equityList = comFxValuztionInfo001Service.converToBean(dataList,checkResult);
       	 // 保存，已存在则更新
       	comFxValuztionInfo001Service.dealAfterCheck(equityList);
        }
        return checkResult;
    }
}
