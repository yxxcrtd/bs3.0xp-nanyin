/*
 * 文件名：ImportGenerator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.interf.common.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.service.ApplicationParaService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.db.struct.dto.TableColumn;
import com.joyintech.tams.report.db.struct.service.SvrTableStructService;
import com.joyintech.tams.report.framework.util.FtpUtil;
import com.joyintech.tams.report.interf.common.dao.SvrInterfCommonMapper;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.report.interf.common.imp.sqlldr.Sqlldr;
import com.joyintech.tams.report.interf.common.imp.sqlldr.SqlldrImpArgument;
import com.joyintech.tams.report.interf.common.imp.sqlldr.TableSequence;
import com.joyintech.tams.report.interf.common.util.InterfUtil;

/**
 * 导入生成工具
 *
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see ImportGenerator
 * @since 1.0
 */
@Component
public final class ImportGenerator {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ImportGenerator.class);
    public static List<ImpColumn> createImpTableColumns(String tableName) {

        SvrTableStructService tableService = SpringContextUtil.getBean(SvrTableStructService.class);

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
                    else if (type.equals(JDBCType.VARCHAR)) {
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
                    if (sb.length() == 0 && columns.get(0).getName().equals(col.getName())) {
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
     * @return 导入是否成功
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static boolean importToDB(ImpTable table, String path, String dataFileName,
                                     List<String> content, Integer reportInterfOneTimeSqlLdrCommitCount, String reportSqlldrDatabaseName) throws IOException, InterruptedException {
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
            String repDb2ServerFtpIp = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_IP);
            String repDb2ServerFtpUser = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_USER);
            String repDb2ServerFtpPassword = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_PASSWORD);
            String repDb2ServerFtpProt = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_PROT);
            String repDb2ServerFtpFilepath = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_DB2_SERVER_FTP_FILEPATH);

            int ftpPort = Integer.valueOf(repDb2ServerFtpProt);

            FtpUtil.uploadFile(repDb2ServerFtpIp, ftpPort, repDb2ServerFtpUser,
            		repDb2ServerFtpPassword, repDb2ServerFtpFilepath, dataFileName,
                new FileInputStream(tmpFile));

            String importFullPath = repDb2ServerFtpFilepath;
            if (!StringUtil.endsWith(importFullPath, "/")) {
                importFullPath = importFullPath + "/";
            }
            importFullPath = importFullPath + dataFileName;

            // 执行import命令导入数据文件
            SvrInterfCommonMapper interfCommonMapper = SpringContextUtil.getBean(
                SvrInterfCommonMapper.class);
            interfCommonMapper.excuteDb2ImportData(importFullPath, table.getName());

            // 删除数据文件
            if (tmpFile.exists()) {
                tmpFile.delete();
            }

            // 删除ftp服务器数据文件
            FtpUtil.deleteFile(repDb2ServerFtpIp, ftpPort, repDb2ServerFtpUser,
            		repDb2ServerFtpPassword, repDb2ServerFtpFilepath, dataFileName);

            return true;
        }
        // 合并mysql早期改造代码  add by zoujiamin  2021/04/20
        boolean isMySqlDataBase = CommonUtil.isMySqlDataBase(); 
        if(isMySqlDataBase){
        	 // 将数据文件通过ftp上传到数据库服务器
            String repMysqlServerFtpIp = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_MYSQL_SERVER_FTP_IP);
            String repMysqlServerFtpUser = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_MYSQL_SERVER_FTP_USER);
            String repMysqlServerFtpPassword = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_MYSQL_SERVER_FTP_PASSWORD);
            String repMysqlServerFtpProt = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_MYSQL_SERVER_FTP_PROT);
            String repMysqlServerFtpFilepath = CacheUtil.getSysParam(
                ReportGlobalConstant.SYS_PARA_REP_MYSQL_SERVER_FTP_FILEPATH);

            int ftpPort = Integer.valueOf(repMysqlServerFtpProt);

            String importFullPath = repMysqlServerFtpFilepath;
            if (!StringUtil.endsWith(importFullPath, "/")) {
                importFullPath = importFullPath + "/";
            }
            importFullPath = importFullPath + dataFileName;

            // 执行远程数据库服务器shell命令导入数据文件
            Session session = null;
            ChannelExec openChannel = null;
            Channel sftpchannel = null;
            ChannelSftp sftp = null;
            JSch jsch = new JSch();
            BufferedReader reader = null;
            OutputStreamWriter osw = null;
            FileOutputStream fout = null;
            // getSession()只是创建一个session，需要设置必要的认证信息之后，调用connect()才能建立连接。
            try {
				session = jsch.getSession(repMysqlServerFtpUser, repMysqlServerFtpIp, ftpPort);
	            java.util.Properties config = new java.util.Properties();
	            config.put("StrictHostKeyChecking", "no");
	            session.setConfig(config);
	            session.setPassword(repMysqlServerFtpPassword);
	            session.connect();

	            //上传文件到数据库服务器
	            sftpchannel = session.openChannel("sftp");
	            sftpchannel.connect();
	            sftp = (ChannelSftp)sftpchannel;
	            if (!StringUtil.endsWith(importFullPath, "/")) {
	            	sftp.put(path + dataFileName, repMysqlServerFtpFilepath);
	            }else{
	            	sftp.put(path + "/" + dataFileName, repMysqlServerFtpFilepath);
	            }
	            
	            openChannel = (ChannelExec) session.openChannel("exec");
	            openChannel.setInputStream(null);
	            openChannel.setErrStream(System.err);
	            //sh /home/tmp/test.sh home/tmp/test.txt
	            openChannel.setCommand("sh " + repMysqlServerFtpFilepath  + "/" + table.getName()+".sh " + repMysqlServerFtpFilepath+"/"+dataFileName);
	            openChannel.connect(); 

	            reader = new BufferedReader(new InputStreamReader(openChannel.getInputStream(),Charset.forName("UTF-8")));
	            String line = null;
	            String sqlldrLogFilePath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, "SQLLDRLOGFILEPATH");
				logger.info("sqlldr输出日志路径："+sqlldrLogFilePath);
				File file = new File(sqlldrLogFilePath+File.separator+"sqlldr_log.txt");
       	    	//判断sqlldr输出日志路径下是否存在日志文件，若存在则加上时间戳
				if(file.exists()){
					file = new File(sqlldrLogFilePath+File.separator+"sqlldr_log_"+System.currentTimeMillis()+".txt");
				}
	            fout = new FileOutputStream(file);
				osw = new OutputStreamWriter(fout);
	            while ((line = reader.readLine()) != null) {
	            	osw.append(line+"\n");
					osw.flush();
	            }
	            reader.close();
	            logger.info(line);

	            // 删除数据文件
	            if (tmpFile.exists()) {
	                tmpFile.delete();
	            }
	            
	            // 删除ftp服务器数据文件
