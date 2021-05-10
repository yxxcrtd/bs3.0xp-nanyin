/*
 * 文件名：EtrInvestBondInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * 资产减值管理画面端service 〈功能详细描述〉
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLossService
 * @since 1.0
 */
@Service
public class TrdAssetLossService extends CommonExportService<TrdAssetLoss001Dto> implements BaseProcessService<TrdAssetLoss001Dto>{

    private static final String CHECK_STATUS_STR_TG = "通过";
    private static final String CHECK_STATUS_STR_BTG = "不通过";
    /**
     * 资产减值管理查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdAssetLoss001Dto> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/findPage", param,
                TrdAssetLoss001Dto.class);
    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/TrdAssetLoss/getfinprodIdForAdd"
                , param,
                SelectOptionDto.class);

    }



    /**
     * 工作流完成方法
     */
    @Override
    public void doComplete(TrdAssetLoss001Dto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/doComplete",dto,
                Integer.class);
    }

    /**
     *
     * 流程修改时调用
     */
    @Override
    public void doUpdate(TrdAssetLoss001Dto entity) {

    }

    /**
     *
     * 流程发起时调用
     */
    @Override
    public void doInsert(TrdAssetLoss001Dto entity, OperationType type) {

    }

    public TrdAssetLoss001Dto getAssetInfo(Map<String, Object> param) {

        List<TrdAssetLoss001Dto>  result =  restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/getAssetInfo",
                param, TrdAssetLoss001Dto.class);
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        String tempVdate = DateUtil.format(result.get(0).getvDate());
        String tempMdate = DateUtil.format(result.get(0).getmDate());
        result.get(0).setvDateStr(tempVdate);
        result.get(0).setmDateStr(tempMdate);
        return result.get(0);
    }

    public List<TrdAssetLoss001Dto> getBondAssetInfo(Map<String, Object> param) {
        List<TrdAssetLoss001Dto>  result =  restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/getBondAssetInfo",
                param, TrdAssetLoss001Dto.class);
        return result;
    }
    public TrdAssetLoss001Dto selectByKey(String tradeId) {
        Map<String, Object> param = new HashMap<>();
        param.put("tradeId",tradeId);
        List<TrdAssetLoss001Dto>  result =  restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/selectByKey",
                param, TrdAssetLoss001Dto.class);
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        String tempVdate = DateUtil.format(result.get(0).getvDate());
        String tempMdate = DateUtil.format(result.get(0).getmDate());
        result.get(0).setvDateStr(tempVdate);
        result.get(0).setmDateStr(tempMdate);
        return result.get(0);
    }

    /**
     * 预览
     * @param param
     * @return
     */
    public Page<TrdAssetLoss001Dto> selectForPagePreView(Map<String, Object> param) {
        String lossTestType = param.get("lossTestType") == null ? "" : (String) param.get("lossTestType");
        String lossRatio = param.get("lossRatio") == null ? "" : (String) param.get("lossRatio");
        String pdValue = param.get("pdValue") == null ? "" : (String) param.get("pdValue");
        String lgdValue = param.get("lgdValue") == null ? "" : (String) param.get("lgdValue");

        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        Page<TrdAssetLoss001Dto> page =  restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/selectForPagePreView", param,
                TrdAssetLoss001Dto.class);
        List<TrdAssetLoss001Dto> root = page.getRoot();
        root.forEach(item->{
            if (StringUtil.isNotEmpty(lossTestType)){
                if (StringUtil.equals(lossTestType, DictCodeListExt3.减值测算方式_外部评估.getValue())){
                    if (StringUtil.isNotEmpty(lossRatio) && StringUtil.isNotEmpty(item.getEadDefaultExposure())){
                        item.setLossRatio(new BigDecimal(lossRatio));
                        item.setThisLossValue((item.getEadDefaultExposure().multiply(item.getLossRatio())).divide(new BigDecimal(100L)));
                    }
                }
                if (StringUtil.equals(lossTestType, DictCodeListExt3.减值测算方式_预期信用损失模型.getValue())){
                    if (StringUtil.isNotEmpty(pdValue) && StringUtil.isNotEmpty(lgdValue) && StringUtil.isNotEmpty(item.getEadDefaultExposure())){
                        item.setLossRatio(new BigDecimal(pdValue).multiply(new BigDecimal(lgdValue)));
                        item.setThisLossValue((item.getEadDefaultExposure().multiply(item.getLossRatio())).divide(new BigDecimal(10000L)));
                        item.setLossRatio((new BigDecimal(pdValue).multiply(new BigDecimal(lgdValue))).divide(new BigDecimal(100L)));
                    }
                }
            }
        });
        page.setRoot(root);
        return page;
    }

    public TrdAssetLoss001Dto selectByFinprodId(String finprodId) {
        Map<String, Object> param = new HashMap<>();
        param.put("finprodId",finprodId);
        List<TrdAssetLoss001Dto>  result =  restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/selectByFinprodId",
                param, TrdAssetLoss001Dto.class);
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        return result.get(0);
    }
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public Map<String, Object> importData(String fileName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tempList", JSONArray.toJSONString(ExcelUtil.getTempList(fileName)));
        return param;
    }

    /**
     * 处理普通模板数据
     * @param tempList
     * @return
     */
    public TrdAssetLoss001Dto getTrdAssetLossListData(String tempList) {
        TrdAssetLoss001Dto dto = new TrdAssetLoss001Dto();
        // 减值信息列表
        List<TrdAssetLoss001Dto> trdAssetLoss001DtoList = new ArrayList<>();
        dto.setSelectedItem(trdAssetLoss001DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            TrdAssetLoss001Dto trdAssetLoss001Dto = new TrdAssetLoss001Dto();
            trdAssetLoss001Dto.setRemark(new StringBuffer());
            //默认通过，若是有不通过的，在下面重新赋值
            trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_TG);
            //根据资产代码【0】、行情日【8】去查询资产行权日
            if (StringUtil.isNotEmpty(rowData.get(0)) && StringUtil.isNotEmpty(rowData.get(8))) {
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId", rowData.get(0).toString());
                param.put("valDate", rowData.get(8).toString());
                TrdAssetLoss001Dto model = this.getAssetExerciseDate(param);
                if (StringUtil.isNotEmpty(model)){
                    trdAssetLoss001Dto.setAssetExerciseDate(model.getAssetExerciseDate());
                }
            }
            // 资产代码【0】
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                trdAssetLoss001Dto.setFinprodId(rowData.get(0).toString());
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId", rowData.get(0).toString());
                TrdAssetLoss001Dto assetInfo = this.getAssetInfo(param);
                if (StringUtil.isNotEmpty(assetInfo)) {
                    trdAssetLoss001Dto.setvDate(assetInfo.getvDate());
                    trdAssetLoss001Dto.setmDate(assetInfo.getmDate());
                    trdAssetLoss001Dto.setAssetType1(assetInfo.getAssetType1());
                    trdAssetLoss001Dto.setFinprodAbbr(assetInfo.getFinprodAbbr());
                    if (StringUtil.isNotEmpty(rowData.get(8))) {
                        if (StringUtil.equals(assetInfo.getExeDate(), DictCodeList.含权债摊销日_到期日.getValue())) {
                            long betweenDayNumber = DateUtil.getBetweenDayNumber(DateUtil.parse(rowData.get(8).toString()),assetInfo.getmDate());
                            trdAssetLoss001Dto.setPeroid((new BigDecimal(betweenDayNumber)).divide(new BigDecimal(365L), 4, BigDecimal.ROUND_HALF_UP));
                        } else {
                            if (StringUtil.isNotEmpty(trdAssetLoss001Dto.getAssetExerciseDate()) && StringUtil.isNotEmpty(DateUtil.parse(rowData.get(8).toString()))) {
                                long betweenDayNumber = DateUtil.getBetweenDayNumber(DateUtil.parse(rowData.get(8).toString()),trdAssetLoss001Dto.getAssetExerciseDate());
                                trdAssetLoss001Dto.setPeroid((new BigDecimal(betweenDayNumber)).divide(new BigDecimal(365L), 4, BigDecimal.ROUND_HALF_UP));
                            }
                        }
                    }
                } else {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("资产代码不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            //减值阶段【4】
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值阶段不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                trdAssetLoss001Dto.setStage(this.getCode(rowData.get(4).toString()));
            }
            // 测算方式【1】  范围校验，只能是内部评估 和外部评估
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                trdAssetLoss001Dto.setLossTestType(this.getCode(rowData.get(1).toString()));
                if (StringUtil.equals(DictCodeListExt3.减值测算方式_外部评估.getValue(), this.getCode(rowData.get(1).toString()))) {
                    trdAssetLoss001Dto.setLossInputPattern(DictCodeListExt3.减值录入方式_按比例减值.getValue());
                    if (!StringUtil.isNotEmpty(rowData.get(7))) {
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值测算方式为外部评估，减值比例不能为空!"));
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    }
                } else if (StringUtil.equals(DictCodeListExt3.减值测算方式_预期信用损失模型.getValue(), this.getCode(rowData.get(1).toString()))) {
                    if (!StringUtil.isNotEmpty(rowData.get(5))) {
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值测算方式为预期信用损失模型，PD不能为空!"));
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6))) {
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值测算方式为预期信用损失模型，LGD不能为空!"));
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    }
                    if (StringUtil.isNotEmpty(rowData.get(5)) && StringUtil.isNotEmpty(rowData.get(6))) {
                        trdAssetLoss001Dto.setEclVaule((new BigDecimal(rowData.get(6).toString())).multiply(new BigDecimal(rowData.get(5).toString())).divide((new BigDecimal(100L)), 4, BigDecimal.ROUND_HALF_UP));
                    }
                } else {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值测算方式仅包括内部评估和外部评估!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("测算方式不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // PD【5】
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                trdAssetLoss001Dto.setPdValue(new BigDecimal(rowData.get(5).toString()));
                if (!(trdAssetLoss001Dto.getPdValue().compareTo(BigDecimal.ZERO) >= 0 && trdAssetLoss001Dto.getPdValue().compareTo(new BigDecimal(100)) <= 0)) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("PD应该大于等于0，小于等于100!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            // LGD【6】
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                trdAssetLoss001Dto.setLgdValue(new BigDecimal(rowData.get(6).toString()));
                if (!(trdAssetLoss001Dto.getLgdValue().compareTo(BigDecimal.ZERO) >= 0 && trdAssetLoss001Dto.getLgdValue().compareTo(new BigDecimal(100)) <= 0)) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("LGD应该大于等于0，小于等于100!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            // 减值比例【7】
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                trdAssetLoss001Dto.setLossRatio(new BigDecimal(rowData.get(7).toString()));
                if (!(trdAssetLoss001Dto.getLossRatio().compareTo(BigDecimal.ZERO) >= 0 && trdAssetLoss001Dto.getLossRatio().compareTo(new BigDecimal(100)) <= 0)) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值比例应该大于等于0，小于等于100!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            // 减值行情日【8】
            if (StringUtil.isNotEmpty(rowData.get(8))) {
                trdAssetLoss001Dto.setValDate(DateUtil.parse(rowData.get(8).toString()));
                //减值行情日需要等于平台日
                if (DateUtil.parse(rowData.get(8).toString()).compareTo(DateUtil.parse(DateUtil.getSysDate())) != 0) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值行情日需要等于平台日!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
            }
            // 初始确认起始日【2】
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                trdAssetLoss001Dto.setStrDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认起始日不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 初始确认截止日【3】
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                trdAssetLoss001Dto.setEndDate(DateUtil.parse(rowData.get(3).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认截止日不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            //检验
            if (StringUtil.isNotEmpty(trdAssetLoss001Dto.getFinprodId())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getValDate())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getPeroid())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getStrDate())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getEndDate())) {
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId", trdAssetLoss001Dto.getFinprodId());
                param.put("valDate", DateUtil.format(trdAssetLoss001Dto.getValDate()));
                List<TrdAssetLoss001Dto> tmpLsit1 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit1)) {
                    TrdAssetLoss001Dto loss001Dto = tmpLsit1.get(0);
                    if (!StringUtil.equals(loss001Dto.getLossTestType(), dto.getLossTestType())) {
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("同一资产同一行情日不能维护两种减值测算方式!"));
                    }
                }
                param.put("peroid", trdAssetLoss001Dto.getPeroid());
                param.put("strDate", DateUtil.format(trdAssetLoss001Dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit2 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit2)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中!"));
                }
                param.remove("strDate");
                param.put("endDate", DateUtil.format(trdAssetLoss001Dto.getEndDate()));
                List<TrdAssetLoss001Dto> tmpLsit3 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit3)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中!"));
                }
                param.put("strDate", DateUtil.format(trdAssetLoss001Dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit4 = this.selectRepeatByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit4)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("新建减值流水的初始确认日与初始确认截止日的区间，不能包含相同资产、相同行情日、相同代偿期的有效流水的初始确认日到截止日区间!!"));
                }
            }
            trdAssetLoss001DtoList.add(trdAssetLoss001Dto);
        }
        for (int i = 0; i < trdAssetLoss001DtoList.size(); i++) {
            TrdAssetLoss001Dto rowData1 = trdAssetLoss001DtoList.get(i);
            for (int k = i + 1; k < trdAssetLoss001DtoList.size(); k++) {
                TrdAssetLoss001Dto rowData2 = trdAssetLoss001DtoList.get(k);
                if (StringUtil.isNotEmpty(rowData1) && StringUtil.isNotEmpty(rowData2)) {
                    // 同一资产同一行情日不能维护两种减值测算方式
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && !StringUtil.equals(rowData1.getLossTestType(), rowData2.getLossTestType())
                    ) {
                        trdAssetLoss001DtoList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001DtoList.get(i).setRemark(trdAssetLoss001DtoList.get(i).getRemark().append("模板中的数据存在重复性:同一资产同一行情日不能维护两种减值测算方式。"));
                        trdAssetLoss001DtoList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001DtoList.get(k).setRemark(trdAssetLoss001DtoList.get(k).getRemark().append("模板中的数据存在重复性:同一资产同一行情日不能维护两种减值测算方式。"));
                    }
                    // 初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && (rowData1.getPeroid().compareTo(rowData2.getPeroid()) == 0)) {
                        if (rowData1.getStrDate().compareTo(rowData2.getStrDate()) >= 0 && rowData1.getStrDate().compareTo(rowData2.getEndDate()) <= 0) {
                            trdAssetLoss001DtoList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoList.get(i).setRemark(trdAssetLoss001DtoList.get(i).getRemark().append("模板中的数据存在重复性:初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                            trdAssetLoss001DtoList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoList.get(k).setRemark(trdAssetLoss001DtoList.get(k).getRemark().append("模板中的数据存在重复性:初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                        }
                    }
                    // 初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && (rowData1.getPeroid().compareTo(rowData2.getPeroid()) == 0)) {
                        if (rowData1.getEndDate().compareTo(rowData2.getStrDate()) >= 0 && rowData1.getEndDate().compareTo(rowData2.getEndDate()) <= 0) {
                            trdAssetLoss001DtoList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoList.get(i).setRemark(trdAssetLoss001DtoList.get(i).getRemark().append("模板中的数据存在重复性:初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                            trdAssetLoss001DtoList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoList.get(k).setRemark(trdAssetLoss001DtoList.get(k).getRemark().append("模板中的数据存在重复性:初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                        }
                    }
                }
            }
        }
        dto.setSelectedItem(trdAssetLoss001DtoList);
        return dto;
    }

    /**
     * 处理中债ECL模板数据【针对：预期信用损失模型】
     * @param tempEclList
     * @return
     */
    public TrdAssetLoss001Dto getTrdAssetLossEclListData(String tempEclList) {
        TrdAssetLoss001Dto dto = new TrdAssetLoss001Dto();
        // 资产减值信息列表
        List<TrdAssetLoss001Dto> trdAssetLoss001DtoEclList = new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(tempEclList);
        for (int i = 0; i < objects.size(); i++ ) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray)objects.get(i)).toJSONString());
            TrdAssetLoss001Dto trdAssetLoss001Dto = new TrdAssetLoss001Dto();
            //默认通过，若是有不通过的，在下面重新赋值
            trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_TG);
            trdAssetLoss001Dto.setRemark(new StringBuffer());
            //默认是预期信用损失模型
            trdAssetLoss001Dto.setLossTestType(DictCodeListExt3.减值测算方式_预期信用损失模型.getValue());

            // 日期【0】减值行情日
            if (StringUtil.isNotEmpty(rowData.get(0))){
                if (DateUtil.parse(rowData.get(0).toString()).compareTo(DateUtil.parse(DateUtil.getSysDate())) != 0) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("日期需要等于平台日!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
                trdAssetLoss001Dto.setValDate(DateUtil.parse(rowData.get(0).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("日期不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 债券简称	【2】不入库
            // 发行人名称【3】不入库
            // 待偿期(年)【4】
            if (StringUtil.isNotEmpty(rowData.get(4))){
                trdAssetLoss001Dto.setPeroid(new BigDecimal(rowData.get(4).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("待偿期不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 初始确认日期起始日【5】
            if (StringUtil.isNotEmpty(rowData.get(5))){
                trdAssetLoss001Dto.setStrDate(DateUtil.parse(rowData.get(5).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认日期起始日不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 初始确定日期截止日【6】
            if (StringUtil.isNotEmpty(rowData.get(6))){
                trdAssetLoss001Dto.setEndDate(DateUtil.parse(rowData.get(6).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确定日期截止日不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 减值阶段【7】
            if (StringUtil.isNotEmpty(rowData.get(7))){
                trdAssetLoss001Dto.setStage(this.getCode(rowData.get(7).toString()));
            }
            if (!StringUtil.isNotEmpty(rowData.get(7))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("减值阶段不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 减值期限(年)【8】不入库
            // 中债市场隐含违约率(%)【9】-- PD
            if (StringUtil.isNotEmpty(rowData.get(9))){
                trdAssetLoss001Dto.setPdValue(new BigDecimal(this.getCode(rowData.get(9).toString())));
            }
            if (!StringUtil.isNotEmpty(rowData.get(9))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("中债市场隐含违约率(%)不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 违约损失率(%)【10】-- LGD
            if (StringUtil.isNotEmpty(rowData.get(10))){
                trdAssetLoss001Dto.setLgdValue(new BigDecimal(this.getCode(rowData.get(10).toString())));
            }
            if (!StringUtil.isNotEmpty(rowData.get(10))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("违约损失率(%)不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 预期信用损失比例(%)【11】-- ECL
            if (StringUtil.isNotEmpty(rowData.get(11))){
                trdAssetLoss001Dto.setEclVaule(new BigDecimal(this.getCode(rowData.get(11).toString())));
            }
            if (!StringUtil.isNotEmpty(rowData.get(11))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("预期信用损失比例(%)!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            // 债券代码	【1】债券代码 作为条件去查询【标的金融表，只查债券即可】，取简称、市场代码,资产类型、资产起息日、资产到期日
            if (StringUtil.isNotEmpty(rowData.get(1))){
                Map<String, Object> map = new HashMap<>();
                map.put("finprodId",rowData.get(1).toString());
                List<TrdAssetLoss001Dto> bondAssetInfos = this.getBondAssetInfo(map);
                if (StringUtil.isEmptyList(bondAssetInfos)) {
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("无此债券!"));
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                }
                if (!StringUtil.isEmptyList(bondAssetInfos) && bondAssetInfos.size() > 1) {
                    for (TrdAssetLoss001Dto model : bondAssetInfos
                    ) {
                        trdAssetLoss001Dto.setFinprodId(model.getFinprodId());
                        trdAssetLoss001Dto.setFinprodAbbr(model.getFinprodAbbr());
                        trdAssetLoss001Dto.setAssetType1(model.getAssetType1());
                        trdAssetLoss001Dto.setvDate(model.getvDate());
                        trdAssetLoss001Dto.setmDate(model.getmDate());
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("同一债券代码存在对应多条市场代码!"));
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001DtoEclList.add(trdAssetLoss001Dto);
                    }
                    continue;
                }
                if (!StringUtil.isEmptyList(bondAssetInfos) && bondAssetInfos.size() == 1){
                    TrdAssetLoss001Dto tmpLossDto = bondAssetInfos.get(0);
                    trdAssetLoss001Dto.setFinprodId(tmpLossDto.getFinprodId());
                    trdAssetLoss001Dto.setFinprodAbbr(tmpLossDto.getFinprodAbbr());
                    trdAssetLoss001Dto.setAssetType1(tmpLossDto.getAssetType1());
                    trdAssetLoss001Dto.setvDate(tmpLossDto.getvDate());
                    trdAssetLoss001Dto.setmDate(tmpLossDto.getmDate());
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("债券代码不能为空!"));
                trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
            }
            //检验
            if (StringUtil.isNotEmpty(trdAssetLoss001Dto.getFinprodId())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getValDate())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getPeroid())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getStrDate())
                    && StringUtil.isNotEmpty(trdAssetLoss001Dto.getEndDate())) {
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId", trdAssetLoss001Dto.getFinprodId());
                param.put("valDate", DateUtil.format(trdAssetLoss001Dto.getValDate()));
                List<TrdAssetLoss001Dto> tmpLsit1 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit1)) {
                    TrdAssetLoss001Dto loss001Dto = tmpLsit1.get(0);
                    if (!StringUtil.equals(loss001Dto.getLossTestType(), dto.getLossTestType())) {
                        trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("同一资产同一行情日不能维护两种减值测算方式!"));
                    }
                }
                param.put("peroid", trdAssetLoss001Dto.getPeroid());
                param.put("strDate", DateUtil.format(trdAssetLoss001Dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit2 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit2)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中!"));
                }
                param.remove("strDate");
                param.put("endDate", DateUtil.format(trdAssetLoss001Dto.getEndDate()));
                List<TrdAssetLoss001Dto> tmpLsit3 = this.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit3)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中!"));
                }
                param.put("strDate", DateUtil.format(trdAssetLoss001Dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit4 = this.selectRepeatByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit4)) {
                    trdAssetLoss001Dto.setCheckStatusStr(CHECK_STATUS_STR_BTG);
                    trdAssetLoss001Dto.setRemark(trdAssetLoss001Dto.getRemark().append("新建减值流水的初始确认日与初始确认截止日的区间，不能包含相同资产、相同行情日、相同代偿期的有效流水的初始确认日到截止日区间!"));
                }
            }
            trdAssetLoss001DtoEclList.add(trdAssetLoss001Dto);
        }
        for (int i = 0; i < trdAssetLoss001DtoEclList.size(); i++) {
            TrdAssetLoss001Dto rowData1 = trdAssetLoss001DtoEclList.get(i);

            for (int k = i + 1; k < trdAssetLoss001DtoEclList.size(); k++) {
                TrdAssetLoss001Dto rowData2 = trdAssetLoss001DtoEclList.get(k);
                if (StringUtil.isNotEmpty(rowData1) && StringUtil.isNotEmpty(rowData2)) {
                    // 同一资产同一行情日不能维护两种减值测算方式
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && !StringUtil.equals(rowData1.getLossTestType(), rowData2.getLossTestType())
                    ) {
                        trdAssetLoss001DtoEclList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001DtoEclList.get(i).setRemark(trdAssetLoss001DtoEclList.get(i).getRemark().append("模板中的数据存在重复性:同一资产同一行情日不能维护两种减值测算方式。"));
                        trdAssetLoss001DtoEclList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                        trdAssetLoss001DtoEclList.get(k).setRemark(trdAssetLoss001DtoEclList.get(k).getRemark().append("模板中的数据存在重复性:同一资产同一行情日不能维护两种减值测算方式。"));
                    }
                    // 初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && (rowData1.getPeroid().compareTo(rowData2.getPeroid()) == 0)) {
                        if (rowData1.getStrDate().compareTo(rowData2.getStrDate()) >= 0 && rowData1.getStrDate().compareTo(rowData2.getEndDate()) <= 0) {
                            trdAssetLoss001DtoEclList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoEclList.get(i).setRemark(trdAssetLoss001DtoEclList.get(i).getRemark().append("模板中的数据存在重复性:初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                            trdAssetLoss001DtoEclList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoEclList.get(k).setRemark(trdAssetLoss001DtoEclList.get(k).getRemark().append("模板中的数据存在重复性:初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                        }
                    }
                    // 初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中
                    if (StringUtil.equals(rowData1.getFinprodId(), rowData2.getFinprodId())
                            && (rowData1.getValDate().compareTo(rowData2.getValDate()) == 0)
                            && (rowData1.getPeroid().compareTo(rowData2.getPeroid()) == 0)) {
                        if (rowData1.getEndDate().compareTo(rowData2.getStrDate()) >= 0 && rowData1.getEndDate().compareTo(rowData2.getEndDate()) <= 0) {
                            trdAssetLoss001DtoEclList.get(i).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoEclList.get(i).setRemark(trdAssetLoss001DtoEclList.get(i).getRemark().append("模板中的数据存在重复性:初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                            trdAssetLoss001DtoEclList.get(k).setCheckStatusStr(CHECK_STATUS_STR_BTG);
                            trdAssetLoss001DtoEclList.get(k).setRemark(trdAssetLoss001DtoEclList.get(k).getRemark().append("模板中的数据存在重复性:初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中。"));
                        }
                    }
                }
            }
        }
        dto.setSelectedItem(trdAssetLoss001DtoEclList);
        return dto;
    }

    /**
     * 验证导入的数据
     * @param fileName 文件名
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 100) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "资产单次最多导入100条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }

        String totalMsg = "";
        JSONArray jsonArrays = new JSONArray();
        // 失败条数
        int errorNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 9) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要9列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                for (int i = 1; i < objects.size(); i++) {
                    List rowData = objects.get(i);
                    jsonArrays.add(rowData);
                }
            }
        }
        totalMsg = this.StringAddByBr(totalMsg,
                getMessage("VAL_C_0001", String.valueOf(errorNum)));
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }
    /**
     * 验证中债ECL导入的数据
     * @param fileName 文件名
     * @return
     */
    public AjaxResult checkImportDataEcl(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 100) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "资产单次最多导入100条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }

        String totalMsg = "";
        JSONArray jsonArrays = new JSONArray();
        // 失败条数
        int errorNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 12) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要12列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                for (int i = 1; i < objects.size(); i++) {
                    List rowData = objects.get(i);
                    jsonArrays.add(rowData);
                }
            }
        }
        totalMsg = this.StringAddByBr(totalMsg,
                getMessage("VAL_C_0001", String.valueOf(errorNum)));
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctEclData", JSONArray.toJSONString(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }
    /**
     * check多对多指令字段用,错误消息拼接
     *
     * @param errorMessage 原错误信息
     * @param addMessage 新增错误信息
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String StringAddByBr(String errorMessage, String addMessage) {
        // 错误信息拼接
        if (StringUtil.isNotBlank(errorMessage) && StringUtil.isNotBlank(addMessage)) {
            return errorMessage + "<br />" + addMessage;
        }
        else if (StringUtil.isNotBlank(errorMessage)) {
            return errorMessage;
        }
        else {
            return addMessage;
        }
    }
    /**
     * check多对多指令字段用,空判断
     *
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsBlank(String cellData, String labelName, String indexId) {
        // 判断是否是空
        if (StringUtil.isBlank(cellData)) {
            return getMessage("VAL_E_0015", getMessage(labelName), indexId);
        }
        return "";
    }

    /**
     * 取空格前的数据
     * @param str
     * @return
     */
    private String getCode(String str) {
        return str.split(" ")[0];
    }

    /**
     * 减值管理页面--导出
     * @param param
     * @return
     */
    public List<TrdAssetLoss001Dto> selectForExport(Map<String, Object> param) {
        List<TrdAssetLoss001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/TrdAssetLoss/selectForExport"
                , param,
                TrdAssetLoss001Dto.class);
        return list;
    }

    /**
     * 重复校验查询
     * @param param
     * @return
     */
    public List<TrdAssetLoss001Dto> selectByFinprodIdAndValDate(Map<String, Object> param) {
        List<TrdAssetLoss001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/TrdAssetLoss/selectByFinprodIdAndValDate"
                , param,
                TrdAssetLoss001Dto.class);
        return list;
    }

    /**
     * 获取资产的资产行权日
     * @param param
     * @return
     */
    public TrdAssetLoss001Dto getAssetExerciseDate(Map<String, Object> param) {
        List<TrdAssetLoss001Dto>  result =  restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLoss/getAssetExerciseDate",
                param, TrdAssetLoss001Dto.class);
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        return result.get(0);
    }

    /**
     * 重复校验查询
     * @param param
     * @return
     */
    public List<TrdAssetLoss001Dto> selectRepeatByFinprodIdAndValDate(Map<String, Object> param) {
        List<TrdAssetLoss001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/TrdAssetLoss/selectRepeatByFinprodIdAndValDate"
                , param,
                TrdAssetLoss001Dto.class);
        return list;
    }
}
