/*
 * 文件名：OdsFileReadService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods.service;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.SqlldrColsEnum;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.ods.sqlldr.CtlArg;
import com.joyintech.service.interf.ods.sqlldr.SqlldrArg;
import com.joyintech.service.interf.ods.sqlldr.SqlldrExecutor;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * ODS文件读取服务
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月25日
 * @see OdsFileReadService
 * @since 1.0
 */
@Service
public class OdsFileReadService extends InterfBaseService {

    /**
     * sqlldr执行log文件路径
     */
    private static final String LOG_PATH = "logPath";

    /**
     * sqlldr执行出错文件路径
     */
    private static final String BAD_PATH = "badPath";

    /**
     * 数据文件扩展名
     */
    private static final String FILE_EXT = "fileExt";

    /**
     * ods文件路径KEY
     */
    private static final String ODS_FILE_PATH = "odsFilePath";

    /**
     * 接口ID
     */
    private static final String INTERFID = "interfId";

    /**
     * 报文流水号
     */
    private static final String SEQ_NO = "seqNo";

    /**
     * 变量初始化
     * 
     * @param uuid 处理识别号
     * @param config 连接配置
     * @param interfId 接口ID
     * @param seqNo 报文流水号
     * @see
     * @since 1.0
     */
    public void init(String uuid, String interfId, String seqNo) {
        setUuid(uuid);
        setHandleOK(true);
        setErrMsg(StringUtil.EMPTY);
        setServiceInfo(INTERFID, interfId);
        setServiceInfo(SEQ_NO, seqNo);
    }

    /**
     * 文件导入表
     * 
     * @param tableName 表名
     * @param file 文件全名
     * @see
     * @since 1.0
     */
    public void loadFileIntoTable(@NotNull String tableName, String file) {
        Map<String, Object> interfCache = getSysParamMap();
        if (StringUtil.isEmpty(file)) {
            String path = (String)interfCache.get(ODS_FILE_PATH);
            // 规范化文件路径
            path = InterfUtil.pathEndAsSeparator(path);
            // 数据文件名：TABLENAME_yyyyMMdd.txt
            String name = SqlldrColsEnum.getDataFileByTable(tableName) + InterfConstant.UNDERLINE
                          + getSysDate() + "." + (String)interfCache.get(FILE_EXT);
            // 取得ODS文件路径
            file = path + name;
        }
        loadFile(file, tableName);
    }

    /**
     * 解析文件数据入库
     * 
     * @param file 文件名
     * @param tableName 表名
     * @see
     * @since 1.0
     */
    private void loadFile(@NotNull String file, @NotNull String tableName) {
        // 取得系统参数
        Map<String, Object> interfCache = getSysParamMap();
        // 日志文件名：path/TABLENAME_yyyyMMdd.log
        String log = InterfUtil.pathEndAsSeparator((String)interfCache.get(LOG_PATH)) + tableName
                     + InterfConstant.UNDERLINE + getSysDate() + ".log";
        // 坏数据文件名：path/TABLENAME_yyyyMMdd.bad
        String bad = InterfUtil.pathEndAsSeparator((String)interfCache.get(BAD_PATH)) + tableName
                     + InterfConstant.UNDERLINE + getSysDate() + ".bad";
        // sqlldr命令构建
        SqlldrArg sqlldr = new SqlldrArg();
        sqlldr.setLog(log);
        sqlldr.setBad(bad);
        // control控制文件构建
        CtlArg ctl = new CtlArg(tableName, file);

        try {
            // 执行命令
            SqlldrExecutor.execute(ctl, sqlldr);
        }
        catch (Exception e) {
            doException(e, "sqlldr执行异常:");
        }
        addInterfLog((String)getServiceInfo(INTERFID), (String)getServiceInfo(SEQ_NO),
            DictCodeList.是否_否.getValue(), StringUtil.EMPTY, StringUtil.EMPTY);
    }
}
