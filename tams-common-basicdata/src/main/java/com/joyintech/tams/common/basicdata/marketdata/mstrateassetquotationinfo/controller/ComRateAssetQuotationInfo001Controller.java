/*
 * 文件名：MstNetAssetQuotationInfo001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstrateassetquotationinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCode;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.mstrateassetquotationinfo.dto.ComRateAssetQuotationInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.mstrateassetquotationinfo.service.ComRateAssetQuotationInfo001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 利率型资产行情信息
 * @author lby
 * @version 1.0 2020年10月20日
 * @see ComRateAssetQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComRateAssetQuotationInfo001")
@RequestMapping("/common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo001")
public class ComRateAssetQuotationInfo001Controller extends BaseController{
    
    /**
     * 查询和修改controller
     */
    @Autowired
    private ComRateAssetQuotationInfo001Service mstRateAssetQuotationInfo001Service;
    /**
     * 流程组件
     */
    //@Autowired
    //private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 查询界面的初始化，日期默认显示平台日期
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        setSelectOptionToModel(model, DictCode.项目类计划类估值来源);

        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("valDate",DateUtil.format(sysDate));
        // 画面元素是否显示
     	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
     	pageElementConfigDto.setPageId(this.getPageId());
     	Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
     	model.addAttribute("isEleShow", isEleShow);
        
        return super.init(model);
    }
    
    /**
     * 查询操作
     *
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComRateAssetQuotationInfo001Dto> selectAllAsset()
        throws Exception {       
        return mstRateAssetQuotationInfo001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 资产代码的联想文本框事件
     *
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCode")
    public List<SuggestDto> getAssetCode()
            throws Exception {
        Map<String,Object> map = new HashMap<>();
        String param = super.getRequest().getParameter("param");
        map.put("finProdMarketId", param);
        map.put("PROFITTYPE_VALUE_02", DictCodeList.收益类型_收益型.getValue());
        // 估值不接入finprod_type2，故用估值的金融产品类型做判断
        map.put("TYPE_GUZHI_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // map.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        // map.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return mstRateAssetQuotationInfo001Service.getAssetCode(map);
    }
    /**
     * 资产名称的联想文本框事件
     *
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetName")
    public List<SuggestDto> getAssetName()
            throws Exception {
        Map<String,Object> map = new HashMap<>();
        String param = super.getRequest().getParameter("param");
        map.put("finProdName", param);
        map.put("PROFITTYPE_VALUE_02", DictCodeList.收益类型_收益型.getValue());
        map.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        map.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        map.put("TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        return mstRateAssetQuotationInfo001Service.getAssetName(map);
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ComRateAssetQuotationInfo001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        /*WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstRateAssetQuotationInfo002");
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getSelectedItem())){
                    dto.getSelectedItem().forEach(item->{
                        if (StringUtil.isNotEmpty(item.getImportDate()) && DateUtil.compareDate(DateUtil.format(DateUtil.parse(item.getImportDate())), DateUtil.format(sysDate)) != 0) {
//                            errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                        }
                        if (StringUtil.isNotEmpty(item.getFinprodId()) && StringUtil.isNotEmpty(item.getValDateStr())){
                            wflValidInputParameter.setBusinessDataPk1(item.getFinprodId());
                            wflValidInputParameter.setBusinessDataPk2(item.getValDateStr());
                            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                                errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"+" 估值日:"+item.getValDateStr()+" 产品代码:"+item.getFinProdMarketId()));
                            }
                        }
                    });
                }
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }*/
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ComRateAssetQuotationInfo001Dto dto, BindingResult br)
            throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ComRateAssetQuotationInfo001Dto> processParameter = new ProcessParameter<ComRateAssetQuotationInfo001Dto>();
        WorkFlowParameter<ComRateAssetQuotationInfo001Dto> params = new WorkFlowParameter<ComRateAssetQuotationInfo001Dto>();
        params.setPageId("ComRateAssetQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_项目类_计划类利率型行情信息.getValue());
        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
                entity -> "资产代码" + "(" + entity.getFinProdMarketId() + ")"
                        + "估值日期" + "(" + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstRateAssetQuotationInfo001Service.start(
                processParameter, dto.getSelectedItem().toArray(new ComRateAssetQuotationInfo001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = paraMap = this.getRequestMap();
        List<ComRateAssetQuotationInfo001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = mstRateAssetQuotationInfo001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, ComRateAssetQuotationInfo001Dto.class);
        }
        File outFile = mstRateAssetQuotationInfo001Service.makeFile(
                "/com/joyintech/tams/common/basicdata/marketdata/mstrateassetquotationinfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getSysDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
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
        return mstRateAssetQuotationInfo001Service.checkImportData(fileName);
    }

    /**
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param) {
        return mstRateAssetQuotationInfo001Service.importFile(param);
    }

}
