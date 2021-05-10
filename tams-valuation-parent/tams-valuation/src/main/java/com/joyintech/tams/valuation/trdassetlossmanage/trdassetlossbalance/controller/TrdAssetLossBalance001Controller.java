package com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.service.TrdAssetLossBalance001Service;
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

@Controller
@PageId("TrdAssetLossBalance001")
@RequestMapping("/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance001")
public class TrdAssetLossBalance001Controller extends BaseController {

    @Autowired
    private TrdAssetLossBalance001Service trdAssetLossBalance001Service;

    @Override
    public String init(Model model) {
        String sysDate = DateUtil.getSysDate();
        String rangeDate = sysDate + " - " + sysDate;
        model.addAttribute("rangeDate",rangeDate);
        return super.init(model);
    }

    /**
     * check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdAssetLossBalance001Dto dto, BindingResult br) throws Exception{
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAssetLossBalance001Dto> findPage() throws Exception {
        Page<TrdAssetLossBalance001Dto> page= trdAssetLossBalance001Service.queryPageList(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 查询资产代码
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public Page<SelectOptionDto> getFinprodId(){
        Map<String, Object> param =RequestUtil.getPageParam();
        Page<SelectOptionDto> finprodId = trdAssetLossBalance001Service.getFinprodId(param);
        return finprodId;
    }

    /**
     * 查询账套代码
     */
    @ResponseBody
    @RequestMapping("getBooksetId")
    public Page<SelectOptionDto> getBooksetId(){
        Map<String, Object> param =RequestUtil.getPageParam();
        Page<SelectOptionDto> getBooksetId = trdAssetLossBalance001Service.getBooksetId(param);
        return getBooksetId;
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
    public ResponseEntity<byte[]> export()throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        String F01 = DictCodeListExt1.金融产品类型_债券.getValue();
        String F07 = DictCodeListExt1.金融产品类型_存款.getValue();
        String F17 = DictCodeListExt1.金融产品类型_计划类资产.getValue();
        String F21 = DictCodeListExt1.金融产品类型_同业借款.getValue();
        paraMap.put("F01",F01);
        paraMap.put("F07",F07);
        paraMap.put("F17",F17);
        paraMap.put("F21",F21);
        List<TrdAssetLossBalance001Dto> listdto;
        if (datas == null) {
            listdto = trdAssetLossBalance001Service.getAllList(paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, TrdAssetLossBalance001Dto.class);
        }
        listdto.forEach(
                item->{
                    item.setBookDateStr(DateUtil.format(item.getBookDate()));
                }
        );
        File outFile = trdAssetLossBalance001Service.makeFile(
                "/com/joyintech/tams/valuation/trdassetlossmanage/trdassetlossbalance/conf/export_conf.xml", listdto);
        String name = this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}

