package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.controller;

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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.service.ValSubjectInfo001Service;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztXmlMessageDto;
import com.joyintech.tams.valuation.common.sztinterf.service.SztInterfCommonService;

/**
 * 科目信息表Controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValSubjectInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValSubjectInfo001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valsubjectinfo/ValSubjectInfo001")
public class ValSubjectInfo001Controller extends BaseController {

    @Autowired
    private ValSubjectInfo001Service valSubjectInfo001Service;

    @Autowired
    private ValInertfStartUp001Service valInertfStartUp001Service;

    @Autowired
    private SztInterfCommonService sztInterfCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.深圳通报文状态);
        this.setSelectOptionToModel(model, DictCode.深圳通接口类型);
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
    public Page<ValSubjectInfo001Dto> findPage()
        throws Exception {
        Page<ValSubjectInfo001Dto> page = valSubjectInfo001Service.findPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 电子对账页面查询check <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValSubjectInfo001Dto dto, BindingResult result) {
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
        List<ValSubjectInfo001Dto> arrList = JsonUtils.json2List(datas,
            ValSubjectInfo001Dto.class);
        String sprodIds = "";
        for (ValSubjectInfo001Dto arr : arrList) {
            sprodIds = (sprodIds == "") ? arr.getProdId() : (sprodIds + "," + arr.getProdId());
        }
        if (sprodIds != "") {
            String interType = DictCodeListExt1.深圳通接口类型_科目信息表.getValue();// 2：科目信息表
            String url = valInertfStartUp001Service.getSztUrl(null);
            String cdate = DateUtil.formatDate(DateUtil.getCurrentDate());
            String isSendDetailSubjectNo = CacheUtil.getSysParam("IS_SEND_DETAILSUBJECTNO");
            Map<String, String> urlParams = valSubjectInfo001Service.getParm(sprodIds, cdate,
                interType, isSendDetailSubjectNo);
            valInertfStartUp001Service.runInterf(url, urlParams);
        }
        return json.toString();
    }

    /**
     * 获取产品id
     * 
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return valSubjectInfo001Service.getFinprodId(map);
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
        List<ValSubjectInfo001Dto> listdto;

        listdto = JsonUtils.json2List(datas, ValSubjectInfo001Dto.class);

        File outFile = valSubjectInfo001Service.makeFile(
            "/com/joyintech/tams/valuation/dailyoperation/dailyaccountmake/valsubjectinfo/conf/export_valsubjectinfo.xml",
            listdto);
        return ExportUtil.export("科目信息"  + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    /**
     * 科目信息表报文下载 <功能详细描述>
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
        String cdate = DateUtil.formatDate(DateUtil.getCurrentDate());
        String isSendDetailSubjectNo = CacheUtil.getSysParam("IS_SEND_DETAILSUBJECTNO");
        sztXmlMessageDto = sztInterfCommonService.createSubjectInfoDocument(prodId, cdate,
            isSendDetailSubjectNo);
        String xmlMessage = valSubjectInfo001Service.formatXml(sztXmlMessageDto.getResp());
        return xmlMessage;
    }

}
