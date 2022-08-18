/*
 * 文件名：ProdPartner001Controller.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodassetinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.service.ProdAssetInfo001Service;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.service.ProdAssetInfo002Service;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.service.ProdAssetInfoImportService;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner001Service;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
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
 * 合作机构信息维护
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdAssetInfo001Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("ProdAssetInfo001")
@RequestMapping("/disclosure/information/config/prodassetinfo/ProdAssetInfo001")
public class ProdAssetInfo001Controller extends BaseController{
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 合作机构信息维护service注入
     */
    @Autowired
    private ProdAssetInfo001Service prodAssetInfo001Service;

    @Autowired
    private ProdAssetInfo002Service prodAssetInfo002Service;

    @Autowired
    private CommonExportService commonExportService;

    /**
     * 导入数据service注入
     */
    @Autowired
    private ProdAssetInfoImportService prodAssetInfoImportService;
    /**
     * 
     * {@inheritDoc}
     */
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.信披_数据状态ED );
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
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return prodAssetInfo001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     *
     * 查询合作机构
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ProdAssetInfoDto> selectAllProdAssetInfo()
            throws Exception {
        Page<ProdAssetInfoDto> page = prodAssetInfo001Service.selectForPage(RequestUtil.getPageParam());
        return page;
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
        List<ProdAssetInfoDto> prodAssetInfoDtos=new ArrayList<>();
        if(param.containsKey("keyList")){
            //导出选中行
            String keyList = param.get("keyList").toString();
            try {
                prodAssetInfoDtos = JsonUtils.json2List(keyList,ProdAssetInfoDto.class);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }else{
            prodAssetInfoDtos = prodAssetInfo001Service.findAll(RequestUtil.getParameters(getRequest()));
        }
        File outFile = commonExportService.makeFile(
                "/com/joyintech/tams/disclosure/information/config/prodassetinfo/conf/export_conf.xml", prodAssetInfoDtos);
        String exportFileName=DictCodeListExt3.共通业务类型_产品资产组合情况维护.getCodeName() + GlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(sysDate,"yyyy-MM-dd")+ GlobalConstant.CLOSE_PARENTHESIS +"."+ FileNameUtil.getFileExtName(outFile);
        return ExportUtil.export(exportFileName, outFile, true);
    }


    /**
     * 导入check：单次导入数据量
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) throws FileNotFoundException, ParseException {
        return prodAssetInfoImportService.checkImportData(fileName);
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return this.prodAssetInfoImportService.importData(fileName);
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
            ValidateGroupDelete.class}) ProdAssetInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ProdAssetInfoDto> processParameter = new ProcessParameter<ProdAssetInfoDto>();
        WorkFlowParameter<ProdAssetInfoDto> params = new WorkFlowParameter<ProdAssetInfoDto>();
        params.setPageId("ProdAssetInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品资产组合情况维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：产品代码 + "(" + 产品代码 + ")" + "-" + "合作机构" + "-" + "生效日"
        params.setComment(entity -> this.getMessage("产品代码") + "(" + entity.getProdId() + ")"
                + "生效区间" + "(" + DateUtil.format(entity.getEffDate(),"yyyyMMdd") +"-"+DateUtil.format(entity.getEffEndDate(),"yyyyMMdd")+ ")");
        processParameter.setWorkFlowParameter(params);
        String msg = prodAssetInfo002Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new ProdAssetInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }


}
