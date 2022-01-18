package com.joyintech.tams.common;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 接口共通类
 *
 * @author zcw
 *
 */

public class BaseInterfService extends BaseService  {

	@Autowired
	private XStream xstream;

	private static final Logger logger = LoggerFactory.getLogger(BaseInterfService.class);


	/**
	 * 根据Entity生成文件
	 *
	 * @param prodIdlist
	 * @param date
	 * @param path
	 * @return
	 */
	public String createFileFromEntity(List<String> prodIdlist, String date, String path, String institutionCode) {
		List<? extends BaseEntity> list = new ArrayList<>();
		try{
			logger.info("下游数据发布-"+getFileName()+"获取业务Entity开始-------------------");
			list = getBusEntity(prodIdlist, StringUtil.isEmpty(date) ?new Date(): DateUtil.parse(date), institutionCode, "F");
			logger.info("下游数据发布-"+getFileName()+"获取业务Entity结束-------------------");
		}catch(Exception e){
			logger.error(e.getMessage(), "下游数据发布-"+getFileName()+"获取业务Entity异常-------------------");
		}
		try{

			//所有数据不可包含updateTime.否则下游所有的删除当日数据逻辑均可能失效
			list.stream().forEach(dto2-> dto2.setUpdateTime(null));
			// 生成xml文件
			logger.info("下游数据发布-"+getFileName()+"生成文件开始-------------------------");
			writeEntityToXmlFile(list, path, date);
			logger.info("下游数据发布-"+getFileName()+"生成文件结束-------------------------");
		}catch(Exception e){
			logger.error(e.getMessage(), "下游数据发布-"+getFileName()+"获取业务Entity异常-------------------");
		}
		return "";
	}

	/**
	 * xml生成调用此方法
	 *
	 * @param list
	 * @param path
	 * @param date
	 */
	public void writeEntityToXmlFile(List<?> list, String path, String date) {
		File file = new SafelyFile(path+File.separator+getFileName()+ "_"+date+".xml");
		// 本地文件夹不存在先创建文件夹 add by zcw
		File exportFilePaths = new SafelyFile(path);
		if (!exportFilePaths.exists()) {
			exportFilePaths.mkdirs();
		}
		// 当配置参数为服务器路径时从新获取本地路径 add by zcw
		String saveFileDirPath = file.getAbsolutePath();

		xstream.alias("info", getEntityClass());
		try {
			FileOutputStream out = new FileOutputStream(saveFileDirPath);
			xstream.toXML(list, out);
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 获取业务对象
	 *
	 * @param prodIdlist
	 * @param date
	 * @param source
	 * @return
	 */
	public List<? extends BaseEntity> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {
		return null;
	}

	/**
	 * 获取文件名
	 *
	 * @return
	 */
	public String getFileName() {
		return null;
	}

	/**
	 * 获取泛型类名
	 *
	 * @return
	 */
	public Class getEntityClass() {
		return null;
	}

}
