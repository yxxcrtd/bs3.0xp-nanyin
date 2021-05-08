package com.joyintech.tams.svreport.chbreport.investreport.utils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;

public class WordBatchUtil {

	/**
	 * 导出单个word文档
	 * @param request
	 * @param response
	 * @param map
	 * @param srcFilePath
	 * @param filename
	 * @param newWordName
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> exportMillCertificateWord(Map map, String srcFilePath, String filename, String newWordName) throws IOException {
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(srcFilePath));
		//configuration.setDirectoryForTemplateLoading(new File(srcFilePath));
		Template freemarkerTemplate = configuration.getTemplate(filename);
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类的createDoc方法生成Word文档
			file = createDoc(map,freemarkerTemplate,newWordName);
			fin = new FileInputStream(file);
			return ExportUtil.export(newWordName, file, true);//ExportUtil.export(fileName, fin);
		}catch(Exception e){
			return null;
		} finally {
			if(fin != null) fin.close();
			if(out != null) out.close();
			if(file != null) file.delete(); // 删除临时文件
		}

	}

	/**
	 * 创建doc文档
	 * @param dataMap
	 * @param template
	 * @param newWordName
	 * @return
	 */
	private static File createDoc(Map<?, ?> dataMap, Template template, String newWordName) {
		File f = new File(newWordName);
		Template t = template;
		Writer w = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			w = new OutputStreamWriter(fos, "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				fos.close();
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return f;

	}

	/*
	 *压缩包方式导出多个word
	 *由于一次请求浏览器只能响应一次，想导出多个必须打包，亲测for循环导出只能导一个
	 *如果想做到分别单独下载，那就得用插件啦，这里不提供插件的做法
	 *思路：生成临时目录-在临时目录生成word-将临时目录打zip包-zip文件下载-删除临时目录和zip包，
	 * 回收系统资源
	 */
	public static ResponseEntity<byte[]> exportWordBatch(List<Map<String, Object>> mapList, List<String> titleList, String ftlFile, String srcFilePath, String zipname) throws IOException {
		File file = null;
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(srcFilePath));
		try {
			Template freemarkerTemplate = configuration.getTemplate(ftlFile, "UTF-8");
			String outPath = CacheUtil.getSysParam("upload_file_path") ;
			String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
			String zipFileName = zipname+".zip";
			File zipFile = new File(exportFilePath + File.separator + zipFileName);
			FileOutputStream stream = null;
			ZipOutputStream zipOut = null;
			try {
				stream = new FileOutputStream(zipFile);
				zipOut = new ZipOutputStream(stream);
				InputStream input = null;
				for (int i = 0; i < mapList.size(); i++) {
					try {
						Map<String, Object> map = mapList.get(i);
						String title = titleList.get(i);
						// 调用工具类的createDoc方法在临时目录下生成Word文档
						file = createDoc(map, freemarkerTemplate, outPath + "/" + title);
						// File file = new File(item.getFilePath());
						input = new FileInputStream(file);
						zipOut.putNextEntry(new ZipEntry(file.getName()));
						int tmp = 0;
						while ((tmp = input.read()) != -1) {
							zipOut.write(tmp);
						}

					}
					catch (Exception e) {
						//logger.error("", e);
					}
					finally {
						if (input != null) {
							input.close();
						}
						//add by yejijie 2020-06-29 start 在关闭流之前删除文件，存在文件不能删除的问题
						// 打包后删除生成的源文件
						if(file.exists()){
							boolean rec = file.delete();
							if (rec) {
								// logger.info("处理成功");
							}
						}
						//add by yejijie 2020-06-29 end
					}
				}
				zipOut.flush();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (zipOut != null) {
					zipOut.close();
				}
				if (stream != null) {
					stream.close();
				}
			}


			return   ExportUtil.export(zipFileName, zipFile, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 *压缩包方式导出多个word
	 *由于一次请求浏览器只能响应一次，想导出多个必须打包，亲测for循环导出只能导一个
	 *如果想做到分别单独下载，那就得用插件啦，这里不提供插件的做法
	 *思路：生成临时目录-在临时目录生成word-将临时目录打zip包-zip文件下载-删除临时目录和zip包，
	 * 回收系统资源
	 */
	public static  ResponseEntity<byte[]> exportWordBatchList(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> mapList, List<String> titleList, List<String> ftlFileList, String srcFilePath, String zipname) throws IOException {
		File file = null;
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(srcFilePath));
		try {
			String outPath = CacheUtil.getSysParam("upload_file_path") ;
			String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
			String zipFileName = zipname+".zip";//FileNameUtil.createFileName("zip");
			File zipFile = new File(exportFilePath + File.separator + zipFileName);
			Template freemarkerTemplate = null;
			FileOutputStream stream = null;
			ZipOutputStream zipOut = null;
			try {
				stream = new FileOutputStream(zipFile);
				zipOut = new ZipOutputStream(stream);
				InputStream input = null;
				for (int i = 0; i < mapList.size(); i++) {
					try {
						Map<String, Object> map = mapList.get(i);
						String title = titleList.get(i);
						String ftlFile=ftlFileList.get(i);
						freemarkerTemplate=configuration.getTemplate(ftlFile, "UTF-8");
						// 调用工具类的createDoc方法在临时目录下生成Word文档
						file = createDoc(map, freemarkerTemplate, outPath + "/" + title);

						// File file = new File(item.getFilePath());
						input = new FileInputStream(file);
						zipOut.putNextEntry(new ZipEntry(file.getName()));
						int tmp = 0;
						while ((tmp = input.read()) != -1) {
							zipOut.write(tmp);
						}
						// 打包后删除生成的源文件
						boolean rec = file.delete();
						if (rec) {
							// logger.info("处理成功");
						}
					}
					catch (Exception e) {
						//logger.error("", e);
					}
					finally {
						if (input != null) {
							input.close();
						}
					}
				}
				zipOut.flush();
			}
			catch (Exception e) {
				//logger.error("", e);
			}
			finally {
				if (zipOut != null) {
					zipOut.close();
				}
				if (stream != null) {
					stream.close();
				}
			}
			return   ExportUtil.export(zipFileName, zipFile, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 根据组装数据及ftl模板生成单个word文档
	 * @param map
	 * @param srcFilePath
	 * @param filename
	 * @param newWordName
	 * @return
	 * @throws IOException
	 */
	public static File createMillCertificateWord(Map map, String srcFilePath, String filename, String newWordName) throws IOException {
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(srcFilePath));
		Template freemarkerTemplate = configuration.getTemplate(filename);
		File file = null;
		try {
			String path = FileNameUtil.createUploadFilePath("");//创建路径
			File filepath = new File(path);
			if(!filepath.exists()){
				filepath.mkdirs();
			}
			// 调用工具类的createDoc方法生成Word文档
			//file = createDoc(map, freemarkerTemplate, newWordName);
			file = createDoc(map, freemarkerTemplate, path + File.separator + newWordName);
		}
		catch(Exception e){
			return null;
		}
		return file;

	}


}
