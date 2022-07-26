package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail001Service;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail002Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("UnClaimAssetDetail001")
@RequestMapping("/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001")
public class UnClaimAssetDetail001Controller extends BaseController {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UnClaimAssetDetail001Service unClaimAssetDetail001Service;
    @Autowired
    private UnClaimAssetDetail002Service unClaimAssetDetail002Service;

    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.信披_数据状态};

    /**
     * UnClaimAssetDetail001画面初始化
     * @param model
     * @return
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<UnClaimAssetDetailDto> selectALL(){
        Page<UnClaimAssetDetailDto> page = unClaimAssetDetail001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    /**
     *
     * 产品代码和产品名称联想文本框
     * @return
     */
    @ResponseBody
    @RequestMapping("getProdInfo")
    public List<SelectOptionDto> getProdInfo(){
//        String param = RequestUtil.getParam("prodId");
        return unClaimAssetDetail001Service.getProdInfo(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) UnClaimAssetDetailDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<UnClaimAssetDetailDto> processParameter = new ProcessParameter<UnClaimAssetDetailDto>();
        WorkFlowParameter<UnClaimAssetDetailDto> params = new WorkFlowParameter<UnClaimAssetDetailDto>();
        params.setPageId("UnClaimAssetDetail002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("产品主代码") + "(" + entity.getProdId() + ")" + "-"
                + SpringContextUtil.getTextValue("非标资产编号") + "("+entity.getNostdAssetId()+")" + "-"
                + this.getMessage("生效日")+ "("+DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = unClaimAssetDetail002Service.start(processParameter,
                dto.getUncliamList().toArray(new UnClaimAssetDetailDto[dto.getUncliamList().size()]));
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
                List<UnClaimAssetDetailDto> unClaimAssetDetailDtoList = JsonUtils.json2List(keyList,UnClaimAssetDetailDto.class);
                param.put("investList", unClaimAssetDetailDtoList);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            List<UnClaimAssetDetailDto> dtoList = unClaimAssetDetail001Service.selectForExport(param);
            File outFile = unClaimAssetDetail001Service.makeFile(
                    "/com/joyintech/tams/disclosure/information/config/unclaimassetdetail/config/export_unclaimassetdetail.xml", dtoList);
            return ExportUtil.export(
                    DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getCodeName() + GlobalConstant.UNDER_LINE
                            + DateUtil.format(sysDate,"yyyyMMdd") +"."+ FileNameUtil.getFileExtName(outFile),
                    outFile, true);
        }else{
            //todo:框架中导出的excel文件与需求中的不符
            return unClaimAssetDetail001Service.export(this.getPageId(),param);
        }
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
        return unClaimAssetDetail001Service.checkImportData(fileName);
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
        return this.unClaimAssetDetail001Service.importData(fileName);
    }






}
