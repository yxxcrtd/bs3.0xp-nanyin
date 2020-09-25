package com.joyintech.tams.invest.settle.order.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.settle.order.dto.StlOrderManagement001Dto;
import com.joyintech.tams.systemtools.dao.JoyinFSMapper;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;

/**
 * @program: tams-invest-parent
 * @description: 划款指令管理
 * @author: 强成西
 * @create: 2019-01-03 10:54
 **/
@Service
public class StlOrderManagement001Service extends CommonExportService<StlOrderManagement001Dto> {

    public Page<StlOrderManagement001Dto> findOrderData(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/StlOrderManagement/findOrderData",
                pageParam, StlOrderManagement001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/settle/order/config/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<StlOrderManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        return findOrderData(selectParam);
    }

    /**
     * 重新发送指令接口
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> resendRoderInfo(List<String>  orderIds) {
        return restTemplate.postForObject(
            ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/StlOrderManagermentInterf/resendOrderInfo", orderIds,
            Map.class);
    }

    /**
     * 刷新
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> refreshInfo() {
        return restTemplate.postForObject(
            ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/StlOrderManagermentInterf/refreshInfo", null,
            Map.class);
    }
    
    //Add By ZhangJin  For 邮储银行--从远程文件服务器下载附件 Start 20191213
    /**
     * 文件服务模板
     */
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * joyinFSMapper
     */
    @Autowired
    private JoyinFSMapper joyinFSMapper;
    /**
     * 检查选择的附件是否存在
     * @param pageParam 参数
     * @return 流水号
     * @see
     * @since 1.0
     */
    public AjaxResult checkFileExists(Map<String, Object> pageParam) {
        String fileNos = restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/StlOrderManagermentInterf/checkFileExists",
                pageParam, String.class);
        AjaxResult result = new AjaxResult();
        result.setData(fileNos);
        return result;
    }

    /**
     * 下载
     * @param param 参数
     * @return 导出
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> download(Map<String, Object> param) {
        String seqNos = param.get("seqNo").toString();
        List<String> seqNoList = StringUtil.splitToList(GlobalConstant.COMMA,seqNos);
        List<UploadFile> uploadFiles = new ArrayList<>();
        for(String seqNo:seqNoList){
            SysUploadFileInfo file = joyinFSMapper.selectByPrimaryKey(seqNo);
            UploadFile f = new UploadFile(null, file.getSeqNo(), new File(file.getPath()));
            f.setFileName(file.getFileName());
            joyinDFSTemplate.fetch(file);
            uploadFiles.add(f);
        }
        // 只有一个文件,直接导出,且不能删除源文件
        if (uploadFiles.size() == 1) {
            return ExportUtil.export(uploadFiles.get(0).getFileName(), uploadFiles.get(0).getTargetFile(), false);
        }
        // 如果生成文件数量大于1,需要压缩之后导出
        File outFile = null;
        List<File> exportFiles = new ArrayList<File>();
        if (uploadFiles.size() > 1) {
            for(UploadFile up:uploadFiles){
                exportFiles.add(up.getTargetFile());
            }
            String exportFilePath = CacheUtil.getSysParam("upload_file_path");
            String zipFileName = param.get("bizCode").toString() + ".zip";
            outFile = new File(exportFilePath + File.separator + zipFileName);
            // 导出路径不存在则重新生成
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            try {
                ZipFileUtil.compressZip(outFile, false, exportFiles.toArray(new File[] {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ExportUtil.export(outFile.getName(), outFile, true);
        }
        return null;
    }
    //Add For 邮储银行--从远程文件服务器下载附件 End 20191213

	@SuppressWarnings("unchecked")
	public Map<String, String> invalidOrderInfo(List<String> orderIds) {
		return restTemplate.postForObject(
	            ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/StlOrderManagermentInterf/invalidOrderInfo", orderIds,
	            Map.class);
	}
}
