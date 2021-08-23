package com.joyintech.service.valuation.basicdata.common;

import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;

import java.util.Arrays;

public final class CommonUtil {
    /**
     * 现金流代码生成
     * @param busiNo 业务代码
     * @param busiType 业务类型
     * @param branch 分支序号
     * @param busiType2 现金流二级类型
     * @return 现金流代码
     */
    public static String generateCashFlowCode(String busiNo, String busiType, long branch,
                                              String busiType2) {
        final String sepChar = String.valueOf((char)256);

        String tmpStr = StringUtil.joinString(
                Arrays.asList(StringUtil.defaultString(busiNo), StringUtil.defaultString(busiType),
                        String.valueOf(branch), StringUtil.defaultString(busiType2)),
                sepChar);

        return EncryptUtil.getInstance().md5(tmpStr);
    }
}
