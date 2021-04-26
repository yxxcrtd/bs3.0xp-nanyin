package com.joyintech.tams.settle.batch.datadown.constant;

/**
 * @author 何阳阳
 * @date 2020/7/2
 * @see StlDataDownConstant
 * @since 1.0
 */
public interface StlDataDownConstant {

    /**
     * 上中登结算明细数据
     */
    int DATA_FILE_MASK_SZD_JSMX = 0x001;

    int DATA_FILE_MASK_SZD_ZQBD = 0x010;

    int DATA_FILE_MASK_SZD_ABCSJ = 0x011;

    int DATA_FILE_MASK_SZD_SJSJG = 0x100;

    int DATA_FILE_MASK_SZD_SJSMX = 0x101;

    int DATA_FILE_MASK_SZD_SJSFX = 0x110;

    int DATA_FILE_MASK_SZD_ZSMX = 0x111;
}
