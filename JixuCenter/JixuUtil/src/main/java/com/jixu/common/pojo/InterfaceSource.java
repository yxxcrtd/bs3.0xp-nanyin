package com.jixu.common.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/** 
 * @ClassName 		InterfaceSource
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[InterfaceSource(接口配置总表),配置了接口的基本信息,与InterfaceColConfig(配置基础列和增强列的计算方式)和InterfaceMsgColMap(配置如何把增强列匹配到对应的消息字段)一起把输入或者输出的数据源数据转换成符合需求的数据格式]  
 */


@Entity 
@Table(name="ADPT_INTERFACESOURCE") 
public class InterfaceSource implements Serializable{

	private static final long serialVersionUID = -2866452376709483L;

	private int sourceID;
	private int sourceType;		//接口类型
	private String sourceSecType;
	private int tranType;       //交易类型
	private String sourceDesc;	//接口描述
	private String sourceName;	//接口名称
	
	private int channelType;	//传输类型
	private String channelAccount;	//用户名
	private String channelPWD;		//密码
	
	private int fileType;		//文件类型
	private String filepath;	//文件路径	(支持公式)
	private String fileName;	//文件名 (支持通配符)
	
	private String dbSQL;
	
	/**
	 * 1. 导入接口: 是否支持多文件
	 * 2. 导出接口: true为多投资组合分N个文件导出。 false为多投资组合合并在一个文件导出
	 */
	private boolean fileMultiFlag;	
	
	private String tablename;		//表名
	private int tableStartLine;		//Start Line
	private String tableSpilitChar;	//读取分隔符
	private String writeSpilitChar;	//写入分隔符
	private String tableEndChar;	//结束符
	
	private String holiday;	//节假日
	
	private boolean autoFlag;	//自动导入
	private int autoType;		//自动导入时间类型
	private int autoNDays;		//每隔N天
	private String autoTime;	//自动导入时间
	
	/**
	 * 1.接口导入: 接口是否备份	
	 * 2.接口导出: 是否包含列名
	 */
	private boolean backupFlag;
	private String backupPath;	//备份路径
	private int enCodeType;		//文件的编码类型
	
	/**
	 * 导出反射的类名,实现ExportDataService接口
	 */
	private String expSourceClass;
	
	/**
	 * 如果是数据库导入，这里填入数据库导入的表，为了自动生成sql语句
	 */
	private String targeDBTable;
	
	/**
	 * 是否批量执行，如果批量执行，将由全局参数来一次性验证是否插入/更新等操作
	 */
	private boolean batchExeFlag;
	
	private Set<InterfaceColConfig> colconfig;
	private Set<InterfaceMsgColMap> msgColMap;
	
	private String msg;
	
	/**
	 * 状态	0: 逻辑删除
	 * 		1: 可用
	 */
	private int activeStatus;
	
	/**
	 * 导入依赖
	 */
	private int dependency;
	
	/**
	 * 如果父适配器没有数据，是否要进行下去
	 */
	private int depNoDataGoFlag;
	
	/**
	 * 自动执行时，可以配置哪天开始执行
	 */
	private String startDate;
	
	/**
	 * 自动执行时，可以配置哪个时间点开始执行
	 */
	private String startTime;
	
	/**
	 * 下游导出文件夹地址
	 */
	private String outFilepath;

	/**
	 * 下游导出的文件名
	 */
	private String outFileName;
	
	/**
	 * 下游导出的文件类型
	 */
	private int outFileType;
	
	/**
	 * 下游导出文件格式
	 */
	private int outFileEnCodeType;
	
	/**
	 * 导出文件是否包含标题
	 */
	private boolean outFileTitleFlag;
	
	/**
	 * 上游空数据，是否生成空文件
	 */
	private boolean outNoDataGenFileFlag;
	
	/**
	 * 结束后调用的下游程序
	 */
	private String afterCallClass;
	
	/**
	 * 	执行前调用自定义方法
	 */
	private String customBeforeExecute;
	
	/**
	 * 	执行后调用自定义方法
	 */
	private String customAfterExecute;
	
	/**
	 * 	文末结束符
	 */
	private String endChar;
	
	/**
	 * 项目组
	 */
	private String projectGroup;
	
