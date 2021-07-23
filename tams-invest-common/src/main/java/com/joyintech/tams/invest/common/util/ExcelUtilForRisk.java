package com.joyintech.tams.invest.common.util;

import java.io.File;

/**
 * excel操作
 *
 * @author zhangnian
 * @version 1.0 2019年07月30日
 * @see ExcelUtilForRisk
 * @since 1.0
 */
public class ExcelUtilForRisk {

    /**
     * 将Excel表格中列号字母转成列索引，从1对应A开始
     *
     * @param column 列号字母
     * @return 列索引
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static int columnToIndex(String column)
            throws Exception {
        if (!column.matches("[A-Z]+")) {
            throw new Exception("Invalid parameter");
        }
        int index = 0;
        char[] chars = column.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++ ) {
            index += (chars[i] - 'A' + 1) * (int)Math.pow(26, chars.length - i - 1);
        }
        return index;
    }


    /**
     * 将excel表格中列索引转成列号字母，从A对应1开始
     *
     * @param index 列索引
     * @return 列号字母
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static String indexToColumn(int index)
            throws Exception {
        if (index <= 0) {
            throw new Exception("Invalid parameter");
        }
        index-- ;
        String column = "";
        do {
            if (column.length() > 0) {
                index-- ;
            }
            column = ((char)(index % 26 + 'A')) + column;
            index = (index - index % 26) / 26;
        }
        while (index > 0);
        return column;
    }

    /**
     * 修改文件名
     * wangyang1
     * @param filePath    需要修改的文件的路径
     * @param oldFileName 的文件的名称
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    public static boolean fixFileName(String filePath,String oldFileName, String newFileName) {
        File file = new File(filePath+File.separator+oldFileName);
        if (!file.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return false;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
            return false;
        String newFilePath = filePath + File.separator + newFileName;
        File newfile = new File(newFilePath);
        try {
            if (newfile.exists()){
                newfile.delete();
            }
            file.renameTo(newfile); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
        return true;
    }
}
