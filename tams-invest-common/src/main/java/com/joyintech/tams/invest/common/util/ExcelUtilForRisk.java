package com.joyintech.tams.invest.common.util;

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

}
