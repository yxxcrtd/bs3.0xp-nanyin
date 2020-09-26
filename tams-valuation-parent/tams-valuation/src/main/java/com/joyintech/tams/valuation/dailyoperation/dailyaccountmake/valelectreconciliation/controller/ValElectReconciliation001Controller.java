package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service.ValElectReconciliation001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztXmlMessageDto;
import com.joyintech.tams.valuation.common.sztinterf.service.SztInterfCommonService;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;

/**
 * 电子对账Controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation001Controller
 * @since 1.0
 */
@Controller
@PageId("ValElectReconciliation001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation001")
public class ValElectReconciliation001Controller extends BaseController {

    @Autowired
    private ValElectReconciliation001Service valElectReconciliation001Service;

    @Autowired
    private BaseCommonService baseCommonService;

    @Autowired
    private ValInertfStartUp001Service valInertfStartUp001Service;

    @Autowired
    private SztInterfCommonService sztInterfCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.深圳通对账状态);
        this.setSelectOptionToModel(model, DictCode.深圳通报文状态);
        this.setSelectOptionToModel(model, DictCode.深圳通对账类型);
        String sysDate = baseCommonService.getSysDateString();
        if (sysDate == null || "".equals(sysDate)) {
            sysDate = DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);

        return super.init(model);
    }

    /**
     * 电子对账页面查询方法 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValElectReconciliation001Dto> findPage()
        throws Exception {
        Page<ValElectReconciliation001Dto> page = valElectReconciliation001Service.findPage(
            RequestUtil.getPageParam());
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValElectReconciliation001Dto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, "");
    }

    /**
     * 调用接口发送对账请求 <功能详细描述>
     * 
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("RunAs")
    public String sztRunAs()
        throws IOException {
        JsonObject json = new JsonObject();
        String datas = this.getParam("datas");
        List<ValElectReconciliation001Dto> arrList = JsonUtils.json2List(datas,
            ValElectReconciliation001Dto.class);
        String bprodIds = "";
        String vprodIds = "";
        String cdate = "";
        for (ValElectReconciliation001Dto arr : arrList) {
            cdate = arr.getValDate();
            if (DictCodeListExt1.深圳通对账类型_估值表.getValue().equals(arr.getInterType())) {
                vprodIds = (vprodIds == "") ? arr.getProdId() : (vprodIds + "," + arr.getProdId());
            }
            else {
                bprodIds = (bprodIds == "") ? arr.getProdId() : (bprodIds + "," + arr.getProdId());
            }
        }
        if (vprodIds != "") {
            String interType = DictCodeListExt1.深圳通接口类型_估值表.getValue();
            String url = valInertfStartUp001Service.getSztUrl(null);
            Map<String, String> urlParams = valElectReconciliation001Service.getParm(vprodIds,
                cdate, interType);
            valInertfStartUp001Service.runInterf(url, urlParams);
        }
        if (bprodIds != "") {
            String interType = DictCodeListExt1.深圳通接口类型_余额表.getValue();
            String url = valInertfStartUp001Service.getSztUrl(null);
            Map<String, String> urlParams = valElectReconciliation001Service.getParm(bprodIds,
                cdate, interType);
            valInertfStartUp001Service.runInterf(url, urlParams);
        }
        return json.toString();
    }

    /**
     * 获取产品代码
     * 
     * @param
     * @return Page
     * @throws Exception
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return valElectReconciliation001Service.getFinprodId(map);
    }

    /**
     * 调整与取消调整
     * 
     * @param result
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("adjustValue")
    public AjaxResult check()
        throws Exception {
        String msg = StringUtil.EMPTY;
        String datas = this.getParam("datas");
        String remark = this.getParam("remark");
        String operateType = this.getParam("operateType");
        List<ValElectReconciliation001Dto> listdto = JsonUtils.json2List(datas,
            ValElectReconciliation001Dto.class);
        if (operateType.equals("adjust")) {
            for (ValElectReconciliation001Dto item : listdto) {
                item.setRemark(remark);
                if (!DictCodeListExt1.深圳通对账状态_对账不一致.getValue().equals(item.getCheckStatus())) {
                    msg = msg.concat("只能对对账不一致的数据进行调整");
                    return new AjaxResult(msg, MessageType.错误消息);
                }
                item.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账一致.getValue());
            }
        }
        else if (operateType.equals("canadjust")) {
            for (ValElectReconciliation001Dto item : listdto) {
                item.setRemark(remark);
                if (!DictCodeListExt1.深圳通对账状态_对账一致.getValue().equals(item.getCheckStatus())) {
                    msg = msg.concat("只能对对账一致的数据进行取消调整");
                    return new AjaxResult(msg, MessageType.错误消息);
                }
                item.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账不一致.getValue());
            }
        }

        msg = valElectReconciliation001Service.adjustValue(listdto);
        return new AjaxResult(msg, MessageType.提示消息);
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
        List<ValElectReconciliation001Dto> listdto;

        listdto = JsonUtils.json2List(datas, ValElectReconciliation001Dto.class);

        File outFile = valElectReconciliation001Service.makeFile(
            "/com/joyintech/tams/valuation/dailyoperation/dailyaccountmake/valelectreconciliation/conf/export_valelectreconciliation.xml",
            listdto);
        return ExportUtil.export("电子对账"  + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    /**
     * 报文下载 <功能详细描述>
     * 
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("down")
    public String sztDown()
        throws Exception {
        SztXmlMessageDto sztXmlMessageDto = new SztXmlMessageDto();
        String prodId = this.getParam("prodId");
        String interType = this.getParam("interType");
        String cdate = this.getParam("valDate");
        if (DictCodeListExt1.深圳通对账类型_估值表.getValue().equals(interType)) {
            sztXmlMessageDto = sztInterfCommonService.createBookValuationDocument(prodId, cdate);
        }
        else if (DictCodeListExt1.深圳通对账类型_余额表.getValue().equals(interType)) {
            sztXmlMessageDto = sztInterfCommonService.createSubjectBalalceDocument(prodId, cdate);
        }
        String xmlMessage = valElectReconciliation001Service.formatXml(sztXmlMessageDto.getResp());
        return xmlMessage;
    }

}
