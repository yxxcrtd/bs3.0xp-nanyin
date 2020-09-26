package com.joyintech.tams.valuation.common.interf.base.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//查找目录下指定文件名的文件   
public class SearchFilePath {

	static Logger logger = LoggerFactory.getLogger(SearchFilePath.class);

	static int countFiles = 0;// 声明统计文件个数的变量

	static int countFolders = 0;// 声明统计文件夹的变量

	public static File[] searchFile(File folder, final String keyWord) {// 递归查找包含关键字的文件

		File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件

			@Override
			public boolean accept(File pathname) {// 实现FileFilter类的accept方法
				if (pathname.isFile())// 如果是文件
					countFiles++;
				else
					// 如果是目录
					countFolders++;
				String pathnames = pathname.getName().toLowerCase();
				String keyWords = keyWord.toLowerCase();
				if (pathname.isDirectory() || (pathname.isFile() && pathnames.contains(keyWords)))// 目录或文件包含关键字
					return true;
				return false;
			}
		});

		List<File> result = new ArrayList<File>();// 声明一个集合
		if (subFolders != null) {
			for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
				if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
					result.add(subFolders[i]);
				} else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
					File[] foldResult = searchFile(subFolders[i], keyWord);
					for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
						result.add(foldResult[j]);// 文件保存到集合中
					}
				}
			}
			File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
			result.toArray(files);// 集合数组化
			return files;
		} else {
			logger.info("指定路径下不存在文件夹或文件:" + folder.getAbsolutePath());
			return null;
		}

	}

	public static void copyFile(String remoteFile, String localDir) {
		FileInputStream fis = null;

		FileOutputStream fos = null;

		byte[] b = new byte[1024];
		int a;
		File file = new File(remoteFile);
		File localFile = new File(localDir);
		if (file.exists()) {
			if (!localFile.exists()) {
				localFile.mkdirs();
			} else if (!localFile.isDirectory()) {
				boolean rec = localFile.delete();
				if (rec) {
					localFile.mkdirs();
				}
			}
			File localfile = new File(localDir + File.separator + file.getName());
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(localfile);
				while ((a = fis.read(b)) != -1) {
					fos.write(b, 0, a);
				}
				fos.flush();
			} catch (FileNotFoundException e) {
				logger.error("拷贝文件是获取文件失败!", e);
			} catch (IOException e) {
				logger.error("文件拷贝失败!", e);
			} finally {
				try {
					if (null != fos) {
						fos.close();
					}
				} catch (Exception e) {
					logger.error("输入输出流关闭失败!", e);
				}
				try {
					if (null != fis) {
						fis.close();
					}
				} catch (Exception e) {
					logger.error("输入输出流关闭失败!", e);
				}
			}
		}

	}

	/**
	 * 获取指定路径下所有文件
	 * 
	 * @param localPath
	 *            文件路径
	 * @param flag
	 *            碰到文件夹是否递归查询
	 * @return
	 */
	public static List<File> searchAllFile(String localPath, boolean flag) {
		List<File> fileList = new ArrayList<File>();
		File localFile = new File(localPath);// 获取文件夹
		if (localFile.isDirectory()) {// 是文件夹则查找目录下文件
			File[] files = localFile.listFiles();// 获取文件夹下的文件列表
			if (files != null) {
				for (File file : files) {
					// 如果是文件夹且flag为true 则递归查询
					if (file.isDirectory() && flag) {
						fileList.addAll(searchAllFile(file.getPath(), flag));
					} else if (file.isFile()) {// 如果是文件则直接填入集合
						fileList.add(file);
					}
				}
			}
		} else {// 非文件夹直接放入集合结束
			fileList.add(localFile);
		}
		return fileList;
	}

	/**
	 * 获取指定路径下所有文件
	 * 
	 * @param localPath
	 *            文件路径
	 * @param flag
	 *            碰到文件夹是否递归查询
	 * @return
	 */
	public static List<File> searchFileByName(String localPath, String fileName) {
		List<File> fileList = new ArrayList<File>();
		File localFile = new File(localPath);// 获取文件夹
		if (localFile.isDirectory()) {// 是文件夹则查找目录下文件
			File[] files = localFile.listFiles();// 获取文件夹下的文件列表
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						fileList.addAll(searchFileByName(file.getPath(), fileName));
					} else if (file.isFile() && file.getName().indexOf(fileName) >= 0) {// 如果是文件则直接填入集合
						fileList.add(file);
					}
				}
			}
		} else if (localFile.getName().indexOf(fileName) > 0) {// 非文件夹直接放入集合结束
			fileList.add(localFile);
		}
		return fileList;
	}

	/**
	 * 获取文件绝对路径 <功能详细描述>
	 * 
	 * @param xmlPath
	 * @param tempNo
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static List<String> queryFilePath(String xmlPath, String tmpEName) {
		List<String> pathList = new ArrayList<String>();
		String path;
		File folder = new File(xmlPath);// 默认目录
		String keyword = tmpEName; // 文件名
		File[] result = searchFile(folder, keyword);// 调用方法获得文件数组
		if (result != null) {
			for (int i = 0; i < result.length; i++) {// 循环显示文件
				File file = result[i];
				path = file.getAbsolutePath();// 显示文件绝对路径
				pathList.add(path);
			}
		}
		return pathList;
	}
}