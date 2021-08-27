/*
 * 文件名：FinPlanAssetC001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.service.ValProfitprojectService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 非标债权及利率型资产
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see ValProfitproject001Controller
 * @since 1.0
 */
@Controller
@PageId("ValProfitproject001")
@RequestMapping("/valuation/basicdata/asset/plan/profitproject/ValProfitproject001")
public class ValProfitproject001Controller extends BaseController{
    @Autowired
    private ValProfitprojectService valProfitprojectService;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model,DictCode.计划类资产类型, DictCode.收益类型);
        model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValProfitprojectDto> findPage() throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<ValProfitprojectDto> page = valProfitprojectService.selectForPage(map);
        return page;
    }
    /**
     * 删除按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) ValProfitprojectDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) ValProfitprojectDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ValProfitprojectDto> processParameter = new ProcessParameter<ValProfitprojectDto>();
        WorkFlowParameter<ValProfitprojectDto> params = new WorkFlowParameter<ValProfitprojectDto>();
        params.setPageId("ValProfitproject002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_利率型_信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：资产代码 + "(" + 资产代码 + ")" + 资产名称 + "(" + 资产名称 + ")"
        params.setComment(entity -> getMessage("ValProfitproject001.finProdMarketId") + "(" + entity.getFinprodMarketId() + ")"+"-"
                +getMessage("ValProfitproject001.finProdName") + "(" + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = valProfitprojectService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ValProfitprojectDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
//    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
//    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
//        return finPlanAssetCService.export(this.getPageId(),param);

        Map<String, Object> pageParam = RequestUtil.getPageParam();
        pageParam.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        String datas = this.getParam("datas");
        List<ValProfitprojectDto> listdto;
        if (datas == null) {
            listdto = valProfitprojectService.selectForList(pageParam);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValProfitprojectDto.class);
        }

        File outFile = valProfitprojectService.makeFile(
                "/com/joyintech/tams/valuation/basicdata/asset/plan/profitproject/conf/export_conf.xml",
                listdto);
        return ExportUtil.export("非标债权及利率型资产"  + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);

    }
    /**
     * 获取所有资产id--联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetId")
    public List<SuggestDto> getAssetId(){
        String search = this.getParam("param")==null? StringUtil.EMPTY:this.getParam("param");
        String finprodType2 = this.getParam("finprodType2")==null?StringUtil.EMPTY:this.getParam("finprodType2");
        String profitType = this.getParam("profitType")==null?StringUtil.EMPTY:this.getParam("profitType");
        return valProfitprojectService.getAssetId(search,finprodType2,profitType);
    }

    /**
     * 发行人的联想下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetSelect")
    public Page<SelectOptionDto> getAssetSelect(){
        Map<String,Object> map = RequestUtil.getPageParam();
        //sql中参数不是search，页面控件传回是search，做转换 yjm 20190731
        map.put("param", map.get("search"));
        Page<SelectOptionDto> temp = valProfitprojectService.getAssetSelect(map);
        return temp;
    }
}
