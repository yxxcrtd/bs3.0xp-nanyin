/*
 * Copyright (C),  兆尹科技有限公司
 * FileName: ContentTypeEnum.java
 * Author:   Administrator
 * Date:     2022年8月2日 下午1:40:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.joyintech.tams.common.oss.config;

import org.apache.commons.lang.StringUtils;

/**
 * 〈可进行预览文件类型枚举〉<br>
 * 〈针对文件服务器中可以进行预览类型枚举值获取，后期如果有新增在此处进行增加〉
 *
 * @author ZY22350
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ViewContentTypeEnum {
    DEFAULT("default", "application/octet-stream"), 
    JPG("jpg", "image/jpeg"),
    TIFF("tiff", "image/tiff"), 
    GIF("gif", "image/gif"),
    JFIF("jfif", "image/jpeg"),
    PNG("png", "image/png"),
    TIF("tif", "image/tiff"),
    ICO("ico", "image/x-icon"), 
    JPEG("jpeg", "image/jpeg"), 
    WBMP("wbmp", "image/vnd.wap.wbmp"), 
    FAX("fax", "image/fax"), 
    NET("net", "image/pnetvue"), 
    JPE("jpe", "image/jpeg"), 
    RP("rp", "image/vnd.rn-realpix");

    private String suffix;

    private String type;

    public static String getContentType(String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            return DEFAULT.getType();
        }
        suffix = suffix.substring(suffix.lastIndexOf(".") + 1);
        for (ViewContentTypeEnum value : ViewContentTypeEnum.values()) {
            if (suffix.equalsIgnoreCase(value.getSuffix())) {
                return value.getType();
            }
        }
        return DEFAULT.getType();
    }

    ViewContentTypeEnum(String suffix, String type) {
        this.suffix = suffix;
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getType() {
        return type;
    }
}
