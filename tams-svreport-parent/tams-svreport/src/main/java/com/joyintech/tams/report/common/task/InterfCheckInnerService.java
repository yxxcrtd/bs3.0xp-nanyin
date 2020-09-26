package com.joyintech.tams.report.common.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.chencknum.service.CheckNumService;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;
import com.joyintech.tams.report.interf.common.util.InterfUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.FileIO;

@Service("InterfCheckInnerService")
@Scope("prototype")
public class InterfCheckInnerService extends InterfService {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(InterfCheckInnerService.class);
	/**
	 * 维护表常量
	 */
	private static final String[] XML_FILE_NAME = new String[] { "INNER_TOTALAMOUNT" };

	public void run() {
		// 获取昨日文件路径
		String receivePath0 = new StringBuilder("")
				.append(this.getParaVauleByparaCode(DictCodeListExt2.数据来源_销售系统.getValue()))
				.append(new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT_EN).format(DateUtil.addDay(new Date(), -1))).append(File.separator).toString();
		File dir0 = new File(receivePath0);
		String receivePath = new StringBuilder("")
				.append(this.getParaVauleByparaCode(DictCodeListExt2.数据来源_销售系统.getValue()))
				.append(new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT_EN).format(new Date())).append(File.separator).toString();
		// String receivePath="E:/recv/" + new
		// SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT).format(new Date()) +
		// File.separator;
		File dir = new File(receivePath);// /tisuser/sale/receive/20180723\
		/** 校验文件夹是否存在 */
		if ((!dir.exists() || !dir.isDirectory()) && (!dir0.exists() || !dir0.isDirectory())) {
			return;
		}
		// 昨日文件所有文件名
		List<String> fileNames0 = InterfUtil.getFileNamesNotHandle(receivePath0);
		this.doMethod(fileNames0, receivePath0);
		// 今日文件所有文件名
		List<String> fileNames = InterfUtil.getFileNamesNotHandle(receivePath);
		this.doMethod(fileNames, receivePath);
		// 扫描两条文件

	}

	public void doMethod(List<String> fileNames, String receivePath) {
		for (int i = 0; fileNames != null && i < fileNames.size(); i++) {
			String fileName = fileNames.get(i);
			if ("".equals(fileName) || fileName.contains(ReportGlobalConstant.FILE_SUFFIX_RES)) {
				continue;
			}
			if (fileName.contains(XML_FILE_NAME[0])){
//					&& fileName.contains(new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT).format(DateUtil.addDay(new Date(), -1)))) {
				// 12点之后解析
				long a = System.currentTimeMillis();
				logger.info("文件" + fileName + "解析入库开始" + DateUtil.now());
				/** 处理详细 */
				List<Map<String, String>> list = null;
				try {
					list = checkNumService.getCheckNumInterf(fileName, receivePath);
				} catch (Exception e) {
					logger.info(e.getMessage());
					logger.error(e.getMessage(),e);
					/** 异常发生时更改文件名，避免后续文件无法解析 */
					InterfUtil.replaceFileName(receivePath, fileName);
				}
				if (list == null) {
					continue;
				}
				// 反馈返回结果
				FileIO.sendResponseFile(list, fileName);
				/** 更改处理完成之后的文件名 */
				InterfUtil.replaceFileName(receivePath, fileName);
				logger.info("文件" + fileName + "使用时间:" + (System.currentTimeMillis() - a) + "毫秒");
			}
		}
	}

	@Resource
	private CheckNumService checkNumService;
}
