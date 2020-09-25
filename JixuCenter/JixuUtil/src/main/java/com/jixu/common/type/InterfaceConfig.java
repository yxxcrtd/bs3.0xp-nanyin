package com.jixu.common.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName InterfaceConfig
 * @author Pan Wei
 * @Date 2016年11月30日 下午12:57:31
 * @Description []
 */

public class InterfaceConfig {
	public final static int dbf = 1;
	public final static int txt = 2;
	public final static int excel = 3;
	public final static int csv = 4;
	public final static int xml = 5;
	public final static int gz = 6;
    public final static int gzcsv = 7;
    public final static int hengshengTA = 8;
	
	public final static int oracle=11;
	public final static int mysql=12;
	public final static int sqlserver=13;
	public final static int db2=14;
	public final static int access=15;
	

	public final static int baseType = 1;	//基础列
	public final static int advanceType = 2;	//增强列
	public final static int filterType = 3;		//过滤
	public final static int groupType = 4;		//分组
	public final static int groupAdvanceType = 5;	//分组增强
	public final static int globalType = 6;			//全局变量
	public final static int exportBaseType = 7;	//导出
	public final static int exportFinalType = 8;	
	public final static int paramsType = 9;		//sql参数
	public final static int mergeKey=10;	//并表的复合主键
	public final static int mergeDelKey=11;	//并表先删除的复合主键
	public final static int paramsCycleType = 12;	//循环参数
	public final static int paramsOut = 13;	//外部传参调用
	public final static int mergeKeyNullEnable = 14;	//并表的复合主键(null值有效)
	
	
	public final static int fileType = 0;
	public final static int dbType = 1;
	public final static int mqType = 2;
	public final static int ftpType = 3;
	public final static int SftpType = 4;

	public final static String fieldStart = "@##@";
	public final static String fieldEnd = "@&@";

	public final static int filterOr = 0;
	public final static int filterAnd = 1;
	
	public final static String sqlBody="SQLBody";
	
	
	public final static String Global_Folder="GlobalFolder";
	public final static String Global_FileName="GlobalFileName";
	public final static String Global_LedgerDate="GlobalLedgerDate";
	public final static String Global_ExeUserID="GlobalExeUserID";
	public final static String Global_AdapterID="GlobalAdapterID";
	
	
	public static HashMap<Integer, String> channelTypeOption = new HashMap<Integer, String>();
	static {
		channelTypeOption.put(fileType, "文件");
		channelTypeOption.put(dbType, "数据库");
		channelTypeOption.put(mqType, "MQ");
		channelTypeOption.put(ftpType, "FTP下载");
		channelTypeOption.put(SftpType, "SFTP上传");
	}
	
	public static HashMap<Integer, String> dbTypeOption = new HashMap<Integer, String>();
	static {
		dbTypeOption.put(oracle, "Oracle");
		dbTypeOption.put(mysql, "MySQL");
		dbTypeOption.put(sqlserver, "SqlServer");
		dbTypeOption.put(db2, "DB2");
		dbTypeOption.put(access, "Access");
	}

	public static HashMap<Integer, String> fileTypeOption = new HashMap<Integer, String>();
	static {
		fileTypeOption.put(dbf, "DBF");
		fileTypeOption.put(txt, "TXT");
		fileTypeOption.put(excel, "EXCEL");
		fileTypeOption.put(csv, "CSV");
		fileTypeOption.put(xml, "XML");
		fileTypeOption.put(gz, "GZ");
        fileTypeOption.put(gzcsv, "GZCSV");
        fileTypeOption.put(hengshengTA, "TA");
	}

	public static HashMap<Integer, String> filterTypeOption = new HashMap<Integer, String>();
	static {
		filterTypeOption.put(filterOr, "或");
		filterTypeOption.put(filterAnd, "并");
	}
	
	/**
	 * 获取全局变量
	 */
	public static List<String> globalValsList=new ArrayList<String>();
	static{
		globalValsList.add(Global_Folder);
		globalValsList.add(Global_FileName);
		globalValsList.add(Global_LedgerDate);
		globalValsList.add(Global_ExeUserID);
		globalValsList.add(Global_AdapterID);
	}
}
