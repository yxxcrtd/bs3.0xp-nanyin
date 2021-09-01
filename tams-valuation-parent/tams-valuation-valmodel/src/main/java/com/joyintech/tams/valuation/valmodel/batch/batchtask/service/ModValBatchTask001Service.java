package com.joyintech.tams.valuation.valmodel.batch.batchtask.service;

import com.google.gson.JsonObject;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto.BatchExecutingStatusDto;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto.BatchTaskDto;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto.ModBatchDetailDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 王波
 * @date 2021-07-13
 * @see ModValBatchTask001Service
 * @since 1.0
 */
@Service
public class ModValBatchTask001Service  extends CommonExportService {

    /**
     * 定义常量 估值模型任务组组号
     * add by yanya on 20210901
     */
    private final static String BATCH_GROUP_SEQNO = "valmodel000000000000000000000001";

    /**
     * 获取树的节点信息
     * @param map
     * @return
     */
    public List<TreeNode> getInterGroup(Map<String, Object> map) {
        List<TreeNode> list = new ArrayList<>();
        List<BatchTaskDto> batchTaskDtos = restTemplate.postForList(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/getAllBatchTask", map, BatchTaskDto.class);
        TreeNode pNode = new TreeNode();
        pNode.setName("估值模型批处理");
        pNode.setId("0");
        pNode.setChecked(false);
        list.add(pNode);

        if (!CollectionUtils.isEmpty(batchTaskDtos)) {
            for (BatchTaskDto batchTaskDto : batchTaskDtos) {
                TreeNode node = new TreeNode();
                node.setName(batchTaskDto.getTaskName());
                node.setId(batchTaskDto.getTaskId());
                node.setChecked(false);
                node.setpId(pNode.getId());
                list.add(node);
            }
        }
        return list;
    }

    public List<String> getBatchList(Map<String, Object> map) {
        //add by yanya on 20210901 添加估值模型任务组组号
        map.put("BATCH_GROUP_SEQNO", BATCH_GROUP_SEQNO);
        List<BatchTaskDto> batchTaskDtos = restTemplate.postForList(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/getAllBatchTask", map, BatchTaskDto.class);
        if (!CollectionUtils.isEmpty(batchTaskDtos)) {
            List<String> res = new ArrayList<>(batchTaskDtos.size());
            for (BatchTaskDto batchTaskDto : batchTaskDtos) {
                res.add(batchTaskDto.getTaskId());
            }
            return res;
        }
        return new ArrayList<>(0);
    }


    public String execute(Map<String, Object> pageParam) {
        // 返回的json串
        JsonObject json = new JsonObject();

        int isCheck = 0;
        String checkMsg = "";


        if (StringUtil.isBlank((String)pageParam.get("execDate"))) {
            json.addProperty("isCheck", isCheck);
            json.addProperty("checkMsg", "请输入执行日期！");
            return json.toString();
        }
//        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//        if(!CollectionUtils.isEmpty(pageParam)) {
//            for (Map.Entry<String, Object> entry : pageParam.entrySet()) {
//                params.add(entry.getKey(), entry.getValue());
//            }
//        }
        // 用户名
        String realName = SecurityUtil.getLoginUser().getRealName();
        pageParam.put("realName", realName);
        boolean checkFlag = restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/checkIsRunning", pageParam, Boolean.class);
        if(!checkFlag) {
            boolean res = restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME
                    + "/modValBatchTask/insertBatch", pageParam, Boolean.class);
            if (res) {
                isCheck = 0;
                checkMsg = "执行成功!";
            } else {
                isCheck = 1;
                checkMsg = "执行失败, 请联系管理员!";
            }
        }
        else {
            isCheck = 2;
            checkMsg = "当前有产品正在跑批，请稍后!";
        }
        json.addProperty("isCheck", isCheck);
        json.addProperty("checkMsg", checkMsg);
        return json.toString();

    }

    /**
     * 读取画面资产信息
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getValBatchAssetInfo(Map<String, Object> pageParam) {
        Page<SelectOptionDto> page = restTemplate.postForPage(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/getAssetInfo", pageParam, SelectOptionDto.class);
        return page;
    }

    /**
     * 查询日志
     * @param sysDate
     * @return
     */
    public Page<BatchExecutingStatusDto> findBatchExeDetail(String sysDate) {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("sysDate", sysDate);
        //add by yanya on 20210901 添加估值模型任务组组号
        params.put("BATCH_GROUP_SEQNO", BATCH_GROUP_SEQNO);
        Page<BatchExecutingStatusDto> page = restTemplate.postForPage(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/findBatchExeDetail", params, BatchExecutingStatusDto.class);

        return page;
    }

    public Page<ModBatchDetailDto> findBatchExeTask(Map<String, Object> pageParam) {

        Page<ModBatchDetailDto> page = restTemplate.postForPage(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/findBatchExeTask", pageParam, ModBatchDetailDto.class);
        return page;
    }

    public List<ModBatchDetailDto> findBatchExeTaskForExport(Map<String, Object> pageParam) {

        List<ModBatchDetailDto> page = restTemplate.postForList(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/modValBatchTask/findBatchExeTaskForExport", pageParam, ModBatchDetailDto.class);
        return page;
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {

        if ("execStatus".equals(propName)) {
            String taskStatus = (String)value;
            if ("UNR".equals(taskStatus)) {
                taskStatus = "未执行";
            } else if ("RUN".equals(taskStatus)) {
                taskStatus =  "执行中";
            } else if ("NOR".equals(taskStatus)) {
                taskStatus =  "正常结束";
            } else if ("EXP".equals(taskStatus)) {
                taskStatus =  "异常结束";
            } else if ("BRK".equals(taskStatus)) {
                taskStatus =  "中断";
            } else if ("WARN".equals(taskStatus)) {
                taskStatus =  "警告";
            } else {
                taskStatus =  "未知";
            }
            cell.setCellValue(taskStatus);
        }

    }
}
