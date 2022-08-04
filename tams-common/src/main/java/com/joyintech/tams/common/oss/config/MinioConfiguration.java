/*
 * Copyright (C),  兆尹科技有限公司
 * FileName: MinioConfiguration.java
 * Author:   Administrator
 * Date:     2022年8月2日 上午11:32:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.joyintech.tams.common.oss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 〈Minio自动装配〉<br>
 * 〈功能详细描述〉
 *
 * @author ZY22350
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
public class MinioConfiguration {

    @Autowired
    MinioProperties minioProperties;
    @Bean
    //如果配置文件中有  minio.enable属性，且值为true，代表该方法会执行，否则不会执行
    @ConditionalOnProperty(prefix = "minio", value = "enable", havingValue = "true")
    public MinioTemplate minioTemplate(MinioProperties minioProperties) {
        return new MinioTemplate( minioProperties);
    }
}