	public InterfaceSource(int sourceID) {
		this.setSourceID(sourceID);
	}
	
	public InterfaceSource() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.tool.GenCustomIntIDByDate", parameters={
			@Parameter(name = "seq_name", value = "ADPT_InterfaceSource_ID_SEQ")})
	@Column(name="sourceID")
	public int getSourceID() {
		return sourceID;
	}
	
	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}
	
	@Column(name="sourceType")
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	
	@Column(name="sourceDesc")
	public String getSourceDesc() {
		return sourceDesc;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	@Column(name="sourceName")
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name="channelType")
	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	@Column(name="channelAccount")
	public String getChannelAccount() {
		return channelAccount;
	}

	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}

	@Column(name="channelPWD")
	public String getChannelPWD() {
		return channelPWD;
	}

	public void setChannelPWD(String channelPWD) {
		this.channelPWD = channelPWD;
	}

	@Column(name="fileType")
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	@Column(name="filepath")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name="fileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="tablename")
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name="tableStartLine")
	public int getTableStartLine() {
		return tableStartLine;
	}

	public void setTableStartLine(int tableStartLine) {
		this.tableStartLine = tableStartLine;
	}
	
	@Column(name="tableSpilitChar")
	public String getTableSpilitChar() {
		return tableSpilitChar;
	}

	public void setTableSpilitChar(String tableSpilitChar) {
		this.tableSpilitChar = tableSpilitChar;
	}

	@Column(name="tableEndChar")
	public String getTableEndChar() {
		return tableEndChar;
	}

	public void setTableEndChar(String tableEndChar) {
		this.tableEndChar = tableEndChar;
	}

	@Column(name="HOLIDAY")
	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	@Column(name="autoFlag", length=1, columnDefinition="int default 0")
	public boolean isAutoFlag() {
		return autoFlag;
	}

	public void setAutoFlag(boolean autoFlag) {
		this.autoFlag = autoFlag;
	}

	@Column(name="autoType")
	public int getAutoType() {
		return autoType;
	}

	public void setAutoType(int autoType) {
		this.autoType = autoType;
	}

	@Column(name="autoTime")
	public String getAutoTime() {
		return autoTime;
	}

	public void setAutoTime(String autoTime) {
		this.autoTime = autoTime;
	}
	
	@Column(name="backupFlag", length=1, columnDefinition="int default 0")
	public boolean isBackupFlag() {
		return backupFlag;
	}

	public void setBackupFlag(boolean backupFlag) {
		this.backupFlag = backupFlag;
	}

	@Column(name="backupPath")
	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	@Column(name="sourceSecType")
	public String getSourceSecType() {
		return sourceSecType;
	}

	public void setSourceSecType(String sourceSecType) {
		this.sourceSecType = sourceSecType;
	}
	
	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	@Column(name="fileMultiFlag", length=1, columnDefinition="int default 0")
	public boolean isFileMultiFlag() {
		return fileMultiFlag;
	}

	public void setFileMultiFlag(boolean fileMultiFlag) {
		this.fileMultiFlag = fileMultiFlag;
	}

	@Column(name="msg", length=10000)
	@Type(type="org.hibernate.type.MaterializedClobType")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@OneToMany(mappedBy = "interfacesource", cascade = CascadeType.REMOVE)
	public Set<InterfaceColConfig> getColconfig() {
		return colconfig;
	}

	public void setColconfig(Set<InterfaceColConfig> colconfig) {
		this.colconfig = colconfig;
	}

	@OneToMany(mappedBy = "interfaceSource", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	public Set<InterfaceMsgColMap> getMsgColMap() {
		return msgColMap;
	}

	public void setMsgColMap(Set<InterfaceMsgColMap> msgColMap) {
		this.msgColMap = msgColMap;
	}

	@Column(name="enCodeType")
	public int getEnCodeType() {
		return enCodeType;
	}

	public void setEnCodeType(int enCodeType) {
		this.enCodeType = enCodeType;
	}

	@Column(name="ExpSourceClass")
	public String getExpSourceClass() {
		return expSourceClass;
	}

	public void setExpSourceClass(String expSourceClass) {
		this.expSourceClass = expSourceClass;
	}

	@Column(name="AutoNDays")
	public int getAutoNDays() {
		return autoNDays;
	}

	public void setAutoNDays(int autoNDays) {
		this.autoNDays = autoNDays;
	}

	@Column(name="targeDBTable")
	public String getTargeDBTable() {
		return targeDBTable;
	}

	public void setTargeDBTable(String targeDBTable) {
		this.targeDBTable = targeDBTable;
	}

	@Column(name="batchExeFlag", length=1, columnDefinition="INT default 1")
	public boolean isBatchExeFlag() {
		return batchExeFlag;
	}

	public void setBatchExeFlag(boolean batchExeFlag) {
		this.batchExeFlag = batchExeFlag;
	}

	@Column(name="activeStatus", length=1, columnDefinition="INT default 1")
	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column(name="dependency",columnDefinition="INT default 0")
	public int getDependency() {
		return dependency;
	}

	public void setDependency(int dependency) {
		this.dependency = dependency;
	}

	@Column(name="startDate")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name="startTime")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name="depNoDataGoFlag")
	public int getDepNoDataGoFlag() {
		return depNoDataGoFlag;
	}

	public void setDepNoDataGoFlag(int depNoDataGoFlag) {
		this.depNoDataGoFlag = depNoDataGoFlag;
	}

	@Column(name="dbSQL", length=30000)
	@Type(type="org.hibernate.type.MaterializedClobType")
	public String getDbSQL() {
		return dbSQL;
	}

	public void setDbSQL(String dbSQL) {
		this.dbSQL = dbSQL;
	}

	@Column(name="OUTFILEPATH")
	public String getOutFilepath() {
		return outFilepath;
	}

	public void setOutFilepath(String outFilepath) {
		this.outFilepath = outFilepath;
	}

	@Column(name="OUTFILENAME")
	public String getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

	@Column(name="OUTFILETYPE")
	public int getOutFileType() {
		return outFileType;
	}

	public void setOutFileType(int outFileType) {
		this.outFileType = outFileType;
	}

	@Column(name="OUTFILEENCODETYPE")
	public int getOutFileEnCodeType() {
		return outFileEnCodeType;
	}
	
	@Column(name="AFTERCALLCLASS")
	public String getAfterCallClass() {
		return afterCallClass;
	}

	public void setAfterCallClass(String afterCallClass) {
		this.afterCallClass = afterCallClass;
	}

	public void setOutFileEnCodeType(int outFileEnCodeType) {
		this.outFileEnCodeType = outFileEnCodeType;
	}

	@Column(name="OUTFILETITLEFLAG")
	public boolean isOutFileTitleFlag() {
		return outFileTitleFlag;
	}

	public void setOutFileTitleFlag(boolean outFileTitleFlag) {
		this.outFileTitleFlag = outFileTitleFlag;
	}

	@Column(name="PROJECTGROUP")
	public String getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(String projectGroup) {
		this.projectGroup = projectGroup;
	}

	@Column(name="WRITESPILITCHAR")
	public String getWriteSpilitChar() {
		return writeSpilitChar;
	}

	public void setWriteSpilitChar(String writeSpilitChar) {
		this.writeSpilitChar = writeSpilitChar;
	}

	@Column(name="OUTNODATAGENFILEFLAG")
	public boolean isOutNoDataGenFileFlag() {
		return outNoDataGenFileFlag;
	}

	public void setOutNoDataGenFileFlag(boolean outNoDataGenFileFlag) {
		this.outNoDataGenFileFlag = outNoDataGenFileFlag;
	}

	@Column(name="CUSTOM_BEFORE_EXECUTE")
	public String getCustomBeforeExecute() {
		return customBeforeExecute;
	}

	public void setCustomBeforeExecute(String customBeforeExecute) {
		this.customBeforeExecute = customBeforeExecute;
	}

	@Column(name="CUSTOM_AFTER_EXECUTE")
	public String getCustomAfterExecute() {
		return customAfterExecute;
	}

	public void setCustomAfterExecute(String customAfterExecute) {
		this.customAfterExecute = customAfterExecute;
	}
	@Column(name="END_CHAR")
	public String getEndChar() {
		return endChar;
	}

	public void setEndChar(String endChar) {
		this.endChar = endChar;
	}
}
