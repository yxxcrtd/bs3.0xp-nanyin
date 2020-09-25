/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.service.ValValuationStatementQuery001Service;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.controller.ValVoucherQuery001Controller;
import com.joyintech.tams.valuation.common.utils.SftpTools;

/**
 * 模板示例<br>
 * 凭证查询
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Controller
 * @since 2.0
 */
@RestController(value="serviceValValuationStatementQuery001Controller")
@RequestMapping("ValValuationStatementQuery001")
public class ValValuationStatementQuery001Controller extends BaseController {

	/**
	 * ValVoucherQuery001Service
	 */
	@Autowired
	private ValValuationStatementQuery001Service valValuationStatementQuery001Service;
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValValuationStatementQuery001Controller.class);

//	/**
//	 * 重写init方法，加载页面 {@inheritDoc}
//	 */
//	@Override
//	public String init(Model model) {
//		// 业务类别下拉
//		this.setSelectOptionToModel(model, DictCode.文件类型);
//		return super.init(model);
//	}

//	/**
//	 * 导出信息excel
//	 * 
//	 * @return excel
//	 * @throws Exception
//	 *             异常
//	 * @see
//	 * @since 1.0
//	 */
//	@RequestMapping(ACTION_EXPORT)
//	public ResponseEntity<byte[]> exportt() throws Exception {
//		String datas = this.getParam("datas");
//		List<ValValuationStatementQuery001Dto> val001dto = JsonUtils.json2List(datas,
//				ValValuationStatementQuery001Dto.class);
//		// 如果当前画面的模板不存在，则下载所有模板
//		String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
//		String zipFileName = FileNameUtil.createFileName("zip");
//		File zipFile = new File(exportFilePath + File.separator + zipFileName);
//		FileOutputStream stream = null;
//		ZipOutputStream zipOut = null;
//		try {
//    		stream = new FileOutputStream(zipFile);
//    		zipOut = new ZipOutputStream(stream);
//    		InputStream input = null;
//    		String filepath1 = "";
//    		String filename = "";
//    		SftpTools sftpTools = new SftpTools();
//    		try {
//    			// 上传文件到文件服务器上
//                String ip = CacheUtil.getSysParam("FILESERVER_HOST");// 主机IP
//                Integer port = Integer.parseInt(CacheUtil.getSysParam("FILESERVER_PORT"));// 主机端口
//                String username = CacheUtil.getSysParam("FILESERVER_USERNAME");// 主机用户名
//                String password = CacheUtil.getSysParam("FILESERVER_PASSWORD");// 主机密码或秘钥地址
//                String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式
//
//                sftpTools.login(username, password, ip, port, sftpLoginType);// sftp登录
//                
//    		} catch (Exception e) {
//    			logger.error("",e);
//    		}
//    		for (int i = 0; i < val001dto.size(); i++) {
//    			filepath1 = val001dto.get(i).getFilePath();
//    			filename = val001dto.get(i).getFileName();
//    			File file = new File(filepath1);
//    			String remotePath = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH");
//    			String remoteFile = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH") + "/" + filename;
//    			try {
//    				// 下载路径从SYS_PARA获取
//    				String localdir = CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE");
//    				sftpTools.getFile(remotePath, remoteFile, localdir);
//    				input = new FileInputStream(file);
//    				zipOut.putNextEntry(new ZipEntry(file.getName()));
//    				int tmp = 0;
//    				while ((tmp = input.read()) != -1) {
//    					zipOut.write(tmp);
//    				}
//    			} catch (Exception e) {
//    				logger.error("",e);
//    			} finally {
//    				if (input != null) {
//    					input.close();
//    				}
//    			}
//    		}
//		
//			sftpTools.close();
//			zipOut.flush();
//		} catch (Exception e) {
//			logger.error("",e);
//		} finally {
//		    if (zipOut != null) {
//                zipOut.close();
//            }
//		    if (stream != null) {
//		        stream.close();
//		    }
//		}
//		return ExportUtil.export(zipFileName, zipFile, true);
//	}

	/**
	 * 查询数据获得
	 *
	 * @return 记账凭证数据
	 * @throws Exception
	 *             异常
	 * @see
	 */
	@PostMapping("/findPage/{page}/{length}")
	public Page<ValValuationStatementQuery001Dto> findPage(@RequestBody Map<String, Object> params,
	                                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valValuationStatementQuery001Service.findPage(page,length,params);

	}
	@PostMapping("/queryFilepath")
    public List<ValValuationStatementQuery001Dto> queryFilepath(@RequestBody Map<String, Object> params) {
        return valValuationStatementQuery001Service.queryFilepath(params);
    }
}
