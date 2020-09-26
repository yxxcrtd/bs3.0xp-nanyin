package com.joyintech.tams.valuation.tradecostmanagement.controller;

import java.io.File;
import java.util.*;

import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.tradecostmanagement.dto.TradeCostManagement001Dto;
import com.joyintech.tams.valuation.tradecostmanagement.service.TradeCostManagement001Service;

/**
 * 交易费用管理 查询
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@Controller
@PageId("TradeCostManagement001")
@RequestMapping("/valuation/tradecostmanagement/TradeCostManagement001")
public class TradeCostManagement001Controller extends BaseController {

    @Autowired
    private TradeCostManagement001Service tradeCostManagement001Service;

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @SuppressWarnings("rawtypes")
    @Autowired
    private CommonExportService commonExportService;

    /**
     * 账套日期Mapper
     */
    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        List<SelectOptionDto> feeTypeOptions = new ArrayList<SelectOptionDto>();
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.一次性费用类型, null);
        List<SelectOptionDto> feeTypeSelect = selectDto.getOptions();
        
        for (SelectOptionDto item : feeTypeSelect) {
            // 一次性费用类型 B05佣金 B13账户维护费 B99其他费用 B06交易手续费  B08结算手续费           
            if (DictCodeListExt1.一次性费用类型_佣金.getValue().equals(item.getId())) {
                feeTypeOptions.add(item);
            }
            if (DictCodeListExt1.一次性费用类型_交易手续费.getValue().equals(item.getId())) {
                feeTypeOptions.add(item);
            }
            if (DictCodeListExt1.一次性费用类型_结算手续费.getValue().equals(item.getId())) {
                feeTypeOptions.add(item);
            }
            if (DictCodeListExt1.一次性费用类型_账户维护费.getValue().equals(item.getId())) {
                feeTypeOptions.add(item);
            }
            if (DictCodeListExt1.一次性费用类型_其他费用.getValue().equals(item.getId())) {
                feeTypeOptions.add(item);
            }
        }
        model.addAttribute("FEE_TYPE", JsonUtils.objectArray2Json(feeTypeOptions.toArray()));

        return super.init(model);
    }

    /**
     * 画面 分页查询
     *
     * @return 分页结果集
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TradeCostManagement001Dto> findPage()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        String feeTypes = (String)param.get("feeTypes");
        feeTypes = feeTypes.replaceAll(" ", "");
        if (StringUtil.isNotEmpty(feeTypes)) {
            String[] split = feeTypes.split(",");
            param.put("feeTypes", split);
        }
        else {
            param.put("feeTypes", null);
        }
        Page<TradeCostManagement001Dto> pageDto = tradeCostManagement001Service.queryPageList(
            param);
        return pageDto;

    }

    /**
     * 批量删除
     *
     * @param dto 选中数据行
     */
    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody TradeCostManagement001Dto dto) {

        List<TradeCostManagement001Dto> rows = dto.getTradeCostManagementDtoList();
        String msg = getMessage("COMMON_I_0005");
            // 启动流程
            ProcessParameter<TradeCostManagement001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<TradeCostManagement001Dto> params = new WorkFlowParameter<>();

            // 业务代码（凭证流水号）
            params.setBusinessCode(entity -> entity.getTradeId());
            // 业务类型
            params.setBusinessType(DictCodeListExt3.共通业务类型_交易费用管理.getValue());
            // 页面id
            params.setPageId("TradeCostManagement003");
            // 元素id
            params.setElementId("TradeCostManagement001_btn_delete");

            //设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("交易费用管理删除：",
                    entity.getTradeId(),//交易编号
                    entity.getBookSetId()//账套代码
            ));

            // 塞入流程参数
            proParam.setWorkFlowParameter(params);


            try {
                tradeCostManagement001Service.start(proParam,rows.toArray(new TradeCostManagement001Dto[rows.size()]));
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult("启动流程失败："+e.getMessage());
            }

        return new AjaxResult(msg);

//        tradeCostManagement001Service.delete(rows);
//        return new AjaxResult("删除成功");
    }

    /**
     * 返回已设待摊的账套代码集合
     *
     * @param rows 选中数据行
     * @return
     */
    @RequestMapping("deleteCheck")
    @ResponseBody
    public List<String> deleteCheck(@RequestBody List<TradeCostManagement001Dto> rows) {
        List<String> booksetIdList = new ArrayList<String>();
        for (TradeCostManagement001Dto row : rows) {
            if (tradeCostManagement001Service.judgeIsPrepaid(row.getTradeId())) {
                booksetIdList.add(row.getBookSetId());
            }
        }
        return booksetIdList;
    }

    /**
     * 点击修改时校验
     * 
     * @param dto 参数
     * @return 校验结果
     * @see
     * @since 1.0
     */
    @RequestMapping("editCheck")
    @ResponseBody
    public AjaxResult editCheck(@RequestBody TradeCostManagement001Dto dto) {
        AjaxResult result = new AjaxResult();
        BokBooksetDate bokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(
            dto.getBookSetId());
        // 若当前交易的支付日小于账套日期，则提示调整账套日期后才可修改
        if (bokBooksetDate != null && DateUtil.compareDate(DateUtil.parse(dto.getPayDate()),
            bokBooksetDate.getBooksetDate()) < 0) {
            return new AjaxResult("当前交易支付日期小于账套日期，请调整账套日期后才可修改！", MessageType.错误消息);
        }
        // 判断是否已做过待摊设置，若做过设置，则返回true
        result.setData(tradeCostManagement001Service.judgeIsPrepaid(dto.getTradeId()));
        return result;
    }

    /**
     * 检索账套代码(只取理财账套)
     *
     * @return 111
     * @throws Exception 111
     */
    @ResponseBody
    @RequestMapping("/getBookSetId2")
    public List<SuggestDto> getBookSetId2()
        throws Exception {
        return valAssetBook001Service.getBooksetId2(RequestUtil.getPageParam());
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<TradeCostManagement001Dto> dtoList;
        if (datas == null) {
            dtoList = tradeCostManagement001Service.findAll(RequestUtil.getPageParam());
        }
        else {
            dtoList = JsonUtils.json2List(datas, TradeCostManagement001Dto.class);
        }
        File outFile = commonExportService.makeFile(
            "/com/joyintech/tams/valuation/tradecostmanagement/conf/export_tradecostmanagement001.xml",
            dtoList);
        String exportFileName = this.getPageId();
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if (null != page) {
            exportFileName = page.getName() + SystemGlobalConstant.OPEN_PARENTHESIS
                             + DateUtil.getCurrentDate() + SystemGlobalConstant.CLOSE_PARENTHESIS
                             + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }

}
