/*
 * 文件名：ProdPartner001Controller.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.saleagency.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.service.SaleAgency001Service;
import com.joyintech.tams.disclosure.information.config.saleagency.service.SaleAgency002Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 代销机构维护
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency001Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("SaleAgency001")
@RequestMapping("/disclosure/information/config/saleagency/SaleAgency001")
public class SaleAgency001Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 代销机构维护service注入
     */
    @Autowired
    private SaleAgency001Service saleAgency001Service;

    @Autowired
    private SaleAgency002Service saleAgency002Service;

    /**
     * 
     * {@inheritDoc}
     */
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.产品系列 );
        return super.init(model);
    }

    /**
     * 产品主代码联想下拉框
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySaleAgency")
    public List<SelectOptionDto> queryProdIdInfo() {
        return saleAgency001Service.querySaleAgency(RequestUtil.getPageParam());
    }

    /**
     *
     * 查询代销机构
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SaleAgencyDto> selectAllProdAssetInfo()
            throws Exception {
        Page<SaleAgencyDto> page = saleAgency001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult editCheck(@RequestBody SaleAgencyDto dto, BindingResult deleteResult) {
        //判断代销机构是否关联了产品
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AjaxResult wr = new AjaxResult();
        StringBuilder sb=new StringBuilder();
        boolean flag=false;
        if(StringUtil.isNotEmpty(dto.getSelectedItem())){//多选删除
            List<SaleAgencyDto> selectedItems = dto.getSelectedItem();
            for (SaleAgencyDto saleAgencyDto : selectedItems) {
                List<ProdSaleAgencyDto> prodSaleAgencyDtos=saleAgency001Service.selectProdByAgencyId(saleAgencyDto.getSequenceNo());
                if(prodSaleAgencyDtos!=null && prodSaleAgencyDtos.size()>0){
                    sb.append(saleAgencyDto.getAgencyName());
                    sb.append(",");
                    flag=true;
                }
            }
            sb.append("机构已关联产品，请确认是否删除？");
            if(flag){
                JoyinMessage ms = new JoyinMessage(sb.toString(),
                        MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
                return wr;
            }
        }
        List<ProdSaleAgencyDto> list=saleAgency001Service.selectProdByAgencyId(dto.getSequenceNo());
        if(list!=null && list.size()>0){
            JoyinMessage ms = new JoyinMessage("该机构已关联产品，请确认是否删除？",
                    MessageType.确认消息);
            joyinMessages.add(ms);
        }
        wr.setJoyinMessages(joyinMessages);
        return wr;
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
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) SaleAgencyDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<SaleAgencyDto> processParameter = new ProcessParameter<SaleAgencyDto>();
        WorkFlowParameter<SaleAgencyDto> params = new WorkFlowParameter<SaleAgencyDto>();
        params.setPageId("SaleAgency002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getAgencyName());
        params.setBusinessType(DictCodeListExt4.共通业务类型_代销机构维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "代销机构" + "(" + entity.getAgencyName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = saleAgency002Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new SaleAgencyDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }

    /**
     *
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        Date sysDate = DateUtil.platDateFromCache()==null?new Date():DateUtil.platDateFromCache();//平台日期
        if(param.containsKey("keyList")){
            //导出选中行
            String keyList = param.get("keyList").toString();
            try {
                List<SaleAgencyDto> saleAgencyDtoList = JsonUtils.json2List(keyList,SaleAgencyDto.class);
                param.put("investList", saleAgencyDtoList);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            List<SaleAgencyDto> dtoList = saleAgency001Service.selectForExport(param);
            File outFile = saleAgency001Service.makeFile(
                    "/com/joyintech/tams/disclosure/information/config/saleagency/conf/export_conf.xml", dtoList);
            return ExportUtil.export(
                    DictCodeListExt4.共通业务类型_代销机构维护.getCodeName() + GlobalConstant.UNDER_LINE
                            + DateUtil.format(sysDate,"yyyyMMdd") +"."+ FileNameUtil.getFileExtName(outFile),
                    outFile, true);
        }else{
            //todo:框架中导出的excel文件与需求中的不符
            return saleAgency001Service.export(this.getPageId(),param);
        }
    }

}
