/*
 * 文件名：MsgLogService.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年10月10日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.msglog.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.ChbInfoReportDto;
import com.joyintech.component.svrreport.chb.dto.ChbSubmitStatusDto;
import com.joyintech.component.svrreport.chb.dto.InterfLog;
import com.joyintech.component.svrreport.chb.msglog.dao.ChbComponentMsgLogMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Date;
import java.util.List;


/**
 * @author 王波
 * @date 2020年10月10日
 * @see ChbComponentMsgLogService
 * @since 1.0
 */
@Service
public class ChbComponentMsgLogService {
    private static final Logger logger = LoggerFactory.getLogger(ChbComponentMsgLogService.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 没多少次提交一次
     */
    @Value("${application.chb.maxSqlNum:200}")
    private int maxSqlNum;

    @Autowired
    private ChbComponentMsgLogMapper chbComponentMsgLogMapper;

    /**
     * 批量插入日志
     * @param logList
     */
    public void insertInterfLogList(List<InterfLog> logList) {
        if (CollectionUtil.isEmpty(logList)) {
            logger.error("没有相关日志！");
            return;
        }
        // 获取sql 连接， 批量执行模式
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ChbComponentMsgLogMapper msgLogMapper = sqlSession.getMapper(ChbComponentMsgLogMapper.class);
            int index = 0;
            for (InterfLog log : logList) {
                msgLogMapper.insertInterfLog(log);
                index ++;
                if (index == maxSqlNum) {
                    sqlSession.commit();
                    index = 0;
                }
            }
        } catch (Exception e) {
            logger.error("批量执行失败", e);
            sqlSession.rollback();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 插入-中债直连报送 -表
     * @param reportDto
     */
    public void insertReportInfo(ChbInfoReportDto reportDto) {
        if (reportDto != null) {
            if (StringUtil.isNotEmpty(reportDto.getRepMesNo())){
                chbComponentMsgLogMapper.delChbInfoReportByMesNo(reportDto.getRepMesNo());
            }
            chbComponentMsgLogMapper.insertChbInfoReport(reportDto);
        }
    }

    /**
     * 插入-报送状态表
     * @param chbSubmitStatusDto 报送状态
     */
    public void insertSubmitStatusInfo(List<ChbSubmitStatusDto> submitStatusDtos) {
        if (CollectionUtil.isNotEmpty(submitStatusDtos)) {
            // 获取sql 连接， 批量执行模式
            SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                ChbComponentMsgLogMapper msgLogMapper = sqlSession.getMapper(ChbComponentMsgLogMapper.class);
                int index = 0;
                for (ChbSubmitStatusDto dto : submitStatusDtos) {
                	//先删后插
                	msgLogMapper.deleteChbSubmitStatusTwo(dto.getSeqno());
                    // 插入 报送状态表-中债一二期 表
                    msgLogMapper.insertChbSubmitStatusTwo(dto);
//                    if (StrUtil.equals(dto.getSubmitType(), DictCodeList.报表名称_投资者明细信息登记.getValue())) {
//                        msgLogMapper.insertChbEquDetSubmitStatus(dto);
//                    } else if  (StrUtil.equals(dto.getSubmitType(), DictCodeList.报表名称_投资者身份信息登记.getValue())
//                        || StrUtil.equals(dto.getSubmitType(), DictCodeList.报表名称_投资者持有信息登记.getValue())) {
//                        msgLogMapper.insertChbSubmitStatus(dto);
//                    } else {
//                        msgLogMapper.insertChbSubmitStatusTwo(dto);
//                    }
                    index ++;
                    if (index == maxSqlNum) {
                        sqlSession.commit();
                        index = 0;
                    }
                }
            } catch (Exception e) {
                logger.error("批量执行失败", e);
                sqlSession.rollback();
            } finally {
            	sqlSession.commit();
                sqlSession.close();
            }
        }
    }

    /**
     * 打印日志基础信息
     *
     * @param log 日志信息
     * @param interfType 接口类型
     * @param taskStatus 接口状态
     * @param isWarning 是否需要预警
     * @param errMessage 错误信息
     * @param flag 结束标识
     * @see
     * @since 1.0
     */
    public void printLog(InterfLog log, String interfType, String taskStatus, String isWarning,
                            String errMessage, Boolean flag) {
        log.setTaskStatus(taskStatus);
        log.setIsneedwarning(isWarning);
        log.setInterfType(interfType);
        log.setErrMessage(errMessage);
        this.log(log);
        if (flag) {
            log.setInterfType(DictCodeList.接口类型_结束.getValue());
            this.log(log);
        }
    }

    /**
     * 储存接口日志
     *
     * @param log 日志
     */
    private void log(InterfLog log) {
        // 接口流水号
        log.setLogId(Sid.nextShort(20));
        log.setCreateTime(new Date());
        chbComponentMsgLogMapper.insertInterfLog(log);
    }
}
