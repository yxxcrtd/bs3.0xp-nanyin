package com.joyintech.tams.common.basicdata.util;

import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author jinmaodong
 * @since v1.0 2021年4月16日
 *  继承框架的ExcelUtil工具类，并扩展读取的方法
 */
public class ComBasicDataExcelUtil extends ExcelUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 获取列表数据
     * @param fileName 文件路径
     * @return 列表数据
     */
    public static List<List<String>> getDataListLocal(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = ExcelUtil.readToList(
                    fileName, null, null, 0, 1, 0);
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }

    /**
     * 获取表头及列表数据
     * @param fileName 文件路径
     * @return 表头及列表数据
     */
    public static List<List<String>> getTempListLocal(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = ExcelUtil.readToList(
                    fileName, null, null, 0, 0, 0);

        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }
}
