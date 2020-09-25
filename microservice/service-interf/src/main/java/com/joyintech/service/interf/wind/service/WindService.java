/*
 * 文件名：WindService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCode;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;

/**
 * wind数据接入Device
 * 
 * @param <E> 参数
 * @author zhangrui1
 * @version 1.0 2018年11月11日
 * @see WindService
 * @since 1.0
 */
public abstract class WindService<E> extends InterfBaseService {

    /**
     * 登录业务表用户名
     */
    public static final String WIND_USER = "wind";

    /**
     * 数据字典项Service
     */
    @Autowired
    private SysDatadictListService dictService;

    /**
     * 查询wind数据
     * 
     * @param sysDate 平台日期
     * @param startNum 开始行
     * @param endNum 结束行
     * @return 数据列表
     * @see
     * @since 1.0
     */
    public abstract List<E> getDataList(String sysDate, long startNum, long endNum);

    /**
     * 表登录操作
     * 
     * @param entity WIND数据
     * @return 登录件数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public abstract int insert(E entity)
        throws Exception;

    /**
     * 执行入口
     * 
     * @param dto batch信息
     * @param list 登录数据
     * @return 执行结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public BatchResultDto executeService(BatchResultDto dto, List<?> list) {
        // 获取事务控制管理器
        DataSourceTransactionManager transactionManager = getTransactionManager();
        // 获取事务控制管理器
        TransactionStatus status = getTransactionStatus();
        int cnt = 0; // 记录件数，当达到100件时提交事务
        List<E> eList = new ArrayList<>();
        if (StringUtil.isEmptyList(list)) {
            setErrMsg("无数据！");
            getLogger().info("无数据！");
        }
        else {
            for (Object obj : list) {
                if (cnt == 100) {
                    transactionManager.commit(status);
                    status = getTransactionStatus();
                    cnt = 0;
                }
                E entity = (E)obj;
                try {
                    cnt += insert(entity);
                    eList.add(entity);
                }
                catch (Exception e) {
                    doException(e, "wind数据接入异常：" + cnt);
                }
            }
        }
        transactionManager.commit(status);
        dto.setResult(isHandleOK());
        dto.setErrMsg(getErrMsg());
        return dto;
    }

    /**
     * 初始化变量
     * 
     * @param uuid 处理识别号
     * @see
     * @since 1.0
     */
    public void init(String uuid) {
        // 初始化处理结果
        setHandleOK(true);
        // 设置处理识别号
        setUuid(uuid);
        // 初始化错误信息
        setErrMsg(StringUtil.EMPTY);
    }

    /**
     * 取得数据代码
     * 
     * @param dictCode 字典代码
     * @param itemValue 参数值
     * @return 数据代码
     * @see
     * @since 1.0
     */
    public String getItemCode(String dictCode, String itemValue) {
        return dictService.getItemCode(dictCode, itemValue);
    }

    /**
     * 数据字典转换
     * 
     * @param dict 数据字典
     * @param busiDict 数据字典
     * @param ratingType 参数值
     * @return 数据字典code名称
     * @see
     * @since 1.0
     */
    public String changeRateType(InterfDictCode dict, String busiDict, String ratingType) {
        InterfDictCodeList code = InterfDictCodeList.from(dict, ratingType);
        return getItemCode(busiDict, code == null ? ratingType : code.getCodeName());
    }

    /**
     * 转换债券代码
     * 
     * @param secCode wind债券代码
     * @return 转换后债券代码
     * @see
     * @since 1.0
     */
    public String changeSecCode(String secCode) {
        String market = StringUtil.substring(secCode, 0, 2);
        String secId = StringUtil.substring(secCode, 2);
        return secId + market;
    }
}
