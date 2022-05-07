package com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dto.ChbInvestTradeDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.service.ChbInvestTradeService;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.dto.ChbMstBondCreditRatingDto;
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
 * @author wubingbing
 * @date 2022-04-27 14:11
 */
@Controller
@PageId("CchbInvestTrade001")
@RequestMapping("/chb/businessdatamaintain/chbinvesttrade/chbinvesttrade001")
public class ChbInvestTrade001Controller extends BaseController {
    @Autowired
    private ChbInvestTradeService chbInvestTradeService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 产品名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProdName")
    public List<SelectOptionDto> getProdName()
    {
        return chbInvestTradeService.getProdName(RequestUtil.getPageParam());
    }
    /**
     * 产品登记编码
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRegCode")
    public List<SelectOptionDto> getRegCode()
    {
        return chbInvestTradeService.getRegCode(RequestUtil.getPageParam());
    }

    /**
     * 行内资产/负债编码
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAssetCode")
    public List<SelectOptionDto> getAssetCode()
    {
        return chbInvestTradeService.getAssetCode(RequestUtil.getPageParam());
    }

    /**
     * 交易对手
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public List<SelectOptionDto> getCustomerName()
    {
        return chbInvestTradeService.getCustomerName(RequestUtil.getPageParam());
    }
    /**
     * 检索页面列表
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChbInvestTradeDto> selectAll()
    {

        return chbInvestTradeService.selectForPage(RequestUtil.getPageParam());
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
    public AjaxResult delete(@RequestBody  ChbInvestTradeDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ChbInvestTradeDto> processParameter = new ProcessParameter<ChbInvestTradeDto>();
        WorkFlowParameter<ChbInvestTradeDto> params = new WorkFlowParameter<ChbInvestTradeDto>();
        params.setPageId("CchbInvestTrade002");
        params.setElementId("CchbInvestTrade001_btn_delete");
        params.setBusinessCode(item -> item.getTradeCode()+item.getProdId()+item.getRegistBankCode()+item.getAssetCode());
        params.setBusinessType(DictCodeList.共通业务类型_投资交易信息.getValue());
        params.setComment(entity -> this.getMessage("ChbInvestTrade001.tradeCode") + "("
                + entity.getTradeCode() + ")"
                + this.getMessage("ChbInvestTrade001.prodId")
                + "(" + entity.getProdId() + ")"
                + this.getMessage("ChbInvestTrade001.registBankCode")
                + "(" + entity.getRegistBankCode() + ")"
                + this.getMessage("ChbInvestTrade001.assetCode")
                + "(" + entity.getAssetCode() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = chbInvestTradeService.start(processParameter,dto.getSelectedItem().toArray(new ChbInvestTradeDto[dto.getSelectedItem().size()]));
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
        /** modify by yanya on 20210209 增加按照选择数据进行导出 start**/
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<ChbInvestTradeDto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, ChbInvestTradeDto.class);
        }
        else {
            list = chbInvestTradeService.selectAll(params);
        }
//        list.forEach(item->{
////            item.setGradeDateStr(DateUtil.format(item.getGradeDate(),"yyyy-MM-dd"));
//            item.setUpdateTimeStr(DateUtil.format(item.getUpdateTime(),"yyyy-MM-dd"));
//        });
        File outFile = chbInvestTradeService.makeFile(
                "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbinvesttrade/conf/export_conf.xml",
                list);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);

    }

}
