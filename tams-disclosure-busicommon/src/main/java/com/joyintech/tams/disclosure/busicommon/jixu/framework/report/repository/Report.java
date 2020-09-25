/**
 * 报表定义类
 * 
 * @author 小川(几许)
 * @version 1.0
*/

package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Report构造函数
 * @param reportID 报表在报表仓库中的唯一ID
 * @param repository 报表所在的报表仓库地址：1) JasperSource类型：服务器文件路径; 2) JasperServer类型：Jasper服务器的URL
 * @param folder 报表所在文件夹
 * @param uri 报表的URI
 * @param parameters 报表的参数定义
 * @param datasource 该报表使用的datasource：1)PROJECT:项目默认数据源;2)EMPTY:空数据源;3)数据源所在的类全路径
 * @param generator 该报表使用的报表生成器：1)JasperSource:对应使用jasper lib生成报表；2)JasperServer：对应jasper server生成报表
 * @param user 报表所在数据仓库的用户名
 * @param password 报表所在数据仓库的密码
 * 
*/

public class Report {
	public Report(String reportID, String repository, String folder, String uri,
		          Map<String, String> parameters, String datasource,
			      String generator, String user, String password) {
		super();
		this.parameters = parameters;
		this.reportID = reportID;
		this.uri = uri;
		this.folder = folder;
		this.repository = repository;
		this.datasource = datasource;
		this.generator = generator;
		this.user = user;
		this.password = password;
	}
	
	private Map<String,String> parameters;
	private String reportID;
	private String uri;
	private String folder;
	private String repository;
	private String desc;
	private String datasource;
	private String generator;
	private String user;
	private String password;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
