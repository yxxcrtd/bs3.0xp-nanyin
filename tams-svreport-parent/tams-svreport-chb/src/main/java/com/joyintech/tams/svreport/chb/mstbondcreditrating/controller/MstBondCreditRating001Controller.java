package com.joyintech.tams.svreport.chb.mstbondcreditrating.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.dto.MstBondCreditRatingDto;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.service.MstBondCreditRateService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-01 15:04
 */
@Controller
@PageId("MstBondCreditRating001")
@RequestMapping("/chb/mstbondcreditrating/mstbondcreditrating001")
public class MstBondCreditRating001Controller extends BaseController {
    @Autowired
    private MstBondCreditRateService mstBondCreditRateService;
    @Override
    public String init(Model model) {
        //评级对象
        this.setSelectOptionToModel(model, DictCode.评级类型);
        //评级类型
        this.setSelectOptionToModel(model, DictCode.长期短期);
        this.setSelectOptionToModel(model, DictCode.维护数据状态);

        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<MstMarketManagementDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstBondCreditRatingDto> selectAllMstMarketManagement(){

        Page<MstBondCreditRatingDto> page = mstBondCreditRateService.selectForPage(RequestUtil.getPageParam());
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstBondCreditRatingDto dto,
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
            ValidateGroupDelete.class}) MstBondCreditRatingDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstBondCreditRatingDto> processParameter = new ProcessParameter<MstBondCreditRatingDto>();
        WorkFlowParameter<MstBondCreditRatingDto> params = new WorkFlowParameter<MstBondCreditRatingDto>();
        params.setPageId("MstBondCreditRating002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getoCode()+item.getoObject());
        params.setBusinessType(DictCodeList.债券评级.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("MstBondCreditRating001.oCode") + "("
                + entity.getoCode() + ")"
                + this.getMessage("MstBondCreditRating001.oName")
                + "(" + entity.getoName() + ")"+this.getMessage("MstBondCreditRating001.oObject")
                +"("+entity.getoObject()+")");
        processParameter.setWorkFlowParameter(params);
        String msg =mstBondCreditRateService.start(processParameter,dto.getSelectedItem().toArray(new MstBondCreditRatingDto[dto.getSelectedItem().size()]));
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
        return this.mstBondCreditRateService.importData(fileName);
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
        List<List<String>> dataList = mstBondCreditRateService.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
            checkResult = mstBondCreditRateService.duplicateCheck(dataList);
            List<MstBondCreditRatingDto> equityList = mstBondCreditRateService.converToBean(dataList,checkResult);
            // 保存，已存在则更新
            mstBondCreditRateService.dealAfterCheck(equityList);
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
        List<MstBondCreditRatingDto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, MstBondCreditRatingDto.class);
        }
        else {
            list = mstBondCreditRateService.selectAll(params);
        }
        File outFile = mstBondCreditRateService.makeFile(
                "/com/joyintech/tams/svreport/chb/mstbondcreditrating/conf/export_conf.xml",
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