//	            sftp.rm(repMysqlServerFtpFilepath+"/"+dataFileName);
	            
			} catch (JSchException | SftpException e) {
				e.printStackTrace();
			}finally{
				if (reader != null){
					reader.close();
				}
	            if (openChannel != null && !openChannel.isClosed()) {
	                openChannel.disconnect();
	            }
	            if (session != null && session.isConnected()) {
	                session.disconnect();
	            }
	            if(sftpchannel != null && !sftpchannel.isClosed()){
	            	sftpchannel.disconnect();
	            }
	            if(sftp != null && !sftp.isClosed()){
	            	sftp.disconnect();
	            }
			}

            return true;
        }
        // 合并mysql早期改造代码  add by zoujiamin  2021/04/20

        else {
            // oracle原有逻辑
            SqlldrImpArgument param = new SqlldrImpArgument();
            param.setData(absoultPath);
            param.setControl(path + StringUtil.lowerCase(table.getName()) + ".ctl");
            param.setLog(path + StringUtil.lowerCase(table.getName()) + ".log");
            param.setTable(table);
            param.setRows(reportInterfOneTimeSqlLdrCommitCount);

            // add by etliu 20180926 start
//            System.out.println("DatabaseName重置前值：" + param.getDatabaseName());
            logger.info("DatabaseName重置前值：{}", param.getDatabaseName());
            param.setDatabaseName(reportSqlldrDatabaseName);
//            System.out.println("DatabaseName重置后值：" + param.getDatabaseName());
            logger.info("DatabaseName重置后值：{}", param.getDatabaseName());
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
     * @return 导入是否成功
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static boolean importToDBByPart(ImpTable table, String path, List<String> content,
                                           Integer reportInterfOneTimeSqlLdrDataCount,
                                           Integer reportInterfOneTimeSqlLdrCommitCount,
                                           String sqlDataFileExtension,
                                           String reportSqlldrDatabaseName) throws IOException, InterruptedException {
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
