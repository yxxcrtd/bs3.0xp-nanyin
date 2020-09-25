/*
 * 文件名：SqlldrUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.disclosure.interf.interf.common.imp.ImpArgument;
import com.joyintech.tams.disclosure.interf.interf.common.imp.ImpExcutor;

/**
 * ORACLE导入数据工具类
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月22日
 * @see Sqlldr
 * @since 1.0
 */
public class Sqlldr implements ImpExcutor {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Sqlldr.class);

    /**
     * 创建控制文件
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    public File createCtl(SqlldrImpArgument args)
        throws IOException {
        LOGGER.debug("创建ctl文件");
        // 被导入表
        ImpTable table = args.getTable();
        // 控制文件路径
        String control = args.getControl();
        // 创建控制文件
        File file = new File(control);
        if (!file.exists()) {
            // 如果控制文件不存在，则创建
            file.createNewFile();
        }
        List<String> lines = new ArrayList<>();
        lines.add("load data");
        lines.add("infile \'" + args.getData() + "\'");
        lines.add("append");
        lines.add("into table " + table.getName());
        lines.add("fields terminated by \'" + args.getFieldTerm() + "\'");
        if (args.getOptionTerm() != null) {
            lines.add("optionally enclosed by \'" + args.getOptionTerm() + "\'");
        }
        lines.add("TRAILING NULLCOLS");
        lines.add(table.getColumnsDef());
        FileUtils.writeLines(file, lines);
        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean excute(ImpArgument args)
        throws Exception {
        String sh = "sqlldr ";
        if (!(args instanceof SqlldrImpArgument)) {
            LOGGER.warn("不支持的参数类型:" + args.getClass().getName());
        }
        String command = ((SqlldrImpArgument)args).toString();
        LOGGER.info("执行命令：" + sh + command);
        Process process = Runtime.getRuntime().exec(sh + command);
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            LOGGER.info(line);
        }
        // 输出日志
        printLog(((SqlldrImpArgument)args).getLog());
        // 删除导入用的临时文件
        deleteTmpFile((SqlldrImpArgument)args);
        int exitValue = process.waitFor();
        return exitValue == 0;
    }

    /**
     * 输出日志
     * 
     * @param logFile 日志文件
     */
    private void printLog(String logFile) {
        File log = new File(logFile);
        if (log.exists()) {
            try {
                LOGGER.info(FileUtils.readFileToString(log));
            }
            catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * 删除临时文件
     * 
     * @return
     * @see
     * @since 1.0
     */
    private void deleteTmpFile(SqlldrImpArgument args) {
        // 数据文件
        delete(args.getData());
        // 控制文件
        delete(args.getControl());
        // 日志文件
        delete(args.getLog());
        // 坏数据文件
        String badFile = args.getBad();
        if (badFile == null || badFile.length() == 0) {
            badFile = args.getData().substring(0, args.getData().length() - 4) + ".bad";
        }
        delete(badFile);
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件路径
     */
    private void delete(String filePath) {
        if (filePath != null && filePath.length() > 0) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
