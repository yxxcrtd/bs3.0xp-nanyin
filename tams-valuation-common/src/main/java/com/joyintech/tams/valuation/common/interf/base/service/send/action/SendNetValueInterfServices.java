package com.joyintech.tams.valuation.common.interf.base.service.send.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcraft.jsch.SftpException;
import com.joyintech.framework.file.SafelyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.service.send.dao.PinAnSendNetValueInterfMapper;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValNetValueConfirmDto;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValRecvServerInfoEntity;
import com.joyintech.tams.valuation.common.interf.base.service.send.utils.SendFtpUtil;

/*
 * 文件名：SendNetValueInterfAction.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：平安估值核算系统净值下发资管系统接口
 * 净值下发TCMS系统，文件夹时间为净值日期，文件日期比文件夹日期多一天。
 * 
 * 修改人：wangwei1
 * 修改时间：2018年8月03日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
@Service
@Scope("prototype")
public class SendNetValueInterfServices {

	@Autowired
	private PinAnSendNetValueInterfMapper mapper;

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(SendNetValueInterfServices.class);

	/**
	 * 回车换行符
	 */
	public static final String CRLF = "\n";
	/**
	 * 分隔符
	 */
	public static final String FGF = "|$|";

	/**
	 * 发送净值信息 到 资管系统
	 *
	 * @param string
	 * @param model
	 *            估值Model
	 * @return
	 * @throws SftpException
	 * @throws JSchException
	 * @throws IOException
	 * @throws ParseException
	 * @throws Exception
	 */
	public int execute(ValNetValueConfirmDto dto) {
		/* 发送净值信息 */
		return upLoadDataFtp(dto);
	}

	/**
	 * @param sendNetValueData
	 *            上传数据
	 * @param curDate
	 *            日期
	 * @param dto
	 * @param flag
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSchException
	 * @throws SftpException
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private int upLoadDataFtp(ValNetValueConfirmDto dto) {
		try {
			StringBuffer sendNetValueData = new StringBuffer();

			/* 得到数净值日期 */
			String curDate = dto.getValDate().replace("-", "");

			log.info("净值下发接口开始：" + "日期：" + curDate);

			List<ValNetValueConfirmDto> list = new ArrayList<ValNetValueConfirmDto>();
			// 查询组装数据信息
			try {
				list = mapper.queryNetValue(dto.getValDate());
			} catch (Exception e) {
				log.info("净值下发接口：查询净值信息失败！" + e.getMessage());
				return 0;
			}

			// 循环组装数据
			try {
				if (list.size() > 0) {
					for (ValNetValueConfirmDto valNetValueConfirmDto : list) {
						sendNetValue_N(valNetValueConfirmDto, sendNetValueData);
					}
				}
			} catch (Exception e) {
				log.info("净值下发接口：组装净值字符串失败！" + e.getMessage());
				return 0;
			}
			// 获取Sftp配置
			ValRecvServerInfoEntity valRecvServerInfoEntity = new ValRecvServerInfoEntity();
			valRecvServerInfoEntity.setRsStatus("S");
			valRecvServerInfoEntity.setSystemID("ZG");
			ValRecvServerInfoEntity serverInfo = mapper.querySftpServer(valRecvServerInfoEntity);

			// 用户名
			String serverName = serverInfo.getServerName();
			// 密码
			String password = serverInfo.getPassword();
			// Ip 服务器地址
			String serverIp = serverInfo.getServerIp();
			// Port 服务器端口
			int serverPort = serverInfo.getServerPort();
			// 本地路径
			String localPath = serverInfo.getLocalPath();
			// 远程路径
			String remotePath = serverInfo.getRemotePath() + File.separator + serverInfo.getSystemID();
			// 登录类型
			String loginType = serverInfo.getServerType();

			// 格式化净值日期
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date valdate = null;

			try {
				valdate = format.parse(curDate);
			} catch (ParseException e) {
				log.info("净值下发接口失败：日期转换错误！" + e.getMessage());
				return 0;
			}

			// 文件日期
			String fileDate = format.format(DateUtil.addDay(valdate, +1));

			// 判断文件夹是否存在
			File fileMkdirs = new SafelyFile(localPath + File.separator + serverInfo.getSystemID() + File.separator + curDate);
			if (!fileMkdirs.exists()) {
				fileMkdirs.mkdirs();
			}

			// 文件名
			String fileName = "VAS-ZG-" + fileDate.substring(0, 8) + "-01.txt";
			log.info("净值下发接口文件名:" + fileName);

			// 文件全路径
			String filePath = localPath + File.separator  + serverInfo.getSystemID() + File.separator +curDate + File.separator + fileName;
			log.info("净值下发接口文件全路径:" + filePath);

			// 文件夹下是否有此文件，没有则创建
			File file = new SafelyFile(filePath);
			if (file.exists()) {
				boolean deleteFlag = file.delete();
				if (deleteFlag) {
					log.debug("");
				}
			}

			// 重新创建文件，写入数据
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
				bw.write(sendNetValueData.toString());
				log.info("净值下发接口写入:" + sendNetValueData.toString());
			} catch (IOException e) {
				log.info("净值下发接口:" + file + " 文件创建写入失败！" + e.getMessage());
				return 0;
			}finally {
				if(bw != null){
					bw.close();
				}
			}

			// 测试时需要（可直接上传至FTP服务器）
			if ("01".equals(serverInfo.getExecInfoId())) {
				/* 数据上传 */
				SendFtpUtil sftpUtil = new SendFtpUtil();

				try {
					// 登录sftp
					if (DictCodeInterfList.接口服务器类型_SFTP_IDENTITY.getValue().equals(loginType)) {
						sftpUtil.loginById(serverName, password, serverIp, serverPort);
					}
					else {
						sftpUtil.login(serverName, password, serverIp, serverPort);
					}
					log.info("净值下发接口: sftp登录成功！");

					// 上传文件
					sftpUtil.putFile(filePath, remotePath + File.separator + curDate);
					log.info("净值下发接口: sftp上传文件成功！");

					// 关闭sftp
					sftpUtil.close();
					log.info("净值下发接口: sftp关闭成功！");

				} catch (SftpException e) {
					// TODO Auto-generated catch block
					log.info("净值下发接口: sftp登录上传文件失败！" + e.getMessage());
					return 0;
				}

			}

			log.info("净值下发接口结束!");

		} catch (Exception e) {
			log.info("净值下发接口失败：" + e.getMessage());
			return 0;
		}
		return 1;
	}

	private void sendNetValue_N(ValNetValueConfirmDto model, StringBuffer sendNetValueData) {
		/*
		 * 关于净值确认下发txt文档中的取值问题：
		 * DK4LK180004|$|DK4LK180004|$|平安财富-180天成长（净值型）人民币理财产品|$|20181008|$|1.
		 * 019|$|1.019|$||$|1|$|365 分割符|$|
		 * 帐套号，产品代码，产品名称，估值日期，单位净值（货币型产品默认为1，其他类型产品去取单位净值），单位净值/万份收益，7日年化收益（
		 * 没有7日年化收益则为空），年华收益（默认为1），计息基础
		 */
		String str = "";
		if (model != null) {
			/* 转化成字符串 */
			str = model.getBookId() + FGF + model.getFinprodId() + FGF + model.getProfitTypeCode() + FGF + model.getFinprodAbbr() + FGF
					+ model.getValDate().replace("-", "") + FGF + model.getAssetValue().toString() + FGF
					+ model.getNetUnitValue().toString() + FGF
					+ (model.getSevenDayRate().toString().equals("0") ? "" : model.getSevenDayRate().toString()) + FGF
					+ "1" + FGF + model.getPayProfit().toString() + FGF + model.getBasis().toString();
		}

		// 下发的字符串信息
		sendNetValueData.append(str).append(CRLF);
		log.info("净值下发接口内容:" + sendNetValueData.toString());

	}
}
