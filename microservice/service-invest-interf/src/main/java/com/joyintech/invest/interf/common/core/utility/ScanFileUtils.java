package com.joyintech.invest.interf.common.core.utility;

import java.util.Map;

import com.joyintech.framework.util.JsonUtils;

public class ScanFileUtils {

	public static ScanSFTPEntity scanFile(String resStr){
		
		// 把请求报文json转为map
		Map<String, Object> dataMap = JsonUtils.json2Object(resStr, Map.class);
		// 获取报文的body部分
		Map<String, Object> requestBody = (Map<String, Object>) dataMap.get("body");
		// 获取报文的head部分
		Map<String, Object> requstHead = (Map<String, Object>) dataMap.get("head");
		// 从报文头中获取接口id
		String interfId = (String) requstHead.get("INF_ID");
		// 从报文头中获取序列号
		String seqNo = (String) requstHead.get("REQUEST_SEQNO");

		// sftp文件路径
		String sftpFilePath = (String) requestBody.get("FILE_PATH");
		
          //创建该对象用来携带接口相关参数并记录异步线程实时状态
			ScanSFTPEntity entity = new ScanSFTPEntity();
			entity.setInterfId(interfId);
			entity.setRequestSeqNo(seqNo);
			entity.setSftpFilePath(sftpFilePath);
			// 启动异步线程扫描sftp文件是否上传成功
			new Thread(new ScanSFTPFile(entity)).start();

			// 获取异步线程的返回值
			while (true) {

				if ((entity.getEndTime() - entity.getStartTime() < 5 * 60 * 1000) && entity.getScanCode().equals("0")) {// 扫描时间在5分钟以内 并且状态为失败，则睡眠1秒，接着循环
 
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else if ((entity.getEndTime() - entity.getStartTime() < 5 * 60 * 1000)
						&& entity.getScanCode().equals("1")) {// 扫描时间在5分钟以内并且状态为成功

					break;
				} else {

					break;
				}
			}
		
			return entity;

		
	}
}
