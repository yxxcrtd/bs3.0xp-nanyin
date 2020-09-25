/*
 * 文件名：ImportGenerator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.interf.interf.common.imp;

import java.io.File;
import java.io.FileInputStream;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.service.ApplicationParaService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.db.struct.dto.TableColumn;
import com.joyintech.tams.disclosure.common.db.struct.service.TableStructService;
import com.joyintech.tams.disclosure.interf.framework.util.FtpUtil;
import com.joyintech.tams.disclosure.interf.interf.common.dao.InterfCommonMapper;
import com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr.Sqlldr;
import com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr.SqlldrImpArgument;
import com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr.TableSequence;
import com.joyintech.tams.disclosure.interf.interf.common.util.InterfUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 导入生成工具
 *
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see ImportGenerator
 * @since 1.0
 */
@Component(value="disImportGenerator")
public final class ImportGenerator {

    public static List<ImpColumn> createImpTableColumns(String tableName) {

        TableStructService tableService = SpringContextUtil.getBean(TableStructService.class);

        // 获取数据库中的列定义
        List<TableColumn> dbCols = tableService.selectTableStruct(tableName);
        // 更加数据库列定义生成sqlldr使用的列
        List<ImpColumn> columns = new ArrayList<>();

        // 系统是否使用的db2数据库
        boolean isDB2DataBase = CommonUtil.isDB2DataBase();

        for (TableColumn col : dbCols) {
            ImpColumn column = new ImpColumn(StringUtil.upperCase(col.getColumnName()));
            // CHAR
            if (StringUtil.equalsIgnoreCase(col.getDataType(), JDBCType.CHAR.name())) {
                column.setType(JDBCType.CHAR);
                column.setLength(col.getDataLength());
            }
            // DATE
            else if (StringUtil.equalsIgnoreCase(col.getDataType(), JDBCType.DATE.name())) {
                column.setType(JDBCType.DATE);
            }
            // TIMESTAMP
            else if (StringUtil.startsWithIgnoreCase(col.getDataType(),
                JDBCType.TIMESTAMP.name())) {
                column.setType(JDBCType.TIMESTAMP);
            }

            if (isDB2DataBase) {
                // DECIMAL
                if (StringUtil.startsWithIgnoreCase(col.getDataType(),
                    JDBCType.DECIMAL.name())) {
                    column.setType(JDBCType.DECIMAL);
                }
                // SMALLINT
                else if (StringUtil.startsWithIgnoreCase(col.getDataType(),
                    JDBCType.SMALLINT.name())) {
                    column.setType(JDBCType.SMALLINT);
                }
                // INTEGER
                else if (StringUtil.startsWithIgnoreCase(col.getDataType(),
                    JDBCType.INTEGER.name())) {
                    column.setType(JDBCType.INTEGER);
                }
                // NUMERIC
                else if (StringUtil.startsWithIgnoreCase(col.getDataType(),
                    JDBCType.NUMERIC.name())) {
                    column.setType(JDBCType.NUMERIC);
                }
            }

            columns.add(column);
        }
        return columns;
    }

    /**
     * 创建序列，该方法只能创建单个序列
     *
     * @param colName 列名
     * @param seqName 序列名
     * @return 单个序列组成的list
     */
    public static List<TableSequence> createSequence(String colName, String seqName) {
        return Arrays.asList(new TableSequence(colName, seqName));
    }

    /**
     * 创建维护数据txt内容
     *
     * @param data Object
     * @param columns 数据表对应的列
     * @return 字符串
     */
    public static String buildTxt(Object data, List<ImpColumn> columns) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> valueMap = BeanUtil.object2Map(data);

        // db2数据库对应
        // 系统是否使用的db2数据库
        boolean isDB2DataBase = CommonUtil.isDB2DataBase();

