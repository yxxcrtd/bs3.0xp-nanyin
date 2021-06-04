/*
 * 文件名：MarketInformationController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzehui
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComBondMarketInfo001")
@RequestMapping("/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {

    /**
     *每日债券行情service
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ComBondMarketInfo001Dto dto = comBondMarketInfo001Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&
                StringUtil.isNotEmpty(pageParam.get("secId")) && StringUtil.isNotEmpty(pageParam.get("valueDate"))) {
            //查询数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("secId",pageParam.get("secId"));
            map.put("valueDate",pageParam.get("valueDate"));
            ComBondMarketInfo001Dto dto = null;
            try {
                dto = comBondMarketInfo001Service.queryDtoByKey(map).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ComBondMarketInfo001Dto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }
    /**
     * 页面查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ComBondMarketInfo001Dto> queryPageList() throws Exception{
        Map<String, Object> params= RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
//        ValBondMarketInfo001SearchDto dto = (ValBondMarketInfo001SearchDto)JsonUtils.map2entity(params, ValBondMarketInfo001SearchDto.class);
        return comBondMarketInfo001Service.queryPageList(params);
    }
    
    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String valueDateBegin = this.getParam("valueDateBegin");
        String valueDateEnd = this.getParam("valueDateEnd");
        if(StringUtil.isNotEmpty(valueDateBegin)){
            paraMap.put("valueDateBegin", DateUtil.parseDate(valueDateBegin, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateBegin");
        }
        if(StringUtil.isNotEmpty(valueDateEnd)){
            paraMap.put("valueDateEnd", DateUtil.parseDate(valueDateEnd, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateEnd");
        }
        return paraMap;
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
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        List<ComBondMarketInfo001Dto> listdto ;
        if (datas == null) {
            listdto = comBondMarketInfo001Service.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ComBondMarketInfo001Dto.class);
        }
        File outFile = comBondMarketInfo001Service.makeFile(
            "/com/joyintech/tams/common/basicdata/marketdata/bondmarketinfo/config/export_conf.xml",
            listdto);
        //修改导出文件名称
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }


    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) ComBondMarketInfo001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComBondMarketInfo001Dto> processParameter = new ProcessParameter<ComBondMarketInfo001Dto>();
        WorkFlowParameter<ComBondMarketInfo001Dto> params = new WorkFlowParameter<ComBondMarketInfo001Dto>();
        params.setPageId("ValBondMarketInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_每日债券行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity ->"债券代码(" + entity.getSecId() + ")" + "-" + "估值日期(" + entity.getValueDate()+ ")");
        processParameter.setWorkFlowParameter(params);
        String msg = comBondMarketInfo001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new ComBondMarketInfo001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

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
        return this.comBondMarketInfo001Service.importData(fileName);
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
         List<List<String>> dataList = comBondMarketInfo001Service.dataAnalysis(fileName);
         List<Integer> checkResult = new ArrayList<>();
         if (null != dataList && dataList.size() > 0) {
             // 导入文件重复数据检查
        	 checkResult = comBondMarketInfo001Service.duplicateCheck(dataList);
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
        List<List<String>> dataList = comBondMarketInfo001Service.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
       	 checkResult = comBondMarketInfo001Service.duplicateCheck(dataList);
       	 List<ComBondMarketInfo001Dto> equityList = comBondMarketInfo001Service.converToBean(dataList,checkResult);
       	 // 保存，已存在则更新
       	comBondMarketInfo001Service.dealAfterCheck(equityList);
        }
        return checkResult;
    }

}
