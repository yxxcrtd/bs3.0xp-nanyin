/*
 * 文件名：DataSourceUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.framework.util;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;

/**
 * 数据源工具类
 * @author 何阳阳
 * @version 1.0 2017年11月27日
 * @see DataSourceUtil
 * @since 1.0
 */
@Component
public final class DataSourceUtil {

    /**
     *
     */
   // private static BasicDataSource dataSource;
    
    //@Value("${application.sqldirurl}")
    private static String sqldirurl;  
    //@Value("${application.sqldiruserName}")
    private static String sqldiruserName; 
   // @Value("${application.sqldirpassword}")
    private static String sqldirpassword;
    
    @Autowired(required=true)     
   /* public void setDataSource(@Qualifier("masterDataSource") BasicDataSource dataSource) {
        DataSourceUtil.dataSource = dataSource;
    }*/
   /**
    * 获取url
    * useraname
    * user
    * @return
    */
//    public static String getURL() {
//    	System.out.println(sqldirurl+"=======url");
//        return sqldirurl;
//    }
//    
//    public static String getUserName() {
//    	System.out.println(sqldiruserName+"========userNmae");
//        return sqldiruserName;
//    }
//    
//    public static String getPassword() {
//    	System.out.println(sqldirpassword+"=========password");
//        return sqldirpassword;
//    }
    
//    @Value("${application.sqldirurl}" )
//    public void setsqldirurl(String url) {
//        DataSourceUtil.sqldirurl = url;
//    }
//    @Value("${application.sqldiruserName}")
//    public void setsqldiruserName( String username) {
//        DataSourceUtil.sqldiruserName = username;
//    }
//    @Value("${application.sqldirpassword}" )
//    public void setsqldirpassword(String password) {
//        DataSourceUtil.sqldirpassword = password;
//    }
    
    /*@Value("${application.reportlnterNeedOKFile}")
    public  void setsqldirurl(String sqldirurl) {
       this.sqldirurl=sqldirurl;
    }
    @Value("${application.reportlnterNeedOKFile}")
    public  void setsqldiruserName(String sqldiruserName) {
        this.sqldiruserName=sqldiruserName;
    }
    @Value("${application.reportlnterNeedOKFile}")
    public  void setPassword(String sqldirpassword) {
    	 this.sqldirpassword=sqldirpassword;
    } 
    */   
   
	/**
	 * @return the sqldirurl
	 */
  /*  @Value("${application.sqldirurl}")
	public static String getSqldirurl() {
		return sqldirurl;
	}

	*//**
	 * @param sqldirurl the sqldirurl to set
	 *//*
	public static void setSqldirurl(String sqldirurl) {
		DataSourceUtil.sqldirurl = sqldirurl;
	}

	*//**
	 * @param sqldiruserName the sqldiruserName to set
	 *//*
	@Value("${application.sqldiruserName}")
	public static void setSqldiruserName(String sqldiruserName) {
		DataSourceUtil.sqldiruserName = sqldiruserName;
	}

	*//**
	 * @param sqldirpassword the sqldirpassword to set
	 *//*
	@Value("${application.sqldirpassword}")
	public static void setSqldirpassword(String sqldirpassword) {
		DataSourceUtil.sqldirpassword = sqldirpassword;
	}*/
    
    
    /**
	 * @return the sqldirurl
	 */
	public static String getSqldirurl() {
		return sqldirurl;
	}

	/**
	 * @param sqldirurl the sqldirurl to set
	 */
    @Value("${application.sqldirurl}" )
	public  void setSqldirurl(String sqldirurl) {
		DataSourceUtil.sqldirurl = sqldirurl;
	}

	/**
	 * @return the sqldiruserName
	 */
	public static String getSqldiruserName() {
		return sqldiruserName;
	}

	/**
	 * @param sqldiruserName the sqldiruserName to set
	 */
	@Value("${application.sqldiruserName}")
	public  void setSqldiruserName(String sqldiruserName) {
		DataSourceUtil.sqldiruserName = sqldiruserName;
	}

	/**
	 * @return the sqldirpassword
	 */
	public static String getSqldirpassword() {
		return sqldirpassword;
	}

	/**
	 * @param sqldirpassword the sqldirpassword to set
	 */
	@Value("${application.sqldirpassword}" )
	public  void setSqldirpassword(String sqldirpassword) {
		DataSourceUtil.sqldirpassword = sqldirpassword;
	}

	/**
     * 获取数据库名
     * @return 数据库名
     */
    public static String getDatabaseName() {
        String dbname = StringUtil.EMPTY;
        String url = getSqldirurl();
        // oracle
        if(url.startsWith("jdbc:oracle:thin:@//")) {
            dbname = splitTwo(url, "@//")[1];
        }
        else if(url.startsWith("jdbc:oracle:thin:@")) {
            dbname = splitTwo(url, "@")[1];
        }

        return dbname;
    }

    

	/**
     * 将字符串根据指定字符串分隔为两个字符串
     *
     * @param old
     *            源字符串
     * @param splitor
     *            分隔符
     * @return 新的字符串数组
     */
    public static final String[] splitTwo(String old, String splitor) {
        String[] result = new String[2];
        if (StringUtil.isNotEmpty(old)) {
            int index = old.indexOf(splitor);
            if (index > -1) {
                result[0] = old.substring(0, index);
                result[1] = old.substring(index + splitor.length());
            } else {
                result[0] = old;
                result[1] = StringUtil.EMPTY;
            }
        }
        return result;
    }
    
    
    
}
