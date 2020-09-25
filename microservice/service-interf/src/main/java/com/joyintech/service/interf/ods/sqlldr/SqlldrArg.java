/*
 * 文件名：Sqlloader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods.sqlldr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.service.interf.core.util.DataSourceUtil;
import com.joyintech.service.interf.ods.Cmd;

/**
 * sqlldr命令参数
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月2日
 * @see SqlldrArg
 * @since 1.0
 */
public class SqlldrArg implements Cmd {

    /**
     * sqlldr命令
     */
    public static final String SQLLDR = "sqlldr";

    /**
     * 空格
     */
    private static final String SPACE = " ";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 控制文件
     */
    private String control;

    /**
     * 日志文件
     */
    private String log;

    /**
     * 坏数据文件
     */
    private String bad;

    /**
     * 跳过记录数
     */
    private long skip;

    /**
     * 丢弃的数据文件
     */
    private String discard;

    /**
     * 使用直通路径方式导入
     */
    private boolean direct = true;

    /**
     * 允许导入的行数
     */
    private long load;

    /**
     * 一次加载的行数
     */
    private long rows = 1000;

    /**
     * 允许出错的次数
     */
    private long errors = 10000;

    /**
     * 缓冲区大小
     */
    private long bindsize = 33554432L;

    /**
     * 自动分隔符
     */
    private String fieldTerm = GlobalConstant.VERTICAL_LINE;

    /**
     * 获取 username
     * 
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 username
     * 
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取 password
     * 
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 password
     * 
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 databaseName
     * 
     * @return databaseName.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * 设置 databaseName
     * 
     * @param databaseName databaseName
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * 获取 control
     * 
     * @return control.
     */
    public String getControl() {
        return control;
    }

    /**
     * 设置 control
     * 
     * @param control control
     */
    public void setControl(String control) {
        this.control = control;
    }

    /**
     * 获取 log
     * 
     * @return log.
     */
    public String getLog() {
        return log;
    }

    /**
     * 设置 log
     * 
     * @param log log
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * 获取 bad
     * 
     * @return bad.
     */
    public String getBad() {
        return bad;
    }

    /**
     * 设置 bad
     * 
     * @param bad bad
     */
    public void setBad(String bad) {
        this.bad = bad;
    }

    /**
     * 获取 skip
     * 
     * @return skip.
     */
    public long getSkip() {
        return skip;
    }

    /**
     * 设置 skip
     * 
     * @param skip skip
     */
    public void setSkip(long skip) {
        this.skip = skip;
    }

    /**
     * 获取 discard
     * 
     * @return discard.
     */
    public String getDiscard() {
        return discard;
    }

    /**
     * 设置 discard
     * 
     * @param discard discard
     */
    public void setDiscard(String discard) {
        this.discard = discard;
    }

    /**
     * 获取 direct
     * 
     * @return direct.
     */
    public boolean isDirect() {
        return direct;
    }

    /**
     * 设置 direct
     * 
     * @param direct direct
     */
    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    /**
     * 获取 load
     * 
     * @return load.
     */
    public long getLoad() {
        return load;
    }

    /**
     * 设置 load
     * 
     * @param load load
     */
    public void setLoad(long load) {
        this.load = load;
    }

    /**
     * 获取 rows
     * 
     * @return rows.
     */
    public long getRows() {
        return rows;
    }

    /**
     * 设置 rows
     * 
     * @param rows rows
     */
    public void setRows(long rows) {
        this.rows = rows;
    }

    /**
     * 获取 errors
     * 
     * @return errors.
     */
    public long getErrors() {
        return errors;
    }

    /**
     * 设置 errors
     * 
     * @param errors errors
     */
    public void setErrors(long errors) {
        this.errors = errors;
    }

    /**
     * 获取 bindsize
     * 
     * @return bindsize.
     */
    public long getBindsize() {
        return bindsize;
    }

    /**
     * 设置 bindsize
     * 
     * @param bindsize bindsize
     */
    public void setBindsize(long bindsize) {
        this.bindsize = bindsize;
    }

    /**
     * 获取 fieldTerm
     * 
     * @return fieldTerm.
     */
    public String getFieldTerm() {
        return fieldTerm;
    }

    /**
     * 设置 fieldTerm
     * 
     * @param fieldTerm fieldTerm
     */
    public void setFieldTerm(String fieldTerm) {
        this.fieldTerm = fieldTerm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SQLLDR).append(SPACE);
        sb.append(this.username);
        sb.append("/");
        sb.append(this.password);
        sb.append("@");
        sb.append(this.databaseName);
        sb.append(SPACE).append("control=" + this.control);
        if (this.log != null) {
            sb.append(SPACE).append("log=" + this.log);
        }
        if (this.bad != null) {
            sb.append(SPACE).append("bad=" + this.bad);
        }
        sb.append(SPACE).append("direct=" + this.direct);
        if (this.skip > 0) {
            sb.append(SPACE).append("skip=" + this.skip);
        }
        if (this.load > 0) {
            sb.append(SPACE).append("load=" + this.load);
        }
        if (this.rows > 0) {
            sb.append(SPACE).append("rows=" + this.rows);
        }
        if (this.errors > 0) {
            sb.append(SPACE).append("errors=" + this.errors);
        }
        if (this.bindsize > 0) {
            sb.append(SPACE).append("bindsize=" + this.bindsize);
        }
        return sb.toString();
    }

    public SqlldrArg() {
        init();
    }

    /**
     * {@inheritDoc} </br>
     * args[0]:table 对象表名</br>
     * args[1]:control 控制文件名
     */
    @Override
    public String getCmd(String ctl) {
        setControl(ctl);
        String cmd = this.toString();
        logger.info(cmd);
        return cmd;
    }

    /**
     * 初始化数据库连接配置
     * 
     * @see
     * @since 1.0
     */
    public void init() {
        this.username = DataSourceUtil.getUserName();
        this.password = DataSourceUtil.getPassword();
        this.databaseName = DataSourceUtil.getDatabaseName();
    }
}
