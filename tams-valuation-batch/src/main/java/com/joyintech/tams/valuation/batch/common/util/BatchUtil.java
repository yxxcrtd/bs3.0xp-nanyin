/*
 * 文件名：BatchUtil.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2017年5月23日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.util;

import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2017年5月23日
 * @see BatchUtil
 * @since 1.0
 */
public class BatchUtil {

    /**
     * 设置batch的参数
     *
     * @param params 参数
     * @param accountType 账套类型
     * @see
     * @since 1.0
     */
//    public static void setParam(Map<String, Object> params, AccountType accountType) {
//        params.put("accountType_FIN", AccountType.理财账.getCode());
//        params.put("accountType_BAN", AccountType.银行账.getCode());
//        if (accountType == AccountType.理财账) {
//            params.put("accountType", AccountType.理财账.getCode());
//        }
//        else if (accountType == AccountType.银行账) {
//            params.put("accountType", AccountType.银行账.getCode());
//        }
//    }
    
    
    public static <T> void addAllToList(List<T> dest, List<T> orig) {
        if (dest != null && orig != null) {
            dest.addAll(orig);
        }
    }
}
