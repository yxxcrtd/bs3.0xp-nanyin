package com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.service.ValRateSwapValuationInfo001Service;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.service.ValRateSwapValuationInfo002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 10:36
 */
@Controller
@PageId("ValRateSwapValuationInfo001")
@RequestMapping("/valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo001")
public class ValRateSwapValuationInfo001Controller extends BaseController {


    @Autowired
    private ValRateSwapValuationInfo001Service valRateSwapValuationInfo001Service;

    @Autowired
    private ValRateSwapValuationInfo002Service valRateSwapValuationInfo002Service;

    @Override
    public String init(Model model) {
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
        param.put("finprodType", DictCodeListExt1.金融产品类型_利率互换.getValue());
        return valRateSwapValuationInfo001Service.getFinprodId2(param);
    }

    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValRateSwapValuationInfo001Dto> queryPageList(){
        return valRateSwapValuationInfo001Service.queryPageList(RequestUtil.getPageParam());
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ValRateSwapValuationInfo002Dto dto,
                            BindingResult bindingResult)
            throws Exception {
        String msg = StringUtils.EMPTY;
        String operateType = dto.getOperateType();

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 删除利率互换估值信息 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult deleteConfig(@RequestBody @Validated({ValidateGroupDelete.class}) ValRateSwapValuationInfo002Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ValRateSwapValuationInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValRateSwapValuationInfo002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("finprodId2", dto.getFinprodId2());
            map.put("datasourceType", dto.getDatasourceType());
            map.put("valDate", dto.getValDate());
            dto = valRateSwapValuationInfo002Service.findByPrimaryKey(map);// dto 为回显值

            workFlowParameter.setPageId("ValRateSwapValuationInfo002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_利率互换估值信息.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    "利率互换估值信息" , entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            msg = valRateSwapValuationInfo002Service.start(processParameter, dto);
        } catch (Exception e) {
            this.getLogger().error("启动利率互换估值信息流程失败：", e);
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
        List<ValRateSwapValuationInfo001Dto> listdto ;

        if (datas == null) {
            listdto = valRateSwapValuationInfo001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ValRateSwapValuationInfo001Dto.class);
        }
        File outFile = valRateSwapValuationInfo001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/rateswapvaluationinfo/conf/export_valrateswapvaluationinfo.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
