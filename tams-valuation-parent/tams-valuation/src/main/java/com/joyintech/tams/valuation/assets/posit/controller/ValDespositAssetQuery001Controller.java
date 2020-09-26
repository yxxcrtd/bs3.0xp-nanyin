package com.joyintech.tams.valuation.assets.posit.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery001Dto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.valuation.assets.posit.service.ValDespositAssetQuery001Service;
import com.joyintech.tams.valuation.assets.posit.service.ValDespositAssetQuery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类资产
 * 
 * @author HuangJian
 * @version 1.0 2018年10月30日
 * @see ValDespositAssetQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValDespositAssetQuery001")
@RequestMapping("/valuation/assets/posit/ValDespositAssetQuery001")
public class ValDespositAssetQuery001Controller extends BaseController {

    /**
     * DespositAssetQuery003Service
     */
    @Autowired
    private ValDespositAssetQuery001Service valDespositAssetQuery001Service;

    /**
     * DespositAssetQuery003Service
     */
    @Autowired
    private ValDespositAssetQuery002Service valDespositAssetQuery002Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.利率类型);
        return super.init(model);
    }

    /**
     * 根据条件分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValDespositAssetQuery001Dto> findPage()
        throws Exception {
        return valDespositAssetQuery001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 资产下拉框获取
     * 
     * @param request HttpServletRequest
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    /*
     * public List<SelectOptionDto> getFinprodId(HttpServletRequest request) { return
     * valDespositAssetQuery001Service.queryValFinproId( RequestUtil.getPageParam()); }
     */
    public List<SelectOptionDto> getFinprodId(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valDespositAssetQuery001Service.queryValFinproId(param);
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
        List<ValDespositAssetQuery001Dto> dtoList;
        if (datas == null) {
            dtoList = valDespositAssetQuery001Service.findPageout(RequestUtil.getPageParam());
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValDespositAssetQuery001Dto.class);
        }
        File outFile = valDespositAssetQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/posit/config/export_DepositQuery.xml", dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除
     * 
     * @param dto ValDespositAssetQuery001Dto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody ValDespositAssetQuery002Dto dto) {
        String finprodId = dto.getFinprodId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());// modify by dongjun on 20181201
        ValDespositAssetQuery002Dto ValDespositAssetQuery002Dto = valDespositAssetQuery002Service.fingByfinprodId(
            map);
        dto.setBasicRate(ValDespositAssetQuery002Dto.getBasicRate());
        dto.setBasis(ValDespositAssetQuery002Dto.getBasis());
        dto.setBlnArea(ValDespositAssetQuery002Dto.getBlnArea());
        dto.setCalcRule(ValDespositAssetQuery002Dto.getCalcRule());
        dto.setCalcType(ValDespositAssetQuery002Dto.getCalcType());
        dto.setCalendarId(ValDespositAssetQuery002Dto.getCalendarId());
        dto.setCapiIncomeFeature(ValDespositAssetQuery002Dto.getCapiIncomeFeature());
        dto.setCcy(ValDespositAssetQuery002Dto.getCcy());
        dto.setFinprodName(ValDespositAssetQuery002Dto.getFinprodName());
        dto.setFinprodType(ValDespositAssetQuery002Dto.getFinprodType());
        dto.setFirstPayDate(ValDespositAssetQuery002Dto.getFirstPayDate());
        dto.setIssueAmt(ValDespositAssetQuery002Dto.getIssueAmt());
        dto.setIssuer(ValDespositAssetQuery002Dto.getIssuer());
        dto.setMdate(ValDespositAssetQuery002Dto.getMdate());
        dto.setPayCycle(ValDespositAssetQuery002Dto.getPayCycle());
        dto.setRemark(ValDespositAssetQuery002Dto.getRemark());
        dto.setTable1(valDespositAssetQuery002Service.getTable1(finprodId));
        dto.setTable2(valDespositAssetQuery002Service.getTable2(map));// modify by dongjun on
                                                                      // 20181201
        dto.setTable2(valDespositAssetQuery002Service.getTable3(map));// modify by dongjun on
                                                                      // 20181201
        //【一体化修改】：begin========================================================================= yangjian 2018/1/3 
        //【一体化修改】：针对估值一体化[55：现金流支付规则表：营业准则改成截止日营业日准则、支付日营业日准则]
        //dto.setTdayRule(ValDespositAssetQuery002Dto.getTdayRule());
        dto.setEndTdayRule(ValDespositAssetQuery002Dto.getEndTdayRule());
        dto.setPayTdayRule(ValDespositAssetQuery002Dto.getPayTdayRule());
        //【一体化修改】：end========================================================================= yangjian 2018/1/3 
        
        dto.setTerm(ValDespositAssetQuery002Dto.getTerm());
        dto.setTradeMarket(ValDespositAssetQuery002Dto.getTradeMarket());
        dto.setType1(ValDespositAssetQuery002Dto.getType1());
        dto.setType2(ValDespositAssetQuery002Dto.getType2());
        dto.setVdate(ValDespositAssetQuery002Dto.getVdate());
        ProcessParameter<ValDespositAssetQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValDespositAssetQuery002Dto> params = new WorkFlowParameter<>();
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产信息.getValue());
        params.setPageId("ValDespositAssetQuery002");
        // 元素id
        params.setElementId(dto.getElementId());
        // 业务代码
        params.setBusinessCode(ValDespositAssetQuery002Dto.getFinprodId());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("存款类资产删除：", dto.getFinprodId(),
            dto.getFinprodType()));
        // 塞入流程参数
        proParam.setWorkFlowParameter(params);
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valDespositAssetQuery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);

        // String msg = StringUtils.EMPTY;
        // int count = valDespositAssetQuery001Service.delete(finprodId);
        // if (count > 0) {
        // msg = "删除成功";
        // return new AjaxResult(msg);
        // }
        // msg = "删除失败";
        // return new AjaxResult(msg, MessageType.错误消息);
    }

    /**
     * 删除前判断
     * 
     * @param dto ValDespositAssetQuery001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult check(@RequestBody ValDespositAssetQuery001Dto dto, BindingResult result)
        throws Exception {
        String finprodId = dto.getFinprodId();
        if (StringUtils.isNotEmpty(finprodId)) {// 删除基金类资产验证
            boolean flag = valDespositAssetQuery001Service.checkFinprodId(finprodId);
            if (flag) {
                return new AjaxResult("存在资产交易，不能删除！", MessageType.错误消息);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        ValDespositAssetQuery002Dto ValDespositAssetQuery002Dto = valDespositAssetQuery002Service.fingByfinprodId(
            map);
        if (valDespositAssetQuery002Service.checkBusinessDataInFlow(ValDespositAssetQuery002Dto)) {

            return new AjaxResult("流程中，不能删除！", MessageType.错误消息);
        }

        return new AjaxResult();
    }

    /**
     * 修改前判断
     * 
     * @param dto ValDespositAssetQuery001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("updateCheck")
    public AjaxResult updateCheck(@RequestBody ValDespositAssetQuery001Dto dto,
                                  BindingResult result)
        throws Exception {
        String finprodId = dto.getFinprodId();

        if (StringUtils.isNotEmpty(finprodId)) {// 修改基金类资产验证
            boolean flag = valDespositAssetQuery001Service.checkFinprodId(finprodId);
            if (flag) {
                return new AjaxResult("已存在交易数据，是否确定修改资产定义信息？", MessageType.确认消息);
            }
        }
        return new AjaxResult();
    }
}
