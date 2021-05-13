/**
 * @author wj
 * @version 1.0.0
 * @ClassName SysPrebatchResult001Service.java
 * @Description 批前检查001 页面对应
 * @createTime 2021年05月13日 10:57:00
 */
package com.joyintech.tams.invest.prebatch.service;

import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.prebatch.dao.SysPrebatchResult001Mapper;
import com.joyintech.tams.invest.prebatch.dao.SysPrebatchTaskAddMapper;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResult001Dto;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchTaskAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysPrebatchResult001Service extends CommonExportService<SysPrebatchResult001Dto>  {


    @Autowired
    private SysPrebatchResult001Mapper sysPrebatchResult001Mapper;

    @Autowired
    private IPlatformDate iPlateFormDate;

    @Autowired
    private SysPrebatchTaskAddMapper sysPrebatchTaskAddMapper;


    public Page<SysPrebatchResult001Dto> findPage(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        if (StringUtil.isNotEmpty(pageParam.get("checkPoint"))) {
            pageParam.put("checkPoint", StringUtil.splitToList(null, pageParam.get("checkPoint").toString()));
        }else{
            pageParam.remove("checkPoint");
        }

        if (StringUtil.isNotEmpty(pageParam.get("prebatchStatus"))) {
            pageParam.put("prebatchStatus", StringUtil.splitToList(null, pageParam.get("prebatchStatus").toString()));
        }else{
            pageParam.remove("prebatchStatus");
        }

        return super.queryForPage(page,length,()->sysPrebatchResult001Mapper.findPage(pageParam));
    }

    /**
     * 目的 ：启动批前检查 任务组 在一个系统中应仅存一个 批前检查任务组
     * 同时注意 多级法人机构 启动批前检查应该 删除 和 生成的数据。
     *
     * 1. 删除 对应机构生成的当前平台日期的所有数据 包括 sys_prebatch_result sys_prebatch_result_detail 表数据
     *          目前 暂时不需要 sys_prebatch_result的更上一层表
     *
     * 2. 生成 SYS_BATCHGRP_EXEC_info 一次跑批仅生成一条  （如果跑多各机构 也生成多条）
     * 3. 取 系统参数中配置的 任务组id 入表   产品组id 暂时默认为空 测试后看是否可以成功跑批
     * 后期可以通过机构号获取
     * 取 数据字典 检查项 即任务id
     *   将此表 SYS_BATCHGRP_EXEC_info  seqNo 存入 sys_prebatch_result和sys_prebatch_result_detail的 batch_seq_no中
     *   注意 sys_prebatch_result表的 机构号
     *   每个批运行时  根据seqno 更新此表状态及结果。
     *
     *   问题1产品组id 暂时默认为空 测试后看是否可以成功跑批
     *   问题2 确认 批中的sql能否 调用拦截器 修改sql
     *
     * @return
     */
    public String executePreBatch(String startMode) {

        //
        String userName = LoginUserUtil.getUserName();

        String prebatchGroupId = CacheUtil.getSysParam("PREBATCH_GROUP_ID");

        // 取平台日
//        Date date = DateUtil.platDateFromCache();
        Date plateFormDate = iPlateFormDate.getSysDate();


        // 增加判断 批前检查任务组id是否配置
        if(StringUtil.isEmpty(prebatchGroupId)){
            return "未配置批前检查任务组，请配置后再开始处理";
        }

//        // 查缓存 或者直接查sql  后期再改
//        DataDict dictCode = CacheUtil.getDictCode(DictCode.检查项.getValue());
//        //增加判断 批前检查 检查项
//        if(StringUtil.isEmptyList(dictCode.getItems())){
//            return "未配置批前检查检查项数据字典，请配置后再开始处理";
//        }


        // 配置项 及  任务 id   及  附属配置等
        List<SysPrebatchTaskAdd> taskLists = sysPrebatchTaskAddMapper.selectByGroupId(prebatchGroupId);
        if(StringUtil.isEmptyList(taskLists)){
            return "未配置对应批前检查的任务组明细及附属表，请配置后再开始处理";
        }

        List<SysBatchgrpExecInfo> infoList = new ArrayList<>();

        List<SysPrebatchResult001Dto> resultList = new ArrayList<>();

        if(StringUtil.isNotEmpty(userName)) {
            if (MultilevelDataAuthorityUtil.isEnabled()) {
                List<String> instCodes = LoginUserUtil.getTreeInstCode();
                if(StringUtil.isEmptyList(instCodes)){
                    return "当前用户不在任何机构下，无法执行批前检查";
                }

                for(String instCode :instCodes){

                    // 新增 SYS_BATCHGRP_EXEC_INFO 表数据
                    String seqId = IDGenerator.generate();
                    SysBatchgrpExecInfo sysBatchgrpExecInfo = packageSysBatchgrpExecInfo(seqId,prebatchGroupId,plateFormDate,userName,instCode);

                    infoList.add(sysBatchgrpExecInfo);

                    // 先删除机构 加  对应日期的 数据 sys_prebatch_result
                    // 再新增 循环字典值的数据 sys_prebatch_result

                    Map<String,Object> param = new HashMap<>();
                    param.put("checkDate",plateFormDate);
                    param.put("institutionCode",instCode);
                    sysPrebatchResult001Mapper.deleteDetailList(param);
                    sysPrebatchResult001Mapper.deleteByDateAndInstCodeLs(param);

                    packageResultList(taskLists,seqId,plateFormDate,startMode,userName,instCode,resultList);

                }

            }else{

                // 银行模式 无 机构

                // 新增 SYS_BATCHGRP_EXEC_INFO 表数据
                String seqId = IDGenerator.generate();
                SysBatchgrpExecInfo sysBatchgrpExecInfo = packageSysBatchgrpExecInfo(seqId,prebatchGroupId,plateFormDate,userName,"");
                infoList.add(sysBatchgrpExecInfo);

                // 先删除
                Map<String,Object> param = new HashMap<>();
                param.put("checkDate",plateFormDate);
                sysPrebatchResult001Mapper.deleteDetailList(param);
                sysPrebatchResult001Mapper.deleteByDate(param);

                packageResultList(taskLists,seqId,plateFormDate,startMode,userName,"",resultList);


            }
        }else{
            // 自动跑批之类的。 暂不处理

        }


        insertIntoDataBase(infoList,resultList);


        return null;
    }

    private void insertIntoDataBase(List<SysBatchgrpExecInfo> infoList, List<SysPrebatchResult001Dto> resultList) {

        if (!StringUtil.isEmptyList(resultList)){

            int singleInsert = 50;
            for(int i=0;i*singleInsert<resultList.size();i++) {
                int endIndex = (i+1)*singleInsert;
                if(endIndex>resultList.size()) {
                    endIndex = resultList.size();
                }
                sysPrebatchResult001Mapper.insertBatchPrebatchResultList(resultList.subList(i*singleInsert, endIndex));
            }
        }

        if(!StringUtil.isEmptyList(infoList)){
            sysPrebatchResult001Mapper.insertBatchExecList(infoList);
        }

    }


    private SysBatchgrpExecInfo packageSysBatchgrpExecInfo(String seqId, String prebatchGroupId, Date plateFormDate, String userName, String instCode) {
        SysBatchgrpExecInfo sysBatchgrpExecInfo = new SysBatchgrpExecInfo();

        sysBatchgrpExecInfo.setSeqNo(seqId);
        sysBatchgrpExecInfo.setSystemCode(SysCodeUtil.getSysCode());
        sysBatchgrpExecInfo.setBatchSeqNo(prebatchGroupId);
        // 不存产品组  先置为空 看是否有问题
        //executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
        sysBatchgrpExecInfo.setStartDate(plateFormDate);
        sysBatchgrpExecInfo.setEndDate(plateFormDate);
        sysBatchgrpExecInfo.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
        sysBatchgrpExecInfo.setCreateUser(userName);
        sysBatchgrpExecInfo.setUpdateUser(userName);

        sysBatchgrpExecInfo.setDetailName("批前检查-"+DateUtil.format(plateFormDate));
        sysBatchgrpExecInfo.setOtherParam4(instCode);
        return sysBatchgrpExecInfo;
    }

    private void packageResultList(List<SysPrebatchTaskAdd> taskLists, String seqId, Date plateFormDate, String startMode, String userName, String instCode, List<SysPrebatchResult001Dto> resultList) {
        for(SysPrebatchTaskAdd temp:taskLists){
            SysPrebatchResult001Dto dto = new SysPrebatchResult001Dto();
            dto.setSeqNo(IDGenerator.generate());
            // 存的是 sysBatchgrpExecInfo表的批处理任务组执行ID
            dto.setBatchSeqNo(seqId);
            dto.setTaskId(temp.getTaskId());
            dto.setBeanName(temp.getBeanName());
            dto.setCheckDate(plateFormDate);
            dto.setTaskStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
            dto.setStartMode(startMode);
            dto.setStopFlag(temp.getStopFlag());
            dto.setInstitutionCode(instCode);
            dto.setCreateUser(userName);
            dto.setUpdateUser(userName);
            resultList.add(dto);

        }


    }
}