        for (ImpColumn col : columns) {
            if (isDB2DataBase || col.isValid()) {
                JDBCType type = col.getType();
                String name = CommonUtil.dealString(StringUtil.lowerCase(col.getName()), false);
                Object value = valueMap.get(name);
                // 如果valuefield被指定
                if (!StringUtil.isNotEmpty(value) && StringUtil.isNotEmpty(col.getValueField())) {
                    value = valueMap.get(col.getValueField());
                }
                if (null != value && value instanceof Date) {
                    if (type.equals(JDBCType.DATE)) {
                        value = InterfUtil.formatDateWithQuotation((Date)value);
                    }
                    else if (type.equals(JDBCType.TIMESTAMP)) {
                        value = InterfUtil.formatDateTimeWithQuotation((Date)value);
                    }
                }
                // 如果值为空，则判断该字段是否需要设置默认值
                if (!StringUtil.isNotEmpty(value)
                    && StringUtil.isNotEmpty(col.getDefaultValue())) {
                    // 数值类型
                    value = col.getDefaultValue();
                }

                // db2数据库对应
                if (isDB2DataBase) {
                    String valueStr = CommonUtil.getNotNullString(value);
                    if (StringUtil.isBlank(valueStr) &&
                        (type.equals(JDBCType.DATE) || type.equals(JDBCType.TIMESTAMP))) {
                        // 如果值为空，且是Date或者TIMESTAMP，则不需要生成双引号
                    }
                    else if (type.equals(JDBCType.DECIMAL) || type.equals(JDBCType.SMALLINT)
                             || type.equals(JDBCType.INTEGER) || type.equals(JDBCType.NUMERIC)) {
                        // 数值型项目，不需要生成双引号
                    }
                    else if (!StringUtil.contains(valueStr, "\"")) {
                        valueStr = "\"" + valueStr + "\"";
                    }

                    if (sb.length() == 0) {
                        sb.append(valueStr);
                    }
                    else {
                        sb.append(GlobalConstant.COMMA).append(valueStr);
                    }
                }
                else {
                    // oracle版本原处理
                    if (sb.length() == 0) {
                        sb.append(CommonUtil.getNotNullString(value));
                    }
                    else {
//                        sb.append(InterfUtil.getTxtDataString(value));
                        sb.append(getTxtDataString(value));
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 输出对象的字符串表示
     *
     * @param value
     * @return 对象的字符串表示
     */
    public static String getTxtDataString(Object value) {
        ApplicationParaService applicationParaService = SpringContextUtil.getBean(
            ApplicationParaService.class);

        String reportInterfSplitCharacter = applicationParaService.getReportInterfSplitCharacter();
        if (StringUtil.isBlank(reportInterfSplitCharacter)) {
            reportInterfSplitCharacter = GlobalConstant.VERTICAL_LINE;
        }

        return reportInterfSplitCharacter + CommonUtil.getNotNullString(value);
    }

    /**
     * 导入数据库
     *
     * @param table 导入表配置
     * @param path 路径
     * @param dataFileName 数据文件名
     * @param content 导入数据内容
     * @throws Exception 异常
     * @return 导入是否成功
     */
    public static boolean importToDB(ImpTable table, String path, String dataFileName,
                                     List<String> content, Integer reportInterfOneTimeSqlLdrCommitCount, String reportSqlldrDatabaseName)
        throws Exception {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        String absoultPath = path + dataFileName;
        File tmpFile = new File(absoultPath);
        FileUtils.writeLines(tmpFile, "UTF-8", content);

        // 系统是否使用的db2数据库
        boolean isDB2DataBase = CommonUtil.isDB2DataBase();
        if (isDB2DataBase) {
            // 将数据文件通过ftp上传到数据库服务器
            String REP_DB2_SERVER_FTP_IP = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_IP);
            String REP_DB2_SERVER_FTP_USER = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_USER);
            String REP_DB2_SERVER_FTP_PASSWORD = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_PASSWORD);
            String REP_DB2_SERVER_FTP_PROT = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_PROT);
            String REP_DB2_SERVER_FTP_FILEPATH = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_FILEPATH);

            int ftpPort = Integer.valueOf(REP_DB2_SERVER_FTP_PROT);

            FtpUtil.uploadFile(REP_DB2_SERVER_FTP_IP, ftpPort, REP_DB2_SERVER_FTP_USER,
                REP_DB2_SERVER_FTP_PASSWORD, REP_DB2_SERVER_FTP_FILEPATH, dataFileName,
                new FileInputStream(tmpFile));

            String importFullPath = REP_DB2_SERVER_FTP_FILEPATH;
            if (!StringUtil.endsWith(importFullPath, "/")) {
                importFullPath = importFullPath + "/";
            }
            importFullPath = importFullPath + dataFileName;

            // 执行import命令导入数据文件
            InterfCommonMapper interfCommonMapper = SpringContextUtil.getBean(
                InterfCommonMapper.class);
            interfCommonMapper.excuteDb2ImportData(importFullPath, table.getName());

            // 删除数据文件
            if (tmpFile.exists()) {
                tmpFile.delete();
            }

            // 删除ftp服务器数据文件
            FtpUtil.deleteFile(REP_DB2_SERVER_FTP_IP, ftpPort, REP_DB2_SERVER_FTP_USER,
                REP_DB2_SERVER_FTP_PASSWORD, REP_DB2_SERVER_FTP_FILEPATH, dataFileName);

            return true;
        }
        else {
            // oracle原有逻辑
            SqlldrImpArgument param = new SqlldrImpArgument();
            param.setData(absoultPath);
            param.setControl(path + StringUtil.lowerCase(table.getName()) + ".ctl");
            param.setLog(path + StringUtil.lowerCase(table.getName()) + ".log");
            param.setTable(table);
            param.setRows(reportInterfOneTimeSqlLdrCommitCount);

            // add by etliu 20180926 start
            System.out.println("DatabaseName重置前值：" + param.getDatabaseName());
            param.setDatabaseName(reportSqlldrDatabaseName);
            System.out.println("DatabaseName重置后值：" + param.getDatabaseName());
            // add by etliu 20180926 end

            // add by etliu 20181018 start
            ApplicationParaService applicationParaService = SpringContextUtil.getBean(
                ApplicationParaService.class);

            String reportInterfSplitCharacter = applicationParaService.getReportInterfSplitCharacter();
            if (StringUtil.isNotBlank(reportInterfSplitCharacter)) {
                param.setFieldTerm(reportInterfSplitCharacter);
            }
            // add by etliu 20181018 end

            Sqlldr sqlldr = new Sqlldr();
            sqlldr.createCtl(param);
            return sqlldr.excute(param);
        }
    }

    /**
     * 导入数据库(单次最大200000，分批处理)
     *
     * @param table 导入表配置
     * @param path 路径
     * @param dataFileName 数据文件名
     * @param content 导入数据内容
     * @throws Exception 异常
     * @return 导入是否成功
     */
    public static boolean importToDBByPart(ImpTable table, String path, List<String> content,
                                           Integer reportInterfOneTimeSqlLdrDataCount,
                                           Integer reportInterfOneTimeSqlLdrCommitCount,
                                           String sqlDataFileExtension,
                                           String reportSqlldrDatabaseName)
        throws Exception {
        if (StringUtil.isEmptyList(content)) {
            return true;
        }

        boolean result = true;

        int dataCount = content.size();
        if (dataCount <= reportInterfOneTimeSqlLdrDataCount) {
            return ImportGenerator.importToDB(table, path,
                table.getName() + FileNameUtil.createFileName(sqlDataFileExtension), content,
                reportInterfOneTimeSqlLdrCommitCount, reportSqlldrDatabaseName);
        }

        List<String> newDataList = new ArrayList<String>();
        newDataList.addAll(content);

        // 分批处理
        int part = dataCount / reportInterfOneTimeSqlLdrDataCount; // 分批数
        for (int i = 0; i < part; i++ ) {
            result = result && ImportGenerator.importToDB(table, path,
                table.getName() + FileNameUtil.createFileName(sqlDataFileExtension),
                newDataList.subList(0, reportInterfOneTimeSqlLdrDataCount),
                reportInterfOneTimeSqlLdrCommitCount, reportSqlldrDatabaseName);
            newDataList.subList(0, reportInterfOneTimeSqlLdrDataCount).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            result = result && ImportGenerator.importToDB(table, path,
                table.getName() + FileNameUtil.createFileName(sqlDataFileExtension), newDataList,
                reportInterfOneTimeSqlLdrCommitCount, reportSqlldrDatabaseName);
        }

        return result;
    }
}
