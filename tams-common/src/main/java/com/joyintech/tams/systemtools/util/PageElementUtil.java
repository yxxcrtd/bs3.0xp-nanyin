/*
 * 文件名：PageElementUtil.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 16:09
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.util;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @see
 * @since 1.0
 */
public class PageElementUtil {

    public static final String PAGE_ELEMENT_CACHE_KEY = "PAGE_ELEMENT_CACHE_KEY";


    /**
     * 画面元素是否关闭
     * @return
     */
    public static Map<String, String> isPageElementShow(PageElementConfigDto dto) {
        Map<String, String> res = new HashMap<String, String>();
        List<PageElementConfigDto> list = (List<PageElementConfigDto>)CacheUtil.getAppCache(PAGE_ELEMENT_CACHE_KEY, List.class);
        if (list == null) {
            return res;
        }
        for (PageElementConfigDto x : list) {
            if (dto.getPageId().equals(x.getPageId())){
                res.put(x.getElementId(), x.getIsShow());
            }
        }
        return res;
    }
}
