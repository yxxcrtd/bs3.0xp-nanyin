/*
 * 文件名：PerformanceBenchmark001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
/**
 * 
 */
package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCode;
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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service.ComponentInfoService;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service.MstPerformanceBenchMarkService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 复合基准管理的基本信息管理
 * 显示业绩基准信息的基本页面
 * @author zhangjun
 * @version 1.0 2018年8月23日
 * @see MstPerformanceBenchMark001Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("MstPerformanceBenchMark001")
@RequestMapping("/invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark001")
public class MstPerformanceBenchMark001Controller extends BaseController {
    /**
     * 复合基准管理service注入
     */
    @Autowired
    private MstPerformanceBenchMarkService mstPerformanceBenchMarkService;
    /**
     * 基准成分service注入
     */
    @Autowired
    private ComponentInfoService componentInfoService;
    /**
     * 页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, new BussinessDictCode[] {BussinessDictCode.基准类型 });
        return super.init(model);
    }

    /**
     * 查询 <功能详细描述>
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstPerformanceBenchMarkDto> selectAll()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        //多选基准类型的值在js中就已经是数组了，直接取出来
        String performBenchmarkindexType = (String)param.get("indexType");
        if (StringUtil.isNotEmpty(performBenchmarkindexType)) {
            param.put("performBenchmarkindexType",
                    StringUtil.splitToList(GlobalConstant.COMMA, performBenchmarkindexType));
        }
        Page<MstPerformanceBenchMarkDto> page = mstPerformanceBenchMarkService.selectForPage(param);
        return page;
    }

    /**
     * 基准名称联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getIndexName")
    public List<SuggestDto> getIndexName()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, String> map = new HashMap<String, String>();
        map.put("param", param);
        List<SuggestDto> temp = mstPerformanceBenchMarkService.getIndexName(map);
        return temp;
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstPerformanceBenchMarkDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto MstPerformanceBenchMarkDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstPerformanceBenchMarkDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //循环查出第二个表格中的数据也就是List<Component> 塞到dto里面用来传值到代办
        for (int i = 0; i < dto.getSelectedItem().size(); i++ ) {
            List<ComponentInfoDto> componentInfo = componentInfoService.selectComponentInfoByPrimaryKey(
                dto.getSelectedItem().get(i).getIndexId());
            dto.getSelectedItem().get(i).setComponentInfo(componentInfo);
        }
        ProcessParameter<MstPerformanceBenchMarkDto> processParameter = new ProcessParameter<MstPerformanceBenchMarkDto>();
        WorkFlowParameter<MstPerformanceBenchMarkDto> params = new WorkFlowParameter<MstPerformanceBenchMarkDto>();
        params.setPageId("MstPerformanceBenchMark002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getIndexId());
        params.setBusinessType(DictCodeList.共通业务类型_复合基准.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity ->SpringContextUtil.getTextValue("MstPerformanceBenchMark002.indexName") + "(" + entity.getIndexName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstPerformanceBenchMarkService.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstPerformanceBenchMarkDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 文件导出 <功能详细描述>
     * 
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstPerformanceBenchMarkService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
