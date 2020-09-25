package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao.ValNetValueConfirm001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokFileInfoDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokNetValueCfmFileDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.utils.SftpTools;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 附件上传service
 * 
 * @author Administrator
 *
 */
@Service(value="serviceValNetValueConfirm003Service")
public class ValNetValueConfirm003Service extends CommonExportService<BokFileTransfer003Dto> implements BaseProcessService<BokFileTransfer003Dto> {

	@Autowired
	private ProgressBarService progressBarService;
	
	@Autowired
    private ValNetValueConfirm001Mapper valNetValueConfirm001Mapper;
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValNetValueConfirm003Service.class);
	
	/**
	 * 读取文件并上传到文件服务器
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		// 文件本地路径
		String filePath = request.getParameter("fileName");
		
		//产品id
		String finprodId = request.getParameter("finprodId");
		
		//估值表余额表标识
		String valBalFlag = request.getParameter("valBalFlag");
        if(StringUtil.isNotEmpty(valBalFlag)){
            if(valBalFlag.equals(DictCodeList.文件类型_估值表.getCodeName())){
                valBalFlag = DictCodeList.文件类型_估值表.getValue();
            }else {
                valBalFlag = DictCodeList.文件类型_余额表.getValue();
            }
        }
		
		//估值表余额表标识
        String valDateS = request.getParameter("valDate");
        String valDate = valDateS.replace("-", "");
        
		//产品唯一标识
        String prodName = finprodId+valDate+valBalFlag;
		// 文件名称
		String fileName = FileNameUtil.formatFileName(filePath);
		
		File file = new File(filePath);
		boolean rec = file.renameTo(new File(fileName));
		if(rec){
			logger.info("处理成功");
		}		
//		String execDate = request.getParameter("execDate");

        String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");// 主机IP
        Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));// 主机端口
        String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");// 主机用户名
        String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");// 主机密码或秘钥地址
        String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式

		// 主机远程路径
		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH");
//		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/"
//				+ DateUtil.format(DateUtil.parse(execDate), DateUtil.DEFAULT_OTHER_FORMAT);

		SftpTools sftpTools = new SftpTools();
		sftpTools.login(username, password, ip, port, sftpLoginType);// sftp登录

		sftpTools.putFileTransfer(fileName, remotePath,prodName);
		// 关闭登录
		sftpTools.close();

		progressBarService.updatePercent("ValNetValueConfirm003", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}
	
	
	public int insertFileTransferInfo(Map<String, Object> params) throws Exception {
	    BokFileTransfer003Dto dto = new BokFileTransfer003Dto();
	    //文件信息
	    BokFileInfoDto infoDto = new BokFileInfoDto();
	    //净值确认附件信息
	    BokNetValueCfmFileDto cfmDto = new BokNetValueCfmFileDto();
	    //产品代码
	    String finprodId = (String)params.get("finprodId");
	    cfmDto.setFinprodId(finprodId);
	    dto.setFinprodId(finprodId);
	    //估值表余额表标识
	    String valBalFlag = (String)params.get("valBalFlag");
	    if(StringUtil.isNotEmpty(valBalFlag)){
	        if(valBalFlag.equals(DictCodeList.文件类型_估值表.getCodeName())){
	            valBalFlag = DictCodeList.文件类型_估值表.getValue();
	        }else {
	            valBalFlag = DictCodeList.文件类型_余额表.getValue();
	        }
	    }
	    cfmDto.setValBalFlag(valBalFlag);
	    dto.setValBalFlag(valBalFlag);
	    //估值日期
	    String valDateS = (String)params.get("valDate");
	    Date valDate = DateUtil.parseDate(valDateS, "yyyy-MM-dd");
	    cfmDto.setValDate(valDate);
	    dto.setValDate(valDate);

	    //文件名称
	    String filename = (String)params.get("fileNameOnly");
	    infoDto.setFileName(filename);
	    dto.setFileName(filename);
	    
	    //文件类型
	    String fileType = DictCodeList.文件类型_净值确认下发附件.getValue();
	    infoDto.setFileType(fileType);
	    
	    //产品附件唯一标识
	    String valDateStr = valDateS.replace("-", "");
	    String prodName = finprodId+valDateStr+valBalFlag;
	    //文件存放目录
	    String filePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") +"/"+ prodName;
	    infoDto.setFilePath(filePath);
	    //校验是否已有数据
        List<BokFileTransfer003Dto> list= valNetValueConfirm001Mapper.quryBokFileInfo(dto);
        if(list.size()>0){
            String fileid = valNetValueConfirm001Mapper.quryBokFileInfoKey(infoDto);
            //删除原有纪录
            if(StringUtil.isNotEmpty(fileid)){
                infoDto.setFileId(fileid);
                cfmDto.setFileId(fileid);
                valNetValueConfirm001Mapper.deleteBokFileInfo(infoDto);
                valNetValueConfirm001Mapper.deleteBokNetValueCfmFile(cfmDto);
            }
        }
	    //创建时间
	    infoDto.setCreateTime(new Date());
	    cfmDto.setCreateTime(new Date());
	    //更新时间
	    infoDto.setUpdateTime(new Date());
	    cfmDto.setCreateTime(new Date());
	    infoDto.setCreateUser("admin");
	    cfmDto.setCreateUser("admin");
	    //fileId
        String fileId = IDGenerator.generate();
        infoDto.setFileId(fileId);
        cfmDto.setFileId(fileId);
	    int res = 0;
	    int res1 = valNetValueConfirm001Mapper.insertBokFileInfo(infoDto);
	    int res2 = valNetValueConfirm001Mapper.insertBokNetValueCfmFile(cfmDto);
	    if(res1>0&&res2>0){
	        res = 1;
	    }
	    return res;
	}
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<BokFileTransfer003Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
        //产品代码
        String finprodId = (String)params.get("finprodId");
        //估值表余额表标识
        String valBalFlag = (String)params.get("valBalFlag");
        if(StringUtil.isNotEmpty(valBalFlag)){
            if(valBalFlag.equals(DictCodeList.文件类型_估值表.getCodeName())){
                valBalFlag = DictCodeList.文件类型_估值表.getValue();
            }else {
                valBalFlag = DictCodeList.文件类型_余额表.getValue();
            }
        }
        //估值日期
        String valDates = (String)params.get("valDate");
        Date valDate = DateUtil.parseDate(valDates, "yyyy-MM-dd");
        params.put("finprodId", finprodId);
        params.put("valBalFlag", valBalFlag);
        params.put("valDate", valDate);
        List<BokFileTransfer003Dto> list = valNetValueConfirm001Mapper.queryBokFileInfoPageList(params);
        if(list.size()>0){
            
        }
        return super.queryForPageForSelectReturn(page,length,()-> valNetValueConfirm001Mapper.queryBokFileInfoPageList(params));
    }


    /* (non-Javadoc)
     * @see com.joyintech.workflow.engine.service.BusinessService#doComplete(com.joyintech.workflow.engine.entity.WorkflowEntity, com.joyintech.workflow.engine.constant.OperationType)
     */
    @Override
    public void doComplete(BokFileTransfer003Dto arg0, OperationType arg1) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 文件下载
     * 
     * @param request
     * @throws Exception
     */
    public BokFileTransfer003Dto downloadFile(String fileId) throws Exception {
        
        
        return  valNetValueConfirm001Mapper.qryBokFileInfoByFileId(fileId);
        
    }
    
    
    /**
     * 文件下载信息删除
     * 
     * @param request
     * @throws Exception
     */
    public void deleteFile(HttpServletRequest request) throws Exception {
        // 文件id
        String fileId = request.getParameter("fileId");
        //文件信息
        BokFileInfoDto infoDto = new BokFileInfoDto();
        //净值确认附件信息
        BokNetValueCfmFileDto cfmDto = new BokNetValueCfmFileDto();
        infoDto.setFileId(fileId);
        cfmDto.setFileId(fileId);
        valNetValueConfirm001Mapper.deleteBokFileInfo(infoDto);
        valNetValueConfirm001Mapper.deleteBokNetValueCfmFile(cfmDto);
    }
    
    
    
}
