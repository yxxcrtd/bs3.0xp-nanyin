/*
 * Copyright (C),  兆尹科技有限公司
 * FileName: MinioProperties.java
 * Author:   Administrator
 * Date:     2022年8月2日 上午11:47:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.joyintech.tams.common.oss.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈minio配置基类〉<br> 
 * 〈功能详细描述〉
 *
 * @author ZY22350
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }
    /**
     * @param accessKey the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    /**
     * @return the secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }
    /**
     * @param secretKey the secretKey to set
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    /**
     * @return the bucket
     */
    public String getBucket() {
        return bucket;
    }
    /**
     * @param bucket the bucket to set
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }
    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    /**
     * @return the readPath
     */
    public String getReadPath() {
        return readPath;
    }
    /**
     * @param readPath the readPath to set
     */
    public void setReadPath(String readPath) {
        this.readPath = readPath;
    }
    private String readPath;
}
