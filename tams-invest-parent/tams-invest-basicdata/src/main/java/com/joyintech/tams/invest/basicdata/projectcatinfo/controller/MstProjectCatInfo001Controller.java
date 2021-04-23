/*
 * 文件名：MstProjectCatInfo001Controller.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.215
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.tams.invest.basicdata.projectcatinfo.service.MstProjectCatInfo001Service;
import com.joyintech.tams.invest.basicdata.projectcatinfo.service.MstProjectCatInfo002Service;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目类资产估值行情信息
 *
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.215
 * @see MstProjectCatInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("MstProjectCatInfo001")
@RequestMapping("/invest/basicdata/projectcatinfo/MstProjectCatInfo001")
public class MstProjectCatInfo001Controller extends BaseController {

    /**
     * 项目类资产估值行情信息Service
     */
    @Autowired
    private MstProjectCatInfo001Service mstProjectCatInfo001Service;

    /**
     * 项目类资产估值行情信息新建修改画面Service
     */
    @Autowired
    private MstProjectCatInfo002Service mstProjectCatInfo002Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 初始化查询
     *
     * @return 查询结果
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstProjectCatInfoDto> findPage()
            throws Exception {
        Page<MstProjectCatInfoDto> page = mstProjectCatInfo001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
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
//        map.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
//        map.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        map.put("FINPROD_TYPE_TG_F37", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        List<SuggestDto> temp = mstProjectCatInfo001Service.getAssetCode(map);
        return temp;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) MstProjectCatInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 删除
     *
     * @param dto 画面参数
     * @param br  check结果
     * @return 删除执行结果
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) MstProjectCatInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstProjectCatInfoDto> processParameter = new ProcessParameter<MstProjectCatInfoDto>();
        WorkFlowParameter<MstProjectCatInfoDto> params = new WorkFlowParameter<MstProjectCatInfoDto>();
        params.setPageId("MstProjectCatInfo002");
        params.setElementId(dto.getElementId());

        // 业务编号
        params.setBusinessCode(item -> item.getFinProdMarketId() == null ? "-空白-" : item.getFinProdMarketId().toString());
        // 业务类型
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产估值行情信息.getValue());

        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
                entity -> this.getMessage("MstProjectCatInfo002.finprodId") + "("+entity.getFinProdMarketId()+ ")"
                        + this.getMessage("MstProjectCatInfo002.finProdName") + "(" + entity.getFinProdName() + ")"
                        + this.getMessage("MstProjectCatInfo002.valDate") + "(" + DateUtil.format(entity.getValDate()) + ")");

        processParameter.setWorkFlowParameter(params);

        String msg = mstProjectCatInfo002Service.start(processParameter,
                dto.getSelectedItem().toArray(new MstProjectCatInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出
     *
     * @return 导出文件
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return mstProjectCatInfo001Service.export(this.getPageId(), param);
    }



}
