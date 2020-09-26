/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo003Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo001Service;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo003Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券估值行情
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月17日
 * @see ValBondValuationInfo001
 * @since 1.0
 */
@Controller
@PageId("ValBondValuationInfo001")
@RequestMapping("/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo001")
public class ValBondValuationInfo001Controller extends BaseController {

    /**
     * 债券估值行情service
     */
    @Autowired
    private ValBondValuationInfo001Service valBondValuationInfo001Service;
    
    /**
     * 债券估值行情-新建、删除、修改Service
     */
    @Autowired
    private ValBondValuationInfo003Service valBondValuationInfo003Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.估值来源);
        return super.init(model);
    }

    /**
     * 查询
     * 
     * @return List<ValBondValuationInfo001Dto>
     * @exception Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("ValBondValuationInfo001Search")
    @ResponseBody
    public Page<ValBondValuationInfo001Dto> queryPageList()
        throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
//        ValBondValuationInfo001SearchDto dto = (ValBondValuationInfo001SearchDto)JsonUtils.map2entity(
//            params, ValBondValuationInfo001SearchDto.class);
//        dto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return valBondValuationInfo001Service.queryPageList(params);
    }

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws Exception ParseException
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap()
        throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String valueDateBegin = this.getParam("valueDateBegin");
        String valueDateEnd = this.getParam("valueDateEnd");
        if (StringUtil.isNotEmpty(valueDateBegin)) {
            paraMap.put("valueDateBegin", DateUtil.parseDate(valueDateBegin, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateBegin");
        }
        if (StringUtil.isNotEmpty(valueDateEnd)) {
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
        Map<String, Object> params = this.getRequestMap();
//        ValBondValuationInfo001SearchDto dto = (ValBondValuationInfo001SearchDto)JsonUtils.map2entity(
//            params, ValBondValuationInfo001SearchDto.class);
        params.put("finprodType",DictCodeListExt1.金融产品类型_债券.getValue());
        List<ValBondValuationInfo001Dto> listdto;
        if (datas == null) {
            listdto = valBondValuationInfo001Service.queryAll(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValBondValuationInfo001Dto.class);
        }
        File outFile = valBondValuationInfo001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/bondvaluationinfo/config/export_bondvaluation.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除前判断
     * 
     * @param dto AccountCurrentRate001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult check(@Valid @RequestBody ValBondValuationInfo001Dto dto, BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }
    
    /**
     * 删除
     * 
     * @param dto AccountCurrentRate001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult deleteList(@RequestBody ValBondValuationInfo001Dto dto, BindingResult result)
        throws Exception {
        String msg = getMessage("COMMON_I_0001");
        List<ValBondValuationInfo001Dto> listDto = dto.getListValBondValuationInfo001Dto();

        for (ValBondValuationInfo001Dto dto1 : listDto) {
            ProcessParameter<ValBondValuationInfo003Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ValBondValuationInfo003Dto> params = new WorkFlowParameter<>();
            ValBondValuationInfo003Dto dto3 = getMstBondValuationInfoByKey(dto1);
            
            // 业务代码
            params.setBusinessCode(dto3.getSecId() + dto3.getValueSource() + dto3.getValueDate());
            // 业务类型
            params.setBusinessType(DictCodeList.共通业务类型_债券估值行情.getValue());
            // 页面id
            params.setPageId("ValBondValuationInfo003");
            // 元素id
            params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            
          //设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("债券估值行情删除：",
                entity.getSecId(),
                entity.getValueSource(),
                entity.getValueDate()
                ));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);

            try {
                msg = valBondValuationInfo003Service.start(proParam, dto3);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }

        }
        return new AjaxResult(msg);
    }
    
    /**
     * 根据主键获取对应的债券估值行情
     *
     * @return 债券估值行情
     * @throws Exception 异常
     * @see
     */
    private ValBondValuationInfo003Dto getMstBondValuationInfoByKey(ValBondValuationInfo001Dto dto) {
        ValBondValuationInfo003Dto searchDto = new ValBondValuationInfo003Dto();
        searchDto.setSecId(dto.getSecId());
        searchDto.setValueSource(dto.getValueSource());
        searchDto.setValueDate(dto.getValueDate());
        return valBondValuationInfo003Service.getMstBondValuationInfoByKey(searchDto);
    }
}
