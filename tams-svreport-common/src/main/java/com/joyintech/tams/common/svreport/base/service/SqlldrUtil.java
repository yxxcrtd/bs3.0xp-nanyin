package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.dao.BaseCheckMysqlMapper;
import com.joyintech.tams.common.svreport.base.dto.BaseCheckMysqlDto;
import com.joyintech.tams.common.svreport.base.dto.BaseCommonCheckDto;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * @author zcw
 * @date 2022-03-31 14:52
 */
@Component
public class SqlldrUtil {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 上游接口解析读取文件处理最大线程数
     */
    @Value("${application.interfMaxCorePoolSize}")
    private int interfMaxCorePoolSize;

    /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrCommitCount}")
    private int reportInterfOneTimeSqlLdrCommitCount;

    /**
     * sqlldr用数据库连接
     */
    @Value("${spring.datasource.reportSqlldrDatabaseName}")
    private String reportSqlldrDatabaseName;

    /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrDataCount}")
    private int reportInterfOneTimeSqlLdrDataCount;

    /**
     * SQLLDR共通方法
     * @param tmpPath
     * @param tablename
     * @param list
     * @param table
     */
    public void exeSqlldr(String tmpPath, String tablename, List<? extends BaseEntity> list, ImpTable table) {
        logger.info("执行sqlldr开始---入表："+tablename +" 时间"+ DateUtil.getCurrentDateTime());

        // 创建一个线程池
        if (interfMaxCorePoolSize < 1) {
            interfMaxCorePoolSize = 20;
        }
        int size = list.size();
        int max = (int) Math.ceil((double) size / 1000);
        ExecutorService service = new JoyinThreadPoolExecutor( max>20?interfMaxCorePoolSize : max);
        List<Callable<Boolean>> tasks = new ArrayList<>();
        if (null != list) {
            int start = 0;
            int end = 0;
            for (int i = 0; i < max; i++) {
                start = end;
                end = Math.min(size, 1000 * (i + 1));
                List<? extends BaseEntity> subList = list.subList(start, end);
                logger.debug("生成sqlloader文件"+i);
                tasks.add(new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        logger.debug("生成sqlloader文件----开始-----");
                        createDataFile(tmpPath + tablename, subList, table.getColumns());
                        logger.debug("生成sqlloader文件----结束-----:");
                        return true;
                    }
                });
            }
        }
        // 为线程池添加任务
        List<Future<Boolean>> taskResult;
        try {
            taskResult = service.invokeAll(tasks);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            return;
        }
        for (Future<Boolean> res : taskResult) {
            try {
                res.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }
        // 任务结束，停止线程池的所有线程
        service.shutdown();
        Collection<File> files = FileUtils.listFiles(new SafelyFile(tmpPath), new String[] { "tmp" }, false);
        logger.debug("sqlloder文件个数"+files.size());
        List<String> content = new ArrayList<>(list.size());
        for (File f : files) {
            if (f.getName().startsWith(tablename)) {
                try {
                    content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
                    logger.debug("查找sqlloder文件"+f);
                } catch (IOException e) {
                    logger.debug("查找sqlloder文件异常");
                    logger.error(e.getMessage(), e);
                } finally {
                    f.delete();
                }
            }
        }
        logger.debug("读取sqlloder文件共"+content.size()+"条");
        // 系统是否使用的db2数据库
        boolean isDB2DataBase = CommonUtil.isDB2DataBase();
        if (isDB2DataBase) {
            // db2数据库处理
            if (CollectionUtils.isNotEmpty(content)) {
                try {
                    ImportGenerator.importToDBByPart(table, tmpPath+tablename+"/", content,
                            reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "del",
                            reportSqlldrDatabaseName);
                } catch (IOException | InterruptedException e) {
                    logger.error(e.getMessage(), e);
                    return;
                }
            }
        } else {
            // oracle原逻辑
            if (CollectionUtils.isNotEmpty(content)) {
                try {
                    logger.debug("sqlloader入库开始");
                    ImportGenerator.importToDBByPart(table, tmpPath+tablename+"/", content,
                            reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
                            reportSqlldrDatabaseName);
                    logger.debug("sqlloader入库结束");
                } catch (IOException | InterruptedException e) {
                    logger.debug("sqlloader入库报错");
                    logger.error(e.getMessage(), e);
                    return;
                }
            }
        }
        logger.info("执行sqlldr结束---入表："+tablename +" 时间"+ DateUtil.getCurrentDateTime());
    }

    /**
     * 创建数据文件
     *
     * @param path 文件路径
     * @param baseinfoList 对象列表
     * @param columns 数据库表对应的列
     */
    public void createDataFile(String path, List<? extends BaseEntity> baseinfoList, List<ImpColumn> columns) {
        // 生成txt文件
        List<String> lines = new ArrayList<>(baseinfoList.size());
        baseinfoList.forEach(item -> lines.add(ImportGenerator.buildTxt(item, columns)));
        try {
            FileUtils.writeLines(
                    new SafelyFile(path + GlobalConstant.UNDER_LINE + UUID.randomUUID().toString() + ".tmp"),
                    "UTF-8", lines, true);
        }
        catch (IOException e) {
            e.getMessage();
            logger.error(e.getMessage(),e);
        }
    }

}
