package com.joyintech.tams.invest.asset.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.system.base.dao.SysUploadFileInfoMapper;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.interf.trade.fromxp.service.InterfServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  附件路径(从信评接口接收)
 *  磁盘共享 只需要处理文件信息即可
 * @author zznn
 * @date 2021/10/8
 */
@Service
public class AssetFileInfoService {

    @Autowired
    private AssetCommonService assetCommonService;
    @Autowired
    private InterfServiceLogService interfServiceLogService;

    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private SysUploadFileInfoMapper uploadFileInfoMapper;

    public void executeInterf(String id,String msg){
        try{
            JSONObject jsonObject = JSONObject.parseObject(msg);
            String finprodMarketId = jsonObject.getString("finprod_market_id");
            String finprodName = jsonObject.getString("finprod_name");
            JSONArray fileList = jsonObject.getJSONArray("file_list");
            if(null!=fileList&&fileList.size()>0){
                FinProduct finProduct = assetCommonService.getAsset(finprodMarketId,finprodName);
                String businessId = finProduct.getFinprodId();
                //删除该业务附件信息
                fileAttachmentProcess.removeByBusinessId(businessId);
                for (int i = 0; i < fileList.size(); i++) {
                    SysUploadFileInfo file = new SysUploadFileInfo();
                    JSONObject jso = (JSONObject)fileList.get(i);
                    String fileName = jso.getString("file_name");
                    String filePath = jso.getString("file_path");
                    file.setSeqNo(IDGenerator.generate());
                    file.setFileName(fileName);
                    file.setPath(filePath);
                    file.setBusinessId(businessId);
                    this.insertFile(file);
                }
            }
            interfServiceLogService.updateInterfServiceLog(id, true, "资产附件路径接口数据接入成功！");
        }catch (Exception e){
            interfServiceLogService.updateInterfServiceLog(id, false, "资产附件路径接口数据接入失败:"+e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public void insertFile(SysUploadFileInfo file) {
        this.uploadFileInfoMapper.insert(file);
    }
}

