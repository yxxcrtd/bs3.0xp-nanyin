package com.jixu.adapter.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.control.service.ExpFileSeqService;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.joyintech.framework.util.StringUtil;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/**
 * @ClassName WriteFileBasic
 * @author Pan Wei
 * @Date 2017年5月3日 上午9:25:39
 * @Description []
 */

public abstract class WriteFileBasic implements WriteDataFile {

	@Resource(name = "interfaceAdapterDao")
	protected InterfaceAdapterDao interfaceSourceDao;

	protected static final Logger logger = LoggerFactory.getLogger(WriteFileBasic.class);

	private String DEFAULT_FORMAT = "yyyyMMdd";

	@Autowired
	private ExpFileSeqService expFileSeqService;

	/**
	 * 获取要输出的文件
	 * 
	 * @param interfaceSource
	 * @return
	 * @throws Exception
	 */
	protected File getWriteFile(InterfaceSource interfaceSource, DataReadResult dataSet) throws Exception {
		String folderPath = interfaceSource.getOutFilepath();
		folderPath = parseDateFolder(folderPath);
		String filename = interfaceSource.getOutFileName();
		filename = parseOutFileName(filename, interfaceSource.getSourceID());
		File folder = new File(folderPath);
		if (!folder.isDirectory()) {
			boolean b = folder.mkdirs();
			if (!b) {
				logger.error("无法创建文件夹：" + folder);
			}
//			throw new Exception("文件夹不存在: " + folder);
		}
		String filePath = folder + "/" + filename;
		File file = new File(filePath);
		file.createNewFile();
		dataSet.setWriteFilePath(file.getPath());

		return file;
	}

