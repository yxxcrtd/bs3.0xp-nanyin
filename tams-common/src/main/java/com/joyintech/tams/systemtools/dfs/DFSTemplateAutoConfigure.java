/*
 * 文件名：DFSTemplateAutoConfigure.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.systemtools.dfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  自动装配文件配置
 * @author 王波
 * @version 1.0 2019年9月4日
 * @see DFSTemplateAutoConfigure
 * @since 1.0
 */
@Configuration
public class DFSTemplateAutoConfigure {
	// 使用SFTP 存储
	private final Integer SFTP_DFS_TYPE = 1;

	private final Integer NAS_DFS_TYPE  = 2;

	private final Integer FAST_DFS_TYPE = 3;

	@Value("${spring.application.dfstype:1}")
	private int dfstype;

	@Bean
	public JoyinDFSTemplate getJoyinFileSystemMethod() {
		if (SFTP_DFS_TYPE == dfstype) {
			// 使用SFTP存储
			return new JoyinDFSTemplate(new SftpFileSystemMethod());
		} else if (NAS_DFS_TYPE == dfstype) {
			return new JoyinDFSTemplate(new NASFileSystemMethod());
		}
		return null;
	}

}
