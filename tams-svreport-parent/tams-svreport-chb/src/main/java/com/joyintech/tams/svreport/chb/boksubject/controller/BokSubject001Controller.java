package com.joyintech.tams.svreport.chb.boksubject.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.boksubject.service.BokSubjectService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 科目定义controller
 * @author wubingbing
 * @date 2022-03-28 14:36
 */
@Controller
@PageId("BokSubject001")
@RequestMapping("/chb/boksubject/boksubject001")
public class BokSubject001Controller extends BaseController {
    @Autowired
    private BokSubjectService bokSubjectService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.科目类型);
        this.setSelectOptionToModel(model, DictCode.科目级别);
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 检索上级科目代码
     *
     * @return 账套上级科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFSubjectId")
    public List<SuggestDto> getFSubjectId(String param)
    {
        return bokSubjectService.getFSubjectId(param);
    }



    /**
     * 检索科目代码 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectId2")
    public List<SelectOptionDto> getSubjectId2()
    {
        return bokSubjectService.getSubjectId2(RequestUtil.getPageParam());
    }

    /**
     * 科目名称 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectName")
    public List<SelectOptionDto> getSubjectName()
    {
        return bokSubjectService.getSubjectName(RequestUtil.getPageParam());
    }


    /**
     * 检索科目级别 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectLevel")
    public List<SelectOptionDto> getSubjectLevel()
    {
        List<SelectOptionDto> list = Lists.newArrayList();
        String subjectNo = getParam("subjectNo");
        if (StringUtils.isEmpty(subjectNo)) {
            list = getSelectDataFromDataDic(DictCode.科目级别, getParam("search")).getOptions();
        }
        else {
            list = bokSubjectService.getSubjectLevel(RequestUtil.getPageParam());
            if(CollectionUtils.isNotEmpty(list)) {
                list.forEach(item -> {
                    item.setText(SysDataUtil.getNameFromDataDict(DictCode.科目级别, item.getId()));
                });
            }
        }
        return list;
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
    public Page<BokSubjectDto> selectAllSubject()
    {
        Page<BokSubjectDto> page = bokSubjectService.selectForPage(RequestUtil.getPageParam());

        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) BokSubjectDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
            ValidateGroupDelete.class}) BokSubjectDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<BokSubjectDto> processParameter = new ProcessParameter<BokSubjectDto>();
        WorkFlowParameter<BokSubjectDto> params = new WorkFlowParameter<BokSubjectDto>();
        params.setPageId("CBokSubject002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSubjectNo());
        params.setBusinessType("SYS");
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("BokSubject.subjectNo") + "(" + entity.getSubjectNo() + ")" + "-"
                + SpringContextUtil.getTextValue("BokSubject.subjectName") + "("
                +entity.getSubjectName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = bokSubjectService.start(processParameter,dto.getSelectedItem().toArray(new BokSubjectDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }
    /**
     * 导入（必填项校验）
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return this.bokSubjectService.importData(fileName);
    }


    /**
     * 导入（数据保存）
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("importFileSave")
    public List<Integer> importFileSave(String fileName)
            throws Exception {
        List<List<String>> dataList = bokSubjectService.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
            checkResult = bokSubjectService.duplicateCheck(dataList);
            List<BokSubjectDto> equityList = bokSubjectService.converToBean(dataList,checkResult);
            // 保存，已存在则更新
            bokSubjectService.dealAfterCheck(equityList);
        }
        return checkResult;
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
        List<BokSubjectDto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, BokSubjectDto.class);
        }
        else {
            list = bokSubjectService.selectAllList(params);
        }
        list.forEach(item->item.setUpdateTimeStr(DateUtil.format(item.getUpdateTime(),"yyyy-MM-dd")));
        File outFile = bokSubjectService.makeFile(
                "/com/joyintech/tams/svreport/chb/boksubject/conf/export_conf.xml",
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