	protected boolean writeFileResult(InterfaceSource interfaceSource, DataReadResult dataSet, int fileType)
			throws Exception {
		boolean writeSuccessFlag = false;
		File file = getWriteFile(interfaceSource, dataSet);
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		String fileSpilit = interfaceSource.getWriteSpilitChar();
		if (fileType == InterfaceConfig.csv) {
			fileSpilit = ",";
		}
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, FileEncodeType.optionHM.get(interfaceSource.getOutFileEnCodeType()));
			bw = new BufferedWriter(osw);
			List<List<Object>> fieldsDataSet = dataSet.getDataResult();
			if (fieldsDataSet != null && !fieldsDataSet.isEmpty()) {
				if (interfaceSource.isOutFileTitleFlag()) {
					List<String> headerList = dataSet.getDataHeader();
					StringBuilder sb = new StringBuilder();
					for (String header : headerList) {
						sb.append(header);
						if (!fileSpilit.equals("")) {
							sb.append(fileSpilit);
						}
					}
					if (fileSpilit != null && !fileSpilit.equals("")) {
						bw.write(sb.substring(0, sb.length() - fileSpilit.length()));
					} else {
						bw.write(sb.substring(0, sb.length()));
					}
					bw.newLine();
				}

				for (List<Object> fieldsList : fieldsDataSet) {
					StringBuilder sb = new StringBuilder();
					for (Object field : fieldsList) {
						if (field == null)
							sb.append("");
						else
							sb.append(field.toString());
						if (fileSpilit != null && !fileSpilit.equals("")) {
							sb.append(fileSpilit);
						}
					}
					if (fileSpilit != null && !fileSpilit.equals("")) {
						bw.write(sb.substring(0, sb.length() - fileSpilit.length()));
					} else {
						bw.write(sb.substring(0, sb.length()));
					}
					bw.newLine();
				}
			}
			if (StringUtil.isNotEmpty(interfaceSource.getEndChar())) {
				bw.write(interfaceSource.getEndChar());
			}
			writeSuccessFlag = true;
		} catch (IOException e) {
			writeSuccessFlag = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
			}
		}
		return writeSuccessFlag;
	}

	/**
	 * 解析带有特殊日期字符的路径
	 * 
	 * @param folderPath
	 * @return
	 */
	protected String parseDateFolder(String folderPath) {
		// 判断是否包含双斜杠，若包含双斜杠，则要求后面必须跟一个日期格式类型 add by wangqidong
		if (folderPath.contains("//")) {
			String ach = "\\\\";
			String bch = "//";
			int apoint = folderPath.indexOf(ach);
			int bpoint = folderPath.indexOf(bch);
			String datetime = "";
			if (apoint >= 0 && bpoint > apoint) {
				datetime = folderPath.substring(apoint + 2, bpoint);
			}
			String[] path = folderPath.split("\\\\");
			DEFAULT_FORMAT = datetime.trim();
			SimpleDateFormat date = new SimpleDateFormat(DEFAULT_FORMAT);
			datetime = date.format(new Date());
			folderPath = path[0] + "/" + datetime;
		}
		if (folderPath.contains("<")) {
			String ach = "<";
			String bch = ">";
			int apoint = folderPath.indexOf(ach);
			int bpoint = folderPath.indexOf(bch);
			String datetime = "";
			if (apoint >= 0 && bpoint > apoint) {
				datetime = folderPath.substring(apoint + 1, bpoint);
			}
			String[] path = folderPath.split("<");
			DEFAULT_FORMAT = datetime.trim();
			SimpleDateFormat date = new SimpleDateFormat(DEFAULT_FORMAT);
			List<Date> curDateList = interfaceSourceDao.getCurDate();
			datetime = date.format(curDateList.get(0));
			folderPath = path[0] + "/" + datetime;
		}
		return folderPath;
	}

	/**
	 * 解析带有特殊日期字符的文件名
	 * 
	 * @param filename
	 * @return
	 */
	@Transactional(value = "jixuTXManager", rollbackFor = Exception.class)
	protected String parseOutFileName(String filename, int sourceId) {
		// 数据库创建一个序列，从1开始，每次查询加1,最大值9999,循环
		if (filename.contains("//")) {
			String ach = "\\\\";
			String bch = "//";
			int apoint = filename.indexOf(ach);
			int bpoint = filename.indexOf(bch);
			String datetime = "";
			if (apoint >= 0 && bpoint > apoint) {
				datetime = filename.substring(apoint + 2, bpoint);
			}
			DEFAULT_FORMAT = datetime.trim();
			SimpleDateFormat date = new SimpleDateFormat(DEFAULT_FORMAT);
			datetime = date.format(new Date());
			filename = filename.replace(DEFAULT_FORMAT, datetime);
			filename = filename.replace("/", "");
			filename = filename.replace("\\\\", "");
			// 暂存初始化文件名
			String fileName = filename;
			List<String> Number = interfaceSourceDao.getFileSeq();
			String fileSeq = Number.get(0);
			fileSeq = fileSeq.substring(fileSeq.length() - 2, fileSeq.length());
			// 如果配置了后缀名，则拼接在后缀名前面
			filename = fileNameSeq(filename, Number.get(0), fileSeq);
			if (fileName.contains("@#")) {
				// 开始和结束标记
				String cch = "@#";
				String dch = "#@";
				String seqParam = "";
				int cpoint = fileName.indexOf(cch);
				int dpoint = fileName.indexOf(dch);
				if (cpoint >= 0 && dpoint > cpoint) {
					seqParam = fileName.substring(cpoint + 2, dpoint);
				}
				fileName = fileName.replace("@#", "");
				fileName = fileName.replace("#@", "");
				fileName = fileName.replace(seqParam, "");
				String[] seqArr = seqParam.split("\\|\\|");
//				String seq = interfaceSourceDao.getSeq(seqArr[0]).get(0);
				String seq = String.format("%010d", expFileSeqService.genNextSeq(sourceId, seqArr[0]));
				// 对指定序列号位数进行截取
				seq = seq.substring(seq.length() - Integer.parseInt(seqArr[1]), seq.length());
				// 处理序列位置
				filename = fileNameSeq(fileName, seqArr[1], seq);
			}
		}
		if (filename.contains("<")) {
			String ach = "<";
			String bch = ">";
			int apoint = filename.indexOf(ach);
			int bpoint = filename.indexOf(bch);
			String datetime = "";
			if (apoint >= 0 && bpoint > apoint) {
				datetime = filename.substring(apoint + 1, bpoint);
			}
			DEFAULT_FORMAT = datetime.trim();
			SimpleDateFormat date = new SimpleDateFormat(DEFAULT_FORMAT);
			List<Date> curDateList = interfaceSourceDao.getCurDate();
			datetime = date.format(curDateList.get(0));
			filename = filename.replace(DEFAULT_FORMAT, datetime);
			filename = filename.replace("<", "");
			filename = filename.replace(">", "");
			String fileName = filename;
			List<String> Number = interfaceSourceDao.getFileSeq();
			String fileSeq = Number.get(0);
			fileSeq = fileSeq.substring(fileSeq.length() - 2, fileSeq.length());
			// 如果配置了后缀名，则拼接在后缀名前面
			filename = fileNameSeq(filename, fileSeq, fileSeq);
			if (fileName.contains("@#")) {
				// 开始和结束标记
				String cch = "@#";
				String dch = "#@";
				String seqParam = "";
				int cpoint = fileName.indexOf(cch);
				int dpoint = fileName.indexOf(dch);
				if (cpoint >= 0 && dpoint > cpoint) {
					seqParam = fileName.substring(cpoint + 2, dpoint);
				}
				fileName = fileName.replace("@#", "");
				fileName = fileName.replace("#@", "");
				fileName = fileName.replace(seqParam, "");
				String[] seqArr = seqParam.split("\\|\\|");
//				String seq = interfaceSourceDao.getSeq(seqArr[0]).get(0);
				String seq = String.format("%010d", expFileSeqService.genNextSeq(sourceId, seqArr[0]));
				// 对指定序列号位数进行截取
				seq = seq.substring(seq.length() - Integer.parseInt(seqArr[1]), seq.length());
				filename = fileNameSeq(fileName, seqArr[1], seq);
			}
		}
		return filename;
	}

	protected String fileNameSeq(String fileName, String fileseq, String seq) {
		String str = "";
		if (fileName.contains(".") && Integer.parseInt(fileseq) > 0) {
			StringBuffer stringBuffer = new StringBuffer(fileName);
			str = stringBuffer.insert(fileName.indexOf("."), "_" + seq).toString();
		} else if (fileName.contains(".") && Integer.parseInt(fileseq) == 0) {
			StringBuffer stringBuffer = new StringBuffer(fileName);
			str = stringBuffer.insert(fileName.indexOf("."), seq).toString();
		} else if (!fileName.contains(".") && Integer.parseInt(fileseq) > 0) {
			str = fileName + "_" + seq;
		} else {
			str = fileName + seq;
		}
		return str;

	}

}
