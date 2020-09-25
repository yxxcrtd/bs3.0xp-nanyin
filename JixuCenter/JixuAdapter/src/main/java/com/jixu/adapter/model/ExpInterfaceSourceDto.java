package com.jixu.adapter.model;

import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;

public class ExpInterfaceSourceDto extends BaseMsgDto{

	private static final long serialVersionUID = 1L;
	
	private int id=0; 
	
	/**
	 * 适配器导出名称
	 */
	@NotEmptyJixu(keymsg="导出名称")
	private String name;
	
	/**
	 * 导出描述
	 */
	@NotEmptyJixu(keymsg="导出描述")
	private String desc;
	
	/**
	 * 适配器分组类型
	 */
	private String category;
	
	/**
	 * 
	 */
	private String resource;
	
	/**
	 * 传递类型 数据库，文件，MQ
	 */
	private int deliverType;
	
	/**
	 * 文件类型	excel, csv, txt
	 */
	/**
	 * 源文件格式
	 */
	private int fileformat;
	
	/**
	 * 文件编码格式
	 */
	private int fileencode;
	
	/**
	 * 导出文件地址
	 */
	private String fileloc;
	
	/**
	 * 导出文件名
	 */
	private String filename;
	
	/**
	 * 导出文件格式
	 */
	private int expFileformat;
	
	/**
	 * 导出字体编码
	 */
	private int expFileencode;
	
	@NotEmptyJixu(keymsg="导出文件路径")
	private String expFileloc;
	
	@NotEmptyJixu(keymsg="导出文件名")
	private String expFilename;
	
	/**
	 * 是否支持多文件导出
	 */
	private int mutipleOps;
	
	/**
	 * excel，导出表名
	 */
	
	private String tablename;
	
	/**
	 * 开始行
	 */
	@IsIntegerJixu(keymsg="开始行")
	private String startcol;
	
	/**
	 * 分割标记 csv文件 逗号，竖线等
	 */
	private String delimiter;
	
	/**
	 * 结束标记位
	 */
	private String end;
	
	/**
	 * 标题名称
	 */
	private String titile;
	
	/**
	 * 是否包含标题
	 */
	private boolean includeCol;
	
	/**
	 * 数据库用户名
	 */
	private String user;
	
	/**
	 * 数据库密码
	 */
	private String pwd;
	
	/**
	 * 数据库类型
	 */
	private int dbFormat;
	
	/**
	 * 数据库连接地址
	 */
	private String dbURL;
	
	/**
	 * SQL语句
	 */
	private String dbSQL;
	
	/**
	 * 空数据是否生成空文件
	 */
	private boolean outNoDataGenFileFlag;
	
	/**
	 * 依赖父适配器
	 */
	private int dependency;

	/**
	 * 空数据是否执行下去 0.停止 1.继续执行
	 */
	private boolean depNoDataGoFlag;
	
	/**
	 * 是否自动导出
	 */
	private boolean autodo;
	
	/**
	 * 节假日
	 */
	private String holiday;
	
	/**
	 * 自动导出，第一次执行适配器的日期
	 */
	private String startDate;
	
	/**
	 * 自动导出，第一次执行适配器的时间
	 */
	private String startTime;
	
	/**
	 * 自动导出的频率类型
	 */
	private int autoDateFormat;
	
	/**
	 * 每隔N天
	 */
	@IsIntegerJixu(keymsg="每隔N天")
	private String fromDay;
	
	/**
	 * 自动导出的时间点，可以自定义
	 */
	private String autoTime;
	
	/**
	 * 下游调用程序
	 */
	private String afterCallClass;
	
	/**
	 * 导出分隔符
	 */
	private String writeSplit;
	
	/**
	 *  文末结束符
	 */
	private String endChar;
	
	/**
	 * 导出文件备份路径
	 */
	private String moveto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(int deliverType) {
		this.deliverType = deliverType;
	}

	public int getFileformat() {
		return fileformat;
	}

	public void setFileformat(int fileformat) {
		this.fileformat = fileformat;
	}

	public int getFileencode() {
		return fileencode;
	}

	public void setFileencode(int fileencode) {
		this.fileencode = fileencode;
	}

	public String getFileloc() {
		return fileloc;
	}

	public void setFileloc(String fileloc) {
		this.fileloc = fileloc;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getMutipleOps() {
		return mutipleOps;
	}

	public void setMutipleOps(int mutipleOps) {
		this.mutipleOps = mutipleOps;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public boolean isIncludeCol() {
		return includeCol;
	}

	public void setIncludeCol(boolean includeCol) {
		this.includeCol = includeCol;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getDbFormat() {
		return dbFormat;
	}

	public void setDbFormat(int dbFormat) {
		this.dbFormat = dbFormat;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbSQL() {
		return dbSQL;
	}

	public void setDbSQL(String dbSQL) {
		this.dbSQL = dbSQL;
	}

	public int getDependency() {
		return dependency;
	}

	public void setDependency(int dependency) {
		this.dependency = dependency;
	}

	public boolean isAutodo() {
		return autodo;
	}

	public void setAutodo(boolean autodo) {
		this.autodo = autodo;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getAutoDateFormat() {
		return autoDateFormat;
	}

	public void setAutoDateFormat(int autoDateFormat) {
		this.autoDateFormat = autoDateFormat;
	}

	public String getAutoTime() {
		return autoTime;
	}

	public void setAutoTime(String autoTime) {
		this.autoTime = autoTime;
	}

	public String getStartcol() {
		return startcol;
	}

	public void setStartcol(String startcol) {
		this.startcol = startcol;
	}

	public String getFromDay() {
		return fromDay;
	}

	public void setFromDay(String fromDay) {
		this.fromDay = fromDay;
	}

	public int getExpFileformat() {
		return expFileformat;
	}

	public void setExpFileformat(int expFileformat) {
		this.expFileformat = expFileformat;
	}

	public String getExpFileloc() {
		return expFileloc;
	}

	public void setExpFileloc(String expFileloc) {
		this.expFileloc = expFileloc;
	}

	public String getExpFilename() {
		return expFilename;
	}

	public void setExpFilename(String expFilename) {
		this.expFilename = expFilename;
	}

	public int getExpFileencode() {
		return expFileencode;
	}

	public void setExpFileencode(int expFileencode) {
		this.expFileencode = expFileencode;
	}

	public String getAfterCallClass() {
		return afterCallClass;
	}

	public void setAfterCallClass(String afterCallClass) {
		this.afterCallClass = afterCallClass;
	}

	public String getWriteSplit() {
		return writeSplit;
	}

	public void setWriteSplit(String writeSplit) {
		this.writeSplit = writeSplit;
	}

	public String getMoveto() {
		return moveto;
	}

	public void setMoveto(String moveto) {
		this.moveto = moveto;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isDepNoDataGoFlag() {
		return depNoDataGoFlag;
	}

	public void setDepNoDataGoFlag(boolean depNoDataGoFlag) {
		this.depNoDataGoFlag = depNoDataGoFlag;
	}

	public boolean isOutNoDataGenFileFlag() {
		return outNoDataGenFileFlag;
	}

	public void setOutNoDataGenFileFlag(boolean outNoDataGenFileFlag) {
		this.outNoDataGenFileFlag = outNoDataGenFileFlag;
	}

	public String getEndChar() {
		return endChar;
	}

	public void setEndChar(String endChar) {
		this.endChar = endChar;
	}
}
