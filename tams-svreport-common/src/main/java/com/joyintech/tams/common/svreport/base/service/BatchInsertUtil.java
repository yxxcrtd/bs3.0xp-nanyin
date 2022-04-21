package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.DateUtil;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.svreport.base.dao.BaseCheckMysqlMapper;
import com.joyintech.tams.common.svreport.base.dto.BaseCheckMysqlDto;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.systemtools.util.CommonUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author zcw
 * @date 2022-03-31 14:52
 */
@Component
public class BatchInsertUtil {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BatchInsertUtil.class);

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



    private Connection conn = null;
    @Value("${spring.datasource.driverClassName}")
    private String driverName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

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
    /**
     * 获取原生jdbc连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private  Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    /**
     * 组装 SQL 语句
     *
     * @param dataBaseName 数据库名。
     * @param tableName    表名。
     */
    public String assembleSql(String dataBaseName, String tableName) {
        BaseCheckMysqlMapper baseCheckMysqlMapper = SpringContextUtil.getBean(BaseCheckMysqlMapper.class);
        String[] schema = url.split("/");
        List<String> columnList = baseCheckMysqlMapper.select(schema[3].substring(0,schema[3].indexOf("?")),tableName).stream().map(ele -> ele.getColumnName()).collect(Collectors.toList());
        String insertColumnName = StringUtils.join(columnList, ",");
        //插入方案的核心就是下面这个语句
        String sql = "LOAD DATA LOCAL INFILE 'sql.csv' IGNORE INTO TABLE " + dataBaseName + "." + tableName + " (" + insertColumnName + ")";
        return sql;
    }



    /**
     * 将数据从输入流加载到MySQL。
     *
     * @param dataStream  输入流。
     * @return int         成功插入的行数。
     */
    public int bulkLoadFromInputStream(String dataBaseName, String tableName,
                                        InputStream dataStream) throws SQLException, ClassNotFoundException, IOException {
        if (null == dataStream) {
            logger.info("输入流为NULL，没有数据导入。");
            return 0;
        }
        String assembleSql = assembleSql(dataBaseName, tableName);
        //做测试发现连接池好像不支持，所以获取原生的jdbc连接
        conn = getConnection();
        PreparedStatement statement = null;
        int result = 0;
        try {
            statement = conn.prepareStatement(assembleSql);
//            mysql8  使用下面的方式
//        if (statement.isWrapperFor(JdbcStatement.class)) {
//            ClientPreparedStatement mysqlStatement = statement.unwrap(ClientPreparedStatement.class);
//            mysqlStatement.setLocalInfileInputStream(dataStream);
//            mysqlStatement.executeUpdate();
//        }
//        mysql5 使用下面的方式
            if (statement.isWrapperFor(com.mysql.jdbc.Statement.class)) {
                com.mysql.jdbc.PreparedStatement mysqlStatement = statement.unwrap(com.mysql.jdbc.PreparedStatement.class);
                mysqlStatement.setLocalInfileInputStream(dataStream);
                result = mysqlStatement.executeUpdate();
            }
        } finally {
            if (null != statement) {
                statement.close();
            }
            if(null !=dataStream){
                dataStream.close();
            }
            if(null !=conn){
                conn.close();
            }
        }
        return result;
    }
    /**
     * 往 StringBuilder 里追加数据。
     *
     * @param builder StringBuilder。
     * @param object  数据。
     */
    public  void builderAppend(StringBuilder builder, Object object) {
        builder.append(object);
        builder.append("\t");
    }

    /**
     * 往 StringBuilder 里追加一条数据的最后一个字段。
     *
     * @param builder StringBuilder。
     * @param object  数据。
     */
    public  void builderEnd(StringBuilder builder, Object object) {
        builder.append(object);
        builder.append("\n");
    }


}
