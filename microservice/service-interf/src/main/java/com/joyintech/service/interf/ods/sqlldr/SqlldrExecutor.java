/*
 * 文件名：SqlldrExecutor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods.sqlldr;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.service.interf.cache.service.SysParamInfService;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.ods.Cmd;
import com.joyintech.service.interf.ods.Ctl;
import com.joyintech.tools.util.FileUtil;

/**
 * Sqlldr执行类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月3日
 * @see SqlldrExecutor
 * @since 1.0
 */
public class SqlldrExecutor {

    /**
     * sqlldr执行控制文件路径
     */
    private static final String CTL_PATH = "ctlPath";

    /**
     * 使用外部存在的控制文件参数KEY
     */
    private static final String EXISTS_CTL = "existsCtl";

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlldrExecutor.class);

    private SqlldrExecutor() {
        // 私有化构造
    }

    /**
     * 执行sqlldr命令
     * 
     * @param ctl 控制文件
     * @param cmd sqlldr命令
     * @return 执行结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static boolean execute(Ctl ctl, Cmd cmd)
        throws Exception {

        // 取得系统参数
        SysParamInfService paraService = ServiceMapperUtil.getApplicationContext().getBean(
            SysParamInfService.class);
        Map<String, Object> interfCache = paraService.interfCache();

        // 系统参数中取得控制文件路径
        String ctlPath = InterfUtil.pathEndAsSeparator((String)interfCache.get(CTL_PATH));

        // 系统参数中取得是否使用已有的控制文件
        boolean exeExistsFile = Boolean.parseBoolean((String)interfCache.get(EXISTS_CTL));
        File file = null;
        if (exeExistsFile) {
            // 如果使用已有的控制文件，那么以表名作为KEY从系统参数取出控制文件全名
            String table = ((CtlArg)ctl).getTableName();
            String ctlFile = (String)interfCache.get(table);
            file = new File(ctlFile);
        }
        else {
            file = ctl.getCtlFile(ctlPath);
        }
        if (file.exists()) {
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd.getCmd(file.getAbsolutePath()));
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }
            // 输出日志
            printLog(((SqlldrArg)cmd).getLog());
            // 删除导入用的临时文件
            deleteTmpFile((SqlldrArg)cmd);
            int exitValue = process.waitFor();
            return exitValue == 0;
        }
        LOGGER.info("控制文件不存在");
        return false;
    }

    /**
     * 输出日志
     * 
     * @param logFile 日志文件
     */
    private static void printLog(String logFile) {
        File log = new File(logFile);
        if (log.exists()) {
            try {
                String msg = FileUtils.readFileToString(log);
                LOGGER.info(msg);
            }
            catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * 删除临时文件
     * 
     * @param args 命令参数
     * @see
     * @since 1.0
     */
    private static void deleteTmpFile(SqlldrArg args) {
        // 控制文件
        FileUtil.deleteFile(args.getControl());
        // 日志文件
        FileUtil.deleteFile(args.getLog());
        // 坏数据文件
        FileUtil.deleteFile(args.getBad());
    }
}
