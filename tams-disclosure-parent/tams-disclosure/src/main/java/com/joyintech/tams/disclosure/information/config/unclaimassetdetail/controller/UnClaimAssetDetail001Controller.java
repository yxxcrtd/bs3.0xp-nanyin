package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail001Service;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.维护数据状态};

    /**
     * UnClaimAssetDetail001画面初始化
     * @param model
     * @return
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        setSelectOptionToModel(model, DictCode.维护数据状态);
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

        List<UnClaimAssetDetailDto> dtoList = dto.getSelectedItem();
        for(UnClaimAssetDetailDto tmp:dtoList) {
            Map<String, Object> params = new HashMap<>();
            params.put("prodId", tmp.getProdId());
            params.put("effDate", tmp.getEffDateStr());
            params.put("effEndDate", tmp.getEffEndDateStr());
            params.put("dataStatus", tmp.getDataStatus());
            List<UnClaimAssetDetailDto> unclaimList = unClaimAssetDetail002Service.selectDetailList(params);
            unclaimList.forEach(item -> {
                item.setId(item.getNostdAssetId());
            });
            tmp.setUnclaimList(unclaimList);
        }

        ProcessParameter<UnClaimAssetDetailDto> processParameter = new ProcessParameter<UnClaimAssetDetailDto>();
        WorkFlowParameter<UnClaimAssetDetailDto> params = new WorkFlowParameter<UnClaimAssetDetailDto>();
        params.setPageId("UnClaimAssetDetail002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("产品主代码") + "(" + entity.getProdId() + ")" + "-"
                + SpringContextUtil.getTextValue("生效日") + "("+DateUtil.format(entity.getEffDate())+")" + "-"
                + SpringContextUtil.getTextValue("生效截止日期") + "("+DateUtil.format(entity.getEffEndDate())+")" );
        processParameter.setWorkFlowParameter(params);
        String msg = unClaimAssetDetail002Service.start(processParameter,
                dtoList.toArray(new UnClaimAssetDetailDto[dtoList.size()]));
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
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return unClaimAssetDetail001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
    /**
     * 导入check：单次导入数据量
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
//    @ResponseBody
//    @RequestMapping("checkImportData")
//    public AjaxResult checkImportData(String fileName) throws FileNotFoundException, ParseException {
//        return unClaimAssetDetail001Service.checkImportData(fileName);
//    }
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return unClaimAssetDetail001Service.checkImportData(fileName);
    }
    /**
     * 导入
     *
     * @param
     * @return 处理结果
     * @throws Exception 异常
     */
//    @ResponseBody
//    @RequestMapping("importFile")
//    public List<ExcelValidDto> importData(String fileName)
//            throws Exception{
//        return this.unClaimAssetDetail001Service.importFile(fileName);
//    }
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return unClaimAssetDetail001Service.importData(fileName);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) UnClaimAssetDetailDto dto, BindingResult br)  {
        AjaxResult result = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        List<UnClaimAssetDetailDto> dtoList = dto.getSelectedItem();
        for(UnClaimAssetDetailDto tmp:dtoList) {
            if(unClaimAssetDetail001Service.isInWorkflow(tmp)){
                errorList.add(new ValidMessage("所选对象中存在流程中的数据！"+SpringContextUtil.getTextValue("产品主代码") + "(" + tmp.getProdId() + ")" + "-"
                        + SpringContextUtil.getTextValue("生效日") + "("+DateUtil.format(tmp.getEffDate())+")" + "-"
                        + SpringContextUtil.getTextValue("生效截止日期") + "("+DateUtil.format(tmp.getEffEndDate())+")"));
                //return new AjaxResult("所选对象中存在流程中的数据！", MessageType.错误消息);
            }
            if(!StringUtil.equals("delete",dto.getOperateType())){
                if(unClaimAssetDetail001Service.checkOverlappingDataNew(tmp)){
                    errorList.add(new ValidMessage("生效区间重叠！"+SpringContextUtil.getTextValue("产品主代码") + "(" + tmp.getProdId() + ")" + "-"
                            + SpringContextUtil.getTextValue("生效日") + "("+DateUtil.format(tmp.getEffDate())+")" + "-"
                            + SpringContextUtil.getTextValue("生效截止日期") + "("+DateUtil.format(tmp.getEffEndDate())+")"));
                }
            }

        }
        if(CollectionUtils.isNotEmpty(errorList)){
            result.setErrorList(errorList);
            return result;
        }
        return commonCheck(br, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody @Validated({
            ValidateGroupDelete.class}) UnClaimAssetDetailDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        List<UnClaimAssetDetailDto> dtoList = dto.getSelectedItem();
        for(UnClaimAssetDetailDto tmp:dtoList) {
            Map<String, Object> params = new HashMap<>();
            params.put("prodId", tmp.getProdId());
            params.put("effDate", tmp.getEffDateStr());
            params.put("effEndDate", tmp.getEffEndDateStr());
            params.put("dataStatus", tmp.getDataStatus());
            List<UnClaimAssetDetailDto> unclaimList = unClaimAssetDetail002Service.selectDetailList(params);
            unclaimList.forEach(item -> {
                item.setId(item.getNostdAssetId());
            });
            tmp.setUnclaimList(unclaimList);
        }

        ProcessParameter<UnClaimAssetDetailDto> processParameter = new ProcessParameter<UnClaimAssetDetailDto>();
        WorkFlowParameter<UnClaimAssetDetailDto> params = new WorkFlowParameter<UnClaimAssetDetailDto>();
        params.setPageId("UnClaimAssetDetail002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("产品主代码") + "(" + entity.getProdId() + ")" + "-"
                + SpringContextUtil.getTextValue("生效日") + "("+DateUtil.format(entity.getEffDate())+")" + "-"
                + SpringContextUtil.getTextValue("生效截止日期") + "("+DateUtil.format(entity.getEffEndDate())+")" );
        processParameter.setWorkFlowParameter(params);
        String msg = unClaimAssetDetail001Service.start(processParameter,
                dtoList.toArray(new UnClaimAssetDetailDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
