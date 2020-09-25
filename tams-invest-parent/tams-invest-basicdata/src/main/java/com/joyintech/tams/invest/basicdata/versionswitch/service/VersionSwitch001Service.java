/*
 * 文件名：VersionSwitch001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：曹锦
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.versionswitch.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.invest.basicdata.yieldcurveinfo.dto.MstYieldCurveInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 版本切换service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caojin
 * @version 1.0 2020年5月23日
 * @see VersionSwitch001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VersionSwitch001Service extends CommonExportService<MstYieldCurveInfoDto>{
	
	
	/**
     * 版本切换
     * 
     * @param map
     * @return
     */
	public Map<String,Object> save(Map<String, Object> map) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(switchVersion(map)){
			updateVersionType(map);
			result.put("flag", "01");
		}else{
			result.put("flag", "02");
		}		
		return result;
	}
	
	
	private boolean switchVersion(Map<String, Object> map) {

		// 版本类型：01基础版 ，02进阶版，03高阶版
		String versionType = (String) map.get("versionType");
		Connection conn = null;
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

//		File f = new File(VersionSwitch001Service.class.getResource("").getPath());
//		String templatepath = f.getAbsolutePath();
//		int aa = templatepath.indexOf("com");
//		if (aa != -1) {
//			templatepath = templatepath.substring(0, aa) + "/menuScript.txt";
//		}
//		File file = new File(templatepath);
		BufferedReader reader = null;
		InputStream is=this.getClass().getResourceAsStream("/menuScript.txt");   
//        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
		String sqlString = null;
		int line = 0;// 行号
		try {
			// 输入字节流，FileInputStream主要用来操作文件输入流
//			FileInputStream intput = new FileInputStream(file);
			// InputStreamReader是转换流，将字节流转成字符流
//			reader = new BufferedReader(new InputStreamReader(intput));
			reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));

			// 基础版
			if(versionType.equals("01")){
				while ((sqlString = reader.readLine()) != null) {
					line++;
					if (StringUtils.isEmpty(sqlString)) {
						continue;
					}
					if (sqlString.startsWith("/*")) {
						continue;
					}
					if (sqlString.startsWith("##Insert")) {
						break;
					}
					if (sqlString.startsWith("##")) {
						continue;
					}
					System.out.println("执行第" + line + "行:" + sqlString);
					sqlString = sqlString.replace(";", "");
					if(!executeSql(conn, sqlString)){
						return false;
					}
				}
			}else if (versionType.equals("02")) {// 进阶版
				while ((sqlString = reader.readLine()) != null) {
					line++;
					if (StringUtils.isEmpty(sqlString)) {
						continue;
					}
					if (sqlString.startsWith("/*")) {
						continue;
					}
					if (sqlString.startsWith("##Insert高阶版菜单")) {
						break;
					}
					if (sqlString.startsWith("##")) {
						continue;
					}
					System.out.println("执行第" + line + "行:" + sqlString);
					sqlString = sqlString.replace(";", "");
					if(!executeSql(conn, sqlString)){
						return false;
					}
				}
			}else if (versionType.equals("03")) {// 高阶版
				while ((sqlString = reader.readLine()) != null) {
					line++;
					if (StringUtils.isEmpty(sqlString)) {
						continue;
					}
					if (sqlString.startsWith("/*")) {
						continue;
					}
					if (sqlString.startsWith("##")) {
						continue;
					}
					System.out.println("执行第" + line + "行:" + sqlString);
					sqlString = sqlString.replace(";", "");
					if(!executeSql(conn, sqlString)){
						return false;
					}
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}
    
    
	private boolean executeSql(Connection conn, String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 创建执行语句
			ps = conn.prepareStatement(sql);
			// 传参
			// ps.setDate(1,new java.sql.Date(valueDate.getTime()));
			// ps.setDate(2,new java.sql.Date(valueDate.getTime()));
			// ps.setString(3, prodId);
			// 执行语句
			rs = ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// 释放连接
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}
	
	/**
     * 查询当前版本类型
     * 
     * @param map
     * @return
     */
	public String selectVersionType() {
		String versionType = 
		 (String) restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/VersionSwitch/selectVersionType"
                , "versionType", String.class);
		return versionType;
	}
	
	/**
     * 更新识字字典表默认值
     * 
     * @param map
     * @return
     */
	public void updateVersionType(Map<String, Object> map) {
		String versionType = (String) map.get("versionType");
		 restTemplate.postForEntity(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/VersionSwitch/updateVersionType"
                , versionType, String.class);
	}
}
