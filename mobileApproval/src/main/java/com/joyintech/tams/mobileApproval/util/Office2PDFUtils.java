package com.joyintech.tams.mobileApproval.util;

import org.apache.commons.lang3.StringUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeException;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;


public class Office2PDFUtils {


//	private static IAppContext context = AppContext.getInstance();
	private static String winPath = "C:/Program Files (x86)/OpenOffice 4";

	private static String linuxPath = "/opt/openoffice4";

	private static String port = "8100";

	private static BlockingQueue<OfficeManager> OfficeManagerQueue = new LinkedBlockingDeque<OfficeManager>();

	private static Lock lock = new ReentrantLock();

	/**
	 * @Description: 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx)
	 *               转化为pdf文件
	 * @method: openOfficeToPDF
	 * @Param: inputFilePath 待转换文件地址
	 * @Param: outputFilePath 目标文件地址
	 * @return: boolean 转换结果
	 * @auther: jp
	 */
	static public boolean openOfficeToPDF(String inputFilePath) {
		return office2Pdf(inputFilePath);
	}

	/**
	 * @Description: 根据操作系统的名称，获取OpenOffice.org 的安装目录
	 * @method: getOfficeHome
	 * @Param:
	 * @return: java.lang.String OpenOffice.org 的安装目录
	 * @auther: jp
	 */
	private static String getOfficeHome() {
		String osName = System.getProperty("os.name");
		if (Pattern.matches("Linux.*", osName)) {
			return linuxPath;
		} else if (Pattern.matches("Windows.*", osName)) {
			return winPath;
		} else if (Pattern.matches("Mac.*", osName)) {
			return winPath;
		}
		return null;
	}

	/**
	 * @Description: 在bean创建实例时, 连接OpenOffice.org 并且启动OpenOffice.org
	 * @method: initOpenOfficeService
	 * @Param:
	 * @return: void
	 * @auther: jp
	 */
	private static void initOpenOfficeService() {
		DefaultOfficeManagerConfiguration builder = new DefaultOfficeManagerConfiguration();
		builder.setOfficeHome(getOfficeHome());
		// 设置任务执行超时为5分钟
		builder.setTaskExecutionTimeout(1000 * 60 * 5L);
		// 设置任务队列超时为1小时
		builder.setTaskQueueTimeout(1000 * 60 * 60 * 1L);
		OfficeManagerQueue.clear();
		for (String portTemp : port.split(",")) {
			builder.setPortNumbers(Integer.valueOf(portTemp));
			OfficeManager OfficeManager = builder.buildOfficeManager();
			try {
				OfficeManager.start();
			} catch (OfficeException e) {
				// 打印日志
				e.printStackTrace();
			}
			try {
				OfficeManagerQueue.put(OfficeManager);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description: bean销毁时停止
	 * @method: destroyOpenOfficeService
	 * @Param:
	 * @return: void
	 * @auther: jp
	 */
	private static void destroyOpenOfficeService() {
		for (OfficeManager manager : OfficeManagerQueue) {
			try {
				manager.stop();
			} catch (OfficeException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description: 转换文件
	 * @method: convertFile
	 * @Param: inputFile
	 * @Param: outputFilePath
	 * @Param: officeManager
	 * @return: java.io.File
	 * @auther: jp
	 */
	private static synchronized File convertFile(File inputFile, String outputFilePath,
			OfficeManager officeManager) throws OfficeException {
		File outputFile = new File(outputFilePath);
		// 假如目标路径不存在,则新建该路径
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		OfficeDocumentConverter converter = new OfficeDocumentConverter(
				officeManager);
		// 锁
//		lock.lock();
		converter.convert(inputFile, outputFile);
		// 释放锁
//		lock.unlock();
		try {
			OfficeManagerQueue.put(officeManager);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return outputFile;
	}

	/**
	 * 文件名需要唯一
	 */
	public static boolean office2Pdf(String inputFilePath) {
		OfficeManager officeManager = null;
		String outputFilePath = getOutputFilePath(inputFilePath);
		initOpenOfficeService();
		try {
			if (StringUtils.isEmpty(inputFilePath)) {
				return false;
			}
			File inputFile = new File(inputFilePath);
			if (!inputFile.exists()) {
				return false;
			}
			officeManager = OfficeManagerQueue.take();
			File file = convertFile(inputFile, outputFilePath, officeManager);
			if (!file.exists()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			destroyOpenOfficeService();
		}
	}

	/**
	 * 文件名需要唯一
	 */
	public static File officeToPdf(String inputFilePath) {
		OfficeManager officeManager = null;
		String outputFilePath = getOutputFilePath(inputFilePath);
		try {
			if (StringUtils.isEmpty(inputFilePath)) {
				return null;
			}
			File inputFile = new File(inputFilePath);
			File file = new File(outputFilePath);
			if (file.exists()) {
				return file;
			}
			if (!inputFile.exists()) {
				return null;
			}
			initOpenOfficeService();
			officeManager = OfficeManagerQueue.take();
			file = convertFile(inputFile, outputFilePath, officeManager);
			if (!file.exists()) {
				return null;
			} else {
				destroyOpenOfficeService();
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			destroyOpenOfficeService();
			return null;
		}
	}

	/**
	 * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"
	 * 
	 * @param inputFilePath
	 * @return
	 */
	private static String getPostfix(String inputFilePath) {
		return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
	}

	/**
	 * 获取输出文件
	 * 
	 * @param inputFilePath
	 * @return
	 */
	private static String getOutputFilePath(String inputFilePath) {
		String outputFilePath = inputFilePath.replaceAll("."
				+ getPostfix(inputFilePath), ".pdf");
		return outputFilePath;
	}
}
