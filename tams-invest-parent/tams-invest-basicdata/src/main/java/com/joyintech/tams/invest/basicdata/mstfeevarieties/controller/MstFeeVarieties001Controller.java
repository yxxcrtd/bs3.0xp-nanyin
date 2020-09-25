/*
 * 文件名：NetWorthMarketInfoController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstfeevarieties.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.service.MstFeeVarietiesService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 费用品种信息管理 提供净值型资产行情信息的管理
 * 
 * @author huwei
 * @version 1.0 2018年7月24日
 * @see MstFeeVarieties001Controller
 * @since 1.0
 */
@Controller
@PageId("MstFeeVarieties001")
@RequestMapping("/invest/basicdata/mstfeevarieties/MstFeeVarieties001")
public class MstFeeVarieties001Controller extends BaseController {
    /**
     * service对象
     */
    @Autowired
    private MstFeeVarietiesService mstFeeVarietiesService;

    /**
     * 初始化页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 下拉框初始化
        setSelectOptionToModel(model, DictCode.费用品种类型);
        return super.init(model);
    }

    /**
     * 查询费用名称
     * 
     * @return list 结果集
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("/getFeeName")
    public List<SuggestDto> getFeeNameOption()
        throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        // 获取前台输入的值
        param.put("feeName", super.getRequest().getParameter("param"));
        List<SuggestDto> list = mstFeeVarietiesService.getFeeNameOption(param);
        return list;// 返回费用名称
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstFeeVarietiesDto> selectAll()
        throws Exception {
        return mstFeeVarietiesService.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
        ValidateGroupDelete.class}) MstFeeVarietiesDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstFeeVarietiesDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstFeeVarietiesDto> processParameter = new ProcessParameter<MstFeeVarietiesDto>();
        WorkFlowParameter<MstFeeVarietiesDto> params = new WorkFlowParameter<MstFeeVarietiesDto>();
        params.setPageId("MstFeeVarieties002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFeeId());
        params.setBusinessType(DictCodeList.共通业务类型_费用品种管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：费用品种代码 + "(" + 费用品种代码 + ")" + "-" + "费用品种名称" + "-" + "费用品种名称"
        params.setComment(entity -> "费用品种代码" + "(" + entity.getFeeId() + ")" + "-" + "费用品种名称" + "("
                                    + entity.getFeeName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstFeeVarietiesService.start(processParameter,
            dto.getSelectedItem().toArray(new MstFeeVarietiesDto[dto.getSelectedItem().size()]));
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
        return mstFeeVarietiesService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
