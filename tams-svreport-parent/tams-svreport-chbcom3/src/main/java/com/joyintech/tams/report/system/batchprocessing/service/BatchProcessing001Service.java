/*
 * 文件名：BatchProcessing001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年12月26日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */

package com.joyintech.tams.report.system.batchprocessing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.ChbBatchLogFileInfoMapper;
import com.joyintech.tams.common.base.entity.ChbBatchLogFileInfo;
import com.joyintech.tams.common.base.entity.ChbSysTask;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.report.common.constant.StartMode;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.system.batchprocessing.dao.BatchProcessing001Mapper;
import com.joyintech.tams.report.system.batchprocessing.dto.BatchProcessing001Dto;
import com.joyintech.tams.report.system.log.batch.service.SystemBatchLog001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年12月26日
 * @see BatchProcessing001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BatchProcessing001Service extends BaseService<BatchProcessing001Dto>{
    /**
     * SystemBatchLog001Service
     */
    @Autowired
    private SystemBatchLog001Service systemBatchLog001Service;
    /**
     * 报送批处理Mapper
     */
    @Autowired
    private BatchProcessing001Mapper batchProcessing001Mapper;

    /**
     * ChbBatchLogFileInfoMapper
     */
    @Autowired
    private ChbBatchLogFileInfoMapper chbBatchLogFileInfoMapper;
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(BatchProcessing001Service.class);


    /**
     * 获取批处理任务名称
     * <功能详细描述>
     * @return 批处理名称
     * @see
     * @since 1.0
     */
    public String queryTaskList() {
        // 查询任务组下拉框初始值
        List<ChbSysTask> list = systemBatchLog001Service.queryTask();
        List<SelectOptionDto> selectList = new ArrayList<>();
        for (ChbSysTask sysTask : list) {
            SelectOptionDto bean = new SelectOptionDto();
            bean.setId(sysTask.getTaskId());
            bean.setText(sysTask.getTaskName());
            bean.setCol(sysTask.getTaskName());
            selectList.add(bean);
        }
        return JsonUtils.object2Json(selectList.toArray());
    }

    /**
     * 批处理跑批后台
     * <功能详细描述>
     * @param param 页面参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public Page<BatchProcessing001Dto> dealBatchProcess(Map<String, Object> param) {
        //返回结果
        Page<BatchProcessing001Dto> page = new Page<BatchProcessing001Dto>();
        //是否已初始化
        //判断是否需要初始化
        if(StringUtil.equals((String)param.get("isNeedInit"),"true")){
            //初始化
            processInit(param);
        }
/*        //非初始化直接取出需要监听状态的数据
        String processIdList =(String) param.get("processIdList");
        //将Id用分隔符切开
        list = StringUtil.splitToList(StringUtil.STR_COMMA,processIdList);*/
        //监听数据的数据状态并返回前台维护
        page = dealShowList(param);
        return page;
    }
    /**
     * 维护画面的任务列表
     * <功能详细描述>
     * @param param 需要维护的数据的key
     * @return 维护的数据结果列
     * @see
     * @since 1.0
     */
    private Page<BatchProcessing001Dto> dealShowList(Map<String, Object> param) {
        List<BatchProcessing001Dto> pageList = batchProcessing001Mapper.getShowDataList(param);
        return new Page<BatchProcessing001Dto>(pageList);
    }

    /**
     * 根据画面参数来对跑批初始化
     * <功能详细描述>
     * @param param 页面参数
     * @see
     * @since 1.0
     */
    public void processInit(Map<String, Object> param){
        List<String> resultList= new ArrayList<String>();
        String process= (String)param.get("batchDealProcess");
        //开始日期
        Date dateStart = DateUtil.parse((String)param.get("batchDealDateStart"));
        //结束日期
        Date dateEnd = DateUtil.parse((String)param.get("batchDealDateEnd"));
        List<Date> dateList =this.getDateList(dateStart,dateEnd);
        //任务列表
        List<String> processList = StringUtil.splitToList(GlobalConstant.COMMA, process);
        //根据日期和待处理任务分类生成任务
        //任务list
        List<BatchProcessing001Dto> taskList = new ArrayList<BatchProcessing001Dto>();
        //文件ID 当前时间精确到毫秒值
        String fileId = (String)param.get("fileId");
        //所有的task数据
        List<ChbSysTask> taskInfo = systemBatchLog001Service.queryTask();
        //转换task 数据后的容器
        Map<String , String>taskMap= new HashMap<>();
        //建立Id与beanName的关系
        taskInfo.forEach(item->{
            taskMap.put(item.getTaskId(), item.getBeanName());
        });
        //subType 子分类
        for(String subType: processList){
            for(Date subDate:dateList){
                //单个任务
                BatchProcessing001Dto dto = new BatchProcessing001Dto();
                //批处理代码
                String batchId = CommonUtil.getSequenceIndexData(DataIndexSequenceType.批处理用);
                //设置批处理代码
                dto.setBatchId(batchId);
                //日志文件
                dto.setLogFileCode(fileId);
                //开始日期
                dto.setBatchStartDate(subDate);
                //结束日期
                dto.setBatchEndDate(subDate);
                //操作员
                dto.setOperator(SecurityUtil.getLoginUser().getUserName());
                //批类型
                dto.setJobId(subType);
                //名称
                dto.setBeanName(taskMap.get(subType));
                //任务状态
                dto.setTaskStatus(DictCodeList.批处理执行状态_未执行.getValue());
                //数据状态
                dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                //启动模式
                dto.setStartMode(StartMode.手动.getCode());
                //任务列表Id 加入到list
                resultList.add(batchId);
                //加入到List
                taskList.add(dto);
            }
        }
        batchProcessing001Mapper.insertBatchData(taskList);
    }
    /**
     * 根据开始日期与结束日期获取数据列
     * <功能详细描述>
     * @param dateStart 日期开始
     * @param dateEnd 日期结束
     * @return 日期序列
     * @see
     * @since 1.0
     */
    private List<Date> getDateList(Date dateStart, Date dateEnd) {
        List<Date> dateList =new ArrayList<Date>();
        //比较日期
        while(DateUtil.compareDate(dateStart, dateEnd)<=0){
            dateList.add(dateStart);
            dateStart=DateUtil.addDate(dateStart, 0, 0, 1);
        }
        return dateList;
    }

    /**
     * 检查画面元素
     * <功能详细描述>
     * @param param 画面
     * @return check結果
     * @see
     * @since 1.0
     */
    public AjaxResult check(Map<String, Object> param) {
    	AjaxResult result = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        // 存不存在种类
        if (StringUtil.isEmpty((String)param.get("batchDealProcess"))) {
            //请选择批处理任务
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0040","批处理任务")));
        }
        // 跑批日期
        if (!param.containsKey("batchDealDateStart")) {
            //请输入跑批起止日期
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001","跑批起止日期")));
        }
        if(CollectionUtils.isNotEmpty(errorList)){
            result.setErrorList(errorList);
        }
        return result;
    }

    /**
     * 取画面的日志
     * <功能详细描述>
     * @param param 画面的参数
     * @return 画面的日志值
     * @see
     * @since 1.0
     */
    public AjaxResult reflushLog(Map<String, Object> param) {
    	AjaxResult wb =new AjaxResult();
        String fileId = (String)param.get("fileId");
        wb.setData(viewLog(fileId));
        return wb;
    }
    /**
     * 查看日志
     *
     * @param fileId 文件名称
     * @return 日志文件内容
     * @see
     * @since 1.0
     */
    public String viewLog(String fileId) {
        ChbBatchLogFileInfo fileInfo = chbBatchLogFileInfoMapper.selectByPrimaryKey(fileId);
        if (fileInfo == null) {
            return StringUtil.EMPTY;
        }

        StringBuffer logContent = new StringBuffer();
        String logFileUrl = fileInfo.getFilePath();
        File logFile = new File(logFileUrl);
        if (logFile.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(logFile), "UTF-8"));
                String tempString = null;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    logContent.append(tempString);
                    logContent.append("\r\n");
                }
            }
            catch (Exception e) {
//                e.printStackTrace();
                logger.error(e.getMessage(),e);
            }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (IOException e) {
//                        e.printStackTrace();
                        logger.error(e.getMessage(),e);
                    }
                }
            }
        }
        return logContent.toString();
    }
}
