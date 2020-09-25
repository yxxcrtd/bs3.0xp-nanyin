/*
 * 文件名：CtlArg.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods.sqlldr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.SqlldrColsEnum;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.ods.Ctl;
import com.joyintech.service.interf.ods.entity.TableColumn;
import com.joyintech.service.interf.ods.service.TableStructService;

/**
 * sqlldr控制文件类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月3日
 * @see CtlArg
 * @since 1.0
 */
public class CtlArg implements Ctl {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CtlArg.class);

    /**
     * 装入的方式:原先的表有数据 就加在后面
     */
    public static final String LOADTYPE_APPEND = "APPEND";

    /**
     * 装入的方式:装载空表 如果原先的表有数据
     */
    public static final String LOADTYPE_INSERT = "INSERT";

    /**
     * 装入的方式:原先的表有数据 原先的数据会全部删除
     */
    public static final String LOADTYPE_REPLACE = "REPLACE";

    /**
     * 装入的方式:指定的内容和replace的相同 会用truncate语句删除现存数据
     */
    public static final String LOADTYPE_TRUNCATE = "TRUNCATE";

    /**
     * 表名
     */
    private String tableName;

    /**
     * 要导入的文件名
     */
    private String dataFile;

    /**
     * 装入的方式 平安默认使用TRUNCATE</br>
     * APPEND // 原先的表有数据 就加在后面 </br>
     * INSERT // 装载空表 如果原先的表有数据 sqlloader会停止 默认值 </br>
     * REPLACE // 原先的表有数据 原先的数据会全部删除 </br>
     * TRUNCATE // 指定的内容和replace的相同 会用truncate语句删除现存数据
     */
    private String loadType = LOADTYPE_TRUNCATE;

    /**
     * 自动分隔符
     */
    private String fieldTerm = GlobalConstant.VERTICAL_LINE;

    /**
     * 
     */
    private String optionTerm;

    /**
     * 表的字段没有对应的值时允许为空
     */
    private boolean nullCols = true;

    /**
     * 对象列
     */
    private List<TableColumn> cols;

    /**
     * 平台日期
     */
    private String sysDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public File getCtlFile(String path)
        throws IOException {
        LOGGER.debug("创建ctl文件");
        String filePath = InterfUtil.pathEndAsSeparator(path) + this.getTableName() + ".CTL";
        // 创建控制文件
        File file = new File(filePath);
        if (file.exists() || file.createNewFile()) {
            // 如果控制文件不存在，则创建
            // 赋予权限
            InterfUtil.changeFolderPermission(file);
            List<String> lines = new ArrayList<>();
            lines.add("LOAD DATA");
            lines.add("INFILE '" + getDataFile() + "'");
            lines.add(getLoadType());
            lines.add("INTO TABLE " + getTableName());
            lines.add("FIELDS TERMINATED BY '" + getFieldTerm() + "'");
            if (getOptionTerm() != null) {
                lines.add("optionally enclosed by '" + getOptionTerm() + "'");
            }
            lines.add("TRAILING NULLCOLS");
            lines.add(getColsAsStr());
            FileUtils.writeLines(file, lines);
        }
        else {
            LOGGER.error("创建ctl文件失败");
        }
        return file;
    }

    /**
     * 取得 sqlldr控制文件对象表的所有列
     * 
     * @return sqlldr控制文件对象表的所有列
     * @see
     * @since 1.0
     */
    public String getColsAsStr() {
        StringBuilder sb = new StringBuilder();
        String[] colsArr = SqlldrColsEnum.getColsAsArr(getTableName());
        sb.append(GlobalConstant.OPEN_PARENTHESIS);
        for (int i = 0; i < colsArr.length; i++ ) {
            if (i > 0) {
                sb.append(GlobalConstant.COMMA).append(GlobalConstant.LINE_SEPARATOR);
            }
            sb.append(getColSqlLine(colsArr[i]));
        }
        sb.append(GlobalConstant.CLOSE_PARENTHESIS);
        return sb.toString();
    }

    /**
     * 根据字段名返回控制文件的列
     * 
     * @param colName 字段名
     * @return 控制文件的列</br>
     *         例如：COL VARCHAR(50)
     * @see
     * @since 1.0
     */
    private String getColSqlLine(String colName) {
        for (TableColumn col : this.cols) {
            if (colName.equals(col.getColumnName())) {
                return col.getSqlldrLine();
            }
        }
        // 平台日期替换
        String getSysDate = "getSysDate";
        if (StringUtil.contains(colName, getSysDate)) {
            return StringUtil.replaceAll(colName, getSysDate, getSysDate());
        }
        return colName;
    }

    /**
     * @param tableName 表名
     * @param dataFile 数据文件名
     */
    public CtlArg(String tableName, String dataFile) {
        this.tableName = tableName;
        this.dataFile = dataFile;
        TableStructService tableStructService = ServiceMapperUtil.getApplicationContext().getBean(
            TableStructService.class);
        this.cols = tableStructService.selectTableStruct(tableName);
    }

    /**
     * 获取 tableName
     * 
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 tableName
     * 
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取 dataFile
     * 
     * @return dataFile.
     */
    public String getDataFile() {
        return dataFile;
    }

    /**
     * 设置 dataFile
     * 
     * @param dataFile dataFile
     */
    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * 获取 loadType
     * 
     * @return loadType.
     */
    public String getLoadType() {
        return loadType;
    }

    /**
     * 设置 loadType
     * 
     * @param loadType loadType
     */
    public void setLoadType(String loadType) {
        this.loadType = loadType;
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
     * 获取 optionTerm
     * 
     * @return optionTerm.
     */
    public String getOptionTerm() {
        return optionTerm;
    }

    /**
     * 设置 optionTerm
     * 
     * @param optionTerm optionTerm
     */
    public void setOptionTerm(String optionTerm) {
        this.optionTerm = optionTerm;
    }

    /**
     * 获取 nullCols
     * 
     * @return nullCols.
     */
    public boolean isNullCols() {
        return nullCols;
    }

    /**
     * 设置 nullCols
     * 
     * @param nullCols nullCols
     */
    public void setNullCols(boolean nullCols) {
        this.nullCols = nullCols;
    }

    /**
     * 获取 cols
     * 
     * @return cols.
     */
    public List<TableColumn> getCols() {
        return cols;
    }

    /**
     * 设置 cols
     * 
     * @param cols cols
     */
    public void setCols(List<TableColumn> cols) {
        this.cols = cols;
    }

    /**
     * 获取 sysDate
     * 
     * @return sysDate.
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * 设置 sysDate
     * 
     * @param sysDate sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
}
