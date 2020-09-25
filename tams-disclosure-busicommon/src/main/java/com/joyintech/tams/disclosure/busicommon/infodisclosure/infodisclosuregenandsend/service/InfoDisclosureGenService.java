package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepInstallFileMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.dao.InfoDisclosureGenAndSendMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity.InfoDisclosureGenAndSendEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity.RepProdIndexEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.CheckConfirmMessage;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service.RepCheckInfoService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.framework.util.FtpUtil;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;


@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDisclosureGenService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(InfoDisclosureGenService.class);

	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	@Autowired
	private RepRevealMapper repRevealMapper;
	@Autowired
	private RepInstallFileMapper repInstallFileMapper;
	
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	/**
	 * 拼接url中固定部分
	 */
	// private static final String MID_URL = "/rest_v2/reports";
	/**
	 * 信息披露生成报表并发送批处理service
	 */
	@Autowired
	private InfoDisclosureGenAndSendMapper infoDisclosureGenAndSendMapper;
	@Autowired
	private ReportDealService reportDealService;
	@Autowired
	private JasparConnectUtilService jasparConnectUtilService;
	/**
	 * 日志详情表mapper
	 */
	@Autowired
	private RepInstallLogDetailMapper repInstallLogDetailMapper;
	/**
     * 信批内容校验
     */
    @Autowired
    private RepCheckInfoService repCheckInfoService;

	public void genDetail(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity, String serverUrl,
			String serverUser, String serverPwd, Map<String, List<RepScreen>> equityInfo001Map) {
		String exeStatus = DictCodeListExt2.执行结果_失败.getValue();
		String execDetail = "";
		// 将查询出的配置转换成披露dto及日志详情dto
		// 将待组装数据成信批数据
		RepReveal repReveal = new RepReveal();
		// 将待组装数据成日志信息
		RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();
		try {
			// 開始執行時間
			repInstallLogDetail.setStrExecDate(DateUtil.now());
			repInstallFileToDto(infoDisclosureGenAndSendEntity, repReveal, repInstallLogDetail);
			// if(StringUtil.equals(ReportDictCodeList.批处理信息披露状态_未执行.getValue(),
			// infoDisclosureGenAndSendEntity.getExecStatus())){
			// //插入日志表
			// repInstallLogDetailMapper.insert(repInstallLogDetail);
			// infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_未生成.getValue());
			// }
			boolean genFlag = false;
			// 未生成数据操作
			// 当时报表类型时
			if (StringUtil.equals(DictCodeListExt2.信息披露报表类型_报表.getValue(),
					infoDisclosureGenAndSendEntity.getRepType())) {
				if (StringUtil.isNotEmpty(infoDisclosureGenAndSendEntity.getTemplateId())) {
					if (reportDealService.selectConfirmStatus(repReveal)) {
						// 查询配置连接参数
						// 查询参数是否完成
						List<RepScreen> screenList = equityInfo001Map.get(infoDisclosureGenAndSendEntity.getRepId());
						for (RepScreen repScreen : screenList) {
							// Field field =
							// ReflectionUtils.findField(infoDisclosureGenAndSendEntity.getClass(),
							// repScreen.getScreenKey());
							if (StringUtil.equals("portnum", repScreen.getScreenKey())) {
								if (StringUtil.isEmpty(infoDisclosureGenAndSendEntity.getProdid())) {
									execDetail = "报表参数不完整";
								}
								;
							} else if (StringUtil.equals("startDate", repScreen.getScreenKey())) {
								if (infoDisclosureGenAndSendEntity.getStartDate() == null) {
									execDetail = "报表参数不完整";
								}
								;
							} else if (StringUtil.equals("endDate", repScreen.getScreenKey())) {
								if (infoDisclosureGenAndSendEntity.getEndDate() == null) {
									execDetail = "报表参数不完整";
								}
								;
							}
						}
						if (StringUtil.isEmpty(execDetail)) {
							// 获取连接url
							String url = getAllUrl(serverUrl, serverUser, serverPwd, infoDisclosureGenAndSendEntity,
									true, screenList, repInstallLogDetail.getId());
							logger.info(
									"=====生成报表开始======" + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
							//添加报表名称处理逻辑  leipeng
							
							if(!StringUtil.isNotEmpty(infoDisclosureGenAndSendEntity.getProdid())){
								infoDisclosureGenAndSendEntity.setProdid("");
							}
							
							genFlag = gentFile(infoDisclosureGenAndSendEntity, url,screenList);
							logger.info(
									"=====生成报表结束======" + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

							// if(!genFlag){
							// logger.info("文件生成失败.尝试生成不完整报表文件!");
							// url = getAllUrl(serverUrl, serverUser, serverPwd,
							// infoDisclosureGenAndSendEntity,false,screenList);
							// logger.info("=====生成报表开始======"+com.joyintech.framework.util.DateUtil.getCurrentDateTime());
							// genFlag =
							// gentFile(infoDisclosureGenAndSendEntity, url);
							// logger.info("=====生成报表结束======"+com.joyintech.framework.util.DateUtil.getCurrentDateTime());
							// if(genFlag){
							// logger.info("尝试生成不完整报表文件成功!确认及披露方式变为手动");
							// repReveal.setIsConfirm(ReportDictCodeList.确认方式_手动.getValue());
							// repReveal.setIsReveal(ReportDictCodeList.确认方式_手动.getValue());
							// exeStatus =
							// ReportDictCodeList.执行结果_报表不完整.getValue();
							// }else{
							// throw new Exception("报表生成失败!");
							// }
							// }
							// else{
							//
							// }
							// 只有生成生工才往披露表中插入数据
							
							
							
							
							
							if (genFlag) {
								exeStatus = DictCodeListExt2.执行结果_成功.getValue();
								repReveal.setId(IDGenerator.generate());
								// 披露时间
								repReveal.setRevealDate(DateUtil.now());
								// 报表类别
								repReveal.setReportCategory(infoDisclosureGenAndSendEntity.getReportCategory());
								// 状态
								repReveal.setStatus(DisclosureDictCodeList.维护数据状态_有效.getValue());
								// 文件名
								repReveal.setSysFilename(infoDisclosureGenAndSendEntity.getFileName());
								// seq
								repReveal.setSysFilepath(infoDisclosureGenAndSendEntity.getFilePathSeqno());

								repReveal.setSysFilenameDate(DateUtil.now());
								//add by wm 增加阀值检查 2019-01-14 begin 宁波暂时不用，此阀值为中信的阀值
								//this.genDetailCheck(infoDisclosureGenAndSendEntity, repReveal, repInstallLogDetail);
								//add by wanghb in 2019-03-29 start 内容校验阈值
								infoDisclosureGenAndSendEntity.setCheckFor(DictCodeListExt2.校验位置_阈值.getValue());
			                    CheckConfirmMessage ccm = repCheckInfoService.check(infoDisclosureGenAndSendEntity,infoDisclosureGenAndSendEntity.getClass());
								if(ccm!=null&&!ccm.isFlag()){
                                    repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
                                    repReveal.setIsReveal(DictCodeListExt1.确认类型_手动.getValue());
                                    execDetail = ccm.getMsg();
                                }
								//add by wanghb in 2019-03-29 end 内容校验阈值
								execDetail = execDetail+repInstallLogDetail.getExecResult();
								//add by wm 增加阀值检查  2019-01-14 end
								// 先将上条数据更新为删除，在插入
								reportDealService.updateDataStatus(repReveal);
								// 插入
								repRevealMapper.insert(repReveal);
								
							} else {
								execDetail = "报表生成失败";
							}

							// if(genFlag){
							// exeStatus =
							// ReportDictCodeList.执行结果_成功.getValue();
							// }
						}
					} else {
						execDetail = "报表已确认，无法重新生成";
					}
				}
			}
			// 暂无，待设计确定后开发
			else if (StringUtil.equals(DictCodeListExt2.信息披露报表类型_集合.getValue(),
					infoDisclosureGenAndSendEntity.getRepType())) {

			}
			// if(StringUtil.equals(DictCodeList.是否_是.getValue(),
			// infoDisclosureGenAndSendEntity.getIsConfirm())&&
			// StringUtil.equals(ReportDictCodeList.批处理信息披露状态_已生成.getValue(),
			// infoDisclosureGenAndSendEntity.getExecStatus())){
			// repReveal.setConfirmStatus(ReportDictCodeList.确认状态_已确认.getValue());
			// repReveal.setConfirmDate(DateUtil.now());
			// infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_已确认.getValue());
			// }
			// if(StringUtil.equals(ReportDictCodeList.批处理信息披露状态_已确认.getValue(),
			// infoDisclosureGenAndSendEntity.getExecStatus())){
			// //将生成的文件上传至svn
			// boolean uploadFlag =
			// uploadFile(repReveal.getId(),infoDisclosureGenAndSendEntity.getFtpCode(),infoDisclosureGenAndSendEntity.getFileName(),
			// infoDisclosureGenAndSendEntity.getLocalPath(),infoDisclosureGenAndSendEntity.getFilePathSeqno());
			// if(uploadFlag){
			// infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_未披露.getValue());
			// }
			// }
		} catch (Exception e) {
			logger.error("error:",e);
		} finally {
			repInstallLogDetail.setExecStatus(exeStatus);
			repInstallLogDetail.setExecResult(execDetail);
			// 結束執行時間
			repInstallLogDetail.setEndExecDate(DateUtil.now());

			repInstallLogDetailMapper.insert(repInstallLogDetail);
			// 删除
			// infoDisclosureGenAndSendMapper.updateRepInstallStatus(infoDisclosureGenAndSendEntity);
			repInstallFileMapper.deleteByPrimaryKey(infoDisclosureGenAndSendEntity.getId());
		}
	}

	/**
	 * 上传文件到ftp服务器功能
	 * 
	 * @param id
	 *            披露表主键
	 * @param ftpCode
	 *            ftp相关配置
	 * @param fileName
	 *            文件名
	 * @param localPath
	 *            真实路径
	 * @param filePathSeqno
	 *            对应文件seqno
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean uploadFile(String id, String ftpCode, String fileName, String localPath, String filePathSeqno)
			throws FileNotFoundException {
		boolean isFtpUploadSucess = false;
		// ftp相关配置通过ftpCode获取
		String url = "192.168.70.66";
		int port = 21;
		String userName = "test1";
		String passWord = "112233";
		String path = "/test1/information";
		// String filename = infoDisclosureGenAndSendEntity.getFileName();
		if (StringUtil.isEmpty(filePathSeqno) && StringUtil.isNotEmpty(id)) {
			// 首先查询出披露表的信息
			RepReveal repReveal = repRevealMapper.selectByPrimaryKey(id);
			// 通过其报表关联id查询匹配相关信息
			UploadFile uploadFile = fileAttachmentProcess.fetch(repReveal.getSysFilepath());
			if (uploadFile != null) {
				fileName = uploadFile.getFileName();
				localPath = uploadFile.getTargetFile().getPath();
			}
		}
		if (StringUtil.isNotEmpty(localPath)) {
			InputStream input = new FileInputStream(localPath);
			isFtpUploadSucess = FtpUtil.uploadFile(url, port, userName, passWord, path, fileName, input);
		}

		return isFtpUploadSucess;
	}

	/**
	 * 生成文件方法
	 * 
	 * @param infoDisclosureGenAndSendEntity
	 * @return
	 * @throws MalformedURLException
	 */
	public Boolean gentFile(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity, String url,List<RepScreen> screenList)
			 {
		boolean flag = false;
		// 文件失败即为失败.不再提供不完整文件
		UploadFile fileInfo;
		try {
			fileInfo = jasparConnectUtilService.connectJasparAndDownload(url,
					infoDisclosureGenAndSendEntity.getProdid() + GlobalConstant.UNDER_LINE
							+ infoDisclosureGenAndSendEntity.getRepName(),
					infoDisclosureGenAndSendEntity.getFileType(), infoDisclosureGenAndSendEntity.getTemplateId(),
					infoDisclosureGenAndSendEntity.getProdid()
					,getParams(infoDisclosureGenAndSendEntity,screenList));
			infoDisclosureGenAndSendEntity.setFileName(fileInfo.getSrcName());
			infoDisclosureGenAndSendEntity.setFilePathSeqno(fileInfo.getFileNo());
			infoDisclosureGenAndSendEntity.setLocalPath(fileInfo.getTargetFile().getPath());
			flag = true;
		} catch (Exception e) {
			logger.error("error:",e);
			logger.info("=======报表生成失败");
		}
		return flag;
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 获取报表全路径
	 * 
	 * @param serverUrl
	 *            服务器url
	 * @param serverUser
	 *            用户
	 * @param serverPwd
	 *            密码
	 * @param infoDisclosureGenAndSendEntity
	 *            批处理配置
	 * @param check
	 *            与报表服务器通信时是否发送完整文件校验字段
	 * @return
	 */
	public String getAllUrl(String serverUrl, String serverUser, String serverPwd,
			InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity, boolean check, List<RepScreen> screenList,
			String detailLogId) {
		String url = serverUrl + ReportGlobalConstant.MID_URL + infoDisclosureGenAndSendEntity.getTemplatePath()
				+ ReportGlobalConstant.OBLIQUE_LINE + infoDisclosureGenAndSendEntity.getTemplateId()
				+ GlobalConstant.POINT + infoDisclosureGenAndSendEntity.getFileType();
		String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser
				+ ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd;
		// 获取相应报表对应的查询条件
		// List<RepScreen> screenList =
		// infoDisclosureGenAndSendMapper.getRepScreenList(infoDisclosureGenAndSendEntity.getRepId());
		for (RepScreen repScreen : screenList) {
			// Field field =
			// ReflectionUtils.findField(infoDisclosureGenAndSendEntity.getClass(),
			// repScreen.getScreenKey());
			if (StringUtil.equals("portnum", repScreen.getScreenKey())) {
				paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + "="
						+ infoDisclosureGenAndSendEntity.getProdid();
			} else if (StringUtil.equals("startDate", repScreen.getScreenKey())) {
				paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + "="
						+ DateUtil.format(infoDisclosureGenAndSendEntity.getStartDate());
			} else if (StringUtil.equals("endDate", repScreen.getScreenKey())) {
				paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + "="
						+ DateUtil.format(infoDisclosureGenAndSendEntity.getEndDate());
			}
		}

		// url增加一个参数：报表日志明细id(REP_INSTALL_LOG_DETAIL.id) zhangyf 20190104 start
		if (StringUtil.isNotEmpty(detailLogId)) {
			paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + "rep_id" + "=" + detailLogId;
		}
		// url增加一个参数：报表日志明细id(REP_INSTALL_LOG_DETAIL.id) zhangyf 20190104 end

		if (check) {
			paramStr += ReportGlobalConstant.AMPERS_AND + "errorFlag=1";
		}

		url = url + paramStr + ReportGlobalConstant.OBLIQUE_LINE + ReportGlobalConstant.HOPE_DOWNLOAD_NAME
				+ GlobalConstant.POINT + infoDisclosureGenAndSendEntity.getFileType();
		return url;
	}

	/**
	 * 组装dto
	 * 
	 * @param repInstallFile
	 *            组装信息
	 * @param repReveal
	 *            需要组装的披露信息
	 * @param repInstallLogDetail
	 *            组装的日志信息
	 */
	public void repInstallFileToDto(InfoDisclosureGenAndSendEntity repInstallFile, RepReveal repReveal,
			RepInstallLogDetail repInstallLogDetail) {
		// 将待组装数据成信批数据
		// id
		// repReveal.setId(IDGenerator.generate());
		// 报表编号
		repReveal.setRepId(repInstallFile.getRepId());
		// 报表名称
		repReveal.setReportName(repInstallFile.getReportName());
		// 产品编号
		repReveal.setProdid(repInstallFile.getProdid());
		// 产品名称
		repReveal.setProdName(repInstallFile.getProdName());
		// 开始日
		repReveal.setStartDate(repInstallFile.getStartDate());
		// 结束日
		repReveal.setEndDate(repInstallFile.getEndDate());
		// 是否自动披露
		repReveal.setIsReveal(repInstallFile.getIsReveal());
		// 是否自动确认
		repReveal.setIsConfirm(repInstallFile.getIsConfirm());
		// 确认状态
		repReveal.setConfirmStatus(DictCodeListExt1.披露确认状态_未确认.getValue());
		// 披露状态
		repReveal.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
		// 披露截止日
		repReveal.setRevealEndDate(repInstallFile.getRevealEndDate());
		// 披露渠道
		repReveal.setRevealChannel(repInstallFile.getRevealChannel());
		// 将待组装数据成日志信息
		// 主键 id
		repInstallLogDetail.setId(IDGenerator.generate());
		// 作业编号
		repInstallLogDetail.setJobId(repInstallFile.getJobId());
		// 作业流水号
		repInstallLogDetail.setJobSeq(repInstallFile.getJobSeq());
		// 产品编号
		repInstallLogDetail.setProdid(repInstallFile.getProdid());
		// 产品名称 暂无
		repInstallLogDetail.setProdName(repInstallFile.getProdName());
		// 开始日
		repInstallLogDetail.setStartDate(repInstallFile.getStartDate());
		// 结束日日
		repInstallLogDetail.setEndDate(repInstallFile.getEndDate());
		// 披露渠道
		repInstallLogDetail.setRevealChannel(repInstallFile.getRevealChannel());
		// 披露截止日
		repInstallLogDetail.setRevealEndDate(repInstallFile.getRevealEndDate());
		// 报表编号
		repInstallLogDetail.setRepId(repInstallFile.getRepId());
		// 报表名称
		repInstallLogDetail.setRepName(repInstallFile.getRepName());
		// 报表类型
		repInstallLogDetail.setRepType(repInstallFile.getRepType());
		// 生成文件类型
		repInstallLogDetail.setFileType(repInstallFile.getFileType());
		// 是否自动确认
		repInstallLogDetail.setIsConfirm(repInstallFile.getIsConfirm());
		// 是否自动披露
		repInstallLogDetail.setIsReveal(repInstallFile.getIsConfirm());
		// 状态
		repInstallLogDetail.setStatus(DictCodeListExt2.数据状态_有效.getValue());
	}

	/**
	 * 判断当天是否有净值或者万分收益
	 * 
	 * @param prodId
	 *            产品代码
	 * @param openMode
	 *            开放模式
	 * @param prodType
	 *            产品类型
	 * @param issueMode
	 *            发行方式
	 * @param repType
	 *            报表类型
	 * @param endDate
	 *            结束日
	 * @param actMdate
	 *            产品实际到期日
	 * @return
	 */
	public boolean isHasValue(String prodId, String openMode, String prodType, String issueMode, String repType,
			Date endDate, Date actMdate) {
		RepProdIndexEntity repProdIndexEntity = new RepProdIndexEntity();
		if (StringUtil.equals(repType, DictCodeListExt2.报表类别_净值披露.getValue())) {
			if (StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), prodType)) {
				repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordIndexInfo(prodId, endDate);
				if (repProdIndexEntity == null || repProdIndexEntity.getwYield() == null) {
					return false;
				} else if (repProdIndexEntity != null && repProdIndexEntity.getwYield() != null) {
					return true;
				}
			} else if (StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), prodType)) {
				if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(), issueMode)
						&& StringUtil.equals(DictCodeListExt2.开放模式_高频开放.getValue(), openMode)) {
					repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordIndexInfo(prodId, endDate);
				} else {
					repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueIndexInfo(prodId, endDate);
				}
				if (repProdIndexEntity == null || repProdIndexEntity.getUnitValue() == null) {
					return false;
				} else if (repProdIndexEntity != null && repProdIndexEntity.getUnitValue() != null) {
					return true;
				}
			}
			return true;
		} else if (StringUtil.equals(repType, DictCodeListExt2.报表类别_理财产品定期公告.getValue())) {
			if (StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), prodType)
					|| StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), prodType)) {
				repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueIndexInfo(prodId, endDate);
				if (repProdIndexEntity == null
						|| (repProdIndexEntity.getwYield() == null && repProdIndexEntity.getUnitValue() == null)) {
					return false;
				} else if (repProdIndexEntity != null
						&& (repProdIndexEntity.getwYield() != null || repProdIndexEntity.getUnitValue() != null)) {
					return true;
				}
			}
			return true;
		} else if (StringUtil.equals(repType, DictCodeListExt2.报表类别_到期公告.getValue())) {
			if (StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), prodType)) {
				repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueCurrencyMdateInfo(prodId,
						com.joyintech.framework.util.DateUtil.addDay(endDate, -1), actMdate);
				if (repProdIndexEntity != null
						&& (repProdIndexEntity.getwYield() != null && repProdIndexEntity.getYield7() != null)) {
					return true;
				} else {
					return false;
				}
			}
			else if (StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), prodType)) {
				repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueNetMdateInfo(prodId, endDate,
						actMdate);
				if (repProdIndexEntity != null
						&& (repProdIndexEntity.getUnitValue() != null && repProdIndexEntity.getUnitValueT() != null)) {
					return true;
				} else {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}
	/**
	 * 生成前check
	 * @param infoDisclosureGenAndSendEntity 传入查询出的数据
	 * @param repReveal 披露表中数据
	 */
	public void genDetailCheck(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity, 
			RepReveal repReveal, RepInstallLogDetail repInstallLogDetail){	
		if(StringUtil.equals(DictCodeListExt2.报表类别_成立公告.getValue(), infoDisclosureGenAndSendEntity.getReportCategory())){
			//成立公告募集金额不在募集上限与下限之间
			if(infoDisclosureGenAndSendEntity.getRaiseAmt().compareTo(infoDisclosureGenAndSendEntity.getRaiseMax())>0
					||infoDisclosureGenAndSendEntity.getRaiseAmt().compareTo(infoDisclosureGenAndSendEntity.getRaiseMin())<0){
				repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
				repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
				repInstallLogDetail.setExecResult("公告超过阀值");
			}
		}
		//净值披露
		else if(StringUtil.equals(DictCodeListExt2.报表类别_净值披露.getValue(), infoDisclosureGenAndSendEntity.getReportCategory())){
			if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
				RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueNetByEnddate(infoDisclosureGenAndSendEntity.getProdid()
						, infoDisclosureGenAndSendEntity.getEndDate());
				if(repProdIndexEntity!=null&&(repProdIndexEntity.getUnitValue().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getUnitValue().compareTo(BigDecimal.TEN)>0
						||repProdIndexEntity.getUnitValueT().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getUnitValueT().compareTo(BigDecimal.TEN)>0)){
					repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
					repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
					repInstallLogDetail.setExecResult("公告超过阀值");
				}
				else if(repProdIndexEntity ==null){
					repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
					repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
					repInstallLogDetail.setExecResult("公告超过阀值");
				}
			}
			else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
				RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueCurrencyByEnddate(infoDisclosureGenAndSendEntity.getProdid()
						, infoDisclosureGenAndSendEntity.getEndDate());
				if(repProdIndexEntity!=null&&(repProdIndexEntity.getwYield().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getwYield().compareTo(BigDecimal.TEN)>0)){
					repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
					repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
					repInstallLogDetail.setExecResult("公告超过阀值");
				}
				else if(repProdIndexEntity ==null){
					repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
					repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
					repInstallLogDetail.setExecResult("公告超过阀值");
				}
			}
		}
		else if(StringUtil.equals(DictCodeListExt2.报表类别_到期公告.getValue(), infoDisclosureGenAndSendEntity.getReportCategory())){
			if(isYield(infoDisclosureGenAndSendEntity)||isFeeRate(infoDisclosureGenAndSendEntity)){
				repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
				repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
				repInstallLogDetail.setExecResult("公告超过阀值");
			}
		}
		else if(StringUtil.equals(DictCodeListExt2.报表类别_理财产品定期公告.getValue(), infoDisclosureGenAndSendEntity.getReportCategory())){
			if(isYieldOut(infoDisclosureGenAndSendEntity)||isCountHundred(infoDisclosureGenAndSendEntity)||isTenHundred(infoDisclosureGenAndSendEntity)){
				repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
				repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
				repInstallLogDetail.setExecResult("公告超过阀值");
			}
		}
	}
	/**
	 * 判断万分收益或者净值、单位净值是否在【0,10】之间
	 * @param infoDisclosureGenAndSendEntity 掺入参数
	 * @return true不在false在
	 */
	private boolean isYieldOut(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
		boolean flag = false;
		RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.getRepPordValueIndexInfo(infoDisclosureGenAndSendEntity.getProdid(),
				infoDisclosureGenAndSendEntity.getEndDate());
		if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			if(repProdIndexEntity!=null&&(repProdIndexEntity.getUnitValue().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getUnitValue().compareTo(BigDecimal.TEN)>0
					||repProdIndexEntity.getUnitValueT().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getUnitValueT().compareTo(BigDecimal.TEN)>0)){
				flag = true;
			}
			else if(repProdIndexEntity ==null){
				flag = true;
			}
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			if(repProdIndexEntity!=null&&(repProdIndexEntity.getwYield().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getwYield().compareTo(BigDecimal.TEN)>0)){
				flag = true;
			}
			else if(repProdIndexEntity ==null){
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 各类资产占比是否为100
	 * @param infoDisclosureGenAndSendEntity 传入参数
	 * @return
	 */
	private Boolean isCountHundred(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
		Boolean isHundred = false;
		List<RepProdIndexEntity> list =  new ArrayList<RepProdIndexEntity>();
		if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			list = infoDisclosureGenAndSendMapper.selectNetProdAssetPercent(infoDisclosureGenAndSendEntity.getProdid(),
					infoDisclosureGenAndSendEntity.getEndDate());
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			list = infoDisclosureGenAndSendMapper.selectCurrencyProdAssetPercent(infoDisclosureGenAndSendEntity.getProdid(),
					infoDisclosureGenAndSendEntity.getEndDate());
		}
		BigDecimal count = BigDecimal.ZERO;
		for (RepProdIndexEntity repProdIndexEntity : list) {
			count = count.add(repProdIndexEntity.getPrecent());
		}
		if(count.compareTo(ONE_HUNDRED)!=0){
			isHundred = true;
		}
		return isHundred;
	}
	/**
	 * 前10资产比例
	 * @param infoDisclosureGenAndSendEntity 参数
	 * @return
	 */
	private boolean isTenHundred(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
		Boolean isTenHundred = false;
		List<RepProdIndexEntity> list =  new ArrayList<RepProdIndexEntity>();
		if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			list = infoDisclosureGenAndSendMapper.selectNetProdAssetPercentTen(infoDisclosureGenAndSendEntity.getProdid(),
					infoDisclosureGenAndSendEntity.getEndDate());
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			list = infoDisclosureGenAndSendMapper.selectCurrencyProdAssetPercentTen(infoDisclosureGenAndSendEntity.getProdid(),
					infoDisclosureGenAndSendEntity.getEndDate());
		}
		BigDecimal count = BigDecimal.ZERO;
		for (RepProdIndexEntity repProdIndexEntity : list) {
			count = count.add(repProdIndexEntity.getPrecent());
		}
		if(count.compareTo(ONE_HUNDRED)>0){
			isTenHundred = true;
		}
		return isTenHundred;
	}
	/**
	 * 收益率判断
	 * @param infoDisclosureGenAndSendEntity 查询条件
	 * @return
	 */
	private Boolean isYield(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
		boolean isYield = false;
		if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.selectNetYield(infoDisclosureGenAndSendEntity.getProdid());
			if(repProdIndexEntity!=null&&repProdIndexEntity.getUnitValue()!=null&&(repProdIndexEntity.getUnitValue().compareTo(BigDecimal.ZERO)<0||repProdIndexEntity.getUnitValue().compareTo(BigDecimal.TEN)>0)){
				isYield = true;
			}
			else if(repProdIndexEntity!=null&&repProdIndexEntity.getUnitValue()==null){
				isYield = true;
			}
			else if(repProdIndexEntity==null){
				isYield = true;
			}
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.selectCurrencyYield(infoDisclosureGenAndSendEntity.getProdid());
			if(repProdIndexEntity!=null&&repProdIndexEntity.getYield7()!=null&&repProdIndexEntity.getYield7().compareTo(ONE_HUNDRED)>0){
				isYield = true;
			}
			else if(repProdIndexEntity!=null&&repProdIndexEntity.getYield7()==null){
				isYield = true;
			}
			else if(repProdIndexEntity==null){
				isYield = true;
			}
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_预期收益型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			RepProdIndexEntity repProdIndexEntity = infoDisclosureGenAndSendMapper.selectProfitYield(infoDisclosureGenAndSendEntity.getProdid());
			if(repProdIndexEntity!=null&&repProdIndexEntity.getYieldE()!=null&&repProdIndexEntity.getYieldE().compareTo(ONE_HUNDRED)>0){
				isYield = true;
			}
			else if(repProdIndexEntity!=null&&repProdIndexEntity.getYieldE()==null){
				isYield = true;
			}
			else if(repProdIndexEntity==null){
				isYield = true;
			}
		}
		return isYield;
	}
	/**
	 * 收益率判断
	 * @param infoDisclosureGenAndSendEntity 查询条件
	 * @return
	 */
	private Boolean isFeeRate(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
		boolean isFeeRate = false;
		RepProdIndexEntity repProdIndexEntity = new RepProdIndexEntity();
		if(StringUtil.equals(DisclosureDictCodeList.产品类型_净值型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			repProdIndexEntity = infoDisclosureGenAndSendMapper.selectNetFee(infoDisclosureGenAndSendEntity.getProdid());
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_货币型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			repProdIndexEntity = infoDisclosureGenAndSendMapper.selectCurrencyFee(infoDisclosureGenAndSendEntity.getProdid());
		}
		else if(StringUtil.equals(DisclosureDictCodeList.产品类型_预期收益型产品.getValue(), infoDisclosureGenAndSendEntity.getProdType())){
			repProdIndexEntity = infoDisclosureGenAndSendMapper.selectProfitFee(infoDisclosureGenAndSendEntity.getProdid());
		}
		BigDecimal sFeeRate = repProdIndexEntity.getsFeeRate() == null ? BigDecimal.ZERO:repProdIndexEntity.getsFeeRate();
		BigDecimal tFeeRate = repProdIndexEntity.gettFeeRate() == null ? BigDecimal.ZERO:repProdIndexEntity.gettFeeRate();
		BigDecimal mFeeRate = repProdIndexEntity.getmFeeRate() == null ? BigDecimal.ZERO:repProdIndexEntity.getmFeeRate();
		BigDecimal otherFeeRate = repProdIndexEntity.getOtherFeeRate() == null ? BigDecimal.ZERO:repProdIndexEntity.getOtherFeeRate();
		if(sFeeRate.compareTo(BigDecimal.TEN)>0||tFeeRate.compareTo(BigDecimal.TEN)>0
				||mFeeRate.compareTo(BigDecimal.TEN)>0||otherFeeRate.compareTo(BigDecimal.TEN)>0){
			isFeeRate = true;
		}
		else if(sFeeRate.compareTo(BigDecimal.ZERO)==0||tFeeRate.compareTo(BigDecimal.ZERO)==0
				||mFeeRate.compareTo(BigDecimal.ZERO)==0||otherFeeRate.compareTo(BigDecimal.ZERO)==0){
			isFeeRate = true;
		}
		return isFeeRate;
	}
	/**
	 * 将报表参数放入map中
	 * @param infoDisclosureGenAndSendEntity 参数值
	 * @param screenList 含有参数
	 * @return 参数map
	 */
	public Map<String,Object> getParams(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity,List<RepScreen> screenList) {
		Map<String,Object> params = new HashMap<String,Object>();
		for (RepScreen repScreen : screenList) {
			// Field field =
			// ReflectionUtils.findField(infoDisclosureGenAndSendEntity.getClass(),
			// repScreen.getScreenKey());
			if (StringUtil.equals("portnum", repScreen.getScreenKey())) {
				params.put("portnum", infoDisclosureGenAndSendEntity.getProdid());
			} else if (StringUtil.equals("startDate", repScreen.getScreenKey())) {
				params.put("startDate", DateUtil.format(infoDisclosureGenAndSendEntity.getStartDate()));
			} else if (StringUtil.equals("endDate", repScreen.getScreenKey())) {
				params.put("endDate", DateUtil.format(infoDisclosureGenAndSendEntity.getEndDate()));
			}
		}		
		return params;
	}
}
